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
            let request = JSON.parse(xhrRequest.responseText);
            let cost = "$"+request.cost;
            let date = request.date.monthValue+"/"+request.date.dayOfMonth+"/"+request.date.year;
            let time = request.time.hour+":"+request.time.minute;
            let location = request.location;
            let description = request.description;
            let gradingFormat = request.gradingFormat;
            let type = request.eventType;
            let projected = "$"+request.projected;
            let status = request.approval;

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

            generateRequest(id, cost, date, time, location, description, gradingFormat, type, projected, status);
            
        }
    }

    //opens up the request
    xhrRequest.open("GET", xhrRequestUrl, true);
    //sends request
    xhrRequest.send();
}

//generate request information table
function generateRequest(id, cost, date, time, location, description, gradingFormat, type, projected, status) {
    //generate elements
    let table = document.getElementById("info-table");
    let tableBody = document.createElement("tbody");
    let idRow = document.createElement("tr");
    let costRow = document.createElement("tr");
    let dateRow = document.createElement("tr");
    let timeRow = document.createElement("tr");
    let locationRow = document.createElement("tr");
    let descriptionRow = document.createElement("tr");
    let gradingFormatRow = document.createElement("tr");
    let typeRow = document.createElement("tr");
    let projectedRow = document.createElement("tr");
    let statusRow = document.createElement("tr");
    let idHead = document.createElement("th");
    let costHead = document.createElement("th");
    let dateHead = document.createElement("th");
    let timeHead = document.createElement("th");
    let locationHead = document.createElement("th");
    let descriptionHead = document.createElement("th");
    let gradingFormatHead = document.createElement("th");
    let typeHead = document.createElement("th");
    let projectedHead = document.createElement("th");
    let statusHead = document.createElement("th");
    let idData = document.createElement("td");
    let costData = document.createElement("td");
    let dateData = document.createElement("td");
    let timeData = document.createElement("td");
    let locationData = document.createElement("td");
    let descriptionData = document.createElement("td");
    let gradingFormatData = document.createElement("td");
    let typeData = document.createElement("td");
    let projectedData = document.createElement("td");
    let statusData = document.createElement("td");

    //set values
    idHead.innerHTML = "Request ID:";
    costHead.innerHTML = "Event Cost:";
    dateHead.innerHTML = "Event Date:";
    timeHead.innerHTML = "Event Time (24hr):";
    locationHead.innerHTML = "Event Location:";
    descriptionHead.innerHTML = "Event Description:";
    gradingFormatHead.innerHTML= "Grading Format:";
    typeHead.innerHTML = "Event Type:";
    projectedHead.innerHTML = "Projected Reimbursement:";
    statusHead.innerHTML = "Approval Status:";
    idData.innerHTML = "# "+id;
    costData.innerHTML = cost;
    dateData.innerHTML = date;
    timeData.innerHTML = time;
    locationData.innerHTML = location;
    descriptionData.innerHTML = description;
    gradingFormatData.innerHTML = gradingFormat;
    typeData.innerHTML = type;
    projectedData.innerHTML = projected;
    statusData.innerHTML = status;

    //append elements together
    idRow.appendChild(idHead);
    idRow.appendChild(idData);
    costRow.appendChild(costHead);
    costRow.appendChild(costData);
    dateRow.appendChild(dateHead);
    dateRow.appendChild(dateData);
    timeRow.appendChild(timeHead);
    timeRow.appendChild(timeData);
    locationRow.appendChild(locationHead);
    locationRow.appendChild(locationData);
    descriptionRow.appendChild(descriptionHead);
    descriptionRow.appendChild(descriptionData);
    gradingFormatRow.appendChild(gradingFormatHead);
    gradingFormatRow.appendChild(gradingFormatData);
    typeRow.appendChild(typeHead);
    typeRow.appendChild(typeData);
    projectedRow.appendChild(projectedHead);
    projectedRow.appendChild(projectedData);
    statusRow.appendChild(statusHead);
    statusRow.appendChild(statusData);
    tableBody.appendChild(idRow);
    tableBody.appendChild(costRow);
    tableBody.appendChild(dateRow);
    tableBody.appendChild(timeRow);
    tableBody.appendChild(locationRow);
    tableBody.appendChild(descriptionRow);
    tableBody.appendChild(gradingFormatRow);
    tableBody.appendChild(typeRow);
    tableBody.appendChild(projectedRow);
    tableBody.appendChild(statusRow);
    table.appendChild(tableBody);
}