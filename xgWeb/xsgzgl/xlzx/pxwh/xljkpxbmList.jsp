<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/pxwh/js/xljkpxbm.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption : "��������ѵδ����",
				pager : "pager",
				url : "xlzx_pxwh.do?method=pxbmList&type=query",
				colList : [
					{label:'key',name:'pxid',index :'pxid',key:true,hidden:true},
					{label:'��ѵ����',name:'pxzt',index:'pxzt',width:'15%',formatter:pxztLink},
					{label:'��ѵ�ص�',name:'pxdd',index:'pxdd',width:'15%'},
					{label:'��ѵʱ��',name:'pxsj',index:'pxsj',width:'10%'},
					{label:'��ʦ',name:'js',index:'js',width:'10%'},
					{label:'��������ʱ��',name:'bmkfsj',index:'bmkfsj',width:'15%'},
					{label:'�ѱ���/</br>��������',name:'rs',index:'rs',width:'5%'},
					{label:'����ʱ���ѹ�',name:'bmyg',index:'bmyg',width:'5%',hidden:true},
					{label:'�Ƿ񿪷ű���',name:'sfkfbm',index:'sfkfbm',width:'5%',hidden:true},
					{label:'����',name:'cz',index:'cz',width:'10%',formatter:bmcz,noSort: true}
				],
				sortname: "pxsj",
		 		sortorder: "desc",
			}
			var gridSetting2 = {
				caption : "��������ѵ�ѱ���",
				pager : "pager",
				url : "xlzx_pxwh.do?method=pxbmList&type=query",
				colList : [
					{label:'key',name:'pxid',index :'pxid',key:true,hidden:true},
					{label:'��ѵ����',name:'pxzt',index:'pxzt',width:'15%',formatter:pxztLink},
					{label:'��ѵ�ص�',name:'pxdd',index:'pxdd',width:'15%'},
					{label:'��ѵʱ��',name:'pxsj',index:'pxsj',width:'10%'},
					{label:'��ʦ',name:'js',index:'js',width:'10%'},
					{label:'��������ʱ��',name:'bmkfsj',index:'bmkfsj',width:'15%'},
					{label:'����ʱ��',name:'bmsj',index:'bmsj',width:'10%'},
					{label:'����ʱ���ѹ�',name:'bmyg',index:'bmyg',width:'5%',hidden:true},
					{label:'����',name:'cz',index:'cz',width:'10%',formatter:qxbmcz,noSort: true}
				],
				sortname: "pxsj",
		 		sortorder: "desc",
			}
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting1["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting1);
			})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xlzx_pxwh">
			<input type="hidden"  id="sfybm" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:70%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>δ����</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>�ѱ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="formbox">
			<h3 class=datetitle_01>
				<span>��������ѵά��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
