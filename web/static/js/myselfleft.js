$().ready(function (){
    myselfleft();
    refuserpho();
})

function myselfleft() {
    var check = false;
    $.ajax({
        type: "post",//请求方式
        url: "/myselfleft",//发送请求地址
        async: false,
        dataType: "html",
        data: {
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            let json = eval("(" + data + ")");
            let name = json.username;
            let university = json.university;
            let subject = json.subject;
            let birth = json.birth;
            let better = json.better;
            let likes = json.likes;
            let imgbase = json.imgbase;


            $("#caricature").attr("src", "data:image/jpg;base64," + imgbase);
            $("#name-myself").html(name);
            $("#birth-myself").html(birth);
            $("#universitydetail-myself").html(university);
            $("#subjectdetail-myself").html(subject);
            $("#better-pan").html(better);
            $("#like-pan").html(likes);

        }
    });

    $.ajax({
        type: "post",//请求方式
        url: "/photo",//发送请求地址
        async: false,
        dataType: "html",
        data: {
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            let json = eval("(" + data + ")");
            let imgbase = json.imgbase;
            $("#caricature").attr("src", "data:image/jpg;base64," + imgbase);
        }
    });
    return check;
}

function toBase64(){
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.onloadend = function () {
        nonimgbase = reader.result;
        imgbase = nonimgbase.split(",")[1];
        $.ajax({
            type: "post",//请求方式
            url: "/uploadimg",//发送请求地址
            async: false,
            dataType: "html",
            data: {
                imgbase64:imgbase
            },
            //请求成功后的回调函数有两个参数
            success: function (data, textStatus) {
            }
        });
    }
    if (file) {
        reader.readAsDataURL(file);
    }

}


