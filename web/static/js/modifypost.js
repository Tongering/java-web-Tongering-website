function modifyinvitation(){
    if($("#divmodify").css("display")=='none'){
        $("#divmodify").show("normal");
    }
    else{
        $("#divmodify").hide("normal");
    }
}

function modifypost(){//提交修改内容
    let modcontent = $("#modifycontent").val();
    let invitationid = $("#invitationid").html();
    $.ajax({
        type: "post",//请求方式
        url: "/modifypost",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            modcontent:modcontent,
            invitationid:invitationid,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){//该贴不属于当前用户
                alert("修改失败");
            }
            else{
                location.reload();
            }

        }
    });
}

function modifypen(){//给予修改权限
    let invitationid = $("#invitationid").html();
    $.ajax({
        type: "get",//请求方式
        url: "/modifypost",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:invitationid,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){//该贴不属于当前用户
                $("#modify").hide("normal");
            }
            else{
                $("#modify").show("normal");
            }
        }
    });
}

function delinvitation(){
    if($("#divdel").css("display")=='none'){
        $("#divdel").show("normal");
    }
    else{
        $("#divdel").hide("normal");
    }
}

function delinvitationtrue(){
    let invitationid = $("#invitationid").html();
    $.ajax({
        type: "post",//请求方式
        url: "/delpost",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:invitationid,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){
                alert("删除失败");
            }
            else{
                $(location).attr("href","/about");
            }
        }
    });
}

