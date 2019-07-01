<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/jgcx/jgcx/js/jgcx.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "���˶����б�",
					radioselect:true,
					pager : "pager",
					url : "khgljgcx.do?method=jgcxList&type=query",
					colList : [ 
					   {label : 'xmid',name : 'xmid',index : 'xmid',key:true ,hidden:true},
					   {label : 'khlx',name : 'khlx',index : 'khlx', hidden:true},
					   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '20%'}, 
					   {label : '��ʼʱ��',name : 'kssj',index : 'kssj',width : '20%'}, 
					   {label : '����ʱ��',name : 'jssj',index : 'jssj',width : '10%'},
					   {label : '���˶���',name : 'khdxmc',index : 'khdxmc',width : '10%'},
					   {label : '��������',name : 'sum',index : 'sum',width : '5%'}
					   ],
					sortname : "jssj",
					sortorder : "desc"
				}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khgljgcx" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="viewJg();return false;" class="btn_ck">�鿴</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>��Ŀ�б�</span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
