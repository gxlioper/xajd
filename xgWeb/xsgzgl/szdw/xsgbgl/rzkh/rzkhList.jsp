<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/rzkh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "У��ס�޽���б�",
				pager : "pager",
				url : "szdw_xsgb_rzkhjg.do?method=rzkhjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'khjgid',
					index : 'khjgid',
					key : true,
					hidden : true
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
					width : '12%'
				},{
					label : 'ѧ��',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : 'ѧ��',
					name : 'xqmc',
					index : 'xq',
					width : '5%'
				}, {
					label : '���ڴ��',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				},{
					label : '��������',
					name : 'bjmc',
					index : 'bjdm',
					width : '10%'
				},{
					label : '����ְ��',
					name : 'zwmcname',
					index : 'zwmc',
					width : '12%'
				},{
					label : '��ְʱ��',
					name : 'rzsj',
					index : 'rzsjname',
					width : '10%'
				},{
					label : '��������',
					name : 'grzp',
					index : 'grzp',
					width : '10%'
				},{
					label : 'zydm',
					name : 'zymc',
					index : 'zydm',
					hidden : true
				},{
					label : 'nj',
					name : 'nj',
					index : 'nj',
					hidden : true
				}],
				sortname : "rzsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_xsgb_rzkhjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update()" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="KhjgViewv2();return false;" id="btn_ck" class="btn_ck">�鿴</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						<li><a href="#" class="btn_dy" onclick="printXyzsjgsqb();return false;">���ؿ��˱�</a></li>	
						<li><a href="#" class="btn_dy" onclick="printKhhzb();return false;">���ؿ��˻��ܱ�</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ���ɲ���ְ���˽��&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
