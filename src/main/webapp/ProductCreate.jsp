<%@ page import="Model.Manufacturer" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Product</title>
</head>
<body>

<%
    String action = "/index.jsp";

    if(request.getAttribute("fromWhatServlet").equals("createProduct")){
        action = "/addProductToDB";
    }
    if(request.getAttribute("fromWhatServlet").equals("updateProduct")){
        action = "/updateProductFromDB";
    }
%>

    <form action="<%=action%>" method="post">

        <input type="hidden" name="id" value="<%=request.getAttribute("productIdToUpdate")%>" />

        <h3>Enter name of product</h3>
        <input type="text" name="productName"/>

        <br/>

        <h3>Enter price of product</h3>
        <input type="number" name="productPrice"/>

        <br/>

        <h3>Choise the manufacturer</h3>
        <select name="manufacturer">
            <%
                List<Manufacturer> list = (List<Manufacturer>) request.getAttribute("list");
                for(Manufacturer m: list){
            %> <option value="<%=m.getId()%>"><%=m.getManufactur_name()%></option> <%
            }
        %>
        </select>

        <p>Submit button.
            <input type="submit" name="submit" value="submit" /></p>
    </form>

</body>
</html>
