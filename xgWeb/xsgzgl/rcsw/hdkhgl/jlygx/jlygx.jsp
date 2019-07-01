<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hdkhgl/jlygx/js/jlygx.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function)(){
				
			})
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/hdkhgl_hddj" method="post" styleId="HdkhglForm">
		<html:hidden property="id" name="xsjlgxxx"/>
		<html:hidden property="hdjgid" name="xsjlgxxx"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>记录与感想</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th><font color="red">*</font>内容<br/><font color="red">(限500字)</font></th>
							<td >
								<html:textarea property="jlygx" styleId="jlygx" name="xsjlgxxx"
								   onkeyup="checkzsonsubmit(this);" 
								   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
								    <button type="button" onclick="saveXsjlgx();">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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