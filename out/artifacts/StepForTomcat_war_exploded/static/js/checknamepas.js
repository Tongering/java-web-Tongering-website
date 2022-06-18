function checkrepassword() {
    let upperCase= new RegExp('[A-Z]');
    let lowerCase= new RegExp('[a-z]');
    let numbers = new RegExp('[0-9]');
    let pas = $("#password").val();
    let rep = $("#repassword").val();
    if (rep == pas) {
        $("#resetpa").html("正确");
        $("#resetpa").attr("class","text-succes")
        $("#sub").attr('disabled', false);
    }
    else {
        $("#resetpa").html("密码不一致");
        $("#resetpa").attr("class", "text-danger");
        $("#sub").attr('disabled', true);
    }

    if (pas.match(lowerCase) && pas.match(numbers) ) {
        if (pas.length >= 8 && pas.length <= 20) {
            $("#pas-c").html("符合要求")
            $("#pas-c").attr("class", "text-succes");
            $("#sub").attr('disabled', false);
        }
        else {
            $("#pas-c").html("密码长度（8~20）");
            $("#pas-c").attr("class", "text-danger");
            $("#sub").attr('disabled', true);
        }
    }
    else {
        $("#pas-c").html("密码应有字母和数字");
        $("#pas-c").attr("class", "text-danger");
        $("#sub").attr('disabled', true);
    }
}

function checkusername() {
    var check = false;
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
                $("#user-c").attr("class", "text-danger");
                $("#user-c").html(" 不可用");
                $("#sub").attr('disabled', true);
            } else {
                if($("#username").val().toString().length>=3&&$("#username").val().toString().length<=10){
                    $("#user-c").attr("class", "text-succes");
                    $("#user-c").html(" 可用");
                    $("#sub").attr('disabled', false);
                }
                else {
                    $("#user-c").attr("class", "text-danger");
                    $("#user-c").html(" 用户名（3~10）");
                    $("#sub").attr('disabled', true);
                }

                check = true;
            }

        }
    });
    return check;
}