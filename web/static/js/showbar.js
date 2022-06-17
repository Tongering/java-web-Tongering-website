function showmodifyprofile(){
    if($("#modifyprofile").css("display")=='none'){
        $("#modifyprofile").show("normal");
    }
    else{
        $("#modifyprofile").hide("normal");
    }
}

function showmodifypassword(){
    if($("#modifypassword").css("display")=='none'){
        $("#modifypassword").show("normal");
    }
    else{
        $("#modifypassword").hide("normal");
    }
}

function showmodifyphoto(){
    if($("#modifyphoto").css("display")=='none'){
        $("#modifyphoto").show("normal");
    }
    else{
        $("#modifyphoto").hide("normal");
    }
}

function showsearchuser(){
    if($("#searchuser").css("display")=='none'){
        $("#searchuser").show("normal");
    }
    else{
        $("#searchuser").hide("normal");
    }
}

function showmythumb(){
    if($("#mythumb").css("display")=='none'){
        $("#mythumb").show("normal");
    }
    else{
        $("#mythumb").hide("normal");
    }
}

function showmyfavorite(){
    if($("#myfavorite").css("display")=='none'){
        $("#myfavorite").show("normal");
    }
    else{
        $("#myfavorite").hide("normal");
    }
}

function showbar(){
    let name = $("#name-myself").html();
    $.ajax({
        type: "post",//请求方式
        url: "/isme",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            name:name,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){
                $("#mynavbar").hide();
            }
            else if(data=='1'){
                $("#mynavbar").show("normal");
            }
        }
    });
}
