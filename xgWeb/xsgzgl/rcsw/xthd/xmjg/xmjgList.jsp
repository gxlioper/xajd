<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmjg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"活动结果列表",
								pager:"pager",
								url:"rcsw_txhd_xmjg.do?method=xmjgList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'活动结果id',name:'guid', index: 'guid',key:true,hidden:true},
								   {label:'学年',name:'xn', index: 'xn',width:'10%'},
								   {label:'学期',name:'xqmc', index: 'xq',width:'2%'},
								   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:dcmcLink},
								   {label:'姓名',name:'xm', index: 'xm',width:'11%'},
								   {label:'性别',name:'xb', index: 'xb',width:'5%'},
								   {label:'班级',name:'bjmc',width:'90px', index: 'bjmc',width:'21%'},
								   {label:'项目代码',name:'xmdm', index: 'xmdm',hidden:true},
								   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'15%'},
								   {label:'活动类别',name:'lbmc', index: 'lbmc',hidden:true},
								   {label:'活动时间',name:'hdsj', index: 'hdsj',width:'22%'},
								   {label:'活动地点',name:'hddd', index: 'hddd',hidden:true},
								   {label:'申请理由',name:'sqly', index: 'sqly',hidden:true},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
								   {label:'申请人',name:'sqr', index: 'sqr',hidden:true},
								   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true}
								],
								sortname: "guid",
							 	sortorder: "desc"
							};
						
						var map = getSuperSearch();
						gridSetting["params"] = map;
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
	<html:form action="rcsw_txhd_xmjg.do?method=xmjgList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >导入</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						<logic:equal value="12309" name="xxdm">
						<li><a href="javascript:void(0);" onclick="getHdsbWord();return false;" class="btn_down">活动申报审批表</a></li> 
						</logic:equal>
						<%-- 
						<li><a href="javascript:void(0);" onclick="printQjjgb('qjjg.do?method=printQjjgb');return false;" class="btn_down">下载结果表</a></li> 
						--%>  
						<%--
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>
				--%></ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 活动结果列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
