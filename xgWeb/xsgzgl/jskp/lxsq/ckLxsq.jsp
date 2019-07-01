<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${LxsqForm.sqid}&tt="+new Date().getTime());
			})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/jskp_lxsq" method="post" styleId="LxsqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>立项信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">项目名称</th>
							<td style="width:35%">
								${LxsqForm.xmmc}
							</td>
							<th style="width:15%">指导部门</th>
							<td style="width:35%">
								${bmmc}
							</td>
						</tr>	
						<tr>
							<th>项目类别</th>
							<td>
								${xmlbmc}
							</td>
							<th>立项时间</th>
							<td>
								${LxsqForm.lxsj}
							</td>
						</tr>
						<tr>
							<th>负责人</th>
							<td>
								<span>${fzrxm}</span>
							</td>
							<th>负责人联系方式</th>
							<td>
								${LxsqForm.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td>
								${LxsqForm.zdls}
							</td>
							<th>指导老师联系方式</th>
							<td>
								${LxsqForm.zdlslxfs}
							</td>
						</tr>
						<logic:equal value="1" name="sfsh">
							<tr>
								<th>评分区间</th>
								<td colspan="3">
									${LxsqForm.zxf}-${LxsqForm.zdf}
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>参与人
							</th>
							<td colspan="3">
									<logic:iterate id="i" name="xhList">
										${i.xh}(${i.xm})&nbsp;
									</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>
								附件
							</th>
							<td colspan="3">
							<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
								jQuery(function(){
									var gid = "${LxsqForm.filepath}";
									jQuery.MultiUploader_q({
										gid : gid,
										targetEl : 'commonfileupload-list-0'
									});
								});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								立项理由
							</th>
							<td colspan="3">
									${LxsqForm.lxly}
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
				<div style="height:30px;"></div>
				<%--;height:520px --%>
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