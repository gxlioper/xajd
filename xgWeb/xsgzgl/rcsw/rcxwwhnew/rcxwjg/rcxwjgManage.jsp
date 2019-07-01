<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwhnew/rcxwjg/rcxwjg.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				
				var gridSetting = {
						caption:"�ճ���Ϊ��Ϣ����б�",
				pager:"pager",
				params:getSuperSearch(),
				url:"rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwjgManage&type=query",
				colList:[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'7%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
				   {label:'��Ϊ���',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
                    {label:'��Ϊ����',name:'dlxx', index: 'dlxx',width:'15%'},
                    {label:'��Ϊ����',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%' ,hidden:true},
				   {label:'��ΪС��',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'10%'},
				   {label:'������ֵ',name:'fz', index: 'fz',width:'8%'},
				   {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'15%'},
				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true},
				   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
				],
				sortname: "rcxwjlsj",
			 	sortorder: "desc"
			}	
				//�����Ƽ���ѧ���Ի�
				if(jQuery("#xxdm").val() == '10704'){
					gridSetting["caption"] = "�ۺϲ�����Ϣ����б�";
					gridSetting["colList"] = [
						       				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						    				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
						    				   {label:'����',name:'xm', index: 'xm',width:'7%'},
						    				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
						    				   {label:jQuery("#xbmc").val(),name:'xymc', index: 'xymc',width:'12%'},
						    				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'12%'},
						    				   {label:'�ۺϲ������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
						    				   {label:'�ۺϲ�������',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
						    				   {label:'�ۺϲ���С��',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
						    				   {label:'����ʱ��',name:'fssj', index: 'fssj',width:'10%'},
						    				   {label:'������ֵ',name:'fz', index: 'fz',width:'8%'},
						    				   {label:'ѧ��ѧ��',name:'xnxq', index: 'xnxq',width:'15%'},
						    				   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
						    				   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
						    				   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true},
						    				   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true}
					    					];
					
				}
				jQuery("#dataTable").initGrid(gridSetting);
	
			});
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
	
			function add() {
				var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=addXwjg";
				var title = "�����ճ���Ϊ���";
				if(${xxdm=="12970"}){
					title = "��������ѧ�ֽ��";
				}
				if(jQuery("#xxdm").val() == '10704'){
					title = "�����ۺϲ������";
				}
				showDialog(title, 950, 450, url);
			}
	
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
	
				if (rows.length != 1) {
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else{
					var sjly = rows[0]["sjly"];
					if (sjly == '1') {
						showAlertDivLayer("���������ݲ������޸ģ�");
						return false;
					} 
					var url = 'rcsw_rcxwwhnew_rcxwjggl.do?method=updateXwjg&id='
							+ rows[0]["id"] + '&xh=' + rows[0]["xh"] + '&rcxwlbdldm='
							+ rows[0]["rcxwlbdldm"] + '&rcxwlbdm=' + rows[0]["rcxwlbdm"]
							+ '&xn=' + rows[0]["xn"] + '&xq=' + rows[0]["xq"];
					var title = "�޸��ճ���Ϊ���";
					if(${xxdm=="12970"}){
						title = "�޸�����ѧ�ֽ��";
					}
					if(jQuery("#xxdm").val() == "10704"){
						title = "�޸��ۺϲ������";
					}
					showDialog(title, 850, 450, url);
				}
	
			}
	
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
	
				if (ids.length == 0) {
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("�������ݲ���ɾ����");
							return false;
						}
					}
	
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
						"okFun" : function() {
							jQuery.post("rcsw_rcxwwhnew_rcxwjggl.do?method=delXwjg", {
								values : ids.toString()
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
	
				}
			}
	
			function xwjgView(id, xh) {
				var title = "�鿴�ճ���Ϊ���";
				if(${xxdm=="12970"}){
					title = "�鿴����ѧ�ֽ��";
				}
				if(jQuery("#xxdm").val() == '10704'){
					title = "�鿴�ۺϲ������";
				}
				showDialog(title, 720, 520,
						"rcsw_rcxwwhnew_rcxwjggl.do?method=viewXwjg&id=" + id + "&xh=" + xh);
			}
	
			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
						+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
						+ "</a>";
			}

            function view() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length == 0) {
                    showAlertDivLayer("��ѡ����ҪԤ���ļ�¼��");
                }else{
                    var title = "�鿴�ճ���Ϊ���";
                    showDialog(title, 720, 520,
                        "rcsw_rcxwwhnew_rcxwjggl.do?method=viewXwjg&id=" + rows[0]["id"] + "&xh=" + rows[0]["xh"]);
				}
            }

            var DCCLBH = "rcsw_rcxwwhnew_rcxwjg.do";// dcclbh,�������ܱ��
	
			// �Զ��嵼�� ����
			function exportConfig() {
				// DCCLBH�������ܱ��,ִ�е�������
				customExport(DCCLBH, rcxwjgExportData);
			}
	
			// ��������
			function rcxwjgExportData() {
				setSearchTj();// ���ø߼���ѯ����
				var url = "rcsw_rcxwwhnew_rcxwjggl.do?method=exportData&dcclbh=" + DCCLBH;// dcclbh,�������ܱ��
				url = addSuperSearchParams(url);// ���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
	
			//�´�����Ʒ��ʵ������
			function xsPxsjDc(){
				var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=xsPxsjDc";
				var xqLength=jQuery("a[name=a_name_xq]").length;
				var xnLength=jQuery("a[name=a_name_xn]").length;
				var yqLength=jQuery("a[name=a_name_yqdm]").length;
				if(xqLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ�ڲ�ѯ������");
					return false;
				}
				if(xnLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
					return false;
				}
				if(yqLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ����ѯ������");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
				
			}
			
			function rcxwsjDc() {
				var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwsjDc";
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xnLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			//�ճ���Ϊ�ֵܷ���
			function rcxwtjbDc() {
				var url ="rcsw_rcxwwhnew_rcxwjggl.do?method=rcxwtjbDc";
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xnLength != "1"){
					showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
					return false;
				}
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			// ���뷽��
			function importData() {
				toImportDataNew("IMPORT_N730204_NEW");
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
		<html:form action="/rcsw_rcxwwhnew_rcxwjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwjg"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						<li><a href="#" onclick="importData();return false;" class="btn_dr">����</a></li>	
						</logic:equal>
						<!-- ��дȨ -->
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<li><a href="javascript:void(0);" onclick="view();" class="btn_xg">�鿴</a></li>
						<logic:equal value="13779" name="xxdm">
						<li><a href="#" class="btn_dy" onclick="printXfjl();return false;">ѧ��ѧ�ּ�¼������</a></li>
						</logic:equal>
						<%-- <logic:equal value="10868" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="sxpdcjhzDc();return false;">�ɼ����ܵ���</a></li>
						</logic:equal>		 --%>
<%--						<logic:equal value="13023" name="xxdm">--%>
<%--							<li><a href="#" class="btn_dc" onclick="rcxwsjDc();return false;">���ʲ����ֵ���</a></li>--%>
<%--						</logic:equal>--%>
<%--						<logic:equal value="10071" name="xxdm">--%>
<%--							<li><a href="#" class="btn_dc" onclick="rcxwtjbDc();return false;">�ճ���Ϊͳ�Ʊ���</a></li>--%>
<%--						</logic:equal>--%>
						<!-- δ�޸� end-->
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<logic:equal name="xxdm" value="12970">	
					<span>����ѧ����Ϣ����б�&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="12970">
					<span>
						<logic:equal value="10704" name="xxdm">
							 �ۺϲ�����Ϣ����б�
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">
							�ճ���Ϊ��Ϣ����б�&nbsp;&nbsp;							
						</logic:notEqual>
					</span>
				</logic:notEqual>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
