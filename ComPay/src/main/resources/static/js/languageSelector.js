document.addEventListener("DOMContentLoaded", () => {
    const mainFlag = document.getElementById("main-lang-flag");
    const languageOptions = document.querySelectorAll(".language-option");

    // Verifica si estás en la página de login
    if (window.location.pathname === "/login" || window.location.pathname === "/") { // Cambia "/login" a la ruta de tu página de login
        localStorage.removeItem("selectedLang");
    }

    // Verifica si ya hay un idioma guardado en el almacenamiento local
    const savedLang = localStorage.getItem("selectedLang");
    if (savedLang) {
        updateMainFlag(savedLang);
    } else {
        // Configura la bandera predeterminada (español)
        updateMainFlag("es");
    }

    // Escucha cada opción de idioma para actualizar la bandera principal
    languageOptions.forEach(option => {
        option.addEventListener("click", function (event) {
            event.preventDefault();
            const lang = option.getAttribute("data-lang");
            localStorage.setItem("selectedLang", lang); // Guarda el idioma seleccionado
            updateMainFlag(lang);

            // Redirige a la URL con el parámetro de idioma
            window.location.href = option.getAttribute("href");
        });
    });

    // Función para actualizar la imagen de la bandera principal
    function updateMainFlag(lang) {
        let flagUrl = "";

        switch (lang) {
            case "es":
                flagUrl = "https://cdn.icon-icons.com/icons2/1531/PNG/512/3253482-flag-spain-icon_106784.png";
                break;
            case "pt":
                flagUrl = "https://cdn.icon-icons.com/icons2/107/PNG/512/portugal_18280.png";
                break;
            case "en":
                flagUrl = "https://cdn.icon-icons.com/icons2/107/PNG/512/united_kingdom_flag_flags_18060.png";
                break;
            case "it":
                flagUrl = "https://cdn.icon-icons.com/icons2/107/PNG/512/italy_18275.png";
                break;
            case "cs":
                flagUrl = "https://cdn.icon-icons.com/icons2/107/PNG/512/czech_republic_18215.png";
                break;
            default:
                return; // No cambia si no hay un idioma guardado
        }

        // Actualiza la fuente de la bandera principal
        if (mainFlag) {
            mainFlag.src = flagUrl;
        }
    }
});
