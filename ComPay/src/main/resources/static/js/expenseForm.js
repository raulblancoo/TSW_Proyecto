document.addEventListener('DOMContentLoaded', function () {
    updateSelectedUsers();
});

document.getElementById("paymentForm").addEventListener("submit", function(event) {
    // Evita el comportamiento predeterminado del formulario (reenvío y borrado de campos)
    event.preventDefault();
    alert("hola");
    alert(new FormData(document.getElementById("paymentForm")).get("debts[2]"))


    // Aquí puedes agregar lógica para mantener el estado de los campos, si es necesario
    // Por ejemplo, podrías recoger los valores de los correos electrónicos y almacenarlos para restaurarlos

    // Llamar a la función para enviar el formulario usando AJAX, si es necesario
    // Si no es necesario usar AJAX, puedes omitir el preventDefault y dejar que el formulario se envíe.
    this.submit();
});

function updateSelectedUsers() {
    const checkboxes = document.querySelectorAll('.user-checkbox');
    const selectedUsersContainer = document.getElementById('selected-users');
    const amount = parseFloat(document.getElementById('payment_amount').value) || 0;
    const shareMethod = document.getElementById('divided_payment').value || "PARTESIGUALES";

    selectedUsersContainer.innerHTML = '';
    let selectedUsers = [];
    const debts = [];

    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            const label = document.querySelector(`label[for="${checkbox.id}"]`);
            const userName = label ? label.textContent : '';
            const userId = checkbox.value; // Usa el valor del checkbox como userId
            selectedUsers.push({ userName, userId });
        }
    });

    if (selectedUsers.length === 0) {
        selectedUsersContainer.innerHTML = 'No hay participantes seleccionados.';
        return;
    }


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
                'focus:outline-none', 'focus:ring-0', 'focus:border-sky-500', 'peer');

            userDebtInput.setAttribute('name', `debts[${user.userId}]`);
            userDebtInput.setAttribute('readonly', `readonly`);

            debts[user.id] = equalShare;

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


            userDebtInput.classList.add('user-input', 'py-2', 'px-0', 'w-40', 'text-sm', 'text-slate-700', 'bg-transparent',
                'border-0', 'border-b-2', 'border-slate-400', 'appearance-none', 'focus:outline-none', 'focus:ring-0',
                'focus:border-sky-500', 'peer');

            userDebtInput.setAttribute('name', `debts[${user.userId}]`);
            userDebtInput.setAttribute('step','any');

            debts[user.id] = 0;

            userDebtInput.addEventListener('input', function () {
                totalEnteredAmount = 0;

                document.querySelectorAll('.user-input').forEach(input => {
                    totalEnteredAmount += parseFloat(input.value) || 0;
                });

                validateAmount(totalEnteredAmount, amount);
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


            userDebtInput.classList.add('user-input', 'py-2', 'px-0', 'w-40', 'text-sm', 'text-slate-700', 'bg-transparent',
                'border-0', 'border-b-2', 'border-slate-400', 'appearance-none', 'focus:outline-none', 'focus:ring-0',
                'focus:border-sky-500', 'peer');

            userDebtInput.setAttribute('name', `debts[${user.userId}]`);
            userDebtInput.setAttribute('step','any');
            // userDebtInput.setAttribute('field', `*{debts[${user.userId}]}`);

            debts[user.id] = 0;

            userDebtInput.addEventListener('input', function () {
                totalEnteredAmount = 0;

                document.querySelectorAll('.user-input').forEach(input => {
                    totalEnteredAmount += parseFloat(input.value) || 0;
                });

                validateAmountPorcentaje(totalEnteredAmount, 100);
            });

            userDiv.appendChild(nameSpan);
            userDiv.appendChild(userDebtInput);

            selectedUsersContainer.appendChild(userDiv);
        });
    }
}


function validateAmount(totalEnteredAmount, amount) {
    const errorMessageElement = document.getElementById('error-message');

    if (!errorMessageElement) {
        const newErrorMessage = document.createElement('div');
        newErrorMessage.id = 'error-message';
        newErrorMessage.classList.add('text-red-600', 'mt-2');
        document.getElementById('selected-users').appendChild(newErrorMessage);
    }

    if (totalEnteredAmount !== amount) {
        document.getElementById('error-message').textContent = 'La suma de las cantidades no es igual a la cantidad total.';
    } else {
        document.getElementById('error-message').textContent = '';
    }
}

function validateAmountPorcentaje(totalEnteredAmount, amount) {
    const errorMessageElement = document.getElementById('error-message');

    if (!errorMessageElement) {
        const newErrorMessage = document.createElement('div');
        newErrorMessage.id = 'error-message';
        newErrorMessage.classList.add('text-red-600', 'mt-2');
        document.getElementById('selected-users').appendChild(newErrorMessage);
    }

    if (totalEnteredAmount !== amount) {
        document.getElementById('error-message').textContent = 'La suma de las cantidades no es igual a la cantidad total.';
    } else {
        document.getElementById('error-message').textContent = '';
    }
}

// Limpiar el formulario y los emails al cerrar la modal
document.querySelector('[data-modal-hide="paymentModal"]').addEventListener('click', function() {
    // Restablecer el formulario
    const form = document.getElementById('paymentForm');
    form.reset(); // Esto vaciará todos los inputs

    // Limpiar el contenedor de usuarios seleccionados
    const selectedUsersContainer = document.getElementById('selected-users');
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
