<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var chkstr = "start-end-jfjzrq";
				if(!checkNotNull(chkstr)){
					 return showAlert("请将带<font class='red'>*</font>的项目填写完整！");
				}
				var url = "cxdd_pyjg.do?method=saveKxxx";
				ajaxSubFormWithFun("CxddJgForm", url, function(data) {
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
		<html:form action="/cxdd_pyjg" method="post" styleId="CxddJgForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>开学信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:20%"><font color="red">*</font>开学日期</th>
							<td style="width:30%">
								<html:text property="kxrq" name="data" style="width:80%" styleId="start" onclick="return showCalendar('start','y-mm-dd',true,'end');"  maxlength="10" ></html:text>
							</td>
							<th style="width:20%"><font color="red">*</font>开课日期</th>
							<td style="width:80%">
								<html:text property="kkrq" name="data" style="width:80%" styleId="end" maxlength="10" onclick="return showCalendar('end','y-mm-dd',false,'start');" ></html:text>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>缴费截止日期</th>
							<td colspan="3">
								<html:text property="jfjzrq" name="data" style="width:80%" styleId="jfjzrq" maxlength="10" onclick="return showCalendar('jfjzrq','y-mm-dd');" ></html:text>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保    存
									</button>
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