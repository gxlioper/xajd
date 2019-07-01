<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initShow();
				onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');
			});
			
			function saveForm(){
				var xxdm = jQuery("#xxdm").val();
				var checkData = "ydlbdm-xjydwh-xjydsj";

				if(!jQuery("#tzbj").is(":hidden")){
					checkData = "ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
				}
				if("10511" == xxdm) {
					checkData = "ydlbdm";
					if(!jQuery("#xzpd").is(":hidden")){
						checkData += "-xz";
					}
					if(!jQuery("#xxpd").is(":hidden")){
						checkData += "-lyqxxxdm";
					}
					checkData += "-ydyydm-xjydwh-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
						checkData = "ydlbdm-nj-xydm-zydm-bjdm";
						if(!jQuery("#xzpd").is(":hidden")){
							checkData += "-xz";
						}
						if(!jQuery("#xxpd").is(":hidden")){
							checkData += "-lyqxxxdm";
						}
						checkData += "-ydyydm-xjydwh-xjydsj";
					}
				}
				if(xxdm == "13871" ){
					checkData.replace("-xjydwh","");
				}	
				if(xxdm == "5002" ){
					checkData = "ydlbdm-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
					checkData = "ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
					}
				}
				if(!check(checkData)){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
			     var url = "xjydjg.do?method=xjydjgUpd&type=save";
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

		</script>
	</head>
	<body>
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<html:hidden property="xjydjgid"/>
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
										<td style="width:180px">&nbsp;${xjydjgForm.ydqxjlbmc }</td>
									</tr>
									<tr>
										<th>是否有学籍</th>
										<td>&nbsp;${xjydjgForm.ydqsfyxjmc }</td>
									</tr>
									<tr>
										<th>是否在校</th>
										<td>&nbsp;${xjydjgForm.ydqsfzxmc }</td>
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
						<th><span class="red">*</span>调整班级</th>
						<td colspan="3">
							<div >
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">原年级</th>
										<td style="width:180px" id="ydqnj">&nbsp;${xjydjgForm.ydqnj }</td>
									</tr>
									<tr>
										<th style="height:20px;">原<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;${xjydjgForm.ydqxymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原专业</th>
										<td id="ydqzy">&nbsp;${xjydjgForm.ydqzymc }</td>
									</tr>
									<tr>
										<th style="height:20px;">原班级</th>
										<td id="ydqbj">&nbsp;${xjydjgForm.ydqbjmc }</td>
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
								<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);"></html:text>
							</td>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10511">
							<th align="right" width="10%">
								<span class="red">*</span>学籍异动审核时间&nbsp;
								<br />					
							</th>
							<td>
								<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);"></html:text>
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
							<html:textarea rows="4" property="xjydbz" styleId="xjydbz" style="width:97%"  onblur="checkLen(this,100);"></html:textarea>
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
