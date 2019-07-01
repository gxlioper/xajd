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
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<html:form action="/stu_active_InfoManage" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
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
				<input type="hidden" id="url" name="url" value="/shgc/xsgygl_xszsxx.jsp" />
				<fieldset>
					<legend>
						学生活动信息维护
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>活动主题：
							</td>
							<logic:present name="modi">
								<td align="left" colspan="3">
									<html:text name='rs' property="hd_zt" readonly="true"
										styleId="zt"  style="width:99%"/>

								</td>
							</logic:present>
							<logic:notPresent name="modi">
								<td align="left" colspan="3">
									<html:text name='rs' property="hd_zt" readonly="readonly"
										styleId="zt"  style="width:99%"/>

								</td>
							</logic:notPresent>
						</tr>
						<tr>
							<td align="right">
								活动性质：
							</td>
							<logic:present name="xshdjl">
								<td align="left" colspan="3">
									<html:text name='rs' property="hd_xz" styleId="xz"  readonly="true" style="width:99%"/>
								</td>
							</logic:present>
							<logic:notPresent name="xshdjl">
								<td align="left" colspan="3">
									<html:text name='rs' property="hd_xz" styleId="xz"  style="width:99%"/>
								</td>
							</logic:notPresent>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>活动内容：
							</td>
							<logic:present name="xshdjl">
								<td align="left" colspan="3">
									<html:text name='rs' property="hd_nr" styleId="nr" readonly="true" style="width:99%"/>
								</td>
							</logic:present>
							<logic:notPresent name="xshdjl">
								<td align="left" colspan="3">
									<html:text name='rs' property="hd_nr" styleId="nr"  style="width:99%"/>
								</td>
							</logic:notPresent>
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>活动时间：
							</td>
							<logic:present name="modi">
							  <td align="left">
								<html:text name='rs' property="hd_sj" styleId="sj" readonly="true"/>
							  </td>
							</logic:present>
							<logic:notPresent name="modi">
							  <td align="left" style="width: 43%">
								<html:text name='rs' property="hd_sj" styleId="sj" style="width: 90px"  onclick="return showCalendar('sj','y-mm-dd');" readonly="true"/> －
								<input type="text" name="sj_hour" id="sj_hour" style="width: 23px" /> ：
								<input type="text" name="sj_minute" id="sj_minute" style="width: 23px" /> ：
								<input type="text" name="sj_second" id="sj_second" style="width: 23px" />
							  </td>
							</logic:notPresent>
							<td align="right">
								<font color="red">*</font>活动地点：
							</td>
							<logic:present name="xshdjl">
								<td align="left">
									<html:text name='rs' property="hd_dd" readonly="true" styleId="dd"/>
								</td>
							</logic:present>
							<logic:notPresent name="xshdjl">
								<td align="left">
									<html:text name='rs' property="hd_dd" styleId="dd"/>
								</td>
							</logic:notPresent>
						</tr>
						<logic:present name="xshdjl">
							<tr>
								<td align="right">
									参与人数：
								</td>
							    <td align="left">
									<html:text name='rs' property="hd_cyrs" styleId="cyrs"/>
							  	</td>
								<td align="right">
									楼层长参与情况：
								</td>
								<td align="left">
									<html:text name='rs' property="hd_lczcyqk" styleId="lczcyqk"/>
								</td>
							</tr>
						</logic:present>
						<tr align="left">
							<td align="right">
								活动对象：
							</td>
							<logic:present name="xshdjl">
								<td colspan="3">
									<html:textarea name='rs' property='hd_dx' readonly="true" styleId="dx" style="width:99%" />
								</td>
							</logic:present>
							<logic:notPresent name="xshdjl">
								<td colspan="3">
									<html:textarea name='rs' property='hd_dx' styleId="dx" style="width:99%" />
								</td>
							</logic:notPresent>
						</tr>
						<logic:present name="xshdjl">
							<tr align="left">
								<td align="right">
									现场气氛：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='hd_xcqf' styleId="xcqf" style="width:99%" />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									效果评估：
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='hd_xgpg' styleId="xgpg" style="width:99%" />
								</td>
							</tr>
						</logic:present>
						<tr>
							<td colspan="4"><font color="red">时间格式：2007-06-27-02:03:04</font></td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2" onclick="dataCanModi(true)"
						style="width:80px;display: none;" id="buttonModi">
						修 改
					</button>
					<button class="button2"
						onclick="Savedata('zt-nr-sj-dd-sj_hour-sj_minute-sj_second','/xgxt/stuActive_DataModi.do');"
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
		</html:form>
	</body>
</html>
