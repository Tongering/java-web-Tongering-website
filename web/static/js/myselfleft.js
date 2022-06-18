$().ready(function (){
    // myselfleft();
    refuserpho();
    copyright();
    showbar();
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
                if(data=='1'){
                    alert("上传成功");
                }
                else{
                    alert("上传失败，请检查浏览器或者图片大小");
                }
            }
        });
    }
    if (file) {
        reader.readAsDataURL(file);
    }

}

function copyright(){
    let content = "$$$$$$$$\\                                                $$\\                                     $$\\                     \n" +
        "\\__$$  __|                                               \\__|                                    $$ |                    \n" +
        "   $$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\ $$$$$$$\\   $$$$$$\\         $$$$$$\\  $$ | $$$$$$\\  $$$$$$$\\  \n" +
        "   $$ |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |$$  __$$\\ $$  __$$\\       $$  __$$\\ $$ | \\____$$\\ $$  __$$\\ \n" +
        "   $$ |$$ /  $$ |$$ |  $$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|$$ |$$ |  $$ |$$ /  $$ |      $$ /  $$ |$$ | $$$$$$$ |$$ |  $$ |\n" +
        "   $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$   ____|$$ |      $$ |$$ |  $$ |$$ |  $$ |      $$ |  $$ |$$ |$$  __$$ |$$ |  $$ |\n" +
        "   $$ |\\$$$$$$  |$$ |  $$ |\\$$$$$$$ |\\$$$$$$$\\ $$ |      $$ |$$ |  $$ |\\$$$$$$$ |      $$$$$$$  |$$ |\\$$$$$$$ |$$ |  $$ |\n" +
        "   \\__| \\______/ \\__|  \\__| \\____$$ | \\_______|\\__|      \\__|\\__|  \\__| \\____$$ |      $$  ____/ \\__| \\_______|\\__|  \\__|\n" +
        "                           $$\\   $$ |                                  $$\\   $$ |      $$ |                              \n" +
        "                           \\$$$$$$  |                                  \\$$$$$$  |      $$ |                              \n" +
        "                            \\______/                                    \\______/       \\__|                              ";
    let content1 = "                                                  $$\\                 $$\\                                                                   \n" +
        "                                                  $$ |                $$ |                                                                  \n" +
        "$$$$$$$$\\  $$$$$$$\\ $$$$$$\\$$$$\\        $$\\   $$\\ $$ |$$$$$$$$\\       $$$$$$$\\   $$$$$$$\\  $$$$$$$\\       $$$$$$$$\\  $$$$$$$\\ $$\\  $$\\  $$\\ \n" +
        "\\____$$  |$$  _____|$$  _$$  _$$\\       \\$$\\ $$  |$$ |\\____$$  |      $$  __$$\\ $$  _____|$$  _____|      \\____$$  |$$  _____|$$ | $$ | $$ |\n" +
        "  $$$$ _/ \\$$$$$$\\  $$ / $$ / $$ |       \\$$$$  / $$ |  $$$$ _/       $$ |  $$ |\\$$$$$$\\  $$ /              $$$$ _/ $$ /      $$ | $$ | $$ |\n" +
        " $$  _/    \\____$$\\ $$ | $$ | $$ |       $$  $$<  $$ | $$  _/         $$ |  $$ | \\____$$\\ $$ |             $$  _/   $$ |      $$ | $$ | $$ |\n" +
        "$$$$$$$$\\ $$$$$$$  |$$ | $$ | $$ |      $$  /\\$$\\ $$ |$$$$$$$$\\       $$ |  $$ |$$$$$$$  |\\$$$$$$$\\       $$$$$$$$\\ \\$$$$$$$\\ \\$$$$$\\$$$$  |\n" +
        "\\________|\\_______/ \\__| \\__| \\__|      \\__/  \\__|\\__|\\________|      \\__|  \\__|\\_______/  \\_______|      \\________| \\_______| \\_____\\____/ \n" +
        "                                                                                                                                            \n" +
        "                                                                                                                                            \n" +
        "                                                                                                                                            ";
    let content2 = "欢迎来到仝古计划";
    console.log(content);
    console.log(content1);
    console.log(content2);
}



