<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script>

//密码初始化
function mmcsh(act){
	var url = "xtwh_qxgl_yhgl_ajax.do?method=yhmmCsh";
		
			if(jQuery("#mm1").val() != jQuery("#mm2").val()){
				alertInfo("确认口令不一致！");
		    	return false;
			}
			
			if(!checkPsw(jQuery("#mm1").val()))
				return false;
				
			var i = 0;
			var parameter = {};
			parameter["array_primarykey_checkVal"]=jQuery("#selectId").val();
			parameter["str_kl"]=escape(jQuery("#mm1").val());
			confirmInfo("确定要将选中的用户的密码初始化吗?",function(ok){
				if(ok=="ok"){
					jQuery.post(url,parameter,function(result){


						　　 showAlert(result+"！",{},{"clkFun":function(){
		　　				    	if (parent.window){
		　　				    		refershParent();
		　　				    	}
		　　					}});
					});					
				}else{
					return false;
				}
			});
	
}
</script>
</head>
<!-- 密码初始化弹出层 -->
<div class="open_win01"  id="cshYhmm">
	<table width="80%" class="formlist">
		<thead>
			<tr>
				<th colspan="2">
					<span>密码初始化</span>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr height='30'>
				<th>
					<font color="red">*</font>新密码
				</th>
				<td>
					<input type="hidden" id="selectId" name="selectId" value="${selectId}"/>
					<input type="password" name="mm1" id="mm1" class="text_nor"  maxlength="20"/>
				</td>
			</tr>
			<tr height='30'>
				<th>
					<font color="red">*</font>确认密码
				</th>
				<td>
					<input type="password" name="mm2" id="mm2" class="text_nor" maxlength="20"/>
				</td>
			</tr>
		<tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					"<span class="red">*</span>"为必填项<br/><span class="red">新密码长度为6~20位且不可为连续数字或相同字符</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btn">
						<button type="button"  onclick="mmcsh()">
							保 存
						</button>
						<button type="button"  onclick="iFClose();return false;">
							关 闭
						</button>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
</div>
</head>