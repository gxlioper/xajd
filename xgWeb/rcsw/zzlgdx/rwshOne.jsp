<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var shyj = document.getElementById('shyj').value;
			
	       	if(shyj != null){
	         	if(shyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("���������ܴ���100����");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zzlgdx_rcsw.do?method=rwshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ����� - ��� - ���������ϸ��Ϣ</a>
			</p>
		</div>

		<html:form action="/zzlgdx_rcsw" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" name="guid" value="${pkVal }"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���������ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="yz()" id="buttonSave">
										�� ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								���֤��
							</th>
							<td>
								<bean:write name="rs" property="sfzh" />
							</td>
						</tr>

						<tr>
							<th>
								����
							</th>
							<td>
								<bean:write name="rs" property="mzmc" />
							</td>
							<th>
								������ò
							</th>
							<td>
								<bean:write name="rs" property="zzmmmc" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ����
							</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								�༶����
							</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>


						<tr>
							<th>
								��������
							</th>
							<td>
								<bean:write name="rs" property="csrq" />
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								<bean:write name="rs" property="sqsj" />
							</td>
						</tr>
						<tr>
							<th>
								�ֻ�
							</th>
							<td>
								<bean:write name="rs" property="sj" />
							</td>
							<th>
								�Ƿ��Ѹ�֪�ҳ�
								<br />
								���õ��ҳ���ͬ��
							</th>
							<td>
								<bean:write name="rs" property="sfygzjzbty" />
							</td>
						</tr>
						<tr>
							<th>
								�ֽ񻧼�
							</th>
							<td>
								<bean:write name="rs" property="xjhj" />
							</td>
							<th>
								��ѧǰ����
								<br />
								���ڵ�
							</th>
							<td>
								<bean:write name="rs" property="rxqhkszd" />
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ϸ��ַ
							</th>
							<td>
								<bean:write name="rs" property="jtxxdz" />
							</td>
							<th>
								��ĸ��ϵ��ʽ
							</th>
							<td>
								<bean:write name="rs" property="fmlxfs" />
							</td>
						</tr>
						<tr>
							<th>
								��˽��
							</th>
							<td>
								<html:select name="rs" property="shjg">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="shyj" rows='3'
									style="width:100%" onblur="yzdx(this,'shyj')" />
								<input type="hidden" id="shyj" name="shyj" value="">
							</td>
						</tr>
				</table>
		</html:form>
		<logic:present name="ok">
			<input type="hidden" id="msg" value="${ok }"/>
			<script type="text/javascript">
				alert($('msg').value);
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:present>
	</body>
</html>
