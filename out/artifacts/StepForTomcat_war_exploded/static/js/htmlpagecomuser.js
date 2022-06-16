function commentuser(){
    $.ajax({
        type: "post",//请求方式
        url: "/disposename",//发送请求地址
        async: false,
        dataType: "html",
        data: null,
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data != 0){
                $("#username").html(data);
                $("#username").show();
                $("#noneuser").hide();
            }
        }
    });
}

function commentphoto(){
    $.ajax({
        type: "post",//请求方式
        url: "/photo",//发送请求地址
        async: false,
        dataType: "html",
        data: null,
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {

            let json = eval("(" + data + ")");
            let imgbase = json.imgbase;
            $("#userphoto").attr("src", "data:image/jpg;base64," + imgbase);
            $("#userphoto").show();
            $("#noneuser").hide();

        }
    });
}