<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：偉大的駱-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customApply.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customForm.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function(){
			createXxwsDiv();
		});
		
		//顯示自定儀表單
		function createXxwsDiv(){
		
			//路徑
			var url = "general_xsxx_dljc.do?method=createXxwsDiv";
			//參數
		 	var parameter = {
				
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			jQuery("#div_xxws").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);
		}
		
		//检测保存信息完善
		function checksSaveXxws(){
		
			var flag = true;
			
			jQuery("select").each(function(){
				if(flag){
					var id = jQuery(this).attr("id");
					var value = jQuery(this).val();
					if(value == ""){
						var zd = id.replace("select_","");
						var zdm = jQuery("#display_"+zd).html();
						alertError(zdm+"为空，请您确认^_^||");
						flag = false;
					}
				}
			});
			
			jQuery("input[type=text][readOnly!=true]").each(function(){
				if(flag){
					var id = jQuery(this).attr("id");
					var value = jQuery(this).val();
					if(value == ""){
						var zd = id.replace("text_","");
						var zdm = jQuery("#display_"+zd).html();
						alertError(zdm+"为空，请您确认^_^||");
						flag = false;
					}
				}
			});
			
			jQuery("input[type=text][readOnly=true]").each(function(){
				if(flag){
					var id = jQuery(this).attr("id");
					var value = jQuery(this).val();
					if(value == ""){
						var zd = id.replace("calendar_","");
						var zdm = jQuery("#display_"+zd).html();
						alertError(zdm+"为空，请您确认^_^||");
						flag = false;
					}
				}
			});
			
			if(flag){
				saveXxws();
			}
		}
		
		//保存信息完善
		function saveXxws(){
		
			var parameter={};
			
			jQuery("select").each(function(){
				var name = jQuery(this).attr("name");
				parameter[name]=escape(jQuery(this).val());
			});
			
			jQuery("input[type=text]").each(function(){
				var name = jQuery(this).attr("name");
				parameter[name]=escape(jQuery(this).val());
			});
			
			var url="general_xsxx_dljc.do?method=saveXxws";
					
			jQuery.ajaxSetup({async:false});	
			
			jQuery.post(url,
				parameter,
				function(result){
					alertInfo(result,function(tag){
						if(tag=="ok"){
							//refreshForm('xsxx_xsdl.do');
							
						}
					});
				}
			);

			refreshForm('xsgzgl/xsxx/zgdzdx/dljc/dljcForward.jsp');
			
			jQuery.ajaxSetup({async:true});
		}
		</script>	
	</head>
	<body ondrag="return false">
		<html:form action="/general_xsxx_dljc" method="post">
			
			<!-- 隐藏域 begin-->
			
			<!-- 隐藏域 end-->
			
			<!-- 自定義表單 begin-->
			<div id="div_xxws" style="width:100%;height:500px;overflow-x:hidden;overflow-y:auto;">
			
			</div>
			<!-- 自定義表單 end-->
			
			<!-- 其他信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>