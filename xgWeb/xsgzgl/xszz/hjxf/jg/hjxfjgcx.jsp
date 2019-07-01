<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/hjxf/jg/js/hjxfjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "ѧ������ѧ����ϸ",
				pager : "pager",
				url : "hjxf_jg.do?method=getJgCx&type=query",
				colList : [ {
					label : 'key',
					name : 'jgid',
					index : 'jgid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : xhLink
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '4%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				}, {
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '10%'
				}, {
					label : 'ѧ��ѧ��',
					name : 'xnxq',
					index : 'xnxq',
					width : '7%'
				},{
					label : 'Ӧ�ɽ��',
					name : 'yjje',
					index : 'yjje',
					width : '7%'
				},{
					label : '�������',
					name : 'hjje',
					index : 'hjje',
					width : '7%'
				},
				{
					name : 'sjly',
					index : 'sjly',
					hidden : true
				}
				],
				sortname : "sqsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzssqgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
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
						<li><a href="#" class="btn_dy" onclick="printsqb();return false;">�����</a></li>
						<li><a href="#" class="btn_dy" onclick="exportHjxfhz();return false;">���ܱ�</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ������ѧ����ϸ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
