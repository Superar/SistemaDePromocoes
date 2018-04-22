<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.role != 'admin'}">
    <c:redirect url="/"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <title>Cadastrar Novo Site</title>
</head>

<body>

<jsp:include page="snippets/navbar.jsp"/>

<div class="container">
    <h1 class="title has-text-centered">Cadastro de Novo Site</h1>
    <hr/>
    <div class="columns">
        <div class="column">
            <form class="form" action="NovoSiteServlet" method="post">
                <div class="field">
                    <label class="label">Nome</label>
                    <div class="control">
                        <input name="nome" class="input" type="text" value="${requestScope.novoSite.nome}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">URL</label>
                    <div class="control">
                        <input name="url" class="input" type="text" value="${requestScope.novoSite.url}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Senha</label>
                    <div class="control">
                        <input name="senha" class="input" type="password" value="${requestScope.novoSite.senha}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Telefone</label>
                    <div class="control">
                        <input name="telefone" class="input" type="text" value="${requestScope.novoSite.telefone}">
                    </div>
                </div>

                <div class="field is-grouped">
                    <div class="control">
                        <input class="button is-link" type="submit" value="Enviar"/>
                    </div>
                    <div class="control">
                        <a href="${home}" class="button is-danger">Cancelar</a>
                    </div>
                </div>

            </form>
        </div>

        <div class="column">
            <c:if test="${requestScope.formEnviado}">
                <c:choose>
                    <c:when test="${not empty requestScope.erros}">
                        <div class="notification is-danger content">
                            <h2 class="has-text-white">Não foi possível cadastrar</h2>
                            <ul>
                                <c:forEach items="${requestScope.erros}" var="erro">
                                    <li>${erro}</li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="notification is-success content">
                            <h2 class="has-text-white">Site cadastrado com sucesso</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>

</div>

</body>
</html>

