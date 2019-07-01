<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "考核对象列表",
					pager : "pager",
					radioselect:true,
					url : "khglKhpf.do?method=khpflList&type=query",
					colList : [ 
					   {label : 'xmid',name : 'xmid',index : 'xmid',hidden:true},
					   {label : 'khbid',name : 'khbid',index : 'khbid',hidden:true}, 
					   {label : 'khlx',name : 'khlx',index : 'khlx',hidden:true},
					   {label : 'sfjs',name : 'sfjs',index : 'sfjs',hidden:true},
					   {label : 'ypcount',name : 'ypcount',index : 'ypcount',hidden:true},
					   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '20%'}, 
					   {label : '开始时间',name : 'kssj',index : 'kssj',width : '15%'}, 
					   {label : '结束时间',name : 'jssj',index : 'jssj',width : '15%'}, 
					   {label : '考核表',name : 'khbmc',index : 'khbmc',width : '20%',formatter:setYl},
					   {label : '考核对象',name : 'khdxmc',index : 'khdxmc',width : '10%'},
<%--					   {label : '待评人数',name : 'dprs',index : 'dprs',width : '10%'},--%>
<%--					   {label : '已评人数',name : 'yprs',index : 'yprs',width : '10%'}--%>
						{label : '状态',name : 'dpcount',index : 'dpcount',width : '10%',formatter:setZt}
					   ],
					sortname : "kssj",
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
	<html:form action="/khglKhpf" method="post">
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
						<li>
							<a href="javascript:void(0);" onclick="quePf();" class="btn_xg">开始评分</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>评分列表</span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
