<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList"/>
<jsp:directive.page import="xgxt.utils.Pages"/>
<jsp:directive.page import="xgxt.action.Base"/>
<%
	String rsName=  (String)request.getAttribute("rsName");
	ArrayList list = ((ArrayList) request.getAttribute(rsName));
	Pages pObj = (Pages)request.getAttribute("pObj");
	String autoPageSize = (String)request.getAttribute("autoPageSize");
	
	int rsNum = 0;
	
	if (list != null) {
		rsNum = list.size();
	}

	int pageSize = pObj.getPageSize();
	if(!Base.isNull(autoPageSize)){
		pageSize = Integer.parseInt(autoPageSize);
	}
	
	for (int i = 0; i < (pageSize - rsNum); i++) {
%>
	<tr>
		<!-- 是否需要checkbox -->
		<logic:equal name="isCheckBox" value="yes">
			<td align="center"  width="15px">
				<input type="checkbox" value="" disabled="true"/>
			</td>
		</logic:equal>
		<!-- 显示所需列 -->
		<logic:notEmpty name="showNum">
			<logic:iterate id="tit" name="topTr" offset="${startNum }" length="${showNum }">
				<td>
					&nbsp;
				</td>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 显示固定列 -->
		<logic:empty name="showNum">
			<logic:iterate id="tit" name="topTr" offset="${startNum }">
				<td>
					&nbsp;
				</td>
			</logic:iterate>
		</logic:empty>
			
	</tr>
<%
	}
%>