<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target = "_self" />
	<script language="javascript">
	function write(){
		var value="";
		var syrs=document.getElementById("xyrs").value;
		var knsbl=document.getElementById("knsbl").value;
		//alert(syrs);
		value=Math.round(syrs*(knsbl*0.01));
		document.getElementById("xyknsrs").value=value;
	}
	
	function doSavePubGw(url,pkFields){
		var RowsStr="!!#!!";
		var knsbl=document.forms[0].knsbl.value;
		var sqdw=document.forms[0].sqdw.value;		
		for (var i=0; i<document.getElementsByName("gwxx").length; i++){
	    	if(document.getElementsByName("gwxx")[i].checked){
	    		RowsStr+=document.getElementsByName("gwxx")[i].value+"!!#!!";	    		
	   		}
		}		
		
		if(sqdw=="" || sqdw==null){
			alert("请选择用人单位！");
			return false;
		}
				
		if (RowsStr=="!!#!!"){
			alert("请选择要申请的岗位！");
			return false;
		}
		
		//var eles = pkFields.split("-");
		//var valu = "";
		for(var i=0;i<document.getElementsByName("gwxx").length; i++){
			if(document.getElementsByName("gwxx")[i].checked){				
				var gwdm=document.getElementsByName("gwxx")[i].value;				
				var xyrs = document.getElementById("xyrs"+gwdm).value;				
				var xyknsrs = document.getElementById("xyknsrs"+gwdm).value;		
				if (parseFloat(xyrs)*parseFloat(knsbl)/100>parseFloat(xyknsrs)) {
					alert("使用困难生数低于标准!");
					return false;
				}
			}
		}
		
		for(var i=0; i<document.getElementsByName("gwxx").length; i++){
			if(document.getElementsByName("gwxx")[i].checked){
				var gwdm=document.getElementsByName("gwxx")[i].value;
				var gwxz = document.getElementById("gwxz"+gwdm).value;
				if(gwxz == "" || gwxz == null){
					alert("必需选择岗位性质！");
					document.getElementById("gwxz"+gwdm).focus();
					return false;
				}
				if(xyrs.match(/^\d+\.{0,1}\d{0,3}$/)==null){
					alert("数据格式错误！");
					document.getElementById("xyrs"+gwdm).focus();
					return false;
				}
				if(xyknsrs.match(/^\d+\.{0,1}\d{0,3}$/)==null){
					alert("数据格式错误！");
					document.getElementById("xyknsrs"+gwdm).focus();
					return false;
				}				
			}
		}
		document.forms[0].gwdmList.value=RowsStr;
		
		
		document.forms[0].action = url;
		document.forms[0].submit();
<%--		if(window.dialogArguments){--%>
<%--		window.close();--%>
<%--		window.dialogArguments.document.all("search_go").click();--%>
<%--	}--%>
	return true;
	}
	function checkKnss(obj,id){
		if(obj.value<Math.round((document.getElementById(id).value*(document.getElementById('knsbl').value*0.01)))){
			obj.value=Math.round((document.getElementById(id).value*(document.getElementById('knsbl').value*0.01)));
		}
	}
</script>
	<body>
	
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/comm_pub" method="post">
		<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 岗位发布 - 岗位信息发布
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">				
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="knsbl" name="knsbl"
					value="<bean:write name="rs" property="knsbl"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/gwxxb.jsp" />
				<input type="hidden" id="doType" name="doType" value="save" />
				<input type="hidden" id="tableName" name="tableName" value="view_gwxx" />
				<input type="hidden" id="gwdmList" name="gwdmList" value="gwdmList" />
				<logic:present name="showmodi">
				<input type="hidden" id="type" name="type" value="modi" />
				<input type="hidden" id="doOper" name="doOper" value="modi" />
				</logic:present>
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								&nbsp;
							</td>
						</tr>
					</thead>
						<thead>
						<tr align="center">
							<td height="22">
								<font color="red">*</font>&#30003;&#35831;&#21333;&#20301;&#65306; <html:select name="rs" property="sqdw" styleId="sqdw" style="width:120px">
							 <html:option value=""></html:option>
							 <html:options collection="sqdwList" property="yrdwdm" labelProperty="yrdwmc" />
						 </html:select>
							</td>			
						<td height="22" align="left">
							学年：<html:text name="rs" property="xn" style="width: 90px" readonly="true"/>
						</td>
							
						<td height="22" align="left">
							年度：<html:text name="rs" property="nd" style="width: 90px" readonly="true"/>
						</td>
						<td height="22" align="left">
							学期：<html:text name="rs" property="xueqi" style="width: 90px" readonly="true"/>
						</td>
						</tr>
					</thead>
					<logic:iterate id="gwxx" name="gwList">
						<tr>
						<td>						
						<input type="checkbox" name="gwxx" id="gwxx" value="<bean:write name="gwxx" property="gwdm"/>">
						岗位名称：
						<logic:present name="showmodi">
							<bean:write name="gwxx" property="gwdm"/>
						</logic:present>
						<logic:notPresent name="showmodi">
							<bean:write name="gwxx" property="gwmc"/>
						</logic:notPresent>	
						</td>
						<td  colspan="3">
							<font color="red">*</font>岗位性质：
							<html:select name="gwxx" property="gwxz${gwxx.gwdm}" styleId="gwxz${gwxx.gwdm}" style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc" />
							</html:select>
						</td>
						</TR>
						<TR>
						<td>需求人数：<input type="text" onblur="getZrs(this)" name="xyrs<bean:write name="gwxx" property="gwdm"/>" id="xyrs<bean:write name="gwxx" property="gwdm"/>" value="<bean:write name="gwxx" property="xyrs"/>"/> </td>
						<td>困难生数：<input type="text" name="xyknsrs<bean:write name="gwxx" property="gwdm"/>" id="xyknsrs<bean:write name="gwxx" property="gwdm"/>" value="<bean:write name="gwxx" property="syknss"/>" onblur="checkKnss(this,'xyrs<bean:write name="gwxx" property="gwdm"/>')" onfocus="this.value=Math.round((document.getElementById('xyrs<bean:write name="gwxx" property="gwdm"/>').value*(document.getElementById('knsbl').value*0.01)))"/> </td>
						<td>工作时间：
							<select id="gzsj<bean:write name="gwxx" property="gwdm"/>" 
							name="gzsj<bean:write name="gwxx" property="gwdm"/>">
							<option value="临时">临时</option>
							<option value="正常工作日">正常工作日</option>
							</select> 
						</td>
						<td>
						工作内容：
						<input type="text" id="gznr<bean:write name="gwxx" property="gwdm"/>" name="gznr<bean:write name="gwxx" property="gwdm"/>" value="<bean:write name="gwxx" property="gznr"/>"/> </td>	
						</tr>
					</logic:iterate>
				</table>
				<font color="red">共申请勤工助学岗位人数:<span id="sqzrs">0</span>人</font>
				<br />
				<font color="red">提示:校内固定岗位使用困难生比例不得低于<bean:write name="rs" property="knsbl"/>%</font>
				<br />
				<logic:present name="writeAble">
					<logic:match value="yes" name="writeAble">
						<div id="button" align="center">
						
						<button class="button2"
							onclick="doSavePubGw('/xgxt/comm_pub.do','sqdw-gwxz')"
							style="width:80px" id="buttonSave">
							保 存
						</button>
			
						<logic:notPresent name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="expAppTab('rsT','勤工助学岗位申报表','')">
						打 印 预 览
						</button>
						</logic:notPresent>
						<logic:present name="zdy">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="printReport('qgzx_bb_gwsqb.do')">
						打 印 预 览
						</button>
						</logic:present>
						</div>
					</logic:match>
				</logic:present>
				
			</logic:notEmpty>
		</html:form>
	</body>
</html>