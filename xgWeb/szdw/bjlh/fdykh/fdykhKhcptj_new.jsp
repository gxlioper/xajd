<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/szdw/bjlh/fdykh/js/fdykhcptj_new.js"></script>
		<script type="text/javascript"><%--
			jQuery(function(){
				
				jQuery("[name=zcxm]:not(:disabled)").bind("click",function(){
					var index = jQuery(this).attr("index");
					var liName = jQuery("#tabUl li.ha").attr("clzt");
					
					if ("dcl" == liName){
						var gridSetting = getDclGird();
						jQuery("#dataTable").initGrid(gridSetting);
					} else {
						var gridSetting = getYclGrid();
						jQuery("#dataTable").initGrid(gridSetting);
					}
				});
				var map = getSuperSearch();
				dclGrid["params"]=map;
				jQuery("#dataTable").initGrid(dclGrid);
			});
			
		--%>
		jQuery(function(){
			var gridSettingRS = getRSGrid();
			var map = getSuperSearch();
			map["tjlx"] = "RS";
			gridSettingRS["params"]=map;
			jQuery("#dataTable").initGrid(gridSettingRS);
		});
				
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
	<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					有效平均分算法：学生已打分人数除以班级人数，如果大于等于参数设置的学生评分有效比例，则有效平均分等于当前平均分，否则等于零。
					<br/>
					<b>总分=学生打分有效平均分*权重+考核小组打分平均分*权重</b>
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/bjlh_fdykh" method="post" styleId="fdykhtjForm">
			<input type="hidden" id="tjlx" value="RS"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<li><a href="#" onclick="exportConfig();return false;" class="btn_dc">导出</a></li>
						</ul>
					</div>
				</div>
			<!-- 过滤条件 -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%" id="tabUl">
	      	<li class="ha"><a href="javascript:void(0);"  onclick="chageTB(this,'0');"><span>按人数</span></a></li>
			<li class="ycl"><a href="javascript:void(0);"  onclick="chageTB(this,'1');"><span>按班级</span></a></li>
			<li class="ycl"><a href="javascript:void(0);"  onclick="chageTB(this,'2');"><span>按职责</span></a></li>
	      </ul>
	    </div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>查询结果 </span>
			</h3>
			<table id="dataTable" style="width:100%;"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
