<%@ page language="java" contentType="text/html; charset=GBK"%>
<div  id="xszz" style="display:none">
		<logic:equal value="true" name="xszztyFlag">
		<%@ include file="/xszz/comm/other/xszzXsInfo.jsp"%>
		</logic:equal>
		<logic:notEqual value="true" name="xszztyFlag">
			<%@ include file="/xszz/comm/other/xszzXsInfoTy.jsp"%>
		</logic:notEqual>	
</div>