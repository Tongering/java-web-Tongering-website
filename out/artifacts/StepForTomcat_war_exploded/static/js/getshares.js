function getlikes(){
    $.ajax({
        type: "get",//请求方式
        url: "/shareservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html(),
            sharetype:"getlikes",
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='1') {
                $("#postlikes").css("color","rgb(0, 161, 214)");
            }
            else{
                $("#postlikes").css("color","rgb(208, 208, 208)");
            }
        }
    });
}

function getfavorite(){
    $.ajax({
        type: "get",//请求方式
        url: "/shareservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html(),
            sharetype:"getfavorite",
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data==1) {
                $("#postfavorite").css("color","rgb(0, 161, 214)");
            }
            else{
                $("#postfavorite").css("color","rgb(208, 208, 208)");
            }
        }
    });
}