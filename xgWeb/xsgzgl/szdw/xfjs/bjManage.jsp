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
					caption:"�༶�б�",
					pager:"pager",
					url:"szdw_xfjsgl.do?method=bjManage&type=query",
					colList:[      
				           {label:'key',name:'bjdm', index: 'bjdm',hidden:true,key:true},
						   {label:'У��',name:'xqmc', index: 'xqmc',hidden:true},
						   {label:'�༶����',name:'bjrs', index: 'bjrs',hidden:true},
 						   {label:'ѧ������',name:'pyccmc', index: 'pyccmc',hidden:true},
    					   {label:'����Ա',name:'fdy', index: 'fdy',hidden:true},
						   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'22%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'22%'}
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
				showAlertDivLayer("��ѡ��һ���༶��");
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
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_ccg" onclick="addBjBc();return false;" >ȷ��</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�༶�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow" style="height: 365px;overflow-y: auto;">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
