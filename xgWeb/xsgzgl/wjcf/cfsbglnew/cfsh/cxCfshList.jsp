<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfsbglnew/cfsh/js/cfsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		if("13011" == ${xxdm}){
			var gridSetting = {
					caption:"Υ�ʹ�������б�",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'����',name:'xm', index: 'xm'},
							   {label:'רҵ',name:'zymc', index: 'zymc'},
							   {label:'ѧ��',name:'xn', index: 'xn'},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
							   {label:'�������',name:'cflbmc', index: 'cflbmc'},
							   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
							   {label:'����ʱ��',name:'cfsj', index: 'cfsj'},
							   {label:'���״̬',name:'shzt', index: 'shzt'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
					params:{shlx:"dsh"}
			}
		}else{
			var gridSetting = {
					caption:"Υ�ʹ�������б�",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'����',name:'xm', index: 'xm'},
                        		{label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
                        		{label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
							   {label:'ѧ��',name:'xn', index: 'xn'},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
							   {label:'�������',name:'cflbmc', index: 'cflbmc'},
							   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
							   {label:'����ʱ��',name:'cfsj', index: 'cfsj'},
							   {label:'���״̬',name:'shzt', index: 'shzt'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
					params:{shlx:"dsh"}
			}
		}
		if("13011" == ${xxdm}){
			var gridSetting2 = {
					caption:"Υ�ʹ�������б�",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'����',name:'xm', index: 'xm'},
							   {label:'רҵ',name:'zymc', index: 'zymc'},
							   {label:'ѧ��',name:'xn', index: 'xn'},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
							   {label:'�������',name:'cflbmc', index: 'cflbmc'},
							   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
							   {label:'����ʱ��',name:'cfsj', index: 'cfsj'},
							   {label:'���״̬',name:'shzt', index: 'shzt'},
                        	   {label:'���ʱ��',name:'shsj', index: 'shsj'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
                sortname : "shsj",
                sortorder : "desc",
                params:{shlx:"ysh"}
			}
		}else{
			var gridSetting2 = {
					caption:"Υ�ʹ�������б�",
					pager:"pager",
					url:"wjcf_cfsh.do?method=cxCfshList&type=query",
					colList:[
							   {label:'ѧ��',name:'xh', index: 'xh'},//,formatter:xhLink
							   {label:'����',name:'xm', index: 'xm'},
							   {label:'ѧ��',name:'xn', index: 'xn'},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
                        		{label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'15%'},
                        		{label:'��Ժ',name:'symc', index: 'symc',width:'15%'},
							   {label:'�������',name:'cflbmc', index: 'cflbmc'},
							   {label:'����ԭ��',name:'cfyymc', index: 'cfyymc'},
							   {label:'����ʱ��',name:'cfsj', index: 'cfsj'},
							   {label:'���״̬',name:'shzt', index: 'shzt'},
							   {label:'ywid',name:'ywid', index: 'ywid',hidden:true},
							   {label:'kzzd1',name:'kzzd1', index: 'kzzd1',hidden:true},
							   {label:'cffwqx',name:'cffwqx', index: 'cffwqx',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true},
							   {label:'cflbdm',name:'cflbdm', index: 'cflbdm',hidden:true}
					],
					params:{shlx:"ysh"}
			}
		}
			/*
			 * ����
			 */
			function cxshnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
				} else {
					splc_cx_new(rows[0]["splcid"],rows[0]["shid"],rows[0]["kzzd1"],rows[0]["cflbdm"]);
				}
			}

			/*
			 * �������̳���[���һ���ɳ���]
			 * shid ���id
			 * splc ��������id 
			 */
			function splc_cx_new(splc,shid,kzzd1,cflbdm){
				//���һ��������˺���õ�·��
				var cancelPath = jQuery("#cancelPath").val();
				confirmInfo("��ȷ��Ҫ����������?",function(ty){
					if(ty=="ok"){
						//�ж��Ƿ�ɳ���
						jQuery.post("wjcf_cfsh.do?method=canCancel",{splcid:splc,shid:shid},function(data){
							//�ɳ���
							if(data["message"]==""){
								jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
										// �ж��Ƿ����һ������(1:���һ�������ɹ���
										// �ж��Ƿ񴦷��������Ƿ��иĶ�����������Ȩ�޸�λ�����޸Ĵ�����𣬳���Ӧ�û�ԭ����һ�Σ�
										if("1" == data["cancelFlg"]||kzzd1!=cflbdm){
											jQuery.post(cancelPath,{splc:splc,shid:shid,kzzd1:kzzd1},function(result){
												showAlertDivLayer(result["message"],{},{"clkFun":function(){
													jQuery("#dataTable").reloadGrid();
												}});
											},'json');
										}else{
											showAlertDivLayer(data["message"],{},{"clkFun":function(){
												jQuery("#dataTable").reloadGrid();
											}});
										}
									
								},'json');
							}else{
								//���ɳ���
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							}
						},'json');
					}
				});
			}
			function showView() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 1) {
					var ywid = rows[0]["ywid"];
					var url = "wjcf_cfsh.do?method=cfshCk&ywid=";
					url += ywid;
					showDialog("�鿴�����Ϣ", 820, 500, url);
				} else {
					showAlertDivLayer("�빴ѡһ����Ҫ�鿴�ļ�¼��");
					return false;
				}
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				if("12930" == jQuery("#xxdm").val()){
					jQuery("#btn_sh").click(go_sh_12930);	
				}else{
					jQuery("#btn_sh").click(go_sh);
				}
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">���</a>
						<a href="javascript:void(0);" id="btn_qxsh" class="btn_qxsh">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a></li>
						<li>
							<a href="#" onclick="showView(); return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:equal value="yes" name="getCfjdWord">
						<li>
							<a href="#" onclick="getCfjdWord(); return false;" class="btn_down">���־���������</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�����ϱ�����б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
