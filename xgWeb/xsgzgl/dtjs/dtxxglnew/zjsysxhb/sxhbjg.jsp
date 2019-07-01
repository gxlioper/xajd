<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		        <script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/sxhb.js"></script>
				<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
				<script type="text/javascript">
				var gridSetting = {
						caption:"���Ž���б�",
						pager:"pager",
						url:"zjsy_sxhb.do?method=list&type=query",
						colList:[
						   {label:'���id',name:'sxhbid', index: 'sxhbid',key:true,hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'12%'},
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'�꼶',name:'nj', index: 'nj'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'18%'},
						   {label:'�Ͻ��׶�����',name:'jdmc', index: 'jdmc'},
						   {label:'�Ͻ�����',name:'sjfs', index: 'sjfs',width:'8%'},
						   {label:'�Ͻ�ʱ��',name:'sjsj', index: 'sjsj',width:'12%'},
						   {label:'�׶��Ͻ�����',name:'jdzs', index: 'jdzs',width:'8%'}
						],
						sortname: "sjsj",
					 	sortorder: "asc"
					}
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
	<html:form action="/zjsy_sxhb">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="mod();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li><a href="#" onclick="importConfig();return false;" class="btn_dr">��������</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</logic:equal>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>˼��㱨�����б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
