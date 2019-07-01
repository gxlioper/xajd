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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
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
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/xszz/htxx.jsp" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								合同信息
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none">
								选择
							</button>
						</td>
						<td align="right">
							学校名称：
						</td>
						<td align="left">
							<input type="text" id="xxmc" name="xxmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xxmc" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<input type="text" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xm" />" readonly="true">
						</td>
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							<input type="text" id="sfzh" name="sfzh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="sfzh" />" readonly="true">
						</td>
					</tr>
					<tr>
						<td align="right">
							合同号：
						</td>
						<td align="left">
							<input type="text" id="hth" name="hth"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hth" />" >
						</td>
						<td align="right">
						</td>
						<td align="left">
						</td>
					</tr>
					<tr>
						<td align="right">
							合同经办金融机构：
						</td>
						<td align="left">
							<input type="text" id="htjbjrjg" name="htjbjrjg"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htjbjrjg" />" >
						</td>
						<td align="right">
							合同分支机构名称：
						</td>
						<td align="left">
							<input type="text" id="htfzjgmc" name="htfzjgmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htfzjgmc" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							合同批准日期：
						</td>
						<td align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('dkqxkssj','y-mm-dd');"
								value="<bean:write name='rs' property="htpzrq" />"
								name="htpzrq" id="htpzrq" />
						</td>
						<td align="right">
							合同总金额：
						</td>
						<td align="left">
							<input type="text" id="htzje" name="htzje"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htzje" />" 
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<td align="right">
							合同经办员：
						</td>
						<td align="left">
							<input type="text" id="htjby" name="htjby"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htjby" />" >
						</td>
						<td align="right">
							合同批准行长：
						</td>
						<td align="left">
							<input type="text" id="htpzhz" name="htpzhz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htpzhz" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							合同还款开始日期：
						</td>
						<td align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('dkqxkssj','y-mm-dd');"
								value="<bean:write name='rs' property="hthkksrq" />"
								name="hthkksrq" id="hthkksrq" />
						</td>
						<td align="right">
							合同还款截止日期：
						</td>
						<td align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('dkqxkssj','y-mm-dd');"
								value="<bean:write name='rs' property="hthkjzrq" />"
								name="hthkjzrq" id="hthkjzrq" />
						</td>
					</tr>
					<tr>
						<td align="right">
							合同展期理由：
						</td>
						<td align="left">
							<input type="text" id="htzqly" name="htzqly"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htzqly" />" >
						</td>
						<td align="right">合同展期时间：</td>
						<td align="left">
							<input type="text" id="htzqsj" name="htzqsj"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htzqsj" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							合同贷款方式：
						</td>
						<td align="left">
							<input type="text" id="htdkfs" name="htdkfs"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="htdkfs" />" >
						</td>
						<td align="right">合同还款方式：</td>
						<td align="left">
							<input type="text" id="hthkfs" name="hthkfs"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hthkfs" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							合同还款介质名称：
						</td>
						<td align="left">
							<input type="text" id="hthkjzmc" name="hthkjzmc"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hthkjzmc" />" >
						</td>
						<td align="right">合同还款介质帐号：</td>
						<td align="left">
							<input type="text" id="hthkjzzh" name="hthkjzzh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="hthkjzzh" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							合同拖欠应还本息金额：
						</td>
						<td align="left">
							<input type="text" id="httqyhbxje" name="httqyhbxje"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="httqyhbxje" />" 
								onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<td align="right">合同拖欠截止时间：</td>
						<td align="left">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('dkqxkssj','y-mm-dd');"
								value="<bean:write name='rs' property="httqjzsj" />"
								name="httqjzsj" id="httqjzsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							合同拖欠备注：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="httqbz" name="httqbz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="httqbz" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							贷款利率：
						</td>
						<td align="left">
							<input type="text" id="dkll" name="dkll"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="dkll" />" >
						</td>
						<td align="right">贷款类别：</td>
						<td align="left">
							<input type="text" id="dklb" name="dklb"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="dklb" />" >
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td align="left" colspan="3">
							<input type="text" id="bz" name="bz"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="bz" />" >
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="dataCanModi(true)"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button class="button2"
						onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh-nsepxmmc');"
						style="width:80px" id="buttonSave">
						保 存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
