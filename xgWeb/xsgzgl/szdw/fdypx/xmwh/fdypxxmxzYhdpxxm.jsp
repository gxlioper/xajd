<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<style>

#tbody_jg th{text-align: center;}
#tbody_jg tr{text-align: center;}
</style>

<div class="con_overlfow">
<table width="100%" border="0" class="formlist" >
	<tbody id="tbody_jg">
		<tr id="sh_th">
			<th >��Ŀ����</th>
			<th >��ѵʱ��</th>
			<th > ��ѵ�ص�</th>
		</tr>
		<logic:empty name="sqjgList">
			<tr>
				<td colspan="3">��ʱû������</td>
			</tr>
		</logic:empty>
		<logic:notEmpty name="sqjgList">
			<logic:iterate id="jg" name="sqjgList">
				<tr>
					<td>${jg.xmmc}</td>
					<td>${jg.pxsj}</td>
					<td>${jg.pxdd}</td>
				</tr>
			</logic:iterate>
		</logic:notEmpty>
	</tbody>
</table>
</div>