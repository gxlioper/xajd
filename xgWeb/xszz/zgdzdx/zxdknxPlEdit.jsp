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
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var dknx = document.getElementById('dknx').value;
			if((dknx == null) || (dknx == "")){
				alert("�������޲���Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zxdknxPlsz&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��������ά�� - ѧ������������������</a>
		</p>
	</div>
	<html:form action="/zgdzdx_xszz.do?method=zxdknxPlsz" method="post">

		<input type="hidden" id="pkDel" name="pkDel"
			value="<bean:write name='rs' property='pkDel'/>" />
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("������ɣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>ѧ������������������</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:notEqual name="doType" value="view">
			          		<button type="button" name="�ύ"  onclick="Close();window.dialogArguments.document.getElementById('search_go').click();yz();">�� ��</button>
			          </logic:notEqual>
			                <button type="button" name="�ر�" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
				<tbody>
				<tr>
					<th width="50%">
						<div align="center">
							 ���ʼʱ��
						</div>
					</th>
					<td align="center" width="50%">
						<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('dkkssj','y-mm-dd');"
								value="<bean:write name='rs' property="dkkssj" />" name="dkkssj"
								id="dkkssj" />
					</td>
				</tr>
				<tr>
					<th width="50%">
						<div align="center">
							<span class="red">*</span> ��������
						</div>
					</th>
					<td align="center" width="50%">
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
