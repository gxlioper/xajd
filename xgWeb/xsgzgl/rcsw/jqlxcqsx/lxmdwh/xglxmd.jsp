<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlxcqsx/lxmdwh/js/lxmdwh.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/jqlx_lxmdwh" method="post" styleId="LxmdwhForm">
		<html:hidden property="id" styleId="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��У������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��У��Ŀ����</th>
							<td>
								${zsjgxx.xmmc}
							</td>
							<th>��У��ֹʱ��</th>
							<td>
								${zsjgxx.lxkssj}&nbsp;��&nbsp;${zsjgxx.lxjssj}
							</td>
						</tr>
						<tr>
							<th>��У��Ŀ˵��</th>
							<td colspan="3">
								${zsjgxx.lxxmsm}
							</td>
						</tr>
						<tr>
							<th><font class='red'>*</font>��У���˵��<br/><font class='red'>(��������500��)</font></th>
							<td colspan="3">
								<html:textarea property="lxqksm" styleId="lxqksm" onblur="checkLen(this,500)" rows="5" style="width:99%" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveXg();">
										����
									</button>
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