document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('add-email-btn-expenses').addEventListener('click', function () {
        const emailInput = document.getElementById('search');
        const emailValue = emailInput.value.trim();
        const emailList = document.getElementById('email-list');
        const emailsField = document.getElementById('emails');


        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (emailValue && emailRegex.test(emailValue)) {
            const existingEmails = Array.from(emailList.children).map(li => li.firstChild.textContent);
            if (!existingEmails.includes(emailValue)) {
                const li = document.createElement('li');
                li.classList.add('flex', 'justify-between', 'items-center', 'text-sm', 'text-slate-700', 'bg-gray-100', 'border', 'border-slate-300', 'p-2', 'rounded-md', 'mb-2');
                li.innerHTML = `
                    <span class="flex-1">${emailValue}</span>
                    <button class="remove-email-btn text-slate-500 hover:text-red-600 px-2" title="Eliminar">&times;</button>
                `;
                emailList.appendChild(li);
                emailList.classList.remove('hidden');

                let emailsArray = emailsField.value ? emailsField.value.split(',') : [];
                emailsArray.push(emailValue);
                emailsField.value = emailsArray.join(',');

                emailInput.value = '';

                li.querySelector('.remove-email-btn').addEventListener('click', function () {
                    li.remove();
                    emailsArray = emailsArray.filter(email => email !== emailValue);
                    emailsField.value = emailsArray.join(',');

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

        if (emailList.children.length > 0) {
            emailList.classList.remove('hidden');
        }
    });

    document.getElementById('newMembersForm').addEventListener('submit', function (event) {
        const emailList = document.getElementById('email-list');
        if (emailList.children.length === 0) {
            event.preventDefault();
            alert('Debes añadir al menos un email antes de enviar el formulario.');
        }
    });

    document.querySelector('[data-modal-hide="newMemberModal"]').addEventListener('click', function () {
        const form = document.getElementById('newMembersForm');
        form.reset();

        const emailList = document.getElementById('email-list');
        const emailsField = document.getElementById('emails');


        emailList.innerHTML = '';
        emailsField.value = '';
        emailList.classList.add('hidden');
    });
});
