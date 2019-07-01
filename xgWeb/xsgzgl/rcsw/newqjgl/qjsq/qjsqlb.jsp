<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsq/js/qjsq.js"></script>
				<script type="text/javascript">
					jQuery(function(){
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
						
					     var gridSetting = {
									caption:"��������б�",
									pager:"pager",
									url:"qjsq.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
									   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
                                       {label:'xydm',name:'xydm', index: 'xydm',hidden:true},
									   {label:'ѧ��',name:'xn', index: 'xn'},
									   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
									   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
									   {label:'����',name:'xm', index: 'xm'},
									   {label:'�������',name:'qjlxmc', index: 'qjlxmc'},
									   {label:'�������',name:'qjts', index: 'qjts'},
									   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
									   {label:'״̬',name:'qjzt', index: 'qjzt',hidden:true},
									   {label:'���״̬',name:'shztmc', index: 'shzt'},
									   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
									   {label:'���״̬',name:'qjlxid', index: 'qjlxid',hidden:true},
									   {label:'����״̬',name:'xjztmc', index: 'xjztmc'}
									],
									sortname: "sqsj",
								 	sortorder: "desc"
								}

					     var gridSetting1 = {
									caption:"��������б�",
									pager:"pager",
									url:"qjsq.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
									   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
									   {label:'ѧ��',name:'xn', index: 'xn',formatter:bs},
									   {label:'ѧ��',name:'xqmc', index: 'xqmc',formatter:bs},
									   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhFormat},
									   {label:'����',name:'xm', index: 'xm',formatter:bs},
									   {label:'�������',name:'qjlxmc', index: 'qjlxmc',formatter:bs},
									   {label:'�������',name:'qjts', index: 'qjts',formatter:bs},
									   {label:'����ʱ��',name:'sqsj', index: 'sqsj',formatter:bs},
									   {label:'״̬',name:'qjzt', index: 'qjzt',hidden:true},
									   {label:'���״̬',name:'shztmc', index: 'shzt',formatter:csFormat},
									   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
									   {label:'���״̬',name:'qjlxid', index: 'qjlxid',hidden:true},
									   {label:'sfcs',name:'sfcs', index: 'sfcs',hidden:true},
									   {label:'����״̬',name:'xjztmc', index: 'xjztmc',formatter:bs}
									],
									sortname: "sqsj",
								 	sortorder: "desc"
								}
						var gridSetting3 = {
									caption:"��������б�",
									pager:"pager",
									url:"qjsq.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'�������id',name:'qjsqid', index: 'qjsqid',key:true,hidden:true},
									   {label:'��������',name:'splcid', index: 'splcid',hidden:true},
									   {label:'ѧ��',name:'xn', index: 'xn'},
									   {label:'ѧ��',name:'xqmc', index: 'xqmc'},
									   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
									   {label:'����',name:'xm', index: 'xm'},
									   {label:'¥������',name:'ldmc', index: 'lddm'},
									   {label:'���Һ�',name:'qsh', index: 'qsh'},
									   {label:'�������',name:'qjlxmc', index: 'qjlxmc'},
									   {label:'�������',name:'qjts', index: 'qjts'},
									   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
									   {label:'״̬',name:'qjzt', index: 'qjzt',hidden:true},
									   {label:'���״̬',name:'shztmc', index: 'shzt'},
									   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
									   {label:'���״̬',name:'qjlxid', index: 'qjlxid',hidden:true},
									   {label:'����״̬',name:'xjztmc', index: 'xjztmc'}
									],
									sortname: "sqsj",
								 	sortorder: "desc"
								}
							if('12866' == jQuery("#xxdm").val()){
								jQuery("#dataTable").initGrid(gridSetting1);
							}else if('12303' == jQuery("#xxdm").val()){
								jQuery("#dataTable").initGrid(gridSetting3);
							}else{
								jQuery("#dataTable").initGrid(gridSetting);
							}
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/qjsq?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="swtjs" value="${swtjs}"/>
		<input type="hidden" id="search_go" onclick="reload()"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" id = "usertype" value = "${usertype}"/>
		<input type="hidden" id = "qjxy" value="${qjxy}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="qjxyCk();return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pltj();return false;" class="btn_tj">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>
						<li>
							<a href="javascript:void(0);" onclick="printQjsqb('qjsq.do?method=printQjsqb');return false;" class="btn_down">������������</a>
						</li>
					
					<!-- �㽭����ְҵѧԺ�����Ի���ӡ��������� -->	   
					<logic:equal name="xxdm" value="12869">
						<li>
							<a href="javascript:void(0);" onclick="printXsqjspb();return false;" class="btn_down">���������</a>
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
				<span style="width: 80%"> ��������б�
				<logic:equal value="12866" name="xxdm">								
						<font color="red">���ѳ�����ʼʱ���δ�ύ��δ�����Ľ������ʾ�� </font>					
					<a onclick="this.parentNode.style.display='none';" title="����" class="close"></a>
				</logic:equal><a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
