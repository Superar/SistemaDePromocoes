<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <title>Cadastrar Novo Hotel</title>
</head>
<body>

<jsp:include page="snippets/navbar.jsp"/>

<div class="container">
    <h1 class="title has-text-centered">Cadastro de Novo Hotel</h1>

    <div class="columns">
        <div class="column">

            <form class="form" action="NovoHotelServlet" method="post">
                <div class="field">
                    <label class="label">Nome</label>
                    <div class="control">
                        <input name="nome" class="input" type="text" value="${novoHotel.nome}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">CNPJ</label>
                    <div class="control">
                        <input name="cnpj" class="input" type="text" value="${novoHotel.cnpj}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Senha</label>
                    <div class="control">
                        <input name="senha" class="input" type="password" value="${novoHotel.senha}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Cidade</label>
                    <div class="control">
                        <input name="cidade" class="input" type="text" value="${novoHotel.cidade}">
                    </div>
                </div>

                <div class="field is-grouped">
                    <div class="control">
                        <input class="button is-link" type="submit" value="Enviar"/>
                    </div>
                    <div class="control">
                        <a class="button" href="/">Cancelar</a>
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
                            <h2 class="has-text-white">Hotel cadastrado com sucesso!</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>

</div>

</body>
</html>
