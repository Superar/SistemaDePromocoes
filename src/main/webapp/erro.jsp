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
    <h1 class="title has-text-centered">Opa, parece que ocorreu um problema</h1>
    <hr/>

    <div class="notification is-danger">
        ${mensagem}
    </div>
</div>
</body>
</html>
