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

	</script>
	<body onload="onShow()">
		<html:form action="/nbcsPjpy">
			<!-- ������ -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
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
							�����ţ�
						</td>
						<td align="left" width="35%">
							<html:hidden name="rs" property="stbh"/>
							${rs.stbh }
						</td>
						<td align="right" width="15%">
							<font color="red">*</font>�������ͣ�
						</td>
						<td align="left">
							<html:select name="rs" property="stlx" style="width:120px" styleId="stlx" onchange="showStDiv()">
								<html:options collection="stlxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							����������
						</td>
						<td>
							<html:select name="rs" property="stss" style="width:120px" styleId="stss" onchange="">
								<html:options collection="stssList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td>
						
						</td>
						<td>
						
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="15%">
							��Ŀ��
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left"colspan="3">
							<html:textarea name="rs" property="stmc" rows="5" styleId="stmc"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="15%">
							��ע��
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left"colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					�������ݣ���ѡ���������ͣ�
				</legend>
				<div id="oneChoose" style="display : none">
					<%@ include file="stxx/stOneChoose.jsp"%>
				</div>
				<div id="allChoose" style="display : none">
					<%@ include file="stxx/stAllChoose.jsp"%>
				</div>
				<div id="questions" style="display : none">
					<%@ include file="stxx/stQueChoose.jsp"%>
				</div>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 	
					<!-- �ǲ鿴 -->
					<logic:notEqual name="doType"value="view">
						<button class="button2" id="buttonSave" onclick="saveSt()"
							style="width: 80px">
								�� ��
						</button> 
					</logic:notEqual>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="window.close();return false;"
						style="width: 80px" id="buttonClose">
							�� ��
					</button>
				</span>
			</div>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
