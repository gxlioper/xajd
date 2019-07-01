<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxxgsh/js/xxxgsh.js"></script>
		<script type="text/javascript">
		var gridSetting1 = {
				caption : "��ҵ����Ϣ�޸����������б�",
				pager : "pager",
				url : "xsxx_bysxx_xxxgsh.do?method=xgSqShList&type=query",
				colList : [ {
					label : 'sqid',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					formatter : dcmcLink
				}, {
					label : '���� ',
					name : 'xm',
					index : 'xm'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xymc'
				}, {
					label : '�༶',
					name : 'bjmc',
					index : 'bjmc'
				}, {
					label : '����ʱ��',
					name : 'xgsj',
					index : 'xgsj'
				}, {
					label : '���״̬',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				}, {
					label : '���״̬',
					name : 'shztmc',
					index : 'shzt',
					formatter : shzt
				}, {
					label : '���������',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '���id',
					name : 'guid',
					index : 'guid',
					hidden : true
				} ],
				sortname : "xgsj",
				sortorder : "desc"
			}
			var gridSetting2 = {
				caption : "��ҵ����Ϣ�޸�����������б�",
				pager : "pager",
				url : "xsxx_bysxx_xxxgsh.do?method=xgSqShList&type=query",
				colList : [ {
					label : 'sqid',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					formatter : dcmcLink
				}, {
					label : '���� ',
					name : 'xm',
					index : 'xm'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xymc'
				}, {
					label : '�༶',
					name : 'bjmc',
					index : 'bjmc'
				}, {
					label : '���ʱ��',
					name : 'shsj',
					index : 'shsj'
				}, {
					label : '���״̬',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				}, {
					label : '���״̬',
					name : 'shztmc',
					index : 'shzt',
					formatter : shzt
				}, {
					label : '���������',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '���id',
					name : 'guid',
					index : 'guid',
					hidden : true
				} ],
				sortname : "shsj",
				sortorder : "desc"
			}

				
		jQuery(function() {
			
			var map = getSuperSearch();
			map["shjg"] = "dsh";
			gridSetting1["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting1);
		});
		// ��������ת
		function dcmcLink(cellValue, rowObject) {
			var xh = rowObject.xh;
			var sqid = rowObject.sqid;
			if (xh == null) {
				xh = "";
			}
			cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='ckxx(\""
					+ xh + "\",\"" + sqid + "\" );return false;' >" + xh + "</a>";
			return cellValue;
		}
		// �鿴��Ϣ
		function ckxx(xh,sqid) {
			var url = "xsxx_bysxx_xxxgsq.do?method=showXgXx&xh=" + xh + "&sqid=" + sqid;
			var title = "��ҵ����Ϣ�޸�����鿴";
			showDialog(title, 850, 550, url);
		}
				</script>

	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xsxx_bysxx_xxxgsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- ��ť -->
					
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xgSqSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxshnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="shlcInfo();return false;" 
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
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ϣ�޸�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
