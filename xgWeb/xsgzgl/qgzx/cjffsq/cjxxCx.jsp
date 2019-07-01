<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "qgzx_cjffsq_ajax.do?method=cjxxCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000");
		}
		
		//�޸Ļ��߲鿴
		function showModi(type){
			var isGly = jQuery("#isGly").val();
			var len=jQuery("[name=div_pkValue]:checked").length;
			var xxdm = jQuery("#xxdm").val();
			if("update"==type){
				if(($("kssj") && $("kssj").value=="") || ($("jssj") && $("jssj").value=="")){
					alertInfo("δ�趨����ʱ�䣬��ȷ�ϣ�",function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				var url="qgzx_cjffsq.do?method=cjxxFf";
				if(len==1){
					var shzt = jQuery("[name=div_pkValue]:checked").parents("tr").find("td").last().text();
					if("δ�ύ"!=shzt&&"���˻�"!=shzt){
						alertInfo("ֻ��δ�ύ�����˻صļ�¼�����޸ģ�",function(tag){
							if(tag=="ok"){
								return false;
							}
						});
						return false;
					}
					var pkValue=jQuery("[name=div_pkValue]:checked").val();
					url+="&pkValue="+pkValue;
				}
				showDialog('��𷢷�', 1000, 525, url);
			}else if("view"==type){
				if(len==1){
					var pkValue=jQuery("[name=div_pkValue]:checked").val();
					var ztArray = jQuery("[name=div_pkValue]:checked");
					var sqid = jQuery(ztArray[0]).parent().parent().find("td").eq(2).text();
					var url="qgzx_cjffsq.do?method=cjxxCk&shck=1&sqid="+sqid;
					url+="&pkValue="+pkValue;
					showDialog('', 900, 525, url);
				}else{
					alertInfo("�빴ѡһ����Ҫ�鿴�����ݣ�",function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
			}
		}
		//�����ύ
		function tjcx() {
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==0){
				showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				return false;
			}
			if(len>1){
				showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				return false;
			}
			var array = jQuery("[name=div_pkValue]:checked");
			var str = "";
			for (var i=0;i<array.length;i++) {
				var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
				str += pkValue+"!!splitOne!!";
			}
			var url = "qgzx_cjffsq_ajax.do?method=cxtj";
			showConfirmDivLayer("��ȷ������ѡ��ļ�¼��", {
				"okFun" : function() {
					jQuery.post(url, {
						pkValue:str
					}, function(result) {
						showAlertDivLayer(result.message);;
						searchRs();
					}, 'json');
				}
			});
		}

		//�ύ��ɾ����
		function czCjff(type){
			if(($("kssj") && $("kssj").value=="") || ($("jssj") && $("jssj").value=="")){
				alertInfo("δ�趨����ʱ�䣬��ȷ�ϣ�");
				return false;
			}
			//�������ظ��ύ
			var isttj=false;
			var ztArray = jQuery("[name=div_pkValue]:checked");
			for(var i=0;i<ztArray.length;i++){
				var zt = jQuery(ztArray[i]).parent().parent().find("td").last().text();
				if(zt != "δ�ύ"&&zt != "���˻�"){
					isttj=true;
					break;
				}
			}
			if(isttj){
				alertInfo("��ѡ��δ�ύ�����˻صļ�¼��");
				return false;
			}

			var len=jQuery("[name=div_pkValue]:checked").length;
			var message = "ɾ��";
			if(type=="tj"){
				message = "�ύ";
				if(len>1){
					alertInfo("��ѡ��һ����Ҫ�ύ�ļ�¼��");
					return false;
				}
				var shzt=jQuery(ztArray[0]).parent().parent().find("td").last().text();
				
				if ("���˻�"!=shzt && "0" == jQuery("#sqkg").val()) {
					showAlertDivLayer("��ǰ�����ѹرգ��������Ա��ϵ��");
					return false;
				}
				
			}
			if(len>=1){
				confirmInfo("�Ƿ�ȷ��"+message+"��ѡ�ļ�¼��",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
							str += pkValue+"!!splitOne!!";
						}
						var parameter={};
						var url="qgzx_cjffsq_ajax.do?method=czCjffxx";
						url+="&doType="+type;
						parameter["pkValue"]=str;
						flg = true;
						//�ൺ�Ƶ������Ի�
						if(jQuery("#xxdm").val() == '13011'){
							jQuery.ajaxSetup({async:false});								
								jQuery.post("qgzx_cjffsq_ajax.do?method=checkSfktj",parameter,function(data){
									if(data != ""){
										flg = false;
										showAlertDivLayer(data+"��λ���н��δ¼�룬��¼����ύ");
									}
									
								},'text');
							jQuery.ajaxSetup({async:true});
							if(!flg){
								return false;
							}
						}
													
						jQuery.ajaxSetup({async:false});	
						/* jQuery.post("qgzx_cjffsq_ajax.do?method=isHaveFfxx",parameter,function(result){
									if(result.message=="false"){ */
										jQuery.post(url,	parameter,function(result){
											alertInfo(result);
											searchRs();
										});
							/* 		}else{
										alertInfo("�����Ѿ����Ź���ѧ��������!");
									}
								},"json"
							); */
						
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				alertInfo("�빴ѡ��Ҫ"+message+"�����ݣ�",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		//����Ƿ���ڣ���ǰʱ���Ƿ���˳�𷢷���
		function aotuChecksfPass(){
				jQuery.ajaxSetup({async:false});	
				jQuery.post("qgzx_cjffsq_ajax.do?method=aotoTjCjffxx",{},'');
				jQuery.ajaxSetup({async:true});
		}
		
		function splcInfo(){
			var ztArray = jQuery("[name=div_pkValue]:checked");
			jQuery(ztArray[0]).parent().parent().eq(2).last().text();
			
			var sqid = jQuery(ztArray[0]).parent().parent().find("td").eq(2).text();
			var splc = jQuery(ztArray[0]).parent().parent().find("td").eq(3).text();
			if (1!=ztArray.length){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {		
				showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+sqid+"&splc="+splc);
			}
		}
		
		
		jQuery(function(){
			onShow();
			var dqsj=jQuery("#dqsj").val();
			var kssj=jQuery("#kssj").val();
			var jssj=jQuery("#jssj").val();
			if(jQuery("#xxdm").val()!="12309"){
				if(parseInt(dqsj,10)<parseInt(kssj,10)||parseInt(dqsj,10)>parseInt(jssj,10)){
					jQuery("li[id=cjffId]").hide();
					jQuery("li[id=czId]").hide();
					jQuery("li[id=cx]").hide();
					jQuery("li[id=czsc]").hide();
					jQuery("#div_help").show();
					jQuery("#helpshow1").show();
				}
			}
			if("0"==jQuery("#sqkg").val()){
				jQuery("#div_help").show();
				jQuery("#helpshow2").show();
			}
			//aotuChecksfPass();
		});

        function toImportData(drmkdm){
            if(drmkdm == null || drmkdm == ""){
                alert("����ģ����벻��Ϊ�ա�");
                return false;
            }
            var url='qgzx_cjffsq.do?method=gzsqImport';
            url+='&drmkdm='+drmkdm;
            showDialog('����',700,500,url);
            return false;
        }
        //dcglbh,�������ܱ��
        var DCGLBH = "qgzx_cjfgfdc";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "qgzx_cjffsq_ajax.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
            url = addSuperSearchParams(url);//���ø߼���ѯ����
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
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
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span id="helpshow1"  style="display: none">
					��𷢷�ʱ���ֹ��<font color="blue">�����ٴ��޸�</font>�����Ϣ </br >
				</span>
				<span id="helpshow2"  style="display: none">
					��ǰδ�������룬ֻ���ύ�����˻ء��ļ�¼��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/qgzx_cjffsq" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="isGly" id="isGly" value="${rs.isGly }"/>
			<input type="hidden" name="kssj" id="kssj" value="${rs.kssj }" />
			<input type="hidden" name="jssj" id="jssj" value="${rs.jssj }" />
			<input type="hidden" name="dqsj" id="dqsj" value="${rs.dqsj }" />
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg }" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
							
							<logic:equal name="writeAble" value="yes">
							<logic:equal name="sfkfsq" value="1">
								<li id="cjffId">
									<a href="#" onclick="showModi('update');return false;" class="btn_zj">��𷢷�</a>
								</li>
								<li id="czId">
									<a href="#" onclick="czCjff('tj');return false;" class="btn_shuc">�ύ</a>
								</li>
								<li id="cx">
									<a href="#" onclick="tjcx();return false;" class="btn_sr">����</a>
								</li>
								<li id="czsc">
									<a href="#" onclick="czCjff('del');return false;" class="btn_sc">ɾ��</a>
								</li>
								<li><a href="#" onclick="toImportData('IMPORT_XAJD_GZSQ');return false;" class="btn_dr">����</a></li>
							</logic:equal>
							</logic:equal>
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a>
						</li>
						<li>
							<a href="#" onclick="showModi('view');return false;" class="btn_ck">�鿴��ϸ</a>
						</li>
						<li>
							<a href="#" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;</span>
					<logic:equal value="10264" name="xxdm">
						<%-- <font color="blue">�ܾ��ѣ�${jftx.hbzje }&nbsp;&nbsp;&nbsp;&nbsp;��ʹ�ã�${jftx.syje}&nbsp;&nbsp;&nbsp;&nbsp;ʣ�ࣺ${jftx.yffje }</font> --%>
					</logic:equal>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxCjffsqForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>