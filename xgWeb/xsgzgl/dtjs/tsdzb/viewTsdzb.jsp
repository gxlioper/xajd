<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/tsdzb/js/tsdzb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/dtjs_tsdzb" method="post" styleId="tsdzbForm" onsubmit="return false;">
			<html:hidden property="dzbid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>党支部信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								党支部名称
							</th>
							<td width="30%">
								${tsdzbForm.dzbmc}
							</td>
							</td>
							<th>
								负责人
							</th>
							<td>
								${tsdzbForm.fzr}
							</td>
						</tr>
						<tr>
							<th width="20%">
								联系方式
							</th>
							<td width="30%">
								${tsdzbForm.lxfs}
							</td>
							</td>
							<th>创建人</th>
							<td>
								${tsdzbForm.cjr}
							</td>
						</tr>
						<tr>
							<th width="20%">
								创建时间
							</th>
							<td colspan="3">
								${tsdzbForm.cjsj}
							</td>
						</tr>
						<tr>
							<th>
								管辖班级
							</th>							
							<td colspan="3">
								${tsdzbForm.bjxx}
							</td>						
							</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								${tsdzbForm.bz}
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

