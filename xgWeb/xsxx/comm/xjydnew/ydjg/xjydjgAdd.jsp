<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>		
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initShow();
			});
			
			function saveForm(){
				var xxdm = jQuery("#xxdm").val();
				var checkData = "xh-ydlbdm-xjydwh-xjydsj";

				if(!jQuery("#tzbj").is(":hidden")){
					checkData = "xh-ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
				}

				if("10511" == xxdm) {
					checkData = "xh-ydlbdm";
					if(!jQuery("#xzpd").is(":hidden")){
						checkData += "-xz";
					}
					if(!jQuery("#xxpd").is(":hidden")){
						checkData += "-lyqxxxdm";
					}
					checkData += "-ydyydm-xjydwh-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
						checkData = "xh-ydlbdm-nj-xydm-zydm-bjdm";
						if(!jQuery("#xzpd").is(":hidden")){
							checkData += "-xz";
						}
						if(!jQuery("#xxpd").is(":hidden")){
							checkData += "-lyqxxxdm";
						}
						checkData += "-ydyydm-xjydwh-xjydsj";
					}
				}
				if(xxdm == "13871"){
					checkData.replace("-xjydwh","");
				}
				if("5002" == xxdm) {
					checkData = "xh-ydlbdm-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
					checkData = "xh-ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
					}
				}
				if(!check(checkData)){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				} 
				
				var sfzx=jQuery("#sfzx").html();
				var sfzx0=jQuery("#sfzx0").val();
				
			    var url = "xjydjg.do?method=xjydjgAdd&type=save";
			    if("12309"==jQuery("#xxdm").val()){  //学籍异动后退宿，武昌首义学院个性化
			    	ajaxSubFormWithFun("xjydjgForm",url,function(data){
			    		if(data["message"]=="保存成功！"){
							if("在校"==sfzx0&&"不在校"==sfzx&&data["sfycw"]=="y"){  //符合退宿条件
								confirmInfo("保存成功，是否同时进行退宿处理？",function(ty){
					    			if(ty=="ok"){
					    				showDialog("退宿", 730, 365, "gyglnew_cwgl.do?method=cwglPlts2&xh="+jQuery('#xh').val());
					    			}
					    			if (parent.window){
				    					refershParent();
				    				}
					    		});
							}else{  //不符合退宿条件
								showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
							}
			    	 	}else{
			    			showAlert(data["message"]);
			    		}
					});
			    }else{
			    	ajaxSubFormWithFun("xjydjgForm",url,function(data){
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
			 }

		</script>
	</head>
	<body>
	<input type="hidden" id="sfzx0" name="sfzx0" value="${jbxx.sfzx}"/>
	<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsxx/comm/selectStudent/selectStudentAll.jsp" %>
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
							<html:select property="ydlbdm" styleId="ydlbdm" disabled="false" onchange="initShow();">
								<html:option value=""></html:option>
								<html:options collection="xjlbList" property="xjlbdm"
									labelProperty="xjlbmc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>学年/学期
						</th>
						<td align="left">
							<html:select property="xn" styleId="xn" disabled="false">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
							<html:select property="xq" styleId="xq" disabled="false" >
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>学籍状态[异动]</th>
						<td colspan="3">
							<div >
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">原学籍类别</th>
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
										<td id="sfzx" name="zxzt" >&nbsp;</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr id="tzbj">
						<th><span class="red">*</span>调整班级</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">原年级</th>
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
											</html:select>
							
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>异动后专业</th>
										<td>
											<html:select property="ydhzydm" styleId="zydm"
											onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
												<html:option value="">&nbsp;</html:option>
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:20px;"><span class="red">*</span>异动后班级</th>
										<td>
											<html:select property="ydhbjdm" styleId="bjdm" style="width:180px;">
												<html:option value="">&nbsp;</html:option>
											</html:select>
										</td>
									</tr>
								</table>
							</div>
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
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'filepath'
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
					<tr>
						<th align="right" width="10%">
							<logic:notEqual name="xxdm" value="13871">
							<logic:notEqual name="xxdm" value="5002">
							<span class="red">*</span>
							</logic:notEqual>
							</logic:notEqual>
							学籍异动文号&nbsp;
							<br />					
						</th>
						<td>
							<html:text property="xjydwh" styleId="xjydwh" maxlength="30"></html:text>
						</td>
						<logic:notEqual name="xxdm" value="10511">
							<th align="right" width="10%">
								<span class="red">*</span>学籍异动时间&nbsp;
								<br />					
							</th>
							<td>
								<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
							</td>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10511">
							<th align="right" width="10%">
								<span class="red">*</span>学籍异动审核时间&nbsp;
								<br />					
							</th>
							<td>
								<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
							</td>
						</logic:equal>
					</tr>
					<tr id="lrqzsj">
						<th><span class="red">*</span>异动起止时间</th>
						<td colspan="3">
							<html:text property="sqkssj" styleId="sqkssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqjssj');" />
						    &nbsp;至&nbsp;
						    <html:text property="sqjssj" styleId="sqjssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkssj');" />
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							备注&nbsp;
							<br />
							<font color="red">(限100字)</font>						
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="xjydbz" styleId="xjydbz" style="width:97%" onblur="checkLen(this,100);"></html:textarea>
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
								<button type="button" id="tssave"  onclick="saveForm();return false;" id="buttonSave">
									保 存
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
