var socket = {

    createSocket: function ()
    {
        var protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';

        var host = window.location.host;

        var path = '/server-endpoint';

        var webSocket = new WebSocket(protocol + host + path);

        webSocket.onopen = function ()
        {
            webSocket.open();

            console.log("Open method called");

            webSocket.send(sessionStorage.getItem("sessionId"));
        };

        webSocket.onmessage = function (message)
        {
            if(message.data=="DeviceAddedSuccesfully")
            {
                alerts.success("Monitor added successfully");
            }
            else if(message.data=="Unreachable")
            {
                alerts.warning("Device is Unreachable");
            }
            else if(message.data=="DeviceCannotAdded")
            {
                alerts.error("Cannot add device");
            }
            else if(message.data=="DeviceTypeIsNotLinux")
            {
                alerts.info("Device type is not linux type");
            }
            else if(message.data=="deviceAlreadyPresent")
            {
                alerts.warning("Device is already present in monitor grid");
            }
        };

        webSocket.onerror = function (message)
        {
            sockethelper.processError(message);
        };

        webSocket.onclose = function ()
        {
            socket.createSocket();
        };
    }
};
