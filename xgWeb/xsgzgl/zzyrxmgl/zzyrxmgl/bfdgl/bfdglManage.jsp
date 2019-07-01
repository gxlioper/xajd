<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"��Ϣ�б�",
						pager:"pager",
						url:"zzyrxmglbfdgl.do?method=bfdglManage&type=query",
						colList:[
							{label:'key',name:'fdfbid', index: 'fdfbid',key:true ,hidden:true},
							{label:'fdxxid',name:'fdxxid', index: 'fdxxid',hidden:true},
						 	{label:'ѧԱѧ��',name:'bfdrxh', index: 'bfdrxh',width:'10%'},
							{label:'ѧԱ����',name:'bfdrxm', index: 'bfdrxm',width:'10%'},
							{label:'ѧԱ����ѧԺ',name:'bfdrxymc', index: 'bfdrxymc',width:'14%'},
							{label:'����ѧ��',name:'fdrxh', index: 'fdrxh',width:'10%'},
							{label:'��������',name:'fdrxm', index: 'fdrxm',width:'10%'},
							{label:'��������ѧԺ',name:'fdrxymc', index: 'fdrxymc',width:'14%'},
						 	{label:'������Ŀ',name:'fdkm', index: 'fdkm',width:'8%'},
						 	{label:'����ʱ��',name:'fdsj', index: 'fdsj',width:'15%'},
						 	{label:'��ע',name:'fbbz', index: 'fbbz',width:'12%',formatter:bzLink},
						 	{label:'���״̬',name:'shzt', index: 'shzt',width:'8%',formatter:shLink}
						],
						params:{fdkm:fdkm},
						sortname: "fdrxh",
					 	sortorder: "asc"
				};
				var fdkm = jQuery("#fdkm").val();
				var userType = "${userType }";
				if(!"stu" == userType){
					gridSetting["params"]=getSuperSearch();					
				}
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function query(){
				var map = {};
				map["fdkm"] = jQuery("#fdkm").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			function bzLink(c,r){
				var fbbz = r.fbbz;
				if(fbbz != "" && fbbz != null && fbbz.length > 8){
					return "<a href='javascript:void(0);' title='"+fbbz+"'>"+fbbz.substr(0,8)+"...</a>";
				}
				return fbbz;
			}
			function shLink(c,r){
				var shzt = r.shzt;
				if(shzt=="1"){
					return "ͬ��";
				}else if(shzt=="0"){
					return "��ͬ��";
				}else{
					return "�����";
				}
				return fbbz;
			}
			function add(){
<%--				var sqkg = jQuery("#sqkg").val();--%>
<%--				if(sqkg == '0' || sqkg == 0){--%>
<%--					showAlertDivLayer("���������ѹرգ�����ÿ�µ�ǰ���췢����");--%>
<%--					return false;--%>
<%--				}--%>
				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var url = "zzyrxmglbfdgl.do?method=addBfdgl";
				var title = "����";
				showDialog(title,750,465,url);
			}
			function mod() {
				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var fdrxh = rows[0]["fdrxh"];
					if(fdrxh != "" && fdrxh != null){
						showAlertDivLayer("����˵ļ�¼����������޸ģ�");
						return false;
					}
					var url = 'zzyrxmglbfdgl.do?method=updateBfdgl&fdfbid=' + rows[0]["fdfbid"];
					var title = "�޸�";
					showDialog(title,750,465,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("zzyrxmglbfdgl.do?method=delBfdgl", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
						}
					});
				}
			}
			function addPj(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_pj").val());
				} else {
					var shzt = rows[0]["shzt"];
					if(shzt == "0"){
						showAlertDivLayer("δͬ�⸨���������ۣ�");
						return false;
					}
					var fdxxid = rows[0]["fdxxid"];
					if(fdxxid == "" || fdxxid == null){
						showAlertDivLayer("��ʱ�������ۣ�");
						return false;
					}
					var url = "zzyrxmglbfdgl.do?method=checkCanpj";
					jQuery.post(url, {
						fdxxid : fdxxid,fdlx : '1'
					}, function(data) {
						if (data["message"] == "true") {
							var ul = 'zzyrxmglbfdgl.do?method=addBfdglpj&fdxxid=' + fdxxid +'&fdlx=1';
							var title = "����";
							showDialog(title,450,155,ul);
						} else {
							showAlertDivLayer("������¼��д2�μ�2�����Ϸ������ۣ�");
						}
					}, 'json');
				}
			}
			
			function addJl(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_tx").val());
				} else {
					var shzt = rows[0]["shzt"];
					if(shzt != "1"){
						showAlertDivLayer("δͬ�⸨��������д��¼��");
						return false;
					}
					var fdxxid = rows[0]["fdxxid"];
					if(fdxxid == "" || fdxxid == null){
						showAlertDivLayer("��ʱ������д��¼��");
						return false;
					}
					var url = 'zzyrxmglbfdgl.do?method=addBfdgljl&fdxxid=' + fdxxid +'&fdlx=1';
					var title = "��д��¼";
					showDialog(title,750,465,url);
				}
			}
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_ck").val());
				} else {
					var url = 'zzyrxmglbfdgl.do?method=viewBfdgl&fdfbid=' + rows[0]["fdfbid"];
					var title = "�鿴";
					showDialog(title,750,465,url);
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
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!--<input type="hidden" id="sqkg" value="${sfsq}" />-->
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<html:form action="/zzyrxmglbfdgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="mod();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="addJl();return false;" class="btn_zj">��д��¼</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="addPj();return false;" class="btn_zj">����</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">�鿴</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<!-- ѧ���û� -->
			<logic:equal name="userType" value="stu">
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">������Ŀ</th>
						<td width="20%">
							<input type="text" id="fdkm" name="fdkm" maxlength="20" onkeypress="if(pressEnter(event)){query();return false;}"/>
						</td>
						<td>
							<div class="btn" style="float: left;">
								<button type="button" class="btn_cx" id="search_go" onclick="query();" >
									�� ѯ
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
			</logic:equal>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
