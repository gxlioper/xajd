<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getRcswDAO.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript">	
	function bjly(num){
		var id = "hfnr"+num;
		if($(id)){
			$(id).readOnly = false;
			$(id).focus();
		}
	}

	function bjbc(num,hfid){
		var id = "hfnr"+num;
		if($(id)){
			if( !$(id).readOnly){
				if($(id).value == ""){
					alert("�ظ����ݲ���Ϊ�գ���ȷ��");
					return false;
				}
				if(confirm("ȷ���޸Ĵ����ԣ�")){
					$("bjlyxx").value = $(id).value;
					$("id").value = hfid
					showTips('���������У���ȴ�......');
					saveUpdate('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=edit','');
				}
			}
		}
	}
	
	function setPj(num,value){
		var id = "pj"+num;
		if($(id)){
			$(id).value = value;
		}
	}
	
	function qdpj(num,hfid){

	dwr.engine.setAsync(false);	
	
	var id = "pj"+ num;
	var tableName="rcsw_lyhfb";					
	var colList =["hfpj"];		
	var value =[$(id).value];						
	var pk = "id";					
	var pkValue = hfid;					
	
	getRcswDAO.updateRcswDwr(tableName, pk, pkValue,colList,value,function(data){					
		if(data){
			alert("���۳ɹ�");
		}
	});		

	dwr.engine.setAsync(true);			

	}
	</script>
	</head>
	<body onload="">

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/gzdxRcsw">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjlyxx" id="bjlyxx" value="" />
			<input type="hidden" name="id" id="id" value="" />
			<logic:notEmpty name="rsList">
				<logic:iterate name="rsList" id="s" indexId="index">
					<input type="hidden" name="pj${index }" id="pj${index }" value="" />
				</logic:iterate>
			</logic:notEmpty>


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="16%">
								������
							</th>
							<td width="34%">
								<html:hidden name="rs" property="lyr" />
								${rs.xm }
							</td>
							<th width="16%">
								����
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td width="34%">
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								����רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								���ڰ༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:hidden name="rs" property="lylx" />
								${rs.lxmc }
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								<html:hidden name="rs" property="lysj" />
								${rs.sj }
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<html:text name="rs" property="lymc" style="width: 100%"
									maxlength="50" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="lynr"
									style="width: 600px;height: 150px" readonly="true" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button"
											onclick="saveUpdate('/xgxt/gzdxRcsw.do?method=xslyUpdate&doType=save','hfnr')">
											�� ��
										</button>
										<button type="button" onclick="czData('hfnr')">
											�� ��
										</button>
									</logic:notEqual>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ظ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="formbox">
									<logic:empty name="rsList">
										<font color="red">�޻ظ�</font>
									</logic:empty>

									<logic:notEmpty name="rsList">
										<table summary="" class="dateline" width="100%">
											<logic:iterate name="rsList" id="s" indexId="index">
												<tr>
													<th width="17.9999999%">
														�ظ���
													</th>
													<td width="30%">
														${s.xm }
													</td>
													<th width="20%">
														�ظ�ʱ��
													</th>
													<td>
														${s.sj }
													</td>
												</tr>
												<tr>
													<th>
														�ظ�����
													</th>
													<td colspan="3">
														<textarea rows="5" cols="" style="width : 600px"
															id="hfnr${index }" readonly="readonly" type="_moz">${s.hfnr }</textarea>
														<br />
														<!-- ѧ���û� -->
														<logic:equal name="userType" value="stu">
															<logic:empty name="lypjList">
													����δά��
												</logic:empty>
															<logic:notEmpty name="lypjList">
																<logic:iterate name="lypjList" id="x" indexId="num">
																	<logic:notEqual name="num" value="0">
																		<logic:equal name="x" property="dm" value="${s.hfpj}">
																			<input type="radio" id="checkVal"
																				name="checkVal${index }" value="${x.dm }"
																				onclick="setPj('${index }',this.value)"
																				checked="checked" />
																		</logic:equal>
																		<logic:notEqual name="x" property="dm"
																			value="${s.hfpj}">
																			<input type="radio" id="checkVal"
																				name="checkVal${index }" value="${x.dm }"
																				onclick="setPj('${index }',this.value)" />
																		</logic:notEqual>
															${x.mc }&nbsp;&nbsp;
														</logic:notEqual>
																</logic:iterate>
															</logic:notEmpty>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button type="button" onclick="qdpj('${index }','${s.id }')" style="width: 60px;height: 20px;">����</button>
														</logic:equal>
														<!-- ��ѧ���û� -->
														<logic:notEqual name="userType" value="stu">
															<logic:equal name="userName" value="${s.hfr }">
													&nbsp;&nbsp;<a onclick="bjly(${index })"
																	style="cursor:hand;"><font color="bule">��
																		&nbsp;��</font> </a>
													&nbsp;&nbsp;<a onclick="bjbc('${index }','${s.id }')"
																	style="cursor:hand;"><font color="bule">��&nbsp;&nbsp;��</font>
																</a>
															</logic:equal>
														</logic:notEqual>
													</td>
												</tr>
											</logic:iterate>
										</table>
										<!--��ҳ��ʾ-->
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tbody>
					<logic:notEqual name="doType" value="view">
						<thead>
							<tr>
								<td colspan="4" align="center">
									�ظ�����
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="16%">
									<font color="red">*</font>�ظ�����
									<br />
									<font color="red">(������500��)</font>
								</th>
								<td colspan="3">
									<html:textarea name="rs" property="hfnr"
										style="width:600px;height: 150px" onblur="chLeng(this,500)" />
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
				</table>
			</div>


			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
