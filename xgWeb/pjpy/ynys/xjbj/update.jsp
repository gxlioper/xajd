<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript">

	function saveXjbj() {
			refreshForm('ynys_xjbjmodisave.do');
			document.getElementById('btn_save').disabled = true;
	}	

</script>
</head>
<body onload="">
	<html:form action="/pjpyynyswh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
	
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a><bean:message key="pjpy_ynys_xjbjsq" bundle="pjpyynys" /></a>
			</p>
		</div>
			
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
				<tr><th colspan="4" align="center">
					<span>�����޸�</span>
				</th></tr>
			</thead>
			<tbody>
			<logic:equal value="10863" name="xxdm">
			<tr>
				<th align="right" style="width:15%">
					Ժ(ϵ)
				</th>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<th align="right" style="width:15%">
					��
				</th>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					רҵ
				</th>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<th align="right" style="width:15%">
					�༶
				</th>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					ѧ��
				</th>
				<td align="left">
					<html:select property="xn" styleId="xn">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
				<th align="right" style="width:15%">
					������
				</th>
				<td align="left">
					<html:text property="bzr" styleId="bzr"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					�೤
				</th>
				<td align="left">
					<html:text property="bzxm" styleId="bzxm"></html:text>
				</td>
				<th align="right" style="width:15%">
					�༶����
				</th>
				<td align="left">
					<html:text property="xsrs" styleId="xsrs"></html:text>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					��֧��
				</th>
				<td align="left">
					<html:text property="tzs" styleId="tzs"></html:text>
				</td>
				<td align="right" style="width:15%">
					
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					�������޲��ϸ�����
				</th>
				<td align="left">
					<html:radio property="bhgqs" value="0">��</html:radio>
					<html:radio property="bhgqs" value="1">��</html:radio>
				</td>
				<th align="right" style="width:15%">
					����ͬѧ�ܹ�����,��,�żʹ���
				</th>
				<td align="left">
					<html:radio property="ywcf" value="0">��</html:radio>
					<html:radio property="ywcf" value="1">��</html:radio>
				</td>
			</tr>
			<tr>
				<th align="right" style="width:15%">
					�༶��������
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bjry" styleId="bjry" style="width:95%" rows="5"></html:textarea>
				</td>
				
			</tr>
			<tr>
				<th align="right" style="width:15%">
					��Ҫ�ɼ����
				</th>
				<td align="left" colspan="3">
					<html:textarea property="zysj" styleId="zysj" style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
			</logic:equal>
			<logic:notEqual value="10863" name="xxdm">
				<logic:equal value="10690" name="xxdm">
				<tr height="22px">
				<th align="right" style="width:15%">
					Ժ(ϵ)
				</th>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<th align="right" style="width:15%">
					�꼶
				</th>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					רҵ
				</th>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<th align="right">
					�༶
				</th>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					ѧ��
				</th>
				<td align="left">
					<bean:write name="rs" property="xn"/>
				</td>
				<th align="right">
					�༶����
				</th>
				<td align="left">
					�Ƚ��༯��
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					�༶����
				</th>
				<td align="left">
					<html:text property="bjrs" styleId="bjrs" maxlength="3"
						onkeypress="chkData1();" styleClass="input"></html:text>
				</td>
				<th align="right">
					����
				</th>
				<td align="left">
					<html:text property="bzr" styleId="bzr" maxlength="15"
						styleClass="input"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					�༶������
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bjdbqk" styleId="bjdbqk" rows="8"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					���������
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bzryj" styleId="bzryj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr height="22px">
				<th align="right">
					����Ա���
				</th>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
				</logic:equal>
				<logic:notEqual value="10690" name="xxdm">
					<tr height="22px">
				<th align="right" style="width:15%">
					Ժ(ϵ)
				</th>
				<td align="left">
					<bean:write name="rs" property="xymc"/>
				</td>
				<th align="right" style="width:15%">
					�꼶
				</th>
				<td align="left">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					רҵ
				</th>
				<td align="left">
					<bean:write name="rs" property="zymc"/>
				</td>
				<th align="right" style="width:15%">
					�༶
				</th>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>
				</td>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					ѧ��
				</th>
				<td align="left">
					<html:text property="xn" styleId="xn" readonly="true"></html:text>
				</td>
				<th align="right" style="width:15%">
					������
				</th>
				<td align="left">
					<html:text property="bzr" styleId="bzr"></html:text>
				</td>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					�೤
				</th>
				<td align="left">
					<html:text property="bzxm" styleId="bzxm"></html:text>
				</td>
				<th align="right" style="width:15%">
					�༶����
				</th>
				<td align="left">
					<html:text property="xsrs" styleId="xsrs"></html:text>
				</td>
			</tr>
			
			<tr height="22px">
				
				<logic:equal value="12682" name="xxdm">
				<th align="right" style="width:15%">
					����ƺ�
				</th>
				<td align="left" colspan="">
					<html:text property="jtch" styleId="jtch" style="width:200px"></html:text>
				</td>
				</logic:equal>
				<logic:notEqual value="12682" name="xxdm">
				<th align="right" style="width:15%">
					�༶��������
				</th>
				<td align="left" colspan="">
					${rs.rychmc }
				</td>
				</logic:notEqual>
			</tr>
			<tr height="22px">
				<th align="right" style="width:15%">
					��Ҫ�¼�
				</th>
				<td align="left" colspan="3">
					<html:textarea property="zysj" styleId="zysj" style="width:95%" rows="5"></html:textarea>
				</td>
			</tr>
			<logic:equal value="10355" name="xxdm">
			<tr height="22px">
				<th align="right" style="width:15%">
					��ע<font color="red">(�༶ѧ��<br/>�ܴ���,����,<br/>����������)</font>
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" 
					style="width:95%" rows="5" readonly="true"></html:textarea>
				</td>
			</tr>
			</logic:equal>
				</logic:notEqual>
			</logic:notEqual>
			</tbody>
			
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <logic:notPresent name="act">
			          		<button type="button" name="�ύ" id="btn_save"
			          		onclick="saveXjbj()">�� ��</button>
			      	 </logic:notPresent>
			            <button type="button" name="�ر�" id="btn_close" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
