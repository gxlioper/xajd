<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/xyzs/xyzsjg/js/xyzsjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/gygl_xyzssqgl" method="post" styleId="XyzsSqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:450px;margin-bottom: 0px;" >
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
								<span>校外住宿信息</span>&nbsp;<lable style="line-height:20px">(${zsjgxx.xn}&nbsp;学年)</lable>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>申请起始时间</th>
							<td>
								${zsjgxx.sqsjstart}
							</td>
							<th>申请结束时间</th>
							<td>
								${zsjgxx.sqsjend}
							</td>
						</tr>
						<tr>
							<th>学历</th>
							<td >
								${xlxx.xlmc}
							</td>
							<th>联系电话</th>
							<td>
								${zsjgxx.lxdh}
							</td>
						</tr>
						<tr>
							<th>家长联系方式</th>
							<td>
								${zsjgxx.parentslxdy}
							</td>
						
							<th>校外住宿的详细地址</th>
							<td>
								${zsjgxx.xxdz}
							</td>
						</tr>
						<tr>
							<th id ="yy"  id="yy"></font>在外居住原因</th>
							 <td colspan="3" >${xyjzyy.mc}</td>
						</tr>
						<tr>
							<th id ="yy"  id="yy"></font>备注</th>
							 <td colspan="3" >${zsjgxx.bz}</td>
						</tr>
						<tr>
							<th align="right" width="15%">
								附件
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" styleId="filepath" />
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