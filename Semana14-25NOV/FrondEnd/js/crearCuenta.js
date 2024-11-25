$(document).ready(function() {
    // Función para validar el formulario
    function validarFormulario() {
        let esValido = true;

        // Validar Cliente
        const cliente = $('#cliente').val().trim();
        if (cliente === '' || cliente.length < 2) {
            $('#clienteError').show();
            esValido = false;
        } else {
            $('#clienteError').hide();
        }

        // Validar Moneda
        const moneda = $('#moneda').val();
        if (moneda === '') {
            $('#monedaError').show();
            esValido = false;
        } else {
            $('#monedaError').hide();
        }

        // Validar Importe
        const importe = parseFloat($('#importe').val());
        if (isNaN(importe) || importe <= 0) {
            $('#importeError').show();
            esValido = false;
        } else {
            $('#importeError').hide();
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

    // Función para procesar el formulario
    function procesarFormulario() {
        // Ocultar mensajes de error previos
        $('.error-message').hide();
        $('#mensajeRespuesta').hide();

        // Validar formulario
        if (!validarFormulario()) {
            return;
        }

        // Preparar datos
        const datos = {
            cliente: $('#cliente').val(),
            moneda: $('#moneda').val(),
            importe: parseFloat($('#importe').val()),
            clave: $('#clave').val(),
            empleado: localStorage.getItem('codEmpleeado')
        };

        // Deshabilitar botón durante la solicitud
        $('#btnProcesar').prop('disabled', true).html('Procesando...');

        // Llamada AJAX
        $.ajax({
            url: 'http://localhost:8080/cuentas/crearcuenta',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(datos),
            success: function(respuesta) {
                $('#mensajeRespuesta')
                    .removeClass('alert-danger')
                    .addClass('alert-success')
                    .text('Cuenta creada exitosamente')
                    .show();
                
                // Limpiar formulario
                $('#cuentaForm')[0].reset();
            },
            error: function(xhr, status, error) {
                $('#mensajeRespuesta')
                    .removeClass('alert-success')
                    .addClass('alert-danger')
                    .text('Error al crear la cuenta: ' + xhr.responseText)
                    .show();
            },
            complete: function() {
                // Rehabilitar botón
                $('#btnProcesar')
                    .prop('disabled', false)
                    .html('Procesar');
            }
        });
    }

    // Evento de clic en botón Procesar
    $('#btnProcesar').on('click', procesarFormulario);

    // Validación en tiempo real
    $('#cliente, #moneda, #importe, #clave').on('input', function() {
        validarFormulario();
    });
});