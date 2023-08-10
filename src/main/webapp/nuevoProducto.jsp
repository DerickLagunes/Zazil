<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="templates/header.jsp" />
<main>
  <div class="container-fluid">
    <div class="row">
      <div class="offset-2 col-8" style="padding-top: 60px;padding-bottom: 60px">
        <h2 class="text-center">Administración de productos</h2>
        <h3 class="text-center">Nuevo producto</h3>
        <br>
        <form method="post" action="Productos" enctype="multipart/form-data">

          <div class="row">

            <div class="mb-3 col-md-6">
              <label for="nombre_producto" class="form-label">Nombre del producto<i class="text-danger">*</i></label>
              <input maxlength="100" required="" type="text" class="form-control" id="nombre_producto" name="nombre_producto" aria-describedby="nombre_producto_ayuda">
              <div id="nombre_producto_ayuda" class="form-text">Escribe aquí el nombre del nuevo producto.</div>
            </div>

            <div class="mb-3 col-md-6">
              <label for="imagen_producto" class="form-label">Subir imagen</label>
              <input type="file" class="form-control" id="imagen_producto" name="imagen_producto" aria-describedby="imagen_producto_ayuda">
              <div id="imagen_producto_ayuda" class="form-text">Sube una imagen para el nuevo producto.</div>
            </div>

          </div>

          <div class="row">

            <div class="mb-3 col-md-6">
              <label for="lote_producto" class="form-label">Lote<i class="text-danger">*</i></label>
              <input required maxlength="20" type="text" class="form-control" id="lote_producto" name="lote_producto" aria-describedby="lote_producto_ayuda">
              <div id="lote_producto_ayuda" class="form-text">Escribe aquí el lote del nuevo producto.</div>
            </div>

            <div class="mb-3 col-md-6">
              <label for="caducidad_producto" class="form-label">Fecha de caducidad<i class="text-danger">*</i></label>
              <input required type="date" onfocus="this.showPicker()" class="form-control" id="caducidad_producto" name="caducidad_producto" aria-describedby="caducidad_producto_ayuda">
              <div id="caducidad_producto_ayuda" class="form-text">Selecciona la fecha de caducidad del nuevo producto.</div>
            </div>

          </div>

          <div class="row">

            <div class="mb-3 col-md-6">
              <label for="precio_costo_producto" class="form-label">Costo<i class="text-danger">*</i></label>
              <input required type="number" step="0.01" min=0 max="99999999.99" class="form-control" id="precio_costo_producto" name="precio_costo_producto" aria-describedby="precio_costo_producto_ayuda">
              <div id="precio_costo_producto_ayuda" class="form-text">¿Cuánto cuesta comprarle el nuevo producto al proveedor?</div>
            </div>

            <div class="mb-3 col-md-6">
              <label for="precio_venta_producto" class="form-label">Precio de venta<i class="text-danger">*</i></label>
              <input required type="number" step="0.01" min=0 max="99999999.99" class="form-control" id="precio_venta_producto" name="precio_venta_producto" aria-describedby="precio_venta_producto_ayuda">
              <div id="precio_venta_producto_ayuda" class="form-text">¿Cuánto cuesta paa nuestros clientes?</div>
            </div>

          </div>

          <div class="row">

          <!-- Distribuidor -->
            <div class="mb-3 col">
              <label for="distribuidor_producto" class="form-label">Distribuidor<i class="text-danger">*</i></label>
              <div class="input-group">
                <select required id="distribuidor_producto" name="distribuidor_producto" class="form-select mb-3">
                  <option selected>Selecciona el distribuidor del nuevo producto o crea uno nuevo</option>
                  <c:forEach items="${distribuidores}" var="d">
                    <option value="${d.id}">${d.nombre_distribuidor}</option>
                  </c:forEach>
                </select>
                <span class="input-group-btn">
                  <button type="button" class="btn btn-outline-primary"
                          data-bs-toggle="modal"
                          data-bs-target="#nuevoDistribuidor"><i class="bi bi-person-plus"></i> Nuevo</button>
                </span>
              </div>
            </div>

          </div>

          <div class="row">

            <div class="mb-3 col-md-6">
              <label for="marca_producto" class="form-label">Marca<i class="text-danger">*</i></label>
              <input required maxlength="60" type="text" class="form-control" id="marca_producto" name="marca_producto" aria-describedby="marca_producto_ayuda">
              <div id="marca_producto_ayuda" class="form-text">Escribe aquí la marca del nuevo producto.</div>
            </div>

            <div class="mb-3 col-md-6">
              <label for="modelo_producto" class="form-label">Modelo<i class="text-danger">*</i></label>
              <input required maxlength="60" type="text" class="form-control" id="modelo_producto" name="modelo_producto" aria-describedby="modelo_producto_ayuda">
              <div id="modelo_producto_ayuda" class="form-text">Escribe aquí el modelo del nuevo producto.</div>
            </div>

          </div>

          <div class="row">

            <div class="mb-3 col-md-6">
              <label for="contenido_producto" class="form-label">Contenido<i class="text-danger">*</i></label>
              <input required maxlength="30" type="text" class="form-control" id="contenido_producto" name="contenido_producto" aria-describedby="contenido_producto_ayuda">
              <div id="contenido_producto_ayuda" class="form-text">Escribe aquí el contenido del nuevo producto.</div>
            </div>

            <div class="mb-3 col-md-6">
              <label for="tipo_contenido_producto" class="form-label">Tipo de contenido<i class="text-danger">*</i></label>
              <select required id="tipo_contenido_producto" name="tipo_contenido_producto" class="form-select form-select mb-3" aria-describedby="tipo_contenido_producto_ayuda">
                <option selected>Selecciona...</option>
                <option value="piezas">Piezas</option>
                <option value="gramos">Gramos</option>
                <option value="mililitros">Mililitros</option>
                <option value="unidades">Unidades</option>
                <option value="viales">Viales</option>
              </select>
              <div id="tipo_contenido_producto_ayuda" class="form-text">Selecciona el tipo contenido del nuevo producto.</div>
            </div>

          </div>

          <div class="row">

          <!-- Categoria -->
            <div class="mb-3 col-md-6">
              <label for="categoria_producto" class="form-label">Categoría<i class="text-danger">*</i></label>
              <div class="input-group">
                <select required id="categoria_producto" name="categoria_producto" class="form-select form-select mb-3"
                 onchange="updateSubCategorias(this.value)">
                  <option selected>Selecciona...</option>
                  <c:forEach items="${categorias}" var="c">
                    <option value="${c.id}">${c.nombre}</option>
                  </c:forEach>
                </select>
                <span class="input-group-btn">
                    <button type="button" class="btn btn-outline-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#nuevaCategoriaModal"><i class="bi bi-bag-plus-fill"></i> Nueva</button>
                </span>
              </div>
            </div>

          <!-- Subcategoria -->
            <div class="mb-3 col-md-6">
              <label for="subcategoria_producto" class="form-label">SubCategoría<i class="text-danger">*</i></label>
              <div class="input-group">
                <select required id="subcategoria_producto" name="subcategoria_producto" class="form-select form-select mb-3">
                  <option selected>Selecciona...</option>
                  <c:forEach items="${subcategorias}" var="s">
                    <option value="${s.id}">${s.nombre}</option>
                  </c:forEach>
                </select>
                <span class="input-group-btn">
                    <button type="button" class="btn btn-outline-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#nuevaSubCategoriaModal"><i class="bi bi-bag-plus-fill"></i> Nueva</button>
                </span>
              </div>
            </div>

          </div>

          <div class="row">
            <div class="offset-3 col-6 text-center">
              <button class="btn btn-lg btn-primary" type="submit"><i class="bi bi-plus" onclick="this.setAttribute('disabled', true)"></i> Registrar Nuevo Producto</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</main>
<jsp:include page="templates/newDistribuidor.jsp" />
<jsp:include page="templates/newCategoria.jsp" />
<jsp:include page="templates/newSubCategoria.jsp" />
<script>
  window.addEventListener("load", (event) => {
    loadPaises();
  });
</script>
<jsp:include page="templates/footer.jsp" />