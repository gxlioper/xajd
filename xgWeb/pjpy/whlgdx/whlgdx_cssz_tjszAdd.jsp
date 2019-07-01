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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>
		<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="pjpy/whlgdx/whlgdxjs.js"></script>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>

		<html:form action="/pjpy_whlgdx.do" method="post">
			<input type="hidden" name="xmdm" id="xmdm" value="<bean:write name="xmdm"/>" />
			<input type="hidden" name="jxjfl" id="jxjfl" value="<bean:write name="jxjfl"/>" />
			<input type="hidden" name="jxjdm" id="jxjdm" value="<bean:write name="jxjdm"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">当前位置：评奖评优 - 参数设置 - 条件设置</span>
				</div>
			</div>
			<div id="repTit" align="center">
				<bean:write name="xmmc" />
				&nbsp;
				<b><bean:write name="jxjfl" />&nbsp;(<bean:write name="jxjmc" />)</b>条件设置
			</div>

			<fieldset>
				<legend>
					设置条件
				</legend>
				<table width="100%" class="tbstyle" align="center" id="rsTable">

					<tr>
						<td width="32%" align="right">
							思想道德素质分数
						</td>
						<td width="34%">
							<html:select property="cztj" styleId="cztj" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="&gt;=">大于或等于</html:option>
								<html:option value="&gt;">大于</html:option>
								<html:option value="=">等于</html:option>
								<html:option value="&lt;">小于</html:option>
								<html:option value="&lt;=">小于或等于</html:option>
							</html:select>
						</td>
						<td width="34%">
							<html:text property="dcj" styleId="dcj" />
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							学习平均成绩
						</td>
						<td width="34%">
							<html:select property="cztj" styleId="cztj" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="&gt;=">大于或等于</html:option>
								<html:option value="&gt;">大于</html:option>
								<html:option value="=">等于</html:option>
								<html:option value="&lt;">小于</html:option>
								<html:option value="&lt;=">小于或等于</html:option>
							</html:select>
						</td>
						<td width="34%">
							<html:text property="xxpjcj" styleId="xxpjcj" />
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							拓展素质分数
						</td>
						<td width="34%">
							<html:select property="cztj" styleId="cztj" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="&gt;=">大于或等于</html:option>
								<html:option value="&gt;">大于</html:option>
								<html:option value="=">等于</html:option>
								<html:option value="&lt;">小于</html:option>
								<html:option value="&lt;=">小于或等于</html:option>
							</html:select>
						</td>
						<td width="34%">
							<html:text property="sztzzf" styleId="sztzzf" />
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							单科最低成绩
						</td>
						<td width="34%">
							<html:select property="cztj" styleId="cztj" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="&gt;=">大于或等于</html:option>
								<html:option value="&gt;">大于</html:option>
								<html:option value="=">等于</html:option>
								<html:option value="&lt;">小于</html:option>
								<html:option value="&lt;=">小于或等于</html:option>
							</html:select>
						</td>
						<td width="34%">
							<html:text property="dkzdfs" styleId="dkzdfs" />
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							综合测评成绩排名比例
						</td>
						<td width="34%">
							<html:select property="cztj" styleId="cztj" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="&gt;=">大于或等于</html:option>
								<html:option value="&gt;">大于</html:option>
								<html:option value="=">等于</html:option>
								<html:option value="&lt;">小于</html:option>
								<html:option value="&lt;=">小于或等于</html:option>
							</html:select>
						</td>
						<td width="34%">
							<html:text property="zhszcpcjpmbl" styleId="zhszcpcjpmbl" />
							%
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							学习平均成绩排名比例
						</td>
						<td width="34%">
							<html:select property="cztj" styleId="cztj" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="&gt;=">大于或等于</html:option>
								<html:option value="&gt;">大于</html:option>
								<html:option value="=">等于</html:option>
								<html:option value="&lt;">小于</html:option>
								<html:option value="&lt;=">小于或等于</html:option>
							</html:select>
						</td>
						<td width="34%">
							<html:text property="xxpjcjpmbl" styleId="xxpjcjpmbl" />
							%
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							外语过级情况
						</td>
						<td width="34%" colspan="2">
							<html:select property="wygjqk" styleId="wygjqk"
								style="width:100px">
								<html:option value=""></html:option>
								<html:option value="三级">三级</html:option>
								<html:option value="四级">四级</html:option>
								<html:option value="六级">六级</html:option>
								<html:option value="八级">八级</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="32%" align="right">
							是否贫困生
						</td>
						<td width="34%" colspan="2">
							<html:select property="sfpks" styleId="sfpks" style="width:100px">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
					</tr>
				</table>
				</fieldset>
				<br />
				<br />
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="refreshForm('pjpy_whlgdx.do?method=tjszAdd')"
						style="width:80px" id="buttonPrint">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
		</html:form>
	</body>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("操作失败！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
</html>
