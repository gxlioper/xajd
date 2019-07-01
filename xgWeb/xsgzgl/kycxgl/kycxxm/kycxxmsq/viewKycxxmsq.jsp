<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="+ new Date().getTime());
			});
	   </script>
	</head>
	<body>
		<html:form action="/kycxgl_kycxxm_kycxxmsqgl" method="post" styleId="kycxxmsqForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>科研项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">学年</th>
							<td width="32%">
								${rs.xn}
							</td>
							<th width="18%">科研项目名称</th>
							<td width="32%">
								${rs.xmmc }
							</td>
					    </tr>
					    <tr>
							<th width="18%">指导老师</th>
							<td width="32%">
								${rs.zdlsxm }
							</td>
							<th width="18%">科研类别</th>
							<td width="32%">
								${rs.lbmc }
							</td>
					    </tr>
					    <tr>
							<th width="18%">项目申请人</th>
							<td width="32%">
								${rs.xmsqrxm }
							</td>
							<th width="18%">申请时间</th>
							<td width="32%">
								${rs.xmsqsj }
							</td>
					    </tr>
					    <tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<input type="hidden" id="fjxx" value="${rs.fjxx }" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjxx').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>科研项目成员</span>
							</th>
						</tr>
					</thead>
				</table>
				<logic:empty name="kycxxmcyList">
					<div>
						<table width="100%" border="0" class="datelist" style="margin:2px auto;">
							<tbody>
								<tr>
									<td style="text-align: center;">暂时没有数据</td>
								</tr>
							</tbody>
						</table>
					</div>				
				</logic:empty>
				<logic:notEmpty name="kycxxmcyList">
					<div>
						<table width="100%" border="0" class="datelist" style="margin:2px auto;">
							<thead>
								<tr>
									<td width='11%'>学号</td>
									<td width='11%'>姓名</td>
									<td width='6%'>性别</td>
									<td width='6%'>年级</td>
									<td width='15%'><bean:message key="lable.xb" /></td>
									<td width='16%'>专业</td>
									<td width='20%'>班级</td>
									<td width='15%'>联系方式</td>
								</tr>
							</thead>
							<tbody id="tbody_kycxxm_xs">
								<logic:iterate id="kycxxmcy" name="kycxxmcyList">
									<tr>
										<td>${kycxxmcy.xh }</td>
										<td>${kycxxmcy.xm }</td>
										<td>${kycxxmcy.xb }</td>
										<td>${kycxxmcy.nj }</td>
										<td>${kycxxmcy.xymc }</td>
										<td>${kycxxmcy.zymc }</td>
										<td>${kycxxmcy.bjmc }</td>
										<td>${kycxxmcy.lxfs }</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>				
				</logic:notEmpty>
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
			<div style="height: 40px"></div>
			<div>
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
			</div>
			
		</html:form>
	</body>
</html>

