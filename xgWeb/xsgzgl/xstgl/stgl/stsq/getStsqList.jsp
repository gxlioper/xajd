<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stsq/js/stgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var xxdm=jQuery("#xxdm").val();
			var gridSetting = {
				caption : "���������б�",
				pager : "pager",
				url : "stglStsq.do?method=getStsqList&type=query",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'key', name : 'stid', index : 'stid', hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sqsj', name : 'sqsj', index : 'sqsj', hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg', hidden : true },
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
							{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '8%' },
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
		<html:form action="/stglStsq">
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
				<span>���������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
