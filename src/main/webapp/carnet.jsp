<%@ page import="com.example.tp01.Models.Carnet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lhadi
  Date: 05/03/2024
  Time: 11:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Carnet </title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex flex-col ">
<header class="bg-blue-500 p-4 flex justify-center">
    <a href="/" class="mx-5 text-white hover:text-gray-200">Home</a>
    <a href="/liste" class="mx-5 text-white hover:text-gray-200">Adresses Gestion</a>
    <a href="/carnet" class="mx-5 text-white hover:text-gray-200">Carnets Gestion</a>
</header>
<h1 class="text-3xl font-bold text-center mt-10">Gestion des carnets</h1>
<form class="w-full max-w-md mx-auto mt-10" action="carnet/add" method="POST">    <input type="hidden" name="ajouter">
    <input type="hidden" name="ajouter">
    <div class="flex flex-wrap -mx-3 mb-6">
        <div class="w-full px-3">
            <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="nom">
                Ajouter un carnet
            </label>
            <input
                    type="text"
                    name="nom"
                    placeholder="Le nom du carnet"
                    class="appearance-none block w-full bg-gray-200 text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"
                    id="nom"
            >
        </div>
    </div>
    <div class="flex items-center justify-between">
        <button
                type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
        >
            Créer carnet
        </button>
    </div>
</form>
<div class="w-full max-w-md mx-auto mt-10">
    <ul class="space-y-4">
        <%
            List<Carnet> carnetList = (List<Carnet>) request.getAttribute("carnets");
            if (carnetList != null) {
                for (Carnet carnet : carnetList) {
        %>
        <li class="flex justify-between items-center border p-4 rounded shadow">
            <span class="text-lg font-medium"><%=carnet.getNom()%></span>
            <div>
                <form action="liste/get/" method="GET" class="inline">
                    <input type="hidden" name="idCarnet" value=<%=carnet.getId()%>>
                    <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Lister</button>
                </form>
                <form action="<%=request.getContextPath()%>/carnet/delete" method="POST" class="inline ml-2">
                    <input type="hidden" name="id" value=<%=carnet.getId()%>>
                    <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Supprimer</button>
                </form>
            </div>
        </li>
        <%
                }
            }
        %>
    </ul>
</div>

</body>
</html>
