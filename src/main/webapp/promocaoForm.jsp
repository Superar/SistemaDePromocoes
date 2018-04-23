<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${sessionScope.role != 'hotel'}">
    <c:redirect url="/"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style/main.css">

    <title>Cadastrar Nova Promoção</title>
</head>
<body>

<jsp:include page="snippets/navbar.jsp"/>

<div class="container">
    <h1 class="title has-text-centered">Nova Promoção</h1>
    <hr/>
    <div class="columns">
        <div class="column">

            <form class="form" action="NovaPromocaoServlet" method="post">
                <div class="field">
                    <label class="label">URL do Site</label>
                    <div class="control">
                        <input name="URLSite" class="input" type="text" value="${requestScope.novaPromocao.URLSite}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">CNPJ do Hotel</label>
                    <div class="control">
                        <input name="CNPJHotel" class="input" type="text"
                               value="${sessionScope.hotel.CNPJHotel}" disabled>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Preço</label>
                    <div class="control">
                        <input name="preco" class="input" type="text" value="${requestScope.novaPromocao.preco}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Data Inicial</label>
                    <div class="control">
                        <input name="dataInicial" class="input" type="text"
                               value="${requestScope.novaPromocao.dataInicial}">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Data do Fim</label>
                    <div class="control">
                        <input name="dataFinal" class="input" type="text"
                               value="${requestScope.novaPromocao.dataFinal}">
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
                            <h2 class="has-text-white">Promoção cadastrada com sucesso</h2>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
    </div>

</div>

</body>
</html>
