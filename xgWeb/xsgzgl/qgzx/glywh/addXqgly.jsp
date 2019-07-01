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
			function selTea(){
				showDialog("选择管理员", 770, 520, "stglStsq.do?method=getTea")	
			}
			
			function savegly(){
				var ids = "stfzr"+"-"+"xq";
				if(!checkNotNull(ids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整");
					return false;
				}
				var url = "glygl_xqgly.do?method=saveXq";
				ajaxSubFormWithFun("XqglyForm", url, function(data) {
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
		<html:form action="/glygl_xqgly" method="post" styleId="XqglyForm" onsubmit="return false;">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>填写信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%"><font color="red">*</font>管理员</th>
							<td  width="35%">
									<input type="text" name="stfzrxm" style="width:60%;" id="stfzrxm" readonly="true" maxlength="10"/>
									<html:hidden property="zgh" styleId="stfzr" />
									<button class="btn_01" onclick="selTea()"  type="button">选择</button>
																
							</td>
							<th  width="15%"><font color="red">*</font>校区</th>
							<td width="35%">
								<html:select property="xq" styleId="xq" style="width:80%">
									<html:options collection="xqList" property="dm" labelProperty="xqmc"/>
								</html:select>
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
									<button type="button" onclick="savegly();">
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