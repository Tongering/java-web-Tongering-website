function modifyusernamecheck(){
    let username = $("[name='username']").val();
    if(username!=""){
        $.ajax({
            type: "post",//请求方式
            url: "/Checkname",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                username:$("#username").val()
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
                if (data == '0') {
                    $("#worryusername").attr("class", "text-danger");
                    $("#worryusername").html(" 不可用");
                    $("#sub").attr('disabled', true);
                } else {
                    if($("#username").val().toString().length>=3&&$("#username").val().toString().length<=10){
                        $("#worryusername").attr("class", "text-succes");
                        $("#worryusername").html(" 可用");
                        $("#sub").attr('disabled', false);
                    }
                    else {
                        $("#worryusername").attr("class", "text-danger");
                        $("#worryusername").html(" 用户名（3~10）");
                        $("#sub").attr('disabled', true);
                    }
                    check = true;
                }

            }
        });
    }
}

function postmodifyprofile(){
    let username = $("[name='username']").val();
    let university = $("[name='university']").val();
    let subject = $("[name='subject']").val();
    let better = $("[name='better']").val();
    let likes = $("[name='likes']").val();

    if(username==""){
        username = $("[name='username']").attr('placeholder');
    }
    if(university==""){
        university = $("[name='university']").attr('placeholder');
    }
    if(subject==""){
        subject = $("[name='subject']").attr('placeholder');
    }
    if(better==""){
        better = $("[name='better']").attr('placeholder');
    }
    if(likes==""){
        likes = $("[name='likes']").attr('placeholder');
    }
    let name = $("#name-myself").html();

    $.ajax({
        type: "post",//请求方式
        url: "/modifyprofile",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            name:name,
            username:username,
            university:university,
            subject:subject,
            better:better,
            likes:likes,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){//查无此人
                alert("不能修改他人信息");
            }
            else{
                location.reload();
            }
        }
    });
}

function postmodifypassword(){
    let oripa = $("#oripassword").val();
    let newpa = $("#newpassword").val();
    let renew = $("#renewpassword").val();

    if(newpa===renew){
        $.ajax({
            type: "post",//请求方式
            url: "/modifypassword",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                oripa:oripa,
                newpa:newpa,
                renew:renew,
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
                if(data=='1'){
                    alert("修改成功");
                }
                else if(data=='0'){
                    alert("重复密码错误");
                }
                else if(data=='2'){
                    alert("原密码错误");
                }
                else if(data=='3'){
                    alert("密码应包含字母和数字，且在8~20位");
                }
            }
        });
    }
    else{
        $("#worrypassword").attr("class", "text-danger");
        $("#worrypassword").html(" 两次输入密码不一致");
    }
}

function searchuser(){
    let searchusername = $("#searchname").val();
    $.ajax({
        type: "post",//请求方式
        url: "/searchusername",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            searchusername:searchusername,
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data=='0'){
                $("#searchusername").html("未查到此用户");
            }
            else{
                let username = data.split("(")[0];
                let userid = data.split("(")[1];
                let userphoto = data.split("(")[2];
                $("#searchuserphoto").attr("src","data:image/jpg;base64," + userphoto);
                $("#searchusername").html(username);
                $("#searchusername").attr("href","/space/" + userid);
            }
        }
    });
}