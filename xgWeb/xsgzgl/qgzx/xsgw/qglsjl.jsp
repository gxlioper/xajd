<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<style>

#shlccx_table th{text-align: center;}
#shlccx_table tr{text-align: center;}
</style>
<div class="con_overlfow">
<table id="shlccx_table" width="100%" class="formlist" >
	<tr id="sh_th">
		<th>Ͷ�ݸ�λ</th>
		<th>��λ����</th>
		<th>Ͷ������</th>
		<th>¼������</th>
		<th>״̬</th>
	</tr>
	<logic:empty name="qglsjlList">
		<tr>
			<td colspan="5">��ʱû������</td>
		</tr>
	</logic:empty>
	<logic:notEmpty name="qglsjlList">
		<logic:iterate id="i" name="qglsjlList" >
			<tr>
				<td>${i.gwmc}</td>
				<td>${i.yrdwmc}</td>
				<td>${i.sqsj}</td>
				<td>${i.sgsj}</td>
				<td>${i.zgzt}</td>
			</tr>
		</logic:iterate>
	</logic:notEmpty>
</table>
</div>
