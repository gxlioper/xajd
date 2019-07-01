<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/fswh/js/jsfs.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var colList="";
				var gridSetting = {
					caption:"竞赛结果",
					pager:"pager",
					url:"xpjpyBfjsFswh.do?method=viewJsfjgList&doType=query",
					params:getSuperSearch(),
					multiselect:false
				};
				colList=[
	                       {label:'竞赛周期',name:'jszq', index: 'jszq',width:'15%',key:true},
						   {label:'学院',name:'xymc', index: 'xymc',width:'10%'},
						   {label:'学院代码',name:'xydm', index: 'xydm',width:'15%',hidden:true},
						   {label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
						];
				var jsxm = jQuery("font[name=jsxm]");
				jQuery.each(jsxm,function(i,n){
					var xmfsJson = {
							label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
					};
						var xmpmJson = {
								label:"排名",
								name:"pm"+i,
								index:"pm"+i
						};
						colList.push(xmpmJson);
					colList.push(xmfsJson);
				});

				var xnColJson = {
						label:"学年",
						name:"xn",
						index:"xn",
						hidden:true
				};
				colList.push(xnColJson);
				var xqColJson = {
						label:"学期",
						name:"xq",
						index:"xq",
						hidden:true
				};
				colList.push(xqColJson);
				
				gridSetting["colList"] = colList;
				
				jQuery("#dataTable").initGrid(gridSetting);		
				
			});
			function searchRs(){
				var xn_num = document.getElementsByName("a_name_xn").length;
				var xq_num = document.getElementsByName("a_name_xq").length;		
				if(0==xn_num||0==xq_num){
					showAlertDivLayer("请先选择一个学年学期！");
					return false;
				}
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			
			}
		
			//竞赛分导出
			function exportJsf(){
				setSearchTj();//设置高级查询条件
				var url = "xpjpyBfjsFswh.do?method=exportJsfjg";
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
		</script>
	</head>

	<body style="min-height: 800px;">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
		<logic:present name="jsxmList">
		<div id="jsxmDiv">
			<logic:iterate id="z" name="jsxmList">
				<font style="display:none;" xmdm="${z.xmdm }" xmmc="${z.xmmc }" name="jsxm"></font>
			</logic:iterate>
		</logic:present>
		</div>
		<html:form action="/xpjpyBfjsFswh">
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<!-- 按钮 -->	
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<li><a href="#" class="btn_dc" onclick="exportJsf(); return false;">导出</a></li>
	
						</li>						
					</ul>
				</div>
			
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>竞赛结果 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
