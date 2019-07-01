<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script>
            var gridSetting = {
                caption:"��λ���ά���б�",
                pager:"pager",
                url:"qgzx_jcdmwh_ajax.do?method=gwxzWh",
                colList:[
                    {label:'��λ������',name:'gwxzdm', index:'gwxzdm',width:'8%',key:true},
                    {label:'��λ��������',name:'gwxzmc', index: 'gwxzmc',width:'13%'},
                    {label:'н������',name:'gwxcsx', index: 'gwxcsx',width:'10%'},
                    {label:'��ʱ����',name:'gssx', index: 'gssx',width:'7%'},
                    {label:'ʱн',name:'sx', index: 'sx',width:'7%'},
                    {label:'˵��',name:'label', index: 'label',width:'20%'}
                ],
                sortname: "gwxzdm",
                sortorder: "desc"
            };
            //��ʼ��
            jQuery(document).ready(function(){
                var map = getSuperSearch();
                gridSetting["params"] = map;
                jQuery("#dataTable").initGrid(gridSetting);
            });

            function searchRs(){
                var map = getSuperSearch();
                jQuery("#dataTable").reloadGrid(map);
            }
		
		function gwxzwhExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("qgzx_jcdmwh_gwxzwh.do", gwxzwhExportData);
			}
			
		
			
		// ��������
		function gwxzwhExportData() {
			//setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_jcdmwh_ajax.do?method=gwxzwhExportData&dcclbh=" + "qgzx_jcdmwh_gwxzwh.do";//dcclbh,�������ܱ��
			//url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		function gwxzDiv1(type,topMsg){
			 var url = "";
            var doType = type;
			if(type=="update"){
                var row = jQuery("#dataTable").getSeletRow();
                if(row.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵ����ݣ�");
                    return;
                }
				url="qgzx_jcdmwh_ajax.do?method=gwxzZjxg&doType="+doType+"&gwxzdm=" + row[0]["gwxzdm"];

			}
			
				var title = topMsg;
				if(type=="add"){
					url="qgzx_jcdmwh_ajax.do?method=gwxzZjxg&doType="+doType;
				}
				showDialog(title, 500, 270, url);
		}
		function gwxzSc(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length == 0){
                showAlertDivLayer("��ѡ����Ҫɶɾ�������ݣ�");
                return;
            }
            var ids = jQuery("#dataTable").getSeletIds();
            var url = "qgzx_jcdmwh_ajax.do?method=gwxzSc";
            showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
                jQuery.post(url,{gwxzdm:ids.toString()},function(data){
                    showAlert(data["message"],{},{"clkFun" :function(){
                        jQuery("#dataTable").reloadGrid();
					}});
                },'json');
			}});

		}
		</script>
	</head>
	<body>
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jcdmwh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="gwxzDiv1('add','���Ӹ�λ���');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="gwxzDiv1('update','�޸ĸ�λ���');return false;" class="btn_xg">�޸�</a></li>
						<li><a href="#" onclick="gwxzSc();return false;" class="btn_sc">ɾ��</a></li>
						</logic:equal>
						
						<li><a href="#" onclick="gwxzwhExportConfig();return false;" class="btn_dc">����</a></li>
					</ul>
				</div>
				<div style="display: none;">
					<!-- �������� -->
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
