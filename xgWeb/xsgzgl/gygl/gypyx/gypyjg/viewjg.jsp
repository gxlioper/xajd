<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
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
							<th width="15%">¥��</th>
							<td width="35%">
								${qsxx.ldmc}
								<!-- 
								<html:select property="lddm" styleId="lddm" style="width:150px;">
								
								</html:select>	 -->							
							</td>
							<th width="15%">¥��</th>
							<td width="35%">
								${qsxx.ch}��
								<!-- <html:select property="ch" styleId="ch" style="width:150px;">
									
								</html:select> -->
							</td>
						</tr>
						<tr>
							<th>���Һ�</th>
							<td>
								${qsxx.qsh}
							<!-- 
								<html:select   property="qsh" styleId="qsh"  style="width:150px;">
								</html:select>-->
							</td>
							<th>�����Ǽ�</th>
							<td>
								${GypyJgForm.sqxj}
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td>
								${GypyJgForm.gxsj}
							</td>
							<th>�Ƿ�Ϊ�ٴι���</th>
							<td>
								${GypyJgForm.sfzcgx}
							</td>
						</tr>
						<tr>
							<th>����ʱ��</th>
							<td>
								${GypyJgForm.sqsj}
							 </td>
							 <th></th>
							 <td></td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								${GypyJgForm.sqly}
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