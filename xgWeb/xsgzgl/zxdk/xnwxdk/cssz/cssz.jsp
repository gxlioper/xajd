<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	    <script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
		<script language="javascript">
		jQuery(function(){
			jQuery("#div_help").show();
			jQuery("#buttonSave").click(function(){

				
				saveForm();
			
			})
			/*
			jQuery("[name=splc]").change(function() {// 审核控制级别,根据审核流程进行显示
				setLckz(jQuery(this).val());
			});
			jQuery("[name=splc]").change();*/
		});
		//流程控制
		/*
		function setLckz(value) {
			if (value == "") {
				jQuery("#qxfwTd").html("");
				return;
			}
			var url = "gygl_xyzsjcszgl.do?method=xmwhShfw";
			jQuery.post(url, {
				value : value
			}, function(data) {
				var sHtml = "";
				if (data != null) {
					for ( var i = 0; i < data.length; i++) {
						var o = data[i];
						sHtml += "<label><input type='checkbox' name='qxfwView' value='"
								+ o.spgwdm + "'/>";
						sHtml += o.spgwmc;
						sHtml += "</label>";
						if (i != data.length - 1) {
							sHtml += "<br/>";
						}
					}
				}
				jQuery("#qxfwTd").html(sHtml);
				var isH=false;
			    jQuery("input[name=qxfwView]").each(function(){
			    	var V=jQuery(this).val();
			    	if(isHaveV(V)){
			    		jQuery(this).attr("checked","checked");
			    		isH=true;
			    	}
			    });
				
			
			}, 'json');
		}*/
		function isHaveV(V){
			var qxfw=jQuery("#qxfw").val();
			var qxfws=qxfw.split(",");
			for(var i=0;i<qxfws.length;i++){
				if(V==qxfws[i]){
					return true;
				}
			}
			return false;
		}
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  var jesx=jQuery("#jesx").val();
			  if(jQuery.trim(splc)=="" || jQuery.trim(jesx)==""){
				  showAlertDivLayer("请将必填项填写完整！");
				  return false;
			  }
              if(!checkMoney2(jQuery.trim(jesx))){
            	  showAlertDivLayer("请填写正确的金额！");
            	  return false;
			  }
			  if(jQuery.trim(jesx).length > 10){
				  showAlertDivLayer("金额不能超过10位！");
				  return false;
			  }
				//获取权限范围
				/*
			    var qxfwIds="";
			    jQuery("input[name=qxfwView]").each(function(){
			    	var isChecked=jQuery(this).is(":checked");
			    	if(isChecked){
			    		qxfwIds+=jQuery(this).val();
			    		qxfwIds+=",";
			    	}
			    });

			    jQuery("#qxfw").val(qxfwIds);*/
			    
			  var id = jQuery("#id").val();
				var url = id == "" ? "xnwxdk_cssz.do?method=save" : "xnwxdk_cssz.do?method=update";
		        ajaxSubFormWithFun("XnwxdkCsszModel",url,function(data){
		    	  showAlertDivLayer(data["message"]);
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
				校内无息贷款申请需要开启申请开关以及申请的起止时间，申请操作需在相应的起止时间内完成，无息贷款<br/>
				审核流和审批岗位请在系统维护菜单下的审批流程维护中进行设置和维护。
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		</div>
		<html:form action="/xnwxdk_cssz" method="post" styleId="XnwxdkCsszModel">
		<html:hidden property="id" styleId="id"/>
		<%--<html:hidden property="qxfw" styleId="qxfw"/>
			--%><div style=''>
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
								申请起止时间
							</th>
							<td width="60%">
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','y-mm-dd');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>审核流程
							</th>
							<td width="60%">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>金额上限
							</th>
							<td colspan="3">
								<html:text property="jesx" onkeyup="checkInput(this)" onblur="ismoney(this)" styleId="jesx" maxlength="10" />
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

