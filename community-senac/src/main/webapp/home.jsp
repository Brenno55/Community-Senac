<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.User"%>
<%@ page import="java.util.ArrayList"%>
<% ArrayList<User> lista = (ArrayList<User>)request.getAttribute("users");
 User userS = (User)session.getAttribute("sessionUser");
%>

<!DOCTYPE html>
<html lang="pt-br">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <title>Community Senac</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link  type="text/css" rel="stylesheet" href="/view/css/home.css">
    <link type="text/css" rel="stylesheet" href="/view/pages/index.css">

</head>
<body>
<header style="background-color: #000000">

    <a href="/home"><h1>Community Senac</h1></a>

    <form action="/home-header">
        <input type="text" placeholder="Pesquisar por nome ou curso"
        name="search-header" id="search-header"
        >

        <button adicionar type="submit">
             Pesquisar
        </button>
    </form>

    <section>
         <p style="margin-right: 10px;"><%= userS.getNome() %> </p>
         <img name="avatar" id="avatar" src=<%= userS.getImage()%> alt="">
         <ul id="menu" class="menu">
           <li><a href="perfil.jsp">Editar Perfil</a></li>
           <li><a href="autenticador">Sair</a></li>
         </ul>
    </section>

</header>

 <div class="container">

    <div class="block">
        <h1>Possiveis conexões</h1>


    </div>

     <div class="side-bar">
            <form action="/amizades" method="get">
                <input type="submit" id="buttonAmizade" value="Minha Amizades" >
           </form>
        </div>

    <main>
           <% for (int i = 0; i < lista.size() ; i++) {  %>
                <div class="card">
                    <a href="detalhes?email=<%=lista.get(i).getEmail()%>">
                     <img  src=<%= lista.get(i).getImage()%> alt="...">

                          <ul id="menu" class="menu" style="display: none;">
                          <li><a type="submit" href="detalhes.jsp" onclick="return validarPerfilParaDetalhes(event)">Visualizar Perfil</a></li>
                          <li><a href="autenticador">Sair</a></li>
                          </ul>
					</a>


                    <ul>
                        <li><%= lista.get(i).getNome() %> </li>
                        <li><%= lista.get(i).getCurso() %> </li>
                        <li><%= lista.get(i).getDataNascimento() %> anos</li>
                         <li>
                            <form action="/solicitar-amizade" method="post">
                              <input type="hidden" id="emailAmigo" name="amigo" value=<%=lista.get(i).getEmail()%> >
                                <input type="submit" class="buttonAmizade" value="Criar Amizade" >
                            </form>
                         </li>


                    </ul>
                    </div>
                <% } %>
    </main>
    <script src="./scripts/index.js"></script>
 </div>
</body>