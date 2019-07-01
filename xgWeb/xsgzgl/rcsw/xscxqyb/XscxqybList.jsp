<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/rcsw/xscxqyb/js/xscxqyb.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- �߼���ѯʱ����Ҫ -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ����ѧ���±������б�",
				pager:"pager",
				url:"rcsw_xscxqyb.do?method=XscxqybList&type=query",
				colList:[
				   {label:'jgid',name:'jgid', index: 'jgid',hidden:true,key:true},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
				   {label:'ѧ��',name:'xqmc', index: 'xq',width:'6%'},
				   {label:'�·�',name:'yf', index: 'yf',width:'8%',formatter:xhLink},
				   {label:'��д��',name:'xm', index: 'xm',width:'10%'},
				   {label:'��дʱ��',name:'txsj', index: 'txsj',width:'10%'},	
				   {label:'��¼������',name:'txr', index: 'txr', width:'10%',hidden:true}			   
				],
				sortname: "txsj",
			 	sortorder: "asc"
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onClick='XscxqybView(\""+rowObject["jgid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";	
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
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>	
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">��д</a></li>
							<li><a href="javascript:void(0);" onclick="update()" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">ɾ��</a></li>						
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����ѧ���±������б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
