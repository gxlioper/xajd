<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommPjpy.js'></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			
			var id = "left_a_0";
			var xmdm=$("czxm").value;
			if($(id) && xmdm==""){
				$(id).click();
			}else if(xmdm!=""){
				var xmdmArr=jQuery("input[name=xmdmArr]").each(function(i){
					var xmdmVal=jQuery(this).val();
					if(xmdmVal==xmdm){
						$("left_a_"+[i]).click();
					}
				});
				searchRs();
			}
			
			
		}

		//��ѯ�����
		function searchRs(){
			//��ť����
<%--			controlBtn();--%>
			var url = "general_wdpj_xmsh_ajax.do?method=searchPjpyXmsh";
			var xmdm = $("shxm").value;
			var xmmc = escape($("xmmc_"+xmdm).value);
			var lyb = escape($("lyb_"+xmdm).value);
			var xmjb = escape($("xmjb_"+xmdm).value);
			var ie = "ie";
			
			var otherValue = [ie,xmdm,xmmc,stylePath];

			//showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}
		
		function pjxmsh(){
			
			var n=0;
			var pk=jQuery("input[name=pkValue]:checked");
			if(pk.length==0){
				alertInfo("�빴ѡ��Ҫ��˵����ݣ�");
				return false;
			}else if(pk.length==1){//��ѡ������¼���
				var xmdm=jQuery("#shxm").val();
				showWdpjXssq(xmdm,"sh",jQuery(pk[0]).val());
			}else{//��ѡ������¼
				
				tipsWindown("ϵͳ��ʾ","id:div_plsh","350","200","true","","true","id");
			}
			
			return false;
		}
		
		function saveShzt(shzt){
			
			var xh=new Array();
			jQuery("input[name=pkValue]:checked").each(function(i){
			
				xh[i]=jQuery(this).val();
			});
			
			
			var xmdm=jQuery("#shxm").val();
			
			var flag=true;
			
			if(shzt=="ͨ��"){
			
				flag=checkShxz(xh,xmdm);
				
			}
			
			if(flag){
				var parameter={}
				
				parameter["xh"]=xh.join(" !!@@!! ");
				parameter["xmdm"]=xmdm;
				parameter["shzt"]=escape(shzt);
				parameter["shyj"]=escape(jQuery("#shyj").val());
				var url = "general_wdpj_xmsh_ajax.do?method=savePlShzt";
				
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
			
		}
		
		//��ʾѧ������
		function showWdpjXssq(xmdm,opera,xh){
			
			var url = "general_pjpy.do?method=wdpjXmshDetail&xmdm="+xmdm;
			url+="&opera="+opera;
			url+="&xh="+xh
			showTopWin(url,"800","600");
		}
		
		function checkShxz(xhArr,xmdm){
			//�ж�ѧ��ѧ��
			
			var parameter={}
			
			parameter["array_xh"]=xhArr.join("!!array!!");;
			
			parameter["str_xmdm"]=xmdm;
			
			//����URL
			jQuery.ajaxSetup({async:false});
			
			var url = "general_pjpy_xmsz_ajax.do?method=checkShxz";
			
			var flag=false;
			
			//------------�����ж� begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!="null" && result!=""){
						alertInfo(result);
						flag=false;
					}else{
						flag=true;
					}
					
				}
			);
			jQuery.ajaxSetup({async:true});
			
			return  flag;
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
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - ��Ŀ���</a>
			</p>

			<!-- ���߰��� -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ���߰��� end -->
			
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
			
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.��������г�����ĿΪ��<font color="blue">���ʸ����,���������ʱ����</font>��������Ŀ��</br>
				2.�����Ŀ����Ļ��������Ե��<font color="blue">��ѯ��Ŀ</font>���й��ˡ�</br>
				3.�����ӵ�ж��������ݣ������ǰ��ϵͳ����ʾ��ȷ����Ҫ�Ժ���<font color="blue">���</font>������ˡ�</br>
				4.�������ѡ<font color="blue">һ��</font>��¼�����<font color="blue">���</font>��ϵͳ��չ�ָ�ѧ����ϸ�����ҳ�档</br>
				5.�������ѡ<font color="blue">����</font>��¼�����<font color="blue">���</font>��ϵͳ����չ����ϸҳ�棬��ִ��������˲�����</br>
				6.�������鿴ĳѧ������ϸ������Ϣ������<font color="blue">˫��</font>������¼��
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>�ҵ�����</p>
						</a>   	
					</div>
					
				    <div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_pjlc_xssq.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
							<p>ѧ������</p>
						</a>   	
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
							<p>��ʦ�ϱ�</p>
						</a>
					</div>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do?shzt=');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>�����ѯ</p>
						</a>
					</div>
					
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->

		<html:form action="/pjpyXmsh">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="shxm" id="shxm" value="${xmdm}"/>
			<input type="hidden" name="spgw" id="spgw" value="${spgw }"/>
			<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
			
			<input type="hidden" id="hid_xmdm" value="${hid_xmdm }"/>
			<input type="hidden" id="hid_xmmc" value="${hid_xmmc }"/>
			<input type="hidden" id="hid_ywmc" value="${hid_ywmc }"/>
			<input type="hidden" id="hid_xmxz" value="${hid_xmxz }"/>
			<input type="hidden" id="hid_xmfw" value="${hid_xmfw }"/>
			<input type="hidden" id="hid_xmlx" value="${hid_xmlx }"/>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			
			<!-- ������ end-->
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
			
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_sh"
								onclick="pjxmsh();return false;"
								class="btn_sh">
								<bean:message key="lable.btn_sh" />
							</a>
							<logic:equal name="fwfs" value="homepage">
								<input type="hidden" name="fwfs" id="fwfs" value="${fwfs}"/>
								<li>
									<a href="#" onclick="returnHomPage('');return false;" class="btn_fh">����</a>
								</li>
							</logic:equal>
							
						</li>
					</ul>
					<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
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
						<td width="20%" valign="top" style="overflow-x: auto;">
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
													<input type="hidden" name="xmdmArr" id="xmdm_${index }" value="${xmnr.xmdm}"/>
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
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
						<!-- ������� DIV -->
			<div id="div_plsh" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2" >
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th style="width:30%">
									������
								</th>
								<td>
									<textarea  name='shyj' 
									id="shyj" style="word-break:break-all;width:85%" onblur="chLeng(this,500);"
										rows='4' ></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<logic:notEqual name="usertType" value="xy">
										<button type="button" name="�˻�" onclick="saveShzt('�˻�')">
											��  ��
										</button>
										</logic:notEqual>
										<button type="button" name="ͨ��" onclick="saveShzt('ͨ��')">
											ͨ  ��
										</button>
										<button type="button" name="��ͨ��" onclick="saveShzt('��ͨ��')">
											��ͨ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
	</body>
</html>