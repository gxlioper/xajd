<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/rysq/js/rysq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/xpjpy_rysq" method="post" styleId="rysqForm">
			<input type="hidden" id="xmdm" name="xmdm" value="${xmwhModel.xmdm}"/>
			<html:hidden styleId="xh" property="xh" />
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
					<!-- ���ҳ�����в��ģ������ʱ��æ��ع�ͷ��Ҫ�Ż��������2018-07-10��Meng.Wei -->
					<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���뽱��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<bean:write property="xn" name="rysqForm"/>
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								${xmwhModel.xmmc}
							</td>
							<th>
								��Ŀ���
							</th>
							<td>
								${xmwhModel.xmje}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveRysqXg('save');return false;">
										����ݸ�
									</button>
									<button type="button" onclick="saveRysqXg('submit');return false;">
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
		</html:form>
	</body>
</html>