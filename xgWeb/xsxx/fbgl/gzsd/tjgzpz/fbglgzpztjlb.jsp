<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/tjgzpz/js/fbglgzpztj.js"></script>
		<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"查询结果",
									pager:"pager",
									url:"fbglgzpztj.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'pzgzid',name:'pzgzid', index: 'pzgzid',key:true,hidden:true},
									   {label:'规则名称',name:'pzgzmc', index: 'pzgzmc',formatter:dcmcLink},
									   {label:'规则类型',name:'gzmc', index: 'gzmc'},
									   {label:'规则类型',name:'gzdm', index: 'gzdm',hidden:true},
									   {label:'启用状态',name:'qyztmc', index: 'qyztmc'},
									   {label:'是否系统内置',name:'sfnzmc', index: 'sfnzmc'},
									   {label:'是否已使用',name:'sfysy', index: 'sfysy'},
									   {label:'更新时间',name:'gxsj', index: 'gxsj'}
									],
									sortname: "gzmc",
								 	sortorder: "asc"
								}
							jQuery("#dataTable").initGrid(gridSetting);
					});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglgzpztj?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="add();return false;"
								class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;"
								class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;"
								class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copy();return false;"
								class="btn_fz">复制</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qysz('1');return false;"
								class="btn_plqy">启用</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qysz('0');return false;"
								class="btn_plty">停用</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
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
