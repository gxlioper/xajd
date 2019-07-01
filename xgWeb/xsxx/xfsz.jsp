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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script>
		function save(){
			if(val("xfje") == ""){
				alert("请将带\*号的项目填写完整！");
				return false;
			}
			if(val("nj") == "" && val("xy") == "" && val("zy") == "" && val("bj") == ""){
				if(confirm("不选择任何条件将设置全部的班级，您确定操作吗？")){
					refreshForm("xsxxgl.do?method=saveXfsz");
				}
			}else{
				refreshForm("xsxxgl.do?method=saveXfsz");
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/xsxxgl.do">
			<input type="hidden" name="xyV" value=""/>
		    <input type="hidden" name="zyV" value=""/>
		    <input type="hidden" name="bjV" value=""/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生信息 - 学生信息 - 学费设置 
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								学费信息
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							年级：
						</td>
						<td>
							<html:select property="nj"  styleId="nj"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>						
					</tr>					
													
					<tr>
						<td align="right" width="50%">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>						
					</tr>

					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							<html:select property="zydm" styleId="zy" onchange="initBjList();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>
							<html:select property="bjdm" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>学费：
						</td>
						<td>
							<html:text property="xfje" styleId="xfje"></html:text>
						</td>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
