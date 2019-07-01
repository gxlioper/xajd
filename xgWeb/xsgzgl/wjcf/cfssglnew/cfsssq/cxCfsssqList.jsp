<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfssglnew/cfsssq/js/cfsssq.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����������Ϣ�б�",
				pager:"pager",
				url:"wjcf_cfsssq.do?method=cxCfsssqList&type=query",
				colList:[
				   {label:'ssid',name:'ssid', index: 'ssid',hidden:true,key:true},
				   {label:'cfid',name:'cfid', index: 'cfid',hidden:true},
				   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh'},
				   {label:'����',name:'xm', index: 'xm'},
                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc'},
                    {label:'��Ժ',name:'symc', index: 'symc'},
				   {label:'ѧ��',name:'xn', index: 'xn'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
				   {label:'�������',name:'cflbmc', index: 'cflbmc'},
				   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
				   {label:'�����ĺ�',name:'sswh', index: 'sswh'},
				   {label:'���״̬',name:'shztmc', index: 'shztmc'},
				   {label:'���߽��',name:'ssjg', index: 'ssjg'},
				   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
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
						if(rows[i]['shztmc']!='�����'){
							showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("wjcf_cfsssq.do?method=cancelCfsssq",
							{
							 values:ids.toString(),
							 splcid : rows[0]['splcid'] 
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
				
			}

			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shztmc']!='δ�ύ' && rows[i]['shztmc']!='�˻�' ){
							showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
							return false;
						}
					}
						     
					showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("wjcf_cfsssq.do?method=submitCfsssq",
							{values:ids.toString(),
							 xh : rows[0]['xh']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

			// ͨ�ô�ӡ��������̨xxdm�ж�
			function getWordWjcfComman(type){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var flag = false;
					var ids = "";
					for(var i = 0; i < rows.length; i++){
						ids +=  rows[i]["cfid"];
						if(i < rows.length - 1){
							ids += ",";
						}
						if(rows[i]["shzt"] != '1'){
							 flag = true;
						 }
					}
					/*if(type == 'cfjctzs' && flag){
						showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
						return false;
					}*/
					
					var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type;
					window.open(url);
				 } else {
					 /*if(type == 'cfjctzs' && rows[0]["shzt"] != '1'){
						 showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
						 return false;
					 }*/
					var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type;
					window.open(url);
			     }
			}

            function getWord(){
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length == 0) {
                    showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
                } else if (rows.length > 1) {
                    var ids = "";
                    var xhs = "";
                    for (var i = 0; i < rows.length; i++) {
                        ids += rows[i]["cfid"];
                        xhs += rows[i]["xh"];
                        if (i < rows.length - 1) {
                            ids += ",";
                            xhs += ",";
                        }
                    }
                    var url = "wjcf_cfsssq.do?method=getWjcfjdsZip&value=" +ids+"&xhs="+ xhs;
                    window.open(url);}
                    else {
                    var url="wjcf_cfsssq.do?method=getWjcfjdsWord";
                    var url= url+"&cfid="+rows[0]["cfid"]+"&xh="+rows[0]["xh"];
                    window.open(url);
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
		<html:form action="/wjcf_cfsssq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">��������</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�����޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">ɾ��</a></li>	
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_sr">�ύ</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="lcgz()" id="btn_cs" class="btn_cs">���̸���</a></li>
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ begin -->
						<logic:equal name="xxdm" value="14008">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfsssq');return false;" class="btn_down">���ش������������</a></li>
						</logic:equal>
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ end -->
						<!-- �ӱ�����ʦ��ѧԺ begin -->
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">Υ�ʹ��־���������</a></li>
						</logic:equal>
						<!-- �ӱ�����ʦ��ѧԺ end -->
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
				<span> ������Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
