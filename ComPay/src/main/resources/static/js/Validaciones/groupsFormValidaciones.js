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
        }else if(imgElement.includes("cz")){
            language = "cz"
        }else if(imgElement.includes("port")){
            language = "por"
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

            case "por":
                if (!groupNameInput.value.trim()) {
                    toret = toret.concat("O nome do grupo é obrigatório. ");
                }
                if (groupNameInput.value.length > 20) {
                    toret = toret.concat("O nome do grupo não deve exceder 20 caracteres. ");
                }
                if (userGroups.includes(groupNameInput.value)) {
                    toret = toret.concat("Este nome de grupo já existe. ");
                }
                if (!regexGroupName.test(groupNameInput.value)) {
                    toret = toret.concat("O nome do grupo contém caracteres não permitidos. ");
                }
                break;

            case "cz":
                if (!groupNameInput.value.trim()) {
                    toret = toret.concat("Název skupiny je povinný. ");
                }
                if (groupNameInput.value.length > 20) {
                    toret = toret.concat("Název skupiny nesmí přesáhnout 20 znaků. ");
                }
                if (userGroups.includes(groupNameInput.value)) {
                    toret = toret.concat("Název této skupiny již existuje. ");
                }
                if (!regexGroupName.test(groupNameInput.value)) {
                    toret = toret.concat("Název skupiny obsahuje nepovolené znaky. ");
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
                    case "por": // Portugués
                        errorMessage = "Este email já foi adicionado.";
                        break;
                    case "cz": // Checo
                        errorMessage = "Tento email byl již přidán.";
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
                    case "por":
                        errorMessage = "Você não pode se adicionar à lista de participantes.";
                        break;
                    case "cz":
                        errorMessage = "Nemůžete se přidat do seznamu účastníků.";
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
                case "por":
                    errorMessage = "Por favor, insira um endereço de e-mail válido.";
                    break;
                case "cz":
                    errorMessage = "Zadejte prosím platnou e-mailovou adresu.";
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
                case "por": // Portugués
                    errorMessage = "Você deve adicionar pelo menos um e-mail antes de enviar o formulário.";
                    break;
                case "cz": // Checo
                    errorMessage = "Musíte přidat alespoň jeden e-mail před odesláním formuláře.";
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
                case "por":
                    errorMessage = "A moeda selecionada não é permitida.";
                    break;
                case "cz":
                    errorMessage = "Vybraná měna není povolena.";
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
