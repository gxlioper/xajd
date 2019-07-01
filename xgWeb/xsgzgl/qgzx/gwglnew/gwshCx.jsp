<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsh.js"></script>
		<script language="javascript" defer="defer">
			
		var gridSetting = {
			colList:[
                {label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
				{label:'�к�',name:'r', index: 'r',width:'6%'},
				{label:'���˲���',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
				{label:'��λ����',name:'gwmc', index: 'gwmc',width:'16%',formatter:gwmcLink},
				{label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
				{label:'��������',name:'xqrs', index: 'xqrs',width:'8%'},
				{label:'������������',name:'knsrs', index: 'knsrs',width:'8%'},
				{label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'11%'},
				{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'12%'},
				{label:'���״̬',name:'shztmcx', index: 'shztmcx',width:'8%'},
				{label:'��λ��ʼʱ��',name:'gwkssj', index: 'gwkssj',hidden:true},
				{label:'��λ����ʱ��',name:'gwjssj', index: 'gwjssj',hidden:true},
				{label:'�Ƿ���Ч��λ',name:'sfyxgw', index: 'sfyxgw',hidden:true},
				{label:'ѧ��',name:'xn', index: 'xn',hidden:true},
				{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
				{label:'���Id',name:'shid', index: 'shid',hidden:true},
				{label:'��λId',name:'gwid', index: 'gwid',hidden:true},
				{label:'��������',name:'splcid', index: 'splcid',hidden:true},
				{label:'��������',name:'sqrzgh', index: 'sqrzgh',hidden:true}
			],
			caption:"����б�",
			pager:"pager",
			params:{shzt:"dsh"},
			url:"qgzx_gwglnew.do?method=gwshCx&type=query",
			sortname: "sqsj",
		 	sortorder: "desc"
		}

		var gridSetting2 = {
				colList:[
	                {label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'�к�',name:'r', index: 'r',width:'6%'},
					{label:'���˲���',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
					{label:'��λ����',name:'gwmc', index: 'gwmc',width:'16%',formatter:gwmcLink},
					{label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'��������',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'������������',name:'knsrs', index: 'knsrs',width:'8%'},
					{label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'11%'},
					{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'12%'},
					{label:'���״̬',name:'shztmcx', index: 'shztmcx',width:'8%'},
					{label:'��λ��ʼʱ��',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'��λ����ʱ��',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'�Ƿ���Ч��λ',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',hidden:true},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'���Id',name:'shid', index: 'shid',hidden:true},
					{label:'��λId',name:'gwid', index: 'gwid',hidden:true},
					{label:'��������',name:'splcid', index: 'splcid',hidden:true},
					{label:'��������',name:'sqrzgh', index: 'sqrzgh',hidden:true}
				],
				caption:"����б�",
				pager:"pager",
				params:{shzt:"ysh"},
				url:"qgzx_gwglnew.do?method=gwshCx&type=query",
				sortname: "sqsj",
			 	sortorder: "desc"
			}

		jQuery(document).ready(function(){ 
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function showView(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length==1){	
				var pkValue=rows[0]["gwdm"];
				var url="qgzx_gwglnew.do?method=gwsqXg&pkValue="+pkValue;
				url+="&doType=view";
				//showTopWin(url,720,500);
				showDialog('�鿴��λ����', 720, 450, url);
			}else{
				showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				return false;
			}
		}

		function gwmcLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='showViewLink(\""+rowObject["gwdm"]+"\");'>"+cellValue+"</a>";
		}
		function showViewLink(pkValue){
			var url="qgzx_gwglnew.do?method=gwsqXg&pkValue="+pkValue;
			url+="&doType=view";
			//showTopWin(url,720,500);
			showDialog('�鿴��λ����', 720, 450, url);
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="shzt" value="dsh"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="showModi();return false;" class="btn_xg">���</a>
						</li>
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<!-- 
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="showView();return false;">�鿴</a>
						</li>
						 -->
					</ul>
				</div>
					<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</html:form>
			<!-- �๦�ܲ����� end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>����б�&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
	</body>
</html>