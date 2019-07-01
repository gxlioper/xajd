
<%
String colCN = request.getAttribute("colNameCN").toString();
String col = request.getAttribute("colName").toString();
String rsInfo = request.getAttribute("rsInfo").toString();
String vCols = request.getAttribute("vCols").toString();
String[] colNameCN = colCN.split("!!SplitSignOne!!");
String[] colName = col.split("!!SplitSignOne!!");
int i =1;
int j =1;
%>
<input type="hidden" name="vCols" value="<%=vCols%>">
<input type="hidden" name="rsInfo" value="<%=rsInfo%>">
<input type="hidden" name="colNameList" value="<%=col%>">
<input type="hidden" name="colNameCNList" value="<%=colCN%>">

<div class="con_overlfow">
<table summary="" class="dateline" align="" width="100%" id="rsTable">
	<thead>
		<tr>
			<%			
for(i=1;i<colNameCN.length;i++){
%>
			<td onclick="tableSort111(this)" style="cursor:hand" id="<%=i-1%>">
				<%=colNameCN[i]%>
			<input type="hidden" name="" id="" value="<%=colName[i]%>"/>
			</td>
			<%
}
%>
		</tr>
	</thead>

		<logic:notEmpty scope="request" name="tbody">
			<logic:iterate id="tbody" name="tbody" scope="request">
				<tr id="tr<%=j++%>" onclick="rowOnClick(this)" style="cursor:hand">
					<%
for(i=1;i<colName.length;i++){
%>
					<td id="<%=colName[i]%>">
						<bean:write name="tbody" property="<%=colName[i]%>" />
					</td>
					<%
}
%>
				</tr>
			</logic:iterate>
		</logic:notEmpty>

	
</table>
</div>
