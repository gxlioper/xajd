<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript">
function saveinfo(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	document.getElementById('btn_save').disabled = true;
	refreshForm(url);
}
</script>
<body>
	<html:form action="/pjpyhxxywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��ѧ������ - ��������ѯ
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����޸�
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					ѧ�ţ�
				</td>
				<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					
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
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
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
					ѧ�ڣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					���壺
				</td>
				<td align="left">
					<bean:write name="rs" property="mzmc" />
				</td>
				<td align="right">
					������ò��
				</td>
				<td align="left">
					<bean:write name="rs" property="zzmmmc" />
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
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�������£�
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
				</td>
				<td align="right">
					��ѧʱ�䣺
				</td>
				<td align="left">
					<bean:write name="rs" property="rxrq" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					����ˮƽ��
				</td>
				<td align="left">
					<html:text property="wysp" styleId="wysp" styleClass="inputtext" maxlength="50"></html:text>
				</td>
				<td align="right">
					��ϵ�绰��
				</td>
				<td align="left">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���ι��������
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw" styleClass="inputtext" maxlength="100"></html:text>
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ƽ��ѧ�ּ��㣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xf" />
				</td>
				<td align="right">
					�༶������
				</td>
				<td align="left">
					<bean:write name="rs" property="xfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�ۺ����ʲ�����
				</td>
				<td align="left">
					<bean:write name="rs" property="zf" />
				</td>
				<td align="right">
					�༶������
				</td>
				<td align="left">
					<bean:write name="rs" property="zfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������ͳɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="zdcj" />
				</td>
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="dcj" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�����ӷ֣�
				</td>
				<td align="left">
					<bean:write name="rs" property="jlf" />
				</td>
				<td align="right">
					���ʽ���������
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
			</tr>
			<tr >
				<td align="right">
					�������ɣ�
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
		<logic:notEqual value="view" name="flag">
					<button class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_hxxy_jxjmodi.do?act=save','jxjdm');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ�ܣ�');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
