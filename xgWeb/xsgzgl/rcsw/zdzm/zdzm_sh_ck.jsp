<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_sq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#zdzmsqid").val()+"&tt="+new Date().getTime());
				
				
			});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_zdzm_shgl" method="post" styleId="rcswZdzmShForm">
		<html:hidden property="zdzmsqid" styleId="zdzmsqid"/>
		<html:hidden property="sqsj" />
		<html:hidden property="splcid" styleId="splcid"/>
		<html:hidden property="shzt" />
		<html:hidden property="xh" />
		<html:hidden property="xtgwid" />
		<html:hidden property="shid" styleId="shid"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>在读证明申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write name="rcswZdzmShForm" property="sqly"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								
								<div class="btn">
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
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

