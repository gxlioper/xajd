<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglxmgl" method="post" styleId="xmglForm" onsubmit="return false;">
			<input type="hidden" name="fdfbid" id="fdfbid" value="${rs.fdfbid }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<td>����</td>
						<td>${jbxx.xm }</td>
						<td>ѧ��</td>
						<td>${jbxx.xh }</td>
					</tr>
					<tr>
						<td>����ѧԺ</td>
						<td>${jbxx.xymc }</td>
						<td>רҵ</td>
						<td>${jbxx.zymc }</td>
					</tr>
					<tr>
						<td>�༶</td>
						<td>${jbxx.bjmc }</td>
						<td>��ϵ�绰</td>
						<td>${jbxx.sjhm }</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ϣ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								�ó�������Ŀ
							</th>
							<td width="30%">
								${rs.fdkm}
							</td>
							</td>
							<th>
								�޶�����
							</th>
							<td>
								${rs.xdrs}
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td colspan="3">
								${rs.fdsj }
							</td>
			      		</tr>
						<tr>
							<th>����<bean:message key="lable.xb" /></th>
							<td colspan="3">
								<table width="100%" border="0" class="formlist">
									<tr>
									<logic:iterate name="xyList" id="l" indexId="index">
										<td style="border:0px;">
										<span style="word-break:break-all;margin-right:15px;">${l.kfxymc }</span>
										</td>
										<%if(index.intValue()%3==2 && index.intValue() != 0){ %>
										</tr><tr>
										<%} %>
									</logic:iterate>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								${rs.fbbz }
							</td>
			      		</tr>				
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

