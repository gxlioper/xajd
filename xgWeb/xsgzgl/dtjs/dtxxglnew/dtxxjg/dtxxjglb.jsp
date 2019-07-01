<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		        <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		        <script type="text/javascript" src="js/calendar/calendar.js"></script>
				<script type="text/javascript" src="xsgzgl/dtjs/dtxxglnew/js/dtxxjg.js"></script>
				<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
				<script type="text/javascript">
				var gridSetting = {
						caption:"���Ž���б�",
						pager:"pager",
						url:"dtxxjg.do?method=list&type=query",
						colList:[
						   {label:'���id',name:'dtxxjgid', index: 'dtxxjgid',key:true,hidden:true},
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:dcmcLink},
						   {label:'����',name:'xm', index: 'xm'},
						   {label:'�꼶',name:'nj', index: 'nj'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
                            {label:'��Ժ',name:'symc', index: 'symc'},
                            {label:'�����༶',name:'bjmc', index: 'bjmc'},
                            {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc'},
						   {label:'�Ա�',name:'xb', index: 'xb'},
						   {label:'��ǰ�׶�����',name:'jdmc', index: 'jdmc'},
						   {label:'��ʼʱ��',name:'kssj', index: 'kssj'},
						   <logic:equal value="13871" name="xxdm">
						   {label:'�뵳־Ը����',name:'zysbh', index: 'zysbh'},
						   </logic:equal>
						   {label:'��¼ʱ��',name:'jlsj', index: 'jlsj',hidden:true},
						   {label:'����id',name:'sqid', index: 'sqid',hidden:true},
						   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true}
						],
						sortname: "jlsj",
					 	sortorder: "asc"
					}
					jQuery(function(){
						jQuery("#dataTable").initGrid(gridSetting);
					});
					//������Ա/��Ա��չ�����
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
								url += "&dtxxsqid=" + ids+"&flag=jg" + "&jdmc=" + rows[0]["jdmc"];
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
							url += "&value=" + ids+"&flag=jg";
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
	<html:form action="/dtxxjg?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
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
						<li><a href="#" class="btn_sx" onclick="tbgxzzmm();return false;">ͬ������������ò</a></li>
						<li><a href="#" onclick="toImportData('IMPORT_N100110');return false;" class="btn_dr">��������</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportJjfzConfig();return false;">�������ӱ����Ǽ�</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportFzdxConfig();return false;">��չ���󱸰��Ǽ�</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportYbdyConfig();return false;">Ԥ����Ա���յǼ�</a></li>
						<li><a href="javascript:void(0);" class="btn_dc" onclick="exportYbzzConfig();return false;">Ԥ����Աת����ʾ</a></li>
						<logic:equal value="12865" name="xxdm">
						   <li><a href="javascript:void(0);" class="btn_dc" onclick="mcexport();return false;">�ⷢչԤ����Ա����</a></li>
						</logic:equal>
						<logic:equal value="14008" name="xxdm">
						   <li><a href="javascript:void(0);" class="btn_down" onclick="getYsqs();return false;">Ԥ����ʾ</a></li>
						   <li><a href="javascript:void(0);" class="btn_down" onclick="getXsyb();return false;">����Ԥ��</a></li>
						   <li><a href="javascript:void(0);" class="btn_down" onclick="getYbzz();return false;">Ԥ��ת��</a></li>
						</logic:equal>	
						<logic:equal name="xxdm"  value="70002">
							<li><a href="javascript:void(0);" onclick="exportTysqb();return false;" class="btn_down">���ص�Ա����Ա��չ�����</a></li>
						</logic:equal>
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
				<span>������Ϣ����б�</span>

			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
