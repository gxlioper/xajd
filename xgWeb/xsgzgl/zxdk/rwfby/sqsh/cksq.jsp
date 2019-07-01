<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rwfbysqshForm.id}&tt="+new Date().getTime());
                dclxChange(document.getElementById("dclb"));
			});
            function dclxChange(target){
                if(target.value == "01"){
                    jQuery("#yhxx").hide();
                } else {
                    jQuery("#yhxx").show();
                }
            }
		</script>
	</head>
	<body>
		<html:form action="/rwfby_sqsh" method="post" styleId="rwfbysqshForm">
			<html:hidden property="id"/>
			<html:hidden property="shzt"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:420px;margin-bottom: 0px;" >
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
								<span>贷款补偿</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>申请学年</th>
							<td>
								${rwfbysqshForm.xn}
							</td>
							<th>资助类型</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);" disabled="true">
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr id="yhxx">
							<th>经办银行</th>
							<td>
								${rwfbysqshForm.yhmc }
							</td>
							<th>贷款合同号</th>
							<td>
								${rwfbysqshForm.dkhth }
							</td>
						</tr>
						<tr>
							<th>申请补偿金额(元)</th>
							<td>
							  ${rwfbysqshForm.dcje}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								证明材料
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="filepath"/>
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
				<table class="formlist">
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