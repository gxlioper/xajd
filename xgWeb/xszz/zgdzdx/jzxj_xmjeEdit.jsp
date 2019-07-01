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

	<title><bean:message key="lable.title" /></title>
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
	<script language="javascript">
		function yz(){
			var xmdm = document.getElementById('xmdm').value;
			var xmje = document.getElementById('xmje').value;
			if((xmdm == null) || (xmdm == "")){
				alert("请选择项目!");
				return false;
			}
			if((xmje == null) || (xmje == "")){
				alert("请填写项目金额!");
				return false;
			}
			if((xmje == 0)){
				alert("项目金额不能为0!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmjeEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>奖助学金 - 基础数据维护 - 项目金额</a>
				</p>
			</div>
		<html:form action="/zgdzdx_xszz.do?method=jzxj_xmjeEdit" method="post">

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
	         	alert("该项目已有此金额！");
	         	</script>
				</logic:match>
			</logic:present>
			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="2"><span>项目金额</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			        <button type="button" class=""
					onClick="yz();">
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
				<tr>
					<th width="25%">
						<div align="">
							项目
						</div>
					</th>
					<td width="75%">
						<html:select name="rs" property="xmdm" style="width:100%;heigh:100%">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="xmdm"
								labelProperty="xmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							项目金额(元)
						</div>
					</th>
					<td>
						<input type="text" id="xmje" name="xmje" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				</tbody>
			</table>
		</html:form>
</body>
</html:html>
