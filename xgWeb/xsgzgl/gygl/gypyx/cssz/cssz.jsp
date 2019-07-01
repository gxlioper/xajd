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
				if(!checkNotNull("splc")){
					showAlertDivLayer("请将带<font class='red'>*</font>的项目填写完整！");
					return false;
				}
				saveForm();
			
			})
		});
		function saveForm(){	  
			  var id = jQuery("#id").val();
				var url = "gypynew_cssz.do?method=saveCssz";
		      ajaxSubFormWithFun("CsszForm",url,function(data){
		    	  showAlertDivLayer(data["message"],{},{"clkFun":function(){
			    	  document.location.href = "gygl_gypynew_cssz.do";
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
				用于设置公寓评优申请的审批流程！
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		</div>
		<html:form action="/jskp_cssz" method="post" styleId="CsszForm">
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>审核流程
							</th>
							<td width="65%">
								<logic:notEmpty name="splcMap" property="id">
									<input type="hidden" name="id" value="${splcMap.id}" />
								</logic:notEmpty>
								<html:select property="splc" styleId="splc" value="${splcMap.splc}">
									<option value=""></option>
									<html:options collection="splcList" property="splc"
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

