$(document).ready(function() {


    // Datos de ejemplo (podrías obtenerlos de una API o base de datos)
    let usuarios = [
        { 
            codigo: 1, 
            paterno: 'García', 
            materno: 'López', 
            nombre: 'Juan', 
            dni: '12345678', 
            ciudad: 'Lima', 
            direccion: 'Av. Siempre Viva 123', 
            telefono: '987654321', 
            email: 'juan.garcia@example.com'
        },
        { 
            codigo: 2, 
            paterno: 'Martínez', 
            materno: 'Sánchez', 
            nombre: 'María', 
            dni: '87654321', 
            ciudad: 'Arequipa', 
            direccion: 'Calle Real 456', 
            telefono: '912345678', 
            email: 'maria.martinez@example.com'
        },
        // Agrega más usuarios de ejemplo
        { 
            codigo: 3, 
            paterno: 'Rodríguez', 
            materno: 'Pérez', 
            nombre: 'Carlos', 
            dni: '45678912', 
            ciudad: 'Cusco', 
            direccion: 'Jr. Las Flores 789', 
            telefono: '956781234', 
            email: 'carlos.rodriguez@example.com'
        }
        // Puedes agregar más usuarios aquí
    ];

    
    // Lee los datos de los clientes utilizando la API     
    fnLeerClientes();

    // Configuración de paginación
    const itemsPerPage = 7;
    let currentPage = 1;

    function mostrarUsuarios(pagina) {
        // Limpiar tabla
        $('#userTableBody').empty();

        // Calcular índices de inicio y fin
        const inicio = (pagina - 1) * itemsPerPage;
        const fin = inicio + itemsPerPage;

        // Obtener usuarios para la página actual
        const usuariosPagina = usuarios.slice(inicio, fin);

        // Llenar tabla
        usuariosPagina.forEach(usuario => {
            $('#userTableBody').append(`
                <tr>
                    <td>${usuario.codigo}</td>
                    <td>${usuario.paterno}</td>
                    <td>${usuario.materno}</td>
                    <td>${usuario.nombre}</td>
                    <td>${usuario.dni}</td>
                    <td>${usuario.ciudad}</td>
                    <td>${usuario.direccion}</td>
                    <td>${usuario.telefono}</td>
                    <td>${usuario.email}</td>
                </tr>
            `);
        });

        // Actualizar paginación
        actualizarPaginacion();
    }



    function actualizarPaginacion() {
        const totalPaginas = Math.ceil(usuarios.length / itemsPerPage);
        
        // Limpiar paginación anterior
        $('#pagination').empty();

        // Botón Anterior
        $('#pagination').append(`
            <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
                <a class="page-link" href="#" data-pagina="${currentPage - 1}">Anterior</a>
            </li>
        `);

        // Números de página
        for (let i = 1; i <= totalPaginas; i++) {
            $('#pagination').append(`
                <li class="page-item ${i === currentPage ? 'active' : ''}">
                    <a class="page-link" href="#" data-pagina="${i}">${i}</a>
                </li>
            `);
        }

        // Botón Siguiente
        $('#pagination').append(`
            <li class="page-item ${currentPage === totalPaginas ? 'disabled' : ''}">
                <a class="page-link" href="#" data-pagina="${currentPage + 1}">Siguiente</a>
            </li>
        `);
    }

    // Evento de cambio de página
    $(document).on('click', '.page-link', function(e) {
        e.preventDefault();
        const paginaSeleccionada = parseInt($(this).data('pagina'));

        // Validar que la página esté dentro del rango
        if (paginaSeleccionada >= 1 && paginaSeleccionada <= Math.ceil(usuarios.length / itemsPerPage)) {
            currentPage = paginaSeleccionada;
            mostrarUsuarios(currentPage);
        }
    });

    // Inicializar tabla
    mostrarUsuarios(currentPage);

    // Cargar los clientes
    function fnLeerClientes(){
        var settings = {
            "url": "http://localhost:8080/clientes/listado",
            "method": "GET",
            "timeout": 0,
          };
          
          $.ajax(settings).done(function (response) {
            console.log(response);
            usuarios = response;
            mostrarUsuarios(currentPage);
          });
    }
});