<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
	<table width="" border="0" class="formlist" style="border-top: 0px;">
		
		<html:hidden property="jtpjdw" value="bj" />
		<html:hidden property="iswzdx" styleId="iswzdx" value="${iswzdx}"/>
		<tr >
			<th width="20%" style="border-top: 0px;">
				<span class="red">*</span>�༶
			</th>			
			<td colspan="3" style="border-top: 0px;">
			<logic:notEqual value="stu" name="userType">
				<html:hidden property="pjjtmc" styleId="pjjtmc" />
				<html:select property="pjjtid" styleId="pjjtid" style="display:none"
					onchange="loadBjpjxx();" value="${bjdm}">
					<html:option value=""></html:option>
					<html:options collection="bjList" property="bjdm"
						labelProperty="bjmc" />
				</html:select>
				<html:text property="bjmc" value="${bjmc}" styleId="bjmc" style="width:80%"/>
				<button class="btn_01" type="button" onclick="showDialog('��ѡ��һ���༶',800,500,'jtpjsq.do?method=bjManage&flag=jtpj');return false;">ѡ��</button>
			</logic:notEqual>
			<logic:equal value="stu" name="userType">
				<html:hidden property="pjjtmc" styleId="pjjtmc" value="${bjmc}"/>
				<html:hidden property="pjjtid" styleId="pjjtid" value="${bjdm}"/>
				<html:hidden property="bjmc" styleId="bjmc" value="${bjmc}"/>
				${bjmc}
			</logic:equal>
			</td>
		</tr>
		<tr>
			<th width="20%">
				�༶����
			</th>
			<td width="30%">
				${bjrs}
				<html:hidden property="rs" value="${bjrs}" />
			</td>
			<th width="20%">
				������
			</th>
			<td width="30%">
				${qss}
			</td>
		</tr>
		<logic:iterate name="bzrList" id="bzr">
			<tr>
				<th width="20%">
					������
				</th>
				<td width="30%">
					${bzr.xm}
				</td>
				<th width="20%">
					��ϵ�绰
				</th>
				<td>
					${bzr.lxdh}
				</td>
			</tr>
		</logic:iterate>
		<logic:iterate name="fdyList" id="fdy">
			<tr>
				<th width="20%">
					����Ա
				</th>
				<td width="30%">
					${fdy.xm}
				</td>
				<th width="20%">
					��ϵ�绰
				</th>
				<td>
					${fdy.lxdh}
				</td>
			</tr>
		</logic:iterate>
		<logic:equal name="iswzdx" value="1">
			<tr>
				<th width="20%">
					ѧ����������״��
				</th>
				<td colspan="3">
					<div id="qswmxx">

					</div>
				</td>
			</tr>
		</logic:equal>
	</table>
</html:form>