<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/xljk_xlwygl_xxsbglwh" method="post" styleId="xlwyglxxsbglForm">
            <div style='tab;width:100%;height:380px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font color="blue" style="font-weight: bold;">${sbxxdata.sblxmc}</font>-�ϱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual value="2" name="sbxxdata" property="sblx">
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									${sbxxdata.xn}
								</td>
								<th>
									ѧ��
								</th>
								<td>
									${sbxxdata.xqmc}
								</td>	
							</tr>
							<tr>
								<th width="100px">
									�ܴ�
								</th>
								<td>
									${sbxxdata.zbzc}
								</td>
								<th width="100px">
									��ֹ����
								</th>
								<td>
									${sbxxdata.zbqzrq}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="2" name="sbxxdata" property="sblx">
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${xn}
								</td>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									${xq}
								</td>	
							</tr>
						</logic:equal>
						<tr>
							<th width="100px">
								�������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${sbxxdata. ztqk}
							</td>
						</tr>
						<tr>
							<th>
								����ѧ��<br />
								��ϸ���<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${sbxxdata. xlxsxxqk}
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
									${sbxxdata. bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
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
		</div>
		</html:form>
	</body>
</html>

