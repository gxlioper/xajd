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
		url : "xlzxnew_zqrcgl.do?method=searchYrcCx",
		colList : [
		        { label :'ybid',name : 'ybid',index : 'ybid',hidden:true,key : true},
				{ label : '学年', name : 'xn', index : 'xn', width : '50%'},
				{ label : '月份', name : 'yf', index : 'yf', width : '50%',formatter:yzqLink },
				{ label : 'sbcnt', name : 'sbcnt', index : 'sbcnt',  hidden : true }

				]}

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
		showDialog('增加',580,420,'xlzxnew_zqrcgl.do?method=addYzqsz');
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
				var SQCOUNT = rows[i]['sbcnt'];
				if(SQCOUNT != '0'){
					showAlertDivLayer("有周次已被使用，不能删除，请确认 ！");
					return false;
				}
			}
			
			showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
				jQuery.post("xlzxnew_zqrcgl.do?method=delYzqSz",{values:ids.toString()},function(data){
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

			var SQCOUNT = rows[0]['sbcnt'];
			if(SQCOUNT != '0'){
				showAlertDivLayer("该周次已被使用，不能修改，请确认 ！");
				return false;
			}
			showDialog('修改',580,420,'xlzxnew_zqrcgl.do?method=editYzqsz&ybid=' + rows[0]['ybid']);
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
					</ul>
				</div>
				</logic:equal>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li ><a href="javascript:void(0);" onclick="selectTab(this,'zb');"><span>班级周报日程</span></a></li>
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'yb');"><span>学院月报日程</span></a></li>
			      </ul>
			    </div>
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
