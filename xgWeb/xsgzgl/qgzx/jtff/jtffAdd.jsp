<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jtff/js/jtff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="form"	action="/qgzx_jtff">
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td colspan="3">
								<html:text property="sj" styleId="sj" onclick="return showCalendar('sj','yyyy-MM');" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ù�����
							</th>
							<td colspan="3">
								<html:select property="yrbm" styleId="yrbm" >
									<html:options collection="bmList" property="bmmc" labelProperty="bmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br><font color="red">��200������</br></font>
							</th>
							<td colspan="3" >
								<html:textarea property="bz" style="width:94%;height:100px"  styleId="bz" onblur="chLengs(this,200);" ></html:textarea>
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
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="save('qgzx_jtff.do?method=JtffSave','xh-sj-yrbm');" id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
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