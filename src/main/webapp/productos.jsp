<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="templates/header.jsp" />
<main>
    <div class="container-fluid">
        <div class="row">
            <div class="text-center offset-2 col-8" style="padding-top: 60px">
                <h2>Administración de productos</h2>
                <br class="d-md-none">
                <form method="get" action="Productos">
                    <input type="hidden" name="operacion" value="nuevo">
                    <button class="btn btn-lg btn-primary" type="submit"><i class="bi bi-plus-square-fill"></i> Nuevo producto</button>
                </form>
                <form method="get" action="Productos">
                    <input type="hidden" name="operacion" value="reporte">
                    <button class="btn btn-lg btn-primary" type="submit"><i class="bi bi-file-pdf"></i> Descargar Reporte</button>
                </form>
            </div>
            <div class="offset-2 col-8" style="padding-top: 60px">
                <table id="tabla" class="table table-striped nowrap" style="width:100%">
                    <thead>
                        <th>Nombre</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th colspan="2">Contenido</th>
                        <th>Lote</th>
                        <th>Caducidad</th>
                        <th>Distribuidor</th>
                        <th>Costo</th>
                        <th>Venta</th>
                        <th>Categoria</th>
                        <th>SubCategoria</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${productos}" var="p">
                            <tr>
                                <td>${p.nombre}</td>
                                <td>${p.marca}</td>
                                <td>${p.modelo}</td>
                                <td>${p.contenido}</td>
                                <td>${p.tipo_contenido}</td>
                                <td>${p.lote}</td>
                                <td>${p.caducidad}</td>
                                <td>${p.distribuidor.nombre_distribuidor}</td>
                                <td>${p.precio_costo}</td>
                                <td>${p.precio_venta}</td>
                                <td>${p.categoria.nombre}</td>
                                <td>${p.subCategoria.nombre}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<script src="${pageContext.request.contextPath}/assets/js/jquery-3.7.0.min.js" ></script>
<script src="${pageContext.request.contextPath}/assets/js/jquery.dataTables.min.js" ></script>
<script src="${pageContext.request.contextPath}/assets/js/es-MX.json"></script>
<script>
    $(document).ready(function() {
        $('#tabla').DataTable({
            language: {
                url: "${pageContext.request.contextPath}/assets/js/es-MX.json"
            }
        });
    });
</script>
<jsp:include page="templates/footer.jsp" />
