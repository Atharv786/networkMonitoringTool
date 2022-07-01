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
        <script src="loginJS.js"></script>
        <script src="homePageJS.js"></script>
        <script src="Polling.js"></script>
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
        <li><a class="Home" style="background-color: white; color: #36a9e1;"><i class="fa fa-home icon"></i> Dashboard</a></li>
        <li><a class="AboutUs"><i class="fa fa-search icon"></i> Discovery</a></li>
        <li><a class="ContactUs"><i class="fa fa-desktop icon"></i> Monitor</a></li>
        <div class="navbar">
            <li><a class="SignOut" id="SignOut"><i class="fa fa-sign-out icon" style="font-size: 18px;"></i> SignOut</a></li>
        </div>
    </ul>
</div>


<button onclick="document.getElementById('id01').style.display='block'" class="addDeviceButton">Add Device</button>


<div class="discovery" style="background-color:white; display:none; width: 70%; margin-left: 15%; margin-top: 5%">
    <h2 class="h1">Discovery Table</h2>
    <table id="discoveryTable" style="width: 98%; background-color: #f1f1f1">
        <thead>
        <tr>
            <th>Name</th>
            <th>IP/Host</th>
            <th>Type</th>
            <th>Operation</th>
        </tr>
        </thead>

    </table>
</div>


<div class="monitor" style="background-color:white; display:none; width: 70%; margin-left: 15%; margin-top: 5%">
    <h2 class="h1">Monitor Table</h2>
    <table id="monitorTable" style="width: 98%; background-color: #f1f1f1">
        <thead>
        <tr>
            <th>IP/Host</th>
            <th>Type</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        </thead>

    </table>
</div>


<div id="id01" class="modal">
    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
    <form class="modal-content">
        <div class="container">
            <h1>Add Device</h1>
            <hr>
            <label for="addDeviceName"><b>Device Name</b></label>
            <input type="text" placeholder="Enter Name" name="addDeviceName" id="addDeviceName" >

            <label for="addDeviceIP"><b>IP/Host</b></label>
            <input type="text" placeholder="Enter IP/Host" name="addDeviceIP" id="addDeviceIP" >

            <label for="addDeviceType"><b>Device Type</b></label>
            <select name="addDeviceType" id="addDeviceType">
                <option id="Ping" value="Ping">Ping</option>
                <option id="SSH" value="SSH">SSH</option>
            </select>

            <label for="addDeviceUsername" id="addDeviceUsernameLabel" style="display: none"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="addDeviceUsername" id="addDeviceUsername" style="display: none" >

            <label for="addDevicePassword" id="addDevicePasswordLabel" style="display: none"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="addDevicePassword" id="addDevicePassword" style="display: none" >

            <div class="clearfix">
                <input type="reset"  id="reset" name="reset" class="reset"></input>
                <input type="button" class="addDevice" value="Add Device" id="addDevice" name="addDevice"></input>
            </div>
        </div>
    </form>
</div>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event)
    {
        if (event.target == modal)
        {
            modal.style.display = "none";
        }
    }
</script>




<div id="id02" class="modal2" style="display: none">
    <span onclick="document.getElementById('id02').style.display='none'" class="close2" title="Close Modal">&times;</span>
    <form class="modal-content2">
        <div class="container2">
            <h1>Update Device</h1>
            <hr>

            <input type="text" name="type" id="type" style="display: none" >
            <input type="text" name="ip" id="ip" style="display: none">
            <input type="text" name="id" id="id" style="display: none" >


            <label for="addDeviceName2"><b>Device Name</b></label>
            <input type="text" placeholder="Enter Name" name="addDeviceName2" id="addDeviceName2" >

            <label for="addDeviceIP2"><b>IP/Host</b></label>
            <input type="text" placeholder="Enter IP/Host" name="addDeviceIP2" id="addDeviceIP2" >

            <label for="addDeviceType2"><b>Device Type</b></label>
            <select name="addDeviceType2" id="addDeviceType2">
                <option id="Ping2" value="Ping">Ping</option>
                <option id="SSH2" value="SSH">SSH</option>
            </select>

            <label for="addDeviceUsername2" id="addDeviceUsernameLabel2" style="display: none"><b>Username</b></label>
            <input type="text" placeholder="Enter Username" name="addDeviceUsername2" id="addDeviceUsername2" style="display: none" >

            <label for="addDevicePassword2" id="addDevicePasswordLabel2" style="display: none"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="addDevicePassword2" id="addDevicePassword2" style="display: none" >

            <div class="clearfix2">
                <input type="button" class="update" value="Update" id="update" name="update"></input>
            </div>
        </div>
    </form>
</div>

<div class="Dashboard">

    <div class="MonitorAvailability">

        <h2 style="text-align: center; padding: 14px; margin-bottom: 50px;">Monitor Availability</h2>

        <div class="Up">

            <h3 style="text-align: center;">UP :</h3>
            <h3 id="Updata" style="text-align: center;"></h3>

        </div>

        <div class="Down">

            <h3 style="text-align: center;">DOWN :</h3>
            <h3 id="Downdata" style="text-align: center;"></h3>

        </div>

        <div class="Unknown">

            <h3 style="text-align: center;">UNKNOWN :</h3>
            <h3 id="Unknowndata" style="text-align: center;"></h3>

        </div>

        <div class="Total">

            <h3 style="text-align: center;">TOTAL :</h3>
            <h3 id="Totaldata" style="text-align: center;"></h3>


        </div>

    </div>

    <div class="Top5Monitor">

        <h3 style="text-align: center;">Top 5 Monitors By Response Time (ms)</h3>

        <table id="tbodytoprtt" style="width: 68%; background-color: #f1f1f1">
            <thead>
            <tr>
                <th>Device IP</th>
                <th>RTT(ms)</th>
            </tr>
            </thead>

        </table>

    </div>

    <div class="MonitorGroup">

        <h3 style="text-align: center;">Monitors Group</h3>
        <table id="tbodymonitorgroup" style="width: 68%; background-color: #f1f1f1">
            <thead>
            <tr>
                <th>Device Type</th>
                <th>Up</th>
                <th>Down</th>
                <th>Total</th>
            </tr>
            </thead>

        </table>

    </div>

    <div class="Top5Cpu">

        <h3 style="text-align: center;">Top 5 Monitors By CPU (%)</h3>

        <table id="tbodytopcpu" style="width: 68%; background-color: #f1f1f1">
            <thead>
            <tr>
                <th>Device IP</th>
                <th>CPU</th>
            </tr>
            </thead>

        </table>

    </div>

    <div class="Top5Memory">

        <h3 style="text-align: center;">Top 5 Monitors By Memory (mb)</h3>

        <table id="tbodytopmemory" style="width: 68%; background-color: #f1f1f1">
            <thead>
            <tr>
                <th>Device IP</th>
                <th>Memory</th>
            </tr>
            </thead>

        </table>

    </div>

    <div class="Top5Disk">

        <h3 style="text-align: center;">Top 5 Monitors By Disk (%)</h3>

        <table id="tbodytopdisk" style="width: 68%; background-color: #f1f1f1">
            <thead>
            <tr>
                <th>Device IP</th>
                <th>Disk</th>
            </tr>
            </thead>

        </table>

    </div>

</div>

<div class="PollingData" style="display: none">

    <div class="Heading" style="display: flex;justify-content: space-between;">
        <h2 style="font-family:Arial, Helvetica, sans-serif; font-size: 35px; margin: 5px; margin-left:10px; padding: 0%; color: black;">Device Status</h2>
        <span style="font-size:16px; position: absolute;
    right: 35px;
    top: 7px;
    font-size: 40px;
    font-weight: bold;
    color: black;" id="closePolling">&times;</span>
    </div>

    <div class="Chart" style="width: 29%; height: 330px; background-color: white; margin-top: 20px; margin-left: 1%; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));">
        <h3 style="font-size: 25px; text-align: center; padding: 14px; color: #36a9e1;">Availablity Chart</h3>
        <div class="PieChart">
            <canvas id="myChart" style="margin-top:-60px;max-width:600p;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5));height:120vh; width:80vw"></canvas>
        </div>
    </div>



    <div class="Data" style="width: 67%; height: 330px; background-color: white; float: right; margin-top: -330px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3)); margin-right: 10px;">

        <div style="margin-top:-12px; display: flex;justify-content: space-between; padding: 0px 20px" class="ip">
            <span style="font-size: 25px; color: #36a9e1; display: flex;">
            <h3 id="IpHostmame" >IP/HostName :   </h3>
            <h3 id="val1"></h3>
            </span>
            <span style="font-size: 25px; color: #36a9e1; display: flex;">
            <h3 id="DeviceType">Type :   </h3>
            <h3 id="val2"></h3>
            </span>
        </div>

        <div class="SentPacket" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-top: 40px; margin-left: 70px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5));
    ">
            <h3 style="text-align: center;padding: 14px; color: white;">Sent Packet</h3>
            <h3 id="data1" style="margin-top:-15px;  font-size:50px; text-align: center;padding: 14px; color: white;"></h3>
        </div>

        <div class="RecievedPacket" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-left: 270px; margin-top: -169px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5));
    ">
            <h3  style="text-align: center;padding: 14px; color: white;">Recieved Packet</h3>
            <h3 id="data2" style="margin-top:-35px;  font-size:50px; text-align: center;padding: 14px; color: white;"></h3>

        </div>

        <div class="PacketLoss" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-left: 470px; margin-top: -168px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5));
    ">
            <h3  style="text-align: center;padding: 14px; color: white;">PacketLoss</h3>
            <h3 id="data3" style="margin-top:-15px;  font-size:50px; text-align: center;padding: 14px; color: white;"></h3>

        </div>

        <div class="RTT" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-left: 670px; margin-top: -168px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5));
    ">
            <h3  style="text-align: center;padding: 14px; color: white;">RTT(ms)</h3>
            <h3 id="data4" style="margin-top:-15px;  font-size:50px; text-align: center;padding: 14px; color: white;"></h3>

        </div>

        <h3 style="font-size: 25px; text-align: center; margin-bottom: 10px; color: #36a9e1;">Live Data</h3>

    </div>

    <div class="Graph" style=" width: 98%; margin-left:1%; height: 380px; background-color:white; margin-top: 30px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));">
        <h3 style="font-size: 25px; text-align: center; padding: 13px; color: #36a9e1;;">Packet Recieved</h3>
        <canvas id="myChart1" style="margin-top:-20px; width: 100%; height: 45vh; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.6));"></canvas>
    </div>


    <div class="MemoryChart" style="margin-top:60px;margin-left:10px; width: 30%; height: 290px; background-color: white; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));">
        <h3 style="text-align: center; padding: 13px; color: #36a9e1;">Memory Chart</h3>
        <canvas id="memoryChart" style="margin-top:-50px;height:60vh;max-width:600px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.7));"></canvas>
    </div>
    <div class="CpuUsage" style="margin-left: 440px; margin-top: -310px; width: 30%; height: 290px; background-color: white; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3)); ">
        <h3 style="text-align: center; padding: 13px; color: #36a9e1;">CPU Usage</h3>
        <canvas id="cpuUsage" style="margin-top:-50px;height:60vh;max-width:600px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.7));"></canvas>
    </div>
    <div class="DiskUsage" style=" width: 30%; height: 290px; margin-left: 870px; margin-top: -310px; background-color: white; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));">
        <h3 style="text-align: center; padding: 13px; color: #36a9e1;">Disk Usage</h3>
        <canvas id="diskUsage" style=" margin-top:-50px;height:60vh;max-width:600px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.7));"></canvas>
    </div>
    <br><br>
</div>


<script>
    // Get the modal
    var modal = document.getElementById('id02');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>


<script>
    $(document).ready(function () {
        functions.buttonBind();
    });
</script>



</body>
</html>



