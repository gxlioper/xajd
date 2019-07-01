<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function queryParentDocumentJgcxTj(){
			var api = frameElement.api;
			if (api){
				return api.get('parentDialog').document;
			} else{
				var W = api.opener;
				return W.document;
			} 
		}
		jQuery(function(){
			var gridSetting = {
					caption:"班级列表",
					pager:"pager",
					url:"szdw_xfjsgl.do?method=bjManage&type=query",
					colList:[      
				           {label:'key',name:'bjdm', index: 'bjdm',hidden:true,key:true},
						   {label:'校区',name:'xqmc', index: 'xqmc',hidden:true},
						   {label:'班级人数',name:'bjrs', index: 'bjrs',hidden:true},
 						   {label:'学生类型',name:'pyccmc', index: 'pyccmc',hidden:true},
    					   {label:'辅导员',name:'fdy', index: 'fdy',hidden:true},
						   {label:'年级',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},
						   {label:'专业',name:'zymc', index: 'zydm',width:'22%'},
						   {label:'班级',name:'bjmc', index: 'bjdm',width:'22%'}
						],
						sortname: "nj,xymc,zymc,bjmc",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function addBjBc() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1) {
				showAlertDivLayer("请选择一个班级！");
			} else {
				var parentD = queryParentDocumentJgcxTj();
				jQuery("#bjmc",parentD).val(rows[0]["bjmc"]);
				jQuery("#bjdm",parentD).val(rows[0]["bjdm"]);
				jQuery("#fdy",parentD).val(rows[0]["fdy"]);
				jQuery("#xymc",parentD).val(rows[0]["xymc"]);
				jQuery("#pyccmc",parentD).val(rows[0]["pyccmc"]);
				jQuery("#yxmc",parentD).val(rows[0]["xqmc"]);
				jQuery("#ydxsrs",parentD).val(rows[0]["bjrs"]);
				iFClose();
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/szdw_xfjsgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_ccg" onclick="addBjBc();return false;" >确定</a>
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
				<span>班级列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow" style="height: 365px;overflow-y: auto;">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
