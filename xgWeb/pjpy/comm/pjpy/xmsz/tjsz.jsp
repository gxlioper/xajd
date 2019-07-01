<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
		<script src="js/check.js"></script>
		<script>
			function setTjz(checkboxName,tjzid){
				var knjb = jQuery("input[name='"+checkboxName+"']");
				var temp = '';
				
				for (var i = 0 ; i < knjb.length ; i++){
					if (knjb[i].checked){
						temp+=knjb[i].value;
						temp+=',';
					}
				}
				temp = temp.substring(0,temp.length-1);
				jQuery(tjzid).val(temp);
			}
			
			function setQtxz(kcxzId,khfsId,qtxzId){
				jQuery(qtxzId).val('kcxz:'+jQuery(kcxzId).val()+','+'khfs:'+jQuery(khfsId).val());
			}
			
			
			
			//��ʼ����������ǰ����Ŀ��������
			jQuery(function(){
				var tsgs = jQuery('input[name="tsgs"]');
				jQuery.each(tsgs,function(i,n){
					if (n.value == 'kns' || n.value == 'qzxm'){
						var tr = jQuery(tsgs[i]).parents("tr:first");//�ҵ����ڵ�tr
					 	var box = jQuery("input[type='checkbox']", tr);//�ҵ�tr�µ�checkbox
						var tjz = jQuery("input[name='tjz']", tr).val().split(',');//�ҵ�tr�µ�����ֵ
						for (var c = 0 ; c < box.length; c++){
							if (jQuery.inArray(box[c].value,tjz) >-1){
								box[c].checked=true;
							}
						}
					}
				})
			})
			
			//������������
			function saveTjsz(type){
				var tjNum = $("tjNum").value;
				
				for(var i=0;i<tjNum;i++){
					var tjgx_id = "tjgx_"+i;
					var tjz_id = "tjz_"+i;
					
					if($(tjgx_id) && $(tjgx_id).value == ""){
						alert("��"+(parseInt(i)+1)+"��������ϵΪ�գ���ȷ�ϣ�");
						return false;
					}
					
					if($(tjz_id) && $(tjz_id).value == ""){
						alert("��"+(parseInt(i)+1)+"������ֵΪ�գ���ȷ�ϣ�");
						return false;
					}
				}
				
				if(type == "all"){
					saveUpdate('pjpy_ty_tjsz.do?method=tjsz&doType=save','')
				}else{
					saveUpdate('pjpy_ty_tjsz.do?method=tjszUpdate&doType=save','')
				}
			}
		</script>
	</head>

	<body>
		<html:form action="/pjpy_ty_tjsz" method="post">
			<html:hidden property="pkValue"/>
			<input type="hidden" name="tjNum" id="tjNum" value="${tjNum }"/>
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:equal value="update" name="doType">
										<button type="button" name="�� ��" onclick="saveTjsz('update')">
											�� ��
										</button>
									</logic:equal>
									<logic:notEqual value="update" name="doType">
										<button type="button" name="��һ��" onclick="saveUpdate('pjpy_ty_tjsz.do?method=tjxz','')">
											��һ��
										</button>
										<button type="button" name="�� ��" onclick="saveTjsz('all')">
											�� ��
										</button>
									</logic:notEqual>
									<button type="button" name="�� ��" onclick="if(confirm('��ҳ�����õ���Ŀ������δ���棬��ȷ��Ҫ�ر���?')){window.close();}">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								����ѧ��
							</th>
							<td>
								<html:select property="pjxn" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								����ѧ��
							</th>
							<td>
								<html:select property="pjxq" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td>
								<html:select property="pjnd" style="width:200px" name="pjxtsz"
									disabled="true">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
							<th>
								��Ŀ����
							</th>
							<td>
								<html:hidden property="xmdm"/>
								<html:select property="xmdm" style="width:200px" disabled="true">
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<table class="dateline" style="width:100%">
									<thead>
										<tr>
											<td>
												��������
											</td>
											<td>
												��ϵ<font color="red">*</font>
											</td>
											<td>
												����ֵ<font color="red">*</font>
											</td>
											<td>
												��Χ<font color="red">*</font>
											</td>
<%--											<td>--%>
<%--												��������--%>
<%--											</td>--%>
										</tr>
									</thead>
									<tbody>
										<logic:iterate id="xmtj" name="xmtjList" indexId="index">
											<tr>
												<!-- ������ -->
												<td width="25%">
													<input type="hidden" name="tjdm" id="tjdm_${index }" value="${xmtj.tjdm }"/>
													${xmtj.tjmc }
												</td>
												<!-- ������ end-->
												
												<!-- ������ϵ -->
												<td width="25%">
													<logic:empty name="xmtj" property="tjvalue">
														<select id="tjgx_${index }"name="tjgx" style="width: 80px">
															<option></option>
															<logic:iterate name="gxList" id="gx">
																<option value="${gx.en }" <logic:equal name="xmtj" property="tjgx" value="${gx.en }">selected="selected"</logic:equal>>${gx.cn }</option>
															</logic:iterate>
														</select>
													</logic:empty>
													<logic:notEmpty name="xmtj" property="tjvalue">
														<input type="text" name="tjgx" id="tjgx_${index }" value="=" style="width: 80px" readonly="readonly"/>
													</logic:notEmpty>
												</td>
												<!-- ������ϵend -->
												
												<!-- ����ֵ -->
												<td width="25%">
													<logic:empty name="xmtj" property="tjvalue">
														<input type="text" name="tjz" id="tjz_${index }" 
															style="width: 80px" onkeyup="checkInputNum(this)" 
															maxlength="5" value="${xmtj.tjz }" />
														<logic:equal name="xmtj" property="tsgs" value="%">
															%
														</logic:equal>
													</logic:empty>
													<logic:notEmpty name="xmtj" property="tjvalue">
														<select id="tjz_${index }"name="tjz" style="width: 80px">
															<option></option>
															<logic:iterate name="xmtj" property="valueList" id="tjRs">
																<option value="${tjRs }"
																<logic:equal name="xmtj" property="tjz" value="${tjRs }">selected="selected"</logic:equal>
																>${tjRs }</option>
															</logic:iterate>
														</select>
													</logic:notEmpty>
												</td>
												<!-- ����ֵ end-->
												
												<!-- ������Χ -->
												<td width="25%">
													<select id="tjfw_${index }"name="tjfw" style="width: 80px">
														<option value="all">ȫ��</option>
														<logic:iterate name="tjfwList" id="tjfwRs">
															<option value="${tjfwRs.xydm }"
															<logic:equal name="xmtj" property="tjfw" value="${tjfwRs.xydm }">selected="selected"</logic:equal>
															>${tjfwRs.xymc }</option>
														</logic:iterate>
													</select>
													<input type="hidden" name="qtxz" id="qtxz_${index }"/>
												</td>
												<!-- ������Χ end-->
											</tr>
										</logic:iterate>
<%--										<logic:notEmpty name="xmtjInfo">--%>
<%--											<logic:iterate id="x" name="xmtjInfo" indexId="i">--%>
<%--												<tr id="${i }">--%>
<%--													<td>--%>
<%--														${x.tjmc }--%>
<%--														<html:hidden property="tjdm" value="${x.tjdm }"/>--%>
<%--														<input type="hidden" name="tsgs" value="${x.tsgs }"/>--%>
<%--													</td>--%>
<%--													<td>--%>
<%--														<logic:equal value="yes" name="x" property="tsgs" >--%>
<%--															<html:hidden property="tjgx" value="="/>--%>
<%--															<html:select property="tjgx" value="=" disabled="true">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="gxList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:equal>--%>
<%--														<logic:equal value="" name="x" property="tsgs" >--%>
<%--															<html:hidden property="tjgx" value="="/>--%>
<%--															<html:select property="tjgx" value="=" disabled="true">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="gxList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:equal>--%>
<%--														<logic:equal value="kns" name="x" property="tsgs" >--%>
<%--															<html:hidden property="tjgx" value="="/>--%>
<%--															<html:select property="tjgx" value="=" disabled="true">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="gxList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:equal>--%>
<%--														<logic:equal value="qzxm" name="x" property="tsgs" >--%>
<%--															<html:hidden property="tjgx" value="="/>--%>
<%--															<html:select property="tjgx" value="=" disabled="true">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="gxList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:equal>--%>
<%--													--%>
<%--														<logic:notEqual value="yes" name="x" property="tsgs" >--%>
<%--														<logic:notEqual value="kns" name="x" property="tsgs" >--%>
<%--														<logic:notEqual value="qzxm" name="x" property="tsgs" >--%>
<%--														<logic:notEqual value="" name="x" property="tsgs" >--%>
<%--															<html:select property="tjgx" value="${x.gx }">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="gxList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:notEqual>--%>
<%--														</logic:notEqual>--%>
<%--														</logic:notEqual>--%>
<%--														</logic:notEqual>--%>
<%--													</td>--%>
<%--													<td>--%>
<%--														<!-- �����ʽ tsgs=null -->--%>
<%--														<logic:equal value="" name="x" property="tsgs" >--%>
<%--															<html:text property="tjz" maxlength="5" value="${x.tjz }" style="width:80px"></html:text>--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=yes -->--%>
<%--														<logic:equal value="yes" name="x" property="tsgs" >--%>
<%--															<html:select property="tjz" value="${x.tjz }" style="width:80px">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="isnotList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=num -->--%>
<%--														<logic:equal value="num" name="x" property="tsgs" >--%>
<%--															<html:text property="tjz" onkeyup="checkInputNum(this)" maxlength="5" value="${x.tjz }" style="width:80px"></html:text>--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=% -->--%>
<%--														<logic:equal value="%" name="x" property="tsgs" >--%>
<%--															<html:text property="tjz" onkeyup="checkInputNum(this)" maxlength="5" value="${x.tjz }" style="width:80px"></html:text>%--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=cj -->--%>
<%--														<logic:equal value="cj" name="x" property="tsgs" >--%>
<%--															<html:text property="tjz" onkeyup="checkInputNum(this)" maxlength="5" value="${x.tjz }" style="width:80px"></html:text>--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=fs -->--%>
<%--														<logic:equal value="fs" name="x" property="tsgs" >--%>
<%--															<html:text property="tjz" onkeyup="checkInputNum(this)" maxlength="5" value="${x.tjz }" style="width:80px"></html:text>--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=kns -->--%>
<%--														<logic:equal value="kns" name="x" property="tsgs" >--%>
<%--															<html:hidden property="tjz" styleId="tjz${i}" value="${x.tjz }"/>--%>
<%--															<logic:iterate id="k" name="knsjb">--%>
<%--																<input type="checkbox" name="knsjb${i }" value="${k.en }" onclick="setTjz('knsjb${i }','#tjz${i }');"/>${k.cn } <br/>--%>
<%--															</logic:iterate>--%>
<%--														</logic:equal>--%>
<%--														<!-- �����ʽ tsgs=qzxm -->--%>
<%--														<logic:equal value="qzxm" name="x" property="tsgs" >--%>
<%--															<html:hidden property="tjz" styleId="tjz${i}" value="${x.tjz }"/>--%>
<%--															<logic:iterate id="q" name="qzxmList">--%>
<%--																<input type="checkbox" name="qzxm${i }" value="${q.xmdm }" onclick="setTjz('qzxm${i }','#tjz${i }')"/>${q.xmmc } <br/>--%>
<%--															</logic:iterate>--%>
<%--														</logic:equal>--%>
<%--													</td>--%>
<%--													<td>--%>
<%--														<html:select property="tjfw" value="${x.tjfw }" styleClass="easyui-combobox" style="width:180px" >--%>
<%--															<html:option value="all">ȫ��</html:option>--%>
<%--															<html:options collection="tjfwList" property="xydm" labelProperty="xymc"/>--%>
<%--														</html:select>--%>
<%--													</td>--%>
<%--													<td>--%>
<%--														<html:hidden property="qtxz" styleId="qtxz${i }" value=""/>--%>
<%--														<!-- �����ʽ tsgs=cj -->--%>
<%--														<logic:equal value="cj" name="x" property="tsgs" >--%>
<%--															�γ�����--%>
<%--															<html:select property="kcxz" styleId="kcxz${i}" onchange="setQtxz('#kcxz${i}','#khfs${i}','#qtxz${i}')" style="width:80px">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="kcxzList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--															<br/>--%>
<%--															���˷�ʽ--%>
<%--															<html:select property="khfs" styleId="khfs${i}" onchange="setQtxz('#kcxz${i}','#khfs${i}','#qtxz${i}')" style="width:80px">--%>
<%--																<html:option value=""></html:option>--%>
<%--																<html:options collection="khfsList" property="en" labelProperty="cn"/>--%>
<%--															</html:select>--%>
<%--														</logic:equal>--%>
<%--														<logic:notEqual value="cj" name="x" property="tsgs" >--%>
<%--															��--%>
<%--														</logic:notEqual>--%>
<%--													</td>--%>
<%--												</tr>--%>
<%--											</logic:iterate>--%>
<%--										</logic:notEmpty>--%>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<input type="hidden" id="message" value="${message }"/>
		<input type="hidden" id="doType" value="${message }"/>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
