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
				pager:"pager",
				url:"wsjcWsflr.do?method=getFslrList",
				radioselect:true,
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'jcdx',name:'jcdx', index: 'jcdx',hidden:true},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xq'},
				   {label:'����',name:'rcmc', index: 'rcmc'},
				   {label:'��ʼʱ��',name:'kssj', index: 'kssj'},
				   {label:'����ʱ��',name:'jssj', index: 'jssj'},
<%--				   {label:'������',name:'lrs', index: 'lrs'},--%>
<%--				   {label:'δ����',name:'wlrs', index: 'wlrs'},--%>
				   {label:'�ύ״̬',name:'tjzt', index: 'tjz',formatter:function(v,r){
					   return v == "1" ? "���ύ" : "δ�ύ";
				   }}
				],
				sortname: "kssj",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function selectTab(obj,jcdx){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				//var map = getSuperSearch();
				var map = {};
				map["jcdx"] = jcdx;
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function wsflr(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ¼��ļ���ճ̣�");
				} else {
					if (rows[0]["tjzt"] == "1"){
						showAlertDivLayer("�ü���ճ����ύ������¼�룡");
						return false;
					}
					document.location.href = "wsjcWsflr.do?method=wsflr&rcid="+rows[0]["id"]+"&jcdx="+rows[0]["jcdx"];
				}
			}
			
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
	<html:form action="/wsjcJcxm" method="post" styleId="form">
	
	
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="wsflr()" class="btn_xg">¼��</a></li>
					</ul>
				</div>
			</logic:equal>
			<!-- �������� -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>����</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��λ</span></a></li>
		      </ul>
			</div>
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ѯ����б�</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
