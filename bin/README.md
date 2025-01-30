Leandro: 1 -> Usuarios y Autenticación
Tareas:

    Modelo y Repositorio: Crear la entidad Usuario con atributos básicos (id, nombre, email, contraseña, etc.).
    Servicio: Implementar lógica para gestionar usuarios, incluyendo el cifrado de contraseñas y validaciones.
    DTOs: Crear DTOs para la transferencia de datos (UsuarioDTO, UsuarioRegistroDTO para evitar exponer contraseñas).
    Controlador: Implementar endpoints para:
        Obtener todos los usuarios
        Obtener un usuario por ID
        Crear un usuario
        Modificar un usuario
        Borrar un usuario
        Cambiar contraseña
    Reglas de negocio: Validar que los correos sean únicos y que las contraseñas sean seguras.

Adrian: 2 -> Establecimientos
Tareas:

    Modelo y Repositorio: Crear la entidad Establecimiento con atributos (id, nombre, ubicación, puntuación, etc.).
    Servicio: Implementar lógica para manejar establecimientos y recalcular puntuaciones según los desayunos.
    DTOs: Crear EstablecimientoDTO para enviar solo los datos relevantes al cliente.
    Controlador: Implementar endpoints para:
        Obtener todos los establecimientos
        Obtener un establecimiento por ID
        Crear un establecimiento
        Modificar un establecimiento
        Borrar un establecimiento
        Obtener los establecimientos ordenados por puntuación
        Buscar establecimientos por ubicación
    Reglas de negocio: La puntuación del establecimiento debe recalcularse al modificar un desayuno asociado.
    
Humane: 3 -> Desayunos
Tareas:

    Modelo y Repositorio: Crear la entidad Desayuno con atributos (id, nombre, precio, puntuación, imagen, establecimiento, etc.).
    Servicio: Implementar lógica para gestionar desayunos y actualizar puntuaciones.
    DTOs: Crear DesayunoDTO para listar desayunos sin sobrecargar información.
    Controlador: Implementar endpoints para:
        Obtener todos los desayunos
        Obtener un desayuno por ID
        Crear un desayuno (inicializando puntuación en 0 y asignando imagen por defecto si no se proporciona).
        Modificar un desayuno
        Borrar un desayuno
        Obtener desayunos ordenados por puntuación
        Obtener desayunos ordenados por puntuación de un establecimiento
        Obtener desayunos ordenados por precio de un establecimiento
        Obtener todos los desayunos de un establecimiento
        Modificar la imagen de un desayuno
    Reglas de negocio: Recalcular la puntuación del establecimiento cuando cambian las puntuaciones de los desayunos.
    
Azahara: 4 -> Reviews
Tareas:

    Modelo y Repositorio: Crear la entidad Review con atributos (id, usuario, desayuno, puntuación, comentario, fecha).
    Servicio: Implementar lógica para manejar reviews y recalcular puntuaciones.
    DTOs: Crear ReviewDTO para limitar la información expuesta en consultas.
    Controlador: Implementar endpoints para:
        Obtener todas las reviews de un usuario
        Obtener todas las reviews de un desayuno
        Obtener una review por ID
        Crear una review
        Modificar una review
        Borrar una review
        Obtener reviews ordenadas por fecha (más recientes y más antiguas)
        Obtener reviews ordenadas por puntuación
        Obtener reviews ordenadas por fecha para un desayuno
        Obtener reviews ordenadas por puntuación para un desayuno
    Reglas de negocio:
        La puntuación de una review debe ser entre 0 y 5.
        Al agregar, modificar o eliminar una review, se debe recalcular la puntuación del desayuno y del establecimiento.
