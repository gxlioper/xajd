<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"�����б�",
						pager:"pager",
						url:"ttgl_stgl.do?method=stglList&type=query",
						colList:[
						   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'��������',name:'stqc', index: 'stqc',width:'10%',formatter:stLink},
						   {label:'��������',name:'stlx', index: 'stlx',width:'8%'},
						   {label:'ҵ��ָ����λ',name:'bmmc', index: 'bmmc',width:'15%'},
						   {label:'����ָ����ʦ',name:'stzdlsxm', index: 'stzdlsxm',width:'6%'},
						   {label:'��������',name:'strs', index: 'strs',width:'6%',formatter:ryLink},
						   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
						   {label:'״̬',name:'stzt', index: 'stzt',width:'10%'}
						],
						sortname: "stqc",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var url = "ttgl_stgl.do?method=addst";
            var height = jQuery(window).height()-210;
			showDialog("��������",790,height,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			var sjly = rows[0]["sjly"];
            var height = jQuery(window).height()-210;
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			}else if(sjly == '��������'){
				showAlertDivLayer("��������ݣ������޸ģ�");
			}else {
				var url = 'ttgl_stgl.do?method=updatest&jgid='+ rows[0]["jgid"];
				showDialog("�޸�����", 790,height, url);
			}

		}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("ttgl_stgl.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="���������������ɾ��!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

		function stLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='View(\""
					+ rowObject["jgid"] + "\");'>" + cellValue
					+ "</a>";
		}
		function View(jgid) {
            var height = jQuery(window).height()-210;
			showDialog("�鿴������Ϣ", 790,height, "ttgl_stgl.do?method=view&jgid=" + jgid);
		}
		function ryLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ViewRy(\""
					+ rowObject["jgid"] + "\");'>" + cellValue
					+ "</a>";
		}
		function ViewRy(jgid) {
			showDialog("������Ա��Ϣ", 550,300, "ttgl_stgl.do?method=viewry&jgid=" + jgid);
		}
		//�Զ��嵼�� ����
		function exportConfig() {
			customExport( "xg_ttgl_stgl.do", ExportData);
		}

		// ��������
		function ExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "ttgl_stgl.do?method=exportData&dcclbh=xg_ttgl_stgl.do";
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function zx() {
			var rows = jQuery("#dataTable").getSeletRow();
			var stzt = rows[0]["stzt"];
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫע���ļ�¼��");
			}else if(stzt == '��ע��'){
				showAlertDivLayer("��������ע����");
			}else {
			showConfirmDivLayer("��ȷ��Ҫע��ѡ��ļ�¼��", {
			"okFun" : function() {
				var url = 'ttgl_stgl.do?method=stzx&jgid='+ rows[0]["jgid"];
				jQuery.post(url, {}, function(data) {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
						}, 'json');
					}
				});
			}
		}
		//�°浼��
		function dr() {
			toImportDataNew("IMPORT_N830105_STGLJG");
			return false;

		}
				
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
		<html:form action="/xg_dwjl_dwjl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="userType" value="stu">
				  		    <logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">����</a></li>
							<li><a href="#" class="btn_shbtg" onclick="zx();return false;">ע��</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:notEqual>
						
					</ul>
				</div>
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�����б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
