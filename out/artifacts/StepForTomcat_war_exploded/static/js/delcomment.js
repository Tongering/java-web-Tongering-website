function delcomment(obj){
    let commentid = obj.id;
    let invitationid = $("#invitationid").html();
    $.ajax({
        type: "post",//请求方式
        url: "/delcomment",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            commentid:commentid,
            invitationid:invitationid,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){
                alert("删除评论失败");
            }
            else{
                location.reload();
            }
        }
    });
}
