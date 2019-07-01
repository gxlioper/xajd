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
		function saveForm(){
			flg = false;
			var selects = jQuery("select");
			  jQuery(selects).each(function(){
				if(!!jQuery(this).val()){
					flg = true;
					return;
				}
			  })
			  if(!flg){
				  showAlertDivLayer("请将必填项填写完整！");
				  return false;
			  }
			  var url = "hdgl_cssz.do?method=save";
		      ajaxSubFormWithFun("hdglCsszForm",url,function(data){
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
		<html:form action="/hdgl_cssz" method="post" styleId="hdglCsszForm">
		<html:hidden property="id" styleId="id"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>审核流设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>100本书评价审核流程
							</th>
							<td width="60%">
								<html:select property="ds" styleId="ds"> 
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>100名教师评价审核流程
							</th>
							<td width="60%">
								<html:select property="js" styleId="js">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="35%">
								<font color="red">*</font>活动补录审核流程
							</th>
							<td width="60%">
								<html:select property="bl" styleId="bl">
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

