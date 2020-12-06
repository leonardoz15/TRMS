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
                form.action = "/approve/"+requestId;

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

function generateRequest(id, cost, date, time, location, description, gradingFormat, type, projected){
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
    let idHead = document.createElement("th");
    let costHead = document.createElement("th");
    let dateHead = document.createElement("th");
    let timeHead = document.createElement("th");
    let locationHead = document.createElement("th");
    let descriptionHead = document.createElement("th");
    let gradingFormatHead = document.createElement("th");
    let typeHead = document.createElement("th");
    let projectedHead = document.createElement("th");
    let idData = document.createElement("td");
    let costData = document.createElement("td");
    let dateData = document.createElement("td");
    let timeData = document.createElement("td");
    let locationData = document.createElement("td");
    let descriptionData = document.createElement("td");
    let gradingFormatData = document.createElement("td");
    let typeData = document.createElement("td");
    let projectedData = document.createElement("td");

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
    idData.innerHTML = "# "+id;
    costData.innerHTML = cost;
    dateData.innerHTML = date;
    timeData.innerHTML = time;
    locationData.innerHTML = location;
    descriptionData.innerHTML = description;
    gradingFormatData.innerHTML = gradingFormat;
    typeData.innerHTML = type;
    projectedData.innerHTML = projected;

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
    tableBody.appendChild(idRow);
    tableBody.appendChild(costRow);
    tableBody.appendChild(dateRow);
    tableBody.appendChild(timeRow);
    tableBody.appendChild(locationRow);
    tableBody.appendChild(descriptionRow);
    tableBody.appendChild(gradingFormatRow);
    tableBody.appendChild(typeRow);
    tableBody.appendChild(projectedRow);
    table.appendChild(tableBody);
}

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