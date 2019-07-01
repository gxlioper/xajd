<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
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
						${da.dabh }.${da.damc }&nbsp;&nbsp;&nbsp;&nbsp;（${da.num }/${da.rs }）
					</td>
					<%if((index.intValue()+1)%2==0){%>
						<% out.print("</tr>"); %>
					<%}%> 
				</logic:notEqual>
				<logic:equal name="st" property="danum" value="${index+1 }">
					<%if((index.intValue()+1)%2==0){%>
						<td>
							${da.dabh }.${da.damc }&nbsp;&nbsp;&nbsp;&nbsp;（${da.num }/${da.rs }）
						</td>
					<%}%> 	
					<%if((index.intValue()+1)%2==1){%>
						<td>
							${da.dabh }.${da.damc }&nbsp;&nbsp;&nbsp;&nbsp;（${da.num }/${da.rs }）
						</td>
						<td></td>
					<%}%> 	
				</logic:equal>
			</logic:iterate>
		</logic:iterate>
	</logic:iterate>
</logic:notEmpty>