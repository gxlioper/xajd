<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">


<style type="text/css">
.yhdl {
	width: 100%;
	border: 1px solid #000000;
	margin-top: 2px;
	height:180px;
}
*html .yhdl {
	width: 100%;
	border: 1px solid #000000;
	margin-top: 2px;
	height:180px;
}
.yhdl h3 {
	background: url(../images/box_title.gif) repeat-x;
	height: 26px;
	line-height: 26px;
	width: 100%;
	color: #000000;
	font-weight: bold;
	border-bottom: 1px solid #000000;
	text-indent: 1em;
	text-align: left;
}

.yhdl dl {
	width: 90%;
	margin: 0 auto;
	padding: 4px 0px;
}

.yhdl dt {
	float: left;
	width: 60px;
}

.yhdl dd {
	float: left;
	width: 100px;
	clear: right;
}

.yhdl span {
	padding: 2px 10px;
	width: 100%;
}

.yhdl span a {
	background: url(../images/icon4.gif) no-repeat left center;
	padding-left: 10px;
	margin: 2px;
}

.yhdl span a:hover {
	color: red;
	text-decoration: none;
}

.mainframe {
	width: 778px;
	margin: 0 auto;
}

.leftframe {
	width: 170px;
	float: left;
}
.midframe{
	width: 402px;
	float: left;
	margin:0 2px;
}
.rightframe{
	width: 202px;
	float: right;
}
.button2 {
	border: #000000 1px solid;
	padding: 2px 0px 0px 2px;
	filter: progid:DXImageTransform.Microsoft.Gradient(GradientType=0,StartColorStr=#000000,EndColorStr=#000000);
	cursor: hand;
	color: #000000;
	margin: 0px 2px;
	height: 22px;
}





fieldset {
	border: #C3A892 1px solid;
	padding: 4px;
}

legend {
	font-weight: bold;
}
/*FOR FF*/
*>input[type="text"] { 
	border: 1px solid #C3A892;
	padding: 1px 2px;
}
*>input[type="text"]:hover{
	padding: 1px 2px;
	background:#FFFFCC;
	border:1px solid #999999;
	}
*>input[type="text"]:focus{
	padding: 1px 2px;
	background:#FFFFCC;
	border:1px solid #999999;
	}
* html input.text_normal {
	border: 1px solid #C3A892;
	padding: 0 2px;
}

input.inputtext {
	border: 1px solid #CCCCCC;
	padding: 0 2px;
	color: #000;
}
input {
	border: 1px solid #CCCCCC;
	padding: 0 2px;
	color: #000;
}
select {
	border: 1px solid #C3A892;
}

textarea {
	border: 1px solid #C3A892;
}

/*======================全局按钮样式===============*/
button {
	padding: 0 4px;
	background: #FFFCF7 url(../images/button_nor.gif) repeat-x left top;
	cursor: pointer;
	color: #000000;
	margin: 0 2px;
	letter-spacing: 0.2em;
}

button:hover {
	padding: 0 4px;
	background: #FFFCF7 url(../images/button_nor.gif) repeat-x left top;
	cursor: pointer;
	color: #FF7400;
	margin: 0 2px;
	letter-spacing: 0.2em;
}

*>button { /*for modern browser*/
	outline: 1px solid #000000;
	border: 1px solid #fff !important;
}

* html button { /*for IE 6.0/IE 5.*/
	border: 1px solid #000000;
	height: 20px;
}

*+html button { /* for IE 7 */
	border: 1px solid #000000 !important;
}

.tbborder {
	border-collapse: collapse;
}

.tbborder thead {
	height: 23px;
}
.tbborder .btys td {
	border: 1px #fff solid;
	background:#FFD9BB;
	padding: 2px;
	font-weight: bold;
	word-break: keep-all;
	color:#D84106;
}
.tbborder thead td {
	border: 1px #fff solid;
	background:#FFD9BB;
	padding: 2px;
	font-weight: bold;
	word-break: keep-all;
	color:#D84106;
}

.tbborder tbody td {
	border: 1px #fff solid;
	background:F7F7F7;
	padding: 2px;
	word-break: keep-all;
	height: 26px;
}

</style>

		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
		function viewMorezpinfo(doType){	  	   
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  var url ="viewzpinfo.do?method=jyzpinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb"; 
		 if (doType == "view"){
		   showTopWin(url, 700, 650);
		  }
		}
		
		function newpage(obj){
		    var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzpinfo.do?method=jyzpinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		function newpage_news(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewnewsinfo.do?method=newsinfo&newsId="+pkValue+"&jytype=jyweb";
		}
		
		function newpage_zczp(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewzczpinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_ggl(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewgglinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_syjs(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewsyjsinfo.do?method=qitainfo&rowid="+pkValue+"&jytype=jyweb";
		}
		function newpage_qzxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "qzxxinfo.do?method=qzxxinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		
		function newpage_tpxx(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[0].value;
		    obj.href = "viewtpxxinfo.do?method=tpxxinfo&doType=view&pkValue="+pkValue+"&jytype=jyweb";
		}
		function userlogin(){
           var usertype =document.getElementById("userty").value;
           var yhm = document.getElementById("yhm").value;
           var mm = document.getElementById("mm").value;
           //var yzm = document.getElementById("yzm").value;
           
           if(yhm==""){
           alert("请输入用户名！");
           return false;
           }
           
           if(mm==""){
           alert("请输入密码！");
           return false;
           }
           
           //if(yzm==""){
           //alert("请输入验证码！");
           //return false;
           //}
           
           //if(yzm.length<4){
            //alert("验证码的位数不正确！");
           //return false;
          // }
           
		   document.forms[0].action = "zjjjindex.do?method=zjjjjyindex&doType=login&usertype="+usertype+"&jytype=jyweb";
		   document.forms[0].submit();
		}	
			
		function dowhat(){
		   document.forms[0].action = "adminoperation.do?method=adminoperation&jytype=jyweb";
		   document.forms[0].submit();
		}
		
		function wjdc(){
		   document.forms[0].action = "index.do?method=jyindex&doType2=wjdc&jytype=jyweb";
		   document.forms[0].submit();
		}
		
		function wjdcresult(){
		    url = "wjdcresult.do?method=wjdcresult&jytype=jyweb";
		    showTopWin(url, 450, 270);
		    
		}
		function findarticle(){
		    document.forms[0].action = "findarticle.do?method=findarticle&doType=find&jytype=jyweb";
		    document.forms[0].submit();
		}
		
		function admingl(){
		 	document.forms[0].action = "adminoperation.do?method=adminoperation&act=zxdt&jytype=jyweb";
		 	document.forms[0].submit();
        }
        function dwgl(){
            //document.forms[0].action = "adminoperation.do?method=adminoperation&act=zpxx&jytype=jyweb";
		 	//document.forms[0].submit();
		 	window.open("adminoperation.do?method=adminoperation&act=zpxx&jytype=jyweb");
        }
         function stugl(){
            //document.forms[0].action = "adminoperation.do?method=adminoperation&act=grjldj&jytype=jyweb";
		 	//document.forms[0].submit();
		 	window.open("adminoperation.do?method=adminoperation&act=grjldj&jytype=jyweb");
        }
         function detect()
		{
			if (document.getElementsByName("userty1")[0].checked)
			{
			    document.getElementById("userty").value=document.getElementsByName("userty1")[0].value;
			    document.getElementById("hreglink").value="xsregister.do?method=xsregister&jytype=jyweb";
			    //alert(document.getElementById("cslj").href);
			}else if (document.getElementsByName("userty1")[1].checked){
			   document.getElementById("userty").value=document.getElementsByName("userty1")[1].value;
			   document.getElementById("hreglink").value="dwregister.do?method=dwregister&jytype=jyweb";
			   //alert(document.getElementById("cslj").href);
			}
		}
		
		function yhzc(){
			var zclj = document.getElementById("hreglink").value;
			window.open(zclj);
		}
	</script>



	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body onload="detect();">
		<form action="zjjjindex.do" method="post">
			<div class="mainframe">
				<div class="leftframe">
					<div class="yhdl">
						<h3>
							用户登录
						</h3>
						<logic:equal name="who" value="anonymous">
							<dl>
								<dt>
									用户名：
								</dt>
								<dd style="height: 30px">
									<input type="text" style="width:80px;" size="10"
										class="inputtext" name="yhm" maxlength="15" />
								</dd>
								
								<dt>
									密&nbsp;&nbsp;&nbsp;&nbsp;码：
								</dt>
								<dd  style="height: 30px">
									<input type="password" style="width:80px;" class="inputtext"
										maxlength="16" name="mm" />
								</dd>
<!--								<dt>-->
<!--									验证码：-->
<!--								</dt>-->
<!--								<dd>-->
<!--									<input name="yzm" type="text" class="inputtext"-->
<!--										style="width:80px" maxlength="4" />-->
<!--									<img src="yzm.jsp" border="0" align="absmiddle" />-->
<!--								</dd>-->
								<dt>
									<input name="userty" id="userty" value="" type="hidden">
									<input name="hreglink" id="hreglink" value="" type="hidden">
									<input name="userty1" id="userty1" value="xs" checked="checked" type="radio" onclick="detect();">个人</input>
								</dt>
								<dd  style="height: 35px">
									<input name="userty1" id="userty1" value="dw" type="radio" onclick="detect();">单位</input>
								</dd>
								<dt>
									<button onClick="userlogin();">
										登&nbsp;录
									</button>
								</dt>
								<dd>
									<a id="cslj" href="#" onclick="yhzc();">&nbsp;用户注册</a>
								</dd>
							</dl>
<!--							<span>-->
<!--								<a href="dwregister.do?method=dwregister&jytype=jyweb">&nbsp;单位注册</a>-->
<!--								<a href="xsregister.do?method=xsregister&jytype=jyweb">&nbsp;学生注册</a>-->
<!--								<a href="findweb.do?method=dwyhmmzh&jytype=jyweb">忘记密码？</a>-->
<!--							</span>-->
						</logic:equal>
						<!-- 管理员登陆后的操作框 -->

						<logic:equal name="who" value="admin">
							<dl>
								<dt>
									用户类型：
								</dt>
								<dd>
									＜管理员＞
								</dd>
								<dt>
									用户名：
								</dt>
								<dd>
									<bean:write name="xm" />
								</dd>
								<dt>
									信息管理：
								</dt>
								<dd>
									<button onclick="admingl()" style="width:100px">
										进入后台管理》》
									</button>
								</dd>
							</dl>
							<span><a
								href="changepassword.do?method=changepassword&jytype=jyweb"><font
									color="red">修改密码</font> </a> <a
								href="index.do?method=jyindex&doType=left&jytype=jyweb"><font
									color="red">注销</font> </a> </span>
						</logic:equal>
						<!-- 学生登陆后的操作框 -->
						<logic:equal name="who" value="xs">
							<dl>
								<dt>
									用户类型：
								</dt>
								<dd>
									＜学生＞
								</dd>
								<dt>
									用户名：
								</dt>
								<dd>
									<bean:write name="xm" />
								</dd>
								<dt>
									信息管理：
								</dt>
								<dd>
									<button onclick="stugl()" style="width:100px">
										进入操作界面》》
									</button>
								</dd>
							</dl>
							<span><a
								href="#" onclick="window.open('viewalldwhfinfo.do?method=alldwhfinfo&jytype=jyweb')">已有<font
									color="red"><bean:write name="howmanydw" scope="session" />
								</font>家单位回复你</a> </span>
							<span> <a
								href="#" onclick="window.open('changepassword.do?method=changepassword&jytype=jyweb')"><font
									color="red">修改密码</font> </a>| <a
								href="zjjjindex1.do?method=zjjjjyindex&doType=left&jytype=jyweb"><font
									color="red">注销</font> </a> </span>
						</logic:equal>
						<!-- 单位登陆后的操作框 -->

						<logic:equal name="who" value="dw">
							<dl>
								<dt>
									用户类型：
								</dt>
								<dd>
									＜单位＞
								</dd>
								<dt>
									用户名：
								</dt>
								<dd>
									<bean:write name="dwmc" />
								</dd>
								<dt>
									信息管理：
								</dt>

								<dd>
									<button onclick="dwgl()" style="width:100px">
										进入操作界面》》
									</button>
								</dd>
							</dl>
							<span><a
								href="#" onclick="window.open('dwviewallgrjlinfo.do?method=dwviewallgrjlinfo&jytype=jyweb')">已有<font
									color="red"><bean:write name="howmany" scope="session" />
								</font>位学生投简历</a> </span>
							<span> <a
								href="#" onclick="window.open('changepassword.do?method=changepassword&jytype=jyweb')"><font
									color="red">修改密码</font> </a> <a
								href="zjjjindex1.do?method=zjjjjyindex&doType=left&jytype=jyweb"><font
									color="red">注销</font> </a> </span>
						</logic:equal>
<!--						您是第&nbsp;-->
<!--						<font color="red" size="8"><b><bean:write name="lll" />-->
<!--						</b> </font>&nbsp;位来访者-->
					</div>
				</div>
			</div>
		</form>

		<logic:notEmpty name="login">
			<logic:equal name="login" value="no">
				<script>
                      alert("用户名或密码错误！");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="yz">
			<logic:equal name="yz" value="no">
				<script>
                      alert("验证码错误！");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="wjdcok">
			<logic:equal name="wjdcok" value="wjdcok">
				<script>
                      alert("问卷提交成功！");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="wjdcno">
			<logic:equal name="wjdcno" value="wjdcno">
				<script>
                      alert("问卷提交失败！");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="wjdcnull">
			<logic:equal name="wjdcnull" value="wjdcnull">
				<script>
                      alert("问卷提交失败！选项不能为空!");
                </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:equal name="wjdccf" value="wjdccf">
				<script>
                      alert("问卷提交失败！您已经提交过了!");
                </script>
		</logic:equal>

	</body>

</html>
