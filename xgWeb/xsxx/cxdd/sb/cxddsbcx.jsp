<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/cxdd/sb/js/cxddsb.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "�༶�б�",
				pager : "pager",
				url : "cxdd_pysb.do?method=getPysbList&type=query",
				colList : [ {
					label : 'key',
					name : 'bjid',
					index : 'bjid',
					key : true,
					hidden : true
				}, {
					label : '�꼶',
					name : 'nj',
					index : 'nj',
					width : '8%'
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				},{
					label : 'רҵ',
					name : 'zymc',
					index : 'zydm',
					width : '15%'
				},{
					label : '�༶',
					name : 'bjmc',
					index : 'bjdm',
					width : '15%'
				},{
					label : '�༶����',
					name : 'bjrs',
					index : 'bjrs',
					width : '10%',
					formatter : bjrsLink
				},{
					label : '�ύ��',
					name : 'tjr',
					index : 'tjr',
					width : '5%'
				}, {
					label : '���״̬',
					name : 'shztmc',
					index : 'shzt',
					width : '10%'
				}, {
					label : '��������',
					name : 'splc',
					index : 'splc',
				    hidden : true
				}, {
					label : '���״̬',
					name : 'shzt',
					index : 'shzt',
				    hidden : true
				},{
					label : '�༶',
					name : 'bjdm',
					index : 'bjdm',
				    hidden : true
				}],
				sortname : "bjdm",
				sortorder : "asc",
				radioselect:true
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
		<html:form action="/cxdd_pysb">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="whpy();return false;"  >ά������</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bjtj();return false;" class="btn_shuc" >�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bjtjcx();return false" class="btn_sr" >����</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_cs" onclick="lcgz();return false;">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color = "blue">${xn}${xqmc}</font> �༶�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
