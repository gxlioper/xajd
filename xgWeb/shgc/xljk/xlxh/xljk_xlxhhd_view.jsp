<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<html:form action="/xljk_xlxhhd" method="post">
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button class="button2" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th> 
							�� ��
						</th>
						<td colspan="3">
							<html:text style="width:98%" property="zt" styleId="zt"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�� �� �� ʽ
						</th>
						<td>
							<html:text property="hdxs" styleId="hdxs" readonly="true" />
						</td>
						<th>
							�� �� �� ʽ
						</th>
						<td>
							<html:text property="qthdxs" styleId="qthdxs" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�� ��
						</th>
						<td>
							<html:text property="dd" styleId="dd" readonly="true" />
						</td>
						<th>
							�� �� �� ��
						</th>
						<td>
							<html:text style="cursor:hand;" styleId="dateF" property="rq"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�� ʼ ʱ ��
						</th>
						<td>
							<html:text property="kssj" styleId="kssj" readonly="true" />
						</td>
						<th>
							�� �� ʱ ��
						</th>
						<td>
							<html:text property="jssj" styleId="jssj" />
						</td>
					</tr>
					<tr>
						<th>
							�� �� ��
						</td>
						<td>
							<html:text property="zcr" styleId="zy" readonly="true" />
						</td>
						<th>
							ѧ ��
						</th>
						<td>
							<html:text property="xs" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�� �� ѧ ��
						</td>
						<td colspan="3">
							<html:text style="width:98%" property="cyxs" styleId="cyxs"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							�� �� ѧ �� �� ��
						</th>
						<td colspan="3">
							<html:text property="rs" styleId="rs" readonly="true" />
						</td>
					</tr>
				<tr>
					<th>
						�� �� �� ¼
					</th>
					<td colspan="3">
						<html:textarea rows="5" style="width:98%" property="hdjl"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						 �� �� Ч ��
					</th>
					<td colspan="3">
						<html:textarea rows="5" style="width:98%" property="hdxg"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
