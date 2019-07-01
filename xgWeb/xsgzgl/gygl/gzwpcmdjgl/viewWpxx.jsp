<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gzwpcmdjgl/js/gzwpcmdj.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gzwpcmdj" method="post" styleId="gzwpcmdjForm">
			<html:hidden property="gzwpdjid" value="${gzwpcmdjxx.gzwpdjid}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贵重物品出门登记信息查看</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学号</th><td width="30%">	<span id="xh">${gzwpcmdjxx.xh}</span></td>
							<th width="20%">姓名</th><td width="30%">	<span id="xm">${gzwpcmdjxx.xm}</span></td>
						</tr>
						
						<tr>
							<th >性别</th><td>	<span id="xb">${gzwpcmdjxx.xb}</span></td>
							<th >学院名称</th><td>	<span id="xymc">${gzwpcmdjxx.xymc}</span></td>
						</tr>
						
						<tr>
							<th >专业名称</th><td>	<span id="zymc">${gzwpcmdjxx.zymc}</span></td>
							<th >班级名称</th><td>	<span id="bjmc">${gzwpcmdjxx.bjmc}</span></td>
						</tr>	
						<tr>
							<th >楼栋名称</th><td>	<span id="ldmc">${gzwpcmdjxx.ldmc}</span></td>	
							<th >寝室号</th><td>	<span id="qsh">${gzwpcmdjxx.qsh}</span></td>			
						</tr>
						
						<tr>
							<th >身份证号</th><td>	<span id="sfzh">${gzwpcmdjxx.sfzh}</span></td>	
							<th >
								物品名称
							</th>
							<td>
								<span id="wpmc">${gzwpcmdjxx.wpmc}</span>
							</td>		
						</tr>
						
						<tr>
							<th>
								出门时间
							</th>
							<td>
								<span id="cmsj">${gzwpcmdjxx.cmsj}</span>
							</td>	
							<th >
								值班人员
							</th>
							<td>
								<span id="zbry">${gzwpcmdjxx.zbry}</span>
							</td>	
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break: break-all;">
								<span id="bz">${gzwpcmdjxx.bz}</span>
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

