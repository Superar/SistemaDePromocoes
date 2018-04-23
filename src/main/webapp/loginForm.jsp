<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:remove var="role"/>

<html>
<head>
    <link rel="stylesheet" href="style/main.css">
    <title>Login</title>
    <section class="hero is-dark is-fullheight">
        <div class="hero-body">
            <div class="container has-text-centered">
                <div class="column is-4 is-offset-4">
                    <h3 class="title has-text-white">Login</h3>
                    <div class="box">
                        <form action="LoginServlet" method="post">
                            <div class="field">
                                <div class="control">
                                    <input class="input is-large" type="text" placeholder="Seu usuario" autofocus=""
                                           name="usuario">
                                </div>
                            </div>

                            <div class="field">
                                <div class="control">
                                    <input class="input is-large" type="password" placeholder="Sua senha" name="senha">
                                </div>
                            </div>
                            <button class="button is-block is-info is-large is-fullwidth">Login</button>
                            <label class="radio">
                                <input type="radio" name="role" value="admin" checked>
                                Administrador
                            </label>
                            <label class="radio">
                                <input type="radio" name="role" value="hotel">
                                Hotel
                            </label>
                            <label class="radio">
                                <input type="radio" name="role" value="site">
                                Site de reservas
                            </label>
                        </form>
                        <c:if test="${not empty requestScope.erro}">
                            <div class="notification is-danger">
                                <h3>Usuario ou senha invalida</h3>
                            </div>
                        </c:if>
                        <c:remove var="erro" scope="request"/>
                    </div>
                </div>
            </div>
        </div>
    </section>