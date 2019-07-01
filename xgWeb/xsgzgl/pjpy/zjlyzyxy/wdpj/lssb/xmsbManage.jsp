<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
			searchRs();
		}

		//��ѯ�����
		function searchRs(){

			jQuery.ajaxSetup({async:false});
			
			var yhlx = $("yhlx").value;
			var url = "general_wdpj_lssb_ajax.do?method=searchWdpjLssb";
			var ie = "ie";
			var xmdm = $("xmdm").value;
			var bjdm = $("bjdm").value;
			
			var otherValue = [ie,xmdm,bjdm];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
			
			if($("tsxxInfo")){
				jQuery("#span_xmts").html($("tsxxInfo").value);
			}
		}

		 function save(opera,xmdm,xh){
     		
     		confirmInfo("���Ƿ�ȷ��Ҫ<font color='blue'>�ϱ���ѧ��</font>��",function(tag){
				
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["xmdm"]=xmdm;
					
					parameter["xh"]=xh;
					
					parameter["opera"]=opera;
					
					parameter["sqly"]=escape(jQuery("#sqly").val());
					
					var url = "general_wdpj_lssb_ajax.do?method=saveXmsb";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result,function(tag){
							
								if(tag=="ok"){
									
									closeWindown();
								
									searchRs();
								}
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
				
			});
		}
		
		//��ʾѧ����ϸ��Ϣ
		function showXsxxDiv(xh){
		
			var url="general_wdpj_lssb_ajax.do?method=showXsxxDiv";
			
			var xmdm=$("xmdm").value;
			//��������
		 	var parameter = {
				"xh":xh,
				"xmdm":xmdm
			};
			
			jQuery("#div_xsxx").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_xsxx","600","400","true","","true","id");
			});
		}
		
		//��ʾѧ������(�޸�)div
		function showDiv(lx,xh){

			var url="general_wdpj_lssb_ajax.do?method=showLssbDiv";
			// ��������
			var opera=lx;
			// ��Ŀ����
			var xmdm=$("xmdm").value;
			
			//��������
		 	var parameter = {
				"opera":opera,
				"xh":xh,
				"xmdm":xmdm
			};
		  	
			jQuery("#div_modi").load(url,parameter,function(){
			
				tipsWindown("ϵͳ��ʾ","id:div_modi","600","320","true","","true","id");
			});
		}
		
		//�ж�ѧ��������Ŀ�ʸ�
		function checkXssqZg(lx,xh){

			var url="general_wdpj_lssb_ajax.do?method=checkXssbZg";
			var xmdm=$("xmdm").value;
			var opera=lx;
			
			//��������
		 	var parameter = {
				"xmdm":xmdm,
				"xh":xh
			};
		  	
			jQuery.post(url,parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					if(result==""){
						showDiv(lx,xh);
					}else{
						alertInfo(result);
					}
					
				}
			);
		}
		
		// �����ϱ�
		function disfrockXssqInfo(pkValue){
			
			confirmInfo("�ò������᳷��������Ϣ���Ƿ�ȷ�������ò�����",function(tag){
				
				if(tag=="ok"){
					
					var xh=new Array();
					
					var parameter={}
					
					
					parameter["pkValue"]=escape(pkValue);
					
					var url= "general_wdpj_xssq_ajax.do?method=disfrockXssqInfo";
					
					jQuery.ajaxSetup({async:false});	
					
					jQuery.post(url,
						parameter,
						function(result){
						
							alertInfo(result,function(tag){
								
								if(tag=="ok"){
									searchRs();
								}
							
							});
							
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		
		function checkLssb(){
		
			var bjdm=jQuery("#bj").val();
			var xmdm=jQuery("#xmmc").val();
			
			var flag=true;
			if(xmdm==""){
				alertInfo("�ϱ���Ŀ����Ϊ��!");
					
				flag= false;
				
			}
			if(bjdm=="" && flag){
				alertInfo("�༶����Ϊ��!");
				flag= false;
			}
			
			if(flag){
				var url = "general_pjpy.do?method=xmsbManage";
					url+= "&xmdm="+xmdm;
					url+= "&bjdm="+bjdm;
					
				refreshForm(url);
			}
		}
		
		//��ʾ��ʦ�ϱ�Div
		function showLssbDiv(){
			tipsWindown("ϵͳ��ʾ","id:div_lssb","400","300","true","","true","id");
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" 
					onmouseover ="showHelpDiv()"
					onmouseout="showHelpDiv()"
				>
				��������</a><img src="<%=stylePath%>/images/ico_new02.gif" />
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt"  id="div_help" style="display: none">
			<h3>
				<span>ϵͳ��ʾ��</span>
			</h3>
			<p>
				<span id="span_xmts"></span>
				<span>
				1.��ҳ��չʾ����ǰҳ����ѡ�༶��<font color="blue">����ѧ��</font>�������ۺϷְ༶����<font color="blue">��������</font>��<br/>
				2.���������ĳѧ�����Ա��ϱ�����Ŀ������<font color="blue">�ϱ�</font>��<br/>
				3.�Ѿ��ϱ���ѧ���������û�б���һ�û���˹��Ļ������Ե��<font color="blue">����</font>ȡ���ϱ���<br/>
				4.<font color="blue">���ѧ����ѧ��</font>������չʾ��ѧ������ϸ��Ϣ��<br/>
				</span>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
			<input type="hidden" name="bjdm" id="bjdm" value="${bjdm}"/>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="goPjpyWdpj();return false;" class="btn_fh">
								�����ҵ�����
							</a>
						</li>
						<li>
							<a href="#" id="btn_sx" 
								onclick="showLssbDiv();return false;"
								class="btn_sx">
								<span>�л��༶����Ŀ</span>
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<div style="display:none">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				</div>
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box" style="overflow-x:hidden;overflow-y:auto;height:480px;width:99.5%;vertical-align: top">
				<table align="center" width="98%">
					<tr style="">
						<td width="100%" valign="top" style="">
							<div id="div_rs" style="">
							</div>
						</td>
					</tr>
				</table>
			</div>
			<!-- ������ʾ����ʼ end-->	
			<div id="div_modi" style="display:none">
				
			</div>
			
			<div id="div_xsxx" style="display:none">
				
			</div>
			<!-- ��ʦ�ϱ������� begin-->
			<div id="div_lssb" style="display:none">
				<div class="open_win01" style="">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϱ����ѡ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="25%">
									<font color="red">*</font>�ϱ���Ŀ
								</th>
								<td>
									<html:select property="xmmc" styleId="xmmc" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width:200px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:200px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zy" style="width:200px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:200px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="checkLssb()">ȷ ��</button>
										<button type="button" onclick="closeWindown();return false;">�� ��</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʦ�ϱ������� end-->
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>