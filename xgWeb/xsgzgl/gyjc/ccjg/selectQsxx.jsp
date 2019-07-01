<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
		List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
	<logic:present name="jbxxList">
		<tr>
				<th width="20%">
				楼栋
				</th>
				<td width="30%">
					<bean:write name="jbxx" property="${j.zddm }"/>
			
			
								<button class="btn_01" type="button"  
										onclick="showDialog('请选择一个学生',800,500,'xsxx_xsgl.do?method=showStudents&goto=${path}');return false;">选择</button>
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