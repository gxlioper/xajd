<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
</script>
	<body onload="checkWinType();initSetPriseOne();">
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<html:form action="/chgPriseOne" method="post">
			<logic:present name="chged">
				<logic:equal value="ok" name="chged">
					<script language="javascript">
alert("更新成功！");
Close();
window.dialogArguments.document.getElementById('search_go').click();
</script>
				</logic:equal>
				<logic:equal value="no" name="chged">
					<script language="javascript">
alert("更新后的总金额已经超出限制，更新失败！");
</script>
				</logic:equal>
			</logic:present>
				<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>评奖评优 - 参数设置 - 参数设置</a>
			</p>
		</div>
		
			<div class="tab">
				<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>奖学金参数批量设置</span>
					</th>
				</tr>
			</thead>
			<tbody>
						
				<tr>
					<th align="right" width="25%">
						学年
					</th>
					<td align="left">
						<input type="text" name="xn" id="xn" style="color:#666666"
							readonly />
					</td>
				</tr>
				<tr>
					<th align="right">
						年度
					</th>
					<td align="left">
						<input type="text" name="nd" id="nd" style="color:#666666"
							readonly />
					</td>
				</tr>
				<tr style="display:none" id= "xqtr">
					<th align="right">
						学期
					</th>
					<td align="left">
						<input type="text" name="xq" id="xq" 
							readonly />
					</td>
				</tr>
				<tr>
					<th align="right">
						部门
					</th>
					<td align="left">
						<input type="text" name="bmT" id="bmT" style="color:#666666"
							readonly />
						<input type="hidden" name="bm" id="bm" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						年级
					</th>
					<td align="left">
						<input type="text" name="njT" id="njT" style="color:#666666"
							readonly />
						<input type="hidden" name="nj" id="nj" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						奖学金
					</th>
					<td align="left">
						<input type="text" name="jxjdmT" id="jxjdmT" style="color:#666666"
							readonly />
						<input type="hidden" name="jxjdm" id="jxjdm" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						参评人数
					</th>
					<td align="left">
						<input type="text" name="jyrs" style="color:#666666" readonly />
						<font color="red">人</font>
					</td>
				</tr>
				<logic:equal value="12764" name="xxdm">
				<tr>
					<th align="right">
						人数调整为
					</th>
					<td align="left">
						<input type="text" name="rstz"
							onkeypress="return numInputValue(this,3,event)" readonly="readonly" />
						<font color="red">人<br /></font>
					</td>
				</tr>
				<tr>
					<th align="right">
						金额调整为
					</th>
					<td align="left">
						<input type="text" name="jsje"
							onblur="ckinpjedata(this)"  maxlength="10"/>
						<font color="red">元<br /></font>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="12764" name="xxdm">
				<tr>
					<th align="right">
						人数调整为
					</th>
					<td align="left">
						<input type="text" name="rstz"
							onkeypress="return numInputValue(this,3,event)" />
						<font color="red">人</font>
					</td>
				</tr>
				<input type="hidden" name="jsje" id="jsje"/>
				</logic:notEqual>
				<tfoot>
				<tr>
				<td colspan="2">
				 <div class="btn">
				<button type="button" class=""
					onclick="if(confirm('确定要保存吗？')){document.forms[0].submit();return true;}return false;">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="" onclick="Close();return false;">
					关 闭
				</button>
				</div>
				</td></tr>
			</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
