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
		
	</head>
	<body>
		<html:form action="/tyxs_zzjg" method="post" styleId="tyxsZzjgForm">
			<html:hidden property="id" styleId="id"/>
		
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
				<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>资助信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<logic:equal name="xxdm" value="10511">
							<tr>
								<th>贷款类型</th>
					    		<td>
					    			${tyxsZzjgForm.dklx }
					    		</td>
							</tr>
							</logic:equal>
							
						<tr>
							<th>申请学年</th>
							<td>
								${tyxsZzjgForm.xn }
							</td>
							<th>申请时间</th>
							<td>
								${tyxsZzjgForm.sqsj }
							</td>
						</tr>
						<logic:equal name="xxdm" value="10511">
						<tr>
							<th>贷款金额(元)</th>
							<td>
								${tyxsZzjgForm.dkbj }
							</td>
							<th>经办银行</th>
							<td>
							   ${yhmc}
							</td>
						</tr>
						<tr>
							<th>贷款合同号</th>
							<td>
								${tyxsZzjgForm.dkhth}
							</td>
							<th>贷款起止时间</th>
							<td>
							    ${tyxsZzjgForm.dkkssj}
							    &nbsp;
								至
								&nbsp;
								 ${tyxsZzjgForm.dkjssj}
							</td>
						</tr>
							</logic:equal>
						<tr>
							<th>申请总金额（元）</th>
							<td>
								${tyxsZzjgForm.sqxfzj }
							</td>
							<%--<th>资助总金额（元）</th>
							<td>
								${tyxsZzjgForm.zzzje }
							</td>--%>
						</tr>
						
						<tr>
							<th height="49px">申请理由</th>
							<td colspan="3" width="100%">
								${tyxsZzjgForm.sqly }
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10511">
							<tr>
								<th align="right" width="10%">
									附件信息
								</th>
								<td colspan="3">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
									<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = "${tyxsZzjgForm.filepath}";
											jQuery.MultiUploader_q({
												gid : gid,
												targetEl : 'commonfileupload-list-0'
											});
										});
									</script>
								</td>
							</tr>					
						</logic:notEqual>			
					</tbody>
					</tbody>
				</table>
				
			</div>
			<div class="tab"  id="content" style="margin-top: 5px; overflow-x:hidden;" ></div>
			</div>
			<div style="height:25px;"></div>
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