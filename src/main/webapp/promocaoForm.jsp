<%@page contentType="text/html" pageEncoding="UTF-8" %>
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

    <div class="columns">
        <div class="column">

            <form class="form" action="NovaPromocaoServlet" method="post">
                <div class="field">
                    <label class="label">URL do Site</label>
                    <div class="control">
                        <input name="URLSite" class="input" type="text">
                    </div>
                </div>

                <div class="field">
                    <label class="label">CNPJ do Hotel</label>
                    <div class="control">
                        <input name="CNPJHotel" class="input" type="text">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Preço</label>
                    <div class="control">
                        <input name="preco" class="input" type="text">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Data Inicial</label>
                    <div class="control">
                        <input name="dataInicial" class="input" type="text">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Data do Fim</label>
                    <div class="control">
                        <input name="dataFinal" class="input" type="text">
                    </div>
                </div>

                <div class="field is-grouped">
                    <div class="control">
                        <input class="button is-link" type="submit" value="Enviar"/>
                    </div>
                    <div class="control">
                        <button class="button">Cancelar</button>
                    </div>
                </div>

            </form>

        </div>

        <div class="column">

        </div>
    </div>

</div>

</body>
</html>
