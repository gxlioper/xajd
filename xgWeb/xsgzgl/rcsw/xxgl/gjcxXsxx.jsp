<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xxgl/xsxxgl.js"></script>
		<script type="text/javascript">
		//初始化查询
		var gridSetting = {
				caption:"学生献血信息列表",
				pager:"pager",
				url:"rcsw_xsxxgl.do?method=gjcxXxgl&type=query",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'专业',name:'zymc', index: 'zymc',width:'15%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
				   {label:'献血时间',name:'xxsj', index: 'xxsj',width:'15%'},
				   {label:'主键',name:'xxgldm', index: 'xxgldm',key:true,hidden:true}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
				
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//为button注册事件
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				//jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
			
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>	
					<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入</a></li>	
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="xsxxxxwhExportConfig();return false;">导出</a></li>				
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
				<span>学生献血管理列表 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
