<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="home" value="/" scope="application"/>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema de Promoções</title>
</head>
<body>

<jsp:include page="snippets/navbar.jsp"/>

<div class="container">
    <h1 class="title has-text-centered">Bem-vindo ao Sistema de Promoções!</h1>
    <hr/>

    <c:if test="${sessionScope.role == 'admin'}">
        <div class="box">
            <h1 class="subtitle">Adminstrador</h1>
            <a class="button is-success" href="hotelForm.jsp">Cadastrar um hotel</a>
            <a class="button is-info" href="siteForm.jsp">Cadastrar um site</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.role == 'hotel'}">
        <div class="box">
            <h1 class="subtitle">Hotel</h1>
            <a class="button is-success" href="promocaoForm.jsp">Cadastrar Promoção</a>
            <a class="button is-info" href="ListarPromocoesServlet">Listar minhas promoções</a>
        </div>
    </c:if>

    <c:if test="${sessionScope.role == 'site'}">
        <div class="box">
            <h1 class="subtitle">Site</h1>
            <a class="button is-success" href="ListarPromocoesServlet">Listar promoções</a>
        </div>
    </c:if>

    <div class="box">
        <h1 class="subtitle">Opção Geral</h1>
        <a class="button is-dark" href="ListarHoteisServlet">Listar Hotéis</a>
    </div>

</div>

</body>
</html>
