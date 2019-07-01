<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="gygl/sspy/pyjg/js/sspyjg.js"></script>
		<script>
			jQuery(function() {
				jQuery("#xsList").empty();
				var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
				var html = "";
				jQuery.post("sspysq.do?method=getCwxx", {
					ldqsxx : ldqsxx
				}, function(data) {
					for(var i =0;i<data.length;i++){
						html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
					}
					jQuery("#xsList").append(html);
				}, 'json');
			});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="sspyjgForm" action="/sspyjg">
		<input type="hidden" id="guid" name="guid" value="${rs.guid }"/>
		<input type="hidden" id="lddm" name="lddm" value="${rs.lddm}"/>
		<input type="hidden" id="qsh" name="qsh" value="${rs.qsh}"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>楼栋寝室信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							楼栋
						</th>
						<td>
							${rs.ldmc}
						</td>
						<th>寝室号</th>
						<td>
							${rs.qsh}
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>宿舍成员</span>
						</th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th  width="20%">
							<div align="center" >学号</div>
						</th>
						<th>
							<div align="center" >姓名</div>
						</th>
						<th>
							<div align="center" >班级</div>
						</th>
						<th>
							<div align="center" >入住床位</div>
						</th>
					</tr>
				</thead>
				<tbody id="xsList">
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>申请评优项目</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>学年</th>
						<td>${rs.xn}</td>
						<th>学期</th>
						<td>${rs.xqmc}</td>
					</tr>
					<tr>
						<th>评优项目</th>
						<td>${rs.pyxmmc}</td>
					</tr>
					<tr>
						<th>申请理由&nbsp;</th>
						<td colspan="3">${rs.sqly}</td>
					</tr>
					<tr>
						<th>备注&nbsp;</th>
						<td colspan="3">${rs.bz}</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden name="rs" property="filepath" styleId="filepath"/>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
									jQuery.MultiUploader_q({
										gid : gid
									});
								});
							</script>
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