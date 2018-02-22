<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manufacturers & Products</title>
    <style type="text/css">
        td.pad { padding: 10px; }
    </style>
</head>
<body>
<h1>Manufacturers and Products</h1>

<%request.setAttribute("fromWhatServlet","createProduct");%>

<p><a href = /showManufacturers>Manufacturers List</a></p>
<p><a href = /showProducts>Products List</a></p>

</body>
</html>