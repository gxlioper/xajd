
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
						<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript">
			function clearxx(){
				document.getElementById('cjmc').value='';
				document.getElementById('zhpfmc').value='';
				document.getElementById('cjmc').value='';
				document.getElementById('drzw').value='';
				document.getElementById('jtdz').value='';
				document.getElementById('zzmm').value='';
				document.getElementById('sjhm').value='';
				document.getElementById('zysj').value='';
				document.getElementById('btn_save').disabled=true;
			}
		</script>	
<body>
	<html:form action="/pjpyhzzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	
		
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� �� �����ƺ����� �� �����
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
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
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
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					<font color="red">*</font>�����ƺţ�
				</td>
				<td align="left">
					<html:select property="rychdm" styleId="rychdm">
						<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
					</html:select>
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
					�ɼ����Σ�
				</td>
				<td align="left">
					<html:text property="cjmc" styleId="cjmc"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					�ۺ��������Σ�
				</td>
				<td align="left">
					<html:text property="zhpfmc" styleId="zhpfmc"></html:text>
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
					������ò��
				</td>
				<td align="left">
					<html:text property="zzmm" styleId="zzmm"></html:text>
				</td>
				
			</tr>
			
			<tr style="height:23px">
				<td align="right">
					����ְ��
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw"></html:text>
				</td>
				<td align="right">
					���壺
				</td>
				<td align="left">
					<html:text property="mz" styleId="mz"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�绰��
				</td>
				<td align="left">
					<html:text property="sjhm" styleId="sjhm"></html:text>
				</td>
				<td align="right">
					
				</td>
				<td align="left">
					
				</td>
				
			</tr>
			<tr style="height:23px">
				
				<td align="right">
					��ͥ��ϸ��ַ��
				</td>
				<td align="left" colspan="3">
					<html:text property="jtdz" styleId="jtdz" style="width:98%"></html:text>
				</td>
				
			</tr>
			
			<tr style="height:23px">
				<td align="right">
					��Ҫ�¼���
					<br/>
							<span class="style1">(������800����)</span>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="zysj" styleId="zysj" rows="8" style="width:98%"></html:textarea>
				</td>
				
				
			</tr>
			
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('hzzyrychmodisave.do','xh-rychdm');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<!-- ������ʾ -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
		
	</html:form>
</body>
