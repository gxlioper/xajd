<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsz/js/wpsz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		function saveForm(){	  
			  var xmmc=jQuery("#xmmc").val();
			  var xmxxjs=jQuery("#xmxxjs").val();
			  var xmlb= jQuery("#xmlb").val();
			  if(jQuery.trim(xmmc)==""||jQuery.trim(xmxxjs)==""||jQuery.trim(xmlb)==""){
				  showAlert("请将必填项填写完整！");
					return false;
			  }
		     var url = "axcsWpsz.do?method=addWp&type=save";
		      ajaxSubFormWithFun("WpszForm",url,function(data){
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
		<html:form action="/axcsWpsz" method="post" styleId="WpszForm"
			onsubmit="return false;">
			<html:hidden property="xmdm" styleId="xmdm" value="${xmdm}"/>
			<html:hidden property="xn" styleId="xn" value="${xn}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">					
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td>
								${xn}
							</td>
							<td rowspan="3">
							<div id="zpHidDiv">
								<jsp:include flush="true" page="wpzpzj.jsp"></jsp:include>
							</div>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span class="red">*</span>
								<bean:message key="lable.axcswpmc" />
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" maxlength="20"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span class="red">*</span>
								<bean:message key="lable.axcswplb" />
							</th>
							<td>
								<html:select property="xmlb" styleId="xmlb">
								<option value=""></option>
								<html:options collection="wplbList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="15%">
								<span class="red">*</span>
								<bean:message key="lable.axcswpxxjs" />
							</th>
							<td colspan="3">
								<html:textarea property="xmxxjs" styleId="xmxxjs" rows="4" style="word-break:break-all;width:97%"></html:textarea>
							</td>
						</tr>
					</tbody>
					</table>
					</div>
					
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

