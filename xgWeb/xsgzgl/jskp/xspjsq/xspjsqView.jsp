<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${xspjsqForm.sqid}&tt="+new Date().getTime());
			})
		</script>
	</head>
	<body>
		<html:form action="/xspj_xspjsq" method="post" styleId="xspjsqForm">
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
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
								<span>自主申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>项目名称</th>
							<td>${rs.xmmc}</td>
							<th>指导部门</th>
							<td>${rs.zdbmmc}</td>
						</tr>
							<th>项目类别</th>
							<td>${rs.xmlbmc}</td>
							<th>参与时间</th>
							<td>${rs.cysj}</td>
						<tr>
							<th>参与人(学号)</th>
							<td>${rs.xh}</td>
							<th>学年</th>
							<td>${rs.xn}</td>
						</tr>
						<tr>
							<th>短学期</th>
							<td>${rs.dxqmc}</td>
							<th>分值</th>
							<td>${rs.fz}</td>
						</tr>
						<!--  
						<tr>
							<th>负责人</th>
							<td>${rs.fzrxm}</td>
							<th>负责人<br/>联系方式</th>
							<td>${rs.fzrlxfs}</td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td>${rs.zdlsxm}</td>
							<th>指导老师<br/>联系方式</th>
							<td>${rs.zdlslxfs}</td>
						</tr>
						-->
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="fjid" styleId="fjid"/>
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
								申请理由
							</th>
							<td colspan="3">
								${rs.sqly}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审批信息</span>
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
			<div style="height: 30px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
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