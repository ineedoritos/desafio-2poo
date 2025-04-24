<%@ page import="model.Producto" %>
<%
    // Obtener el producto pasado como atributo desde el servlet
    Producto producto = (Producto) request.getAttribute("producto");

    // Formatear la fecha a 'yyyy-MM-dd' para que funcione correctamente con el campo tipo date
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fechaFormateada = sdf.format(producto.getFechaExpiracion());
%>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Producto</title>
</head>
<body>
<h1>Editar Producto</h1>
<form action="EditarProducto" method="post">
    <!-- Campo oculto para el ID del producto -->
    <input type="hidden" name="id" value="<%= producto.getId() %>">

    <!-- Campos para editar los detalles del producto -->
    Nombre: <input type="text" name="nombre" value="<%= producto.getNombre() %>"><br>
    Precio: <input type="number" step="0.01" name="precio" value="<%= producto.getPrecio() %>"><br>
    Cantidad: <input type="number" name="cantidad" value="<%= producto.getCantidad() %>"><br>

    <!-- Campo de fecha con la fecha formateada -->
    Fecha Expiración: <input type="date" name="fecha_expiracion" value="<%= fechaFormateada %>"><br>

    <button type="submit">Actualizar</button>
</form>

</body>
</html>
