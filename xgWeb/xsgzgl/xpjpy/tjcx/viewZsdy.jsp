<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"数据列表",
				pager:"pager",
				url:"xpj_tjcx.do?method=viewZsdy&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'学号',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
					//{label:'性别',name:'xb', index: 'xb',width:'3%'},
					//{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
					//{label:'年级',name:'nj', index: 'nj',width:'7%'},
					//{label:'班级',name:'bjmc', index: 'bjdm',width:'28%'},
					//{label:'评奖周期',name:'pjzq', index: 'pjzq',width:'13%'},
					{label:'奖项',name:'xmmc', index: 'xmmc',width:'25%'},
					{label:'姓名英文',name:'xmpy', index: 'xmpy',width:'20%'},
					{label:'奖项英文',name:'xmywmc', index: 'xmywmc',width:'30%'}
					//{label:'金额',name:'xmje', index: 'xmje',width:'11%'}
				],
				sortname: "XSXH asc,xm",
			 	sortorder: "asc"
			}
			//高级查询
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			//评奖结果查看
			function xhView(id,xh){
				showDialog("评奖结果查询",800,473,"xpj_pjjg.do?method=pjxmjgView&id="+id+"&xh="+xh);
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xhView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			//证书打印软件下载
			function exportZs(){
				/*var url="xpj_tjcx.do?method=exportZs";
		 		window.open(url);*/

		 		setSearchTj();//设置高级查询条件
		 		var url = "xpj_tjcx.do?method=exportZsNew";
		 		url = addSuperSearchParams(url);//设置高级查询参数
		 		jQuery("form").eq(0).attr("action", url);
		 		jQuery("form").eq(0).submit(); 
			}

			//校设奖学金打印数据下载
			function exportXsjxj(){
				var lxmc='002';
				exportData(lxmc);
			}

			//外设奖学金打印数据下载
			function exportWsjxj(){
				var lxmc='001';
				exportData(lxmc);
			}

			//荣誉称号打印数据下载
			function exportRych(){
				var lxmc='004';
				exportData(lxmc);
			}

			function exportData(lxmc){
				var DCCLBH = "pj_zsdy.do";
				var url = "xpj_tjcx.do?method=exportData&dcclbh=" + DCCLBH+"&lxmc="+lxmc;//dcclbh,导出功能编号
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_tjcx">		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportZs();return false;" class="btn_dc">证书打印下载</a>
						</li>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="exportXsjxj();return false;" class="btn_dc">校设奖学金打印数据下载</a>--%>
<%--						</li>--%>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="exportWsjxj();return false;" class="btn_dc">外设奖学金打印数据下载</a>--%>
<%--						</li>--%>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="exportRych();return false;" class="btn_dc">荣誉称号打印数据下载</a>--%>
<%--						</li>--%>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>数据列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
