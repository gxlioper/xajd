<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		        <script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxjg.js"></script>
				<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
				<script type="text/javascript">
				var gridSetting = {
						caption:"党团结果列表",
						pager:"pager",
						url:"dtxxjg.do?method=list&type=query",
						colList:[
						   {label:'结果id',name:'dtxxjgid', index: 'dtxxjgid',key:true,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'年级',name:'nj', index: 'nj'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
                            {label:'书院',name:'symc', index: 'symc'},
                            {label:'行政班级',name:'bjmc', index: 'bjmc'},
                            {label:'专业班级',name:'zybjmc', index: 'zybjmc'},
						   {label:'性别',name:'xb', index: 'xb'},
						   {label:'当前阶段名称',name:'jdmc', index: 'jdmc'},
						   {label:'开始时间',name:'kssj', index: 'kssj'},
						   <logic:equal value="13871" name="xxdm">
						   {label:'入党志愿书编号',name:'zysbh', index: 'zysbh'},
						   </logic:equal>
						   {label:'记录时间',name:'jlsj', index: 'jlsj',hidden:true},
						   {label:'申请id',name:'sqid', index: 'sqid',hidden:true},
						   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true}
						],
						sortname: "jlsj",
					 	sortorder: "asc"
					}
					jQuery(function(){
						jQuery("#dataTable").initGrid(gridSetting);
					});
					//导出团员/党员发展申请表
					function exportTysqb(){
						var ids = jQuery("#dataTable").getSeletIds();
						var rows = jQuery("#dataTable").getSeletRow();
						var jdmc = rows[0]["jdmc"];	
						var len = ids.length;
						if (len == 1) {
							if(rows[0]["jdmc"] != "入团申请" && rows[0]["jdmc"] != "入党积极分子" ){
								return showAlertDivLayer("请选择入团申请或入党积极分子记录！");
							}
							if(rows[0]["jdmc"] == "入团申请" || rows[0]["jdmc"] == "入党积极分子"){
								var url = "dtxxsq.do?method=getRtsqb";
								url += "&dtxxsqid=" + ids+"&flag=jg" + "&jdmc=" + rows[0]["jdmc"];
							}
							window.open(url);
						} else if (len == 0) {
							showAlertDivLayer("请选择您要下载的记录！");
							return false;
						} else {
							for(var i=0;i < rows.length;i++){
								if(rows[i]["jdmc"] != "入团申请" &&rows[i]["jdmc"] != "入党积极分子"){
									return showAlertDivLayer("请选择入团申请或入党积极分子记录！");
								}
							}
							var url = "dtxxsq.do?method=getRtsqbZip";
							url += "&value=" + ids+"&flag=jg";
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
	<html:form action="/dtxxjg?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li><a href="#" class="btn_sx" onclick="tbgxzzmm();return false;">同步更新政治面貌</a></li>
						<li><a href="#" onclick="toImportData('IMPORT_N100110');return false;" class="btn_dr">导入数据</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportJjfzConfig();return false;">积极分子备案登记</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportFzdxConfig();return false;">发展对象备案登记</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportYbdyConfig();return false;">预备党员接收登记</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportYbzzConfig();return false;">预备党员转正请示</a></li>
						<logic:equal value="12865" name="xxdm">
						   <li><a href="javascript:void(0);" class="btn_dc" onclick="mcexport();return false;">拟发展预备党员名册</a></li>
						</logic:equal>
						<logic:equal value="14008" name="xxdm">
						   <li><a href="javascript:void(0);" class="btn_down" onclick="getYsqs();return false;">预审请示</a></li>
						   <li><a href="javascript:void(0);" class="btn_down" onclick="getXsyb();return false;">吸收预备</a></li>
						   <li><a href="javascript:void(0);" class="btn_down" onclick="getYbzz();return false;">预备转正</a></li>
						</logic:equal>	
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="exportTysqb();return false;" class="btn_down">下载党员、团员发展申请表</a></li>
						</logic:equal>
					</logic:equal>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>党团信息结果列表</span>

			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
