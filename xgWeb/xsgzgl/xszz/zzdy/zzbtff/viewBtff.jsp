<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script>
		
		</script>
	</head>
	<body>
		<html:form action="/xszz_zzdyzzbtff" method="post"
			styleId="ZzdyBtffForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;height:565px;'>
				<table align="center" class="formlist">
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
								<span>��ʷ���ż�¼</span>
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
							<th width="30%">
								��Ŀ����
							</th>
							<th width="30%">
								��������
							</th>
							<th width="40%">
								���Ž��
							</th>
						</thead>
						<tbody>
						<logic:iterate id="i" name="ffjlList" indexId="index01">
							<tr>
							<td>${i.xmmc}</td>
							<td>${i.ffyf}</td>
							<td>${i.ffje}</td>
							</tr>
						</logic:iterate>
						<logic:empty name="ffjlList">
						<tr >
							<td colspan="3" align="center">�޷��ż�¼!</td>
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

