<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxdj/js/lxdj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				proviceCiyyLocalMain({type:"view",id:"mddssx",flag:"wxxdz"});
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/qmlxjg" method="post" styleId="LxjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
							<th>ѧ��</th>
							<td>
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>�Ƿ���У</th>
							<td>
									${rs.sflxdm}
							</td>
							<th>��У����</th>
							<td>
									${rs.lxlxmc}
							</td>
						</tr>
						<tr>
							<th>�໤������</th>
							<td>
								${rs.jhrxm}
							</td>
							<th>�໤����ϵ��ʽ</th>
							<td>
								${rs.jhrlxfs}
							</td>
						</tr>
						<tr>
							<th>�Ƿ���໤����ϵ</th>
							<td>
								${rs.sflx}
							</td>
							<th>��Уʱ��</th>
							<td>
								${rs.lxsj}
							</td>
						</tr>
						<tr>
							<th>��У��ͨ����</th>
							<td>
								${rs.lxgj}
							</td>
							<th>��У����/����</th>
							<td>
								${rs.lxcchb}
							</td>
						</tr>
						<tr>
							<th>Ŀ�ĵ�</th>
							<td colspan="3">
								<html:hidden name="rs"  property="mddssx" styleId="mddssx"/>
							</td>
						</tr>
						<tr>
							<th>��Уʱ��</th>
							<td>
								${rs.fxsj}
							</td>
							<th>��У��ͨ����</th>
							<td>
								${rs.fxgj}
							</td>
						</tr>
						<tr>
							<th>��У����/����</th>
							<td>
								${rs.fxcchb}
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3">
								${rs.bz}
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px"></div>
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