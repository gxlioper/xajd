<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"��������б�",
			pager:"pager",
			url:"dekt_dsgljg.do?method=dsglJgList&type=query",
			colList:[
			   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%'},
			   {label:'����',name:'xm', index: 'xm',width:'8%'},
			   {label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
			   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
			   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'}	,
			   {label:'�Ķ�����',name:'ydbs', index: 'ydbs',width:'15%',formatter:xhLink}	
			]
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
});
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
	
}


function viewDsxq(xh) {
	showDialog("�鿴�Ķ�����", 720, 520, "dekt_dsgljg.do?method=viewydxq&xh=" + xh);
}

function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='viewDsxq(\""
			+ rowObject["xh"] + "\");'>" + cellValue
			+ "</a>";
}



var DCCLBH = "xg_dekt_dsgljg.do";//dcclbh,�������ܱ��
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, ExportData);
}

// ��������
function ExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "dekt_dsgljg.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function Print(){
//var url = "hdgl_hdxx.do?method=xscjdPri";
//window.open(url);         
//location.href =url;
}


		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_dsgljg">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!--<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						</logic:equal>
						--><li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<li><a href="#" class="btn_dc" onclick="Print();return false;">�ɼ���</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
