<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
  </head>
  
  <body>
    <html:form action="/xljk_xsyyzx" method="post" styleId="xsyysqForm">
    	<table width="100%" border="0" class="formlist">
    		<logic:present name="xsyysqForm" property="sqid">
	    		<thead>
					<tr>
						<th colspan="4">
							<span>������ѯԤԼ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							��ѯԤԼ˵��
						</th>
						<td colspan="3" width="80%">
							${zxyysm }
						</td>
					</tr>
					<tr>
						<th>
							ԤԼ��ѯʱ��
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxsj"/>
						</td>
					</tr>
					<tr>
						<th>
							��ϵ����
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="xslxdh"/>
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td colspan="3">
							${wtlxMcStr }
						</td>
					</tr>
					
					<tr>
						<th>
							ԤԼ��ѯʦ
						</th>
						<td colspan="3">
							<a href='javascript:void(0);' style="text-decoration: underline;color: #0f5dc2!important;" onclick="zxsLink('${xsyysqForm.zxs}')">${yyzxsxm }</a>
						</td>
					</tr>
					
					<tr>
						<th>
							�����Ҫ����
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxzt" filter="false"/>
						</td>
					</tr>
					<tr>
						<th>
							������ע<br/>
						</th>
						<td colspan="3">
							<bean:write name="xsyysqForm" property="yyzxxq" filter="false"/>
						</td>
					</tr>
				</tbody>
				<logic:notEqual value="1" name="xsyysqForm" property="yyzt">
					<logic:notEqual value="3" name="xsyysqForm" property="yyzt">
						<thead>
							<tr>
								<th colspan="4">
									<span>��ѯ������Ϣ</span>
								</th>
							</tr>
						</thead>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal value="2" name="xsyysqForm" property="yyzt">
					<tbody>
						<tr>
							<th width="20%">
								ԤԼ״̬
							</th>
							<td width="30%">
								${yyztmc }
							</td>
							<th width="20%">
								������ѯʦ
							</th>
							<td width="30%">	
								<a href='javascript:void(0);' style="text-decoration: underline;color: #0f5dc2!important;" onclick="zxsLink('${xlzxclMap.zxs}')">${zxsxm }</a>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��������
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zzaprq"/>
							</td>
							<th>
								��ѯʱ��
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zxsdkssj"/>
								��
								<bean:write name="xlzxclMap" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��ַ
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
				<logic:equal value="4" name="xsyysqForm" property="yyzt">
					<tbody>
						<tr>
							<th width="20%">
								ԤԼ״̬
							</th>
							<td width="30%">
								${yyztmc }
							</td>
							<th width="20%">
								������ѯʦ
							</th>
							<td width="30%">	
								${zxsxm }
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��������
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zzaprq"/>
							</td>
							<th>
								��ѯʱ��
							</th>
							<td>
								<bean:write name="xlzxclMap" property="zxsdkssj"/>
								��
								<bean:write name="xlzxclMap" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��ַ
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<bean:write name="xlzxclMap" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
				<logic:equal value="5" name="xsyysqForm" property="yyzt">
					<tbody>
						<tr>
							<th width="20%">
								ԤԼ״̬
							</th>
							<td colspan="3" width="80%">
								${yyztmc }
							</td>
						</tr>
						<tr>
							<th width="20%">
								ԤԼʧ��ԭ��
							</th>
							<td colspan="3" width="80%">
								<bean:write name="xsyysqForm" property="yysbyy" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
				<logic:present name="xlzxclMap" property="zxid">
				<thead>
					<tr>
						<th colspan="4">
							<span>��ѯ������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							��ѯ���
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxzt"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							��ѯЧ�����������
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxxgmydpf"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							��ѯ����
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="xszxpj" filter="false"/>
						</td>
					</tr>
				</tbody>
				</logic:present>
			</logic:present>
			<logic:notPresent name="xsyysqForm" property="sqid">
				<thead>
					<tr>
						<th colspan="4">
							<span>��ѯ������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							��ѯ���
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxzt"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							��ѯЧ�����������
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="zxxgmydpf"/>
						</td>
					</tr>
					<tr>
						<th width="20%">
							��ѯ����
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclMap" property="xszxpj" filter="false"/>
						</td>
					</tr>
				</tbody>
			</logic:notPresent>
			<tfoot>
				<tr>
					<td colspan="4">
						<div class="btn">
							<button type="button" name="�� ��" onclick="iFClose();">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</html:form>
  </body>
</html>
