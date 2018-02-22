<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new Manufacturer</title>
</head>
<body>

<%
    String action = "/index.jsp";

    if(request.getAttribute("fromWhatServlet").equals("createManufacturer")){
        action = "/addManufacturerToDB";
    }
    if(request.getAttribute("fromWhatServlet").equals("updateManufacturer")){
        action = "/updateManufacturerFromDB";
    }
%>

<form action="<%=action%>" method="post">

    <input type="hidden" name="id" value="<%=request.getAttribute("manufacturerIdToUpdate")%>" />

    <h3>Enter name of Manufacturer</h3>
    <input type="text" name="manufacturerName"/>

    <br/>

    <p>Submit button.
        <input type="submit" name="submit" value="submit" /></p>
</form>

</body>
</html>
