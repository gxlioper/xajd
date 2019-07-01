<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${dazcsqForm.sqid}&tt="+new Date().getTime());
				var zcfs = "${rs.zcfs}";
				if("邮寄" == zcfs){
					/*隐藏字段*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#byoOne").hide();
				}else{
					/*隐藏字段*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#byoOne").show();
				}
			})
		</script>
	</head>
	<body>
		<html:form action="/dagl_dazcsq" method="post" styleId="dazcsqForm">
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
								<span>档案转出申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>转出方式</th>
							<td colspan="3">${rs.zcfs}</td>
						</tr>
						
						<tr id="mailedOne">
							<th>邮寄地址</th>
							<td>${rs.yjdz}</td>
							<th>邮政编码</th>
							<td>${rs.yzbm}</td>
						</tr>
						
						<tr id="mailedTwo">
							<th>收件人</th>
							<td>${rs.sjr}</td>
							<th>收件人电话</th>
							<td>${rs.sjrdh}</td>
						</tr>
						
						<tr id="mailedThree">
							<th>单位名称</th>
							<td>${rs.dwmc}</td>
							<th>单位地址</th>
							<td>${rs.dwdz}</td>
						</tr>
						
						<tr id="byoOne">
							<th>自带档案承诺</th>
							<td>
								我已阅并接受
								<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${dazccsszForm.uploadid}');return false;" class="name" style="margin-left: 0px;">
									《档案转出协议》
								</a>
							</td>
							<th>预期提档日期</th>
							<td>${rs.yqtdrq}</td>
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