<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

			var qsfGrid = {
				pager:"pager",
				url:"wsjcWsflr.do?method=getFscxList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xq'},
				   {label:'检查日程名称',name:'rcmc', index: 'rcmc'},
				   {label:'开始时间',name:'kssj', index: 'kssj'},
				   {label:'结束时间',name:'jssj', index: 'jssj'},
				   {label:'楼栋',name:'ldmc', index: 'ldmc'},
				   {label:'寝室',name:'qsh', index: 'qsh'},
				   {label:'总分',name:'wszf', index: 'wszf'}
				],
				params:{jcdx:"0"},
				sortname: "wszf",
			 	sortorder: "desc"
			};
			
			var cwfGrid = {
				pager:"pager",
				url:"wsjcWsflr.do?method=getFscxList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc', index: 'xq'},
				   {label:'日程名称',name:'rcmc', index: 'rcmc'},
				   {label:'开始时间',name:'kssj', index: 'kssj'},
				   {label:'结束时间',name:'jssj', index: 'jssj'},
				   {label:'楼栋',name:'ldmc', index: 'ldmc'},
				   {label:'寝室',name:'qsh', index: 'qsh'},
				   {label:'床位',name:'cwh', index: 'cwh'},
				   {label:'总分',name:'wszf', index: 'wszf'}
				],
				params:{jcdx:"1"},
				sortname: "wszf",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(qsfGrid);
			});

			function selectTab(obj,jcdx){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				jQuery("#jcdx").val(jcdx);
				if ("1" == jcdx){
					jQuery("#dataTable").initGrid(cwfGrid);
				} else {
					jQuery("#dataTable").initGrid(qsfGrid);
				}
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			//导出
			function exportWsf(){
				var jcdx = jQuery("#jcdx").val();
				
				if ("1" == jcdx){
					customExport('gygl_wsjc_wsftj_cw', exportData);
				} else {
					customExport('gygl_wsjc_wsftj_qs', exportData);
				}
			}

			function exportData(){
				var jcdx = jQuery("#jcdx").val();
				var DCCLBH= "1" == jcdx ? "gygl_wsjc_wsftj_cw" : "gygl_wsjc_wsftj_qs";
				setSearchTj();//设置高级查询条件
				
				var url = "wsjcWsflr.do?method=exportFscxList&dcclbh=" + DCCLBH+"&jcdx="+jcdx;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/wsjcJcxm" method="post" styleId="form">
	
		<input type="hidden" id="jcdx" value="0"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="exportWsf()" class="btn_dc">导出</a></li>
					</ul>
				</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>寝室</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>床位</span></a></li>
		      </ul>
			</div>
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>查询结果列表</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
