var stompClient = null;
var initalMessages = true;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/chatroom');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        setConnected(true);
        stompClient.subscribe('/topic/chat', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
    initalMessages = true;
}

function sendMesssage() {
    var name = document.getElementById('name').value;
    var text = document.getElementById('message').value;
    stompClient.send("/app/sendMessage", {}, JSON.stringify({'name': name, 'text': text}));
}

function showMessage(message) {
    if (Array.isArray(message)) {
        if (initalMessages) {
            message.forEach(
                function (x) {$("#messages").append("<tr><td>" + "(" + x.timestamp.substring(11, 16) + ") " + 
                x.user + ": " + x.content + "</td></tr>");}
            )
        }
        initalMessages = false;
    }
    else 

    $("#messages").append("<tr><td>" + "(" + message.timestamp.substring(11, 16) + ") " + 
    message.user + ": " + message.content + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMesssage(); });
});