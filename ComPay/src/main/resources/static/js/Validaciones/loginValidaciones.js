document.addEventListener("DOMContentLoaded", function () {
    const formLogin = document.getElementById("login-form");
    const formRegister = document.getElementById("register-form");

    // Crear contenedores de errores para login y registro con menos espacio inferior
    const errorLoginContainer = document.createElement("div");
    errorLoginContainer.style.color = "#dc2626"; // Color rojo intenso
    errorLoginContainer.style.marginTop = "10px"; // Espaciado superior

    formLogin.appendChild(errorLoginContainer);

    const errorRegisterContainer = document.createElement("div");
    errorRegisterContainer.style.color = "#dc2626";
    errorRegisterContainer.style.marginTop = "10px"; //

    formRegister.appendChild(errorRegisterContainer);

    // Expresiones regulares para validar email y caracteres de nombre
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const regexNames = /^[\x21-\xA8\xAD\xE0-\xED]*$/;

    // Validación de formulario de login
    function validacionesLogin() {
        let errors = [];

        const loginEmail = document.getElementById("loginEmail").value;
        const loginPsw = document.getElementById("loginPsw").value;

        if (!loginEmail.trim()) {
            errors.push("El email no puede ir vacío.");
        } else if (!emailRegex.test(loginEmail)) {
            errors.push("El formato del email es incorrecto.");
        }

        if (!loginPsw.trim()) {
            errors.push("La contraseña no puede ir vacía.");
        }

        return errors;
    }

    // Validación de formulario de registro
    function validacionesRegister() {
        let errors = [];

        const registerName = document.getElementById("registerName").value;
        const registerEmail = document.getElementById("registerEmail").value;
        const registerUserName = document.getElementById("registerUserName").value;
        const registerSurName = document.getElementById("registerSurname").value;
        const registerPsw = document.getElementById("registerPsw").value;

        if (!regexNames.test(registerName) || !regexNames.test(registerUserName) || !regexNames.test(registerSurName)) {
            errors.push("Caracter introducido inválido.");
        }
        if (registerName.length > 20 || registerUserName.length > 20 || registerSurName.length > 20) {
            errors.push("El tamaño máximo por campo es de 20 caracteres.");
        }
        if (!registerName.trim() || !registerUserName.trim() || !registerPsw.trim() || !registerEmail.trim() || !registerSurName.trim()) {
            errors.push("Ningún campo puede ir vacío.");
        } else if (!emailRegex.test(registerEmail)) {
            errors.push("El formato del email es incorrecto.");
        }

        return errors;
    }

    // Validación y visualización de errores en el formulario de login
    formLogin.addEventListener("submit", function (event) {
        event.preventDefault();
        errorLoginContainer.innerHTML = ""; // Limpiar errores previos
        const loginErrors = validacionesLogin();

        if (loginErrors.length > 0) {
            errorLoginContainer.innerHTML = loginErrors.map(error => `${error}</p>`).join("");
        } else {
            formLogin.submit(); // Enviar formulario si no hay errores
        }
    });

    // Validación y visualización de errores en el formulario de registro
    formRegister.addEventListener("submit", function (event) {
        event.preventDefault();
        errorRegisterContainer.innerHTML = ""; // Limpiar errores previos
        const registerErrors = validacionesRegister();

        if (registerErrors.length > 0) {
            errorRegisterContainer.innerHTML = registerErrors.map(error => `${error}</p>`).join("");
        } else {
            formRegister.submit(); // Enviar formulario si no hay errores
        }
    });
});
