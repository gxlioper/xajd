<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/js/jcrc.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"wsjcJcrc.do?method=getJcrcList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xq'},
				   {label:'����',name:'rcmc', index: 'rcmc'},
				   {label:'����',name:'fslx', index: 'fslx',formatter:function(v,r){
					   if (v == "0"){
						   return "����";
					   } else if (v == "1"){
						   return "�ȼ�";
					   } else {
						   return "�Ǽ�";
					   }
				   }},
				   {label:'��ʼʱ��',name:'kssj', index: 'kssj'},
				   {label:'����ʱ��',name:'jssj', index: 'jssj'},
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
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="jcrcSubmit();" class="btn_shtg">�ύ</a></li>						
					<li><a href="javascript:void(0);" onclick="jcrcCancel();" class="btn_shbtg">ȡ���ύ</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ����ճ��б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
