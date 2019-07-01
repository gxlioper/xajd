<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		      <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript">
		var gridSetting = {
				caption:"���ѧ����Ϣ",
				pager:"pager",
				url:"xsxx_xsxxgl.do?method=xycx&type=query",
				colList:[
					{label:'ѧ��',name:'xh', index: 'xh',key:true,width:'6%',formatter:xhLink },
					{label:'����',name:'xm', index: 'xm',width:'6%'},
					{label:'�꼶',name:'nj', index: 'nj',width:'2%'},
					{label:'��ǰѧԺ',name:'xymch', index: 'xymch',width:'10%'},
					{label:'����רҵȷ��ǰѧ԰',name:'xymc', index: 'xymc',width:'12%'}
				],
			};

		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\",\""+cellValue+"\");'>"+cellValue+"</a><input type='hidden' name='key_xh' value='" + rowObject["xh"] + "' >"
		}

		//�°�鿴������
		function zxsxxView(xh){
			showDialog("ѧ����Ϣ��ѯ",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
		}

		//�߼���ѯ
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		//�鿴
		function showzxsxxView(){

			var rows = jQuery("#dataTable").getSeletRow();
			 if (rows.length == 1){
				 var ids = jQuery("#dataTable").getSeletIds();
				 zxsxxView(ids);
			 } else {
				 alertInfo("��<font color='blue'>��ѡһ��</font>��ϣ���鿴�ļ�¼��");
		     }
		}

		//����
		function exportConfig(){
			var DCCLBH='xsxx_xycx.do';
			customExport(DCCLBH, exportData);
		}

		function exportData(){
			var DCCLBH='xsxx_xycx.do';
			setSearchTj();//���ø߼���ѯ����
			var url = "xsxx_xsxxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		// �����ʼ��
		function mmcsh() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlert("��ѡ��Ҫ��ʼ�������ѧ��");
			} else {
				showDialog("�����ʼ��", 350, 210, "xsxx_xsxxgl.do?method=mmcsh");
			}
			
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/xsxx_xsxxgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck"
								class="btn_ck"> �鿴 </a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
						</li>
						<li>
							<a href="#" onclick="mmcsh('show');return false;" class="btn_csh">�����ʼ��</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->

				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ����Ϣ</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
