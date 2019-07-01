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
	//�ش��ʾ�
	function hdwj(){
		var oneSs = $("oneSs").value;
		
		if(oneSs !=""){
			setOneChooseDa(oneSs);
		}
		
		if (confirm("ȷ�����ش�Ĵ�?")) {
			saveUpdate('/xgxt/nbcsPxpj.do?method=pxpjUpdate&doType=save','');
		}
	}
	
	//��ѡ���
	function setOneChooseDa(oneSs){

		if(oneSs != ""){
			for(var i=0;i<oneSs;i++){
			
				var stnumId = "oneStNum"+ i;
				var num = $(stnumId).value;
				
				for(var j=0;j<num;j++){
								
					var stId = "one_da" + i + j;
					var st = document.getElementsByName(stId);
					
					for(var k=0;k<st.length;k++){
					
						var daId = stId + (k + 1);

						if($(daId)){
							if($(daId).checked == true){
								var tmp1 = document.createElement("input");
								tmp1.type = "hidden";
								tmp1.name = "oneChooseDa";
								tmp1.value = st[k].value;
								document.forms[0].appendChild(tmp1);
							}
						}	
					}
				}
			}
		}
	}
	</script>
	<body onload="">
		<html:form action="/nbcsPxpj">
			<!-- ������ -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<input type="hidden" name="id" id="id" value="${id }"/>
			<input type="hidden" name="bpjr" id="bpjr" value="${bpjr }"/>
			<input type="hidden" name="oneSs" id="oneSs" value="${oneSs }"/>
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
							<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" readonly="true"/>		
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							��ע��
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz"
								readonly="true" style="width: 90%"/>
						</td>
					</tr>
				</table>
			</fieldset>
			<!-- �����Ϣ -->
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
							<%@ include file="/pjpy/nbcs/stxx/zjOneChoose.jsp"%>
						</logic:equal>
						<!-- ��ѡ�� -->
						<logic:equal name="lx" property="lxdm" value="allChoose">
							<%@ include file="/pjpy/nbcs/stxx/zjAllChoose.jsp"%>
						</logic:equal>
						<!-- �ʴ��� -->
						<logic:equal name="lx" property="lxdm" value="questions">
							<%@ include file="/pjpy/nbcs/stxx/zjQueChoose.jsp"%>
						</logic:equal>
					</logic:iterate>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 				
					<button type="button" class="button2" id="buttonSave" onclick="hdwj()"
						style="width: 80px">
							ȷ	��
					</button> 
					&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;"
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
