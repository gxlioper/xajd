<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"ahgf_sztz.do?method=getXfhzList",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'学号',name:'xh', index: 'xh',width:'13%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'学年',name:'xn', index: 'xn'},
				   {label:'学期',name:'xqmc',index:'xqmc'},
				   {label:'总分',name:'tzzf',index:'tzzf'}
				],
				sortname:"bjdm,tzzf",
				sortorder:"desc"
			};

			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//导出
			function exportConfig(){
				var DCCLBH='nhgf_sztz_xfhz.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='nhgf_sztz_xfhz.do';
				setSearchTj();//设置高级查询条件
				
				var url = "ahgf_sztz.do?method=exportXfhzList&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
		</script>
	</head>

	<body>
		<html:form action="/ahgf_sztz" method="post" styleId="zxdkSyddkForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
						<li><a href="javascript:void(0);" onclick="history.back();" class="btn_fh">返回</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>素质拓展学分汇总</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
