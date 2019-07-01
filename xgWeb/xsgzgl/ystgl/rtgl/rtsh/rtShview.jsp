<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsh/js/rtsh.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${form.rtid}&tt="+new Date().getTime());
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ystglRtsh" method="post" styleId="YstRtshForm">
				
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>艺术团项目维护</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>艺术团项目名称</th>
							<td>${ystxx.ystxmmc}</td>
						</tr>
						<tr>
							<th>艺术团类别</th>
							<td>${ystxx.ystlbmc}</td>
							<th>项目类别</th>
							<td>${ystxx.xmlbmc}</td>
							
						</tr>
						<tr>
							<th>联系电话</th>
							<td>${ystxx.lxdh }</td>
							<th>挂靠单位</th>
							<td>${ystxx.gkdwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
							   <a style="text-align: left;" onclick="showYstmx(this);" class="down"
									href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_toggle" style="display:none">
						<tr>
							<th>负责人类别</th>
							<td>${ystxx.fzrlb }</td>
							<th>负责人</th>
							<td>${ystxx.stfzrxm }</td>
						</tr>
						<tr>
							<th>指导老师</th>
							<td>${ystxx.zdlsxm }</td>
							<th>指导老师职称</th>
							<td>${ystxx.zcmc }</td>
						</tr>
						<tr>
							<th>指导老师联系方式</th>
							<td>${ystxx.zdlslxfs }</td>
							<th>所属部门</th>
							<td>${ystxx.ssbmmc }</td>
						</tr>
						<tr>
							<th>艺术团成立时间</th>
							<td>${ystxx.ystclsj }</td>
							<th>申请时间</th>
							<td>${ystxx.sqsj }</td>
						</tr>
						<tr>
							<th>
								艺术团简介
							</th>
							<td colspan="3" name="stsm">
								${ystxx.ystjj}
							</td>
						</tr>
						<tr>
							<th>
								艺术团获奖情况
							</th>
							<td colspan="3" name="sthjqk">
								${ystxx.ysthjqk}
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>特长</th>
							<td colspan="3">${ystxx.tc}</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">${ystxx.sqly}</td>
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