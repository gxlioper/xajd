<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<jsp:directive.page import="java.util.HashMap" />
	<jsp:directive.page import="java.util.List" />
	<jsp:directive.page import="java.util.ArrayList" />
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" defer="defer">
	</script>
	</head>

	<body>
		<logic:equal name="xsh" property="leftMap.zd" value="syd">
			<logic:present name="ssList">
				<html:select name="rs" property="syshen" styleId="syshen" onchange="loadShi('syshen','syshi','syxian')">
					<html:option value="">--请选择--</html:option>
					<html:options collection="ssList" property="ssdm"
						labelProperty="ssmc" />
				</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<html:select name="rs" property="syshi" styleId="syshi"
					onchange="loadXian('syshi','syxian')">								
					<html:options collection="shiList" property="shidm"
						labelProperty="shimc" />
				</html:select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<html:select name="rs" property="syxian" styleId="syxian">								
					<html:options collection="xianList" property="xiandm"
						labelProperty="xianmc" />
				</html:select>
			</logic:present>
			<!--end地址信息取标准码-->
			<logic:notPresent name="ssList">
				<html:text name="rs" property="syd" styleId="syd" maxlength="25" styleClass="text_nor" style="width:90%"/>
			</logic:notPresent>			
		</logic:equal>
	</body>
</html>
