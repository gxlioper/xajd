<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/hmdgl/js/hmdgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"���˵�λ�б�",
			pager:"pager",
			url:"qgzxhmdgl.do?method=selectDw&type=query&dwlx="+jQuery("#dwlx").val(),
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'��λ/�ҳ�����',name:'yrdwmc', index: 'yrdwmc',width:'8%'},
               {label:'����',name:'dwlb', index: 'dwlb',width:'8%',formatter:function(value,cell){
                   if(value == "01"){
                       return "У�ڵ�λ";
				   } else if(value == "02"){
                       return "У�ⵥλ";
				   } else if(value == "03"){
                       return "�ҳ��û�";
				   } else {
                       return value;
				   }
			   }},
               {label:'��λ������',name:'xm', index: 'xm',width:'10%'},
               {label:'����',name:'', index: '',width:'15%',formatter:function(value,cell){
                   return "<label class='btn_01' onclick=\"select('"+cell["id"]+"','"+cell["yrdwmc"]+"');\">ѡ��</label>";
			   }},
			],
			sortname: "yrdwmc",
			sortorder: "desc"
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
});
		function select(id,mc){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#dwid").val(id);
            parent.jQuery("#mc").html(mc);
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
		<html:form action="/qgzxhmdgl">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" value="${dwlx}" id="dwlx">
			<div class="toolbox">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���˵�λ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
