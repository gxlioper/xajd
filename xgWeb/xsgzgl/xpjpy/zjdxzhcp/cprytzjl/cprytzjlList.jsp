<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"项目申请列表",
				pager:"pager",
				url:"xpjpy_cprytzjl.do?method=getCprytzjlDate",
				colList:[
						   {label:'学号',name:'xh', index: 'xh',width:'11%'},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'性别',name:'xb', index: 'xb',width:'5%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'24%'},
						   {label:'调整人',name:'tzrxm', index: 'tzrxm',width:'6%'},
						   {label:'操作时间',name:'tzsj', index: 'tzsj',width:'11%'},
						   {label:'调整备注',name:'tzbz', index: 'tzbz',width:'35%'}
						],
						multiselect:false,
						sortname: "tzsj",
					 	sortorder: "desc"
					}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			//调整记录导出
			function cprytzjlExport(){
				var DCCLBH="xpjpy_zhcp_cprytzjl.do";
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH="xpjpy_zhcp_cprytzjl.do";
				setSearchTj();//设置高级查询条件
				var url = "xpjpy_cprytzjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		<html:form action="/xpjpy_cprytzjl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:equal value="zf01" name="userName">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="cprytzjlExport();return false;">导出</a>
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
				<span><font color="blue">${cssz.xn}</font>&nbsp;&nbsp;参评人员调整记录&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>