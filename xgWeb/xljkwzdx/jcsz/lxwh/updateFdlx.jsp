<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="xljkwzdx/jcsz/js/fdlxwh.js"></script>
	  <script type="text/javascript">
	  </script>
  </head>
  
  <body>
    <html:form action="/xljk_fdlxwh" method="post" styleId="fdlxwhForm">
    	<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>��������</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<span class="red">*</span> ���ʹ���
					</th>
					<td>
						<html:text property="fdlxdm" styleId="fdlxdm" styleClass="text_nor" readonly="true" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th>
						 ��������
					</th>
					<td>
						<html:text property="fdlxmc" styleId="fdlxmc" styleClass="text_nor" maxlength="100"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"Ϊ������</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateFdlxAction();">
								�� ��
							</button>
							<button type="button" name="�� ��" onclick="iFClose();">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
    </html:form>
  </body>
</html>
