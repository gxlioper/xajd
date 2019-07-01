<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsflr/js/wsflr.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "评分组列表",
					radioselect:true,
					pager : "pager",
					url : "cjWsflr.do?method=getWsflrList&type=query",
					colList : [ 
					   {label : 'wsfid',name : 'wsfid',index : 'wsfid',key:true ,hidden:true},
					   {label : 'dfszid',name : 'dfszid',index : 'dfszid',key:true ,hidden:true},					   
					   {label : '学年',name : 'xn',index : 'xn',width : '20%'}, 
					   {label : '学期',name : 'xqmc',index : 'xq',width : '10%'},
					   {label : '抽查年月',name : 'ccny',index : 'ccny',width : '20%'},
					   {label : '开放维护时间',name : 'kfwhsj',index : 'kfwhsj',width : '30%'},
					   {label : '已提交寝室数',name : 'ytjqs',index : 'ytjqs',width : '10%',formatter:function(cellValue,rowObject){
							var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
							html.bind("click",function(){
								showDialog("查看已提交寝室",800,500,"cjWsflr.do?method=viewqs&tjzt=1&ccny="+rowObject["ccny"]);
							});
							return html;
						 }},
					   {label : '未提交寝室数',name : 'wtjqs',index : 'wtjqs',width : '10%',formatter:function(cellValue,rowObject){
								var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
								html.bind("click",function(){
									showDialog("查看未提交寝室",800,500,"cjWsflr.do?method=viewqs&tjzt=0&ccny="+rowObject["ccny"]);
								});
								return html;
							 }}
					   ],
					sortname : "xn",
					sortorder : "desc"
				}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/cjWsflr" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">录入</a></li>						
				</ul>
			</div>
			</logic:equal>
				
			<!-- 过滤条件 -->
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 卫生分录入列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
