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
        console.log(data);

        table = $('#monitorTable').DataTable({lengthMenu: [5, 10, 20, 50, 100, 200, 500], destroy: true});

        monitor.adddata(data, table);
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
            callback : monitor.PollingCallback
        }

        ajax.ajaxRequest(request);
    },

    PollingCallback : function (data)
    {

        $(".nav").hide();
        $(".Dashboard").hide();
        $(".monitor").hide();
        $(".discovery").hide();
        $(".addDeviceButton").hide();
        $(".Content").hide();
        $(".PollingData").show();

        monitor.chartLoad(data);
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

        if(chart)
        {
            chart.destroy();
        }

        chart = new Chart("myChart1", {
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

        var table = $('#monitorTable').DataTable();

        var rows = table.rows().remove().draw();
    },

    deleteCallback : function (request)
    {
        if(request.json.status=="success")
        {
            monitor.load();

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

}