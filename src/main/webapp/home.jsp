<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.*, model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Producto" %>
<%@ page import="model.ProductoDAO" %>

<%
    // Verificación de sesión
    HttpSession session2 = request.getSession(false);
    Usuario usuario = null;

    if (session2 != null) {
        usuario = (Usuario) session2.getAttribute("usuario");
    }

    if (usuario == null) {
        response.sendRedirect("Login.html");
        return;
    }

    String email = usuario.getEmail();

    // Obtener lista de productos desde la base de datos
    ProductoDAO productoDAO = new ProductoDAO();
    List<Producto> productos = productoDAO.listar(); // Obtener todos los productos de la base de datos
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Bienvenido, <%= email %>!</h1>

<form action="logout" method="post">
    <button type="submit">Cerrar sesión</button>
</form>

<h2>Lista de Productos</h2>

<a href="formularioCrear.jsp">Agregar nuevo producto</a>

<table border="1">
    <tr>
        <th>ID</th><th>Nombre</th><th>Precio</th><th>Cantidad</th><th>Fecha Expiración</th><th>Acciones</th>
    </tr>
    <%
        for (Producto p : productos) {
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getPrecio() %></td>
        <td><%= p.getCantidad() %></td>
        <td><%= p.getFechaExpiracion() %></td>
        <td>
            <a href="MostrarFormularioEditar?id=<%= p.getId() %>">Editar</a>
            <form action="EliminarProducto" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= p.getId() %>"> <!-- El id del producto a eliminar -->
                <button type="submit">Eliminar</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>

</body>
</html>
