<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsh/js/txhdsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"��������������б�",
						pager:"pager",
						url:"rcsw_txhd_sh.do?method=list&type=query",
						colList:[
						   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'���� ',name:'xm', index: 'xm'},
						   {label:'�Ա�',name:'xb', index: 'xb'},
						   {label:'<bean:message key="lable.xb" />����',name:'xymc', index: 'xymc'},
						   {label:'�༶����',name:'bjmc', index: 'bjmc'},
						   {label:'������Ŀ',name:'xmmc', index: 'xmmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'���״̬',name:'shztmc', index: 'shzt',formatter:shzt},
						   {label:'���������',name:'mc', index: 'mc',hidden:true},
						   {label:'splc',name:'splc', index: 'splc',hidden:true},
						   {label:'shid',name:'shid', index: 'shid',hidden:true}
								],
						sortname: "sqsj",
					 	sortorder: "asc"
					}
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
						jQuery("#btn_qxsh").click(function (){
							var rows = jQuery("#dataTable").getSeletRow();
							if (rows.length != 1){
								showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
								return false;
							}
							var obj=new Object(0);
							obj["data"]={splc:"splc",sfkq:"1"};
							cxshnew_splc(obj);
						});
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcsw_txhd_sh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reloadsh()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="rcsw_txhd_sh.do?method=cancelSh"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="rcsw_txhd_sh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_qxsh"
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>
						</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
						   class="btn_cs">���̸���</a></li>	
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			</div>
				
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span id="title">��ѧ������б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
