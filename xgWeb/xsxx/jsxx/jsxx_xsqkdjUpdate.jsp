<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script type="text/javascript">	

	function saveQk(){
		var url = "/xgxt/stu_info_add.do?method=jsxxXsdjUpdate&doType=save";
		showTips('处理数据中，请等待......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	</script>
	<body onload="">
		<html:form action="/stu_info_add">
		<html:hidden name='rs' property="rxnj" styleId="rxnj" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center" style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							学生综合素质情况
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<html:text name='stuInfo' property="xh" styleId="xh" readonly="true" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<html:text name='stuInfo' property="xymc" styleId="xymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<html:text name='stuInfo' property="xm" styleId="xm" readonly="true"/>
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
						<html:text name='stuInfo' property="zymc" styleId="zymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<html:text name='stuInfo' property="xb" styleId="xb" readonly="true"/>
					</td>
					<td align="right">
						班级：
					</td>
					<td align="left">
						<html:text name='stuInfo' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
			</table>
			<table class="tbstyle" border="0" id="rsTable" align="center" style="width: 100%">
				<tr>
					<td width="5%">
						年级
					</td>
					<td width="10%">
						学期
					</td>
					<td width="10%">
						德育
					</td>
					<td width="10%">
						智育
					</td>
					<td width="10%">
						体育
					</td>
					<td width="10%">
						综合测评
					</td>
					<td width="10%">
						名次
					</td>
					<td width="35%">
						学年奖惩情况及任职情况
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						一年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<html:text name="rs" property="yydcj" styleId="yydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yyzcj" styleId="yyzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yytcj" styleId="yytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yykpf" styleId="yykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yybjpm" styleId="yybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="yqk" styleId="yqk" rows="3" style="width:100%;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<html:text name="rs" property="yrdcj" styleId="yrdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yrzcj" styleId="yrzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yrtcj" styleId="yrtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yrkpf" styleId="yrkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="yrbjpm" styleId="yrbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						二年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<html:text name="rs" property="rydcj" styleId="rydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="ryzcj" styleId="ryzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rytcj" styleId="rytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rykpf" styleId="rykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rybjpm" styleId="rybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="rqk" styleId="rqk" rows="3" style="width:100%;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<html:text name="rs" property="rrdcj" styleId="rrdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rrzcj" styleId="rrzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rrtcj" styleId="rrtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rrkpf" styleId="rrkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="rrbjpm" styleId="rrbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						三年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<html:text name="rs" property="sydcj" styleId="sydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="syzcj" styleId="syzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="sytcj" styleId="sytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="sykpf" styleId="sykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="sybjpm" styleId="sybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="sqk" styleId="sqk" rows="3" style="width:100%;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<html:text name="rs" property="srdcj" styleId="srdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="srzcj" styleId="srzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="srtcj" styleId="srtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="srkpf" styleId="srkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="srbjpm" styleId="srbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						四年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<html:text name="rs" property="xydcj" styleId="xydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xyzcj" styleId="xyzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xytcj" styleId="xytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xykpf" styleId="xykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xybjpm" styleId="xybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="xqk" styleId="xqk" rows="3" style="width:100%;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<html:text name="rs" property="xrdcj" styleId="xrdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xrzcj" styleId="xrzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xrtcj" styleId="xrtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xrkpf" styleId="xrkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="xrbjpm" styleId="xrbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td rowspan="2">
						五年级
					</td>
					<td>
						上学期
					</td>
					<td>
						<html:text name="rs" property="fydcj" styleId="fydcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="fyzcj" styleId="fyzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="fytcj" styleId="fytcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="fykpf" styleId="fykpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="fybjpm" styleId="fybjpm" style="width:100%" readonly="true"/>
					</td>
					<td rowspan="2">
						<html:textarea name="rs" property="fqk" styleId="fqk" rows="3" style="width:100%;" onblur="chLeng(this,200)"/>
					</td>
				</tr>
				<tr>
					<td>
						下学期
					</td>
					<td>
						<html:text name="rs" property="frdcj" styleId="frdcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="frzcj" styleId="frzcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="frtcj" styleId="frtcj" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="frkpf" styleId="frkpf" style="width:100%" readonly="true"/>
					</td>
					<td>
						<html:text name="rs" property="frbjpm" styleId="frbjpm" style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="8">
					<button type="button" class="button2" id="buttonSave" onclick="saveQk()"
						style="width: 80px">
						保	存
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
