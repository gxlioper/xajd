<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 无题目 -->
<logic:empty name="allChooseList">

</logic:empty>
<!-- 有题目 -->
<logic:notEmpty name="allChooseList">
	<logic:iterate name="allChooseList" id="stss" indexId="ss">
		<tr>
			<td colspan="2">
				<font size="5">${stss.num }.${stss.ssmc }</font>
				<input type="hidden" name="allStNum${ss }" value="${stss.stnum }"/>	
			</td>
		</tr>
		<logic:iterate name="stss" property="stList" id="st" indexId="num">
			<tr>
				<td colspan="2">
					<font size="3">${num+1 }.${st.stmc }</font>
					<input type="hidden" name="allChooseBh" value="${st.stbh }"/>	
				</td>
			</tr>
			<logic:iterate name="st" property="stda" id="da"  indexId="index">
				<logic:notEqual name="st" property="danum" value="${index+1 }">
					<td>
						<logic:equal name="da" property="isChecked" value="true">
							<input type="checkbox" name="all_da${ss }${num }" id="all_da${ss }${num }${index+1 }" value="${da.dabh }" checked="checked"/>
						</logic:equal>
						<logic:equal name="da" property="isChecked" value="false">
							<input type="checkbox" name="all_da${ss }${num }" id="all_da${ss }${num }${index+1 }" value="${da.dabh }"/>
						</logic:equal>
						${da.dabh }.${da.damc }
					</td>
					<%if((index.intValue()+1)%2==0){%>
						<% out.print("</tr>"); %>
					<%}%> 
				</logic:notEqual>
				<logic:equal name="st" property="danum" value="${index+1 }">
					<%if((index.intValue()+1)%2==0){%>
						<td>
							<logic:equal name="da" property="isChecked" value="true">
								<input type="checkbox" name="all_da${ss }${num }" id="all_da${ss }${num }${index+1 }" value="${da.dabh }" checked="checked"/>
							</logic:equal>
							<logic:equal name="da" property="isChecked" value="false">
								<input type="checkbox" name="all_da${ss }${num }" id="all_da${ss }${num }${index+1 }" value="${da.dabh }"/>
							</logic:equal>
							${da.dabh }.${da.damc }
						</td>
					<%}%> 	
					<%if((index.intValue()+1)%2==1){%>
						<td>
							<logic:equal name="da" property="isChecked" value="true">
								<input type="checkbox" name="all_da${ss }${num }" id="all_da${ss }${num }${index+1 }" value="${da.dabh }" checked="checked"/>
							</logic:equal>
							<logic:equal name="da" property="isChecked" value="false">
								<input type="checkbox" name="all_da${ss }${num }" id="all_da${ss }${num }${index+1 }" value="${da.dabh }"/>
							</logic:equal>
							${da.dabh }.${da.damc }
						</td>
						<td></td>
					<%}%> 	
				</logic:equal>
			</logic:iterate>
		</logic:iterate>
	</logic:iterate>
</logic:notEmpty>