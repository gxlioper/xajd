<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/rcpy/rcpyjg/js/rcpyjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"�˲���������б�",
			pager:"pager",
			url:"rcpy_rcpyjg.do?method=getjgList&type=query",
			colList:[
			   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
			   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'����',name:'xm', index: 'xm',width:'5%'},
			   {label:'�꼶',name:'nj', index: 'nj',width:'4%'},
			   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'8%'},
			   {label:'�༶',name:'bjmc', index: 'bjmc',width:'8%'},
			   {label:'������Ŀ',name:'xmmc', index: 'xmmc',width:'8%'},
			   {label:'�������',name:'pylbmc', index: 'pylbmc',width:'8%'},
			   {label:'����ָ��',name:'khzbmc', index: 'khzbmc',width:'8%'},
			   {label:'��������',name:'xztjmc', index: 'xztjmc',width:'8%'},
			   {label:'������Դ',name:'sjlymc', index: 'sjlymc',width:'8%'},
			   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
			],
			sortname: "sqsj",
			sortorder: "desc"
		}
		gridSetting["params"]=getSuperSearch();
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
		<html:form action="/rcpy_rcpyjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr"
							>����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�˲���������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
