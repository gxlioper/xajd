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
						caption:"��Ϣ�б�",
						pager:"pager",
						url:"zzyrxmglfdxxgl.do?method=fdxxshManage&type=query",
						colList:[
							{label:'key',name:'fdxxid', index: 'fdxxid',key:true ,hidden:true},
						 	{label:'����ѧ��',name:'fdrxh', index: 'fdrxh',width:'10%'},
							{label:'��������',name:'fdrxm', index: 'fdrxm',width:'10%'},
							{label:'ѧԱѧ��',name:'bfdrxh', index: 'bfdrxh',width:'10%'},
							{label:'ѧԱ����',name:'bfdrxm', index: 'bfdrxm',width:'10%'},
						 	{label:'������Ŀ',name:'fdkm', index: 'fdkm',width:'14%'},
						 	{label:'�μ�ʱ��',name:'tysj', index: 'tysj',width:'15%'}, 
						 	{label:'����Ա',name:'fdrfdy', index: 'fdrfdy',hidden:true},
						 	{label:'fdyshzt',name:'fdyshzt', index: 'fdyshzt',hidden:true}, 
						 	{label:'������Ա',name:'bfdrfdy', index: 'bfdrfdy',hidden:true},
						 	{label:'bfdyshzt',name:'bfdyshzt', index: 'bfdyshzt',hidden:true}, 
						 	{label:'ѧ����',name:'xscshr', index: 'xscshr',hidden:true},
						 	{label:'xscshzt',name:'xscshzt', index: 'xscshzt',hidden:true}, 
						 	{label:'���״̬',name:'shzt', index: 'shzt' ,hidden:true},
						 	{label:'shr',name:'shrxz', index: 'shrxz',hidden:true},
						 	{label:'����',name:'', index: '',width:'12%',formatter:czLink}
						],
						sortname: "tysj",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function czLink(cellValue,rowObject){
				var shrxz = rowObject.shrxz;
				var fdyshzt = rowObject.fdyshzt;
				var bfdyshzt = rowObject.bfdyshzt;
				var xscshzt = rowObject.xscshzt;
				var fdxxid = rowObject.fdxxid;
				var dqyh= jQuery("#userName").val();
				var userStatus= jQuery("#userStatus").val();
				if(shrxz=="fdy"){
					if(fdyshzt=="1"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}else if(fdyshzt=="0"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}else if (shrxz=="bfdy"){
					if(bfdyshzt=="1"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}else if(bfdyshzt=="0"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}else if (shrxz=="jdfdy"){
					if(bfdyshzt=="1"||bfdyshzt=="0" && fdyshzt=="1"||fdyshzt=="0"){
						if(fdyshzt=="1"){
							return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
						}else if(fdyshzt=="0"){
							return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
						}
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}else if(shrxz=="xy"){
					if(fdyshzt=="1"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}else if(fdyshzt=="0"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}else if (shrxz=="bxy"){
					if(bfdyshzt=="1"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}else if(bfdyshzt=="0"){
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}else if (shrxz=="jdxy"){
					if(bfdyshzt=="1"||bfdyshzt=="0" && fdyshzt=="1"||fdyshzt=="0"){
						if(fdyshzt=="1"){
							return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
						}else if(fdyshzt=="0"){
							return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
						}
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}else{
				if(xscshzt=="1"||xscshzt=="0"){
						if(xscshzt=="1"){
							return "<button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
						}else if(xscshzt=="0"){
							return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button>";
						}
					}else{
						return "<button type='button' onclick='sh(\""+fdxxid+"\",1,\""+shrxz+"\");'>ͬ��</button><button type='button' onclick='sh(\""+fdxxid+"\",0,\""+shrxz+"\");'>��ͬ��</button>";
					}
				}
			}
			
			function sh(fdxxid,shzt,shrxz){//Ҫ����һ��״̬�������˵ĸ���Ա���Ǳ������˵ĸ���Ա
				var url = "zzyrxmglfdxxgl.do?method=sh";
				jQuery.post(url, {
					fdxxid:fdxxid,shzt : shzt,shrxz:shrxz
				}, function(data) {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
				}, 'json');
				
		
			}
			
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_ck").val());
				} else {
					var url = 'zzyrxmglfdxxgl.do?method=viewFdxxgl&fdxxid=' + rows[0]["fdxxid"];
					var title = "�鿴";
					showDialog(title,750,465,url);
				}
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zzyrxmglfdxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">�鿴��ϸ</a>
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
				<span>��Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
