<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<tbody>
	<%
		List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
	<logic:present name="jbxxList">
		<tr>
			<logic:iterate id="j" name="jbxxList" indexId="i">
				<th width="16%">
					${j.zdmc }
				</th>
				<td width="34%">
					<bean:write name="jbxx" property="${j.zddm }"/>
				</td>
				<%
					if ((i+1) % 2 == 0 && i != jbxxList.size()-1){
				%>
					</tr>
					<tr>
				<%
					}
				%>
			</logic:iterate>
		</tr>
	</logic:present>
</tbody>