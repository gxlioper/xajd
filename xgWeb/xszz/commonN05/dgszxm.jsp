<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			document.forms[0].action = "/xgxt/n05_xszz.do?method=dgszxm&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>
			ѧ������ - ��������ά�� - ��Ŀ��ص�������</a></p>
		</div>
	<html:form action="/n05_xszz.do?method=dgszxm" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab">
			<table width="99%"  border="0" class="formlist">
			<thead>
   				<tr>
       				<th colspan="4"><span>��������</span></th>
       			</tr>
  			</thead>
  			<tbody>
			<tr>
				<th width="50%">
					��Ŀ����
				</th>
				<td width="50%">
					<input type="text" id="xmmc" name="xmmc" readonly="readonly"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xmmc"/>">
					<input type="hidden" id="xmb" name="xmb"
						value="<bean:write name="rs" property="xmb"/>" />
				</td>
			</tr>
			<tr>
				<th>
					��˼���
				</th>
				<td>
					<html:select name="rs" property="shjb">
						<html:option value="2">����(<bean:message key="lable.xsgzyxpzxy" />��<bean:message key="lable.xsgzyxpzxx" />���)</html:option>
						<html:option value="3">����(����Ա��<bean:message key="lable.xsgzyxpzxy" />��<bean:message key="lable.xsgzyxpzxx" />���)</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					�Ƿ����Ϊ������
				</th>
				<td>
					<html:select name="rs" property="sfkns">
						<html:option value="0">��</html:option>
						<html:option value="1">��</html:option>
					</html:select>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	<button  id="buttonSave" onclick="yz();">
						�� ��
					</button>
					&nbsp;
					<button id="buttonClose"
						onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						�� ��
					</button>						           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
		
	</html:form>
</body>
</html>
