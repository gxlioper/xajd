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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type='text/javascript' src='/xgxt/dwr/interface/pjpy_countZhszcpzf_dwr'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/lrh_new_js.js"></script>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<script language="javascript">
		function lrh_countCpzf()
		{
			var xh=document.all['xh_l'].value;
			var xn=document.all['xn'].value;
			var nd=document.all['nd'].value;
			var dcj=document.all['dcj'].value;
			var zcj=document.all['zcj'].value;
			var tcj=document.all['tcj'].value;
			var rwszf=document.all['rwszf'].value;
			var cxysjszf=document.all['cxysjszf'].value;
			var zhszcpzf=document.all['zhszcpzf'].value;

				var zhszcp_model={
					xh:xh,xn:xn,nd:nd,dcj:dcj,zcj:zcj,tcj:tcj,
					rwszf:rwszf,cxysjszf:cxysjszf,zhszcpzf:zhszcpzf
				};
				pjpy_countZhszcpzf_dwr.pjpy_countZhszcpzf(zhszcp_model,count_zhszzf);
		}
		function count_zhszzf(data)
		{
			document.all['zhszcpzf'].value=data.zhszcpzf;
		}
		function zhszcpcj_save()
		{
			document.all['nd'].disabled=false;
			document.all['xn'].disabled=false;
			refreshForm('/xgxt/pjpy_zbdx_weihu_one.do?doType=save');
			document.all['nd'].disabled=true;
			document.all['xn'].disabled=true;
		}
	</script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
    %>
	<body onload="">
		<html:form action="/data_search" method="post">	
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
					当前所在位置：
					<bean:write name="tips" scope="request" />
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
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								综合素质测评信息维护
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh_l" readonly="true"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none">
								选择
							</button>
						</td>
						<td align="right">
							<font color="red">*</font>年度：
						</td>
						<td align="left">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd" disabled="true">
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
							<html:text name='rs' property="xm_l" styleId="xm" readonly="true"/>
						</td>
						<td align="right">
							<font color="red">*</font>学年：
						</td>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn" disabled="true">
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
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							德成绩：
						</td>
						<td align="left">
							<html:text name="rs" property="dcj" styleId="dcj" onblur="chkInput('dcj');lrh_countCpzf()"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
						<td align="right">
							智成绩：
						</td>
						<td align="left">
							<html:text name="rs" property="zcj" styleId="zcj" onblur="chkInput('zcj');lrh_countCpzf()"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:text name='rs' property="xymc_l" styleId="xy" readonly="true"/>
						</td>
						<td align="right">
							体成绩：
						</td>
						<td align="left">
							<html:text name="rs" property="tcj" styleId="tcj" onblur="chkInput('tcj');lrh_countCpzf()"/>
						</td>
					
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:text name='rs' property="zymc_l" styleId="zy" readonly="true"/>
						</td>
					
						<td align="right">
							人文素质成绩：
						</td>
						<td align="left">
							<html:text name="rs" property="rwszf" styleId="rwszf" onblur="chkInput('rwszf');lrh_countCpzf()"/>
						</td>
						
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc_l" styleId="bj" readonly="true"/>
						</td>
						
						<td align="right">
							创新与实践成绩：
						</td>
						<td align="left">
							<html:text name="rs" property="cxysjszf" styleId="cxysjszf"  onblur="chkInput('cxysjszf');lrh_countCpzf()" ></html:text>
						</td>
					
					</tr>
					<tr>
						<td align="right">
							综合素质测评总分：
						</td>
						<td align="left">
							<html:text name="rs" property="zhszcpzf" styleId="cpf" readonly="true"/>
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							备注：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="zhszcpcj_save()"
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
			<logic:notEmpty name="message">
					<logic:equal name="message" value="update_success">
						<script>
							alert("操作成功!");
							dialogArgumentsQueryChick();
						</script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
