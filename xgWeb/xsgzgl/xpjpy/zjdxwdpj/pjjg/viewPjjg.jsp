<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
		
	<body>
		<html:form action="/xpjpy_pjjg" method="post" styleId="pjjgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>评奖项目申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>学年</th>
							<td colspan="3">
								${rs.xn }
							</td>
					    </tr>
					    <tr>
							<th>项目类型</th>
							<td>
								${rs.lxmc }
							</td>
							<th>项目性质</th>
							<td>
								${rs.xzmc }
							</td>
						</tr>
						<tr>
							<th>项目名称</th>
							<td>
								${rs.xmmc }
							</td>
					    
							<th>金额</th>
							<td>
								${rs.xmje }
							</td>
						</tr>
						<tr>
							<th>外语水平</th>
							<td>${rs.wysp}</td>
							<th>宿舍电话</th>
							<td>${rs.ssdh}</td>
						</tr>
						<tr>
							<th>担任社会工作职务</th>
							<td colspan="3">${rs.gzzw}</td>
						</tr>
						<tr>
							<th>个人学习经历</th>
							<td colspan="3">${rs.grxxjl}</td>
						</tr>
						<tr>
							<th>参加科研情况</th>
							<td colspan="3">${rs.cjkyqk}</td>
						</tr>
						<tr>
							<th>对设奖单位的认识</th>
							<td colspan="3">${rs.dwrs}</td>
						</tr>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="ylzd5" styleId="fjid"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
					    <tr>	
							<th>
							申请时间
							</th>
							<td colspan="3">
								${rs.sqsj }
							</td>
					    </tr>
					    <tr>
							<th>
								申请理由
							</th>
							<td colspan="3">
								${rs.sqly }
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
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