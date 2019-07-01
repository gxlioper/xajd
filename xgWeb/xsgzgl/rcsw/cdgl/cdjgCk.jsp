<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_cdgl_cdjg" method="post" styleId="rcswCdjgForm">
			<html:hidden property="sqid"/>
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>场地申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								场地名称
							</th>
							<td colspan="3">
								${cdjgxx.cdmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								楼栋
							</th>
							<td width="34%">
								${cdjgxx.ld}
							</td>
							<th width="16%">
								房间
							</th>
							<td width="34%">
								${cdjgxx.fj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								容纳人数
							</th>
							<td width="34%">
								${cdjgxx.rnrs}
							</td>
							<th width="16%">
								收费标准
							</th>
							<td width="34%">
								${cdjgxx.sfbz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								联系人
							</th>
							<td>
								${cdjgxx.lxr}
							</td>
							<th width="16%">
								联系方式
							</th>
							<td>
								${cdjgxx.lxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								对外开放时间
							</th>
							<td colspan="3">
								${cdjgxx.dwkfsjkssj}
									至
								${cdjgxx.dwkfsjjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								用途
							</th>
							<td colspan="3">
								${cdjgxx.yt}
							</td>
						</tr>
						<tr>
							<th width="16%">
								基本设备介绍
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.jbsbjs}
							</td>
						</tr>
						<logic:equal value="10351" name="xxdm">
						<tr>
							<th width="16%">
								幸福工坊使用协议
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.xfgfsyxy}
							</td>
						</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" name="cdjgxx" styleId="fjid"/>
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
						</logic:equal>
						<tr>
							<th width="16%">
								申请使用时间段
							</th>
							<td colspan="3">
								${cdjgxx.sqsjdkssj}
								至
								${cdjgxx.sqsjdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">使用部门</th>
							<td width="34%">
								${cdjgxx.bmmc}
							</td>
							<th width="16%">申请时间</th>
							<td width="34%">
								${cdjgxx.sqsj}
							</td>
						</tr>
						<tr>
							<th width="16%">参与人数</th>
							<td width="34%">
								${cdjgxx.cyrs}
							</td>
							<th width="16%">负责人</th>
							<td width="34%">
								${cdjgxx.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="16%">负责人联系方式</th>
							<td colspan="3">
								${cdjgxx.fzrlxfs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请理由
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.sqly}
							</td>
						</tr>
						<logic:equal name="xxdm" value="10346">
						<tr>
							<th width="16%">评价</th>
							<td colspan="3">
								${cdjgxx.pj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								评价备注
							</th>
							<td colspan="3" style="word-break: break-all;">
								${cdjgxx.pjbz}
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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

