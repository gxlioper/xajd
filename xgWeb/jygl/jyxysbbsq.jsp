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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
		
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function savejyxy(){
		var bbsm = document.getElementById("bbsm").value;
		var xh = document.getElementById("xh").value;
		if(xh == ""){
			alert("请填写学号！！！");
			return false;
		}
		if(bbsm.length>2000){
			alert("补办说明不能超过2000个汉字！！！");
			return false;
		}
		if(confirm("如果该学号的学生已经申请，本次提交将会被覆盖掉以前提交的信息")){
			document.forms[0].action = "/xgxt/jyxysbbsq.do?act=add&doType=save";
			document.forms[0].submit();
		}
    }
    
   
    
    function refreshtheweb(){
    var byqxdm = document.getElementById("byqxdm").value;
    var dwdq = document.getElementById("dwdq").value;
    var xsxh = document.getElementById("xsxh").value;
    var zgbm = document.getElementById("zgbm").value;
    
                document.forms[0].action = "/xgxt/savejyxy.do?doType=go&byqxdm="+byqxdm+"&dwdq="+dwdq+"&xsxh="+xsxh+"&zgbm"+zgbm;
                document.forms[0].submit();       
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	
	function jyxyreinput(){
	            window.location.href="/xgxt/jyxy_input.do?act=first";  
	}
	function ysbb(){
		var bbyy = document.getElementById("bbyy").value;
		if(bbyy == "遗失"){
			document.getElementById("ys").style.display='inline';
			document.getElementById("gh").style.display='none';
		}else if(bbyy == "更换"){
			document.getElementById("ys").style.display='none';
			document.getElementById("gh").style.display='inline';
		}else{
			document.getElementById("ys").style.display='none';
			document.getElementById("gh").style.display='none';
		}
	}
	</script>
	<body onload="ysbb();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jyxysbbsq" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 就业协议方案 - 就业协议书补办申请
				</div>
			</div>
			<logic:notEmpty name="errMsg">
				<center><font color="red" size="8"><bean:write name="errMsg"/></font></center>
			</logic:notEmpty>
			<logic:empty name="errMsg">
				<table width="100%" id="rsT" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								协议书补办申请
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td>
<%--							<logic:equal value="student" name="userType">--%>
							<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
<%--							</logic:equal>--%>
<%--							<logic:notEqual value="student" name="userType">--%>
<%--								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />--%>
<%--								<button class="button2"--%>
<%--									onclick="showTopWin('/xgxt/stu_info.do',750,550);"--%>
<%--									style="width:20px" id="buttonFindStu">--%>
<%--									选择--%>
<%--								</button>--%>
<%--							</logic:notEqual>--%>
						</td>
						<td align="right">
							姓名：
						</td>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								毕业年度：
							</div>
						</td>
						<td>
							<html:text name="rs" property="byny" readonly="true"></html:text>
						</td>
						<td align="right" nowrap="nowrap">
							年级：
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							学制：
						</td>
						<td>
							<bean:write name="rs" property="xz" />
						</td>							
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							专业：
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>				
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>补办原因：
							</div>
						</td>
						<td>
							<html:select property="bbyy" styleId="bbyy" onchange="ysbb();">
								<html:option value=""></html:option>
								<html:option value="遗失">遗失</html:option>
								<html:option value="更换">更换</html:option>
							</html:select>
						</td>	
					</tr>
					<tr>
						<td align="right">
							补办说明
						</td>
						<td colspan="3">
							<html:textarea property="bbsm" rows="10" style="width: 750px"></html:textarea>
						</td>
					</tr>
					<tr id="ys" style="display: none">
						<td colspan="4" style="width: 800px;">
							<font color="red">
							提示：“遗失补办原则：根据江苏省教育厅的规定，在遗失的当地登报（必须是公开发行的报纸）
							申明‘遗失启事’。毕业生查询原协议书编号，到市级以上公开发行的报刊上刊登原协议书声明作
							废的遗失启事，需刊登姓名、毕业院校及就业协议书编号，例：×××遗失毕业生就业协议书，
							××学校 ，号码×××××，声明作废。”
							</font>
						</td>
					</tr>
					<tr id="gh" style="display: none">
						<td colspan="4" style="width: 800px;">
							<font color="red">
							提示：“原单位有效退函有三种：一是直接在《协议书》上注明‘此协议已解除’，并盖协议专用章；
							二是单独开出的有效解约证明，并盖协议专用章；三是在已开出的报到证上注明‘同意改派’，
							并由该单位盖章或由毕业生目前档案托管单位（即人事局或人才市场）盖章。单位已解体或倒闭的，
							相关部门（如原单位所挂靠的人才市场）的证明可代替有效退函。”
							</font>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savejyxy()">
						申请补办
					</button>
				</div>
				</logic:empty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
					    alert("提交成功！");
					    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    				alert("提交失败！");
   				 </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="iszc">
				<logic:equal name="iszc" value="iszc">
					<script>
					    alert("该用户已经存在了，不要重复提交同一个学生");
					    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
