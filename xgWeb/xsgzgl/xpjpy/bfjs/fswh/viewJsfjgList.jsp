<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/bfjs/fswh/js/jsfs.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var colList="";
				var gridSetting = {
					caption:"�������",
					pager:"pager",
					url:"xpjpyBfjsFswh.do?method=viewJsfjgList&doType=query",
					params:getSuperSearch(),
					multiselect:false
				};
				colList=[
	                       {label:'��������',name:'jszq', index: 'jszq',width:'15%',key:true},
						   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
						   {label:'ѧԺ����',name:'xydm', index: 'xydm',width:'15%',hidden:true},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
						];
				var jsxm = jQuery("font[name=jsxm]");
				jQuery.each(jsxm,function(i,n){
					var xmfsJson = {
							label:jQuery(n).attr("xmmc"),
							name:"fs"+i,
							index:"fs"+i
					};
						var xmpmJson = {
								label:"����",
								name:"pm"+i,
								index:"pm"+i
						};
						colList.push(xmpmJson);
					colList.push(xmfsJson);
				});

				var xnColJson = {
						label:"ѧ��",
						name:"xn",
						index:"xn",
						hidden:true
				};
				colList.push(xnColJson);
				var xqColJson = {
						label:"ѧ��",
						name:"xq",
						index:"xq",
						hidden:true
				};
				colList.push(xqColJson);
				
				gridSetting["colList"] = colList;
				
				jQuery("#dataTable").initGrid(gridSetting);		
				
			});
			function searchRs(){
				var xn_num = document.getElementsByName("a_name_xn").length;
				var xq_num = document.getElementsByName("a_name_xq").length;		
				if(0==xn_num||0==xq_num){
					showAlertDivLayer("����ѡ��һ��ѧ��ѧ�ڣ�");
					return false;
				}
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			
			}
		
			//�����ֵ���
			function exportJsf(){
				setSearchTj();//���ø߼���ѯ����
				var url = "xpjpyBfjsFswh.do?method=exportJsfjg";
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
		</script>
	</head>

	<body style="min-height: 800px;">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		
		<logic:present name="jsxmList">
		<div id="jsxmDiv">
			<logic:iterate id="z" name="jsxmList">
				<font style="display:none;" xmdm="${z.xmdm }" xmmc="${z.xmmc }" name="jsxm"></font>
			</logic:iterate>
		</logic:present>
		</div>
		<html:form action="/xpjpyBfjsFswh">
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<!-- ��ť -->	
				<div class="buttonbox">
					<ul>
						<li id="li_sh">
							<li><a href="#" class="btn_dc" onclick="exportJsf(); return false;">����</a></li>
	
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
				<span>������� </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
