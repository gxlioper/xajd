<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/jcsd/js/pfbz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/gyjc_pfbz" method="post" styleId="PfbzForm">
			<html:hidden property="js" styleId="js"/>
			<html:hidden property="jjlx" styleId="jjlx"/>
			<html:hidden property="xydm" styleId="xydm"/>
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="fjid" styleId="fjid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:notEqual value="top" property="fjid"  name="PfbzForm">
						<tr>
							<th>������Ŀ</th>
							<td>
								<bean:write name="PfbzForm" property="fjmc"/>
							</td>
						</tr>
					</logic:notEqual>
						<tr>
								<th><font color="red">*</font>���</th>
								<td>
									<html:text property="xh" styleId="xh" onkeyup="checkInput(this)" maxlength="10" style="width:90%" />
								</td>
						</tr>
						<tr>
							<th><font color="red">*</font>
								<logic:equal value="top" property="fjid"  name="PfbzForm">
								<font >��Ŀ</font>
								</logic:equal>
								<logic:notEqual value="top" property="fjid"  name="PfbzForm">
								<font >Ҫ��</font>
								</logic:notEqual>
							</th>
							<td>
								<html:textarea property="wsqkyq" styleId="wsqkyq" style="width:90%" rows="5" onblur="checkLen(this,50)" />
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
									<button type="button" onclick="savePfbz();">
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