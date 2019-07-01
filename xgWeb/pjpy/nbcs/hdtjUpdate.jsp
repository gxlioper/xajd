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
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">	

	</script>
	<body onload="">
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
					统计范围(若无范围的话为统计全校)
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							年级：
						</td>
						<td align="left" width="35%">
							<html:select property="nj" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<td align="right" width="15%">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:hidden name="rs" property="xq"/>
							<html:select property="xydm" style="" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td align="left">
							<html:select property="zydm" style="" styleId="zy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							<html:select property="bjdm" style="" styleId="bj" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:select property="xb" style="" styleId="xb" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							<html:select property="zzmm" style="" styleId="zzmm" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>
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
							<html:hidden name="rs" property="xn"/>
							${rs.xn }
						</td>
						<td align="right" width="15%">
							学期：
						</td>
						<td align="left">
							<html:hidden name="rs" property="xq"/>
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:hidden name="rs" property="nd"/>
							${rs.nd }
						</td>
						<td align="right">
							建立时间：
						</td>
						<td align="left">
							<html:hidden name="rs" property="jlsj"/>
							${rs.jlsjmc }
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					问卷信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>问卷名称：
							<br>
							<font color="red">(限50字)</font>
						</td>
						<td align="left" colspan="3">
							<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" readonly="true"/>			
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							备注：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz" readonly="true"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					统计信息（选择人次/总答卷人数）
				</legend>
				<table width="100%" class="tbstyle">
					<logic:iterate name="zjlxList" id="lx">
						<logic:notEqual name="lx" property="lxdm" value="questions">
							<thead>
								<td align="center" colspan="2">
									${lx.lxmc}
								</td>
							</thead>
							<!-- 单选题 -->
							<logic:equal name="lx" property="lxdm" value="oneChoose">
								<%@ include file="stxx/tjOneChoose.jsp"%>
							</logic:equal>
							<!-- 多选题 -->
							<logic:equal name="lx" property="lxdm" value="allChoose">
								<%@ include file="stxx/tjAllChoose.jsp"%>
							</logic:equal>
						</logic:notEqual>
					</logic:iterate>		
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 	
					<button class="button2" id="buttonClose" onclick="window.close();return false;"
						style="width: 80px" id="buttonClose">
							关 闭
					</button>
				</span>
			</div>
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
