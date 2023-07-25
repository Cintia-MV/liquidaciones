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
const institucionesSalud =[
    {id: 1, descripcion: "Fonasa", porcDescuento: 7}
]
$(document).ready(() =>{
    //FUNCIÓN PARA OBTENER INFO DEL TRABAJADOR
    const infoTrabajador = () =>{
        //CAPTURO EL ID DEL TRABAJADOR DESDE EL SELECT
        const idTrabajador = document.getElementById("trabajadorId").value;

        //CONSUMO LA API CON EL ID DEL TRABAJADOR SELECCIONADO
    fetch(url + "/" +idTrabajador)
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
                const idPrevision = trabajador.instPrevision.idInstPrevision;
                const idSalud= trabajador.instSalud.idInstSalud;

                //AGREGO LA AFP QUE CORRESPONDE AL TRABAJADOR EN EL FORMULARIO
            const nombreAfp = document.getElementById("nombreAfp");
            nombreAfp.value = descripcionPrevision;

            //AGREGO LA INST. DE SALUD QUE CORRESPONDE AL TRABAJADOR EN EL FORMULARIO
            const nombreSalud = document.getElementById("nombreSalud");
            nombreSalud.value = descripcionSalud;

                // Imprimir los datos por consola
                console.log("Trabajador ID:", trabajador.idTrabajador);
                console.log("Nombre completo:", trabajador.nombre + " " + trabajador.apellido1 + " " + trabajador.apellido2);
                console.log("Id de la institución de salud:", idSalud);
                console.log("Descripción de la institución de salud:", descripcionSalud);
                console.log("Id de la institución previsional:", idPrevision);
                console.log("Descripción de la institución previsional:", descripcionPrevision);
                console.log("--------------------------------------");

        })
        .catch((error) => {
            console.error("Error al obtener los datos:", error);
        });

    }

    //EVENTO CHANGE PARA CAPTURAR LA INFO DEL TRABAJADOR AL MOMENTO DE SELECCIONARLO
    document.getElementById("trabajadorId").addEventListener("change", infoTrabajador);

    //CALCULAR DESCUENTO DE AFP
    //busco en la lista de afp el objeto que coincida con el id del trabajador para seleccionar la afp y obtener el
    //porcentaje de descuento correspondiente
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


    const calcularDatos = () => {
        const sueldoImponibleInput = document.getElementById("sueldoImponible");
        const sueldoImponible = parseFloat(sueldoImponibleInput.value);

        // Obtener el id de la institución previsional seleccionada
        const idInstitucionPrevisional = document.getElementById("trabajadorId").value;

        // Obtener el porcentaje de descuento asociado a la institución previsional
        const porcentajeDescuento = descuentoAFP(parseInt(idInstitucionPrevisional));

        // Realizar los cálculos con el sueldo imponible y el porcentaje de descuento
        const descuento = sueldoImponible * (porcentajeDescuento / 100);

        console.log("Sueldo imponible ingresado:", sueldoImponible);
        console.log("Porcentaje de descuento asociado:", porcentajeDescuento);
        console.log("Descuento calculado:", descuento);

    };

    document.getElementById("sueldoImponible").addEventListener("change", calcularDatos);


})


