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
		<script language="javascript">
		jQuery(function(){
			jQuery("#div_help").show();
			jQuery("#buttonSave").click(function(){
				if(!checkNotNull("splc1-splc2")){
					showAlertDivLayer("请将带<font class='red'>*</font>的项目填写完整！");
					return false;
				}
				/*是否审核字段判断*/
				var sfsh = jQuery("input[name='sfsh']:checked").val();
				if("" == sfsh || null == sfsh){
					showAlertDivLayer("请选择自立项目是否审核！");
					return false;
				}
				saveForm();
			})
		});
		
		function saveForm(){	  
		  var id = jQuery("#id").val();
		  var url = "jskp_cssz.do?method=saveCssz";
	      ajaxSubFormWithFun("jskpCsszForm",url,function(data){
	    	  showAlertDivLayer(data["message"],{},{"clkFun":function(){
		    	  document.location.href = "pjpy_jskp_cssz.do";
				}});;
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
				用于设置立项和申报的审批流程！
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		</div>
		<html:form action="/jskp_cssz" method="post" styleId="jskpCsszForm">
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>自主立项审核流程
							</th>
							<td width="65%">
								<logic:notEmpty name="lxsplc" property="id">
									<input type="hidden" name="id" value="${lxsplc.id}" />
								</logic:notEmpty>
								<input type="hidden" name="lx" value="lx" />
								<html:select property="splc" styleId="splc1" value="${lxsplc.splc}">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>项目申报审核流程
							</th>
							<td width="65%">
								<logic:notEmpty name="sbsplc" property="id">
									<input type="hidden" name="id" value="${sbsplc.id}" />
								</logic:notEmpty>
								
								<input type="hidden" name="lx" value="sb" />
								<html:select property="splc" styleId="splc2" value="${sbsplc.splc}">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>自立项目是否审核
							</td>
							<td>
								<input type="radio" name="sfsh" property="sfsh" value="1" ${sbsplc.sfsh == "1" ? "checked" : ""}/>是
								<input type="radio" name="sfsh" property="sfsh" value="0" ${sbsplc.sfsh == "0" ? "checked" : ""}/>否
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

