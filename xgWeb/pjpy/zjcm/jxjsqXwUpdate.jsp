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
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	

	</script>
	
	<body onload="">
		<html:form action="/zjcmPjpy">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							校外奖学金申报
						</td>
					</tr>
				</thead>
					<tr>
						<td align="right" width="20%">
							学号：
						</td>
						<td align="left" width="30%">
							<html:text name="rs" property="xh" style="" readonly="true"/>
						</td>
						<td align="right" width="20%">
							评奖学年：
						</td>
						<td align="left" width="30%">
							<html:hidden name="rs" property="xn"/>
							<html:select name="rs" property="xn" style="" onchange="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>			
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>
						<td align="right">
							评奖学期：
						</td>
						<td align="left">
							<html:hidden name="rs" property="xq"/>
							<html:select name="rs" property="xq" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>
						<td align="right">
							<font color="red">*</font>申请奖学金(校外)：
						</td>
						<td align="left">
							<html:select name="rs" property="jxjdm" style="" onchange="" >
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							${rs.xymc }
						</td>
						<td align="right">
							有无校级通报：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="xjtb"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>
						<td align="right">
							处分情况：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="wjInfo"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							旷课节数：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="kkjs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							有无拖欠学费：
						</td>
						<td align="left">
							
						</td>
						<td align="right">
							学习成绩不及格门数：
						</td>
						<td align="left">
							${otherRs.bjgs }
						</td>
					</tr>
					<tr>
						<td align="right">
							英语等级考试成绩：
						</td>
						<td align="left">
							${otherRs.yycj }
						</td>
						<td align="right">
							计算机等级考试成绩：
						</td>
						<td align="left">
							${otherRs.pccj }
						</td>
					</tr>
					<tr>
						<td align="right">
							德育分：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="dyf"/>(<bean:write name="otherRs" property="dyzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="dypm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							智育分：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="zyf"/>(<bean:write name="otherRs" property="zyzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="zypm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							体育分：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="tyf"/>(<bean:write name="otherRs" property="tyzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="typm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							能力分：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="nlf"/>(<bean:write name="otherRs" property="nlzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="nlpm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							综合分：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="zhf"/>
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="otherRs" property="zhpm"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							辅导员审核意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="rs" property="fdyyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="rs" property="xyyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							学校审核意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="rs" property="xxyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/zjcmPjpy.do?method=jxjsqXwUpdate&doType=save','jxjdm')"
						style="width: 80px">
						保	存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="Close();return false;"
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
