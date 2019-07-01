<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/ylbx/ylbxjg/js/ylbxjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"需交保险名单列表",
						pager:"pager",
						url:"rcsw_ylbx_ylbxjggl.do?method=ylbxmdManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'年级',name:'nj', index: 'nj',width:'6%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'15%'},
						   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
						   {label:'学制',name:'xz', index: 'xz',width:'5%'},
						   {label:'保险截止年份',name:'tbjzsj', index: 'tbjzsj',width:'5%'},
						   {label:'流程数据',name:'sjly', index: 'sjly',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}


		function ylbxjgView(jgid, xh) {
			showDialog("医疗保险查看", 700, 401, "rcsw_ylbx_ylbxjggl.do?method=viewOneYlbxjg&jgid=" + jgid
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ylbxjgView(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_ylbx_ylbxmd.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfigXjbx() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, ylbxjgExportData);
		}

		// 导出方法
		function ylbxjgExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_ylbx_ylbxjggl.do?method=exportConfigXjbx&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
			
		<html:form action="/rcsw_ylbx_ylbxjggl">
		
		<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
						<!-- 按钮 -->
						<div class="buttonbox">
							<ul>
					  		    
								<li><a href="#" class="btn_dc" onclick="exportConfigXjbx();return false;">导出</a></li>	
								
							</ul>
						</div>
				</logic:notEqual>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>需交保险名单列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
