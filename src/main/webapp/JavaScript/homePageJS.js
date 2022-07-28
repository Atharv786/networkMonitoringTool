/**
 * Created by atharv on 14/4/22.
 */

$(document).ready(function(){

    discovery.load();

    $(".AboutUs").click(function(){

        discovery.load();
    });

    $(".Home").click(function(){

        dashboard.load();

    });

    $(".ContactUs").click(function()
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
        validator.name($("#addDeviceName").val());
    });

    $("#content").on("focusout","#addDeviceIP",function ()
    {
        validator.ip($("#addDeviceIP").val());
    });

    $("#content").on("focusout","#addDeviceUsername",function ()
    {
        validator.username($("#addDeviceUsername").val())
    });

    $("#content").on("focusout","#addDevicePassword",function ()
    {
        validator.password($("#addDevicePassword").val())
    });

});
