<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"党团待审核列表",
						pager:"pager",
						url:"dtxxsh.do?method=list&type=query",
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
						   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核人',name:'shr', index: 'shr',hidden:true},
						   {label:'审核状态',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'jddm',name:'jddm', index: 'jddm',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
				}
				var gridSetting2 = {
						caption:"党团已审核列表",
						pager:"pager",
						url:"dtxxsh.do?method=list&type=query",
						colList:[
						   {label:'申请id',name:'dtxxsqid', index: 'dtxxsqid',key:true,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'姓名',name:'xm', index: 'xm'},
						   {label:'年级',name:'nj', index: 'nj'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'班级',name:'bjmc', index: 'bjmc'},
						   {label:'性别',name:'xb', index: 'xb'},
						   {label:'申请阶段名称',name:'jdmc', index: 'jdmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
						   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核人',name:'shr', index: 'shr',hidden:true},
						   {label:'审核状态',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'jddm',name:'jddm', index: 'jddm',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
				}
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
						jQuery("#btn_qxsh").click({splc:"splc",sfkq:"1",dtxxsqid:"dtxxsqid"},cxshnews_splc);
					});
				
				/*
				 * 撤销
				 */
				function cxshnews_splc(obj){
					var sfkq=obj.data.sfkq;
					var rows = jQuery("#dataTable").getSeletRow();
					if(sfkq=="1"){//开启 则最后一级可撤销
						if (rows.length != 1){
							alertInfo("请选择一条您要撤销审核的记录！");
						} else {
							splc_cx_news(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);
						}
					}else{
						splc_cxs(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);

					}
				}
				/*
				 * 审批流程撤销[最后一级可撤销]
				 * shid 审核id
				 * splc 审批流程id 
				 */
				function splc_cx_news(splc,shid,dtxxsqid){
					//最后一级撤销审核后调用的路径
					var cancelPath = jQuery("#cancelPath").val();
					confirmInfo("您确定要撤销操作吗?",function(ty){
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
									// 判断是否最后一级撤销(1:最后一级撤销成功）
									if("1" == data["cancelFlg"]){
										jQuery.post(cancelPath,{splc:splc,shid:shid,dtxxsqid:dtxxsqid},function(result){
											showAlertDivLayer(result["message"],{},{"clkFun":function(){
												jQuery("#dataTable").reloadGrid();
											}});
										},'json');
									}else{
										showAlertDivLayer(data["message"],{},{"clkFun":function(){
											jQuery("#dataTable").reloadGrid();
										}});
									}
								
							},'json');
						}
					});
				}

				/*
				 * 审批流程撤销
				 * shid 审核id
				 * splc 审批流程id 
				 */
				function splc_cxs(splc,shid,dtxxsqid){
					confirmInfo("您确定要撤销操作吗?",function(ty){
						//alert(ty);
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxsh",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									//if (parent.window){
										//refersh();
										jQuery("#dataTable").reloadGrid();
									//}
								}});
							},'json');
						}
					});
				}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/dtxxsh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="dtxxsh.do?method=cancel"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="dtxxsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_qxsh"
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			</div>
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span id="title"> 请假待审核列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
