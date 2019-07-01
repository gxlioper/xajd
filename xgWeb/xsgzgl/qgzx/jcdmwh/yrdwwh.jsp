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
                caption:"���˵�λά���б�",
                pager:"pager",
                url:"qgzx_jcdmwh_ajax.do?method=yrdwWh",
                colList:[
                    {label:'key',name:'id', index: 'id',key:true,hidden:true},
                    {label:'��λ����',name:'yrdwmc', index: 'yrdwmc',formatter:function(cell,rowObject){
                        return "<a href='javascript:void(0);' class='name' onclick='view(\""
                            + rowObject["id"] + "\");'>" + cell
                            + "</a>";
					}},
                    {label:'������',name:'xm', index: 'xm',width:'10%'},
                    {label:'��λ���',name:'dwlb', index: 'dwlb',formatter:function(cell,rowObject){
                        if(cell == "01"){
                            return "У�ڵ�λ";
						}
						return "У����ҵ";
					}},
                    {label:'��λ����',name:'gws', index: 'gws',width:'7%'},
                    {label:'��������',name:'gzrs', index: 'gzrs',width:'13%'}
                    /*{label:'����״̬',name:'qyzt', index: 'qyzt',width:'11%',formatter:function(cell,rowObject){
                        if(cell == "1"){
                            return "����";
						}
                        return "ͣ��";
					}}*/
                ],
                sortname: "",
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

		function yrdwwhExportConfig() {
			//DCCLBH�������ܱ��,ִ�е�������
			customExport("qgzx_jcdmwh_yrdwwh.do", yrdwwhExportData);
			}



		// ��������
		function yrdwwhExportData() {
			//setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_jcdmwh_ajax.do?method=yrdwwhExportData&dcclbh=" + "qgzx_jcdmwh_yrdwwh.do";//dcclbh,�������ܱ��
			//url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		function zjyrdw(){
			var url="qgzx_jcdmwh_ajax.do?method=yrdwZj";
			showDialog("���˵�λ����", 800, 600, url);
		}

		function xgyrdw(){
			var row = jQuery("#dataTable").getSeletRow();
		    if(row.length != 1){
                showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵ����ݣ�");
                return;
			}
			var url="qgzx_jcdmwh_ajax.do?method=yrdwXg&id="+row[0]["id"];
			showDialog("���˵�λ�޸�", 800, 600, url);
		}
		function view(id){
            var url="qgzx_jcdmwh_ajax.do?method=yrdwCk&id="+id;
            showDialog("���˵�λ�鿴", 800, 400, url);
		}
		function mmcsh(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length != 1){
                showAlertDivLayer("��ѡ��һ�����ݣ�");
                return;
            }
            if(row[0]["dwlb"] == "01"){
                showAlertDivLayer("��ѡ��У����ҵ��");
                return;
			}
            var url="qgzx_jcdmwh_ajax.do?method=mmcsh&id="+row[0]["id"];
            showDialog("���˵�λ�鿴", 400, 200, url);
		}
		function yrdwSc(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length == 0){
                showAlertDivLayer("��ѡ����Ҫɾ�������ݣ�");
                return;
            }
            var url="qgzx_jcdmwh_ajax.do?method=blsc&id="+row[0]["id"];
            jQuery.post(url,{},function(data){
                if (data["message"] == "ɾ���ɹ���") {
                    showAlert(data["message"], {}, {
                        "clkFun" : function() {
                            jQuery("#dataTable").reloadGrid();
                        }
                    });
                } else {
                    showAlert(data["message"]);
                }
			},'json')
		}
		</script>
	</head>
	<body>
		<html:form action="/qgzx_jcdmwh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="zjyrdw();return false;" class="btn_zj">������λ</a></li>
						<li><a href="#" onclick="xgyrdw();return false;" class="btn_xg">�޸ĵ�λ</a></li>
						<li><a href="#" onclick="yrdwSc();return false;" class="btn_sc">ɾ��</a></li>
						<%--<li><a href="#" onclick="" class="btn_xg">��������</a></li>--%>
						<%--<li><a href="#" onclick="" class="btn_xg">״̬����</a></li>--%>
						<%--<li><a href="#" onclick="mmcsh();return false;" class="btn_csh">�����ʼ��</a></li>--%>
						</logic:equal>

						<li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">����</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>

		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ������Ŀ�����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
