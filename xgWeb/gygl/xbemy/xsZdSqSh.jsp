<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ���� - ѧ���߶�(��ס)����</a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/XsgyglDispatch.do?method=xsZdSqSh" method="post">
		<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">							
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th align="right">
								ѧ�ţ�
							</th>
							<td align="left">	
							 <bean:write name="rs" property="xh"/>							
							</td>
							<th align="right">
								��ס��ʼѧ�꣺
							</th>
							<td align="left">
								<bean:write name="rs" property="xn"/>	
							</td>
						</tr>
						<tr>
						    <th align="right">
								������
							</th>
							<td align="left">
								<bean:write name="rs" property="xm"/>	
							</td>						
							<th align="right">
								��ס��ʼѧ�ڣ�
							</th>
							<td align="left">
							 <bean:write name="rs" property="xq"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								�꼶��
							</th>
							<td align="left">
								<bean:write name="rs" property="nj"/>
							</td>
							 <th align="right">
								��ס��ʼ���ڣ�
							</th>
							<td align="left">
                                  <bean:write name="rs" property="wzksrq"/>	
                            </td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb"/>
							</td>
							<th align="right">
								�ƻ���סʱ�䣺
							</th>
							<td align="left">
								<bean:write name="rs" property="jhwzsj"/>
							</td>
						</tr>
						
						<tr>						   
							<th align="right">
								רҵ��
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
							<th align="right">
								��ס���ͣ�
							</th>
							<td align="left">
								<bean:write name="rs" property="wzlxmc"/>	
							</td>
						</tr>
						<tr>						   
							<th align="right">
								�༶��
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>							
							<th align="right">
								��ס��ַ��
							</th>
							<td align="left">
								<bean:write name="rs" property="wzdz"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								�ֻ����룺
							</th>
							<td align="left">
								<bean:write name="rs"  property="sjhm"/>
							</td>							
							<th align="right">
								�ҳ��Ƿ�ͬ�⣺
							</th>
							<td align="left">
								<bean:write name="rs" property="jzsfty"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								�̶��绰��
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdh"/>
							</td>
							<th align="right">
								��ˣ�
							</th>
							<td align="left">
								<html:select name="rs" property="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>						
						<tr>						   
							<th align="right">
								�������䣺
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdzxx"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>					
	
						<tr>
							<th align="right">
								��סԭ��
							</th>
							<td align="left" colspan="3">
							    <bean:write name="rs" property="wzyy"/>								
							</td>
							
						</tr>
						</tbody>
						<tfoot>
							<tr bgcolor="EEF4F9" align="center">
								<td colspan="4">
									<div class="btn">
										<logic:equal value="yes" name="writeAble">
											<button id="buttonSave" 
												onclick="refreshForm('/xgxt/XsgyglDispatch.do?method=xsZdSqSh&doType=save');this.disabled=true"
												style="width: 80px">
												��	��
											</button>
										</logic:equal>
										<logic:equal value="no" name="writeAble">
											<script>
									         alert("���û�ֻ�ж�Ȩ��!");
									        </script>
										</logic:equal>
										&nbsp;&nbsp;
										<button id="buttonClose" onclick="Close();return false;"
											style="width: 80px">
											��	��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>	
	</body>
	
</html>
