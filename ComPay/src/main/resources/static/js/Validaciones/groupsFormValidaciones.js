document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("group-form");
    const emailInput = document.getElementById("search");
    const emailList = document.getElementById("email-list");
    const emailsField = document.getElementById("emails");
    const groupNameInput = document.getElementById("group-name");
    const currency = document.getElementById("currency")


    const generalErrorContainer = document.getElementById("divErrores");
    generalErrorContainer.style.display = "none";
    function validateGroupName() {
        const imgElement = document.getElementById("main-lang-flag").getAttribute("src");
        language = "sp";
        toret = "";
        const regexGroupName = /^[\x20\x21-\xA8\xAD\xE0-\xED]*$/;
        var userGroups = document.getElementById('userGroups').getAttribute('user-group');
        if(imgElement.includes("united")){
            language = "unit";
        }else if(imgElement.includes("ita")){
            language = "ita";
        }
        switch (language) {
            case "sp":
                if (!groupNameInput.value.trim()) {
                    toret = toret.concat("El nombre del grupo es obligatorio. ");
                }
                if (groupNameInput.value.length > 20) {
                    toret = toret.concat("El nombre del grupo no debe exceder 20 caracteres. ");
                }
                if (userGroups.includes(groupNameInput.value)) {
                    toret = toret.concat("El nombre de este grupo ya existe. ");
                }
                if (!regexGroupName.test(groupNameInput.value)) {
                    toret = toret.concat("El nombre del grupo tiene caracteres no permitidos. ");
                }
                break;

            case "ita":
                if (!groupNameInput.value.trim()) {
                    toret = toret.concat("Il nome del gruppo è obbligatorio. ");
                }
                if (groupNameInput.value.length > 20) {
                    toret = toret.concat("Il nome del gruppo non deve superare 20 caratteri. ");
                }
                if (userGroups.includes(groupNameInput.value)) {
                    toret = toret.concat("Il nome di questo gruppo esiste già. ");
                }
                if (!regexGroupName.test(groupNameInput.value)) {
                    toret = toret.concat("Il nome del gruppo contiene caratteri non consentiti. ");
                }
                break;

            case "unit":
                if (!groupNameInput.value.trim()) {
                    toret = toret.concat("Group name is required. ");
                }
                if (groupNameInput.value.length > 20) {
                    toret = toret.concat("Group name must not exceed 20 characters. ");
                }
                if (userGroups.includes(groupNameInput.value)) {
                    toret = toret.concat("This group name already exists. ");
                }
                if (!regexGroupName.test(groupNameInput.value)) {
                    toret = toret.concat("Group name contains invalid characters. ");
                }
                break;
            default:
                toret = "Idioma no soportado.";
                break;
        }

        return toret;
    }


    document.getElementById("add-email-btn").addEventListener("click", function () {
        const emailValue = emailInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const imgElement = document.getElementById("main-lang-flag").getAttribute("src");
        language = "sp";
        toret = "";
        if(imgElement.includes("united")){
            language = "unit";
        }else if(imgElement.includes("ita")){
            language = "ita";
        }

        generalErrorContainer.style.display = "none";

        if (emailValue && emailRegex.test(emailValue)) {
            const existingEmails = Array.from(emailList.children).map(li => li.querySelector("span").textContent);
            var userEmail = document.getElementById('userEmail').getAttribute('user-email');
            if (!existingEmails.includes(emailValue) && !emailValue.includes(userEmail)) {
                const li = document.createElement("li");
                li.classList.add("flex", "justify-between", "items-center", "text-sm", "text-slate-700", "bg-gray-100", "border", "border-slate-300", "p-2", "rounded-md", "mb-2");
                li.innerHTML = `
                    <span class="flex-1">${emailValue}</span>
                    <button class="remove-email-btn text-slate-500 hover:text-red-600 px-2" title="Eliminar">&times;</button>
                `;
                emailList.appendChild(li);
                emailList.classList.remove("hidden");

                let emailsArray = emailsField.value ? emailsField.value.split(",") : [];
                emailsArray.push(emailValue);
                emailsField.value = emailsArray.join(",");

                emailInput.value = "";

                li.querySelector(".remove-email-btn").addEventListener("click", function () {
                    li.remove();
                    emailsArray = emailsArray.filter(email => email !== emailValue);
                    emailsField.value = emailsArray.join(",");
                    if (!emailsArray.length) {
                        emailList.classList.add("hidden");
                    }
                });
            }if (existingEmails.includes(emailValue)) {

                let errorMessage = "";
                switch (language) {
                    case "sp": // Español
                        errorMessage = "Este email ya ha sido añadido.";
                        break;
                    case "ita": // Italiano
                        errorMessage = "Questa email è già stata aggiunta.";
                        break;
                    case "unit": // Inglés
                        errorMessage = "This email has already been added.";
                        break;
                    default:
                        errorMessage = "Idioma no soportado.";
                        break;
                }
                generalErrorContainer.style.display = "block";
                addErrorMessage(errorMessage);

            } else if (emailValue.includes(userEmail)) {
                let errorMessage = "";
                switch (language) {
                    case "sp":
                        errorMessage = "No puedes añadirte a la lista de participantes.";
                        break;
                    case "ita":
                        errorMessage = "Non puoi aggiungerti alla lista dei partecipanti.";
                        break;
                    case "unit":
                        errorMessage = "You cannot add yourself to the participant list.";
                        break;
                    default:
                        errorMessage = "Idioma no soportado.";
                        break;
                }
                generalErrorContainer.style.display = "block";
                addErrorMessage(errorMessage);

            }
        } else {

            let errorMessage = "";
            switch (language) {
                case "sp":
                    errorMessage = "Por favor, introduce una dirección de correo válida.";
                    break;
                case "ita":
                    errorMessage = "Per favore, inserisci un indirizzo email valido.";
                    break;
                case "unit":
                    errorMessage = "Please enter a valid email address.";
                    break;
                default:
                    errorMessage = "Idioma no soportado.";
                    break;
            }
            generalErrorContainer.style.display = "block";
            addErrorMessage(errorMessage);
        }
    });



    function addErrorMessage(message) {
        const errorList = generalErrorContainer.querySelector("ul");
        const existingErrors = Array.from(errorList.children).map(item => item.textContent);
        if (!existingErrors.includes(message)) {
            const errorItem = document.createElement("li");
            errorItem.textContent = message;
            errorList.appendChild(errorItem);
        }
    }


    form.addEventListener("submit", function (event) {
        event.preventDefault();
        generalErrorContainer.style.display = "none";
        generalErrorContainer.querySelector("ul").innerHTML = "";
        let errors = [];


        const groupNameError = validateGroupName();
        if (groupNameError) {
            errors.push(groupNameError);
        }

        if (emailList.children.length === 0) {
            let errorMessage = "";
            switch (language) {
                case "sp": // Español
                    errorMessage = "Debes añadir al menos un email antes de enviar el formulario.";
                    break;
                case "ita": // Italiano
                    errorMessage = "Devi aggiungere almeno un'email prima di inviare il modulo.";
                    break;
                case "unit": // Inglés
                    errorMessage = "You must add at least one email before submitting the form.";
                    break;
                default:
                    errorMessage = "Idioma no soportado.";
                    break;
            }
            errors.push(errorMessage);
        }


        if (currency.value !== "DOLAR" && currency.value !== "EURO") {
            let errorMessage = "";
            switch (language) {
                case "sp":
                    errorMessage = "La moneda seleccionada no está permitida.";
                    break;
                case "ita":
                    errorMessage = "La valuta selezionata non è consentita.";
                    break;
                case "unit":
                    errorMessage = "The selected currency is not allowed.";
                    break;
                default:
                    errorMessage = "Idioma no soportado.";
                    break;
            }
            errors.push(errorMessage);
        }

        if (errors.length > 0) {
            generalErrorContainer.style.display = "block";
            errors.forEach(error => addErrorMessage(error));
        } else {
            form.submit();
        }
    });


    document.querySelector('[data-modal-hide="groupModal"]').addEventListener("click", function () {
        form.reset();
        emailList.innerHTML = "";
        emailsField.value = "";
        emailList.classList.add("hidden");

        generalErrorContainer.style.display = "none";
        generalErrorContainer.querySelector("ul").innerHTML = "";
    });
});
