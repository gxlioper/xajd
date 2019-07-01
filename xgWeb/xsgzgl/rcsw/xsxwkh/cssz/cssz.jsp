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
			  var kssj=jQuery("#kssj").val();
			  var jssj=jQuery("#jssj").val();
			  if(jQuery.trim(kssj)==""||jQuery.trim(jssj)==""){
				  showAlertDivLayer("请将必填项填写完整！");
					return false;
			  }
			  var id = jQuery("#id").val();
				var url = id == "" ? "xsxwkhCssz.do?method=save" : "xsxwkhCssz.do?method=update";
		      ajaxSubFormWithFun("xsxwCsszForm",url,function(data){
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
		<html:form action="/xsxwkhCssz" method="post" styleId="xsxwCsszForm">
		<html:hidden property="id" styleId="id"/>
		<html:hidden property="xn" styleId="xn"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>基础设置</span>
							</th>
						</tr>
					</thead>
					<tbody >
					<tr>
							<th width="35%">
								考核终评学年
							</th>
							<td>
							${xsxwCsszForm.xn}
						</td>
						</tr>
					<tr>
							<th width="35%">
								<font color="red">* </font>考核终评起始时间
							</th>
							<td>
							<html:text  property="kssj" styleId="kssj"   size="10"
									onfocus="showCalendar('kssj','y-mm-dd',true,'jssj');" 
									readonly="true"></html:text>
								-
								<html:text  property="jssj" styleId="jssj"   size="10"
									onfocus="showCalendar('jssj','y-mm-dd',false,'kssj');" 
									readonly="true"></html:text>
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

