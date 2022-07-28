var functions = {

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


            ajax.ajaxRequest(request);
        });

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


}


$(document).ready(function()
{
    $(".showPassword").click(function()
    {
        var x = $("#password");

        if (x.attr("type") === "password")
        {
            x.attr("type","text");
        }
        else
        {
            x.attr("type","password");
        }

    });


});