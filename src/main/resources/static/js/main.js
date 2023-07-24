$(document).ready(function() {
    // Crear un objeto o mapa que mapee los IDs de las AFP a sus nombres
    const afpNombres = {
        1: 'Capital',
        2: 'Cuprum',
        3: 'Habitat',
        4: 'PlanVital',
        5: 'ProVida',
        6: 'Modelo',
        7: 'Uno'
    };

    let trabajador = document.querySelector("#trabajadorId")
    console.log(trabajador)
    // Cuando el valor del select de trabajador cambie
    $("#trabajadorId").change(function() {
        // Obtén el ID de la AFP asociada al trabajador seleccionado
        const afpId = $(this).children("option:selected");

        console.log(afpId);
        // Obtén el nombre de la AFP a partir del objeto o mapa
        const nombreAfp = afpNombres[afpId];

        // Asigna el nombre de la AFP al campo de nombreAfp
        $("#nombreAfp").val(nombreAfp);
    });
});
