<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- Vertically centered scrollable modal -->
<div class="modal fade" id="nuevaCategoriaModal"  data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Agregar categoría</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div class="offset-2 col-8" style="padding-top: 60px;padding-bottom: 60px">
                    <h4 class="text-center">Nueva categoría</h4>
                    <br>
                    <form id="cat" method="post" action="Categoria" onsubmit="return false">

                        <div class="row">

                            <div class="mb-3">
                                <label for="nombre_categoria" class="form-label">Nombre de la categoría<i class="text-danger">*</i></label>
                                <input maxlength="80" required="" type="text" class="form-control" id="nombre_categoria"
                                       name="nombre_categoria" aria-describedby="nombre_categoria_ayuda">
                                <div id="nombre_categoria_ayuda" class="form-text">Escribe aquí el nombre de la nueva categoría.</div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><i class="bi bi-x"></i> Cancelar</button>
                <button onclick="enviarCat()" type="button" class="btn btn-primary">Registrar Categoría</button>
            </div>

        </div>
    </div>
</div>

<script>
    var myModal = document.getElementById('nuevaCategoriaModal')
    var myInput = document.getElementById('nombre_categoria')

    myModal.addEventListener('shown.bs.modal', function () {
        myInput.focus()
    })
</script>
<script>
    function enviarCat(){
        let form = document.querySelector("#cat");

        var req = new XMLHttpRequest();
        req.open ("POST", "Categoria", true);
        req.onload = function(){
            if (req.readyState == 4 && req.status == 200){
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
        let select2 = document.getElementById("subcat_categoria");
        let req = new XMLHttpRequest();

        limpiarSelect("categoria_producto");
        limpiarSelect("subcat_categoria");
        req.open("GET", "Categoria", true);
        req.onreadystatechange = function() {
            if (req.readyState === XMLHttpRequest.DONE) {
                if (req.status == 200) {
                    let respuesta = JSON.parse(req.responseText); //json
                    for (let key in respuesta) {
                        if (respuesta.hasOwnProperty(key)) {
                            //Crear elementos del select
                            let option = document.createElement("option");
                            option.setAttribute("value", respuesta[key].id);
                            option.text = respuesta[key].nombre;
                            select.appendChild(option);
                            // Crear una nueva opción para el segundo select
                            let option2 = document.createElement("option");
                            option2.setAttribute("value", respuesta[key].id);
                            option2.text = respuesta[key].nombre;
                            select2.appendChild(option2);
                        }
                    }
                } else {
                    console.log('Error on updating Categories');
                    console.log(req.status);
                    console.log(req.readyState );
                    console.log(req.responseText);
                }
            }
        };
        req.send(null);
    }
</script>