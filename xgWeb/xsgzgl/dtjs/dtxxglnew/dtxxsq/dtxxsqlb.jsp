<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
				<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxsq.js"></script>
				<script type="text/javascript">
				var gridSetting = {
						caption:"���������б�",
						pager:"pager",
						url:"dtxxsq.do?method=list&type=query",
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
						   {label:'���״̬',name:'shztmc', index: 'shztmc'},
						   <logic:equal value="13871" name="xxdm">
						   {label:'�뵳־Ը����',name:'zd3', index: 'zd3'},
						   </logic:equal>
						   {label:'jddm',name:'jddm', index: 'jddm',hidden:true},
						   {label:'splc',name:'splc',index:'splc',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
					}
					jQuery(function(){
						jQuery("#dataTable").initGrid(gridSetting);
					});

					function submitBusi(){
						var ids = jQuery("#dataTable").getSeletIds();
						if (ids.length != 1){
							showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
						} else {
							var rows = jQuery("#dataTable").getSeletRow();
							var shzt = rows[0]["shzt"];
							if(shzt!="0" && shzt!="3"){
								showAlertDivLayer("��ѡ��δ�ύ���˻صļ�¼��");
								return false;
							}
							showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
									if(shzt!="3"){
										// ��֤�Ƿ���ύ
										jQuery.post("dtxxsq.do?method=checkSfktj", {
											jddm:rows[0]["jddm"]
										}, function(data) {
											if(data ==null || data=="false"){
												showAlertDivLayer("������Ľ׶��ѹر����룬�޷��ύ����������ϵ����Ա��");
												return false;
											}else{
	
												// �ύ
												jQuery.post("dtxxsq.do?method=submitDtxx",
													{values:ids.toString(),
													 xh : rows[0]['xh']
													},function(data){
													showAlertDivLayer(data["message"]);
													jQuery("#dataTable").reloadGrid();
												},'json');
											}
										});
										
									}else{

										jQuery.post("dtxxsq.do?method=submitDtxx",
											{values:ids.toString(),
											 xh : rows[0]['xh']
											},function(data){
											showAlertDivLayer(data["message"]);
											jQuery("#dataTable").reloadGrid();
										},'json');
									}
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
								jQuery.post("dtxxsq.do?method=cancelDtxxsq",
									{
									 values:ids.toString(),
									 splcid : rows[0]['splc'] 
									},function(data){
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								},'json');
							}});
						}
						
					}

					//������Ա��չ�����
					function exportTysqb(){
						var ids = jQuery("#dataTable").getSeletIds();
						var rows = jQuery("#dataTable").getSeletRow();
						var jdmc = rows[0]["jdmc"];						
						var len = ids.length;
						if (len == 1) {
							if(rows[0]["jdmc"] != "��������" && rows[0]["jdmc"] != "�뵳��������" ){
								return showAlertDivLayer("��ѡ������������뵳�������Ӽ�¼��");
							}
							if(rows[0]["jdmc"] == "��������" || rows[0]["jdmc"] == "�뵳��������"){
								var url = "dtxxsq.do?method=getRtsqb";
								url += "&dtxxsqid=" + ids+"&flag=sq" + "&jdmc="+rows[0]["jdmc"];
							}
							window.open(url);
						} else if (len == 0) {
							showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
							return false;
						} else {
							for(var i=0;i < rows.length;i++){
								if(rows[i]["jdmc"] != "��������" &&rows[i]["jdmc"] != "�뵳��������"){
									return showAlertDivLayer("��ѡ������������뵳�������Ӽ�¼��");
								}
							}
							var url = "dtxxsq.do?method=getRtsqbZip";
							url += "&value=" + ids+"&flag=sq";
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
	<html:form action="/dtxxsq?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="dtxxsqLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="exportTysqb();return false;" class="btn_down">���ص�Ա����Ա��չ�����</a></li>
						</logic:equal>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>������Ϣ�����б�</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
