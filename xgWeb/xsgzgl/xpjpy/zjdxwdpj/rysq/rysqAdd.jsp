<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q_href.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/rysq/js/rysq.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();
				var xh = jQuery("#xh").val();
				if (xh != ""){
					showDialog("选择申请奖项",500,400,"xpjpy_rysq.do?method=selectRyxm&xh="+xh);
				}
			});

		</script>
	</head>
	<body>
		<html:form action="/xpjpy_rysq" method="post" styleId="rysqForm" onsubmit="return false;">
			<html:hidden property="id" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/zjdxwdpj/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									申请荣誉
									<a onclick="showDialog('选择申请荣誉',500,400,'xpjpy_rysq.do?method=selectRyxm&xh=${jbxx.xh}');" href="javascript:void(0);">
									   <font color="blue"><u>选择申请荣誉</u></font>
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>评奖周期</th>
							<td colspan="3">${cssz.xn}</td>
						</tr>
						<tr>
							<th>荣誉</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td width="20%">荣誉名称</td>
											<td width="10%">金额</td>
											<td width="25%">申请表</td>
											<td width="45%">申请理由填写说明</td>
										</tr>
									</thead>
									<tbody id="sqjx"></tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:100%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
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
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">

								</script>
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveRysq('save');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveRysq('submit');return false;">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
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