<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"�ɲ���������Ŀѡ���б�",
					pager:"pager",
					url:"ttxm_comm.do?method=getXmSelect&type=query",
					colList:[
					   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'12%',key:true},
					   {label:'��Ŀ����',name:'xmdm', index: 'xmdm',width:'12%',hidden:true},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'12%',hidden:true},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'12%',hidden:true},
					   {label:'�걨����',name:'bmmc', index: 'bmmc',width:'12%',hidden:true},
					   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'12%',hidden:true},
					   {label:'��Ŀ���',name:'xmjbmc', index: 'xmjbmc',width:'8%'},
					   {label:'������Ŀ',name:'sskmmc', index: 'sskmmc',width:'5%'},
					   {label:'��Ŀ��ʼʱ��',name:'xmkssj', index: 'xmkssj',width:'8%'},
					   {label:'�ɲ���������',name:'kcyrs', index: 'kcyrs',width:'20%'},
					   {label:'����ѧ��',name:'jcxf', index: 'jcxf',width:'20%'},
					   {label:'ͨ��������',name:'tgrs', index: 'tgrs',width:'20%'},
					   {label:'����������',name:'sqrs', index: 'sqrs',width:'20%',hidden:true},
					   {label:'����������',name:'splc', index: 'splc',width:'20%',hidden:true}
					],
					sortname: "xmmc",
				 	sortorder: "asc"
				}
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function save() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ŀ��");
					return false;
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				parentsW.jQuery("#xmmc").val(rows[0]['xmmc']);
				parentsW.jQuery("#xmdm").val(rows[0]['xmdm']);
				parentsW.jQuery("#xmjbmc").text(rows[0]['xmjbmc']);
				parentsW.jQuery("#xn").text(rows[0]['xn']);
				parentsW.jQuery("#xq").text(rows[0]['xqmc']);
				parentsW.jQuery("#sbbmmc").text(rows[0]['bmmc']);
				parentsW.jQuery("#lxdh").text(rows[0]['lxdh']);
				parentsW.jQuery("#sskmmc").text(rows[0]['sskmmc']);
				parentsW.jQuery("#jcxf").text(rows[0]['jcxf']);
				parentsW.jQuery("#kcyrs").text(rows[0]['kcyrs']);
				parentsW.jQuery("#sqrs").text(rows[0]['sqrs']);
				parentsW.jQuery("#tgrs").text(rows[0]['tgrs']);
				parentsW.jQuery("#xmkssj").text(rows[0]['xmkssj']);
				parentsW.reSelectXm();
				parentsW.calculateRs();
				closeDialog();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/ttxm_comm.do" onsubmit="return false;">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">���</a>
						</li>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
		</html:form>
		<div class="toolbox">
			<h3 class="datetitle_01">
				<span>�ɲ���������Ŀѡ���б�</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
