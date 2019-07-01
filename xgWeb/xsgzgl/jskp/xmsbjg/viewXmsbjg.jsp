<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/jskpXmjg" method="post" styleId="jskpXmsbjgForm">
		<input type="hidden" value="${sfsh}" name="sfsh" id="sfsh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal value="1" name="sfsh">
							<tr>
								<th>申报项目</th>
								<td>
									${rs.xmmc}
								</td>
								<th >
								评分
								</th>
								<td>
									${rs.fs}
								</td>
							</tr>
							<tr>
								<th>获奖时间</th>
								<td colspan="3">
									${rs.hjsj}
								</td>
							</tr>
							<tr>
								<th>申报理由</th>
								<td colspan="3">
								${rs.sbly}
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="0" name="sfsh">
							<tr>
								<th>申报项目</th>
								<td>${rs.xmmc}</td>
								<th >评分</th>
								<td>${rs.fs}</td>
								<tr>
								<th>申报理由</th>
								<td colspan="3">${rs.lxly}</td>
							</tr>
							</tr>
						</logic:equal>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="fjid" styleId="fjid"/>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
										});
									});
								</script>
							</td>
						</tr>
					</tbody>
					<!-- 得分汇总不让学生查看 -->
					<logic:notEqual value="stu" name="userType">
						<thead>
							<tr>
								<th colspan="4">
									<span>得分汇总</span>
								</th>
							</tr>
						</thead>
						<table style="margin-bottom: 5px" width="100%" border="0" class="formlist" id="tab_rcxw">
							<tbody>
								<tr>
									<th><div align="center">学年</div></th>
									<th><div align="center">项目类别</div></th>
									<th><div align="center">总分</div></th>
								</tr>
								<logic:iterate name="singleSummary" id="s">
									<tr>
										<td>
											<div align="center"><bean:write name="s" property="xn"/></div>
										</td>
										<td>
											<div align="center"><bean:write name="s" property="xmlbmc"/></div>
										</td>
										<td>
											<div align="center"><bean:write name="s" property="fs"/></div>
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</logic:notEqual>
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