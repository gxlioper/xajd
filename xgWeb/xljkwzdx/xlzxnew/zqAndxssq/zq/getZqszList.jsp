<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>	
		<script type="text/javascript" src="xljkwzdx/xlzxnew/zqAndxssq/zq/js/zqsz.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xlzxnew_zqrcgl.do?method=searchZrcCx",
		colList : [
				{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
				{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : 'zbid', name : 'zbid', index : 'zbid',key : true, hidden : true },
				{ label : 'zblx', name : 'zblx', index : 'zblx',hidden : true },
				{ label : '周次', name : 'zbzc', index : 'zbzc', width : '25%',formatter:zqLink},
				{ label : '开始日期', name : 'zbksrq', index : 'zbksrq', width : '15%' },
				{ label : '结束日期', name : 'zbjsrq', index : 'zbjsrq', width : '15%' },
				{ label : '时间', name : 'czsj', index : 'czsj', width : '20%' },
				{ label : 'sqcount', name : 'sqcount', index : 'sqcount',  hidden : true }],
		sortname : "czsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting_1["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting_1);
	});


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
		showDialog('增加',580,420,'xlzxnew_zqrcgl.do?method=addZq');
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
				var SQCOUNT = rows[i]['sqcount'];
				if(SQCOUNT != '0'){
					showAlertDivLayer("有周次已被使用，不能删除，请确认 ！");
					return false;
				}
			}
			
			showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
				jQuery.post("xlzxnew_zqrcgl.do?method=delzqsz",{zbids:ids.toString()},function(data){
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

			var SQCOUNT = rows[0]['sqcount'];
			if(SQCOUNT != '0'){
				showAlertDivLayer("该周次已被使用，不能修改，请确认 ！");
				return false;
			}
			showDialog('修改',580,420,'xlzxnew_zqrcgl.do?method=editZq&zbid=' + rows[0]['zbid']);
		}
	}
	// 未/已上报班级
	function cxSbbj(sbbjlx){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一个上报周次！");
			return false;
		} else{
			var title = "已上报班级";
			if(sbbjlx == 'wsb'){
				title = "未上报班级";
			}
			showDialog(title,800,500,'xlzxnew_zqrcgl.do?method=ckZqDetailxx&zbid=' + rows[0]['zbid'] + "&sbbjlx=" + sbbjlx);
		}
	}
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xljk_xlwygl_zbrcglwh">
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
						<li id="ysbbj_li" >
							<a href="javascript:void(0);"
								onclick="cxSbbj('ysb');return false;" class="btn_cx" 
								>已上报班级</a>
						</li>
						<li id="wsbbj_li" >
							<a href="javascript:void(0);"
								onclick="cxSbbj('wsb');return false;" class="btn_cx" 
								>未上报班级</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
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
