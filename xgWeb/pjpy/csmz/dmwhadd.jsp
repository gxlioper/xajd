<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<body onload="">
	<html:form action="/pjpycsmzwh" method="post">
	<div class="title">
		��ǰ����λ�ã�ϵͳά�� - ����ά�� - ��������
	</div>
	<fieldset>
				<legend>
					��������ά��
				</legend>
				<input type="hidden" id="xydm" name="xydm" value="${xydm }"/>
				<input type="hidden" id="realTable" name="realTable" value="${realTable }"/>
		<table width='98%' align='center' class='tbstyle' id="rsTable">
			<thead>
				<tr align='center'>
					<td colspan='2'>
						* �� �� �� �� *
					</td>
				</tr>
			</thead>
				<logic:present name="jxjdmb">
					<logic:equal value="yes" name="jxjdmb">
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>��ѧ����룺
						</td>
						<td align="left">
							<html:text property="jxjdm" styleId="jxjdm" styleClass="inputtext" maxlength="${len }" value="${autoid}" readonly="true"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>��ѧ�����ƣ�
						</td>
						<td align="left">
							<html:text property="jxjmc" styleId="jxjmc" styleClass="inputtext"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							��ѧ�����
						</td>
						<td align="left">
							<html:select property="jxjlb" style="width:150px"
							 styleId="jxjlb" styleClass="select" disabled="true">
							 <html:option value=""></html:option>
								<html:options collection="jxjlbList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>��ѧ�𼶱�
						</td>
						<td align="left">
							<html:text property="jxjjb" styleId="jxjjb" styleClass="inputtext" maxlength="2" onblur="ckdata(this)"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>������
						</td>
						<td align="left">
							<html:text property="jlje" styleId="jlje" styleClass="inputtext" maxlength="6" ></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							���óɼ�ѧ�ֱ�׼��
						</td>
						<td align="left">
							<html:text property="szjdbz" styleId="szjdbz" styleClass="inputtext" maxlength="5" onblur="ckdata(this)"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							������չѧ�ֱ�׼��
						</td>
						<td align="left">
							<html:text property="sztzxfbz" styleId="sztzxfbz" styleClass="inputtext" maxlength="5" onblur="ckdata(this)"></html:text>
						</td>
						</tr>
					</logic:equal>
				</logic:present>
				<logic:present name="rychdmb">
					<logic:equal value="yes" name="rychdmb">
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>�����ƺŴ��룺
							</td>
							<td align="left">
								<html:text property="rychdm" styleId="rychdm" styleClass="inputtext" maxlength="${len }" value="${autoid }" readonly="true"></html:text>
							</td>
						</tr>
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>�����ƺ����ƣ�
							</td>
							<td align="left">
								<html:text property="rychmc" styleId="rychmc" styleClass="inputtext"></html:text>
							</td>
						</tr>
						<tr style="width:35px">
							<td align="right">
								�����ƺ����
							</td>
							<td align="left">
								<html:select property="rychlb" styleId="rychlb"
								style="width:150px" styleClass="select" disabled="true">
								<html:option value=""></html:option>
									<html:options collection="jxjlbList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
						</tr>
					</logic:equal>
				</logic:present>
				<logic:present name="jxjxdmb">
					<logic:equal value="yes" name="jxjxdmb">
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>�񽱴��룺
							</td>
							<td align="left">
								<html:text property="jxdm" styleId="jxdm" styleClass="inputtext" maxlength="10" value="${autoid }" readonly="true"></html:text>								
							</td>
						</tr>
						<tr style="width:35px">
							<td align="right">
								<font  color="red">*</font>�����ƣ�
							</td>
							<td align="left">
								<html:text property="jxmc" styleId="jxmc" styleClass="inputtext"></html:text>								
							</td>
						</tr>
					</logic:equal>
				</logic:present>	
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button class="button2" style="" id="btn_save" 
						onclick="savedm('pjpydmsave.do');">
						&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="window.close();return false;" >
						&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;
					</button>
			</logic:equal>
		</div>
	</fieldset>
	<logic:notEmpty name="inserted">
				<logic:equal value="no" name="inserted">
					<script language="javascript">
		alert("����ʧ�ܣ�");
		</script>
				</logic:equal>
				<logic:equal value="yes" name="inserted">
					<script language="javascript">
		alert("�����ɹ���");
		</script>
				</logic:equal>
				<script language="javascript">
			Close();
			var tN = window.dialogArguments.document.forms[0].tname.value;
			window.dialogArguments.document.getElementById(tN).click();
		</script>
			</logic:notEmpty>
	</html:form>
<script type="text/javascript">
		function savedm(url) {
			var realTable = document.getElementById('realTable').value;
			url += '?realTable=';
			url += realTable;
			if (realTable=='jxjdmb') {
				url += '&jxjlb=';
				url += document.getElementById('jxjlb').value;
				if (document.getElementById('jxjdm').value=='' || document.getElementById('jxjdm').value==null 
				|| document.getElementById('jxjmc').value=='' || document.getElementById('jxjmc').value==null 
				|| document.getElementById('jxjjb').value=='' || document.getElementById('jxjjb').value==null 
				|| document.getElementById('jlje').value=='' || document.getElementById('jlje').value==null) {
					alert('��*��Ϊ�����ȷ�ϣ�');
					return;
				}
			}
			if (realTable=='rychdmb') {
				url += '&rychlb=';
				url += document.getElementById('rychlb').value;
				if (document.getElementById('rychdm').value==null || document.getElementById('rychdm').value==''
				|| document.getElementById('rychmc').value==null || document.getElementById('rychmc').value=='') {
				alert('��*��Ϊ�����ȷ�ϣ�');
				return;
				}
			}
			if (realTable=='jxjxdmb') {
				if (document.getElementById('jxdm').value==null || document.getElementById('jxdm').value==''
				|| document.getElementById('jxmc').value==null || document.getElementById('jxmc').value=='') {
					alert('��*��Ϊ�����ȷ�ϣ�');
					return;
				}
			}
			
			refreshForm(url);
			document.getElementById('btn_save').disabled = true;
			//window.close();
		}
		function onlynum() {
			if (event.keyCode>=48 && event.keyCode<=57) {
				alert('ֻ����������!');
				document.getElementById('jxjjb').value='';
				return;
			}
		}
	</script>
</body>
