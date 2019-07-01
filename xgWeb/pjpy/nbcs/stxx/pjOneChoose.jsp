<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 无题目 -->
<logic:empty name="oneChooseList">

</logic:empty>
<!-- 有题目 -->
<logic:notEmpty name="oneChooseList">
	<logic:iterate name="oneChooseList" id="stss" indexId="ss">
		<tr>
			<td colspan="2">
				<font size="5">${stss.num }.${stss.ssmc }</font>
				<input type="hidden" name="oneStNum${ss }" value="${stss.stnum }"/>	
			</td>
		</tr>
		<logic:iterate name="stss" property="stList" id="st" indexId="num">
			<tr>
				<td colspan="2">
					<font size="3">${num+1 }.${st.stmc }</font>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:empty name="st" property="bzda">
						<font color="red"> 无标准答案</font>
					</logic:empty>
					<logic:notEmpty name="st" property="bzda">
						<font color="red"> 标准答案：${st.bzda }</font>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red"> ${st.isTrue }</font>
					</logic:notEmpty>
					<input type="hidden" name="oneChooseBh" value="${st.stbh }"/>	
				</td>
			</tr>
			<logic:iterate name="st" property="stda" id="da"  indexId="index">
				<logic:notEqual name="st" property="danum" value="${index+1 }">
					<td>
						<logic:equal name="da" property="isChecked" value="true">
							<input type="radio" name="one_da${ss }${num }" id="one_da${ss }${num }${index+1 }" value="${da.dabh }" checked="checked"/>	
						</logic:equal>
						<logic:equal name="da" property="isChecked" value="false">
							<input type="radio" name="one_da${ss }${num }" id="one_da${ss }${num }${index+1 }" value="${da.dabh }"/>	
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
								<input type="radio" name="one_da${ss }${num }" id="one_da${ss }${num }${index+1 }" value="${da.dabh }" checked="checked"/>	
							</logic:equal>
							<logic:equal name="da" property="isChecked" value="false">
								<input type="radio" name="one_da${ss }${num }" id="one_da${ss }${num }${index+1 }" value="${da.dabh }"/>	
							</logic:equal>
							${da.dabh }.${da.damc }
						</td>
					<%}%> 	
					<%if((index.intValue()+1)%2==1){%>
						<td>
							<logic:equal name="da" property="isChecked" value="true">
								<input type="radio" name="one_da${ss }${num }" id="one_da${ss }${num }${index+1 }" value="${da.dabh }" checked="checked"/>	
							</logic:equal>
							<logic:equal name="da" property="isChecked" value="false">
								<input type="radio" name="one_da${ss }${num }" id="one_da${ss }${num }${index+1 }" value="${da.dabh }"/>	
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