<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%> 
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script language="javascript" defer="defer">

		function jcjgsave() {
			var api = frameElement.api,W = api.opener;
			var jcwh = jQuery("#jcwh").val();//解除问号
			var jcsj = jQuery("#jcsj").val()//解除时间
			if("12684"==jQuery("#xxdm").val()){
				if(null==jcsj||""==jcsj){
					alertError("请将带*的项目填写完整！");
					return false;
				}
			}else{
				if(null==jcsj||""==jcsj||null==jcwh||""==jcwh){
					alertError("请将带*的项目填写完整！");
					return false;
				}
			}
			
			var pkValue=W.jQuery("#dataTable").getSeletIds();
			 var url = "wjcf_cfjg.do?method=saveWjcfjc&cfid="+pkValue;
		      ajaxSubFormWithFun("cfjgForm",url,function(data){
		    	 if(data["message"]=="操作成功！"){
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
	<body >
		<html:form action="/wjcf_cfjg" method="post" styleId="cfjgForm" onsubmit="return false;">
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<logic:equal value="12684" name="xxdm">
									<th>
										<bean:message key="wjcf.text" />文号
									</th>
								</logic:equal>
								<logic:notEqual value="12684" name="xxdm">
									<th>
										<span class="red">*</span><bean:message key="wjcf.text" />文号
									</th>
								</logic:notEqual>
								<td>
									<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span><bean:message key="wjcf.text" />时间
								</th>
								<td>
									<html:text property="jcsj" styleId="jcsj"  style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd','','','100','40');" ></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<bean:message key="wjcf.text" />意见<br/>
									<font color="red">(限制200字)</font>
								</th>
								<td>
									<html:textarea property="jcyj" styleId="jcyj" rows="4" style="width:280px" onblur="checkLen(this,200)"></html:textarea>
								</td> 
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button"  name="保存" onclick="jcjgsave()">
											保 存
										</button>
										<button type="button"  name="取消" onclick="Close();return false;">
											取消
										</button>

									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
