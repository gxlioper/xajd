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
				//Ϊbuttonע���¼�
				jQuery("#but_save").click(function(){
					saveForm('yyxxUpdate');
				});
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
								<span class="red">*</span>ԤԼʱ��
							</th>
							<td align="left" colspan="3" >
								<html:hidden property="yybxid" name="model"/>
								<html:text name="model" property="yysj" styleId="yysj" style="width:145px;" onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'yysj');" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��������
								<br />
								<font color="red">(��250��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea  name="model" property="blnr" styleId="blnr" style="width:95%;" rows="8"></html:textarea>
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
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="but_save" type="button" >
										�� ��
									</button>
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

