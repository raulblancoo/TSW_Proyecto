document.addEventListener("DOMContentLoaded", function (){
    const formLogin = document.getElementById("login-form");
    const formRegister = document.getElementById("register-form");

    const errorLoginContainer = document.createElement("div");
    errorLoginContainer.className = "text-red-500 text-sm mt-4";
    formLogin.appendChild(errorLoginContainer);

    const errorRegisterContainer = document.createElement("div");
    errorRegisterContainer.className = "text-red-500 text-sm mt-4";
    formRegister.appendChild(errorRegisterContainer);

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const regexNames = /^[\x21-\xA8\xAD\xE0-\xED]*$/;


    function validacionesLogin(){
        toret = "\n";

        const loginEmail = document.getElementById("loginEmail").value;
        const loginPsw = document.getElementById("loginPsw").value;
        if(!loginEmail.trim()){
            toret = toret.concat("El email no puede ir vacío. ")
        }else if (!emailRegex.test(loginEmail)){
            toret = toret.concat("El formato del email es incorrecto. ")
        }


        if(!loginPsw.trim()){
            toret = toret.concat("La contraseña no puede ir vacía. ")
        }
        return toret;

    }
    function validacionesRegister(){
        toret = "";

        const registerName = document.getElementById("registerName").value;
        const registerEmail = document.getElementById("registerEmail").value;
        const registerUserName = document.getElementById("registerUserName").value;
        const registerSurName = document.getElementById("registerSurname").value;
        const registerPsw = document.getElementById("registerPsw").value;

        if (!regexNames.test(registerName) || !regexNames.test(registerUserName) || !regexNames.test(registerSurName)){
            toret = toret.concat("Caracter introducido inválido. ");
        }
        if (registerName.length > 20 || registerUserName.length > 20 || registerSurName.length > 20){
            toret = toret.concat("El tamaño máximo por campo es de 20 caracteres. ");
        }

        if (!registerName.trim() || !registerUserName.trim() || !registerPsw.trim() || !registerEmail.trim() || !registerSurName.trim()){
            toret = toret.concat("Ningún campo puede ir vacío. ")
        }else if (!emailRegex.test(registerEmail)){
            toret = toret.concat("El formato del email es incorrecto. ")
        }

        return toret;
    }
    // Validar antes de enviar el formulario
    formLogin.addEventListener("submit", function (event) {
        event.preventDefault(); // Detener envío para validar primero
        errorLoginContainer.innerHTML = ""; // Limpiar errores previos
        let errors = [];

        // Validar nombre del grupo
        const loginError = validacionesLogin();
        if (loginError) {
            errors.push(loginError);
        }
        // Mostrar errores si hay alguno
        if (errors.length > 0) {
            errorLoginContainer.innerHTML = errors.map(error => `<p>${error}</p>`).join("");
            errorLoginContainer.classList.add('text-red-900');
        } else {
            form.submit(); // Enviar formulario si no hay errores
        }
    });
    formRegister.addEventListener("submit", function (event){
        event.preventDefault(); // Detener envío para validar primero
        errorRegisterContainer.innerHTML = ""; // Limpiar errores previos
        let errors = [];

        // Validar nombre del grupo
        const loginError = validacionesRegister();
        if (loginError) {
            errors.push(loginError);
        }
        // Mostrar errores si hay alguno
        if (errors.length > 0) {
            errorRegisterContainer.innerHTML = errors.map(error => `<p>${error}</p>`).join("");
            errorRegisterContainer.classList.add('text-red-600');
        } else {
            form.submit(); // Enviar formulario si no hay errores
        }
    })



});