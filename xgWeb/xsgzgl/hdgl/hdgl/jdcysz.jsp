<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function submitYhsz(hdid,jdid,jdsx){
				 var url = "hdgl_hdgl.do?method=zjtcysz&ids=" + hdid + "&jdid=" + jdid;
				 var title = "ר���ų�Ա����";
		        showDialog(title, 800, 500, url);
			}
		</script>
	</head>
	<body>
		<html:form action="/hdgl_hdgl" method="post">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�����</th>
							<td>${hdxx.hdmc}</td>
							<th>�����</th>
							<td>${hdxx.hdlxmc}</td>
						</tr>
						<tr>
							<th>�����ʱ��</th>
							<td>${hdxx.fbsj}</td>
							<th>��ص�</th>
							<td>${hdxx.hddd}</td>
						</tr>
						<tr>
							<th>���ʼʱ��</th>
							<td>${hdxx.hdkssj}</td>
							<th>�����ʱ��</th>
							<td>${hdxx.hdjssj}</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>${hdxx.bmdx}</td>
							<th>��������</th>
							<td>${hdxx.bmlxmc}</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>�׶γ�Ա����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="rsTable" style="width:100%;">
									<table  style="width:100%;" border="0">
										<thead>
											<tr>
												<td align="center">���</td>
												<td align="center">�׶�����</td>
												<td align="center">�����������</td>
												<td align="center">�û�����</td>
											</tr>
										</thead>
										<logic:present name="jdcyxxList">
											<logic:iterate id="jdcyxx" name="jdcyxxList" indexId="i">
												<tr>
													<td align="center">${jdcyxx.num}</td>
													<td align="center">${jdcyxx.jdmc}</td>
													<td align="center">${jdcyxx.yyshrs}</td>
													<td align="center"><button type="button" onclick="submitYhsz('${jdcyxx.hdid}','${jdcyxx.jdid}','${jdcyxx.jdsx}')">�û�����</button></td>
												</tr>
											</logic:iterate>
										</logic:present>
										<logic:empty name="jdcyxxList">
											<tr>
												<td console="5" align="center">�׶�δ���ó�Ա��</td>
											</tr>
										</logic:empty>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
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
		</html:form>
	</body>
</html>