<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Hotéis</title>
</head>
<body>

<jsp:include page="snippets/navbar.jsp"/>

<div class="container">
    <h1 class="title has-text-centered">Lista de Hotéis</h1>

    <form action="ListarHoteisServlet" method="get">
        <div class="field has-addons">
            <div class="control">
                <input class="input" name="cidade" type="text" placeholder="Procurar pela cidade">
            </div>
            <div class="control">
                <button type="submit" class="button is-info">
                    Procurar
                </button>
            </div>
        </div>
    </form>

    <hr>
    <c:if test="${empty requestScope.listaHoteis}">
        <h2 class="subitle has-text-centered">Não há hoteis!</h2>
    </c:if>
    <c:if test="${!empty requestScope.listaHoteis}">
        <table class="table is-hoverable is-fullwidth">
            <tr>
                <th>Nome</th>
                <th>Cidade</th>
                <th>CNPJ</th>
            </tr>
            <c:forEach items="${requestScope.listaHoteis}" var="hotel">
                <tr>
                    <td>${hotel.nome}</td>
                    <td>${hotel.cidade}</td>
                    <td>${hotel.CNPJ}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>