<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/wh.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		
		<html:form action="/szdw_bjxwjlwh" method="post" styleId="bjxwjlwhForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="100px">ѧ��</th>
							<td style="font-weight: bold">${bjxwjlxx.xn}</td>
							<th width="100px">ѧ��</th>
							<td style="font-weight: bold">${bjxwjlxx.xqmc}</td>
						</tr>
						<tr>
							<th>ְ����</th>
							<td>${bjxwjlxx.jlr}</td>
							<th>����  </th>
							<td>${bjxwjlxx.jlrmc}</td>
						</tr>
						<tr>
							<th>����  </th>
							<td>${bjxwjlxx.jlrbmmc}</td>
							<th>��λ</th>
							<td>${bjxwjlxx.gwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶��Ϊ��¼</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�༶</th>
							<td >${bjxwjlxx.bjmc}</td>
							<th>���</th>
							<td >${bjxwjlxx.xndsmc}</td>
						</tr>
						<tr>
							<th>��¼ʱ��</th>
							<td  colspan="3">${bjxwjlxx.jlsj}</td>
						</tr>
						<tr>
							<th>��¼����</th>
							<td colspan="3">${bjxwjlxx.jlnr}</td>
						</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button" >
										����
									</button>
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

