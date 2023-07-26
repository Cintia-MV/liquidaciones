//AGREGO LA URL EN UNA CONSTANTE
const url = "http://localhost:8080/api/trabajador";

$(document).ready(() => {
    let idPrevision;
    let idSalud;
    let anticipo = 0;
    let porcentajeAfp1;
    let porcentajeSalud1;

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
                porcentajeAfp1 = trabajador.instPrevision.porcDcto;
                porcentajeSalud1 = trabajador.instSalud.porcDcto;

                // Mostrar el nombre del trabajador seleccionado
                document.getElementById("trabajadorId").innerText = trabajador.nombre + " " + trabajador.apellido1 + " " + trabajador.apellido2;

                //AGREGO LA AFP QUE CORRESPONDE AL TRABAJADOR EN EL FORMULARIO
                const nombreAfp = document.getElementById("idPrevision");
                const optionAfp = document.createElement("option");
                optionAfp.value = idPrevision;
                optionAfp.text = descripcionPrevision;
                nombreAfp.appendChild(optionAfp);

                //AGREGO LA INST. DE SALUD QUE CORRESPONDE AL TRABAJADOR EN EL FORMULARIO
                const nombreSalud = document.getElementById("idSalud");
                const optionSalud = document.createElement("option");
                optionSalud.value = idSalud;
                optionSalud.text =descripcionSalud;
                nombreSalud.appendChild(optionSalud);


                // Imprimir los datos por consola
                console.log("Trabajador ID:", trabajador.idTrabajador);
                console.log("Nombre completo:", trabajador.nombre + " " + trabajador.apellido1 + " " + trabajador.apellido2);
                console.log("Id de la institución de salud:", idSalud);
                console.log("Descripción de la institución de salud:", descripcionSalud);
                console.log("Id de la institución previsional:", idPrevision);
                console.log("Descripción de la institución previsional:", descripcionPrevision);
                console.log("porcentajes: ", porcentajeSalud1)
                console.log(porcentajeAfp1)
                console.log("--------------------------------------");

            })
            .catch((error) => {
                console.error("Error al obtener los datos:", error);
            });
    }

    //EVENTO CHANGE PARA CAPTURAR LA INFO DEL TRABAJADOR AL MOMENTO DE SELECCIONARLO
    document.getElementById("trabajadorId").addEventListener("change", infoTrabajador);

    // Función para obtener el anticipo
    const obtenerAnticipo = () => {
        const anticipoInput = document.getElementById("anticipo");
        anticipo = parseFloat(anticipoInput.value) || 0; // Si no se ingresa valor, se considera 0 como anticipo
        calcularDatos(); // Llamamos a calcularDatos después de obtener el anticipo para actualizar el "Total Haber"
    };

    // Evento change para capturar el anticipo al momento de ingresarlo
    document.getElementById("anticipo").addEventListener("change", obtenerAnticipo);

    // Función para calcular los datos y actualizar los campos
    const calcularDatos = () => {
        let sueldoImponibleInput = document.getElementById("sueldoImponible");
        let sueldoImponible = parseInt(sueldoImponibleInput.value);

        // Realizar los cálculos con el sueldo imponible y el porcentaje de descuento
        let dctoFinalAfp = Math.round(sueldoImponible * (porcentajeAfp1 / 100));
        let dctoFinalSalud = Math.round(sueldoImponible * (porcentajeSalud1 / 100));

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
        const inputTotalHaber = document.getElementById("totalHaber");
        inputTotalHaber.value = sueldoImponible;

        // Calcular el "Total Haber" restando los descuentos y el anticipo
        const sueldoLiquido = sueldoImponible - totalDescuentos - anticipo;

        // Actualizar el valor del input "sueldoLiquido"
        const sueldoLiquidoInput = document.getElementById("sueldoLiquido");
        sueldoLiquidoInput.value = sueldoLiquido;

        const actualizarFormulario = () => {
            // Actualizar los campos ocultos con los valores calculados
            document.getElementById("idMontoSalud").value = dctoFinalSalud;
            document.getElementById("idMontoAFP").value = dctoFinalAfp;
            document.getElementById("idTotalDscts").value = totalDescuentos;
            document.getElementById("idTotalHaber").value = sueldoImponible;
            document.getElementById("idAnticipo").value = anticipo;
            document.getElementById("idSueldoLiquido").value = sueldoLiquido;

            console.log("Descuento prueba " + dctoFinalSalud);

            // Enviar el formulario
            document.getElementById("contactForm").submit();
        };

        // Evento submit para enviar el formulario
        document.getElementById("contactForm").addEventListener("submit", (event) => {
            event.preventDefault();
            actualizarFormulario();
        });
    };

    document.getElementById("sueldoImponible").addEventListener("change", calcularDatos);



});
