<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" defer="defer">

	</script>	
</head>

<body onload="" >	
    <html:form action="/rcsw_nthy_xfhjgl.do">
    <input type="hidden" name="pk" value="${pk }"/>
    <input type="hidden" name="num" value="${num }"/>
    <input type="hidden" name="doType" value="${doType }"/>
    
    
		<div class="open_win">
			<logic:equal value="dg" name="doType">
			<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
								<logic:notEqual value="view" name="act">
									<button type="button" class="button2" id="btn_save" 
										onclick="refreshForm('rcsw_nthy_xfhjsjsh.do?act=save')">
										�� ��
									</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ�ѻ�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��
							</th>
							<td>
								${rs.xn }
							</td> 
							<th>
								����ʱ��
							</th>
							<td>
								${rs.sqsj }
							</td>
						</tr>
						<tr>
							<th>
							����ԭ��
							</th>
							<td colspan="3">
								<html:textarea property="sqyy" name="rs" styleId="sqyy" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
						</tr>
						
						<logic:equal value="xy" name="userType">
						
							<logic:equal value="true" name="fdyQx">
										<tr>
							<th>
								<bean:message key="lable.xb" />������
							</th>
							<td colspan="3">
								<html:textarea property="xyyj" name="rs" styleId="xyyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							
						</tr>
						<tr>
							<th>
								ѧУ������
							</th>
							<td colspan="3">
								<html:textarea property="xxyj" name="rs" styleId="xxyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							
						</tr>
									</logic:equal>
									<logic:notEqual value="true" name="fdyQx">
										<tr>
							<th>
								ѧУ������
							</th>
							<td colspan="3">
								<html:textarea property="xxyj" name="rs" styleId="xxyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							
						</tr>
									</logic:notEqual>
							
						
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
						<tr>
							<th>
								����Ա������
							</th>
							<td colspan="3">
								<html:textarea property="fdyyj" name="rs" styleId="fdyyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							</tr>
							<tr>
						<th>
								<bean:message key="lable.xb" />������
							</th>
							<td colspan="3">
								<html:textarea property="xyyj" name="rs" styleId="xyyj" rows="4" style="width:500px" readonly="true"></html:textarea>
							</td>
							</tr>
						</logic:notEqual>
						
						<tr>
							<th>
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="fdyQx">
										����Ա���
									</logic:equal>
									<logic:notEqual value="true" name="fdyQx">
										<bean:message key="lable.xb" />���
									</logic:notEqual>
									</logic:equal>
								<logic:notEqual value="xy" name="userType">
									ѧУ���
								</logic:notEqual>
							</th>
							<td>
								<html:select property="shjg" style="width:90px" styleId="shjg">
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="δ���">δ���</html:option>
								</html:select>
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
							������
							</th>
							<td colspan="3">
								<html:textarea property="shyj"  styleId="shyj" rows="5" style="width:500px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</logic:equal>
			<logic:notEqual value="dg" name="doType">
				<table width="100%" border="0" class="formlist" >
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									
									<button type="button" class="button2" id="btn_save" 
										onclick="refreshForm('rcsw_nthy_xfhjsjsh.do?act=save')">
										�� ��
									</button>
						
									<button type="button" class="button2" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="2">
								<span>ѧ�ѻ�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
							<th colspan="2">
							<div align="left"><font color="blue"><b>��ʾ����ǰ��˵ļ�¼��${num }��</b></font></div>
							</th>
							
						</tr>
						<tr>
							<th width="30%">
								<logic:equal value="xy" name="userType">
									<logic:equal value="true" name="fdyQx">
										����Ա���
									</logic:equal>
									<logic:notEqual value="true" name="fdyQx">
										<bean:message key="lable.xb" />���
									</logic:notEqual>
									</logic:equal>
								<logic:notEqual value="xy" name="userType">
									ѧУ���
								</logic:notEqual>
							</th>
							<td width="70%">
								<html:select property="shjg" style="width:90px" styleId="shjg">
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
									<html:option value="δ���">δ���</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
							������
							</th>
							<td >
								<html:textarea property="shyj"  styleId="shyj" rows="5" style="width:230px"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</logic:notEqual>
			
		</div>
    </html:form>
    <logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		dialogArgumentsQueryChick();
			 		window.close();
			 	}
			</script>
		</logic:present>
  </body>
</html>
