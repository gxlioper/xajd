<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxsq.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"党团申请列表",
						pager:"pager",
						url:"dtxxsq.do?method=list&type=query",
						colList:[
						   {label:'申请id',name:'dtxxsqid', index: 'dtxxsqid',key:true,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'年级',name:'nj', index: 'nj'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
                            {label:'书院',name:'symc', index: 'symc'},
                            {label:'行政班级',name:'bjmc', index: 'bjmc'},
                            {label:'专业班级',name:'zybjmc', index: 'zybjmc'},
						   {label:'性别',name:'xb', index: 'xb'},
						   {label:'申请阶段名称',name:'jdmc', index: 'jdmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'审核状态',name:'shztmc', index: 'shztmc'},
						   <logic:equal value="13871" name="xxdm">
						   {label:'入党志愿书编号',name:'zd3', index: 'zd3'},
						   </logic:equal>
						   {label:'jddm',name:'jddm', index: 'jddm',hidden:true},
						   {label:'splc',name:'splc',index:'splc',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
					}
					jQuery(function(){
						jQuery("#dataTable").initGrid(gridSetting);
					});

					function submitBusi(){
						var ids = jQuery("#dataTable").getSeletIds();
						if (ids.length != 1){
							showAlertDivLayer("请选择一条您要提交的记录！");
						} else {
							var rows = jQuery("#dataTable").getSeletRow();
							var shzt = rows[0]["shzt"];
							if(shzt!="0" && shzt!="3"){
								showAlertDivLayer("请选择未提交或退回的记录！");
								return false;
							}
							showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
									if(shzt!="3"){
										// 验证是否可提交
										jQuery.post("dtxxsq.do?method=checkSfktj", {
											jddm:rows[0]["jddm"]
										}, function(data) {
											if(data ==null || data=="false"){
												showAlertDivLayer("您申请的阶段已关闭申请，无法提交，详情请联系管理员。");
												return false;
											}else{
	
												// 提交
												jQuery.post("dtxxsq.do?method=submitDtxx",
													{values:ids.toString(),
													 xh : rows[0]['xh']
													},function(data){
													showAlertDivLayer(data["message"]);
													jQuery("#dataTable").reloadGrid();
												},'json');
											}
										});
										
									}else{

										jQuery.post("dtxxsq.do?method=submitDtxx",
											{values:ids.toString(),
											 xh : rows[0]['xh']
											},function(data){
											showAlertDivLayer(data["message"]);
											jQuery("#dataTable").reloadGrid();
										},'json');
									}
							}});
							
						}
					}

					function cancel(){
						var ids = jQuery("#dataTable").getSeletIds();
						if (ids.length == 0) {
							showAlertDivLayer("请选择您要撤销的记录！");
						} else if (ids.length >1 ) {
							showAlertDivLayer("请选择一条您要撤销的记录！");
						} else {
							var rows = jQuery("#dataTable").getSeletRow();
							for(var i=0;i<ids.length;i++){
								if(rows[i]['shzt']!='5'){
									showAlertDivLayer("只有审核中的记录才能被撤销！");
									return false;
								}
							}
							showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
								jQuery.post("dtxxsq.do?method=cancelDtxxsq",
									{
									 values:ids.toString(),
									 splcid : rows[0]['splc'] 
									},function(data){
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								},'json');
							}});
						}
						
					}

					//导出团员发展申请表
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
								url += "&dtxxsqid=" + ids+"&flag=sq" + "&jdmc="+rows[0]["jdmc"];
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
							url += "&value=" + ids+"&flag=sq";
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
	<html:form action="/dtxxsq?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="dtxxsqLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="exportTysqb();return false;" class="btn_down">下载党员、团员发展申请表</a></li>
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
				<span>党团信息申请列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
