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
	function changerecord(url,lx){
		var sbznum = "0";
		url += "&lx="+lx;
		if($("sbznum")){
			sbznum = $("sbznum").value;
		}
		for(var i = 0;i<sbznum;i++){
			if($("sbzxh"+i)){
				url+="&sbzxh"+i+"="+$("sbzxh"+i).value;
			}
		}
		refreshForm(url);
	}
	
function shot(obj){
	var xhnum = $("xhnum").value;
	var id = "checkVal"+ xhnum;
	var fatherObj = window.dialogArguments.document.getElementById(id);
	fatherObj.checked = obj.checked;
}
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
							荣誉称号申请
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
						<td align="right">
							所申请奖学金：
						</td>
						<td align="left">
							<html:hidden name="rs" property="rychdm"/>
							<html:select name="rs" property="rychdm" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="rychList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:select name="rs" property="xydm" style="" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
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
							<html:select name="rs" property="zydm" style="" styleId="zy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
						<td align="right">
							处分情况：
						</td>
						<td align="left">
							<bean:write name="rs" property="wjInfo" />
						</td>
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:select name="rs" property="bjdm" style="" styleId="bj" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
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
							<bean:write name="rs" property="kkjs"/>
						</td>
						<td align="right">
							学习成绩不及格门数：
						</td>
						<td align="left">
							<bean:write name="rs" property="kkjs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							英语等级考试成绩：
						</td>
						<td align="left">
							<bean:write name="rs" property="kkjs"/>
						</td>
						<td align="right">
							计算机等级考试成绩：
						</td>
						<td align="left">
							<bean:write name="rs" property="kkjs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							上学期获得荣誉：
						</td>
						<td align="left">
							<bean:write name="rs" property="sxqry"/>
						</td>
						<td align="right">
							本学期获得荣誉：
						</td>
						<td align="left">
							<bean:write name="rs" property="bxqry"/>
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
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:equal value="theFirst" name="swbj">
						<input   id="up" value="上一条" disabled="true">
					</logic:equal>
					<logic:notEqual value="theFirst" name="swbj">
						<input  id="up" value="上一条" onclick="showTips('刷新数据中，请稍候...');changerecord('/xgxt/zjcmPjpy.do?method=rychsqUpdate','before');">
					</logic:notEqual>
					&nbsp; &nbsp;
					<logic:equal value="theLast" name="swbj">
						<input   id="next" value="下一条" disabled="true">
					</logic:equal>
					<logic:notEqual value="theLast" name="swbj">
						<input  id="next" value="下一条" onclick="showTips('刷新数据中，请稍候...');changerecord('/xgxt/zjcmPjpy.do?method=rychsqUpdate','next')">
					</logic:notEqual>
					&nbsp; &nbsp;
					<input   id="next" value="关&nbsp闭" onclick="Close();return false;"/>
					&nbsp; &nbsp;&nbsp; &nbsp;
					<logic:equal value="true" name="sel">
						<input type="checkbox" id="selected" onclick="shot(this);" checked="true"/>&nbsp;选中
					</logic:equal>
					<logic:notEqual value="true" name="sel">
						<input type="checkbox" id="selected" onclick="shot(this);"/>&nbsp;选中
					</logic:notEqual>
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
