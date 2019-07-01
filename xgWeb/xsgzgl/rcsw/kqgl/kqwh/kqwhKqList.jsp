<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/kqgl/kqwh/js/kqwh.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�����ѯ",
				pager:"pager",
				url:"zjsy_kqwh.do?method=getKqjgList",
				colList:[
					{label:'key',name:'id', index: 'id',key:true,hidden:true},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'splc',name:'splc', index: 'splc',hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
					{label:'ѧ��',name:'xqmc', index: 'xqmc',width:'6%'},
					{label:'�·�',name:'toyf', index: 'toyf',width:'7%'},
					{label:'�ܴ�',name:'tozc', index: 'tozc',width:'7%'},
					{label:'ѧԺ',name:'xymc', index: 'xymc',width:'12%'},
					{label:'�༶',name:'bjmc', index: 'bjdm',width:'12%',formatter:bjLink},
					{label:'��������',name:'cqrs', index: 'cqrs',width:'8%'},
					{label:'��������',name:'bjrs', index: 'bjrs',width:'8%'},
					{label:'�¼�����',name:'sjrs', index: 'sjrs',width:'8%'},
					{label:'��������',name:'kkrs', index: 'kkrs',width:'8%'},
					{label:'������(%)',name:'cql', index: 'cql',width:'8%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'8%'}
				],
				sortname: "to_number(yf),to_number(zc),bjdm",
			 	sortorder: "desc"
			};
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//��ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="zjsy_kqgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
					   <logic:equal name="writeAble" value="yes">	
					      <li><a href="javascript:void(0);" onclick="modKq();return false;" class="btn_xg">ά��</a></li>
						  <li><a href="javascript:void(0);" onclick="submit();return false;" class="btn_up">�ύ</a></li>	
						  <li><a href="javascript:void(0);" onclick="cancleRst();return false;" class="btn_sr">����</a></li>	
					   </logic:equal>
						  <li><a href="javascript:void(0);" onclick="viewLcgz();return false;" class="btn_cs">���̸���</a></li>				
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ڽ��</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
