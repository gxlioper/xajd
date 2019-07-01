<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/sjy/jcsjsz.js"></script>
		<script language="javascript" defer="defer">
		
		defaultBmOption();
		
		function defaultBmOption(){
			setTimeout("defaultList('nj')",0)
			setTimeout("defaultList('xy')",100)
			setTimeout("defaultList('zy')",1500)
			setTimeout("defaultList('bj')",2500)
		}
		
		function defaultList(lx){
			if(lx == "nj"){
				jQuery('#nj').combobox('setValue',$('search_nj').value);
			}else if(lx == "xy"){
				jQuery('#xydm').combobox('setValue',$('search_xy').value);
			}else if(lx == "zy"){
				jQuery('#zydm').combobox('setValue',$('search_zy').value);
			}else if(lx == "bj"){
				jQuery('#bjdm').combobox('setValue',$('search_bj').value);
			}
		}
		
		jQuery(function(){
		
			var nj = jQuery('#nj');
			var url = "sjyJcsjcsh.do?method=setNjOption";
			
			nj.combobox({
				valueField:'dm',
 				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto",
				onChange:function(){
					setTimeout('setBjList()',100);
				}
			});
		});
		
		jQuery(function(){
		
			var xydm = jQuery('#xydm');
			var url = "sjyJcsjcsh.do?method=setBmOption&id=xydm";
			
			xydm.combobox({
				valueField:'dm',
 				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto",
				onChange:function(){
					setTimeout('setZyList()',100);
					setTimeout('setBjList()',200);
				}
			});
		});
		
		jQuery(function(){
			
			var zydm = jQuery('#zydm');
			var nj = jQuery('#nj').combobox('getValue');
			var xy = jQuery('#xydm').combobox('getValue');
			
			var url = "sjyJcsjcsh.do?method=setZyOption";
				url+= "&search_nj="+nj;
				url+= "&search_xy="+xy;
				
			zydm.combobox({
				valueField:'dm',
 				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto",
				onChange:function(){
					setTimeout('setBjList()',100);
				}
			});
		});
		
		jQuery(function(){
	
			var bjdm = jQuery('#bjdm');
			var nj = jQuery('#nj').combobox('getValue');
			var xy = jQuery('#xydm').combobox('getValue');
			var zy = jQuery('#zydm').combobox('getValue');
			
			var url = "sjyJcsjcsh.do?method=setBjOption";
				url+= "&search_nj="+nj;
				url+= "&search_xy="+xy;
				url+= "&search_zy="+zy;
				
			bjdm.combobox({
				valueField:'dm',
 				textField:'mc',
				url:url,
				data:{url:url},
				panelHeight:"auto"
			});
		});
		
		//����רҵ�б�
		function setZyList(){
			
			var nj = jQuery('#nj').combobox('getValue');
			var xy = jQuery('#xydm').combobox('getValue');
			
			var url = "sjyJcsjcsh.do?method=setZyOption";
				url+= "&search_nj="+nj;
				url+= "&search_xy="+xy;
				
			jQuery(function(){
				var zydm = jQuery('#zydm');
				
				zydm.combobox({
					valueField:'dm',
	 				textField:'mc',
					url:url,
					data:{url:url}
				});
			});

		}
				
		//�����༶�б�
		function setBjList(){
			
			var nj = jQuery('#nj').combobox('getValue');
			var xy = jQuery('#xydm').combobox('getValue');
			var zy = jQuery('#zydm').combobox('getValue');
			
			var dm = jQuery('#bjdm').combobox('getValue');
			var mc = jQuery('#bjdm').combobox('getText');
			
			var url = "sjyJcsjcsh.do?method=setBjOption";
				url+= "&search_nj="+nj;
				url+= "&search_xy="+xy;
				url+= "&search_zy="+zy;

			jQuery(function(){
				var bjdm = jQuery('#bjdm');
				bjdm.combobox({
					valueField:'dm',
	 				textField:'mc',
					url:url,
					data:{url:url}
				});
			});			
		}
		
		//��ѯ
		function searchRs(){
			allNotEmpThenGo('/xgxt/sjyJcsjcsh.do?method=jcsjcshManage');
		}

		</script>
	</head>
	<body onload="">
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
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ͨ�������ݵ��롱���ߡ��ӿ�ͬ�������뱾ϵͳ��ʱ��Ȼ������������ƶ�����
				�������Ϲ�������ݣ���ɺ󣬲�������ѡ�ύ�����ߡ������ύ��������ʱ�������
				������ʽ��ʹ��Ӧ��������ѧ��ϵͳ	��
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/sjyJcsjcsh">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="hadRs" id="hadRs" value="${hadRs }"/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>

			<input type="hidden" name="search_nj" id="search_nj" value="${nj }"/>
			<input type="hidden" name="search_xy" id="search_xy" value="${xy }"/>
			<input type="hidden" name="search_zy" id="search_zy" value="${zy }"/>
			<input type="hidden" name="search_bj" id="search_bj" value="${bj }"/>
			<input type="hidden" name="importResult" id="importResult" value=""/>
			
			<!-- loading -->
			<%@ include file="/comm/other/loading.jsp"%>
			<!-- loading -->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="div_toolbox" style="display: none">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="impAndChkData();return false;"
									class="btn_dr">���ݵ���</a>
							</li>
							<li>
								<a href="#"
									onclick="tbData();return false;"
									class="btn_gx">�ӿ�ͬ��</a>
							</li>
							<li>
								<a href="#"
									onclick="showRule();return false;"
									class="btn_sq">�����ƶ�</a>
							</li>
							<li>
								<a href="#"
									onclick="submitBmInfo('choose');return false;"
									class="btn_sh">��ѡ�ύ</a>
							</li>
							<li>
								<a href="#"
									onclick="submitBmInfo('all');return false;"
									class="btn_shtg">�����ύ</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="btn_cx" id="search_go" onclick="hiddenNr();searchRs();">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('bjdm-xydm-nj-zydm-xh-xm');">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj" style="width: 150px" title="��¼��">
									</html:select>
								</td>
								<th>
									Ժϵ
								</th>
								<td>
									<html:select property="xydm" styleId="xydm" style="width: 150px" title="��¼��">
									</html:select>
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zydm" style="width: 150px" title="��¼��">
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" styleId="bjdm" style="width: 150px" title="��¼��">
									</html:select>
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="width: 150px"
										onkeypress="if(pressEnter(event)){searchRs();return false;}" maxlength="20"/>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width: 150px"
										onkeypress="if(pressEnter(event)){searchRs();return false;}" maxlength="20"/>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box" id="div_main_box" style="display: none">
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
								<div class="titlelist" style="height: 477px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');changeCzxm('${xmnr.xmdm}');return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="setLiClick('${index}');changeCzxm('${xmnr.xmdm}');return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="dy_area" style="width:650px;overflow-x:auto;overflow-y:hidden;">
								<span class="formbox">
									<table class="dateline" width="100%">
										<!-- ���� -->
								    	<thead>
											<tr>
												<td width="5px">
													<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
												</td>
												<logic:iterate id="tit" name="topTr" offset="0">
													<td>
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
								      		</tr>
										</thead>
										<!-- ���� end-->
										
										<!-- ���� -->
										<logic:equal name="hadRs" value="yes">
								    	<tbody>
									    	<logic:iterate name="rsArrList" id="rs" indexId="index">
									    		<tr onclick="rowOnClick(this);">
									    			<td align="center">
														<input type="checkbox" id="checkVal" 
															   name="primarykey_checkVal"  
															   value="<logic:iterate id="v" name="rs" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
													</td>
									    			<logic:iterate id="v" name="rs">
														<td align="left" nowrap="nowrap">
															${v }
														</td>
													</logic:iterate>
												</tr>
									    	</logic:iterate>
										</tbody>
										</logic:equal>
										<!-- ������ -->
										<%@ include file="/comm/noRows.jsp"%>
										<!-- ������ end-->
									</table>
								</span>
								</br>
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=sjyJcsjcshForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>