<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsh.js"></script>
		<script language="javascript" defer="defer">
			
		var gridSetting = {
			colList:[
                {label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
				{label:'行号',name:'r', index: 'r',width:'6%'},
				{label:'用人部门',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
				{label:'岗位名称',name:'gwmc', index: 'gwmc',width:'16%',formatter:gwmcLink},
				{label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
				{label:'需求人数',name:'xqrs', index: 'xqrs',width:'8%'},
				{label:'需求困难生数',name:'knsrs', index: 'knsrs',width:'8%'},
				{label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'11%'},
				{label:'申请时间',name:'sqsj', index: 'sqsj',width:'12%'},
				{label:'审核状态',name:'shztmcx', index: 'shztmcx',width:'8%'},
				{label:'岗位开始时间',name:'gwkssj', index: 'gwkssj',hidden:true},
				{label:'岗位结束时间',name:'gwjssj', index: 'gwjssj',hidden:true},
				{label:'是否有效岗位',name:'sfyxgw', index: 'sfyxgw',hidden:true},
				{label:'学年',name:'xn', index: 'xn',hidden:true},
				{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
				{label:'审核Id',name:'shid', index: 'shid',hidden:true},
				{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
				{label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
				{label:'审批流程',name:'sqrzgh', index: 'sqrzgh',hidden:true}
			],
			caption:"审核列表",
			pager:"pager",
			params:{shzt:"dsh"},
			url:"qgzx_gwglnew.do?method=gwshCx&type=query",
			sortname: "sqsj",
		 	sortorder: "desc"
		}

		var gridSetting2 = {
				colList:[
	                {label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'行号',name:'r', index: 'r',width:'6%'},
					{label:'用人部门',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
					{label:'岗位名称',name:'gwmc', index: 'gwmc',width:'16%',formatter:gwmcLink},
					{label:'岗位性质',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'需求人数',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'需求困难生数',name:'knsrs', index: 'knsrs',width:'8%'},
					{label:'岗位有效时',name:'gwyxs', index: 'gwyxs',width:'11%'},
					{label:'申请时间',name:'sqsj', index: 'sqsj',width:'12%'},
					{label:'审核状态',name:'shztmcx', index: 'shztmcx',width:'8%'},
					{label:'岗位开始时间',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'岗位结束时间',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'是否有效岗位',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'学年',name:'xn', index: 'xn',hidden:true},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true},
					{label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
					{label:'审批流程',name:'sqrzgh', index: 'sqrzgh',hidden:true}
				],
				caption:"审核列表",
				pager:"pager",
				params:{shzt:"ysh"},
				url:"qgzx_gwglnew.do?method=gwshCx&type=query",
				sortname: "sqsj",
			 	sortorder: "desc"
			}

		jQuery(document).ready(function(){ 
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function showView(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length==1){	
				var pkValue=rows[0]["gwdm"];
				var url="qgzx_gwglnew.do?method=gwsqXg&pkValue="+pkValue;
				url+="&doType=view";
				//showTopWin(url,720,500);
				showDialog('查看岗位申请', 720, 450, url);
			}else{
				showAlertDivLayer("请选择一条您要查看的记录！");
				return false;
			}
		}

		function gwmcLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='showViewLink(\""+rowObject["gwdm"]+"\");'>"+cellValue+"</a>";
		}
		function showViewLink(pkValue){
			var url="qgzx_gwglnew.do?method=gwsqXg&pkValue="+pkValue;
			url+="&doType=view";
			//showTopWin(url,720,500);
			showDialog('查看岗位申请', 720, 450, url);
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="shzt" value="dsh"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="showModi();return false;" class="btn_xg">审核</a>
						</li>
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<!-- 
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="showView();return false;">查看</a>
						</li>
						 -->
					</ul>
				</div>
					<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</html:form>
			<!-- 多功能操作区 end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>审核列表&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
	</body>
</html>