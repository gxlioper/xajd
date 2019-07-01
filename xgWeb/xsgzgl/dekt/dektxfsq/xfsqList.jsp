<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/dektxfsq/js/xfsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "�����ѯ",
				pager : "pager",
				url : "dekt_xfsq.do?method=getXfsqList",
				colList : [
					{label:'sqid',name:'sqid',index :'sqid',key:true,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'�϶�����',name:'rdnr', index: 'rdnr',width:'44%'},
					{label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'12%'},
                    {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
					{label:'��������',name:'splc', index: 'splc',hidden:true}
				],
				sortname: "hjsj",
			 	sortorder: "desc"
			};
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_xmwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_shuc" onclick="submitBusi();return false;">�ύ</a></li>
						<li><a href="#" class="btn_dr" onclick="xfsqZdyImport();return false;">����</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<li><a href="javascript:void(0);" onclick="shLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����ѯ</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
