<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
</head>
<body style="width: 100%">
<html:form action="/jjgl_jjlsjggl" method="post" styleId="jjlsjgForm" onsubmit="return false;">
	<html:hidden property="xh" styleId="xh"/>
	<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
					<span>ѧ��������Ϣ</span>
				</th>
			</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
			<tr>
				<th colspan="4">
					<span>�ҽ���Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th width="20%">
					<span class="red">*</span>�ó���Ŀ
				</th>
				<td width="80%" colspan="3">
					${jjlsjgForm.sckmmcs}
				</td>
			</tr>
			<tr>
				<th width="20%">
					�ҽ��꼶
				</th>
				<td width="30%">
					${jjlsjgForm.jjnjmc}
				</td>
				<th width="20%">
					��ϵ�绰
				</th>
				<td width="30%">
					${jjlsjgForm.lxdh}
				</td>
			</tr>

			<tr>
				<th width="20%">
					��ѧ����
				</th>
				<td colspan="3" width="80%">
					${jjlsjgForm.jxxy}
				</td>

			</tr>
			<tr>
				<th width="20%">
					�����Ա
				</th>
				<td width="30%">
					<input type="checkbox" name="yxjy" value="1" ${jjlsjgForm.yxjy=="1" ? "checked" : ""} onclick="return false" />
				</td>
				<th>�ö���ʾ</th>
				<td>
					<input type="checkbox" name="zdxs" value="1" ${jjlsjgForm.zdxs=="1" ? "checked" : ""} onclick="return false" />
				</td>
			</tr>
			</tbody>
		</table>
	</div>
	<div style="height:35px"></div>
	<div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			<tr>
				<td colspan="4">
					<div class="btn">
						<button type="button" onclick="iFClose();">
							�ر�
						</button>
					</div>
				</td>
			</tr>
			</tfoot>
		</table>
	</div>
</html:form>
</body>
</html>

