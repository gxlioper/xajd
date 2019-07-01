<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxsh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//为button注册事件
				jQuery("#xzpxxm").load('szdw_fdypxxmwh.do?method=fdypxxmxzView&xmdm=${model.xmdm}')
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
				jQuery("#yhdpxxm").load('szdw_fdypxxmsh.do?method=yhdpxxm&sqid=${model.sqid}');
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl.do?method=zjXxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>基本信息</span>
								<html:hidden property="sqid" name="model" styleId="sqid"/>
								<html:hidden property="shid" name="model" styleId="shid"/>
								<html:hidden property="shzt" name="model" styleId="shzt"/>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								职工号
							</th>
							<td>
							${rs.zgh }
							</td>
							<th >
								姓名
							</th>
							<td width="34%">
							${rs.xm}
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td width="34%">
							${rs.xbs}
							</td>
							
							<th>
								所在部门
							</th>
							<td width="34%">
							${rs.bmmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>申请培训信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqly">
						<tr>
							<th >
								申请时间
							</th>
							<td width="34%" colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th >
								申请理由
							</th>
							<td width="34%" colspan="3">
								${model.sqly }
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>申请培训项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqlys">
						<tr>
							<td colspan="4" id="xzpxxm">
								 
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>已通过培训项目</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_sqjg">
						<tr>
							<td colspan="4" id="yhdpxxm">
								 
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
								<div class="bz"></div>
								<div class="btn">
									<div class="btn">
										
										<button type="button" name="关 闭" onclick="iFClose();">
											关 闭
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

