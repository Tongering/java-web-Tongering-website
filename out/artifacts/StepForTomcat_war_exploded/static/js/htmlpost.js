function showthepost(){
    if($("#postinvitation").css("display")=='none'){
        $("#postinvitation").show("normal");
    }
    else{
        $("#postinvitation").hide("normal");
    }

}

function showtheposttype(){
    if($("#types").val()=='about'){
        jQuery(".posttype").show();
    }
}
