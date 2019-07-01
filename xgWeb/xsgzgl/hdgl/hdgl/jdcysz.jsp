<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function submitYhsz(hdid,jdid,jdsx){
				 var url = "hdgl_hdgl.do?method=zjtcysz&ids=" + hdid + "&jdid=" + jdid;
				 var title = "专家团成员设置";
		        showDialog(title, 800, 500, url);
			}
		</script>
	</head>
	<body>
		<html:form action="/hdgl_hdgl" method="post">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>活动信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>活动名称</th>
							<td>${hdxx.hdmc}</td>
							<th>活动类型</th>
							<td>${hdxx.hdlxmc}</td>
						</tr>
						<tr>
							<th>活动发布时间</th>
							<td>${hdxx.fbsj}</td>
							<th>活动地点</th>
							<td>${hdxx.hddd}</td>
						</tr>
						<tr>
							<th>活动开始时间</th>
							<td>${hdxx.hdkssj}</td>
							<th>活动结束时间</th>
							<td>${hdxx.hdjssj}</td>
						</tr>
						<tr>
							<th>报名对象</th>
							<td>${hdxx.bmdx}</td>
							<th>报名类型</th>
							<td>${hdxx.bmlxmc}</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>阶段成员设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="rsTable" style="width:100%;">
									<table  style="width:100%;" border="0">
										<thead>
											<tr>
												<td align="center">序号</td>
												<td align="center">阶段名称</td>
												<td align="center">已有审核人数</td>
												<td align="center">用户授予</td>
											</tr>
										</thead>
										<logic:present name="jdcyxxList">
											<logic:iterate id="jdcyxx" name="jdcyxxList" indexId="i">
												<tr>
													<td align="center">${jdcyxx.num}</td>
													<td align="center">${jdcyxx.jdmc}</td>
													<td align="center">${jdcyxx.yyshrs}</td>
													<td align="center"><button type="button" onclick="submitYhsz('${jdcyxx.hdid}','${jdcyxx.jdid}','${jdcyxx.jdsx}')">用户授予</button></td>
												</tr>
											</logic:iterate>
										</logic:present>
										<logic:empty name="jdcyxxList">
											<tr>
												<td console="5" align="center">阶段未设置成员！</td>
											</tr>
										</logic:empty>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px;"></div>
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