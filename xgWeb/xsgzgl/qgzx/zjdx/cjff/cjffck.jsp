<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/cjff/js/cjff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
	</head>
	<body>
		<html:form action="/cjff_zjdx" method="post" styleId="CjffForm">
		<input type="hidden" name="sxsz" id="sxsz" value="${sxsz}" />
		<input type="hidden" name="cjbz" id="cjbz" value="${cjbz}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/qgzx/zjdx/cjff/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��𷢷���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td>
								${bdxxMap.ffndyf}								
							</td>
							<th>���˵�λ</th>
							<td>
								${bdxxMap.yrdwmc}
							</td>
						</tr>
						<tr>
							<th>��λ����</th>
							<td>
								${bdxxMap.gwxzmc}
							</td>
							<th>��λ���</th>
							<td>
								${bdxxMap.gwlbmc}
							</td>
						</tr>
						<tr>
							<th>У��</th>
							<td>
								${bdxxMap.xqmc}
							</td>
							<th>���(Ԫ)</th>
							<td>
								${bdxxMap.bcje}
							</td>
						</tr>
						<tr>
							<th>��ʱ(Сʱ)</th>
							<td >
								${bdxxMap.gss}
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${bdxxMap.gznr}
							</td>
						</tr>
						<tr>
							<th>��ע
							</th>
							<td colspan="3">
								${bdxxMap.bz}
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