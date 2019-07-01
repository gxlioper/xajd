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
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();">		
		<html:form action="/bxgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：其它数据 - 保险信息 - 投保信息增加
				</div>
			</div>
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/bxgl.do?method=tbxxAdd" />
				<fieldset>
					<legend>
						学生保险信息维护
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td colspan="4" align="center">
									学生保险信息维护
								</td>
							</tr>
						</thead>
						<tr>
							<td align="right">
								<font color="red">*</font>学号：
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="readonly"
									       styleId="xh" 
									       onkeypress="autoFillStuInfo(event.keyCode,this);" />
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="button2" 
									id="buttonFindStu">
									选择
								</button>
							</td>
							<td align="right">
								保险凭证号：
							</td>
							<td align="left">
								<html:text name='rs' property="save_bxpzh" styleId="bxpzh" maxlength="10"/>
								<!--默认审核通过-->
								<input type="hidden" name="save_xxsh" value="通过" />
								<!--end默认审核通过-->
							</td>
						</tr>
						<tr>
							<td align="right">
								姓名：
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<td align="right">
								<font color="red">*</font>年度：
							</td>
							<td align="left">
								<html:select name="rs" property="save_nd"
									styleId="nd" onchange="getInsureInfoExist();">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								性别：
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>保险公司：
							</td>
							<td align="left">
								<html:select name="rs" 
								             property="save_tbgsdm"
									         styleId="tbgsdm" 
									         onchange="loadBxxzList()">
									<html:option value=""></html:option>
									<html:options collection="bxgsdmList" property="bxgsdm"
										labelProperty="bxgsmc" />
								</html:select>
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
								<font color="red">*</font>投保险种：
							</td>
							<td align="left">
								<html:select name="rs" 
								             property="save_tbxzdm" 
								             styleId="tbxzdm" 
								             onchange="loadBxnxAndBf()">
									<html:option value=""></html:option>
									<logic:notEmpty name="tbxzdmList">
										<html:options collection="tbxzdmList" property="bxxzdm"
											labelProperty="bxxzmc" />
									</logic:notEmpty>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>投保日期：
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_tbsj" 
								           styleId="tbsj"
									       onblur="dateFormatChg(this)" 
									       onclick="return showCalendar('tbsj','y-mm-dd');"
									       style="cursor:hand;"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
							<td align="right">
								<font color="red">*</font>保险期限：
							</td>
							<td align="left">
								<html:text name="rs" property="save_bxnx" styleId="bxnx" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>(年)
							</td>	
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
							<td align="right">
								保费：
							</td>
							<td align="left">
								<html:text name='rs' property="save_bf" styleId="bf" style="width:90px" onkeyup="value=value.replace(/[^\d|.]/g,'') " maxlength="6"/>(元)
							</td>
						</tr>
						<tr>
							<td align="right">
								学制：
							</td>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>
						    <td align="right">
								退保日期：
							</td>
							<td align="left">
								<html:text name='rs' 
								           property="save_tuibsj" 
								           styleId="tuibsj"
									       onblur="dateFormatChg(this)" 
									       style="cursor:hand;"
									       onclick="return showCalendar('tuibsj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<td align="right">
								身份证号：
							</td>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<td align="right">
								退保标记：
							</td>
							<td align="left">
								<html:select name="rs" property="save_tbbj" style="width:90px"
									styleId="tbbj">
									<html:option value=""></html:option>
									<html:option value="未退">未退</html:option>
									<html:option value="已退">已退</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								手机号码：
							</td>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
							 <td align="right">
								缴费标记：
							</td>
							<td align="left">
								<html:select name="rs" property="save_jfbj" style="width:90px" styleId="jfbj">
								    <html:option value="否">否</html:option>
								    <html:option value="是">是</html:option>
								</html:select>
							</td>
						</tr>
						<!--广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />-->
						<logic:equal value="12742" name="xxdm">
						<tr>	
							<td align="right">
								保险档次：
							</td>							
							<td align="left">
								<html:select name="rs" property="save_bxdc" onchange="">
									<html:option value="">---请选择---</html:option>
									<html:options collection="bxdcList" property="dcdm" labelProperty="dcmc"/>
								</html:select>							
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						</logic:equal>
						<!--非广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />-->
						<tr align="left">
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='save_bz' style="width:99%"
									rows='5'  onblur="chLeng(this,250)"/>
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button class="button2" onclick="saveData('bxgl.do?method=tbxxAdd&type=add','xh-nd-tbxzdm-tbsj-bxnx-tbgsdm')"
							style="width:80px" id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
				</fieldset>
		</html:form>
		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
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
