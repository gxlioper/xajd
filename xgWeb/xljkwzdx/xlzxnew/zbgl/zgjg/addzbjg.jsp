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
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zbgl/comm/js/comm.js"></script>
		<script type="text/javascript" defer="defer">

			function saveAction(type){
					
				var checkids = "xn-xq-sbzbid-ztqk";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}
				
				if(jQuery("[name='xh']").length == 0){
					showAlert("请至少填写一位学生的心理状况!");
					return false;
				}
				var message = null;
				jQuery("[name='zbwtms']").each(function(){
					if(jQuery.trim(this.value).length == 0){
						message = "主要问题描述不可为空！";
						return false;
					}else if(this.value.length > 500){
						message = "主要问题描述限500字！";
						return false;
					}	
				})
				if(message){
					showAlert(message);
					return false;
				}
				var url = "xlzxnew_zbjg.do?method=saveSbjg";
				ajaxSubFormWithFun("ZbjgForm",url,function(data){
					if(data["message"] == "保存成功！"){
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
			function selectbj(obj){
				if(obj.value != jQuery("#bjdm").val()){
					jQuery("#tablebody").empty();
				}
				jQuery("#bjdm").val(obj.value);
			}
			function on_change(){
				var xn = jQuery("#xn").val();
				var xq = jQuery("#xq").val();
				jQuery.post("xlzxnew_zbjg.do?method=getZcList",{xn:xn,xq:xq},function(data){
					var html = "<option value=''></option>";
					  if(data && data.length > 0){
						  for(var i = 0; i < data.length; i++){
							html += '<option value='+data[i].zbid+'>'+data[i].zbzc+'</option>';
						  }
					  }else{
						  jQuery("#zbqzrq").html("");
					  }
					jQuery("#sbzbid").html(html);
				  },'json');
			}

			function on_changeZc(){
				var sbzbid = jQuery("#sbzbid").val();
				jQuery.post("xlzxnew_zbjg.do?method=getQzrq",{sbzbid:sbzbid},function(data){
					var html = "";
					  if(data && data.length > 0){	 
							html += data[0].zbqzrq;		  
					  }
					jQuery("#zbqzrq").html(html);
				  },'json');
			}
			jQuery(function(){
				on_change();
			})
		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_zbjg" method="post" styleId="ZbjgForm">
			<input type="hidden" id="bjdm" name="bjdm"/>
			<%--<html:hidden property="splcid" value="${sbxx.splcid}"/>
			<html:hidden property="splcidpz" value="${sbxx.splcidpz}"/>
			--%><div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
								<tr>
								<th>
									<font color="red">* </font>学年
								</th>
								<td>
									<html:select property="xn" styleId="xn" onchange="on_change()" style="width:150px;">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									<span class="red">*</span>学期
								</th>
								<td>
									<html:select property="xq" styleId="xq" onchange="on_change()" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>周次
								</th>
								<td>
									<select name="sbzbid" id="sbzbid" style="width: 150px;" onchange="on_changeZc()">
									</select>
								</td>
								<th>
									起止日期
								</th>
								<td>
									<span name="zbqzrq" id="zbqzrq">
									</span>
								</td>
							</tr>
							<tr>
								<th>班级选择</th>
								<td colspan="4">
									<logic:iterate name="bjList" id="i">
									<input value="${i.bjdm}" onclick="selectbj(this)" type="radio" name="bj"/>班级:${i.bjmc}&nbsp;&nbsp;班主任:${i.xm}<br/>
									</logic:iterate>
								</td>
							</tr>
						<tr>
							<th>
								<span class="red">*</span>
								本周内班级发生的<br />
								重大事件(1000字)<br />
								<font color="red">(限1000字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="ztqk" styleId="ztqk" style="width:95%;" rows="5" onblur="checkLen(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>个别情况描述</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th colspan="4"> <!-- style="margin-top:2px;margin-left:30px"  -->
								<button type="button" onclick="addRowDialog();return false;" style="float:left">增加</button>
									<button type="button" onclick="delRow();return false;" style="float:left">删除</button>
							</th>
						</tr>
						<tr>
							<th colspan="5">
								<table width="100%" >
									<thead>
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='20%' style="text-align:center">学号</th>
											<th width='20%' style="text-align:center">姓名</th>
											<th width='15%' style="text-align:center">性别</th>
											<th width='40%' style="text-align:center"><font class="red">*</font>主要问题描述(限500字)</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="saveAction('save');">
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

