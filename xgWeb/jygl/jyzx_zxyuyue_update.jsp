<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function zxyuyueupdate(){
			 var zxwtjs = document.getElementById("zxwtjs").value;
			 var pkValue = document.getElementById('pkValue').value;
				if(zxwtjs.length>300){
					alert("��ѯ��������������ܳ���300��");
					return false;
				}
	         document.forms[0].action = "/xgxt/updateYuyue.do?act=go&doType=update&pkValue="+pkValue;
			 document.forms[0].submit();
		}
		
		function meetonchange(){
	         document.forms[0].action = "/xgxt/updateYuyue.do?act=go&doType2=meetonchange";
			 document.forms[0].submit();
		}
		</script>
	</head>
	<body>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
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
					
					<tbody> 
						<tr>
							<th>
								ԤԼ�ύʱ��
							</th>
							<td colspan="3">
								<html:text name="rs" property="tjsj" readonly="true"
									style="width:160px" />
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ѯʦ���
							</th>
							<td width="30%">
								<bean:write name="rs" property="num" />
							</td>
							<th width="20%">
								��ѯʦ����
							</th>
							<td width="30%">
								<bean:write name="rs" property="zxsname" />
							</td>
						</tr>
					<tr>
						<th>
							ѧ��
						</th>
						<td>
							<html:text name="rs" property="xsxh" style="width=100%"
								readonly="true" />
						</td>
						<th>
							ѧ������
						</th>
						<td>
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr>
						<th>
							ѧ���Ա�
						</th>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<th>
							ѧ���꼶
						</th>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />����
						</th>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<th>
							רҵ����
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr>
						<th>
							�Ƿ�Ҫ�����
						</th>
						<td>							
								<logic:equal value="��" name="rs" property="meet">
									<input type="radio" name="meet" value="��" checked onclick="meetonchange()"/>&nbsp;&nbsp;��
							   <input type="radio" name="meet" value="��" onclick="meetonchange()"/>&nbsp;&nbsp;��
							</logic:equal>
								<logic:equal value="��" name="rs" property="meet">
									<input type="radio" name="meet" value="��" onclick="meetonchange()"/>&nbsp;&nbsp;��
							   <input type="radio" name="meet" value="��" checked onclick="meetonchange()"/>&nbsp;&nbsp;��
							</logic:equal>		
						</td>

						<th>
							����Լ��ʱ��
						</th>
						<td>
							<logic:equal name="mt" value="yes">
								<html:text name="rs" style="cursor:hand; width=100%;"
									styleId="qwyjtime" property="qwyjtime"
									onclick="return showCalendar('qwyjtime','y-mm-dd');"
									readonly="true" />
							</logic:equal>
							<logic:equal name="mt" value="no">

							</logic:equal>
						</td>
					</tr>
					<tr>
						<th>
							��ѯ�������
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zxwtjs" rows="6"
								style="width:90%" />
						</td>
					</tr>
					<tr>
						<th>
							��ѯ����ظ�
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zxwthf" rows="11"
								style="width:90%" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							ѧ��ȷ��
						</th>
						<td>
							<bean:write name="rs" property="xsqr" />
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
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button  onclick="zxyuyueupdate()">
									�� ��
								</button>
								<button 
									onclick="window.close();window.dialogArguments.document.getElementById('query_go').click();"
									>
									�� ��
								</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                       alert("�޸ĳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("�޸�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
