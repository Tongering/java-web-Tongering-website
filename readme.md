# 仝古计划

[TOC]

## 项目依赖

1. `jakarta-taglibs-standard-1.1.2`
2. `jquery-3.6.0.min`
3. `bootstrap-3.4.1-dist`
4. `editor.md-master`
5. `JDK1.8`
6. `Tomcat8.5.78`



## 数据库构建

数据库采用`云端部署`的方式构建，地址[远程数据库地址](118.195.240.125)，外网IP地址为120.0.0.0/255.0.0.0的用户可以访问。数据库分为`八个部分`：评论 comments、示例图片 examphoto、个人信息id_myself、用户照片 id_photo、用户登录信息 id_user_password、帖子 invitation、分享 shares、可爱的语言 wellknowsaying。

#### 评论 comments

| id         | inviationid  | commentid        | content  | commenttime |
| ---------- | ------------ | ---------------- | -------- | ----------- |
| 评论用户id | 被评论帖子id | 评论id（自增长） | 评论内容 | 评论时间    |

#### 示例图片 examphoto

| photo id | photobase64    |
| -------- | -------------- |
| 图片id   | 图片base64编码 |

#### 个人信息 id_myself

| id     | university | subject  | birth        | better   | likes    |
| ------ | ---------- | -------- | ------------ | -------- | -------- |
| 用户id | 用户大学   | 用户专业 | 用户注册时间 | 用户特长 | 用户爱好 |

#### 用户照片 id_photo

| id     | user_photo |
| ------ | ---------- |
| 用户id | 用户照片   |

#### 用户登录信息 id_user_password

| id     | user   | password |
| ------ | ------ | -------- |
| 用户id | 用户名 | 用户密码 |

#### 帖子 invitation

| id         | invitationid | typeinvitation | title    | content  | posttime     | likes      | favorite   | browse     |
| ---------- | ------------ | -------------- | -------- | -------- | ------------ | ---------- | ---------- | ---------- |
| 发帖用户id | 帖子id       | 帖子类型       | 帖子标题 | 帖子内容 | 帖子发表时间 | 帖子点赞数 | 帖子收藏数 | 帖子浏览数 |

#### 分享 shares

| shareid | id             | invitationid | likes                         | favorite                      | browse                        |
| ------- | -------------- | ------------ | ----------------------------- | ----------------------------- | ----------------------------- |
| 分享id  | 操作分享用户id | 被分享帖子id | 该用户对该帖子的点赞数（0/1） | 该用户对该帖子的收藏数（0/1） | 该用户对该帖子的浏览数（+00） |

#### 可爱的语言 wellknowsaying

| sayingid | saying   |
| -------- | -------- |
| 话id     | 所说的话 |



## 首页

 ![首页.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_eb0a3c09ee-首页.png) 

### 总览

输入118.195.240.125时访问的是index.jsp页面

``` jsp
<meta http-equiv="refresh" content="0;url=/about"> 
```
```xml
<servlet>
        <servlet-name>eachpostservlet</servlet-name>
        <servlet-class>com.postapost.servlet.EachPostServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>eachpostservlet</servlet-name>
        <url-pattern>/html</url-pattern>
        <url-pattern>/css</url-pattern>
        <url-pattern>/js</url-pattern>
        <url-pattern>/c</url-pattern>
        <url-pattern>/python</url-pattern>
        <url-pattern>/java</url-pattern>
        <url-pattern>/project</url-pattern>
        <url-pattern>/about</url-pattern><!--    跳转至此-->
    </servlet-mapping>
```
此时请求EachPostServlet
```java
		String servleturl = req.getServletPath();
        String htmltype = servleturl.split("/")[1];//获取请求的页面类型

        req.setAttribute("htmltype",htmltype);
```

```java
		String sqlall = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
                "from invitation\n" +
                "inner join id_photo\n" +
                "on invitation.id = id_photo.id\n" +
                "inner join id_user_password\n" +
                "on invitation.id = id_user_password.id\n" +
                "order by invitation.posttime desc";//查询所有帖子

        EachPostQuery EachPostQuery = new EachPostQuery();//查询帖子操作
		List posts;

        if(htmltype.equals("about")){
            posts = EachPostQuery.queryeachposts(sqlall);//执行这条语句
        }
        else {
            posts = EachPostQuery.queryeachposts(sqlappoint,htmltype);
        }
		req.setAttribute("posts",posts);
```

```java
InputStream is = EachPostServlet.class.getClassLoader().getResourceAsStream("postrandom.properties");
        Properties pros = new Properties();
        pros.load(is);//获取输入流

        Random rand = new Random();
        int min = Integer.parseInt(pros.getProperty("postmin")), max = Integer.parseInt(pros.getProperty("postmax"));
        int sayingid = rand.nextInt(max - min + 1) + min;//获取一个随机数

        String sqlrandsaying = "select saying\n" +
                "from wellknowsaying\n" +
                "where sayingid = ?";

        QuerySaying querySaying = new QuerySaying();
        SayingInstantiation sayingInstantiation = querySaying.querysaying(sqlrandsaying,sayingid);//通过随机数获取随机帖子内容

        req.setAttribute("wellsay",sayingInstantiation.getSaying());
```

```java
req.getRequestDispatcher("Html.jsp").forward(req,resp);
```

```proper
postmin=1
postmax=8//postrandom.properties context
```

EachPostServlet传递至前端内容参数：

```java
req.setAttribute("htmltype",htmltype);//传送帖子类型
req.setAttribute("posts",posts);//传送查询帖子数据
req.setAttribute("wellsay",sayingInstantiation.getSaying());//传送名言
```

请求完成后跳转至`Html.jsp`内容模块

```jsp
<%@include file="component/navbar.jsp"%>
<%@include file="component/htmlbase.jsp"%>//主要内容
<%@include file="component/bottom.jsp"%>
<div class="rock fly" onclick="showrocky()">//右下角小鸭子
```

接下来由`htmlbase.jsp`处理后端传来的数据

### 标题框展现

显示`htmltype`以及`wellsay`两个数据

```jsp
 <div class="panel-body" style="text-align: center;"><h3 id="titletype" style="margin-top: 10px;">${htmltype}</h3><h5 style="margin-top: 10px;">${wellsay}</h5></div>
```

通过`jstl`中`c:forEach`来逐条显示帖子内容

```jsp
<c:forEach items="${posts}" var="i" varStatus="status">
        <div class="row"style="">
            <div class="col-xs-4" style="float: none;display: block;margin-left: auto;margin-right: auto">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <img class="img-fluid borderpho" src="data:image/jpg;base64,${i.user_photo}" style="max-width: 35px;max-height: 20px">
                        <a id="${i.id}" href="/space/${i.id}" target="_blank">${i.user}</a>
                        <a class="posttype" style="display: none;float: right;margin-right: 10px" href="/${i.typeinvitation}">${i.typeinvitation}</a>
                    </div>
                    <div class="panel-body" style="text-align: center;">
                        <a id="${i.invitationid}"onclick="turn(this)" style="user-select:none;" href="/${i.typeinvitation}/content/${i.invitationid}">${i.title}</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
```

此外讲下`EachPostServlet`中`帖子查询语句`

```java
EachPostQuery EachPostQuery = new EachPostQuery();//查询帖子操作
```

`EachPostQuery`中相关操作

```java
getconnection = JDBCutils.getconnection();//建立连接
JDBCutils.closeResource(getconnection,preparedStatement,resultSet);//关闭连接
```

对应自建`JDBCutils`类

```java
public static Connection getconnection() throws Exception {//建立连接
        InputStream is = JDBCutils.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //加载驱动
        Class.forName(driverClass);

        //获取链接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
```

```java
public static void closeResource(Connection connection, PreparedStatement preparedStatement){//两个参数的关闭连接
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
```

```java
    public static void closeResource(Connection connection, PreparedStatement preparedStatement,ResultSet resultSet){//三个参数的关闭连接
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
```

`EachPostQuery`中处理多个参数的形式

```java
queryeachposts(String sql, Object... args)//方法
    
preparedStatement = getconnection.prepareStatement(sql);
for (int i = 0;i <args.length;i++){
    preparedStatement.setObject(i+1,args[i]);
}
resultSet = preparedStatement.executeQuery();//预处理
```

针对不止一个结果的内容用`LIST`接收

```java
List posts = new ArrayList();
if(resultSet != null){
    while(resultSet.next()){
        EachPostInstantiation EachPostInstantiation = new EachPostInstantiation();

        EachPostInstantiation.setId(resultSet.getInt(1));
        EachPostInstantiation.setUser_photo(resultSet.getString(2));
        EachPostInstantiation.setUser(resultSet.getString(3));
        EachPostInstantiation.setTitle(resultSet.getString(4));
        EachPostInstantiation.setInvitationid(resultSet.getInt(5));
        EachPostInstantiation.setPosttime(resultSet.getTimestamp(6));
        EachPostInstantiation.setTypeinvitation(resultSet.getString(7));//对应查询结果放置对应对象内
        posts.add(EachPostInstantiation);
    }
}
```

### 发帖功能

回到开头，主页内容还有`发帖功能`，其`前端展示代码`为：

```jsp
<div class="row" style="text-align: center;">
        <button type="button" class="btn btn-primary">
            <span class="glyphicon glyphicon-edit" aria-hidden="true" onclick="showthepost()" id="writepost"> 我也来写一篇</span>
        </button>
    </div>
```
`我也来写一篇`的触发事件为`showthepost()`，其方法为：

```js
function showthepost(){
    if($("#postinvitation").css("display")=='none'){
        $("#postinvitation").show("normal");
    }
    else{
        $("#postinvitation").hide("normal");
    }
}
```

发帖框：

```jsp
<div class="row" id="postinvitation" style="display:none ;">
        <div class="col-xs-2"></div>
        <div class="col-xs-8">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-edit" aria-hidden="true"> ${htmltype}</span>
                </div>
                <div class="panel-body">
                    <form action="postinvitation" method="post" target="myIframe">
                        <div class="form-group">
                            <label for="types">类型</label>
                            <input type="text" class="form-control" id="types" name="types" value="${htmltype}" readonly="readonly" required="required">
                        </div>
                        <div class="form-group">
                            <label for="title">标题</label>
                            <input type="text" class="form-control" id="title" name="title" required="required">
                        </div>
                        <div class="form-group">
                            <label for="content" >正文</label>
                            <textarea class="form-control" rows="3" id="content" style="resize: vertical;min-height: 140px" name="content"></textarea>
                        </div>
                        <button type="submit" class="btn btn-success">发帖</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-xs-2"></div>
    </div>
```

发帖框中提交至`postinvitation`，PostInvitationServlet.java

获取前端相关信息

```java
Object id = req.getSession().getAttribute("id_user".toString());
String title = req.getParameter("title");
String content = req.getParameter("content");
String types = req.getParameter("types");
```

获取发帖时间

```java
Date date = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
String dateTime = df.format(date); // Formats a Date into a date/time string.
```

执行SQL语句

```java
String sql = "insert into invitation (id,title,content,posttime,typeinvitation) values (?,?,?,?,?)";
updata updata = new updata();
updata.updateutil(sql,id,title,content,dateTime,types);
```

判断用户是否登录，登录才可以发帖

```java
if(id!=null){
    updata updata = new updata();
    updata.updateutil(sql,id,title,content,dateTime,types);
    resp.addHeader("refresh","0;URL=/" + types);
    pw.write("<script language='javascript'>alert('发帖成功')</script>");
}
else{
    pw.write("<script language='javascript'>alert('请登录')</script>");
    resp.sendRedirect("/login.jsp");
}
```



## 导航条 navbar

导航条是一个单独的模块，在任何需要导航条的部分添加以下代码即可

```jsp
<%@include file="/component/navbar.jsp"%>
```

其采用`bootstrap`导航条布局

其特点在于导航条`右侧用户相关功能`

```jsp
<ul class="nav navbar-nav navbar-right">
    <li><a href="/Register.jsp" id="resg">注册</a></li>
    <li ><a href="/login.jsp" id="logi">登录</a></li>
    <li ><a href="" id="pho"></a></li>
    <li ><a href="/login.jsp" onclick="qq()" id="quit">退出</a></li>
</ul>
```

### 登录检测机制

在使用`navbar.jsp`页面里，加入以下语句，可自动触发检测是否登录

```js
$().ready(function (){
    refuserpho();
})
```

```js
function refuserpho() {
    var check = false;
    $.ajax({
        type: "post",//请求方式
        url: "/disposename",//发送请求地址
        async: false,
        dataType: "html",
        data: null,
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if (data == '0') {
                $("#pho").html("请登录");
                $("#resg").show();
                $("#logi").show();
                $("#quit").hide();
                $("#pho").attr('href','/login.jsp');
            } else {
                let username = data.split("/")[0];
                let userid = data.split("/")[1];
                $("#pho").html(username);
                $("#resg").hide();
                $("#logi").hide();
                $("#quit").show();
                $("#pho").attr('href','/space/' + userid);
                check = true;
            }
        }
    });
    return check;
}
```

该ajax触发`DisposeNameServlet`

```JAVA
Object id_user = req.getSession().getAttribute("id_user".toString());//获取用户id
```

通过判断用户是否登录，来选择是否需要查询语句，可以一定程度上减少资源的消耗

```java
if(id_user != null){
    String sql = "select user from id_user_password where id = ?";

    UserProfileQuery userProfileQuery = new UserProfileQuery();
    UserProfileInstantiation getusername = userProfileQuery.queryutil(sql, id_user);

    String username = getusername.getUser();
    out.print(username + "/" + id_user);
}
else{
    out.print(0);
}
```

其查询语句采用`UserProfileQuery`，UserProfileQuery.java为

```java
if(resultSet.next()){
    UserProfileInstantiation userProfileInstantiation = new UserProfileInstantiation();
    for(int i=0;i<columnCount;i++){
        Object columnvalue = resultSet.getObject(i + 1);

        String columnName = resultSetMetaData.getColumnName(i + 1);

        Field field = UserProfileInstantiation.class.getDeclaredField(columnName);
        field.setAccessible(true);
        field.set(userProfileInstantiation,columnvalue);

    }
    return userProfileInstantiation;
}
```

其采用`UserProfileInstantiation`的类，其实例化对象为

```java
private int id;
private String user;
private String password;
```

### 退出检测机制

当用户点击退出按钮时，触发`qq()`事件

```js
function qq(){
    $.ajax({
        type: "post",//请求方式
        url: "/exit",//发送请求地址
        async: false,
        dataType: "html",
        data: null,
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
        }
    });
}
```

其触发`exit`的`ExitServlet`

```java
req.getSession().removeAttribute("id_user");
req.getSession().removeAttribute("name_user");
```



## 页底标注

其同样是个独立模块，在某个页面需要采用的时候可添加如下代码

```jsp
<%@include file="/component/bottom.jsp"%>
```

其代码极其简单且静态，不与后端进行交互

```jsp
<div class="container-fluid" style="padding-top: 40px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="jumbotron">
                <div class="container">
                    <center>
                        <h4>Tongering Copyright© 2022 All Rights Reserved. </h4>
                        <a href="#">仝古计划</a>
                        <a> | </a>
                        <a href="http://beian.miit.gov.cn">粤ICP备2021119183号</a>
                        <a> | </a>
                        <img src="/static/img/beian.png">
                        <a href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=44030602006113">粤公网安备 44030602006113号</a>
                    </center>

                </div>
            </div>
        </div>
    </div>
</div>
```



## 右下角的小鸭子

其涉及一个`div`标签

```jsp
<div class="rock fly" onclick="showrocky()">
```

以及需要引入`js`与`css`标签

```js
function showrocky(){
    $("html,body").animate({
        scrollTop: 0
    }, 500);
}
```

```css
.rock {
    position: fixed;
    right: 0;
    bottom: 0;
    width: 150px;
    height: 174px;
    background-image: url("/static/img/roky-none.png");
    background-size: 100px 100px;
    background-repeat: no-repeat;
    overflow: hidden;
}

.rock:hover {
    background-image: url("/static/img/roky-active.gif");
}
```



## 登录页面

![登录页面.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_36b6eafeee-登录页面.png) 

### 登录按钮

前端代码

```jsp
<div class="col-sm-offset-4 col-sm-2">
    <button type="submit"  class="btn btn-success" id="sub">登入 Submit</button>
</div>
```

其触发后端`login`的`LoginServlet.java`

```java
String username = req.getParameter("username");
String password = req.getParameter("password");
String code = req.getParameter("validate");
String sql = "select id,user,password from id_user_password where user = ? and password = ?";
```

执行查询操作

```java
UserProfileQuery userProfileQuery = new UserProfileQuery();
UserProfileInstantiation checknamepas = userProfileQuery.queryutil(sql, username, password);
```

检测用户是否存在

```java
int test = -1;
if(checknamepas !=null) test = checknamepas.getId();
else test = -1;
```

获取用户验证码是否正确

```java
boolean codet = code.equalsIgnoreCase(req.getSession().getAttribute("code").toString());
```

判断机制

```java
if((test!=-1)&&codet){
    req.getSession().setAttribute("id_user", test);
    resp.sendRedirect("/space" + "/" + test);
}
else{
    PrintWriter pw=resp.getWriter();
    resp.addHeader("refresh","0;URL=login.jsp");
    if(codet){
        pw.write("<script language='javascript'>alert('用户名或密码错误！')</script>");
    }
    else{
        pw.write("<script language='javascript'>alert('验证码错误！')</script>");
    }
}
```

### 注册按钮

前端代码，即转跳

```jsp
<div class="col-sm-2">
    <button class="btn btn-info" onclick="location.href='Register.jsp'">注册 Register</button>
</div>
```

### 验证码机制

前端代码

```jsp
<div class="col-sm-2">
    <img class="code" alt="点击刷新" src="code" onclick="this.src='code?s='+new Date().getTime();">
</div>
```

后端处理`CodeServlet.java`

相关预处理

```java
System.setProperty("java.awt.headless", "true");//在linux下避免发生sun.awt.X11GraphicsEnvironment异常
response.setContentType("image/JPEG");
ServletOutputStream out = response.getOutputStream();
```
相关定义
```java
// 定义图片的高度和宽度
int width = 80;
int height = 25;
// 定义随机数对象
Random ran = new Random();
// 定义图片缓冲区，使用RGB模式输出图片
BufferedImage image = new BufferedImage(width, height,
                                        BufferedImage.TYPE_INT_RGB);
// 定义画笔工具对象
Graphics graphics = image.getGraphics();
// 设置验证码框的背景颜色
graphics.setColor(new Color(200, 200, 200));
// 填充整个矩形
graphics.fillRect(0, 0, width, height);
// 定义要显示的验证码
StringBuffer sb = new StringBuffer("");
// 定义验证码数组
String[] code = { "A", "a", "B", "b", "C", "c", "D", "d", "E", "e",
                 "F", "f", "G", "g", "H", "h", "I", "i", "J", "j", "K", "k",
                 "L", "l", "M", "m", "N", "n", "O", "o", "P", "p", "Q", "q",
                 "R", "r", "S", "s", "T", "t", "U", "u", "V", "v", "W", "w",
                 "X", "x", "Y", "y", "Z", "z", "0", "1", "2", "3", "4", "5",
                 "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7",
                 "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                 "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1",
                 "2", "3", "4", "5", "6", "7", "8", "9", "0", "1" };
```
绘制验证码
```java
for (int i = 0; i < 4; i++) {
    String str = code[ran.nextInt(code.length)];
    // 设置颜色
    graphics.setColor(new Color(ran.nextInt(150), ran.nextInt(150), ran
                                .nextInt(150)));
    // 设置字体
    graphics.setFont(new Font("宋体", Font.BOLD, 20));
    // 绘制验证码
    graphics.drawString(str, 20 * i+4, 20 - ran.nextInt(5));
    sb.append(str);
}
```
产生干扰点
```java
// 随机产生100个干扰点
for (int i = 0; i < 100; i++) {
    // 随机生成干扰点的坐标
    int x = ran.nextInt(width);
    int y = ran.nextInt(height);
    // 随机生成干扰点的颜色
    graphics.setColor(new Color(ran.nextInt(185) + 40,
                                ran.nextInt(185) + 40, ran.nextInt(185) + 40));
    // 设置干扰点的位置长宽
    graphics.drawOval(x, y, 1, 1);
}
```
结尾处理
```java
String codeInfo = sb.toString();
// 将验证码的值放到session中，以供前台进行验证
request.getSession().setAttribute("code", codeInfo);
// 将Image通过out输出为jpeg格式的图片

ImageIO.write(image, "JPEG", out);
out.flush();
out.close();
```



## 注册页面

![注册页面.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_3ffa6ed8ee-注册页面.png) 
### 前端代码

在用户名、密码、重复一遍密码中会`触发一定事件`去检测是否符合要求

```jsp
<div class="col-sm-6">
    <input type="text" class="form-control" id="username" placeholder="Username" name="username"
           required="required" onblur="checkusername()">
</div>

<div class="col-sm-6">
    <input type="password" class="form-control" id="password" placeholder="Password" name="password"
           required="required" onblur="checkrepassword()">
</div>

<div class="col-sm-6">
    <input type="password" class="form-control" id="repassword" placeholder="Repassword" name="repassword"
           required="required" onblur="checkrepassword()">
</div>
```

先将密码与重复一遍密码都采用`checkrepassword()`

```js
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
```

再讲检测用户名，其采用方法为`checkusername()`

```js
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
```

其请求`/Checkname`的`CheckNameServlet.java`

获取用户名

```java
String username = req.getParameter("username");
```

查询后台是否有该用户

```java
if(username != null){
    String sql = "select id from id_user_password where user = ?";

    UserProfileQuery userProfileQuery = new UserProfileQuery();
    UserProfileInstantiation getusername = userProfileQuery.queryutil(sql, username);

    if(getusername == null){
        out.print(1);
    }
    else{
        out.print(0);
    }
}
else{
    out.print(0);
}
```

### 后端代码

当其点击注册时，向后台发送请求`register`

获取静态内容

```java
String username = req.getParameter("username");
String password = req.getParameter("password");
String repassword = req.getParameter("repassword");
String code = req.getParameter("validate");
boolean codet = code.equalsIgnoreCase(req.getSession().getAttribute("code").toString());
String defaultuniversity = "未填写大学";
String defaultsubject = "未填写专业";
Date date = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
String defaultbirth = df.format(date); // Formats a Date into a date/time string.
String defaultbetter = "未填写特长";
String defaultlikes = "未填写爱好";
```

获取随机图片数值

```java
InputStream is = EachPostServlet.class.getClassLoader().getResourceAsStream("postrandom.properties");
Properties pros = new Properties();
pros.load(is);

Random rand = new Random();
int min = Integer.parseInt(pros.getProperty("postmin")), max = Integer.parseInt(pros.getProperty("postmax"));
int photoid = rand.nextInt(max - min + 1) + min;
```

获取随机图片

```java
String sqlrandphoto = "select photobase64\n" +
                "from examphoto\n" +
                "where photoid = ?";
QueryExamplePhoto queryExamplePhoto = new QueryExamplePhoto();
Radompho radompho = queryExamplePhoto.queryexamphoto(sqlrandphoto,photoid);
String examphoto = radompho.getPhotobase();
```

判断是否新密码与重复密码一直并验证码正确

```java
if(codet&&password.equals(repassword))
```

再次校验新密码格式是否正确

```java
boolean isDigit = false;
boolean isLetter = false;
for(int i=0; i<password.length(); i++){
    if(Character.isDigit(password.charAt(i))){
        isDigit = true;
    }
    else if(Character.isLetter(password.charAt(i))){
        isLetter = true;
    }
}
String regex = "^[a-zA-Z0-9]{8,20}$";
boolean isRight = isDigit && isLetter && password.matches(regex);//判断新密码是否包含字母和数字，并为8~20位
```

正确则创建SQL语句以及执行SQL语句

```java
String sqlup = "insert into id_user_password (user,password) values (?,?)";
String sqlinpho = "insert into id_photo (id,user_photo) values(?,?)";
String sqlid = "select id\n" +
                "from id_user_password\n" +
                "where user = ?";
String sqlinself = "insert into id_myself (id,university,subject,birth,better,likes) values(?,?,?,?,?,?)";
```

```java
updata updata = new updata();
updata.updateutil(sqlup, username, password);

QueryId queryId = new QueryId();
GetId getId = queryId.queryid(sqlid,username);

int id = getId.getId();

updata.updateutil(sqlinpho,id,examphoto);
updata.updateutil(sqlinself,id,defaultuniversity,defaultsubject,defaultbirth,defaultbetter,defaultlikes);


resp.addHeader("refresh","0;URL=login.jsp");

pw.write("<script language='javascript'>alert('注册成功')</script>");
```

不正确则返回

```java
else{
    pw.write("<script language='javascript'>alert('密码应包含字母和数字且2~20位')</script>");
}
```

如果密码输入错误则返回

```java
else if(!password.equals(repassword)){
    pw.write("<script language='javascript'>alert('两次输入密码不一致')</script>");
}
```

如果验证码错误则返回

```java
else {
    pw.write("<script language='javascript'>alert('验证码错误')</script>");
}
```



## 个人主页

个人主页功能更为复杂，涉及操作繁多

![个人主页.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_4768713bee-个人主页.png) 

### 后端

该页面由后端处理完后发送给前端，故从后端开始讲起

```xml
<servlet>
    <servlet-name>eachperson</servlet-name>
    <servlet-class>com.myself.left.servlet.Eeachperson</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>eachperson</servlet-name>
    <url-pattern>/space/*</url-pattern>
</servlet-mapping>
```

处理该界面的是`Eeachperson.java`

获取用户ID

```java
String personnone = req.getPathInfo();
String personid = personnone.split("/")[1];//已获取personid
```
处理是否为数字，若不是则返回至404界面
```java
int k = 0;
for(int i = personid.length(); --i>=0;)
    if(!Character.isDigit(personid.charAt(i))){
        resp.sendRedirect("/404.jsp");
        k=1;
        break;
    }
```

查询该用户id是否存在

```java
String sqlname = "select user from id_user_password where id = ?";
UserProfileQuery userProfileQuery = new UserProfileQuery();
UserProfileInstantiation userProfileInstantiation = userProfileQuery.queryutil(sqlname,personid);
```

不存在则

```java
if(userProfileInstantiation.getUser()==null){
    k=1;
}
```

存在则

查询用户图片并发送给前端

```java
String sqlphoto = "select user_photo from id_photo where id = ? ";
QueryForPhoto queryforphoto = new QueryForPhoto();
Userphoto userphoto = queryforphoto.queryutilphoto(sqlphoto,personid);

req.setAttribute("userphoto",userphoto.getUser_photo());
```

查询用户信息并发送给前端

```java
String sqlprofile = "select id, university,subject, birth,better,likes from id_myself where id = ? ";
QueryForMy queryformy = new QueryForMy();
Usermyself usermyself = queryformy.queryutilmyself(sqlprofile, personid);

String username = userProfileInstantiation.getUser();
String university = usermyself.getUniversity();
String subject = usermyself.getSubject();
Date birth = usermyself.getBirth();
String better = usermyself.getBetter();
String likes = usermyself.getLikes();

req.setAttribute("username",username);
req.setAttribute("university",university);
req.setAttribute("subject",subject);
req.setAttribute("birth",birth);
req.setAttribute("better",better);
req.setAttribute("likes",likes);
```

查询用户自己的帖子并发送给前端

```java
String sqlpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
    "                from invitation\n" +
    "                inner join id_photo\n" +
    "                on invitation.id = id_photo.id\n" +
    "                inner join id_user_password\n" +
    "                on invitation.id = id_user_password.id\n" +
    "                where id_user_password.id = ?\n" +
    "                order by invitation.posttime desc";

EachPostQuery EachPostQuery = new EachPostQuery();
List posts = EachPostQuery.queryeachposts(sqlpost,personid);
req.setAttribute("posts",posts);//传递自己的帖子
```

查询自己收藏的帖子并发送给前端

```java
String sqlfavoritepost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
        "from invitation\n" +
        "inner join id_photo\n" +
        "on invitation.id = id_photo.id\n" +
        "inner join id_user_password\n" +
        "on invitation.id = id_user_password.id\n" +
        "inner join shares\n" +
        "on invitation.invitationid = shares.invitationid\n" +
        "where shares.id = ? and shares.favorite = ?";

List favoritepost = EachPostQuery.queryeachposts(sqlfavoritepost,personid,1);
req.setAttribute("favoritepost",favoritepost);//传递收藏的帖子
```

查询自己点赞的帖子并发送给前端

```java
String sqlthumbpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime, invitation.typeinvitation\n" +
        "from invitation\n" +
        "inner join id_photo\n" +
        "on invitation.id = id_photo.id\n" +
        "inner join id_user_password\n" +
        "on invitation.id = id_user_password.id\n" +
        "inner join shares\n" +
        "on invitation.invitationid = shares.invitationid\n" +
        "where shares.id = ? and shares.likes = ?";

List thumbpost = EachPostQuery.queryeachposts(sqlthumbpost,personid,1);
req.setAttribute("thumbpost",thumbpost);//传递点赞的帖子
```

转跳界面

```java
req.getRequestDispatcher("/myself.jsp").forward(req,resp);
```

### 前端

个人信息界面，即左侧界面

```jsp
<div class="panel-body" id="panel">
    <img class="img-thumbnail borderpho" id="caricature" src="data:image/jpg;base64,${userphoto}" alt="">
    <hr>
    <h3 class="text-center" id="message-myself">个 人 信 息</h3>
    <h5 class="text-center" id="name-myself">${username}</h5>
    <h5 class="text-center" id="birth-myself">${birth}</h5>
    <h5 class="text-center" id="universitydetail-myself">${university}</h5>
    <h5 class="text-center" id="subjectdetail-myself">${subject}</h5>
    <hr>
    <h3 class="text-center" id="better-myself">优 势 特 长</h3>
    <div id="better-pan" style="text-align: center">${better}</div>
    <hr>
    <h3 class="text-center" id="like-myself">兴 趣 爱 好</h3>
    <div id="like-pan" style="text-align: center">${likes}</div>
    <br>
</div>
```


我的点赞列表

```jsp
<div id="mythumb" style="display: none">
    <c:forEach items="${thumbpost}" var="thumb" varStatus="status">
        <div class="panel panel-default">
            <div class="panel-body">
                <h4 style="margin-top: 0px;margin-bottom: 0px;font-weight:bold">${thumb.typeinvitation}</h4>
                <hr style="margin-top: 5px;margin-bottom: 10px">
                <div class="row">
                    <div class="col-xs-2 col-sm-1">
                        <img class="borderpho" src="data:image/jpg;base64,${thumb.user_photo}"style="max-width: 40px">
                    </div>
                    <div class="col-xs-10 col-sm-11">
                        <a href="/space/${thumb.id}" target="_blank">${thumb.user}</a>
                        <br>
                        <span>${thumb.posttime}</span>
                    </div>
                </div>
                <div style="text-align: center">
                    <a href="/${thumb.typeinvitation}/content/${thumb.invitationid}" target="_blank">${thumb.title}</a>
                </div>
                <hr>
                <br>
            </div>
        </div>
    </c:forEach>
    <div style="text-align: center"><a> 我的点赞</a><hr></div>
</div>
```

我的收藏列表

```jsp
<div id="myfavorite" style="display: none">
    <c:forEach items="${favoritepost}" var="fav" varStatus="status">
        <div class="panel panel-default">
            <div class="panel-body">
                <h4 style="margin-top: 0px;margin-bottom: 0px;font-weight:bold">${fav.typeinvitation}</h4>
                <hr style="margin-top: 5px;margin-bottom: 10px">
                <div class="row">
                    <div class="col-xs-2 col-sm-1">
                        <img class="borderpho" src="data:image/jpg;base64,${fav.user_photo}"style="max-width: 40px">
                    </div>
                    <div class="col-xs-10 col-sm-11">
                        <a href="/space/${fav.id}" target="_blank">${fav.user}</a>
                        <br>
                        <span>${fav.posttime}</span>
                    </div>
                </div>
                <div style="text-align: center">
                    <a href="/${fav.typeinvitation}/content/${fav.invitationid}" target="_blank">${fav.title}</a>
                </div>
                <hr>
                <br>
            </div>
        </div>
    </c:forEach>
    <div style="text-align: center"><a> 我的收藏</a><hr></div>
</div>
```

我发的帖子列表

```jsp
<div id="mypost">
    <c:forEach items="${posts}" var="i" varStatus="status">
        <div class="panel panel-default">
            <div class="panel-body">
                <h4 style="margin-top: 0px;margin-bottom: 0px;font-weight:bold">${i.typeinvitation}</h4>
                <hr style="margin-top: 5px;margin-bottom: 10px">
                <div class="row">
                    <div class="col-xs-2 col-sm-1">
                        <img class="borderpho" src="data:image/jpg;base64,${i.user_photo}"style="max-width: 40px">
                    </div>
                    <div class="col-xs-10 col-sm-11">
                        <a href="/space/${i.id}" target="_blank">${i.user}</a>
                        <br>
                        <span>${i.posttime}</span>
                    </div>
                </div>
                <div style="text-align: center">
                    <a href="/${i.typeinvitation}/content/${i.invitationid}" target="_blank">${i.title}</a>
                </div>
                <hr>
                <br>
            </div>
        </div>
    </c:forEach>
    <div style="text-align: center"><a> 帖子</a><hr></div>
</div>
```

#### 修改信息面包屑

下面的触发事件都在`showbar.js`文件里

```jsp
<ol class="breadcrumb" id="mynavbar" style="display: none">
    <li><a onclick="showmodifyprofile()">修改我的信息</a></li>
    <li><a onclick="showmodifypassword()">修改密码</a></li>
    <li><a onclick="showmodifyphoto()">修改头像</a></li>
    <li><a onclick="showmythumb()">我的点赞</a></li>
    <li><a onclick="showmyfavorite()">我的收藏</a></li>
    <li style="float: right"><a onclick="showsearchuser()">搜索用户</a></li>
</ol>
```

`showbar.js`文件部分预览

```js
function showmodifyprofile(){
    if($("#modifyprofile").css("display")=='none'){
        $("#modifyprofile").show("normal");
    }
    else{
        $("#modifyprofile").hide("normal");
    }
}
```

`showbar.js`文件里有个方法，用于控制在`自己界面`才能`显示`修改面包屑

```js
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
```

`IsMe`后端关键语句

```java
String username = req.getParameter("name");
Object id = req.getSession().getAttribute("id_user".toString());

String sqlid = "select id\n" +
        "from id_user_password\n" +
        "where user = ?";

QueryId queryId = new QueryId();
GetId getId = queryId.queryid(sqlid,username);

Object personid = getId.getId();
```

#### 修改个人信息界面

```jsp
<div id="modifyprofile" style="display: none">
    <hr>
    <div class="col-sm-5" style="text-align: center;margin: 0 auto;">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">我的名字</span>
            <input type="text" class="form-control" placeholder="${username}" required="required" name="username" onblur="modifyusernamecheck()" id="username">
        </div>
        <div id="worryusername"></div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">我的大学</span>
            <input type="text" class="form-control" placeholder="${university}"  name="university">
        </div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">我的专业</span>
            <input type="text" class="form-control" placeholder="${subject}"  name="subject">
        </div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">我的优势</span>
            <input type="text" class="form-control" placeholder="${better}"  name="better">
        </div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">我的特长</span>
            <input type="text" class="form-control" placeholder="${likes}" name="likes">
        </div>
        <br>
        <button class="btn btn-default" id="sub" onclick="postmodifyprofile()">提交修改</button>
    </div>
</div>
```

其获取对应input的值，若值不存在，则获取默认值

```js
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
```

然后执行ajax访问servlet

```js
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
```

其后端处理的是`ModifyProfile.java`

获取参数

```java
Object id = req.getSession().getAttribute("id_user".toString());
String name = req.getParameter("name");
String username = req.getParameter("username");
String university = req.getParameter("university");
String subject = req.getParameter("subject");
String better = req.getParameter("better");
String likes = req.getParameter("likes");
```

获取完相关参数后执行SQL语句

```java
String sqlexist = "select * from id_user_password where id = ? and user = ?";
String sqlupself = "update id_myself set university = ?, subject = ?, better = ?, likes = ? where id = ?";
String sqlupname = "update id_user_password set user = ? where id = ?";
```

查询此人是否存在

```java
UserProfileQuery userProfileQuery = new UserProfileQuery();
UserProfileInstantiation userProfileInstantiation = userProfileQuery.queryutil(sqlexist,id,name);
```

存在则执行SQL语句

```java
updata.updateutil(sqlupself,university,subject,better,likes,id);
updata.updateutil(sqlupname,username,id);
```

#### 修改个人密码界面

```jsp
<div id="modifypassword" style="display: none">
    <hr>
    <div class="col-sm-5" style="text-align: center;margin: 0 auto;">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">原密码</span>
            <input type="text" class="form-control" id="oripassword">
        </div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">新密码</span>
            <input type="text" class="form-control" id="newpassword">
        </div>
        <br>
        <div class="input-group input-group-sm">
            <span class="input-group-addon">重复新密码</span>
            <input type="text" class="form-control" id="renewpassword">
        </div>
        <div id="worrypassword"></div>
        <br>
        <button class="btn btn-default" onclick="postmodifypassword()">提交修改</button>
    </div>
</div>
```

其触发`postmodifypassword()`

获取相关参数

```js
let oripa = $("#oripassword").val();
let newpa = $("#newpassword").val();
let renew = $("#renewpassword").val();
```

执行相关ajax

```js
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
```

其请求路径为`/modifypassword`，其servlet为`ModifyPassword`

获取相关参数

```java
Object id = req.getSession().getAttribute("id_user".toString());
String oripa = req.getParameter("oripa");
String newpa = req.getParameter("newpa");
String renew = req.getParameter("renew");
```

创建SQL语句

```java
String sqlcheckoripa ="select password from id_user_password where id = ?";
String sqluppas = "update id_user_password set password = ? where id = ?";
```

比对`新密码`和`重复密码`是否一致并且是否符合其要求

```java
if(newpa.equals(renew))
    ...
boolean isDigit = false;
boolean isLetter = false;
for(int i=0; i<newpa.length(); i++){
    if(Character.isDigit(newpa.charAt(i))){
        isDigit = true;
    }
    else if(Character.isLetter(newpa.charAt(i))){
        isLetter = true;
    }
}
String regex = "^[a-zA-Z0-9]{8,20}$";
boolean isRight = isDigit && isLetter && newpa.matches(regex);//判断新密码是否包含字母和数字，并为8~20位
```

符合要求并执行查询个人信息语句且比对`数据库原密码`是否和`所获取的原密码`相同

```java
UserProfileQuery userProfileQuery = new UserProfileQuery();
UserProfileInstantiation userProfileInstantiation = userProfileQuery.queryutil(sqlcheckoripa,id);
if(Objects.equals(userProfileInstantiation.getPassword(), oripa))
```

以上均通过则执行update语句

```java
updata updata = new updata();
updata.updateutil(sqluppas,newpa,id);
```

#### 修改头像

```jsp
<div id="modifyphoto" style="display: none">
    <hr>
    <h5>请上传头像</h5>
    <img class="layui-upload-img" name="titleBase64Img"
         id="base64Img" src="" style="display: none" width="300px" height="100px;">
    <input  type="file" id="image" lay-verify="required"
            onchange="toBase64()" accept="image/jpeg,image/png,image/jpg"
            class="layui-upload-button">
</div>
```

其触发事件为`tobase64()`

将图片转换为base64编码

获取base64编码

```js
reader.readAsDataURL(file);//文件转为base64
```

```js
var file = document.querySelector('input[type=file]').files[0];
var reader = new FileReader();
reader.onloadend = function () {//文件有了，则触发该事件
    nonimgbase = reader.result;//获取文件结果
    imgbase = nonimgbase.split(",")[1];//获取base64
```

通过ajax向后台发送数据

```js
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
```

通过路径`/uploadimg`访问`UpLoadingImgServlet.java`

获取数据

```java
String imgbase = req.getParameter("imgbase64");
Object id = req.getSession().getAttribute("id_user".toString());
```

imgbase64存在则

```java
String sql = "update id_photo set user_photo = ? where id = ?";
updata updata = new updata();
updata.updateutil(sql,imgbase,id);
```

#### 搜索用户

```jsp
<div id="searchuser" style="display: none">
    <div class="col-sm-5">
        <div class="input-group input-group-sm">
            <span class="input-group-addon">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </span>
            <input type="text" class="form-control" id="searchname" placeholder="不支持模糊搜索,请输入用户名">
        </div>
        <button class="btn btn-default" onclick="searchuser()">搜索</button>
        <br>
        <div id="usercard">
            <img id="searchuserphoto" style="width: 80px">
            <a style="display: inline" id="searchusername"></a>
        </div>
    </div>
</div>
```

其触发事件`searchuser()`

获取信息并向后台发送数据

```js
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
```

请求路径`/searchusername`访问`SearchUsername.java`

获取信息

```java
String searchusername = req.getParameter("searchusername");
```

执行查询语句

```java
String sqlid = "select id\n" +
        "from id_user_password\n" +
        "where user = ?";

QueryId queryId = new QueryId();
GetId getId = queryId.queryid(sqlid,searchusername);
```

查找到了则

```java
int id = getId.getId();
String sql = "select user_photo from id_photo where id = ? ";
QueryForPhoto queryforphoto = new QueryForPhoto();

Userphoto userphoto = queryforphoto.queryutilphoto(sql,id);
String result = searchusername + "(" + id + "(" + userphoto.getUser_photo();
```

#### 彩蛋

其实也不太叫彩蛋，就是说很多版权信息一般会放在这里，F12后，console界面有版权信息

![彩蛋.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_2d9e6f42ee-彩蛋.png) 

其代码为

```js
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
```



## 查看帖子

![单篇帖子.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_e9965d92ee-单篇帖子.png) 

![评论区.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_494098c2ee-评论区.png) 

### 后端

这次先从后端讲起，该页面先从后端发送数据给前端

通过地址获取相关信息

```java
String contentnone = req.getPathInfo();

String contents = contentnone.split("/")[1];//已获取invitationid

String servleturl = req.getServletPath();
String htmltype = servleturl.split("/")[1];
```

同样进行信息预处理，不符合的跳转至404

```java
int k = 0;

for(int i = contents.length(); --i>=0;)
if(!Character.isDigit(contents.charAt(i))){
    resp.sendRedirect("/404.jsp");
    k=1;
    break;
}
```

通过地址获取的帖子id进行查找

```java
String sqlserachpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
        "                from invitation\n" +
        "                inner join id_photo\n" +
        "                on invitation.id = id_photo.id\n" +
        "                inner join id_user_password\n" +
        "                on invitation.id = id_user_password.id\n" +
        "where invitation.invitationid = ?";

QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sqlserachpost,contents);
```

不符合的也同样跳转至404

```java
if(showPostPageInstantiation.getUser()==null){
    resp.sendRedirect("/404.jsp");
    k=1;
}
```

获取当前会话的id

```java
Object id = req.getSession().getAttribute("id_user".toString());
```

id存在时，执行查找分享信息（点赞收藏之类的）

```java
String sqlis = "select shareid, id, invitationid, likes, favorite, browse\n" +
"from shares\n" +
"where shares.id = ? and shares.invitationid = ?";
IsExistQuery isExistQuery = new IsExistQuery();
IsExistInstantiation isExistInstantiation = isExistQuery.queryshareexist(sqlis,id,contents);
```

查看用户对应的帖子有无记录，有则update，无则insert

```java
if(isExistInstantiation.getShareid()==0){//对应的用户和帖子没有记录  insert
    sqlbrowse = "insert into shares (id,invitationid,browse) values(?,?,1)";
}
else{
    sqlbrowse = "update shares set browse = browse + 1 where id = ? and invitationid = ?";
}
```

然后执行SQL语句

```java
String sqlupinvitation = "update invitation set browse = browse + 1 where invitationid = ?";
updata.updateutil(sqlbrowse,id,contents);
updata.updateutil(sqlupinvitation,contents);
```

查询帖子信息

```java
String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
        "                from invitation\n" +
        "                inner join id_photo\n" +
        "                on invitation.id = id_photo.id\n" +
        "                inner join id_user_password\n" +
        "                on invitation.id = id_user_password.id\n" +
        "where invitationid = ? and invitation.typeinvitation = ?";

QueryShowPostPage queryshowpostpage = new QueryShowPostPage();
ShowPostPageInstantiation ShowPostPageInstantiation = queryshowpostpage.queryshowpostpage(sql,contents,htmltype);
```

将数据发送给前端

```java
req.setAttribute("user_photo", ShowPostPageInstantiation.getUser_photo());//把参数传给前端
req.setAttribute("id", ShowPostPageInstantiation.getId());
req.setAttribute("user", ShowPostPageInstantiation.getUser());
req.setAttribute("title", ShowPostPageInstantiation.getTitle());
req.setAttribute("invitationid", ShowPostPageInstantiation.getInvitationid());
req.setAttribute("posttime", ShowPostPageInstantiation.getPosttime());
req.setAttribute("likes", ShowPostPageInstantiation.getLikes());
req.setAttribute("favorite", ShowPostPageInstantiation.getFavorite());
req.setAttribute("browse", ShowPostPageInstantiation.getBrowse());
```

加载评论区并发送给前端

```java
String sqlcomments = "select id_user_password.user as user, id_photo.user_photo as photo, comments.content as comment, comments.id as id, comments.commentid as commentid\n" +
        "from invitation\n" +
        "\n" +
        "inner join comments\n" +
        "on invitation.invitationid = comments.invitationid\n" +
        "\n" +
        "inner join id_user_password\n" +
        "on comments.id = id_user_password.id\n" +
        "\n" +
        "inner join id_photo\n" +
        "on comments.id = id_photo.id\n" +
        "\n" +
        "where invitation.invitationid = ?\n" +
        "order by comments.commentid desc";

CommentInvitationQuery commentInvitationQuery = new CommentInvitationQuery();
List comments = commentInvitationQuery.queryeachcomment(sqlcomments,contents);
req.setAttribute("comments",comments);
```

然后再转跳界面

```java
req.getRequestDispatcher("/htmlpage.jsp").forward(req,resp);
```

### 前端

#### 顶部帖子信息

```jsp
<div class="panel panel-default" >
    <div class="panel-body" style="text-align: center;padding-bottom: 0px;"><h3 style="margin-top: 10px;margin-bottom: 0px;">${title}</h3></div>
    <div style="display:inline;padding-left: 100px;"><img class="borderpho" src="data:image/jpg;base64,${user_photo}" style="max-width: 50px;margin-top: -15px"/></div>
    <div style="display:inline;margin-left:20px" >${user}</div>
    <div style="display:inline;float: right;margin-right: 100px;margin-top: 10px;">
        <div style="display:inline;" id="postid">
            <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
            <div id="invitationid" style="display:inline;">${invitationid}</div>
        </div>
        <div style="display:inline;">
            <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
            <div id="likes" style="display:inline;">${likes}</div>
        </div>
        <div style="display:inline;">
            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
            <div id="favorite" style="display:inline;">${favorite}</div>
        </div>
        <div style="display:inline;">
            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
            <div id="browse" style="display:inline;">${browse}</div>
        </div>
    </div>
    <hr>
</div>
```

同样通过`jstl`将数据写入所需要的地方

#### 修改帖子的信息

```jsp
<div style="float: right;margin-right: 25px;display: none" id="modify">
    <span class="glyphicon glyphicon-pencil" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="modifyinvitation()">&nbsp;</span>
    <span class="glyphicon glyphicon-trash" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="delinvitation()"></span>
</div>
<div style="display:none ;margin-top: 50px;" id="divmodify">
    <textarea id="modifycontent"class="form-control" rows="3" style="resize: vertical;min-height: 140px"></textarea>
    <a class="btn btn-info" href="#" role="button" style="display:inline;float: right;margin-left:50px;margin-top: 25px" onclick="modifypost()">提交</a>
</div>
<div style="display:none ;margin-top: 50px;text-align: center;" id="divdel">
    <a id="delinv" style="text-decoration: none;color: black;">你真的要删除这个帖子吗？</a>
    <a class="btn btn-danger" href="#" role="button" style="display:inline;margin-left:50px;margin-top: 25px" onclick="delinvitationtrue()">提交</a>
    <hr>
</div>
```

这里用到会触发两个事件`修改帖子`和`删除帖子`，`modifyinvitation()`以及`delinvitation()`这两个事件去展现以及关闭操作功能

接下来两个事件是`modifypost()`和`delinvitationtrue()`其代码如下

```js
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
```

其路径为`modifypost`其指向`Modifypost.java`

常规的获取信息

```java
String modcontent = req.getParameter("modcontent");
Object id = req.getSession().getAttribute("id_user".toString());
String invitationid = req.getParameter("invitationid");
```

以及查询当前用户是否有修改这个帖子的权限

```java
String sql = "update invitation set content = ? where id = ? and invitationid = ?";
String sqlsearchpost = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
        "                from invitation\n" +
        "                inner join id_photo\n" +
        "                on invitation.id = id_photo.id\n" +
        "                inner join id_user_password\n" +
        "                on invitation.id = id_user_password.id\n" +
        "where invitation.id = ? and invitation.invitationid = ?";
QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
        ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sqlsearchpost,id,invitationid);
```

有则update信息

```java
updata updata = new updata();
updata.updateutil(sql, modcontent, id, invitationid);
```

另一个触发事件

```js
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
```

其路径指向`/delpost`由`Delpost`处理

常规的获取信息

```java
Object id = req.getSession().getAttribute("id_user".toString());
String invitationid = req.getParameter("invitationid");
```

以及相关SQL语句

```java
String sqldelinv = "delete from invitation where id = ? and invitationid = ?";
String sqldelcom = "delete from comments where invitationid = ?";
String sqlshares = "delete from shares where invitationid = ?";

String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
        "                from invitation\n" +
        "                inner join id_photo\n" +
        "                on invitation.id = id_photo.id\n" +
        "                inner join id_user_password\n" +
        "                on invitation.id = id_user_password.id\n" +
        "where invitation.id = ? and invitation.invitationid = ?";
```

以及执行SQL语句

```java
QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sql,id,invitationid);
```

判断该帖是否属于该用户，属于则update

```java
f(showPostPageInstantiation.getUser()==null)//不属于
//以下为属于的操作
updata updata = new updata();
updata.updateutil(sqldelinv,id,invitationid);
updata.updateutil(sqldelcom,invitationid);
updata.updateutil(sqlshares,invitationid);
```

#### 帖子正文

```jsp
<div id="testEditorMdview">
    <textarea id="appendTest" style="display:none;"></textarea>
</div>
```

其会自动加载以下代码，后半部分由`editormd`去渲染`markdown`转为`HTML页面`

```js
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
```

其请求路径为`contentservlet`由`ContentServlet`去处理

常规获取帖子信息

```java
String invitationid = req.getParameter("invitationid");
```

执行SQL语句

```java
String sql = "select content\n" +
        "from invitation\n" +
        "where invitationid = ?";

QueryContent querycontent = new QueryContent();
ContentInstantiation ContentInstantiation = querycontent.querycontent(sql,invitationid);
```

并传递给前端

```java
PrintWriter out = resp.getWriter();
out.print(ContentInstantiation.getContent());
```

#### 帖子的点赞与收藏

```jsp
<div style="float: right;margin-right: 75px;">
    <span class="glyphicon glyphicon-thumbs-up" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="likes();" id="postlikes">&nbsp;</span>
    <span class="glyphicon glyphicon-star-empty" style="color: rgb(208, 208, 208); font-size: 19px;" onclick="favorite();" id="postfavorite"></span>
    <a id="worryshares"></a>
</div>
```

其会触发`like()`或`favorite()`事件

先介绍`like()`事件，状态由`未点击`到`已点击`

```js
if($("#postlikes").css("color")=="rgb(208, 208, 208)"){//未点击
    $.ajax({
        type: "post",//请求方式
        url: "/shareservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html(),
            sharetype:"addlike",
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data!='0'){
                $("#postlikes").css("color","rgb(0, 161, 214)");
            }
            else{
                $("#worryshares").html("请登录");
            }
        }
    });
}
```

以及状态由`已点击`到`未点击`

```js
else{
    $.ajax({
        type: "post",//请求方式
        url: "/shareservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html(),
            sharetype:"dellike",
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data!='0'){
                $("#postlikes").css("color","rgb(208, 208, 208)");
            }
            else{
                $("#worryshares").val("请登录");
            }
        }
    });
}
```

再接着介绍`favorite()`点击事件，请求路径都一样

状态由`未点击`到`已点击`

```js
if($("#postfavorite").css("color")=="rgb(208, 208, 208)"){
    $.ajax({
        type: "post",//请求方式
        url: "/shareservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html(),
            sharetype:"addfavorite",
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data!='0'){
                $("#postfavorite").css("color","rgb(0, 161, 214)");
            }
            else{
                $("#worryshares").val("请登录");
            }
        }
    });

}
```

状态由`未点击`到`已点击`

```js
else{
    $.ajax({
        type: "post",//请求方式
        url: "/shareservlet",//发送请求地址
        async: false,
        dataType: "html",
        data: {
            invitationid:$("#invitationid").html(),
            sharetype:"delfavorite",
        },
        //请求成功后的回调函数有两个参数
        success: function (data, textStatus) {
            if(data!='0'){
                $("#postfavorite").css("color","rgb(208, 208, 208)");
            }
            else{
                $("#worryshares").val("请登录");
            }
        }
    });
}
```

请求路径都为`/shareservlet`由`SharesServlet`相应

获取信息

```java
Object id = req.getSession().getAttribute("id_user".toString());
String invitationid = req.getParameter("invitationid");
String sharestype = req.getParameter("sharetype");
```

通过该语句判断用户是否登录

```java
if(id==null)
```

已登录则执行

```java
String sqlis = "select shareid, id, invitationid, likes, favorite, browse\n" +
        "from shares\n" +
        "where shares.id = ? and shares.invitationid = ?";
IsExistQuery isExistQuery = new IsExistQuery();
IsExistInstantiation isExistInstantiation = isExistQuery.queryshareexist(sqlis,id,invitationid);
```

去判断该用户是否对该帖子有记录

```
if(isExistInstantiation.getShareid()==0)
```

有则update无则insert

有则根据类型进行对应的操作

```java
String sqlinshares = "";
String sqlininvitation = "";

if(sharestype.equals("addlike")){
    sqlinshares = "insert into shares (id,invitationid,likes) values(?,?,1)";
    sqlininvitation = "update invitation set likes = likes + 1 where invitationid = ?";
    updata.updateutil(sqlinshares,id,invitationid);
    updata.updateutil(sqlininvitation,invitationid);
}
else if(sharestype.equals("addfavorite")){
    sqlinshares = "insert into shares (id,invitationid,favorite) values(?,?,1)";
    sqlininvitation = "update invitation set favorite = favorite + 1 where invitationid = ?";
    updata.updateutil(sqlinshares,id,invitationid);
    updata.updateutil(sqlininvitation,invitationid);
}
else if(sharestype.equals("delfavorite")||sharestype.equals("dellike")){
    pw.print(0);
}
```

无则根据类型进行对应的操作

```java
String sqlupshares = "";
String sqlupinvitation = "";
if(sharestype.equals("addlike")&&(isExistInstantiation.getLikes()==0)){
    sqlupshares = "update shares set likes = likes + 1 where id = ? and invitationid = ?";
    sqlupinvitation = "update invitation set likes = likes + 1 where invitationid = ?";
    updata.updateutil(sqlupshares,id,invitationid);
    updata.updateutil(sqlupinvitation,invitationid);
}
else if(sharestype.equals("dellike")&&(isExistInstantiation.getLikes()==1)){
    sqlupshares = "update shares set likes = likes - 1 where id = ? and invitationid = ?";
    sqlupinvitation = "update invitation set likes = likes - 1 where invitationid = ?";
    updata.updateutil(sqlupshares,id,invitationid);
    updata.updateutil(sqlupinvitation,invitationid);
}
else if(sharestype.equals("addfavorite")&&(isExistInstantiation.getFavorite()==0)){
    sqlupshares = "update shares set favorite = favorite + 1 where id = ? and invitationid = ?";
    sqlupinvitation = "update invitation set favorite = favorite + 1 where invitationid = ?";
    updata.updateutil(sqlupshares,id,invitationid);
    updata.updateutil(sqlupinvitation,invitationid);
}
else if(sharestype.equals("delfavorite")&&(isExistInstantiation.getFavorite()==1)){
    sqlupshares = "update shares set favorite = favorite - 1 where id = ? and invitationid = ?";
    sqlupinvitation = "update invitation set favorite = favorite - 1 where invitationid = ?";
    updata.updateutil(sqlupshares,id,invitationid);
    updata.updateutil(sqlupinvitation,invitationid);
}
else{
    pw.print(0);
}
```

#### 发表评论

```jsp
<div>
    <div>
        <span class="glyphicon glyphicon-user" aria-hidden="true" id="noneuser"> 请登录</span>
    </div>
    <img class="borderpho" src="" style="max-width: 40px;display: none;" id="userphoto">
    <div style="margin-left: -10px;margin-top:5px;display: none;" id="username"></div>
    <div style="display: inline;">
        <textarea class="form-control" rows="3" id="commentcontent" style="resize: vertical;min-height: 140px" name="content"></textarea>
    </div>
    <div id="worryuser" style="color: red"></div>
    <div>
        <a class="btn btn-info" href="#" role="button" style="display:inline;float: right;margin-left:50px;margin-top: 25px" onclick="postcomments()">发表评论</a>
    </div>
</div>
```

其触发事件为`postcomments()`

其获取信息

```js
let content = $("#commentcontent").val();
let invitationid = $("#invitationid").html();
```

然后发送请求

```js
$.ajax({
    type: "post",//请求方式
    url: "/postcommentservlet",//发送请求地址
    async: false,
    dataType: "html",
    data: {
        commentcontent:content,
        invitationid:invitationid,
    },
    //请求成功后的回调函数有两个参数
    success: function (data, textStatus) {
        if(data == '0'){
            $("#worryuser").html("请登录");
        }
        else{
            location.reload();
        }
    }
});
```

其请求路径为`/postcommentservlet`由`PostCommentServlet`处理

同样获取信息

```java
Object id = req.getSession().getAttribute("id_user".toString());
String invitationid = req.getParameter("invitationid");
String content = req.getParameter("commentcontent");
```

创建信息

```java
Date date = new Date();

SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
String commenttime = df.format(date);

String sql = "insert into comments (id,invitationid,content,commenttime) values(?,?,?,?)";
```

如果用户已登录则写入信息

```java
if(id!=null) {
    updata updata = new updata();
    updata.updateutil(sql, id, invitationid, content, commenttime);
}
```

#### 评论区

```jsp
<div id="comments">
    <c:forEach items="${comments}" var="comment" varStatus="status">
        <div on="commentdrawing()" style="background-color: rgba(0,0,0,0.1);margin-top: 10px;margin-bottom: 10px;border-radius: 10px;padding-bottom: 10px">
            <div style="margin-top: 5px;margin-left: 5px">
                <img class="img-fluid borderpho" src="data:image/jpg;base64,${comment.photo}" style="max-width: 35px;max-height: 20px">
                <a style="margin-left: 5px" href="/space/${comment.id}" target="_blank">${comment.user}</a>
                <span id="${comment.commentid}" class="glyphicon glyphicon-trash" style="color: rgb(0, 161, 214); font-size: 19px; margin-right: 10px; float: right;margin-top: 10px" onclick="delcomment(this);"></span>
            </div>
            <div style="margin-left: 50px;margin-top: 10px;margin-bottom: 10px">
                ${comment.comment}
            </div>
        </div>
    </c:forEach>
</div>
```

依旧通过`jstl`获取后台传来的数据进行前台渲染

这里的触发事件由`delcomment()`该事件由该帖主或者发表评论的这个人可以删除，其他成员不可删除

照常获取信息

```js
let commentid = obj.id;
let invitationid = $("#invitationid").html();
```

```js
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
```

向后台`delcomment`发起请求，由`DelCommentServlet`处理

照常获取数据

```java
Object id = req.getSession().getAttribute("id_user".toString());
String commentid = req.getParameter("commentid");
String invitationid = req.getParameter("invitationid");
```

查看是否为自己发的帖子

```java
String sqlexistuser = "select id, invitationid, commentid, content, commenttime\n" +
        "from comments\n" +
        "where commentid = ? and id = ?";

OnlyCommentQuery onlyCommentQuery = new OnlyCommentQuery();
OnlyCommentInstantiation isuser = onlyCommentQuery.querycomment(sqlexistuser,commentid,id);//查看是否是自己发的评论
```

查看是否为帖主发的信息

```java
String sql = "select id_photo.id as id, id_photo.user_photo, id_user_password.user, invitation.title, invitation.invitationid, invitation.posttime,invitation.likes,invitation.favorite,invitation.browse\n" +
                "                from invitation\n" +
                "                inner join id_photo\n" +
                "                on invitation.id = id_photo.id\n" +
                "                inner join id_user_password\n" +
                "                on invitation.id = id_user_password.id\n" +
                "where invitation.id = ? and invitation.invitationid = ?";

QueryShowPostPage queryShowPostPage = new QueryShowPostPage();
ShowPostPageInstantiation showPostPageInstantiation = queryShowPostPage.queryshowpostpage(sql,id,invitationid);
```

判断

```java
if(isuser.getContent()==null&&showPostPageInstantiation.getUser()==null){//不是自己和题主发的评论
```



## 404页面

![404.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_d85faebfee-404.png) 



简单一行代码

```jsp
<img class="img-fluid" src="/static/img/404notfound.jpg" style="max-height: 700px;">
```



## 计算器

![calculator.png](https://cdn.acwing.com/media/article/image/2022/06/18/162339_37246cf1ee-calculator.png) 该计算器采用react编写，写完后采用`npm run build `放置在`jsp`中有些小不兼容，编译过的代码可读性较差

项目依赖

1. react-router-dom
2. redux
3. bootstrap
4. jquery

项目目录

```
.
|-- component
|   |-- app.jsx
|   `-- content
|       |-- base.jsx
|       |-- calculator
|       |   |-- digitButton.jsx
|       |   `-- operationButton.jsx
|       `-- calculator.jsx
|-- index.css
|-- index.js
`-- redux
    |-- actions.js
    |-- reducer.js
    `-- store.js

4 directories, 10 files
```

`app.jsx`

```jsx
import React, { Component } from "react";
import { Navigate, Route, Routes } from "react-router-dom";


import Calculator from "./content/calculator";


class App extends Component {
  state = {
  };
  render() {
    return (
      <React.Fragment>
          <Routes>
            <Route
              path="/calculator/"
              element={
                  <Calculator />
              }
            />
          </Routes>

      </React.Fragment>
    );
  }
}

export default App;

```
`base.jsx`

```jsx
import React, { Component } from 'react';

class Base extends Component {
    state = {  } 
    render() { 
        return (
            <div className='card' style={{marginTop:"20px"}}>
                <div className='card-body'>
                    {this.props.children}
                </div>
            </div>
        );
    }
}
 
export default Base;
```

`digitButton.jsx`

```jsx
import React, { Component } from 'react';
import ACTIONS from './../../../redux/actions';
import { connect } from 'react-redux';


class DigitButton extends Component {
    state = {  } 
    render() { 
        return (
            <button onClick={()=>this.props.add_digit(this.props.digit)}>
                {this.props.digit}
            </button>
        );
    }
}

const mapDispatchToProps={
    add_digit:digit=>{
        return {
            type:ACTIONS.ADD_DIGIT,
            digit:digit,
        }
    }
}
 
export default connect(null,mapDispatchToProps)(DigitButton);
```

`operationButon.jsx`

```jsx
import React, { Component } from 'react';
import ACTIONS from './../../../redux/actions';
import { connect } from 'react-redux';


class Operationbutton extends Component {
    state = {  } 
    render() { 
        return (
            <button onClick={()=>{
                this.props.choose_operation(this.props.operation)
            }}>
                {this.props.operation}
            </button>
        );
    }
}

const mapDispatchToProps={
    choose_operation:operation=>{
        return {
            type:ACTIONS.CHOOST_OPRATION,
            operation:operation
        }
    }
}
 
export default connect(null,mapDispatchToProps)(Operationbutton);
```

`calculator.jsx`

```jsx
import React, { Component } from "react";
import { connect } from "react-redux";
import Base from "./base";
import DigitButton from "./calculator/digitButton";
import Operationbutton from "./calculator/operationButton";
import ACTIONS from './../../redux/actions';


class Calculator extends Component {
  state = {
    formater: Intl.NumberFormat("en-us"),
  };

  format = (number) => {
    if (number === "") return "";
    const [integer, decimal] = number.split('.');
    if (decimal === undefined) return this.state.formater.format(integer);
    return `${this.state.formater.format(integer)}.${decimal}`
  };
  render() {
    return (
      <Base>
        <div className="calculator">
          <div className="output">
            <div className="last-output">{this.format(this.props.lastOperand)} {this.props.operation}</div>
            <div className="current-output">{this.format(this.props.currentOperand)}</div>
          </div>
          <button className="button-ac" onClick={this.props.clear}>AC</button>
          <button onClick={this.props.delete_digit}>Del</button>
          <Operationbutton operation={"÷"} />
          <DigitButton digit={"7"} />
          <DigitButton digit={"8"} />
          <DigitButton digit={"9"} />
          <Operationbutton operation={"×"} />
          <DigitButton digit={"4"} />
          <DigitButton digit={"5"} />
          <DigitButton digit={"6"} />
          <Operationbutton operation={"-"} />
          <DigitButton digit={"1"} />
          <DigitButton digit={"2"} />
          <DigitButton digit={"3"} />
          <Operationbutton operation={"+"} />
          <DigitButton digit={"0"} />
          <DigitButton digit={"."} />
          <button className="button-equal" onClick={this.props.evaluate}>=</button>
        </div>
      </Base>
    );
  }
}

const mapStateToProps=(state,props)=>{
    return {
        currentOperand:state.currentOperand,
        lastOperand:state.lastOperand,
        operation:state.operation
    }
};

const mapDispatchToProps={
    delete_digit:()=>{
        return {
            type:ACTIONS.DELETE_DIGIT,
        }
    },
    clear:()=>{
        return {
            type:ACTIONS.CLEAR
        }
    },
    evaluate:()=>{
        return {
            type:ACTIONS.EVALUATE
        }
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Calculator);

```

`actions.js`

```js
const ACTIONS={
    ADD_DIGIT:"add-digit",
    DELETE_DIGIT:"delete-digit",
    CHOOST_OPRATION:"choose-operation",
    CLEAR:"clear",
    EVALUATE:"evaluate"
}

export default ACTIONS;
```

`reducer.js`

```js
import ACTIONS from './actions';

const evaluate=state=>{
    let {lastOperand,currentOperand,operation}=state;
    let last=parseFloat(lastOperand);
    let current=parseFloat(currentOperand);
    let res="";
    switch(operation){
        case '+':
            res=last+current;
            break;
        case '-':
            res=last-current;
            break;
        case '×':
            res=last*current;
            break;
        case '÷':
            res=last/current;
    }
    return res.toString();
};

const reducer=(state={
    currentOperand:"",
    lastOperand:"",
    operation:"",
    overwrite:false
},action)=>{
    switch(action.type){
        case ACTIONS.DELETE_DIGIT:
            if(state.overwrite){
                return {
                    ...state,
                    currentOperand:"",
                    overwrite:false
                }
            }
            if(state.currentOperand==="") return state;
            return {
                ...state,
                currentOperand:state.currentOperand.slice(0,-1),
            }
        case ACTIONS.CLEAR:
            return {
                currentOperand:"",
                lastOperand:"",
                operation:"",
                overwrite:false
            }
        case ACTIONS.EVALUATE:
            if (state.currentOperand===""|| state.lastOperand===""|| state.operation==="") return state;
            return {
                currentOperand:evaluate(state),
                lastOperand:"",
                operation:"",
                overwrite:true
            }
        case ACTIONS.ADD_DIGIT:
            if(state.overwrite){
                return {
                    ...state,
                    currentOperand:action.digit,
                    overwrite:false
                }
            }
            if (state.currentOperand==='0' && action.digit==='0') return state
            if(state.currentOperand==='0' && action.digit!=='.'){
                return {
                    ...state,
                    currentOperand:action.digit
                }
            }
            if(action.digit==="." && state.currentOperand.includes('.')) return state;
            if(action.digit==="." && state.currentOperand===""){
                return {
                    ...state,
                    currentOperand:"0."
                }
            }
            return {
                ...state,
                currentOperand:state.currentOperand+action.digit
            }
        case ACTIONS.CHOOST_OPRATION:
            if (state.lastOperand==="" && state.currentOperand==="") return state
            if(state.lastOperand===""){
                return {
                    ...state,
                    lastOperand:state.currentOperand,
                    operation:action.operation,
                    currentOperand:"",
                }
            }
            if(state.currentOperand===""){
                return{
                    ...state,
                    operation:action.operation
                }
            }
            return {
                ...state,
                lastOperand:evaluate(state),
                operation:action.operation,
                currentOperand:""
            }
        default:
            return state;
    }
};

export default reducer;
```

`store.js`

```js
import { configureStore } from "@reduxjs/toolkit";
import reducer from "./reducer";

const store =configureStore({
    reducer
});

export default store;
```

`index.css`

```css
body {
  margin: 0;
}

* {
  box-sizing: border-box;
}

.calculator{
  display: grid;
  grid-template-columns: repeat(4, 6rem);
  grid-template-rows: minmax(6rem, auto) repeat(5, 4rem);
  gap:1px;
  background-color: rgba(191,191,191, 0.75);
  width: calc(24rem + 5px);
  margin: auto;
  border: 2px solid black;
}

.output{
  grid-column: 1/span 4;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: space-around;
  padding: 10px;
  word-wrap: break-word;
  word-break: break-all;
}

.last-output{
  font-size: 1.8rem;
}

.current-output{
  font-size: 3rem;
}

.button-ac{
  grid-column: 1/span 2;
}

.button-equal{
  grid-column: 3/span 2;
}

.calculator>button{
  background-color: rgba(250,250,249, 0.75);
}

.calculator>button:hover{
  background-color: #B5B5B5;
}
```

`index.js`

```js
import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './component/app';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap'
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from "./redux/store";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);
```



## KOF



项目纯JS

项目目录

```
.
|-- static
|   |-- css
|   |   `-- base.css
|   |-- images
|   |   |-- background
|   |   |   `-- 0.gif
|   |   `-- player
|   |       `-- kyo
|   |           |-- 0.gif
|   |           |-- 1.gif
|   |           |-- 2.gif
|   |           |-- 3.gif
|   |           |-- 4.gif
|   |           |-- 5.gif
|   |           `-- 6.gif
|   `-- js
|       |-- Tongering_game_object
|       |   `-- base.js
|       |-- base.js
|       |-- controller
|       |   `-- base.js
|       |-- game_map
|       |   `-- base.js
|       |-- jquery-3.6.0.min.js
|       |-- players
|       |   |-- base.js
|       |   `-- kyo.js
|       `-- utils
|           `-- gif.js
`-- templates
    `-- index.html

13 directories, 18 files

```

`/static/css/base.css`

```css
#kof{
    width: 1280px;
    height: 720px;
    background-image: url('../images/background/0.gif');
    background-size: 200% 100%;
    background-position: top;
    position: absolute;
}

#kof > .kof-head{
    width: 100%;
    height: 80px;
    position: absolute;
    top :0;
    display: flex;
    align-items: center;
}

#kof > .kof-head > .kof-head-hp-0{
    height: 40px;
    width: calc(50% - 60px);

    margin-left: 20px;
    border: white 5px solid ;
    border-right: 0px;
    box-sizing: border-box;
}

#kof > .kof-head > .kof-head-hp-timer{
    height: 60px;
    width: 80px;
    background-color: orange;
    border: white 5px solid ;
    box-sizing: border-box;
    color: white;
    font-size: 30px;
    font-weight: 800;
    text-align: center;
    line-height: 50px;
    user-select: none;
}

#kof > .kof-head > .kof-head-hp-1{
    height: 40px;
    width: calc(50% - 60px);

    border: white 5px solid ;
    border-left: 0px;
    box-sizing: border-box;
}

#kof > .kof-head > .kof-head-hp-0 > div{
    background-color: red;
    height: 100%;
    width: 100%;
    float: right;
}

#kof > .kof-head > .kof-head-hp-1 > div{
    background-color: red;
    height: 100%;
    width: 100%;
}

#kof > .kof-head > .kof-head-hp-0 > div > div{
    background-color: lightgreen;
    height: 100%;
    width: 100%;
    float: right;
}

#kof > .kof-head > .kof-head-hp-1 > div > div{
    background-color: lightgreen;
    height: 100%;
    width: 100%;
}
```

`/static/js/controller/base.js`

```js
class Controller{
    constructor($canvas) {
        this.$canvas = $canvas;

        this.pressed_keys = new Set();
        this.start();
    }

    start() {
        let outer = this;
        this.$canvas.keydown(function (e) {
            outer.pressed_keys.add(e.key);
            
        });
        this.$canvas.keyup(function (e) {
            outer.pressed_keys.delete(e.key);
        })
        
    }
}

export {
    Controller
}
```

`/static/js/game_map/base.js`

```js
import { TongeringGameObject } from "/KOF(done)/static/js/Tongering_game_object/base.js";
import {Controller} from '/KOF(done)/static/js/controller/base.js'

class GameMap extends TongeringGameObject{
    constructor(root) {
        super();

        this.root = root;
        this.$canvas = $('<canvas width="1280" height="720" tabindex=0></canvas>');
        this.ctx = this.$canvas[0].getContext('2d');
        this.root.$kof.append(this.$canvas);  //追加上面的内容至base.js里与root一起
        this.$canvas.focus();

        this.controller = new Controller(this.$canvas);

        this.root.$kof.append($(` <div class="kof-head">
        <div class="kof-head-hp-0"><div><div></div></div></div>
        <div class="kof-head-hp-timer">60</div>
        <div class="kof-head-hp-1"><div><div></div></div></div>
    </div>`));
        
        this.time_left = 60000;
        this.$timer = this.root.$kof.find(".kof-head-hp-timer");
    }

    start() {
        
    }

    update() {
        this.time_left -= this.timedelta;
        if (this.time_left < 0) {
            this.time_left = 0;

            let [a, b] = this.root.players;
            if (a.status !== 6 && b.status !== 6) {
                a.status = b.status = 6;
                a.frame_current_cnt = b.frame_current_cnt = 0;
                a.vx = b.vx = 0;
            }
        }

        this.$timer.text(parseInt(this.time_left / 1000));

        this.render();
    }

    render() {
        this.ctx.clearRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
        // this.ctx.fillStyle = 'black';
        // this.ctx.fillRect(0, 0, this.$canvas.width(), this.$canvas.height());
        
    }
      
}

export {
    GameMap
}
```

`/static/js/players/base.js`

```js
import { TongeringGameObject } from "/KOF(done)/static/js/Tongering_game_object/base.js";

class Player extends TongeringGameObject{
    constructor(root, info) {
        super();

        this.root = root
        this.id = info.id;
        this.x = info.x;
        this.y = info.y;
        this.width = info.width;
        this.height = info.height;
        this.color = info.color;

        this.direction = 1;

        this.vx = 0;
        this.vy = 0;

        this.speedx = 400;
        this.speedy = -1000;

        this.gravity = 50;

        this.ctx = this.root.game_map.ctx;
        this.pressed_keys = this.root.game_map.controller.pressed_keys;

        this.status = 3; //0：站立 1：向前 2：向后 3：跳跃 4：攻击 5：被打 6：死亡
    
        this.animations = new Map();
        this.frame_current_cnt = 0;//用于记录经过了多少帧

        this.hp = 100;
        this.$hp = this.root.$kof.find(`.kof-head-hp-${this.id}>div`);
        this.$hp_div = this.$hp.find(`div`);
    }

    start() { 

    }

    update_move() {
        this.vy += this.gravity;

        this.x += this.vx * this.timedelta / 1000;
        this.y += this.vy * this.timedelta / 1000;

        let [a, b] = this.root.players;
        if (a !== this) {
            [a, b] = [b, a];
        }

        let r1 = {
            x1: a.x,
            y1: a.y,
            x2: a.x + a.width,
            y2: a.y + a.height,
        };
        let r2 = {
            x1: b.x,
            y1: b.y,
            x2: b.x + b.width,
            y2: b.y + b.height,
        };

        if (this.is_collision(r1, r2)) {
            b.x += this.vx * this.timedelta / 1000 /2;
            b.y += this.vy * this.timedelta / 1000 /2;
            a.x -= this.vx * this.timedelta / 1000 /2;
            a.y -= this.vy * this.timedelta / 1000 /2;
            if (this.status === 3) {
                this.status = 0;
            }
        }

        if (this.y > 450) {
            this.y = 450;
            this.vy = 0;

            if (this.status === 3) {
                this.status = 0;
            }
        }

        if (this.x < 0) {
            this.x = 0;
        }
        else if (this.x + this.width > this.root.game_map.$canvas.width()) {
            this.x = this.root.game_map.$canvas.width() - this.width;;
        }
    }

    update_control() {
        let w, a, d, space;

        if (this.id === 0) {
            w = this.pressed_keys.has('w');
            a = this.pressed_keys.has('a');
            d = this.pressed_keys.has('d');
            space = this.pressed_keys.has(' ');
        }
        else {
            w = this.pressed_keys.has('ArrowUp');
            a = this.pressed_keys.has('ArrowLeft');
            d = this.pressed_keys.has('ArrowRight');
            space = this.pressed_keys.has('Enter');
        }

        if (this.status === 0 || this.status === 1) {
            if (space) {
                this.status = 4;
                this.vx = 0;
                this.frame_current_cnt = 0;
            }
            else if (w) {
                if (d) {
                    this.vx = this.speedx;
                }
                else if (a) {
                    this.vx = -this.speedx;
                }
                else {
                    this.vx = 0;
                }
                this.vy = this.speedy;
                this.status = 3;
            }
            else if (d) {
                this.vx = this.speedx;
                this.status = 1;
            }
            else if (a) {
                this.vx = -this.speedx;
                this.status = 1;
            }
            else {
                this.vx = 0;
                this.status = 0;
            }
        }
    }

    update_direction() {
        if (this.status === 6) return;
        let players = this.root.players;
        if (players[0] && players[1]) {
            let me = this, you = players[1 - this.id];
            if (me.x < you.x) {
                me.direction = 1;
            }
            else {
                me.direction = -1;
            }
        }
    }

    is_attack() {
        if (this.status === 6) return;
        this.status = 5;
        this.frame_current_cnt = 0;

        this.hp = Math.max(this.hp - 10, 0);

        this.$hp_div.animate({
            width: this.$hp.parent().width() * this.hp / 100
        },100);
        this.$hp.animate({
            width: this.$hp.parent().width() * this.hp / 100
        },800);

        if (this.hp <= 0) {
            this.status = 6;
            this.frame_current_cnt = 0;
        }
    }

    is_collision(r1, r2) {
        if (Math.max(r1.x1,r2.x1) > Math.min(r1.x2,r2.x2)) {
            return false;
        }
        if (Math.max(r1.y1,r2.y1) > Math.min(r1.y2,r2.y2)) {
            return false;
        }
        return true;
    }

    update_attack() {
        if (this.status === 4 && this.frame_current_cnt === 18) {
            let me = this, you = this.root.players[1 - this.id];
            let r1;
            if (this.direction > 0) {
                r1 = {
                    x1: me.x + 120,
                    y1: me.y + 40,
                    x2: me.x + 120 + 100,
                    y2: me.y + 40 + 20,
                };
            }
            else {
                r1 = {
                    x1: me.x + me.width - 120 - 100,
                    y1: me.y + 40,
                    x2: me.x + me.width - 120 - 100 + 100,
                    y2: me.y + 40 + 20,
                };
            }

            let r2 = {
                x1: you.x,
                y1: you.y,
                x2: you.x + you.width,
                y2: you.y + you.height,
            };

            if (this.is_collision(r1, r2)) {
                you.is_attack();
            }
        }
    }

    update() {
        this.update_control();
        this.update_move();
        this.update_direction();
        this.update_attack();

        this.render();
    }

    render() {
        // this.ctx.fillStyle = 'blue';
        // this.ctx.fillRect(this.x, this.y, this.width, this.height);
        
        // this.ctx.fillStyle = 'red';
        // if (this.direction > 0) {
        //     this.ctx.fillRect(this.x + 120, this.y + 40, 100, 20);
        // }
        // else {
        //     this.ctx.fillRect(this.x - 120 + this.width - 100, this.y + 40, 100, 20);
        // }
        //物体碰撞盒子临时

        let status = this.status;

        if (this.status === 1 && this.direction * this.vx < 0) {
            status = 2;
        }

        let obj = this.animations.get(status);
        if (obj && obj.loaded) {

            if (this.direction > 0) {
                let k = parseInt(this.frame_current_cnt / obj.frame_rate) % obj.frame_cnt;
                let image = obj.gif.frames[k].image;
                this.ctx.drawImage(image, this.x, this.y + obj.offset_y, image.width * obj.scale, image.height * obj.scale);
            }
            else {//反转
                this.ctx.save();
                this.ctx.scale(-1, 1);
                this.ctx.translate(-this.root.game_map.$canvas.width(), 0);

                let k = parseInt(this.frame_current_cnt / obj.frame_rate) % obj.frame_cnt;
                let image = obj.gif.frames[k].image;
                this.ctx.drawImage(image, this.root.game_map.$canvas.width() - this.x - this.width, this.y + obj.offset_y, image.width * obj.scale, image.height * obj.scale);
                
                this.ctx.restore();
            }

            
        }

        if (status === 4 || status === 5 || status === 6 ) {
            if (parseInt(this.frame_current_cnt / obj.frame_rate) === obj.frame_cnt - 1) {
                if (status === 6) {
                    this.frame_current_cnt--;
                    this.root.game_map.timedelta = 0;
                }
                else {
                    this.status = 0;
                }
                
            }
        }

        

        this.frame_current_cnt++;
    }
}

export {
    Player
}
```

`/static/js/players/kyo.js`

```js
import { Player } from '/KOF(done)/static/js/players/base.js';
import { GIF } from '/KOF(done)/static/js/utils/gif.js';

class kyo extends Player{
    constructor(root, info) {
        super(root, info);

        this.init_animations();
    }

    init_animations() {
        let outer = this;
        let offsets = [0, -22, -22, -140, 0, 0, 0];//偏移量y
        for (let i = 0; i < 7; i++){
            let gif = GIF();
            gif.load(`/KOF(done)/static/images/player/kyo/${i}.gif`);
            this.animations.set(i, {
                gif: gif,
                frame_cnt: 0,  //总图片数
                frame_rate: 5, //每5帧过度一次
                offset_y: offsets[i],  //y方向偏移量
                loaded: false, //是否加载完成 
                scale:2,  //放大多少倍
            });

            gif.onload = function () {
                let obj = outer.animations.get(i);
                obj.frame_cnt = gif.frames.length;
                obj.loaded = true;

                if (i === 3) {
                    obj.frame_rate = 4;
                }
            }
        }
    }
}

export {
    kyo
}
```

`/static/js/Tongering_game_object/base.js`

```js
let TONGERING_GAME_OBJECTS = [];

class TongeringGameObject{
    constructor() {
        TONGERING_GAME_OBJECTS.push(this);

        this.timedelta = 0;
        this.has_call_start = false;
    }

    start() {
        
    }

    update() {
        
    }

    destroy() {
        for (let i in TONGERING_GAME_OBJECTS) {
            if (TONGERING_GAME_OBJECTS[i] === this) {
                TONGERING_GAME_OBJECTS.splice(i, 1);
                break;
            }
        }
    }
}

let last_timestamp;

let TONGERING_GAME_OBJECTS_FRAME = (timestamp) => {
    for (let obj of TONGERING_GAME_OBJECTS) {
        if (!obj.has_call_start) {
            obj.start();
            obj.has_call_start = true;
        }
        else {
            obj.timedelta = timestamp - last_timestamp;
            obj.update();
        }
    }
    last_timestamp = timestamp;
    requestAnimationFrame(TONGERING_GAME_OBJECTS_FRAME);
}

requestAnimationFrame(TONGERING_GAME_OBJECTS_FRAME);

export {
    TongeringGameObject
}
```

`/static/js/utils/gif.js`

```js
//工具类不给予展示（代码过长）
```

`/static/js/base.js`

```js
import { GameMap } from '/KOF(done)/static/js/game_map/base.js'
import {Player} from '/KOF(done)/static/js/players/base.js'
import {kyo} from '/KOF(done)/static/js/players/kyo.js'

class KOF{
    constructor(id) {
        this.$kof = $('#' + id);
        
        this.game_map = new GameMap(this);

        this.players = [
            new kyo(this, {
                id: 0,
                x: 200,
                y: 0,
                width: 120,
                height: 200,
                color:'blue',
            }),
            new kyo(this, {
                id: 1,
                x: 900,
                y: 0,
                width: 120,
                height: 200,
               color:'red',
            }),
        ]
    }
}

export {
    KOF
}
```

`/templates/index.html`

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KOF</title>
    <link rel="stylesheet" href="../static/css/base.css">
    <script src="../static/js/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div id="kof">
       
    </div>
    
    <script type="module">
        import {KOF} from '../static/js/base.js'
        
        let kof = new KOF('kof');  //传递body里的div标签为kof的
    </script>
</body>
</html>
```

