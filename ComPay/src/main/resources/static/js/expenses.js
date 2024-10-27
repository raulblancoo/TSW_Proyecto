document.addEventListener("DOMContentLoaded", function () {
    // Seleccionar el botón y el formulario
    const submitButton = document.getElementById('submitPayment');
    const expensesForm = document.getElementById('expensesForm');

    // Manejar el envío del formulario
    submitButton.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar el envío tradicional del formulario
        if (expensesForm.checkValidity()) {
            // Simular envío del formulario (puede ser un fetch para AJAX)
            console.log('Form data:', {
                userName: expensesForm.userName.value,
                amount: expensesForm.amount.value,
                description: expensesForm.description.value
            });

            // Aquí podrías enviar los datos con fetch o AJAX
            // fetch('/your-endpoint', { method: 'POST', body: new FormData(expensesForm) })
            //     .then(response => response.json())
            //     .then(data => console.log(data));

            // Ocultar el modal después de enviar
            document.querySelector('[data-modal-hide="paymentModal"]').click();
        } else {
            expensesForm.reportValidity(); // Mostrar validaciones del formulario
        }
    })

});
