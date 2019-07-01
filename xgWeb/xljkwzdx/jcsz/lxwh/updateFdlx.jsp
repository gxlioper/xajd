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
						<span>辅导类型</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<span class="red">*</span> 类型代码
					</th>
					<td>
						<html:text property="fdlxdm" styleId="fdlxdm" styleClass="text_nor" readonly="true" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th>
						 类型名称
					</th>
					<td>
						<html:text property="fdlxmc" styleId="fdlxmc" styleClass="text_nor" maxlength="100"/>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"为必填项</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateFdlxAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
								关 闭
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
    </html:form>
  </body>
</html>
