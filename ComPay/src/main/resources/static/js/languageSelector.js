document.addEventListener("DOMContentLoaded", () => {
    const mainFlag = document.getElementById("main-lang-flag");
    const languageOptions = document.querySelectorAll(".language-option");


    if (window.location.pathname === "/login" || window.location.pathname === "/") {
        localStorage.removeItem("selectedLang");
    }


    const savedLang = localStorage.getItem("selectedLang");
    if (savedLang) {
        updateMainFlag(savedLang);
    } else {
        updateMainFlag("es");
    }

    languageOptions.forEach(option => {
        option.addEventListener("click", function (event) {
            event.preventDefault();
            const lang = option.getAttribute("data-lang");
            localStorage.setItem("selectedLang", lang);
            updateMainFlag(lang);

            window.location.href = option.getAttribute("href");
        });
    });

    function updateMainFlag(lang) {
        let flagUrl = "";

        switch (lang) {
            case "es":
                flagUrl = "https://cdn.icon-icons.com/icons2/1531/PNG/512/3253482-flag-spain-icon_106784.png";
                break;
            case "en":
                flagUrl = "https://cdn.icon-icons.com/icons2/107/PNG/512/united_kingdom_flag_flags_18060.png";
                break;
            case "it":
                flagUrl = "https://cdn.icon-icons.com/icons2/107/PNG/512/italy_18275.png";
                break;
            default:
                return;
        }

        if (mainFlag) {
            mainFlag.src = flagUrl;
        }
    }
});
