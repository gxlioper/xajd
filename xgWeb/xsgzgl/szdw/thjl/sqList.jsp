<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	 	
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<%--<script type="text/javascript" src='xsgzgl/szdw/thjl/js/thjlManage.js'></script>--%>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption : "̸����¼�б�",
					pager : "pager",
					url : "szdw_thjl.do?method=sqList&doType=query",
					colList : [
						{label : 'sqid',name : 'sqid',index : 'sqid',hidden : true,key : true},
						{label : 'ѧ��',name : 'xh',index : 'xh'},
						{label : '����',name : 'xm',index : 'xm'},
						{label : '�Ա�',name : 'xb',index : 'xb'},
						{label : '�꼶',name : 'nj',index : 'nj'},
						{label : 'ѧԺ',name : 'xymc',index : 'xymc'},
						{label : '��Ժ',name : 'symc',index : 'symc'},
						{label : 'רҵ',name : 'zymc',index : 'zymc'},
						{label : '�����༶',name : 'bjmc',index : 'bjmc'},
						{label : 'רҵ�༶',name : 'zybjmc',index : 'zybjmc'},
						{label : '̸������',name : 'thsj',index : 'thsj'},
						{label : '̸����ʦ',name : 'jsxm',index : 'jsxm'},
						{label : '���״̬',name : 'shztmc',index : 'shztmc'},
                        {label : 'shzt',name : 'shzt',index : 'shzt',hidden:'true'},
                        {label : 'splc',name : 'splc',index : 'splc',hidden:'true'}
						],

					sortname : "",
					sortorder : ""
				};
				jQuery("#dataTable").initGrid(gridSetting);
			})
            function addThjl() {
                showDialog("����̸����¼", 700, 505, "szdw_thjl.do?method=zjsq&doType=add");
            }
            function updateThjl(){
                var rows = jQuery("#dataTable").getSeletRow();
                if(rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                    return false;
                }
                if(rows[0]["shzt"] != '0' && rows[0]["shzt"] != '3'){
                    showAlertDivLayer("��������ݲ����޸ģ�");
                    return;
                }
                showDialog("�޸�̸����¼",700,550,"szdw_thjl.do?method=zjsq&doType=update&sqid="+rows[0]["sqid"]);
            }
            function searchRs(){
                var map = getSuperSearch();
                jQuery("#dataTable").reloadGrid(map);
            }
            function submit(){
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
                }else{
                    var rows = jQuery("#dataTable").getSeletRow();
                    var url = "szdw_thjl.do?method=submit";
                    for(var i=0;i<ids.length;i++){
                        if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
                            showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
                            return false;
                        }
                    }
                    showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
                        jQuery.post(url,
                            {
                                values:ids.toString(),
                                splc : rows[0]['splc'],
                                shzt : rows[0]['shzt']
                            },function(data){
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            },'json');
                    }});
                }
            }
            function cancel(){
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length == 0) {
                    showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
                } else if (ids.length >1 ) {
                    showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
                } else {
                    var rows = jQuery("#dataTable").getSeletRow();
                    for(var i=0;i<ids.length;i++){
                        if(rows[i]['shzt']!='5'){
                            showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
                            return false;
                        }
                    }
                    showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
                        jQuery.post("szdw_thjl.do?method=cancel",
                            {
                                values:ids.toString(),
                                splc : rows[0]['splc']
                            },function(data){
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            },'json');
                    }});
                }
            }
            function Lcinfo(){
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                if (ids.length != 1){
                    showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
                } else {
                    var shzt = rows[0]["shzt"];
                    if ("0" == shzt){
                        showAlertDivLayer(jQuery("#lable_wxglcxx").val());
                        return false;
                    }
                    showDialog("���̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
                }
            }
            function deleteThjl() {
                var ids = jQuery("#dataTable").getSeletIds();
                if(ids == 0){
                    showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
                    return false;
                }
                var rows = jQuery("#dataTable").getSeletRow();
                for(var i=0;i<rows.length;i++){
                    if(rows[i]["shzt"] != '0' && rows[i]["shzt"] != '3'){
                        showAlertDivLayer("��������ݲ���ɾ������ȷ�ϣ�");
                        return false;
                    }
                }
                confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
                    if(ty=="ok"){
                        jQuery.post("szdw_thjl.do?method=del",{values:ids.toString()},function(data){
                            alertInfo(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                    }
                });
            }
            function viewThjl(val){
                var rows = jQuery("#dataTable").getSeletRow();
                if(rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                    return false;
                }
                showDialog("�鿴̸����¼����",700,550,"szdw_thjl.do?method=zjsq&doType=view&sqid="+rows[0]["sqid"]);
            }
		</script>
	</head>


	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_thjl" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="addThjl();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="#" onclick="updateThjl();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="deleteThjl();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="#" onclick="viewThjl();return false;" class="btn_ck">�鿴</a>
						</li>
						<li><a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">�ύ</a></li>
						<li><a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a></li>
						<li>
							<a href="#" onclick="Lcinfo();return false;" class="btn_cs">���̸���</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ̸����¼��Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
