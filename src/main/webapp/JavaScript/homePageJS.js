/**
 * Created by atharv on 14/4/22.
 */

$(document).ready(function(){

    discovery.load();

    $(".DiscoveryTab").click(function(){

        discovery.load();
    });

    $(".DashBoardTab").click(function(){

        dashboard.load();

    });

    $(".MonitorTab").click(function()
    {
        monitor.load();

    });

    $("#SignOut").click(function()
    {
        var request = {
            url : "logout",
            callback: functions.onSignOutButtonBind
        }

        ajax.ajaxRequest(request);

    });


    $("#content").on("click",".addDeviceButton",function () {
        $(".modal").show();
        $("#ipValidate").html("");
        $("#invalidName").html("");
        $("#invalidUsername").html("");
        $("#invalidPassword").html("");
    });

    $("#content").on("click",".close",function () {
        $(".modal").hide();
    });

    $("#content").on("click",".close2",function () {
        $(".modal2").hide();
    });

    $("#content").on("click","#addDeviceType",function ()
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

    });

    $("#content").on("click","#reset",function ()
    {
        $("#addDeviceUsernameLabel").hide();
        $("#addDeviceUsername").hide();
        $("#addDevicePasswordLabel").hide();
        $("#addDevicePassword").hide();
    });


    $("#content").on("click","#addDevice",function(){

        discovery.addDeviceRequest();

    });

    $("#content").on("click","#update",function(){

        discovery.updateRequest();
    });

    $("#content").on("click","#closePolling",function()
    {
        $(".nav").show();

        monitor.load();
    });

    $("#content").on("focusout","#addDeviceName",function ()
    {
        validator.required($("#addDeviceName").val(), "#invalidName");
    });

    $("#content").on("focusout","#addDeviceIP",function ()
    {
        validator.ip($("#addDeviceIP").val(), "#invalidIp");
    });

    $("#content").on("focusout","#addDeviceUsername",function ()
    {
        validator.required($("#addDeviceUsername").val(), "#invalidUsername")
    });

    $("#content").on("focusout","#addDevicePassword",function ()
    {
        validator.required($("#addDevicePassword").val(), "#invalidPassword")
    });






    $("#content").on("focusout","#addDeviceName2",function ()
    {
        validator.updateRequired($("#addDeviceName2").val(), "#invalidName2");
    });

    $("#content").on("focusout","#addDeviceIP2",function ()
    {
        validator.updateIp($("#addDeviceIP2").val());
    });


});
