<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"周报列表",
						pager:"pager",
						url:"rcsw_xsgzzb_xsgzzbjggl.do?method=xsgzzbjgManage&type=query&gzzblx=bj",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'填写人用户名',name:'lrr', index: 'lrr',hidden:true},
						   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
						   {label:'学年',name:'xn', index: 'xn',width:'10%'},
						   {label:'学期',name:'xqmc', index: 'xqmc',width:'7%'},
						   {label:'周次',name:'zcmc', index: 'zcmc',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'13%'},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
						   {label:'应到/实到/请假/未到<br/>（人）',name:'rstj', index: 'rstj',width:'9%'},
						   {label:'填写时间',name:'lrsj', index: 'lrsj',width:'9%'},
						   {label:'填写人',name:'lrrxm', index: 'lrrxm',width:'9%'}
						],
						sortname: "lrsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
	
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
	
			function xsgzzbjgView(jgid) {
				showDialog("周报查看", 720, 340, "rcsw_xsgzzb_xsgzzbjggl.do?method=viewXsgzzbjg&gzzblx=bj&jgid=" + jgid);
			}
	
			function bjmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xsgzzbjgView(\""
						+ rowObject["jgid"] + "\");'>" + cellValue
						+ "</a>";
			}
	
			var DCCLBH = "rcsw_bjgzzb_bjgzzbjg.do";//dcclbh,导出功能编号
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, xsgzzbjgExportData);
			}
			// 导出方法
			function xsgzzbjgExportData() {
				setSearchTj();//设置高级查询条件
				var url = "rcsw_xsgzzb_xsgzzbjggl.do?method=exportData&gzzblx=bj&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			// 打印
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="rcsw_xsgzzb_xsgzzbjggl.do?method=getDjbZip&gzzblx=bj&value="+ids;
					window.open(url);
				 } else {
					var url="rcsw_xsgzzb_xsgzzbjggl.do?method=getDjbWord&gzzblx=bj&jgid="+rows[0]["jgid"];
					window.open(url);
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
		<html:form action="/rcsw_xsgzzb_xsgzzbjggl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>			
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							<!-- 陕西师范大学 begin -->
							<logic:equal name="xxdm" value="10718">	
								<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载周报表</a></li>
							</logic:equal>
							<!-- 陕西师范大学 end -->
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>周报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
