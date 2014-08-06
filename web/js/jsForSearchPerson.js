document.getElementById('age').onkeyup = function(){
    var val = document.getElementById('age').value;
    if(isInt(val)){
        document.getElementById('age').style.borderColor = "#ccc";
    }
    else{
        document.getElementById('age').style.borderColor = "red";
    }
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
function isInt(obj){
    var reg = /^\d+$/;
    if(reg.test(obj)){
        return true;
    }
    else {return false};
}