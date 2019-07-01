<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/byhkgl/jg/js/byhkglJg.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
	
				var sfzq = jQuery("#sfzq").val();
				if (sfzq == "是"){				
					jQuery('.sfzhanqi').css("display","");
				} else {		
					jQuery('.sfzhanqi').css("display","none");
				}
			});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/byhkgl_jg" method="post" styleId="byhkglJgForm" onsubmit="return false;">
		<input type="hidden" name="sfzq" id="sfzq" value="${rs.sfzq}"/>
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
								<span>贷款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;">学年</th>
										<th style="text-align: center;">合同编号</th>
										<th style="text-align: center;">贷款总金额</th>
										<th style="text-align: center;">累计放款金额</th>
										<th style="text-align: center;">贷款期限</th>
									</tr>
									<logic:iterate id="dkxx" name="khkList">
										<tr>
											<td>${dkxx.xn }</td>
											<td>${dkxx.htbh }</td>
											<td>${dkxx.dkje }</td>
											<td>${dkxx.fkze }</td>
											<td>${dkxx.dkqx }</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>还款信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								还款金额（元）
							</th>
							<td width="30%">
								${rs.hkje}
							</td>
							</td>
							<th>是否展期</th>
							<td>
								${rs.sfzq}
							</td>
						</tr>					
						<tr class="sfzhanqi">
						    <th>展期原因</th>
						    <td>
								${zqyymc}
							</td>
							<th>展期期限（月）</th>
						    <td>
								${rs.zqqx}
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
										var gid = "${rs.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
			      		</tr>						
					</tbody>
			  	 </table>		
			  </div>
			  <div style="height:35px"></div>   
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

