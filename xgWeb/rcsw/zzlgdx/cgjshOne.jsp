<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			
			if(("δ���" != xxsh ) && (userType == "xy")){
				alert("ѧУ����ˣ��������޸���˽��!");
	          	return false;
			}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("ѧУ���������ܴ���200���ַ�");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zzlgdx_rcsw.do?method=cgjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ����� - ��� - ����(��)�����ϸ��Ϣ</a>
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
								<span>����(��)�����ϸ��Ϣ</span>
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
								������ϵ��ʽ
							</th>
							<td>
								<bean:write name="rs" property="lxfs_br" />
							</td>
							<th>
								�ҳ���ϵ��ʽ
							</th>
							<td>
								<bean:write name="rs" property="lxfs_jz" />
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<bean:write name="rs" property="sqsj" />
							</td>
							<th>
								����ȥ��
							</th>
							<td>
								<bean:write name="rs" property="sqqx" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<bean:write name='rs' property="cgrq" />
							</td>
							<th>
								��������
							</th>
							<td>
								<bean:write name='rs' property="fhrq" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>

							<td colspan="3" style="word-break:break-all;">
								<bean:write name="rs" property="sqly" />
							</td>
						</tr>
						<logic:equal name="userType" value="xy">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									���
								</th>
								<td>
									<html:select name="rs" property="xysh">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
									<input type="hidden" id="xxsh" name="xxsh"
										value="<bean:write name="rs" property="xxsh"/>" />
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									������
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea name="rs" property="xyshyj" rows='3'
										style="width:100%" onblur="yzdx(this,'xyshyj')" />
									<input type="hidden" id="xyshyj" name="xyshyj" value="" />
									<input type="hidden" id="xxshyj" name="xxshyj"
										value="<bean:write name="rs" property="xxshyj"/>" />
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									���
								</th>
								<td>
									<bean:write name="rs" property="xysh" />
								</td>
								<th>
									ѧУ���
								</th>
								<td>
									<html:select name="rs" property="xxsh">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
									<input type="hidden" id="xysh" name="xysh"
										value="<bean:write name="rs" property="xysh"/>" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									������
								</th>
								<td colspan="3" style="word-break:break-all;">
									<bean:write name="rs" property="xyshyj" />
								</td>
							</tr>
							<tr>
								<th>
									ѧУ������
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea name="rs" property="xxshyj" rows='3'
										style="width:100%" onblur="yzdx(this,'xxshyj')" />
									<input type="hidden" id="xxshyj" name="xxshyj" value="" />
									<input type="hidden" id="xyshyj" name="xyshyj"
										value="<bean:write name="rs" property="xyshyj"/>" />
								</td>
							</tr>
						</logic:equal>
						</tbody>
				</table>
				</div>
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
