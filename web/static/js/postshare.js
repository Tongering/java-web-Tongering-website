function likes(){
    console.log("likes");
    console.log($("#invitationid").html());
    console.log($("#postlikes").css("color")=="rgb(208, 208, 208)");
    if($("#postlikes").css("color")=="rgb(208, 208, 208)"){//未点击
        $.ajax({
            type: "post",//请求方式
            url: "/shareservlet",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                invitationid:$("#invitationid").html(),
                sharetype:"addlike",
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
                console.log(data);
                if(data!='0'){
                    $("#postlikes").css("color","rgb(0, 161, 214)");
                }
                else{
                    $("#worryshares").html("请登录");
                }
            }
        });
    }
    else{
        $.ajax({
            type: "post",//请求方式
            url: "/shareservlet",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                invitationid:$("#invitationid").html(),
                sharetype:"dellike",
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
                if(data!='0'){
                    $("#postlikes").css("color","rgb(208, 208, 208)");
                }
                else{
                    $("#worryshares").val("请登录");
                }
            }
        });
    }
}

function favorite(){
    console.log("favorite");
    console.log($("#postfavorite").css("color")=="rgb(208, 208, 208)");
    if($("#postfavorite").css("color")=="rgb(208, 208, 208)"){
        $.ajax({
            type: "post",//请求方式
            url: "/shareservlet",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                invitationid:$("#invitationid").html(),
                sharetype:"addfavorite",
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
                if(data!='0'){
                    $("#postfavorite").css("color","rgb(0, 161, 214)");
                }
                else{
                    $("#worryshares").val("请登录");
                }
            }
        });

    }
    else{
        $.ajax({
            type: "post",//请求方式
            url: "/shareservlet",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                invitationid:$("#invitationid").html(),
                sharetype:"delfavorite",
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
                if(data!='0'){
                    $("#postfavorite").css("color","rgb(208, 208, 208)");
                }
                else{
                    $("#worryshares").val("请登录");
                }
            }
        });
    }
}
