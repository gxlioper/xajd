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
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xmdm = document.getElementById('xmdm').value;
			var xydm = document.getElementById('xydm').value;
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((xmdm == null) || (xmdm == "")){
				alert("请选择奖助学金项目!");
				return false;
			}
			if((xydm == null) || (xydm == "")){
				alert("请选择<bean:message key="lable.xsgzyxpzxy" />!");
				return false;
			}
			if((kssj == null) || (kssj == "")){
				alert("申请开始时间不能为空!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("申请结束时间不能为空!");
				return false;
			}
			if (kssj > jssj){
				alert("申请开始时间不能大于申请结束时间！");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<html:form action="shgc_kns.do" method="post">
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
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>奖助学金 - 基础数据维护 - 项目申请时间维护</a>
				</p>
			</div>
		<div class="tab">
  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>项目申请时间维护</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:equal name="writeAble" value="yes">
			         <button type="button" class="" onClick="yz();">
						保存
					</button>
					<button type="button" class=""
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关闭
					</button>
					</logic:equal>
				</td>
			      </tr>
			    </tfoot>
			    
			    <tbody>
			      <tr>
			        <th width="25%">项目</th>
			        <td width="25%">
			        	<input type="hidden" id="xmdm" name="xmdm"
							value="<bean:write name="rs" property="xmdm"/>" />
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
			        </td>
			        <th width="25%"><bean:message key="lable.xsgzyxpzxy" /></th>
			        <td width="25%">
			        	<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
			        </td>
			      </tr>
					
					 <tr>
			        <th width="25%">是否必须是困难生</th>
			        <td width="25%">
			        	<logic:present name="rs" property="sfkns">
						<logic:match value="是" name="rs" property="sfkns">
							<input type="radio" name="sfkns" value="是" checked>&nbsp;&nbsp;是
							&nbsp;
							<input type="radio" name="sfkns" value="否">&nbsp;&nbsp;否
						</logic:match>
						<logic:match value="否" name="rs" property="sfkns">
							<input type="radio" name="sfkns" value="是">&nbsp;&nbsp;是
							&nbsp;
							<input type="radio" name="sfkns" value="否" checked>&nbsp;&nbsp;否
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfkns">
						<input type="radio" name="sfkns" value="是">&nbsp;&nbsp;是
						&nbsp;
						<input type="radio" name="sfkns" value="否" checked>&nbsp;&nbsp;否
					</logic:notPresent>
			        </td>
			        <th width="25%">申请开始时间</th>
			        <td width="25%">
			        	<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
			        </td>
			      </tr>
			<tr>
			<th></th><td></td>
				<th>
					申请结束时间
				</th>
				<td align="center">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
				
			</tr>
			</tbody>
		</table>
		</div>
	</html:form>
</body>
</html:html>
