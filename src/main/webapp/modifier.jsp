<%@ page import="com.example.tp01.Models.Adresse" %>
<%@ page import="com.example.tp01.Models.Carnet" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lhadi
  Date: 28/03/2024
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Modifier</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="antialiased h-full w-full ">
    <header class="bg-blue-500 p-4 flex justify-center">
        <a href="/" class="mx-5 text-white hover:text-gray-200">Homme</a>
        <a href="/liste" class="mx-5 text-white hover:text-gray-200">Adresses Gestion</a>
        <a href="/carnet" class="mx-5 text-white hover:text-gray-200">Carnets Gestion</a>
    </header>
    <%
        Adresse adrsPrsn = (Adresse) request.getAttribute("personne");
    %>
    <%
        String message = (String) session.getAttribute("message");
        if (message != null) {
    %>
    <div id="messageDiv" class=" my-5 alert alert-info mx-auto text-center w-1/4 bg-green-500 text-white p-4 rounded-md shadow-md">
        <%= message %>
    </div>
    <%
            session.removeAttribute("message");
        }
    %>
    <div class="flex items-center justify-center px-12 my-5">

        <div class="mx-auto w-full max-w-[550px]">
            <h1 class="text-3xl font-bold mb-4 text-center">Modifier adresse</h1>
            <form action="modification" method="POST">
                <input type="hidden" name="idPersonne" value=<%= adrsPrsn.getId() %>>
                <div class="mb-5">
                    <label
                            class="mb-3 block text-base font-medium text-[#07074D]"
                    >
                        Nom Personne
                    </label>
                    <input
                        type="text"
                        name="nom"
                        placeholder="Nom Personne"
                        class="w-full rounded-md border border-[#e0e0e0] bg-white py-2 px-4 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
                        value="<%= adrsPrsn.getNomPersonne() %>"
                        required
                    />
                </div>
                <div class="mb-5">
                    <label
                            class="mb-3 block text-base font-medium text-[#07074D]"
                    >
                        Nom de la rue
                    </label>
                    <input
                            type="text"
                            name="nomRue"
                            placeholder="Nom de la rue "
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-2 px-4 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
                            value="<%= adrsPrsn.getNomRue() %>"
                            required
                    />
                </div>
                <div class="mb-5">
                    <label
                            class="mb-3 block text-base font-medium text-[#07074D]"
                    >
                        Numéro de la rue
                    </label>
                    <input
                            type="text"
                            name="numRue"
                            placeholder="Num Rue "
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
                            value="<%= adrsPrsn.getNumRue() %>"
                            required
                    />
                </div>
                <div class="mb-5">
                    <label
                            class="mb-3 block text-base font-medium text-[#07074D]"
                    >
                        Nom de la ville
                    </label>
                    <input
                            type="text"
                            name="ville"
                            placeholder="Ville"
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
                            value="<%= adrsPrsn.getNomVille() %>"
                            required
                    />
                </div>

                <div class="mb-5">
                    <label
                            class="mb-3 block text-base font-medium text-[#07074D]"
                    >
                        choisir un carnet pour cette personne
                    </label>

                    <select
                            name="carnetId"
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-3 px-6 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
                    >

                        <%
                            Carnet carnet = (Carnet) request.getAttribute("carnet");
                            if(carnet != null ) {
                        %>
                                <option value=<%=carnet.getId()%> selected> <%=carnet.getNom()%> </option>
                        <%
                            }
                        %>

                        <%
                            List<Carnet> carnets = (List<Carnet>) request.getAttribute("listeCarnets");
                            if (carnets != null) {
                                for (Carnet carnetList : carnets) {
                                    if(carnet.getId() != carnetList.getId() ) {
                        %>
                                <option value=<%=carnetList.getId()%> > <%=carnetList.getNom()%> </option>
                        <%
                                    }
                                }
                            }
                        %>
                    </select>

                </div>


                <div class="mt-8">
                    <button type="submit"
                            class="hover:shadow-form rounded-md bg-blue-600 hover:bg-blue-500 transition-all duration-100 py-3 px-8 w-full text-base font-semibold text-white outline-none"
                    >
                        Submit
                    </button>
                </div>
            </form>
        </div>
    </div>

</body>
    <script>
        setTimeout(function() {
            document.getElementById('messageDiv').classList.add('hidden');
        }, 2500);
    </script>
</html>
