/**
 * Created by Xiaoke Zhang on 2/24/2016.
 */

function $(str) {
    return (document.getElementById(str));
}

(function () {
    alert(10000);
    $("#loginBtn").bind("click", function () {
        alert(11);
    });

    //
    //$("#loginBtn").bind("click",function(){
    //    alert(11);
    //});
//    $("#loginBtn").bind("click",function(){
//        console.log("click");
//        var username =$("#username").val();
//        var password=$("#password").val();
//        if(username.length==0||password==0){
//            $("login_tip").innerHTML='请填写用户名和密码';
////                        alert("请填写用户名和密码");
//
//        }else if(userpwd.length<8){
//            alert("密码不可小于8位");
//
//        }else{
//            var send={"username":username,"userpwd":userpwd};
//            alert(send);
////            var data_send=JSON.stringify(send);
////            login(data_send);
//        }
//
//
//    }
//    );
}());