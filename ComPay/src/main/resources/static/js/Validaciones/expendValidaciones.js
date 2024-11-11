document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("paymentForm");
    const payerSelect = document.getElementById("payer_select").value;
    const pagoCantidad = document.getElementById("payment_amount").value;
    const conceptoPago = document.getElementById("payment_concept").value;
    const checkboxUsers = document.getElementById("checkbox");
    const dividedPayment = document.getElementById("divided_payment").value;
    const generalErrorContainer = document.getElementById("divErrores"); // Asumiendo que ya existe en el HTML
    generalErrorContainer.style.display = "none"; // Asegurarse que está oculto inicialmente

    function addErrorMessage(message) {
        const errorItem = document.createElement("li");
        errorItem.textContent = message;
        generalErrorContainer.querySelector("ul").appendChild(errorItem);
    }

    form.addEventListener("submit", function (event){
        event.preventDefault();
        let errors = [];
        generalErrorContainer.style.display = "none"; // Limpiar y ocultar errores previos
        generalErrorContainer.querySelector("ul").innerHTML = ""; // Limpiar lista de errores

        if(!payerSelect.trim() || !pagoCantidad.trim() || !conceptoPago.trim()){
            errors.push("Ningún campo puede ir vacío");
        }
        if(payerSelect !== "PARTESIGUALES" && payerSelect !== "PARTESDESIGUALES" && payerSelect !== "PORCENTAJES"){
            errors.push("No existe este metodo de división del pago")
        }
        if (errors.length > 0) {
            generalErrorContainer.style.display = "block"; // Mostrar el contenedor de errores
            errors.forEach(error => addErrorMessage(error));
        } else {
            form.submit(); // Enviar formulario si no hay errores
        }

    });

});
