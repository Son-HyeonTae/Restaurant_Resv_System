function validate(){
    var id=document.getElementById("id").value;
    var password=document.getElementById("password").value;

    if(id=="user"&&password=="1234"){
        alert("회원님 환영합니다.");
    }
    else if(id=="admin"&&password=="123456"){
        alert("관리자님 환영합니다.");
    }
    else{
        alert("입력하신 정보가 맞지 않습니다.");
    }
}

