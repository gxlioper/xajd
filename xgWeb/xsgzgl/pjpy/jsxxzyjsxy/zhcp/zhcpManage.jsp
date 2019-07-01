<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}
			
			
		}

		//��ѯ�����
		function searchRs(){
			//��ť���� 
			controlBtn();
			var url = "general_zhcp_ajax.do?method=searchZhcpZcxx";
			var xmdm = $("shxm").value;
			var xmmc = escape($("xmmc_"+xmdm).value);
			var lyb = escape($("lyb_"+xmdm).value);
			var xmjb = escape($("xmjb_"+xmdm).value);
			var ie = "ie";

			var otherValue = [ie,xmdm,xmmc,lyb,xmjb];

			//showWaitingDiv("1000");
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
				$("is_default").value = "no";
			}
		}
		
		
		//��ť����
		function controlBtn(){
			
			var xmdm = $("shxm").value;
			if(xmdm == "zd1"){
				$("buttonbox").style.display="";
				if($("li_js")){//����
					$("li_js").style.display="";
				}
				if($("li_bc")){//����
					$("li_bc").style.display="none";
				}
			}else if(xmdm=="zd2"){
				$("buttonbox").style.display="";
				if($("li_bc")){//����
					$("li_bc").style.display="";
				}
				if($("li_js")){//����
					$("li_js").style.display="none";
				}
			}else {
				$("buttonbox").style.display="none";
				if($("li_js")){//����
					$("li_js").style.display="none";
				}
				if($("li_bc")){//����
					$("li_bc").style.display="none";
				}
			}
		}

		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				
				if(xn_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
					flag = false;
				}
			}
			return flag;
		}
		
		//��ʾ�ֶ��޸�
		function showEditDiv(zd,zdid){
			
			var url="czzy_zhcp_ajax.do?method=showZdxgDiv";
			
			//��������
		 	var parameter = {
				"zd":zd,
				"zdid":zdid
			};
		  	
			jQuery("#div_zdxg").load(url,parameter,function(){
			
				var sqqx = jQuery("#sqqx").val();
				
				if(sqqx == "no"){
					$("btn_bc").style.display = "none";
				}
				
				jQuery("#"+zd+"_id").val(jQuery("#"+zdid).val());
				
				tipsWindown("ϵͳ��ʾ","id:div_zdxg","350","250","true","","true","id");
			});
		}
		
		
		function affirmValue(zd,zdid){
			jQuery("#"+zdid).val(jQuery("#"+zd+"_id").val());
			closeWindown();
		}
		
		//�����ۺϲ�����Ϣ
		function saveZhcpInfo(){
			
			confirmInfo("�ò������ᱣ�����޸���Ϣ���Ƿ������",function(tag){
				if(tag=="ok"){
						var kzzd=document.getElementsByName("kzzdArr");
						var xmdm=jQuery("#shxm").val();
						var pkValue=new Array();
						var fs=new Array();
						jQuery.ajaxSetup({async:false});
							
						var joinArr=new Array();
							//������չ�ֶ�����
						var kzzdArr=jQuery("input[name=kzzdArr]").each(function(i){
								// ��ȡ��չ�ֶ������ֵ
								var kzzd=jQuery(this).val();
								
								// ����һ�����������Ϣ������;
								var array=new Array();
								
								jQuery("input[name="+kzzd+"],select[name="+kzzd+"]").each(function(j){
									
										array[j] =escape(jQuery(this).val());
									
								});
							
								joinArr[i]=array.join(" !!@@!! ");
								
						});
						
						jQuery("[name=div_pkValue]").each(function(i){
									
							pkValue[i] =escape(jQuery(this).val());
									
						});
						
						jQuery("[name=fs]").each(function(i){
									
							fs[i] =escape(jQuery(this).val());
									
						});
						
		
						var url = "general_zhcp_ajax.do?method=saveZhcpInfo";
						
		             // �õ�JSON����
		             var parameter ={};
		          	 
					 for(i=0;i<joinArr.length;i++){
					 	
					 	parameter[kzzd[i].value]=joinArr[i];
					 		
				 	 }
				 	 parameter["xmdm"]=xmdm;
					 parameter["pkValue"]=pkValue.join(" !!@@!! ");
					 parameter["fs"]=fs.join(" !!@@!! ");
		
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
							
							searchRs();
							closeWindown();	
						}
					);
					
					jQuery.ajaxSetup({async:true});
				}
			});
			
		}
		
		function showJsfw(){
			tipsWindown("ϵͳ��ʾ","id:plszDiv","350","300","true","","true","id");
		}
		
		function save(){
			var xy=jQuery("#xy option:selected").text();
			var zy=jQuery("#zy option:selected").text();
			var bj=jQuery("#bj option:selected").text();
			var nj=jQuery("#nj option:selected").text();
			var message="";
			if(jQuery("#nj").val()!=""){
				message+=nj+"��";
			}
			
			if(jQuery("#xy").val()!=""){
				message+=xy+jQuery("#xbmc").val();
			}
			
			if(jQuery("#zy").val()!=""){
				message+=zy+"רҵ";
			}
			
			if(jQuery("#bj").val()!=""){
				message+=bj+"��";
			}
			
			confirmInfo(" ����Ҫͬ��"+message+"����ѧ���������֡������֡�ƽʱ�����֣��Ƿ������",function(ok){
				if(ok=="ok"){
					var url = "general_zhcp_ajax.do?method=accountZhcp";
					
					var parameter ={};
					parameter["xydm"]=$("xy").value;
					parameter["zydm"]=$("zy").value;
					parameter["bjdm"]=$("bj").value;
					parameter["nj"]=$("nj").value;
	
					jQuery.ajaxSetup({async:false});
					jQuery.post(url,
						parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
							searchRs();
							closeWindown();	
						}
					);
					jQuery.ajaxSetup({async:true});
				}
			});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �Ƿ��ʼ��  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<input type="hidden" name="shxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn }"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq }"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox" >
				<!-- ��ť -->
				<div class="buttonbox" id="buttonbox">
					<ul>
						<li >
							<a href="#" id="li_bc" onclick="saveZhcpInfo();return false;" class="btn_yl">
								�� ��
							</a>
						</li>
						<li >
							<a href="#" id="li_js" onclick="showJsfw();return false;" class="btn_tj">
								ͬ �� 
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
		
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
				<!-- From���� -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}" 
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													<input type="hidden" id="xmmc_${xmnr.xmdm}" value="${xmnr.xmmc}"/>
													<input type="hidden" id="xmjb_${xmnr.xmdm}" value="${xmnr.xmjb}"/>
													<input type="hidden" id="lyb_${xmnr.xmdm}" value="${xmnr.lyb}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- �ֶ��޸ĵ����� -->
			<div id="div_zdxg" style="display:none">
			</div>
			
			<!-- �ּܷ���ѡ��DIV -->
			<div id="plszDiv" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ּܷ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										style="width:150px" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xb" />
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:select property="xydm" styleId="xy" disabled="true"
											value="${userDep }" style="width:150px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:hidden property="xydm" value="${userDep }" />
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="xydm" styleId="xy" style="width:150px"
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
									<html:select property="zydm" styleId="zy" style="width:150px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bj" style="width:150px">
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
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<button type="button" name="�� ��" onclick="save();return false;">
											ȷ ��
										</button>
										<button type="button" name="ȡ ��" onclick="closeWindown();return false;">
											ȡ ��
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