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
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/xsqj_jscx" method="post" styleId="XsqjcxForm">
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
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<% String sjly=((HashMap<String,String>)request.getAttribute("ckxx")).get("sjly");//��ǰѡ�е�¥������
						String qjts = ((HashMap<String,String>)request.getAttribute("ckxx")).get("qjts");
					 %>
						<%if("new".equals(sjly)){ %>
						<tr>
							<th>�������</th>
							<td >
								${ckxx.qjlxmc}
							</td>
							<th>�������</th>
							<td>
								${ckxx.qjts}
							</td>
						</tr>
						<tr>
							<th>�����������</th>
							<td>
								${ckxx.qjtslx}
							</td>
							<th>�Ƿ񲹼�</th>
							<td >
								${ckxx.sfbj}
							</td>
						</tr>
						<tr>
							<th>����¥</th>
							<td>${ckxx.qsl}</td>
							<th>���Һ�</th>
							<td>
								${ckxx.qsh}
							</td>
						</tr>
						<tr>
							<th>��ϵ��ʽ</th>
							<td>
								${ckxx.lxfs}
							</td>
							<th>�ҳ��绰</th>
							<td>
								${ckxx.jzlxfs}
							</td>
						</tr>
						<tr>
							<th>ǰ���ص�</th>
							<td colspan="3">${ckxx.qwdd}</td>
						</tr>
						<tr>
							<th>�������</th>
							<td colspan="3">${ckxx.qjly}</td>
						</tr>
						<tr>
						<th>��ٿ�ʼʱ��</th>
							<td>
								${ckxx.qjkssj}
							</td>
							<th>��ٽ���ʱ��</th>
							<td>
								${ckxx.qjjssj}
							</td>
						</tr>
						<tr>
							<th>��ٽڴ�</th>
							<td colspan="3">${ckxx.qjjc}</td>
						</tr>
						<tr>
						<th>�Ƿ����</th>
							<td>
								${ckxx.sfgq}
							</td>
							<th>������˵��</th>
							<td>
								${ckxx.bgqsm}
							</td>
						</tr>
						<%}else if("gj".equals(sjly)){ %>
						<tr>
							<th>�������</th>
							<td >
								${ckxx.qjlxmc}
							</td>
							<th>���췽������</th>
							<td>
								${ckxx.zbffzr}
							</td>
						</tr>
						<tr>
						<th>��ٿ�ʼʱ��</th>
							<td>
								${ckxx.qjkssj}
							</td>
							<th>��ٽ���ʱ��</th>
							<td>
								${ckxx.qjjssj}
							</td>
						</tr>
						<tr>
							<th>��ٽڴ�</th>
							<td colspan="3">${ckxx.qjjc}</td>
						</tr>
						<tr>
						<th>�Ƿ����</th>
							<td>
								${ckxx.sfgq}
							</td>
							<th>������ʱ��</th>
							<td>
								${ckxx.bgqsj}
							</td>
						</tr>
						<tr>
							<th>�������</th>
							<td colspan="3">${ckxx.qjly}</td>
						</tr>
						<tr>
							<th>��ٽڴ�</th>
							<td colspan="3">${ckxx.qjjc}</td>
						</tr>
							<tr>
							<th>��ע</th>
							<td colspan="3">
								${ckxx.bz}
							</td>
						</tr>
							<%} %>
						</table>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>�����б�</span>
								</th>
							</tr>
						</thead>
						<tr align='center'>
							<td width="20%">��������</td>
							<td width="18%">��ʼʱ��</td>
							<td width="18%">����ʱ��</td>
							<td width="10%">ִ����</td>
							<td width="24%">�������</td>
							<td width="10%">����״̬</td>
						</tr>
						<logic:iterate id="i" name="splslist">
							<tr align='center'>
								<td>${i.taskname}</td>
								<td>${i.starttime}</td>
								<td>${i.endtime}</td>
								<td>${i.exefullname}</td>
								<td>${i.opinion}</td>
								<td>${i.checkstatus}</td>
							</tr>
						</logic:iterate>
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