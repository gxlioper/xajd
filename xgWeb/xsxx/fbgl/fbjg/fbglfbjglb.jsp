<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/fbjg/js/fbjg.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				url:"fbglfbjg.do?method=list&type=query",
				multiselect:false,
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'专业',name:'zymc', index: 'zymc'},
				   {label:'层次',name:'pyccmc', index: 'pyccmc'},
				   {label:'学制',name:'xz', index: 'xz'},
				   {label:'班级数',name:'bjs', index: 'bjs'},
				   {label:'人数',name:'rs', index: 'rs'},
				   {label:'男生/女生',name:'nvqk', index: 'nvqk'},
				   {label:'未分班/已分班',name:'fbqk', index: 'fbqk'},
				   {label:'未编学号/已编学号',name:'bxhqk', index: 'bxhqk'}
				]
				
	} 
	 var gridSetting2 = {
				caption:"查询结果",
				pager:"pager",
				url:"fbglfbjg.do?method=list&type=query&tjzt=ytj",
				multiselect:false,
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
				   {label:'专业',name:'zymc', index: 'zymc'},
				   {label:'层次',name:'pyccmc', index: 'pyccmc'},
				   {label:'学制',name:'xz', index: 'xz'},
				   {label:'班级数',name:'bjs', index: 'bjs'},
				   {label:'人数',name:'rs', index: 'rs'},
				   {label:'男生/女生',name:'nvqk', index: 'nvqk'}
				]
				
	}
			jQuery(function($){
				 	var map = getSuperSearch();
					gridSetting["params"] = map;
					$("#dataTable").initGrid(gridSetting);
					$("#cx").hide();
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglxsxx?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" value="${tjzt}" id="tjzt"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li id="tj">
							<a href="javascript:void(0);" onclick="tj();return false;"
								class="btn_tj">提交正式库</a>
						</li>
						<li id="cx">
							<a href="javascript:void(0);" onclick="cx();return false;"
								class="btn_cx">撤销提交</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wtj');"><span>未提交</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ytj');"><span>已提交</span></a></li>
			      </ul>
				</div>
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">查询结果 <a id="title"
					href="javascript:;"
					style="float: right; margin-right: 30px; color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
