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
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>	
		<script type="text/javascript">

			jQuery(function() {
				
				initShow();
			});
			
			function saveForm(){
				var xxdm = jQuery("#xxdm").val();
				if(jQuery("#yxzxss").val()=="0" || jQuery("#xzxsKey").val()==""){
					return showAlert("请至少选择一个学生！");
				}
				var checkData = "ydlbdm-xjydwh-xjydsj";
				
				if(!jQuery("#tzbj").is(":hidden")){
					checkData = "ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
				}
				if(xxdm == "13871"){
					checkData.replace("-xjydwh","");
				}
				if(xxdm == "5002"){
					checkData = "ydlbdm-xjydsj";
					if(!jQuery("#tzbj").is(":hidden")){
					checkData = "ydlbdm-nj-xydm-zydm-bjdm-xjydwh-xjydsj";
					}
				}
				if(!check(checkData)){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}

				
			     var url = "xjydjg.do?method=xjydPlcl&type=save";

			     BatAlert.showTips("正在保存，请稍候！");
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
			// 删除批量选择学生信息
			function closeWindows(){
			  var url = "xjydjg.do?method=xjydPlcl&type=close";
		      ajaxSubFormWithFun("xjydjgForm",url,function(data){
    				if (parent.window){
    					refershParent();
    				}
				 });
			 
		      iFClose();
			}


			function initXyzybj(){
				jQuery('#xydm').val("");
				jQuery('#xymc').val("");
				
				jQuery('#zydm').val("");
				jQuery('#zymc').val("");
				
				jQuery('#bjdm').val("");
				jQuery('#bjmc').val("");
			}
		</script>
	</head>
	<body>
		<!-- 提示信息 START-->
		<div class="prompt">
			<h3>
				<span>提示：</span>
			</h3>
			<p>请先选择学生!</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- 提示信息 END-->
		<html:form method="post" styleId="xjydjgForm" action="/xjydjg">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>学籍异动信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>批量选择学生</th>
						<td colspan="3">
								<button type="button" onclick="selectStudent();return false;" id="buttonSelect">
									选择
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;已选择学生：<span style="color:red;">${yxzxss }</span>个
						</td>
					</tr>
					<tr>
						<th align="right" width="16%">
							<span class="red">*</span>学籍异动类别
						</th>
						<td align="left"  width="34%">
							<html:select property="ydlbdm" styleId="ydlbdm" disabled="false" onchange="initShow();">
								<html:option value=""></html:option>
								<html:options collection="xjlbList" property="xjlbdm"
									labelProperty="xjlbmc" />
							</html:select>
						</td>
						<th align="right"  width="16%">
							<span class="red">*</span>学年/学期
						</th>
						<td align="left"  width="34%">
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
						<th>是否有学籍</th>
						<td id="sfyxj">&nbsp;</td>
						<th>是否在校</th>
						<td id="sfzx">&nbsp;</td>
					</tr>
					<logic:equal name="xxdm" value="10511">		
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>是否师范生
							</th>
							<td align="left">
								<html:select property="sfsfs" styleId="sfsfs" disabled="false">
									<html:option value=""></html:option>
									<html:option value="非师范">非师范</html:option>
									<html:option value="师范">师范</html:option>
									<html:option value="免费师范生">免费师范生</html:option>
								</html:select>
							</td>
						</tr>
					</logic:equal>
					<tr id="tzbj">
						<th><span class="red">*</span>调整班级[异动后]</th>
						<td colspan="3">
							<div >
							
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px;height:26px;">异动后年级</th>
										<td style="width:205px">
											<html:select property="ydhnj" styleId="nj" 
												onchange="initXyzybj();" style="margin-left:3px;width:80px">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th style="height:23px;">异动后<bean:message key="lable.xb" /></th>
										<td>
											<html:text property="ydhxymc" styleId="xymc" readonly="true"></html:text>
											<html:hidden property="ydhxydm" styleId="xydm" />
										</td>
									</tr>
									<tr>
										<th>异动后专业</th>
										<td>
											<html:text property="ydhzymc" styleId="zymc" readonly="true"></html:text>
											<html:hidden property="ydhzydm" styleId="zydm" />
										</td>
									</tr>
									<tr>
										<th>异动后班级</th>
										<td>
											<div>
												<html:text property="ydhbjmc" styleId="bjmc" readonly="true"></html:text>
												<html:hidden property="ydhbjdm" styleId="bjdm" />
												<button type="button" class="btn_01" id="button_bj" style="float:right;margin:2px 0px 0px 0px;"
													onclick="getBj();">
													选择
												</button>
											</div>
										</td>
									</tr>
								</table>
							</div>
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
						<th align="right" width="10%">
							<span class="red">*</span>学籍异动时间&nbsp;
							<br />					
						</th>
						<td>
							<html:text property="xjydsj" styleId="xjydsj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
						</td>
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
		<div>
			<table width="100%" border="0" class="formlist">
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
								<button type="button"  onclick="closeWindows();" id="buttonClose">
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
