<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/twgl/js/tzz.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/tzzgl" method="post" styleId="tzzForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="20%"><span><font color="red">*</font></span>
								组织名称
							</th>
							<td width="30%">
								<html:text property="zzmc" styleId="zzmc" maxlength="40"></html:text>
							</td>
							
							<th>指导单位<span><font color="red">*</font></span></th>
							<td>
								<html:text property="zddw" styleId="zddw" maxlength="40"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%"><span><font color="red">*</font></span>
								组织地址
							</th>
							<td width="30%">
								<html:text property="zzdz" styleId="zzdz" maxlength="40"></html:text>
							</td>
							</td>
							<th><span><font color="red">*</font></span>负责人</th>
							<td>
								<html:text property="fzr" styleId="fzr" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								组织简介
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='zzjj' style="width:98%" styleId="zzjj" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
			      								
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
									<button type="button" onclick="saveTzz('save');">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

