<%@page contentType="text/html" pageEncoding="UTF-8" %>
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

    <div class="columns">
        <div class="column">
            <form class="form" action="NovoSiteServlet" method="post">
                <div class="field">
                    <label class="label">Nome</label>
                    <div class="control">
                        <input name="nome" class="input" type="text">
                    </div>
                </div>

                <div class="field">
                    <label class="label">URL</label>
                    <div class="control">
                        <input name="url" class="input" type="text">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Senha</label>
                    <div class="control">
                        <input name="senha" class="input" type="password">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Telefone</label>
                    <div class="control">
                        <input name="telefone" class="input" type="text">
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

