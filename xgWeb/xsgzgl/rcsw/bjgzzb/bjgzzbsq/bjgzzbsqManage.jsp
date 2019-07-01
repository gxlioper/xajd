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
					caption:"�ܱ��б�",
					pager:"pager",
					url:"rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&type=query&gzzblx=bj",
					colList:[      
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					   {label:'��������',name:'splc', index: 'splc',hidden:true},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'11%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'5%'},
					   {label:'�ܴ�',name:'zcmc', index: 'zcmc',width:'8%'},
					   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
					   {label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
					   {label:'Ӧ��/ʵ��/���/δ��<br/>���ˣ�',name:'rstj', index: 'rstj',width:'9%'},
					   {label:'��дʱ��',name:'lrsj', index: 'lrsj',width:'9%'},
					   {label:'��д��',name:'lrrxm', index: 'lrrxm',width:'9%'},
					   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'10%'},
					   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
					   {label:'��д���û���',name:'lrr', index: 'lrr',hidden:true}
					],
					sortname: "lrsj",
				 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);

			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("false" == isopen){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_false_isopen").show();
				return false;
			}
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
				return false;
			}
			if("${zcListSize}" == "0"){
				showAlertDivLayer("ѧ������δ��ʼ��������ϵ����Ա��");
				return false;
			}
			var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=addXsgzzbsq&gzzblx=bj";
			var title = "�����ܱ�";
			showDialog(title,790,452,url);
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			if("${zcListSize}" == "0"){
				showAlertDivLayer("ѧ������δ��ʼ��������ϵ����Ա��");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" != shzt&&"3" != shzt) {
					showAlertDivLayer("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�");
					return false;
				}
				var userName = jQuery("#userName").val();
				if (userName != rows[0]["lrr"]) {
					showAlertDivLayer("�����޸������û���д���ܱ���");
					return false;
				}
				var url = 'rcsw_xsgzzb_xsgzzbsqgl.do?method=updateXsgzzbsq&gzzblx=bj&sqid=' + rows[0]["sqid"];
				var title = "�޸��ܱ�";
				showDialog(title,790,452,url);
			}
		}

		//ɾ��
		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				var userName = jQuery("#userName").val();
				for (var i = 0; i < ids.length; i++) {
					if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
						showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
						return false;
					}
					if (userName != rows[i]["lrr"]) {
						showAlertDivLayer("����ɾ�������û���д���ܱ���");
						return false;
					}
				}
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("rcsw_xsgzzb_xsgzzbsqgl.do?method=delXsgzzbsq&gzzblx=bj", { values : ids.toString() },
								function(data) {
									var mes = "�ɹ�ɾ����<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>������";
									showAlertDivLayer(mes);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
					}
				});
			}
		}

		function viewXsgzzbsq(sqid) {
			showDialog("�ܱ��鿴", 700, 445, "rcsw_xsgzzb_xsgzzbsqgl.do?method=viewXsgzzbsq&gzzblx=bj&sqid=" + sqid);
		}

		function bjmcLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXsgzzbsq(\""
					+ rowObject["sqid"] + "\");'>" + cellValue
					+ "</a>";
		}

		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1){
				if ("false" == isopen){
					showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
					return false;
				}
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if ('3'!=rows[0]["shzt"] && "false" == isopen){
					showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
					return false;
				}
				var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=submitXsgzzbsq&gzzblx=bj";
				if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
					showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
					return false;
				}
				var userName = jQuery("#userName").val();
				if (userName != rows[0]["lrr"]) {
					showAlertDivLayer("�����ύ�����û���д���ܱ���");
					return false;
				}
				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
						 splc : rows[0]["splc"],
						 shzt : rows[0]["shzt"]
						},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
			}
		}

		function cancel(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
			} else if (ids.length >1 ) {
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var userName = jQuery("#userName").val();
				for(var i=0;i<ids.length;i++){
					if (rows[i]['shzt'] != '5') {
						showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
						return false;
					}
					if (userName != rows[i]["lrr"]) {
						showAlertDivLayer("���ܳ��������û���д���ܱ���");
						return false;
					}
				}
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_xsgzzb_xsgzzbsqgl.do?method=cancelXsgzzbsq&gzzblx=bj",
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

		function xsgzzbLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" == shzt) {
					showAlertDivLayer('<bean:message key="lable.wxglcxx" />');
					return false;
				}
				showDialog("�ܱ��������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}

		var DCCLBH = "rcsw_bjgzzb_bjgzzbsq.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, xsgzzbsqExportData);
		}

		// ��������
		function xsgzzbsqExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=exportData&gzzblx=bj&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>

	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbjggl">
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
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_xsgzzb_xsgzzbsqb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >��д</a>
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
						<li>
							<a href="javascript:void(0);" onclick="xsgzzbLcinfo();return false;" title="ѡ��һ����¼������ð�ť���Բ鿴������̡�" class="btn_cs">���̸���</a>
						</li>	
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ܱ��б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
