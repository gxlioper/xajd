<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script language="javascript"  src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSjxyDtjsDAO.js'></script>
	<script type="text/javascript">	
	function getDzz(xydm){
		dwr.engine.setAsync(false);
		getSjxyDtjsDAO.getDzz(xydm,function(data){
			if(data !=null){
				if(data.dzzmc != null){
					$("dzzmc").value = data.dzzmc;
				}else{
					$("dzzmc").value = "";
				}
				if(data.xsrs != null){
					$("xsrs").value = data.xsrs;
				}else{
					$("xsrs").value = "";
				}
				if(data.dyrs != null){
					$("dyrs").value = data.dyrs;
				}else{
					$("dyrs").value = "";
				}
				if(data.khqk != null){
					$("khqk").value = data.khqk;
				}else{
					$("khqk").value = "";
				}
				if(data.sqrs != null){
					$("sqrs").value = data.sqrs;
				}else{
					$("sqrs").value = "";
				}
				if(data.bz != null){
					$("bz").value = data.bz;
				}else{
					$("bz").value = "";
				}
			}
		});
		dwr.engine.setAsync(true);
	}
	</script>
	<body onload="">
		<html:form action="/sjxyDtjsZbgl">
			<input type="hidden" id="doType" name="doType" value="${ doType}"/>
			<input type="hidden" id="realTable" name="realTable" value="${ realTable}"/>
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}"/>
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
							党总支信息
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						<font color="red">*</font>所属<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left" width="30%">
						<html:select name="rs" property="xydm" style="" styleId="xydm" onchange="getDzz(this.value)" >
							<html:option value="">-----请选择-----</html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<td align="right" width="20%">
						<font color="red">*</font>党组织名称：
					</td>
					<td align="left">
						<html:text name='rs' property="dzzmc" styleId="dzzmc" maxlength="25"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						学生人数：
					</td>
					<td align="left">
						<html:text name='rs' property="xsrs" styleId="xsrs" readonly="true"/>
					</td>
					<td align="right">
						党员人数：
					</td>
					<td align="left">
						<html:text name='rs' property="dyrs" styleId="dyrs" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						党员考核情况：
					</td>
					<td align="left">
						<html:text name='rs' property="khqk" styleId="khqk" maxlength="25"/>
					</td>
					<td align="right">
						申请入党人数：
					</td>
					<td align="left">
						<html:text name='rs' property="sqrs" styleId="sqrs" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						备注：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" onblur="chLeng(this,250)"/>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<logic:notEqual name="doType" value="view">
					<button type="button" class="button2" id="buttonSave"
						onclick="saveUpdate('/xgxt/sjxyDtjsZbgl.do?method=dzzUpdate&doType=save','xydm-dzzmc');"
						style="width: 80px">
						保	存
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
			<div id="tmpdiv1"></div>
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
