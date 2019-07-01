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
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function savethemessage(){
		 var yzm =document.getElementById("yzm").value;
	     var yhm =document.getElementById("yhm").value;
	     var mm =document.getElementById("mm").value;
	     var mm2 =document.getElementById("mm2").value;
	     var tswt1 =document.getElementById("tswt1").value;
	     var da1 =document.getElementById("da1").value;
	     var tswt2 =document.getElementById("tswt2").value;
	     var da2 =document.getElementById("da2").value;
	     var dwmc =document.getElementById("dwmc").value;
	     var qyfr =document.getElementById("qyfr").value;
	     var jgdmzh =document.getElementById("jgdmzh").value;
	     var dwxz =document.getElementById("dwxz").value;
	     var hyfl =document.getElementById("hyfl").value;
	     var lxr =document.getElementById("lxr").value;
	     var lxdh =document.getElementById("lxdh").value;
	     var cz =document.getElementById("cz").value;
	     var email =document.getElementById("email").value;
	     var yb =document.getElementById("yb").value;
	     
	 
	     if(yhm==""){
	      alert("请输入用户名!");
	     return false;
	     }
	     
	     if(onlyNumWords(yhm)){
	      alert("用户名只能为数字或字母!");
	     return false;
	     } 	   
	       
	       
	     if(mm==""){
	      alert("请输入密码!");
	     return false;
	     }
	     
	     if(mm.length<6){
	      alert("密码长度不能少于6位!");
	     return false;
	     }   
	     
	     if(mm2==""){
	      alert("请再次输入密码!");
	     return false;
	     }  
	        
	     if(mm!=mm2){
	      alert("密码输入前后不符！");
	     return false;
	     }
     
	     if(yzm==""){
	      alert("请输入验证码!");
	     return false;
	     }
	    
	     if(yzm.length!=4){
	      alert("验证码位数不对!");
	     return false;
	     }
		
		 if(tswt1==""){
	      alert("请选择第一个提示问题！");
	     return false;
	     }
		 
		 if(tswt1!=""&&da1==""){
		  alert("请填写提示问题1的答案！");
	     return false;
		 }
		 
		 if(tswt2==""){
	      alert("请选择第二个提示问题！");
	     return false;
	     }
		 
		 if(tswt2!=""&&da2==""){
		  alert("请填写提示问题2的答案！");
	     return false;
		 }
		
		 
		 if(dwmc==""){
		  alert("请填写单位名称！");
		  return false;
		 }
		 
		 
		 
		 if(qyfr==""){
		  alert("请填写企业法人！");
	     return false;
		 }
		
		 if(isNumber(qyfr)){
		  alert("请正确输入企业法人！");
	     return false;
		 }
		 
		 if(jgdmzh==""){
		  alert("请填写机构代码证号！");
	     return false;
		 }
		 
		 if(onlyNumWords(jgdmzh)){
		  alert("机构代码证号格式不正确！");
	     return false;
		 }
		 
		 if(dwxz==""){
		  alert("请填写单位性质!");
	     return false;
		 }
		 
		 if(hyfl==""){
		  alert("请填写行业分类!");
	     return false;
		 }
		 
		 if(lxr==""){
		  alert("请填写联系人!");
	     return false;
		 }
		 
		 if(isNumber(lxr)){
		  alert("请正确填写联系人!");
	     return false;
		 }
		 
		 if(lxdh==""){
		  alert("请填写联系电话!");
	     return false;
		 }
		 
		 if(lxdh.lenght<7){
		  alert("联系电话长度不能少于7位！");
		  return false;
		 }
		 
		 
		 if(cz!=""&&!isNumber(cz)){
		  alert("传真应为数字!");
	     return false;
		 }
		 
		 if(email!=""&&!isEmail(email)){
		  alert("电子邮箱格式不正确!");
	     return false;
		 }
		 
		 if(yb!=""&&!isNumber(yb )){
		  alert("邮编应为数字！");
		  return false;
		 }
		 
	     if(yb!=""&&yb.length!=6){
	      alert("邮编长度应为6位！");
	      return false;
	     }
		 
		 
		
		 document.forms[0].action = "dwregister.do?method=dwregister&doType=save&jytype=jyweb";
		 document.forms[0].submit();
		
		}
		
		/*
        *判断是否数字 
        */
        function isNumber(s){
	      var p = /^(-|\+)?\d+$/;
	      return p.test(s); 
       } 
    
       /*
        *判断是否只有字母和数字 
        */
        function onlyNumWords(s){
         var p = /[^\da-zA-Z]/g;
         return p.test(s);
       }
      
      /*
        *判断电子邮件地址正确性
        */
      
          //exclude left and right space; 
	  function trim(s){
 		return rtrim(ltrim(s)); 
	  }
     	//exclude left space; 
	  function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	  } 
	    //exclude right space; 
	  function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	  }
    
      function isEmail(s){
	   s = trim(s); 
 	   var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	  return p.test(s);
      }
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<logic:equal name="xxdm" value="10856" scope="session">
			<logic:equal name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head_false.jsp"></jsp:include>
				<input type="hidden" name="lock" value="closed" />
			</logic:equal>
			<logic:notEqual name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head.jsp"></jsp:include>
				<input type="hidden" name="lock" value="open" />
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="xxdm" value="10856" scope="session">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="lock" value="open" />
		</logic:notEqual>
		<html:form action="/dwregister" method="post">
			<div class="mainframe">
				<div class="jy_midframe">
					<h1>
						单位注册
					</h1>
					<table width="97%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="25px" bgcolor="#F4F4F4">
								<div class="biaoti">
									基本信息
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> 为必填项)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									用户名：
								</div>

								<div class="bdan">
									<html:text name="rs" property="yhm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="15" />
									<font color="red">*</font><font color="black">(最长15位的英文和数字)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									密码：
								</div>
								<div class="bdan">
									<html:password name="rs" property="mm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="16" />
									<font color="red">*</font><font color="black">(长度：6-16位)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									重复密码：
								</div>
								<div class="bdan">
									<html:password name="rs" property="mm2"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="16" />
									<font color="red">*</font><font color="black">(长度：6-16位)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									请输入验证码：
								</div>
								<div class="bdan">
									<html:text name="rs" property="yzm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="4" />
									<img src="yzm.jsp" border="0" align="absmiddle" />
									<font color="red">*</font><font color="black">(请以小写输入)</font>
								</div>
							</td>
						</tr>
						<tr>
							<td height="25px" bgcolor="#F4F4F4">
								<div class="biaoti">
									密码找回信息
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> 为必填项)</font>
								</div>
							</td>

						</tr>

						<tr height="25">
							<td>
								<div class="dwxx">
									提示问题1：
								</div>
								<div class="bdan">
									<html:select name="rs" property="tswt1"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:option value="你最喜欢的电影？"></html:option>
										<html:option value="你最喜欢的明星？"></html:option>
										<html:option value="你最喜欢的歌曲？"></html:option>
										<html:option value="你最喜欢的小说？"></html:option>
										<html:option value="你最喜欢的颜色？"></html:option>
										<html:option value="你最喜欢的宠物？"></html:option>
										<html:option value="你最喜欢的食物"></html:option>
										<html:option value="你最喜欢的饮料？"></html:option>
										<html:option value="你最喜欢的国家？"></html:option>
										<html:option value="你最喜欢的运动员？"></html:option>
										<html:option value="你最喜欢的历史人物？"></html:option>
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									答案1：
								</div>
								<div class="bdan">
									<html:text name="rs" property="da1"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="18" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									提示问题2：
								</div>
								<div class="bdan">
									<html:select name="rs" property="tswt2"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:option value="你最讨厌的电影？"></html:option>
										<html:option value="你最讨厌的明星？"></html:option>
										<html:option value="你最讨厌的歌曲？"></html:option>
										<html:option value="你最讨厌的小说？"></html:option>
										<html:option value="你最讨厌的颜色？"></html:option>
										<html:option value="你最讨厌的宠物？"></html:option>
										<html:option value="你最讨厌的食物"></html:option>
										<html:option value="你最讨厌的饮料？"></html:option>
										<html:option value="你最讨厌的国家？"></html:option>
										<html:option value="你最讨厌的运动员？"></html:option>
										<html:option value="你最讨厌的历史人物？"></html:option>
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									答案2：
								</div>
								<div class="bdan">
									<html:text name="rs" property="da2"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="18" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>



						<tr height="25">
							<td bgcolor="#F4F4F4">
								<div class="biaoti">
									单位基本信息
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> 为必填项)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									单位名称：
								</div>
								<div class="bdan">
									<html:text name="rs" property="dwmc"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									企业法人：
								</div>
								<div class="bdan">
									<html:text name="rs" property="qyfr"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="10" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									机构代码证号：
								</div>
								<div class="bdan">
									<html:text name="rs" property="jgdmzh"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="15" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									单位性质：
								</div>
								<div class="bdan">
									<html:select name="rs" property="dwxz" styleId="dwxz"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:options collection="dwxzdm2List" property="dwxz"
											labelProperty="dwxz" />
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									行业分类：
								</div>
								<div class="bdan">
									<html:select name="rs" property="hyfl" styleId="hyfl"
										style="width:250px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl"
											labelProperty="hyfl" />
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									联系人：
								</div>
								<div class="bdan">
									<html:text name="rs" property="lxr"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="10" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									联系电话：
								</div>
								<div class="bdan">
									<html:text name="rs" property="lxdh"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
									<font color="red">*</font><font color="black">(外地电话请留区号)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									传真：
								</div>
								<div class="bdan">
									<html:text name="rs" property="cz"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									电子邮箱：
								</div>
								<div class="bdan">
									<html:text name="rs" property="email"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="25" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									地址：
								</div>
								<div class="bdan">
									<html:text name="rs" property="dz"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="25" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									邮编：
								</div>
								<div class="bdan">
									<html:text name="rs" property="yb"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="6" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									单位简介：
								</div>
								<div class="bdan">
									<html:textarea name="rs" property="dwjj"
										style="width:100%;height:200px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										rows="15" />

								</div>
							</td>
						</tr>

					</table>
					<div align="center">
						<button onclick="savethemessage();">
							提交注册信息
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							重 置
						</button>
					</div>
				</div>
		</html:form>
		<h3>

		</h3>
		<div>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>

		<logic:notEmpty name="add">
			<logic:equal name="add" value="no">
				<script>
                      alert("验证码错误！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="no">
				<script>
                      alert("注册失败，用户名或单位名称已被使用！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="dwmcchongfu">
			<logic:equal name="dwmcchongfu" value="dwmcchongfu">
				<script>
                      alert("该单位名称已被注册！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
