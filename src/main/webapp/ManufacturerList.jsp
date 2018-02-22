<%@ page import="java.util.List" %>
<%@ page import="Model.Manufacturer" %>
<%@ page import="java.util.UUID" %>
<%@ page import="Model.Product" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manufacturers</title>
    <style>
        p {
            text-indent: 50px; /* Отступ первой строки в пикселах */
        }
    </style>
</head>
<body>



<h3>Список производителей</h3>
<%
    List<Manufacturer> manufacturerList = (List<Manufacturer>) request.getAttribute("list");
    for(Manufacturer m: manufacturerList) {

        String name = m.getManufactur_name();
        Set<Product> productList = m.getProducts();

%><p><%=name%>
<form action="/deleteManufacturerFromDB">
    <input type="hidden" name="DELETE" value="<%=m.getId().toString()%>" />
    <input type="submit" value="DELETE ITEM" name="BTN_DELETE">
</form>
<form action="/updateManufacturer">
    <input type="hidden" name="UPDATE" value="<%=m.getId().toString()%>" />
    <input type="submit" value="UPDATE ITEM" name="BTN_UPDATE">
</form><%

    }
%>
<a href="/createManufacturer">Create new</a>
</body>
</html>