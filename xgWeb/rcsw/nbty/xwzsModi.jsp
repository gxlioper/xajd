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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">
		<html:form action="/bxgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务-校外住宿维护-校外住宿修改
				</div>
			</div>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xh-xm-nj-zymc-sjhm-jtdh" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xh-xm-nj-zymc-sjhm-jtdh" />
			<input type="hidden" id="url" name="url"
				value="/rcsw_xwzswh.do?method=xwzsAdd" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="${rs.pkValue}" />
			<fieldset>
				<legend>
					校外住宿维护
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								校外住宿维护
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							学号：
						</td>
						<td align="left">
							<html:text name='rs' property="save_xh" readonly="true"
								styleId="xh" />
						</td>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" disabled="true" />
						</td>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							手机号码：
						</td>
						<td align="left">
							<html:text name='rs' property="sjhm" styleId="sjhm"
								disabled="true" />
						</td>
						<td align="right">
							家庭电话：
						</td>
						<td align="left">
							<html:text name='rs' property="jtdh" styleId="jtdh"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>实习医院：
						</td>
						<td align="left">
							<html:text name="rs" property="save_sxyy" styleId="sxyy"
								maxlength="20">
							</html:text>
						</td>
						<td align="right">
							<font color="red">*</font>现租住详细地址：
						</td>
						<td align="left">
							<html:text name="rs" property="save_xzzxxdz" styleId="xzzxxdz"
								maxlength="20">
							</html:text>
						</td>

					</tr>

					<tr>

						<td align="right">
							实习医院电话：
						</td>

						<td align="left">
							<html:text name="rs" property="save_sxyydh" styleId="sxyydh"
								maxlength="20" onkeyup="value=value.replace(/[^\d|.]/g,'')">
							</html:text>
						</td>
						<td align="right">
							租住社区联系电话：
						</td>
						<td align="left">
							<html:text name="rs" property="save_zzsqlxdh" styleId="zzsqlxdh"
								maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') " />
						</td>
					</tr>

					<tr align="left">
						<td align="right">
							备注：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='save_bz' style="width:99%"
								rows='5' onblur="chLeng(this,250)" />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<logic:equal name="readOnly" value="false">
						<button type="button" class="button2"
							onclick="saveData('rcsw_xwzswh.do?method=xwzsModi&type=save','xh-sxyy-xzzxxdz')"
							style="width:80px" id="buttonSave">
							保 存
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>
			</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}" />
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
