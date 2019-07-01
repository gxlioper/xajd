<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfjcglnew/cfjcsq/js/cfjcsq.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����<bean:message key="wjcf.text" />��Ϣ�б�",
				pager:"pager",
				url:"wjcf_cfjcsq.do?method=cxCfjcsqList&type=query",
				colList:[
				   {label:'jcid',name:'jcid', index: 'jcid',hidden:true,key:true},
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
				   {label:'<bean:message key="wjcf.text" />�ĺ�',name:'jcwh', index: 'jcwh'},
				   {label:'<bean:message key="wjcf.text" />ʱ��',name:'jcsj', index: 'jcsj'},
				   {label:'<bean:message key="wjcf.text" />״̬',name:'shztmc', index: 'shztmc'},
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
						jQuery.post("wjcf_cfjcsq.do?method=cancelCfjcsq",
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
						jQuery.post("wjcf_cfjcsq.do?method=submitCfjcsq",
							{values:ids.toString(),
							 xh : rows[0]['xh']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function printJccfwjb() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				var len = ids.length;
				if (len == 1) {
					var url = "wjcf_cfjcsq.do?method=getJccfwjb";
					url += "&xh=" + ids+"&cfid="+rows[0]["cfid"];
					window.open(url);
				} else {
					showAlertDivLayer("��<font color='blue'>��ѡһ��</font>��ϣ�����صļ�¼��");
					return false;
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
					if(type == 'cfjctzs' && flag){
						showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
						return false;
					}
					
					var url="wjcf_cfsbgl.do?method=getDjbZip&value="+ids+"&type="+type;
					window.open(url);
				 } else {
					 if(type == 'cfjctzs' && rows[0]["shzt"] != '1'){
						 showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
						 return false;
					 }
					var url="wjcf_cfsbgl.do?method=getDjbWord&cfid="+rows[0]["cfid"]+"&type="+type;
					window.open(url);
			     }
			}

            function getCfjcWord() {
                var rows = jQuery("#dataTable").getSeletRow();

                if (rows.length == 0) {
                    showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
                } else if (rows.length > 1) {
                    var flag = false;
                    var ids = "";
                    var xhs = "";
                    var jcids="";
                    for (var i = 0; i < rows.length; i++) {
                        ids += rows[i]["cfid"];
                        xhs += rows[i]["xh"];
                        jcids += rows[i]["jcid"];
                        if (i < rows.length - 1) {
                            ids += ",";
                            xhs += ",";
                            jcids += ",";
                        }
                        if (rows[i]["shzt"] != '1') {
                            flag = true;
                        }
                    }
                    if (flag) {
                        showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
                        return false;
                    }

                    var url = "wjcf_cfsbgl.do?method=getCfjcZip&value="+ids+"&xhs="+xhs+"&jcids="+jcids;
                    window.open(url);
                } else {
                    if (rows[0]["shzt"] != '1') {
                        showAlertDivLayer("��ѡ�����ͨ���ļ�¼��");
                        return false;
                    }
                    var url = "wjcf_cfsbgl.do?method=getCfjcWord&cfid=" + rows[0]["cfid"] + "&xh=" + rows[0]["xh"]+"&jcid="+rows[0]["jcid"];
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
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>
			<input type="hidden" id="xbmc" value="���" />
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj"><bean:message key="wjcf.text" />��������</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg"><bean:message key="wjcf.text" />�����޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">ɾ��</a></li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>	
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_sr">�ύ</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="lcgz()" id="btn_cs" class="btn_cs">���̸���</a></li>
									
						<logic:equal name="xxdm" value="10606">							
							<li>
								<a href="javascript:void(0);" onclick="printJccfwjb();return false;"
									class="btn_dy"><bean:message key="wjcf.text" />�����ļ�����</a>
							</li>
						</logic:equal>
						<!-- ��ͨ��ó��ʦѧԺ begin -->
						<logic:equal name="xxdm" value="5002">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">���س������������</a></li>
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjctzs');return false;" class="btn_down">���س�������֪ͨ��</a></li>
						</logic:equal>
						<!-- ��ͨ��ó��ʦѧԺ end -->
						<!-- ������ũ��ְҵѧԺ begin -->
						<logic:equal name="xxdm" value="12727">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">���ؽ������������</a></li>
						</logic:equal>
						<!-- ������ũ��ְҵѧԺ end -->
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ begin -->
						<logic:equal name="xxdm" value="14008">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjcsq');return false;" class="btn_down">���ش��ֳ��������</a></li>
						</logic:equal>
						<!-- ������Ͽҽҩ�ߵ�ר��ѧУ end -->
						<!-- �ɶ�����ѧԺ begin -->
						<logic:equal name="xxdm" value="10653">	
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('wjjcsq');return false;" class="btn_down">���ش��ֳ��������</a></li>
						</logic:equal>
						<!-- �ɶ�����ѧԺ end-->
						<!-- ����ҽҩ�ߵ�ר��ѧУ begin -->
						<logic:equal value="70002" name="xxdm">
							<li><a href="javascript:void(0);" onclick="getWordWjcfComman('cfjgdy');return false;" class="btn_down">���ش��ֳ��������</a></li>
						</logic:equal>
						<!-- ����ҽҩ�ߵ�ר��ѧУ end -->
						<!-- �ӱ�����ʦ��ѧԺ begin -->
						<logic:equal name="xxdm" value="10098">
							<li><a href="javascript:void(0);" onclick="getCfjcWord();return false;" class="btn_down">�����������������</a></li>
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
				<span><bean:message key="wjcf.text" />��Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
