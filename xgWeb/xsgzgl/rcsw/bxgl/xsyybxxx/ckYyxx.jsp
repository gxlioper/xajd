<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/xsyybxxx/js/xsyybxxx.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
				jQuery("#but_close").click(btn_close);
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_bxgl_xsyybx.do" method="post" styleId="demoForm">
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
					<thead>
						<tr>
							<th colspan="4">
								<span>ԤԼ������Ϣ</span>		
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								ԤԼʱ��
							</th>
							<td align="left" colspan="3" >
								${model.yysj}
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" >
								${model.blnr}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position:
					fixed; _position:absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz"></div>
								<div class="btn">
									<button type="button" name="�� ��" id="but_close" >
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

