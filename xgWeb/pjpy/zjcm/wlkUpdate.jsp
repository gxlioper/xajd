<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	
function saveWlk(){
	var nj = $("nj").value;
	var xydm = $("xydm").value;
	var zydm = $("zydm").value;
	var lx = $("lx").value;
	var xymc = "";
	var zymc = "";
	var mc = "";
	var msg = "";
	var url = "/xgxt/zjcmPjpy.do?method=wlkUpdate&doType=save";
	
	if(lx == ""){
		alert("���Ͳ���Ϊ�գ���ȷ�ϣ���");
		return false;
	}
	
	for(var i=0;i<$("xy").options.length;i++){
		if($("xy").options[i].selected){
			xymc=$("xy").options[i].text;
		}
	}
	
	for(var i=0;i<$("zy").options.length;i++){
		if($("zy").options[i].selected){
			zymc=$("zy").options[i].text;
		}
	}
	
	for(var i=0;i<$("lx").options.length;i++){
		if($("lx").options[i].selected){
			mc=$("lx").options[i].text;
		}
	}	

	for(var i = 0 ; i < $("bjP").options.length; i++){
		var tmp = document.createElement("input");
		tmp.type = "hidden";
		tmp.name = "wlkbjdm";
		tmp.value = $("bjP").options[i].value;
		document.forms[0].appendChild(tmp);
	}
	
	if(nj =="" && xydm == "" && zydm == "" && $("bjP").options.length == "0"){
		alert("��ȷ����������!!");
		return false;
	}else if(nj !="" && xydm == "" && zydm == "" && $("bjP").options.length == "0"){
		msg = "��" + nj + "�꼶ȫ��༶����Ϊ" + mc + ",��ȷ��";
		url+="&fs=nj"
	}else if(xydm != "" && zydm == "" && $("bjP").options.length == "0"){
		msg = "��";
		if(nj != ""){
			msg += nj + "�꼶";
		}
		msg += xymc + "ȫ��༶����Ϊ" + mc + ",��ȷ��";
		url+="&fs=xy"
	}else if(zydm != "" && $("bjP").options.length == "0"){
		msg = "��";
		if(nj != ""){
			msg += nj + "�꼶";
		}
		if(xymc != ""){
			msg += xymc;
		}
		msg += zymc + "ȫ��༶����Ϊ" + mc + ",��ȷ��";
		url+="&fs=zy"
	}else if($("bjP").options.length != "0"){
		msg = "����ѡ�༶����Ϊ" + mc + ",��ȷ��";
		url+="&fs=bj"
	}
	
	if (confirm(msg)) {
		saveUpdate(url,'lx');
	}
}

	</script>
	<body onload="">
		<html:form action="/zjcmPjpy">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�༶��������
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="10%">
						�꼶��
					</td>
					<td align="left" width="30%" colspan="">
						<html:select property="nj" style="" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<td align="right" width="10%">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left" width="30%" colspan="">
						<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>		
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" width="">
						רҵ��
					</td>
					<td align="left" width="" colspan="">
						<html:select property="zydm" style="" styleId="zy" onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<td align="right" width="">
						<font color="red">*</font>���ͣ�
					</td>
					<td align="left" width="" colspan="">
						<html:select property="lx" style="" styleId="lx" onchange="">
							<html:options collection="wlkList" property="dm" labelProperty="mc" />
						</html:select>		
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td width="10%" align="right">
									��<br><br><br>��
								</td>
								<td width="30%">
									<html:select property="bjdm" style="width:100% " styleId="bj" size="12" onchange="">
									</html:select>
								</td>
								<td width="10%">
									<button class="button2" style="width:100%" id="addBj" onclick="addBjP()">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button class="button2" style="width:100%" id="delBj"
										onclick="delBjP();">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<td width="10%" align="right">
									��<br>��<br>��<br>��
								</td>
								<td width="30%">
									<html:select property="xh" style="width:100%" styleId="bjP"  size="12">
									</html:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button class="button2" id="buttonSave" onclick="saveWlk()"
						style="width: 80px">
						��&nbsp;&nbsp;��
					</button>
					&nbsp;&nbsp;
					<button class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						��&nbsp;&nbsp;��
					</button>
				</tr>
			</table>
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
