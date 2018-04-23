<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar is-info">
    <div class="navbar-start">
        <a class="navbar-item" href="${home}">Home</a>
    </div>

    <div class="navbar-end">
        <c:choose>
            <c:when test="${not empty sessionScope.role}">
                <div class="navbar-item">
                    <div class="tags has-addons">
                        <c:if test="${sessionScope.role == 'admin'}">
                            <span class="tag is-dark">Administrador</span>
                            <span class="tag">${sessionScope.usuario.login}</span>
                        </c:if>

                        <c:if test="${sessionScope.role == 'hotel'}">
                            <span class="tag is-dark">Hotel</span>
                            <span class="tag">${sessionScope.usuario.nome}</span>
                        </c:if>

                        <c:if test="${sessionScope.role == 'site'}">
                            <span class="tag is-dark">Site</span>
                            <span class="tag">${sessionScope.usuario.nome}</span>
                        </c:if>
                    </div>
                </div>

                <div class="navbar-item">
                    <a class="button is-outlined is-inverted is-info" href="loginForm.jsp">
                        Logout
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="navbar-item">
                    <a class="button is-outlined is-inverted is-info" href="loginForm.jsp">
                        Login
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>