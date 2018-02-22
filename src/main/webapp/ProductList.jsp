<%@ page import="java.util.List" %>
<%@ page import="Model.Manufacturer" %>
<%@ page import="java.util.UUID" %>
<%@ page import="Model.Product" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <style>
        p {
            text-indent: 50px; /* Отступ первой строки в пикселах */
        }
    </style>
</head>
<body>



<h3>Список товаров</h3>
<%
    List<Product> productList = (List<Product>) request.getAttribute("list");
    for(Product p: productList) {

        UUID id = p.getId();
        String strId = id.toString();

        String name = p.getName();
        BigDecimal price = p.getPrice();
        Manufacturer manufacturer = p.getManufacturer();

%><%=name+";"+price+manufacturer.getManufactur_name()%>
<form action="/deleteProjectFromDB">
    <input type="hidden" name="DELETE" value="<%=p.getId().toString()%>" />
    <input type="submit" value="DELETE ITEM" name="BTN_DELETE">
</form>
<form action="/updateProduc">
    <input type="hidden" name="UPDATE" value="<%=p.getId().toString()%>" />
    <input type="submit" value="UPDATE ITEM" name="BTN_UPDATE">
</form>
    <%

    }
%>
<a href="/createProduct">Create new</a>
</body>
</html>