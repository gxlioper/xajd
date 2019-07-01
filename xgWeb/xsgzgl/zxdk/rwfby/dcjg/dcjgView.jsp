<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
            jQuery(function(){
                dclxChange(document.getElementById("dclb"));
            })

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
		<html:form action="/rwfby_dcjg" method="post" styleId="rwfbydcjgForm">
			<html:hidden property="id" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
								<span>贷款补偿</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>申请学年</th>
							<td>
								${rwfbydcjgForm.xn}
							</td>
							<th>资助类型</th>
							<td>
								<html:select property="dclb" styleId="dclb" onchange="dclxChange(this);" disabled="true" >
									<html:options collection="dclblist" property="dm" labelProperty="mc" />
								</html:select>
							</td>

						</tr>
						<tr>
							<th>经办银行</th>
							<td>
								${rwfbydcjgForm.yhmc }
							</td>
							<th>贷款合同号</th>
							<td>
								${rwfbydcjgForm.dkhth }
							</td>
						</tr>
						<tr>
						   <th>申请代偿金额(元)</th>
							<td colspan="3">
							  ${rwfbydcjgForm.dcje}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
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
			</table>
			<div style="height:30px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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
		</html:form>
	</body>
</html>

