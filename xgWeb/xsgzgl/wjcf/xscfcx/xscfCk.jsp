<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		
	</head>
	<body >
		<html:form action="/wjcfCfshwh_cfsjwh" method="post" enctype='multipart/form-data'>	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
						ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							����
						</th>
						<td align="left" width="30%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							�Ա�
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							�꼶
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							רҵ
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							�༶
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							������ò
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����ϱ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							����ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xn }
						</td>
						<th align="right" width="20%">
							����ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<th align="right">
							����ԭ��
						</th>
						<td align="left">
							${rs.cfyymc }
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
							${rs.cflbmc }
						</td>
					</tr>
					
					<tr>
						<th align="right">
							�����ĺ�
						</th>
						<td align="left">
							${rs.cfwh }
						</td>
						<th align="right">
							����ʱ��
						</th>
						<td align="left">
							${rs.cfsj }
						</td>
					</tr>
					
					<tr>
						<th align="right">
							Υ��ʱ��
						</th>
						<td align="left">
							${rs.wjsj }
						</td>
						<th align="right">
							�������������ϻ򸽼�
						</th>
						<td align="left">
							<logic:notEmpty name="rs" property="fjmc">
								<a  href="fjxz.do?cfid=${rs.cfid }&fjmc=${rs.fjmc }" class="name"
												target="_self">���ظ���</a>
							</logic:notEmpty>
							<logic:empty name="rs" property="fjmc">
							���ϴ����ָ���
							</logic:empty>
						</td>
					</tr>
					<tr>
						<th align="right">
							Υ����ʵ����
						</th>
						<td colspan="9" style="word-break:break-all;width:100%">
								${rs.wjssjg }
						</td>
					</tr>
						<tr>
						<th align="right">
							��ע
						</th>
						<td colspan="9" style="word-break:break-all;width:100%" >
								${rs.bz }
						</td>
					</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
