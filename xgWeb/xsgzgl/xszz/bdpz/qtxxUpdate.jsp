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
					<th>
						<logic:equal value="yes" name="j" property="sfbt">
							<font color="red">*</font>
						</logic:equal>
						${j.zdmc }
					</th>
				</logic:notEqual>
				<logic:equal value="radio" name="j" property="kjlx">
					<td name="radioTd" property="${j.zddm }" source="${j.sjy }" 
						tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
						<logic:equal value="yes" name="j" property="sfbt">
							sfbt="yes"
						</logic:equal>
					></td>
				</logic:equal>
				<logic:equal value="text" name="j" property="kjlx">
					<td>
						<input type="text" name="${j.zddm}" maxlength="${j.zdcd}" style="${j.kjys}"
							<logic:equal value="yes" name="j" property="sfbt">
								sfbt="yes"
							</logic:equal>
							   value='<bean:write name="mkxxForm" property="${j.zddm}"/>'
							   onblur="${j.zdgs }"
							   style="${j.kjys }"
						/>
					</td>
				</logic:equal>
				<logic:equal value="view" name="j" property="kjlx">
					<td>
						<bean:write name="mkxxForm" property="${j.zddm}"/>
					</td>
				</logic:equal>
				<logic:equal value="select" name="j" property="kjlx">
					<td>
						<select name="${j.zddm}" style="${j.kjys}" source="${j.sjy }" type="source"
								tempValue='<bean:write name="mkxxForm" property="${j.zddm}"/>'
							<logic:equal value="yes" name="j" property="sfbt">
								sfbt="yes"
							</logic:equal>
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
							<logic:equal value="yes" name="j" property="sfbt">
								<font color="red">*</font>
							</logic:equal>
							${j.zdmc }
							<br/>
							<font color="red">
								&lt;ÏÞ${j.zdcd }×Ö&gt;
							</font>
						</th>
						<td colspan="3" style="word-break:break-all;">
							<textarea rows="5" style="width:98%;${j.kjys}" name="${j.zddm}"
									  onblur="checkLen(this,${j.zdcd });" 
									  <logic:equal value="yes" name="j" property="sfbt">
										  sfbt="yes"
									  </logic:equal>
							><bean:write name="mkxxForm" property="${j.zddm}"/></textarea>
						</td>
					</tr>
				</logic:equal>
			</logic:iterate>
		</tr>
	</logic:present>
</tbody>