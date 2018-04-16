<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Login<br/>
<form action="LoginServlet" method="post">
    Digite seus dados:<br/>
    Usuário: <input type="text" name="usuario" /><br/>
    Senha: <input type="password" name="senha" /><br/>
    <input type="submit" value="Login" />
</form>
</body>
</html>
