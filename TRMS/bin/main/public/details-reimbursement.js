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
                let query = window.location.search;
                let params = new URLSearchParams(query);
                var requestId = params.get('request_id');

                let form = document.getElementById("approver-form");
                form.action = "/approve/"+id;

                getRequestToApprove(requestId);
            }
        }
    }

    //opens up the request
    xhrCheck.open("GET", checkUrl, true);
    //sends request
    xhrCheck.send();
}

function getRequestToApprove(id) {
    var xhrRequest = new XMLHttpRequest();
    var xhrRequestUrl = "http://localhost:9091/request/"+id;
    xhrRequest.onreadystatechange = function () {
        if(xhrRequest.readyState == 4 && xhrRequest.status === 200) {
            let request = JSON.parse(xhrRequest.responseText);
            let cost = "$"+request.cost;
            let date = request.date.monthValue+"/"+request.date.dayOfMonth+"/"+request.date.year;
            let time = request.time.hour+":"+request.time.minute;
            let location = request.location;
            let description = request.description;
            let gradingFormat = request.gradingFormat;
            let type = request.eventType;
            let projected = "$"+request.projected;
            //let hasAttachment = getAttachedFile();

            generateRequest(id, cost, date, time, location, description, gradingFormat, type, projected);
            
        }
    }

    //opens up the request
    xhrRequest.open("GET", xhrRequestUrl, true);
    //sends request
    xhrRequest.send();
}

function generateRequest(id, cost, date, time, location, description, gradingFormat, type, projected);

//getAttachedFile()


function getInfo(selection) {
    if(selection){
        let moreInfoValue = document.getElementById("more").value;
        if(selection.value == moreInfoValue) {
            document.getElementById("more-info").style.display = "block";
        }
        else {
            document.getElementById("more-info").style.display = "none";
        }
    }
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