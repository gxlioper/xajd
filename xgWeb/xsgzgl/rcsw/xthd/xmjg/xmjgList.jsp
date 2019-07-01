<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmjg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"�����б�",
								pager:"pager",
								url:"rcsw_txhd_xmjg.do?method=xmjgList&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'����id',name:'guid', index: 'guid',key:true,hidden:true},
								   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
								   {label:'ѧ��',name:'xqmc', index: 'xq',width:'2%'},
								   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:dcmcLink},
								   {label:'����',name:'xm', index: 'xm',width:'11%'},
								   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
								   {label:'�༶',name:'bjmc',width:'90px', index: 'bjmc',width:'21%'},
								   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',hidden:true},
								   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'15%'},
								   {label:'����',name:'lbmc', index: 'lbmc',hidden:true},
								   {label:'�ʱ��',name:'hdsj', index: 'hdsj',width:'22%'},
								   {label:'��ص�',name:'hddd', index: 'hddd',hidden:true},
								   {label:'��������',name:'sqly', index: 'sqly',hidden:true},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
								   {label:'������',name:'sqr', index: 'sqr',hidden:true},
								   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
								],
								sortname: "guid",
							 	sortorder: "desc"
							};
						
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
	<html:form action="rcsw_txhd_xmjg.do?method=xmjgList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
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
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						<logic:equal value="12309" name="xxdm">
						<li><a href="javascript:void(0);" onclick="getHdsbWord();return false;" class="btn_down">��걨������</a></li> 
						</logic:equal>
						<%-- 
						<li><a href="javascript:void(0);" onclick="printQjjgb('qjjg.do?method=printQjjgb');return false;" class="btn_down">���ؽ����</a></li> 
						--%>  
						<%--
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>
				--%></ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �����б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
