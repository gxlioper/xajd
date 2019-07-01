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
		<title>学生资助子系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">	
		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	 <script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
       function update(){
    
         var pkValue = $("pkValue").value;
         BatAlert.showTips('正在修改，请稍侯...');
		 document.forms[0].action = "zgkydx_xszz_hkgl.do?method=hkglupdate&doType=update";
		 document.forms[0].submit();
        
    }
    
    
    </script>


	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<form action="/zgkydx_xszz_hkgl" method="post">
		 <input type="hidden" name="pkValue"
				value="<bean:write name="rs" property="xh" />" />
			<fieldset>
				<legend>
					学生还款基本信息
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td colspan="4">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 学号：
							<html:text name="rs" property="xh" />
						</td>
					</tr>
					<tr>
						<td align="right" style="width:22%">
							姓名：
						</td>
						<td style="width:28%">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right" style="width:22%">
							性别：
						</td>
						<td style="width:28%">
							<bean:write name="rs" property="xb" />
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							班级：
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
					</tr>



					<tr>
						<td colspan="4" align="center">
							其他相关信息
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭地址：
						</td>
						<td>
							<html:text name="rs" property="jtdz" />
						</td>
						<td align="right">
							家庭邮政编码：
						</td>
						<td>
							<html:text name="rs" property="jtyzbm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							家庭联系电话：
						</td>
						<td>
							<html:text name="rs" property="jtlxdh" />
						</td>
						<td align="right">
							工作单位：
						</td>
						<td>
							<html:text name="rs" property="gzdw" />
						</td>
					</tr>
					<tr>
						<td align="right">
							工作单位地址：
						</td>
						<td>
							<html:text name="rs" property="gzdwdz" />
						</td>
						<td align="right">
							工作单位联系电话：
						</td>
						<td>
							<html:text name="rs" property="gzdwlxdh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							手机号：
						</td>
						<td>
							<html:text name="rs" property="sjh" />
						</td>
						<td align="right">
							座机电话：
						</td>
						<td>
							<html:text name="rs" property="zjdh" />
						</td>
					</tr>

					<tr>
						<td align="right">
							电子邮箱：
						</td>
						<td>
							<html:text name="rs" property="email" />
						</td>
						<td align="right">
							QQ号：
						</td>
						<td>
							<html:text name="rs" property="qq" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							合同信息
						</td>
					</tr>

					<tr>
						<td align="right">
							合同号：
						</td>
						<td>
							<bean:write name="rs" property="hth" />
						</td>
						<td align="right">
							贷款总金额：
						</td>
						<td>
							<bean:write name="rs" property="dkzje" />
						</td>
					</tr>


					<tr>
						<td align="right">
							放款金额1：
						</td>
						<td>
							<bean:write name="rs" property="fkje1" />
						</td>
						<td align="right">
							放款金额2：
						</td>
						<td>
							<bean:write name="rs" property="fkje2" />
						</td>
					</tr>
					<tr>
						<td align="right">
							放款金额3：
						</td>
						<td>
							<bean:write name="rs" property="fkje3" />
						</td>
						<td align="right">
							放款金额4：
						</td>
						<td>
							<bean:write name="rs" property="fkje4" />
						</td>
					</tr>
					<tr>
						<td align="right">
							放款金额5：
						</td>
						<td colspan="3">
							<bean:write name="rs" property="fkje5" />
						</td>
					</tr>
					<tr>
						<td align="right">
							违约金额：
						</td>
						<td>
							<bean:write name="rs" property="wyje" />
						</td>
						<td align="right">
							违约时间：
						</td>
						<td>
							<bean:write name="rs" property="wysj" />
						</td>
					</tr>
				</table>
				<div align="center">
					<button type="button" class="button2" onclick="update();return false;" style="width:80px">
						提 交
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" type="reset" style="width:80px">
						取 消
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
						type="reset" style="width:80px">
						关 闭
					</button>
				</div>
			</fieldset>
		</form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("修改成功！");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("操作失败!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

