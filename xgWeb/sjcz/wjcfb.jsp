<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/commWjcfDAO.js"></script>
		<script type="text/javascript">
			function getwh(tid) {
				var cfwh = document.getElementById(tid).value;
				var xxdm = document.getElementById('xxdm').value;
				if (xxdm=='10856') {
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='������ѧ[][]��';
					}
				} else if (xxdm=='10402') {
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='��ʦ[]��';
					}
				} else if (xxdm=='10628#') {
					if (cfwh==null || cfwh=='') {
						document.getElementById(tid).value='����ѧ�ĺš���';
					}
				}
			}
			function dataDoSave11(mustFill) {
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alert("�뽫��\"*\"�ŵ���Ŀ����������");
						return false;
					}
				}
				
				var url = "/xgxt/modiData.do?realTable=";
				url += 'wjcfb';
			//	var tmpTable = window.dialogArguments.document.forms[0].realTable.value;
				url += "&doType=save";
				url += "&tableName=";
				url += 'view_wjcf';
				url += "&pk=";
				url += 'xh||cfwh||cfsj';
				url += "&pkValue=";
				url += document.forms[0].pkValue.value;
				url += "&from=";
				url += 'discipInfo';
				
				//������ͬһ����ʱ�����ĺ������Ƿ��д���
				var sj = $('cfsj').value;
				var wh = $('cfwh').value;
				var xh = $('xh').value;
				var hidd_cfsj = $('hidd_cfsj').value;
				var hidd_cfwh = $('hidd_cfwh').value;
				var type = $('doType').value;
			
				commWjcfDAO.checkStuWjcfIsExists(xh,sj,wh,function(data) {
				
					if (type=='add') {
						if (!data) {
							alert("�����ڴ���ʱ��Ϊ:'" + sj + "',�����ĺ�Ϊ:'" + wh+"'���ļ������ܹ�һ�δ��֣�\nͬһѧ��ͬһ����ʱ�����ĺ����治���ж��δ��֣����������ʱ����ĺš�");
							return false;
						} else {
								document.forms[0].action = url;
								document.forms[0].submit();
								alert("����ɹ���");
								window.close();
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
							}
					} else {

						if (!data && ((hidd_cfsj != sj) || (hidd_cfwh !=wh)) && (sj != null && wh != null)) {
							alert("�����ڴ���ʱ��Ϊ:'" + sj + "',�����ĺ�Ϊ:'" + wh+"'���ļ������ܹ�һ�δ��֣�\nͬһѧ��ͬһ����ʱ�����ĺ����治���ж��δ��֣����������ʱ����ĺš�");
							return false;
						} else {
								document.forms[0].action = url;
								document.forms[0].submit();
								alert("����ɹ���");
								window.close();
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
						}
					}	
				});
				
			}
		</script>
	</head>
	<body onload="">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ����ά�� - Υ�ʹ�������ά��</a>
			</p>
		</div>


		<html:form action="/data_search" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
			<logic:notPresent name="showXbemy">
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						�д�������
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
                       alert("�������ѧ����Ч!");
                    </script>
					</logic:equal>
					<input type="hidden" id="doType" name="doType"
						value="<bean:write name="doType" scope="request"/>" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="<bean:write name="pkValue" scope="request"/>" />
					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc-zzmmmc" />
					<input type="hidden" id="url" name="url" value="/sjcz/wjcfb.jsp" />
					<input type="hidden" id="clwh" name="clwh"
						value="<bean:write name="clwh"/>" />
					<input type="hidden" name="hidd_cfsj" id="hidd_cfsj" value="${rs.cfsj }"/>	
					<input type="hidden" name="hidd_cfwh" id="hidd_cfwh" value="${rs.cfwh }"/>	
							
					<logic:notEqual value="13022" name="xxdm">
						<logic:notEqual value="add" name="doType">

							<div class="formbox">
								<h3 class="datetitle_01">
									<span> �����ļ�����&nbsp;&nbsp; <logic:empty name="rs" property="fjsclj">
											<font color="red"> ���޴����ļ�!</font>
										</logic:empty> </span>
								</h3>

								<logic:notEmpty name="rs" property="fjsclj">
									<table summary="" class="dateline" align="" width="100%">
										<tbody>
											<tr onclick="rowMoreClick(this,'',event);" style="cursor: hand">
												<logic:empty name="rs" property="fjsclj">
													<td colspan="2">

													</td>
												</logic:empty>
												<logic:notEmpty name="rs" property="fjsclj">
													<td align="center" title="�����ļ�����">
														<a href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }"
															target="_blank">����</a>
													</td>
													<td align="center" title="�����ĺ�">
														${rs.cfwh }
													</td>
													<td align="center" title="����ʱ��">
														${rs.cfsj }
													</td>
												</logic:notEmpty>
											</tr>
										</tbody>
									</table>
								</logic:notEmpty>
							</div>
							
						</logic:notEqual>
					</logic:notEqual>


					<div class="tab">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>Υ�ʹ�����Ϣά��2</span>
									</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
											<logic:equal value="11417" name="xxdm">
												<button type="button" onclick="dataDoSave11('xn-xq-xh-cflb-cfyy');"
													id="buttonSave">
													�� ��
												</button>
											</logic:equal>
											<logic:notEqual value="11417" name="xxdm">

													<button type="button"
														onclick="if(checkXnNd('xn','nd'))dataDoSave11('xn-xq-xh-bz-cfsj-cfwh');"
														id="buttonSave">
														�� ��
													</button>
											</logic:notEqual>
											<button type="button" onclick="Close();return false;" id="buttonClose">
												�� ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<tbody>
								<tr>
									<th width="16%">
										<font color="red">*</font>ѧ��
									</th>
									<td width="34%">
										<html:text name='rs' property="xh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
										<logic:equal name="doType" value="add">
											<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu" style="">
												ѡ��
											</button>
										</logic:equal>
									</td>
									<logic:notEqual value="11417" name="xxdm">
										<th width="16%">
											<font color="red">*</font>���
										</th>
										<td width="34%">
											<html:select name="rs" property="nd" style="width:90px"
												styleId="nd">
												<html:options collection="xnList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
									</logic:notEqual>
								</tr>
								<tr>
									<th>
										����
									</th>
									<td>
										${rs.xm }
									</td>
									<th>
										<font color="red">*</font>ѧ��
									</th>
									<td>
										<html:select name="rs" property="xn" style="width:90px"
											styleId="xn">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
								</tr>

								<tr>
									<th>
										������ò
									</th>
									<td align="left">
											${rs.zzmmmc }
										<html:text name='rs' property="xb" styleId="xb"
											style="display:none" />
									</td>
									<th>
										<font color="red">*</font>ѧ��
									</th>
									<td align="left">
										<html:select name="rs" property="xq" style="width:90px"
											styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										�꼶
									</th>
									<td align="left">
										${rs.nj }
									</td>
									<th>
										<font color="red">*</font>�������
									</th>
									<td align="left">
										<html:select name="rs" property="cflb" style="width:90px"
											styleId="cflb">
											<html:option value=""></html:option>
											<html:options collection="cflbList" property="cflbdm"
												labelProperty="cflbmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />

									</th>
									<td align="left">
										${rs.xymc }
									</td>
									<th>
										<font color="red">*</font>��������
									</th>
									<td align="left">
										<html:select name="rs" property="cfyy" style="width:90px"
											styleId="cfyy">
											<html:option value=""></html:option>
											<html:options collection="cfyyList" property="cfyydm"
												labelProperty="cfyymc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th>
										רҵ
									</th>
									<td align="left">
										${rs.zymc }
									</td>
									<th>
										<font color="red">*</font>����ʱ��
									</th>
									<td align="left">
										<html:text name='rs' property="cfsj" styleId="cfsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('cfsj','y-mm-dd');" />
									</td>
								</tr>
								<tr>
									<th>
										�༶
									</th>
									<td align="left">
										${rs.bjmc }
									</td>
									<th>
										<logic:equal value="11078" name="xxdm">
									Υ�ʹ��־������ĺ�
								</logic:equal>
										<logic:notEqual value="11078" name="xxdm">
									<font color="red">*</font>�����ĺ�
								</logic:notEqual>
									</th>
									<td align="left">
										<html:text name='rs' property="cfwh" styleId="cfwh"
											onclick="getwh('cfwh');" maxlength="30"/>
									</td>
								</tr>
								<tr>
									<th>
										���ߴ���ʱ��
									</th>
									<td align="left">
										<html:text name='rs' property="cxsj" styleId="cxsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('cxsj','y-mm-dd');" />
									</td>
									<th>
										���ߴ����ĺ�
									</th>
									<td align="left">
										<html:text name='rs' property="cxwh" styleId="cxwh"
											onclick="getwh('cxwh');" maxlength="30"/>
									</td>
								</tr>
								<tr>
									<th>
										���߽��
									</th>
									<td align="left">
										<%--							<html:text name='rs' property="ssjg" styleId="ssjg" />--%>
										<html:select name="rs" property="ssjg" style="width:100px"
											styleId="ssjg">
											<html:option value=""></html:option>
											<html:option value="�������"></html:option>
											<html:option value="���Ĵ���"></html:option>
											<html:option value="ά��ԭ����"></html:option>
											<html:option value="δ����"></html:option>
										</html:select>
									</td>


									<logic:equal value="1049701" name="xxdm">
										<th>
											�۷�
										</th>
										<td align="left">
											<html:text name='rs' property="kf" styleId="kf"
												style="width:90px" />
										</td>
									</logic:equal>
									<logic:equal value="yes" name="isCSMZ">
										<th>
											�������
										</th>
										<td align="left">
											<html:text name='rs' property="cxjg" styleId="cxjg"
												readonly="true" />
										</td>
									</logic:equal>
									<logic:notPresent name="isCSMZ">
										<logic:equal value="13022" name="xxdm">
											<th>
												���Ĵ��ֽ��
											</th>
											<td>
												<html:select name="rs" property="ggcflbdm"
													style="width:90px" styleId="ggcflbdm">
													<html:option value=""></html:option>
													<html:options collection="cflbList" property="cflbdm"
														labelProperty="cflbmc" />
												</html:select>
												<br />
												<font color="red">(���߽��Ϊ���Ĵ���ʱ�޸ĸ���)</font>
											</td>
										</logic:equal>
										<!-- �������� begin-->
										<logic:notEqual value="13022" name="xxdm">
											<th>
												�����ļ��ϴ�
											</th>
											<td align="left">
												<input type="file" name="uploadFile" style="width:240px"  value="" contenteditable="false"  />
											</td>
										</logic:notEqual>
										<!-- end -->
									</logic:notPresent>
								</tr>
								<logic:notEqual value="10827" name="xxdm">
									<logic:equal value="13022" name="xxdm">
										<tr>
											<th>
												���ʱ��
											</th>
											<td align="left">
												<input type="text" disabled="disabled" readonly="readonly"
													value="${rs.cxclsj }" />
											</td>
											<th>
												����ĺ�
											</th>
											<td align="left">
												<input type="text" disabled="disabled" readonly="readonly"
													value="${rs.cxclwh }" />
											</td>
										</tr>

										<tr>
											<th>
												������
											</th>
											<td colspan="3">
												<input type="text" disabled="disabled" readonly="readonly"
													value="${rs.cxjg }" />
											</td>
										</tr>
									</logic:equal>
								</logic:notEqual>
								<!-- �㽭��  begin-->
								<logic:equal value="10338" name="xxdm">
									<logic:equal value="yes" name="zjlgLxck">
										<tr>
											<th>
												��У�쿴���ʱ��
											</th>
											<td align="left">
												${rs.cxclsj }
											</td>
											<th>
												��У�쿴����ĺ�
											</th>
											<td align="left">
												${rs.cxclwh }
											</td>
										</tr>
										<tr>
											<th>
												��У�쿴������
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<td align="right">

											</td>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- end -->
								<!-- ������  begin-->
								<logic:equal value="11062" name="xxdm">
									<logic:equal value="yes" name="xmlgLxck">
										<tr>
											<th>
												��У�쿴���ʱ��
											</th>
											<td align="left">
												${rs.cxclsj }
											</td>
											<th>
												��У�쿴����ĺ�
											</th>
											<td align="left">
												${rs.cxclwh }
											</td>
										</tr>
										<tr>
											<th>
												��У�쿴������
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<td align="right">

											</td>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- end -->
								<!-- �㽭��ý  begin-->
								<logic:equal value="11647" name="xxdm">
									<logic:equal value="yes" name="lxck">
										<tr>
											<th>
												��У�쿴���ʱ��
											</th>
											<td align="left">
												${rs.cxclsj }
											</td>
											<th>
												��У�쿴����ĺ�
											</th>
											<td align="left">
												${rs.cxclwh }
											</td>
										</tr>
										<tr>
											<th>
												��У�쿴������
											</th>
											<td align="left">
												${rs.cxjg }
											</td>
											<td align="right">

											</td>
											<td align="left">

											</td>
										</tr>
									</logic:equal>
								</logic:equal>
								<!-- end -->
								<!-- ���������ⶼ��ʾ -->
								<logic:notEqual value="13022" name="xxdm">
									<logic:equal value="11078" name="xxdm">
										<tr>
											<th>
												ԭ(���߸���ǰ)
												<br />
												�������
											</th>
											<td align="left">
												<html:select name="rs" property="ycflb" style="width:100px"
													styleId="ycflb">
													<html:option value=""></html:option>
													<html:options collection="cflbList" property="cflbdm"
														labelProperty="cflbmc" />
												</html:select>
											</td>
											<th>
												<font color="red">*</font>�Ƿ��������
											</th>
											<td>
												<html:select property="sfjw" name="rs" style="width:90px">
													<html:option value="��">��</html:option>
													<html:option value="��">��</html:option>
												</html:select>
											</td>
										</tr>
									</logic:equal>
								</logic:notEqual>
								<tr align="left">
									<th>
										Υ��ʱ��
									</th>
									<td align="left">
										<html:text property="wjsj" name="rs" styleId="wjsj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('wjsj','y-mm-dd');"></html:text>
									</td>

									<!-- �������е��� -->
									<logic:equal value="12645" name="xxdm">
										<th>
											��������
										</th>
										<td align="left">
											<html:text property="cfnx" name="rs" styleId="cfnx"
												maxlength="20"></html:text>
										</td>
									</logic:equal>
									<logic:notEqual value="12645" name="xxdm">
										<th>

										</th>
										<td align="left">

										</td>
									</logic:notEqual>
								</tr>

								<!-- ���ݴ�ѧ������ -->
								<%--						<logic:equal value="11078" name="xxdm">--%>
								<tr>
									<th>
										ѧ������ȷ��
									</th>
									<td align="left">
										${rs.xsqr }
									</td>
									<th>
										ȷ��ʱ��
									</th>
									<td align="left">
										${rs.qrsj }
									</td>
								</tr>
								<!-- ���칤��ְҵ����ѧԺ������ -->
								<logic:equal value="12759" name="xxdm">
								<tr>
									<th>
										�Ƿ���
									</th>
									<td align="left">
										${rs.sfcx }
									</td>
									<th>
										��������
									</th>
									<td align="left">
										${rs.cxrq }
									</td>
								</tr>
								</logic:equal>
							<thead>
								<tr>
									<td align="right" colspan="4">
										<span>��ʷΥ�ʹ�����Ϣ &nbsp;&nbsp;<a  onclick="
											document.all.child4.style.display=(document.all.child4.style.display=='none')?'':'none';getStucfxx();"><font color="red">�鿴</font> </a>
										</span>
									</td>
								</tr>
							</thead>

							<tr>
								<td colspan="4">
									<div id="child4" style="display:none">
										<table width="100%" border="1" align="center" class="dateline">
											<thead>
												<tr>
													<td align="center" width="80px">
														ѧ��
													</td>
													<td align="center" width="110px">
														�������
													</td>
													<td align="center" width="110px">
														����ԭ��
													</td>
													<td align="center" width="80px">
														����ʱ��
													</td>
													<td align="center" width="110px">
														�����ĺ�
													</td>
													<td align="center" width="80px">
														Υ��ʱ��
													</td>
													<td align="center">
														Υ����ʵ
													</td>
												</tr>
											</thead>
											<!-- ������ͨ��DWR���е��õ� -->
											<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
										</table>
									</div>
								</td>
							</tr>

							<tr align="left">
								<th>
									<logic:equal value="10856" name="xxdm">
										<font color="red">*</font>
										<bean:message key="lable.xsgzyxpzxy" />���</logic:equal>
									<logic:notEqual value="10856" name="xxdm">
										<font color="red">*</font>Υ����ʵ</logic:notEqual>
									<br />
									<font color="red">(������2000����)</font>
								</th>
								<td colspan="3">
									<html:textarea name='rs' property='bz' style="width:500px"
										onkeyup="checkLen(this,2000)" rows='5' />
								</td>
							</tr>
						</table>
				</logic:notEmpty>
			</logic:notPresent>
			<logic:present name="showXbemy">
				<logic:equal value="showXbemy" name="showXbemy">
					<div class="title">
						<div class="title_img" id="title_m">
							<span id="tipFollow"></span>
						</div>
					</div>
					<logic:empty name="rs">
						<br />
						<br />
						<p align="center">
							�д�������
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<logic:equal name="rs" property="stuExists" value="no">
							<script>
							    alert("�������ѧ����Ч!");
							   </script>
						</logic:equal>
						<input type="hidden" id="doType" name="doType"
							value="<bean:write name="doType" scope="request"/>" />
						<input type="hidden" id="pkValue" name="pkValue"
							value="<bean:write name="pkValue" scope="request"/>" />
						<input type="hidden" id="disableEle" name="disableEle"
							value="xm-xb-xy-nj-zy-bj" />
						<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
						<input type="hidden" id="getStuInfo" name="getStuInfo"
							value="xm-xb-xymc-nj-zymc-bjmc" />
						<input type="hidden" id="url" name="url" value="/sjcz/wjcfb.jsp" />
						<input type="hidden" id="clwh" name="clwh"
							value="<bean:write name="clwh"/>" />
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td colspan="5" align="center">
										Υ�ʹ�����Ϣά��
									</td>
								</tr>
							</thead>
							<tr>
								<td align="right">
									<font color="red">*</font>ѧ��
								</td>
								<td align="left">
									<html:text name='rs' property="xh" readonly="true" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										ѡ��
									</button>
								</td>
								<td align="right">
									<font color="red">*</font>���
								</td>
								<td align="left">
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<td rowspan="5" width="15%">
									<img align="center" border="0"
										style="height:133px;width:100px;"
										src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg"
										id="pic"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									����
								</td>
								<td align="left">
									<html:text name='rs' property="xm" styleId="xm" />
								</td>
								<td align="right">
									<font color="red">*</font>ѧ��
								</td>
								<td align="left">
									<html:select name="rs" property="xn" style="width:90px"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<html:text name='rs' property="xb" styleId="xb" />
								</td>
								<td align="right">
									<font color="red">*</font>ѧ��
								</td>
								<td align="left">
									<html:select name="rs" property="xq" style="width:90px"
										styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									�꼶
								</td>
								<td align="left">
									<html:text name='rs' property="nj" styleId="nj" />
								</td>
								<td align="right">
									�������
								</td>
								<td align="left">
									<html:select name="rs" property="cflb" style="width:90px"
										styleId="cflb">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="cflbdm"
											labelProperty="cflbmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />
									
								</td>
								<td align="left">
									<html:text name='rs' property="xymc" styleId="xy" />
								</td>
								<td align="right">
									��������
								</td>
								<td align="left">
									<html:select name="rs" property="cfyy" style="width:90px"
										styleId="cfyy">
										<html:option value=""></html:option>
										<html:options collection="cfyyList" property="cfyydm"
											labelProperty="cfyymc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									רҵ
								</td>
								<td align="left">
									<html:text name='rs' property="zymc" styleId="zy" />
								</td>
								<td align="right">
									<font color="red">*</font>����ʱ��
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfsj" styleId="cfsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cfsj','y-mm-dd');" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�༶
								</td>
								<td align="left">
									<html:text name='rs' property="bjmc" styleId="bj" />
								</td>
								<td align="right">
									<font color="red">*</font>�����ĺ�
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cfwh" styleId="cfwh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���ʱ��
								</td>
								<td align="left">
									<html:text name='rs' property="cxsj" styleId="cxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cxsj','y-mm-dd');" />
								</td>
								<td align="right">
									����ĺ�
								</td>
								<td align="left" colspan="2">
									<html:text name='rs' property="cxwh" styleId="cxwh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���߽��
								</td>
								<td align="left">
									<html:text name='rs' property="ssjg" styleId="ssjg" />
								</td>
								<td align="right">
									Υ��ʱ��
								</td>
								<td colspan="2" align="left">
									<input type="text" name="wjsj" id="wjsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('wjsj','y-mm-dd');"
										value="<logic:present name="rs"><bean:write name="rs" property="wjsj"/></logic:present>"/>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									Υ������
								</td>
								<td colspan="4">
									<html:textarea name='rs' property='bz' style="width:99%"
										rows='5' />
								</td>
							</tr>
						</table>
						<div class="buttontool" align="center">
							<%--					<button type="button" class="button2" onclick="dataCanModi(true)"--%>
							<%--						style="width:80px" id="buttonModi">--%>
							<%--						�� ��--%>
							<%--					</button>--%>
							
							<button type="button" class="button2"
								onclick="if(checkXnNd('xn','nd'))dataDoSave11('xn-xq-xh-cfsj-cfwh');"
								style="width:80px" id="buttonSave">
								�� ��
							</button>
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
								id="buttonClose">
								�� ��
							</button>
						</div>
					</logic:notEmpty>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
