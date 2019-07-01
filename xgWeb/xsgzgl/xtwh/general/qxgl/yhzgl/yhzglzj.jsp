<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>	
				<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
				<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>	
				<script language="javascript" src="js/comm/commFunction.js"></script>
				<script language="javascript" defer="defer">
		function teshuzifu(str){
			//����������ַ�����
			var str_ne =jQuery(str).val();
			var pat="[`~!@#\$%\^&\-\*\(\)_\+<>\?:\"{},\.\/;'\[\\]]";
		    var pattern = new RegExp(pat) 
		    //  
		    if(pattern.test(str_ne)||str_ne=='-'){  
		    	jQuery(str).val(str_ne.replaceAll(pat,'').replaceAll('-','')); 
		        alert("�������������ַ�");   
		    }     
		    
		}

		function addYhzxx(){
			var url = "xtwh_qxgl_yhzgl_ajax.do?method=yhzxxAdd";
			var zmc = jQuery("#zmc_add").val();
			if (trim(zmc) == "") {
				showAlert("�����Ʋ���Ϊ�գ�");
				jQuery("#zmc_add").focus();
				return false;
			}
			var parameter={"str_zmc":escape(zmc)};
			jQuery.post(url,parameter,function(result){
				if(result == "exist"){
					showAlert("�û��������Ѵ��ڣ�");
					jQuery("#zmc_add").focus();
					return false;
				}else{
					showAlert(result+"��",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
				}
			});
		}

		function showAlertYhz() {
			var argumentsArr = Array.prototype.slice.call(arguments);
			if(argumentsArr[0] == null) return;
			
			var clickFun = null;
			
			if (argumentsArr.length == 3){
				clickFun = argumentsArr[2]["clkFun"];
			}
			ymPrompt.alert({
				title:"ϵͳ��ʾ",
				useSlide:true,
				maskAlphaColor:"#FFFFFF",
				maskAlpha:0.3,
				message:argumentsArr[0],
				width:235,
				height:105,
				handler:clickFun
			});
		}
		
			</script>
	</head>
	<body>
		<div class="open_win01" id="addYhzxx">
				<table width='80%' class='formlist'>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="2">--%>
<%--								<span>�û�������</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
					<tbody>
						<tr>
							<th>
								<font color='red'>*</font>������
							</th>
							<td>
								<input type='text' name='zmc_add' id='zmc_add' onkeyup="teshuzifu(this);"  onkeypress="if(pressEnter(event)){return false;}" onblur="teshuzifu(this);"  class='text_nor' maxlength="10"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"  onclick="addYhzxx()">
										�� ��
									</button>
									<button type="button"  onclick="iFClose()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
	</body>
</html>
