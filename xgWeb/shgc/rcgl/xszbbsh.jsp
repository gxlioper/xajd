<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<html:form action="/bb_shOne" method="post">
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="tips" scope="request"/></a>
				</p>
			</div>
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<div class="tab">
				<table width="100%" class="formlist" id="">
					   <thead>
						   <tr style="height:22px">
							  <th colspan="4">
								<span>�������</span>
							 </th>
						   </tr>
					   </thead>
					   <tbody>
					   <tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="xh" scope="request"/>								
							</td>
							<th>
								���
							</th>
							<td align="left">
								<bean:write name="nd" scope="request"/>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="xm" scope="request"/>
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="xn" scope="request"/>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="xb" scope="request"/>
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="xqmc" scope="request"/>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<bean:write name="nj" scope="request"/>
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<bean:write name="sqsj" scope="request"/>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="xymc" scope="request"/>
							</td>
							<th>
							  ���
							</th>
							<td align="left">
							<html:select property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<bean:write name="zymc" scope="request"/>
							</td>
							<th>
								�༶
							</th>
							<td align="left">
								<bean:write name="bjmc" scope="request"/>
							</td>
						</tr>
						
						
						<tr>
						<th>
							������Ŀ
						</th>
							<td align="left">
							<bean:write name="bbxmmc" scope="request" />
						</td>
							<th>
								����ԭ��
							</th>
							<td align="left" colspan="3">
								<bean:write name="bbyy"/>
							</td>
						</tr>
						<tr>
						<tr>
							<logic:present name="syd">
								<th>
									��Դ��
								</th>
								<td align="left" id="tsyd">
									<bean:write name='syd' scope="request" />
								</td>
							</logic:present>
							<logic:present name="mz">	
								<th >
									����
								</th>
								<td align="left" id="tmz">
									<bean:write name='mz' scope="request" />
								</td>
							</logic:present>
						</tr>
						<tr style="height:22px">
						<logic:present name="sfzh">	
							<th>
								���֤��
							</th>
							<td align="left" id="tshzh">
								<bean:write name='sfzh' scope="request" />
							</td>
						</logic:present>	
						<logic:present name="csrq">	
							<th>
								��������
							</th>
							<td align="left" id="tcsrq">
								<bean:write name='csrq' scope="request" />
							</td>
						</logic:present>
						</tr>
						<logic:present name="csrq">	
							<th >
								ѧ��
							</th>
							<td align="left">
								<bean:write name="xz" scope="request" />
							</td>
						</logic:present>
						<logic:present name="hczm">		
							<th>
								��վ��
							</th>
							<td align="left" >
								<bean:write name="hczm" scope="request" />
							</td>
						</logic:present>	
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
				          		<button name="�ύ" 
				          		onclick="refreshForm('/xgxt/bb_shOne.do?doType=save')" id="buttonSave">�� ��</button>
					            <button name="�ر�" onclick="window.close();return false;">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>					
					</table>
					</div>
		</html:form>
	</body>
	<logic:present name="done">
		<logic:equal value="yes" name="done">
			<script type="text/javascript">
	            alertInfo("�����ɹ���",function(t){
	            	if(t=="ok"){
			            Close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
	        </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script type="text/javascript">
	             alertInfo("�����ɹ���",function(t){
	            	if(t=="ok"){
		            Close();
					window.dialogArguments.document.getElementById('search_go').click();
					}
				});
	        </script>
		</logic:equal>
	</logic:present>
</html>
