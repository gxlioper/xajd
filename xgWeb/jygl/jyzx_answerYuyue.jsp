<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	
	function zxyuyueanswer(){
		var zxsqr = document.getElementById("zxsqr").value;
		var pkValue = document.getElementById('num').value+document.getElementById("xsxh").value+document.getElementById('tjsj').value;
		var url = "/xgxt/answerYuyue.do?doType=view&act=answer&pkValue=";
     
		if(zxsqr=="δȷ��"){ 
		     if (confirm("�㻹û��ȷ�ϣ���ʱ��ȷ����")) {
				url += pkValue;
				refreshForm(url);
				return true;
			    } else {
				return false;
			    }
		}else{
		url += pkValue;
		refreshForm(url);
		}
	}
	
	</script>
	</head>
	
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ѯԤԼ��ϸ��Ϣ</a>
			</p>
		</div>
	
	
		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯԤԼ��ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button name="�ύ" onclick="zxyuyueanswer()">
										�� ��
									</button>
									<button name="�ر�" onclick="Close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								��ѯʦ���
								<html:hidden name="rs" property="tjsj" styleId="tjsj" style="width:160px" />
							</th>
							<td width="34%">
								<bean:write name="rs" property="num" />
								<input  type="hidden" value="${rs.num }" id="num"/>
							</td>
							<th width="16%">
								��ѯʦ����
							</th>
							<td width="34%">
								<bean:write name="rs" property="zxsname" />
							</td>
						</tr>
						<tr>
							<th>
								ѧ��ѧ��
							</th>
							<td>
								${rs.xsxh }
								<html:hidden name="rs" property="xsxh" 
								 />
							</td>
							<th>ѧ������
							</th>
							<td><bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr>
							<th>
								ѧ���Ա�
							</th>
							<td><bean:write name="rs" property="xb" />
							</td>
							<th>ѧ���꼶
							</th>
							<td><bean:write name="rs" property="nj" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />����
							</th>
							<td><bean:write name="rs" property="xymc" />
							</td>
							<th>רҵ����
							</th>
							<td><bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�Ҫ�����
							</th>
							<td><bean:write name="rs" property="meet" />
							</td>
							<th>����Լ��ʱ��
							</th>
							<td><bean:write name="rs" property="qwyjtime" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯ�������
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxwtjs" rows="6"
								 style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>
								��ѯ����ظ�
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxwthf" rows="11"
								 style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>ѧ��ȷ��
							</th>
							<td>
								<bean:write name="rs" property="xsqr" />
							</td>
							<th>
								<font color="red">*</font>��ѯʦȷ��
							</th>
							<td>
								<html:select name="rs" property="zxsqr">
									<html:option value="δȷ��">δȷ��</html:option>
									<html:option value="�ѻظ���">�ѻظ���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ѧ��ȷ��ʱ��
							</th>
							<td>
								<bean:write name="rs" property="xsqrsj" />
							</td>
							<th>
								��ѯʦȷ��ʱ��
							</th>
							<td>
								<bean:write name="rs" property="zxsqrsj" />
							</td>
						</tr>
						<tr>
							<th>
								����Լ���ص�
							</th>
							<td>
								<bean:write name="rs" property="yjdd" />
							</td>
							<th>
								����Լ��ʱ��
							</th>
							<td>
								<bean:write name="rs" property="yjsj" />
							</td>
						</tr>
					</tbody>
				</table>
				</div>
		</html:form>
		<logic:notEmpty name="answer">
			<logic:equal name="answer" value="ok">
				<script defer="defer">
                       alert("�ظ��ɹ���");
                       if (window.dialogArguments) {
							window.close();
							var form = window.dialogArguments.document.forms[0];
							form.action = "jyzxResultQuery.do?act=go";
							form.submit();
						}
                    </script>
			</logic:equal>
			<logic:equal name="answer" value="no">
				<script defer="defer">
                      alert("�ظ�ʧ�ܣ�");
                      if (window.dialogArguments) {
							window.close();
							var form = window.dialogArguments.document.forms[0];
							form.action = "jyzxResultQuery.do?act=go";
							form.submit();
						}
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
