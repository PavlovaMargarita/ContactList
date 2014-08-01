function openCorrectPhone(obj) {

//    var checkedValue = 0;
//    var find = false;
//    var inputElements = document.getElementsByName('checkPhone');
//    for(var i=0; inputElements[i]; ++i){
//        if(inputElements[i].checked && find == false){
//            checkedValue = i;
//            break;
//        }
//    }
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
    document.getElementById('phoneTypeIDCorrect').value = phoneTypeID;

    var commentIDElement = document.getElementsByName('commentPhoneID');
    var commentID = commentIDElement[x].value;
    document.getElementById('commentPhoneIDCorrect').value = commentID;

    document.getElementById('rowCountPhoneCorrect').value = x;

}

function saveCorrectPhone(){
    var rowCount = document.getElementById('rowCountPhoneCorrect').value;
    var countryCode = document.getElementById('countryCodeIDCorrect').value;
    var operatorCode = document.getElementById('operatorCodeIDCorrect').value;
    var phoneNumber = document.getElementById('phoneNumberIDCorrect').value;
    var phoneType = document.getElementById('phoneTypeIDCorrect').value;
    var comment = document.getElementById('commentPhoneIDCorrect').value;
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

function saveAddPhone(){
    var countryCode = document.getElementById('countryCodeIDAdd').value;
    var operatorCode = document.getElementById('operatorCodeIDAdd').value;
    var phoneNumber = document.getElementById('phoneNumberIDAdd').value;
    var phoneType = document.getElementById('phoneTypeIDAdd').value;
    var comment = document.getElementById('commentPhoneIDAdd').value;
    document.getElementById('countryCodeIDAdd').value = "";
    document.getElementById('operatorCodeIDAdd').value = "";
    document.getElementById('phoneNumberIDAdd').value = "";
    document.getElementById('phoneTypeIDAdd').value = "";
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
    elementSpan.innerHTML = '<a href="#correctContactForm" onclick="openCorrectPhone(this)"> Редактировать контакт </a>';
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
//    var fileName = document.getElementById('fileNameIDAdd').value;
//    var today = new Date();
//    var dd = today.getDate();
//    var mm = today.getMonth() + 1;
//    var yyyy = today.getFullYear();
//    var comment = document.getElementById('commentFileIDAdd').value;
//    document.getElementById('fileNameIDAdd').value = "";
//    document.getElementById('commentFileIDAdd').value = "";

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

//    var elementFileData = document.createElement("input");
//    elementFileData.type = "hidden";
//    elementFileData.name = "fileData";
//    elementFileData.value = yyyy+"-"+mm+"-"+dd;
//    row.appendChild(elementFileData);
//
//    var elementComment = document.createElement("input");
//    elementComment.type = "hidden";
//    elementComment.name = "commentFile";
//    elementComment.value = comment;
//    row.appendChild(elementComment);
//
//    var cellCheckBox = row.insertCell(0);
//    var elementCheckBox = document.createElement("input");
//    elementCheckBox.type = "checkBox";
//    elementCheckBox.name = "checkFile";
//    elementCheckBox.value = "";
//    cellCheckBox.appendChild(elementCheckBox);
//
//    var cellFileName = row.insertCell(1);
//    cellFileName.innerHTML = fileName;
//
//    var cellFileData = row.insertCell(2);
//    cellFileData.innerHTML = dd+"-"+mm+"-"+yyyy;
//
//    var cellComment = row.insertCell(3);
//    cellComment.innerHTML = comment;
//
//    var cellButton = row.insertCell(4);
//    var elementButton = document.createElement("button");
//    elementButton.className = "standard";
//    elementButton.type = "button";
//    var elementSpan = document.createElement("span");
//    elementSpan.innerHTML = '<a href="#correctAccessionFormm" onclick="openCorrectPhone(this)"> Редактировать контакт </a>';
//    elementButton.appendChild(elementSpan);
//    cellButton.appendChild(elementButton);
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
    var comment = document.getElementById('commentFileIDAdd').value;
    document.getElementById('commentFileIDAdd').value = "";

    var elementIdFile = document.createElement("input");
    elementIdFile.type = "hidden";
    elementIdFile.name = "idFile";
    elementIdFile.value = "0";
    row.appendChild(elementIdFile);

    var elementFileName = document.createElement("input");
    elementFileName.type = "hidden";
    elementFileName.name = "fileName";
    fileName = fileName.replace("C:\\fakepath\\" , "");
    elementFileName.value = fileName;
    row.appendChild(elementFileName);


    var elementFileData = document.createElement("input");
    elementFileData.type = "hidden";
    elementFileData.name = "fileData";
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
    elementSpan.innerHTML = '<a href="#correctAccessionForm" onclick="openCorrectFile(this)"> Редактировать контакт </a>';
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
    var fileName = document.getElementById('fileNameIDCorrect').value;
    var commentFile = document.getElementById('commentFileIDCorrect').value;

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
//function deleteFile(){
//        var inputElements = document.getElementsByName('checkFile');
//    for(var i=inputElements.length - 1; inputElements[i]; i--){
//        if(inputElements[i].checked){
//            document.getElementById("fileTable").deleteRow(inputElements[i].parentNode.parentNode.rowIndex);
//        }
//    }

//}

document.getElementById('loadFotoButton').onclick = function(){
    var fileName = document.getElementById('loadFoto').value;
    if(fileName == ""){
        return false;
    }

}

var resultFoto;
function onFileSelected(event) {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();

//    var imgtag = document.getElementById("fotoPerson");
//    imgtag.title = selectedFile.name;

    reader.onload = function(event) {
//        imgtag.src = event.target.result;
        resultFoto = event.target.result;
    };

    reader.readAsDataURL(selectedFile);
}

document.getElementById('loadFotoButton').onclick = function(){
    if(resultFoto == "" || resultFoto == undefined){
        return false;
    }
    var imgtag = document.getElementById("fotoPerson");
    imgtag.src = resultFoto;
}