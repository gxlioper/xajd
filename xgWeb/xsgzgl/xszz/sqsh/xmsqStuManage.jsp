<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">
		function jesfkt(cellValue,rowObject){
			var jesfxssq=rowObject.jesfxssq;
			var value='';
			if(jesfxssq=='1'){
				value=cellValue+"<font color='red'>(��)</font>";
			}else{
				value=cellValue;
			}
			return value;
		}
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
					url:"xszz_sqsh.do?method=xmsqStuManage&type=query&sqType=wsq",
					colList:[
					   {label:'key',name:'xmdm', index: 'xmdm',hidden:true,key:true},
					   {label:'guid',name:'guid', index: 'guid',hidden:true},
					   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'15%',formatter:setXmsm},
					   {label:'��Ŀ˵��',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'��Ŀ���',name:'lbmc', index: 'lbmc',width:'12%'},
					   {label:'���',name:'je', index: 'je',width:'8%',formatter:jesfkt},
					   {label:'�Ƿ��ѡ', name:'checkable', index: 'checkable',hidden:true},
					   {label:'��������',name:'conditionCheckResult', index: 'conditionCheckResult',width:'42%' , formatter:limFn},
					   {label:'����Ƿ�ѧ������',name:'jesfxssq', index:'jesfxssq',hidden:true},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'���뿪ʼʱ��',name:'sqkssj', index: 'sqkssj',hidden:true},
					   {label:'�������ʱ��',name:'sqjssj', index: 'sqjssj',hidden:true},
					   {label:'����״̬',name:'sqkg', index: 'sqkg',hidden:true,formatter:setSqkg},
					   {label:'����״̬',name:'kgzt', index: 'kgzt',width:'10%'},
					   {label:'����״̬',name:'shztmc', index: 'shztmc',width:'10%'}
					],
					params:{shzt:"wsq"},//δ����
					sortname:"sqkg desc,xmmc",
					sortorder:"asc",
					checkboxFormatter:function(rowObj){
						var check = rowObj['checkable'];
						return check == 'false' ? false : true;
						},
					radioselect:true
				};

			var gridSetting2 = {
					caption:"������Ŀ�����б� ",
					pager:"pager",
					url:"xszz_sqsh.do?method=xmsqStuManage&type=query&sqType=ysq",
					colList:[
					   {label:'key',name:'guid', index: 'guid',hidden:true,key:true},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'13%'},
					   {label:'ѧ��',name:'xqmc', index: 'xq',width:'10%'},
					   {label:'���뽱��',name:'xmmc', index: 'xmdm',width:'13%',formatter:setXmsm},
					   {label:'��Ŀ˵��',name:'xmsm', index: 'xmsm',width:'15%',hidden:true},
					   {label:'��Ŀ���',name:'lbmc', index: 'lbmc',width:'12%'},
					   {label:'���',name:'je', index: 'je',width:'8%',formatter:jesfkt},
					   {label:'����Ƿ�ѧ������',name:'jesfxssq', index:'jesfxssq',hidden:true},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'25%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'}
					],
					params:{shzt:"ysq"},//������
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};

			/**
			*�����������
			**/
			function query(){
				var map = {};
				map["lbmc"]= jQuery("#lbmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

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
					if((rowObject.sqkssj ==null || currDate >= rowObject.sqkssj) && (rowObject.sqjssj==null || currDate <= rowObject.sqjssj) ){
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


			/**
			 ********��ʼ��������
			*/
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});


			/**
			 * �޸������
			 * @returns {Boolean}
			 */
			function xgSqbStu(){
					
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫȡ���ļ�¼��");
				} else {
					if(rows[0]["guid"] == '' || rows[0]["guid"] == null){
						showAlertDivLayer("����Ŀδ��д���룬�����޸ģ�������д��");
						return false;
					}
					showDialog("������Ŀ�޸�",715,393,"xszz_sqsh.do?method=updateXmsqStu&guid="+rows[0]["guid"]);
				}
			}
				

			/**
			 * ��д�����
			 */
			function xmsqStu(){

				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length == 0){
					showAlertDivLayer("������ѡ��һ��������Ŀ��");
					return false;
				}

				for ( var i = 0; i < rows.length; i++) {
					if(rows[i]["sqkg"]!="1"){
						showAlertDivLayer("��ѡ��Ŀ����״̬δ���ţ�������ѡ��");
						return false;
					}
					if(rows[i]["guid"] != '' && rows[i]["guid"] != null){
						showAlertDivLayer("��ѡ��Ŀ��������дδ�ύ�����˻ؼ�¼����ȷ�ϣ�");
						return false;
					}
				}
				
				
				//��ü��
				var selectedIds = jQuery("#dataTable").getSeletIds();
				jQuery.post("xszz_sqsh.do?method=xszzXmsqChkJdStu" , 
					{
						xmdmids : selectedIds.join(',')
					} , 
					function(data){
						var isSuccess = data['success'];
						var msg = data['message'];
						if(isSuccess === 'true'){
							var xxdm = jQuery("#xxdm").val();
							if(xxdm != '10335'){
								showDialog("������Ŀ����",715,450,"xszz_sqsh.do?method=xszzXmsqStu");
							}else{
								showDialog("��Ŀ˵��",500,350,"xszz_sqsh.do?method=showXmxx_10335&xmdm="+selectedIds);
							}
								
							}else{
								showAlertDivLayer(msg);
							}
						} ,
					"json");
				//��ü��
			}	
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
	
		<html:form action="/xszz_sqsh" method="post" styleId="zcxmForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">	
								<li id="li_sq">
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="xmsqStu();return false;" 
									   title="����ð�ť�����������дҳ�档">
									����
									</a>
								</li>
								<li id="li_xg" >
									<a href="javascript:void(0);" onclick="xgSqbStu();return false;" class="btn_xg"
							   			title="ֻ���޸�δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ɾ����"
										>�޸�</a>
								</li>
								
								<li id="li_sc" >
									<a href="javascript:void(0);" onclick="xmsqDeleteStu();return false;" class="btn_sc"
							   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
										>ɾ��</a>
								</li>
								
								<li id="li_tj" >
									<a href="javascript:void(0);" onclick="submitBusiStu();return false;" class="btn_shuc"
							   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
										>�ύ</a>
								</li>
								
								<li id="li_cx" style="display: none;">
									<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr"
							   			title="ֻ��ȡ��δ��˻��˻صļ�¼���ѱ�����˵Ĳ���ȡ����"
										>����</a>
								</li>
						</logic:equal>
								<li id="li_lc" style="display: none;">
									<a href="javascript:void(0);" onclick="xmsqLcgz();return false;" 
							   			title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   			class="btn_cs">���̸���</a>
							   	</li>	

								<li id="li_xz" style="display: none;">
									<a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a>
								</li>
								
							<logic:equal name="xxdm" value="10530">	
								<li><a href="javascript:void(0);" onclick="printSqlct();return false;" class="btn_down">��������ͼ</a></li>
							</logic:equal>	
						</ul>
					</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>δ������Ŀ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>��������Ŀ</span></a></li>
			      </ul>
			    </div>
			</div>
			<div class="searchtab" >
			<!-- �������� -->
				<table width="100%" border="0">
					<tr>
						<th width="10%">��Ŀ���</th>
						<td>
							<html:select property="lbmc" styleId="lbmc" style="width:150px;">
							<html:option value=""></html:option>
							<html:options collection="xmlbList" property="mc"
								labelProperty="mc" />
							</html:select>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span><li id="li_ts" ><font color="blue">${xnxqmc}</font> </li>������Ŀ�б� </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
