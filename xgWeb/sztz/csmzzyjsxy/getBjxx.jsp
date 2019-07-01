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
	<base target="_self" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/csmz_sztz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：素质拓展 - 统计分析 - 学生拓展成果(认证)记录汇总 - 选择汇总班级
				</div>
			</div>
			<fieldset>
				<legend>
					汇总班级选择
				</legend>
				<div class="buttontool" id="btn" align="center">
					<input type="hidden" name="njV" id="njV">
					<input type="hidden" name="xyV" id="xyV">
					<input type="hidden" name="zyV" id="zyV">
					<input type="hidden" name="bjV" id="bjV">
					<table class="tbstyle" >
						<tr >
							<td>
								学年
							</td>
							<td>
								<html:select property="xn" 
									styleId="xn" style="width:180px;">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr >
							<td>
								年级
							</td>
							<td>
								<html:select property="nj" onchange="initZyList();initBjList();"
									styleId="nj" style="width:180px;">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />
							</td>
							<td>
								<html:select property="xydm" style="width:250px;"
									onchange="initZyList();initBjList();" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td>
								专业
							</td>
							<td>
								<html:select property="zydm" onchange="initBjList();"
									styleId="zy" style="width:250px;">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
							<font color="red">*</font>	班级
							</td>
							<td>
								<html:select property="bjdm" styleId="bj" style="width:250px;">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan=2 align=center>
								<button class="button2" onclick="sendBj()">
									确 定
								</button>
								&nbsp;&nbsp;
								<button class="button2" onclick="Close();return false;">
									关 闭
								</button>
							</td>
						</tr>
					</table>
				</div>
			</fieldset>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
<script type="text/javascript">
  function sendBj(){
      if (document.getElementById("bj").value == "") {
			alert("请将选择班级！");
			return false;
	   }
       var url="/xgxt/csmz_sztz.do?method=cgrzhzb";
       document.forms[0].action = url;
	   document.forms[0].target = "_blank";
	   document.forms[0].submit();
	   document.forms[0].target = "_self";
  }
</script>

</html>


