document.getElementById("addGroupBtn").addEventListener("click", function () {
    // Mostrar el modal
    document.getElementById("modal").classList.remove("hidden");

    // Cargar el contenido del modal desde el backend (fetch a la URL)
    fetch('/groups/create')
        .then(response => response.text())
        .then(html => {
            // Insertar el HTML cargado dentro del modal
            document.getElementById("modalContent").innerHTML = html;

            // Reasignar el evento para el botón de agregar email después de cargar el contenido
            document.getElementById('add-email-btn').addEventListener('click', function() {
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
            });
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

document.getElementById('addGroupBtn').addEventListener('click', function() {
    document.getElementById('modal').classList.remove('hidden');  // Muestra la modal
    this.classList.add('hidden');  // Oculta el botón
});

// Cerrar modal
document.getElementById('modalCloseBtn').addEventListener('click', function() {
    document.getElementById('modal').classList.add('hidden');  // Oculta la modal
    document.getElementById('addGroupBtn').classList.remove('hidden');  // Vuelve a mostrar el botón
});
