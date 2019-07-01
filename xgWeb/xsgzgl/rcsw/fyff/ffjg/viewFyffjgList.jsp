<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/fyff/ffjg/js/fyffjg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
		var gridSetting = {
				caption:"费用发放结果",
				pager:"pager",
				url:"rcsw_fyff_ffjg.do?method=viewFyffjgList&type=query",
				colList:[
					{label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'性别',name:'xb', index: 'xb',width:'3%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					{label:'班级',name:'bjmc', index: 'bjdm',width:'20%'},
					{label:'发放项目',name:'ffxmmc', index: 'ffxmdm',width:'16%'},
					{label:'发放金额',name:'sfje', index: 'sfje',width:'10%'},
					{label:'发放时间',name:'ffsj', index: 'ffsj',width:'18%'},
					{label:'发放途径',name:'fftjmc', index: 'fftjdm',width:'11%'}
				],
				sortname: "ffsj",
			 	sortorder: "desc"
			};
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//查询
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
		<html:form action="/rcsw_fyff_ffjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
						<li><a href="#" class="btn_dr" onclick="dr();return false;">导入</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
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
				<span>费用发放结果</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
