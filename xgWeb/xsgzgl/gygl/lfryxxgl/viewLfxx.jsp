<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/lfryxxgl/js/lfrydj.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_lfrydj" method="post" styleId="lfrydjForm">
			<html:hidden property="lfrdjid" value="${lfrydjxx.lfrdjid}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ�鿴</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th style="width:18%">
								¥������
							</th>
							<td colspan="3">
								<span id="ldmc">${lfrydjxx.ldmc }</span>
							</td>
						</tr>
						<tr>
							<th style="width:18%">
								����������
							</th>
							<td>
								<span id="lfrxm">${lfrydjxx.lfrxm}</span>
							</td>
							<th style="width:18%">
								�������Ա�
							</th>
							<td>
								<span id="lfrxb">${lfrydjxx.lfrxb}</span>
							</td>
						</tr>
						<tr>
							<th>
								������֤������
							</th>
							<td colspan="3">
								<span id="lfrsfzh">${lfrydjxx.lfrsfzh}</span>
							</td>
						</tr>
						<tr>
							<th>
								������ѧ��
							</th>
							<td >
								<span id="xh">${lfrydjxx.xh}</span>
							</td>
							<th>
								����������
							</th>
							<td>
								<span id="xm">${lfrydjxx.xm}</span>
							</td>
						</tr>
						
						<tr>
							<th >������ѧԺ����</th><td>	<span id="xymc">${lfrydjxx.xymc}</span></td>
							<th >������רҵ����</th><td>	<span id="zymc">${lfrydjxx.zymc}</span></td>
						</tr>
						
						<tr>
							<th >�����˰༶����</th><td>	<span id="bjmc">${lfrydjxx.bjmc}</span></td>
							<th >¥������</th><td>	<span id="ldmc">${lfrydjxx.ldmc}</span></td>	
						</tr>
						
						<tr>
							<th >���Һ�</th><td colspan="3">	<span id="qsh">${lfrydjxx.qsh}</span></td>
						</tr>
						
						<tr>
							<th >��������</th>
							<td colspan="3">
								<span id="lfsymc">${lfrydjxx.lfsymc}</span>
							</td>
						</tr>
						
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<span id="lfsj">${lfrydjxx.lfsj}</span>
							</td>
							<th>
								��ȥʱ��
							</th>
							<td>
								<span id="lqsj">${lfrydjxx.lqsj}</span>
							</td>
						</tr>
						<tr>
							<th>
								ֵ����Ա
							</th>
							<td colspan="3">
								<span id="zbry">${lfrydjxx.zbry}</span>
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break: break-all;">
								<span id="bz">${lfrydjxx.bz}</span>
							</td>
						</tr>					
					</tbody>
					
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

