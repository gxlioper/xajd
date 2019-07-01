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
		<script type="text/javascript" src="xljkwzdx/xlzxnew/ybgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" defer="defer">

			function saveAction(type){
				var checkids = "xydm-xn-yf-ztqk";
				if("${xxdm}" == "10704"){
					var sfywt;
					var radio = document.getElementsByName("sfywt");  
				    for (i=0; i<radio.length; i++) {  
				        if (radio[i].checked) {  
				          sfywt=radio[i].value;  
				        }  
				    }  
				    if(sfywt=="是"){
				    	if(!checkNotNull(checkids)){
							showAlert("请将带<font color='red'>*</font>的项目填写完整!");
							return false;
						}
						if(jQuery("[name='xh']").length == 0){
							showAlert("请至少填写一位学生的心理状况!");
							return false;
						}
				    }else{
				    	var checkid = "xydm-xn-yf";
				    	if(!checkNotNull(checkid)){
							showAlert("请将带<font color='red'>*</font>的项目填写完整!");
							return false;
						}
				    }
				}else{
					if(!checkNotNull(checkids)){
							showAlert("请将带<font color='red'>*</font>的项目填写完整!");
							return false;
					}
					if(jQuery("[name='xh']").length == 0){
							showAlert("请至少填写一位学生的心理状况!");
							return false;
					}
				}
				var message = null;
				jQuery("[name='deltr']").each(function(){
					/*
					if(jQuery.trim(this.value).length == 0){
						message = "主要问题描述不可为空！";
						return false;
					}else if(this.value.length > 500){
						message = "主要问题描述限500字！";
						return false;
					}	*/
					if("${xxdm}" != "10704"){
						if(jQuery.trim(jQuery(this).find("[name='ybwtms']").val()).length == 0){
							message = "情况说明不可为空！";
						}else if(jQuery(this).find("[name='ybwtms']").val().length > 500){
							message = "情况说明限500字！";
							return false;
						}else if(jQuery.trim(jQuery(this).find("[name='ybgycs']").val()).length == 0){
							message = "干预措施不可为空！";
						}else if(jQuery(this).find("[name='ybgycs']").val().length > 500){
							message = "干预措施限500字！";
							return false;
						}else if(jQuery.trim(jQuery(this).find("[name='ybgyhjg']").val()).length == 0){
							message = "干预后效果不可为空！";
						}else if(jQuery(this).find("[name='ybgyhjg']").val().length > 500){
							message = "干预后效果500字！";
							return false;
						}	
					}else{
						if(jQuery.trim(jQuery(this).find("[name='wtfsrq']").val()).length == 0){
							message = "发生日期不可为空！";
						}else if(jQuery.trim(jQuery(this).find("[name='ybwtms']").val()).length == 0){
							message = "危机程度不可为空！";
						}else if(jQuery.trim(jQuery(this).find("[name='ybgyhjg']").val()).length == 0){
							message = "情况进程不可为空！";
						}else if(jQuery(this).find("[name='ybgyhjg']").val().length > 500){
							message = "情况进程限500字！";
							return false;
						}	
					}
				})
				if(message){
					showAlert(message);
					return false;
				}
				var url = "xlzxnew_ybsb.do?method=saveYbsb&saveFlag="+type;
				ajaxSubFormWithFun("YbsbForm",url,function(data){
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
			
			function show(obj){
				if(obj.value=="否"){
					jQuery(".h").hide();
				}else{
					jQuery(".h").show();	
				}
			
			}
		</script>
	</head>
	<body>
		<html:form action="/xlzxnew_ybsb" method="post" styleId="YbsbForm">
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
								<th  width="15%">
									学院
								</th>
								<td  width="35%">
									${xymc}
									<html:hidden property="xydm"  styleId="xydm"/>
								</td>
								<th  width="15%">
									填写人
								</th>
								<td  width="35%">
									${txr}
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>学年
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:150px;">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th>
									<span class="red">*</span>月度
								</th>
								<td>
									<select name="yf" id="yf" style="width:150px;">
										<option></option>
										<option value="01">1月</option>
										<option value="02">2月</option>
										<option value="03">3月</option>
										<option value="04">4月</option>
										<option value="05">5月</option>
										<option value="06">6月</option>
										<option value="07">7月</option>
										<option value="08">8月</option>
										<option value="09">9月</option>
										<option value="10">10月</option>
										<option value="11">11月</option>
										<option value="12">12月</option>
									</select>
								</td>
							</tr>
							<logic:equal value="10704" name="xxdm">
							<tr>
								<th  width="15%">
									<span class="red">*</span>是否有问题
								</th>
								<td >
									<input type="radio" name="sfywt" value="是" checked="checked" onclick="show(this)"/>是
									<input type="radio" name="sfywt" value="否" onclick="show(this)"/>否
								</td>
							</tr>
							</logic:equal>
						<tr class="h">
							<th>
								<span class="red">*</span>
								学院整体情况及<br />
								重大事件<br />
								<font color="red">(限1000字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="ztqk" styleId="ztqk" style="width:95%;" rows="5" onblur="checkLen(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr class="h">
							<th colspan="4">
								<span>个别情况描述</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="h">
							<th colspan="4"> <!-- style="margin-top:2px;margin-left:30px"  -->
								<button type="button" onclick="addRowDialog();return false;" style="float:left">增加</button>
									<button type="button" onclick="delRow();return false;" style="float:left">删除</button>
							</th>
						</tr>
						<tr class="h">
							<th colspan="7">
								<table width="100%" >
									<thead>
									<logic:equal value="10704" name="xxdm">
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='15%' style="text-align:center">学号</th>
											<th width='10%' style="text-align:center">姓名</th>
											<th width='18%' style="text-align:center">班级</th>
											<th width='10%' style="text-align:center">联系方式</th>
											<th width='12%' style="text-align:center"><font class="red">*</font>发生日期</th>
											<th width='10%' style="text-align:center"><font class="red">*</font>危机程度</th>
											<th width='20%' style="text-align:center"><font class="red">*</font>情况进程(限500字)</th>
										</tr>
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										<tr>
											<th width='5%'><input name='chkAll' onclick="selectAll(this)" type="checkbox" /></th>
											<th width='15%' style="text-align:center">学号</th>
											<th width='10%' style="text-align:center">姓名</th>
											<th width='10%' style="text-align:center">班级</th>
											<th width='20%' style="text-align:center"><font class="red">*</font>情况说明(限500字)</th>
											<th width='20%' style="text-align:center"><font class="red">*</font>干预措施(限500字)</th>
											<th width='25%' style="text-align:center"><font class="red">*</font>干预后效果(限500字)</th>
										</tr>
									</logic:notEqual>
									</thead>
									<tbody id="tablebody">
									
									</tbody>
								</table>
							</th>
						</tr>
						<logic:equal value="10704" name="xxdm">
						<tr>
							<th>附件上传</th>
							<td colspan="3">
								<html:hidden property="filepath" styleId="filepath" />
								<input type="file" id="filepath_f" name="filepath" />
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										jQuery('#filepath_f').multiUploader({
											maxcount : 3,
											//后缀
											accept : 'png|gif|jpg|zip|rar|doc|docx',
											//最大文件大小 单位M
											maxsize: 10,
											//存放附件的隐藏域的id
											elementid : 'filepath',
			
											eid : 'filepath_f'
										});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
					</tbody>
				</table>
			</div>
			<div style="height:40px;"></div>
			<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="saveAction('save');">
										保存草稿
									</button>
									<button id="submit_button" type="button"  onclick="saveAction('submit');">
										提交上报
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

