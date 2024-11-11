document.addEventListener("DOMContentLoaded", function () {
    // Evento para añadir un email a la lista
    document.getElementById('add-email-btn-expenses').addEventListener('click', function () {
        const emailInput = document.getElementById('search');
        const emailValue = emailInput.value.trim();
        const emailList = document.getElementById('email-list');
        const emailsField = document.getElementById('emails');

        // Expresión regular para validar el email
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (emailValue && emailRegex.test(emailValue)) {
            // Verifica que el email no esté ya en la lista
            const existingEmails = Array.from(emailList.children).map(li => li.firstChild.textContent);
            if (!existingEmails.includes(emailValue)) {
                // Crear un nuevo elemento de lista para el email
                const li = document.createElement('li');
                li.classList.add('flex', 'justify-between', 'items-center', 'text-sm', 'text-slate-700', 'bg-gray-100', 'border', 'border-slate-300', 'p-2', 'rounded-md', 'mb-2');
                li.innerHTML = `
                    <span class="flex-1">${emailValue}</span>
                    <button class="remove-email-btn text-slate-500 hover:text-red-600 px-2" title="Eliminar">&times;</button>
                `;
                emailList.appendChild(li);
                emailList.classList.remove('hidden'); // Mostrar la lista cuando haya emails

                // Actualizar el campo oculto con los emails añadidos
                let emailsArray = emailsField.value ? emailsField.value.split(',') : [];
                emailsArray.push(emailValue);
                emailsField.value = emailsArray.join(',');

                // Limpiar el input de email
                emailInput.value = '';

                // Evento para eliminar el email de la lista y del campo oculto
                li.querySelector('.remove-email-btn').addEventListener('click', function () {
                    li.remove();
                    emailsArray = emailsArray.filter(email => email !== emailValue);
                    emailsField.value = emailsArray.join(',');

                    // Ocultar la lista solo si no quedan emails
                    if (!emailsArray.length) {
                        emailList.classList.add('hidden');
                    }
                });
            } else {
                alert('Este email ya ha sido añadido.');
            }
        } else {
            alert('Por favor, introduce una dirección de correo válida.');
        }

        // Solo mostrar la lista si tiene elementos
        if (emailList.children.length > 0) {
            emailList.classList.remove('hidden');
        }
    });

    // Validar antes de enviar el formulario
    document.getElementById('newMembersForm').addEventListener('submit', function (event) {
        const emailList = document.getElementById('email-list');
        if (emailList.children.length === 0) {
            event.preventDefault(); // Evita que se envíe el formulario
            alert('Debes añadir al menos un email antes de enviar el formulario.');
        }
    });

    // Limpiar el formulario y los emails al cerrar la modal
    document.querySelector('[data-modal-hide="newMemberModal"]').addEventListener('click', function () {
        // Restablecer el formulario
        const form = document.getElementById('newMembersForm');
        form.reset(); // Esto vaciará todos los inputs

        // Limpiar la lista de emails y el campo oculto
        const emailList = document.getElementById('email-list');
        const emailsField = document.getElementById('emails');

        // Limpiar la lista de emails y el campo oculto
        emailList.innerHTML = ''; // Elimina todos los elementos de la lista
        emailsField.value = ''; // Vaciar el campo oculto
        emailList.classList.add('hidden'); // Ocultar la lista
    });
});
