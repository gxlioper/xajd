<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjjg/js/operation.js"></script>
				<script type="text/javascript">
				jQuery(function(){
				     var gridSetting = {
								caption:"��ٽ���б�",
								pager:"pager",
								url:"qjjg.do?method=list&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'��ٽ��id',name:'qjjgid', index: 'qjjgid',key:true,hidden:true},
								   {label:'�������id',name:'qjsqid', index: 'qjsqid',hidden:true},
								   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
								   {label:'ѧ��',name:'xn', index: 'xn'},
								   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink,width:'10%'},
								   {label:'�����༶',name:'bjmc', index: 'bjmc'},
                                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc'},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'�������',name:'qjlxmc', index: 'qjlxid'},
								   {label:'��ٽ���ʱ��',name:'jssj', index: 'qjjssj',hidden:true},
								   {label:'��ٿ�ʼʱ��',name:'kssj', index: 'qjkssj',hidden:true},
								   {label:'�������',name:'qjts', index: 'qjts'},
								   {label:'���״̬',name:'qjzt', index: 'qjzt',hidden:true},
								   <logic:equal name="xxdm" value="12866">
								   {label:'������Ϣ',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="12866">
								   {label:'ʵ���������',name:'sjqjts', index: 'sjqjts'},
								   </logic:notEqual>
								   {label:'����״̬',name:'xjztmc', index: 'xjzt'},
								   <logic:equal name="xxdm" value="14008">
								   {label:'������Ϣ',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="14008">
								   {label:'���ʱ���',name:'qjsjd', index: 'qjsjd'},
								   </logic:notEqual>
								   <logic:equal name="xxdm" value="14008">
								   {label:'������',name:'xjrxm', index: 'xjrxm',width:'10%'},
								   </logic:equal>
								   {label:'����״̬',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'��������״̬',name:'xjshztmc', index: 'xjshztmc',width:'10%'},
								   {label:'xjqjjgid',name:'xjqjjgid', index: 'xjqjjgid',hidden:true},
								   {label:'xjshzt',name:'xjshzt', index: 'xjshzt',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
							}
					var gridSetting2 = {
								caption:"��ٽ���б�",
								pager:"pager",
								url:"qjjg.do?method=list&type=query",
								params:getSuperSearch(),
								colList:[
								   {label:'��ٽ��id',name:'qjjgid', index: 'qjjgid',key:true,hidden:true},
								   {label:'�������id',name:'qjsqid', index: 'qjsqid',hidden:true},
								   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
								   {label:'ѧ��',name:'xn', index: 'xn'},
								   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink,width:'10%'},
								   {label:'�༶',name:'bjmc', index: 'bjmc'},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'¥������',name:'ldmc', index: 'lddm'},
								   {label:'���Һ�',name:'qsh', index: 'qsh'},
								   {label:'�������',name:'qjlxmc', index: 'qjlxid'},
								   {label:'��ٽ���ʱ��',name:'jssj', index: 'qjjssj',hidden:true},
								   {label:'��ٿ�ʼʱ��',name:'kssj', index: 'qjkssj',hidden:true},
								   {label:'�������',name:'qjts', index: 'qjts'},
								   {label:'���״̬',name:'qjzt', index: 'qjzt',hidden:true},
								   <logic:equal name="xxdm" value="12866">
								   {label:'������Ϣ',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="12866">
								   {label:'ʵ���������',name:'sjqjts', index: 'sjqjts'},
								   </logic:notEqual>
								   {label:'����״̬',name:'xjztmc', index: 'xjzt'},
								   <logic:equal name="xxdm" value="14008">
								   {label:'������Ϣ',name:'qsxx', index: 'qsxx',width:'10%'},
								   </logic:equal>
								   <logic:notEqual name="xxdm" value="14008">
								   {label:'���ʱ���',name:'qjsjd', index: 'qjsjd'},
								   </logic:notEqual>
								   <logic:equal name="xxdm" value="14008">
								   {label:'������',name:'xjrxm', index: 'xjrxm',width:'10%'},
								   </logic:equal>
								   {label:'����״̬',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'��������״̬',name:'xjshztmc', index: 'xjshztmc',width:'10%'},
								   {label:'xjqjjgid',name:'xjqjjgid', index: 'xjqjjgid',hidden:true},
								   {label:'xjshzt',name:'xjshzt', index: 'xjshzt',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
							}						
						var map = getSuperSearch();
						if(jQuery("#xxdm").val() == '12303'){
							gridSetting2["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting2);
						}else{
							gridSetting["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting);
						}
				});
				//��������
				function xjsq(){
					var userStatus = jQuery("#userStatus").val();
					var myDate = jQuery("#currTime").val();
					var xxdm=jQuery("#xxdm").val();
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlertDivLayer("��ѡ��һ����Ҫ���ٵļ�¼��");
						return false;
					} 
					var xh =rows[0]["xh"];
					var kssj =rows[0]["kssj"];
					var qjsqid =rows[0]["qjsqid"];
					jQuery.ajaxSetup({async:false});
					var haveNewSqjl = false;
					/*
					jQuery.post("qjjg.do?method=haveNewSqjl", {
						qjsqid:qjsqid,xh:xh,kssj:kssj
					}, function(data) {
					
						if("true"==data["message"]){
							haveNewSqjl=true;
						}
					},'json');*/
					var xh=rows[0]["xh"];
					var xjzt=rows[0]["xjzt"];
					var jssj = rows[0]["jssj"];
					if(xjzt=="1"){//1��ʾ�Ѿ�����
						showAlertDivLayer("����Ϣ�Ѿ����٣������ظ�������");
						return false;
					}
					var days = dateDiff(myDate,jssj);
					//������Ͽ:δ���ٳ���8�죬��ʾ��������
					if(days>jQuery("#xjts").val()&&"14008"==xxdm&&"xx"!=userStatus&&false==haveNewSqjl){
						showAlertDivLayer("���δ���ٳ���"+jQuery("#xjts").val()+"�죬�������٣�");
						return false;
					}
					//������Ͽҽ��ר�����ٸ��Ի� 
					//���� XG_2016-0135 RA002 ���������������������������ʾȡ�� 20161215
					//	var qjts = rows[0]["qjts"];
					//	var qjzt = rows[0]["qjzt"];	
					//	var nzcs = false;
					//	if("14008" == xxdm && "0" == qjzt && "xx"!=userStatus) {
					//		var xjUrl = "qjjg.do?method=xjrpdOneToN";
					//		if(qjts > 9){
					//			xjUrl = "qjjg.do?method=xjrpdTenD";
					//		}
					//		jQuery.post(xjUrl, {
					//			qjsqid:qjsqid,xh:xh
					//		}, function(data) {
					//			if("true"==data["message"]){
					//				nzcs=true;
					//			}
					//		},'json');
					//		if(true == nzcs) {
					//			showAlertDivLayer("����Ȩ�Ը�ѧ���������ٲ�������ȷ�ϣ�");
					//			return false;
					//		}
					//	}

					var url = 'qjgl_xjsh.do?method=xjsqAdd&xh='+xh+'&qjjgid=' + rows[0]["qjjgid"];
					var title = "��������";
					showDialog(title, 800,450, url);
					jQuery.ajaxSetup({async:true});
				}
				/**
				 * ����
				 * @return
				 */
				function cancle(){
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						return showAlertDivLayer("��ѡ��һ��Ҫ�����ļ�¼��");
					} else {
						if(rows[0]["xjshzt"] != "5"){
							return showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
						}
						showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
							"okFun" : function() {
								jQuery.post("qjgl_xjsh.do?method=cancelSq", {
									qjjgid : rows[0]['qjjgid'],
									lcid : rows[0]['splc']
								}, function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
							}
						});
					}
				}
				function submitBusi(){
					var rows = jQuery("#dataTable").getSeletRow();
					if (rows.length != 1) {
						showAlert("��ѡ��һ��Ҫ�ύ�ļ�¼��");
						return false;
					} else {
						if(rows[0]["xjshzt"] != "0" && rows[0]["xjshzt"] != "3"){
							return showAlertDivLayer("ֻ�����˻ػ�δ�ύ�ļ�¼�����ύ��");
						}
						showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
							"okFun" : function() {
								jQuery.post("qjgl_xjsh.do?method=submit", {
									qjjgid : rows[0]['qjjgid'],
								}, function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
							}
						});
					}
				}
				//ɾ��
				function delxj(){
					var ids = jQuery("#dataTable").getSeletIds();
					if (ids.length == 0){
						showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
					} else {
						var flagstr = "";
						var rows = jQuery("#dataTable").getSeletRow();
						jQuery(rows).each(function(i,row){
							if(row["xjshzt"] == "x"){
								flagstr = "����δ��д���������룬������ɾ����"
								return;
							}else if(row["xjshzt"] != "0" && row["xjshzt"] != "3"){
								flagstr = "����������е��������룬������ɾ����"
								return;
							}
						})
						if(flagstr != ""){
							showAlertDivLayer(flagstr);
							return false;
						}
						showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("qjgl_xjsh.do?method=del",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
						}});
					}
				}

				/*
				 * ���̸���
				 */
				function lcgz() {
					var rows = jQuery("#dataTable").getSeletRow();
					if (1 != rows.length) {
						showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
						return false;
					} else {
						if(rows[0]["xjshzt"] == "0" || rows[0]["xjshzt"] == "x"){
							showAlertDivLayer(jQuery("#lable_wxglcxx").val());
							return false;
						}
						showDialog("�����������̸���",530,310, 'qjgl_xjsh.do?method=lcgz&sqid='
								+ rows[0]['qjjgid']);
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
	<html:form action="/qjjg?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden"  name="currTime" id="currTime" value="${currTime}"/>
		<input type="hidden"  name="xjts" id="xjts" value="${xjts}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:notEqual value="stu" name="usertype">
						<logic:equal name="writeAble" value="yes">	
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
								<a href="javascript:void(0);" onclick="xjcl();return false;" class="btn_shbtg">����</a>
							</li>
						</logic:equal>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					
						<li>
							<a href="javascript:void(0);" onclick="printQjjgb('qjjg.do?method=printQjjgb');return false;" class="btn_down">������������</a>
						</li>   
						<%--<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>--%>
					
					<!-- �㽭����ְҵѧԺ�����Ի���ӡ����������ӡ -->	   
					<logic:equal name="xxdm" value="12869">
						<li>
							<a href="javascript:void(0);" onclick="printXsqjspb();return false;" class="btn_down">���������</a>
						</li>
					</logic:equal> 
					</logic:notEqual>
				
					<%--ѧ�����������룬������� --%>
					<logic:equal value="stu" name="usertype">
					<logic:equal value="true" name="xjadmit">
						<li>
							<a href="javascript:void(0);" onclick="xjsq();return false;" class="btn_shbtg">��������</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">������������</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delxj();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
					</logic:equal>
					<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>
					</logic:equal>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ٽ���б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
