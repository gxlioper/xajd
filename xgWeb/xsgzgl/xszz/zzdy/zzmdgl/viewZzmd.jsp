<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzdy/zzmdgl/js/zzmdgl.js"></script>	
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	<body>
		<html:form action="/xszz_zzdyzzmdgl" method="post"
			styleId="ZzdyMdglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;height:430px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����¼</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="4">
					<div class="con_overlfow">
					<table class="dateline" width="100%">
					<thead>
					<tr>
							<td>�����</td>
							<td>���ǰ���</td>
							<td>���ǰ״̬</td>
							<td>�������</td>
							<td>���ʱ��</td>
						</tr>
					</thead>
					<tbody>
					<logic:present name="bgjlList">
						<logic:iterate id="i" name="bgjlList" indexId="index01">
							<tr>
							<td>${i.bgr}</td>
							<td>${i.bgqje}</td>
							<td>${i.ffztmc}</td>
							<td>${i.bgly}</td>
							<td>${i.bgsj}</td>
							</tr>
						</logic:iterate>
						</logic:present>
						<logic:empty name="bgjlList">
						<tr >
							<td colspan="5" align="center">�ޱ����¼!</td>
							</tr>
						</logic:empty>
					</tbody>
					</table>
					</div>
					</td>
					</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

