<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyjcwh/js/gyjcwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/zjly_jcxxwh" method="post" styleId="GyjcWhForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ԣ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${xn}
								<html:hidden property="xn" styleId="xn" />
								<html:hidden property="xh" styleId="xh" />
								<html:hidden property="id" styleId="id" />
							</td>
							<th>ѧ��</th>
							<td>
								${xqmc}
								<html:hidden property="xq" styleId="xq" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�������</th>
							<td>
								<html:select   property="jcdm" styleId="jcdm"  style="width:150px;">
									<html:options collection="jclblist" property="gyjllbdldm" labelProperty="gyjllbdlmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="savejg('update');">
										��    ��
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