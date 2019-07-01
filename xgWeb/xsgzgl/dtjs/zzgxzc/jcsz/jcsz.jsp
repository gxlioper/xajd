<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
		jQuery(function(){
			jQuery("#div_help").show();
			jQuery("#buttonSave").click(function(){
				saveForm();
			})
		});
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  if(jQuery.trim(splc)==""){
				  showAlertDivLayer("请将必填项填写完整！");
					return false;
			  }
			    
			  var id = jQuery("#id").val();
				var url = id == "" ? "dtjs_jcsz.do?method=save" : "dtjs_jcsz.do?method=update";
		      ajaxSubFormWithFun("zzgxzcjcszForm",url,function(data){
		    	  showAlertDivLayer(data["message"],{},{"clkFun":function(){
		    		  document.location.href = "dtjs_jcsz.do?method=jcsz";
					}});
				});
		  }
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
		   </p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				组织关系转出登记申请需要开启申请开关以及申请开放时间，申请操作需在相应的开放时间内完成，<br/>
				审核流和审批岗位请在系统维护菜单下的审批流程维护中进行设置和维护。
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		</div>
		<html:form action="/dtjs_jcsz" method="post" styleId="zzgxzcjcszForm">
			<html:hidden property="id" styleId="id"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>基础设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="35%">
								<font color="red">*</font>申请开关
							</th>
							<td width="60%">
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList">
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th width="35%">
								申请开放时间
							</th>
							<td width="60%">
								<html:text property="kssj" styleId="kssj"
									onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" />
								&nbsp;至
								<html:text property="jssj" styleId="jssj"
									onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" />
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>审核流
							</th>
							<td width="60%">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick=";" id="buttonSave">
										保 存
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

