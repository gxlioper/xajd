<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='js/BatAlert.js'></script>
		<script language="javascript">
		//����
		function addLdsj(){
			var url="wjcfJcsz_cflbdm.do?method=cflbdmZj&doType=add";
			//showTopWin(url,500,350);
			showDialog("",500,260,url);
		}

		function chec_page(){
		      for(i=0;i<document.getElementsByName("checkVal").length;i++){
		    	if(document.getElementsByName("checkVal")[i].disabled == false){
		    		document.getElementsByName("checkVal")[i].checked=document.getElementsByName("all")[0].checked;
		    	}
		      }
		}
	
		
		//�޸�
		function modiLdsj(){
			var pkValues=document.getElementsByName("checkVal");
			var n=0;
			var pkValue="";
			for(i=0;i<pkValues.length;i++){
				if(pkValues[i].checked){
					n++;
					if(pkValue==""){
						pkValue=pkValues[i].value;
					}else{
						alertInfo('��ѡ��һ���޸�');
						return false;
					}
				}
			}
			if(n==1 && pkValue!=""){
				//var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = 'wjcfJcsz_cflbdm.do?method=cflbdmXg&doType=edit&pkValue='+pkValue;
				//showTopWin(url,500,350);
				showDialog("",520,235,url);
			}else{
				alertInfo('��ѡ��һ��');
				return false;
			}
		}
		//ɾ��
		function delLdsj(){
			var pkV=document.getElementsByName("checkVal");
			var pkValue="";
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					if(pkValue==""){
						pkValue=pkV[i].value;
					}else{
						pkValue=pkValue+"@@"+pkV[i].value;
					}
				}
			}
			if(pkValue!=""){
				jQuery.ajax({
							url:"wjcfJcsz_cflbdm.do?method=cflbdmKfsc",
							type:"POST",
							data:{pkValue:pkValue},
							success: function(data){
						     	if (data != null && data != "") {
						     		alertError("�������"+data+"���Ѿ���ѧ������������ʹ�ã�����ɾ����");
						     		return false;
						     	} else {
						     		confirmInfo("ȷ��Ҫɾ��ѡ�еļ�¼��?",function(ok){
										if(ok=="ok"){
											var url="wjcfJcsz_cflbdm.do?method=cflbdmSc";
											url+="&doType=delete&pkValue="+pkValue;
											refreshForm(url);
											hiddenMessage(true,true);
											BatAlert.showTips('���ڲ��������Ե�...');						
										}
									});
						     	}
						   }
					});
			
			}else{
				alertInfo("��ѡ����Ҫɾ���ļ�¼!");
				return false;
			}
		}
		//�ύ�ɹ���ˢ���б�
		function searchRs(){
			allNotEmpThenGo('cflbdmCx.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				showDialog("",h,w,url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		
		function cflbdmExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("wjcfJcsz_cflbdm.do", cflbdmExportData);
			}
			
			
			
		// ��������
		function cflbdmExportData() {
			//setSearchTj();//���ø߼���ѯ����
			var url = "wjcfJcsz_cflbdm.do?method=cflbExportData&dcclbh=" + "wjcfJcsz_cflbdm.do";//dcclbh,�������ܱ��
			//url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		
		</script>
	</head>
	<body >
		<html:form action="/wjcfJcsz_cflbdm" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>						
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="addLdsj();return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modiLdsj();return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="delLdsj();return false;" class="btn_sc"> ɾ�� </a></li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="cflbdmExportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� 
				include file="/comm/search/superSearchArea.jsp"
				 �������� end-->
				<div class="searchtab">
				<table width="100%" class="">
					<tbody>
						<tr>
							<th width="width:12%;">����</th>
							<td width="width:21%;">
								<input type="text" onkeypress="if(event.keyCode==13){return false;}" name="cflbmc" id="cflbmc" value="<logic:present name="rs"><bean:write name="rs" property="cflbmc" /></logic:present>" />
							</td>
							<th width="width:12%;"></th>
							<td width="width:21%;"></td>
							<th width="width:12%;"></th>
							<td width="width:22%;">
								<div class="btn">
				              		<button type="button"  id="search_go"   
				              		onclick="allNotEmpThenGo('wjcfJcsz_cflbdm.do?method=cflbdmCx&go=go')">
				              		�� ѯ
				              		</button>
				            	</div>
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;
					</span>
				</h3>

				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec_page()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td>
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rsList" id="rs" indexId="index">	
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi('wjcfJcsz_cflbdm.do?method=cflbdmCk',500,310);">
									<logic:iterate id="s" name="rs" indexId="ind">
										<logic:equal name="ind" value="1">
										<td>
											<logic:iterate id="s0" name="rs" indexId="ind0" offset="0" length="1">
											<input type="checkbox" name="checkVal" id="pkV" 
												value="<bean:write name="s"/>" 
											/>
											</logic:iterate>
										</td>
										</logic:equal>
										<logic:greaterEqual name="ind" value="2">
										<td>
											<bean:write name="s"/>
										</td>
										</logic:greaterEqual>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<!--��ҳ��ʾ-->
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfJcszForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</logic:notEmpty>
				<logic:empty name="rsList">
					<div class="con_overlfow" style="text-align: center; color: red;" >
						��ǰ������������ݡ�
					</div>
				</logic:empty>
			</div>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
