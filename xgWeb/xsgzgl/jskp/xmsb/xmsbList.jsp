<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsb/js/xmsb.js"></script>
				<script type="text/javascript">

					var gridSetting = {
								caption:"项目申报列表",
								pager:"pager",
								url:"jskpXmsb.do?method=getXmsbList&type=query",
								colList:[
								   {label:'项目id',name:'xmid', index: 'xmid',key:true,hidden:true},
								   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
								   {label:'申报开关',name:'sbkg', index: 'sbkg',hidden:true},
								   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'12%'},
								   {label:'项目大类',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
								   {label:'项目类别名称',name:'xmlbmc', index: 'xmlbmc',width:'10%'},
								   {label:'指导部门名称',name:'zdbmmc', index: 'zdbmmc',width:'8%'},
								   {label:'负责人',name:'fzrxm', index: 'fzrxm',width:'8%'},
								   {label:'立项时间',name:'lxsj', index: 'lxsj',width:'8%'}
								],
								sortname: "lxsj",
							 	sortorder: "desc"
					};
					var gridSetting2 = {
							caption:"项目已申报列表",
							pager:"pager",
							url:"jskpXmsb.do?method=getXmsbList&type=query",
							colList:[
								   {label:'申请id',name:'sqid', index: 'sqid',key:true,hidden:true},
							       {label:'项目id',name:'xmid', index: 'xmid',hidden:true},
								   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
								   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'12%'},
								   {label:'项目大类',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
								   {label:'项目类别名称',name:'xmlbmc', index: 'xmlbmc',width:'10%'},
								   {label:'指导部门名称',name:'zdbmmc', index: 'zdbmmc',width:'8%'},
								   {label:'负责人',name:'fzrxm', index: 'fzrxm',width:'8%'},
								   {label:'获奖时间',name:'hjsj', index: 'hjsj',width:'8%'},
								   {label:'申报时间',name:'sbsj', index: 'sbsj',width:'8%'}
							],
							sortname: "sbsj",
						 	sortorder: "desc"
					};
		
					jQuery(function(){
						var map = getSuperSearch();
						map["sbzt"]="0";
							gridSetting["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting);
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/jskpXmsb">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="0" id="sbzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="qjsh.do?method=cancel"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sb">
							<a href="javascript:void(0);" onclick="xmsb();return false;" 
							   title="选中您要申报的记录，点击该按钮可以打开申报页面。"
							   class="btn_sh">申报</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_sc" onclick="del();return false;"
							   title="选中一条记录，点击该按钮您可以删除失误的审核操作。"
							   class="btn_sc">删除</a>
						</li>
						</logic:equal>						
						<li id="li_lcgz" style="display: none;"><a href="javascript:void(0);" onclick="lcgz();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>未申报</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已申报</span></a></li>
			      </ul>
			</div>
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span id="title"> 列表 
				</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>