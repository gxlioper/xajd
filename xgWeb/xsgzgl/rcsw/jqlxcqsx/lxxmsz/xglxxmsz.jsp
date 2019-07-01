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
		<script type="text/javascript" src="xsgzgl/rcsw/jqlxcqsx/lxxmsz/js/lxxmsz.js"></script>	
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/jqlx_lxxmsz" method="post" styleId="LxxmszForm">
			<html:hidden property="xmid" styleId="xmid"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>留校项目设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%" ><font color="red">*</font>留校项目名称</th>
							<td width="70%">
								<html:text property="xmmc" styleId="xmmc" maxlength="20" style="width:95%" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>留校起止时间</th>
							<td>
								<html:text property="lxkssj" styleId="lxkssj" onclick="return showCalendar('lxkssj','y-mm-dd',true,'lxjssj');"  maxlength="10" style="width:41%" />&nbsp;&nbsp;至&nbsp;&nbsp;<html:text property="lxjssj" styleId="lxjssj" onclick="return showCalendar('lxjssj','y-mm-dd',false,'lxkssj');" maxlength="10" style="width:41%" />
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>留校项目说明<br/><font class='red'>(限制字数500字)</font></th>
							<td>
								<html:textarea property="lxxmsm" styleId="lxxmsm" onblur="checkLen(this,500)" style="width:95%" rows="10"  />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveSzjg('update');">
										保    存
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