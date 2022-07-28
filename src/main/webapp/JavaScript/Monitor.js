var chart;

var monitor = {

    load : function ()
    {
        var request = {
            url : "monitor",
            type : "POST",
            callback : monitor.onload
        }

        ajax.ajaxRequest(request);
    },


    onload: function (data)
    {
        if(data.json.status=="unsuccess")
        {
            alerts.error("Monitor grid cannot load");
        }
        else
        {
            $("#content").html('<div class="monitor" style="background-color:white; width: 70%; margin-left: 15%; margin-top: 5%"> <h2 class="h1">Monitor Table</h2> <table id="monitorTable" style="width: 98%; background-color: #f1f1f1"> <thead> <tr> <th>IP/Host</th> <th>Type</th> <th>Status</th> <th>Action</th> </tr></thead> </table></div>');

            table = $('#monitorTable').DataTable({lengthMenu: [5, 10, 20, 50, 100, 200, 500], destroy: true});

            monitor.adddata(data, table);
        }
    },

    adddata: function (data)
    {
        $.each(data.json.monitorBeanList, function ()
        {
            table.row.add([this.ip, this.type, this.deviceStatus, "<button onclick='monitor.pollingRequest(this) 'data-id='" + this.id + "' data-ip='" + this.ip + "' data-type='" + this.type + "' class='monitorActionButton' style='width: 32%;'>Action</button><button onclick='monitor.deleteRequest(this)' data-id='" + this.id + "' data-type='" + this.type + "' class='deleteButton' style='width: 32%'>Delete</button>"]).draw();
        });
    },

    pollingRequest : function(that)
    {
        $("#content").html('<div class="PollingData"> <div class="Heading" style="display: flex;justify-content: space-between;"> <h2 style="font-family:Arial, Helvetica, sans-serif; font-size: 35px; margin: 5px; margin-left:10px; padding: 0%; color: black;">Device Status</h2> <span style="font-size:16px; position: absolute; right: 35px; top: 7px; font-size: 40px; font-weight: bold; color: black;" id="closePolling">&times;</span> </div><div class="Chart" style="width: 29%; height: 330px; background-color: white; margin-top: 20px; margin-left: 1%; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));"> <h3 style="font-size: 25px; text-align: center; padding: 14px; color: #36a9e1;">Availablity Chart</h3> <div class="PieChart"> <canvas id="myChart" style="margin-top:-60px;max-width:600p;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5));height:120vh; width:80vw"></canvas> </div></div><div class="Data" style="width: 67%; height: 330px; background-color: white; float: right; margin-top: -330px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3)); margin-right: 10px;"> <div style="margin-top:-12px; display: flex;justify-content: space-between; padding: 0px 20px" class="ip"> <span style="font-size: 25px; color: #36a9e1; display: flex;"> <h3 id="IpHostmame" >IP/HostName : </h3> <h3 id="val1"></h3> </span> <span style="font-size: 25px; color: #36a9e1; display: flex;"> <h3 id="DeviceType">Type : </h3> <h3 id="val2"></h3> </span> </div><div class="SentPacket" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-top: 40px; margin-left: 70px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5)); "> <h3 style="text-align: center;padding: 14px; color: white;">Sent Packet</h3> <h3 id="data1" style="margin-top:-15px; font-size:50px; text-align: center;padding: 14px; color: white;"></h3> </div><div class="RecievedPacket" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-left: 270px; margin-top: -169px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5)); "> <h3 style="text-align: center;padding: 14px; color: white;">Recieved Packet</h3> <h3 id="data2" style="margin-top:-35px; font-size:50px; text-align: center;padding: 14px; color: white;"></h3> </div><div class="PacketLoss" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-left: 470px; margin-top: -168px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5)); "> <h3 style="text-align: center;padding: 14px; color: white;">PacketLoss</h3> <h3 id="data3" style="margin-top:-15px; font-size:50px; text-align: center;padding: 14px; color: white;"></h3> </div><div class="RTT" style="background-color: #36a9e1; width: 150px; height: 150px; border-radius: 4px; margin-left: 670px; margin-top: -168px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.5)); "> <h3 style="text-align: center;padding: 14px; color: white;">RTT(ms)</h3> <h3 id="data4" style="margin-top:-15px; font-size:50px; text-align: center;padding: 14px; color: white;"></h3> </div><h3 style="font-size: 25px; text-align: center; margin-bottom: 10px; color: #36a9e1;">Live Data</h3> </div><div class="Graph" style=" width: 98%; margin-left:1%; height: 380px; background-color:white; margin-top: 30px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));"> <h3 style="font-size: 25px; text-align: center; padding: 13px; color: #36a9e1;;">Packet Recieved</h3> <canvas id="myChart1" style="margin-top:-20px; width: 100%; height: 45vh; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.6));"></canvas> </div><div class="MemoryChart" style="margin-top:60px;margin-left:10px; width: 30%; height: 290px; background-color: white; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));"> <h3 style="text-align: center; padding: 13px; color: #36a9e1;">Memory Chart</h3> <canvas id="memoryChart" style="margin-top:-50px;height:60vh;max-width:600px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.7));"></canvas> </div><div class="CpuUsage" style="margin-left: 440px; margin-top: -310px; width: 30%; height: 290px; background-color: white; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3)); "> <h3 style="text-align: center; padding: 13px; color: #36a9e1;">CPU Usage</h3> <canvas id="cpuUsage" style="margin-top:-50px;height:60vh;max-width:600px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.7));"></canvas> </div><div class="DiskUsage" style=" width: 30%; height: 290px; margin-left: 870px; margin-top: -310px; background-color: white; filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.3));"> <h3 style="text-align: center; padding: 13px; color: #36a9e1;">Disk Usage</h3> <canvas id="diskUsage" style=" margin-top:-50px;height:60vh;max-width:600px;filter: drop-shadow(5px 5px 5px rgba(0,0,0,0.7));"></canvas> </div><br><br></div>');

        var id=$(that).data("id");
        var ip=$(that).data("ip");
        var type = $(that).data("type");
        $("#val1").html(ip);
        $("#val2").html(type);

        var param = {
            id : id,
            ip : ip,
            type : type
        }

        var request = {
            url : "Polling",
            type : "POST",
            param : param,
            callback : monitor.PollingCallback
        }

        ajax.ajaxRequest(request);
    },

    PollingCallback : function (data)
    {
        $(".nav").hide();
        monitor.chartLoad(data);
    },

    chartLoad: function (data)
    {
        if(data.json.type=="SSH")
        {
            var yValues = data.json.sshStatistic.pieChart;

            $("#data1").html(data.json.sshStatistic.PingData[0]);
            $("#data2").html(data.json.sshStatistic.PingData[1]);
            $("#data3").html(data.json.sshStatistic.PingData[2]);
            $("#data4").html(data.json.sshStatistic.PingData[3]);

            $(".MemoryChart").show();
            $(".CpuUsage").show();
            $(".DiskUsage").show()

            param = {
                xValues : ["Free Memory(GB)", "Used Memory(GB)"],
                yValues : [data.json.sshStatistic.LiveData[5]-data.json.sshStatistic.LiveData[1], data.json.sshStatistic.LiveData[1]],
                barColors : ["#36a9e1","#b91d47"],
                chartId : "memoryChart",
                chartType : "doughnut",
                label : true
            }
            monitor.chart(param);



            param = {
                xValues : ["Idle CPU(%)", "Used CPU(%)"],
                yValues : [100-data.json.sshStatistic.LiveData[0],data.json.sshStatistic.LiveData[0] ],
                barColors : ["#36a9e1","#b91d47"],
                chartId : "cpuUsage",
                chartType : "doughnut",
                label : true
            }
            monitor.chart(param);



            param = {
                xValues : ["Used Disk Usage(%)", "Free Disk Usage(%)"],
                yValues : [data.json.sshStatistic.LiveData[2], 100-data.json.sshStatistic.LiveData[2]],
                barColors : [
                    "#36a9e1",
                    "#b91d47"
                ],
                chartId : "diskUsage",
                chartType : "doughnut",
                label : true
            }
            monitor.chart(param);

        }
        else
        {

            $("#data1").html(data.json.pingStatistic.LiveData[0]);
            $("#data2").html(data.json.pingStatistic.LiveData[1]);
            $("#data3").html(data.json.pingStatistic.LiveData[2]);
            $("#data4").html(data.json.pingStatistic.LiveData[3]);

            var yValues = data.json.pingStatistic.pieChart;


            $(".MemoryChart").hide();
            $(".CpuUsage").hide();
            $(".DiskUsage").hide()
        }


        var param = {
            barColors : ["#36a9e1", "#b91d47"],
            chartId : "myChart",
            chartType : "doughnut",
            xValues : ["Available", "Not Available"],
            yValues : yValues,
            label : true
        }
        monitor.chart(param);



        if(data.json.type=="SSH")
        {
            var yValues = data.json.sshStatistic.Values;
            var xValues = data.json.sshStatistic.TimeStamp;
        }
        else
        {
            var yValues = data.json.pingStatistic.Values;
            var xValues = data.json.pingStatistic.TimeStamp;
        }

        param = {
            barColors : ["#36a9e1","#36a9e1","#36a9e1","#36a9e1","#36a9e1","#36a9e1","#36a9e1", "#36a9e1","#36a9e1","#36a9e1","#36a9e1"],
            chartId : "myChart1",
            chartType : "bar",
            xValues : xValues,
            yValues : yValues,
            label : false
        }
        monitor.chart(param);

    },


    deleteRequest : function(that)
    {
        var id=$(that).data("id");
        var type=$(that).data("type");


        var param = {
            id : id,
            type : type
        }

        var request = {
            url: "deleteMonitor",
            type: "POST",
            param  : param,
            callback : monitor.deleteCallback
        }

        ajax.ajaxRequest(request);
    },

    deleteCallback : function (request)
    {
        if(request.json.status=="success")
        {
            monitor.load();
            alerts.success("Deletion Successfull");
        }
    },

    chart : function (param)
    {
        new Chart(param.chartId, {
            type: param.chartType,
            data: {
                labels: param.xValues,
                datasets: [{
                    backgroundColor: param.barColors,
                    data: param.yValues
                }]
            },
            options: {
                legend: {display: param.label},
                title: {
                    display: true,
                }
            }
        });

    }

}