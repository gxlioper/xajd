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
	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type="text/javascript">	
		function printBb(){
		
			var xn = $("xn").value;
			var xq = $("xq").value;
			var lx = $("lx").value;
			var xydm = $("xydm").value;
			var bjdm = $("bjdm").value;
			var jxjdm = $("jxjdm").value;
			var yhdm = $("yhdm").value;

			if(lx == ""){
				alert("��ȷ���������ͣ���");
				return false;
			}
			if(xn == "" || xq == ""){
				alert("����ѧ����ѧ�ڲ���Ϊ�գ���");
				return false;
			}
			
			if(xydm == ""){
				if(lx == "zhbnoyj" || lx == "zhbyj" || lx == "zhblxy" || lx=='jxjjehz'){
					alert("��ȷ��<bean:message key="lable.xsgzyxpzxy" />���ƣ���");
					return false;
				}
			}
				
			if(bjdm == ""){
				if(lx == "zhbnoyj" || lx == "zhbyj"){
					alert("��ȷ���༶���ƣ���");
					return false;
				}
			}

			if(jxjdm == ""){
				if(lx == "jxjjetj"){
					alert("��ȷ����ѧ�����ƣ���");
					return false;
				}
			}

			if(yhdm == ""){
				if(lx == "jxjjetj"){
					alert("��ȷ���������ƣ���");
					return false;
				}
			}
			
			//��ѧ������ѡ
			if($("jxjlbdmTr").style.display != "none" && $("jxjlbdm").value == ""){
				alert("��ѡ��ѧ�����");
				return false;
			}
			//�����ƺű�ѡ
			if($("rychdmTr").style.display != "none" && $("rychdm").value == ""){
				alert("��ѡ�������ƺţ�");
				return false;
			}
			
			var url = "/xgxt/zjcmPjpy.do?method=bbtjManage&doType=print";
			if (lx=='jxjjehz') {
				url += '&yhmc=';
				url += document.getElementById('yhlx').options[document.getElementById('yhlx').selectedIndex].text;
				url += '&xymc=';
				url += document.getElementById('xy').options[document.getElementById('xy').selectedIndex].text;
			}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		//showidArr Ҫ��ʾԪ�ص�id���ϣ�hiddenidArr Ҫ����Ԫ�ص�id����
		function changeDisplay(showidArr,hiddenidArr){
			for(var i=0; i<showidArr.length; i++){
				if($(showidArr[i])){
					$(showidArr[i]).style.display = "";
				}
			}
			for(var i=0; i<hiddenidArr.length; i++){
				if($(hiddenidArr[i])){
					$(hiddenidArr[i]).style.display = "none";
				}
			}
		}
		
		function checkBj(lx){
			var showEle = [];
			var hiddenEle = [];
			
			//jxjlbdm ��ѧ�����;rychdm �����ƺ����
			if(lx == "zhblxy"){
				hiddenEle = ["zyTr","bj1","jxjlbdmTr","rychdmTr","jxjTr","yhTr", "nj", "yh"];				
			}else if(lx == "jxjhj"){//��ѧ�������
				showEle = ["jxjlbdmTr"];
				hiddenEle = ["zyTr","bj1","rychdmTr","jxjTr","yhTr", "nj", "yh"];
			}else if(lx == "rychhj"){//�����ƺŻ�����	
				showEle = ["rychdmTr"];
				hiddenEle = ["zyTr","bj1","jxjlbdmTr","jxjTr","yhTr", "nj", "yh"];	
			}else if(lx == "jxjjetj"){//��ѧ����ͳ��
				showEle = ["jxjlbdmTr","jxjTr","yhTr"];
				hiddenEle = ["zyTr","bj1","rychdmTr", "yh"];	
			}else if (lx=='jxjjehz') {
				showEle = ["yh", "nj"];
				hiddenEle = ["zyTr","bj1","jxjlbdmTr", "yhTr", "jxjTr", "rychdmTr"];	
			}else{
				showEle = ["zyTr","bj1"];
				hiddenEle = ["jxjlbdmTr","rychdmTr","jxjTr","yhTr", "nj", "yh"];				
			}
			changeDisplay(showEle,hiddenEle);
		}
		
		function chJxjlb(){
		
			var lx = $("lx").value;
			var jxjlbdm = $("jxjlbdm").value;
			
			var tableName = "view_jxjxx"; 
			var dm = "jxjdm"; 
			var mc = "jxjmc";
			var msg = "";
			var pk = "jxjlbdm";
			var pkValue = jxjlbdm;
			
			var objId = "jxjdm";
			
			if(lx == "jxjjetj"){
				getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
					if(data!=null){
						DWRUtil.removeAllOptions(objId);
						DWRUtil.addOptions(objId,data,"dm","mc");
						$(objId).options[0].value = "";
					}
				});
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
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 50%">
				<thead>
					<tr>
						<td colspan="2" align="center">
							ͳ�Ʊ���
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
					 	<font color="red">*</font>�������ͣ�
					</td>
					<td>
						<html:select property="lx" style="" onchange="checkBj(this.value)">
							<html:option value=""></html:option>
							<html:options collection="bblxList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right" >
						<font color="red">*</font>����ѧ�꣺
					</td>
					<td align="left" width="">
						<html:select property="xn" style="" styleClass="select"styleId="xn" onchange="">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						<font color="red">*</font>����ѧ�ڣ�
					</td>
					<td align="left" width="">
						<html:select property="xq" style="" styleClass="select"styleId="xq" onchange="">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="display:none" id="jxjlbdmTr">
					<td align="right">
					 	<font color="red">*</font>��ѧ�����
					</td>
					<td>
						<html:select property="jxjlbdm" onchange="chJxjlb()">
							<html:options collection="jxjlbList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="display:none" id="rychdmTr">
					<td align="right">
					 	<font color="red">*</font>�����ƺţ�
					</td>
					<td>
						<html:select property="rychdm">
							<html:options collection="rychList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr style="display:none" id="jxjTr">
					<td align="right">
					 	<font color="red">*</font>��ѧ��
					</td>
					<td>
						<html:select property="jxjdm" >
							<html:option value="">----��ѡ��----</html:option>
						</html:select>
					</td>
				</tr>
				<tr style="display:none" id="yhTr">
					<td align="right">
					 	<font color="red">*</font>���У�
					</td>
					<td>
						<html:select property="yhdm" >
							<html:options collection="yhList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr id="nj" style="display:none">
					<td align="right" width="">
						�꼶��
					</td>
					<td align="left">
						<html:select property="nj" styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<logic:equal name="userType" value="xy">
							<html:hidden property="xydm"/>
							<html:select property="xydm" styleClass="select" style="" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select property="xydm" onchange="initZyList();initBjList()" styleClass="select" style="" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr id="zyTr">
					<td align="right">
					 	רҵ��
					</td>
					<td>
						<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr id="bj1" >
					<td align="right" width="">
						�༶��
					</td>
					<td align="left">
						<html:select property="bjdm" style="" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr id="yh" style="display:none">
					<td align="right" width="">
						�������
					</td>
					<td align="left">
						<html:select property="yhlx" styleId="yhlx">
							<html:option value=""></html:option>
							<html:options collection="yhlxList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
				</tr>
				
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
					<button class="button2" id="buttonSave" onclick="printBb()"
						style="width: 80px">
						��ӡ
					</button>
				</tr>
			</table>
			</logic:empty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
