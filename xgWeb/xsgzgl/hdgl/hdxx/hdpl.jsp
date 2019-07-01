<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
			function savePl(){
				if(!!jQuery("#plnr").val()){
					var url = "hdgl_hdxx.do?method=savePl";
					ajaxSubFormWithFun("hdxxForm", url, function(data) {
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
				}else{
					showAlert("请填写评论内容！");
					return false;
				}				
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
			<html:hidden property="hdid" styleId="hdid"/>
			<html:hidden property="plid" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th width="20%">
								活动名称
							</th>
							<td colspan="3">
								${hdmc}
							</td>
						</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								评论内容
								<br /><font color="red">&lt;限1000字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='plnr' style="width:98%" styleId="plnr" rows='14' onblur="checkLen(this,1000);"/>
							</td>
			      		</tr>
					</tr>						
					</tbody>
				 </table>			
			</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
										<button type="button" onclick="savePl();">
											保存
										</button>
										<button type="button" onclick="iFClose();">
											关闭
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

