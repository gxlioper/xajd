<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/jgcx/jgcx/js/jgcx.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "考核对象列表",
					radioselect:true,
					pager : "pager",
					url : "khgljgcx.do?method=jgcxList&type=query",
					colList : [ 
					   {label : 'xmid',name : 'xmid',index : 'xmid',key:true ,hidden:true},
					   {label : 'khlx',name : 'khlx',index : 'khlx', hidden:true},
					   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '20%'}, 
					   {label : '开始时间',name : 'kssj',index : 'kssj',width : '20%'}, 
					   {label : '结束时间',name : 'jssj',index : 'jssj',width : '10%'},
					   {label : '考核对象',name : 'khdxmc',index : 'khdxmc',width : '10%'},
					   {label : '被评人数',name : 'sum',index : 'sum',width : '5%'}
					   ],
					sortname : "jssj",
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
	<html:form action="/khgljgcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="viewJg();return false;" class="btn_ck">查看</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>项目列表</span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
