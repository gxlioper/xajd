<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"社团列表",
						pager:"pager",
						url:"ttgl_stcygl.do?method=stcyglList&type=query",
						colList:[
						   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'团体名称',name:'stqc', index: 'stqc',width:'8%',formatter:stLink},
						   {label:'团体类型',name:'stlx', index: 'stlx',width:'15%'},
						   {label:'社团人数',name:'strs', index: 'strs',width:'15%'},
						   {label:'状态',name:'stzt', index: 'stzt',width:'15%'},
						   {label:'数据来源',name:'ly', index: 'ly',hidden:true}//来自结果表还是申请表
						],
						sortname: "stqc",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function stzz(){
			var rows = jQuery("#dataTable").getSeletRow();
            var height = jQuery(window).height()-210;
			if (rows.length != 1) {
				showAlertDivLayer("请先选择一条记录！");
			}else {
				if(rows[0]["stzt"]!="预备期"){
					showAlertDivLayer("只有预备期的社团才能发起转正申请！");
				}else{
					var url = 'ttgl_stcygl.do?method=stzz&jgid='+ rows[0]["jgid"];
					showDialog("转正申请", 790,height, url);
				}
			}
		}
		function ryManage() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请先选择一条记录！");
			}else {
				if(rows[0]["stzt"]=="预备期"||rows[0]["stzt"]=="正式"){
					var url = 'ttgl_stcygl.do?method=ryManage&jgid='+ rows[0]["jgid"];
					showDialog("社团人员管理", 790,476, url);
				}else{
					showAlertDivLayer("只有预备期和正式社团才能进行人员管理！");
				}
			}
		}

		function View(jgid,ly) {
            var height = jQuery(window).height()-210;
			showDialog("查看社团信息", 790,height, "ttgl_stcygl.do?method=view&jgid=" + jgid+"&ly="+ly);
		}

		function stLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='View(\""+ rowObject["jgid"] + "\",\""+ rowObject["ly"] + "\");'>" + cellValue
					+ "</a>";
		}

		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
		<html:form action="/xg_dwjl_dwjl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
			  		    <logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="stzz();return false;" >社团转正申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="ryManage();return false;" class="btn_xg" >团体人员管理</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>对外列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
