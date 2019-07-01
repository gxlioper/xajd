<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "������ѯ��¼�б�",
				pager : "pager",
				url : "zxzx_zxzxjl.do?method=getZxList&type=query",
				colList : [
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '15%',key:true,formatter:xhLink},
							{ label : '����', name : 'xm', index : 'xm', width : '10%' },
							{ label : '�Ա�', name : 'xb', index : 'xb', width : '10%' },
							{ label : '�꼶', name : 'nj', index : 'nj', width : '5%' },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : 'רҵ', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '�༶', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : '��ѯ����', name : 'cs', index : 'cs', width : '15%' }
						 ],							
					sortname : "cs desc,djrq",
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
		<html:form action="/zxzx_zxzxjl">
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
							<a href="javascript:void(0);" onclick="wh();return false;" class="btn_sz">��ѯ��¼ά��</a>
						</li>
<%--						<li>--%>
<%--							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>--%>
<%--						</li>--%>
						</logic:equal>	
						<li>
							<a href="javascript:void(0);" onclick="printExportDjb();" class="btn_dy">��ӡ�ǼǱ�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="printExportZxjlDjb();" class="btn_dy">��ӡ��ѯ��¼</a>
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
				<span>������ѯ��¼�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
