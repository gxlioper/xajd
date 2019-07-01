<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zyfwSqForm.fwid}&tt=" + new Date().getTime());
				
				proviceCiyyLocalMain({type:"view",id:"fwddssx",flag:"yxxdz"});
			});
		</script>
	</head>
	<body style="width: 100%">
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
							<span>志愿服务登记信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							学年
						</th>
						<td width="30%">
							${zyfwSqForm.xn}
						</td>
						</td>
						<th width="20%">学期</th>
						<td width="30%">
							${zyfwSqForm.xqmc}
						</td>
					</tr>
					<tr>
						<th width="20%">
							服务开始时间
						</th>
						<td width="30%">
							${zyfwSqForm.fwkssj}
						</td>
						<th width="20%">
							服务结束时间
						</th>
						<td width="30%">
							${zyfwSqForm.fwjssj}
						</td>
					</tr>
					<tr>
						<th width="20%">
							见证人
						</th>
						<td width="30%">
							${zyfwSqForm.jzr}
						</td>
						<th width="20%">服务小时数</th>
						<td width="30%">
							${zyfwSqForm.fwxss}
						</td>
					</tr>
					<tr>
						<th width="20%">
							服务地点
						</th>
						<td colspan="3">
							<input type="hidden" id="fwddssx" value="${zyfwSqForm.fwddssx}"/>
							${zyfwSqForm.fwdd}
						</td>
					</tr>
					<tr>
						<th width="20%" >服务内容 </th>
						<td colspan="3">
							${zyfwSqForm.fwnr}
						</td>
					</tr>
				</tbody>
			 </table>
			 <table width="100%" border="0" class="formlist">
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
		  <div style="height:35px"></div>   
		  <div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>

