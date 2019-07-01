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
	function ysbb(){
		var bbyy = document.getElementById("BBYY").value;
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
					当前位置：就业管理 - 就业协议方案 - 就业协议书补办查看
				</div>
			</div>
				<table width="100%" id="rsT" class="tbstyle">
					<tr>
						<td align="right" nowrap="nowrap">
							学号：
						</td>
						<td>
						<bean:write name="rs" property="XH"/>
						</td>
						<td align="right" nowrap="nowrap">
							姓名：
						</td>
						<td>
							<bean:write name="rs" property="XM" />
						</td>
					</tr>
					<tr>
						<td nowrap="nowrap">
							<div align="right">
								毕业年度：
							</div>
						</td>
						<td>
							<bean:write name="rs" property="BYNY" />
						</td>
						<td align="right" nowrap="nowrap">
							年级：
						</td>
						<td>
							<bean:write name="rs" property="NJ" />
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							<bean:write name="rs" property="XB" />
						</td>
						<td align="right">
							学制：
						</td>
						<td>
							<bean:write name="rs" property="XZ" />
						</td>							
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<bean:write name="rs" property="XYMC" />
						</td>
						<td align="right">
							专业：
						</td>
						<td>
							<bean:write name="rs" property="ZYMC" />
						</td>				
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>
							<bean:write name="rs" property="BJMC" />
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>补办原因：
							</div>
						</td>
						<td>
							<html:select name="rs" property="BBYY" styleId="bbyy" onchange="ysbb();" style="display: none">
								<html:option value=""></html:option>
								<html:option value="遗失">遗失</html:option>
								<html:option value="更换">更换</html:option>
							</html:select>
							<bean:write name="rs" property="BBYY"/>
						</td>	
					</tr>
					<tr>
						<td align="right">
							补办说明
						</td>
						<td colspan="3">
							<bean:write  name="rs" property="BBSM"/>
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
					<button class="button2" onclick="Close();return false;">
						关闭
					</button>
				</div>
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
