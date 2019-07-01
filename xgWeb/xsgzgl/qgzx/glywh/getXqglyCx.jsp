<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script>
		//��ʼ��
		jQuery(function(){ 
			var gridSetting = {
					caption : "",
					pager : "pager",
					url : "glygl_xqgly.do?method=searchForXqgly",
					colList : [ {
						label : 'ְ����',
						name : 'zgh',
						index : 'zgh',
						width : '10%'
					}, {
						label : 'xq',
						name : 'xq',
						index : 'xq',
						hidden : true
					}, {
						label : '����',
						name : 'xm',
						index : 'xm',
						width : '10%'
					}, {
						label : '�Ա�',
						name : 'xb',
						index : 'xb',
						width : '10%'
					}, {
						label : '����',
						name : 'bmmc',
						index : 'bmmc',
						width : '20%'
					}, {
						label : 'ѧ��',
						name : 'xqmc',
						index : 'xqmc',
						width : '20%'
					}]
				}
				jQuery("#dataTable").initGrid(gridSetting);
		});
		function selectTab(url) {
			document.location.href = url ;
		}
		function searchRs() {
			var map = {};
			jQuery("#dataTable").reloadGrid(map);
		}
			function del() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					var ids = "";
					for(var i=0;i<rows.length;i++){
						ids += rows[i]["zgh"]+rows[i]["xq"];
						if(i != rows.length-1){
							ids += ",";
						}
					}
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("glygl_xqgly.do?method=del",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					�˲˵�ά�������������������˵�λ��λ����е�У����˷�Χ
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/qgzx_glygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="showDialog('���ӹ���Ա', 550, 400, 'glygl_xqgly.do?method=add');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc">ɾ��</a></li>
					</ul>
				</div>
				</logic:equal>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li><a href="javascript:void(0);" onclick="selectTab('qgzx_glygl_glywh.do');"><span>�ڹ�����Ա</span></a></li>
			        <li  class="ha"><a href="javascript:void(0);" onclick="selectTab('qgzx_glygl_xqgly.do');"><span>У������Ա</span></a></li>
			      </ul>
			    </div>
				<div style="display: none;">
				<button type="button" id="search_go" onclick="searchRs();">
										��    ��
									</button>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
			</div>	
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ѯ���&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
