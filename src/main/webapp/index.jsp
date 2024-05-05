<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Carnet managment</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="antialiased h-full w-full">
<header class="bg-blue-500 p-4 flex justify-center">
    <a href="/" class="mx-5 text-white hover:text-gray-200">Home</a>
    <a href="/liste" class="mx-5 text-white hover:text-gray-200">Adresses Gestion</a>
    <a href="/carnet" class="mx-5 text-white hover:text-gray-200">Carnets Gestion</a>
</header>
<div class="flex flex-col items-center justify-center space-y-6 py-24">
    <h1 class="text-blue-500 font-bold">Carnet Management</h1>
    <a href="${pageContext.request.contextPath}/liste"
       class="bg-blue-600 hover:bg-blue-500 px-4 py-2 text-white rounded-md transition-all duration-200">Add
        and view Adresses
    </a>
    <a href="${pageContext.request.contextPath}/carnet"
       class="bg-blue-600 hover:bg-blue-500 px-4 py-2 text-white rounded-md transition-all duration-200">Add
        gestion des carnets
    </a>
</div>
</body>
</html>