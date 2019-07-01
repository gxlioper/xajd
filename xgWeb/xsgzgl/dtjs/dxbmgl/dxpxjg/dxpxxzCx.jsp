<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
        <script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"��ѵ��Ϣ�б�",
				pager:"pager",
				multiselect:false,
				url:"dtjs_dxbmgl_dxpxxzCx.do?type=query&xh=${xh}",
				colList:[
				   {label:'jgid',name:'jgid', index: 'jgid',key:true,hidden:true},
				   {label:'��ѵ�ڴ�',name:'pxqc', index: 'pxqc'},
				   {label:'��ѵʱ��',name:'pxsj', index: 'xm'},
				   {label:'��ѵ����',name:'pxnr', index: 'pxnr'},
				   {label:'����',name:'pxid', index: '',formatter:xzpx},
				   {label:'kqcjbfb',name:'kqcjbfb', index: 'kqcjbfb',hidden:true},
				   {label:'sjcjbfb',name:'sjcjbfb', index: 'sjcjbfb',hidden:true},
				   {label:'bjcjbfb',name:'bjcjbfb', index: 'bjcjbfb',hidden:true},
				   {label:'kscjbfb',name:'kscjbfb', index: 'kscjbfb',hidden:true}
				],
				sortname: "pxsj",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xzpx(cellValue, rowObject){
				return "<button class=\"btn_01\" type=\"button\" onclick=\"xzpxsj('"+cellValue+"');\">ѡ��</button>";
			}
			function xzpxsj(pxid){
				var gotoPath = jQuery("#gotoPath").val();
				gotoPath = gotoPath+"&pxid="+pxid;
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
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="dxbmgl_dxpxjg.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<input type="hidden" id="xh" name="xh" value="${xh}"/>
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->		
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ѵ��Ϣ����б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
