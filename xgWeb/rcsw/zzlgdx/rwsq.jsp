<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			showTips('���������У���ȴ�......');
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=rwsqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function back() {
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=rwDate";
			document.forms[0].submit();
		}
	</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ����� - ���� - ��������</a>
			</p>
		</div>


		<html:form action="zzlgdx_rcsw.do?method=rwsq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zzlgdx_rcsw.do?method=rwsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="guid" name="guid"
				value="<bean:write name="rs" property="guid" />">
			<input type="hidden" id="type" name="type"
				value="<bean:write name="type" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
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
	         			alert("��ͨ����ˣ������ύ��");
	         		</script>
				</logic:match>
			</logic:present>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="buttonSave" onclick="yz();">
										�ύ
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
									<html:text name='rs' property="xh" styleId="xh" readonly="true"
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
								����
							</th>
							<td>
								<input type="text" id="mzmc" name="mzmc" readonly="readonly"
									value="<bean:write name="rs" property="mzmc"/>">
							</td>
							<th>

								������ò
							</th>
							<td>
								<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
									value="<bean:write name="rs" property="zzmmmc"/>" />
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

								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td>
								<input type="text" id="xymc" name="xymc" readonly="readonly"
									value="<bean:write name="rs" property="xymc"/>">
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
						<tr>
							<th>

								��������
							</th>
							<td>
								<input type="text" id="csrq" readonly="readonly" name="csrq"
									value="<bean:write name="rs" property="csrq"/>">
							</td>
							<th>

								����ʱ��
							</th>
							<td>
								<input type="text" id="sqsj" readonly="readonly" name="sqsj"
									value="<bean:write name="rs" property="sqsj"/>" />
							</td>
						</tr>
						<tr>
							<th>

								�ֻ�
							</th>
							<td>
								<input type="text" id="sj" name="sj" maxlength="15"
									value="<bean:write name="rs" property="sj"/>"
									onkeyup="value=value.replace(/[^\d]/g,'')"
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
							</td>
							<th>

								�Ƿ��Ѹ�֪�ҳ�
								<br />
								���õ��ҳ���ͬ��
							</th>
							<td>

								<logic:present name="rs" property="sfygzjzbty">
									<logic:match value="��" name="rs" property="sfygzjzbty">
										<input type="radio" name="sfygzjzbty" value="��"
											checked="checked" />&nbsp;&nbsp;��
							    <input type="radio" name="sfygzjzbty" value="��" />&nbsp;&nbsp;��
							</logic:match>
									<logic:match value="��" name="rs" property="sfygzjzbty">
										<input type="radio" name="sfygzjzbty" value="��" />&nbsp;&nbsp;��
							    <input type="radio" name="sfygzjzbty" value="��"
											checked="checked" />&nbsp;&nbsp;��
							</logic:match>
								</logic:present>
								<logic:notPresent name="rs" property="sfygzjzbty">
									<input type="radio" name="sfygzjzbty" value="��" checked>&nbsp;&nbsp;��
							<input type="radio" name="sfygzjzbty" value="��">&nbsp;&nbsp;��
						</logic:notPresent>
								</div>
							</td>
						</tr>
						<tr>
							<th>

								�ֽ񻧼�
							</th>
							<td>
								<input type="text" id="xjhj" name="xjhj" maxlength="100"
									value="<bean:write name="rs" property="xjhj"/>" />
							</td>
							<th>

								��ѧǰ����
								<br />
								���ڵ�
							</th>
							<td>
								<input type="text" id="rxqhkszd" name="rxqhkszd" maxlength="100"
									value="<bean:write name="rs" property="rxqhkszd"/>" />
							</td>
						</tr>
						<tr>
							<th>

								��ͥ��ϸ��ַ
							</th>
							<td>
								<input type="text" id="jtxxdz" name="jtxxdz" maxlength="200"
									value="<bean:write name="rs" property="jtxxdz"/>" />
							</td>
							<th>

								��ĸ��ϵ��ʽ
							</th>
							<td>
								<input type="text" id="fmlxfs" name="fmlxfs" maxlength="50"
									value="<bean:write name="rs" property="fmlxfs"/>" />
							</td>
						</tr>
				</table>
		</html:form>
	</body>
</html>
