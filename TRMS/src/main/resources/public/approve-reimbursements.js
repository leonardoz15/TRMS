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
            requestList.forEach(element => {
                let id = element.requestId;
                let type = element.eventType;
                let cost = element.cost;
                let urgency = element.urgent;
                switch(urgency) {
                    case true:
                        urgency = "URGENT";
                        break;
                    case false:
                        urgency = "NOT URGENT";
                        break;
                }
                generateRequests(id, type, cost, urgency);
            });
        }
    }
    //opens up the request
    approveRequests.open("GET", approveUrl, true);
    //sends request
    approveRequests.send();
}



//generate table of requests to approve
function generateRequests(id, type, cost, urgency) {
    //generate elements
    let table = document.getElementById("requests-table");
    let tableBody = document.createElement("tbody");
    let tableRow = document.createElement("tr");
    let idCol = document.createElement("td");
    let typeCol = document.createElement("td");
    let costCol = document.createElement("td");
    let urgencyCol = document.createElement("td");
    let actionsCol = document.createElement("td");
    let detailsButton = document.createElement("button");
    let approveButton = document.createElement("button");
    let denyButton = document.createElement("button");

    //style elements
    idCol.className = "table-active";
    typeCol.className = "table-active";
    costCol.className = "table-active";
    urgencyCol.className = "table-active";
    actionsCol.className = "table-active";
    detailsButton.type = "button";
    approveButton.type = "button";
    denyButton.type = "button";
    detailsButton.className = "btn btn-outline-primary";
    approveButton.className = "btn btn-outline-success";
    denyButton.className = "btn btn-outline-danger";

    //set values
    idCol.innerHTML = "# "+id;
    typeCol.innerHTML = type;
    costCol.innerHTML = "$"+cost;
    urgencyCol.innerHTML = urgency;
    detailsButton.innerHTML = "Details";
    approveButton.innerHTML = "Approve";
    denyButton.innerHTML = "Deny";

    //append elements
    actionsCol.appendChild(detailsButton);
    actionsCol.appendChild(approveButton);
    actionsCol.appendChild(denyButton);
    tableRow.appendChild(idCol);
    tableRow.appendChild(typeCol);
    tableRow.appendChild(costCol);
    tableRow.appendChild(urgencyCol);
    tableRow.appendChild(actionsCol);
    tableBody.appendChild(tableRow);
    table.appendChild(tableBody);

    //add listeners to buttons
    detailsButton.addEventListener("click", function() {
        //window.location.replace("http://localhost:9091/details-reimbursement.html?request_id="+id);
    })
    approveButton.addEventListener("click", function () {
        //approveRequest(id);
    })
    denyButton.addEventListener("click", function () {
        //denyRequest(id);
    })

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