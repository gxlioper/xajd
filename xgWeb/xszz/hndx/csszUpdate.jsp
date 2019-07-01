<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/jxgl/jxglFunction.js"></script>
	<script type="text/javascript">	
	function saveCssz(){
		var mklx = $("mklx").value;
		//����
		if(mklx == "all"){
			if(document.getElementsByTagName("xmbl")){
				 var obj = document.getElementsByName("xmbl"); 
				 var size = obj.length;
				 var zbl = "0";
				 for(var i=0;i<size;i++){
				 	var bl = obj[i].value;
				 	if(bl == ""){
				 		bl = "0";
				 		obj[i].value = "0";
				 	}
				 	zbl = parseInt(zbl) + parseInt(bl);
				 }
				 if(zbl > 100){
				 	alert("���������Ӳ��ܳ���100����ȷ��");
				 	return false;
				 }
			}
		}
		saveUpdate('/xgxt/hndxXszz.do?method=csszUpdate&doType=save','');
	}
	
	function onShow(){
		var mklx = $("mklx").value;
		//����
		if(mklx == "kg"){
			var kg = $("kg").value;
			var obj = document.getElementsByName("kg"); 
			var size = obj.length;
	
			for(var i=0;i<size;i++){
				if(kg == obj[i].value){
					obj[i].checked = true;
				}else{
					obj[i].checked = false;
				}
			}
		}
	}
	
	function setKgValue(value){
		$("kg").value=value;
	}
	</script>
	<body onload="onShow()">
		<html:form action="/zjjtPjpy">
		<!-- ������ -->
		<%@ include file="/pjpy/hiddenValue.jsp"%>
		<html:hidden property="xmmc" value="�����������϶�"/>
		<!-- ������ end-->
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<fieldset>
				<legend>
					����
				</legend>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<td align="right" width="40%">
						��ǰѧ�꣺
					</td>
					<td align="left" width="60%">
						<html:hidden name='rs' property="xn" styleId="xn"/>	
						<html:select name="rs" property="xn" style="" styleClass="select"styleId="xn" onchange="" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>	
				</tr>
				<!-- ���� -->
				<logic:equal name="mklx" value="kg">
				<tr style="height: 23px">
					<td align="right" width="40%">
						���뿪�أ�
					</td>
					<td align="left" width="60%">
						<html:hidden name="rs" property="kg"/>
						<input type="radio" name="kg" value="true" onclick="setKgValue(this.value)">��
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="kg" value="false" onclick="setKgValue(this.value)">��
					</td>	
				</tr>
				</logic:equal>
				<!-- ���� end-->
				<!-- �������� -->
				<logic:equal name="mklx" value="all">
					<logic:iterate id="jb" name="blList">
						<tr>
							<td align="right" width="">
								${jb.mc }��
							</td>
							<td align="left" width="">
								<input type="text" name="xmbl" value="${jb.xmbl }" maxlength="3" onblur="chMax(this,100)" style="width : 10%"/>%
							</td>
						</tr>
					</logic:iterate>
				</logic:equal>
				<!-- �������� end-->
				<!-- �������� -->
				<logic:equal name="mklx" value="dt">
				<tr>
					<td align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left" width="">
						<html:hidden name="rs" property="xydm"/>
						<html:select name="rs" property="xydm" style="" styleId="xy" disabled="true">
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
					<logic:iterate id="jb" name="blList">
						<tr>
							<td align="right" width="">
								${jb.mc }��
							</td>
							<td align="left" width="">
								<input type="hidden" name="xmbl" value="${jb.xmbl }" />
								<input type="hidden" name="xmjb" value="${jb.xmjb }" />
								<input type="text" name="xmrs" value="${jb.xmrs }" maxlength="5" onblur="" style="width : 10%"/>��
							</td>
						</tr>
					</logic:iterate>
				</logic:equal>
				<!-- �������� end-->
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button class="button2" id="buttonSave" onclick="saveCssz()"
						style="width: 80px">
						��	��
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose"
						onclick="window.close();return false;" 
						style="width: 80px" id="buttonClose">
						�� ��
					</button>
					</td>
				</tr>
			</table>
			</fieldset>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<script>
					if($("message")){
						alert($("message").value);
						dialogArgumentsQueryChick();
						window.close();
					}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
