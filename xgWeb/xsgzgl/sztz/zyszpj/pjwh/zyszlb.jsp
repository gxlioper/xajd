<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/sztz/zyszpj/pjwh/js/pjwh.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						var query=jQuery("#query").val();
					     var gridSetting = {
									caption:"职业素质评价列表",
									pager:"pager",
									url:"zyszpjwh.do?method=list&type=query&query="+query,
									colList:[
									   {label:'职业素质id',name:'zyszid', index: 'zyszid',key:true,hidden:true},
									   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
									   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
									   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'10%'},
									   {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
									   {label:'学年',name:'xn', index: 'xn',width:'10%'},
									   {label:'学期',name:'xqmc', index: 'xqmc',width:'6%'},
									   {label:'填写时间',name:'txsj', index: 'txsj',width:'14%'},
									   {label:'互评状态',name:'hpzt', index: 'hpzt',width:'10%'},
									   {label:'师评状态',name:'spzt', index: 'spzt',width:'10%'}
									   
									],
									sortname: "txsj",
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
	<html:form action="/zyszpjwh.do?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
				<logic:equal value="yes" name="writeAble">
					<logic:equal value="brcx" name="query">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">填写申请表</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
					</logic:equal>
					<logic:equal value="txcx" name="query">
						<li>
							<a href="javascript:void(0);" onclick="txhp();return false;" class="btn_sz">互评</a>
						</li>
					</logic:equal>
					<logic:equal value="lscx" name="query">
						<li>
							<a href="javascript:void(0);" onclick="sp();return false;" class="btn_sz">师评</a>
						</li>
					</logic:equal>
					<logic:notEqual value="txcx" name="query">
					<li>
						<a href="javascript:void(0);" onclick="dy();return false;" class="btn_dy">打印</a>
					</li>
					</logic:notEqual>
				</logic:equal>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 职业素质评价申请列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
