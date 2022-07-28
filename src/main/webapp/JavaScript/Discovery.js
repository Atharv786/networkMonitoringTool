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

        if(data.json.status=="unsuccess")
        {
            alerts.error("Discovery grid cannot loaded");
        }
        else
        {
            $("#content").html('<button class="addDeviceButton">Add Device</button><div class="discovery" style="background-color:white; width: 70%; margin-left: 15%; margin-top: 5%"> <h2 class="h1">Discovery Table</h2> <table id="discoveryTable" style="width: 98%; background-color: #f1f1f1"> <thead> <tr> <th>Name</th> <th>IP/Host</th> <th>Type</th> <th>Operation</th> </tr></thead> </table></div><div id="id01" class="modal"> <span class="close" title="Close Modal">&times;</span> <form class="modal-content"> <div class="container"> <h1>Add Device</h1> <hr> <label for="addDeviceName"><b>Device Name</b></label> <input type="text" placeholder="Enter Name" name="name" id="addDeviceName" ><div id="invalidName"></div> <label for="addDeviceIP"><b>IP/Host</b></label> <input type="text" placeholder="Enter IP/Host" name="ip" id="addDeviceIP" > <div id="ipValidate"></div><label for="addDeviceType"><b>Device Type</b></label> <select name="type" id="addDeviceType"> <option id="Ping" value="Ping">Ping</option> <option id="SSH" value="SSH">SSH</option> </select> <label for="addDeviceUsername" id="addDeviceUsernameLabel" style="display: none"><b>Username</b></label> <input type="text" placeholder="Enter Username" name="username" id="addDeviceUsername" style="display: none" ><div id="invalidUsername"></div> <label for="addDevicePassword" id="addDevicePasswordLabel" style="display: none"><b>Password</b></label> <input type="password" placeholder="Enter Password" name="password" id="addDevicePassword" style="display: none" > <div id="invalidPassword"></div><div class="clearfix"> <input type="reset" id="reset" name="reset" class="reset"></input> <input type="button" class="addDevice" value="Add Device" id="addDevice" name="addDevice"></input> </div></></form></div><div id="id02" class="modal2" style="display: none"> <span onclick="document.getElementById("id02").style.display="none"" class="close2" title="Close Modal">&times;</span> <form class="modal-content2"> <div class="container2"> <h1>Update Device</h1> <hr> <input type="text" name="type" id="type" style="display: none" > <input type="text" name="ip" id="ip" style="display: none"> <input type="text" name="id" id="id" style="display: none" > <label for="addDeviceName2"><b>Device Name</b></label> <input type="text" placeholder="Enter Name" name="name" id="addDeviceName2" required/> <h4 id="invalidName"></h4> <label for="addDeviceIP2"><b>IP/Host</b></label> <input type="text" placeholder="Enter IP/Host" name="newIp" id="addDeviceIP2" required/> <h4 id="invalidIp"></h4> <label for="addDeviceType2"><b>Device Type</b></label> <select name="type" id="addDeviceType2"> <option id="Ping2" value="Ping">Ping</option> <option id="SSH2" value="SSH">SSH</option> </select> <div class="clearfix2"> <input type="button" class="update" value="Update" id="update" name="update"></input> </div></div></form></div> ');

            socket.createSocket();

            table = $('#discoveryTable').DataTable({lengthMenu: [5, 10, 20, 50, 100, 200, 500], destroy: true});

            discovery.adddata(data, table);
        }

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

        var param = {
            id : id,
        }

        var request = {
            url: "provision",
            type: "POST",
            param  : param,
            callback : discovery.provisionCallback
        }

        ajax.ajaxRequest(request);
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
    },

    deleteCallback : function (request)
    {

        if(request.json.status=="success")
        {
            discovery.load();

            alerts.success("Deletion successfull", "Success");

        }
    },


    updateForm : function (that)
    {
        $(".modal2").show();

        $("#id").val($(that).data("id"));
        $("#ip").val($(that).data("ip"));
        $("#addDeviceName2").val($(that).data("name"));
        $("#addDeviceIP2").val($(that).data("ip"));
        $("#addDeviceType2").val($(that).data("type"));

        $("#invalidIp").html("");
        $("#invalidName").html("");

        $("#Ping2").hide();
        $("#SSH2").hide();

    },

    updateRequest : function ()
    {
        var param = $('.modal-content2').serializeArray().reduce(function (finalParam, currentValue)
        {
            finalParam[currentValue.name] = currentValue.value;
            return finalParam;
        }, {});

        param.ip = param.ip.trim();
        param.newIp = param.newIp.trim();


        if(validator.ip6Validator(param.newIp))
        {
            $("#invalidIp").html("<h4 style='color: red; font-size: 15px; margin-top: -30px'>IPv6 not allowed</h4>")
        }
        else
        {
            if(param.newIp=="")
            {
                $("#invalidIp").html("<h4 style='color: red; font-size: 15px; margin-top: -30px'>IPv4 is mandatory</h4>")
            }
            else if(validator.ipValidator(param.newIp))
            {
                var request = {
                    url: "update",
                    param: param,
                    type: "POST",
                    callback: discovery.updateCallback
                }

                ajax.ajaxRequest(request);
            }
            else
            {
                $("#invalidIp").html("<h4 style='color: red; font-size: 15px; margin-top: -30px'>Entered IPv4 is not valid</h4>")
            }
        }

    },

    updateCallback: function (request)
    {
        discovery.load();

        if(request.json.status=="success")
        {
            alerts.success("Update successfull", "Success");
        }
        else if(request.json.status=="unsuccess")
        {
            alerts.error("Update unsuccessfull", "Error");
        }
        else
        {
            alerts.warning("Device is already present");
        }

    },

    addDeviceRequest : function ()
    {
        var param = $('.modal-content').serializeArray().reduce(function (finalParam, currentValue)
       {
           finalParam[currentValue.name] = currentValue.value;
           return finalParam;
       }, {});


       param.ip = param.ip.trim();
       param.username = param.username.trim();
       param.password = param.password.trim();


       if(param.type=="SSH")
       {
           if(validator.ip(param.ip) && validator.name(param.name) && validator.username(param.username) && validator.password(param.password))
           {
               var request = {
                   url: "addDevice",
                   param: param,
                   type: "POST",
                   callback: discovery.addDeviceCallback
               }

               ajax.ajaxRequest(request);
           }
       }
       else
       {
           if(validator.ip(param.ip))
           {
               var request = {
                   url: "addDevice",
                   param: param,
                   type: "POST",
                   callback: discovery.addDeviceCallback
               }

               ajax.ajaxRequest(request);
           }
       }


       /*if(validator.ip6Validator(param.ip))
       {
           $("#ipValidate").html("<h4 style='color: red; font-size: 15px; margin-top: -14px'>IPv6 not allowed</h4>")
       }
       else
       {
           if(param.ip=="")
           {
               $("#ipValidate").html("<h4 style='color: red; font-size: 15px; margin-top: -14px'>IPv4 is mandatory</h4>")
           }
           else if(validator.ipValidator(param.ip))
           {
               $("#ipValidate").html("");

               var request = {
                   url: "addDevice",
                   param: param,
                   type: "POST",
                   callback: discovery.addDeviceCallback
               }

               ajax.ajaxRequest(request);
           }
           else
           {
               $("#ipValidate").html("<h4 style='color: red; font-size: 15px; margin-top: -14px'>Entered IPv4 is not valid</h4>")
           }
       }*/

    },

    addDeviceCallback: function (request){

        discovery.load();

        if(request.json.status=="DeviceAlreadyPresent")
        {
           alerts.warning("Device is laready present", "Warning");
        }
        else if(request.json.status=="success")
        {
            alerts.success("Monitor added Successfully", "Success");
        }
        else if(request.json.status=="unsuccess")
        {
            alerts.error("Device not added", "Error");
        }
    },
}