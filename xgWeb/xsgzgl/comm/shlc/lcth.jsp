<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
		<script>
			function showThr(tmpValue){
				if(tmpValue=='3'){
					jQuery("#thgw").show();
				}else{
					jQuery("#thgw").hide();
				}
			}
		</script>
	</head>
	
	<body style="width:97%">
		<select id="shjg" name="shjg" onchange="showThr(this.value);">
			<option value="1">通过</option>
			<option value="2">不通过</option>
			<option value="3">退回</option>
		</select>
		
		<select id="thgw" name="thgw" style="display:none">
			<option value="-1">申请人</option>
			<logic:iterate id="gw" name="spgw" indexId="pp" >
				<option value="${gw.spgw}">${gw.gwmc}</option>
			</logic:iterate>
		</select>
	</body>
</html>