<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：偉大的駱-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customForm.js"></script>
		<script language="javascript" defer="defer">
		jQuery(function(){
			//創建自定義查詢結果
			createCustomSearch();
			//創建自定義查詢結果操作字段
			createSearchCzzdTable();
		});
		
		//显示修改Div
		function showEditDiv(zd,zdm){
		
		}
		</script>
	</head>
	<body ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
			
		<html:form action="/customForm" method="post">
			<!-- 隐藏域 begin-->
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 表单ID -->
			<input type="hidden" name="str_form_id" id="hidden_form_id" value="${formInfo.form_id }"/>
			<!-- 查詢視圖 -->
			<input type="hidden" name="str_search_view" id="hidden_search_view" value="${formInfo.search_view}"/>
			
			<!-- 隐藏域 end-->
			
			<!-- 顯示區域begin -->
			<div class="formbox"> 
				<!--标题start-->
				<h3 class="datetitle_01"> <span>显示字段设置</span> </h3>
				<!--标题end-->
				
				<!-- 內容begin -->
				<table border="1" bordercolor="#000000" style="width:100%;height: 100%">
					<tr>
						<td width="20%" valign="top">
							<div id="div_czzd_${formInfo.search_view}"
								style="width:100%;height:400px;overflow-x:hidden;overflow-y:auto;">
							
							</div>
						</td>
						<td valign="top">
							<div id="div_customSearch">
					
							</div>
						</td>
					</tr>
				</table>			
				<!-- 內容end -->
				
			</div>
			<!-- 顯示區域end-->
			
			<!-- 其他信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>