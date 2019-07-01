<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/yyrq/js/yyrq.js"></script>	
		<script type="text/javascript">
		function saveForm(){
		 if(!checkNull("yyrq")){
				return false;
		}
	     var url = "wsbz_yyrq.do?method=add&type=save";
	      ajaxSubFormWithFun("YyrqForm",url,function(data){
	    	 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					var api = frameElement.api,W = api.opener;
	    					W.jQuery("#dataTable").reloadGrid();
	    					closeDialog();
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
		<html:form action="/wsbz_yyrq" method="post" styleId="YyrqForm" onsubmit="return false;">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>预约日期
								</th>
								<td>
								<html:text property="yyrq" styleId="yyrq"  style="width:50%" onfocus="return showCalendar('yyrq','yyyy-MM-dd');" />
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
										<button type="button" name="保存" onclick="saveForm();return false;">
											保 存
										</button>
										<button type="button" name="关 闭" onclick="iFClose();return false;">
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

