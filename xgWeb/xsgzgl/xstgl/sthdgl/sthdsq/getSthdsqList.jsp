<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/sthdgl/sthdsq/js/sthdsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "${gnmkmc}�б�",
				pager : "pager",
				url : "sthdglSthdsq.do?method=getSthdsqList&type=query",
				colList : [
							{ label : 'key', name : 'hdid', index : 'hdid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'ѧ��', name : 'xh', index : 'xh', width : '10%', formatter : xhLink},
							{ label : '����', name : 'xm', index : 'xm', width : '8%' },
							{ label : '�����', name : 'hdmc', index : 'hdmc', width : '12%'},
							{ label : 'ʱ��', name : 'fwsj', index : 'fwsj', width : '10%' },
							{ label : '����ص�', name : 'fwddxxdz', index : 'fwddxxdz', width : '10%' },
							{ label : '����ʱ��', name : 'fwsc', index : 'fwsc', width : '5%' },
                    		{ label : '�ٰ쵥λ', name : 'zbdw', index : 'zbdw', width : '10%' },
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
							{ label : '���״̬', name : 'shzt', index : 'shzt', hidden : true},
                    		{ label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true}],
					sortname : "lrsj",
				    sortorder : "desc" };
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
		<html:form action="/sthdglSthdsq">
		<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
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
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
										
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
				<span>${gnmkmc}�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
