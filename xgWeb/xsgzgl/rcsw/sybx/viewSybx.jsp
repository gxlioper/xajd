<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sybx/js/updateSybx.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>
		
	</head>
	<body style="width:699px">
		
		<html:form action="/rcsw_sybx" method="post" styleId="sybxForm">
			<html:hidden property="guid"/>
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>ѧ��</th>
							<td>
								${rs.xn }
							</td>
							<th>�������ҽ��<br/>���ս��</th>
							<td>
								${rs.czjmylbxje }
							</td>
					    </tr>
					    <tr>
					    	<th>�������ҽ�Ʊ��ղα���ʼ����</th>
							<td>
								${rs.czjmylbxcbqsrq }
							</td>
							<th>�������ҽ�Ʊ��ղα���������</th>
							<td>
								${rs.czjmylbxcbjsrq }
							</td>
					    </tr>
					    <tr>
					    	<th>��ҵ���ս��</th>
							<td>
								${rs.sybxje }
							</td>
							<th>��ҵ���ղα�<br/>��ʼ����</th>
							<td>
								${rs.sybxcbqsrq }
							</td>
					    </tr>
					    <tr>
							<th>��ҵ���ղα�<br/>��������</th>
							<td>
								${rs.sybxcbjsrq }
							</td>
							<th>�����ܽ��</th>
							<td>
								${rs.bxje }
							</td>
					    </tr>
					    <tr>
					    	<th>����ԭ��</th>
							<td>
								${rs.zjyymc }
							</td>
							<th>�α���Ա���</th>
							<td>
								${rs.cbrylbmc }
							</td>
					    </tr>
					    <tr>
							<th>�ɷ���Ա���</th>
							<td>
								${rs.jfrylbmc }
							</td>
							<th>���֤ǩ������</th>
							<td>
								${rs.sfzqfjg }
							</td>
					    </tr>
					    <tr>
							<th>���֤��Ч����<br/>��ʼ����</th>
							<td>
								${rs.sfzyxqxqsrq }
							</td>
							<th>���֤��Ч����<br/>��ֹ����</th>
							<td>
								${rs.sfzyxqxjzrq }
							</td>
					    </tr>
					    <tr>
							<th>�໤������</th>
							<td>
								${rs.jhrxm }
							</td>
							<th>�໤�����֤��</th>
							<td>
								${rs.jhrsfzh }
							</td>
					    </tr>
					    <tr>
							<th>
								ͨѶ��ַ
							</th>
							<td colspan="4">
								${rs.txdz }
							</td>
			     		 </tr>
					    <tr>
							<th>
								��ע
							</th>
							<td colspan="4" style="word-wrap: break-word!important; word-break: break-all!important">
								${rs.bz }
							</td>
			     		 </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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