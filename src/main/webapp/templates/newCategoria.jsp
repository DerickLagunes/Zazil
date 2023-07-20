<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- Vertically centered scrollable modal -->
<div class="modal fade" id="nuevaCategoriaModal"  data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Agregar categoria</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div class="offset-2 col-8" style="padding-top: 60px;padding-bottom: 60px">
                    <h4 class="text-center">Nueva categoria</h4>
                    <br>
                    <form id="cat" method="post" action="Categoria" onsubmit="return false">

                        <div class="row">

                            <div class="mb-3">
                                <label for="nombre_categoria" class="form-label">Nombre de la categoria<i class="text-danger">*</i></label>
                                <input maxlength="80" required="" type="text" class="form-control" id="nombre_categoria"
                                       name="nombre_distribuidor" aria-describedby="nombre_distribuidor_ayuda">
                                <div id="nombre_distribuidor_ayuda" class="form-text">Escribe aquí el nombre de la nueva categoria.</div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><i class="bi bi-x"></i> Cancelar</button>
                <button onclick="enviar()" type="button" class="btn btn-primary">Registrar Categoria</button>
            </div>

        </div>
    </div>
</div>

<script>
    var myModal = document.getElementById('nuevaCategoriaModal')
    var myInput = document.getElementById('nombre_distribuidor')

    myModal.addEventListener('shown.bs.modal', function () {
        myInput.focus()
    })
</script>
<script>
    function enviar(){
        let form = document.querySelector("#cat");

        var req = new XMLHttpRequest();
        req.open ("POST", "Categoria", true);
        req.onload = function(){
            if (req.readyState == 4 && req.status == 200){
                alert (req.responseText);
                let myModalEl = document.getElementById('nuevaCategoriaModal');
                let modal = bootstrap.Modal.getInstance(myModalEl)
                modal.hide();
                updateCategorias();
            }
            else{
                alert ("Failed!");
            }
        };
        req.send (new FormData(form));
        return false;
    }

    function updateCategorias(){
        let select = document.getElementById("categoria_producto");
        let req = new XMLHttpRequest();

        limpiarSelect("categoria_producto");

        req.onreadystatechange = function() {
            if (req.readyState == XMLHttpRequest.DONE) { // XMLHttpRequest.DONE == 4
                if (req.status == 200) {
                    let respuesta = JSON.parse(req.responseText); //json
                    for(let key in respuesta){
                        if (respuesta.hasOwnProperty(key)) {
                            //Crear elementos del select
                            let option = document.createElement("option");
                            option.setAttribute("value", respuesta[key].id);
                            option.text = respuesta[key].nombre;

                            select.appendChild(option);
                        }
                    }
                }
                else if (req.status == 400) {
                    alert('There was an error 400');
                }
                else {
                    alert('something else other than 200 was returned');
                }
            }
        };

        req.open("GET", "Categoria", true);
        req.send(null);
    }
</script>