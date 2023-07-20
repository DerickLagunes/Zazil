<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
    <%
        request.getSession().removeAttribute("mensaje");
    %>
</footer>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.js" ></script>
</body>
</html>
