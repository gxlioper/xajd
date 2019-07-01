<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		function dcmcLink(cellValue, rowObject) {
			return "<button type=\"button\" onclick=\"show('"+rowObject["zgh"]+"');\" class=\"btn_01\" >ѡ��</button>";
		}
		function show(zgh){
			var gotoPath = jQuery("#gotoPath").val();

			if (gotoPath.split("?").length > 1){
				gotoPath = gotoPath+"&zgh="+zgh;
			} else {
				gotoPath = gotoPath+"?zgh="+zgh;
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
			var gridSetting = {
				caption:"��ְ����Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"szdw_fdyjtff.do?method=showFdys&type=query&fdyjtfflx=${fdyjtfflx}",
				colList:[
				   {label:'ְ����',name:'zgh', index: 'zgh',width:'20%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'20%'},
				   {label:'�Ա�',name:'xbmc', index: 'xbmc',width:'15%'},
				   {label:'����',name:'bmmc', index: 'bmmc',width:'30%'},
				   {label:'����',name:'cz', index: 'cz',width:'',formatter:dcmcLink}
				],
				dblclick:function(rowObject){
					var gotoPath = jQuery("#gotoPath").val();

					if (gotoPath.split("?").length > 1){
						gotoPath = gotoPath+"&zgh="+rowObject["zgh"];
					} else {
						gotoPath = gotoPath+"?zgh="+rowObject["zgh"];
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
				},
				sortname: "zgh",
			 	sortorder: "asc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<input type="hidden" value="${gotoPath}" id="gotoPath"/>
	
		<html:form action="/xsxx_xsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ְ����Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
