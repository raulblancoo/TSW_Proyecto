document.addEventListener('DOMContentLoaded', function () {
    updateSelectedUsers();
});

function updateSelectedUsers() {

    const checkboxes = document.querySelectorAll('.user-checkbox');
    const selectedUsersContainer = document.getElementById('selected-users');
    const amount = parseFloat(document.getElementById('payment_amount').value) || 0;
    const shareMethod = document.getElementById('divided_payment').value || "PARTESIGUALES";
    errorMessage = true;

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
            userDiv.classList.add('grid', 'grid-cols-1', 'md:grid-cols-2','gap-4', 'items-center', 'mb-2');

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
            userDiv.classList.add('grid', 'grid-cols-1', 'md:grid-cols-2', 'gap-4', 'items-center', 'mb-2');

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
            userDiv.classList.add('grid', 'grid-cols-1', 'md:grid-cols-2', 'gap-4', 'items-center', 'mb-2');

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

document.querySelector('[data-modal-hide="paymentModal"]').addEventListener('click', function() {
    const form = document.getElementById('paymentForm');
    form.reset();

    const selectedUsersContainer = document.getElementById('selected-users');
    selectedUsersContainer.innerHTML = '';

    const noUsersMessage = document.createElement('div');
    noUsersMessage.textContent = 'No hay participantes seleccionados.';
    selectedUsersContainer.appendChild(noUsersMessage);

    const errorMessage = document.getElementById('error-message');
    if (errorMessage) {
        errorMessage.textContent = '';
    }
});
