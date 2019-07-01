<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/xszygl/js/xszygl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "����֮�ѽ���б�",
				pager : "pager",
				url : "xszygl.do?method=getXszyList&type=query",
				colList : [
							{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
							{ label : 'dwdm', name : 'dwdm', index : 'dwdm', hidden : true },
							{ label : 'nj', name : 'nj', index : 'nj', hidden : true },
							{ label : 'ְ����', name : 'zgh', index : 'zgh', width : '8%', formatter:zghLink},
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : '��������', name : 'bmmc', index : 'bmmc', width : '12%' },
							{ label : '������ò', name : 'zzmmmc', index : 'zzmmmc', width : '10%' },
							{ label : '��ϵ�绰', name : 'lxdh', index : 'lxdh', width : '10%'},
							{ label : 'ְ��ְ��', name : 'zwzc', index : 'zwzc', width : '5%' },
							{ label : '��Ժϵ���', name : 'kyxbj', index : 'kyxbj', width : '10%' ,formatter:kyxbjFormatter},
							{ label : '���Ժϵ', name : 'bjyxmc', index : 'bjyxmc', width : '12%'},
							{ label : 'ƥ������', name : 'ppqs', index : 'ppqs', width : '12%' }],
					sortname : "dwdm", sortorder : "desc" }
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
		<html:form action="/xszygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="nj" styleId="nj" value="${nj}"/>
			<div class="toolbox">
				<!-- ��ť -->
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
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="kyxbj();return false;" class="btn_sz" >��Ժϵ���</a>
						</li>
						<logic:equal name="userType" value="admin">
						<li>
							<a href="javascript:void(0);" onclick="fpyx();return false;" class="btn_sz" >���䵽Ժϵ</a>
						</li>
						</logic:equal>
						</logic:equal>
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
				<span>��ѯ���&nbsp;&nbsp; <font color="blue">${nj }������֮����Ϣ</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
