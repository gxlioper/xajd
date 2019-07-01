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
			当前所在位置：评奖评优 - 奖学金审核 - 桑麻奖学金
		</div>
	</div>
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
							政治面貌
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							担任职务
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="drzw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							生源省市
						</div>
					</td>
					<td colspan="2"><bean:write name="rs" property="jgs"/>
<%--						<html:text name="rs" property="jgs" readonly="true"></html:text>--%>
						<input id="jgs" name="jgs" type="text"" value="<bean:write name="rs" property="jg"/>"/>
					</td>
					<td>
						<div align="center">
							特长
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="tc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							农村城镇
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="nccz" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							综合测评名次
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zhszcpcjpm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						专业名次
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zhuanypm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							申 报 等 级
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="sbdj" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
						第几次获得桑麻奖学金
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="djchdjxj" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>获<br>奖<br>情<br>况<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="jfqk" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							香港<br>桑麻<br>基金<br>会简<br>介
						</div>
					</td>
					<td colspan="8">
						香港桑麻基金会是著名实业家、爱国人士、香港特别行政区大紫荆勋章获得
						<br>者查济民先生所创建。查济民先生是我国纺织界耆宿，自三十年代初至今，
						<br>为发展纺织工业倾注了大量心力，成绩卓著。他以一位成功实业家的经验和
						<br>智慧，看到科技进步和培养人才是促进我国纺织工业开拓和发展的根本问
						<br>题，因此，他于一九九二年率先出资成立了桑麻基金会，并亲自担任基金会
						<br>主席，旨在缅怀我国纺织工业起源于桑麻，用以激励国内纺织界从业人员及
						<br>在培学子发扬爱国主义精神，重视科技，努力钻研，振兴中华纺织事业，基
						<br>金会先后在天津纺织工<bean:message key="lable.xsgzyxpzxy" />、东华大学（原中国纺织大学）、浙江理工大学
						<br>（原浙江丝绸工<bean:message key="lable.xsgzyxpzxy" />）和北京服装<bean:message key="lable.xsgzyxpzxy" />设立了桑麻奖学金，对奖励先进、发
						<br>掘人才提供了有利条件，促进了纺织教育事业的发展。
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							本<br>人<br>申<br>请
						</div>
					</td>
					<td colspan="8">
					（本人申请应包括：本人德智体诸方面的表现、担任社会工作及参加社会活动情况、申请奖学金的动机及努力目标，并简要说明家庭情况。）
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
							<br>班<br>级<br>辅<br>导<br>员<br>意<br>见<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="fdyyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br><bean:message key="lable.xsgzyxpzxy" /><br>审核<br>意见<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="xyshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>桑麻<br>奖学<br>评金<br>审评<br>意审<br>见委<br>员会<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea name="rs" property="lshshyj" rows='6' style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>备<br>注<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%;display: none"  readonly="true"/>
					1.本表一式二份，表中填写内容要求电脑打印；<br>
					2.学校、院、系班级不可缩写。
					
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
