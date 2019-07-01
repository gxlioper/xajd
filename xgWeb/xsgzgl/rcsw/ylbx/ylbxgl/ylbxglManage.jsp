<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/ylbx/ylbxgl/js/ylbxglManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		var gridSetting;
		var gridSetting2;
		jQuery(function(){
			 	gridSetting = {
						caption:"ҽ�Ʊ����б�",
						pager:"pager",
						url:"rcsw_ylbx_ylbxglgl.do?method=ylbxglManage&type=query",
						colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink,key:true},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'4%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'10%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'18%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
						   {label:'ѧ��',name:'xz', index: 'xz',width:'6%'},
						   {label:'���ս�ֹʱ��',name:'zd22', index: 'zd22',width:'12%'},
						   {label:'�轻����',name:'xjnx', index: 'xjnx',width:'6%'}, 
						   {label:'�ͱ���־',name:'sfkns', index: 'sfkns',width:'6%'},
						   {label:'���id',name:'jgid', index: 'jgid',hidden:true}
						],
						params:{ylbxzt:"djz"},
						sortname: "xh",
					 	sortorder: "asc"
				}
			 	gridSetting2 = {
						caption:"ҽ�Ʊ����б�",
						pager:"pager",
						url:"rcsw_ylbx_ylbxglgl.do?method=ylbxglManage&type=query",
						colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',width:'11%',formatter:xhyjLink,key:true},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'4%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'11%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'17%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
						   {label:'ѧ��',name:'xz', index: 'xz',width:'6%'},
						   {label:'����',name:'zd23', index: 'zd23',width:'6%'},
						   {label:'���տ�ʼʱ��',name:'zd21', index: 'zd21',width:'12%'},
						   {label:'���ս�ֹʱ��',name:'zd22', index: 'zd22',width:'12%'},
						   {label:'�ͱ���־',name:'sfkns', index: 'sfkns',width:'6%'},
						   {label:'���id',name:'jgid', index: 'jgid',hidden:true}
						],
						params:{ylbxzt:"yjm"},
						sortname: "xh",
					 	sortorder: "asc"
				};
			 	var map = getSuperSearch();
				map["ylbxzt"] = "djz";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);			
				
		});

		function searchRs(){
			var map = getSuperSearch();
			var ylbxzt = jQuery("#ylbxzt").val();
			if (ylbxzt != ""){
				map["ylbxzt"] = ylbxzt;
			}
			jQuery("#dataTable").reloadGrid(map);
		}

		function selectTab(obj,ylbxzt){
			jQuery("#ylbxzt").val(ylbxzt);
			var map = getSuperSearch();
			map["ylbxzt"] = ylbxzt;
			if (ylbxzt == "djz"){
				jQuery("#pd_zj").css("display","");
				jQuery("#pd_xg").css("display","none");
				jQuery("#pd_del").css("display","none");
				jQuery("#pd_dr").css("display","none");
				jQuery("#pd_dc").css("display","none");
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			} else {			
				jQuery("#pd_zj").css("display","none");
				jQuery("#pd_xg").css("display","");
				jQuery("#pd_del").css("display","");
				jQuery("#pd_dr").css("display","");
				jQuery("#pd_dc").css("display","");
				gridSetting2["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting2);
			}
			jQuery(".ha").removeClass("ha");
			jQuery(obj).parent().addClass("ha");
		}
		function add(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length == 1) {
				
				var url = "rcsw_ylbx_ylbxglgl.do?method=addYlbxgl&xh=" + rows[0]["xh"] + '&yjnum=' + rows[0]["yjnum"];
				var title = "����ҽ�Ʊ���";
				showDialog(title,790,503,url);
			}else {
				if(rows.length == 0){
					// ���ݲ�ѯ���������������
					var yjnum_num = jQuery("a[name=a_name_yjnum]").length;
					var yjnumT = jQuery("a[name=a_name_yjnum]").eq(0).attr("id");
					var rowConut = jQuery("#rowConut").html();
					var url = "rcsw_ylbx_ylbxglgl.do?method=addYlbxglPl&xsnum="+rowConut+"&yjnum="+yjnumT;
					var title = "��������ҽ�Ʊ���";
					showDialog(title,790,331,url);
				}else{
					// ���ݹ�ѡ��¼������������
					var yjnumT = rows[0]['yjnum'];
					var xhs = new Array();
					for(var i=0;i<rows.length;i++){
						xhs.push(rows[i]['xh']);
					}
					jQuery("#xhPlHidden").val(xhs.join(','));
					var url = "rcsw_ylbx_ylbxglgl.do?method=addYlbxglPl&xsnum="+rows.length+"&yjnum="+yjnumT;
					var title = "��������ҽ�Ʊ���";
					showDialog(title,790,331,url);
				}
			}
		}

		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			}else {
				var url = 'rcsw_ylbx_ylbxglgl.do?method=updateYlbxgl&xh=' + rows[0]["xh"] + '&jgid=' + rows[0]["jgid"];
				var title = "�޸�ҽ�Ʊ���";
				showDialog(title, 790,503, url);
			}
		}

		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			}else {
				var rows = jQuery("#dataTable").getSeletRow();
				var jgids = new Array();
				for(var i=0;i<ids.length;i++){
					jgids.push(rows[i]['jgid']);
				}
				
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("rcsw_ylbx_ylbxglgl.do?method=delYlbxgl", {
							values : jgids.join(",")
						}, function(data) {
							var mes = "�ɹ�ɾ����<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>������";
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		function ylbxglView(jgid, xh) {
				showDialog("ҽ�Ʊ��ղ鿴", 700, 436, "rcsw_ylbx_ylbxglgl.do?method=viewOneYlbxgl&jgid=" + jgid
						+ "&xh=" + xh);
				
		}
		function ylbxglyjView(jgid, xh) {
			showDialog("ҽ�Ʊ��ղ鿴", 700, 436, "rcsw_ylbx_ylbxglgl.do?method=viewYlbxgl&jgid=" + jgid
					+ "&xh=" + xh);
				
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ylbxglView(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\",\"" + rowObject['ylbxzt'] + "\");'>" + cellValue
					+ "</a>";
		}
		function xhyjLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ylbxglyjView(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\",\"" + rowObject['ylbxzt'] + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_ylbx_ylbxgl.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, ylbxglExportData);
		}

		// ��������
		function ylbxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_ylbx_ylbxglgl.do?method=exportData&dcclbh=" + DCCLBH + "&ylbxzt=" + ylbxzt;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//�°浼��
		function dr() {
			// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
			toImportDataNew("IMPORT_N732605_YLBXGLNEW");
			return false;

		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_ylbx_ylbxglgl">
		<input type="hidden" id="xhPlHidden" value=""/>
		<input type="hidden" value="djz" id="ylbxzt"/>
		<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
						<!-- ��ť -->
						<div class="buttonbox">
							<ul>
					  			<logic:equal name="writeAble" value="yes">
								<li id="pd_zj">
									<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
								</li>
								<li id="pd_xg" style="display: none;">
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
								</li>
								<li id="pd_del" style="display: none;">
									<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
								</li>
								<li id="pd_dr" style="display: none;"><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">����</a></li>
					    		</logic:equal>
								<li id="pd_dc" style="display: none;"><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							</ul>
						</div>
				</logic:notEqual>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'djz');"><span>��������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'yjm');"><span>�ѽ�����</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ҽ�Ʊ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
