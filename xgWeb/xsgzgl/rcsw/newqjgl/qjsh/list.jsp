<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
				<script type="text/javascript">

					var gridSetting = {
								caption:"请假待审核列表",
								pager:"pager",
								url:"qjsh.do?method=list&type=query",
								colList:[
								   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
								   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
								   {label:'学年',name:'xn', index: 'xn',width:'12%'},
								   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
								   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
								   {label:'行政班级',name:'bjmc', index: 'bjmc',width:'8%'},
                                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'8%'},
								   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
								   {label:'请假类型',name:'qjlxmc', index: 'qjlxid',width:'8%'},
								   {label:'请假天数',name:'qjts', index: 'qjts',width:'8%'},
								   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'8%'},
								   <logic:equal name="xxdm" value="12866">
								   		{label:'请假开始时间',name:'kssj', index: 'kssj',width:'10%'},
								   		{label:'请假结束时间',name:'jssj', index: 'jssj',width:'10%'},
								   	</logic:equal>
								   {label:'审核状态',name:'shztmc', index: 'shzt',width:'10%'},
								   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
								   {label:'审核人',name:'shr', index: 'shr',hidden:true},
								   {label:'shid',name:'shid', index: 'shid',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
					}
					var gridSetting2 = {
							caption:"请假已审核列表",
							pager:"pager",
							url:"qjsh.do?method=list&type=query",
							colList:[
							   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
							   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
							   {label:'学年',name:'xn', index: 'xn',width:'10%'},
							   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
							   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
							   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
							   {label:'请假类型',name:'qjlxmc', index: 'qjlxid',width:'10%'},
							   {label:'请假天数',name:'qjts', index: 'qjts',width:'10%'},
							   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'10%'},
							   <logic:equal name="xxdm" value="12866">
							   		{label:'请假开始时间',name:'kssj', index: 'kssj',width:'10%'},
							   		{label:'请假结束时间',name:'jssj', index: 'jssj',width:'10%'},
							   	</logic:equal>
							   {label:'审核状态',name:'shztmc', index: 'shzt',width:'10%'},
							   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
							   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
							   {label:'审核人',name:'shr', index: 'shr',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
					}

					var gridSetting3 = {
							caption:"请假待审核列表",
							pager:"pager",
							url:"qjsh.do?method=list&type=query",
							colList:[
							   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
							   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
							   {label:'学年',name:'xn', index: 'xn',width:'12%',formatter:bs},
							   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%',formatter:bs},
							   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhFormat},
							   {label:'班级',name:'bjmc', index: 'bjmc',width:'8%',formatter:bs},
							   {label:'姓名',name:'xm', index: 'xm',width:'8%',formatter:bs},
							   {label:'请假类型',name:'qjlxmc', index: 'qjlxid',width:'8%',formatter:bs},
							   {label:'请假天数',name:'qjts', index: 'qjts',width:'8%',formatter:bs},
							   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'8%',formatter:bs},							   
							   {label:'请假开始时间',name:'kssj', index: 'kssj',width:'10%',formatter:bs},
							   {label:'请假结束时间',name:'jssj', index: 'jssj',width:'10%',formatter:bs},							  
							   {label:'审核状态',name:'shztmc', index: 'shzt',width:'10%',formatter:csFormat},
							   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
							   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
							   {label:'审核人',name:'shr', index: 'shr',hidden:true},
							   {label:'sfcs',name:'sfcs', index: 'sfcs',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
				}
				var gridSetting4 = {
								caption:"请假待审核列表",
								pager:"pager",
								url:"qjsh.do?method=list&type=query",
								colList:[
								   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
								   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
								   {label:'学年',name:'xn', index: 'xn',width:'12%'},
								   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
								   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
								   {label:'班级',name:'bjmc', index: 'bjmc',width:'8%'},
								   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
								   {label:'楼栋名称',name:'ldmc', index: 'lddm'},
								   {label:'寝室号',name:'qsh', index: 'qsh'},
								   {label:'请假类型',name:'qjlxmc', index: 'qjlxid',width:'8%'},
								   {label:'请假天数',name:'qjts', index: 'qjts',width:'8%'},
								   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'8%'},
								   <logic:equal name="xxdm" value="12866">
								   		{label:'请假开始时间',name:'kssj', index: 'kssj',width:'10%'},
								   		{label:'请假结束时间',name:'jssj', index: 'jssj',width:'10%'},
								   	</logic:equal>
								   {label:'审核状态',name:'shztmc', index: 'shzt',width:'10%'},
								   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
								   {label:'审核人',name:'shr', index: 'shr',hidden:true},
								   {label:'shid',name:'shid', index: 'shid',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
					}
					var gridSetting5 = {
							caption:"请假已审核列表",
							pager:"pager",
							url:"qjsh.do?method=list&type=query",
							colList:[
							   {label:'请假申请id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
							   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
							   {label:'学年',name:'xn', index: 'xn',width:'10%'},
							   {label:'学期',name:'xqmc', index: 'xqmc',width:'8%'},
							   {label:'学号',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
							   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
							   {label:'楼栋名称',name:'ldmc', index: 'lddm'},
							   {label:'寝室号',name:'qsh', index: 'qsh'},
							   {label:'请假类型',name:'qjlxmc', index: 'qjlxid',width:'10%'},
							   {label:'请假天数',name:'qjts', index: 'qjts',width:'10%'},
							   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'10%'},
							   <logic:equal name="xxdm" value="12866">
							   		{label:'请假开始时间',name:'kssj', index: 'kssj',width:'10%'},
							   		{label:'请假结束时间',name:'jssj', index: 'jssj',width:'10%'},
							   	</logic:equal>
							   {label:'审核状态',name:'shztmc', index: 'shzt',width:'10%'},
							   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
							   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
							   {label:'审核人',name:'shr', index: 'shr',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
					}

					function bs(cellValue, rowObject){
						var sfcs = rowObject["sfcs"];
						if(sfcs == '1'){
							return '<font color="red">'+cellValue+'</font>';
						}else{
							return cellValue;
						}
					}
					
					function csFormat(cellValue, rowObject){
						var sfcs = rowObject["sfcs"];
						if(sfcs == '1'){
							var tds = jQuery(rowObject).find('td');
							return '<span><font color="red">审核超时</font></span>';
						}else{
							return cellValue;
						}
					}

					function xhFormat(cellValue, rowObject){
						var sfcs = rowObject["sfcs"];
						var qjsqid = rowObject["qjsqid"];
						if(sfcs == '1'){
							return  "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
							+ "')\" class='name'><font color=\"red\">" + cellValue + "</font></a>";
						}else{
							return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
									+ "')\" class='name'>" + cellValue + "</a>";
						}
					}
					
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						if(jQuery("#xxdm").val() == '12866'){
							gridSetting3["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting3);
						}else if(jQuery("#xxdm").val() == '12303'){
							gridSetting4["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting4);
						}else{
							gridSetting["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting);
						}
						jQuery("#btn_qxsh").click(function (){
							var rows = jQuery("#dataTable").getSeletRow();
							if (rows.length != 1){
								showAlertDivLayer("请选择一条您要撤消的审核记录！");
								return false;
							} else {
								var xjzt = rows[0]["xjzt"];
								if(xjzt=="1"){
									showAlertDivLayer("此申请已经销假，不能撤销！");
									return false;
								}
							}
							var obj=new Object(0);
							obj["data"]={splc:"splcid",sfkq:"1"};
							cxqjsh(rows[0]["qjsqid"],rows[0][obj.data.splc],rows[0]["shid"]);
						});
					});
					function cxqjsh(qjsqid,splc,shid){
						//最后一级撤销审核后调用的路径
						var cancelPath = jQuery("#cancelPath").val();
						confirmInfo("您确定要撤销操作吗?",function(ty){
							if(ty=="ok"){
								jQuery.post("qjsh.do?method=cxshnew",{qjsqid:qjsqid,splcid:splc,shid:shid},function(data){
										// 判断是否最后一级撤销(1:最后一级撤销成功）
										if("1" == data["cancelFlg"]){
											jQuery.post(cancelPath,{splcid:splc,shid:shid},function(result){
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


					function savePlsh(shzt,shyj){
						
						var rows = jQuery("#dataTable").getSeletRow();
						var guid = new Array();
						var gwids = new Array();
						var xhs = new Array();
						
						jQuery.each(rows,function(i,row){
							guid.push(row["qjsqid"]);
							gwids.push(row["gwid"]);
							xhs.push(row["xh"]);
						});

						jQuery.post(
							"qjsh.do?method=savePlsh",
							{
							 shzt:shzt,
							 id:guid,
							 gwids:gwids,
							 xhs:xhs,
							 shyj:shyj
							},function(data){
								
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},
							'json'
						);
					}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/qjsh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="qjsh.do?method=cancel"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="qjsh();return false;" 
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
				<span id="title"> 请假待审核列表 
				<logic:equal value="12866" name="xxdm">								
						<font color="red">（已超过开始时间的未提交和未审核完的将标红显示） </font>					
				</logic:equal>
				</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
