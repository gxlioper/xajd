<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxkqgl/jxkqjg/js/jxkqjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"��ѵ����б�",
								pager:"pager",
								url:"jxkqjg.do?method=jxkqjgList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'���ڽ��id',name:'kqid', index: 'kqid',key:true,hidden:true},
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'ѧԺ',name:'xymc', index: 'xymc'},
								   {label:'רҵ',name:'zymc', index: 'zymc'},
								   {label:'�༶',name:'bjmc', index: 'bjmc'},
								   {label:'��ѵ����',name:'jxmc', index: 'jxmc'},
								   {label:'�������',name:'kqlbmc', index: 'kqlbmc'},
								   {label:'��������',name:'kqlxmc', index: 'kqlxmc'},
								   {label:'����ʱ��',name:'kqsj', index: 'kqsj'}
								],
								sortname: "kqsj",
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
	<html:form action="/jxkqjg?method=jxkqjgList&type=query">
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
				<span> ���ڽ���б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
