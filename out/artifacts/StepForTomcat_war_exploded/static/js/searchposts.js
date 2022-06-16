function searchposts(){
    $.ajax({
        type: "post",//请求方式
        url: "/eachpostservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {

        }
    });
}

function turn(obj){
    // let id = obj.id;
    // let url = document.URL;
    // let urlbase = url.split("/")[0];
    // let posttype = $("#posttype").val();
    // let newurl = urlbase + posttype + '/content/' + id;
    //
    //  $(location).attr("href",newurl);
}