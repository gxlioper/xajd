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
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">	
		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
			
	<script language="javascript">
	function add(){
		var zpsj = document.getElementById("zpsj").value;
		var xydm = document.getElementById("xydm").value;
		if(xydm == "--请选择--"){
			alert("<bean:message key="lable.xsgzyxpzxy" />名称必须填写");
			document.getElementById("xydm").focus();
			return false;
		}
		if(zpsj == ""){
			alert("招聘时间必须填写");
			document.getElementById("zpsj1").focus();
			return false;
		}
		
	         BatAlert.showTips('正在提交，请稍侯...');
			 document.forms[0].action = "zphcs.do?act=add&doType=add";
			 document.forms[0].submit();
    }
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/bysbm" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>招聘会信息录入</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>系(院)名称：
					</td>
					<td>
						<html:select name="rs1" property="xydm" onchange="" style="width:200px" styleId="xy">
									<html:option value="--请选择--"></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						招聘类型：
					</td>
					<td>  
						<html:select name="rs1" property="zplx">
							<html:option value="大型招聘会">大型招聘会</html:option>
							<html:option value="单位宣讲会">单位宣讲会</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						招聘次数：
					</td>
					<td>  
						<html:text name="rs1" property="zpcs"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						单位数量：
					</td>
					<td>  
						<html:text name="rs1" property="dwsl"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						岗位数量：
					</td>
					<td>
						<html:text name="rs1" property="gwsl"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>招聘时间：
					</td>
					<td>
						<html:text name="rs1" property="zpsj" onclick="return showCalendar('zpsj','y-mm-dd');" />
					</td>
				</tr>
			</table>
			<div align="center">
				<button class="button2" onclick="add();" style="width:80px">
					提 交
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					关 闭
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

