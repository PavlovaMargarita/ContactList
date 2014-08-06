function openCorrectPhone(obj) {
    var x = obj.parentNode.parentNode.parentNode.parentNode.rowIndex;
    x--;
    var countryCodeIDElement = document.getElementsByName('countryCodeID');
    var countryCodeID = countryCodeIDElement[x].value;
    document.getElementById('countryCodeIDCorrect').value = countryCodeID;

    var operatorCodeIDElement = document.getElementsByName('operatorCodeID');
    var operatorCodeID = operatorCodeIDElement[x].value;
    document.getElementById('operatorCodeIDCorrect').value = operatorCodeID;

    var phoneNumberIDElement = document.getElementsByName('phoneNumberID');
    var phoneNumberID = phoneNumberIDElement[x].value;
    document.getElementById('phoneNumberIDCorrect').value = phoneNumberID;

    var phoneTypeIDElement = document.getElementsByName('phoneTypeID');
    var phoneTypeID = phoneTypeIDElement[x].value;
    var phoneTypeSelect = document.getElementById('phoneTypeSelect');
    if(phoneTypeID == 'Мобильный'){
        phoneTypeSelect.selectedIndex = "0";
    }
    else{
        phoneTypeSelect.selectedIndex = "1";
    }
//    var phoneTypeIDElement = document.getElementsByName('phoneTypeID');
//    var phoneTypeID = phoneTypeIDElement[x].value;
//    document.getElementById('phoneTypeIDCorrect').value = phoneTypeID;

    var commentIDElement = document.getElementsByName('commentPhoneID');
    var commentID = commentIDElement[x].value;
    document.getElementById('commentPhoneIDCorrect').value = commentID;

    document.getElementById('rowCountPhoneCorrect').value = x;

}

document.getElementById('saveCorrectPhone').onclick = function(){
    var rowCount = document.getElementById('rowCountPhoneCorrect').value;
    var countryCode = document.getElementById('countryCodeIDCorrect').value.trim();
    var operatorCode = document.getElementById('operatorCodeIDCorrect').value.trim();
    var phoneNumber = document.getElementById('phoneNumberIDCorrect').value.trim();
//    var phoneType = document.getElementById('phoneTypeIDCorrect').value.trim();
    var e = document.getElementById('phoneTypeSelect');
    var phoneType = e.options[e.selectedIndex].value;
    var comment = document.getElementById('commentPhoneIDCorrect').value.trim();
    if(countryCode == "" || operatorCode == "" || phoneNumber == "" || phoneType == "" || !isInt(countryCode) ||
        !isInt(operatorCode) || !isInt(phoneNumber)){
        return false;
    }
    var rows = document.getElementById('phoneTable').rows;
    rowCount++;
    var row = rows[rowCount].cells;
    row[1].innerHTML = "+"+countryCode+operatorCode+phoneNumber;
    row[2].innerHTML = phoneType;
    row[3].innerHTML = comment;
    rowCount--;
    document.getElementsByName('countryCodeID')[rowCount].value = countryCode;
    document.getElementsByName('operatorCodeID')[rowCount].value = operatorCode;
    document.getElementsByName('phoneNumberID')[rowCount].value = phoneNumber;
    document.getElementsByName('phoneTypeID')[rowCount].value = phoneType;
    document.getElementsByName('commentPhoneID')[rowCount].value = comment;
}

document.getElementById('saveAddPhone').onclick = function(){

    var countryCode = document.getElementById('countryCodeIDAdd').value.trim();
    var operatorCode = document.getElementById('operatorCodeIDAdd').value.trim();
    var phoneNumber = document.getElementById('phoneNumberIDAdd').value.trim();
//    var phoneType = document.getElementById('phoneTypeIDAdd').value.trim();
    var e = document.getElementById('phoneTypeSelectADD');
    var phoneType = e.options[e.selectedIndex].value;
    var comment = document.getElementById('commentPhoneIDAdd').value.trim();
    if(countryCode == "" || operatorCode == "" || phoneNumber == "" || phoneType == "" || !isInt(countryCode) ||
        !isInt(operatorCode) || !isInt(phoneNumber)){
        return false;
    }

    document.getElementById('countryCodeIDAdd').value = "";
    document.getElementById('operatorCodeIDAdd').value = "";
    document.getElementById('phoneNumberIDAdd').value = "";
//    document.getElementById('phoneTypeIDAdd').value = "";
    document.getElementById('commentPhoneIDAdd').value = "";

    var table = document.getElementById('phoneTable');
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var elementCountyCode = document.createElement("input");
    elementCountyCode.type = "hidden";
    elementCountyCode.name = "countryCodeID";
    elementCountyCode.value = countryCode;
    row.appendChild(elementCountyCode);

    var elementOperatorCode = document.createElement("input");
    elementOperatorCode.type = "hidden";
    elementOperatorCode.name = "operatorCodeID";
    elementOperatorCode.value = operatorCode;
    row.appendChild(elementOperatorCode);

    var elementPhoneNumber = document.createElement("input");
    elementPhoneNumber.type = "hidden";
    elementPhoneNumber.name = "phoneNumberID";
    elementPhoneNumber.value = phoneNumber;
    row.appendChild(elementPhoneNumber);

    var elementPhoneType = document.createElement("input");
    elementPhoneType.type = "hidden";
    elementPhoneType.name = "phoneTypeID";
    elementPhoneType.value = phoneType;
    row.appendChild(elementPhoneType);

    var elementComment = document.createElement("input");
    elementComment.type = "hidden";
    elementComment.name = "commentPhoneID";
    elementComment.value = comment;
    row.appendChild(elementComment);

    var cellCheckBox = row.insertCell(0);
    var elementCheckBox = document.createElement("input");
    elementCheckBox.type = "checkBox";
    elementCheckBox.name = "checkPhone";
    elementCheckBox.value = "";
    cellCheckBox.appendChild(elementCheckBox);

    var cellPhone = row.insertCell(1);
    cellPhone.innerHTML = "+"+countryCode+operatorCode+phoneNumber;

    var cellPhoneType = row.insertCell(2);
    cellPhoneType.innerHTML = phoneType;

    var cellComment = row.insertCell(3);
    cellComment.innerHTML = comment;

    var cellButton = row.insertCell(4);
    var elementButton = document.createElement("button");
    elementButton.className = "standard";
    elementButton.type = "button";
    var elementSpan = document.createElement("span");
    elementSpan.innerHTML = '<a href="#correctContactForm" onclick="openCorrectPhone(this)" style="color:white"> Редактировать телефон</a> ';
    elementButton.appendChild(elementSpan);
    cellButton.appendChild(elementButton);
}

function deletePhone(){
    var inputElements = document.getElementsByName('checkPhone');
    for(var i=inputElements.length - 1; inputElements[i]; i--){
        if(inputElements[i].checked){
            document.getElementById("phoneTable").deleteRow(inputElements[i].parentNode.parentNode.rowIndex);
        }
    }

}

var row;
var idFile;
document.getElementById('addFileButton').onclick = function() {

    var table = document.getElementById('fileTable');
    var rowCount = table.rows.length;
    row = table.insertRow(rowCount);
    row.style.display = "none";

    var elementFile = document.createElement("input");
    elementFile.type = "file";
    elementFile.name = "fileF";
    elementFile.style.display = "none";
    elementFile.id = new Date();
    idFile = elementFile.id;
    row.appendChild(elementFile);


    document.getElementById(idFile).click();
}

document.getElementById('addFile').onclick = function(){
    var fileName = document.getElementById(idFile).value;
    if(fileName == ""){
        return false;
    }
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1;
    var yyyy = today.getFullYear();
    var comment = document.getElementById('commentFileIDAdd').value.trim();
    document.getElementById('commentFileIDAdd').value = "";

    var elementFileName = document.createElement("input");
    elementFileName.type = "hidden";
    elementFileName.name = "fileName";
    fileName = fileName.replace("C:\\fakepath\\" , "");
    elementFileName.value = fileName;
    row.appendChild(elementFileName);


    var elementFileData = document.createElement("input");
    elementFileData.type = "hidden";
    elementFileData.name = "fileDate";
    elementFileData.value = yyyy+"-"+mm+"-"+dd;
    row.appendChild(elementFileData);

    var elementComment = document.createElement("input");
    elementComment.type = "hidden";
    elementComment.name = "commentFile";
    elementComment.value = comment;
    row.appendChild(elementComment);

    var cellCheckBox = row.insertCell(0);
    var elementCheckBox = document.createElement("input");
    elementCheckBox.type = "checkBox";
    elementCheckBox.name = "checkFile";
    elementCheckBox.value = "";
    cellCheckBox.appendChild(elementCheckBox);

    var cellFileName = row.insertCell(1);
    cellFileName.innerHTML = fileName;

    var cellFileData = row.insertCell(2);
    cellFileData.innerHTML = dd+"-"+mm+"-"+yyyy;

    var cellComment = row.insertCell(3);
    cellComment.innerHTML = comment;

    var cellButton = row.insertCell(4);
    var elementButton = document.createElement("button");
    elementButton.className = "standard";
    elementButton.type = "button";
    var elementSpan = document.createElement("span");
    elementSpan.innerHTML = '<a href="#correctAccessionForm" onclick="openCorrectFile(this)" style="color:white"> Редактировать файл </a>';
    elementButton.appendChild(elementSpan);
    cellButton.appendChild(elementButton);

    row.style.display = "";
}

function openCorrectFile(obj){
    var x = obj.parentNode.parentNode.parentNode.parentNode.rowIndex;
    x--;
    var fileNameElement = document.getElementsByName('fileName');
    var fileNameID = fileNameElement[x].value;
    document.getElementById('fileNameIDCorrect').value = fileNameID;

    var commentFileElement = document.getElementsByName('commentFile');
    var commentFileID = commentFileElement[x].value;
    document.getElementById('commentFileIDCorrect').value = commentFileID;

    document.getElementById('rowCountFileCorrect').value = x;
}

document.getElementById('saveCorrectFileButton').onclick = function(){
    var rowCount = document.getElementById('rowCountFileCorrect').value;
    var fileName = document.getElementById('fileNameIDCorrect').value.trim();
    var commentFile = document.getElementById('commentFileIDCorrect').value.trim();
    if(fileName == ""){
        return false;
    }
    var rows = document.getElementById('fileTable').rows;
    rowCount++;
    var row = rows[rowCount].cells;
    row[1].innerHTML = fileName;
    row[3].innerHTML = commentFile;
    rowCount--;
    document.getElementsByName('fileName')[rowCount].value = fileName;
    document.getElementsByName('commentFile')[rowCount].value = commentFile;

}

document.getElementById('deleteFile').onclick = function() {
    var inputElements = document.getElementsByName('checkFile');
    for (var i = inputElements.length - 1; inputElements[i]; i--) {
        if (inputElements[i].checked) {
            document.getElementById("fileTable").deleteRow(inputElements[i].parentNode.parentNode.rowIndex);
        }
    }
}

document.getElementById('save').onclick = function(){
    var regEmail= /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var regWeb = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    return document.getElementById('surname').value.trim() != "" &&
        document.getElementById('name').value.trim() != "" &&
        document.getElementById('patronymic').value.trim() != "" &&
        document.getElementById('dateOfBirth').value.trim() != "" &&
        dateCheck(document.getElementById('dateOfBirth').value) &&
        document.getElementById('nationality').value.trim() != "" &&
        document.getElementById('email').value.trim() != "" &&
        document.getElementById('city').value.trim() != "" &&
        document.getElementById('street').value.trim() != "" &&
        document.getElementById('home').value.trim() != "" &&
        isInt(document.getElementById('home').value) &&
        document.getElementById('flat').value.trim() != "" &&
        isInt(document.getElementById('flat').value) &&
        document.getElementById('index').value.trim() != "" &&
        regEmail.test(document.getElementById('email').value) &&
        ((document.getElementById('webSite').value.trim() == "") ||
         (document.getElementById('webSite').value.trim() != "" && regWeb.test(document.getElementById('webSite').value)))
}

document.getElementById('email').onkeyup = function(){
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var val = document.getElementById('email').value;
    if(re.test(val) == false){
        document.getElementById('email').style.borderColor = "red";
    }
    else{
        document.getElementById('email').style.borderColor = "#ccc";
    }
}

document.getElementById('webSite').onkeyup = function(){
    var re = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
    var val = document.getElementById('webSite').value;
    if(re.test(val) == false){
        document.getElementById('webSite').style.borderColor = "red";
    }
    else{
        document.getElementById('webSite').style.borderColor = "#ccc";
    }
}
function isInt(obj){
    var reg = /^\d+$/;
    if(reg.test(obj)){
        return true;
    }
    else return false;
}
document.getElementById('home').onkeyup = function(){
    var val = document.getElementById('home').value;
    if(isInt(val)){
        document.getElementById('home').style.borderColor = "#ccc";
    }
    else{
        document.getElementById('home').style.borderColor = "red";
    }
}

document.getElementById('flat').onkeyup = function(){
    var val = document.getElementById('flat').value;
    if(isInt(val)){
        document.getElementById('flat').style.borderColor = "#ccc";
    }
    else{
        document.getElementById('flat').style.borderColor = "red";
    }
}
document.getElementById('dateOfBirth').onkeyup = function() {
    dateCheck(document.getElementById('dateOfBirth').value);

}
function dateCheck(val){
var dateFormat = /^(0?[1-9]|[12][0-9]|3[01])[\-](0?[1-9]|1[012])[\-]\d{4}$/;
    if(val.match(dateFormat)) {
        var dateArray = val.split('-');
        var dateArrayLength = dateArray.length;
        if (dateArrayLength == 3) {
            var dd = parseInt(dateArray[0]);
            var mm = parseInt(dateArray[1]);
            var yy = parseInt(dateArray[2]);
            // Create list of days of a month [assume there is no leap year by default]
            var listOfDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            if (mm == 1 || mm > 2) {
                if (dd > listOfDays[mm - 1]) {
                    document.getElementById('dateOfBirth').style.borderColor = "red";
                    return false;
                }
                else {
                    document.getElementById('dateOfBirth').style.borderColor = "#ccc";
                    return true;
                }
            }
            if (mm == 2) {
                var lyear = false;
                if ((!(yy % 4) && yy % 100) || !(yy % 400)) {
                    lyear = true;
                }
                if ((lyear == false) && (dd >= 29)) {
                    document.getElementById('dateOfBirth').style.borderColor = "red";
                    return false;
                }
                if ((lyear == true) && (dd > 29)) {
                    document.getElementById('dateOfBirth').style.borderColor = "red";
                    return false;
                }
                document.getElementById('dateOfBirth').style.borderColor = "#ccc";
                return true;
            }
        }
        else {
            document.getElementById('dateOfBirth').style.borderColor = "red";
            return false;
        }
    }
    else {
        document.getElementById('dateOfBirth').style.borderColor = "red";
        return false;
    }
}

document.getElementById('countryCodeIDAdd').onkeyup = function(){
    if(isInt(document.getElementById('countryCodeIDAdd').value)){
        document.getElementById('countryCodeIDAdd').style.borderColor = "#ccc";
    }
    else {
        document.getElementById('countryCodeIDAdd').style.borderColor = "red";
    }
}

document.getElementById('operatorCodeIDAdd').onkeyup = function(){
    if(isInt(document.getElementById('operatorCodeIDAdd').value)){
        document.getElementById('operatorCodeIDAdd').style.borderColor = "#ccc";
    }
    else {
        document.getElementById('operatorCodeIDAdd').style.borderColor = "red";
    }
}

document.getElementById('phoneNumberIDAdd').onkeyup = function(){
    if(isInt(document.getElementById('phoneNumberIDAdd').value)){
        document.getElementById('phoneNumberIDAdd').style.borderColor = "#ccc";
    }
    else {
        document.getElementById('phoneNumberIDAdd').style.borderColor = "red";
    }
}

document.getElementById('countryCodeIDCorrect').onkeyup = function(){
    if(isInt(document.getElementById('countryCodeIDCorrect').value)){
        document.getElementById('countryCodeIDCorrect').style.borderColor = "#ccc";
    }
    else {
        document.getElementById('countryCodeIDCorrect').style.borderColor = "red";
    }
}

document.getElementById('operatorCodeIDCorrect').onkeyup = function(){
    if(isInt(document.getElementById('operatorCodeIDCorrect').value)){
        document.getElementById('operatorCodeIDCorrect').style.borderColor = "#ccc";
    }
    else {
        document.getElementById('operatorCodeIDCorrect').style.borderColor = "red";
    }
}

document.getElementById('phoneNumberIDCorrect').onkeyup = function(){
    if(isInt(document.getElementById('phoneNumberIDCorrect').value)){
        document.getElementById('phoneNumberIDCorrect').style.borderColor = "#ccc";
    }
    else {
        document.getElementById('phoneNumberIDCorrect').style.borderColor = "red";
    }
}

document.getElementById('selectPhotoButton').onclick = function(){
    document.getElementById('photoFileID').click();
}
var resultPhoto;
function onFileSelected(event) {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function(event) {
        resultPhoto = event.target.result;
    };

    reader.readAsDataURL(selectedFile);
}

document.getElementById('loadPhotoButton').onclick = function(){
    if(resultPhoto == "" || resultPhoto == undefined){
        return false;
    }
    var imgtag = document.getElementById("photoPerson");
    imgtag.src = resultPhoto;
}