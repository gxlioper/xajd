<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

			var qsfGrid = {
				pager:"pager",
				url:"wsjcWsflr.do?method=getFscxList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xq'},
				   {label:'����ճ�����',name:'rcmc', index: 'rcmc'},
				   {label:'��ʼʱ��',name:'kssj', index: 'kssj'},
				   {label:'����ʱ��',name:'jssj', index: 'jssj'},
				   {label:'¥��',name:'ldmc', index: 'ldmc'},
				   {label:'����',name:'qsh', index: 'qsh'},
				   {label:'�ܷ�',name:'wszf', index: 'wszf'}
				],
				params:{jcdx:"0"},
				sortname: "wszf",
			 	sortorder: "desc"
			};
			
			var cwfGrid = {
				pager:"pager",
				url:"wsjcWsflr.do?method=getFscxList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xq'},
				   {label:'�ճ�����',name:'rcmc', index: 'rcmc'},
				   {label:'��ʼʱ��',name:'kssj', index: 'kssj'},
				   {label:'����ʱ��',name:'jssj', index: 'jssj'},
				   {label:'¥��',name:'ldmc', index: 'ldmc'},
				   {label:'����',name:'qsh', index: 'qsh'},
				   {label:'��λ',name:'cwh', index: 'cwh'},
				   {label:'�ܷ�',name:'wszf', index: 'wszf'}
				],
				params:{jcdx:"1"},
				sortname: "wszf",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(qsfGrid);
			});

			function selectTab(obj,jcdx){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				jQuery("#jcdx").val(jcdx);
				if ("1" == jcdx){
					jQuery("#dataTable").initGrid(cwfGrid);
				} else {
					jQuery("#dataTable").initGrid(qsfGrid);
				}
			}
			
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			//����
			function exportWsf(){
				var jcdx = jQuery("#jcdx").val();
				
				if ("1" == jcdx){
					customExport('gygl_wsjc_wsftj_cw', exportData);
				} else {
					customExport('gygl_wsjc_wsftj_qs', exportData);
				}
			}

			function exportData(){
				var jcdx = jQuery("#jcdx").val();
				var DCCLBH= "1" == jcdx ? "gygl_wsjc_wsftj_cw" : "gygl_wsjc_wsftj_qs";
				setSearchTj();//���ø߼���ѯ����
				
				var url = "wsjcWsflr.do?method=exportFscxList&dcclbh=" + DCCLBH+"&jcdx="+jcdx;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/wsjcJcxm" method="post" styleId="form">
	
		<input type="hidden" id="jcdx" value="0"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="exportWsf()" class="btn_dc">����</a></li>
					</ul>
				</div>
			</logic:equal>
			<!-- �������� -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>����</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��λ</span></a></li>
		      </ul>
			</div>
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��ѯ����б�</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
