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
                getRequestById(requestId);
            }
        }
    }

    //opens up the request
    xhrCheck.open("GET", checkUrl, true);
    //sends request
    xhrCheck.send();
}

//get requests by requestId
function getRequestById(id) {

    var xhrRequest = new XMLHttpRequest();
    var xhrRequestUrl = "http://localhost:9091/request/"+id;
    xhrRequest.onreadystatechange = function () {
        if(xhrRequest.readyState == 4 && xhrRequest.status === 200) {
            let requestList = JSON.parse(xhrRequests.responseText);
            requestList.forEach(element => {
                let id = element.requestId;
                let type = element.eventType;
                let projected = element.projected;
                let status = element.approval;

                switch(status) {
                    case "DS_PENDING":
                        status = "Pending";
                        break;
                    case "DH_PENDING":
                        status = "Pending";
                        break;
                    case "BC_PENDING":
                        status = "Pending";
                        break;
                    case "INFO_NEEDED":
                        status = "Awaiting more info";
                        break;
                    case "DENIED":
                        status = "Denied";
                        break;
                    case "APPROVED":
                        status = "Approved";
                        break;
                }

                generateRequest(id, type, projected, status);
            });
        }
    }

    //opens up the request
    xhrRequest.open("GET", xhrRequestUrl, true);
    //sends request
    xhrRequest.send();
}