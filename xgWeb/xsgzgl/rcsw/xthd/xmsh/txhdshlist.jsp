<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsh/js/txhdsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"集体评奖待审核列表",
						pager:"pager",
						url:"rcsw_txhd_sh.do?method=list&type=query",
						colList:[
						   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'姓名 ',name:'xm', index: 'xm'},
						   {label:'性别',name:'xb', index: 'xb'},
						   {label:'<bean:message key="lable.xb" />名称',name:'xymc', index: 'xymc'},
						   {label:'班级名称',name:'bjmc', index: 'bjmc'},
						   {label:'申请项目',name:'xmmc', index: 'xmmc'},
						   {label:'申请时间',name:'sqsj', index: 'sqsj'},
						   {label:'岗位id',name:'gwid', index: 'gwid',hidden:true},
						   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
						   {label:'审核状态',name:'shztmc', index: 'shzt',formatter:shzt},
						   {label:'审核人名称',name:'mc', index: 'mc',hidden:true},
						   {label:'splc',name:'splc', index: 'splc',hidden:true},
						   {label:'shid',name:'shid', index: 'shid',hidden:true}
								],
						sortname: "sqsj",
					 	sortorder: "asc"
					}
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
						jQuery("#btn_qxsh").click(function (){
							var rows = jQuery("#dataTable").getSeletRow();
							if (rows.length != 1){
								showAlertDivLayer("请选择一条您要撤消的审核记录！");
								return false;
							}
							var obj=new Object(0);
							obj["data"]={splc:"splc",sfkq:"1"};
							cxshnew_splc(obj);
						});
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcsw_txhd_sh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reloadsh()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="rcsw_txhd_sh.do?method=cancelSh"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="rcsw_txhd_sh();return false;" 
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
				<span id="title">团学待审核列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
