<%@ page language="java" contentType="text/html; charset=GBK"%>
<table class="formlist" border="0" align="center" style="width: 100%">
	<thead>
		<tr>
			<th colspan="4">
				<span>综合测评</span>				
			</th>
		</tr>
	</thead>
	<tbody>
		<!-- 占一个单元格的字段 -->			
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="short">
				<tr style="height: 23px">
					<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">
						<logic:notEqual name="nr" property="lastZd" value="yes">
							<th align="right" width="15%">
								${nr.zdm }
							</th>
							<td align="left" width="35%">
								${nr.zdz }
							</td>
							<%if((index.intValue()+1)%2==0){%>
								<% out.print("</tr>"); %>
							<%}%> 
						</logic:notEqual>
						<logic:equal name="nr" property="lastZd" value="yes">
							<%if((index.intValue()+1)%2==0){%>
								<th align="right" width="15%">
									${nr.zdm }
								</th>
								<td align="left" width="35%">
									${nr.zdz }
								</td>
							<%}%> 	
							<%if((index.intValue()+1)%2==1){%>
								<th align="right" width="15%">
									${nr.zdm }
								</th>
								<td align="left" width="35%">
									${nr.zdz }			
								</td>
								<td></td>
								<td></td>
							<%}%> 	
							<% out.print("</tr>"); %>
						</logic:equal>
					</logic:iterate>		
				</tr>
			</logic:equal>
		</logic:iterate>
		<!-- 占多个单元格的字段 -->
		<logic:iterate name="xmnr" property="nrList" id="zdxx">
			<logic:equal name="zdxx" property="zdlx" value="long">
				<logic:iterate id="nr" name="zdxx" property="zdList" indexId="index">	
					<tr style="height: 23px">						
						<th align="right" width="15%">
							${nr.zdm }
						</th>
						<td align="left" colspan="3">
							${nr.zdz }
						</td>				
					</tr>
				</logic:iterate>		
			</logic:equal>
		</logic:iterate>
	</tbody>
</table>