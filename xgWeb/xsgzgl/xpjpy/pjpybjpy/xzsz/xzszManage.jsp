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
					caption:"�༶�б�",
					pager:"pager",
					url:"xpjpy_pjpybjpy_xzszgl.do?method=xzszManage&type=query",
					colList:[      
				         {label:'key',name:'id', index: 'id',hidden:true,key:true},
						   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'15%'},
						   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'16%'},
						   {label:'�༶����',name:'bjrs', index: 'bjrs',width:'8%'},
						   {label:'����С������',name:'xzrs', index: 'xzrs',width:'8%',
							formatter:function(cellValue,rowObject){
										var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
										html.bind("click",function(){
											var isopen = jQuery("#isopen").val();
											if(isopen==null||isopen==''){
												showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
												return false;
											}
											if ("false" == isopen){
												showAlertDivLayer("��ǰδ���Ű༶���飬����ϵ����Ա��");
												return false;
											}
											var url = "xpjpy_pjpybjpy_xzszgl.do?method=updateXzsz&bjdm=" + rowObject["bjdm"] + "&sfksbjpy="+rowObject["sfksbjpy"];
											var title = "�޸İ༶����С���Ա";
											showDialog(title,790,490,url);
										});
										return html;
									 }
						   },
						   {label:'����С���Ա',name:'xms', index: 'xms',width:'10%',formatter:function(cellValue,rowObject){
							   if(!cellValue){
								   cellValue = "";
							   }
							   var cellValueTemp = cellValue;
							   if(cellValue.length > 6){
								   cellValueTemp = cellValue.substring(0,6)+"...";
							   }
							   return jQuery("<span title='"+cellValue+"'>"+cellValueTemp+"</span>");
							 }
							},
						   {label:'����С�����',name:'xsdbxm', index: 'xsdbxm',width:'10%'},
						   {label:'�ύ״̬',name:'tjztmc', index: 'tjztmc',width:'9%'},
						   {label:'�ύ״̬',name:'tjzt', index: 'tjzt',hidden:true},
						   {label:'�Ƿ�ʼ�༶����',name:'sfksbjpy', index: 'sfksbjpy',hidden:true}
						],
						sortname: "tjzt,nj,xydm,zydm",
					 	sortorder: "asc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ���Ű༶���飬����ϵ����Ա��");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ���༶��");
			} else {
				if(rows[0]["sfksbjpy"] == 'true'){
					showAlertDivLayer("��ǰ�༶�Ѿ���ʼ���飡");
					return false;
				}
				var url = "xpjpy_pjpybjpy_xzszgl.do?method=addXzsz&bjdm=" + rows[0]["bjdm"];
				var title = "���Ӱ༶����С���Ա";
				showDialog(title,790,490,url);
			}
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ���Ű༶���飬����ϵ����Ա��");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ���༶��");
			} else {
				if(rows[0]["sfksbjpy"] == 'true'){
					showAlertDivLayer("��ǰ�༶�Ѿ���ʼ���飡");
					return false;
				}
				var url = "xpjpy_pjpybjpy_xzszgl.do?method=updateXzsz&bjdm=" + rows[0]["bjdm"] + "&sfksbjpy="+rows[0]["sfksbjpy"];
				var title = "�޸İ༶����С���Ա";
				showDialog(title,790,490,url);
			}
		}

		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("��ǰδ���Ű༶���飬����ϵ����Ա��");
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1 ){
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows[0]["tjzt"] != "0") {
					showAlertDivLayer("��ѡ��δ�ύ�ļ�¼��");
					return false;
				}
				if (!rows[0]["xsdbxm"]) {
					showAlertDivLayer("������������С�����");
					return false;
				}
				var url = "xpjpy_pjpybjpy_xzszgl.do?method=submitXzsz";
				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
					jQuery.post(url,
						{bjdm:rows[0]["bjdm"],
						bjrs:rows[0]["bjrs"],
						xzrs:rows[0]["xzrs"]
						},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/xpjpy_pjpybjpy_xzszgl">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.bjpyisopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�༶�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
