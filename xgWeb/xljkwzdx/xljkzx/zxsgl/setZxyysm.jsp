<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	 <%@ include file="/syscommon/head.ini"%>
	 <script type="text/javascript" src="js/check.js"></script>
	 <script type="text/javascript">
	    function setZxyysmAction(){
	 		var zxyysm = jQuery("#zxyysm").val();
	 		if(zxyysm.length > 500){
	 			showAlertDivLayer("咨询预约说明最大字数不超过"+500+",请确认！");
	 			return false;
	 		}
	 		jQuery.post("xljk_zxsgl.do?method=setZxyysmAction",{zxyysm: zxyysm},function(data){
	 			showAlert(data["message"],{},{"clkFun":function(){
	 					iFClose();
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
					<span>咨询预约说明</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th width="20%">
					<span class="red">*</span>咨询预约说明<br/>
					<span class="red">(限500字)</span>
				</th>
				<td>
					<textarea name="zxyysm" id="zxyysm" rows="9" style="width: 95%;" onkeypress="checkLen(this,500);">${zxyysm }</textarea>
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<button id="submit_button" type="button"  onclick="setZxyysmAction();">
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
