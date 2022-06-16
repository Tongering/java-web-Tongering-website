function qq(){
    $.ajax({
        type: "post",//请求方式
        url: "/exit",//发送请求地址
        async: false,
        dataType: "html",
        data: null,
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
        }
    });

}