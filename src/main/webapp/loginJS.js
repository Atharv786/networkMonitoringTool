var functions = {


    loginAjax: function (request)
    {

        $.ajax({

           url: request.url,
           type: request.type,
           data: request.param,

            success: function (data) {

                var callbacks;
                if (request.callback != undefined)
                {
                    callbacks = $.Callbacks();

                    callbacks.add(request.callback);

                    request.json = data;

                    callbacks.fire(request);

                    callbacks.remove(request.callback);
                }
            },

            error: function (data) {
               alert("Invalid Credentials");
            }

        });
    },


    buttonBind: function ()
    {
            $("#submit").click(function()
            {
                 var username= $("#username").val();
                 var password= $("#password").val();

                 var param ={
                     username : username,
                     password : password
                 }

                 var request = {

                     url : "login",
                     type : "POST",
                     param : param,
                     callback : functions.onSuccessButtonBind
                 }
                 functions.loginAjax(request);
            });


            $("#addDevice").click(function(){

                $(".modal").hide();
                if($("#addDeviceType").val()=="SSH")
                {
                    var addDeviceName = $("#addDeviceName").val();
                    var addDeviceIP = $("#addDeviceIP").val();
                    var addDeviceType = $("#addDeviceType").val();
                    var addDeviceUsername = $("#addDeviceUsername").val();
                    var addDevicePassword = $("#addDevicePassword").val();

                    var param = {

                        addDeviceName: addDeviceName,
                        addDeviceType: addDeviceType,
                        addDeviceIP: addDeviceIP,
                        addDeviceUsername: addDeviceUsername,
                        addDevicePassword: addDevicePassword
                    }
                }

                if($("#addDeviceType").val()=="Ping")
                {
                    var addDeviceName = $("#addDeviceName").val();
                    var addDeviceIP = $("#addDeviceIP").val();
                    var addDeviceType = $("#addDeviceType").val();

                    var param = {

                        addDeviceName: addDeviceName,
                        addDeviceType: addDeviceType,
                        addDeviceIP: addDeviceIP,
                    }
                }

                var request = {
                    url: "addDeviceUrl",
                    param: param,
                    type: "POST",
                    callback: functions.onSuccessInsertion

                }

                functions.loginAjax(request);

            });


            $("#update").click(function(){

                $(".modal2").hide();

                if($("#addDeviceType2").val()=="SSH")
                {
                    var addDeviceName2 = $("#addDeviceName2").val();
                    var addDeviceIP2 = $("#addDeviceIP2").val();
                    var addDeviceType2 = $("#addDeviceType2").val();
                    var addDeviceUsername2 = $("#addDeviceUsername2").val();
                    var addDevicePassword2 = $("#addDevicePassword2").val();
                    var id=$("#id").val();
                    var ip=$("#ip").val();



                    var param = {

                        addDeviceName2: addDeviceName2,
                        addDeviceType2: addDeviceType2,
                        addDeviceIP2: addDeviceIP2,
                        addDeviceUsername2: addDeviceUsername2,
                        addDevicePassword2: addDevicePassword2,
                        id: id,
                        ip: ip
                    }
                }

                if($("#addDeviceType2").val()=="Ping")
                {
                    var addDeviceName2 = $("#addDeviceName2").val();
                    var addDeviceIP2 = $("#addDeviceIP2").val();
                    var addDeviceType2 = $("#addDeviceType2").val();
                    var id=$("#id").val();
                    var ip=$("#ip").val();



                    var param = {

                        addDeviceName2: addDeviceName2,
                        addDeviceType2: addDeviceType2,
                        addDeviceIP2: addDeviceIP2,
                        id: id,
                        ip: ip
                    }
            }

            var request = {
                url: "updateDevice",
                param: param,
                type: "POST",
                callback: functions.onSuccessUpdate

            }

            functions.loginAjax(request);

        });

    },


    onSuccessUpdate: function (request) {

        if(request.json.status=="success")
        {
            var table = $('#discoveryTable').DataTable();
            var rows = table.rows().remove().draw();
            functions.reload();null
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"]("Update successfull", "Success");
        }

        if(request.jdeletionSuccessfullson.status=="UpdateUnsuccessfull")
        {
            var table = $('#discoveryTable').DataTable();
            var rows = table.rows().remove().draw();
            functions.reload();
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["error"]("Update unsuccessfull", "Error");
        }

        if(request.json.status=="fieldsRequired")
        {
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["warning"]("All fields are required", "Warning");
        }

    },

    edit: function (that) {
        $(".modal2").show();

        var type=$(that).data("type");
        var id=$(that).data("id");
        var ip=$(that).data("ip");
        var name=$(that).data("name");

        $("#id").val(id);
        $("#ip").val(ip);

        if(type=="Ping")
        {

            $("#addDeviceName2").val(name);
            $("#addDeviceIP2").val(ip);
            $("#addDeviceType2").val("Ping");
            $("#addDeviceUsernameLabel2").hide();
            $("#addDeviceUsername2").hide();
            $("#addDevicePasswordLabel2").hide();
            $("#addDevicePassword2").hide();
            $("#SSH2,#Ping2").hide();
        }
        else
        {
            $("#addDeviceName2").val(name);
            $("#addDeviceIP2").val(ip);
            $("#addDeviceType2").val("SSH");
            $("#addDeviceUsernameLabel2").show();
            $("#addDeviceUsername2").show();
            $("#addDevicePasswordLabel2").show();
            $("#addDevicePassword2").show();
            $("#Ping2, #SSH2").hide();
        }

    },

    deleteDiscovery: function(that)
    {
        var id=$(that).data("id");

        var param = {

            id : id,
        }

        var request = {
            url: "deleteDiscovery",
            type: "POST",
            param  : param,
            callback : functions.delete
        }

        functions.loginAjax(request);

        var table = $('#discoveryTable').DataTable();

        var rows = table.rows().remove().draw();
    },

    deleteMonitor: function(that)
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
            callback : functions.deleteMonitor1
        }

        functions.loginAjax(request);

        var table = $('#monitorTable').DataTable();
        var rows = table.rows().remove().draw();
    },


    actionMonitor : function(that)
    {
        var id=$(that).data("id");

        var ip=$(that).data("ip");

        var type = $(that).data("type");

        const val1 = document.getElementById('val1');

        const val2 = document.getElementById('val2');

        val1.textContent = ip;

        val2.textContent = type;

        var param = {
            id : id,
            ip : ip,
            type : type
        }

        var request = {
            url : "Polling",
            type : "POST",
            param : param,
            callback : functions.Polling
        }

        functions.loginAjax(request);
    },


    addMonitor: function (that)
    {
        var id=$(that).data("id");
        var ip=$(that).data("ip");
        var name=$(that).data("name");
        var type=$(that).data("type");

        var param = {
            id : id,
            ip : ip,
            name : name,
            type: type
        }

        var request = {
            url: "addMonitor",
            type: "POST",
            param  : param,
            callback : functions.provision
        }

        functions.loginAjax(request);

        var table = $('#monitorTable').DataTable();
        var rows = table.rows().remove().draw();
    },


    Polling: function (data)
    {

        $(".nav").hide();
        $(".Dashboard").hide();
        $(".monitor").hide();
        $(".discovery").hide();
        $(".addDeviceButton").hide();
        $(".Content").hide();
        $(".PollingData").show();

        functions.chartLoad(data);
    },


    chartLoad: function (data)
    {
        var xValues = ["Available", "Not Available"];

        if(data.json.type=="SSH")
        {
            console.log(data.json.sshStatistic);

            var yValues = data.json.sshStatistic.pieChart;

            const data1 = document.getElementById('data1');

            data1.innerHTML = data.json.sshStatistic.PingData[0]

            const data2 = document.getElementById('data2');

            data2.innerHTML = data.json.sshStatistic.PingData[1]

            const data3 = document.getElementById('data3');

            data3.innerHTML = data.json.sshStatistic.PingData[2]

            const data4 = document.getElementById('data4');

            data4.innerHTML = data.json.sshStatistic.PingData[3]

            $(".MemoryChart").show();
            $(".CpuUsage").show();
            $(".DiskUsage").show()

        }
        else
        {
            console.log(data.json.pingStatistic);
            var yValues = data.json.pingStatistic.pieChart;


            const data1 = document.getElementById('data1');

            data1.innerHTML = data.json.pingStatistic.LiveData[0];

            const data2 = document.getElementById('data2');

            data2.innerHTML = data.json.pingStatistic.LiveData[1]

            const data3 = document.getElementById('data3');

            data3.innerHTML = data.json.pingStatistic.LiveData[2]

            const data4 = document.getElementById('data4');

            data4.innerHTML = data.json.pingStatistic.LiveData[3]


            $(".MemoryChart").hide();
            $(".CpuUsage").hide();
            $(".DiskUsage").hide()
        }

        var barColors = [
            "#36a9e1",
            "#b91d47"
        ];

        new Chart("myChart", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,
                }
            }
        });


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

        var barColors = ["#36a9e1","#36a9e1","#36a9e1","#36a9e1","#36a9e1","#36a9e1","#36a9e1", "#36a9e1","#36a9e1","#36a9e1","#36a9e1"];

        new Chart("myChart1", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                legend: {display: false},
                title: {
                    display: true,
                }
            }
        });


        var xValues = ["Free Memory(GB)", "Used Memory(GB)"];

        var yValues =[data.json.sshStatistic.LiveData[5]-data.json.sshStatistic.LiveData[1], data.json.sshStatistic.LiveData[1]];

        var barColors = [
            "#36a9e1",
            "#b91d47"
        ];

        new Chart("memoryChart", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,        }
            }
        });


        var xValues = ["Idle CPU(%)", "Used CPU(%)"];
        var yValues = [100-data.json.sshStatistic.LiveData[0],data.json.sshStatistic.LiveData[0] ];
        var barColors = [
            "#36a9e1",
            "#b91d47"
        ];

        new Chart("cpuUsage", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options: {
                title: {
                    display: true,        }
            }
        });



        var xValues = ["Used Disk Usage(%)", "Free Disk Usage(%)"];
        var yValues = [data.json.sshStatistic.LiveData[2], 100-data.json.sshStatistic.LiveData[2]];
        var barColors = [
            "#36a9e1",
            "#b91d47"
        ];

        new Chart("diskUsage", {
            type: "doughnut",
            data: {
                labels: xValues,
                datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
            },
            options:
                {
                    title:
                        {
                            display: true,
                        }
                }
        });
    },


    provision: function (request)
    {

        alert(request.json.status);

        if(request.json.status=="provisionSuccessfull")
        {
            functions.reloadMonitor();

            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"]("Monitor added succesfully", "Success");
        }
        else
        {
            functions.reloadMonitor();

            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-bottom-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["error"]("Monitor cannot added", "Error");
        }
    },

    delete: function (request)
    {
        if(request.json.status=="deletionSuccessfull")
        {
            functions.reload();
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"]("Deletion successfull", "Success");

        }
    },


    deleteMonitor1: function (request)
    {
        if(request.json.status=="deletionSuccessfull")
        {
            functions.reloadMonitor();
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"]("Deletion successfull", "Success");

        }
    },

    reload: function ()
    {
        var request = {

            url : "discovery",
            type : "POST",
            callback : functions.onload
        }
        functions.loginAjax(request);
    },

    reloadMonitor: function ()
    {
        var request = {

            url : "monitor",
            type : "POST",
            callback : functions.onloadMonitor
        }
        functions.loginAjax(request);
    },

    onload: function (data)
    {
        table = $('#discoveryTable').DataTable({lengthMenu: [5, 10, 20, 50, 100, 200, 500], destroy: true});

        functions.adddata(data, table);
    },

    adddata: function (data)
    {
        $.each(data.json.discoveryBeanList, function ()
        {
            table.row.add([this.name, this.ip, this.type, "<button onclick='functions.addMonitor(this) 'data-id='" + this.id + "' data-type='" + this.type + "' data-ip='" + this.ip + " 'data-name='" + this.name + "' class='provisionButton' style='width: 32%;'>Provision</button><button onclick='functions.edit(this)' data-id='" + this.id + "' data-type='" + this.type + "' data-ip='" + this.ip + " 'data-name='" + this.name + "' class='updateButton' style='width: 32%'>Update</button><button onclick='functions.deleteDiscovery(this)' data-id='" + this.id + "' class='deleteButton' style='width: 32%'>Delete</button>"]).draw();
        });
    },

    onloadMonitor: function (data)
    {
        table = $('#monitorTable').DataTable({lengthMenu: [5, 10, 20, 50, 100, 200, 500], destroy: true});

        functions.adddataMonitor(data, table);
    },

    adddataMonitor: function (data)
    {
        $.each(data.json.discoveryBeanList, function ()
        {
            table.row.add([this.ip, this.type, this.deviceStatus, "<button onclick='functions.actionMonitor(this) 'data-id='" + this.id + "' data-ip='" + this.ip + "' data-type='" + this.type + "' class='monitorActionButton' style='width: 32%;'>Action</button><button onclick='functions.deleteMonitor(this)' data-id='" + this.id + "' data-type='" + this.type + "' class='deleteButton' style='width: 32%'>Delete</button>"]).draw();
        });
    },


    onloadDashboard: function (data)
    {

        $("#Updata").append(data.json.availability[0]);

        $("#Downdata").append(data.json.availability[1]);

        $("#Unknowndata").append(data.json.availability[2]);

        $("#Totaldata").append(data.json.availability[3]);


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

        console.log(data);
    },

    onSuccessButtonBind: function (request) {

        if(request.json.status=="success")
        {
            location.href="homePage.jsp";
        }
        else if(request.json.status=="loginUnsuccessful")
        {
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-bottom-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["error"]("Username or Password is wrong", "Invalid Credentials");
        }
    },


    onSignOutButtonBind: function (request){
            alert("Yor are SignOut");
            location.href="index.jsp";

    },

    onSuccessInsertion: function (request){

        var table = $('#discoveryTable').DataTable();
        var rows = table.rows().remove().draw();
        functions.reload();

        if(request.json.status=="fieldsRequired")
        {
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["warning"]("All fields are required", "Warning");
        }
        else if(request.json.status=="success")
        {
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["success"]("Monitor added Successfully", "Success");
        }
        else if(request.json.status=="InsertionUnsuccessfull")
        {
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            toastr["error"]("Device not added", "Error");
        }
    }

}


$(document).ready(function(){
    $(".showPassword").click(function()
    {
        var x = $("#password");
        if (x.attr("type") === "password") {
            x.attr("type","text");
        }
        else {
            x.attr("type","password");
        }

    });

    $("#addDeviceType").click(function ()
    {

        if($("#addDeviceType").val()=="SSH")
        {
            $("#addDeviceUsernameLabel").show();
            $("#addDeviceUsername").show();
            $("#addDevicePasswordLabel").show();
            $("#addDevicePassword").show();
        }
        else
        {
            $("#addDeviceUsernameLabel").hide();
            $("#addDeviceUsername").hide();
            $("#addDevicePasswordLabel").hide();
            $("#addDevicePassword").hide();
        }

    }),

    $("#reset").click(function ()
    {
        $("#addDeviceUsernameLabel").hide();
        $("#addDeviceUsername").hide();
        $("#addDevicePasswordLabel").hide();
        $("#addDevicePassword").hide();
    })

});

var request = {

    url : "discovery",
    type : "POST",
    callback : functions.onload
}
functions.loginAjax(request);

var request = {

    url : "monitor",
    type : "POST",
    callback : functions.onloadMonitor
}
functions.loginAjax(request);

var request = {

    url : "dashboard",
    type : "POST",
    callback : functions.onloadDashboard
}
functions.loginAjax(request);

