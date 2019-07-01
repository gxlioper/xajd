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
			alert("��ȷ���ʾ������!");
			return false;
		}
		
		saveUpdate('/xgxt/nbcsPjpy.do?method=wjUpdate&doType=save','wjmc');
	}
	</script>
	<body onload="">
		<html:form action="/nbcsPjpy">
			<!-- ������ -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="oneSs" id="oneSs" value="${oneSs }"/>
			<input type="hidden" name="allSs" id="allSs" value="${allSs }"/>
			<!-- ������ end-->
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					������Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							ѧ�꣺
						</td>
						<td align="left" width="35%">
							<html:hidden name="rs" property="xn"/>
							${rs.xn }
						</td>
						<td align="right" width="15%">
							ѧ�ڣ�
						</td>
						<td align="left">
							<html:hidden name="rs" property="xq"/>
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<html:hidden name="rs" property="nd"/>
							${rs.nd }
						</td>
						<td align="right">
							����ʱ�䣺
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
					�������
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>�Ƿ�����
						</td>
						<td align="left" width="35%">
							��
							<html:radio name="rs" property="sfkq" styleId="kgkq" value="��"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��
							<html:radio name="rs" property="sfkq" styleId="kggb" value="��"/>
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
					�ʾ���Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>�ʾ����ƣ�
							<br>
							<font color="red">(��50��)</font>
						</td>
						<td align="left" colspan="3">
							<!-- �ش��ʾ� -->
							<logic:equal name="mklx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" readonly="true"/>		
							</logic:equal>
							<!-- �ǻش��ʾ� -->
							<logic:notEqual name="mklx" value="hd">
								<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" onblur="getWjInfo()"/>	
							</logic:notEqual>		
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							��ע��
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" colspan="3">
							<!-- �ش��ʾ� -->
							<logic:equal name="mklx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									readonly="true" style="width: 90%"/>
							</logic:equal>
							<!-- �ǻش��ʾ� -->
							<logic:notEqual name="mklx" value="hd">
								<html:textarea name="rs" property="bz" rows="5" styleId="bz"
									onblur="chLeng(this,500)" style="width: 90%"/>
							</logic:notEqual>
						</td>
					</tr>
				</table>
			</fieldset>
			<!-- �����Ϣ -->
			<logic:equal name="isSt" value="true">
				<fieldset>
					<legend>
						�����Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<logic:iterate name="zjlxList" id="lx">
							<thead>
								<td align="center" colspan="2">
									${lx.lxmc}
								</td>
							</thead>
							<!-- ��ѡ�� -->
							<logic:equal name="lx" property="lxdm" value="oneChoose">
								<%@ include file="stxx/zjOneChoose.jsp"%>
							</logic:equal>
							<!-- ��ѡ�� -->
							<logic:equal name="lx" property="lxdm" value="allChoose">
								<%@ include file="stxx/zjAllChoose.jsp"%>
							</logic:equal>
							<!-- �ʴ��� -->
							<logic:equal name="lx" property="lxdm" value="questions">
								<%@ include file="stxx/zjQueChoose.jsp"%>
							</logic:equal>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:equal>
			<!-- �����Ϣ end-->
			<div class="buttontool" align="center">
				<span class="openbutton"> 	
					<!-- �ǲ鿴 -->
					<logic:notEqual name="doType"value="view">
						<!-- �ش��ʾ� -->
						<logic:equal name="mklx" value="hd">
							<button class="button2" id="buttonSave" onclick="hdwj()"
								style="width: 80px">
									ȷ	��
							</button> 
						</logic:equal>
						<!-- �ǻش��ʾ� -->
						<logic:notEqual name="mklx" value="hd">
							<button class="button2" id="buttonSave" onclick="saveWj()"
								style="width: 80px">
									�� ��
							</button> 
						</logic:notEqual>
					</logic:notEqual>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="window.close();return false;"
						style="width: 80px" id="buttonClose">
							�� ��
					</button>
				</span>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>	
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
