<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
   //User usuAutenticado = (User)session.getAttribute("usuAutenticado");

   //String avatar;
   //String altText = "Imagem da pessoa";

    //tratamento nome do usuario
    //String nomeUsuario= usuAutenticado.getNome();
%>

<!DOCTYPE html>
<html lang="pt-br">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <title>Community Senac</title>
    <link  type="text/css" rel="stylesheet" href="/view/css/home.css">
    <link type="text/css" rel="stylesheet" href="/view/pages/index.css">
</head>

<body>

<header>

    <h1>Community Senac</h1>

    <form action="">
        <input type="text"  placeholder="Pesquisar pelo nome da pessoa">

        <button>
             Pesquisar
        </button>
    </form>

    <section>
         <p> Nome de algum user </p>
         <a href="autenticador">Sair</a>

        <img src= "/view/assents/avatar_placeholder.svg" alt="">
    </section>

</header>

 <div class="container">

    <div class="block">
        <h1>Possiveis conexões</h1>

        <button><span>+</span>Adicionar Filtro</button>
    </div>

    <main>
     <c:forEach var="user" items="${users}">
                <div class="card">
                    <img
                        src= "/view/assents/avatar_placeholder.svg"
                         alt="foto do usuario"
                    >
                    <ul>

                     <li> ${user.nome}</li>
                        <li>instagram</li>
                        <li><a href="/details">Ver mais</a></li>
                     </ul>
                </div>
        </c:forEach>
    </main>
 </div>
</body>