<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stjg/js/stjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "���Ž���б�",
				pager : "pager",
				url : "stglStjg.do?method=getStjgList&type=query",
				colList : [
							{ label : 'key', name : 'stid', index : 'stid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sqsj', name : 'sqsj', index : 'sqsj', hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
							{ label : 'sqkssj', name : 'sqkssj', index : 'sqkssj', hidden : true },
							{ label : 'sqjssj', name : 'sqjssj', index : 'sqjssj', hidden : true },
							{ label : 'xmlbdm', name : 'xmlbdm', index : 'xmlbdm', hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '15%' },
							{ label : '������Ŀ����', name : 'stxmmc', index : 'stxmmc', width : '15%',formatter : xmmcLink  },
							{ label : '�������', name : 'stlbmc', index : 'stlbmc', width : '10%' },
							{ label : '��Ŀ���', name : 'xmlbmc', index : 'xmlbmc', width : '12%' },
							{ label : '������Чѧ��', name : 'xn', index : 'xn', width : '5%'},
							{ label : '������ϵ�绰', name : 'lxdh', index : 'lxdh', width : '5%' },
							{ label : 'ָ����ʦ', name : 'zdlsxm', index : 'zdlsxm', width : '12%' },
							<logic:equal value="12872" name = "xxdm">
							{ label : '�����Ǽ�', name : 'stxj', index : 'stxj', width : '5%' },
							</logic:equal>
							{ label : '��Ա����', name : 'cysl', index : 'cysl', width : '5%' },
							{ label : '���뿪��', name : 'sqkg', index : 'sqkg', width : '15%' ,formatter : setXmsz}
							],
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
		<html:form action="/stglStjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:notEqual value="stu" name="usertype">
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
									<a href="javascript:void(0);" onclick="updatestcyxx();return false;" class="btn_xg" >���ų�Ա��ϸ�޸�</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="copystxx();return false;" class="btn_fz" >����</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a>
								</li>
							</logic:notEqual>
						</logic:equal>
							<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							<li>
							<a href="javascript:void(0);" onclick="getStqkdjb();return false;" class="btn_dy">���ŵǼǱ�</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���Ž���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
