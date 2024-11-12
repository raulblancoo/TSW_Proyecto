document.addEventListener("DOMContentLoaded", function () {
    const submitButton = document.getElementById('submitPayment');
    const expensesForm = document.getElementById('expensesForm');

    // Manejar el envío del formulario
    submitButton.addEventListener('click', function (event) {
        event.preventDefault();
        if (expensesForm.checkValidity()) {

            console.log('Form data:', {
                userName: expensesForm.userName.value,
                amount: expensesForm.amount.value,
                description: expensesForm.description.value
            });

            document.querySelector('[data-modal-hide="paymentModal"]').click();
        } else {
            expensesForm.reportValidity();
        }
    })
});
