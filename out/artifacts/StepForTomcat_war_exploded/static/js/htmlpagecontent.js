function getcontent(){


    $.ajax({
        type: "post",//请求方式
        url: "/contentservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html()
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            $("#modifycontent").val(data);
            $("#appendTest").val(data);
            editormd.markdownToHTML("testEditorMdview", {
                htmlDecode: "style,script,iframe", //可以过滤标签解码
                emoji: true,
                taskList:true,
                tex: true,               // 默认不解析
                flowChart:true,         // 默认不解析
                sequenceDiagram:true,  // 默认不解析
            });
        }
    });
}