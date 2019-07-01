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
					url:"rcsw_xsgzqkbb_bjybbgl.do?method=getBjListData&xyybbid=${xyybbid}",
					colList:[      
				         {label:'key',name:'bjdm', index: 'bjdm',hidden:true,key:true},
						   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'16%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'22%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'22%'},
						   {label:'�༶����',name:'bjrs', index: 'bjrs',hidden:true},
						   {label:'��ѧ����',name:'mxss', index: 'mxss',hidden:true},
						   {label:'Ůѧ����',name:'wxss', index: 'wxss',hidden:true}
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
				var bj_span = rows[0]["nj"] + "�� " + rows[0]["xymc"] + " " + rows[0]["zymc"];
				var parentD = queryParentDocumentJgcxTj();
				jQuery("#bj_span",parentD).html(bj_span);
				jQuery("#bjmc",parentD).val(rows[0]["bjmc"]);
				jQuery("#bjmc",parentD).attr("title",rows[0]["bjmc"]);
				jQuery("#bjdm",parentD).val(rows[0]["bjdm"]);
				jQuery("#wxss",parentD).val(rows[0]["wxss"]);
				jQuery("#mxss",parentD).val(rows[0]["mxss"]);
				iFClose();
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybsqgl">
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
