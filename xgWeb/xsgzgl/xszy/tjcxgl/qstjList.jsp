<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/qsppgl/js/qsppgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/tjcxgl/js/tjcxgl.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"����ͳ��",
				pager:"pager",
				url:"qsppgl.do?method=getQstjList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'qsfpid',name:'qsfpid', index: 'qsfpid',hidden:true},
					{label:'���Һ�',name:'qsh', index: 'qsh',width:'5%',formatter:qshLink},
					{label:'¥������',name:'ldmc', index: 'ldmc',width:'8%'},
					{label:'ѧ԰',name:'xymc', index: 'xymc',width:'8%'},
					{label:'�Ա�',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'����',name:'dl', index: 'dl',width:'15%'},
					{label:'��ס<br/>����',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'�Ƿ��<br/>������',name:'sfhhqs', index: 'bz',width:'5%'},
					{label:'����Ժϵ',name:'ssyxmc', index: 'ssyxmc',width:'10%'},
					{label:'����֮<br/>�ѹ���',name:'zgh', index: 'zgh',width:'5%'},
					{label:'����֮<br/>������',name:'xm', index: 'xm',width:'5%'},
					{label:'����֮��<br/>��ϵ�绰',name:'lxdh', index: 'lxdh',width:'10%'}
					],
			 	radioselect:false
		}
		jQuery(function(){
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
		<html:form action="/qsppgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					    <li>
							<a href="javascript:void(0);" class="btn_dc" onclick="qstjExport();return false;"  >����</a>
						</li>
						 <li>
							<a href="javascript:void(0);" class="btn_dc" onclick="qstjExportGroup();return false;"  >�����ŵ���</a>
						</li>
						 <li>
							<a href="javascript:void(0);" class="btn_dc" onclick="fwExport();return false;"  >���ĵ���</a>
						</li>
					<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="ryzsPrint();return false;" class="btn_dy" >����֤�����ش�ӡ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="lxkPrint();return false;" class="btn_dy" >��ϵ�����ش�ӡ</a>
						</li>
						</logic:equal>					
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����ͳ���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
