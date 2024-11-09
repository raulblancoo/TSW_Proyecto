document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("group-form");
    const emailInput = document.getElementById("search");
    const emailList = document.getElementById("email-list");
    const emailsField = document.getElementById("emails");
    const groupNameInput = document.getElementById("group-name");

    // Crear contenedor de errores al final del formulario
    const generalErrorContainer = document.createElement("div");
    generalErrorContainer.className = "text-red-500 text-sm mt-4";
    form.appendChild(generalErrorContainer);

    // Función para validar el campo de nombre del grupo
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

    // Evento para añadir un email a la lista
    document.getElementById("add-email-btn").addEventListener("click", function () {
        const emailValue = emailInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        generalErrorContainer.textContent = ""; // Limpiar mensaje de error general

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

                emailInput.value = "";  // Limpiar el input de email

                li.querySelector(".remove-email-btn").addEventListener("click", function () {
                    li.remove();
                    emailsArray = emailsArray.filter(email => email !== emailValue);
                    emailsField.value = emailsArray.join(",");
                    if (!emailsArray.length) {
                        emailList.classList.add("hidden");
                    }
                });
            } else if(existingEmails.includes(emailValue)){
                generalErrorContainer.textContent = "Este email ya ha sido añadido.";
            }
            else if(emailValue.includes(userEmail)){
                generalErrorContainer.textContent = "No puedes añadirte a la lista de participantes.";
            }
        } else {
            generalErrorContainer.textContent = "Por favor, introduce una dirección de correo válida.";
        }
    });

    // Validar antes de enviar el formulario
    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Detener envío para validar primero
        generalErrorContainer.innerHTML = ""; // Limpiar errores previos
        let errors = [];

        // Validar nombre del grupo
        const groupNameError = validateGroupName();
        if (groupNameError) {
            errors.push(groupNameError);
        }

        // Validar si hay al menos un email en la lista
        if (emailList.children.length === 0) {
            errors.push("Debes añadir al menos un email antes de enviar el formulario.");
        }

        // Mostrar errores si hay alguno
        if (errors.length > 0) {
            generalErrorContainer.innerHTML = errors.map(error => `<p>${error}</p>`).join("");
            generalErrorContainer.classList.add('text-red-600');
        } else {
            form.submit(); // Enviar formulario si no hay errores
        }
    });

    // Limpiar el formulario y los emails al cerrar la modal
    document.querySelector('[data-modal-hide="groupModal"]').addEventListener("click", function () {
        form.reset();
        emailList.innerHTML = "";
        emailsField.value = "";
        emailList.classList.add("hidden");

        generalErrorContainer.innerHTML = ""; // Limpiar mensajes de error
    });
});
