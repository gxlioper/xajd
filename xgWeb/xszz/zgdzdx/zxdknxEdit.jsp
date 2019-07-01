<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<base target="_self" />
<head>


	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var dknx = document.getElementById('dknx').value;
			if((dknx == null) || (dknx == "")){
				alert("贷款年限不能为空!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zxdknxEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>助学贷款 - 基础数据维护 - 学生贷款年限设置</a>
		</p>
	</div>
	<html:form action="/zgdzdx_xszz.do?method=zxdknxEdit" method="post">

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
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>学生贷款年限设置</span></th>
			        </tr>
			    </thead>
			 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          <logic:equal name="writeAble" value="yes">
				          	<logic:notEqual name="doType" value="view">
				          		<button type="button" name="提交" onclick="yz()">保 存</button>
				          	</logic:notEqual>
				          </logic:equal>
				          	<button type="button" name="关闭" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">关 闭</button>
				          </div></td>
				      </tr>
				    </tfoot>
		   <tbody>
			<tr>
				<th width="50%">
					<div align="center">
						年级
					</div>
				</th>
				<td width="50%">
					<bean:write name="rs" property="nj"/>
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</th>
				<td>
					<bean:write name="rs" property="xymc"/>
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						专业
					</div>
				</th>
				<td>
					<bean:write name="rs" property="zymc"/>
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						行政班号
					</div>
				</th>
				<td>
					<bean:write name="rs" property="bjmc"/>
					<input type="hidden" id="bjdm" name="bjdm"
						value="<bean:write name="rs" property="bjdm" />">
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						 贷款开始时间
					</div>
				</th>
				<td align="center">
					<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('dkkssj','y-mm-dd');"
							value="<bean:write name='rs' property="dkkssj" />" name="dkkssj"
							id="dkkssj" />
				</td>
			</tr>
			<tr>
				<th>
					<div align="center">
						 <span class="red">*</span>贷款年限(年)
					</div>
				</th>
				<td align="center">
					<input type="text" id="dknx" name="dknx" maxlength="2"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dknx"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			</tbody>
		</table>
		</div>
	</html:form>
</body>
</html>
