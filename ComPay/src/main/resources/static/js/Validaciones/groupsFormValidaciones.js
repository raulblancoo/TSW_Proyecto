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
        toret = "";
        const regexGroupName = /^[\x21-\xA8\xAD\xE0-\xED]*$/;
        var userGroups = document.getElementById('userGroups').getAttribute('user-group');
        if (!groupNameInput.value.trim()) {
            toret = toret.concat("El nombre del grupo es obligatorio. ");
        }if (groupNameInput.value.length > 20) {
            toret = toret.concat("El nombre del grupo no debe exceder 20 caracteres. ");
        }if(userGroups.includes(groupNameInput.value)){
            toret = toret.concat("El nombre de este grupo ya existe. ")
        }if(!regexGroupName.test(groupNameInput.value)){
            toret = toret.concat("El nombre del grupo tiene caracteres no permitidos");
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
            } else if (existingEmails.includes(emailValue)) {
                generalErrorContainer.style.display = "block";
                addErrorMessage("Este email ya ha sido añadido.");
            } else if (emailValue.includes(userEmail)) {
                generalErrorContainer.style.display = "block";
                addErrorMessage("No puedes añadirte a la lista de participantes.");
            }
        } else {
            generalErrorContainer.style.display = "block";
            addErrorMessage("Por favor, introduce una dirección de correo válida.");
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
            errors.push("Debes añadir al menos un email antes de enviar el formulario.");
        }
        if(currency.value !== "DOLLAR" && currency.value != "EURO"){
            errors.push("La moneda seleccionada no esta permitida")
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
