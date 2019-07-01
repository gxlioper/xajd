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
			当前所在位置：评奖评优 - 奖学金审核 -  纺织之光奖学金
		</div>
	</div>
		<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" id="jxjcxzj" value="<bean:write name="jxjcxzj"/>" />
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
						<td align="center" colspan="1">
							<font color="red">*</font>学号
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							姓名
						</div>
					</td>
					<td colspan="4" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							学年
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							学期
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							出生年月
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xymc" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							专业
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zymc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							民族
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="mz" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							入学日期
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="rxrq" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						</div>
					</td>
					<td colspan="4">
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							担任<br>社会<br>工作<br>情况
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="kycg" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							何时<br>何地<br>受何<br>表彰
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							主要<br>事迹
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="zysj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							学校<br>评审<br>意见
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xxshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							理事<br>会审<br>批意<br>见
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="lshshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				
				<tr style="display: none">
					<td colspan="1" rowspan="1">
						<div align="center">
							备<br>注
						</div>
					</td>
					<td colspan="8">
					<html:textarea name="rs" property="bz" rows='6' style="width:100%"  readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>备<br>注<br>
						</div>
					</td>
					<td colspan="8">
						1. 本表一式二份。 <br>
						2. 学校、院、系、专业不可缩写。<br>
						3．在“专业”栏目中，注明高职、本科、硕士或博士。
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
