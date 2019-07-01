<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:form action="/jtpjsq" styleId="innerForm">
	<table width="" border="0" class="formlist">
		<html:hidden property="iswzdx" styleId="iswzdx" value="${iswzdx}"/>
		<tbody>
			<tr>
				<th width="20%" style="border-top: 0px;">
					班级
				</th>
				<td colspan="3" style="border-top: 0px;">
					${data.pjjtmc}
				</td>
			</tr>
			<tr>
				<th width="20%">
					班级人数
				</th>
				<td width="30%">
					${bjrs}
					<html:hidden property="rs" value="${bjrs}" />
				</td>
				<th width="20%">
					寝室数
				</th>
				<td width="30%">
					${qss}
				</td>
			</tr>
			<logic:iterate name="bzrList" id="bzr">
				<tr>
					<th width="20%">
						班主任
					</th>
					<td width="30%">
						${bzr.xm}
					</td>
					<th width="20%">
						联系电话
					</th>
					<td>
						${bzr.lxdh}
					</td>
				</tr>
			</logic:iterate>
			<logic:iterate name="fdyList" id="fdy">
				<tr>
					<th width="20%">
						辅导员
					</th>
					<td width="30%">
						${fdy.xm}
					</td>
					<th width="20%">
						联系电话
					</th>
					<td>
						${fdy.lxdh}
					</td>
				</tr>
			</logic:iterate>
		<logic:equal name="iswzdx" value="1">
			<tr>
				<th width="20%">
					学生寝室文明状况
				</th>
				<td colspan="3">
					<div id="qswmxx">

					</div>
				</td>
			</tr>
		</logic:equal>
	</table>
</html:form>