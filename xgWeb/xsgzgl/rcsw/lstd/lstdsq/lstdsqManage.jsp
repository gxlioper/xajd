<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/lstd/lstdsq/js/lstdsqManage.js"></script>
		<script type="text/javascript">
		function submitBusi(){
			
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
			} else if (ids.length >1 ) {
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shztmc']!='δ�ύ' && rows[i]['shztmc']!='�˻�' ){
						showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
						return false;
					}
					if(rows[i]['shztmc']!='�˻�'){
						url = "rcsw_lstd_sqgl.do?method=submitLstdsq&returnflag=back";
					}else{
						url = "rcsw_lstd_sqgl.do?method=submitLstdsq";
					}
					
				}

				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
					return false;
				}
				if ("false" == isopen&&'3'!= rows[0]["shzt"]){
					showAlertDivLayer("��ǰδ������ɫͨ�����룬����ϵ����Ա��");
					return false;
				}
				
				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'],
						 splc : rows[0]["splc"],
						 shzt : rows[0]["shzt"]
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}

		}

		function cancel(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
			} else if (ids.length >1 ) {
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shztmc']!='�����'){
						showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
						return false;
					}
				}
				
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_lstd_sqgl.do?method=cancelLstdsq",
						{
						 values:ids.toString(),
						 splcid : rows[0]['splc'] 
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
			
		}

		function lstdLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows == null || rows.length <= 0) {
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
				return false;
			}
			var shzt = rows[0]["shztmc"];
			if (1!=ids.length){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {	
				if ("δ�ύ" == shzt){
					showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
					return false;
				}	
				showDialog("��ɫͨ���������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_lstd_sqgl">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
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
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lstdLcinfo();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��ɫͨ��������Ϣά���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
