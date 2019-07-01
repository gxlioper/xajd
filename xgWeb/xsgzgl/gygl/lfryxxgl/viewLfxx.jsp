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
		<script type="text/javascript" src="xsgzgl/gygl/lfryxxgl/js/lfrydj.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_lfrydj" method="post" styleId="lfrydjForm">
			<html:hidden property="lfrdjid" value="${lfrydjxx.lfrdjid}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>来访信息查看</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th style="width:18%">
								楼栋名称
							</th>
							<td colspan="3">
								<span id="ldmc">${lfrydjxx.ldmc }</span>
							</td>
						</tr>
						<tr>
							<th style="width:18%">
								来访人姓名
							</th>
							<td>
								<span id="lfrxm">${lfrydjxx.lfrxm}</span>
							</td>
							<th style="width:18%">
								来访人性别
							</th>
							<td>
								<span id="lfrxb">${lfrydjxx.lfrxb}</span>
							</td>
						</tr>
						<tr>
							<th>
								来访人证件号码
							</th>
							<td colspan="3">
								<span id="lfrsfzh">${lfrydjxx.lfrsfzh}</span>
							</td>
						</tr>
						<tr>
							<th>
								被访人学号
							</th>
							<td >
								<span id="xh">${lfrydjxx.xh}</span>
							</td>
							<th>
								被访人姓名
							</th>
							<td>
								<span id="xm">${lfrydjxx.xm}</span>
							</td>
						</tr>
						
						<tr>
							<th >被访人学院名称</th><td>	<span id="xymc">${lfrydjxx.xymc}</span></td>
							<th >被访人专业名称</th><td>	<span id="zymc">${lfrydjxx.zymc}</span></td>
						</tr>
						
						<tr>
							<th >被访人班级名称</th><td>	<span id="bjmc">${lfrydjxx.bjmc}</span></td>
							<th >楼栋名称</th><td>	<span id="ldmc">${lfrydjxx.ldmc}</span></td>	
						</tr>
						
						<tr>
							<th >寝室号</th><td colspan="3">	<span id="qsh">${lfrydjxx.qsh}</span></td>
						</tr>
						
						<tr>
							<th >来访事由</th>
							<td colspan="3">
								<span id="lfsymc">${lfrydjxx.lfsymc}</span>
							</td>
						</tr>
						
						<tr>
							<th>
								来访时间
							</th>
							<td>
								<span id="lfsj">${lfrydjxx.lfsj}</span>
							</td>
							<th>
								离去时间
							</th>
							<td>
								<span id="lqsj">${lfrydjxx.lqsj}</span>
							</td>
						</tr>
						<tr>
							<th>
								值班人员
							</th>
							<td colspan="3">
								<span id="zbry">${lfrydjxx.zbry}</span>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break: break-all;">
								<span id="bz">${lfrydjxx.bz}</span>
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

