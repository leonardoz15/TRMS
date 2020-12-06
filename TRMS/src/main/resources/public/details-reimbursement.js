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