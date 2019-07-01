<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dycjgl/dmwh/js/dmwh.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
            jQuery(function(){

                jQuery("#dataTable").initGrid(gridSetting);
                jQuery("#cxxmmc").bind("keypress",function(event){
                    if(event.keyCode==13||"13"==event.keyCode){
                        query();
                        return false;
                    }
                });
            })

            function query(){
                var map = {};
                map["cxxmmc"] = jQuery("#cxxmmc").val();
                jQuery("#dataTable").reloadGrid(map);
            }


            //����
            function add(){
                var url = "dycjgl_dmwh.do?method=addDmwh";
                var title = "���ӵ����ɼ���Ŀ";
                showDialog(title,500,350,url);
            }

            //�޸�
            function update(){
                var rows = jQuery("#dataTable").getSeletRow();

                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                } else {
                    var url = 'dycjgl_dmwh.do?method=updateDmwh&xmdm='+rows[0]["xmdm"];
                    var title = "�޸�";
                    showDialog(title,500,350,url);
                }
            }


            //ɾ��
            function del(){
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                var dms = "";
                for(var i=0;i<rows.length;i++){
                    if(i==rows.length-1)
					{
                        dms+=rows[i]["xmdm"];
					}
					else{
                        dms+=rows[i]["xmdm"]+",";
					}
                }

                if (dms.length == ""){
                    showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
                } else {
                    showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
                        jQuery.post("dycjgl_dmwh.do?method=delDmwh",{values:dms.toString()},function(data){
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                    }});

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
		<html:form action="/dycjgl_dmwh">
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">�����ɼ���Ŀ����</th>
						<td>
							<input type="text" id="cxxmmc" name="cxxmmc" maxleng="20" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		</html:form>
		<div class="formbox">
			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
