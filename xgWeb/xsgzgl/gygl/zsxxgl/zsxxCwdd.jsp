<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript">
		function cwdd_submit(){
			if($("rzsj").value==""){
				showAlert("请选择入住时间！");
				return false;
			}else{
				var rzsj = jQuery("#rzsj").val();
				var bz = jQuery("#bz").val();
				var idList = jQuery("#idList").val();
				//gyglnew_zsxxgl.do?method=zsxxglManage&go=go&doType=cwdd
				var url = "gyglnew_zsxxgl.do?method=zsxxCwdd&go=go&doType=cwdd&rzsj="+rzsj+"&bz="+bz+"&idList="+idList;
			      ajaxSubFormWithFun("gyglnewCwglForm",url,function(data){
			    	 if(data["message"]=="对调成功！"){
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
		}
		</script>
	</head>
	<body >

		<html:form styleId="gyglnewCwglForm" action="/gyglnew_cwgl" method="post">
			<input type="hidden" id="idList" name="idList" value="${idList}" />	
			
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>对调时间</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="60px">
									入住时间
								</th>
								<td>
									<input type="text" id="rzsj" name="rzsj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>
								</td>
							</tr>
							<tr id="tr_rzyy">
								<th >
									入住原因
								</th>
								<td>
									<html:select property="rzyy" styleId="rzyy">
										<html:optionsCollection name="rzyylist" label="rzyymc" value="rzyydm"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td>
									<input type="text" id="bz" name="bz" style="width: 95%;height: 50px" />
								</td>
							</tr>
							<tr>
								<th>
									注意
								</th>
								<td>
									<div id="submit_bz" class="bz" style="color: red;"></div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="保存"  onclick="cwdd_submit()">
											保存 
										</button>
										<button type="button" name="取消"  onclick="iFClose();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
