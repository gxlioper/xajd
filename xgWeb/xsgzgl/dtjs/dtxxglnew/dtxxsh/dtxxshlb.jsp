<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"���Ŵ�����б�",
						pager:"pager",
						url:"dtxxsh.do?method=list&type=query",
						colList:[
						   {label:'����id',name:'dtxxsqid', index: 'dtxxsqid',key:true,hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'�꼶',name:'nj', index: 'nj'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
                            {label:'��Ժ',name:'symc', index: 'symc'},
                            {label:'�����༶',name:'bjmc', index: 'bjmc'},
                            {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc'},
						   {label:'�Ա�',name:'xb', index: 'xb'},
						   {label:'����׶�����',name:'jdmc', index: 'jdmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'�����',name:'shr', index: 'shr',hidden:true},
						   {label:'���״̬',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'jddm',name:'jddm', index: 'jddm',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
				}
				var gridSetting2 = {
						caption:"����������б�",
						pager:"pager",
						url:"dtxxsh.do?method=list&type=query",
						colList:[
						   {label:'����id',name:'dtxxsqid', index: 'dtxxsqid',key:true,hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'�꼶',name:'nj', index: 'nj'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'�༶',name:'bjmc', index: 'bjmc'},
						   {label:'�Ա�',name:'xb', index: 'xb'},
						   {label:'����׶�����',name:'jdmc', index: 'jdmc'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
						   {label:'�����',name:'shr', index: 'shr',hidden:true},
						   {label:'���״̬',name:'shztmc', index: 'shztmc'},
						   {label:'shid',name:'shid', index: 'shid',hidden:true},
						   {label:'jddm',name:'jddm', index: 'jddm',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
				}
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
						jQuery("#btn_qxsh").click({splc:"splc",sfkq:"1",dtxxsqid:"dtxxsqid"},cxshnews_splc);
					});
				
				/*
				 * ����
				 */
				function cxshnews_splc(obj){
					var sfkq=obj.data.sfkq;
					var rows = jQuery("#dataTable").getSeletRow();
					if(sfkq=="1"){//���� �����һ���ɳ���
						if (rows.length != 1){
							alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
						} else {
							splc_cx_news(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);
						}
					}else{
						splc_cxs(rows[0][obj.data.splc],rows[0]["shid"],rows[0][obj.data.dtxxsqid]);

					}
				}
				/*
				 * �������̳���[���һ���ɳ���]
				 * shid ���id
				 * splc ��������id 
				 */
				function splc_cx_news(splc,shid,dtxxsqid){
					//���һ��������˺���õ�·��
					var cancelPath = jQuery("#cancelPath").val();
					confirmInfo("��ȷ��Ҫ����������?",function(ty){
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
									// �ж��Ƿ����һ������(1:���һ�������ɹ���
									if("1" == data["cancelFlg"]){
										jQuery.post(cancelPath,{splc:splc,shid:shid,dtxxsqid:dtxxsqid},function(result){
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
						}
					});
				}

				/*
				 * �������̳���
				 * shid ���id
				 * splc ��������id 
				 */
				function splc_cxs(splc,shid,dtxxsqid){
					confirmInfo("��ȷ��Ҫ����������?",function(ty){
						//alert(ty);
						if(ty=="ok"){
							jQuery.post("comm_spl.do?method=cxsh",{shlc:splc,shid:shid,dtxxsqid:dtxxsqid},function(data){
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									//if (parent.window){
										//refersh();
										jQuery("#dataTable").reloadGrid();
									//}
								}});
							},'json');
						}
					});
				}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/dtxxsh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="dtxxsh.do?method=cancel"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="dtxxsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_qxsh"
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			</div>
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span id="title"> ��ٴ�����б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
