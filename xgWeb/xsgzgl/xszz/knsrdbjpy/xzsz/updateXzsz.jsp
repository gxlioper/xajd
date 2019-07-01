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
					url:"xszz_knsrdbjpy_xzszgl.do?method=updateXzsz&type=query&bjdm=${rs.bjdm}",
					colList:[      
				         {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
						   {label:'ѧ��',name:'xh', index: 'xh',width:'10%'},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'14%'},
						   {label:'רҵ',name:'zymc', index: 'zydm',width:'16%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
						   {label:'�Ƿ�ѧ������',name:'sfxsdbmc', index: 'sfxsdbmc',width:'10%'},
						   {label:'�Ƿ�ѧ������',name:'sfxsdb', index: 'sfxsdb',hidden:true},
						   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true}
						],
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

		function xzszDel() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ����ѧ����");
			} else {
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ���ѧ����",{"okFun":function(){
					jQuery.post("xszz_knsrdbjpy_xzszgl.do?method=xzszDel",{values:ids.toString(), bjdm: "${rs.bjdm}"},function(data){
						showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					},'json');
					}
				});
			}
		}
				
		function xsdbBc() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1) {
				showAlertDivLayer("��ѡ��һ��ѧ����");
			} else {
				jQuery.post("xszz_knsrdbjpy_xzszgl.do?method=xsdbBc",{values:ids.toString(), bjdm: "${rs.bjdm}"},function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
				},'json');
			}
		}

		</script>
	</head>

	<body>
		<html:form action="/xszz_knsrdbjpy_xzszgl">
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<logic:equal name="rs" property="sfksbjpy" value="false">
						<font color="red">ɾ��</font>ѧ���󣬰༶����С����ύ״̬��Ϊ<font color="red">δ�ύ</font>�������½���<font color="red">�ύ</font>������
					</logic:equal>
					<logic:equal name="rs" property="sfksbjpy" value="true">
						��ǰ�༶�Ѿ���ʼ���飬�޷�������Ա������
					</logic:equal>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="rs" property="sfksbjpy" value="false">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="xzszDel();return false;" >ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_csh" onclick="xsdbBc();return false;" >����ѧ������</a>
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
				<span>ѧ���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
