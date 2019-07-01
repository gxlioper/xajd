<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
		function saveForm(shzt){
			var xxdm = jQuery("#xxdm").val();	
			var checkData = "ydlbdm";
	
			if(!jQuery("#tzbj").is(":hidden")){
				checkData = "ydlbdm-nj-xydm-zydm";
			}		

			if(!jQuery("#lrqzsj").is(":hidden")){
				checkData += "-sqkssj-sqjssj";
			}

			if("10511" == xxdm) {
				if(!jQuery("#xzpd").is(":hidden")){
					checkData += "-xz";
				}
				if(!jQuery("#xxpd").is(":hidden")){
					checkData += "-lyqxxxdm";
				}
				checkData += "-ydyydm";
			}
			
			if(!check(checkData)){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
			  
		     var url = "xjydsq.do?method=xjydsqUpd&type=update&shzt="+shzt;

		     // 原审核状态
		     var shztGet = jQuery("#shzt").val();
		     // 提交申请
			if("5" == shzt){
				confirmInfo("您确定提交吗?",function(ty){
					if(ty=="ok"){
						// 原审核状态为退回,且未变更异动类型，则不需要验证是否可提交，直接提交
						if(shztGet == '3'&& jQuery("#ydlbdm").val() == jQuery("#ydlbdmOld").val()){
							// 是否已退回：1[退回]
							url += "&sfyth=1";
							ajaxSubFormWithFun("xjydsqForm",url,function(data){
					    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
					    		 showAlert(data["message"],{},{"clkFun":function(){
					    				if (parent.window){
					    					refershParent();
					    				}
					    			}});
					    	 }else{
					    		 showAlert(data["message"]);
					    	 }
							});
						}else{
							// 验证是否可提交
							jQuery.post("xjydsq.do?method=checkSfktj", {
								ydlbdm:jQuery("#ydlbdm").val()
							}, function(data) {
								if(data ==null || data=="false"){
									showAlertDivLayer("您申请的异动已关闭申请，无法提交，详情请联系管理员。");
									return false;
								}else{
									ajaxSubFormWithFun("xjydsqForm",url,function(data){
							    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
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
							});
						}
					}
				 });
				 
			// 保存草稿
			}else{
				// 原审核状态为退回
				if(shztGet == '3'&& jQuery("#ydlbdm").val() == jQuery("#ydlbdmOld").val()){
					// 是否已退回：1[退回]
					url += "&sfyth=1";

				}
			      ajaxSubFormWithFun("xjydsqForm",url,function(data){
				    	 if(data["message"]=="保存成功！" || data["message"]=="提交成功！"){
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
		  }

		function initShow(){
			var xxdm = jQuery("#xxdm").val();
			var xjlbdm = jQuery("#ydlbdm").val();
			if(xjlbdm == ""){
				jQuery("#xjlbmc").html("");
				jQuery("#sfyxj").html("");
				jQuery("#sfzx").html("");
				jQuery("#tzbj").hide();
				jQuery("#lrqzsj").hide();
				jQuery("#xzpd").hide();
				jQuery("#xxpd").hide();
			}else{
				jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
					if(data != null){
						jQuery("#xjlbmc").html(data["xjlbmc"]);
						jQuery("#sfyxj").html(data["sfyxjmc"]);
						jQuery("#sfzx").html(data["sfzxmc"]);

						if(data["lrqzsj"]=='1'){
							jQuery("#lrqzsj").show();
						}else{
							jQuery("#lrqzsj").hide();
							jQuery("#sqkssj").val("");
							jQuery("#sqjssj").val("");
							
						}
						
						if(data["sftjbj"]=='0'){
							jQuery("#tzbj").show();
						}else{
							jQuery("#tzbj").hide();
							jQuery("#nj").val("");
							jQuery("#xydm").val("");
							jQuery("#zydm").val("");
							jQuery("#bjdm").val("");
						}

						if("10511" == xxdm) {
							if(data["xzsfkq"]=='1'){
								jQuery("#xzpd").show();
							}else{
								jQuery("#xzpd").hide();
								jQuery("#xz").val("");
							}
							if(data["xxsfkq"]=='1') {
								jQuery("#xxpd").show();
							}else{
								jQuery("#xxpd").hide();
								jQuery("#lyqxxxdm").val("");				
							}
						}
						
					}
				},'json');
			}
		}
		jQuery(function(){
			initShow();
			if(jQuery("#shzt").val()=='3'){
				jQuery("#ydlbdm").attr("disabled","disabled");
			}
			onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');
		});
		
		</script>
	</head>
	<body>
		<html:form action="/xjydsq" method="post" styleId="xjydsqForm" onsubmit="return false;">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xjydsqid"/>
			<input type="hidden" id="ydlbdmOld" value="${xjydsqForm.ydlbdm}" />
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="splcid" styleId="splcid"/>
			<html:hidden property="xh"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>学籍异动申请信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>学籍异动类别
						</th>
						<td align="left">
							<html:select property="ydlbdm" styleId="ydlbdm" 
							 	onchange="initShow();">
								<html:option value=""></html:option>
								<html:options collection="xjlbList" property="xjlbdm"
									labelProperty="xjlbmc" />
							</html:select>
						</td>
						<th align="right">
							学年/学期
						</th>
						<td align="left">
							${dqxn} ${dqxq}
						</td>
					</tr>
					<tr>
						<th>学籍状态[异动]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:100px">原学籍类别</th>
										<td style="width:180px">&nbsp;<bean:write name="jbxx" property="xjlbmc"/></td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td>&nbsp;<bean:write name="jbxx" property="xjztm"/></td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td>&nbsp;<bean:write name="jbxx" property="sfzx"/></td>
									</tr>
								</table>
								<img style="float:left;margin:33px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">异动后学籍类别</th>
										<td style="width:187px" id="xjlbmc">&nbsp;</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td id="sfyxj">&nbsp;</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td id="sfzx">&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th><span class="red">*</span>调整专业/班级</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:100px;height:20px;">原年级</th>
										<td style="width:180px" id="ydqnj">&nbsp;<bean:write name="jbxx" property="nj"/></td>
									</tr>
									<tr>
										<th style="height:20px;">原<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;<bean:write name="jbxx" property="xymc"/></td>
									</tr>
									<tr>
										<th style="height:20px;">原专业</th>
										<td id="ydqzy">&nbsp;<bean:write name="jbxx" property="zymc"/></td>
									</tr>
									<tr>
										<th style="height:20px;">原班级</th>
										<td id="ydqbj">&nbsp;<bean:write name="jbxx" property="bjmc"/></td>
									</tr>
								</table>
								<img style="float:left;margin:70px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:80px;height:20px;"><span class="red">*</span>异动后年级</th>
										<td style="width:205px">
											<html:select property="ydhnj" styleId="nj" 
												onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');" style="width:100px">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>异动后<bean:message key="lable.xb" /></th>
										<td>											
											<html:select property="ydhxydm" styleId="xydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"  style="width:180px;">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
							
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>异动后专业</th>
										<td>
											<html:select property="ydhzydm" styleId="zydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;">异动后班级</th>
										<td>
											<html:select property="ydhbjdm" styleId="bjdm" style="width:180px;">
												<html:option value="">&nbsp;</html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					
					<tr id="lrqzsj">
						<th><span class="red">*</span>申请异动起止时间</th>
						<td colspan="3">
							<html:text property="sqkssj" styleId="sqkssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqjssj');" />
						    &nbsp;至&nbsp;
						    <html:text property="sqjssj" styleId="sqjssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkssj');" />
						</td>
					</tr>
					
					<logic:equal name="xxdm" value="10511">					
						<tr id="xzpd">
							<th align="right" width="10%">
								<span class="red">*</span>学制
							</th>
							<td align="left" colspan="3">
								<html:text property="xz" styleId="xz" maxlength="1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr id="xxpd">
							<th align="right" width="18%">
								<span class="red">*</span>来源学校/去向学校
							</th>
							<td align="left" colspan="3">
								<html:select property="lyqxxxdm" styleId="lyqxxxdm" disabled="false">
									<html:option value=""></html:option>
									<html:options collection="lyqxxxList" property="lyqxxxdm"
										labelProperty="lyqxxxmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>异动原因
							</th>
							<td align="left" colspan="3">
								<html:select property="ydyydm" styleId="ydyydm" disabled="false">
									<html:option value=""></html:option>
									<html:options collection="ydyyList" property="ydyydm"
										labelProperty="ydyymc" />
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th align="right" width="16%">
							附件信息
						</th>
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
					<tr>
						<th align="right" width="10%">
							申请理由&nbsp;
							<br />
							<font color="red">(限400字)</font>						
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,400);"></html:textarea>
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
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="saveForm('0');return false;" id="buttonSave">
									保存草稿
								</button>
								<button type="button"  onclick="saveForm('5');return false;" id="buttonSubmit">
									提交申请
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
