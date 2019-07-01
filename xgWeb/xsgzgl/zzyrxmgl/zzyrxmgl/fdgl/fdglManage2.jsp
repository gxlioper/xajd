<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption:"�ҵĸ�����Ϣ�б�",
						pager:"pager",
						url:"zzyrxmglfdgl.do?method=fdglManage&type=query&fblx=1",
						colList:[
							{label:'key',name:'fdxxid', index: 'fdxxid',key:true ,hidden:true},
						 	{label:'����ѧ��',name:'fdrxh', index: 'fdrxh',width:'10%'},
							{label:'��������',name:'fdrxm', index: 'fdrxm',width:'10%'},
							{label:'��������ѧԺ',name:'fdrxymc', index: 'fdrxymc',width:'6%'},
							{label:'ѧԱѧ��',name:'bfdrxh', index: 'bfdrxh',width:'13%'},
							{label:'ѧԱ����',name:'bfdrxm', index: 'bfdrxm',width:'10%'},
							{label:'ѧԱ����ѧԺ',name:'bfdrxymc', index: 'bfdrxymc',width:'6%'},
							{label:'������Ŀ',name:'fdkm', index: 'fdkm',width:'8%'},
						 	{label:'��������',name:'', index: '',width:'12%',formatter:czLink}
						],
						sortname: "fdrxh",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_ck").val());
				} else {
					var url = 'zzyrxmglfdgl.do?method=viewFdgl&fdxxid=' + rows[0]["fdxxid"]+ '&fdlx=0';
					var title = "�鿴";
					showDialog(title,750,465,url);
				}
			}
			function czLink(c,r){
				var fdxxid = r.fdxxid;
				return "<button type='button' onclick='txFdnr(\""+fdxxid+"\")'>��д</button>";
			}

			function txFdnr(fdxxid){
				var url = 'zzyrxmglfdgl.do?method=txFdgl&fdxxid=' + fdxxid + '&fdlx=0';
				var title = "��д";
				showDialog(title,1000,465,url);
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zzyrxmglfdgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">�鿴</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="compTab" id="card">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li>
								<a href="javascript:void(0)"
									onclick="refreshForm('zzyrxmglfdgl.do?method=fdglManage&fblx=0')">
									<span>�ѷ���</span> </a>
							</li>
							<li class="ha">
								<a href="javascript:void(0)"
									onclick="refreshForm('zzyrxmglfdgl.do?method=fdglManage&fblx=1')">
									<span>������</span> </a>
							</li>
						</ul>
					</div>
			</div>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
