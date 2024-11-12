document.addEventListener("DOMContentLoaded", function() {



    editUpdateSelectedUsers();

    document.body.addEventListener("click", function(event) {
        if (event.target && event.target.matches("a[data-id][data-group-id]")) {
            openEditModal(event)

            const link = event.target;
            const editPaymentForm = document.getElementById("editPaymentForm");
            const expenseId = link.getAttribute('data-id');
            const groupId = link.getAttribute('data-group-id');

            const actionUrl = `/group/expenses/edit/${groupId}/${expenseId}`;
            editPaymentForm.setAttribute("action", actionUrl);
        }
    });
});


function openEditModal(event) {
    const link = event.target;

    const expenseId = link.getAttribute('data-id');
    const groupId = link.getAttribute('data-group-id');

    fetch(`/group/expenses/edit/${groupId}/${expenseId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Error al recuperar los datos del expense");
            }
            return response.json();
        })
        .then(data => {
            if (data && data.expense) {
                document.getElementById('edit_payment_concept').value = data.expense.expense_name || '';
                document.getElementById('edit_payment_amount').value = data.expense.amount || '';
                document.getElementById('edit_payer_select').value = data.expense.originUser.id || '';
                document.getElementById('edit_divided_payment').value = data.expense.share_method || '';

                const payerSelect = document.getElementById('edit_payer_select');

                document.querySelectorAll('.user-edit-checkbox').forEach(checkbox => {
                    const userId = checkbox.value;

                    // Comprobamos si el checkbox debe estar marcado
                    if (data.users && data.users.includes(parseInt(userId))) {
                        checkbox.checked = true;
                    }
                });

                editUpdateSelectedUsers();

                data.debts.forEach( (debt,index) => {
                    document.getElementsByClassName('edit-debt')[index].value = debt || '';
                })

            } else {
                console.error("Datos del expense no encontrados");
            }

            const modal = document.getElementById('editPaymentModal');

            if (modal) {
                modal.classList.remove("hidden")
                modal.classList.add("h-screen")
            } else {
                console.error("El modal no se encontró en el DOM.");
            }
        })
        .catch(error => {
            console.error("Hubo un problema al cargar los datos:", error);
        });
}

function closeModal() {
    const modal = document.getElementById('editPaymentModal');
    if (modal) {
        modal.classList.add("hidden")
    }
}
document.getElementById("editPaymentForm").addEventListener("submit", function(event) {
    event.preventDefault();
    this.submit();
});

function editUpdateSelectedUsers() {
    const checkboxes = document.querySelectorAll('.user-edit-checkbox');
    const selectedUsersContainer = document.getElementById('edit-selected-users');
    const amount = parseFloat(document.getElementById('edit_payment_amount').value) || 0;
    const shareMethod = document.getElementById('edit_divided_payment').value || "PARTESIGUALES";

    selectedUsersContainer.innerHTML = ''; // Limpiar contenido previo

    let selectedUsers = [];
    const debts = [];

    // Recorremos las checkboxes y recogemos los usuarios seleccionados
    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            const label = document.querySelector(`label[for="${checkbox.id}"]`);
            const userName = label ? label.textContent : '';
            const userId = checkbox.value; // Usa el valor del checkbox como userId
            selectedUsers.push({ userName, userId });
        }
    });

    // Si no hay usuarios seleccionados, mostrar el mensaje predeterminado
    if (selectedUsers.length === 0) {
        selectedUsersContainer.innerHTML = 'No hay participantes seleccionados.';
        return;
    }

    // Lógica según el método de división de la cantidad
    if (shareMethod === 'PARTESIGUALES') {
        const equalShare = (amount / selectedUsers.length).toFixed(2);

        selectedUsers.forEach((user) => {
            const userDiv = document.createElement('div');
            userDiv.classList.add('grid', 'grid-cols-3', 'gap-4', 'items-center', 'mb-2');

            const nameSpan = document.createElement('span');
            nameSpan.classList.add('mr-4');
            nameSpan.textContent = user.userName;

            const userDebtInput = document.createElement('input');
            userDebtInput.textContent = equalShare;
            userDebtInput.classList.add('py-2', 'px-0', 'w-40', 'text-sm', 'text-slate-700', 'bg-transparent',
                'border-0', 'border-b-2', 'border-slate-400', 'appearance-none',
                'focus:outline-none', 'focus:ring-0', 'focus:border-sky-500', 'peer', 'edit-debt');

            userDebtInput.setAttribute('name', `debts[${user.userId}]`);
            userDebtInput.setAttribute('readonly', `readonly`);

            debts[user.userId] = equalShare;

            userDebtInput.value = equalShare;

            userDiv.appendChild(nameSpan);
            userDiv.appendChild(userDebtInput);

            selectedUsersContainer.appendChild(userDiv);
        });

    } else if (shareMethod === 'PARTESDESIGUALES') {
        selectedUsers.forEach((user) => {
            const userDiv = document.createElement('div');
            userDiv.classList.add('grid', 'grid-cols-3', 'gap-4', 'items-center', 'mb-2');

            const nameSpan = document.createElement('span');
            nameSpan.classList.add('mr-4');
            nameSpan.textContent = user.userName;

            const userDebtInput = document.createElement('input');
            userDebtInput.type = 'number';
            userDebtInput.placeholder = 'Cantidad';

            userDebtInput.classList.add('edit-user-input', 'py-2', 'px-0', 'w-40', 'text-sm', 'text-slate-700', 'bg-transparent',
                'border-0', 'border-b-2', 'border-slate-400', 'appearance-none', 'focus:outline-none', 'focus:ring-0',
                'focus:border-sky-500', 'peer', 'edit-debt');

            userDebtInput.setAttribute('name', `debts[${user.userId}]`);
            userDebtInput.setAttribute('step', 'any');

            debts[user.userId] = 0;

            // Escuchar cambios en los inputs de deuda
            userDebtInput.addEventListener('input', function () {
                let totalEnteredAmount = 0;

                document.querySelectorAll('.edit-user-input').forEach(input => {
                    totalEnteredAmount += parseFloat(input.value) || 0;
                });

                editValidateAmount(totalEnteredAmount, amount);
            });

            userDiv.appendChild(nameSpan);
            userDiv.appendChild(userDebtInput);

            selectedUsersContainer.appendChild(userDiv);
        });

    } else if (shareMethod === 'PORCENTAJES') {
        selectedUsers.forEach((user) => {
            const userDiv = document.createElement('div');
            userDiv.classList.add('grid', 'grid-cols-3', 'gap-4', 'items-center', 'mb-2');

            const nameSpan = document.createElement('span');
            nameSpan.classList.add('mr-4');
            nameSpan.textContent = user.userName;

            const userDebtInput = document.createElement('input');
            userDebtInput.type = 'number';
            userDebtInput.placeholder = 'Porcentaje';

            userDebtInput.classList.add('edit-user-input', 'py-2', 'px-0', 'w-40', 'text-sm', 'text-slate-700', 'bg-transparent',
                'border-0', 'border-b-2', 'border-slate-400', 'appearance-none', 'focus:outline-none', 'focus:ring-0',
                'focus:border-sky-500', 'peer', 'edit-debt');

            userDebtInput.setAttribute('name', `debts[${user.userId}]`);
            userDebtInput.setAttribute('step', 'any');

            debts[user.userId] = 0;

            // Escuchar cambios en los inputs de porcentaje
            userDebtInput.addEventListener('input', function () {
                let totalEnteredAmount = 0;

                document.querySelectorAll('.edit-user-input').forEach(input => {
                    totalEnteredAmount += parseFloat(input.value) || 0;
                });

                editValidateAmountPorcentaje(totalEnteredAmount, 100);
            });

            userDiv.appendChild(nameSpan);
            userDiv.appendChild(userDebtInput);

            selectedUsersContainer.appendChild(userDiv);
        });
    }
}

// Función para validar que la cantidad ingresada coincida con la cantidad total
function editValidateAmount(totalEnteredAmount, amount) {
    const errorMessageElement = document.getElementById('error-message');

    if (!errorMessageElement) {
        const newErrorMessage = document.createElement('div');
        newErrorMessage.id = 'error-message';
        newErrorMessage.classList.add('text-red-600', 'mt-2');
        document.getElementById('edit-selected-users').appendChild(newErrorMessage);
    }

    if (totalEnteredAmount !== amount) {
        document.getElementById('error-message').textContent = 'La suma de las cantidades no es igual a la cantidad total.';
    } else {
        document.getElementById('error-message').textContent = '';
    }
}

// Función para validar el porcentaje
function editValidateAmountPorcentaje(totalEnteredAmount, amount) {
    const errorMessageElement = document.getElementById('error-message');

    if (!errorMessageElement) {
        const newErrorMessage = document.createElement('div');
        newErrorMessage.id = 'error-message';
        newErrorMessage.classList.add('text-red-600', 'mt-2');
        document.getElementById('edit-selected-users').appendChild(newErrorMessage);
    }

    if (totalEnteredAmount !== amount) {
        document.getElementById('error-message').textContent = 'La suma de los porcentajes no es igual al 100%.';
    } else {
        document.getElementById('error-message').textContent = '';
    }
}

// Limpiar el formulario y los emails al cerrar la modal
document.querySelector('[data-modal-hide="editPaymentModal"]').addEventListener('click', function() {
    // Restablecer el formulario
    const form = document.getElementById('editPaymentForm');
    form.reset(); // Esto vaciará todos los inputs

    // Limpiar el contenedor de usuarios seleccionados
    const selectedUsersContainer = document.getElementById('edit-selected-users');
    selectedUsersContainer.innerHTML = ''; // Vacía el contenido del div dinámico

    // Mostrar mensaje predeterminado cuando está vacío
    const noUsersMessage = document.createElement('div');
    noUsersMessage.textContent = 'No hay participantes seleccionados.';
    selectedUsersContainer.appendChild(noUsersMessage);

    // Limpieza de errores
    const errorMessage = document.getElementById('error-message');
    if (errorMessage) {
        errorMessage.textContent = ''; // Limpia el mensaje de error, si existe
    }
});

