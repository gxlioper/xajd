<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/yxybgl/jg/js/yxybjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "Ժ���±�����б�",
				pager : "pager",
				url : "yxybgl_jg.do?method=getYxybjgList&type=query",
				colList : [
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '5%' },
							{ label : '�·�', name : 'yf', index : 'yf', width : '10%',formatter:yfLink },
							{ label : 'ѧԺ', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : '��д��', name : 'mz', index : 'mz', width : '10%' },
							{ label : '��дʱ��', name : 'txsj', index : 'txsj', width : '10%' },
							{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true }
						  ],
					sortname : "yf",
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
		<html:form action="/yxybgl_jg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >��д</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
						</li>
						</logic:equal>							
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<logic:equal value="10026" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="hbdc();return false;">�ϲ�����</a></li>
						</logic:equal>			
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>Ժ��ѧ���±�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
