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
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：评奖评优 - 奖学金审核 - 国家奖学金
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内或不符合申请条件！！！
			</p>
		</center>
	</logic:equal>
		<html:form action="/zjlgPjpy" method="post">
		<TABLE width="99%" id="rsTable1" class="tbstyle">
			<TR>
				<TD>
					<jsp:include flush="true" page="/pjpy/zjlg/jxjsq/jxjsh/jxjShDh.jsp"></jsp:include>
				</TD>
			</TR>
		</TABLE>
		<table class="tbstyle" width="90%">
				<tr>
					<td align="center" colspan="1" style="width: 120px">
						<font color="red">*</font>奖学金名称
					</td>
					<td colspan="2">
						<html:select name="rs" property="jxjdm" style="width:180px;display:none"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
						<html:text name="rs" property="jxjmc" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							奖学金类别
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="jxjlbmc" readonly="true"></html:text>
						<html:text name="rs" property="jxjlb" readonly="true" style="display: none"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row" rowspan="10">
						<div align="center">
							<b>基<br>本<br>情<br>况</b>
						</div>
					</td>
						<td align="center" colspan="1">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="1">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="1" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							民族 
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="mz" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
					<td colspan="1" nowrap="nowrap">
						<div align="center">
							入学时间
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="rxrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" nowrap="nowrap">
						<div align="center">
							身份证号
						</div>
					</td>
					<td colspan="5" nowrap="nowrap">
						<html:text name="rs" property="sfzh" readonly="true" style="width: 100%"></html:text>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							银行卡号
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="yhkh" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							银行类型
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="yhlx" readonly="true"></html:text>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="1">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							联系电话 
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="lxdh" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="height:22px">
					<td scope="row">
					<div align="center">
						综测成绩
						</div>
					</td>
					<td>
						<bean:write name='rs' property="zhcj" />
					</td>
					<td >
					<div align="center">
						综测排名
						</div>
					</td>
					<td align="left" colspan="4">
						<bean:write name='rs' property="zcbjpm" />
					</td>
					
				</tr>
				<tr>
				<td >
				<div align="center">
						德育
						</div>
					</td>
					<td >
						<bean:write name='rs' property="dycj" />
					</td>
				<td >
				<div align="center">
						德育排名
						</div>
					</td>
					<td  colspan="4">
						<bean:write name='rs' property="dypm" />
					</td>	
				
				</tr>
					<tr>
				<td >
				<div align="center">
						智育
						</div>
					</td>
					<td >
						<bean:write name='rs' property="zycj" />
					</td>
				    <td >
				    <div align="center">
						智育排名
						</div>
					</td>
					<td colspan="4">
						<bean:write name='rs' property="zypm" />
					</td>
				
				</tr>
				<tr >
					
					<td >
					<div align="center">
						体育
						</div>
					</td>
					<td>
						<bean:write name='rs' property="tycj" />
					</td>
				    <td  >
				    <div align="center">
						英语等级成绩
						</div>
					</td>
					<td  colspan="4">
						<bean:write name='rs' property="yydjcj" />
					</td>
				</tr>
					<tr >
					<td >
					<div align="center">
						计算机成绩
						</div>
					</td>
					<td >
						<bean:write name='rs' property="jsjdjcj" />
					</td>
					  <td >
					  <div align="center">
					  不及格课程数
					  </div>
					</td>
					<td  colspan="4">
					<bean:write name='rs' property="bjg" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b>学<br>习<br>等<br>情<br>况</b>
						</div>
					</td>
					<td colspan="8">
<%--						本学年必修课程     门，其中，优秀     门，良好     门<br>--%>
<%----%>
<%--						成绩排名（专业或年级）：              （名次/总人数）<br>--%>
<%----%>
<%--						综合考评成绩     分，排名            （名次/总人数）<br>--%>
						<html:textarea name="rs" property="xxjl" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>获<br>奖<br>情<br>况<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							<b><br>申<br>请<br>理<br>由<br></b>
						</div>
					</td>
					<td colspan="8">
						（全面反映德智体美情况）
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<html:textarea name="rs" property="sqly" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>年级<br>（专业）<br>推荐意见<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="fdyyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>学<br>院<br>意<br>见<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<b><br>学<br>校<br>意<br>见<br></b>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xxshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
			</table>
<%--	<div class="buttontool" align="center">--%>
<%--				<button type="button" class="button2" id="buttonSave" onclick="jxjSqSavett();">--%>
<%--					提 交 申 请--%>
<%--				</button>--%>
<%--				&nbsp;&nbsp;--%>
<%--			</div>--%>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			        </script>
			</logic:equal>

		</html:form>
</body>
</html:html>
