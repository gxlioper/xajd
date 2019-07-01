<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xljk_xlwygl_xssqglwh.do?method=query&lx=0",
		colList : [
				{ label : 'pk', name : 'pk', index : 'pk',key : true, hidden : true },
				{ label : 'sbcount', name : 'sbcount', index : 'sbcount',hidden : true },
				{ label : 'lx', name : 'lx', index : 'lx',hidden : true },
				{ label : '学号', name : 'xh', index : 'xh', width : '15%',formatter : xhLink },
				{ label : '姓名', name : 'xm', index : 'xm', width : '15%'},
				{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
				{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '20%' },
				{ label : '班级', name : 'bjmc', index : 'bjmc', width : '20%' },
				{ label : '任职开始日期', name : 'rzksrq', index : 'rzksrq', width : '10%' },
				{ label : '任职结束日期', name : 'rzjsrq', index : 'rzjsrq', width : '10%' },
				{ label : '上报记录', name : 'ywsbmc', index : 'ywsbmc', width : '5%' }],
		sortname : "xm", sortorder : "asc" }

	var gridSetting_2 = {
			pager : "pager",
			url : "xljk_xlwygl_xssqglwh.do?method=query&lx=1",
			colList : [
					{ label : 'pk', name : 'pk', index : 'pk',key : true, hidden : true },
					{ label : 'lx', name : 'lx', index : 'lx',hidden : true },
					{ label : 'sbcount', name : 'sbcount', index : 'sbcount',hidden : true },
					{ label : '学号', name : 'xh', index : 'xh', width : '15%',formatter : xhLink },
					{ label : '姓名', name : 'xm', index : 'xm', width : '15%'},
					{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
					{ label : '年级', name : 'nj', index : 'nj', width : '10%' },
					{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '18%' },
					{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
					{ label : '是否需要平时上报', name : 'sfxypssbmc', index : 'sfxypssbmc', width : '15%' },
					{ label : '上报记录', name : 'ywsbmc', index : 'ywsbmc', width : '5%' }],
			sortname : "xm", sortorder : "asc" }
	
	jQuery(function() {
		gridSetting_1["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting_1);

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
			jQuery("#li_sh").css("display","");
			jQuery("#li_qx").css("display","none");
			
			jQuery("#dataTable").initGrid(gridSetting_1);
		} else {
			jQuery("#li_sh").css("display","none");
			jQuery("#li_qx").css("display","");
			
			jQuery("#dataTable").initGrid(gridSetting_2);
		}
		
		jQuery(".ha").removeClass("ha");
		jQuery(obj).parent().addClass("ha");
		
		searchRs();
	}
	
	/**
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('详情' , 680 , 430 , 'xljk_xlwygl_xssqglwh.do?method=ck&lx="
				+ rowObject['lx'] + "&xh=" + rowObject['xh'] +"')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
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
	 * 新增
	 * @return
	 */
	function add(){
		showDialog('新增',680,430,'xljk_xlwygl_xssqglwh.do?method=xz&lx=' + jQuery("#query_type").val());
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
				var sbcount = rows[i]['sbcount'];
				if(sbcount != '0'){
					showAlertDivLayer("不能删除有上报记录的数据，请确认 ！");
					return false;
				}
			}
			
			showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
				jQuery.post("xljk_xlwygl_xssqglwh.do?method=delAction",{pks:ids.toString()},function(data){
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
			showDialog('修改',680,430,'xljk_xlwygl_xssqglwh.do?method=xg&xh=' + rows[0]['xh'] + '&lx=' + rows[0]['lx']);
		}
	}


	var DCCLBH1 = "xljk_xlwygl_xssqgl1.do";//dcclbh,导出功能编号

	var DCCLBH2 = "xljk_xlwygl_xssqgl2.do";//dcclbh,导出功能编号

	//自定义导出 功能
	function exportConfig() {
		var dcdm = "";
		if(jQuery("#query_type").val() == '0'){
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
		}else{
			dcdm = DCCLBH2;
		}
		setSearchTj();//设置高级查询条件
		var url = "xljk_xlwygl_xssqglwh.do?method=exportData&dcclbh=" + dcdm + "&lx=" + jQuery("#query_type").val();//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}

	/**
	导入
	*/
	function importConfig(){
		var drdm = "IMPORT_XLZX_XSSQGL_1";
		if(jQuery("#query_type").val() == '0'){
			drdm = "IMPORT_XLZX_XSSQGL_1";
		}else{
			drdm = "IMPORT_XLZX_XSSQGL_2";
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
		<html:form action="/xljk_xlwygl_xssqglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			<logic:equal name="writeAble" value="yes">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="add();return false;">增加</a>
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
						<li>
							<a href="javascript:void(0);"
								onclick="importConfig();return false;" class="btn_dr">导入</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>班级心理委员</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>特殊授权学生</span></a></li>
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
