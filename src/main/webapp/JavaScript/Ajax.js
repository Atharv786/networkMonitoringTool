var ajax = {

    ajaxRequest : function (request)
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

            error: function (data)
            {
                alert("Error");
            }

        });
    },



}