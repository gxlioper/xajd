<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bfjsgl/js/bfjsgl.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��罨���б�",
				pager : "pager",
				url : "bfjsgl_bfjsglwh.do?method=bfjsglwhList&type=query",
				colList : [
							{ label : 'jcid', name : 'jcid', index : 'jcid',key : true, hidden : true },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '8%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '25%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '20%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '20%' },
							{ label : '����', name : 'bjrs', index : 'rs', width : '5%' },
							{ label : '��ٵ���', name : 'zcs', index : 'zcs', width : '5%' },
							{ label : '�������', name : 'zds', index : 'zds', width : '5%' },
							{ label : '�Ͽε���', name : 'sks', index : 'sks', width : '5%' },
							{ label : '����ϰ����', name : 'wzxs', index : 'wzxs', width : '5%' },
							{ label : '�������', name : 'jcrq', index : 'jcrq', width : '15%' }
						  ]
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
		<html:form action="/bfjsgl_bfjsglwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
						</logic:equal>
							<li>
								<a href="#" onclick="showView(); return false;" class="btn_ck">�鿴</a>
							</li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
<%--							<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">���صǼǱ�</a></li>	--%>
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��罨���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
