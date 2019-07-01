<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
	jQuery(function() {

		jQuery("#shlccx").load(
				"comm_spl.do?method=lccx&sqid=${model.sqid}&tt="
						+ new Date().getTime());
	});

</script>
	</head>
	<body>

		<html:form action="/gzjlsq" method="post" styleId="GzjlsqForm">
			<html:hidden property="sqid" styleId="sqid" />
			<div style="overflow-x:hidden;overflow-y:auto;height:510px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/szdw/gzjl/comm/viewTeacher.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>工作记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>工作时间</th>
							<td>
							${model.gzsj}
							</td>
							<th>记录时间</th>
							<td>
							${model.jlsj}
							</td>
						</tr>
						<tr>
							<th>工作类别</th>
							<td>
								${model.gzlbmc}
							</td>
							<th>类别编号</th>
							<td>
							${model.lbdm}
							</td>
						</tr>
						<tr>
						
							<th>工作摘要</th>
							<td colspan="3">
								${model.gzzy}
							</td>
						</tr>
						<tr>
						
							<th>备注</th>
							<td colspan="3">
								${model.bz}
							</td>
						</tr>
						<logic:equal value="11842" name="xxdm">
							<tr>
								<th>六困生</th>
								<td colspan="3">
									${model.lksmc}
								</td>
							</tr>
							<logic:present name="thdxList">
								<thead>
									<tr>
										<th colspan="4">
											<span>谈话对象</span>
										</th>
									</tr>
						  		</thead>
							</logic:present>							
						</logic:equal>
					</tbody>					
				</table>
				<logic:equal value="11842" name="xxdm">
			   <logic:present name="thdxList"> 
				<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">					
					<thead>
						<tr>
							<td width='15%'>学号</td>
							<td width='10%'>姓名</td>
							<td width='10%'>性别</td>
							<td width='20%'>学院</td>
							<td width='20%'>专业</td>
							<td width='25%'>班级</td>
						</tr>
					</thead>
					<tbody id="tbody_dhryxx">
						<logic:iterate id="i" name="thdxList" indexId="index01">
							<tr>
								<td name="xhArr">${i.xh}</td>
								<td>${i.xm}</td>
								<td>${i.xb}</td>						
								<td>${i.xymc}</td>
								<td>${i.zymc}</td>
								<td >${i.bjmc}</td>						
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>			
			</logic:present>
			</logic:equal>
							
				<logic:notEqual value="无需审核" name="shztmc">
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
				</logic:notEqual>
				</div>
				<div style="height:20px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

