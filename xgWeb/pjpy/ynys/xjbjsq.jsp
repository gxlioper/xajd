
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript">
<!--
	function saveXjbj() {
		if (document.getElementById('bj').value == '' || document.getElementById('bj').value == null) {
			alert('��*�ŵ�����Ϊ��ѡ��!');
			return;
		} else {
			refreshForm('ynys_xjbjsave.do');
			document.getElementById('btn_save').disabled = true;
		}
	}	
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyynyswh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message key="pjpy_ynys_xjbjsq" bundle="pjpyynys" />
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<tr>
				<th colspan="4" align="center">
					<div align="center" style="font-size:18px;font:'����' ">
						${tit }
					</div>
				</th>
			</tr>
			<tr height="22px">
				<td align="right" style="width:15%">
					Ժ(ϵ):
				</td>
				<td align="left">
					<html:select property="xydm" styleId="xy"
						onchange="initZyList();initBjList()" styleClass="select"
						style="width:180px">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"
							labelProperty="xymc" />
					</html:select>
				</td>
				<td align="right" style="width:15%">
					�꼶:
				</td>
				<td align="left">
					<html:select property="nj" styleId="nj"
						onchange="initZyList();initBjList()" styleClass="select">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj" labelProperty="nj" />
					</html:select>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					רҵ:
				</td>
				<td align="left">
					<html:select property="zydm" styleId="zy" onchange="initBjList()"
						styleClass="select" style="width:180px">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"
							labelProperty="zymc" />
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>�༶:
				</td>
				<td align="left">
					<html:select property="bjdm" styleId="bj" styleClass="select"
						style="width:180px">
						<html:option value=""></html:option>
						<html:options collection="bjList" property="bjdm"
							labelProperty="bjmc" />
					</html:select>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					ѧ��:
				</td>
				<td align="left">
					${xn }
				</td>
				<td align="right">
					&nbsp;
				</td>
				<td align="left">
					&nbsp;
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					�༶����:
				</td>
				<td align="left">
					<html:text property="bjrs" styleId="bjrs" maxlength="3"
						onkeypress="chkData1();" styleClass="input"></html:text>
				</td>
				<td align="right">
					������:
				</td>
				<td align="left">
					<html:text property="bzr" styleId="bzr" maxlength="15"
						styleClass="input"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					�༶������:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bjdbqk" styleId="bjdbqk" rows="8"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					���������:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bzryj" styleId="bzryj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<td align="right">
					����Ա���:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<button class="button2" id="btn_save" onclick="saveXjbj()"
				style="width:80px">
				�� ��
			</button>
			&nbsp;&nbsp;
			<button class="button2" id="btn_print"
				onclick="window.open('ynys_printxjbj.do?bjdm=' + document.getElementById('bj').value)"
				style="width:80px">
				��ӡ����
			</button>
		</div>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ�!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ��!');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
