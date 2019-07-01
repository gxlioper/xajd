<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/wyjl.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>  <!-- �߼���ѯʱ����Ҫ -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"��Ŀά���б� ",
					pager:"pager",
					url:"zxdkWyjl.do?method=getWyjlList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',key:true,formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckWyjl('"+rowObject["xh"]+"');\" class='name'>"+cell+"</a>";
					   }},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm'},
                        {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
                        {label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybj',width:'13%'},
					   {label:'�ֻ�����',name:'sjhm', index: 'sjhm'},
					   {label:'QQ',name:'qqhm', index: 'qqhm'},
					   {label:'ΥԼʱ��',name:'wysj',index:'wysj'},
					   {label:'�Ƿ���ϵ��',name:'wyzt',index:'wyzt'}
					],
					sortname : "xh",
				    sortorder : "asc"
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
	
		<html:form action="/zxdkXyddk" method="post" styleId="wyxxForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" class="btn_zj" onclick="addWyjl();return false;">����</a></li>
							<li><a href="javascript:void(0);" class="btn_xg" onclick="editWyjl();return false;">�޸�</a></li>
							<li><a href="javascript:void(0);" class="btn_sc" onclick="delWyjl();return false;">ɾ��</a></li>
							<li><a href="#" class="btn_dr" onclick="importYwxx()();return false;">����ΥԼ����</a></li>
							
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��Ŀά���б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
