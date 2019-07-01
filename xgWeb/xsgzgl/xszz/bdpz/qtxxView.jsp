<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
		boolean tempQtxx = true;	
		List<HashMap<String,String>> qtxxList = (List<HashMap<String,String>>)request.getAttribute("qtxxList");
	%>
	<logic:present name="qtxxList">
		<tr>
			<logic:iterate id="j" name="qtxxList" indexId="i">
				<logic:notEqual value="textarea" name="j" property="kjlx">
					<th width="16%">
						${j.zdmc }
					</th>
				</logic:notEqual>
				<logic:equal value="radio" name="j" property="kjlx">
					<td width="34%" name="radioTd" property="${j.zddm }" source="${j.sjy }" 
						tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
					></td>
				</logic:equal>
				<logic:equal value="text" name="j" property="kjlx">
					<td width="34%" class="breakword">
						<bean:write name="mkxxForm" property="${j.zddm}"/>
					</td>
				</logic:equal>
				<logic:equal value="view" name="j" property="kjlx">
					<td>
						<bean:write name="mkxxForm" property="${j.zddm}"/>
					</td>
				</logic:equal>
				<logic:equal value="select" name="j" property="kjlx">
					<td width="34%" >
						<select name="${j.zddm}" source="${j.sjy }" type="source"
								tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
						>
							<option></option>
						</select>
					</td>
				</logic:equal>
				<logic:notEqual value="textarea" name="j" property="kjlx">
					<%
						if ((i+1) % 2 == 0 && i != qtxxList.size()-1){
					%>
						</tr>
						<tr>
					<%
						}
					%>
				</logic:notEqual>
				<logic:equal value="textarea" name="j" property="kjlx">
					<%
						if ((i-1)%2 == 0 && tempQtxx){
					%>
						<th></th>
						<td></td>
					</tr>
					<%	
						} 
						tempQtxx =false;
					%>	
					<tr>
						<th>
							${j.zdmc }
						</th>
						<td colspan="3" class="breakword">
							<bean:write name="mkxxForm" property="${j.zddm}" filter="false"/>
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</tr>
	</logic:present>
</tbody>