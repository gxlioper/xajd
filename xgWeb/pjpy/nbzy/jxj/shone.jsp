<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpynbzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkVlaue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� �� ��� �� ��ѧ�����
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
						${rs.xh }
				</td>
				<td align="right">
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					<font color="red">*</font>�걨��ѧ��ȼ�:
				</td>
				<td align="left">
					${rs.jxjmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					���Һ�:
				</td>
				<td align="left">
					${rs.qsh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					ְҵ��������������:
				</td>
				<td align="left">
					${rs.zyjnsyf }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					�ɳ�����չ���ʲ�����:
				</td>
				<td align="left">
					${rs.kcxfzsyf }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					ְҵ����������:
				</td>
				<td align="left">
					${rs.zysyf }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					${rs.bjmc }
				</td>
				<td align="right">
					����:
				</td>
				<td align="left">
					${rs.zypm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�ۺϲ����ܷ�:
				</td>
				<td align="left">
					${rs.zhcpzf }
				</td>
				<td align="right">
					����:
				</td>
				<td align="left">
					${rs.zhpm }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<logic:equal value="xy" name="userType"><bean:message key="lable.xsgzyxpzxy" /></logic:equal><logic:notEqual value="xy" name="userType">ѧУ</logic:notEqual>���:
				</td>
				<td align="left" colspan="3">
					<html:select property="yesNo" styleId="yesNo">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					��������:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" rows="3" readonly="true" styleId="sqly" style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶����&nbsp;<br/>С�����:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" style="width:95%" readonly="true" rows="3"></html:textarea>
				</td>
			</tr>
			<logic:equal value="xy" name="userType">
				<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />�ۺϲ�&nbsp;<br/>��С�����:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj"  style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="xy" name="userType">
				<tr style="height:23px">
				<td align="right">
					ѧ����<br/>���:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" style="width:95%" rows="3"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />���:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyshyj" styleId="xyshyj" style="width:95%" rows="3"></html:textarea>
				</td>
			</tr>
			</logic:notEqual>
		</table>
		<div class="buttontool" align="center">
					<logic:notPresent name="act">
						<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nbzy_shone.do?act=save','');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notPresent>
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						�ر�
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ�!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ��!');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
