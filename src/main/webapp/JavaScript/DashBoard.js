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

        const data1 = document.getElementById('Updata');

        data1.innerHTML = data.json.availability[0]

        const data2 = document.getElementById('Downdata');

        data2.innerHTML = data.json.availability[1]

        const data3 = document.getElementById('Unknowndata');

        data3.innerHTML = data.json.availability[2]

        const data4 = document.getElementById('Totaldata');

        data4.innerHTML = data.json.availability[3]


        var tdata="";

        $.each(data.json.topRtt, function (){

            tdata+="<tr>"+
                "<td>"+this.deviceIP+"</td>"+
                "<td>"+this.rtt+"</td>"+
                "</tr>";
        });


        const topRtt = document.getElementById('tbodytoprtt');

        topRtt.innerHTML = tdata

        tdata="";

        $.each(data.json.monitorGroup, function (){

            tdata+="<tr>"+
                "<td>"+this.deviceType+"</td>"+
                "<td>"+this.UP+"</td>"+
                "<td>"+this.Down+"</td>"+
                "<td>"+this.Total+"</td>"+
                "</tr>";
        });


        const monitorGroup = document.getElementById('tbodymonitorgroup');

        monitorGroup.innerHTML = tdata

        tdata="";

        $.each(data.json.topCpu, function (){

            tdata+="<tr>"+
                "<td>"+this.deviceIP+"</td>"+
                "<td>"+this.cpu+"%</td>"+
                "</tr>";
        });

        const topCpu = document.getElementById('tbodytopcpu');

        topCpu.innerHTML = tdata

        tdata="";

        $.each(data.json.topMemory, function (){

            tdata+="<tr>"+
                "<td>"+this.deviceIP+"</td>"+
                "<td>"+this.Memory+"</td>"+
                "</tr>";
        });

        const topMemory = document.getElementById('tbodytopmemory');

        topMemory.innerHTML = tdata

        tdata="";

        $.each(data.json.topDisk, function (){

            tdata+="<tr>"+
                "<td>"+this.deviceIP+"</td>"+
                "<td>"+this.Disk+"%</td>"+
                "</tr>";
        });

        const topDisk = document.getElementById('tbodytopdisk');

        topDisk.innerHTML = tdata

    }

}