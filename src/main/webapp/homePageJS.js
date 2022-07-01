/**
 * Created by atharv on 14/4/22.
 */

$(document).ready(function(){

    $(".AboutUs").click(function(){

        $(".PollingData").hide();
        $(".Dashboard").hide();
        $(".monitor").hide();
        $(".discovery").show();
        $(".addDeviceButton").show();
        $(".Content").hide();
        $(".AboutUs").css({"background-color":"white","color":"#36a9e1"});
        $(".Home, .ContactUs, .SignOut").css({"background-color":"#36a9e1","color":"white"});
        $(".AboutUs").hover(function(){
            $(".AboutUs").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".AboutUs").css({"background-color":"white","color":"#36a9e1"});
        });
        $(".Home").hover(function(){
            $(".Home").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".Home").css({"background-color":"#36a9e1","color":"white"});
        });
        $(".ContactUs").hover(function(){
            $(".ContactUs").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".ContactUs").css({"background-color":"#36a9e1","color":"white"});
        });
        $(".SignOut").hover(function(){
            $(".SignOut").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".SignOut").css({"background-color":"#36a9e1","color":"white"});
        });
    });

    $(".Home").click(function(){

        $(".PollingData").hide();
        $(".Dashboard").show();
        $(".monitor").hide();
        $(".addDeviceButton").hide();
        $(".discovery").hide();
        $(".Content").html("<h1>Hello Home Page</h1>");
        $(".Content").css({"text-align":"center"});
        $(".Home").css({"background-color":"white","color":"#36a9e1"});
        $(".AboutUs, .ContactUs, .SignOut").css({"background-color":"#36a9e1","color":"white"});
        $(".Home").hover(function(){
            $(".Home").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".Home").css({"background-color":"white","color":"#36a9e1"});
        });
        $(".AboutUs").hover(function(){
            $(".AboutUs").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".AboutUs").css({"background-color":"#36a9e1","color":"white"});
        });
        $(".ContactUs").hover(function(){
            $(".ContactUs").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".ContactUs").css({"background-color":"#36a9e1","color":"white"});
        });
        $(".SignOut").hover(function(){
            $(".SignOut").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".SignOut").css({"background-color":"#36a9e1","color":"white"});
        });
    });

    $(".ContactUs").click(function()
    {
        $(".PollingData").hide();
        $(".Dashboard").hide();
        $(".monitor").show();
        $(".addDeviceButton").hide();
        $(".discovery").hide();
        $(".Content").css({"text-align":"center"});
        $(".ContactUs").css({"background-color":"white","color":"#36a9e1"});
        $(".Home, .AboutUs, .SignOut").css({"background-color":"#36a9e1","color":"white"});
        $(".ContactUs").hover(function(){
            $(".ContactUs").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".ContactUs").css({"background-color":"white","color":"#36a9e1"});
        });
        $(".AboutUs").hover(function(){
            $(".AboutUs").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".AboutUs").css({"background-color":"#36a9e1","color":"white"});
        });
        $(".Home").hover(function(){
            $(".Home").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".Home").css({"background-color":"#36a9e1","color":"white"});
        });
        $(".SignOut").hover(function(){
            $(".SignOut").css({"background-color":"white","color":"#36a9e1"});
        },function(){
            $(".SignOut").css({"background-color":"#36a9e1","color":"white"});
        });
    });

    $("#SignOut").click(function()
    {
        var request = {
            url : "logout",
            callback: functions.onSignOutButtonBind
        }
        functions.loginAjax(request);
    });

    $("#closePolling").click(function()
    {
        $(".nav").show();
        $(".PollingData").hide();
        $(".Dashboard").hide();
        $(".monitor").show();
        $(".addDeviceButton").hide();
        $(".discovery").hide();
    });

    $("#addDevice").click(function()
    {
        var table = $('#discoveryTable').DataTable();

        var rows = table
            .rows()
            .remove()
            .draw();

        var request = {

            url : "discovery",
            type : "POST",
            callback : functions.onload
        }
        functions.loginAjax(request);

    });

});
