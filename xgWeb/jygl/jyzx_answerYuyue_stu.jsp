<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	
	function zxyuyueanswer(){
		var xsqr = document.getElementById("xsqr").value;
		var pkValue = document.getElementById('num').value+document.getElementById("xsxh").value+document.getElementById('tjsj').value;
		var url = "/xgxt/answerYuyueStu.do?doType=view&act=answer&pkValue=";
       
		if(xsqr=="����ԼX"){ 
		     if (confirm("ѡ���Ժ��޷����ģ���ȷ������Լ��")) {
				url += pkValue;
				refreshForm(url);
				return true;
			    } else {
				return false;
			    }
		}		
		if(xsqr=="��ȷ�ϡ�"){ 
		     if (confirm("ѡ���Ժ��޷����ģ�����ȷ����")) {
				url += pkValue;
				refreshForm(url);
				return true;
			    } else {
				return false;
			    }
		}
		url += pkValue;
		refreshForm(url);
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
									<button name="�ر�" onclick="Close();window.dialogArguments.document.getElementById('query_go').click();">
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
								<html:hidden name="rs" property="tjsj" styleId="tjsi" style="width:160px" />
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
							<td><html:text name="rs" property="xsxh" style="width=100%"
								readonly="true" />
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
								readonly="true" style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>
								��ѯ����ظ�
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name="rs" property="zxwthf" rows="11"
								readonly="true" style="width:100%" />
								</td>
						</tr>
						<tr>
							<th>ѧ��ȷ��
							</th>
							<td>
								<logic:equal name="xsqr" value="ok">
								<html:select name="rs" property="xsqr">
									<html:option value="�Ѳ鿴">�Ѳ鿴</html:option>
									<html:option value="����ԼX">����ԼX</html:option>
									<html:option value="��ȷ�ϡ�">��ȷ�ϡ�</html:option>
								</html:select>
							</logic:equal>
							<logic:equal name="xsqr" value="no">
								<html:select name="rs" property="xsqr" disabled="true" >
								    <html:option value="����ԼX">����ԼX</html:option>
								    <html:option value="��ȷ�ϡ�">��ȷ�ϡ�</html:option>
								</html:select>
								
								<input type="hidden" name="xsqr" value="${rs.xsqr }" />
							</logic:equal>
							</td>
							<th>
								<font color="red">*</font>��ѯʦȷ��
							</th>
							<td>
								<bean:write name="rs" property="zxsqr" />
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
				<script>
                       alert("�ظ��ɹ���");
                       if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('query_go').click();
						}
                    </script>
			</logic:equal>
			<logic:equal name="answer" value="no">
				<script>
                      alert("�ظ�ʧ�ܣ�");
                      if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('query_go').click();
					}
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

