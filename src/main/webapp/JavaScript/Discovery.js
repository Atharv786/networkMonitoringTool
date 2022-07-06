var discovery = {

    load : function ()
    {
        var request = {

            url : "discovery",
            type : "POST",
            callback : discovery.onload
        }

        ajax.ajaxRequest(request);
    },


    onload: function (data)
    {
        console.log("data"+data.json.status);

        table = $('#discoveryTable').DataTable({lengthMenu: [5, 10, 20, 50, 100, 200, 500], destroy: true});

        discovery.adddata(data, table);
    },

    adddata: function (data)
    {
        $.each(data.json.discoveryBeanList, function ()
        {
            table.row.add([this.name, this.ip, this.type, "<button onclick='discovery.provisionRequest(this) 'data-id='" + this.id + "' data-type='" + this.type + "' data-ip='" + this.ip + " 'data-name='" + this.name + "' class='provisionButton' style='width: 32%;'>Provision</button><button onclick='discovery.updateForm(this)' data-id='" + this.id + "' data-type='" + this.type + "' data-ip='" + this.ip + " 'data-name='" + this.name + "' class='updateButton' style='width: 32%'>Update</button><button onclick='discovery.deleteRequest(this)' data-id='" + this.id + "' class='deleteButton' style='width: 32%'>Delete</button>"]).draw();
        });
    },

    provisionRequest: function (that)
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
            url: "provision",
            type: "POST",
            param  : param,
            callback : discovery.provisionCallback
        }

        ajax.ajaxRequest(request);

        var table = $('#monitorTable').DataTable();

        var rows = table.rows().remove().draw();
    },


    provisionCallback: function (request)
    {

        if(request.json.status=="success")
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
            toastr["success"]("Monitor added succesfully", "Success");
        }
        else
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
            toastr["error"]("Monitor cannot added", "Error");
        }
    },


    deleteRequest: function(that)
    {
        var id=$(that).data("id");

        var param = {

            id : id,
        }

        var request = {
            url: "delete",
            type: "POST",
            param  : param,
            callback : discovery.deleteCallback
        }

        ajax.ajaxRequest(request);

        var table = $('#discoveryTable').DataTable();

        var rows = table.rows().remove().draw();
    },

    deleteCallback : function (request)
    {
        if(request.json.status=="success")
        {
            discovery.load();

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


    updateForm : function (that)
    {
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

    updateRequest : function ()
    {

        if($("#addDeviceType2").val()=="SSH")
        {
            var name = $("#addDeviceName2").val();
            var newIp = $("#addDeviceIP2").val();
            var type = $("#addDeviceType2").val();
            var username = $("#addDeviceUsername2").val();
            var password = $("#addDevicePassword2").val();

            var id=$("#id").val();
            var ip=$("#ip").val();



            var param = {

                name : name,
                type : type,
                newIp : newIp,
                username : username,
                password : password,
                id: id,
                ip: ip
            }
        }

        if($("#addDeviceType2").val()=="Ping")
        {
            var name = $("#addDeviceName2").val();
            var newIp = $("#addDeviceIP2").val();
            var type = $("#addDeviceType2").val();
            var id = $("#id").val();
            var ip = $("#ip").val();



            var param = {

                name : name,
                type : type,
                newIp : newIp,
                id: id,
                ip: ip
            }
        }

        var request = {
            url: "update",
            param: param,
            type: "POST",
            callback: discovery.updateCallback

        }

        ajax.ajaxRequest(request);

    },

    updateCallback: function (request)
    {

        if(request.json.status=="success")
        {
            var table = $('#discoveryTable').DataTable();

            var rows = table.rows().remove().draw();

            discovery.load();

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

        if(request.jdeletionSuccessfullson.status=="unsuccess")
        {
            var table = $('#discoveryTable').DataTable();

            var rows = table.rows().remove().draw();

            discovery.reload();

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

    addDeviceRequest : function ()
    {
        if($("#addDeviceType").val()=="SSH")
        {
            var name = $("#addDeviceName").val();
            var ip = $("#addDeviceIP").val();
            var type = $("#addDeviceType").val();
            var username = $("#addDeviceUsername").val();
            var password = $("#addDevicePassword").val();

            var param = {

                name : name,
                type : type,
                ip : ip,
                username : username,
                password : password
            }
        }

        if($("#addDeviceType").val()=="Ping")
        {
            var name = $("#addDeviceName").val();
            var ip = $("#addDeviceIP").val();
            var type = $("#addDeviceType").val();

            var param = {

                name : name,
                type : type,
                ip : ip,
            }
        }

        var request = {
            url: "addDevice",
            param: param,
            type: "POST",
            callback: discovery.addDeviceCallback

        }

        ajax.ajaxRequest(request);

    },

    addDeviceCallback: function (request){

        var table = $('#discoveryTable').DataTable();

        var rows = table.rows().remove().draw();

        discovery.load();

        if(request.json.status=="invalid")
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
        else if(request.json.status=="unsuccess")
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
    },
}