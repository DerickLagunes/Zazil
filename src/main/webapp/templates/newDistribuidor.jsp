<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- Vertically centered scrollable modal -->
<div class="modal fade" id="nuevoDistribuidor"  data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title">Agregar distribuidor</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body">
        <div class="offset-2 col-8" style="padding-top: 60px;padding-bottom: 60px">
          <h4 class="text-center">Nuevo distribuidor</h4>
          <br>
          <form id="dist" method="post" action="Distribuidor" onsubmit="return false">

            <div class="row">

              <div class="mb-3">
                <label for="nombre_distribuidor" class="form-label">Nombre del distribuidor<i class="text-danger">*</i></label>
                <input maxlength="60" required="" type="text" class="form-control" id="nombre_distribuidor" name="nombre_distribuidor" aria-describedby="nombre_distribuidor_ayuda">
                <div id="nombre_distribuidor_ayuda" class="form-text">Escribe aquí el nombre del nuevo distribuidor.</div>
              </div>

              <div class="mb-3">
                <label for="nombre_contacto" class="form-label">Nombre del contacto<i class="text-danger">*</i></label>
                <input maxlength="80" required="" type="text" class="form-control" id="nombre_contacto" name="nombre_contacto" aria-describedby="nombre_contacto_ayuda">
                <div id="nombre_contacto_ayuda" class="form-text">Escribe aquí el nombre de contacto del nuevo distribuidor.</div>
              </div>

              <div class="mb-3">
                <label for="telefono" class="form-label">Teléfono<i class="text-danger">*</i></label>
                <input maxlength="20" required="" type="text" class="form-control" id="telefono" name="telefono" aria-describedby="telefono_ayuda">
                <div id="telefono_ayuda" class="form-text">Escribe aquí el teléfono de contacto del nuevo distribuidor.</div>
              </div>

              <div class="mb-3">
                <label for="telefono2" class="form-label">Otro teléfono (opcional)</label>
                <input maxlength="20" required="" type="text" class="form-control" id="telefono2" name="telefono2" aria-describedby="telefono2_ayuda">
                <div id="telefono2_ayuda" class="form-text">Escribe aquí otro teléfono de contacto del nuevo distribuidor.</div>
              </div>

              <div class="mb-3">
                <label for="correo1" class="form-label">Correo electrónico<i class="text-danger">*</i></label>
                <input maxlength="50" required="" type="email" class="form-control" id="correo1" name="correo1" aria-describedby="correo1_ayuda">
                <div id="correo1_ayuda" class="form-text">Escribe aquí el correo de contacto del nuevo distribuidor.</div>
              </div>

              <div class="mb-3">
                <label for="correo2" class="form-label">Otro correo (opcional)</label>
                <input maxlength="20" required="" type="text" class="form-control" id="correo2" name="correo2" aria-describedby="correo2_ayuda">
                <div id="correo2_ayuda" class="form-text">Escribe aquí otro correo de contacto del nuevo distribuidor.</div>
              </div>

              <div class="row">

                <div class="mb-3 col-md-6">
                  <label for="pais" class="form-label">País<i class="text-danger">*</i></label>
                  <select onchange="loadEstados(this.value)" required id="pais" name="pais" class="form-select form-select mb-3">
                    <option selected>Selecciona...</option>
                  </select>
                </div>

                <div class="mb-3 col-md-6">
                  <label for="estado" class="form-label">Estado<i class="text-danger">*</i></label>
                  <select onchange="loadMunicipios(this.value)" required id="estado" name="estado" class="form-select form-select mb-3">
                    <option selected>Selecciona...</option>
                  </select>
                </div>

              </div>

              <div class="row">

                <div class="mb-3 col-md-6">
                  <label for="municipio" class="form-label">Municipio<i class="text-danger">*</i></label>
                  <select onchange="loadColonias(this.value)" required id="municipio" name="municipio" class="form-select form-select mb-3">
                    <option selected>Selecciona...</option>
                  </select>
                </div>

                <div class="mb-3 col-md-6">
                  <label for="colonia" class="form-label">Colonia<i class="text-danger">*</i></label>
                  <select required id="colonia" name="colonia" class="form-select form-select mb-3">
                    <option selected>Selecciona...</option>
                  </select>
                </div>

              </div>

            </div>
          </form>
        </div>
      </div>

      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal"><i class="bi bi-x"></i> Cancelar</button>
        <button onclick="enviar()" type="button" class="btn btn-primary">Registrar Distribuidor</button>
      </div>

    </div>
  </div>
</div>

<script>
  var myModal = document.getElementById('nuevoDistribuidor')
  var myInput = document.getElementById('nombre_distribuidor')

  myModal.addEventListener('shown.bs.modal', function () {
    myInput.focus()
  })
</script>
<script>
  function loadPaises() {
    let req = new XMLHttpRequest();
    let paisSelect = document.getElementById("pais");

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

              paisSelect.appendChild(option);
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

    req.open("GET", "Pais", true);
    req.send(null);
  }

  function loadEstados(paisId){
    let req = new XMLHttpRequest();
    let select = document.getElementById("estado");

    limpiarSelect("estado");
    limpiarSelect("municipio");
    limpiarSelect("colonia");

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

    req.open("GET", "Estado?pais="+paisId, true);
    req.send(null);
  }

  function loadMunicipios(estadoId){
    let req = new XMLHttpRequest();
    let select = document.getElementById("municipio");

    limpiarSelect("municipio");
    limpiarSelect("colonia");

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

    req.open("GET", "Municipio?estado="+estadoId, true);
    req.send(null);
  }

  function loadColonias(municipioId){
    let req = new XMLHttpRequest();
    let select = document.getElementById("colonia");

    limpiarSelect("colonia");

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

    req.open("GET", "Colonia?municipio="+municipioId, true);
    req.send(null);
  }

  function limpiarSelect(id){
    let select = document.getElementById(id);

    let def = document.createElement("option");
    def.setAttribute("selected", "");
    def.text = "Selecciona...";
    select.replaceChildren(def);
  }

  function enviar(){
    let form = document.querySelector("#dist");

    var req = new XMLHttpRequest();
    req.open ("POST", "Distribuidor", true);
    req.onload = function(){
      if (req.readyState == 4 && req.status == 200){
        alert (req.responseText);
        let myModalEl = document.getElementById('nuevoDistribuidor');
        let modal = bootstrap.Modal.getInstance(myModalEl)
        modal.hide();
        updateDists();
      }
      else{
        alert ("Failed!");
      }
    };
    req.send (new FormData(form));
    return false;
  }

  function updateDists(){
    let select = document.getElementById("distribuidor_producto");
    let req = new XMLHttpRequest();

    limpiarSelect("distribuidor_producto");

    req.onreadystatechange = function() {
      if (req.readyState == XMLHttpRequest.DONE) { // XMLHttpRequest.DONE == 4
        if (req.status == 200) {
          let respuesta = JSON.parse(req.responseText); //json
          for(let key in respuesta){
            if (respuesta.hasOwnProperty(key)) {
              //Crear elementos del select
              let option = document.createElement("option");
              option.setAttribute("value", respuesta[key].id);
              option.text = respuesta[key].nombre_distribuidor;

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

    req.open("GET", "Distribuidor", true);
    req.send(null);
  }
</script>