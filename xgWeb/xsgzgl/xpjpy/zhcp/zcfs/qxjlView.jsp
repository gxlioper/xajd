<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
		
	<body>
		<html:form action="/xpj_zcfs" method="post" >
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>ԭ�ύ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
							<tr>
								<th align="right" width="20%">
									ѧ��
								</th>
								<td align="left" width="30%">
									${qxjlInfor.xn }
								</td>
								<th align="right" width="20%">
									ѧ��
								</th>
								<td align="left" id="xm">
									${qxjlInfor.xqmc}
								</td>
							</tr>
							<tr>
								<th align="right">
									�꼶
								</th>
									<td align="left" id="nj">
									${qxjlInfor.nj}
								</td>
								<th align="right">
									<bean:message key="lable.xb" />
								</th>
									<td align="left" id="xymc">
									${qxjlInfor.xymc}
								</td>
							</tr>
							<tr>
								<th align="right">
									רҵ
								</th>
									<td align="left"  id="zymc">
									${qxjlInfor.zymc}
								</td>
								<th align="right">
									�༶
								</th>
								<td align="left" id="bjmc">
									${qxjlInfor.bjmc}
								</td>
							</tr>
							<tr>
								<th>ԭ�ύ��</th>
								<td>
									${qxjlInfor.ytjrxm }
								</td>
								<th>ԭ�ύʱ��</th>
								<td>
									${qxjlInfor.ytjsj }
								</td>
						    </tr>
						    <logic:equal value="1" name="szyf">
						    <tr>
								<th>�۲�����</th>
								<td>
									${qxjlInfor.yf}
								</td>
								<th></th>
								<td>
									
								</td>
						    </tr>
						    </logic:equal>
					</tbody>
						<thead>
							<tr>
								<th colspan="4">
								<span>ȡ����Ϣ</span>
								</th>
							</tr>
						</thead>
					</thead>
					<tbody>
						<tr>
							<th>ȡ����</th>
							<td>
								${qxjlInfor.qxrxm }
							</td>
							<th>ȡ��ʱ��</th>
							<td>
								${qxjlInfor.qxsj }
							</td>
						</tr>
					    <tr>
							<th>
								ȡ������
							</th>
							<td colspan="3">
								${qxjlInfor.qxyy }
							</td>
			      		</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

