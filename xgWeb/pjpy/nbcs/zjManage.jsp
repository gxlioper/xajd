<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type="text/javascript">	
	
	</script>
	<body onload="setWjstList();setTkList();getWjList();">
		<html:form action="/nbcsPjpy">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							学年：
						</td>
						<td align="left" width="35%">
							<html:select property="xn" style="" styleId="xn" onchange="getWjList()">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<td align="right" width="15%">
							<font color="red">*</font>问卷：
						</td>
						<td align="left">
							<html:select property="id" style="" styleId="id" onchange="setTkList();setWjstList();">
								<html:options collection="wjList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:select property="nd" style="" styleId="nd" onchange="getWjList()">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>
						</td>
						<td align="right">
							试题所属：
						</td>
						<td align="left">
							<html:select property="stss" style="width:120px" styleId="stss" onchange="setTkList();">
								<html:options collection="stssList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<html:select property="xq" style="" styleId="xq" onchange="getWjList()">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
						<td align="right">
							试题类型：
						</td>
						<td align="left">
							<html:select property="stlx" style="width:120px" styleId="stlx" onchange="setTkList();">
								<html:options collection="stlxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					组卷信息
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">提示：双击试题题目可以查看详细信息;按Shift或者Ctrl可以进行批量选择.</font>
				</legend>
				<table width="80%" border="0" align="center">
					<tr>
						<td width="10%" align="right">
							题<br><br><br>库
						</td>
						<td width="30%">
							<html:select property="xydm" style="width:100% " multiple="multiple"
								styleId="xyLeft" size="12" ondblclick="showStInfo(this.value)">
								<html:options collection="tkList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td width="10%">
							<button class="button2" style="width:100%" id="addBtn" onclick="addAllRightFrame('xyLeft','xyRight')">
							&gt;&nbsp;&gt;
							</button>
							<br />
							<br />
							<br />
							<button class="button2" style="width:100%" id="delBtn" onclick="addAllLeftFrame('xyLeft','xyRight')">
							&lt;&nbsp;&lt;
							</button>
						</td>
						<td width="5%" align="right">
							试<br>卷<br>题<br>目
						</td>
						<td width="30%">
							<html:select property="xh" style="width:100%" 
								styleId="xyRight" multiple="multiple"
								size="12" ondblclick="showStInfo(this.value)">
							</html:select>				
						</td>
					</tr>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="5">
						<button class="button2" id="buttonSave" onclick="saveZjInfo()"
							style="width: 80px">
							保&nbsp;&nbsp;存
						</button>
					</tr>
				</table>
			</fieldset>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');					
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
