<%@ page import="com.example.tp01.Models.Adresse" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.tp01.Models.Carnet" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Carnet Management</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="antialiased h-full w-full ">
        <header class="bg-blue-500 p-4 flex justify-center">
            <a href="/" class="mx-5 text-white hover:text-gray-200">Home</a>
            <a href="/liste" class="mx-5 text-white hover:text-gray-200">Adresses Gestion</a>
            <a href="/carnet" class="mx-5 text-white hover:text-gray-200">Carnets Gestion</a>
        </header>
        <div class="container mx-auto px-4">

        <%
                Carnet carnet = (Carnet) request.getAttribute("carnet");
        %>
            <div class="flex flex-col justify-center items-center p-12 space-y-6">
                <%if(carnet != null){ %>
                    <h1 class="text-3xl font-bold mb-4 text-center">Carnet Management <%=carnet.getNom()%></h1>
                <% }else{ %>
                    <h1 class="text-3xl font-bold mb-4 text-center">Carnet Management </h1>
                <% } %>
                 <form action="/liste" method="GET">
            <!-- <input type="hidden" name="_method" value="RECHERCHE"> -->
                      <% if(carnet != null){ %>
                        <input type="hidden" name="idCarnet" value=<%=carnet.getId()%>>
                      <% } %>
                    <input
                            type="text"
                            name="likeNom"
                            placeholder="Entrer le nom d'une personne"
                            class="w-full rounded-md border border-[#e0e0e0] bg-white py-2 px-4 text-base font-medium text-[#6B7280] outline-none focus:border-[#6A64F1] focus:shadow-md"
                            required
                    >
                    <button
                            type="submit"
                            class="w-1/2 my-3 py-2 px-4 rounded-md text-base font-semibold text-white bg-blue-500 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                    >
                        Submit
                    </button>
                </form>
                <div class="flex flex-col items-center justify-center">
                    <%
                        Adresse adrsPrsn = (Adresse) request.getAttribute("personne");
                       // System.out.println("ligne 45 de mon jsp  -> adresse prs : "+adrsPrsn);
                        if (adrsPrsn != null) {
                            // Code to use adrsPrsn here
                    %>
                    <table class="min-w-full">
                        <thead class="bg-gray-200 border-b">
                        <tr>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                #Id
                            </th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                Nom
                            </th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                Nom rue
                            </th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                Num rue
                            </th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                Nom ville
                            </th>
                            <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-center">
                                Actions
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100">
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">

                        <%= adrsPrsn.getId() %>
                            </td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                <%= adrsPrsn.getNomPersonne() %>
                            </td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                <%= adrsPrsn.getNomRue() %>
                            </td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                <%= adrsPrsn.getNumRue() %>
                            </td>
                            <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                <%= adrsPrsn.getNomVille() %>
                            </td>
                            <td class="flex space-x-4 items-center justify-center my-3">
                                <form action="liste/<%= adrsPrsn.getId() %>" method="POST">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button type="submit"
                                            class="bg-red-600 hover:bg-red-500 transition-all duration-200 px-3 py-1 text-white rounded-md">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <%
                        } else {
                            // Handle the case where adrsPrsn is null (e.g., display a message)
                            String errorMessage = (String) request.getAttribute("errorMessage");
                            if (errorMessage != null) {
                    %>
                    <p class="text-red-500 text-sm font-medium">
                        <%= errorMessage %>
                    </p>
                    <%
                            }
                        }
                    %>
                </div>
            </div>


            <div class="flex items-center justify-center px-12">
                <div class="mx-auto w-full max-w-[550px]">
                    <h1 class="text-3xl font-bold mb-4 text-center">Ajouter adresse</h1>
                    <form action="/liste" method="POST">
                        <input type="hidden" name="_method" value="INSERT">
                        <% if(carnet != null){ %>
                            <input type="hidden" name="idCarnet" value=<%=carnet.getId()%>>
                        <% } %>
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
                                    List<Carnet> carnets = (List<Carnet>) request.getAttribute("listeCarnets");
                                    if (carnets != null) {
                                        for (Carnet carnetList : carnets) {
                                %>
                                <option value=<%=carnetList.getId()%> > <%=carnetList.getNom()%> </option>
                                <%
                                        }
                                    }else{
                                        if(carnet != null){
                                %>
                                <option value=<%=carnet.getId()%> > <%=carnet.getNom()%> </option>
                                <%
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



            <div class="flex flex-col">
                <h2 class="text-2xl font-bold mt-8 mb-2">List d'adresses </h2>
                <div class="overflow-x-auto sm:mx-0.5 lg:mx-0.5">
                    <div class="py-2 inline-block min-w-full sm:px-6 lg:px-8">
                        <div class="overflow-hidden">
                            <table class="min-w-full">
                                <thead class="bg-gray-200 border-b">
                                    <tr>
                                        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                            #Id
                                        </th>
                                        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                            Nom
                                        </th>
                                        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                            Nom rue
                                        </th>
                                        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                            Num rue
                                        </th>
                                        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-left">
                                            Nom ville
                                        </th>
                                        <th scope="col" class="text-sm font-medium text-gray-900 px-6 py-4 text-center">
                                            Actions
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                    List<Adresse> adrsPs = (List<Adresse>) request.getAttribute("listeAdresses");
                                    if (adrsPs != null) {
                                        for (Adresse adr : adrsPs) {
                                %>
                                <tr class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100">
                                    <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                        <%= adr.getId() %>
                                    </td>
                                    <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                        <%= adr.getNomPersonne() %>
                                    </td>
                                    <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                        <%= adr.getNomRue() %>
                                    </td>
                                    <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                        <%= adr.getNumRue() %>
                                    </td>
                                    <td class="text-sm text-gray-900 font-light px-6 py-4 whitespace-nowrap">
                                        <%= adr.getNomVille() %>
                                    </td>
                                    <td class="flex space-x-4 items-center justify-center my-3">
                                        <form action="/liste" method="POST" >
                                            <input type="hidden" name="_method" value="DELETE">
                                            <input type="hidden" name="personneId" value=<%=adr.getId()%>>

                                            <% if(carnet != null){ %>
                                                <input type="hidden" name="idCarnet" value=<%=carnet.getId()%>>
                                            <% } %>
                                            <button type="submit"
                                                    class="bg-red-600 hover:bg-red-500 transition-all duration-200 px-3 py-1 text-white rounded-md">
                                                Delete
                                            </button>
                                        </form>
                                        <a href="/modification?id=<%=adr.getId()%>"
                                           class="bg-blue-600 hover:bg-green-500 transition-all duration-200 px-3 py-1 text-white rounded-md">
                                            modifier
                                        </a>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>