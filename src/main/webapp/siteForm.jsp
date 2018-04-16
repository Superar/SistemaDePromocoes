<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Novo Site</title>
    </head>
    <body>
        <form action="NovoSiteServlet" method="post">
            Digite seus dados:<br/>
            Url: <input name="url" type="text" /><br/>
            Nome: <input name="nome" type="text" /><br/>
            Senha <input name="senha" type="text" /><br/>
            Telefone: <input name="telefone" type="text" /><br/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
