<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	 <%@ include file="/syscommon/head.ini"%>
	 <script type="text/javascript">
	 	function setZxsxxStatusAction(){
	 		var zghs = jQuery("#zghs").val();
	 		var status = jQuery("#status").val();
	 		
	 		jQuery.post("xljk_zxsgl.do?method=setZxsxxStatusAction",{zghs: zghs, status:status},function(data){
	 			showAlert(data["message"],{},{"clkFun":function(){
		 				if (frameElement.api){
			 				var api = frameElement.api,W = api.opener;
			 				W.reloadZxsxxDataTable();
			 				iFClose();
			 			}
	 				}
	 			});
			},'json');
	 	}
	 </script>
  </head>
  
  <body>
  	<html:form action="/xljk_zxsgl" method="post" styleId="zxsxxForm">
	    <table width="100%" border="0" class="formlist">
	    	<thead>
				<tr>
					<th colspan="2">
						<span>设置咨询师在岗状态</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						在岗状态
					</th>
					<td>
						<input type="hidden" name="zghs" id="zghs" value="${zghs }"/>
						<html:select  property="status" styleId="status">
							<html:option value="1">在岗</html:option>
							<html:option value="0">不在岗</html:option>
						</html:select>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"为必填项</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="setZxsxxStatusAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
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
