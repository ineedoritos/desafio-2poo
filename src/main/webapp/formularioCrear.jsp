<!DOCTYPE html>
<html>
<head>
    <title>Agregar Producto</title>
</head>
<body>
<h1>Nuevo Producto</h1>
<form action="CrearProducto" method="post">
    Nombre: <input type="text" name="nombre"><br>
    Precio: <input type="number" step="0.01" name="precio"><br>
    Cantidad: <input type="number" name="cantidad"><br>
    Fecha Expiración: <input type="date" name="fecha_expiracion"><br>
    <button type="submit">Guardar</button>
</form>
</body>
</html>