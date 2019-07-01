<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/dkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				onShow("zxdk_query");
			});
		</script>
	</head>
	<body>
		<html:form action="/zxdkXyddk" method="post" styleId="xyddkForm">
			<html:hidden property="id" styleId="id"/>
		
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>贷款申请</span>
							</th>
						</tr>
					</thead>
				<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				</table>
				
					<div class="tab"  id="content" ></div>
					<table width="100%" border="0" class="formlist" style="position: relative;top: -6px;">
						<tr >
							<th width="15%">申请时间</th>
							<td width="85%" colspan="3">
								${zxdkXyddkForm.sqsj }
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
										var gid = "${zxdkXyddkForm.filepath}";
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
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>放贷记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">放贷年份</th>
										<th style="text-align: center;">放贷时间</th>
										<th style="text-align: center;">放贷金额</th>
									</tr>
									<logic:iterate id="f" name="fdxxList">
										<tr>
											<td>${f.htbh }</td>
											<td>${f.fdnf }</td>
											<td>${f.fksj }</td>
											<td>${f.fkje }</td>
										</tr>
									</logic:iterate>
									<logic:empty name="fdxxList">
										<tr>
											<td colspan="5">
												无放贷记录！
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<div style="height: 30px">
				</div>
				<table class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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