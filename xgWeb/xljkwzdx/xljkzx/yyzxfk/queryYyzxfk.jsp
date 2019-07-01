<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="xljkwzdx/js/xljkwzdx.js"></script>
	<script type="text/javascript" src="xljkwzdx/xljkzx/js/yyzxfk.js"></script>
	<script type="text/javascript">
		var gridSetting={
			caption:"心理咨询列表",
			pager:"pager",
			multiselect:true,
			rowNum:10,
			url:"xljk_yyzxfk.do?method=queryYyzxfkAction",
			colList:[
			   {label:'sqid',name:'sqid',index:'sqid',hidden:true, key:true  },
			   {label:'zxid',name:'zxid',index:'zxid',hidden:true},
			   {label:'学号',name:'xh', index: 'xh', formatter:xhLinkForYyfk},
			   {label:'姓名',name:'xm', index: 'xm'},
			   {label:'性别',name:'xb', index: 'xb'},
			   {label:'预约状态',name:'yyzt', index: 'yyzt',formatter:yyztChange},
			   {label:'预约反馈',name:'yyfkzt', index: 'yyfkzt',formatter:yyfkztChange},
			   {label:'咨询安排日期',name:'zzaprq', index: 'zzaprq'},
			   {label:'安排咨询师',name:'zxsmc', index: 'zxsmc',formatter:zxsxmLink},
			   {label:'咨询状态',name:'zxzt', index: 'zxzt',formatter:zxztChange},
			   {label:'zxs',name:'zxs',index:'zxs',hidden:true}
			],
			sortname: "yyzt",
		 	sortorder: "asc"
		}
		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function reloadYyzxfkDataTable(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		/**
		 * 高级查询
		 * @return
		 */
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
	</script>
  </head>
  
  <body>
     <div class="tab_cur">
		<p class="location">
			<em>您的当前位置：</em><a>${title }</a>
		</p>
	 </div>
	 <html:form action="/xljk_xlzxcl">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
			  <ul>
				<logic:equal value="yes" name="writeAble">
					<li>
						<a href="javascript:void(0);" onclick="xlzxyyfk();return false;" class="btn_xg" id="xgButton">预约反馈</a>
					</li>
				</logic:equal>
			  </ul>
			</div>
		</div>
		<!-- 过滤条件 -->	
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- 过滤条件 end-->
	</html:form>
	<div>
		<h3 class="datetitle_01">
			<span> 
				心理咨询列表   
			</span>
		</h3>
	</div>
	<div class="formbox" style="width:100%;height:320px;overflow-x:hidden;overflow-y:auto;">
		<table id="dataTable" ></table>
	</div>
	<div id="pager"></div>
  </body>
</html>
