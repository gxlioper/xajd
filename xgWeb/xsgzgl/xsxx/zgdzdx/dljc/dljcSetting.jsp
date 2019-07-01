<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//显示字段选择Div
		function createZdszDiv(){
			
			var url="general_xsxx_dljc.do?method=createZdszDiv";
			
			//其他数据
		 	var parameter = {
				
			};
		  
			jQuery("#div_zdsz").load(url,parameter,function(){});
		}
		
		//点击全部
		function checkAll(num){
			var cb_name = "cb_"+num;
			
			var count = jQuery("input[name="+cb_name+"]").length;

			var cb_check = $("cb_all_"+num).checked;
			
			for(var i=0;i<count;i++){
				var obj = jQuery("input[name="+cb_name+"]")[i];
				obj.checked=cb_check;
			}
			
		}
		//保存字段设置
		function saveZdsz(tag){
		
			if(tag == "ok"){
				var i = 0;
				var zd = new Array();   				  
				jQuery("input[type=checkbox][value!=all]:checked").each(
					function(){zd[i] = escape(jQuery(this).val());i++;}
				);
				
				var url="general_xsxx_dljc.do?method=saveZdsz";
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"array_zd":zd.join("!!array!!")
				};
				
				jQuery.post(url,parameter,function(result){	
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
				});
			}
		}
		</script>
	</head>
	<body onload="createZdszDiv()" ondrag="return false">	
		<html:form action="/xsxx_cssz" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			
			<!-- 维护信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th>
							<span>学生可修改字段选择</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td width="">	
							<div style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;" id="div_zdsz">
							
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td>
							<div class="btn">							
								<button type="button" onclick="confirmInfo('确定后将自动重置全校学生的完善信息状态为否，请您再次确认所勾选的字段',saveZdsz);">
									保 存
								</button>
								
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 维护信息 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>