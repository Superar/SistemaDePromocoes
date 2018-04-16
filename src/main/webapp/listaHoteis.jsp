<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de Promocoes</title>
    </head>
    <body>
        <h1>Hoteis cadastrados</h1>
        <hr>
        <c:if test="${empty requestScope.listaHoteis}">
            Não há hoteis!
        </c:if>
        <c:if test="${!empty requestScope.listaHoteis}">
            <table>
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
    </body>
</html>