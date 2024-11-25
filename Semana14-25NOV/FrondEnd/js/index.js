$(document).ready(function() {
    // Función para validar el formulario
    function validarFormulario() {
        let esValido = true;

        // Validar Usuario
        const usuario = $('#usuario').val().trim();
        if (usuario === '' || usuario.length < 3) {
            $('#usuarioError').show();
            esValido = false;
        } else {
            $('#usuarioError').hide();
        }

        // Validar Clave
        const clave = $('#clave').val();
        if (clave === '' || clave.length < 4) {
            $('#claveError').show();
            esValido = false;
        } else {
            $('#claveError').hide();
        }

        return esValido;
    }

    // Función para procesar el formulario de login
    function procesarLogin() {
        // Ocultar mensajes de error previos
        $('.error-message').hide();
        $('#mensajeRespuesta').hide();

        // Validar formulario
        if (!validarFormulario()) {
            return;
        }

        // Preparar datos
        const datos = {
            usuario: $('#usuario').val(),
            clave: $('#clave').val()
        };

        // Deshabilitar botón durante la solicitud
        $('#btnProcesar')
            .prop('disabled', true)
            .html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Iniciando sesión...');

        // Llamada AJAX
        $.ajax({
            url: 'http://localhost:8080/seguridad/logon',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(datos),
            success: function(respuesta) {
                // Guardando el codigo del empleado
                localStorage.setItem('codEmpleeado', respuesta.codigo);
                // Manejar respuesta exitosa
                $('#mensajeRespuesta')
                    .removeClass('alert-danger')
                    .addClass('alert-success')
                    .text('Inicio de sesión exitoso')
                    .show();
                
                // Aquí podrías redirigir a otra página o realizar alguna acción
                setTimeout(() => {
                    window.location.href = '/main.html'; // Ejemplo de redirección
                }, 1500);
            },
            error: function(xhr, status, error) {
                // Manejar error de inicio de sesión
                $('#mensajeRespuesta')
                    .removeClass('alert-success')
                    .addClass('alert-danger')
                    .text('Error de inicio de sesión: ' + (xhr.responseJSON?.mensaje || 'Credenciales inválidas'))
                    .show();
            },
            complete: function() {
                // Rehabilitar botón
                $('#btnProcesar')
                    .prop('disabled', false)
                    .html('Iniciar Sesión');
            }
        });
    }

    // Evento de clic en botón Procesar
    $('#btnProcesar').on('click', procesarLogin);

    // Validación en tiempo real
    $('#usuario, #clave').on('input', function() {
        validarFormulario();
    });

    // Toggle de visibilidad de contraseña
    $('#togglePassword').on('click', function() {
        const claveInput = $('#clave');
        const type = claveInput.attr('type') === 'password' ? 'text' : 'password';
        claveInput.attr('type', type);
        
        // Cambiar ícono
        $(this).find('i').toggleClass('fa-eye fa-eye-slash');
    });

    // Recuperar contraseña (placeholder)
    $('#recuperarClave').on('click', function(e) {
        e.preventDefault();
        alert('Funcionalidad de recuperación de contraseña aún no implementada');
    });

    // Manejo de Enter en campos
    $('#usuario, #clave').on('keypress', function(e) {
        if (e.which === 13) { // Código 13 es Enter
            procesarLogin();
        }
    });
});