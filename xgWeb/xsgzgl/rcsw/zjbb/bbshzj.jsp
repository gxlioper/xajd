<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function saveBbsz(){
			if(jQuery("#zjmc")==null || jQuery("#zjmc").val()==""){
				alertInfo("补办证件名称不能为空！");
				return false;
			}
			
			confirmInfo("该操作将会<font color='blue'>保存证件补办设置</font>信息，是否确定？",saveBbszInfo);
			
	     }
		 function modiBbsz(xmid){
				jQuery("#xmid").val(xmid);
				
				if($("zjmc") && $("zjmc").value==""){
					alertInfo("补办证件名称不能为空！");
					return false;
				}
				confirmInfo("该操作将会<font color='blue'>修改证件补办设置</font>信息，是否确定？",modiBbszInfo);
				
		 }
		 function modiBbszInfo(tag){
	     		
				if(tag=="ok"){
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["lcid"]=escape(jQuery("[name=lcid]:checked").eq(0).val());
					
					parameter["zjmc"]=escape(jQuery("#zjmc").val());
					
					parameter["id"]=escape(jQuery("#xmid").val());
					
					var url = "rcsw_zjbb_ajax.do?method=modiBbsz";
					jQuery.post(url,parameter,
						function(result){

						 showAlert(result,{},{"clkFun":function(){
										    				if (parent.window){
										    					refershParent();
										    				}
										    			}});
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			}

		 function saveBbszInfo(tag){
	     		
				if(tag=="ok"){
					
					//主键
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// 得到JSON对象
			        var parameter ={};
					
					parameter["lcid"]=escape(jQuery("[name=lcid]:checked").eq(0).val());
					
					parameter["zjmc"]=escape(jQuery("#zjmc").val());
					
					var url = "rcsw_zjbb_ajax.do?method=saveBbsz";
					jQuery.post(url,parameter,
						function(result){
						 showAlert(result,{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    			}});
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			}
		</script>
	</head>
	<%--
		<input type="hidden" id="zjmc" name="zjmc" value="${zjmc}"/>
		<input type="hidden" id="lcid" name="lcid" value="${lcid}"/>
		<input type="hidden" id="xmid" name="xmid" value="${xmid}"/>--%>
					<input type="hidden" name="xmid" id="xmid" />
		${html}
</html>