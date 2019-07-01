<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zyjgl/grzyjwh/js/grzyjwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
					caption:"רҵ����Ϣά���б�",
					pager:"pager",
					url:"pjpy_zyjwhwh.do?method=grzyjwhManage&type=query",
					colList:[
					    {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
						{label:'����',name:'xm', index: 'xm',width:'6%'},
						{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
						{label:'�༶',name:'bjmc', index: 'bjdm',width:'8%'},
						{label:'ѧԺ',name:'xymc', index: 'xymc',width:'8%'},
					    {label:'��������',name:'bsmc', index: 'bsmc',width:'13%'},
					    {label:'���쵥λ',name:'zbdw', index: 'zbdw',width:'8%'},
					    {label:'�϶��ȼ�',name:'rddjmc', index: 'rddjmc',width:'6%'},
					    {label:'��ʱ��',name:'hjsj', index: 'hjsj',hidden:true}
					],
					sortname: "hjsj",
				 	sortorder: "desc"
				}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/pjpy_zyjwhwh">
		<%--<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>--%>
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
								<logic:notEqual value="stu" name="userType">				
									<li><a href="#" class="btn_dc" onclick="rending();return false;">�϶�</a></li>
								</logic:notEqual>			
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
				<span>רҵ����Ϣά���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
