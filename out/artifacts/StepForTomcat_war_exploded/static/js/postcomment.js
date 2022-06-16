function postcomments(){
    let content = $("#commentcontent").val();
    let invitationid = $("#invitationid").html();


    $.ajax({
        type: "post",//请求方式
        url: "/postcommentservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            commentcontent:content,
            invitationid:invitationid,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data == '0'){
                $("#worryuser").html("请登录");
            }
            else{
                location.reload();
            }
        }
    });
}

function commentdrawing(){
    // editormd.markdownToHTML("comments", {
    //     htmlDecode: "style,script,iframe", //可以过滤标签解码
    //     emoji: true,
    //     taskList:true,
    //     tex: true,               // 默认不解析
    //     flowChart:true,         // 默认不解析
    //     sequenceDiagram:true,  // 默认不解析
    // });
}