//AGREGO LA URL EN UNA CONSTANTE
const url = "http://localhost:8080/api/trabajador";

//CREO UN ARREGLO CON UN OBJETO QUE CONTIENE INFORMACION DE LAS INSTITUCIONES PREVISIONALES
const institucionesPrevisionales = [
    { id: 1, descripcion: "Capital", porcDescuento: 11.44 },
    { id: 2, descripcion: "Cuprum", porcDescuento: 11.44 },
    { id: 3, descripcion: "Habitat", porcDescuento: 11.27 },
    { id: 4, descripcion: "PlanVital", porcDescuento: 11.16 },
    { id: 5, descripcion: "ProVida", porcDescuento: 11.45 },
    { id: 6, descripcion: "Modelo", porcDescuento: 10.58 },
    { id: 7, descripcion: "Uno", porcDescuento: 10.69 }
];

//ARREGLO CON INFO DE INSTITUCIONES DE SALUD
const institucionesSalud = [
    { id: 1, descripcion: "Fonasa", porcDescuento: 7.0 }
];

$(document).ready(() => {
    let idPrevision;
    let idSalud;
    let anticipo = 0;

    //FUNCIÓN PARA OBTENER INFO DEL TRABAJADOR
    const infoTrabajador = () => {
        //CAPTURO EL ID DEL TRABAJADOR DESDE EL SELECT
        const idTrabajador = document.getElementById("trabajadorId").value;

        //CONSUMO LA API CON EL ID DEL TRABAJADOR SELECCIONADO
        fetch(url + "/" + idTrabajador)
            .then((response) => {
                // Verifica si la respuesta fue exitosa (código de estado 200)
                if (!response.ok) {
                    throw new Error("Error en la solicitud. Código de estado: " + response.status);
                }
                return response.json();
            })
            .then((trabajador) => {
                //Crear las variables de acuerdo al trabajador seleccionado
                const descripcionSalud = trabajador.instSalud.descripcion;
                const descripcionPrevision = trabajador.instPrevision.descripcion;
                idPrevision = trabajador.instPrevision.idInstPrevision;
                idSalud = trabajador.instSalud.idInstSalud;

                //AGREGO LA AFP QUE CORRESPONDE AL TRABAJADOR EN EL FORMULARIO
                const nombreAfp = document.getElementById("nombreAfp");
                nombreAfp.value = descripcionPrevision;

                //AGREGO LA INST. DE SALUD QUE CORRESPONDE AL TRABAJADOR EN EL FORMULARIO
                const nombreSalud = document.getElementById("nombreSalud");
                nombreSalud.value = descripcionSalud;

                // Agrego el ID de la institución de salud al campo oculto
                const idSaludInput = document.getElementById("idSalud");
                idSaludInput.value = idSalud;

                const idAfpInput = document.getElementById("idPrevision");
                idAfpInput.value = idPrevision;

                // Imprimir los datos por consola
                console.log("Trabajador ID:", trabajador.idTrabajador);
                console.log("Nombre completo:", trabajador.nombre + " " + trabajador.apellido1 + " " + trabajador.apellido2);
                console.log("Id de la institución de salud:", idSalud);
                console.log("Descripción de la institución de salud:", descripcionSalud);
                console.log("Id de la institución previsional:", idPrevision);
                console.log("Descripción de la institución previsional:", descripcionPrevision);
                console.log("--------------------------------------");

                // Calcular los datos cuando se obtiene la información del trabajador
                calcularDatos();
            })
            .catch((error) => {
                console.error("Error al obtener los datos:", error);
            });
    }

    //EVENTO CHANGE PARA CAPTURAR LA INFO DEL TRABAJADOR AL MOMENTO DE SELECCIONARLO
    document.getElementById("trabajadorId").addEventListener("change", infoTrabajador);

    //CALCULAR DESCUENTO DE AFP
    const descuentoAFP = (idInstitucionPrevisional) => {
        const institucionPrevisional = institucionesPrevisionales.find(
            (institucion) => institucion.id === idInstitucionPrevisional
        );

        if (institucionPrevisional) {
            return institucionPrevisional.porcDescuento;
        }

        return 0; // Por defecto, si no se encuentra la institución previsional, retorna 0% de descuento.
    };

    const descuentoSalud = (idInstitucionSalud) => {
        const institucionSalud = institucionesSalud.find(
            (institucion) => institucion.id === idInstitucionSalud
        );

        if (institucionSalud) {
            return institucionSalud.porcDescuento;
        }

        return 0; // Por defecto, si no se encuentra la institución previsional, retorna 0% de descuento.
    };

    // Función para obtener el anticipo
    const obtenerAnticipo = () => {
        const anticipoInput = document.getElementById("anticipo");
        anticipo = parseFloat(anticipoInput.value) || 0; // Si no se ingresa valor, se considera 0 como anticipo
        calcularDatos(); // Llamamos a calcularDatos después de obtener el anticipo para actualizar el "Total Haber"
    };

    // Evento change para capturar el anticipo al momento de ingresarlo
    document.getElementById("anticipo").addEventListener("change", obtenerAnticipo);

    // Función para actualizar los campos ocultos y enviar el formulario
    const actualizarFormulario = () => {
        // Obtener los valores calculados
        const sueldoImponible = parseFloat(document.getElementById("sueldoImponible").value);
        const porcentajeDctoAFP = descuentoAFP(parseInt(idPrevision));
        const porcentajeDctoSalud = descuentoSalud(parseInt(idSalud));
        const dctoFinalAfp = Math.round(sueldoImponible * (porcentajeDctoAFP / 100));
        const dctoFinalSalud = Math.round(sueldoImponible * (porcentajeDctoSalud / 100));
        const totalDescuentos = dctoFinalAfp + dctoFinalSalud;
        const sueldoLiquido = sueldoImponible - totalDescuentos - anticipo;

        // Actualizar los campos ocultos con los valores calculados
        document.getElementById("idMontoSalud").value = dctoFinalSalud;
        document.getElementById("idMontoAFP").value = dctoFinalAfp;
        document.getElementById("idTotalDscts").value = totalDescuentos;
        document.getElementById("idTotalHaber").value = sueldoImponible;
        document.getElementById("idAnticipo").value = anticipo;
        document.getElementById("idSueldoLiquido").value = sueldoLiquido;

        // Enviar el formulario
        document.getElementById("contactForm").submit();
    };



    // Función para calcular los datos y actualizar los campos
    const calcularDatos = () => {
        let sueldoImponibleInput = document.getElementById("sueldoImponible");
        let sueldoImponible = parseFloat(sueldoImponibleInput.value);

        // Obtener el porcentaje de descuento asociado a la institución previsional
        const porcentajeDctoAFP = descuentoAFP(parseInt(idPrevision));
        const porcentajeDctoSalud = descuentoSalud(parseInt(idSalud));

        // Realizar los cálculos con el sueldo imponible y el porcentaje de descuento
        let dctoFinalAfp = Math.round(sueldoImponible * (porcentajeDctoAFP / 100));
        let dctoFinalSalud = Math.round(sueldoImponible * (porcentajeDctoSalud / 100));

        //TOTAL DESCUENTOS
        let totalDescuentos = dctoFinalAfp + dctoFinalSalud;

        //AGREGO EL DCTO DE AFP EN EL FORMULARIO
        const porcDsctoAFP = document.getElementById("montoAfp");
        porcDsctoAFP.value = dctoFinalAfp;

        //AGREGO EL DCTO DE SALUD EN EL FORMULARIO
        const porcDsctoSalud = document.getElementById("montoSalud");
        porcDsctoSalud.value = dctoFinalSalud;

        //AGREGO EL TOTAL DE DESCUENTOS EN EL FORMULARIO
        const totalDsct = document.getElementById("totalDscts");
        totalDsct.value = totalDescuentos;

        //AGREGO EL TOTAL DE HABERES EN EL FORMULARIO
        const totalHaber = document.getElementById("totalHaber");
        totalHaber.value = sueldoImponible;

        // Calcular el "Total Haber" restando los descuentos y el anticipo
        const sueldoLiquido = sueldoImponible - totalDescuentos - anticipo;

        // Actualizar el valor del input "sueldoLiquido"
        const sueldoLiquidoInput = document.getElementById("sueldoLiquido");
        sueldoLiquidoInput.value = sueldoLiquido;

        console.log("Sueldo imponible ingresado:", sueldoImponible);
        console.log("Porcentaje de descuento AFP:", porcentajeDctoAFP);
        console.log("Descuento AFP calculado:", dctoFinalAfp);
        console.log("Porcentaje de descuento Salud:", porcentajeDctoSalud);
        console.log("Descuento Salud calculado:", dctoFinalSalud);
        console.log("Total Descuentos:", totalDescuentos);
        console.log("Sueldo Líquido:", sueldoLiquido);
    };

    document.getElementById("sueldoImponible").addEventListener("change", calcularDatos);
    // Evento submit para enviar el formulario
    document.getElementById("contactForm").addEventListener("submit", (event) => {
        event.preventDefault();
        actualizarFormulario();
    });
});
