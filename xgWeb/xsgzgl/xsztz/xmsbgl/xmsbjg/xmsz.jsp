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
			var url = "xmsbXmsbjg.do?method=xmrsKzfw";
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
			var rskzjb=jQuery("#rskzjb").val();
			var rskzjbs=rskzjb.split(",");
			for(var i=0;i<rskzjbs.length;i++){
				if(V==rskzjbs[i]){
					return true;
				}
			}
			return false;
		}
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  var qxfwView = jQuery("[name=qxfwView]:checked").val();
			  if(jQuery.trim(splc)=="" || qxfwView == null || qxfwView ==""){
				  showAlert("请将必填项填写完整！");
					return false;
			  }
			//获取权限范围
			    var qxfwIds="";
			    jQuery("input[name=qxfwView]").each(function(){
			    	var isChecked=jQuery(this).is(":checked");
			    	if(isChecked){
			    		qxfwIds+=jQuery(this).val();
			    		qxfwIds+=",";
			    	}
			    });

			    jQuery("#rskzjb").val(qxfwIds);
		     var url = "xmsbXmsbjg.do?method=xmsz&type=save";
		      ajaxSubFormWithFun("XmsbjgForm",url,function(data){
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
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：
					<font color="red">${xmmc} &nbsp; <span id="spztTip"
						style="display: none;"> 当前项目已有学生申请，部分项不允许修改 </span> </font>
					只有在申请开关开启状态，并且申请时间有效范围内进行设置后，才为“已设置”状态，其余均为“未设置”状态
				</p>
				<a class="close" title="隐藏"
					onclick="this.parentNode.style.display='none';"></a>
			</div>
			<html:hidden property="jgid" styleId="jgid" />
			<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="rskzjb" styleId="rskzjb"/>
			<input type="hidden" name="oldsplc" id="oldsplc" value="${oldsplc}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目申请设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="120px">
								<font color="red">*</font>申请开关
							</th>
							<td colspan="3">
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
							<th>
								项目申请起始时间
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
									onfocus="showCalendar('sqkssj','ymmdd',true,'sqjssj');" />
								&nbsp;至
								<html:text property="sqjssj" styleId="sqjssj"
									onfocus="showCalendar('sqjssj','ymmdd',false,'sqkssj');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>审核流程
							</th>
							<td>
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr id="qxfwTr">
							<th>
								<font color="red">*</font>人数控制级别
							</th>
							<td id="qxfwTd" colspan="3"></td>
						</tr>
					</tbody>
				</table>

	<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
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

