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
		<title><bean:message key="lable.title" /></title>
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
	<script language="javascript">
	function saveWs(){
		if($("xh").value == ""){
			alert("学号不能为空，请确认！！");
			return false;
		}
		if($("wsqk").value == ""){
			alert("卫生情况不能为空，请确认！！");
			return false;
		}
		if($("lddm").value == ""){
			alert("连队名称不能为空，请确认！！");
			return false;
		}
		if($("zs").value == ""){
			alert("周数不能为空，请确认！！");
			return false;
		}
		if($("jcsj").value == ""){
			alert("检查时间不能为空，请确认！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm('/xgxt/zgdd_jxgl.do?method=gfswsglUpdate&doType=save');
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/zgdd_jxgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/zgdd_jxgl.do?method=gfswsglUpdate&doType=stuInfo" />
				<fieldset>
					<legend>
						国防生卫生检查
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?gfs=yes',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
							</td>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly = "true" />
							</td>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj"  readonly = "true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly = "true"/>
							</td>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								学年：
							</td>
							<td align="left">
								<html:text name='rs' property="xn" styleId="xn" readonly = "true"/>
							</td>
							<td align="right">
								学期：
							</td>
							<td align="left">
								<html:text name='rs' property="xq" styleId="xq" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly = "true"/>
							</td>
							<td align="right">
								宿舍号：
							</td>
							<td align="left">
								<html:text name='rs' property="ssbh" styleId="ssbh" readonly = "true"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>周数：
							</td>
							<td align="left">
								<html:select name="rs" property="zs"  styleId="zs">
									<html:option value=""></html:option>
									<html:options collection="zsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>检查时间：
							</td>
							<td align="left">
								<html:text name='rs' property="jcsj" styleId="jcsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jcsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>卫生情况：
							</td>
							<td align="left">
								<html:select name="rs" property="wsqk"  styleId="wsqk">
									<html:option value=""></html:option>
									<html:options collection="wsPfList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							<td align="right">
								<font color="red">*</font>连队名称：
							</td>
							<td align="left">
								<html:select name="rs" property="lddm"  styleId="lddm">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								备注:
							</td>
							<td colspan="3">
								<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width:100%"
								onblur="chLeng(this,100)"/>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2"
						onclick="saveWs();"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("提交成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
