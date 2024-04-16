
$(document).ready( () => {
    //Variables globales
    let idPrevision;
    let idSalud;
    let anticipo = 0;
    let porcentajePrevision;
    let porcentajeSalud;

    const idLiquidacion = $('#contactForm').data('liquidacion-id');
    const url ="http://localhost:8080/api/liquidacion"
        const infoLiquidacion = () => {
            fetch(url +"/"+idLiquidacion)
                .then((response) => {
                    if(!response.ok){
                        throw new Error("Error en la solicitud. Código de estado: " + response.status);
                    }
                    return response.json();
                })
                .then((liquidacion) =>{
                    //Variables locales
                    let idTrabajador = liquidacion.trabajador.idTrabajador;
                    let nombreTrabajador = liquidacion.trabajador.nombre;
                    let apellidoTrabajador = liquidacion.trabajador.apellido1;
                    let instPrevisional = liquidacion.trabajador.instPrevision.descripcion;
                    let instSalud = liquidacion.trabajador.instSalud.descripcion;

                    //Asignación de variables globales
                    idSalud = liquidacion.trabajador.instSalud.idInstSalud;
                    idPrevision = liquidacion.trabajador.instPrevision.idInstPrevision;
                    porcentajeSalud = liquidacion.trabajador.instSalud.porcDcto;
                    porcentajePrevision = liquidacion.trabajador.instPrevision.porcDcto;


                    //Mostrar nombre y apellido del trabajador en el formulario
                    const nombre = document.getElementById("trabajadorId");
                    const optionNombre = document.createElement("option");
                    optionNombre.value = idTrabajador;
                    optionNombre.text = nombreTrabajador + " " + apellidoTrabajador;
                    nombre.appendChild(optionNombre);

                    //Mostrar nombre de institución de salud
                    const nombreSalud = document.getElementById("saludId");
                    const optionInstSalud = document.createElement("option");
                    optionInstSalud.value = idSalud;
                    optionInstSalud.text = instSalud;
                    nombreSalud.appendChild(optionInstSalud);

                    //Mostrar nombre de institución previsional
                    const nombrePrevision = document.getElementById("previsionId");
                    const optionInstPrevision = document.createElement("option");
                    optionInstPrevision.value = idPrevision;
                    optionInstPrevision.text = instPrevisional;
                    nombrePrevision.appendChild(optionInstPrevision);

                })
                .catch((error) => {
                    console.log("Error al obtener datos: ", error)
                })
        }

             infoLiquidacion()

         const obtenerAnticipo = () => {
        const anticipoInput = document.getElementById("anticipo");
        anticipo = parseFloat(anticipoInput.value) || 0; // Si no se ingresa valor, se considera 0 como anticipo
        calcularDatos(); // Llamamos a calcularDatos después de obtener el anticipo para actualizar el "Total Haber"
    };

    // Evento change para capturar el anticipo al momento de ingresarlo
    document.getElementById("anticipo").addEventListener("change", obtenerAnticipo);

    const calcularDatos = () => {
        let sueldoImponibleInput = document.getElementById("sueldoImponible");
        let sueldoImponible = parseInt(sueldoImponibleInput.value);

        // Realizar los cálculos con el sueldo imponible y el porcentaje de descuento
        let dctoFinalAfp = Math.round(sueldoImponible * (porcentajePrevision / 100));
        let dctoFinalSalud = Math.round(sueldoImponible * (porcentajeSalud / 100));

        //TOTAL DESCUENTOS
        let totalDescuentos = dctoFinalAfp + dctoFinalSalud;

        //AGREGO EL DCTO DE AFP EN EL FORMULARIO
        const inputDsctoAFP = document.getElementById("montoAfp");
        inputDsctoAFP.value = dctoFinalAfp;

        //AGREGO EL DCTO DE SALUD EN EL FORMULARIO
        const inputDsctoSalud = document.getElementById("montoSalud");
        inputDsctoSalud.value = dctoFinalSalud;

        //AGREGO EL TOTAL DE DESCUENTOS EN EL FORMULARIO
        const inputTotalDsct = document.getElementById("totalDscts");
        inputTotalDsct.value = totalDescuentos;

        //AGREGO EL TOTAL DE HABERES EN EL FORMULARIO
        const inputTotalHaber = document.getElementById("totalHaberes");
        inputTotalHaber.value = sueldoImponible;

        // Calcular el "Total Haber" restando los descuentos y el anticipo
        const sueldoLiquido = sueldoImponible - totalDescuentos - anticipo;

        // Actualizar el valor del input "sueldoLiquido"
        const sueldoLiquidoInput = document.getElementById("sueldoLiquido");
        sueldoLiquidoInput.value = sueldoLiquido;

    };

    document.getElementById("sueldoImponible").addEventListener("change", calcularDatos);

    });

