<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"ѧ���б�",
					pager:"pager",
					url:"xszz_knsrdbjpy_jgcxgl.do?method=jgcxView&type=query&xn=${rs.xn}&xq=${rs.xq}&sqr=${rs.sqr}&shztbjpy=${rs.shztbjpy}",
					colList:[      
				         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
				         {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
<%--						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},--%>
<%--						   {label:'רҵ',name:'zymc', index: 'zydm',width:'16%'},--%>
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
						   {label:'�Ƿ�ѧ������',name:'sfxsdbmc', index: 'sfxsdbmc',width:'10%'},
						   {label:'������',name:'pyjgmc', index: 'pyjgmc',width:'10%'},
						   {label:'�϶�����',name:'ylzd3', index: 'ylzd3',width:'26%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   var cellValueTemp = cellValue;
							   if(cellValue.length > 15){
								   cellValueTemp = cellValue.substring(0,15)+"...";
							   }
							    return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
							 }
							},
						   {label:'�Ƿ�ѧ������',name:'sfxsdb', index: 'sfxsdb',hidden:true},
						   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true}
						],
						multiselect:false,
						radioselect:false,
						sortname: "xh",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_knsrdbjpy_jgcxgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
<%--				<div class="buttonbox">--%>
<%--					<ul>--%>
<%--						<logic:equal name="writeAble" value="yes">--%>
<%--						<li>--%>
<%--						</li>--%>
<%--						</logic:equal>--%>
<%--					</ul>--%>
<%--				</div>--%>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>ѧ���б�&nbsp;&nbsp;<font color="#0000ff">(������:&nbsp;${xsxxMap.xh }&nbsp;${xsxxMap.xm })</font> </h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
