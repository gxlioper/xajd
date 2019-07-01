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
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type="text/javascript">	
	function saveWj(){
	
		var kgkq = $("kgkq");
		var kggb = $("kggb");
		
		if(!kgkq.checked && !kggb.checked){
			alert("请确定问卷开关与否!");
			return false;
		}
		
		saveUpdate('/xgxt/nbcsPjpy.do?method=wjUpdate&doType=save','wjmc');
	}
	</script>
	<body onload="">
		<html:form action="/nbcsPjpy">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="oneSs" id="oneSs" value="${oneSs }"/>
			<input type="hidden" name="allSs" id="allSs" value="${allSs }"/>
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
			<logic:notEqual name="userType" value="stu">
			<fieldset>
				<legend>
					相关设置
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>是否开启：
						</td>
						<td align="left" width="35%">
							开
							<html:radio name="rs" property="sfkq" styleId="kgkq" value="是"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							关
							<html:radio name="rs" property="sfkq" styleId="kggb" value="否"/>
						</td>
						<td align="right" width="15%">
							
						</td>
						<td align="left" width="35%">
						</td>
					</tr>
				</table>
			</fieldset>
			</logic:notEqual>
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
							<!-- 回答问卷 -->
							<logic:equal name="mklx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" readonly="true"/>		
							</logic:equal>
							<!-- 非回答问卷 -->
							<logic:notEqual name="mklx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" onblur="getWjInfo()"/>	
							</logic:notEqual>		
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							备注：
							<br>
							<font color="red">(限500字)</font>
						</td>
						<td align="left" colspan="3">
							<!-- 回答问卷 -->
							<logic:equal name="mklx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									readonly="true" style="width: 90%"/>
							</logic:equal>
							<!-- 非回答问卷 -->
							<logic:notEqual name="mklx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									onblur="chLeng(this,500)" style="width: 90%"/>
							</logic:notEqual>
						</td>
					</tr>
				</table>
			</fieldset>
			<!-- 组卷信息 -->
			<logic:equal name="isSt" value="true">
				<fieldset>
					<legend>
						组卷信息
					</legend>
					<table width="100%" class="tbstyle">
						<logic:iterate name="zjlxList" id="lx">
							<thead>
								<td align="center" colspan="2">
									${lx.lxmc}
								</td>
							</thead>
							<!-- 单选题 -->
							<logic:equal name="lx" property="lxdm" value="oneChoose">
								<%@ include file="stxx/zjOneChoose.jsp"%>
							</logic:equal>
							<!-- 多选题 -->
							<logic:equal name="lx" property="lxdm" value="allChoose">
								<%@ include file="stxx/zjAllChoose.jsp"%>
							</logic:equal>
							<!-- 问答题 -->
							<logic:equal name="lx" property="lxdm" value="questions">
								<%@ include file="stxx/zjQueChoose.jsp"%>
							</logic:equal>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:equal>
			<!-- 组卷信息 end-->
			<div class="buttontool" align="center">
				<span class="openbutton"> 	
					<!-- 非查看 -->
					<logic:notEqual name="doType"value="view">
						<!-- 回答问卷 -->
						<logic:equal name="mklx" value="hd">
							<button class="button2" id="buttonSave" onclick="hdwj()"
								style="width: 80px">
									确	定
							</button> 
						</logic:equal>
						<!-- 非回答问卷 -->
						<logic:notEqual name="mklx" value="hd">
							<button class="button2" id="buttonSave" onclick="saveWj()"
								style="width: 80px">
									保 存
							</button> 
						</logic:notEqual>
					</logic:notEqual>
					&nbsp;&nbsp;
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
