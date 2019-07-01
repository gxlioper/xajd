<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/xlzx/xlsc/js/xlscjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- �߼���ѯʱ����Ҫ -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"����ɸ�����б�",
				pager:"pager",
				url:"xlzx_xlscjg.do?method=xlscjgManage&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'5%'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm',width:'12%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'12%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'12%'},
				   {label:'ɸ������',name:'scrq', index: 'scrq',width:'8%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="addXlscjg()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="updateXlscjg();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="deleteXlscjg()" class="btn_sc">ɾ��</a></li>						
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr" >����</a></li>
					</logic:equal>
						<li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc" >����</a></li>
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
				<span> ����ɸ�����б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>