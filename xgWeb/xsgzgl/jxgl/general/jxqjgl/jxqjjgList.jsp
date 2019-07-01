<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxqjgl/js/jxqjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"��ѵ����б�",
								pager:"pager",
								url:"jxqjjg.do?method=jxqjjgList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'��ٽ��id',name:'qjid', index: 'qjid',key:true,hidden:true},
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
								   {label:'ѧ��',name:'xn', index: 'xn'},
								   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
								   {label:'ѧԺ',name:'xymc', index: 'xymc'},
								   {label:'�༶',name:'zymc', index: 'zymc'},
								   {label:'�༶',name:'bjmc', index: 'bjmc'},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'�������',name:'qjlxmc', index: 'qjlxid'},
								   {label:'�������',name:'qjts', index: 'qjts'},
								   {label:'���ʱ���',name:'qjsjd', index: 'qjsjd'},
								   {label:'����ʱ��',name:'sqsj', index: 'sqsj',hidden:true},
								   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
							}
						jQuery("#dataTable").initGrid(gridSetting);
						
						var map = getSuperSearch();
						gridSetting["params"] = map;
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
	<html:form action="/jxqjjg?method=jxqjjgList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li><a href="javascript:void(0);" class="btn_dr" onclick="dr();return false;">����</a></li>	
					
					</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					<logic:notEqual name="xxdm" value="14073">
						<li><a href="javascript:void(0);" onclick="printjxqjjgb('jxqjjg.do?method=printQjjgb');return false;" class="btn_down">������ٵ�</a></li>
					</logic:notEqual>  
					<logic:equal name="xxdm" value="14073">
						<li><a href="javascript:void(0);" onclick="printjxqjjgb('jxqjjg.do?method=printQjjgb');return false;" class="btn_down">������ٵ�</a></li>
						<li><a href="javascript:void(0);" onclick="printjxqjBjd('jxqjjg.do?method=printBjd');return false;" class="btn_down">���ز��ٵ�</a></li>
					</logic:equal> 
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ٽ���б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
