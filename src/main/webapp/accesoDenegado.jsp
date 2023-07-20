<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="templates/headerSinBarra.jsp" />
<main>
    <div class="d-flex align-items-center justify-content-center vh-100">
        <div class="text-center row">
            <div class="offset-md-1 col-md-5">
                <img src="${pageContext.request.contextPath}/assets/img/busted.jpg" alt=""
                     class="img-fluid">
            </div>
            <div class="col-md-5 mt-5">
                <p class="fs-3"> <span class="text-danger"><i class="bi bi-sign-stop-fill"></i> Aceso denegado <i class="bi bi-sign-stop-fill"></i></span></p>
                <p class="lead">
                    No tienes permiso para ver esta página, comuníquese con el administrador para obtener los permisos.
                </p>
                <a href="index.jsp" class="btn btn-primary">Ir a la página principal</a>
            </div>

        </div>
    </div>
</main>
<jsp:include page="templates/footer.jsp" />
