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
			<input type="hidden" id="sbznum" name=sbznum value="${sbznum}"/>
			<input type="hidden" id="xhnum" name=xhnum value="${xhnum}"/>
			<logic:present name="sbzxh">
			<logic:iterate name="sbzxh" id="num" indexId="index">
				<input type="hidden" id="sbzxh${index }" name="sbzxh" value="${num}"/>
			</logic:iterate>
			</logic:present>
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
							详细信息
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
							<html:text name="rs" property="xm" style="" readonly="true"/>
						</td>
						<td align="right">
							评奖学期：
						</td>
						<td align="left">
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
							<html:select name="rs" property="nj" style="" styleId="nj" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<logic:equal name="lx" value="jxj">
							<td align="right">
								所申请奖学金：
							</td>
							<td align="left">
								<html:hidden name="rs" property="jxjdm"/>
								<html:select name="rs" property="jxjdm" style="" onchange="" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</logic:equal>
						<logic:equal name="lx" value="rych">
							<td align="right">
								所申请荣誉称号：
							</td>
							<td align="left">
								<html:select name="rs" property="rychdm" style="" onchange="" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</logic:equal>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							有无校级通报：
						</td>
						<td align="left">
							<bean:write name="rs" property="xjtb"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td align="right">
							处分情况：
						</td>
						<td align="left">
							<bean:write name="rs" property="wjInfo"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td align="right">
							旷课节数：
						</td>
						<td align="left">
							<bean:write name="rs" property="kkjs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							有无拖欠学费：
						</td>
						<td align="left">
							<bean:write name="rs" property="tqxf"/>
						</td>
						<td align="right">
							学习成绩不及格门数：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjgs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							英语等级考试成绩：
						</td>
						<td align="left">
							<bean:write name="rs" property="yycj"/>
						</td>
						<td align="right">
							计算机等级考试成绩：
						</td>
						<td align="left">
							<bean:write name="rs" property="pccj"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							德育分：
						</td>
						<td align="left">
							<bean:write name="rs" property="dyf"/>(<bean:write name="rs" property="dyzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="rs" property="dypm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							智育分：
						</td>
						<td align="left">
							<bean:write name="rs" property="zyf"/>(<bean:write name="rs" property="zyzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="rs" property="zypm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							体育分：
						</td>
						<td align="left">
							<bean:write name="rs" property="tyf"/>(<bean:write name="rs" property="tyzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="rs" property="typm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							能力分：
						</td>
						<td align="left">
							<bean:write name="rs" property="nlf"/>(<bean:write name="rs" property="nlzhf"/>)
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="rs" property="nlpm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							综合分：
						</td>
						<td align="left">
							<bean:write name="rs" property="zhf"/>
						</td>
						<td align="right">
							班级排名：
						</td>
						<td align="left">
							<bean:write name="rs" property="zhpm"/>
						</td>
					</tr>
					<logic:equal name="lx" value="jxj">
						<tr>
							<td align="right" style="">
								辅导员审核意见：
							</td>
							<td align="left" colspan="3">
								<html:textarea  name="stuInfo" property="fdyyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />审核意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="stuInfo" property="xyyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							学校审核意见：
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="stuInfo" property="xxyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td align="left" colspan="3">
							<html:textarea name="stuInfo" property="bz" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
						<button class="button2" id="buttonClose" onclick="Close();return false;"
							style="width: 80px">
							关	闭
						</button>
					</td>
				</tr>
			</table>
			
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
