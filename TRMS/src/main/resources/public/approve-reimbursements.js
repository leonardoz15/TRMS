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
                getRequestsToApprove();
            }
        }
    }

    //opens up the request
    xhrCheck.open("GET", checkUrl, true);
    //sends request
    xhrCheck.send();
}

//get requests by user privildege
function getRequestsToApprove() {

    var approveRequests = new XMLHttpRequest();
    var approveUrl = "http://localhost:9091/requestApproval";
    approveRequests.onreadystatechange = function () {
        if(approveRequests.readyState == 4 && approveRequests.status === 200) {
            let requestList = JSON.parse(approveRequests.responseText);



            //generateRequests(id, type, cost, urgency)
        }
    }
    //opens up the request
    approveRequests.open("GET", approveUrl, true);
    //sends request
    approveRequests.send();
}



//generate table of requests to approve
function generateRequests(id, type, cost, urgency) {

}

//logout:
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