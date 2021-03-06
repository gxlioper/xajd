<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
			<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- 高级查询时间需要 -->
			<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jbfgl/js/jbfgl.js"></script>
			<script type="text/javascript">
				var gridSetting = {
					caption : "展示信息列表",
					pager : "pager",
					url : "xsxwkhJbfgl.do?method=getjbflist&type=query",
					colList:[										
					    { label:'jbfid',name:'jbfid', index: 'jbfid',hidden:true,key:true},
						{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter:xhLink},
						{ label : '学年', name : 'xn', index : 'xn', width : '10%'},
						{ label : '姓名', name : 'xm', index : 'xm', width : '8%' },
						{ label : '学院', name : 'xymc', index : 'xymc', width : '12%'},
						{ label : '班级', name : 'bjmc', index : 'bjmc', width : '10%'},
						{ label : '班主任辅导员测评等级', name : 'bzrdj', index : 'bzrcpdj', width : '10%'},
						{ label : '班级学生测评等级', name : 'xsdj', index : 'xscpdj', width : '10%'},
						{ label : '总分', name : 'zf', index : 'zf', width : '8%'}
						],
						sortname: "zf,xn,xymc,bjmc",
				 		sortorder: "desc"
				 	}	
				jQuery(function(){
					var map = getSuperSearch();
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
							
				});
	
				function xhLink(cellValue,rowObject){
					return "<a href='javascript:void(0);' class='name' onClick='viewJbfgl(\""+rowObject["jbfid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
				
				}
				function searchRs(){
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
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">		
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="#" onclick="importXX();return false;" class="btn_dr">导入</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_dc" onclick="exportXX();return false;">导出</a>
						</li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>基本分信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	
	</body>
</html>
