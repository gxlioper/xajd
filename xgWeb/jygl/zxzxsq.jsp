<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title>就业管理信息系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<base target="_self">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	
	function add(){
	 var xh = document.getElementById("xh").value;
	 var xsxm = document.getElementById("xsxm").value;
	 var zymc = document.getElementById("zymc").value;
     var email = document.getElementById("email").value;
     var lxdh = document.getElementById("lxdh").value;
 
     if(xh==""){
     alert("学号不能为空！");
     return false;
     }
     //if(!isNumber(num)){
     //alert("编号应为数字！");
     //return false;
     //}
     if(xsxm==""){
     alert("姓名不能为空！");
     return false;
     }
     if(zymc==""){
     alert("专业名称不能为空！");
     return false;
     }
     //if(isNumber(name)){
     //alert("姓名不能为数字！");
     //return false;
     //}
     if(lxdh!=""&&!isNumber(lxdh)){
     alert("联系电话应为数字！");
     return false;
     }  
     if((email != null) && (email != "") && (!isEmail(email))){
     alert("电子邮箱不合法！");
     return false;
     }

     var zxnr = document.getElementById("zxnr").value;
		if(zxnr.length>600){
			alert("咨询内容不能超过600个汉字");
			return false;
		}
		
		 document.forms[0].action = "/xgxt/jyglxszxsq.do?act=savesq";
		 document.forms[0].submit();
        
    }
    
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
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    } 
	
	function winclo(){
		dialogArgumentsQueryChick();;
	   	window.Close();
	}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 职业咨询 - 在线咨询申请
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>在现咨询登记表</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td width="15%">
						学号<font color="red">*</font>：
					</td>
					<td width="35%">
						<html:text name="rs" property="xh" maxlength="30" style="width=70%"/>
					</td>
					<td width="15%">
						姓名<font color="red">*</font>：
					</td>
					<td>
						<html:text name="rs" property="xsxm" maxlength="20" style="width=55%"/>
					</td>
				</tr>
				<tr>
					<td align="">
						年龄：
					</td>
					<td>
						<html:text name="rs" property="age" maxlength="2" style="width=70%"/>
					</td>
					<td align="">
						性别：
					</td>
					<td>
						<html:select name="rs" property="xb">
							<html:option value="男">男</html:option>
							<html:option value="女">女</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="" >
						专业名称<font color="red">*</font>：
					</td>
					<td>
						<html:text name="rs" property="zymc" maxlength="28" style="width=70%"/>
					</td>
					<td align="">
						电子邮箱<font color="red">*</font>：
					</td>
					<td>
						<html:text name="rs" property="email" style="width=55%" maxlength="38"/>
					</td>
				</tr>
				<tr>
					<td align="">
						联系电话<font color="red">*</font>：
					</td>
					<td>
						<html:text name="rs" property="lxdh" style="width=70%" maxlength="28"/>
					</td>
					
				</tr>
				<tr>
					<td align="">
						咨询内容：
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="zxnr" rows="12"
							style="width=100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="add()" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="button" style="width:80px" onclick="winclo();">
					关闭
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("提交成功！");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("重复提交！操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

