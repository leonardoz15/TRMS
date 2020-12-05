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
                getRequests()
            }
        }
    }

    //opens up the request
    xhrCheck.open("GET", checkUrl, true);
    //sends request
    xhrCheck.send();
}


//get requests by userId
function getRequests() {

    var xhrRequests = new XMLHttpRequest();
    var xhrRequestUrl = "http://localhost:9091/request";
    xhrRequests.onreadystatechange = function () {
        if(xhrRequests.readyState == 4 && xhrRequests.status === 200) {
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
    xhrRequests.open("GET", xhrRequestUrl, true);
    //sends request
    xhrRequests.send();
}


//generate table row for request
function generateRequest(id, type, projected, status) {
    //generate elements
    let table = document.getElementById("requests-table");
    let tableBody = document.createElement("tbody");
    let tableRow = document.createElement("tr");
    let idCol = document.createElement("td");
    let typeCol = document.createElement("td");
    let projectedCol = document.createElement("td");
    let statusCol = document.createElement("td");
    let actionsCol = document.createElement("td");
    let editButton = document.createElement("button");
    let deleteButton = document.createElement("button");

    //setup styling
    idCol.className = "table-active";
    typeCol.className = "table-active";
    projectedCol.className = "table-active";
    statusCol.className = "table-active";
    actionsCol.className = "table-active";
    editButton.type = "button";
    editButton.className = "btn btn-outline-primary";
    deleteButton.type = "button";
    deleteButton.className = "btn btn-outline-danger";

    //set values
    idCol.innerHTML = "# "+id;
    typeCol.innerHTML = type;
    projectedCol.innerHTML = "$"+projected;
    statusCol.innerHTML = status;
    editButton.innerHTML = "Edit";
    deleteButton.innerHTML = "Delete";

    //append to table
    actionsCol.appendChild(editButton);
    actionsCol.appendChild(deleteButton);
    tableRow.appendChild(idCol);
    tableRow.appendChild(typeCol);
    tableRow.appendChild(projectedCol);
    tableRow.appendChild(statusCol);
    tableRow.appendChild(actionsCol);
    tableBody.appendChild(tableRow);
    table.appendChild(tableBody);

    //TODO: add listeners to both action buttons
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