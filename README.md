Se trata de desarrollar una aplicación web que implemente un sistema de gestión de pagos en grupo. La idea es facilitar que, por ejemplo, un grupo de amigos que va de excursión no tenga que poner un "bote" para los gastos comunes, sino que la aplicación web permita anotar los pagos que cada usuario va haciendo para el resto de compañeros, de forma que luego, al final de la excursión, los amigos puedan ver qué deudas hay pendientes y qué pagos habría que hacer entre usuarios para saldar las deudas. Una aplicación muy popular en plataforma móvil es Settle Up

La aplicación debe permitir registrar usuarios, y cada usuario registrado podrá crear un nuevo "proyecto" (como por ejemplo una excursión), al que podrá añadir a otros usuarios registrados. Una vez dentro de cualquier proyecto al que se tenga acceso, el usuario podrá ver los pagos ya realizados, añadir un pago nuevo y ver un listado de transacciones a realizar entre usuarios del proyecto si se quisiesen saldar todas las deudas.

Las funcionalidades a implementar son:

(F1) Registrarse, indicando un alias (sin espacios), una contraseña y un email.
(F2) Autenticarse, comprobando los credenciales. Una vez autenticado, se va al listado de proyectos (ver F3).
(F3) Listado de proyectos. Se ve un listado de todos los proyectos donde el usuario está incluido. Una vez se hace clic en un proyecto, se va a F5. Desde aquí también se podrá:
(F4) Crear proyecto nuevo, indicando un nombre del proyecto.
(F5) Ver proyecto en detalle. En este panel deberá verse un listado de pagos ya realizados. Además, desde este panel se podrá:
(F6) Añadir un usuario al proyecto. Se deberá indicar un email del usuario que se quiere añadir.
(F7) Crear nuevo pago indicando: (i) usuario que paga (por defecto, el usuario autenticado), (ii) nombre de pago, (iii) cantidad y (iv) otros usuarios del proyecto para los que se hace el pago (por defecto todos, incluido el pagador).
(F8) Editar un pago existente, pudiendo cambiar cualquier campo.
(F9) Eliminar un pago.
(F10) Listado de deudas pendientes entre usuarios. Esta parte muestra qué pagos se deberían realizar entre usuarios para saldar todas las deudas.

Objetivos
A) Desarrollar una plantilla para el proyecto web. La plantilla mostrará el aspecto de la web, con colores, iconos, logos, texto de ejemplo, formularios de ejemplo, etc., de cara a ser presentada a un hipotético cliente del sistema web. Para el diseño de iconos o trabajo con bocetos, puedes ayudarte de alguna herramienta de edición visual (Inkscape para vectorial o Gimp para mapa de bits). Maquetar dicha plantilla en HTML y CSS (JavaScript si fuese necesario).

B) Desarrollar la parte del servidor para la aplicación web. Se podrá programar en cualquier lenguaje de programación/framework. Sin embargo, el diseño resultante debe cumplir que:

Separe lógica de negocio, lógica de acceso a datos (sentencias SQL o similar) y generación de HTML.
Una alternativa es emplear el patrón MVC (Modelo Vista Controlador) para separar vista y lógica; y uso de DAO (Data Access Object), Data Mappers, o motores de persistencia para separar el acceso a datos.
Plantilla de página. Facilidad para modificar/eliminar/mover elementos comunes, como encabezados, pies, etc.
Permita la traducción sencilla de la interfaz a otros idiomas (internacionalización).
Sea flexible para añadir comportamiento común a todas las peticiones (como, por ejemplo, creación de un log de accesos, filtrar peticiones, etc.).
Es decir, toda petición HTTP debe pasar en el servidor por algún punto de entrada común (ejemplo: un mismo index.php).
Siga el patrón PRG (Post-Redirect-Get), es decir, tras hacer un POST, si éste es exitoso (e.j: validan bien todos los datos), no se debe devolver HTML, sino que siempre se debe redirigir a otra página. 
Elementos a entregar
Plantilla Web
Plantilla web en HTML+CSS, con un aspecto "definitivo", aunque sin funcionalidad. Será una carpeta donde dentro deberá haber al menos 2 ficheros html de ejemplo (p.ej. en la práctica: "nuevo-gasto.html" y "detalle-proyecto.html").
Algunas capturas de pantalla del prototipo (sin ver el navegador) en formato PNG. Mínimo 2, máximo 4.

Aplicación Web

MODO A: SÓLO si se usa Apache/PHP/MySQL:
Un único ZIP por grupo de proyecto Web. Dentro del ZIP deberá haber:
Código fuente.
Fichero SQL para crear la base de datos. Debe hacerse exportando la base de datos.
Fichero readme.txt con el nombre de los alumnos que entregan.
