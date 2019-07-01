<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/xsxx/yrgl/js/yrgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- �߼���ѯʱ����Ҫ -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"����б�",
				pager:"pager",
				url:"xpjpy_bjxsgl.do?method=getBjxsgl&type=query",
				colList:[
				   {label:'guid',name:'guid', index: 'guid',hidden:true,key:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'12%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
				   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
				   {label:'����ѧ��',name:'xn', index: 'xn',width:'10%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onClick='Yrglview(\""+rowObject["guid"]+"\",\""+cellValue+"\")'>"+cellValue+"</a>";
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
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">ɾ��</a></li>						
							<li><a href="#" class="btn_dr" onclick="importConfig();return false;">����</a></li>
						</logic:equal>
						
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
				<span> ��������б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
