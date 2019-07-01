<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
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
					<logic:equal value="zgh" property="zddm" name="j">
					<%--<a href="javascript:void(0);" class="name" 
						   onclick="showTopWin('stu_info_details.do?xh=<bean:write name="jbxx" property="${j.zddm }"/>',700,500)"
						   style="margin-left: 1px;"
						 ><bean:write name="jbxx" property="${j.zddm }"/></a>
					--%>
					<a href="javascript:void(0);" class="name" 
						   onclick="showDialog('教师详细信息',700,500,'szdw_dwwh.do?method=ckJzgxx&zgh=<bean:write name="jbxx" property="${j.zddm }"/>')"
						   style="margin-left: 1px;"
						 ><bean:write name="jbxx" property="${j.zddm }"/></a>
					</logic:equal>
					<logic:notEqual value="zgh" property="zddm" name="j">
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