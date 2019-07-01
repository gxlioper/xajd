<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//��ѯ����
		function searchRs(){
			
			var url = "wjcfCfssgl_cfsswh.do?method=cfsswhCxSj";

			var ie = "ie";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","3000")
		}
		
		//�鿴
		function wjcfssglCk(){

			var objs = jQuery("input[type=checkbox][name=checkVal]:checked");
			var pkValue="";
			if(objs.length!=1){
				alertError("��ѡ��һ����¼��");
				return false;
			}else{
				pkValue+=objs[0].value;
			}
			var url = 'wjcfCfssgl_cfsswh.do?method=cfsswhCk&pkValue='+pkValue;
			//showTopWin(url,800,520);
			showDialog("", 800,500, url);
		}
		
		//����
		function cfss(pkValue){

			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&cfid="+pkValue;
			showDialog("", 450,320, url);
		}

		//�����޸�
		function cfssxg(pkValue){
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhXg&cfid="+pkValue;
			showDialog("", 450,320, url);
		}

		function save(){
			var ssfj=jQuery("#ssfj").val();
			var jQssfjmc=jQuery("#ssfjmc");
			var ssfjmc="";
			if(ssfj!=""){
				ssfjmc=ssfj.substring((ssfj.lastIndexOf("\\")+1),ssfj.length);
				if (ssfjmc != null && ssfjmc != "") {
					var hz = ssfjmc.substr(ssfjmc.lastIndexOf(".")+1,ssfjmc.length);
					if (hz!='doc'&&hz!='rar'&&hz!='pdf'){
						alertError("�ϴ��ļ�����ֻ��Ϊ��doc,rar,pdf");
						return false;
					}
				}
				if(ssfjmc.length > 50){
					alertError("�ļ�������,���޸��ļ��������ϴ�");
					return false;
				}
				jQssfjmc.val(ssfj);
			}
			
			if (jQuery("#sqly").val()=="") {
				alertInfo("����д��������!");
				return false;
		 	}
			var url="wjcfCfssgl_cfsswh.do?method=cfsswhZj&doType=save";
			refreshForm(url);
		}
		
		//���� 
		function cfssSc(pkValue){
			var url = 'wjcfCfssgl_cfsswh.do?method=cfssglSc&pkValue='+pkValue;
			document.forms[0].action = url;
			document.forms[0].submit();
		}

		//��ʼ��ҳ��
		function setPage(){
			searchRs();
		}
		//��������
		function expData(){
			var url="wjcfCfssgl_cfsswh.do?method=expCfss";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		function cfsswhExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			//showOpenWindow('configureExportData.do?method=choiceExportFields&tableName='+tableName,1000,600);
			customExport("wjcfCfssgl_cfsswh.do", cfsswhExportData);
			}
			
		
			
		// ��������
		function cfsswhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "wjcfCfssgl_cfsswh.do?method=cfsswhExportData&dcclbh=" + "wjcfCfssgl_cfsswh.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>
	<body onload="setPage();">
		<html:form action="/wjcfCfssgl_cfsswh.do?method=cfsswhCx" method="post" enctype='multipart/form-data'>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="cfid" styleId="cfId"/>	
			<input type="hidden" name="message" id="message" value="${message }">	
			<input type="hidden" name="ssfjmc" id="ssfjmc" value="" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>						
						<li><a href="#" onclick="wjcfssglCk();return false;" class="btn_ck"> �鿴 </a></li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" class="btn_dc" onclick="cfsswhExportConfig();return false;">����</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					--%></ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="red">�Ѿ���˹������߲����ٲ�����</font>
					</span>
				</h3>
				<div id="div_rs" 
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfCfssglForm"></jsp:include>
				<!--��ҳ��ʾ-->
				
			</div>
			
			<input type="hidden" name="lx" id="lx" />
		<div id="tmpdiv1" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<font color="red">�ļ������ܳ���50������</font>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th style="width:30%">
								֤�����ϻ򸽼�:
								<br/>(��doc,rar,pdf��ʽ)
							</th>
							<td style="width:70%">
							<input type="file" name="ssfj" id="ssfj"/>
							</td>
						</tr>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>��������:<br/>
								<font color="red">(��1000��)</font>
							</th>
							<td  style="width:70%">
								<textarea rows="5" cols="" onblur="checkLen(this,1000)" name="sqly" id="sqly" style="width:250px;height: 90%; margin: 1px;"></textarea>
							</td>
						</tr>
					</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button"  onclick="save();return false;">
											�� ��
										</button>
										
										<button type="button"  onclick="closeWindown();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		
	</body>
</html>
