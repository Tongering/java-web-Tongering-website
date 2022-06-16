function refuserpho() {
    var check = false;
    $.ajax({
        type: "post",//请求方式
        url: "/disposename",//发送请求地址
        async: false,
        dataType: "html",
        data: null,
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if (data == '0') {
                $("#pho").html("请登录");
                $("#resg").show();
                $("#logi").show();
                $("#quit").hide();
                $("#pho").attr('href','/login.jsp');
            } else {
                let username = data.split("/")[0];
                let userid = data.split("/")[1];
                $("#pho").html(username);
                $("#resg").hide();
                $("#logi").hide();
                $("#quit").show();
                $("#pho").attr('href','/space/' + userid);
                check = true;
            }
        }
    });
    return check;

}

