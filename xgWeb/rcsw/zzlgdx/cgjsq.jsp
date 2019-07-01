<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var xxdm = document.getElementById('xxdm').value;
			var lxfs_br = document.getElementById('lxfs_br').value;
			var lxfs_jz = document.getElementById('lxfs_jz').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(xxdm == "10338"){
				//�㽭����ѧ
				if(lxfs_br == null || lxfs_br == ""){
					alert("������ϵ��ʽ����Ϊ��!");
					return false;
				}
				if(lxfs_jz == null || lxfs_jz == ""){
					alert("�ҳ���ϵ��ʽ����Ϊ��!");
					return false;
				}
			}
			
			if(checkSjTj("cgrq","��������","fhrq","��������")){
				showTips('���������У���ȴ�......');
				document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=cgjsqSave";
				document.forms[0].submit();
			}
		}
		
		function back() {
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=cgjDate";
			document.forms[0].submit();
		}
	</script>
	</head>

	<body>
		<html:form action="zzlgdx_rcsw.do?method=cgjsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zzlgdx_rcsw.do?method=cgjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />"/>
			<input type="hidden" id="guid" name="guid"
				value="<bean:write name="rs" property="guid" />"/>
			<input type="hidden" id="type" name="type"
				value="<bean:write name="type" />"/>
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" />"/>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript" defer>
	         	alert("����ɹ���");
	         	if (window.dialogArguments) {
	         		close();
	         		window.dialogArguments.document.getElementById('search_go').click();
	         	}
	         	</script>
				</logic:match>
			<logic:notPresent name="isPASS">	
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("�����ظ���¼,����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:notPresent>
			</logic:present>
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("��ͨ��ѧУ��ˣ������ύ��");
	         		</script>
				</logic:match>
			</logic:present>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����(��)����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" name="����" onclick="yz();">
										����
									</button>
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<button type="button" onclick="Close();return false;">
											�ر�
										</button>
									</logic:equal>
									<logic:notEqual name="userOnLine" value="teacher"
										scope="session">
										<button type="button" onclick="back();">
											����
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<th width="16%">
									<font color="red">*</font>ѧ��
								</th>
								<td width="34%">
									<html:text name='rs' property="xh" styleId="xh" 
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:notEqual name="type" value="modi">
										<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<th width="16%">
									<font color="red">*</font>ѧ��
								</th>
								<td width="34%">
									<input type="text" id="xh" name="xh"
										value="<bean:write name='rs' property="xh" />" readonly="true" />
								</td>
							</logic:equal>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<input type="text" readonly="readonly" id="xm" name="xm"
									value="<bean:write name="rs" property="xm"/>"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<input type="text" id="xb" readonly="readonly" name="xb"
									value="<bean:write name="rs" property="xb"/>" />
							</td>
							<th>
								���֤��
							</th>
							<td>
								<input type="text" id="sfzh" name="sfzh" readonly="readonly"
									value="<bean:write name="rs" property="sfzh"/>" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<input type="text" id="nj" readonly="readonly" name="nj"
									value="<bean:write name="rs" property="nj"/>" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />����
							</th>
							<td>
								<input type="text" id="xymc" name="xymc" readonly="readonly"
									value="<bean:write name="rs" property="xymc"/>" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ����
							</th>
							<td>
								<input type="text" id="zymc" readonly="readonly" name="zymc"
									value="<bean:write name="rs" property="zymc"/>" />
							</td>
							<th>
								�༶����
							</th>
							<td>
								<input type="text" id="bjmc" name="bjmc" readonly="readonly"
									value="<bean:write name="rs" property="bjmc"/>" />
							</td>
						</tr>
						<logic:equal value="10338" name="xxdm">
							<tr>
								<th>
									<font color="red">*</font>������ϵ��ʽ
								</th>
								<td>
									<input type="text" id="lxfs_br" name="lxfs_br" maxlength="20"
										value="<bean:write name="rs" property="lxfs_br"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
								<th>
									<font color="red">*</font>�ҳ���ϵ��ʽ
								</th>
								<td>
									<input type="text" id="lxfs_jz" name="lxfs_jz" maxlength="20"
										value="<bean:write name="rs" property="lxfs_jz"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm">
							<tr>
								<th>
									������ϵ��ʽ
								</th>
								<td>
									<input type="text" id="lxfs_br" name="lxfs_br" maxlength="20"
										value="<bean:write name="rs" property="lxfs_br"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
								<th>
									�ҳ���ϵ��ʽ
								</th>
								<td>
									<input type="text" id="lxfs_jz" name="lxfs_jz" maxlength="20"
										value="<bean:write name="rs" property="lxfs_jz"/>"
										onkeyup="value=value.replace(/[^\d]/g,'')"
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<input type="text" id="sqsj" readonly="readonly" name="sqsj"
									value="<bean:write name="rs" property="sqsj"/>" />
							</td>
							<th>
								����ȥ��
							</th>
							<td>
								<input type="text" id="sqqx" name="sqqx" maxlength="100"
									value="<bean:write name="rs" property="sqqx"/>" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<input type="text" readonly 
									onclick="return showCalendar('cgrq','y-mm-dd');"
									value="<bean:write name='rs' property="cgrq" />" name="cgrq"
									id="cgrq" />
							</td>
							<th>
								��������
							</th>
							<td>
								<input type="text" readonly
									onclick="return showCalendar('fhrq','y-mm-dd');"
									value="<bean:write name='rs' property="fhrq" />" name="fhrq"
									id="fhrq" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="sqly" rows='10'
									style="width:99%;word-break:break-all;" onblur="chLeng(this,1000)" />
							</td>
						</tr>
				</table>
			</div>
		</html:form>
	</body>
	</html>