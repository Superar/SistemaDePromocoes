<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cadastrar Novo Hotel</title>
    </head>
    <body>
        <form action="NovoHotelServlet" method="post">
            Digite seus dados:<br/>
            CNPJ: <input name="CNPJ" type="text" /><br/>
            Nome: <input name="nome" type="text" /><br/>
            Senha <input name="senha" type="text" /><br/>
            Cidade: <input name="cidade" type="text" /><br/>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
