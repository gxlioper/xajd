<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/jg/js/xnwxdkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var jmbl = jQuery("#jmbl").text();
			    jmbl = parseInt(jmbl.replace("%",""))/100;
				var yjjmje = (parseInt(jQuery("#zje").text())*jmbl).toFixed(2);
				jQuery("#yjjmje").text(yjjmje);
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xnwxdkjm_sq" method="post" styleId="XnwxdkjmsqModel">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
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
								<span>申请人经济情况（本学年）</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${jg.xn}
							</td>
							<th>学期</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>申请减免率</th>
							<td id = "jmbl">
								${jg.jmbl}
							</td>
							<th>预计减免金额（元）</th>
							<td>
								<label id="zje" style="display:none">${zje}</label>
								<label id="yjjmje"></label>
							</td>
						</tr>
						<tr>
							<th>减免依据</th>
							<td colspan="3">
							    <table  border="0" cellspacing="0" cellpadding="0">
								    <logic:iterate id="jm"  name="jmyjlist">
								      <tr>
								        <td>
								    	    ${jm.rownum}
								        </td>
								        <td>
								        	${jm.jmyj}
								        </td>
								       </tr>
									</logic:iterate>	
									</table>
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								 ${jg.sqly}
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
										var gid = "${jg.filepath}";
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
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
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