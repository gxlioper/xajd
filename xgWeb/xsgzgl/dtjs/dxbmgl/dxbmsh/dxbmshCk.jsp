<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.sqid}&tt="+new Date().getTime());
			});
		</script>
	</head>
	<body>
	<html:form method="post" styleId="form" action="/dtxxsh">
		<%@ include file="/comm/hiddenValue.jsp"%>
		 <html:hidden property="dtxxsqid"/>
		 <html:hidden property="splc" styleId="splc"/>
		 <html:hidden property="shid" styleId="shid"/>
		 <html:hidden property="shzt" styleId="shzt"/>
		<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>党团信息申请</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							申请阶段名称
						</th>
						<td align="left" colspan="3" id="jdmc">
							${data.jdmc}
						</td>
						<th hidden align="right" width="10%" name="rdzys">
							入党志愿书编号
						</th>
						<td hidden align="left"  name="rdzys">
							${data.zd3}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							个人小结&nbsp;
							<br />
						</th>
						<td colspan="3">
							${data.grxj}
						</td>
					</tr>
					<logic:equal name="xxdm" value="11527">
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${data.zd5}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
					</logic:equal>
				</tbody>
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
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
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
