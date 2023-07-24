
    $(document).ready(function () {
    // Función para calcular montos y actualizar campos
    function calcularMontos() {
        var sueldoImponible = parseInt($("#sueldoImponible").val());
        var montoAfp = sueldoImponible * parseFloat($("#porcentajeAfp").val()) / 100;
        var montoSalud = sueldoImponible * parseFloat($("#porcentajeSalud").val()) / 100;
        var totalDescuentos = montoAfp + montoSalud + parseInt($("#anticipo").val());
        var sueldoLiquido = sueldoImponible - totalDescuentos;

        $("#montoAfp").val(montoAfp);
        $("#montoSalud").val(montoSalud);
        $("#totalDscts").val(totalDescuentos);
        $("#sueldoLiquido").val(sueldoLiquido);
    }

    // Detectar el cambio en el select de trabajador
    $("#trabajadorId").change(function () {
    var trabajadorId = $(this).val();

    // Realizar una llamada a tu controlador para obtener la información de AFP y la institución de salud
    $.ajax({
    url: "/obtenerInformacionTrabajador",
    type: "GET",
    data: {trabajadorId: trabajadorId},
    success: function (data) {
    // Actualizar el select de AFP con la opción correspondiente al trabajador seleccionado
    var previsionSelect = $("#previsionId");
    previsionSelect.val(data.afpId);

    // Actualizar el select de institución de salud con la opción correspondiente al trabajador seleccionado
    var saludSelect = $("#saludId");
    saludSelect.val(data.saludId);

    // Calcular montos y actualizar campos
    calcularMontos();
},
    error: function () {
    console.log("Error al obtener la información del trabajador");
}
});
});

    // Detectar cambios en los campos para calcular montos en tiempo real
    $("#sueldoImponible, #porcentajeAfp, #porcentajeSalud, #anticipo").on('input', function () {
    calcularMontos();
});
})

