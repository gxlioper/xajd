<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"团员注册列表",
				pager:"pager",
				url:"dtjs_tyzc.do?method=tyzcManage&type=query",
				colList:[
					{label:'key',name:'pk', index: 'pk',key:true ,hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'12%' , formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'8%'},
					{label:'性别',name:'xb', index: 'xb',width:'3%'},
					{label:'年级',name:'nj', index: 'nj',width:'7%'},
					{label:'班级',name:'bjmc', index: 'bjdm',width:'18%'},
					{label:'学年',name:'xn', index: 'xn',width:'12%'},
					{label:'注册状态',name:'zcztmc', index: 'zcztmc',width:'8%'},
					{label:'注册时间', name:'zcsj', index: 'zcsj',width:'15%'},
					{label:'注册状态代码',name:'zczt', index: 'zczt', hidden:true}
				],
				sortname: "xn,xh",
			 	sortorder: "asc"
			};
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick=\"showDialog('团员注册信息' , 700,305 , 'dtjs_tyzc.do?method=tyzcView&pk=" + rowObject['pk'] + "')\" >" + cellValue + "</a>";
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			//团员注册
			function tyzc(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 1) {
					if(rows[0]['zczt']=='1'){
						showAlertDivLayer("请选择未注册的学生！");
						return false;
					}
					var url = "dtjs_tyzc.do?method=tyzcDgzc&pk=" + rows[0]["pk"];
					var title = "团员注册";
					showDialog(title,700,305,url);
				}else {
					if(rows.length == 0){
						// 重置
						jQuery("#pksPlHidden").val("");
						// 根据查询结果进行批量增加
						var xn_num = jQuery("a[name=a_name_xn]").length;
						if(xn_num != 1){
							alertError("请选择一个学年！");
							return false;
						}
						var a_name_zczt = jQuery("a[name=a_name_zczt]");
						var zczt_num = a_name_zczt.length;
						if(zczt_num == 1 && a_name_zczt.eq(0).attr("id").replace("a_id_","") == '-1'){
							var rowConut = jQuery("#rowConut").html();
							var url = "dtjs_tyzc.do?method=tyzcPlzc&len="+rowConut;
							var title = "批量注册";
							showDialog(title,350,255,url);
						}else{
							showAlertDivLayer("条件【注册状态】必须选【未注册】才能批量注册！");
							return false;
						}
					}else{
						var pks = new Array();
						// 根据勾选记录进行批量增加
						for(var i=0;i<rows.length;i++){
							if(rows[i]['zczt']=='1'){
								showAlertDivLayer("请选择未注册的学生！");
								return false;
							}
							pks.push(rows[i]['pk']);
						}
						jQuery("#pksPlHidden").val(pks.join(','));
						var url = "dtjs_tyzc.do?method=tyzcPlzc&len="+rows.length;
						var title = "批量注册";
						showDialog(title,350,255,url);
					}
				}
			}
			//撤销注册
			function tyzcCancel(){
				var rows = jQuery("#dataTable").getSeletRow();
				if(rows.length == 0){
					// 重置
					jQuery("#pksPlHidden").val("");
					// 根据查询结果进行批量增加
					var xn_num = jQuery("a[name=a_name_xn]").length;
					if(xn_num != 1){
						alertError("请选择一个学年！");
						return false;
					}
					var a_name_zczt = jQuery("a[name=a_name_zczt]");
					var zczt_num = a_name_zczt.length;
					if(zczt_num == 1 && a_name_zczt.eq(0).attr("id").replace("a_id_","") == '1'){
						var rowConut = jQuery("#rowConut").html();
						var url = "dtjs_tyzc.do?method=tyzcCancel&len="+rowConut;
						var title = "批量撤销注册";
						showDialog(title,360,170,url);
					}else{
						showAlertDivLayer("条件【注册状态】必须选【已注册】才能批量撤销注册！");
						return false;
					}
				}else{
					var pks = new Array();
					// 根据勾选记录进行批量增加
					for(var i=0;i<rows.length;i++){
						if(rows[i]['zczt']=='-1'){
							showAlertDivLayer("请选择已注册的学生！");
							return false;
						}
						pks.push(rows[i]['pk']);
					}
					jQuery("#pksPlHidden").val(pks.join(','));
					var url = "dtjs_tyzc.do?method=tyzcCancel&len="+rows.length;
					var title = "批量撤销注册";
					if(rows.length == 1){
						title = "撤销注册";
					}
					showDialog(title,360,170,url);
				}
			}
			//导出
			function exportConfig(){
				var DCCLBH='dtjs_tyzcgl.do';
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH='dtjs_tyzcgl.do';
				setSearchTj();//设置高级查询条件
				var url = "dtjs_tyzc.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<input type="hidden" id="pksPlHidden" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					当不勾选学生时，对查询结果里面的所有学生进行注册/撤销注册；<br/>
					当勾选学生时，对勾选的学生进行注册/撤销注册。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/dtjs_tyzc" method="post" styleId="tyzcForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="tyzc();" class="btn_plqy">注册</a></li>
							<li><a href="javascript:void(0);" onclick="tyzcCancel();" class="btn_sc">撤销注册</a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>团员注册列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
