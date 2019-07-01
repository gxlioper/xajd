<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					radioselect:true,
					url : "khglKhpf.do?method=khpflList&type=query",
					colList : [ 
					   {label : 'xmid',name : 'xmid',index : 'xmid',hidden:true},
					   {label : 'khbid',name : 'khbid',index : 'khbid',hidden:true}, 
					   {label : 'khlx',name : 'khlx',index : 'khlx',hidden:true},
					   {label : 'sfjs',name : 'sfjs',index : 'sfjs',hidden:true},
					   {label : 'ypcount',name : 'ypcount',index : 'ypcount',hidden:true},
					   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '20%'}, 
					   {label : '��ʼʱ��',name : 'kssj',index : 'kssj',width : '15%'}, 
					   {label : '����ʱ��',name : 'jssj',index : 'jssj',width : '15%'}, 
					   {label : '���˱�',name : 'khbmc',index : 'khbmc',width : '20%',formatter:setYl},
					   {label : '���˶���',name : 'khdxmc',index : 'khdxmc',width : '10%'},
<%--					   {label : '��������',name : 'dprs',index : 'dprs',width : '10%'},--%>
<%--					   {label : '��������',name : 'yprs',index : 'yprs',width : '10%'}--%>
						{label : '״̬',name : 'dpcount',index : 'dpcount',width : '10%',formatter:setZt}
					   ],
					sortname : "kssj",
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
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="quePf();" class="btn_xg">��ʼ����</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>�����б�</span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
