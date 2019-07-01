<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/menuManage.js'></script>
		<script type="text/javascript">
			function changeBtnSfqy(obj){
				var sfqy;
				var text = obj.value;
				
				if ("Ӧ��"==text) {
					obj.value = "ȡ��";
					sfqy = "��";
				} else{
					obj.value = "Ӧ��";
					sfqy = "��";
				}
				
				menuManage.changeBtnSfqy($('path').value,obj.id,sfqy,function(data){
					if (data){
						alert(text+"�ɹ�");
					} else {
						alert(text+"ʧ��");
					}
				})
				
			}
			
			function setChildList(gnmkdm,id){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,[{dm:"",mc:""}],"dm","mc")
			
				if ("" != gnmkdm){
					menuManage.getChildGnmkList(gnmkdm,function(data){
						DWRUtil.addOptions(id,data,"gnmkdm","gnmkmc");
						$('ejgnmkdm').value = $('tempEjgnmkdm').value;
					});
				} 
				
				if (id=="ejgnmkdm" && $("gnmkdm")){
					setChildList("","gnmkdm");
				}
			}
			
			function changeGnmkdm(text){
				if ("" != text){
					menuManage.getNextGnmkdm(text,function(data){
						$('gnmkdm').value = data;
					})
				} else {
					$('gnmkdm').value = $('pkValue').value;
				}
			}
		</script>
	</head>
	<body onload="<logic:equal value="3" name="rs" property="cdjb">setChildList('${rs.yjgnmkdm }','ejgnmkdm')</logic:equal>">
		<html:form action="/menuManage" method="post">
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			<input type="hidden" id="path" name="path" value="${rs.dyym }" />
			<input type="hidden" id="tempEjgnmkdm"  value="${rs.ejgnmkdm }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>���ܲ˵�ά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="�ύ"
											onclick="saveUpdate('/xgxt/menuManage.do?method=menuUpdate&doType=save','save_gnmkmc')">
											����
										</button>
									</logic:notEqual>
									<button type="button" name="�ر�" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<logic:equal value="3" name="rs" property="cdjb">
						<tr>
							<th>һ�����˵�</th>
							<td colspan="2">
								<html:select property="queryequals_yjgnmkdm"
										value="${rs.yjgnmkdm }"
										onchange="setChildList(this.value,'ejgnmkdm')"
										style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="gnmkList" property="gnmkdm"
											labelProperty="gnmkmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�������˵�</th>
							<td colspan="2">
								<html:select property="queryequals_ejgnmkdm" 
										value="${rs.ejgnmkdm }" styleId="ejgnmkdm"
										style="width:140px" onchange="changeGnmkdm(this.value);">
									<html:option value=""></html:option>
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th width="25%">
								<font color="red">*</font>�˵�����
							</th>
							<td width="45%">
								<html:text property="save_gnmkmc" maxlength="40" value="${rs.gnmkmc }"></html:text>
								<html:hidden property="save_gnmkdm" value="${rs.gnmkdm }" styleId="gnmkdm"/>
							</td>
							<td rowspan="5" align="center">
								<logic:equal value="3" name="rs" property="cdjb">
									<img src="<%=stylePath %>/images/blue/54/${rs.picpath }" style="width:54px;height:54px" id="picpath"/>
									<br/><br/>
									<button type="button" class="btn_01" onclick="showTopWin('/xgxt/menuManage.do?method=setShortcutPic',600,400)">
										���
									</button>
								</logic:equal>
								<logic:notEqual value="3" name="rs" property="cdjb">
								<font color="blue">��3���˵�����ά����ݷ�ʽ</font>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								�˵�����
							</th>
							<td>
								${rs.cdjb }
							</td>
						</tr>
						<tr>
							<th>
								����·��
							</th>
							<td>
								${rs.dyym }
								<input type="hidden" value="${rs.dyym }" name="dyym"/>
							</td>
						</tr>
						<tr>
							<th>
								��ʾ˳��
							</th>
							<td>
								<html:text property="save_xssx" value="${rs.xssx }"
									maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�����
							</th>
							<td>
								<html:radio property="save_sfqy" value="��" name="rs">��</html:radio>
								<html:radio property="save_sfqy" value="��" name="rs">��</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								��������<br/>
								<font color="red">(��250��)</font>
							</th>
							<td colspan="2" style="word-break:break-all;">
								<html:textarea property="save_gnbz" style="width:98%" rows="5"
									onblur="checkLen(this,250)" value="${rs.gnbz }"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>���ܰ�ť</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
							<logic:notEmpty name="btnList">
								<logic:iterate id="b" name="btnList">
										${b.btnmc }
										<button type="button" class="btn_01" id="${b.btndm }" onclick="changeBtnSfqy(this);">
											<logic:equal value="��" name="b" property="sfqy">
												ȡ��
											</logic:equal>
											<logic:equal value="��" name="b" property="sfqy">
												Ӧ��
											</logic:equal>
										</button>
										&nbsp;&nbsp;
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="btnList">
								<font color="blue">�޹��ܰ�ť</font>
							</logic:empty>
							</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<logic:present name="message">
				<script defer="defer">
				    alert("${message}");
				    if (window.dialogArguments) {
						dialogArgumentsQueryChick();
						window.close();
					}
				 </script>
			</logic:present>
		</html:form>
	</body>
</html>
