
<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<html:hidden property="shlcid" value="${rs.shlcid }"/>	
			<html:hidden property="id" value="${rs.id }"/>	
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<logic:notEqual value="view" name="sztzActionForm" property="doType">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
								</logic:notEqual>
								<div class="btn">
									<logic:notEqual value="view" name="sztzActionForm" property="doType">
										<button onclick="saveUpdate('sztz.do?method=xmsqSave&sftj=��','')">
											�� �� 
										</button>
									</logic:notEqual>
									<button onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" styleId="xh" value="${rs.xh}" readonly="true"/>
			            	</td>
			            	<th width="16%">
			            		��Ŀ����
							</th>
							<td width="34%">
								${rs.xmmc }
								<html:hidden property="xmid" value="${rs.xmid }"/>
			            	</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xn }
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
								ѧ��
							</th>
							<td>
								${rs.xqmc }
							</td>
						</tr>
						
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								������Ŀ
							</th>
							<td>
								${rs.kmmc }
							</td>
						</tr>
						
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								��������
							</th>
							<td>
								${rs.hxnlmc }
							</td>
						</tr>
						
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								���췽
							</th>
							<td>
								${rs.zbf }
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
								������
							</th>
							<td>
								${rs.fzr }
							</td>
						</tr>
						<tr>
							<th>
								�ٰ�ʱ��
							</th>
							<td>
								${rs.jbkssj } �� ${rs.jbjssj }
							</td>
							<th>
								������
							</th>
							<td>
								${rs.jcf }
							</td>
						</tr>
						<tr>
							<th>
								�����ɫ
							</th>
							<td>
								<html:radio value="����" property="cyjs" name="rs">����</html:radio>
								<html:radio value="��֯" property="cyjs" name="rs">��֯</html:radio>
							</td>
							<th>
								�Ƿ�����
							</th>
							<td>
								<html:radio value="��" property="sfcx" name="rs">��</html:radio>
								<html:radio value="��" property="sfcx" name="rs">��</html:radio>
							</td>
						</tr>
						
						<tr>
							<th>
								�ɹ�����<br/>
								<font color="red">&lt;��400��&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="cgms" style="width:85%" rows="5" onblur="checkLen(this,400)" value="${rs.cgms }"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								��ע<br/>
								<font color="red">&lt;��400��&gt;</font>
							</th>
							<td colspan="3"  style="word-break:break-all;">
								<html:textarea property="bz" style="width:85%" rows="5" onblur="checkLen(this,400)" value="${rs.bz }"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<logic:present name="message">
				<script defer>
					alert('${message}');
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:present>
			
		</html:form>
	</body>
</html>
