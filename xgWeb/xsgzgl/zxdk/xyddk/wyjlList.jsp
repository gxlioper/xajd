<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/wyjl.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- 高级查询时间需要 -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"项目维护列表 ",
					pager:"pager",
					url:"zxdkWyjl.do?method=getWyjlList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'学号',name:'xh', index: 'xh',width:'13%',key:true,formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckWyjl('"+rowObject["xh"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                        {label:'书院',name:'symc', index: 'sydm',width:'13%'},
                        {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybj',width:'13%'},
					   {label:'手机号码',name:'sjhm', index: 'sjhm'},
					   {label:'QQ',name:'qqhm', index: 'qqhm'},
					   {label:'违约时间',name:'wysj',index:'wysj'},
					   {label:'是否联系到',name:'wyzt',index:'wyzt'}
					],
					sortname : "xh",
				    sortorder : "asc"
				}

				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			})
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
							<li><a href="javascript:void(0);" class="btn_zj" onclick="addWyjl();return false;">增加</a></li>
							<li><a href="javascript:void(0);" class="btn_xg" onclick="editWyjl();return false;">修改</a></li>
							<li><a href="javascript:void(0);" class="btn_sc" onclick="delWyjl();return false;">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="importYwxx()();return false;">导入违约名单</a></li>
							
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
								</li>
							</logic:equal>
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
				<span>项目维护列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
