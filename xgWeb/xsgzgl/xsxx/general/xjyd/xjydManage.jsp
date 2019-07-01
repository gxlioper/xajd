<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			searchRs();
		}

		//同步
		function tongBu(){
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			var flag = true;
			
			if(num == "0"){
				alertError("请勾选希望同步的数据");
				flag = false;
			}

			if(flag){
				confirmInfo("将要同步您所勾选记录，请确认",saveXjyd);
			}		
		}

		function saveXjyd(tag){
			if(tag == "ok"){
				var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
				var RowsStr="";
				if(objs.length>0){
					for (i=0; i<objs.length; i++){
				     RowsStr+=objs[i].value+",";
					}
				}

				jQuery.ajaxSetup({async:false});
				var url = "general_xsxx_xjyd_ajax.do?method=saveXjyd";
				//参数
			 	var parameter = {
			 		"str_xh":RowsStr
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						searchRs();
					}
				);
				
				jQuery.ajaxSetup({async:true});
				
			}
			}

		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xsxx_xjyd_ajax.do?method=searchXjydResult";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});

			setDivHeight();
		}

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		
		<html:form action="/general_xsxx" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="tongBu();return false;" class="btn_sx">
								同步
							</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;height:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>