<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/zzgxzc/zcsq/js/zcsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load(
				"comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
						+ new Date().getTime());
		})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/dzzgxsq" method="post" styleId="ZcsqForm">
			<html:hidden property="sqid" styleId="sqid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>登记信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>所在党支部</th>
							<td>
								<bean:write name="rs"  property="dzbmc"/>					
							</td>
							<th>是否省内</th>
							<td>
								<bean:write name="rs"  property="sfsn"/>
							</td>
						</tr>
						<tr>
							<th>接收本人组织关系的党组织</th>
							<td colspan="3">
								<bean:write name="rs"  property="jsdzz"/>
							</td>
						</tr>
						<tr>
							<th>本人组织关系所去单位</th>
							<td colspan="3">
								<bean:write name="rs"  property="sqdw"/>
							</td>
						</tr>
						<tr>
							<th>党费交至日期</th>
							<td >
								<bean:write name="rs"  property="dfjzrq"/>
							</td>
							<th>是否需要开具婚姻证明</th>
							<td >
								<bean:write name="rs"  property="sfkjhyzm"/>
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
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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