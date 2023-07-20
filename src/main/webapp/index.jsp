<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="templates/header.jsp" />

<main>
    <div class="container-fluid" style="padding-top:60px; text-align: center;">
        <div class="row">
            <div class="d-none d-sm-block offset-md-1 col-md-4 align-content-center">
                <h1>Bienvenido a Zazil</h1><br/>
                <img class="img-fluid" src="assets/img/logo.png" alt="logo">
                <p class="lead">Zazil es una empresa dedicada a la distribución y venta de insumos medicos y esteticos.</p>
            </div>
<c:if test="${not empty sesion}">
    <div class="col-sm-12 col-md-6 align-content-center">
        <div class="row">
            <div class="col-6">
                <h1 class="h3 mb-3 font-weight-normal">Usuario: ${sesion.nombre}</h1>
                <c:if test="${tipoSesion == 'admin'}">
                    <p>Eres <strong>administrador</strong></p>
                </c:if>
            </div>
            <div class="col-6">
                <br>
                <a class="btn btn-danger" href="login"><i class="bi bi-door-open-fill"></i> Cerrar sesión</a>
            </div>
        </div>

        <div class="row">
        <c:forEach items="${operaciones}" var="o">
            <div class="col-4">
                <a href="${o.operacion}?operacion=&quot;&quot;">
                <div class="card shadow-sm">
                    <img class="bd-placeholder-img card-img-top"
                         width="100%"
                         height="160"
                         src="${o.media}"
                         alt="Imagen representativa"
                    />

                    <div class="card-body">
                        <p class="text-break"><strong>${o.operacion}</strong></p>
                    </div>
                </div>
                </a>
            </div>
        </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${empty sesion}">
            <div class="col-sm-12 col-md-6 align-content-center">
                <form class="form-signin" action="login" method="post">
                    <img class="mb-4" src="assets/img/login.png" alt="login" width="350" height="200">
                    <h1 class="h3 mb-3 font-weight-normal">Porfavor inicia sesión:</h1>
                    <label for="inputEmail" class="sr-only">Correo:</label>
                    <input type="text" id="inputEmail" class="form-control" placeholder="Correo electronico" required="" autofocus="" name="email">
                    <label for="inputPassword" class="sr-only">Contraseña:</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Contraseña" required="" name="pass">
                    <br/>
                    <c:if test="${not empty mensaje}">
                        <div class="offset-3 col-6 alert alert-danger alert-dismissible fade show" role="alert">
                            <strong><i class="bi bi-exclamation-triangle-fill"></i> ${mensaje}</strong>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <a class="link-info">¿Has olvidado tu contraseña?</a><br><br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar sesión</button>
                </form>
            </div>
</c:if>
        </div>
        <p class="mt-5 mb-3 text-muted">©Zazil</p>
    </div>
</main>

<jsp:include page="templates/footer.jsp" />