$().ready(function (){
  refuserpho();
})

function admindelcomment(obj){
  let commentid = obj.id;
  $.ajax({
    type: "post",//请求方式
    url: "/admindelcomment",//发送请求地址
    async: false,
    dataType: "html",
    data: {
      commentid:commentid,
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

function admindeluser(obj){
  let userid = obj.id;
  $.ajax({
    type: "post",//请求方式
    url: "/admindeluser",//发送请求地址
    async: false,
    dataType: "html",
    data: {
      userid:userid,
    },
    //请求成功后的回调函数有两个参数
    success: function (data, textStatus) {
      if(data=='0'){
        alert("删除用户失败");
      }
      else{
        location.reload();
      }
    }
  });
}

function admindelinvitation(obj){
  let invitationid = obj.id;
  $.ajax({
    type: "post",//请求方式
    url: "/admindelinvitation",//发送请求地址
    async: false,
    dataType: "html",
    data: {
      invitationid:invitationid,
    },
    //请求成功后的回调函数有两个参数
    success: function (data, textStatus) {
      if(data=='0'){
        alert("删除帖子失败");
      }
      else{
        location.reload();
      }
    }
  });
}

function adminadd(obj){
  let addid = obj.id;
  $.ajax({
    type: "post",//请求方式
    url: "/adminadd",//发送请求地址
    async: false,
    dataType: "html",
    data: {
      addid:addid,
    },
    //请求成功后的回调函数有两个参数
    success: function (data, textStatus) {
      if(data=='0'){
        alert("添加管理员失败");
      }
      else{
        location.reload();
      }
    }
  });
}

function admindel(obj){
  let delid = obj.id;
  $.ajax({
    type: "post",//请求方式
    url: "/admindel",//发送请求地址
    async: false,
    dataType: "html",
    data: {
      delid:delid,
    },
    //请求成功后的回调函数有两个参数
    success: function (data, textStatus) {
      if(data=='0'){
        alert("删除管理员失败");
      }
      else{
        location.reload();
      }
    }
  });
}