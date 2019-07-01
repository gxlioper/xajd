<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"助学贷款违约列表 ",
					pager:"pager",
					url:"zxdkWygl.do?method=getWyxxList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckDkjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                        {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                        {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
					   {label:'合同编号',name:'htbh', index: 'htbh'},
					   {label:'违约详情',name:'wyxq',index:'wyxq'},
					   {label:'备注',name:'bz',index:'bz'}
					]
				};

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function importWyxx(){
				toImportData("ZXDK_XYDDK_WYXX");
				return false;
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='zxdk_wyxx.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='zxdk_wyxx.do';
				setSearchTj();//设置高级查询条件
				
				var url = "zxdkWygl.do?method=dcwy&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function ckDkjg(id){
				showDialog("查看",800,520,"zxdkDkjg.do?method=ckDkjg&id="+id);
			}
			
			function editWyxq(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					showDialog("违约跟踪",500,240,"zxdkWygl.do?method=wybz&htbh="+rows[0]["htbh"]);
				}
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/zxdkXyddk" method="post" styleId="wyxxForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dr" onclick="importWyxx();return false;">导入违约名单</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
							<li><a href="#" class="btn_xg" onclick="editWyxq();return false;">违约跟踪</a></li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>助学贷款违约列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
