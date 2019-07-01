<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xszz/zzdy/zzmdgl/js/zzmdgl.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		var gridSetting = {
				caption : "资助名单列表",
				pager : "pager",
				url : "xszz_zzdyzzmdgl.do?method=getZzmdList&type=query",
				colList : [ 
				    	   {label : 'id',name : 'id',index : 'id',key : true,hidden:true},
				    	   {label : '学号',name : 'xh',index : 'xh',width : '15%' ,formatter : xhLink },
				    	   {label : '姓名',name : 'xm',index : 'xm',width : '10%'},
				    	   {label : '学年',name : 'xn',index : 'xn',width : '10%'},
				    	   {label : '学期',name : 'xqmc',index : 'xqmc',width : '10%'},
				    	   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '10%'},
				    	   {label : '资助总金额',name : 'zzzje',index : 'zzzje',width : '10%'},
				    	   {label : '发放月数',name : 'ffys',index : 'ffys',width : '5%'},
				    	   {label : '学院',name : 'xymc',index : 'xymc',width : '20%'},
				    	   {label : '发放金额',name : 'yffje',index : 'yffje',width : '10%'},
				    	   {label : '发放状态',name : 'ffztmc',index : 'ffztmc',width : '10%'}
				    	   
				    	   ]
			}

			jQuery(function() {
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
		
	</head>
	<body>
	<html:form action="/xszz_zzdyzzmdgl" method="post">
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
					<li><a href="javascript:void(0);" onclick="zzmdTb();" class="btn_sx">同步</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 项目设置列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
