<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var zcfs = "${rs.zcfsmc}";
				var yjzt = "${rs.yjztmc}";
				if("邮寄" == zcfs){
					/*隐藏字段*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#mailedFour").show();
					if("已邮寄" == yjzt){
						jQuery("#mailedFive").show();
						jQuery("#mailedSix").show();
					}else{
						jQuery("#mailedFive").hide();
						jQuery("#mailedSix").hide();
					}
					jQuery("#byoOne").hide();
					jQuery("#byoTwo").hide();
				}else if("自带" == zcfs){
					/*隐藏字段*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#mailedFour").hide();
					jQuery("#mailedFive").hide();
					jQuery("#mailedSix").hide();
					jQuery("#byoOne").show();
					jQuery("#byoTwo").show();
				}else{
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#mailedFour").hide();
					jQuery("#mailedFive").hide();
					jQuery("#mailedSix").hide();
					jQuery("#byoOne").hide();
					jQuery("#byoTwo").hide();
				}
			})
		</script>
	</head>
	<body>
		<html:form action="/dagl_dazcjg" method="post" styleId="dazcjgForm">
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>学号</th>
						<td>${jbxx.xh}</td>
						<th>年级</th>
						<td>${jbxx.nj}</td>
					</tr>
					<tr>
						<th>姓名</th>
						<td>${jbxx.xm}</td>
						<th>学院</th>
						<td>${jbxx.xymc}</td>
					</tr>
					<tr>
						<th>性别</th>
						<td>${jbxx.xb}</td>
						<th>专业</th>
						<td>${jbxx.zymc}</td>
					</tr>
					<tr>
						<th>身份证号</th>
						<td>${jbxx.sfzh}</td>
						<th>班级</th>
						<td>${jbxx.bjmc}</td>
					</tr>
					<logic:equal value="1" name="countRs">
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
							<td colspan="3">${rs.zcfsmc}</td>
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
						
						<tr id="mailedFour">
							<th>邮寄状态</th>
							<td colspan="3">${rs.yjztmc}</td>
						</tr>
						
						<tr id="mailedFive">
							<th>快递方式</th>
							<td>${rs.kdfs}</td>
							<th>快递单号</th>
							<td>${rs.kddh}</td>
						</tr>
						
						<tr id="mailedSix">
							<th>邮寄时间</th>
							<td colspan="3">${rs.yjsj}</td>
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
						
						<tr id="byoTwo">
							<th>实际提档日期</th>
							<td>${rs.sjtdrq}</td>
							<th>实际提档人</th>
							<td>${rs.sjtdr}</td>
						</tr>
					</logic:equal>
					<logic:equal value="0" name="countRs">
							<tr>
								<th colspan="4" style="text-align:center;">
									<span style="color: red;"><b>此学生未登记</b></span>
								</th>
							</tr>
					</logic:equal>
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