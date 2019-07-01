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
			jQuery("[name=splc]").change(function() {// 审核控制级别,根据审核流程进行显示
				setLckz(jQuery(this).val());
			});
			jQuery("[name=splc]").change();
		});
		//流程控制
		function setLckz(value) {
			if (value == "") {
				jQuery("#qxfwTd").html("");
				return;
			}
			var url = "mrgzkhJcsz.do?method=xmwhShfw";
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

                jQuery("[name=qxfwView]").bind("click",function(){
                    var selectV=jQuery(this).val();
                    jQuery("[name=qxfwView]:checked").each(function(){
                        var sv=jQuery(this).val();
                        if(sv!=selectV){
                            jQuery(this).removeAttr("checked");
                        }
                    });
                });
			}, 'json');
		}
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
			  if(jQuery.trim(splc)==""){
				  showAlertDivLayer("请将必填项填写完整！");
					return false;
			  }
			  
				//获取权限范围
			    var qxfwIds="";
			    jQuery("input[name=qxfwView]").each(function(){
			    	var isChecked=jQuery(this).is(":checked");
			    	if(isChecked){
			    		qxfwIds+=jQuery(this).val();
//			    		qxfwIds+=",";
			    	}
			    });

			    jQuery("#qxfw").val(qxfwIds);
			    
			  var id = jQuery("#id").val();
				var url = id == "" ? "mrgzkhJcsz.do?method=save" : "mrgzkhJcsz.do?method=update";
		      ajaxSubFormWithFun("GzkhJcszForm",url,function(data){
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
				用人单位审核级别: 设定用人单位审核时的数据范围。如果<font color="blue">勾选</font>，审核人为学生<font color="blue">申请岗位所在部门</font>的教师；如果<font color="blue">不勾选</font>，审核人为<font color="blue">负责申请学生</font>的教师(具体根据身份过滤可审核学生)。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		</div>
		<html:form action="/mrgzkhJcsz" method="post" styleId="GzkhJcszForm">
		<html:hidden property="id" styleId="id"/>
		<html:hidden property="qxfw" styleId="qxfw"/>
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
								<font color="red">*</font>工作情况填写开关
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
								填写起始时间
							</th>
							<td width="60%">
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" />
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>工作情况审核开关
							</th>
							<td width="60%">
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList">
										<label>
											<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th width="35%">
								审核起始时间
							</th>
							<td width="60%">
								<html:text property="shkssj" styleId="shkssj"
									onfocus="showCalendar('shkssj','y-mm-dd',true,'shjssj');" />
								&nbsp;至
								<html:text property="shjssj" styleId="shjssj"
									onfocus="showCalendar('shjssj','y-mm-dd',false,'shkssj');" />
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>工作情况审核流
							</th>
							<td width="60%">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr id="qxfwTr">
							<th>
								用人单位审核控制
							</th>
							<td id="qxfwTd" colspan="3"></td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm();return false;" id="buttonSave">
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

