function checkCode() {
    var check = false;
    $.ajax({
        type: "post",//请求方式
        url: "/checkCode",//发送请求地址
        async: false,
        dataType: "html",
        data: {//发送数据
            validate: $('[name=validate]').val()
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if (data == '0') {
                $("#checkCode").html("验证码错误！");
            } else {
                $("#checkCode").html("正确！");
                check = true;
            }
        }
    });
    return check;
}
