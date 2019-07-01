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
			
		});
		function saveForm(){	  
			  var splc=jQuery("#splc").val();
			  if(jQuery.trim(splc)==""){
				  showAlertDivLayer("请将必填项填写完整！");
					return false;
			  }
			  var id = jQuery("#id").val();
				var url = id == "" ? "jxgl_cssz.do?method=save" : "jxgl_cssz.do?method=update";
		      ajaxSubFormWithFun("jxglCsszForm",url,function(data){
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
		</div>
		<html:form action="/jxgl_cssz" method="post" styleId="jxglCsszForm">
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
								<font color="red">*</font>奖项申报审核流
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

