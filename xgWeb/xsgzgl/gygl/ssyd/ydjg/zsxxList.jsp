<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function xhLink(cellValue,row){
				return "<a href='javascript:void(0);' class='name' onClick='seeXsydInfo(\""+row["xh"]+"\")'>"+cellValue+"</a>";
			}
			//查看学生异动信息
			function seeXsydInfo(xh){
				showDialog("查看学生住宿信息",650,480,"ydjg.do?method=ckXsydInfoLsxx&xh="+xh,null);
			}
			
			function ckYdjg(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要查看的记录！");
				}  else{
					showDialog("住宿历史信息",760,390,'ydjg.do?method=zsxxck&ssydid='+rows[0]["ssydid"]);
				}
			}

			function exportConfig() {
				customExport("lsxxgl_lsxxgl_zslsxxgl.do", exportData,690,500);
			}
			// 导出方法
			function exportData() {
				setSearchTj();//设置高级查询条件
				var url = "ydjg.do?method=exportData&dcclbh=ydjgbase.do";//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}

			//查看退宿床位信息
			function tscwLink(cellValue,row){
				if( cellValue!=null && cellValue!=""){
					return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydqlddm"]+"\",\""+row["ydqqsh"]+"\",\""+row["ydqcwh"]+"\")'>"+cellValue+"</a>";
				}else{
					return "";
				}
			}

			//查看入住床位信息
			function rzcwLink(cellValue,row){
				if( cellValue!=null && cellValue!=""){
					return "<a href='javascript:void(0);' class='name' onClick='seeCwInfo(\""+row["ydhlddm"]+"\",\""+row["ydhqsh"]+"\",\""+row["ydhcwh"]+"\")'>"+cellValue+"</a>";
				}else{
					return "";
				}
			}


			//入住时间
			function rzsjLink(cellValue,row){
				if( cellValue!=null && cellValue!=""){
					return cellValue.substr(0,10);
				}else{
					return "";
				}
			}
						
			//备注过长截取
			function bzSubstring(cellValue,row){
				if(cellValue==null){
					cellValue="";
				}
				var strValue = cellValue;
				if(strValue.length > 10){
					strValue = strValue.substring(0, 10)+"...";
				}
				return "<span title='"+cellValue+"'>"+strValue+"</span>";;
			}

			//查看床位信息
			function seeCwInfo(lddm,qsh,cwh){
				showDialog("查看床位信息",850,350,"ydjg.do?method=ckQsydInfo&ydqlddm="+lddm+"&ydqqsh="+qsh+"&ydqcwh="+cwh,null);
			}

			//查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			jQuery(function(){
				//初始化查询
				var gridSetting = {
						caption:"住宿历史信息列表",
						pager:"pager",
						url:"ydjg.do?method=zslslist&type=query",
						colList:[
								   {label:'学号',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'姓名',name:'xm', index: 'xm'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
								   {label:'性别',name:'xb', index: 'xb'},
								   {label:'变更情况',name:'ssydlxmc', index: 'ssydlxmc'},
								   {label:'原寝室',name:'tsqs', index: 'tsqs',formatter:tscwLink},
								   {label:'新寝室',name:'rzqs', index: 'rzqs',formatter:rzcwLink},
								   {label:'变更时间',name:'tstzsj', index: 'tstzsj',formatter:rzsjLink},
								   {label:'记录时间',name:'czsj', index: 'czsj',formatter:rzsjLink,hidden:true},
								   {label:'备注',name:'bz', index: 'bz',title:'bz',formatter:bzSubstring},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},	
								   {label:'ydqlddm',name:'ydqlddm', index: 'ydqlddm',hidden:true},	
								   {label:'ydqqsh',name:'ydqqsh', index: 'ydqqsh',hidden:true},	
								   {label:'ydqcwh',name:'ydqcwh', index: 'ydqcwh',hidden:true},	
								   {label:'ydhlddm',name:'ydhlddm', index: 'ydhlddm',hidden:true},	
								   {label:'ydhqsh',name:'ydhqsh', index: 'ydhqsh',hidden:true},	
								   {label:'ydhcwh',name:'ydhcwh', index: 'ydhcwh',hidden:true},	
								   {label:'ssydid',name:'ssydid', index: 'ssydid',key:true,hidden:true}
						],
						sortname: "czsj",
					 	sortorder: "desc"
				}
								
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="ckYdjg();return false;" class="btn_ck">查看</a>
						</li>
						<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>住宿历史信息列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
