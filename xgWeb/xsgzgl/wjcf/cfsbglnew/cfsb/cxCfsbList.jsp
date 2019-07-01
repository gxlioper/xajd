<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsb/js/cfsb.js"></script>
		
		<script type="text/javascript">
		if("13011" == ${xxdm}){
				var gridSetting = {
					caption:"Υ����Ϣ�б�",
					pager:"pager",
					url:"wjcf_cfsbgl.do?method=cxCfsbList&type=query",
					colList:[
					   {label:'pk',name:'cfid', index: 'cfid',hidden:true,key:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'sbjg',name:'sbjg', index: 'sbjg',hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'15%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'10%'},
					   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc',width:'15%'},
					   {label:'�������',name:'cflbmc', index: 'cflbmc',width:'15%'},
					   {label:'���״̬',name:'shjg', index: 'shjg',width:'10%'},
					   {label:'sbjg',name:'sbjg',index:'sbjg',hidden:true},
					   {label:'wjsj',name:'wjsj',index:'wjsj',hidden:true},
					   {label:'cflbdm',name:'cflbdm',index:'cflbdm',hidden:true}
					],
					sortname: "xh",
				 	sortorder: "asc"
				}
			}else{
				var gridSetting = {
					caption:"Υ����Ϣ�б�",
					pager:"pager",
					url:"wjcf_cfsbgl.do?method=cxCfsbList&type=query",
					colList:[
					   {label:'pk',name:'cfid', index: 'cfid',hidden:true,key:true},
					   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
					   {label:'sbjg',name:'sbjg', index: 'sbjg',hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'15%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
                        {label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'15%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'15%'},
					   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc',width:'15%'},
					   {label:'�������',name:'cflbmc', index: 'cflbmc',width:'15%'},
					   {label:'���״̬',name:'shjg', index: 'shjg',width:'10%'},
					   {label:'sbjg',name:'sbjg',index:'sbjg',hidden:true},
					   {label:'wjsj',name:'wjsj',index:'wjsj',hidden:true},
					   {label:'cflbdm',name:'cflbdm',index:'cflbdm',hidden:true}
					],
					sortname: "xh",
				 	sortorder: "asc"
				}
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='cfsbView(\""+rowObject["cfid"]+"\");'>"+cellValue+"</a>";
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


			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shjg']!='δ�ύ' && rows[i]['shjg']!='�˻�' ){
							showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
							return false;
						}
					}
					var xh = rows[0]['xh'];
					var cflbdm =rows[0]['cflbdm'];
					var wjsj = rows[0]['wjsj'];
					var cfid = ids.toString();
					
					// ��֤�����ڽ���⵱���Ƿ���� ����֤������ѧ�š�������𡢴���ʱ��
					jQuery.post("wjcf_cfsbgl.do?method=checkExistCfsbjg", {
						xh:xh,
						cflbdm:cflbdm,
						wjsj:wjsj,
						cfid:cfid
					}, function(data) {
						if(data ==null || data){
							showAlert("��ѧ����"+wjsj+"��Υ�����ڴ��ֽ���д��ڣ�");
							return false;
						}else{
							// �ύ
							showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
								jQuery.post("wjcf_cfsbgl.do?method=submitCfsb",
									{values:ids.toString(),
									 xh : rows[0]['xh']
									},function(data){
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								},'json');
							}});
						}
					},"json");
					
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
						if(rows[i]['shjg']!='�����'){
							showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("wjcf_cfsbgl.do?method=cancelCfsb",
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
			// ͨ�ô�ӡ��������̨xxdm�ж�
			function getWordWjcfComman(type){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					if(type == 'wjcftzs'){ 
						var flag = false;
						for(var i = 0; i < rows.length; i++){
							if(rows[i]["sbjg"] != '1'){
								 flag = true;
							 }
						}
						if(flag){
							showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
							return false;
						}
					}
					
					var ids = jQuery("#dataTable").getSeletIds();
					var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type;
					window.open(url);
				 } else {
					 if(type == 'wjcftzs' && rows[0]["sbjg"] != '1'){
						 showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
						 return false;
					 }
					var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type;
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
		<html:form action="/wjcf_cfsbgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">ɾ��</a></li>	
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" onclick="lcgz()" class="btn_cs">���̸���</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>				
						<!-- ��ͨ��ó��ʦѧԺ begin -->
						<logic:equal name="xxdm" value="5002">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">���ش����걨��</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcftzs');return false;" class="btn_down">���ش���֪ͨ��</a></li>
						</logic:equal>
						<!-- ��ͨ��ó��ʦѧԺ end -->
						<!-- ������ũ��ְҵѧԺ begin -->
						<logic:equal name="xxdm" value="12727">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">����Υ�ʹ���������</a></li>
						</logic:equal>
						<!-- ������ũ��ְҵѧԺ end -->
						<!-- ����ҽҩ�ߵ�ְҵѧУ begin -->
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">����Υ�ʹ���������</a></li>
						</logic:equal>
						<!-- ����ҽҩ�ߵ�ְҵѧУ end -->
						<!-- ����ְҵ����ѧԺ begin -->
						<logic:equal name="xxdm"  value="11773">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">����Υ�ʹ���������</a></li>
						</logic:equal>
						<!-- ����ְҵ����ѧԺend -->
						<!-- �ɶ�����ѧԺbegin -->
						<logic:equal name="xxdm" value="10653">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">���ɴ���ǼǱ�</a></li>
						</logic:equal>	
						<!-- �ɶ�����ѧԺend -->
						<!-- �Ĵ�ְҵ����ѧԺbegin -->
						<logic:equal name="xxdm" value="12970">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">���ɴ���ǼǱ�</a></li>
						</logic:equal>	
						<!-- �Ĵ�ְҵ����ѧԺend -->	
						<!-- �������ù���ְҵѧԺbegin -->
						<logic:equal name="xxdm" value="14073">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjcfsq');return false;" class="btn_down">Υ�ʹ��ֵǼǱ�</a></li>
						</logic:equal>	
						<!-- �������ù���ְҵѧԺend -->
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
				<span> Υ����Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
