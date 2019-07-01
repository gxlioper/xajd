<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			function dcmcLink(cellValue, rowObject) {
				var rowData = JSON.stringify(rowObject);
				return '<button type=\'button\' onclick=\'show('+rowData+');\' class=\'btn_01\' >ѡ��</button>';
			}
			function show(rowData){
				var api = frameElement.api;
				var W = api.get('parentDialog');
				W.showFdysNotF5CallBack(rowData);
				api.close();
			}
			var gridSetting = {
				caption:"��ʦ��Ϣ�б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"szdw_fdyjtff.do?method=showFdysNotF5&type=query",
				colList:[
				   {label:'ְ����',name:'zgh', index: 'zgh',width:'20%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'20%'},
				   {label:'�Ա�',name:'xbmc', index: 'xbmc',width:'7%'},
				   {label:'����',name:'bmmc', index: 'bmmc',width:'20%'},
                    {label:'��Ժ',name:'symc', index: 'symc',width:'20%'},
                    {label:'bmdm',name:'bmdm', index: 'bmdm',width:'20%',hidden:true},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'30%',hidden:true},
				   {label:'zc',name:'zc', index: 'zc',width:'10%',hidden:true},
				   {label:'zcmc',name:'zcmc', index: 'zcmc',width:'10%',hidden:true},
                    {label:'����',name:'mzmc', index: 'mzmc',width:'10%',hidden:true},
                    {label:'������ò',name:'zzmmmc', index: 'zzmmmc',width:'10%',hidden:true},
                    {label:'��У����ʱ��',name:'rxgzsj', index: 'rxgzsj',width:'10%',hidden:true},
				   {label:'����',name:'cz', index: 'cz',width:'',formatter:dcmcLink}
				],
				dblclick:function(rowObject){
					show(rowObject);
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
				<span> ��ʦ��Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
