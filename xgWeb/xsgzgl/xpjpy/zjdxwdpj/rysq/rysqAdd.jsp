<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q_href.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/rysq/js/rysq.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//��������ѡ��
				loadMkxxSelectOptions();
				//����radioѡ��
				loadMkxxRadioOptions();
				var xh = jQuery("#xh").val();
				if (xh != ""){
					showDialog("ѡ�����뽱��",500,400,"xpjpy_rysq.do?method=selectRyxm&xh="+xh);
				}
			});

		</script>
	</head>
	<body>
		<html:form action="/xpjpy_rysq" method="post" styleId="rysqForm" onsubmit="return false;">
			<html:hidden property="id" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/zjdxwdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									��������
									<a onclick="showDialog('ѡ����������',500,400,'xpjpy_rysq.do?method=selectRyxm&xh=${jbxx.xh}');" href="javascript:void(0);">
									   <font color="blue"><u>ѡ����������</u></font>
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td colspan="3">${cssz.xn}</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td width="20%">��������</td>
											<td width="10%">���</td>
											<td width="25%">�����</td>
											<td width="45%">����������д˵��</td>
										</tr>
									</thead>
									<tbody id="sqjx"></tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">

								</script>
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveRysq('save');return false;">
										����ݸ�
									</button>
									<button type="button" onclick="saveRysq('submit');return false;">
										�ύ����
									</button>
									<button type="button" onclick="iFClose();">
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