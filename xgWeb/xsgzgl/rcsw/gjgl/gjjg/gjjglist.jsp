<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/gjgl/js/gjgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "��Ϣ�б�",
				pager : "pager",
				url : "xsgjgl.do?method=gjjgList&type=query",
				colList:[	
						    { label:'key',name:'gjxxid', index: 'gjxxid',hidden:true,key:true},						   
						    { label : 'ѧ��', name : 'xh', index : 'xh', width : '12%',formatter:xhLink},
							{ label : '����', name : 'xm', index : 'xm', width : '8%'},
							{ label : '����', name : 'qsh', index : 'qsh', width : '8%'},
							{ label : '���췽������', name : 'zbffzr', index : 'zbffzr', width : '8%'},
							{ label : '��ٿ�ʼʱ��', name : 'qjkssj', index : 'qjkssj', width : '8%'},
							{ label : '��ٽ���ʱ��', name : 'qjjssj', index : 'qjjssj', width : '8%'},
							{ label : '��ٽڴ�', name : 'qjjc', index : 'qjjc', width : '12%'},
							{ label : '�Ƿ����', name : 'sfgq', index : 'sfgq', width : '5%'},
							{ label : '������ʱ��', name : 'bgqsj', index : 'bgqsj', width : '8%'},
							{ label : '��ע', name : 'bz', index : 'bz', width : '8%'}
							],
							sortname: "",
					 		sortorder: "desc"
					 		//radioselect:true
					 	}	
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
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
		<html:form action="/xsgjgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<%--<li>
							<a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg" >�޸�</a>
						</li>--%>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="#" onclick="importXX();return false;" class="btn_dr">����</a>
						</li>
						</logic:equal>
							<li>
							<a href="#" class="btn_dc" onclick="exportXX();return false;">����</a>
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
				<span>ѧ��������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>