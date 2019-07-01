<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "${gnmkmc }�б�",
				pager : "pager",
				url : "zwzxkqKqjg.do?method=getKqjgList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'bjdm', name : 'bjdm', index : 'bjdm',hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '10%' },
							{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '14%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '14%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '12%',formatter : bjmcLink },
							{ label : '${cclxTitle}', name : 'cclxmc', index : 'cclxmc', width : '5%' },
							<logic:equal name="xxdm" value="2297">
								{ label : 'Ӧ������', name : 'ydrsszly', index : 'ydrsszly', width : '5%' },
							</logic:equal>
							<logic:notEqual name="xxdm" value="2297">
								{ label : 'Ӧ������', name : 'ydrs', index : 'ydrs', width : '5%' },
							</logic:notEqual>
							{ label : 'ʵ������', name : 'sdrs', index : 'sdrs', width : '5%' },
							<logic:equal name="xxdm" value="12970">
							{ label : 'δ��У����', name : 'qqrs', index : 'qqrs', width : '5%' },
							</logic:equal>
							<logic:notEqual name="xxdm" value="12970">
							{ label : 'ȱ������', name : 'qqrs', index : 'qqrs', width : '5%' },
							</logic:notEqual>
							<logic:equal value="true" name="gnmkmcKq">
							{ label : '���ɷ�', name : 'jlf', index : 'jlf',width : '5%' },
							</logic:equal>
							{ label : '${ccrqTitle}', name : 'ccrq', index : 'ccrq', width : '10%' }],
					sortname : "ccrq", sortorder : "desc" };
			
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
		<html:form action="/zwzxkqKqjg">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
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
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
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
		<logic:equal name="xxdm" value="12970">
			<h3 class=datetitle_01>
				<span>���ڷ�Уѧ��������Ϣ&nbsp;&nbsp; </span>
			</h3>
		</logic:equal>
		<logic:notEqual name="xxdm" value="12970">
			<h3 class=datetitle_01>
				<span>${gnmkmc }�б�&nbsp;&nbsp; </span>
			</h3>
		</logic:notEqual>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
