<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sistema de Promoções</title>
</head>
<body>

<jsp:include page="snippets/navbar.jsp" />

<div class="container">
    <h1 class="title has-text-centered">Bem-vindo ao Sistema de Promocoes!</h1>
    <hr>
    <div class="columns">

        <div class="column">
            <h1 class="subtitle">Não logado</h1>
            <a href="ListarHoteisServlet">Listar Hotéis</a><br/>
        </div>

        <div class="column">
            <h1 class="subtitle">Adminstrador</h1>
            <a href="hotelForm.jsp">Cadastrar um hotel</a><br/>
            <a href="siteForm.jsp">Cadastrar um site</a><br/>
        </div>

        <div class="column">
            <h1 class="subtitle">Hotel</h1>
            <a href="promocaoForm.jsp">Cadastrar Promoção</a><br/>
            <a href="ListarPromocoesServlet">Listar minhas promoções</a><br/>
        </div>

        <div class="column">
            <h1 class="subtitle">Site</h1>
            <a href="ListarPromocoesServlet">Listar promoções</a><br/>
        </div>

    </div>
</div>

</body>
</html>
