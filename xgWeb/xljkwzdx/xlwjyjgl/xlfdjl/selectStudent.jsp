<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
		List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
	<logic:present name="jbxxList">
		<tr>
			<logic:iterate id="j" name="jbxxList" indexId="i">
				<th width="16%">
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="0" name="i">
							<logic:notEqual name="type" value="update">
								<font color="red">*</font>
							</logic:notEqual>
						</logic:equal>
					</logic:notEqual>
					${j.zdmc }
				</th>
				<td width="34%">
					<logic:equal value="stu" name="userType">
						<html:hidden property="xh" styleId="xh"/>
						<logic:present name="jbxx">
							<bean:write name="jbxx" property="${j.zddm }"/>
						</logic:present>
					</logic:equal>
					
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="0" name="i">
							
							<logic:notEqual name="type" value="update">
								<input type="text" name="xh" value="${jbxx.xh }" readonly="true" id="xh" style="width:120px;"/>
								<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个学生',800,500,'xsxx_xsgl.do?method=showStudentsForXlfdlr&goto=${path}');">选择</button>
							</logic:notEqual>
							
							<logic:equal name="type" value="update">
								<input type="hidden" name="xh" value="${jbxx.xh }" id="xh"/>
								${jbxx.xh }
							</logic:equal>
								
						</logic:equal>
						<logic:notEqual value="0" name="i">
							<logic:present name="jbxx">
								<bean:write name="jbxx" property="${j.zddm }"/>
							</logic:present>
						</logic:notEqual>
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