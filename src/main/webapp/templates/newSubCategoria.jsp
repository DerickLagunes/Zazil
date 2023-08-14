<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Vertically centered scrollable modal -->
<div class="modal fade" id="nuevaSubCategoriaModal"  data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Agregar subcategoría</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div class="offset-2 col-8" style="padding-top: 60px;padding-bottom: 60px">
                    <h4 class="text-center">Nueva categoría</h4>
                    <br>
                    <form id="subcat" method="post" action="SubCategoria" onsubmit="return false">

                        <div class="row">

                            <div class="mb-3">
                                <label for="nombre_subcategoria" class="form-label">Nombre de la categoría<i class="text-danger">*</i></label>
                                <input maxlength="80" required="" type="text" class="form-control" id="nombre_subcategoria"
                                       name="nombre_subcategoria" aria-describedby="nombre_subcategoria_ayuda">
                                <div id="nombre_subcategoria_ayuda" class="form-text">Escribe aquí el nombre de la nueva categoría.</div>
                            </div>

                        </div>

                        <div class="row">

                            <div class="mb-3">
                                <label for="subcat_categoria" class="form-label">Categoría<i class="text-danger">*</i></label>
                                <div class="input-group">
                                    <select required id="subcat_categoria" name="subcat_categoria" class="form-select form-select mb-3">
                                        <option selected>Selecciona...</option>
                                        <c:forEach items="${categorias}" var="c">
                                            <option value="${c.id}">${c.nombre}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><i class="bi bi-x"></i> Cancelar</button>
                <button onclick="enviarSubCat()" type="button" class="btn btn-primary">Registrar SubCategoría</button>
            </div>

        </div>
    </div>
</div>

<script>
    var myModal = document.getElementById('nuevaSubCategoriaModal')
    var myInput = document.getElementById('nombre_subcategoria')

    myModal.addEventListener('shown.bs.modal', function () {
        myInput.focus()
    })
</script>
<script>
    function enviarSubCat(){
        let form = document.querySelector("#subcat");

        let req = new XMLHttpRequest();
        req.open ("POST", "SubCategoria", true);
        req.onload = function(){
            if (req.readyState == 4){
                if(req.status == 200){
                    let myModalEl = document.getElementById('nuevaSubCategoriaModal');
                    let modal = bootstrap.Modal.getInstance(myModalEl)
                    modal.hide();
                    updateSubCategorias(req.responseText);
                }
                else{
                    alert ("Failed!");
                }
            }
        };
        req.send (new FormData(form));
        return false;
    }

    function updateSubCategorias(catId){
        let select = document.getElementById("subcategoria_producto");
        let req = new XMLHttpRequest();

        limpiarSelect("subcategoria_producto");

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
                            select.removeAttribute("disabled");
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

        req.open("GET", "SubCategoria?catid="+catId, true);
        req.send(null);
    }
</script>