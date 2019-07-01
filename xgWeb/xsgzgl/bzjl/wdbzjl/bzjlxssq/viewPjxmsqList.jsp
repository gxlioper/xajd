<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">


		function limFn(cellVal , rowObj){
						if(cellVal == null)
							return "<font color='green'>����������</font>";
						else{
							var html = "";
							for(i = 0 ; i < cellVal.length ; i++ ){
								var item = cellVal[i];
								var result = item.result;
								var msg = item.sqts;
								if(result == 'true'){
									var htmli = "<img src='images/ico_38.gif' title='��������' /> " + (i + 1) + "��" + msg + "<br/>";
									html += htmli;
								}else{
									var htmli = "<img src='images/ico_39.gif'name='faidImg'  title='����������' /> " + (i + 1) + "��" + msg + "<br/>";
									html += htmli;
								}
							}
							return html;
						}

				}
			var gridSetting1 = {
					caption:"������Ŀ�����б� ",
					pager:"pager",
					url:"bzjl_bzxssq.do?method=viewPjxmsqList&type=query&queryType=wsq&xzdm=${xzdm}",
					colList:[
					   {label:'key',name:'xmdm', index: 'xmdm',hidden:true,key:true},
					   {label:'xmdm',name:'xmdm', index: 'xmdm',width:'13%',hidden:true,formatter:hideXmdm},
					   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'15%',formatter:setXmsm},
					   {label:'��Ŀ˵��',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'��Ŀ���',name:'xmlx', index: 'xmlx',width:'15%'},
					   {label:'���',name:'xmje', index: 'xmje',width:'10%'},
					   {label:'�Ƿ��ѡ', name:'checkable', index: 'checkable',hidden:true},
					   {label:'��������',name:'conditionCheckResult', index: 'conditionCheckResult',width:'39%' , formatter:limFn},
					   {label:'���뿪ʼʱ��',name:'sqkssj', index: 'sqkssj',hidden:true},
					   {label:'�������ʱ��',name:'sqjssj', index: 'sqjssj',hidden:true},
					   {label:'���ر�ע',name:'kgbz', index: 'kgbz',hidden:true},
					   {label:'����״̬',name:'sqkg', index: 'sqkg',width:'10%',formatter:setSqkg},
					   {label:'����״̬',name:'shztmc', index: 'shztmc',width:'10%'},
					   {label:'sqid',name:'sqid', index: 'sqid',hidden:true},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splc',name:'splc', index: 'splc',hidden:true}
					],
					params:{shzt:"wsq"},//δ����
					sortname:"sqkg",
					sortorder:"desc",
					checkboxFormatter:function(rowObj){
						var check = rowObj['checkable'];
						return check == 'false' ? false : true;
						},
					radioselect:true
				};
			function hideXmdm(cellVal , rowObj){
				return "<input name='"+rowObj['xmdm']+"' value='"+rowObj['xmdm']+"' type='text' />";
			}
			var gridSetting2 = {
					caption:"������Ŀ�����б� ",
					pager:"pager",
					url:"bzjl_bzxssq.do?method=viewPjxmsqList&type=query&queryType=ysq",
					colList:[
					   {label:'key',name:'sqid', index: 'sqid',hidden:true,key:true},
					   {label:'xmdm',name:'xmdm', index: 'xmdm',width:'13%',hidden:true},
					   {label:'��������',name:'pjzq', index: 'xn',width:'13%'},
					   {label:'���뽱��',name:'xmmc', index: 'xmdm',width:'18%',formatter:setXmsm},
					   {label:'��Ŀ˵��',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'��Ŀ���',name:'xmlxmc', index: 'xmlxmc',width:'15%'},
					   {label:'���',name:'xmje', index: 'xmje',width:'10%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'25%'},
					   {label:'���ջ�ý���',name:'tzhxmmc',index:'tzhxmdm',width:'18%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'splc',name:'splc', index: 'splc',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'}
					   //{label:'ɾ������',name:'ff', index:'ff',hidden:true}
					],
					params:{shzt:"ysq"},//������
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

			/*
			 *���뿪�� 
			 */
			function setSqkg(cellValue,rowObject){
				var xmdm = rowObject.xmdm;
				var value = "�ر�����";
				var status = '0';
				var color;
				if(cellValue == '1'){
					var currDate = jQuery("#currDate").val();
					if(rowObject.sqkssj != null && currDate < rowObject.sqkssj || rowObject.sqjssj != null && currDate > rowObject.sqjssj ){
					}else{
						value = "��������";
						status = '1';
					}
				}
				rowObject.sqkg=status;
				return value;
			}

			/**
			 *������Ŀ˵��
			 */
			function setXmsm(cellValue,rowObject){

				if(rowObject['xmsm'] == null) {
					var value = "<a title=''>"+cellValue+"</a>";
				}else {
					var xmsm = rowObject["xmsm"];
					xmsm = xmsm.replaceAll("<br/>","\n");
					var value = "<a title='"+xmsm+"'>"+cellValue+"</a>";
				}
		
				return value;	
				
			}
			
			/**
			 * �л�tabҳ
			 * @param obj
			 * @param shzt
			 * @return
			 */
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);

				if (shzt == "wsq"){
					jQuery("#li_sq").css("display","");
					jQuery("#li_xg").css("display","");
					jQuery("#li_sc").css("display","");
					jQuery("#li_tj").css("display","");
					jQuery("#li_ts").css("display","");

					jQuery("#li_cx").css("display","none");
					jQuery("#li_lc").css("display","none");
					jQuery("#li_xz").css("display","none");
					
					jQuery("#dataTable").initGrid(gridSetting1);
				} else {
					jQuery("#li_sq").css("display","none");
					jQuery("#li_xg").css("display","none");
					jQuery("#li_sc").css("display","none");
					jQuery("#li_tj").css("display","none");
					jQuery("#li_ts").css("display","none");
					
					jQuery("#li_cx").css("display","");
					jQuery("#li_lc").css("display","");
					jQuery("#li_xz").css("display","");
					
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});


			/**
			 * �޸������
			 * @returns {Boolean}
			 */
			function xgSqb(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫȡ���ļ�¼��");
				} else {
					if(rows[0]["sqkg"]!="1"){
						showAlertDivLayer("��ѡ��Ŀ����״̬δ���ţ�������ѡ��");
						return false;
					}
					if(rows[0]["sqid"] == '' || rows[0]["sqid"] == null){
						showAlertDivLayer("����Ŀδ��д���룬�����޸ģ�������д��");
						return false;
						}
					showDialog("�޸������",600,380,"bzjl_bzxssq.do?method=updateSqb&sqid="+rows[0]["sqid"]);
				}
			}

			/**
			 * ��д�����
			 */
			function xmsq(){

				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length == 0){
					showAlertDivLayer("������ѡ��һ�����뽱�");
					return false;
				}

				for ( var i = 0; i < rows.length; i++) {
					if(rows[i]["sqkg"]!="1"){
						showAlertDivLayer("��ѡ��Ŀ����״̬δ���ţ�������ѡ��");
						return false;
					}
					if(rows[i]["sqid"] != '' && rows[i]["sqid"] != null){
						showAlertDivLayer("��ѡ��Ŀ��������дδ�ύ�����˻ؼ�¼����ȷ�ϣ�");
						return false;
					}
				}

				jQuery.post("xpj_xmwh_jdsz.do?method=getJdysq",{xmdm:rows[0]["xmdm"]},function(data){

					if(data!=""&&data!=null){
						showAlertDivLayer("����<font color='red'>����</font>��<font color='red'>����</font>��"+data[0]["xmmc"]+"�������������뵱ǰ�����ȷ�ϣ�");
						return false;
					}
					
					showDialog("��д�����",600,380,"bzjl_bzxssq.do?method=viewXmsq");
					
				},"json");
				
				
			}


			/**
			 * ���̸���
			 * @return
			 */
			function viewLcgz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
						showAlertDivLayer("�����������Ϣ��");
						return false;
					}
					//���˻ؿ������̸��ٹ��ܣ������������״̬�����̸��ٹ���
					if(rows[0]["shzt"]==""||rows[0]["shzt"]==null){
						showAlertDivLayer("�����������Ϣ��");
						return false;
					}
					showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
			
			/**
			 * ���̸���
			 */
			//function viewLcgz(){
			//	var rows = jQuery("#dataTable").getSeletRow();

			//	if (rows.length != 1){
			//		showAlertDivLayer("��ѡ��һ����¼��");
			//	} else {
			//		var url = "xpj_sqsh.do?method=viewLcgz&sqid="+rows[0]["sqid"];
			//		showDialog("���̸���",580,450,url,{max:false,min:false});
			//	}
			//}	

			/**
			*	���صǼǱ�
			*/
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="bzjl_bzxssq.do?method=getDjbZip&value="+ids + "&actionFrom=sqsh";
					window.open(url);
				 } else {
					var url="bzjl_bzxssq.do?method=getDjbWord&sqid="+rows[0]["sqid"] + "&actionFrom=sqsh";
					window.open(url);
			     }
			}		

			//����
			function cxSqb(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				} else if (ids.length >1 ) {
					showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					var SFBJPY_Y = jQuery("#SFBJPY_Y").val();
					for(var i=0;i<ids.length;i++){
						if(SFBJPY_Y == 'true'){
							if(rows[i]['shzt']!='6'){
								showAlertDivLayer("ֻ�а༶�����еļ�¼���ܱ�������");
								return false;
							}
						}else{
							if(rows[i]['shzt']!='5'){
								showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
								return false;
							}
						}
					}
					
					showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
						"okFun" : function() {
							jQuery.post("xpj_sqsh.do?method=cancle", {
								values : ids.toString(),
								lcid : rows[0]['splc']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
						}
					});
				}
			}


			//�ύ
			function submitBusi(){
				
				var rows = jQuery("#dataTable").getSeletRow();

				var ids = [];
				for ( var int = 0; int < rows.length; int++) {
					ids.push(rows[int]['sqid'])
				}
				
				if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
					showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼��");
					return false;
				}
				
				if (ids.length == 0){
					showAlertDivLayer("��ѡ��δ�ύ�������˻صļ�¼����");
				} else if (ids.length >1 ) {
					showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
				} else {
					var xmdm=rows[0]["xmdm"];
					showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("xpj_sqsh.do?method=saveUpdateSqb&type=tj&xh=" + jQuery("input[name='userName']").val(),{values:ids.toString(),xmdm:xmdm},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
				
			}
			
			/**
			 * ɾ������
			 */
			function delSqb(){
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = [];
				for ( var int = 0; int < rows.length; int++) {
					ids.push(rows[int]['sqid'])
				}
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
				} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
					showAlertDivLayer("ֻ��ɾ��δ�ύ�������˻صļ�¼��");
					return false;
				}else {
					showConfirmDivLayer("��ȷ��Ҫȡ����������",{"okFun":function(){
						jQuery.post("xpj_sqsh.do?method=cancelXmsq",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

<%--			jQuery(function($){--%>
<%--				//���--%>
<%--				$("#dataTable").find("tbody>tr").live("click",function(){--%>
<%--					if($(this).find(":checkbox").is(":checked")){--%>
<%--						var obj=$(this);--%>
<%--						var xmdm=$(this).find("td")[1];--%>
<%----%>
<%--						//--%>
<%--						$.post("xpj_xmwh_jdsz.do?method=getJdysq",{xmdm:$(xmdm).text()},function(data){--%>
<%----%>
<%--							if(data!=""&&data!=null){--%>
<%--								showAlertDivLayer("��������򱣴桰"+data[0]["xmmc"]+"�������������뵱ǰ�����ȷ�ϣ�");--%>
<%--								$(obj).removeAttr("checked");--%>
<%--							}--%>
<%--							--%>
<%--						},"json");--%>
<%----%>
<%--						//--%>
<%--						$.post("xpj_xmwh_jdsz.do?method=getBjdxm",{xmdm:$(xmdm).text()},function(data){--%>
<%--							$("#dataTable").find("tbody>tr").each(function(){--%>
<%--								for (var i = 0; i < data.length ; i++){--%>
<%--									var xmobj;--%>
<%--									if($(this).find(":checkbox").is(":checked")){--%>
<%--										xmobj=$(this).find("input[name='"+data[i]["xmdm"]+"']").val();--%>
<%--									}--%>
<%--									if (xmobj){--%>
<%--										showAlertDivLayer("�ý����롰 "+$($(this).find("td")[3]).text()+"������ͬʱ���룬��ȷ�ϣ�");--%>
<%--										$(obj).find(":checkbox").removeAttr("checked");--%>
<%--										break;--%>
<%--									}									--%>
<%--								}--%>
<%--							});--%>
<%--						},"json");	--%>
<%--					}--%>
<%--				});--%>
<%--					--%>
<%--			});--%>

			
		</script>
	</head>

	<body>
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<input type="hidden" id="SFBJPY_Y" value="${SFBJPY_Y }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xpj_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
								
							<logic:equal value="true" name="cssz" property="kgzt">
								<li id="li_sq">
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="xmsq();return false;" 
									   title="����ð�ť�����������дҳ�档"
									>
									����
									</a>
								</li>
								
								<li id="li_xg" >
									<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
							   			title="ֻ���޸�δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ɾ����"
										>�޸�</a>
								</li>
								
								<li id="li_sc" >
									<a href="javascript:void(0);" onclick="delSqb();return false;" class="btn_sc"
							   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
										>ɾ��</a>
								</li>
								
								<li id="li_tj" >
									<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
							   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
										>�ύ</a>
								</li>
								<li id="li_cx" style="display: none;">
									<a href="javascript:void(0);" onclick="cxSqb();return false;" class="btn_xg"
							   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
										>����</a>
								</li>
							</logic:equal>
								<li id="" style="">
									<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
							   			title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   			class="btn_cs">���̸���</a>
								</li>	
								<li id="li_xz" style="display: none;">
									<a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a>
								</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>δ������Ŀ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>��������Ŀ</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>
				<li id="li_ts" ><font color="blue">${pjzqmc}&nbsp;</font></li>������Ŀ�����б�
				 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
