<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Promoções</title>
</head>
<body>

<jsp:include page="snippets/navbar.jsp"/>

<div class="container">
    <h1 class="title has-text-centered">Lista de Promoções</h1>
    <hr/>
    <c:choose>
        <c:when test="${empty requestScope.listaPromocoes}">
            <h2 class="subtitle has-text-centered">Não há promoções</h2>
        </c:when>
        <c:otherwise>
            <table class="table is-hoverable is-fullwidth">
                <thead>
                <tr>
                    <th>Site</th>
                    <th>Hotel</th>
                    <th>Preço</th>
                    <th>Data</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.listaPromocoes}" var="promocao">
                    <tr>
                        <td>${promocao.URLSite}</td>
                        <td>${promocao.CNPJHotel}</td>
                        <td>R$ ${promocao.preco}</td>
                        <td>${promocao.getDataInicialString()} - ${promocao.getDataFinalString()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>
