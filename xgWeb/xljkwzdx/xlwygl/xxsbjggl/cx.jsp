<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xljk_xlwygl_xxsbjgglwh.do?method=query&sblx=0",
		colList : [
				{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
				{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',hidden : true },
				{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
				{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
				{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
				{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : '学号', name : 'xh', index : 'xh', width : '13%',formatter : link},
				{ label : '姓名', name : 'xm', index : 'xm', width : '8%'},
				{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
				{ label : '年级', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
				{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
				{ label : '周次', name : 'zbzc', index : 'zbzc', width : '7%' },
				{ label : '记录时间', name : 'sbsj', index : 'sbsj', width : '10%' },
				{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
		sortname : "sbsj", sortorder : "desc" }

	var gridSetting_2 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbjgglwh.do?method=query&sblx=1",
			colList : [
						{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',hidden : true },
						{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
						{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
						{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
						{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '学号', name : 'xh', index : 'xh', width : '13%',formatter : link},
						{ label : '姓名', name : 'xm', index : 'xm', width : '8%'},
						{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
						{ label : '年级', name : 'nj', index : 'nj', width : '5%' },
						{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
						{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
						{ label : '周次', name : 'zbzc', index : 'zbzc', width : '7%' },
						{ label : '记录时间', name : 'sbsj', index : 'sbsj', width : '10%' },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }

	var gridSetting_3 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbjgglwh.do?method=query&sblx=2",
			colList : [
						{ label : 'sbjgid', name : 'sbjgid', index : 'sbjgid',key : true, hidden : true },
						{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',hidden : true },
						{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
						{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
						{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
						{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
						{ label : '学号', name : 'xh', index : 'xh', width : '13%',formatter : link},
						{ label : '姓名', name : 'xm', index : 'xm', width : '10%'},
						{ label : '性别', name : 'xb', index : 'xb', width : '10%' },
						{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
						{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '19%' },
						{ label : '班级', name : 'bjmc', index : 'bjmc', width : '19%' },
						{ label : '记录时间', name : 'sbsj', index : 'sbsj', width : '15%' },
						{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }
	
	jQuery(function() {
		jQuery('#tab_title li:first').css('ha');
		jQuery('#tab_title li:first').children().click();
	});

	/**
	 * 待处理、已处理面签切换
	 * @param obj
	 * @param shzt
	 * @return
	 */
	function selectTab(obj,query_type){
		jQuery("#query_type").val(query_type);

		if (query_type == "0"){
			jQuery("#dataTable").initGrid(gridSetting_1);
		} else if(query_type == "1"){
			jQuery("#dataTable").initGrid(gridSetting_2);
		}else if(query_type == "2"){
			jQuery("#dataTable").initGrid(gridSetting_3);
		}
		jQuery(".ha").removeClass("ha");
		jQuery(obj).parent().addClass("ha");
		
		searchRs();
	}
	
	/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 上报
	 * @return
	 */
	function sb(){
		var sblx = jQuery("#query_type").val();
		var rows = jQuery("#dataTable").getSeletRow();
		showDialog('上报',680,430,'xljk_xlwygl_xxsbjgglwh.do?method=sb&sblx=' + sblx);
	}

	/**
	 * 删除
	 * @return
	 */
	function del(){
		var rows = jQuery("#dataTable").getSeletRow();
		var ids = jQuery("#dataTable").getSeletIds();
		if (rows.length == 0){
			showAlertDivLayer("请选择一条您要删除的记录！");
			return false;
		} else{

			for(i = 0 ; i < rows.length; i++){
				var sjly = rows[i]['sjly'];
				if(sjly == '1'){
					showAlertDivLayer("不能删除流程数据，请确认 ！");
					return false;
				}
			}
			
			showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
				jQuery.post("xljk_xlwygl_xxsbjgglwh.do?method=delAction",{pks:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	/**
	 * 修改
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} else{
			var sjly = rows[0]['sjly'];
			if(sjly == '1'){
				showAlertDivLayer("不能修改流程数据，请确认 ！");
				return false;
			}
			showDialog('修改',680,430,'xljk_xlwygl_xxsbjgglwh.do?method=xg&sbjgid=' + rows[0]['sbjgid']);
		}
	}

	/**
	 * 链接
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){
		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,430 , 'xljk_xlwygl_xxsbjgglwh.do?method=ck&sbjgid=" + rowObject['sbjgid'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	var DCCLBH1 = "xljk_xlwygl_xxsbjggl1.do";//dcclbh,导出功能编号

	var DCCLBH2 = "xljk_xlwygl_xxsbjggl2.do";//dcclbh,导出功能编号


	//自定义导出 功能
	function exportConfig() {
		var dcdm = "";
		if(jQuery("#query_type").val() == '0'){
			dcdm = DCCLBH1;
		}else if(jQuery("#query_type").val() == '1'){
			dcdm = DCCLBH1;
		}else{
			dcdm = DCCLBH2;
		}
		//DCCLBH导出功能编号,执行导出函数 
		customExport(dcdm, exportData);
	}

	// 导出方法
	function exportData() {
		var dcdm = "";
		if(jQuery("#query_type").val() == '0'){
			dcdm = DCCLBH1;
		}else if(jQuery("#query_type").val() == '1'){
			dcdm = DCCLBH1;
		}else{
			dcdm = DCCLBH2;
		}
		setSearchTj();//设置高级查询条件
		var url = "xljk_xlwygl_xxsbjgglwh.do?method=exportData&dcclbh=" + dcdm + "&sblx=" + jQuery("#query_type").val();//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}


	/**
	导入
	*/
	function importConfig(){
		var drdm = "";
		if(jQuery("#query_type").val() == '0'){
			drdm = "IMPORT_XLZX_XXSBJGGL_1";
		}else if(jQuery("#query_type").val() == '1'){
			drdm = "IMPORT_XLZX_XXSBJGGL_1";
		}else{
			drdm = "IMPORT_XLZX_XXSBJGGL_2";
		}
		toImportData(drdm);
		return false;
	}
	
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xljk_xlwygl_xxsbjgglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">上报</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
								<a href="javascript:void(0);"
									onclick="del();return false;" class="btn_sc"
									>删除</a>
							</li>
							<%--<li>
								<a href="javascript:void(0);"
									onclick="importConfig();return false;" class="btn_dr">导入</a>
							</li>
							--%><li>
								<a href="javascript:void(0);"
									onclick="exportConfig();return false;" class="btn_dc">导出</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tab_title">
			       		<li><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>班级心理周报</span></a></li>
				       	<li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>公寓心理周报</span></a></li>
				        <li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>平时情况上报</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
