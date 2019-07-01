<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	
		<%@ include file="/syscommon/head.ini"%> 
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsgzgl/wjcf/cfsjwh/js/wjcfsjCx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ-->
		<script language="javascript" defer="defer">

		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		
		//��ѯ�����
		function searchRs(){
		
			var url = "wjcfCfshwh_cfsjwh.do?method=wjcfsjCxAjax";

			var ie = "ie";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","1000")
		}
		
		
		
		jQuery(function(){
			onShow();
		})
		
		
		function sjkwhExportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport("wjcfCfshwh_cfsjwh.do", sjkwhExportData);
		}
				
				
		// ��������
		function sjkwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "wjcfCfshwh_cfsjwh.do?method=sjkwhExportData&dcclbh=" + "wjcfCfshwh_cfsjwh.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/wjcfCfshwh_cfsjwh" method="post">
			<input type="hidden" id="text" value="<bean:message key="wjcf.text" />"/>

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal value="yes" name="czqx">
						<li>
							<a href="#" onclick="showDialog('', 800, 500,'wjcfsjZj.do');return false;" class="btn_zj">����</a>
						</li>
						<li>
							<a href="#" onclick="showModi();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="deleteJcrcgl();return false;" class="btn_sc">ɾ��</a>
						</li>
						
						<li>
							<a href="#" onclick="cfsscl();return false;" class="btn_xg">��������</a>
						</li>
						<li>
							<a href="#" onclick="cfjccl();return false;" class="btn_xg">����<bean:message key="wjcf.text" /></a>
						</li>
						<li>
							<a href="#" onclick="cfzzcl();return false;" class="btn_xg">������ֹ</a>
						</li>
						<li>
							<a href="#" onclick="toImportData('IMPORT_N100109'); return false;" class="btn_dr">��������</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">�鿴</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="sjkwhExportConfig();return false;">����</a></li>
						
						<%--
						
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>--%>
						<logic:equal value="10351" name="xxdm">
							<li><a href="#" class="btn_down" onclick="getWord();return false;">����<bean:message key="wjcf.text" />��������</a></li>
						</logic:equal>
						
						<logic:equal value="12915" name="xxdm">
							<li><a href="#" class="btn_dy" onclick="getWjCfWord();return false;">Υ�ʹ���֪ͨ���ӡ</a></li>
						</logic:equal>
						<logic:equal value="12865" name="xxdm">
							<li><a href="#" class="btn_down" onclick="getCfjdWord();return false;">���־���������</a></li>
						</logic:equal>
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
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=wjcfCfsjwhActionForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
			<!-- �������� -->
			<div id="cfssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<th>
									<span class="red">*</span>�����ĺ�
								</th>
								<td>
									<html:text property="sswh" styleId="sswh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span>����ʱ��
								</th>
								<td>
									<html:text property="sssj" styleId="sssj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sssj','y-mm-dd');" ></html:text>
								</td>
							</tr>
							<tr id="" style="">
								<th>
									<span class="red">*</span>���߽��
								</th>
								<td>
									<html:select property="ssjg" styleId="ssjg" style="width:140px" onchange="discfgg()">
										<html:option value=""></html:option>
										<html:option value="ά��ԭ����">ά��ԭ����</html:option>
										<html:option value="��������">��������</html:option>
										<html:option value="���Ĵ���">���Ĵ���</html:option>
									</html:select>
								</td> 
							</tr>
							<tr id="cfggw" style="display: none">
								<th>
									<span class="red">*</span>���ָ���Ϊ
								</th>
								<td>
									<html:select property="cflbdm" styleId="cflbdm" style="width:140px" >
										<html:option value=""></html:option>
										<html:options collection="cflbsList" property="dm" labelProperty="mc"/>
									</html:select>
								</td> 
							</tr>
							<tr id="">
								<th>
									�������
								</th>
								<td>
									<html:textarea property="ssyj" styleId="ssyj" rows="5" style="width:290px"></html:textarea>
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
										<button type="button"  name="����" onclick="ssjgsave();">
											�� ��
										</button>
										<button type="button"  name="ȡ��" onclick="closeWindown();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ���ֽ�� -->
			<div id="jcssDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="">
								<th>
									<span class="red">*</span><bean:message key="wjcf.text" />�ĺ�
								</th>
								<td>
									<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<span class="red">*</span><bean:message key="wjcf.text" />ʱ��
								</th>
								<td>
									<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');" ></html:text>
								</td>
							</tr>
							<tr id="">
								<th>
									<bean:message key="wjcf.text" />���
								</th>
								<td>
									<html:textarea property="jcyj" styleId="jcyj" rows="4" style="width:280px"></html:textarea>
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
										<button type="button"  name="����" onclick="jcjgsave()">
											�� ��
										</button>
										<button type="button"  name="ȡ��" onclick="Close();return false;">
											ȡ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
