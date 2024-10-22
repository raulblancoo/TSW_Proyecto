document.getElementById("addGroupBtn").addEventListener("click", function () {
    // Mostrar el modal
    document.getElementById("modal").classList.remove("hidden");

    // Cargar el contenido del modal desde el backend (fetch a la URL)
    fetch('/groups/create')
        .then(response => response.text())
        .then(html => {
            // Insertar el HTML cargado dentro del modal
            document.getElementById("modalContent").innerHTML = html;
        })
        .catch(error => {
            console.error('Error al cargar el contenido del modal:', error);
        });
});

// Cerrar el modal cuando se haga clic fuera del contenido
document.getElementById("modal").addEventListener("click", function (event) {
    if (event.target === this) {
        this.classList.add("hidden");
    }
});

document.addEventListener('DOMContentLoaded', function() {
    // Utiliza delegación de eventos para manejar el botón que se carga dinámicamente
    document.body.addEventListener('click', function(event) {
        if (event.target && event.target.id === 'add-email-btn') {
            const emailInput = document.getElementById('search');
            const emailValue = emailInput.value;
            const emailList = document.getElementById('email-list');
            const emailsField = document.getElementById('emails');

            if (emailValue) {
                const li = document.createElement('li');
                li.textContent = emailValue;
                emailList.appendChild(li);

                let emailsArray = emailsField.value ? emailsField.value.split(',') : [];
                emailsArray.push(emailValue);
                emailsField.value = emailsArray.join(',');

                emailInput.value = '';
            } else {
                alert('Please enter a valid email address.');
            }
        }
    });
});