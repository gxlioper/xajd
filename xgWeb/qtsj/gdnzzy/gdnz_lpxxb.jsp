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
	<script language="javascript">
	
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
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
					value="xm-xb-xy-nj-zy-bj-sjhm-sfzh-xz" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-xz-sjhm-sfzh" />
				<input type="hidden" id="url" name="url"
					value="/qtsj/gdnzzy/gdnz_lpxxb.jsp" />
				<logic:present name="showXfzrx">
					<input type="hidden" id="type" name="type"
						value="<bean:write name="showXfzrx"/>" />
				</logic:present>
				<fieldset>
					<legend>
						培训信息维护
					</legend>
					<table width="100%" class="tbstyle" height="600">
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this);getInsureInfoExist();" />
								<button
									onclick="showTopWin('/xgxt/stu_info.do',750,550);getInsureInfoExist();"
									class="btn_01" id="buttonFindStu" style="display:none">
									选择
								</button>
							</td>
							<td align="right">
								<font color="red">*</font>年度：
							</td>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd" onchange="getInsureInfoExist();">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<td align="right">
								<font color="red">*</font>学年：
							</td>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								学期：
							</td>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								<font color="red">*</font>保险公司：
							</td>
							<td align="left">
								<html:select name="rs" property="bxgsdm" style="width:150px"
									styleId="bxgsdm">
									<html:option value=""></html:option>
									<html:options collection="bxgsList" property="bxgsdm"
										labelProperty="bxgsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<td align="right">
								保险期限：
							</td>
							<td align="left">
								<logic:present name="showXfzrx">
									<html:text name='rs' property='bxqx' styleId="bxqx"
										style="width:90px" maxlength="8" />（年）
							</logic:present>
								<logic:notPresent name="showXfzrx">
									<html:text name='rs' property='bxqx' styleId="bxqx"
										style="width:90px" maxlength="8"
										onblur="CheckSameOfInsureInfo('bxnx')" />（年）
							</logic:notPresent>
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<logic:notPresent name="showXfzrx">
							<td align="right">								
								保险档次：								
							</td>
							<td align="left">								
									<html:select property="bxdc" name="rs" styleId="bxdc"
										onchange="CheckSameOfInsureInfo('bxdc')">
										<html:options collection="bxdcList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>								
							</td>
							</logic:notPresent>
							<logic:present name="showXfzrx">
								<td align="right">
									保单号码：
								</td>
								<td align="left">
									<html:text property="bdhm" name="rs" />
								</td>
							</logic:present>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<logic:notPresent name="showXfzrx">
								<td align="right">
									保险金额：
								</td>
								<td align="left">
									<html:text property="bf" name="rs" styleId="bf"
										onblur="numFormatChk(this)"
										onblur="CheckSameOfInsureInfo('bf')"></html:text>
									(元)

								</td>
							</logic:notPresent>
							<logic:present name="showXfzrx">
								<td></td>
								<td></td>
							</logic:present>
						</tr>
						<tr style="height:22px">
							<td align="right">
								学制：
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" />
							</td>
							<td align="right">

							</td>
							<td align="left">

							</td>
						</tr>
						<tr>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" />
							</td>
							<td align="right">
								就诊医院：
							</td>
							<td align="left">
								<html:text property="jzyy" name="rs" />

							</td>
						</tr>
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" />
							</td>
							<td align="right">
								理赔金额：
							</td>
							<td align="left">
								<html:text property="lpje" name="rs" onblur="numFormatChk(this)" />
							</td>
						</tr>
						<tr>
							<td align="right">
								住院开始时间：
							</td>
							<td align="left">
								<html:text name='rs' property="zykssj" styleId="zykssj"
									onclick="return showCalendar('zykssj','y-mm-dd');"
									onblur="timeFormatChk(this)" />
							</td>
							<td align="right">
								住院结束时间：
							</td>
							<td align="left">
								<html:text property="zyjzsj" name="rs" styleId="zyjzsj"
									onclick="return showCalendar('zyjzsj','y-mm-dd');"
									onblur="timeFormatChk(this);checkTime();" />
							</td>
						</tr>
						<tr>
							<td align="right">
								送赔时间：
							</td>
							<td align="left">
								<html:text name='rs' property="spsj" styleId="spsj"
									onclick="return showCalendar('spsj','y-mm-dd');"
									onblur="timeFormatChk(this)" />
							</td>
							<td align="right">
								送赔金额：
							</td>
							<td align="left">
								<html:text property="spje" name="rs" styleId="spje"
									onblur="numFormatChk(this)" />
								(元)

							</td>
						</tr>
						<tr>
							<td align="right">
								申请理赔时间：
							</td>
							<td align="left">
								<html:text name='rs' property="sqlpsj" styleId="sqlpsj"
									onclick="return showCalendar('sqlpsj','y-mm-dd');"
									onblur="timeFormatChk(this)" />
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								申请理赔原因：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='sqlpyy' style="width:99%"
									rows='3' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								材料清单：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='clqd' style="width:99%"
									rows='3' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								不赔原因：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bpyy' style="width:99%"
									rows='3' />
							</td>
						</tr>
						<tr align="left">
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='3' />
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button class="button2" onclick="dataCanModi(true);"
						style="width:80px" id="buttonModi">
						修 改
					</button>
					<button class="button2"
						onclick="if(checkXnNd('xn','nd')){if(checkTime())dataDoSave('xh-nd-bxgsdm');}"
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
