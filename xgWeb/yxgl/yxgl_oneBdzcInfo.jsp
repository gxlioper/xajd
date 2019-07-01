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


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function save(){
			var bz = document.getElementById('bz').value;
			if(bz != null && bz.length > 100){
				alert('备注必须少于100字！');
				return false;
			}
			document.forms[0].action = '/xgxt/yxgl_bdzcInfo.do?doType=save';
			document.forms[0].submit();
		}
function viewBdZc(str){
	var obj;
	if(str == 'bd'){
		obj = $('sfbd');
		if(obj.value == '已报到'){
			$('bddm').disabled=true;
			$('bddm').value='';
		}else{
			$('bddm').disabled=false;
		}
	}else{
		obj = $('sfzc');
		if(obj.value == '未注册'){
			$('zcdm').disabled=true;
			$('zcdm').value='';
		}else{
			$('zcdm').disabled=false;
		}
	}
}
	</script>
</head>

<body onload="viewBdZc('bd');viewBdZc('zc')">
	<div class="title">
		<div class="title_img" id="title_m">
			<bean:write name="tips" />
		</div>
	</div>
	<html:form action="yxgl_bdzcInfo.do" method="post">
		<input type="hidden" name="xn" id="xn"
				value="<bean:write name='rs' property="xn" />" />
		<input type="hidden" name="xq" id="xq"
				value="<bean:write name='rs' property="xq" />" />
		<table class="tbstyle" style="width:100%">
			<tr>
				<td align="right" width="16%">
					学号：
				</td>
				<td align="left" width="34%">
					<input type="hidden" name="xh" id="xh"
						value="<bean:write name='rs' property="xh" />" />
					<bean:write name='rs' property="xh" />
				</td>
				<td width="16%">
					<div align="right">
						姓名：
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td align="right" width="16%">
					学年：
				</td>
				<td align="left" width="34%">
					<bean:write name='rs' property="xn" />
				</td>
				<td width="16%">
					<div align="right">
						学期：
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="xqmc" />
				</td>
			</tr>
			<tr height="28">
				<td>
					<div align="right">
						性别：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xb" />
				</td>
				<td>
					<div align="right">
						年级：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr height="28">
				<td>
					<div align="right">
						<bean:message key="lable.xsgzyxpzxy" />名称：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
				<td>
					<div align="right">
						专业名称：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr height="28">
				<td>
					<div align="right">
						班级名称：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
				<td>
					<div align="right">
						身份证号：
					</div>
				</td>
				<td>
					<bean:write name="rs" property="sfzh" />
				</td>
			</tr>
			<tr height="28">
				<td>
					<div align="right">
						是否报到：
					</div>
				</td>
				<td>
					<html:select name="rs" property="sfbd" style="width:80px"
						styleId="sfbd" onchange="viewBdZc('bd');">
						<html:option value="已报到">已报到</html:option>
						<html:option value="未报到">未报到</html:option>
					</html:select>
				</td>
				<td>
					<div align="right">
						报到情况：
					</div>
				</td>
				<td>
					<html:select name="rs" property="bddm" style="width:120px"
						styleId="bddm">
						<html:option value=""></html:option>
						<html:options collection="bdList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr height="28">
				<td>
					<div align="right">
						是否注册：
					</div>
				</td>
				<td>
					<html:select name="rs" property="sfzc" style="width:80px"
						styleId="sfzc" onchange="viewBdZc('zc');">
						<html:option value="已注册">已注册</html:option>
						<html:option value="未注册">未注册</html:option>
					</html:select>
				</td>
				<td>
					<div align="right">
						注册情况：
					</div>
				</td>
				<td>
					<html:select name="rs" property="zcdm" style="width:120px"
						styleId="zcdm">
						<html:option value=""></html:option>
						<html:options collection="zcList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="center">
					<br>
					备注：
					<br>
					<font color="red">(100个字以内)</font>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="bz" rows='4' style="width:100%" styleId="bz"></html:textarea>
				
				</td>
			</tr>
		</table>

		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onClick="save();">
				保 存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"
				onclick="Close();return false;">
				关 闭
			</button>
		</div>
	</html:form>
	<logic:equal value="yes" name="result">
		<script type="text/javascript">
			alert('保存成功！');
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="no" name="result">
		<script type="text/javascript">
			alert('保存失败！');
		</script>
	</logic:equal>
	<logic:equal value="no" name="check">
		<script type="text/javascript">
			alert('该新生报到号不存在！');
			Close();
		</script>
	</logic:equal>
</body>
</html:html>
