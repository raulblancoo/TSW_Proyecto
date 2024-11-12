document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("paymentForm");
    const payerSelect = document.getElementById("payer_select");
    const pagoCantidad = document.getElementById("payment_amount");
    const conceptoPago = document.getElementById("payment_concept");
    const dividedPayment = document.getElementById("divided_payment");

    const generalErrorContainer = document.getElementById("divErrores"); // Asumiendo que ya existe en el HTML
    generalErrorContainer.style.display = "none"; // Asegurarse que está oculto inicialmente

    function addErrorMessage(message) {
        const errorList = generalErrorContainer.querySelector("ul");
        const existingErrors = Array.from(errorList.children).map(item => item.textContent);
        if (!existingErrors.includes(message)) {
            const errorItem = document.createElement("li");
            errorItem.textContent = message;
            errorList.appendChild(errorItem);
        }
    }

    form.addEventListener("submit", function (event){
        const imgElement = document.getElementById("main-lang-flag").getAttribute("src");
        language = "sp";
        event.preventDefault();
        let errors = [];
        generalErrorContainer.querySelector("ul").innerHTML = ""; // Limpiar lista de errores
        generalErrorContainer.style.display = "none"; // Limpiar y ocultar errores previos
        if(imgElement.includes("united")){
            language = "unit";
        }else if(imgElement.includes("ita")){
            language = "ita";
        }else if(imgElement.includes("cz")){
            language = "cz"
        }else if(imgElement.includes("port")){
            language = "por"
        }
        switch (language) {
            case "sp": // Español
                if (pagoCantidad.value <= 0) {
                    errors.push("La cantidad del pago no puede ser 0.");
                }
                if (!payerSelect.value.trim() || !pagoCantidad.value.trim() || !conceptoPago.value.trim() || !validarReparto() || !comprobacionCheckbox()) {
                    errors.push("Ningún campo puede ir vacío.");
                } else if (sumasReparto() != pagoCantidad.value) {
                    errors.push("El reparto especificado no suma la cantidad total");
                }
                if (dividedPayment.value !== "PARTESIGUALES" && dividedPayment.value !== "PARTESDESIGUALES" && dividedPayment.value !== "PORCENTAJES") {
                    errors.push("No existe este método de división del pago.");
                }
                break;

            case "ita": // Italiano
                if (pagoCantidad.value <= 0) {
                    errors.push("L'importo del pagamento non può essere 0.");
                }
                if (!payerSelect.value.trim() || !pagoCantidad.value.trim() || !conceptoPago.value.trim() || !validarReparto() || !comprobacionCheckbox()) {
                    errors.push("Nessun campo può essere vuoto.");
                } else if (sumasReparto() != pagoCantidad.value) {
                    errors.push("La ripartizione specificata non corrisponde all'importo totale.");
                }
                if (dividedPayment.value !== "PARTESIGUALES" && dividedPayment.value !== "PARTESDESIGUALES" && dividedPayment.value !== "PORCENTAJES") {
                    errors.push("Metodo di divisione del pagamento non esistente.");
                }
                break;

            case "unit": // Inglés
                if (pagoCantidad.value <= 0) {
                    errors.push("The payment amount cannot be 0.");
                }
                if (!payerSelect.value.trim() || !pagoCantidad.value.trim() || !conceptoPago.value.trim() || !validarReparto() || !comprobacionCheckbox()) {
                    errors.push("No field can be empty.");
                } else if (sumasReparto() != pagoCantidad.value) {
                    errors.push("The specified distribution does not sum to the total amount.");
                }
                if (dividedPayment.value !== "PARTESIGUALES" && dividedPayment.value !== "PARTESDESIGUALES" && dividedPayment.value !== "PORCENTAJES") {
                    errors.push("This payment division method does not exist.");
                }
                break;

            case "por": // Portugués
                if (pagoCantidad.value <= 0) {
                    errors.push("O valor do pagamento não pode ser 0.");
                }
                if (!payerSelect.value.trim() || !pagoCantidad.value.trim() || !conceptoPago.value.trim() || !validarReparto() || !comprobacionCheckbox()) {
                    errors.push("Nenhum campo pode estar vazio.");
                } else if (sumasReparto() != pagoCantidad.value) {
                    errors.push("A divisão especificada não soma ao valor total.");
                }
                if (dividedPayment.value !== "PARTESIGUALES" && dividedPayment.value !== "PARTESDESIGUALES" && dividedPayment.value !== "PORCENTAJES") {
                    errors.push("Método de divisão do pagamento não existente.");
                }
                break;

            case "cz": // Checo
                if (pagoCantidad.value <= 0) {
                    errors.push("Částka platby nemůže být 0.");
                }
                if (!payerSelect.value.trim() || !pagoCantidad.value.trim() || !conceptoPago.value.trim() || !validarReparto() || !comprobacionCheckbox()) {
                    errors.push("Žádné pole nemůže být prázdné.");
                } else if (sumasReparto() != pagoCantidad.value) {
                    errors.push("Uvedené rozdělení nesouhlasí s celkovou částkou.");
                }
                if (dividedPayment.value !== "PARTESIGUALES" && dividedPayment.value !== "PARTESDESIGUALES" && dividedPayment.value !== "PORCENTAJES") {
                    errors.push("Tento způsob rozdělení platby neexistuje.");
                }
                break;

            default:
                break;
        }
        if (errors.length > 0) {
            generalErrorContainer.style.display = "block"; // Mostrar el contenedor de errores
            errors.forEach(error => addErrorMessage(error));
        } else {
            form.submit(); // Enviar formulario si no hay errores
        }

    });
    function comprobacionCheckbox(){
        // Seleccionar todos los checkboxes con el name "destinationUsers"
        const checkboxes = document.querySelectorAll('input[name="destinationUsers"]');

        // Filtrar los checkboxes que están marcados
        const selectedUsers = Array.from(checkboxes)
            .filter(checkbox => checkbox.checked)
            .map(checkbox => checkbox.value); // Obtener el valor (id del usuario) de los seleccionados

        if (selectedUsers.length > 0) {
            return true;
        } else {
            return false;
        }


    }
    function validarReparto() {
        const inputsReparto = document.querySelectorAll("#userValuesForm input[type='number']");
        for (let input of inputsReparto) {
            if (input.value === "" || parseFloat(input.value) <= 0) {
                return false;
            }
        }
        return true;
    }
    function sumasReparto(){
        const inputsReparto = document.querySelectorAll("#userValuesForm input[type='number']");
        toret = 0;
        for (let input of inputsReparto) {
            toret+=parseFloat(input.value);
        }
        return toret;
    }


    });


