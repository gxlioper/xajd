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
		<script type="text/javascript" src="xsgzgl/rwgl/rwtw/js/rwxfDgbc.js"></script>
		
	</head>
	<body style="width:689px">
		
		<html:form action="/rwgl_rwxfbcgl" method="post" styleId="rwxfbcglForm">
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
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>
								${rs.rwxn }
							</td>
							<th>����ʱ��</th>
							<td>
								${rs.rwsj }
							</td>
					    </tr>
					    <tr>
							<th style="word-break:break-all;width:150px">�����</th>
							<td colspan="3">
								<!-- ���ݴ�ѧ -->
								<logic:equal name="xxdm" value="10351">	
									${rs.rwdwdmc }
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									${rs.rwd }
								</logic:notEqual>
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����ѧ�Ѳ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>ѧ��</th>
							<td>
								${rs.xn }
							</td>
							<th>ѧ�Ѳ������</th>
							<td>
								${rs.xfbcje }
							</td>
					    </tr>
					    <tr>
							<th>ѧ�Ѳ���ʱ��</th>
							<td>
								${rs.xfbcsj }
							</td>
							<th></th>
							<td>
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