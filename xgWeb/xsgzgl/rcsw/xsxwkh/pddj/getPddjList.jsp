<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/pddj/js/pddj.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "�����ȼ���Ϣ�б�",
				pager : "pager",
				url : "xsxwkhDjpd.do?method=getPddjList&type=query",
				colList : [
							{ label : 'key', name : 'jbfid', index : 'jbfid',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%',formatter : xhLink  },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '8%' },
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '10%' },
							{ label : '������</br>����Ա����', name : 'bzrcpdj', index : 'bzrcpdj', width : '5%' },
							{ label : '�༶</br>ѧ������', name : 'xscpdj', index : 'xscpdj', width : '10%' },
							{ label : '������', name : 'jlf', index : 'jlf', width : '5%' },
							{ label : '������', name : 'cff', index : 'cff', width : '5%' },
							{ label : '��ʵ��', name : 'jsf', index : 'jsf', width : '5%' },
							{ label : '��ʵ����', name : 'jsdj', index : 'jsdj', width : '5%' },
							{ label : '���ӷ�', name : 'fjf', index : 'fjf', width : '5%' },
							{ label : '�ܷ�', name : 'zf', index : 'zf', width : '5%' },
							{ label : '�����ȼ�', name : 'pddj', index : 'pddj', width : '10%' }],
					sortname : "xn,xh",
				    sortorder : "desc" }
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
		<html:form action="/xsxwkhDjpd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<logic:equal name="userType" value="stu">
				<logic:equal value="true" name="flag">
				<div class="prompt" id="pts" style="">
					<h3><span>��ʾ��</span> </h3>
					<p>���´������Ѵﵽ10�֣���ע�⣡</p>
				</div>
				</logic:equal>
			</logic:equal>
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal value="true" property="kgzt" name="csszForm">
						<li>
							<a href="javascript:void(0);" onclick="getZpdj();return false;" class="btn_xg" >�����ȼ�����</a>
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
				<span>�����ȼ���Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
