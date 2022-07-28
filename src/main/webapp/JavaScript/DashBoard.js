var dashboard = {

    load : function ()
    {
        var request = {

            url : "dashboard",
            type : "POST",
            callback : dashboard.onloadDashboard
        }

        ajax.ajaxRequest(request);

    },

    onloadDashboard: function (data)
    {
        if(data.json.status=="unsuccess")
        {
            alerts.error("Dashboard cannot loaded");
        }
        else
        {
            $("#content").html('<div class="Dashboard"> <div class="MonitorAvailability"> <h2 style="text-align: center; padding: 14px; margin-bottom: 50px;">Monitor Availability</h2> <div class="Up"> <h3 style="text-align: center;">UP :</h3> <h3 id="Updata" style="text-align: center;"></h3> </div><div class="Down"> <h3 style="text-align: center;">DOWN :</h3> <h3 id="Downdata" style="text-align: center;"></h3> </div><div class="Unknown"> <h3 style="text-align: center;">UNKNOWN :</h3> <h3 id="Unknowndata" style="text-align: center;"></h3> </div><div class="Total"> <h3 style="text-align: center;">TOTAL :</h3> <h3 id="Totaldata" style="text-align: center;"></h3> </div></div><div class="Top5Monitor"> <h3 style="text-align: center;">Top 5 Monitors By Response Time (ms)</h3> <table id="tbodytoprtt" style="width: 68%; background-color: #f1f1f1"> <thead> <tr> <th>Device IP</th> <th>RTT(ms)</th> </tr></thead> </table> </div><div class="MonitorGroup"> <h3 style="text-align: center;">Monitors Group</h3> <table id="tbodymonitorgroup" style="width: 68%; background-color: #f1f1f1"> <thead> <tr> <th>Device Type</th> <th>Up</th> <th>Down</th> <th>Total</th> </tr></thead> </table> </div><div class="Top5Cpu"> <h3 style="text-align: center;">Top 5 Monitors By CPU (%)</h3> <table id="tbodytopcpu" style="width: 68%; background-color: #f1f1f1"> <thead> <tr> <th>Device IP</th> <th>CPU</th> </tr></thead> </table> </div><div class="Top5Memory"> <h3 style="text-align: center;">Top 5 Monitors By Memory (mb)</h3> <table id="tbodytopmemory" style="width: 68%; background-color: #f1f1f1"> <thead> <tr> <th>Device IP</th> <th>Memory</th> </tr></thead> </table> </div><div class="Top5Disk"> <h3 style="text-align: center;">Top 5 Monitors By Disk (%)</h3> <table id="tbodytopdisk" style="width: 68%; background-color: #f1f1f1"> <thead> <tr> <th>Device IP</th> <th>Disk</th> </tr></thead> </table> </div></div>');

            $("#Updata").html(data.json.availability[0]);

            $("#Downdata").html(data.json.availability[1]);

            $("#Unknowndata").html(data.json.availability[2]);

            $("#Totaldata").html(data.json.availability[3]);


            var tdata="";

            $.each(data.json.topRtt, function (){

                tdata+="<tr>"+
                    "<td>"+this.deviceIP+"</td>"+
                    "<td>"+this.rtt+"</td>"+
                    "</tr>";
            });

            $("#tbodytoprtt").append(tdata);


            tdata="";

            $.each(data.json.monitorGroup, function (){

                tdata+="<tr>"+
                    "<td>"+this.deviceType+"</td>"+
                    "<td>"+this.UP+"</td>"+
                    "<td>"+this.Down+"</td>"+
                    "<td>"+this.Total+"</td>"+
                    "</tr>";
            });


            $("#tbodymonitorgroup").append(tdata);



            tdata="";

            $.each(data.json.topCpu, function (){

                tdata+="<tr>"+
                    "<td>"+this.deviceIP+"</td>"+
                    "<td>"+this.cpu+"%</td>"+
                    "</tr>";
            });

            $("#tbodytopcpu").append(tdata);


            tdata="";

            $.each(data.json.topMemory, function (){

                tdata+="<tr>"+
                    "<td>"+this.deviceIP+"</td>"+
                    "<td>"+this.Memory+"</td>"+
                    "</tr>";
            });

            $("#tbodytopmemory").append(tdata);


            tdata="";

            $.each(data.json.topDisk, function (){

                tdata+="<tr>"+
                    "<td>"+this.deviceIP+"</td>"+
                    "<td>"+this.Disk+"%</td>"+
                    "</tr>";
            });

            $("#tbodytopdisk").append(tdata);
        }

    }

}