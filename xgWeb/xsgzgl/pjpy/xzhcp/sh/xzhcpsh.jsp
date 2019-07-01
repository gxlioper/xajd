<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/pjpy/xzhcp/sq/js/xzhcp.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${ZhcpShForm.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${ZhcpShForm.splc}&shid=${ZhcpShForm.shid}");
			});
			function saveSh(){
				var shzt = jQuery("#shjg").val();
				if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
					showAlert("请将必填项填写完整！");
					return false;
				}
				var url = "xzhcp_zcsh.do?method=saveDgsh";
				ajaxSubFormWithFun("ZhcpShForm",url,function(data){
					 if(data["message"]=="保存成功！"){
			    		 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
			    	 }else{
			    		 showAlert(data["message"]);
			    		}
					});
			}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xzhcp_zcsh" method="post" styleId="ZhcpShForm">
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="splc" styleId="splc"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>综测信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:notEqual value="10364" name="xxdm">
								<th>学年</th>
								<td>${xn}</td>
								<th>学期</th>
								<td>${xqmc}</td>
							</logic:notEqual>
							<logic:equal value="10364" name="xxdm">
								<th>学年</th>
								<td colspan="3">${xn}</td>
							</logic:equal>
						</tr>
						<tr>
							<th>
								附件
							</th>
							<td colspan="3">
								<html:hidden property="fjpath" styleId="fjpath"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											var gid = jQuery('#fjpath').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
								</script> 
							</td>
						</tr>
						<tr>
							<th>个人测评
							<td colspan="3">
								${ZhcpShForm.grpc}
							</td>
						</tr>
					
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
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
					<th >
						审核结果
					</th>
					<td id="shjgSpan">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xzhcp&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
				</div>	
				<div style="height:50px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="保存"  onclick="saveSh();return false;">
										保 存
									</button>
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