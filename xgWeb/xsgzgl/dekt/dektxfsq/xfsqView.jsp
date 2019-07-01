<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
		</script>
	</head>
	<body>

		<html:form action="/dekt_xfsq" method="post"	styleId="dektxfsqForm">
			<div style="height:440px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/lstd/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>认定内容</th>
							<td colspan="3" >
								${model.rdnr}
							</td>
						</tr>
			      		 <tr>
							<th >
							   	参与方式
							</th>
							<td colspan="3" >
								${model.cyfsmc}
							</td>
					    </tr>
			      		 <tr>
							<th>获奖时间</th>
							</th>
							<td colspan="3"> 
								${model.hjsj}
							</td>
			      		</tr>
			      		 <tr>
							<th>申请说明</th>
							</th>
							<td colspan="3"> 
								${model.sqsm}
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="15%">
								附件
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="model" property="filepath" styleId="filepath" />
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
				<table width="100%" border="0" class="formlist">

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
		</html:form>
	</body>
</html>

