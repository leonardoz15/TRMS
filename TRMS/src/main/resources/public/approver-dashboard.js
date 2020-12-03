
window.onload = function () {

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

let addBalance = function(balance) {
    let span = document.getElementById("bal");
    span.innerText = balance;
}