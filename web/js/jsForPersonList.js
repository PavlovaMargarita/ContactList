document.getElementById('sendEmailPopup').onclick = function(){
    var checkPerson = document.getElementsByName('checkPerson');
    var personEmail = document.getElementsByName('email');
    var personID = document.getElementsByName('id');
    var fullEmail = "";
    var fullID = "";
    for(var i = 0; i < checkPerson.length; i++){
        if( checkPerson[i].checked){
            fullEmail = fullEmail.concat(personEmail[i].value);
            fullEmail = fullEmail.concat('; ');
            fullID = fullID.concat(personID[i].value);
            fullID = fullID.concat('; ');
        }
    }
    document.getElementById('emails').value = fullEmail;
    document.getElementById('emails').readOnly = true;
    document.getElementById('personsIDForEmail').value= fullID;

}
var idTemplate;
function changeTemplate(obj){
    var value = obj.value;
    var array = value.split("/");
    idTemplate = array[0];
    if(array[1] == undefined){
        document.getElementById("message").value = "";
    } else{
        document.getElementById("message").value = array[1];
        document.getElementById('message').disabled = true;
    }
}

document.getElementById('closeID').onclick = function(){
    document.getElementById('template').getElementsByTagName('option')[0].selected = 'selected'
    document.getElementById('message').value = "";
    document.getElementById('message').disabled = false;
}

document.getElementById('popupSendEmailButton').onclick = function(){
    if(document.getElementById('emails').value != "") {
        document.getElementById('send').click();
        return true;
    }
    else {
        return false;
    }
}