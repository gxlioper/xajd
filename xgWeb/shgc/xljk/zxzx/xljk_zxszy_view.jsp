<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������ - ������ѯ - ԤԼ��ѯ - ��ϸ��Ϣ</a>
			</p>
		</div>


	<html:form action="/xljk_zxszyAtion">
		<logic:present name="list" scope="request">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:text name="list" property="rq" readonly="true" />
							</td>
							<th>
								ʱ���
							</th>
							<td>
								<html:text name="list" property="sjd" readonly="true" />
							</td>
						</tr>
				<tr>

					<th>
						�ص�
					</th>
					<td>
						<html:text name="list" property="dd" readonly="true" />
					</td>
					<th>
						��ѯʦ���
					</th>
					<td width="156" height="20%">
						<html:text name="list" property="zxxbh" readonly="true" />
					</td>
				</tr>
				<tr >
					<th>
						��ѯʦ����
					</th>
					<td>
						<html:text name="list" property="zxxxm" readonly="true" />
					</td>
					<th>
						��ѯʦ�Ա�
					</th>
					<td>
						<html:text name="list" property="sex" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
							��ѯʦ�ʸ�
					</th>
					<td align="left" style="height:60px" colspan="3">
						<html:textarea name="list" property="zxxzg" rows="5"
							style="width:100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
							��ѯʦ���
					</th>
					<td colspan="3" align="left" style="height:60px;word-break:break-all;">
						<html:textarea name="list" property="jj" rows="5"
							style="width:100%" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</logic:present>
	</html:form>
</body>
</html>
