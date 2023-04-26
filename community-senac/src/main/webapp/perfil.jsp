

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Community Senac</title>
    <link rel="stylesheet"  href="./view/pages/index.css" media="screen" />
    <link rel="stylesheet" href="./view/pages/perfil/perfil.css" media="screen"/>
</head>
<body>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.User"%>
<% User user = (User)session.getAttribute("sessionUser"); %>


<container class="container" >
    <h1>Edite seu perfil</h1>

    <form action="/perfil" name="frmContato" method="post">


        <input type="date" name="data_nascimento" placeholder="">
        <input type="text" name="email" placeholder="Email" value=<%=user.getEmail()%>>
        <input type="int"  name="celular" placeholder="Celular">
        <select name="Curso">
            <option value="null">Curso</option>
            <option value="analise">ADS</option>
            <option value="analise">Administração</option>
            <option value="analise">Redes de computadores</option>
            <option value="analise">Nutrição</option>
        </select>
        <select name="sexo">
            <option value="null">sexo</option>
            <option value="masculino">Masculino</option>
            <option value="feminino">Feminino</option>
        </select>
        <input type="text" name="bio" placeholder="Biografia">

        <button onclick="validarPerfil()">Finalizar</button>
        <script src="./scripts/validador.js"></script>
    </form>



</container>
</body>
</html>