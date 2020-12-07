
window.onload = function () {

    var xhrCheck = new XMLHttpRequest();
    var checkUrl = "http://localhost:9091/login";
    xhrCheck.onreadystatechange = function () {
        if(xhrCheck.readyState == 4 && xhrCheck.status === 200) {
            let authorized = JSON.parse(xhrCheck.responseText);
            if(authorized === false) {
                alert("Please login first");
                window.location.replace("http://localhost:9091/login.html");
            }
            else {
                generateBal();
            }
        }
    }

    //opens up the request
    xhrCheck.open("GET", checkUrl, true);
    //sends request
    xhrCheck.send();
}

function generateBal() {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:9091/employeeBalance";
    //sets up ready state handler
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {
            case 0:
                console.log("Nothing, initialized not sent");
                break;
            case 1:
                console.log("Connection established");
                break;
            case 2:
                console.log("Request sent");
                break;
            case 3:
                console.log("Waiting response");
                break;
            case 4:
                console.log("Response received");
                //logic to add balance to document
                if (xhr.status === 200) {
                    let balance = JSON.parse(xhr.responseText);
                    addBalance(balance);                    
                }
                break;
        }
    };
    //opens up the request
    xhr.open("GET", url, true);
    //sends request
    xhr.send();

};

var logout = document.getElementById("logout");

logout.onclick = function() {

    var out = new XMLHttpRequest();
    var outUrl = "http://localhost:9091/logout";
    out.onreadystatechange = function () {
        if(out.readyState == 4 && out.status === 200) {
            window.location.replace("http://localhost:9091/index.html");   
        }
    }

    //opens up the request
    out.open("GET", outUrl, true);
    //sends request
    out.send();

};

let addBalance = function(balance) {
    let span = document.getElementById("bal");
    span.innerText = balance;
}