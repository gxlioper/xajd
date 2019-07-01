<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjjg/js/operation.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"请假结果列表",
								pager:"pager",
								url:"qjjg.do?method=list&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'请假结果id',name:'qjjgid', index: 'qjjgid',key:true,hidden:true},
								   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',hidden:true},
								   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
								   {label:'学年',name:'xn', index: 'xn'},
								   {label:'学期',name:'xqmc', index: 'xqmc'},
								   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink,width:'10%'},
								   {label:'行政班级',name:'bjmc', index: 'bjmc'},
                                    {label:'专业班级',name:'zybjmc', index: 'zybjmc'},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'请假类型',name:'qjlxmc', index: 'qjlxid'},
								   {label:'请假结束时间',name:'jssj', index: 'qjjssj',hidden:true},
								   {label:'请假开始时间',name:'kssj', index: 'qjkssj',hidden:true},
								   {label:'请假天数',name:'qjts', index: 'qjts'},
								   {label:'请假状态',name:'qjzt', index: 'qjzt',hidden:true},
								   <logic:equal name="xxdm" value="12866">
								   {label:'寝室信息',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="12866">
								   {label:'实际请假天数',name:'sjqjts', index: 'sjqjts'},
								   </logic:notEqual>
								   {label:'销假状态',name:'xjztmc', index: 'xjzt'},
								   <logic:equal name="xxdm" value="14008">
								   {label:'寝室信息',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="14008">
								   {label:'请假时间段',name:'qjsjd', index: 'qjsjd'},
								   </logic:notEqual>
								   <logic:equal name="xxdm" value="14008">
								   {label:'销假人',name:'xjrxm', index: 'xjrxm',width:'10%'},
								   </logic:equal>
								   {label:'销假状态',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'销假申请状态',name:'xjshztmc', index: 'xjshztmc',width:'10%'},
								   {label:'xjqjjgid',name:'xjqjjgid', index: 'xjqjjgid',hidden:true},
								   {label:'xjshzt',name:'xjshzt', index: 'xjshzt',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
							}
					var gridSetting2 = {
								caption:"请假结果列表",
								pager:"pager",
								url:"qjjg.do?method=list&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'请假结果id',name:'qjjgid', index: 'qjjgid',key:true,hidden:true},
								   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',hidden:true},
								   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
								   {label:'学年',name:'xn', index: 'xn'},
								   {label:'学期',name:'xqmc', index: 'xqmc'},
								   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink,width:'10%'},
								   {label:'班级',name:'bjmc', index: 'bjmc'},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'楼栋名称',name:'ldmc', index: 'lddm'},
								   {label:'寝室号',name:'qsh', index: 'qsh'},
								   {label:'请假类型',name:'qjlxmc', index: 'qjlxid'},
								   {label:'请假结束时间',name:'jssj', index: 'qjjssj',hidden:true},
								   {label:'请假开始时间',name:'kssj', index: 'qjkssj',hidden:true},
								   {label:'请假天数',name:'qjts', index: 'qjts'},
								   {label:'请假状态',name:'qjzt', index: 'qjzt',hidden:true},
								   <logic:equal name="xxdm" value="12866">
								   {label:'寝室信息',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="12866">
								   {label:'实际请假天数',name:'sjqjts', index: 'sjqjts'},
								   </logic:notEqual>
								   {label:'销假状态',name:'xjztmc', index: 'xjzt'},
								   <logic:equal name="xxdm" value="14008">
								   {label:'寝室信息',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="14008">
								   {label:'请假时间段',name:'qjsjd', index: 'qjsjd'},
								   </logic:notEqual>
								   <logic:equal name="xxdm" value="14008">
								   {label:'销假人',name:'xjrxm', index: 'xjrxm',width:'10%'},
								   </logic:equal>
								   {label:'销假状态',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'销假申请状态',name:'xjshztmc', index: 'xjshztmc',width:'10%'},
								   {label:'xjqjjgid',name:'xjqjjgid', index: 'xjqjjgid',hidden:true},
								   {label:'xjshzt',name:'xjshzt', index: 'xjshzt',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
							}						
						var map = getSuperSearch();
						if(jQuery("#xxdm").val() == '12303'){
							gridSetting2["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting2);
						}else{
							gridSetting["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting);
						}
				});
				//销假申请
				function xjsq(){
					var userStatus = jQuery("#userStatus").val();
					var myDate = jQuery("#currTime").val();
					var xxdm=jQuery("#xxdm").val();
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("请选择一条您要销假的记录！");
						return false;
					} 
					var xh =rows[0]["xh"];
					var kssj =rows[0]["kssj"];
					var qjsqid =rows[0]["qjsqid"];
					jQuery.ajaxSetup({async:false});
					var haveNewSqjl = false;
					/*
					jQuery.post("qjjg.do?method=haveNewSqjl", {
						qjsqid:qjsqid,xh:xh,kssj:kssj
					}, function(data) {
					
						if("true"==data["message"]){
							haveNewSqjl=true;
						}
					},'json');*/
					var xh=rows[0]["xh"];
					var xjzt=rows[0]["xjzt"];
					var jssj = rows[0]["jssj"];
					if(xjzt=="1"){//1表示已经销假
						showAlertDivLayer("此信息已经销假，不能重复操作！");
						return false;
					}
					var days = dateDiff(myDate,jssj);
					//重庆三峡:未销假超过8天，提示必须续假
					if(days>jQuery("#xjts").val()&&"14008"==xxdm&&"xx"!=userStatus&&false==haveNewSqjl){
						showAlertDivLayer("请假未销假超过"+jQuery("#xjts").val()+"天，必须续假！");
						return false;
					}
					//重庆三峡医高专：销假个性化 
					//需求 XG_2016-0135 RA002 根据天数和请假审批流程销假提示取消 20161215
					//	var qjts = rows[0]["qjts"];
					//	var qjzt = rows[0]["qjzt"];	
					//	var nzcs = false;
					//	if("14008" == xxdm && "0" == qjzt && "xx"!=userStatus) {
					//		var xjUrl = "qjjg.do?method=xjrpdOneToN";
					//		if(qjts > 9){
					//			xjUrl = "qjjg.do?method=xjrpdTenD";
					//		}
					//		jQuery.post(xjUrl, {
					//			qjsqid:qjsqid,xh:xh
					//		}, function(data) {
					//			if("true"==data["message"]){
					//				nzcs=true;
					//			}
					//		},'json');
					//		if(true == nzcs) {
					//			showAlertDivLayer("您无权对该学生进行销假操作，请确认！");
					//			return false;
					//		}
					//	}

					var url = 'qjgl_xjsh.do?method=xjsqAdd&xh='+xh+'&qjjgid=' + rows[0]["qjjgid"];
					var title = "销假申请";
					showDialog(title, 800,450, url);
					jQuery.ajaxSetup({async:true});
				}
				/**
				 * 撤销
				 * @return
				 */
				function cancle(){
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						return showAlertDivLayer("请选择一条要撤销的记录！");
					} else {
						if(rows[0]["xjshzt"] != "5"){
							return showAlertDivLayer("只有审核中的记录才能被撤销！");
						}
						showConfirmDivLayer("您确定要撤销选择的记录吗？", {
							"okFun" : function() {
								jQuery.post("qjgl_xjsh.do?method=cancelSq", {
									qjjgid : rows[0]['qjjgid'],
									lcid : rows[0]['splc']
								}, function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
							}
						});
					}
				}
				function submitBusi(){
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlert("请选择一条要提交的记录！");
						return false;
					} else {
						if(rows[0]["xjshzt"] != "0" && rows[0]["xjshzt"] != "3"){
							return showAlertDivLayer("只有已退回或未提交的记录才能提交！");
						}
						showConfirmDivLayer("您确定要提交选择的记录吗？", {
							"okFun" : function() {
								jQuery.post("qjgl_xjsh.do?method=submit", {
									qjjgid : rows[0]['qjjgid'],
								}, function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
							}
						});
					}
				}
				//删除
				function delxj(){
					var ids = jQuery("#dataTable").getSeletIds();
					if (ids.length == 0){
						showAlertDivLayer("请选择您要删除的记录！");
					} else {
						var flagstr = "";
						var rows = jQuery("#dataTable").getSeletRow();
						jQuery(rows).each(function(i,row){
							if(row["xjshzt"] == "x"){
								flagstr = "存在未填写的销假申请，不允许删除！"
								return;
							}else if(row["xjshzt"] != "0" && row["xjshzt"] != "3"){
								flagstr = "存在审核流中的销假申请，不允许删除！"
								return;
							}
						})
						if(flagstr != ""){
							showAlertDivLayer(flagstr);
							return false;
						}
						showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("qjgl_xjsh.do?method=del",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
						}});
					}
				}

				/*
				 * 流程跟踪
				 */
				function lcgz() {
					var rows = jQuery("#dataTable").getSeletRow();
					if (1 != rows.length) {
						showAlertDivLayer("请选择一条流程跟踪记录！");
						return false;
					} else {
						if(rows[0]["xjshzt"] == "0" || rows[0]["xjshzt"] == "x"){
							showAlertDivLayer(jQuery("#lable_wxglcxx").val());
							return false;
						}
						showDialog("申请审批流程跟踪",530,310, 'qjgl_xjsh.do?method=lcgz&sqid='
								+ rows[0]['qjjgid']);
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
	<html:form action="/qjjg?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden"  name="currTime" id="currTime" value="${currTime}"/>
		<input type="hidden"  name="xjts" id="xjts" value="${xjts}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:notEqual value="stu" name="usertype">
						<logic:equal name="writeAble" value="yes">	
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="xjcl();return false;" class="btn_shbtg">销假</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					
						<li>
							<a href="javascript:void(0);" onclick="printQjjgb('qjjg.do?method=printQjjgb');return false;" class="btn_down">下载请假申请表</a>
						</li>   
						<%--<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>--%>
					
					<!-- 浙江警官职业学院，个性化打印请假审批表打印 -->	   
					<logic:equal name="xxdm" value="12869">
						<li>
							<a href="javascript:void(0);" onclick="printXsqjspb();return false;" class="btn_down">请假审批表</a>
						</li>
					</logic:equal> 
					</logic:notEqual>
				
					<%--学生端销假申请，走审核流 --%>
					<logic:equal value="stu" name="usertype">
					<logic:equal value="true" name="xjadmit">
						<li>
							<a href="javascript:void(0);" onclick="xjsq();return false;" class="btn_shbtg">销假申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销销假申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delxj();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
					</logic:equal>
					<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>
					</logic:equal>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 请假结果列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
