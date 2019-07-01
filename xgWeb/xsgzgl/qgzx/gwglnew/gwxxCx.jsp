<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script language="javascript" defer="defer">
		function getGridSettiong(){
			var colList = [
	                {label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'�к�',name:'r', index: 'r',width:'6%'},
					{label:'���˲���',name:'yrdwmc', index: 'yrdwmc',width:'19%'},
					{label:'��λ����',name:'gwmc', index: 'gwmc',width:'14%'},
					{label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'��λ���',name:'bmlb', index: 'bmlb',width:'7%'},
					{label:'��������',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'�ڸ�����',name:'zgrs', index: 'zgrs',width:'8%'},
					{label:'�˸�����',name:'tgrs', index: 'tgrs',width:'8%'},
					{label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'16%'},
					{label:'��λ��ʼʱ��',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'��λ����ʱ��',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'�Ƿ���Ч��λ',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',hidden:true},
					{label:'ѧ��',name:'xq', index: 'xq',hidden:true},
					{label:'sjly',name:'sjly', index: 'sjly',hidden:true}
				];
			var xxdm = jQuery("#xxdm").val();
			if(xxdm == "10052"){
				colList = [
					{label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'�к�',name:'r', index: 'r',width:'6%'},
					{label:'���˲���',name:'yrdwmc', index: 'yrdwmc',width:'16%'},
					{label:'��λ���',name:'gwxh', index: 'gwxh',width:'8%'},
					{label:'��λ����',name:'gwmc', index: 'gwmc',width:'14%'},
					{label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'��������',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'�ڸ�����',name:'zgrs', index: 'zgrs',width:'8%'},
					{label:'�˸�����',name:'tgrs', index: 'tgrs',width:'8%'},
					{label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'19%'},
					{label:'��λ��ʼʱ��',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'��λ����ʱ��',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'�Ƿ���Ч��λ',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',hidden:true},
					{label:'ѧ��',name:'xq', index: 'xq',hidden:true},
					{label:'sjly',name:'sjly', index: 'sjly',hidden:true}
					
				];
			}else if(xxdm=="10351"){
				colList = [
					{label:'key',name:'gwdm', index: 'gwdm',hidden:true,key:true},
					{label:'�к�',name:'r', index: 'r',width:'6%'},
					{label:'���˲���',name:'yrdwmc', index: 'yrdwmc',width:'19%'},
					{label:'��λ����',name:'gwmc', index: 'gwmc',width:'14%'},
					{label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
					{label:'��������',name:'xqrs', index: 'xqrs',width:'8%'},
					{label:'�ڸ�����',name:'zgrs', index: 'zgrs',width:'8%'},
					{label:'������</br>��������',name:'knsrs', index: 'knsrs',width:'8%'},
					{label:'������</br>�ڸ�����',name:'knszgrs', index: 'knszgrs',width:'8%'},
					{label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'16%'},
					{label:'��λ��ʼʱ��',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'��λ����ʱ��',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'�Ƿ���Ч��λ',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',hidden:true},
					{label:'ѧ��',name:'xq', index: 'xq',hidden:true},
					{label:'sjly',name:'sjly', index: 'sjly',hidden:true}
					
				];
			}
			
			var gridSetting = {
				caption:"����б�",
				pager:"pager",
				url:"qgzx_gwglnew.do?method=gwxxCx&type=query",
				sortname: "r",
			 	sortorder: "asc"
			}
			gridSetting["colList"] = colList;
			return gridSetting;				
		}
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		//��ʼ��
		jQuery(document).ready(function(){ 
			var gridSetting = getGridSettiong();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		//����
		function copyGwxx(){
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if(len>=1){
				var num = 0;
				var str = "";
				var idList = "";
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					var xn = rows[i]["xn"];
					var qgzq = jQuery("#qgzq").val();
					if(qgzq == "xq" ){
						xn = xn+ rows[i]["xq"];
					}
					if(str.indexOf(xn)==-1){
						str += xn+"!!@@!!";
						num++;
					}
					var gwdm = rows[i]["gwdm"];
					idList += gwdm + "!!@@!!";
				}
				url = "qgzx_gwglnew_ajax.do?method=gwxxFz&str="+str+"&num="+num+"&len="+len +"&idList="+idList;
				showDialog('���Ƹ�λ', 380, 390, url)
			}else{
				showAlertDivLayer("��ѡ����Ҫ���Ƶļ�¼��");
			}
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_gwglnew" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="gwdmPlHidden" value="" />
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
            <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
            <input type="hidden"  id="qgzq" name="qgzq" value="${qgzq}" />
            
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="sfQggly" value="1">
						<li> 
							<a href="javascript:void(0);" onclick="showDialog('���Ӹ�λ', 760, 520, 'qgzx_gwglnew.do?method=gwxxZj');return false;" class="btn_zj">���Ӹ�λ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="showModi('gwxxXg','�޸ĸ�λ');return false;" class="btn_xg">�޸ĸ�λ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteGwxx();return false;" class="btn_sc">ɾ����λ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copyGwxx();return false;" class="btn_fz">���Ƹ�λ</a>
						</li>
						</logic:equal>
						<logic:equal value="10407" name="xxdm">
                        <li>
                            <a href="javascript:;" onclick="gwxxDr();return false;" id="btn_dr" class="btn_dr">��λ����</a>
                       </li>
						 <li>
                            <a href="javascript:;" onclick="gwryDr();return false;" id="btn_dr" class="btn_dr">��λ��Ա����</a>
                       </li>
						</logic:equal>
						</logic:equal>
						<logic:equal name="xxdm" value="10022">
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxZj','��Ա����');return false;" class="btn_zj">��Ա����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxTg','��Ա�˸�');return false;" class="btn_sc">��Ա�˸�</a>
						</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10052">
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxZj','��Ա����');return false;" class="btn_zj">��Ա����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxTg','��Ա�˸�');return false;" class="btn_sc">��Ա�˸�</a>
						</li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="10022">
						<logic:notEqual name="xxdm" value="10052">
						<logic:notEqual name="xxdm" value="10351">
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="sfQggly" value="1">
						<logic:notEqual name="xxdm" value="11799">
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxZj','��Ա����');return false;" class="btn_zj">��Ա����</a>
						</li>
						</logic:notEqual>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="showModi('ryxxTg','��Ա�˸�');return false;" class="btn_sc">��Ա�˸�</a>
						</li>
						</logic:equal>
						</logic:notEqual>
						</logic:notEqual>
						</logic:notEqual>
						<logic:equal name="xxdm" value="10351">
							<logic:equal name="writeAble" value="yes">
								<logic:equal name="sfQggly" value="1">
									<li>
										<a href="javascript:void(0);" onclick="showModi('ryxxZj','��Ա����');return false;" class="btn_zj">��Ա����</a>
									</li>
								</logic:equal>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="showModi('ryxxTg','��Ա�˸�');return false;" class="btn_sc">��Ա�˸�</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="showModi('gwxxCk','�鿴��ϸ');return false;">�鿴��ϸ</a>
						</li>
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</html:form>
			<!-- �๦�ܲ����� end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>����б�&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
	</body>
</html>