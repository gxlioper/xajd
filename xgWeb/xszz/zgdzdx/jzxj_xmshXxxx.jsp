<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.Iterator" />
<jsp:directive.page import="java.util.ArrayList" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript">
		function yz(){
			if(document.getElementById('xyshyj')){
			var xyshyj = document.getElementById('xyshyj').value;
			}
			if(document.getElementById('xxshyj')){
			var xxshyj = document.getElementById('xxshyj').value;
			}
			if(document.getElementById('xxshyj')){
			var xxshyj = document.getElementById('xxshyj').value;
			}
			
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if((("ͨ��" == xxsh)) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("ѧУ���������ܴ���1000���ַ�");
	          		 return false;
	       		 }
	       	}
			if(document.getElementById("islx")){
				if(document.getElementById("islx").value==""){
					alert("��Ų���Ϊ�գ���");
					return false;
				}
			}
			refreshForm('/xgxt/zgdzdx_xszz.do?method=jzxj_xmshXxxx&actDo=save');
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function chg(){
			var yesNoT = document.getElementById('yesNoT').value;
			var xmjeT = document.getElementById('xmjeT').value;
			var yesNo = document.getElementById('yesNo').value;
			var kshrs = document.getElementById('kshrs').value;
			
			if (kshrs == "0" && yesNo == "ͨ��" && yesNoT != "ͨ��"){
				document.getElementById('buttonSave').disabled = true;
			} else {
				document.getElementById('buttonSave').disabled = false;
			}
			
			if (yesNo != "ͨ��"){
				document.getElementById('xmje').value = "";
				document.getElementById('xmje').disabled = true;
			} else {
				document.getElementById('xmje').value = xmjeT;
				document.getElementById('xmje').disabled = false;
			}
		}
		function islx(){
			var islx = document.getElementById('islx').value;
			
			if (islx == "xy"){
				document.getElementById('xyshyj').disabled = false;
				document.getElementById('xxshyj').disabled = true;
				document.getElementById('xxshyj').style.display = "none";
				document.getElementById('xgshyj').style.display = "none";
			} else if(islx == "xx"){
				document.getElementById('xyshyj').disabled = true;
				document.getElementById('xxshyj').disabled = false;
				document.getElementById('xgshyj').style.display = "none";
			} else if(islx == "admin"){
				document.getElementById('xyshyj').disabled = true;
				document.getElementById('xxshyj').disabled = true;
				document.getElementById('xgshyj').disabled = false;
			}
		}
		function showjlje(act){
			var jbje;
			var xypzje;
			var xypzdj;
			if(act == "xy"){
				xypzje = document.getElementById('xypzdj').value
				if(xypzje != ''){
				xypzdj = document.getElementById('xypzdj').options[document.getElementById('xypzdj').selectedIndex].text
				}
					}
			if(act == "xx"){
				xypzje = document.getElementById('xxpzdj').value
				if(xypzje != ''){
				xypzdj = document.getElementById('xxpzdj').options[document.getElementById('xxpzdj').selectedIndex].text
				}
				}
			if(act == "admin"){
				xypzje = document.getElementById('xgpzdj').value
				if(xypzje != ''){
				xypzdj = document.getElementById('xgpzdj').options[document.getElementById('xgpzdj').selectedIndex].text
				}
				//alert(xypzje);
				//alert(xypzdj);
				}
			GetListData.getJxjje(xypzje,xypzdj,function jxjNum(data){
				//alert(data);
		   		if (data != null || typeof data == 'object') {
			   		if(act=="xy"){
		   			document.getElementById("xypzje").value=data;
			   		}
			   		if(act=="xx"){
			   			document.getElementById("xxpzje").value=data;
				   		}
			   		if(act=="admin"){
			   			document.getElementById("xgpzje").value=data;
				   		}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}
				});
		}
		function showjljeez(){
			var lx = document.getElementById("islx").value;
			showjlje(lx);
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus();showjljeez();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>����ѧ�� - ��� - �������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_xszz" method="post">
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" name="yesNoT" value="<bean:write name="rs" property="yesNo" />" />
			<input type="hidden" name="xmjeT" value="<bean:write name="rs" property="xmje" />" />
			<input type="hidden" name="kshrs" value="<bean:write name="kshrs" />" />
			<input type="hidden" name="islx" id="islx" value="<bean:write name="islx" />" />
			<input type="hidden" name="xxsh"
				value="<bean:write name='rs' property='xxsh' />" />
			<input type="hidden" name="isXX" value="<bean:write name="isXX" />" />
			
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="10">
							<span>��ϸ���</span>
						</th>
					</tr>
				</thead>
				<tbody>		
				<tr>
					<th colspan="2" scope="row">
						��Ŀ����
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xmmc" />
					</td>
					<th>
							����ʱ��
					</th>
					<td colspan="3">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr>
					<th align="center" colspan="2">
						ѧ��
					</th>
					<td align="left" colspan="3">
						<bean:write name="rs" property="xh" />
					</td>
					<th width="11%" scope="col">
							����
					</th>
					<td colspan="3" scope="col">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
							�Ա�
					</th>
					<td colspan="3">
						<bean:write name='rs' property="xb" />
					</td>
					<th>
							�꼶
					</th>
					<td colspan="3">
						<bean:write name='rs' property="nj" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							���֤��
					
					</th>
					<td colspan="3">
						<bean:write name='rs' property="sfzh" />
					</td>
					<th>
						
							��ϵ�绰
					
					</th>
					<td colspan="3">
						<bean:write name='rs' property="lxdh" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
					
							<bean:message key="lable.xsgzyxpzxy" />
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="xymc" />
					</td>
					<th>
						
							רҵ
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="zymc" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							�༶
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="bjmc" />
					</td>
					<th>
						
							��Դ��
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="syd" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							������
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="ssbh" />
					</td>
					<th>
						
							���ҵ绰
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="qsdh" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							������ò
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="zzmmmc" />
					</td>
					<th>
						
							����
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="mzmc" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ѧ����
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="rxny" />
					</td>
					<th>
						
							��������
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="csrq" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							�Ƿ�������
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="sfkns" />
					</td>
					<th>
						
							�༶����
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="bjpm" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ѧ��ɼ�
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="jd" />
					</td>
					<th>

							������Ŀ��
	
					</th>
					<td colspan="3">
						<bean:write name='rs' property="bkkms" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						
							�񽱼��������
				
					</th>
					<td colspan="7">
						<bean:write name='rs' property="fblw" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							����
						
					</th>
					<td colspan="3">
						<bean:write name='rs' property="kh" />
					</td>
					<th>
				
							��ѧǰ����
			
					</th>
					<td colspan="3">
						<bean:write name='rs' property="rxqhk" />
					</td>
				</tr>
				<tr>
					<th colspan="4" scope="row">
						
							�Ƿ�Ը��μӴ��ƻ�־Ը�
				
					</th>
					<td width="12%">
						<bean:write name='rs' property="sfyycjcshzyhd" />
					</td>
					<th colspan="3">
						
							�Ƿ�Ը�����������ѧ������ڹ���ѧ
						
					</th>
					<td width="12%">
						<bean:write name='rs' property="sfyysqgjzxdkhqgzx" />
					</td>
				</tr>
				<tr>
					<th colspan="9" scope="row">
						
							��ͥ��Ϣ
						
					</th>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ͥ����
						
					</th>
					<td colspan="7">
						&nbsp;&nbsp;&nbsp;�Ƿ�ȫ��
						<bean:write name='rs' property="sfjq" />
						&nbsp;&nbsp; �Ƿ�¶���
						<bean:write name='rs' property="sfge" />
						&nbsp;&nbsp; �Ƿ��ף�
						<bean:write name='rs' property="sfdq" />
						&nbsp;&nbsp; �Ƿ�м���
						<bean:write name='rs' property="sfcj" />
						<br />
						�Ƿ��������
						<bean:write name='rs' property="sfjls" />
						&nbsp;&nbsp; �Ƿ����죺
						<bean:write name='rs' property="sfly" />
						&nbsp;&nbsp; �Ƿ��ز���
						<bean:write name='rs' property="sfzb" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ͥסַ
						
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtzz" />
					</td>
					<th>
						
							��������
						
					</th>
					<td colspan="3">
						<bean:write name="rs" property="yzbm" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ͥ��ϵ�绰
						
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtlxdh" />
					</td>
					<th>
						
							��ͥ�˾�
							<br />
							������
						
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtrjnsr" />
					</td>
				</tr>
				<tr>
					<th width="4%" rowspan="6" scope="row">
						
							��
							<br />
							ͥ
							<br />
							��
							<br />
							Ա
							<br />
							��
							<br />
							Ϣ
						
					</th>
					<th width="12%">
						<div align="center">
							����
						</div>
					</th>
					<th width="12%">
						<div align="center">
							����
						</div>
					</th>
					<th width="10%">
						<div align="center">
							�뱾��
							<br />
							��ϵ
						</div>
					</th>
					<th colspan="2">
						<div align="center">
							����(ѧϰ)��λ
						</div>
					</th>
					<th width="12%">
						<div align="center">
							ְҵ
						</div>
					</th>
					<th width="10%">
						<div align="center">
							������
							<br />
							(Ԫ)
						</div>
					</th>
					<th width="12%">
						<div align="center">
							����״��
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy1_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy1_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy1_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy2_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy2_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy2_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy3_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy3_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy3_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy4_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy4_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy4_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_xm" />
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name="rs" property="jtcy5_nl" />
							&nbsp;
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_gx" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name="rs" property="jtcy5_gzdw" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_zy" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_nsr" />
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name="rs" property="jtcy5_jkzk" />
						</div>
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
					
							ѧ���ڱ���
							<br />
							�������

					</th>
					<td colspan="7">
						<bean:write name="rs" property="xszbdszqk" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ͥ����
							<br />
							��Ȼ�ֺ����
					
					</th>
					<td colspan="7">
						<bean:write name="rs" property="jtzszrzhqk" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��ͥ����
							<br />
							ͻ�������¼�
					
					</th>
					<td colspan="7">
						<bean:write name="rs" property="jtzstfywsj" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							�������
					
					</th>
					<td colspan="7">
						<bean:write name="rs" property="qtqk" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							��������ͨѶ��ַ
						
					</th>
					<td colspan="7">
						<bean:write name="rs" property="mzbm_txdz" />
					</td>
				</tr>
				<tr>
					<th colspan="2" scope="row">
						
							����������������
						
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_yzbm" />
					</td>
					<th>
						
							��������
							<br />
							��ϵ�绰
						
					</th>
					<td colspan="3">
						<bean:write name="rs" property="mzbm_lxdh" />
					</td>
				</tr>
				<tr>
					<th colspan="9">
						<div align="center">
							������Ϣ
						</div>
					</th>
				</tr>
				<logic:equal name="isNULL" value="no">
					<%
								ArrayList zdyzdList = (ArrayList) request
								.getAttribute("zdyzdList");

								for (Iterator it = zdyzdList.iterator(); it.hasNext();) {
							String[] temp = (String[]) it.next();
							String zdydm = "zdy" + temp[0];
					%>
					<tr>
						<td colspan="2">
							<div align="right">
								<%=temp[1]%>
							</div>
						</td>
						<td colspan="7">
							<bean:write name="rs" property="<%=zdydm%>" />
						</td>
					</tr>
					<%
					}
					%>
				</logic:equal>
				<tr>
					<th colspan="2">
						
							��������
						
					</th>
					<td colspan="7">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<%
				String[] je = (String[]) request.getAttribute("zjeList");
				%>
				<tr>
					<th colspan="9">
						<div align="center">
							���˼�¼
						</div>
					</th>
				</tr>
				<tr>
					<th rowspan="2">
						
							��
							<br />
							��
							<br />
							ѧ
							<br />
							��
							<br />
							��
							<br />
							¼
						
					</th>
					<th>
						<div align="center">
							��ý��
						</div>
					</th>
					<td colspan="7">
						<%=je[0]%>
						&nbsp;Ԫ
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<%
								ArrayList jxjList = (ArrayList) request
								.getAttribute("xsJzxjList");
								if (jxjList.size() == 0) {
						%>
						&nbsp;&nbsp;&nbsp;&nbsp;�޼�¼
						<%
						} else {
						%>
						<br />
						<%
						for (Iterator it = jxjList.iterator(); it.hasNext();) {
						%>
						&nbsp;
						<%=it.next()%>
						<br />
						<%
						}
						%>
						<br />
						<%
						}
						%>
					</td>
				</tr>
				<logic:equal name="islx" value="xx">
					<tr>
						<th colspan="2">
							
								<bean:message key="lable.xsgzyxpzxy" />���
							
						</th>
						<td colspan="3">
							<bean:write name="rs" property="xysh" />
						</td>
						<th>
							
								��������
							
						</th>
						<td colspan="1">
							<html:select name="rs" property="xypzdj" onchange="" disabled="true">
								<html:options collection="xmjejList" property="xmdm"
								labelProperty="jbmc" />
							</html:select>
						</td>
						<th colspan="1">
							���
						</th>
						<td colspan="1">
							<bean:write name="rs" property="xypzje" /> 
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="islx" value="admin">
				<tr>
						<th colspan="2">
							
								<font color="red">*</font>���
							
						</th>
						<td colspan="3">
							<html:text name="rs" property="jxjbh"></html:text>
						</td>
						<th>
							
						</th>
						<td colspan="3">
						
						</td>
					</tr>
					<tr>
						<th colspan="2">
							
								<bean:message key="lable.xsgzyxpzxy" />���
							
						</th>
						<td colspan="3">
							<bean:write name="rs" property="xysh" />
						</td>
						<th>
							
								��������
							
						</th>
						<td colspan="1">
							<html:select name="rs" property="xypzdj" onchange="" disabled="true">
								<html:options collection="xmjejList" property="xmdm"
								labelProperty="jbmc" />
							</html:select>
						</td>
						<th colspan="1">
							���
						</th>
						<td colspan="1">
							<bean:write name="rs" property="xypzje" /> 
						</td>
					</tr>
					<tr>
						<th colspan="2">
							
								ѧУ���
					
						</th>
						<td colspan="3">
							<bean:write name="rs" property="xxsh" />
						</td>
						<th>
							
								��������
						
						</th>
						<td colspan="1">
							<html:select name="rs" property="xxpzdj" onchange="" disabled="true">
								<html:options collection="xmjejList" property="xmdm"
								labelProperty="jbmc" />
							</html:select>
						</td>
						<th colspan="1">
							���
						</th>
						<td colspan="1">
							<bean:write name="rs" property="xxpzje" /> 
						</td>
					</tr>
<%--					<tr>--%>
<%--						<td colspan="2">--%>
<%--							<div align="center">--%>
<%--								��ѧ������ίԱ�����--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td colspan="3">--%>
<%--							<bean:write name="rs" property="pswyh" />--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<div align="center">--%>
<%--								��������--%>
<%--							</div>--%>
<%--						</td>--%>
<%--						<td colspan="1">--%>
<%--							<html:select name="rs" property="xxpzdj" onchange="" disabled="true">--%>
<%--								<html:options collection="xmjejList" property="xmdm"--%>
<%--								labelProperty="jbmc" />--%>
<%--							</html:select>--%>
<%--						</td>--%>
<%--						<td colspan="1">--%>
<%--							���--%>
<%--						</td>--%>
<%--						<td colspan="1">--%>
<%--							<bean:write name="rs" property="xxpzje" /> --%>
<%--						</td>--%>
<%--					</tr>--%>
				</logic:equal>
				<!-- �ύʱ�������ʾ -->
				<logic:equal name="islx" value="xy">
				<tr>
					<th colspan="2">
						
							��˽��
						
					</th>
					<td colspan="3">
						<html:select name="rs" property="yesNo" onchange="chg();">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<th>
							
								��������
							
						</th>
						<td colspan="1">
							<html:select name="rs" property="xypzdj" onchange="showjlje('xy');">
								<html:options collection="xmjejList" property="xmdm"
								labelProperty="jbmc" />
							</html:select>
						</td>
						<th colspan="1">
							���
						</th>
						<td colspan="1">
							<html:text name="rs" property="xypzje" readonly="true"></html:text>
							<input type="hidden" id="xmje">
						</td>
				</tr>
				</logic:equal>
				<logic:equal name="islx" value="xx">
				<tr>
					<th colspan="2">
						
							��˽��
						
					</th>
					<td colspan="3">
						<html:select name="rs" property="yesNo" onchange="chg();">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<th>
							
								��������
						
						</th>
						<td colspan="1">
							<html:select name="rs" property="xxpzdj" onchange="showjlje('xx');">
								<html:options collection="xmjejList" property="xmdm"
								labelProperty="jbmc" />
							</html:select>
						</td>
						<th colspan="1">
							���
						</th>
						<td colspan="1">
							<html:text name="rs" property="xxpzje" readonly="true"></html:text>
							<input type="hidden" id="xmje">
						</td>
				</tr>
				</logic:equal>
				<logic:equal name="islx" value="admin">
				<tr>
					<th colspan="2">
						
							��˽��
						
					</th>
					<td colspan="3">
						<html:select style="display:" name="rs" property="yesNo" onchange="chg();">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<th>
							
								��������
						
						</th>
						<td colspan="1">
							<html:select name="rs" property="xgpzdj" onchange="showjlje('admin');">
								<html:options collection="xmjejList" property="xmdm"
								labelProperty="jbmc" />
							</html:select>
						</td>
						<th colspan="1">
							���
						</th>
						<td colspan="1">
							<html:text name="rs" property="xgpzje" readonly="true"></html:text>
							<input type="hidden" id="xmje">
						</td>
				</tr>
				</logic:equal>
				
				<logic:equal name="islx" value="xy">
				<tr>
					<th colspan="2">
						
							<bean:message key="lable.xsgzyxpzxy" />������
						
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="xyshyj" rows='5'
							style="width:100%" />
					</td>
					
				</tr>
				</logic:equal>
				<logic:equal name="islx" value="xx">
				<tr>
					<th colspan="2">
						
							<bean:message key="lable.xsgzyxpzxy" />������
						
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="xyshyj" rows='5'
							style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						
							ѧУ������
						
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="xxshyj" rows='5'
							style="width:100%" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="islx" value="admin">
				<tr>
					<th colspan="2">
						
							<bean:message key="lable.xsgzyxpzxy" />������
						
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="xyshyj" rows='5'
							style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						
							ѧУ������
						
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="xxshyj" rows='5'
							style="width:100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						
							ѧ����������
						
					</th>
					<td colspan="7">
						<html:textarea name="rs" property="xgshyj" rows='5'
							style="width:100%"/>
					</td>
				</tr>
				</logic:equal>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="10">
							<div class="btn">
								<logic:notEqual name="kshrs" value="0">
								
										<button type="button" id="buttonSave" 
											onclick="yz()"
											style="width: 80px">
											��	��
										</button>
							
									&nbsp;&nbsp;
							
									<button type="button" id="buttonClose" 
										onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
										style="width: 80px">
										��	��
									</button>
								</logic:notEqual>
								<logic:equal name="kshrs" value="0">
									<logic:equal name="rs" property="yesNo" value="��ͨ��">
										<button type="button" onclick="yz()" style="width:80px"
											id="buttonSave">
											�� ��
										</button>
									</logic:equal>
									<logic:notEqual name="rs" property="yesNo" value="��ͨ��">
									<logic:notEqual value="view" name="writ">
										<button type="button" onclick="yz()" style="width:80px"
											id="buttonSave">
											�� ��
										</button>
									</logic:notEqual>
									&nbsp;&nbsp;
									</logic:notEqual>
									<button type="button"
										onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
										style="width:80px" id="buttonClose">
										�� ��
									</button>
									<div align="center">
										<font color="red">
											ע������׼ͨ������������������ͨ������Ϊ��<bean:write name="yshNum" />���涨�������Ϊ��<bean:write name="zrs" />
										</font>
									</div>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
	<script language="javascript">
			var xmjeT = document.getElementById('xmjeT').value;
			var yesNo = document.getElementById('yesNo').value;
			
			if (yesNo != "ͨ��"){
				document.getElementById('xmje').value = "";
				document.getElementById('xmje').disabled = true;
			} else {
				document.getElementById('xmje').value = xmjeT;
				document.getElementById('xmje').disabled = false;
			}
		</script>
</html>
