<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
				<%@ include file="/syscommon/head.ini"%>	
				<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
				<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
				<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>	
				<script language="javascript" src="js/comm/commFunction.js"></script>
				<script language="javascript" defer="defer">
		function teshuzifu(str){
			//输入框特殊字符过滤
			var str_ne =jQuery(str).val();
			var pat="[`~!@#\$%\^&\-\*\(\)_\+<>\?:\"{},\.\/;'\[\\]]";
		    var pattern = new RegExp(pat) 
		    //  
		    if(pattern.test(str_ne)||str_ne=='-'){  
		    	jQuery(str).val(str_ne.replaceAll(pat,'').replaceAll('-','')); 
		        alert("不能输入特殊字符");   
		    }     
		    
		}

		function updateYhzxx(act){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxUpdate";
					var zmc_new = jQuery("#zmc_update").val();
					var zmc_old=jQuery("#old").val();
					var zdm=jQuery("#zdm").val();
					var zmc = jQuery("#zmc_update").val();
					if (trim(zmc) == "") {
						showAlert("组名称不能为空！");
						jQuery("#zmc_update").focus();
						return false;
					}
					if(trim(zmc_new) == trim(zmc_old)){
						showAlert("保存成功！",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					}else{
						var parameter={"str_zdm":zdm,"str_zmc":escape(zmc_new)};
						jQuery.post(url,parameter,function(result){
							if(result == "exist"){
								showAlert("用户组名称已存在！");
								document.getElementById('zmc_update').focus();
								return false;
							}else{
								showAlert(result+"！",{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
							}						
						});
					}
		}

		function showAlertYhz() {
			var argumentsArr = Array.prototype.slice.call(arguments);
			if(argumentsArr[0] == null) return;
			
			var clickFun = null;
			
			if (argumentsArr.length == 3){
				clickFun = argumentsArr[2]["clkFun"];
			}
			ymPrompt.alert({
				title:"系统提示",
				useSlide:true,
				maskAlphaColor:"#FFFFFF",
				maskAlpha:0.3,
				message:argumentsArr[0],
				width:260,
				height:110,
				handler:clickFun
			});
		}
			</script>
	</head>
	<body>
		<div class="open_win01" id="updateYhzxx" >
				<table width="80%" class="formlist">
					<tbody>
						<tr height='30'>
							<th>
								<font color="red">*</font>组名称
							</th>
							<td>
								<input type="text" name="zmc_update" id="zmc_update" value="${zmc_old}" onkeyup="teshuzifu(this);" onkeypress="if(pressEnter(event)){return false;}"  onblur="teshuzifu(this);" class="text_nor" maxlength="10"/>
								<input type="hidden" name="old" id="old" value="${zmc_old}"/>
								<input type="hidden" name="zdm" id="zdm" value="${zdm}"/>
								<input type="hidden" name="yymc" id="yymc"/>
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
									<button type="button"  onclick="updateYhzxx('do')">
										保 存
									</button>
									<button type="button"  onclick="iFClose()">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
	</body>
</html>
