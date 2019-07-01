<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var url = "qgzx_cxtj_ajax.do?method=jfhbCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//查看
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="qgzx_cxtj.do?method=jfhbCk";		
				url+="&pkValue="+pkValue;
				//showTopWin(url,800,600);
				showDialog("", 760, 525, url);
			}else{
				alertInfo("请勾选一条记录进行查看！");
				return false;
			}
		}
		
		function jfhbcxExportConfig() {
			var jfhbfs=jQuery("#jfhbfs").val();
			if("yf"==jfhbfs){
				customExport("jfhbCx_qgzx_cxtj_ajax_yf.do", jfhbcxExportData);
			}else{
				customExport("jfhbCx_qgzx_cxtj_ajax.do", jfhbcxExportData);
			}
		}
			
		
			
		// 导出方法
		function jfhbcxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "qgzx_cxtj_ajax.do?method=jfhbCxExportData&dcclbh=" + "jfhbCx_qgzx_cxtj_ajax.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_cxtj" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="jfhbfs" id="jfhbfs" value="${jfhbfs}" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ck" onclick="showView();return false;">查看明细</a>
						</li>
							
						<li><a href="#" onclick="jfhbcxExportConfig();return false;" class="btn_dc">导出</a></li>
						
						<%--<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>
					--%></ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxCxtjForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>