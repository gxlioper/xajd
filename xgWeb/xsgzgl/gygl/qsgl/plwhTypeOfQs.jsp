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
		function savePlwh(shzt){
			if (!checkNotNull("cws-sfbz")){
				showAlert("请将带<font class='red'>*</font> 的项目填写完整！");
				return false;
			}
			if(parseInt(jQuery("#cws").val()) == 0){
				showAlert("床位数必须大于0！");
				return false;
			}
			ajaxSubFormWithFun("gyglnewQsglForm", "gyglnew_qsgl.do?method=saveplwhTypeOfQs", function(data) {
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
	</head>
	<body>
		<html:form action="/gyglnew_qsgl" method="post" styleId="gyglnewQsglForm">
		<logic:iterate id="i" name="primaryKeyArray" >
			<input type="hidden" name="primarykey_checkVal" value="${i}"/>
		</logic:iterate>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>批量寝室信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%"><font color="red">*</font>床位数</th>
							<td width="25%">
								<input type="text" name="cws" maxlength="2" value="0" onkeyup="checkInputData(this);" id="cws" class="text_nor">
							</td>
							<th width="25%"><font color="red">*</font>收费标准</th>
							<td width="25%">
								<input type="text" name="sfbz" maxlength="5"  onkeyup="checkInputData(this);" id="sfbz" class="text_nor">
							</td>
						</tr>
						<tr>
							<th width="25%">有无空调</th>
							<td width="25%">
								<input type="radio" name="ywkt" value="无" id="ywkt">无
								<input type="radio" name="ywkt" value="有" checked="checked" id="ywkt">有
							</td>
							<logic:equal name="xxdm" value="11799">
							<th width="25%">寝室性别</th>
								<td width="25%">
									<select id="qsxb" name="qsxb">
	   								<option value="男">男</option>
	   								<option value="女">女</option>
									</select>
								</td>
							</logic:equal>
							<logic:notEqual name="xxdm" value="11799">
								<th width="25%"></th>
								<td width="25%"></td>
							</logic:notEqual>
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
									<button type="button" onclick="savePlwh();">
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