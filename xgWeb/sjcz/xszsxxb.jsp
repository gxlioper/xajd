<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
			<script type='text/javascript'
			src='/xgxt/dwr/interface/modiDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body onload="checkWinType();sslxLoad()">
		
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ס����Ϣά��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("��ѧ��������,�������ѧ����Ч!");
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
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="/sjcz/xszsxxb.jsp" />
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="5">
							<span>ס����Ϣά��</span>
						</th>
					</tr>
				</thead>
				<tbody>		
						<tr>
							<th align="right" width="15%">
								<font color="red">*</font>ѧ��
							</th>
							<td align="left" width="15%">
								<logic:notEqual value="add" name="doType">
									<logic:equal value="10491" name="xxdm">
										<html:text name='rs' property="xh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</logic:equal>
									<logic:notEqual value="10491" name="xxdm">
										<html:text name='rs' property="xh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="add" name="doType">
									<logic:equal value="10491" name="xxdm">
									<html:text name='rs' property="xh" readonly="true" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</logic:equal>
									<logic:notEqual value="10491" name="xxdm">
										<html:text name='rs' property="xh" readonly="" onblur="dctStuXh()"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</logic:notEqual>
									<logic:empty name="xslx">
										<button type="button" class="btn_01" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											id="buttonFindStu">
											ѡ��
										</button>
									</logic:empty>
								</logic:equal>
							</td>
							<th align="right" width="20%">
								<font color="red">*</font>��������
							</th>
							<td align="left">
								<html:select name="rs" property="sslx" style="width:150px"
									styleId="sslx" onchange="loadQscw();" >
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="yfpss">�ѻ�������(����<bean:message key="lable.xsgzyxpzxy" />)</html:option>
									<html:option value="wfpss">δ��������</html:option>
								</html:select>
							</td>
							
								<%--�ǽ�����Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
								  <td align="left" width="15%" rowspan="6">
										<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
												border="0" align="absmiddle" style="width:140;height:160" />
								  </td>
								<%--������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
								<logic:equal value="13108" name="xxdm">
									<td align="left" width="15%" rowspan="6">
										<img alt="�޷���ʾ" height="100" width="75" border="1"
											align="right"
											src="/xgxt/pictures/<bean:write name="rs" property="sfzh" />.jpg"></img>
									</td>
								</logic:equal>
							
						</tr>
						<tr>
							<th align="right">
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
							</td>
							<th align="right">
								<font color="red">*</font>�����
							</th>
							<td align="left">
								<html:select name="rs" property="ssbh" styleId="ssbh"
									onchange="loadCwxx()" >
									<html:option value=""></html:option>
									<html:options collection="ssList" property="dm"
										labelProperty="mc" />
								</html:select>
						
								<logic:notEmpty name="rs" property="sfbz">
								<br>&nbsp;&nbsp;�շѱ�׼<bean:write name="rs"
										property="sfbz" />
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
							<th align="right">
								<font color="red">*</font>��λ��
							</th>
							<td align="left">
								<html:select name="rs" property="cwh" styleId="cwh"
									style="width:150px">
									<html:options collection="cwhList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
							</td>
							<th align="right">
								��סʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd','aa');"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
							</td>
							<%--                            <td align="right">--%>
							<%--								�˷�ʱ��--%>
							<%--							</td>--%>
							<%--							<td align="left">--%>
							<%--								<html:text name='rs' property="jzrq" styleId="jzrq"--%>
							<%--									onblur="dateFormatChg(this)" style="cursor:hand;"--%>
							<%--									onclick="if($('rzrq').value.replace(' ','')==''){return false;}else{return showCalendar('jzrq','y-mm-dd');}" readonly="true" />--%>
							<%--							</td>--%>
							<logic:present name="rsOtherInfo">
								<th align="right">
									��Ԣ����Ա
								</th>
								<td align="left" >
									${rsGyFdy.xm }
								</td>
							</logic:present>
							<logic:notPresent name="rsOtherInfo">
								<logic:equal name="xxdm" value="10690">
									<th align="right">
										���ҳ�:
									</th>
									<td align="left" >
										${qsz }
									</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10690">
								<td align="right">

								</td>
								<td align="left" >

								</td>
								</logic:notEqual>
							</logic:notPresent>
						</tr>
						<logic:equal value="view" name="doType">
							<logic:present name="showXbemy">
								<tr>
									<th align="right">
										���֤��
									</th>
									<td align="left">
										<html:text name='rs' property="sfzh" styleId="sfzh" />
									</td>
									<th align="right">
										��Դ��
									</th>
									<td align="left">
										<html:text name='rs' property="sydmc" styleId="sydmc"
											readonly="true" />
									</td>
								</tr>
								<tr>
									<th align="right">
										��ͥסַ
									</th>
									<td align="left">
										<html:text name='rs' property="jtdz" styleId="jtdz" />
									</td>
									<th align="right">
										���˵绰
									</th>
									<td align="left">
										<html:text name='rs' property="sjhm" styleId="sjhm"
											readonly="true" />
									</td>
								</tr>
								<tr>
									<th align="right">
										��ͥ�绰
									</th>
									<td align="left">
										<html:text name='rs' property="jtdh" styleId="jtdh" />
									</td>
									<th align="right">
										������ò
									</th>
									<td align="left">
										<html:text name='rs' property="zzmmmc" styleId="zzmmmc"
											readonly="true" />
									</td>
								</tr>
							</logic:present>
						</logic:equal>
						<tr>
							<th align="right">
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
							</td>
							<logic:present name="showhzy">
								<!-- ����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
								<th align="right">
									ס�޷�
								</th>
								<td align="left">
									<html:text name='rs' property="zsf" styleId="zsf"
										maxlength="10" onblur="chkInput(this,event)" />
								</td>
							</logic:present>

							<logic:notPresent name="showhzy">

								<logic:present name="showlczrr">
									<!-- �㽭��ѧ������<bean:message key="lable.xsgzyxpzxy" /> -->
									<th align="right">
										��¥¥��
									</th>
									<td align="left">
										<bean:write name="rsLzCz" property="lz" />
									</td>
								</logic:present>

								<logic:notPresent name="showlczrr">
									<logic:present name="rsOtherInfo">
										<th align="right">
											¥��
										</th>
										<td align="left">
											${rsLcqsz.lz }
										</td>
									</logic:present>
									<logic:notPresent name="rsOtherInfo">
										<td align="right">

										</td>
										<td align="left" >

										</td>
									</logic:notPresent>
								</logic:notPresent>
							</logic:notPresent>
						</tr>
						<tr>
							<th align="right">
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
							</td>
							<logic:present name="showhzy">
								<th align="right">
									���ӷ�
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="dsjssf" styleId="dsjssf"
										maxlength="10" onblur="chkInput(this,event)" />
								</td>
							</logic:present>
							<logic:notPresent name="showhzy">
								<logic:present name="showlczrr">
									<th align="right">
										��¥��㳤
									</th>
									<td align="left" colspan="2">
										<bean:write name="rsLzCz" property="cz" />
									</td>
								</logic:present>
								<logic:notPresent name="showlczrr">
									<logic:present name="rsOtherInfo">
										<th align="right">
											�㳤
										</th>
										<td align="left" colspan="2">
											${rsLcqsz.cz }
										</td>
									</logic:present>
									<logic:notPresent name="rsOtherInfo">
										<td align="right">

										</td>
										<td align="left" colspan="2">

										</td>
									</logic:notPresent>
								</logic:notPresent>
							</logic:notPresent>
						</tr>
						<logic:present name="showzjcmxy">
							<jsp:include flush="true" page="/sjcz/include/zjcmZsjl.jsp"></jsp:include>
							<jsp:include flush="true" page="/sjcz/include/zjcmWsjc.jsp"></jsp:include>
						</logic:present>
						<logic:present name="rsOtherInfo">
							<tr>
								<td align="right">

								</td>
								<td align="left">

								</td>
								<th align="right">
									���ҳ�
								</th>
								<td align="left" colspan="2">
									${rsLcqsz.qsz }
								</td>
							</tr>
						</logic:present>
						<logic:present name="showlczrr">
							<!-- ������ -->
							<tr>
								<td align="right">

								</td>
								<td align="left">

								</td>
								<th align="right">
									��������ϵ��
								</th>
								<td align="left" colspan="2">
									<bean:write name="rslxr" property="lxr" />
								</td>
							</tr>
						</logic:present>
						<logic:present name="showlczrr">
							<!-- ������ -->
							<tr>
								<td align="right">

								</td>
								<td align="left">

								</td>
								<th align="right">
									��Ԣ����Ա
								</th>
								<td align="left" colspan="2">
									<bean:write name="gyfdyxx" property="cy" />
								</td>
							</tr>
						</logic:present>
						<logic:present name="showhzy">
							<tr align="left">
								<td align="right">
								</td>
								<td align="left">
								</td>
								<th align="right">
									��Ԣ���
								</th>
								<td align="left" colspan="2">
									<html:text name='rs' property="gyqk" styleId="gyqk" />
								</td>
							</tr>
						</logic:present>
						<tr align="left">
							<th align="right">
								��ע<br/>
								<font color="red">(���Ƴ���100������)</font>
							</th>
							<td colspan="5">
								<html:textarea name='rs' property='bz' style="width:600px"
									rows='5' onblur="chLeng(this,100)"/>
							</td>
						</tr>
					</table>
				<logic:present name="rsOtherInfo">
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td>
									<div id="main1" style="color:blue;cursor:hand" s
										onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
										<div align="center">
											<strong>���������Ϣ</strong>
										</div>
									</div>
								</td>
							</tr>
						</thead>
						<tr>
							<td>
								<div id="child1" style="display:none">
									<table width="100%" class="tbstyle">
										<tr align="center" bgcolor="#CCCCCC" >
											<td style="font-size: 14px">
												���������������ʷ���
											</td>
										</tr>
										<logic:empty name="rsWsjc">
											<tr align="center">
												<td align="center">
													δ�ҵ��κμ�¼��
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="rsWsjc">
											<tr>
												<td>
													<table width="100%" id="rsTable" class="tbstyle">
														<thead>
															<tr align="center" style="cursor:hand">
																<td>
																	ѧ��
																</td>
																<td>
																	ѧ��
																</td>
																<td>
																	�����ȼ�
																</td>
																<td>
																	�������
																</td>
															</tr>
														</thead>
														<logic:iterate name="rsWsjc" id="s">
															<tr align="center">
																<logic:iterate id="v" name="s">
																	<td>
																		<bean:write name="v" />
																	</td>
																</logic:iterate>
															</tr>
														</logic:iterate>

													</table>
												</td>
											</tr>
										</logic:notEmpty>
									</table>
									<table width="100%" class="tbstyle">
										<tr align="center" bgcolor="#CCCCCC">
											<td style="font-size: 14px">
												�ٻ��Ǽ�����������ʷ��¼
											</td>
										</tr>
										<logic:empty name="rsXjwmqs">
											<tr align="center">
												<td align="center">
													δ�ҵ��κμ�¼��
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="rsXjwmqs">
											<tr>
												<td>
													<table width="100%" id="rsTable" class="tbstyle">
														<thead>
															<tr align="center" style="cursor:hand">
																<td>
																	ѧ��
																</td>
																<td>
																	ѧ��
																</td>
																<td>
																	�ȼ�
																</td>
															</tr>
														</thead>
														<logic:iterate name="rsXjwmqs" id="s">
															<tr align="center">
																<logic:iterate id="v" name="s">
																	<td>
																		<bean:write name="v" />
																	</td>
																</logic:iterate>
															</tr>
														</logic:iterate>
													</table>
												</td>
											</tr>
										</logic:notEmpty>
									</table>
									<table width="100%" class="tbstyle">
										<tr align="center" bgcolor="#CCCCCC"> 
											<td style="font-size: 14px">
												���������ʷ��¼
											</td>
										</tr>
										<logic:empty name="rsZsjl">
											<tr align="center">
												<td align="center">
													δ�ҵ��κμ�¼��
												</td>
											</tr>
										</logic:empty>
										<logic:notEmpty name="rsZsjl">
											<tr>
												<td>
													<table width="100%" id="rsTable" class="tbstyle">
														<thead>
															<tr align="center" style="cursor:hand">
																<td>
																	ѧ��
																</td>
																<td>
																	ѧ��
																</td>
																<td>
																	Υ��
																</td>
																<td>
																	�۷�
																</td>
																<td>
																	����
																</td>
																<td>
																	Υ��ʱ��ס����
																</td>
															</tr>
														</thead>

														<logic:iterate name="rsZsjl" id="s">
															<tr align="center">
																<logic:iterate id="v" name="s">
																	<td>
																		<bean:write name="v" />
																	</td>
																</logic:iterate>
															</tr>
														</logic:iterate>
													</table>
												</td>
											</tr>
										</logic:notEmpty>
									</table>
								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</logic:present>
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
							<logic:notEqual value="view" name="doType">
								<button type="button" onclick="checkZfrq('xh-ssbh-cwh-sslx')" id="buttonSave">
									�� ��
								</button>
							</logic:notEqual>				
							<button type="button" onclick="window.close();return false;" id="buttonClose">
								�� ��
							</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</logic:notEmpty>
			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
		</html:form>
		<script type="text/javascript">
       function refresh(){
         var url = "/xgxt/modiData.do?realTable=";
         var doType = document.getElementById('doType').value;
         url +="xszsxxb";
         url +="&tableName=";
         url +="view_xszsxx";
         url +="&pk=";
         url +="xh";
         url +="&from=";
         url +="usingInfo";
         url +="&doType=";
         //url +="add";
         url +=doType;
         url +="&ssbh=";
         url +=$("ssbh").value
         url += "&pkValue=";
	     url +=$("pkValue").value;
         refreshForm(url);
       }
       function sslxLoad(){
           var userType = "";         
           if($("userType")){
              userType = $("userType").value;
           } 
           if(userType=="xy"){
               $("sslx").value = "yfpss";
               $("sslx").disabled = true;
           }
       }
       
       function loadCwxx(){
       		var ssbh=$("ssbh").value;
       		modiDAO.getCwList(ssbh,function(data){
       			DWRUtil.removeAllOptions($("cwh"));			
				DWRUtil.addOptions($("cwh"),data,"dm","mc");
       		});
       }
       
       function loadQscw(){
       		var sslx=$("sslx").value;
       		
       		modiDAO.getQsList(sslx,function(data){
       			DWRUtil.removeAllOptions($("ssbh"));			
				DWRUtil.addOptions($("ssbh"),data,"dm","mc");
				loadCwxx();
       		});
       		
       }
   </script>
		<logic:equal value="10628#" name="xxdm">
			<logic:equal value="add" name="doType">
				<logic:present name="rzsj">
					<script type="text/javascript">
				document.getElementById('rzrq').value='<bean:write name="rzsj"/>'
			</script>
				</logic:present>
			</logic:equal>
		</logic:equal>
	</body>
</html>
