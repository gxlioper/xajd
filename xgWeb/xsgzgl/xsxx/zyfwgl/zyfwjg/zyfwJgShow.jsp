<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zyfwJgForm.fwid}&tt=" + new Date().getTime());
				
				proviceCiyyLocalMain({type:"view",id:"fwddssx",flag:"yxxdz"});
				
				jQuery.ajax({
					url:"xsxx_zyfwgl_jg.do?method=getZyfwJgListDataByXh",
					type:"POST",
					dataType: "JSON",
					data:{xh:'${zyfwJgForm.xh}'},
					success: function(data){
						if(data.length>0){
							var html = "";
							var hjfwxss = 0;
							for(var i=0;i<data.length;i++){
								html+="<tr><td>"+data[i]["xn"]+"</td><td>"+data[i]["xqmc"]+"</td><td>"+data[i]["fwddxxdz"]+"</td>"+
								"<td>"+data[i]["fwsj"]+"</td><td>"+data[i]["fwxss"]+"</td></tr>";
								hjfwxss+=Number(data[i]["fwxss"]);
							}
							html+="<tr><td align=\"center\">合计服务小时数</td><td colspan=\"4\" align=\"center\">"+hjfwxss+"</td></tr>";
							jQuery("tbody#zyfwLsxx").html(html);
						}else{
							var html = "<tr style=\"height:22px\"><td colspan=\"6\" align=\"center\">暂时没有数据</td></tr>";
							jQuery("tbody#zyfwLsxx").html(html);
						}
					}
				});
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
							<span>志愿服务信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							学年
						</th>
						<td width="30%">
							${zyfwJgForm.xn}
						</td>
						</td>
						<th width="20%">学期</th>
						<td width="30%">
							${zyfwJgForm.xqmc}
						</td>
					</tr>
					<tr>
						<th width="20%">
							服务开始时间
						</th>
						<td width="30%">
							${zyfwJgForm.fwkssj}
						</td>
						<th width="20%">
							服务结束时间
						</th>
						<td width="30%">
							${zyfwJgForm.fwjssj}
						</td>
					</tr>
					<tr>
						<th width="20%">
							见证人
						</th>
						<td width="30%">
							${zyfwJgForm.jzr}
						</td>
						<th width="20%">服务小时数</th>
						<td width="30%">
							${zyfwJgForm.fwxss}
						</td>
					</tr>
					<tr>
						<th width="20%">
							服务地点
						</th>
						<td colspan="3">
							<input type="hidden" id="fwddssx" value="${zyfwJgForm.fwddssx}"/>
							${zyfwJgForm.fwdd}
						</td>
					</tr>
					<tr>
						<th width="20%" >服务内容 </th>
						<td colspan="3">
							${zyfwJgForm.fwnr}
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>志愿服务记录</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>学年</b></td>
										<td ><b>学期</b></td>
										<td><b>服务地点</b></td>
										<td ><b>服务时间</b></td>
										<td ><b>服务小时数</b></td>
									</tr>
								</thead>
								<tbody align="left" id="zyfwLsxx">
								</tbody>
							</table>
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

