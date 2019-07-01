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
		
		jQuery(function(){
			var zydm = jQuery('#zydm');

			zydm.combobox({
				valueField:'dm',
 				textField:'mc',
				url:'sjyJcsjcsh.do?method=setZyOption',
				data:{url:'sjyJcsjcsh.do?method=setZyOption'}
			});
			
			zydm.combobox('setValue',$('search_zy').value);
			
		});
		
		jQuery(function(){
			var nj = jQuery('#nj');

			nj.combobox({
				valueField:'dm',
 				textField:'mc',
				url:'sjyJcsjcsh.do?method=setNjOption',
				data:{url:'sjyJcsjcsh.do?method=setNjOption'}
			});
			
			nj.combobox('setValue',$('search_nj').value);
		});
		
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

			<input type="hidden" id="search_nj" value="${nj }"/>
			<input type="hidden" id="search_zy" value="${zy }"/>
			<input type="hidden" name="importResult" id="importResult" value=""/>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
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
										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" 
											onclick="czSearchCond('bjdm-bjmc-nj-zydm');">
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
									�༶����
								</th>
								<td>
									<html:text property="bjdm" styleId="bjdm" style="width: 150px"
										onkeypress="if(pressEnter(event)){searchRs();return false;}" maxlength="20"/>
								</td>
								<th>
									�༶����
								</th>
								<td>
									<html:text property="bjmc" styleId="bjmc" style="width: 150px"
										onkeypress="if(pressEnter(event)){searchRs();return false;}" maxlength="20"/>
								</td>
								<th>
									�꼶
								</th>
								<td>
<%--									class="easyui-combobox"--%>
									<html:select property="nj" styleId="nj" style="width: 150px" title="��¼��">
										
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									����רҵ
								</th>
								<td>
									<html:select property="zydm" styleId="zydm" style="width: 150px" title="��¼��">
										
									</html:select>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
								<th>
									
								</th>
								<td>
								
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end -->
			
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