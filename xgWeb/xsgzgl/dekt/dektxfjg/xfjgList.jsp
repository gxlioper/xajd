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
				caption : "ѧ�ֲ�ѯ",
				pager : "pager",
				url : "dekt_xfjg.do?method=getXfjgList",
				colList : [
					{label:'jgid',name:'jgid',index :'jgid',key:true,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'8%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'14%'},
					{label:'�϶�����',name:'rdnr', index: 'rdnr',width:'46%'},
					{label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'10%'},
					{label:'ѧ��',name:'xf', index: 'xf',width:'10%'}
				],
				sortname: "hjsj",
			 	sortorder: "desc"
			};
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);

			jQuery("#pf").bind({click:function(){
				pf();
			}});
		})
		
		function viewXfjg(jgid, xh) {
			showDialog("ѧ�ֲ�ѯ", 700, 480, "dekt_xfjg.do?method=xfjgView&jgid=" + jgid+ "&xh=" + xh);
		}
		
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXfjg(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue	+ "</a>";
		}
		
		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport('dekt_xfjg_list.do', ExportDatas);
		}

		//��������
		function ExportDatas() {
			setSearchTj();//���ø߼���ѯ����
			var url = "dekt_xfjg.do?method=exportData&dcclbh=" + 'dekt_xfjg_list.do';//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}


		function pf(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ���۵ļ�¼��");
				return false;
			}
			var url = 'dekt_xfjg.do?method=pf&jgid=' + rows[0]["jgid"];
			var title = "����";
			showDialog(title, 400, 200, url);
		}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/dekt_xmwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<%-- <logic:equal name="writeAble" value="yes">
						 <li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="edit();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a></li>
						<li><a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >����</a></li>
						</logic:equal> --%>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
<%--						<logic:equal value="10346" name="xxdm">--%>
							<li>
								<a href="javascript:void(0);" class="btn_sz" id="pf">����</a> 
							</li>
<%--						</logic:equal>	--%>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>
					ѧ�ֲ�ѯ&nbsp;&nbsp;
					<logic:present name="xf">
						<logic:notEmpty name="xf">
							��ѧ�֣�
							<font color="red">
								${xf}
							</font>
						</logic:notEmpty>
					</logic:present>
				</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
