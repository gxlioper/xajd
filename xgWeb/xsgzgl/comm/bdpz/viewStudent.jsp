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
					<logic:equal value="xh" property="zddm" name="j">
					<%--<a href="javascript:void(0);" class="name" 
						   onclick="showTopWin('stu_info_details.do?xh=<bean:write name="jbxx" property="${j.zddm }"/>',700,500)"
						   style="margin-left: 1px;"
						 ><bean:write name="jbxx" property="${j.zddm }"/></a>
					--%>
					<a href="javascript:void(0);" class="name" 
						   onclick="showDialog('学生详细信息',700,500,'xsxx_tygl.do?method=ckZxsxx&xh=<bean:write name="jbxx" property="${j.zddm }"/>')"
						   style="margin-left: 1px;"
						 ><bean:write name="jbxx" property="${j.zddm }"/></a>
					</logic:equal>
					<logic:notEqual value="xh" property="zddm" name="j">
						<bean:write name="jbxx" property="${j.zddm }"/>
					</logic:notEqual>
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