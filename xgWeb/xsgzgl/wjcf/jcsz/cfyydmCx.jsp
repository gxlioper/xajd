<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="js/uicomm.js"></script> 
		<script type='text/javascript' src='js/BatAlert.js'></script>
		<script language="javascript">
		function addLdsj(){

			var url="wjcfJcsz_cfyydm.do?method=cfyydmZj";
			//showTopWin(url,'780','660');
			showDialog("",380,200,url);
			
<%--			tipsWindown("����ԭ���������","id:tmpdiv1","350","200","true","","true","id");--%>
<%--			jQuery('#divcfyymc').val('');--%>
<%--			jQuery('#lx').val('zj');--%>
		}


		//�޸Ľ��
		function modiLdsj(){
			var pkValues = jQuery("input[type=checkbox][name=checkVal]:checked");
			var pkValue="";

			if(pkValues.length!=1){
				alertError("��ѡ��һ����¼��");
				return false;
			}else{
				pkValue=pkValues[0].value;
			}
			
<%--			if(pkValue!=""){--%>
<%--				tipsWindown("���ֽ������","id:tmpdiv1","350","200","true","","true","id");--%>
<%--				jQuery.ajaxSetup({async:false});	--%>
<%--				jQuery('#lx').val('xg');--%>
<%--				jQuery('#cfyydm').val(pkValue);--%>
<%--				jQuery.post("wjcfJcsz_cfyydm.do?method=xgCfyydm",--%>
<%--								{pkValue:pkValue},--%>
<%--								function(result){--%>
<%--									var json=eval(result);--%>
<%--										jQuery("#divcfyymc").val(json[0].cfyymc);--%>
<%--										jQuery("#ycfyymc").val(json[0].cfyymc);--%>
<%--								}--%>
<%--							);--%>
<%--				jQuery.ajaxSetup({async:true});	--%>
<%--			}--%>
			var url="wjcfJcsz_cfyydm.do?method=cfyydmXg&pkValue="+pkValue;
		
			showDialog("",380,200,url);
			}
		
		function save(){
			var lx = $("lx").value;
			var cfyymc = $("divcfyymc").value;
			if ($("divcfyymc").value=="") {
				alertInfo("����д����ԭ��!");
				return false;
		 	}
			var url="wjcfJcsz_cfyydm.do?method=saveCfyy&lx="+lx;
			if(checkExists('xg_wjcf_cfyydmb','cfyymc')){
				refreshForm(url);
			}
			
		}
		
		//Ψһ��֤
		function checkExists(tableName, pk){
			var pkV = jQuery('#divcfyymc').val();
			var ycfyymc = jQuery('#ycfyymc').val();
			 var result = false;
			 dwr.engine.setAsync(false);
				if(pkV!=ycfyymc){
				getSztzData.getInfoEx(tableName,pk,pkV ," 1=1",function(bool){
				       if(bool){
				    	   alertError("�ô���ԭ�������Ѿ����ڣ�");
				    	   result = false;
				       }else{
				    	   result = true;
					       }
					});	
			}
				dwr.engine.setAsync(true);
				return result;
		}
		
		function chec_page(){
		      for(i=0;i<document.getElementsByName("checkVal").length;i++){
		    	if(document.getElementsByName("checkVal")[i].disabled == false){
		    		document.getElementsByName("checkVal")[i].checked=document.getElementsByName("all")[0].checked;
		    	}
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
				confirmInfo("ȷ��Ҫɾ��ѡ�еļ�¼��?",function(ok){
					if(ok=="ok"){
						var url="wjcfJcsz_cfyydm.do?method=cfyydmSc";
						url+="&doType=delete&pkValue="+pkValue;
						refreshForm(url);
						hiddenMessage(true,true);
						BatAlert.showTips('���ڲ��������Ե�...');						
					}
				});
			}else{
				alertInfo("��ѡ����Ҫɾ���ļ�¼!");
				return false;
			}
		}

		//�ύ�ɹ���ˢ���б�
		function searchRs(){
			allNotEmpThenGo('cfyydmCx.do');
		}

		
		function modi(url,h,w){
			if(curr_row != null){
				
				var pkValue=curr_row.getElementsByTagName("input")[0].value;
	
				tipsWindown("���ֽ������","id:tmpdiv2","350","200","true","","true","id");
				jQuery.ajaxSetup({async:false});	
				jQuery('#lx').val('xg');
				jQuery('#cfyydm').val(pkValue);
				jQuery.post("wjcfJcsz_cfyydm.do?method=xgCfyydm",
								{pkValue:pkValue},
								function(result){
									var json=eval(result);
										jQuery("#divcfyymc1").val(json[0].cfyymc);
								}
							);
				jQuery.ajaxSetup({async:true});
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		function cfyyExportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport("wjcfJcsz_cfyydm.do", cfyyExportData);
		}
			
			
			
		// ��������
		function cfyyExportData() {
			//setSearchTj();//���ø߼���ѯ����
			var url = "wjcfJcsz_cfyydm.do?method=cfyydmExportData&dcclbh=" + "wjcfJcsz_cfyydm.do";//dcclbh,�������ܱ��
			//url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body >
		<html:form action="/wjcfJcsz_cfyydm" method="post">
		<html:hidden property="cfyydm" styleId="cfyydm"/>
		<input type="hidden" name="ycfyymc" id="ycfyymc"/>
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
						<li><a href="#" class="btn_dc" onclick="cfyyExportConfig();return false;">����</a></li>
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
								<input type="text"  onkeypress="if(event.keyCode==13){return false;}"  name="cfyymc" id="cfyymc" value="<logic:present name="rs"><bean:write name="rs" property="cfyymc" /></logic:present>" />
							</td>
							<th width="width:12%;"></th>
							<td width="width:21%;"></td>
							<th width="width:12%;"></th>
							<td width="width:22%;">
								<div class="btn">
				              		<button type="button"  id="search_go" 
				              		onclick="allNotEmpThenGo('wjcfJcsz_cfyydm.do?method=cfyydmCx&go=go');return false;">
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
						��ѯ���&nbsp;&nbsp;<font color="red">����ѡ�е�ԭ�����Ϊ��ʹ�ô���</font>
					</span>
				</h3>

				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="width:10%">
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
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi('wjcfJcsz_cfyydm.do?method=cfyydmCk',410,280);">
									<logic:iterate id="s" name="rs" indexId="ind">	
										<logic:equal name="ind" value="1">
										<td>
											<logic:iterate id="s0" name="rs" indexId="ind0" offset="0" length="1">
											<input type="checkbox" name="checkVal" id="pkV" 
												value="<bean:write name="s"/>" 
												<logic:equal name="s0" value="y">
													disabled="none"
												</logic:equal>
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
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
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
		<input type="hidden" name="lx" id="lx" />
		<div id="tmpdiv1" style="display:none">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ִ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								<font color="red">*</font>
								����ԭ������
							</th>
							<td style="width:70%">
								<input type="text"  onkeypress="if(event.keyCode==13){return false;}"   name="divcfyymc" id="divcfyymc" maxlength="20" onblur="checkExists('xg_wjcf_cfyydmb','cfyymc');" />
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									��"<font color="red">*</font>"����Ϣ����¼��
								</div>
								<div class="btn">
									<button type="button"  class="button2" id="btn_bc"  onclick="save();return false;">
										�� ��
									</button>
									<button type="button"  class="button2"  onclick="closeWindown();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</div>
			<div id="tmpdiv2" style="display:none">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ִ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								
								����ԭ������
							</th>
							<td style="width:70%">
								<input type="text"  name="divcfyymc" id="divcfyymc1" maxlength="20" onblur="checkExists('xg_wjcf_cfyydmb','cfyymc');" />
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button"  class="button2"  onclick="closeWindown();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</div>
	</body>
</html>
