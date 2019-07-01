<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsq.js"></script>
		<script language="javascript" defer="defer">
		function getGridSettiong(){
			var colList = [
	                {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
//					{label:'�к�',name:'r', index: 'r',width:'6%'},
                    {label:'ѧ��',name:'xh', index: 'xh'},
                {label:'����',name:'xm', index: 'xm'},
                {label:'���',name:'pycc', index: 'pycc'},
                {label:'�༶',name:'bjmc', index: 'bjmc'},
                {label:'��λ����',name:'gwmc', index: 'gwmc',width:'16%'},
					{label:'���˲���',name:'yrdwmc', index: 'yrdwmc',width:'18%'},
//					{label:'��λ����',name:'gwxzmc', index: 'gwxzmc',width:'12%'},
//					{label:'��������',name:'xqrs', index: 'xqrs',width:'8%'},
//					{label:'������������',name:'knsrs', index: 'knsrs',width:'8%'},
//					{label:'��λ��Чʱ',name:'gwyxs', index: 'gwyxs',width:'11%'},
					{label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'12%'},
					{label:'���״̬',name:'shztmc', index: 'shztmc',width:'8%'},
					/*{label:'��λ��ʼʱ��',name:'gwkssj', index: 'gwkssj',hidden:true},
					{label:'��λ����ʱ��',name:'gwjssj', index: 'gwjssj',hidden:true},
					{label:'�Ƿ���Ч��λ',name:'sfyxgw', index: 'sfyxgw',hidden:true},
					{label:'ѧ��',name:'xn', index: 'xn',hidden:true},
					{label:'ѧ��',name:'xq', index: 'xq',hidden:true},*/
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'splcid',name:'splcid', index: 'splcid',hidden:true}
					
				];
			var gridSetting = {
				caption:"�����б�",
				pager:"pager",
				url:"qgzx_gwglnew.do?method=gwsqCx&type=query",
				sortname: "sqsj",
			 	sortorder: "desc"
			}
			gridSetting["colList"] = colList;
			return gridSetting;
		}

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		jQuery(document).ready(function(){ 
			var gridSetting = getGridSettiong();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function yrdwgwsqExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("qgzx_gwglnew_ajax.do", yrdwgwsqExportData);
		}
			
		// ��������
		function yrdwgwsqExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_gwglnew_ajax.do?method=gwsqExportData&dcclbh=" + "qgzx_gwglnew_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
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
				url = "qgzx_gwglnew_ajax.do?method=gwsqFz&str="+str+"&num="+num+"&len="+len +"&idList="+idList;
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

			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:notEmpty name="splc">
							<li>
								<a href="javascript:void(0);" onclick="showAdd();return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="showModi('update','�޸ĸ�λ����');return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="deleteGwsq();return false;" class="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >�ύ</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >����</a>
							</li>
						</logic:notEmpty>
						<logic:equal name="xxdm" value="10351">
						<li>
							<a href="javascript:void(0);" onclick="copyGwxx();return false;" class="btn_fz">���Ƹ�λ</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="showModi('view','�鿴��λ����');return false;">�鿴</a>
						</li>

						<li><a href="javascript:void(0);" onclick="yrdwgwsqExportConfig();return false;" class="btn_dc">����</a></li>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
						<logic:equal name="xxdm" value="11799">
							<li>
								<a href="#" onclick="showModiTg('ryxxZj');return false;" class="btn_zj">��Ա����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="showModiTg('ryxxTg','��Ա�˸�');return false;" class="btn_sc">��Ա�˸�</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
							<li>
								<a href="javascript:void(0);" onclick="showModiTg('ryxxTg','��Ա�˸�');return false;" class="btn_sc">��Ա�˸�</a>
							</li>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<li>
							<a href="javascript:void(0);" class="btn_dy" onclick="printgwsqb();return false;">��ӡ��λ�����</a>
						</li>
	                    </logic:equal>
						<%--<li><a href="javascript:void(0);" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>
					--%></ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</html:form>
			<!-- �๦�ܲ����� end-->

			<div class="main_box">
				<h3 class=datetitle_01>
					<span>�����б�&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
	</body>
</html>