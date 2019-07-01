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
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var bz = document.getElementById('bz').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((xn == null) || (xn == "")){
				alert("学年不能为空!");
				return false;
			}
			if((xq == null) || (xq == "")){
				alert("学期不能为空!");
				return false;
			}
			if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("备注不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_zhcpEditSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>

<body>
		<html:form action="zgdzdx_xszz.do?method=jzxj_zhcpEdit" method="post">
			<input type="hidden" id="url" name="url" value="/zgdzdx_xszz.do?method=jzxj_zhcpEdit" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="type" name="type"
				value="${type }">

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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已存在信息，不能再次增加！");
	         		</script>
				</logic:match>
			</logic:present>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>奖助学金 - 基础数据维护 - 综合测评信息</a>
				</p>
			</div>
			
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>综合测评信息</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <logic:notEqual value="view" name="type">
			        <button type="button" class="" id="buttonSave"
						onClick="yz();">
						保存
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class=""
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						关闭
					</button>
				</td>
			      </tr>
			    </tfoot>
			
			<tbody>
				<tr>
					<th align="" width="16%">
						<font color="red">*</font>学号
					</th>
					<td align="left" width="34%">
						<html:text name='rs' property="xh" styleId="xh"
							readonly="true"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<logic:notEqual name="type" value="modi">
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
						</logic:notEqual>
					</td>
					<th width="16%">
						<div align="">
							<font color="red">*</font>学年
						</div>
					</th>
					<td width="34%">
						<logic:equal name="type" value="modi">
							<html:select name="rs" property="xn" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							<input type="hidden" id="xn" name="xn"
									value="<bean:write name="rs" property="xn" />">
						</logic:equal>
						<logic:notEqual name="type" value="modi">
							<html:select name="rs" property="xn" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							姓名
						</div>
					</th>
					<td>
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
					<th>
						<div align="">
							<font color="red">*</font>学期
						</div>
					</th>
					<td>
						<logic:equal name="type" value="modi">
							<html:select name="rs" property="xq" styleId="xq" disabled="true">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							<input type="hidden" id="xq" name="xq"
									value="<bean:write name="rs" property="xq" />">
						</logic:equal>
						<logic:notEqual name="type" value="modi">
							<html:select name="rs" property="xq" styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							性别
						</div>
					</th>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
						<div align="">
							年级
						</div>
					</th>
					<td>
						<input type="text" id="nj" name="nj" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</th>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
					<th>
						<div align="">
							专业名称
						</div>
					</th>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							班级名称
						</div>
					</th>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<td>
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							综合测评总成绩
						</div>
					</th>
					<td>
						<input type="text" id="zhszzcj" name="zhszzcj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zhszzcj"/>">
					</td>
					<th>
						<div align="">
							综合测评排名
						</div>
					</th>
					<td>
						<input type="text" id="zhszpm" maxlength="10" name="zhszpm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zhszpm"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" 
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						<div align="">
							备注
						</div>
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="bz" rows='3' style="width:100%" onblur="yzdx(this,'bz')"/>
						<input type="hidden" id="bz" name="bz" value="">
					</td>
				</tr>
				</tbody>
			</table>
	
		</html:form>
	</body>
</html:html>
