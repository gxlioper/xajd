<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
  </head>
  
  <body>
    <html:form action="/xljk_xlzxcl" method="post" styleId="xlzxclForm">
    	<table width="100%" border="0" class="formlist">
	    	<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
			<logic:present name="xsyysqForm">
				<thead>
					<tr>
						<th colspan="4">
							<span>������ѯԤԼ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xlzxyyxxviewtbody" style="display: none;">
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
				<tbody>
					<td colspan="4" style="height: 10px;padding: 0px;" align="center">
						<div class="more--item_bottom">
							<p>
								<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'xlzxyyxxviewtbody');return false">&nbsp;&nbsp;չ��</a>
							</p>
						</div>
					</td>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>��ѯ������Ϣ</span>
						</th>
					</tr>
				</thead>
				<logic:equal value="2" name="xsyysqForm" property="yyzt" >
					<tbody id="zxapxxviewtbody" style="display: none;">
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
								<a href='javascript:void(0);' style="text-decoration: underline;color: #0f5dc2!important;" onclick="zxsLink('${xlzxclForm.zxs}')">${zxsxm }</a>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��������
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zzaprq"/>
							</td>
							<th>
								��ѯʱ��
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zxsdkssj"/>
								��
								<bean:write name="xlzxclForm" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��ַ
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
					<tbody>
						<td colspan="4" style="height: 10px;padding: 0px;" align="center">
							<div class="more--item_bottom">
								<p>
									<a href="#" class="down" style="text-decoration: none;" id="moreAndLess" onclick="zkandbxqwdview(this,'zxapxxviewtbody');return false">&nbsp;&nbsp;չ��</a>
								</p>
							</div>
						</td>
					</tbody>
				</logic:equal>
				<logic:equal value="4" name="xsyysqForm" property="yyzt" >
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
								<bean:write name="xlzxclForm" property="zzaprq"/>
							</td>
							<th>
								��ѯʱ��
							</th>
							<td>
								<bean:write name="xlzxclForm" property="zxsdkssj"/>
								��
								<bean:write name="xlzxclForm" property="zxsdjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxslxdh"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ��ַ
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxdz"/>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="bz" filter="false"/>
							</td>
						</tr>
					</tbody>
				</logic:equal>
			</logic:present>
			<logic:notPresent name="xsyysqForm">
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
							��ѯ״̬
						</th>
						<td colspan="3" width="80%">
							<bean:write name="xlzxclForm" property="zxzt"/>
						</td>
					</tr>
					<logic:equal value="����ѯ" name="xlzxclForm" property="zxzt" >
						<tr>
							<th width="20%">
								��ѯ����
							</th>
							<td width="30%">
								<bean:write name="xlzxclForm" property="zxrq"/>
							</td>
							<th width="20%">
								��ѯʱ��
							</th>
							<td width="30%">
								<bean:write name="xlzxclForm" property="zxkssj"/>
								��
								<bean:write name="xlzxclForm" property="zxjssj"/>
							</td>
						</tr>
						<tr>
							<th>
								����������
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="lfzzs" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ���̼���Ҫ<br/>��������
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="xlhd" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѯ����ܽ�
							</th>
							<td colspan="3">
								<bean:write name="xlzxclForm" property="zxzj" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								������������
							</th>
							<td colspan="3">
								${gswtlxMcStr }
							</td>
						</tr>
						<tr>
							<th>
								��ѯʦ�����߶�<br/>
								��ѯ�Ľ��̶ܳ�
							</th>
							<td colspan="3">
								${jscdMcStr }
							</td>
						</tr>
						<tr>
							<th>
								������������<br/>
								���س̶�����
							</th>
							<td colspan="3">
								${yzcdpgMcStr }
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ���Ҫת��
							</th>
							<td>
								<bean:write name="xlzxclForm" property="sfxyzj"/>
							</td>
							<th>
								�Ƿ�ԤԼ�´���ѯ
							</th>
							<td>
								<bean:write name="xlzxclForm" property="sfyyxczx"/>
								<logic:equal value="��" name="xlzxclForm" property="sfyyxczx" >
									<br/>ʱ�䣺<bean:write name="xlzxclForm" property="xczxsj"/>
								</logic:equal>
							</td>
						</tr>
						<logic:present name="xlzxclForm" property="xszxpj">
							<tr>
								<th >
									��ѯЧ�����������
								</th>
								<td colspan="3" >
									<bean:write name="xlzxclForm" property="zxxgmydpf" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									ѧ����ѯ����
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="xszxpj" filter="false"/>
								</td>
							</tr>
						</logic:present>
					</logic:equal>
				</tbody>
			</logic:notPresent>
			<logic:present name="xsyysqForm">
				<logic:equal value="2" name="xsyysqForm" property="yyzt" >
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
								��ѯ״̬
							</th>
							<td colspan="3" width="80%">
								<bean:write name="xlzxclForm" property="zxzt"/>
							</td>
						</tr>
						<logic:equal value="����ѯ" name="xlzxclForm" property="zxzt" >
							<tr>
								<th width="20%">
									��ѯ����
								</th>
								<td width="30%">
									<bean:write name="xlzxclForm" property="zxrq"/>
								</td>
								<th width="20%">
									��ѯʱ��
								</th>
								<td width="30%">
									<bean:write name="xlzxclForm" property="zxkssj"/>
									��
									<bean:write name="xlzxclForm" property="zxjssj"/>
								</td>
							</tr>
							<tr>
								<th>
									����������
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="lfzzs" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									��ѯ���̼���Ҫ<br/>��������
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="xlhd" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									��ѯ����ܽ�
								</th>
								<td colspan="3">
									<bean:write name="xlzxclForm" property="zxzj" filter="false"/>
								</td>
							</tr>
							<tr>
								<th>
									������������
								</th>
								<td colspan="3">
									${gswtlxMcStr }
								</td>
							</tr>
							<tr>
								<th>
									��ѯʦ�����߶�<br/>
									��ѯ�Ľ��̶ܳ�
								</th>
								<td colspan="3">
									${jscdMcStr }
								</td>
							</tr>
							<tr>
								<th>
									������������<br/>
									���س̶�����
								</th>
								<td colspan="3">
									${yzcdpgMcStr }
								</td>
							</tr>
							<tr>
								<th>
									�Ƿ���Ҫת��
								</th>
								<td>
									<bean:write name="xlzxclForm" property="sfxyzj"/>
								</td>
								<th>
									�Ƿ�ԤԼ�´���ѯ
								</th>
								<td>
									<bean:write name="xlzxclForm" property="sfyyxczx"/>
									<logic:equal value="��" name="xlzxclForm" property="sfyyxczx" >
										<br/>ʱ�䣺<bean:write name="xlzxclForm" property="xczxsj"/>
									</logic:equal>
								</td>
							</tr>
							<logic:present name="xlzxclForm" property="xszxpj">
								<tr>
									<th >
										��ѯЧ�����������
									</th>
									<td colspan="3" >
										<bean:write name="xlzxclForm" property="zxxgmydpf" filter="false"/>
									</td>
								</tr>
								<tr>
									<th>
										ѧ����ѯ����
									</th>
									<td colspan="3">
										<bean:write name="xlzxclForm" property="xszxpj" filter="false"/>
									</td>
								</tr>
							</logic:present>
						</logic:equal>
					</tbody>
				</logic:equal>
			</logic:present>
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
