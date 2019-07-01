<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/jg/js/xnwxdkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xnwxdk_sq" method="post" styleId="XnwxdkSqModel">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>申请人经济情况（本学年）</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${jg.xn}
							</td>
							<th>学期</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>家庭提供（元）</th>
							<td>
								${jg.jttg}
							</td>
							<th>助学金（元）</th>
							<td>
								${jg.zxj}
							</td>
						</tr>
						<tr>
							<th>奖学金（元）</th>
							<td>
							    ${jg.jxj}
							</td>
							<th>勤工助学收入（元）</th>
							<td>
							    ${jg.qgzxsr}
							</td>
						</tr>
						<tr>
							<th>校内无息借款（元）  </th>
							<td>
							    ${jg.xnwxjk}
							</td>
							<th>其他收入（元） </th>
							<td>
							     ${jg.qtsr}
							</td>
						</tr>
						<tr>
							<th>助学贷款金额（元）</th>
							<td>
							     ${jg.zxdksqje}
							</td>
							<th>助学贷款时间 </th>
							<td>
							    ${jg.zxdksqsj}
							</td>
						</tr>
						<tr>
							<th>发放金额（元）</th>
							<td>
							    ${jg.ffje}
							</td>
							<th>发放时间 </th>
							<td>
							    ${jg.ffsj}
							</td>
						</tr>
						<tr>
							<th>本次申请金额（元）</th>
							<td>
							     ${jg.sqje}
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								 ${jg.sqly}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${jg.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>				
					</tbody>
				</table>
				</div>
				<div style="height:30px"></div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
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