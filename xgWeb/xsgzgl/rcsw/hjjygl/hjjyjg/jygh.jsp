<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zdxljk/tbxs/thjl.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#saveBtn").bind("click",function(){
					if(""==jQuery("#jyjzsj").val().trim()){
						showAlert("请将必填项填写完整。");
						return false;
						}
					if(jQuery("#ghbz").val().length>500){
						showAlert("归还备注最多填写500字。");
						return false;
						}
					var url = "hjjyJyjg.do?method=saveJygh";
					ajaxSubFormWithFun("HjjyJgForm",url,function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							refershParent();
						}});
						
					});
					
				});
			});
		</script>
  	</head>
  
  	<body>
  		<html:form action="/hjjyJyjg" method="post" styleId="HjjyJgForm">
  			<html:hidden property="jgid" styleId="jgid"/>
  			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>户籍归还</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								借用开始时间
							</th>
							<td width="30%">
								${rs.jykssj}
								<html:hidden property="jykssj" value="${rs.jykssj}" styleId="jykssj"/>
							</td>
							<th width="20%">
								<font color="red">*</font>借用截止时间
							</th>
							<td width="30%">
								<html:text property="jyjzsj" styleId="jyjzsj"
									onfocus="showCalendar('jyjzsj','y-mm-dd',false,'jykssj');" />
							</td>
						</tr>
						
						<tr>
							<th width="16%">归还备注</th>
							<td colspan="3" style="text-align:left;">
								<textarea name="ghbz"  id="ghbz" onblur="checkLen(this,500);"
							   	 style="width:99%;" rows="4"></textarea>
							</td>
						</tr>
										
										
					</tbody>
					</table>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="2">
							<div class="btn">
								<button type="button" id="saveBtn">
									保存
								</button>
								<button type="button" onclick="iFClose();">
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
