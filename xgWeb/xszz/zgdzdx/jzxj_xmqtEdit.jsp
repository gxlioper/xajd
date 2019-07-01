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
<html:html>
<base target="_self" />
<head>

<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
		function yz(){
			var xmdm = document.getElementById('xmdm').value;
			var szbz = document.getElementById('szbz').value;
			var bjgmc = document.getElementById('bjgmc').value;
			var xfjd = document.getElementById('xfjd').value;
			var zhszpf = document.getElementById('zhszpf').value;
			var zhszpm = document.getElementById('zhszpm').value;
			
			if((xmdm == null) || (xmdm == "")){
				alert("请选择项目!");
				return false;
			}
			if (szbz == "p1"){
				document.getElementById('sznrdm').value = document.getElementById('nj').value;
			}
			if (szbz == "p2"){
				document.getElementById('sznrdm').value = document.getElementById('xydm').value;
			}
			if (szbz == "p3"){
				document.getElementById('sznrdm').value = document.getElementById('zydm').value;
			}
			if((bjgmc == null) || (bjgmc == "")){
				alert("请填写不及格门次数!");
				return false;
			}
			if((xfjd == null) || (xfjd == "")){
				alert("请填写学分绩点!");
				return false;
			}
			if((zhszpf == null) || (zhszpf == "")){
				alert("请填写综合测评评分!");
				return false;
			}
			if((zhszpm == null) || (zhszpm == "")){
				alert("请填写综合测评排名!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmqtEdit&actDo=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>

	<html:form action="/zgdzdx_xszz.do?method=jzxj_xmqtEdit" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
		<logic:present name="have">
			<logic:match value="have" name="have">
				<script language="javascript">
	         	alert("该项目已有此设置数据！");
	         	</script>
			</logic:match>
		</logic:present>
		<input type="hidden" name="xyV" value="" />
		<input type="hidden" name="zyV" value="" />
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="request"/>" />
		<input type="hidden" name="sznrdm" value="" />
		<input type="hidden" name="szbz" value="<bean:write name="szbz"/>" />
		<input type="hidden" name="act" value="<bean:write name="act"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>奖助学金 - 基础数据维护 - 项目其他设置</a>
				</p>
			</div>
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="2"><span>项目其他设置</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			        
	
						<button type="button" class="" onClick="yz();">
						保存
					</button>
		
					<button type="button" class=""
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关闭
					</button>
					</div>
				</td>
			      </tr>
			    </tfoot>
		
		<tbody>
			<logic:equal name="act" value="add">
			<tr>
				<th width="50%">
					<div align="right">
						项目
					</div>
				</th>
				<td width="50%">
					<html:select name="rs" property="xmdm"
						style="width:100%;heigh:100%">
						<html:option value=""></html:option>
						<html:options collection="jzxjxmList" property="xmdm"
							labelProperty="xmmc" />
					</html:select>
				</td>
			</tr>
			<logic:equal name="szbz" value="p1">
				<tr>
					<th>
						<div align="">
							年级
						</div>
					</th>
					<td>
						<html:select name="rs" property="nj">
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="szbz" value="p2">
				<tr>
					<th>
						<div align="">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<html:select name="rs" property="xydm" style="width:100%">
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="szbz" value="p3">
				<tr>
					<th>
						<div align="">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<html:select name="rs" property="xydm" style="width:100%"
							onchange="initZyList();" styleId="xy">
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							专业
						</div>
					</th>
					<td>
						<html:select name="rs" property="zydm" style="width:100%"
							styleId="zy">
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
			</logic:equal>
			</logic:equal>
			<logic:notEqual name="act" value="add">
			<tr>
				<th width="50%">
					<div align="">
						项目
					</div>
				</th>
				<td width="50%">
					<bean:write name="rs" property="xmmc"/>
					<input type="hidden" name="xmdm" value="<bean:write name="rs" property="xmdm"/>" />
				</td>
			</tr>
			<logic:equal name="szbz" value="p1">
				<tr>
					<th>
						<div align="">
							年级
						</div>
					</th>
					<td>
						<bean:write name="rs" property="nj"/>
						<input type="hidden" name="nj" value="<bean:write name="rs" property="nj"/>" />
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="szbz" value="p2">
				<tr>
					<th>
						<div align="">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
						<input type="hidden" name="xydm" value="<bean:write name="rs" property="xydm"/>" />
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="szbz" value="p3">
				<tr>
					<th>
						<div align="">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							专业
						</div>
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
						<input type="hidden" name="zydm" value="<bean:write name="rs" property="zydm"/>" />
					</td>
				</tr>
			</logic:equal>
			</logic:notEqual>
			<tr>
				<th>
					<div align="">
						不及格门次
					</div>
				</th>
				<td>
					<input type="text" id="bjgmc" name="bjgmc" maxlength="2"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjgmc"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					<div align="">
						学分绩点
					</div>
				</th>
				<td>
					<input type="text" id="xfjd" name="xfjd" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xfjd"/>">
				</td>
			</tr>
			<tr>
				<th>
					<div align="">
						综合测评评分
					</div>
				</th>
				<td>
					<input type="text" id="zhszpf" name="zhszpf" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zhszpf"/>">
				</td>
			</tr>
			<tr>
				<th>
					<div align="">
						综合测评排名<br />
						(班级排名百分比)
					</div>
				</th>
				<td>
					<input type="text" id="zhszpm" name="zhszpm" maxlength="3"
						style="width:50px;heigh:100%"
						value="<bean:write name="rs" property="zhszpm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">%
				</td>
			</tr>
			</tbody>
		</table>
		</div>
	</html:form>
</body>
</html:html>
