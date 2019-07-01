<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/sq/js/jcftzsq.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			jQuery(function() {
				var gridSetting = {
						caption : "评分组列表",
						//radioselect:true,
						pager : "pager",
						url : "jcftz_sq.do?method=getJcftzSqList&type=query",
						colList : [ 
						   {label : 'jgid',name : 'jgid',index : 'jgid',key:true ,hidden:true},					   
						   {label : '学年',name : 'xn',index : 'xn',width : '20%'}, 
						   {label : '学期',name : 'xqmc',index : 'xq',width : '10%'},
						   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '20%',formatter : xmmcLink},
						   {label : '项目类型',name : 'csmsmc',index : 'csmsmc',width : '5%'},
						   {label : '项目类型',name : 'csms',index : 'csms',hidden:true},
						   {label : '项目级别',name : 'xmjbmc',index : 'xmjbmc',width : '30%'},
						   {label : '所属科目',name : 'sskmmc',index : 'sskmmc',width : '30%'},
						   {label : '项目开始时间',name : 'xmkssj',index : 'xmkssj',width : '30%'},
						   {label : '团/人数',name : 'ybjrs',index : 'ybjrs',width : '30%',formatter : rsLink},
						   {label : '审核状态',name : 'shztmc',index : 'xfrdsqzt',width : '30%'},
						   {label : 'xfrdsqzt',name : 'xfrdsqzt',index : 'xfrdsqzt',hidden:true},
						   {label : 'xmdm',name : 'xmdm',index : 'xmdm',hidden:true}
						   ],
						sortname : "xmkssj",
						sortorder : "desc"
					}
				var map = getSuperSearch();
				gridSetting["params"] = map;
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
	<html:form action="/jcftz_sq" method="post" onsubmit="return false;">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="sqkg" name="sqkg" value="${sqkg}"/>
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_xg">学分认定</a></li>
					<li><a href="javascript:void(0);" onclick="tijiao();" class="btn_up" >提交</a></li>				
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
					<span>校内拓展结果上报列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
