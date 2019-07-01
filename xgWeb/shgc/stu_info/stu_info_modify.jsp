<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getXjydInfo.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
		function hiddenButton(){
			var xxdm = document.getElementById("xxdm").value;
			<%--���ս�����ҵѧԺ--%>
			if(xxdm=="10878"){
				document.getElementById("btn").style.display="none";
			}
		}
		
		function chxj(xj){
			var xxdm = $("xxdm").value;
			if(xxdm == "10491"){
				dwr.engine.setAsync(false);
				var objId = "xszt";
				if(xj == "��ѧ��"){
					getStuDetails.getXsztList(xj,function(data){
						if (data != null && typeof data == 'object') {
							DWRUtil.removeAllOptions(objId);
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					});
				}else{
					getStuDetails.getXsztList(xj,function(data){
						if (data != null && typeof data == 'object') {
							DWRUtil.removeAllOptions(objId);
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					});
				}
				dwr.engine.setAsync(true);
				$("xj").value=xj;
			}
		}
		
		
		function changeXjxx(){
			setVal("nj",val("t_ydhnj"));
			setVal("xy",val("t_ydhxydm"));
			setVal("zy",val("t_ydhzydm"));
			setVal("bj",val("t_ydhbjdm"));
			setVal("xz",val("t_ydhxz"));
			
			var xxdm = $("xxdm").value;
			//�ǵش�
			if( xxdm != "10491"){
				
				setVal("ydhxj",val("t_ydhxjztm"));
			}
		}
		//���ذ༶��������Ϣ
		function loadParentInfo(bjdm){
			getXjydInfo.queryBjParentInfo(bjdm,function(data){
				if(data != null){
					setVal('nj',data.nj == null ? "" : data.nj);
					setVal('xy',data.xydm == null ? "" : data.xydm);
					setVal('zy',data.zydm == null ? "" : data.zydm);
				}
			});
		}
		
		function dataCommit(){
			//���ʱ��
			var ydrq = val('ydrq');
			var ydjzrq = val('ydjzrq');
			ydrq = replaceChar(ydrq,'-','');
			ydjzrq = replaceChar(ydjzrq,'-','');
			if(ydjzrq != ""){
				if(toInt(ydrq)>toInt(ydjzrq)){
					alert("�춯��ֹ����Ӧ�����춯���ڣ�");
					return false;
				}
			}			
			checkNull('stu_status_different.do?doType=modify&type=');
		}
		
		function loadXjzt(){	
			var ydlbdm=$("ydlbdm").value;
			if($("xxdm").value=='10491'){
				getXjydInfo.getXjydInfo(ydlbdm,function(data){
					var result=data;	
					$("ydhxjztm").value=result;;
				});	
			}
		}
	</script>
	</head>
	<body onload="hiddenButton();loadXjzt()">
		<html:form action="/stu_status_different" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ���춯-ѧ���춯�޸�</a>
				</p>
			</div>
			<input type="hidden" name="url" id="url"
				value="/shgc/stu_info/stu_info_modify.jsp" />
			<input type="hidden" name="variable" id="variable" value="ydinfo" />
			<input type="hidden" name="redirect" id="redirect" value="true" />
			<input type="hidden" name="realTable" id="realTable"
				value="stu_archives" />
			<input type="hidden" id="read" value="" />
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" value="bks_xjydxx" id="rsTable" />
			<input type="hidden" value="bks_xjydxx" id="rstTable" />
			<input type="hidden" value="xh-ydxh-clwh-ydlbdm-ydrq-nj-xy-zy-bj"
				id="notnull" />
			<input type="hidden" value="${rs.ydhxydm}" name="ydhxydm" />
			<input type="hidden" value="${rs.ydhzydm}" name="ydhzydm" />
			<input type="hidden" value="${rs.ydhbjdm}" name="ydhbjdm" />
			<input type="hidden" value="${rs.ydhnj}" name="ydhnj" />
			<input type="hidden" value="${rs.ydhxz}" name="ydhxz" />
			<!--		<input type="hidden" value="${rs.ydhxjztm}" name="ydhxjztm" />-->
			<input type="hidden" name="zyV" id="zyV" value="zyV" />
			<input type="hidden" name="bjV" id="bjV" value="bjV" />
			<input type="hidden" name="xyV" id="xyV" value="xyV" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<input type="hidden" name="xj" id="xj" value="${xjzt}" />

			<div class="tab">
				<%--���ս�����ҵѧԺ--%>
				<logic:equal value="10878" name="xxdm">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ���춯</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>ѧ��
								</th>
								<td>
									${rs.xh}
								</td>
								<th>
									����
								</th>
								<td>
									${rs.xm}
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�춯���
								</th>
								<td>
									${rs.ydxh}
								</td>
								<th>
									<span class="red">*</span>�����ĺ�
								</th>
								<td>
									${rs.clwh}
								</td>
							</tr>
							<tr>
								<th>
									�춯ԭ��
									</h>
								<td>
									${rs.ydyy}
								</td>
								<th>
									�춯˵��
								</th>
								<td>
									${rs.ydsm}
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�춯���
								</th>
								<td>
									${rs.ydlbmc}
								</td>
								<th>
									<span class="red">*</span>�춯��
								</th>
								<td>
									${rs.ydrq}
								</td>
							</tr>
						</tbody>
					</table>
				</logic:equal>

				<%--�ǰ��ս�����ҵѧԺ>--%>
				<logic:notEqual value="10878" name="xxdm">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ���춯</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>ѧ��
								</th>
								<td>
									<logic:equal value="update" name="oper">
										<html:text name="rs" styleId="xh" property="xh"
											readonly="true" style="cursor:hand" />
									</logic:equal>
									<logic:equal value="add" name="oper">
										<html:text name="rs" property="xh"
											onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
										<button class="btn_01"
											onclick="showTopWin('/xgxt/stu_info.do?oper=<bean:write name="oper"/>',750,550);"
											id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text name="rs" property="xm" styleId="xm"
										style="cursor:hand" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�춯���
								</th>
								<td>
									<logic:equal value="update" name="oper">
										<html:text name="rs" property="ydxh" styleId="ydxh"
											readonly="true" style="cursor:hand" />
									</logic:equal>
									<logic:equal value="add" name="oper">
										<logic:notPresent name="ydxh">
											<html:text property="ydxh" styleId="ydxh" value="${rs.ydxh}" />(<bean:write
												name="rs" property="ydxh" />)
						</logic:notPresent>
										<logic:present name="ydxh">
											<html:text property="ydxh" styleId="ydxh" value="${ydxh}" />(<bean:write
												name="ydxh" />)							
						</logic:present>
									</logic:equal>

								</td>
								<th>
									<span class="red">*</span>�����ĺ�
								</th>
								<td>
									<html:text name="rs" property="clwh" styleId="clwh" />
								</td>
							</tr>
							<tr>
								<th>
									�춯ԭ��
								</th>
								<td>
									<html:text name="rs" property="ydyy" />
								</td>
								<th>
									�춯˵��
								</th>
								<td>
									<html:text name="rs" property="ydsm" />
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>�춯���
								</th>
								<td>
									<logic:equal value="student" name="user">
										<html:select name="rs" styleId="ydlbdm" property="ydlbdm"
											disabled="true" style="width:160px">
											<html:option value=""></html:option>
											<html:options name="rs" collection="ydlbList"
												property="ydlbdm" labelProperty="ydlbmc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="student" name="user">
										<html:select name="rs" styleId="ydlbdm" property="ydlbdm"
											style="width:160px"
											onchange="getYdlbInfo();chxj('��ѧ��');changeXjxx();loadXjzt()">
											<html:option value=""></html:option>
											<html:options name="rs" collection="ydlbList"
												property="ydlbdm" labelProperty="ydlbmc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									<span class="red">*</span>�춯����
								</th>
								<td>
									<html:text name="rs" property="ydrq" styleId="ydrq"
										onclick="return showCalendar('ydrq','y-mm-dd');" />
								</td>
							</tr>
							<tr>
								<th>
									�춯��ֹ����
								</th>
								<td>
									<html:text name="rs" property="ydjzrq" styleId="ydjzrq"
										onclick="return showCalendar('ydjzrq','y-mm-dd');" />
								</td>
								<th></th>
								<td>

								</td>
							</tr>
							<%--���ݴ�ѧ--%>
							<logic:equal value="11068" name="xxdm">
								<th>
									�Ƿ���У
								</th>
								<td colspan="3">
									<html:select property="sfzx" name="rs" styleId="sfzx">
										<html:option value="��У">��У</html:option>
										<html:option value="����У">����У</html:option>
									</html:select>
								</td>
							</logic:equal>
						</tbody>
					</table>
				</logic:notEqual>
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�춯ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="50%">
								<table class="formlist" style="width:100%;height:100%">
									<thead>
										<tr>
											<th colspan="2">
												<span>�춯ǰ��Ϣ</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th height="20px">
												�꼶
											</th>
											<td>
												<bean:write name="rs" property="ydqnj" />
												<input type="hidden" id="ydqnj" name="ydqnj"
													value="<bean:write name="rs" property="ydqnj" />" />
												<br />
											</td>
										</tr>
										<tr>
											<th height="20px">
												<bean:message key="lable.xsgzyxpzxy" />
											</th>
											<td>
												${rs.ydqxymc}
												<input type="hidden" id="ydqxymc" name="ydqxymc"
													value="${rs.ydqxymc}" />
												<br />
											</td>
										</tr>
										<tr>
											<th height="20px">
												רҵ
											</th>
											<td>
												${rs.ydqzymc}
												<input type="hidden" id="ydqzymc" name="ydqzymc"
													value="${rs.ydqzymc}" />
											</td>
										</tr>
										<tr>
											<th height="20px">
												�༶
											</th>
											<td>
												${rs.ydqbjmc}
												<input type="hidden" id="ydqbjmc" name="ydqbjmc"
													value="${rs.ydqbjmc}" />
											</td>
										</tr>
										<tr>
											<th height="20px">
												ѧ��
											</th>
											<td>
												${rs.ydqxz}
												<input type="hidden" id="ydqxz" name="ydqxz"
													value="${rs.ydqxz}" />
											</td>
										</tr>
										<tr>
											<th height="20px">
												ѧ��״̬
											</th>
											<td>
												<html:select property="ydqxjztm" name="rs" styleId="ydqxj"
													disabled="true">
													<html:option value=""></html:option>
													<html:options collection="xjztList" property="zxdmmc"
														labelProperty="zxdmmc" />
													<%--�������ϴ�ѧ--%>
												</html:select>
											</td>
										</tr>
									</tbody>
								</table>
							</td>

							<td>
								<table class="formlist">
									<thead>
										<tr>
											<th colspan="2">
												<span>�춯����Ϣ</span>
											</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>
												<span class="red">*</span>�꼶
											</th>
											<td>
												<html:select disabled="true" name="rs" property="ydhnj"
													styleId="nj" style="width:180px"
													onchange="initZyList();initBjList();">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
												<input type="hidden" name="t_ydhnj" value="${rs.ydhnj}" />
											</td>
										</tr>
										<tr>
											<th>
												<span class="red">*</span>
												<bean:message key="lable.xsgzyxpzxy" />
											</th>
											<td>
												<html:select disabled="true" styleId="xy" name="rs"
													property="ydhxydm" style="width:180px"
													onchange="initZyList();initBjList();">
													<html:option value=""></html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
												<input type="hidden" name="t_ydhxydm" value="${rs.ydhxydm}" />
											</td>
										</tr>
										<tr>
											<th>
												<span class="red">*</span>רҵ
											</th>
											<td>
												<html:select disabled="true" name="rs" property="ydhzydm"
													styleId="zy" style="width:180px" onchange="initBjList();">
													<html:option value=""></html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
												<input type="hidden" name="t_ydhzydm" value="${rs.ydhzydm}" />
											</td>
										</tr>
										<tr>
											<th>
												<span class="red">*</span>�༶
											</th>
											<td>
												<html:select disabled="true" name="rs" property="ydhbjdm"
													styleId="bj" style="width:180px"
													onchange="loadParentInfo(this.value)">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
												<input type="hidden" name="t_ydhbjdm" value="${rs.ydhbjdm}" />
											</td>
										</tr>
										<tr>
											<th>
												ѧ��
											</th>
											<td>
												<html:text disabled="true" name="rs" styleId="xz"
													property="ydhxz"
													onkeyup="value=value.replace(/[^\d]/g,'') " />
												<input type="hidden" name="t_ydhxz" value="${rs.ydhxz}" />
											</td>
										</tr>
										<%--�й��ش�--%>
										<logic:equal value="10491" name="xxdm">
											<tr>
												<th>
													ѧ��״̬
													</h>
												<td>
													<html:select property="xszt" styleId="xszt" style="">
														<html:options collection="xsztList" property="dm"
															labelProperty="mc" />
													</html:select>
													<input type="hidden" name="ydhxjztm" id="ydhxjztm"
														value="${rs.ydhxjztm}" />
											</tr>
										</logic:equal>
										<logic:notEqual value="10491" name="xxdm">
											<tr>
												<th>
													ѧ��״̬
												</th>
												<td>
													<html:select property="ydhxjztm" name="rs" styleId="ydhxj">
														<html:option value=""></html:option>
														<html:options collection="xjztList" property="zxdmmc"
															labelProperty="zxdmmc" />
													</html:select>
													<input type="hidden" name="t_ydhxjztm"
														value="${rs.ydhxjztm}" />
												</td>
											</tr>
										</logic:notEqual>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<%--�人����ѧ--%>
									<logic:notEqual value="10497" name="xxdm">
										<%--�㽭����ְҵ����ѧԺ--%>
										<logic:notEqual value="12861" name="xxdm">
											<logic:notEqual value="stu" name="userType">
												<logic:notEqual value="view" name="userOper">
													<button onclick="dataCommit()" style="width:80px">
														�� ��
													</button>
												</logic:notEqual>
											</logic:notEqual>
						&nbsp;&nbsp;&nbsp;
						<button onclick="Close();return false;" style="width:80px">
												�ر�
											</button>
										</logic:notEqual>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<logic:notEqual value="student" name="user">
					<script>
				getYdlbInfo()
			</script>
				</logic:notEqual>
				<logic:notEmpty name="result" scope="request">
					<logic:equal value="true" name="result" scope="request">
						<script>
				alert("�����ɹ���");
				Close();					
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}		
			</script>
					</logic:equal>
					<logic:equal value="false" name="result" scope="request">
						<script>
				Close();					
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
	</body>
</html>
