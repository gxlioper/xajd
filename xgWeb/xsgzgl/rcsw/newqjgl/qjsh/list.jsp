<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
				<script type="text/javascript">

					var gridSetting = {
								caption:"��ٴ�����б�",
								pager:"pager",
								url:"qjsh.do?method=list&type=query",
								colList:[
								   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
								   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
								   {label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
								   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
								   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
								   {label:'�����༶',name:'bjmc', index: 'bjmc',width:'8%'},
                                    {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'8%'},
								   {label:'����',name:'xm', index: 'xm',width:'8%'},
								   {label:'�������',name:'qjlxmc', index: 'qjlxid',width:'8%'},
								   {label:'�������',name:'qjts', index: 'qjts',width:'8%'},
								   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'8%'},
								   <logic:equal name="xxdm" value="12866">
								   		{label:'��ٿ�ʼʱ��',name:'kssj', index: 'kssj',width:'10%'},
								   		{label:'��ٽ���ʱ��',name:'jssj', index: 'jssj',width:'10%'},
								   	</logic:equal>
								   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%'},
								   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
								   {label:'�����',name:'shr', index: 'shr',hidden:true},
								   {label:'shid',name:'shid', index: 'shid',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
					}
					var gridSetting2 = {
							caption:"���������б�",
							pager:"pager",
							url:"qjsh.do?method=list&type=query",
							colList:[
							   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
							   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
							   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
							   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
							   {label:'����',name:'xm', index: 'xm',width:'8%'},
							   {label:'�������',name:'qjlxmc', index: 'qjlxid',width:'10%'},
							   {label:'�������',name:'qjts', index: 'qjts',width:'10%'},
							   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
							   <logic:equal name="xxdm" value="12866">
							   		{label:'��ٿ�ʼʱ��',name:'kssj', index: 'kssj',width:'10%'},
							   		{label:'��ٽ���ʱ��',name:'jssj', index: 'jssj',width:'10%'},
							   	</logic:equal>
							   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%'},
							   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
							   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'�����',name:'shr', index: 'shr',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
					}

					var gridSetting3 = {
							caption:"��ٴ�����б�",
							pager:"pager",
							url:"qjsh.do?method=list&type=query",
							colList:[
							   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
							   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
							   {label:'ѧ��',name:'xn', index: 'xn',width:'12%',formatter:bs},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%',formatter:bs},
							   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhFormat},
							   {label:'�༶',name:'bjmc', index: 'bjmc',width:'8%',formatter:bs},
							   {label:'����',name:'xm', index: 'xm',width:'8%',formatter:bs},
							   {label:'�������',name:'qjlxmc', index: 'qjlxid',width:'8%',formatter:bs},
							   {label:'�������',name:'qjts', index: 'qjts',width:'8%',formatter:bs},
							   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'8%',formatter:bs},							   
							   {label:'��ٿ�ʼʱ��',name:'kssj', index: 'kssj',width:'10%',formatter:bs},
							   {label:'��ٽ���ʱ��',name:'jssj', index: 'jssj',width:'10%',formatter:bs},							  
							   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%',formatter:csFormat},
							   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
							   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'�����',name:'shr', index: 'shr',hidden:true},
							   {label:'sfcs',name:'sfcs', index: 'sfcs',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
				}
				var gridSetting4 = {
								caption:"��ٴ�����б�",
								pager:"pager",
								url:"qjsh.do?method=list&type=query",
								colList:[
								   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
								   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
								   {label:'ѧ��',name:'xn', index: 'xn',width:'12%'},
								   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
								   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
								   {label:'�༶',name:'bjmc', index: 'bjmc',width:'8%'},
								   {label:'����',name:'xm', index: 'xm',width:'8%'},
								   {label:'¥������',name:'ldmc', index: 'lddm'},
								   {label:'���Һ�',name:'qsh', index: 'qsh'},
								   {label:'�������',name:'qjlxmc', index: 'qjlxid',width:'8%'},
								   {label:'�������',name:'qjts', index: 'qjts',width:'8%'},
								   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'8%'},
								   <logic:equal name="xxdm" value="12866">
								   		{label:'��ٿ�ʼʱ��',name:'kssj', index: 'kssj',width:'10%'},
								   		{label:'��ٽ���ʱ��',name:'jssj', index: 'jssj',width:'10%'},
								   	</logic:equal>
								   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%'},
								   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
								   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
								   {label:'�����',name:'shr', index: 'shr',hidden:true},
								   {label:'shid',name:'shid', index: 'shid',hidden:true}
								],
								sortname: "sqsj",
							 	sortorder: "desc"
					}
					var gridSetting5 = {
							caption:"���������б�",
							pager:"pager",
							url:"qjsh.do?method=list&type=query",
							colList:[
							   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
							   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
							   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
							   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
							   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:dcmcLink},
							   {label:'����',name:'xm', index: 'xm',width:'8%'},
							   {label:'¥������',name:'ldmc', index: 'lddm'},
							   {label:'���Һ�',name:'qsh', index: 'qsh'},
							   {label:'�������',name:'qjlxmc', index: 'qjlxid',width:'10%'},
							   {label:'�������',name:'qjts', index: 'qjts',width:'10%'},
							   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'10%'},
							   <logic:equal name="xxdm" value="12866">
							   		{label:'��ٿ�ʼʱ��',name:'kssj', index: 'kssj',width:'10%'},
							   		{label:'��ٽ���ʱ��',name:'jssj', index: 'jssj',width:'10%'},
							   	</logic:equal>
							   {label:'���״̬',name:'shztmc', index: 'shzt',width:'10%'},
							   {label:'xjzt',name:'xjzt', index: 'xjzt',hidden:true},
							   {label:'��λid',name:'gwid', index: 'gwid',hidden:true},
							   {label:'�����',name:'shr', index: 'shr',hidden:true},
							   {label:'shid',name:'shid', index: 'shid',hidden:true}
							],
							sortname: "sqsj",
						 	sortorder: "desc"
					}

					function bs(cellValue, rowObject){
						var sfcs = rowObject["sfcs"];
						if(sfcs == '1'){
							return '<font color="red">'+cellValue+'</font>';
						}else{
							return cellValue;
						}
					}
					
					function csFormat(cellValue, rowObject){
						var sfcs = rowObject["sfcs"];
						if(sfcs == '1'){
							var tds = jQuery(rowObject).find('td');
							return '<span><font color="red">��˳�ʱ</font></span>';
						}else{
							return cellValue;
						}
					}

					function xhFormat(cellValue, rowObject){
						var sfcs = rowObject["sfcs"];
						var qjsqid = rowObject["qjsqid"];
						if(sfcs == '1'){
							return  "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
							+ "')\" class='name'><font color=\"red\">" + cellValue + "</font></a>";
						}else{
							return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjsqid
									+ "')\" class='name'>" + cellValue + "</a>";
						}
					}
					
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						if(jQuery("#xxdm").val() == '12866'){
							gridSetting3["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting3);
						}else if(jQuery("#xxdm").val() == '12303'){
							gridSetting4["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting4);
						}else{
							gridSetting["params"] = map;
							jQuery("#dataTable").initGrid(gridSetting);
						}
						jQuery("#btn_qxsh").click(function (){
							var rows = jQuery("#dataTable").getSeletRow();
							if (rows.length != 1){
								showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
								return false;
							} else {
								var xjzt = rows[0]["xjzt"];
								if(xjzt=="1"){
									showAlertDivLayer("�������Ѿ����٣����ܳ�����");
									return false;
								}
							}
							var obj=new Object(0);
							obj["data"]={splc:"splcid",sfkq:"1"};
							cxqjsh(rows[0]["qjsqid"],rows[0][obj.data.splc],rows[0]["shid"]);
						});
					});
					function cxqjsh(qjsqid,splc,shid){
						//���һ��������˺���õ�·��
						var cancelPath = jQuery("#cancelPath").val();
						confirmInfo("��ȷ��Ҫ����������?",function(ty){
							if(ty=="ok"){
								jQuery.post("qjsh.do?method=cxshnew",{qjsqid:qjsqid,splcid:splc,shid:shid},function(data){
										// �ж��Ƿ����һ������(1:���һ�������ɹ���
										if("1" == data["cancelFlg"]){
											jQuery.post(cancelPath,{splcid:splc,shid:shid},function(result){
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


					function savePlsh(shzt,shyj){
						
						var rows = jQuery("#dataTable").getSeletRow();
						var guid = new Array();
						var gwids = new Array();
						var xhs = new Array();
						
						jQuery.each(rows,function(i,row){
							guid.push(row["qjsqid"]);
							gwids.push(row["gwid"]);
							xhs.push(row["xh"]);
						});

						jQuery.post(
							"qjsh.do?method=savePlsh",
							{
							 shzt:shzt,
							 id:guid,
							 gwids:gwids,
							 xhs:xhs,
							 shyj:shyj
							},function(data){
								
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},
							'json'
						);
					}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/qjsh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="qjsh.do?method=cancel"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="qjsh();return false;" 
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
				<span id="title"> ��ٴ�����б� 
				<logic:equal value="12866" name="xxdm">								
						<font color="red">���ѳ�����ʼʱ���δ�ύ��δ�����Ľ������ʾ�� </font>					
				</logic:equal>
				</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
