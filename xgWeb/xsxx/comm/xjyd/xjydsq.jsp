<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xjydService.js'></script>
		<script type="text/javascript">
			function getShlcid(ydlb){
				if (ydlb != ''){
					xjydService.getShlcidByYdlb(ydlb,function(data){
						$('shlcid').value = data.shlcid;
						$('ydhxjztm').value = data.xjzt;
						$('xjzt').value = data.xjzt;
						$('ydhsfzx').value = data.sfzx;
						$('sfzx').value = data.sfzx;
					})
				}
			}
			
			function getXh(){
				var url = 'general_xsxx_xjyd.do?method=getXh';	
				showTopWin(url,800,600);
			}
			
			function downXsxx(){
				refreshForm('xsxxXjyd.do?method=xjydsq&xh='+jQuery("#xh").val())
			}
			
			function yzyd(){
				var ydqdm = $('save_ydqbdm').value;
				var bj = $('bj').value;
				var xjztm = $('save_ydqxjztm').value;
				var xjzt = $('xjzt').value;
				var ydqsfzx = $('save_ydqsfzx').value;
				var sfzx = $('sfzx').value;
								
				if(ydqdm==bj&&xjztm==xjzt&&ydqsfzx==sfzx){
					alertInfo('<font color="blue">"�༶"��"ѧ��״̬"</font>��<font color="blue">"�Ƿ���У"</font>δ�����仯,��ȷ�ϣ�');
					return false;
				}
				saveUpdate('xsxxXjyd.do?method=xjydsq&doType=save&sftj=��','xh-ydlbm-xn');
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xsxxXjyd" method="post">
		
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="url" name="url" value="/xsxxXjyd.do?method=xjydsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
			
			<html:hidden property="save_id"/>
		
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
									<%--
									<button type="button" name="�� ��" onclick="saveUpdate('xsxxXjyd.do?method=xjydsq&doType=save','xh-ydlbm')" id="save_button">
										�� ��
									</button>
									--%>
									<button type="button" name="�� ��" onclick="yzyd();" id="submit_button">
										�� ��
									</button>
									<button type="button" name="�� ��" type="reset">
										�� ��
									</button>
									
									<logic:equal value="false" name="sfcf">
										<script>
											$('save_button').disabled = true;
											$('submit_button').disabled = true;
										</script>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									ѧ��������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<logic:equal value="stu" name="userType">
									<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsbfxx','xh')"  readonly="true"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType">
									<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
										onkeypress="autoFillStuInfo(event.keyCode,this)"
										onblur="chkIsStu(this,'view_xsbfxx','xh')"/>	
									<logic:notEqual value="modi" name="oper">
									<%--
									    <button type="button" onclick="showTopWin('stu_info.do',800,600);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									--%>
									    <input  style="display: none" onclick="downXsxx();" id="disbutton"/>
										<button type="button" onclick="getXh();" class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:notEqual>						
								</logic:notEqual>			
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" id="xm">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td id="xb">
								${rs.xb }
							</td>
							<th>
								��ϵ�绰
							</th>
							<td id="lxdh">
								${rs.lxdh }
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�춯���
							</th>
							<td>
								<html:hidden property="save_shlcid" styleId="shlcid"/>
							
								<html:select property="save_ydlbm" styleId="ydlbm" style="width:180px" onchange="getShlcid(this.value);">
									<html:option value=""></html:option>
									<html:options collection="ydlbList" property="ydlbm" labelProperty="ydlbmc"/>
								</html:select>
							</td>
							<th>
								������
							</th>
							<td>
								<html:text property="save_sqr" value="${userNameReal }" readonly="true"></html:text>
								<html:hidden property="save_sqsj"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>ѧ��
							</th>
							<td>
								<html:select property="save_xn" style="width:180px" 
									styleId="xn" styleClass="select" >
									<html:option value=""></html:option>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>	
							<th>
								<span class="red">*</span>ѧ��
							</th>
							<td>
								<html:select property="save_xq" style="width:180px" 
									styleId="xq" styleClass="select" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��������
								<br/>
								<font color="red"><��400��></font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="save_ydsm" style="width:90%" rows="5" onblur="checkLen(this,400)"></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									ѧ���춯��Ϣ
								</span>
							</td>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="2" align="center">��ǰ���ڰ༶��ѧ��״̬</td>
							<td colspan="2" align="center">�����춯��Ϣ��ѧ��״̬</td>
						</tr>
						<tr>
							<th>�꼶</th>
							<td>
								${rs.nj }
								<html:hidden property="save_ydqnj" value="${rs.nj }"/>
							</td>
							<th>�꼶</th>
							<td>
								<html:select property="save_ydhnj" styleId="nj" value="${rs.nj }"
									onchange="initZyList();initBjList()" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								${rs.xymc }
								<html:hidden property="save_ydqxydm" value="${rs.xydm }"/>
								<html:hidden property="save_ydqxymc" value="${rs.xymc }"/>
							</td>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<html:hidden property="save_ydhxymc" styleId="ydhxymc" value="${rs.xymc }"/><!-- �춯��ѧԺ���� -->
								<html:select property="save_ydhxydm" value="${rs.xydm }"
									onchange="initZyList();initBjList();$('ydhxymc').value=DWRUtil.getText('xy')" styleId="xy"
									style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>
								${rs.zymc }
								<html:hidden property="save_ydqzydm" value="${rs.zydm }"/>
								<html:hidden property="save_ydqzymc" value="${rs.zymc }"/>
							</td>
							<th>רҵ</th>
							<td>
								<html:hidden property="save_ydhzymc" styleId="ydhzymc" value="${rs.zymc }"/><!-- �춯��רҵ���� -->
								<html:select property="save_ydhzydm" value="${rs.zydm }"
									onchange="initBjList();$('ydhzymc').value=DWRUtil.getText('zy')" styleId="zy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�༶</th>
							<td>
								${rs.bjmc }
								<html:hidden property="save_ydqbdm" value="${rs.bjdm }" styleId="ydqbdm"/>
								<html:hidden property="save_ydqbjmc" value="${rs.bjmc }"/>
							</td>
							<th>�༶</th>
							<td>
								<html:hidden property="save_ydhbjmc" styleId="ydhbjmc" value="${rs.bjmc }"/><!-- �춯��༶���� -->
								<html:select property="save_ydhbdm" styleId="bj"
									onchange="$('ydhbjmc').value=DWRUtil.getText('bj')"
									style="width:180px" value="${rs.bjdm }">
									<html:option value=""></html:option>
									<logic:present name="bjList">
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</logic:present>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��״̬</th>
							<td>
								${rs.xjztm }
								<html:hidden property="save_ydqxjztm" value="${rs.xjztm }"/>
							</td>
							<th>ѧ��״̬</th>
							<td>
								<html:hidden property="save_ydhxjztm" styleId="xjzt"/>
								<html:select property="save_ydhxjztm" style="width:180px"  styleId="ydhxjztm" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>�Ƿ���У</th>
							<td>
								${rs.sfzx }
								<html:hidden property="save_ydqsfzx" value="${rs.sfzx }"/>
							</td>
							<th>�Ƿ���У</th>
							<td>
								<html:hidden property="save_ydhsfzx" styleId="sfzx"/>
								<html:select property="save_ydhsfzx" style="width:180px"  styleId="ydhsfzx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		
		<logic:present name="message">
			<script>
				alert("${message}");
				if(window.dialogArguments){
					dialogArgumentsQueryChick();
					window.close();
				}
			</script>
		</logic:present>
	</body>
</html>
