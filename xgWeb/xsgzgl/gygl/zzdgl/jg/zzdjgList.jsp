<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/zzdgl/jg/js/zzdjg.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��Ŀ�걨�б�",
				pager : "pager",
				url : "xgygl_zdjg.do?method=getZzdjgList&type=query",
				colList : [	
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'zzdid', name : 'zzdid', index : 'zzdid', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'qrzt', name : 'qrzt', index : 'qrzt',hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
							{ label : '����', name : 'xm', index : 'xm', width : '10%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '5%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : 'ѧ��ѧ��', name : 'xnxq', index : 'xnxq', width : '10%' },
							{ label : '����ʱ��', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '����Աȷ��', name : 'qrztmc', index : 'qrztmc', width : '10%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true}],
					sortname : "sqsj",
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
		<html:form action="/xgygl_zdjg">
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
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a>
						</li>
						</logic:equal>	
										
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
							<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">���صǼǱ�</a></li>	
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ѧ��ת�߶���ϸ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
