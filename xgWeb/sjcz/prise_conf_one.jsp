<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
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
alert("���³ɹ���");
Close();
window.dialogArguments.document.getElementById('search_go').click();
</script>
				</logic:equal>
				<logic:equal value="no" name="chged">
					<script language="javascript">
alert("���º���ܽ���Ѿ��������ƣ�����ʧ�ܣ�");
</script>
				</logic:equal>
			</logic:present>
				<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �������� - ��������</a>
			</p>
		</div>
		
			<div class="tab">
				<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>��ѧ�������������</span>
					</th>
				</tr>
			</thead>
			<tbody>
						
				<tr>
					<th align="right" width="25%">
						ѧ��
					</th>
					<td align="left">
						<input type="text" name="xn" id="xn" style="color:#666666"
							readonly />
					</td>
				</tr>
				<tr>
					<th align="right">
						���
					</th>
					<td align="left">
						<input type="text" name="nd" id="nd" style="color:#666666"
							readonly />
					</td>
				</tr>
				<tr style="display:none" id= "xqtr">
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<input type="text" name="xq" id="xq" 
							readonly />
					</td>
				</tr>
				<tr>
					<th align="right">
						����
					</th>
					<td align="left">
						<input type="text" name="bmT" id="bmT" style="color:#666666"
							readonly />
						<input type="hidden" name="bm" id="bm" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<input type="text" name="njT" id="njT" style="color:#666666"
							readonly />
						<input type="hidden" name="nj" id="nj" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						��ѧ��
					</th>
					<td align="left">
						<input type="text" name="jxjdmT" id="jxjdmT" style="color:#666666"
							readonly />
						<input type="hidden" name="jxjdm" id="jxjdm" value="" />
					</td>
				</tr>
				<tr>
					<th align="right">
						��������
					</th>
					<td align="left">
						<input type="text" name="jyrs" style="color:#666666" readonly />
						<font color="red">��</font>
					</td>
				</tr>
				<logic:equal value="12764" name="xxdm">
				<tr>
					<th align="right">
						��������Ϊ
					</th>
					<td align="left">
						<input type="text" name="rstz"
							onkeypress="return numInputValue(this,3,event)" readonly="readonly" />
						<font color="red">��<br /></font>
					</td>
				</tr>
				<tr>
					<th align="right">
						������Ϊ
					</th>
					<td align="left">
						<input type="text" name="jsje"
							onblur="ckinpjedata(this)"  maxlength="10"/>
						<font color="red">Ԫ<br /></font>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="12764" name="xxdm">
				<tr>
					<th align="right">
						��������Ϊ
					</th>
					<td align="left">
						<input type="text" name="rstz"
							onkeypress="return numInputValue(this,3,event)" />
						<font color="red">��</font>
					</td>
				</tr>
				<input type="hidden" name="jsje" id="jsje"/>
				</logic:notEqual>
				<tfoot>
				<tr>
				<td colspan="2">
				 <div class="btn">
				<button type="button" class=""
					onclick="if(confirm('ȷ��Ҫ������')){document.forms[0].submit();return true;}return false;">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="" onclick="Close();return false;">
					�� ��
				</button>
				</div>
				</td></tr>
			</tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
