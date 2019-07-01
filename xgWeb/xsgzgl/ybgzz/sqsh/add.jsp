<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveForm(url){
				if (jQuery("#xh").val() == "" || jQuery("#sqjrsj").val() == ""){
					showAlert("请将必填项填写完整。");
					return false;
				}
				
				ajaxSubFormWithFun("jdqkForm",url,function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
			
			jQuery(function(){
				var isExists = jQuery("#isExists").val();
				var isSqExists = jQuery("#isSqExists").val();
				
				if (isExists == "true"){
					showAlert("您已经是易班工作站成员。");
				} else if (isSqExists == "true") {
					showAlert("您已有申请记录，无需重复申请。");
				}
			});
		</script>
	</head>
	<body>
		<html:form action="/ybgzzSqsh" method="post" styleId="jdqkForm">
			<input type="hidden" name="splcid" value="${cssz.splc }"/>
			<input type="hidden" id="isExists" value="${isExists }"/>
			<input type="hidden" id="isSqExists" value="${isSqExists }"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>易班工作站成员申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>申请加入日期
							</th>
							<td colspan="3">
								<html:text  property="sqjrsj" styleId="sqjrsj"
										onfocus="showCalendar('sqjrsj','y-mm-dd');" 
										readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								申请理由<br/>
								<font color="red">(限400字)</font>
							</th>
							<td colspan="3">
								<html:textarea  property="sqly" rows="4" style="word-break:break-all;width:99%" styleId="sqly" 
									onblur="chLeng(this,400);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:notEqual value="true" name="isExists">
										<logic:notEqual value="true" name="isSqExists">
											<button type="button" onclick="saveForm('ybgzzSqsh.do?method=save');">
												保存草稿
											</button>
											<button type="button" onclick="saveForm('ybgzzSqsh.do?method=saveAndSubmit');">
												提交申请
											</button>
										</logic:notEqual>
									</logic:notEqual>
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

