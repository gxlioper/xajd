<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��ѯʦ�б�",
				pager:"pager",
				url:"xlzx_zxs.do?method=zxsglManage&doType=query&status=1",
				colList:[
				   {label:'ְ����',name:'zgh', index: 'zgh'},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'����',name:'age', index: 'age'},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
				   {label:'����',name:'bmmc', index: 'bmmc'},
				   {label:'�ڸ�״̬',name:'statusmc', index: 'statusmc'},
				   {label:'����',name:'xh', index: '',width:'58px',noSort:true,formatter:function(cell,rowObject){
					   var rowData = JSON.stringify(rowObject);
						return '<button type=\'button\' onclick=\'selectZxs('+rowData+');\' class=\'btn_01\' >ѡ��</button>';
				   }}
				],
				sortname: "",
			 	sortorder: ""
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function selectZxs(rowData){
				var api = frameElement.api;
				
				if (api){
					if (api.get('childDialog')){
						var w = api.get('parentDialog');
						updateElementContent(rowData,w);
					} else {
						var w = api.opener;
						updateElementContent(rowData,w);
					}
				} else if (parent.window){
					var w = parent.window;
					updateElementContent(rowData,w);
					
				}
				
				iFClose();
			}
			
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function updateElementContent(rowData,w){
				jQuery("#zxsInfo .zgh",w.document).val(rowData["zgh"]);
				jQuery("#zxsInfo .xm",w.document).text(rowData["xm"]);
				jQuery("#zxsInfo .xb",w.document).text(rowData["xb"]);
				jQuery("#zxsInfo .nl",w.document).text(rowData["age"]);
				jQuery("#zxsInfo .lxdh",w.document).text(rowData["lxdh"]);
				jQuery("#zxsInfo .bmmc",w.document).text(rowData["bmmc"]);
				
				jQuery("#zxrq",w.document).val("");
				jQuery("#zxqssj",w.document).val("");
				jQuery("#zxjssj",w.document).val("");
				jQuery("#sjddm",w.document).val("");
			}
		</script>
	</head>

	<body>
	
		<html:form action="/xsxx_xsgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div>
					<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
