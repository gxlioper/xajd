<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsqjs/js/xmsqjs.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					showDialog("选择活动项目",500,350,"rcsw_txhd_xmsq.do?method=getXmsqInfo&xh="+xh);
				}
			});
			
		</script>
	</head>
	<body>
	
		<html:form action="/rcsw_txhd_xmsq" method="post" styleId="TxhdXmsqJsForm" onsubmit="return false;">
		
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									活动项目申请信息&nbsp;&nbsp;
									<a onclick="showXmxz();" 
									   href="javascript:void(0);">
									   <font color="blue">选择活动项目</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow" style="width:100%;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>项目名称 </td>
												<td>活动时间 </td>
												<td>活动地点</td>
											</tr>
										</thead>
										<tbody id="xmInfo" name="se">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>申请学年</th>
							<td>${currXn}</td>
							<th>申请学期</th>
							<td>${currXq}</td>
						</tr>
					</tbody>
					<tbody>
						<th>
							<span class="red">*</span>申请理由
							<br /><font color="red">(限200字)</font>
						</th>
						<td colspan="3">
							<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,200);"/>
						</td>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveXmsq('save');">
										保存草稿
									</button>
									<button type="button" id="save_button" type="button" onclick="saveXmsq('submit');">
										提交申请
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

