<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gypyx/gypyjg/js/gypyjg.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gypyx/gypysq/js/commUtil.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/gypynew_gypyjg" method="post" styleId="GypyJgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
							<th><font color="red">*</font>¥��</th>
							<td>
								
								<html:select property="lddm" styleId="lddm" style="width:150px;">
								
								</html:select>	 						
							</td>
							<th>¥��</th>
							<td>
								<html:select property="ch" styleId="ch" style="width:150px;">
									
								</html:select> 
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���Һ�</th>
							<td>
							
								<html:select   property="qsh" styleId="qsh"  style="width:150px;">
								</html:select>
							</td>
							<th>�����Ǽ�</th>
							<td>
								<html:select property="sqxj" styleId="sqxj" style="width:150px;">
									<html:option value="һ��">һ��</html:option>
									<html:option value="����">����</html:option>
									<html:option value="����">����</html:option>
									<html:option value="����">����</html:option>
									<html:option value="����">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>����ʱ��</th>
							<td>
								<html:text property="gxsj" styleId="gxsj" maxlength="10" onclick="return showCalendar('gxsj','y-mm-dd');" style="width:150px" />
							</td>
							<th>�Ƿ�Ϊ�ٴι���</th>
							<td>
								<html:select property="sfzcgx" styleId="sfzcgx" style="width:150px;">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font class="red">*</font>����ʱ��</th>
							<td>
								<html:select property="sqsj" styleId="sqsj" style="width:150px;">
									<html:option value=""></html:option>
									<html:option value="${month}-01">${month}-01</html:option>
									<html:option value="${month}-16">${month}-16</html:option>
								</html:select>
							 </td>
							 <th></th>
							 <td></td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" onblur="checkLen(this,500)" style="width:90%" rows="10"/>
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
									<button type="button" onclick="saveSq();">
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