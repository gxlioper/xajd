<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsb/js/xmsb.js"></script>
				<script type="text/javascript">

					var gridSetting = {
								caption:"��Ŀ�걨�б�",
								pager:"pager",
								url:"jskpXmsb.do?method=getXmsbList&type=query",
								colList:[
								   {label:'��Ŀid',name:'xmid', index: 'xmid',key:true,hidden:true},
								   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
								   {label:'�걨����',name:'sbkg', index: 'sbkg',hidden:true},
								   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'12%'},
								   {label:'��Ŀ����',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
								   {label:'��Ŀ�������',name:'xmlbmc', index: 'xmlbmc',width:'10%'},
								   {label:'ָ����������',name:'zdbmmc', index: 'zdbmmc',width:'8%'},
								   {label:'������',name:'fzrxm', index: 'fzrxm',width:'8%'},
								   {label:'����ʱ��',name:'lxsj', index: 'lxsj',width:'8%'}
								],
								sortname: "lxsj",
							 	sortorder: "desc"
					};
					var gridSetting2 = {
							caption:"��Ŀ���걨�б�",
							pager:"pager",
							url:"jskpXmsb.do?method=getXmsbList&type=query",
							colList:[
								   {label:'����id',name:'sqid', index: 'sqid',key:true,hidden:true},
							       {label:'��Ŀid',name:'xmid', index: 'xmid',hidden:true},
								   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
								   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'12%'},
								   {label:'��Ŀ����',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
								   {label:'��Ŀ�������',name:'xmlbmc', index: 'xmlbmc',width:'10%'},
								   {label:'ָ����������',name:'zdbmmc', index: 'zdbmmc',width:'8%'},
								   {label:'������',name:'fzrxm', index: 'fzrxm',width:'8%'},
								   {label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'8%'},
								   {label:'�걨ʱ��',name:'sbsj', index: 'sbsj',width:'8%'}
							],
							sortname: "sbsj",
						 	sortorder: "desc"
					};
		
					jQuery(function(){
						var map = getSuperSearch();
						map["sbzt"]="0";
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
	<html:form action="/jskpXmsb">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="0" id="sbzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="qjsh.do?method=cancel"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sb">
							<a href="javascript:void(0);" onclick="xmsb();return false;" 
							   title="ѡ����Ҫ�걨�ļ�¼������ð�ť���Դ��걨ҳ�档"
							   class="btn_sh">�걨</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_sc" onclick="del();return false;"
							   title="ѡ��һ����¼������ð�ť������ɾ��ʧ�����˲�����"
							   class="btn_sc">ɾ��</a>
						</li>
						</logic:equal>						
						<li id="li_lcgz" style="display: none;"><a href="javascript:void(0);" onclick="lcgz();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>δ�걨</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>���걨</span></a></li>
			      </ul>
			</div>
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span id="title"> �б� 
				</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>