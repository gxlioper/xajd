<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/dispatch" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 审核 - 其它审核汇总</a>
				</p>
			</div>
			
				<logic:equal value="12872" name="xxdm">
				<div class="tab">
					<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="">
						<th colspan="4">
							<span>单个审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="">
					<th align="right">
						班级
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						班长
					</th>
					<td align="left">
						<bean:write name="rs" property="bzxm"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						学生人数
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xsrs"/>
					</td>
					<th align="right">
						班主任
					</th>
					<td align="left">
						<bean:write name="rs" property="bzr"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						学年
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xn"/>
					</td>
					<th align="right">
						学期
					</th>
					<td align="left">
						<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						审核
					</th>
					<td align="left" id="">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
					
					<logic:equal value="xy" name="userType">
						<th align="right">
						(系)院签名
					</th>
					<td align="left">
						<html:text property="xyqm" name="rs" styleId="xyqm"></html:text>
					</td>
						
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
					</logic:notEqual>
					
				</tr>
				
				<tr style="">
					<th align="right">
						主要事迹
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="zysj" rows="8" style="width:98%"></html:textarea>
					</td>	
				</tr>
				<tr style="">
					<th align="right">
						审核意见
					</th>
					<td align="left" colspan="3">
						<logic:notEqual value="xy" name="userType">
							<textarea name="xxyj" id="xxyj" rows="3" style="width:98%"><logic:equal value="" name="xxyj">同意</logic:equal><logic:notEqual value=" " name="xxyj">${xxyj}</logic:notEqual></textarea>
							
						</logic:notEqual>
						<logic:equal value="xy" name="userType">
							<textarea name="xyyj" id="xyyj" rows="3" style="width:98%"><logic:equal value="" name="xyyj">同意推荐</logic:equal><logic:notEqual value=" " name="xyyj">${xyyj}</logic:notEqual></textarea>
						</logic:equal>
					</td>	
				</tr>
				</tbody>
			</table>
			</div>
				</logic:equal>
			<logic:notEqual value="12872" name="xxdm">
			<logic:equal value="10863" name="xxdm">
			<div class="tab">
			<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="">
						<th colspan="4">
							<span>单个审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="">
					<th align="right" style="width:20%">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" id="" style="width:30%">
						<bean:write name="rs" property="xymc"/>
					</td>
					<th align="right" style="width:20%">
						专业
					</th>
					<td align="left" style="width:30%">
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						班级
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						班长
					</th>
					<td align="left">
						<bean:write name="rs" property="bzxm"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						学生人数
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xsrs"/>
					</td>
					<th align="right">
						班主任
					</th>
					<td align="left">
						<bean:write name="rs" property="bzr"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						学年
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xn"/>
					</td>
					<th align="right">
						团支书
					</th>
					<td align="left">
						${rs.tzs }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						班内有无&nbsp;&nbsp;&nbsp;<br/>不合格寝室
					</th>
					<td align="left" id="">
						${rs.bhgqs }
					</td>
					<th align="right">
						有无同学受过&nbsp;&nbsp;&nbsp;<br/>行政,党,团纪处分
					</th>
					<td align="left">
						${rs.ywcf }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						班级荣誉
					</th>
					<td align="left" colspan="3">
						${rs.bjry }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						主要成绩
					</th>
					<td align="left" colspan="3" >
						${rs.zysj }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						审核
					</th>
					<td align="left" colspan="3">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<logic:equal value="xy" name="userType">
					<tr style="">
					<th align="right" >
						<bean:message key="lable.xsgzyxpzxy" />党总支意见
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="yxdzbyj" styleId="yxdzbyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<tr style="">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />学工部意见
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="xxyj" styleId="xxyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
					</tr>
					<tr style="">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</td>
					<th align="left" colspan="3">
						<html:textarea name="rs" property="xyyj" styleId="xyyj" style="width:95%" rows="5">
						</html:textarea>
					</th>
					</tr>
				</logic:notEqual>
				</tbody>
			</table>
			</div>
			</logic:equal>
				<logic:notEqual value="10863" name="xxdm">
					<div class="tab">
					<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="">
						<th colspan="4">
							<span>单个审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="">
					<th align="right" style="width:20%">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" id="" style="width:30%">
						<bean:write name="rs" property="xymc"/>
					</td>
					<th align="right" style="width:20%">
						专业
					</th>
					<td align="left" style="width:30%">
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						班级
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						班长
					</th>
					<td align="left">
						<bean:write name="rs" property="bzxm"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						学生人数
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xsrs"/>
					</td>
					<th align="right">
						班主任
					</th>
					<td align="left">
						<bean:write name="rs" property="bzr"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						学年
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xn"/>
					</td>
					<th align="right">
						审核
					</th>
					<td align="left" colspan="">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<logic:equal value="12682" name="xxdm">
				<tr style="">
					<th align="right">
						集体称号
					</th>
					<td align="left" colspan="3">
						${rs.jtch }
					</td>	
				</tr>
				</logic:equal>
				<tr style="">
					<th align="right">
						主要事迹
					</th>
					<td align="left" id="" colspan="3" height="70px">
						<html:textarea property="zysj" name="rs" rows="4" style="width:95%" readonly="true"></html:textarea>
					</td>
				</tr>
				<logic:equal value="10355" name="xxdm">
				<tr style="">
					<th align="right">
						备注<font color="red">(班级学生<br/>受处分,旷课,<br/>体育达标情况)</font>
					</th>
					<td align="left" id="" colspan="3" height="70px">
					<html:textarea property="bz" name="rs" rows="4" style="width:95%" readonly="true"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<%--<tr style="">
					<td align="right">
						审核：
					</td>
					<td align="left" colspan="3">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				--%><logic:equal value="xy" name="userType">
					<tr style="">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见
					</th>
					<td align="left" colspan="3">
						<html:textarea property="xyyj" styleId="xyyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<tr style="">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />学工部意见
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="xxyj" styleId="xxyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
					</tr>
					
				</logic:notEqual>
				</tbody>
			</table>
			</div>
				</logic:notEqual>
				
			</logic:notEqual>
			
		
			          <div class="btn" align="right">
			          <logic:notEqual name="doType" value="view">
			          		<button name="提交" id="buttonSave" onclick="refreshForm('dispatch.do?method=xjbjwmbjSave');">保 存</button>
			          </logic:notEqual>
			            <button name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div>
			
		</html:form>
		<logic:present name="result">
			<logic:equal value="view" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script>
					alert("操作失败！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
