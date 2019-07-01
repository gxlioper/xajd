<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/cxdd/jg/js/cxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "����б�",
				pager : "pager",
				url : "cxdd_pyjg.do?method=getPyjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'xsid',
					index : 'xsid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '8%'
				}, {
					label : 'ѧ��',
					name : 'xqmc',
					index : 'xq',
					width : '8%'
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%',
				    formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '5%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjmc',
					width : '12%'
				},{
					label : '����',
					name : 'cxdjmc',
					index : 'cxdjmc',
					width : '6%'
				},{
					label : '������Դ',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				}],
				sortname : "tjsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		//���سɼ����浱
		function exportCjbgd(){
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if (len == 1) {
				var url = "cxdd_pyjg.do?method=exportCjbgd";
				url += "&xsid=" + ids;
				window.open(url);
			} else if (len == 0) {
				showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				return false;
			} else {
				var url = "cxdd_pyjg.do?method=getCjbgdZip";
				url += "&value=" + ids;
				window.open(url);
			}
		}

		//��ѧ��Ϣ����
		function kxxxSz(){
			var url = "cxdd_pyjg.do?method=kxxxSz";
			var title = "��ѧ��Ϣ����";
			showDialog(title, 500, 350, url);
		}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/cxdd_pyjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						<logic:equal name="xxdm" value="70002">
							<li><a href="javascript:void(0);" onclick="exportCjbgd();return false;" class="btn_down">���سɼ����浥</a></li>
							<li><a href="javascript:void(0);" onclick="kxxxSz();return false;" class="btn_down">��ѧ��Ϣ����</a></li>
						</logic:equal>
					</ul>
				</div>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
