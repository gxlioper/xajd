<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
			<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		
		<script language="javascript" defer="defer">
		//页面初始化
		function onShow(){
			
			searchRs()
			
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "rcsw_zjbb_ajax.do?method=bbjgSearch";
			var ie = "ie";// ie版本
			var v4Path = stylePath;//v4样式路径
			
			// 需要传入后台的其它数据
			var otherValue = [ie,v4Path];

			// loding
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			// 查询操作
			searchRsByAjax(url,otherValue);

			// 隐藏 loding
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			
			jQuery.ajaxSetup({async:true});
		}

		//评奖项目审核(单个审核、批量审核)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			
			tipsWindown("系统提示","id:div_002","500","300","true","","true","id");
			
		}


		//前往项目审核
		function showSpgw(){
		
			var url="gyglnew_jqlx_ajax.do?method=showShgwDiv";
			
			var parameter={};
			
			jQuery("#div_spgw").load(url,parameter,function(){
				
				var len=jQuery("[name=spgw]").length;
		
				tipsWindown("系统提示","id:div_spgw","300","170","true","","true","id");
					
			});
		}
		
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len!=1){
				
				alertInfo("请勾选一条需要查看的记录！");
			}else {
				var sqid=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var url = "rcsw_zjbb.do?method=zjbbCk";
				url+="&sqid="+sqid;
				showDialog("证件补办结果查询", 800, 500, url);
				//showTopWin(url,"800","600");
			}
		}
		
		jQuery(function(){
			onShow();
		})
		
		function zjbbjgExportConfig() {
			customExport("rcsw_zjbb_bbjg.do", zjbbjgExportData);
		}
			
		
			
		// 导出方法
		function zjbbjgExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_zjbb_ajax.do?method=zjbbjgExportData&dcclbh=" + "rcsw_zjbb_bbjg.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_jqlx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">查看</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="zjbbjgExportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>	
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
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
