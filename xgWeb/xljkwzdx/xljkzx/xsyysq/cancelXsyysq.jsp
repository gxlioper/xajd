<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/check.js"></script>
	  <script type="text/javascript">
	  	function cancelXsyysqAction(){
	  		var sqid = jQuery("#sqid").val();
	  		var qxyyyy = jQuery('#qxyyyy').val();
	  		if(!checkNotNull("qxyyyy")){
	 			showAlert("请将必填项填写完整！");
	 			return false;
	 		}
	  		var yyzt = jQuery('#yyzt').val();
	  		if(qxyyyy.length > 500){
	  			showAlertDivLayer("取消预约原因最大字数不超过"+500+",请确认！");
	  			return false;
	  		}
			jQuery.post("xljk_xsyyzx.do?method=cancelXsyysqAction",{sqid: sqid,qxyyyy:qxyyyy,yyzt:yyzt},function(data){
	 			showAlert(data["message"],{},{"clkFun":function(){
		 				if (frameElement.api){
							var api = frameElement.api,W = api.opener;
							W.reloadXsyysqDataTable();
							iFClose();
						} 
	 				}
	 			});
			},'json');
	  	}
	  </script>
  </head>
  
  <body>
    <table width="100%" border="0" class="formlist">
    	<thead>
			<tr>
				<th colspan="2">
					<span>取消预约</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="30%">
					<span class="red">*</span>取消预约原因<br/>
					<span class="red">(限500字)</span>
				</th>
				<td>
					<textarea name="qxyyyy" id="qxyyyy" rows="5" style="width: 95%;" onkeypress="checkLen(this,500);"></textarea>
					<input type="hidden" name="sqid" id="sqid" value="${sqid }"/>
					<input type="hidden" name="yyzt" id="yyzt" value="${yyzt }"/>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button id="submit_button" type="button"  onclick="cancelXsyysqAction();">
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
  </body>
</html>
