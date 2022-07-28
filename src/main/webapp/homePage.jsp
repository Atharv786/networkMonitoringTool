<%--
  Created by IntelliJ IDEA.
  User: atharv
  Date: 14/4/22
  Time: 12:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.*"%>
<!DOCTYPE html>
<html>

<head>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <script src="JavaScript/Discovery.js"></script>
    <script src="JavaScript/loginJS.js"></script>
    <script src="JavaScript/homePageJS.js"></script>
    <script src="JavaScript/Alerts.js"></script>
    <script src="JavaScript/DashBoard.js"></script>
    <script src="JavaScript/Monitor.js"></script>
    <script src="JavaScript/Ajax.js"></script>
    <script src="JavaScript/Websocket.js"></script>
    <script src="JavaScript/Validator.js"></script>


    <script src=https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>


    <link rel="stylesheet" href="homePageCSS.css">
    <link rel="stylesheet" href="DashBoard.css">
    <link rel="stylesheet" href=https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">

</head>
<body>


<div class="nav">
    <ul>
        <li><a class="Home"><i class="fa fa-home icon"></i> Dashboard</a></li>
        <li><a class="AboutUs"><i class="fa fa-search icon"></i> Discovery</a></li>
        <li><a class="ContactUs"><i class="fa fa-desktop icon"></i> Monitor</a></li>
        <div class="navbar">
            <li><a class="SignOut" id="SignOut"><i class="fa fa-sign-out icon" style="font-size: 18px;"></i> SignOut</a></li>
        </div>
    </ul>
</div>


<div id="content">
</div>


</body>
</html>

