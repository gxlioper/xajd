<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/twgl/js/tgb.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {			
					caption : "����֯�б�",
					pager : "pager",
					url : "tzzgl.do?method=tzzList&type=query&xh="+'${xh}',
					colList : [
								{ label : 'zzid', name : 'zzid', index : 'zzid',key : true, hidden : true },
								{ label : '��֯����', name : 'zzmc', index : 'zzmc', width : '15%', formatter:xhLink },
								{ label : 'ָ����λ', name : 'zddw', index : 'zddw', width : '10%' },
								{ label : '��֯��ַ', name : 'zzdz', index : 'zzdz', width : '5%' },
								{ label : '������', name : 'fzr', index : 'fzr', width : '15%' },
								{label:'����',name:'zzid', index: '',width:'12%',noSort:true,formatter:function(cell,rowObject){
									   return "<label class='btn_01' onclick=\"selectTzz('"+cell+"');\">ѡ��</label>";}}
							  ]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

	

	function selectTzz(zzid){
		var gotoPath = jQuery("#gotoPath").val();

		if (gotoPath.split("?").length > 1){
			gotoPath = gotoPath+"&rzzz="+zzid;
		} else {
			gotoPath = gotoPath+"?rzzz="+zzid;
		}
		var api = frameElement.api;
		
		if (api){
			if (api.get('childDialog')){
				api.reload(api.get('parentDialog') ,gotoPath);
			} else {
				var W = api.opener;
				W.location=gotoPath;			
			}
		} else if (parent.window){
			parent.window.document.location=gotoPath;						
		}
		
		iFClose();
	}

	/**
	 * ��ѯ
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

</script>
	</head>

	<body>
		<html:form action="/tzzgl" >
			<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����֯��Ϣ &nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
