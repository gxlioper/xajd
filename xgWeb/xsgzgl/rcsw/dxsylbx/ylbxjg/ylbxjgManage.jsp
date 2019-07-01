<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/dxsylbx/ylbxjg/js/ylbxjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
		function getWord(){
			 var rows = jQuery("#dataTable").getSeletRow();
			 if (rows.length == 0){
				showAlertDivLayer("��ѡ��һ����¼��");
			 } else if (rows.length > 1){
				var url="rcsw_dxsylbx_ylbxjggl.do?method=getDxsylbxZip";
				var ids = jQuery("#dataTable").getSeletIds();
				var url= url+"&value="+ids;
				window.open(url);
			 } else {
				var url="rcsw_dxsylbx_ylbxjggl.do?method=getDxsylbxWord";
				var url= url+"&yljgid="+rows[0]["yljgid"]+"&xh="+rows[0]["xh"];
				window.open(url);
		     }
		}
		jQuery(function(){
			var btndr=jQuery("#btn_dr");
			//����
			if(btndr!=null){
				btndr.click(function () {
					//����ͨ�õĵ���function�������ǵ��빦��ģ����롣
					toImportData("IMPORT_N730805_YLBXJG");
					return false;
				});
			}
		});
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_dxsylbx_ylbxjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						</logic:equal>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">				
							<!-- ���ݴ�ѧ -->
							<logic:equal name="xxdm" value="10351">	
							<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>	
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ҽ�Ʊ��ս���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
