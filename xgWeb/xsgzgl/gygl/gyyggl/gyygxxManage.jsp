<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			var gridSetting = {
				caption:"公寓员工列表",
				pager:"pager",
				url:"gygl_gyygxxgl.do?type=query",
				colList:[
						   {label:'员工编号',name:'ygbh', index: 'ygbh',width:'10%',key:true },
						   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
						   {label:'年龄',name:'nl', index: 'nl',width:'5%'},
						   {label:'性别',name:'xbmc', index: 'xbmc',width:'5%'},
						   {label:'zwdm',name:'zwdm', index: 'zwdm',hidden:true},
						   {label:'聘用日期',name:'pyrq', index: 'pyrq',width:'10%'},
						   {label:'身份证号',name:'sfzh', index: 'sfzh',width:'18%'},
						   {label:'现岗',name:'zwmc', index: 'zwmc',width:'10%'},
						   {label:'是否在岗',name:'zgztmc', index: 'zgzt',width:'2%'},
						   {label:'联系电话',name:'lxdh', index: 'lxdh',width:'15%'}
						],
				sortname: "ygbh",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function zjyg(){
				showDialog('增加外聘人员',600,400,'gyglnew_gyygxxgl.do?method=zjYg');
				}

			function xgyg(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					showDialog('修改外聘人员',600,400,'gyglnew_gyygxxgl.do?method=xgYg&ygbh='+rows[0]["ygbh"]);
				}
			}

			function ckyg(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要查看的记录！");
				} else {
					showDialog('查看外聘人员',600,400,'gyglnew_gyygxxgl.do?method=ckYg&ygbh='+rows[0]["ygbh"]);
				}
				}

			function scyg(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				
				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
					jQuery.post("gyglnew_gyygxxgl.do?method=scYg",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
					}});
				}
			}

			function exportConfig() {
				customExport("gyglnew_gyygxxgl.do", sjkwhExportData,700,480);
		}
				
				
		// 导出方法
		function sjkwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_gyygxxgl.do?method=sjkwhExportData&dcclbh=" + "gyglnew_gyygxxgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
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
		<html:form action="/xszz_jtqkdc">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="zjyg();return false;" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="xgyg();return false;" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="scyg();" class="btn_sc">删除</a></li>
						
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="ckyg();return false;" class="btn_ck">查看</a></li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a></li>
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
				<span>公寓员工列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
