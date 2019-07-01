<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：偉大的駱-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customForm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "customForm.do?method=searchCustomForm";
			var ie = "ie";
			
			var otherValue = [ie];
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//表單設置
		function addCustomForm(){
			var url = "customForm.do?method=customFormParameter";
			showTopWin(url,"600","480");	
			//tipsWindown("系统提示","id:div_CustomForm","600","400","true","","true","id");
		}

		//顯示字段設置
		function xszdSetup(form_id,souce_table,detail_view){
			var url = "customForm.do?method=customFormSetting";
				url+= "&form_id="+form_id;
				url+= "&souce_table="+souce_table;
				url+= "&detail_view="+detail_view;
			showTopWin(url,"800","600");
		}
		
		//查詢字段設置
		function jgcxSetup(form_id,souce_table,search_view){
			var url = "customForm.do?method=customFormSearch";
				url+= "&form_id="+form_id;
				url+= "&souce_table="+souce_table;
				url+= "&search_view="+search_view;
			showTopWin(url,"800","600");
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				1.本功能默认展示的是本评奖学年学期的数据。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/customForm" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="addCustomForm();return false;" class="btn_zj">
								增加
							</a>
						</li>
						<li>
							<a href="#" onclick="delCustomForm();return false;" class="btn_sc">
								删除
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>

			<!-- 内容显示区开始 -->
			<div class="main_box">

				<!-- From内容 begin-->
				<div id="div_rs" style="">

				</div>
				<!-- From内容 end-->

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=customFormForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>