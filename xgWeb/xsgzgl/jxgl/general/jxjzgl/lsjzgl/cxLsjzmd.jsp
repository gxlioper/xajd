<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>	
		--%><%@ include file="/syscommon/head.ini"%>	
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxmd.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});


		//查询结果集
		function searchRs(){
			//按钮控制

			//controlBtn();
			var url = "lsjzgl_cxLsjz_ajax.do?method=cxLsjzmdAjax";
		
			var ie = "ie";

			var otherValue = [ie,jQuery("#sjid").val()];
			
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
		
			
		}

		//
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//返回军训建制
		function fhJxjz(){
			var url = "jxjzgl_lsjzgl_cxLsjz.do";
			window.location.href=url;
		}

		//导出历史建制名单
		function dcLsjzmd(){
			var url="lsjzgl_cxLsjz_ajax.do?method=dcLsjzmd";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		
		function lsjzglExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("lsjzgl_cxLsjz_ajax.do", lsjzglExportData);
			}
			
		
			
		// 导出方法
		function lsjzglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "lsjzgl_cxLsjz_ajax.do?method=lsjzglExportData&dcclbh=" + "lsjzgl_cxLsjz_ajax.do";//dcclbh,导出功能编号
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
				<span>
				导出功能为导出当前查询条件下的被编制的学生信息。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="jxjzgl_cxJxjz.do?method=cxJzmd" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="jzid" name="jzid" value="${model.jzid }"/>
			<input type="hidden" id="sjid" name="sjid" value="${model.sjid }"/>
			<input type="hidden" id="jxid" name="jxid" value="${model.sjid }"/>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="fhJxjz();return false;">返回</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="lsjzglExportConfig();return false;">自定义导出</a></li>
					</ul>
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
					<span> 军训名称： ${jxxxwhModel.jxmc} &nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>