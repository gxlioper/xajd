<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type="text/javascript" src="js/checkUtils.js"></script>
		<script>
		function checkCf() {
			if (document.getElementById('cfqk')) {
				alert('�����Ա��Υ�ͼ�¼�����������Ƚ��༶');
				return false;
			}
			saveUpdate('/xgxt/guizhdx.do?method=xjbjUpdate&doType=save','save_xn');
		}
		
		function dy() {
			var pkValue = document.getElementById('pkValue').value;
			var url = '/xgxt/guizhdx.do?method=xjbjDy&pkValue='+pkValue;
			window.open(url);
		}
		
		
		function sendBjdm() {
			var bjdm = $('bj').value;
			
			if (''!=bjdm ) {
				refreshForm('/xgxt/guizhdx.do?method=xjbjUpdate&bjdm='+bjdm);
			}
		}
		
		function setDisabled(){
			var userType = $('userType').value;
			
			if ('stu'== userType) {
				$('nj').disabled=true;
				$('xy').disabled=true;
				$('zy').disabled=true;
			} 
		}
		
		function checkSyme() {
			var flg = true;
			var syme = window.dialogArguments.document.getElementById('syme').value;
			if ($('xysh')) {
				var xysh = $('xysh').value;
				if((Number(syme) < 0 || Number(syme) == 0) && 'ͨ��'== xysh){
					flg = false;
				}
			}
			
			if (flg){
				saveUpdate('/xgxt/guizhdx.do?method=xjbjOne&doType=save','');
			} else {
				alert('ʣ�������!');
			}
		}
	</script>
	</head>
<body onload="xyDisabled('xy'); setDisabled();">
	<html:form action="/guizhdx" method="post">
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �Ƚ��༶ - �Ƚ��༶����</a>
			</p>
		</div>
	
		<input type="hidden" name="xyV" value=""/>
		<input type="hidden" name="zyV" value=""/>
		<input type="hidden" name="bjV" value=""/>
		<input type="hidden" id="cbVal" name="cbVal" value="" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
		<input type="hidden" id="userName" name="userName" value="${userName }"/>
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="xn" name="save_xn" value="${rs.xn }" />
		<input type="hidden" id="nd" name="save_nd" value="${rs.nd }" />
		<input type="hidden" id="sqr" name="save_sqr" value="${userName }" />
		<div class="tab">
			<table width="100%"  border="0" class="formlist">
			 <thead>
   				<tr>
       				<th colspan="4"><span>�Ƚ��༶����</span></th>
       			</tr>
  			</thead>
  			<tbody>
			<tr>
				<th>
					�꼶
				</th>
				<td>
					<html:select property="nj"   style="width:150px" value="${rs.nj}" styleId="nj" onchange="initZyList();initBjList()">
						<html:option value=""></html:option>
						<html:options collection="njList" property="nj" labelProperty="nj"/>
					</html:select>
				</td>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td>
					<html:select property="xydm"  style="width:150px" value="${rs.xydm}" onchange="initZyList();initBjList()"  styleId="xy">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm"labelProperty="xymc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<th>
					רҵ
				</th>
				<td>
					<html:select property="zydm"  style="width:150px" value="${rs.zydm}" onchange="initBjList()"  styleId="zy">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"	labelProperty="zymc" />
					</html:select>
				</td>
				<th>
					<font color="red">*</font>�༶
				</th>
				<td>
					<logic:equal value="stu" name="userType">
						<html:hidden property="save_bjdm"  value="${rs.bjdm}"/>
						<html:select property="save_bjdm" style="width:150px" styleId="bj" disabled="true" value="${rs.bjdm}">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"	labelProperty="bjmc" />
						</html:select>
					</logic:equal>
					
					<logic:notEqual value="stu" name="userType" >
						<logic:equal value="view" name="doType">
							<html:select property="save_bjdm"  style="width:150px" styleId="bj" onchange="sendBjdm();" value="${rs.bjdm}" disabled="true"> 
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"	labelProperty="bjmc" />
							</html:select>
						</logic:equal>
						
						<logic:equal value="sh" name="doType">
							<html:hidden property="save_bjdm"  value="${rs.bjdm}"/>
							<html:select property="save_bjdm"  style="width:150px" styleId="bj" onchange="sendBjdm();" value="${rs.bjdm}" disabled="true"> 
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"	labelProperty="bjmc" />
							</html:select>
						</logic:equal>
						
						<logic:notEqual value="view" name="doType">
							<logic:notEqual value="sh" name="doType">
								<html:select property="save_bjdm"  style="width:150px" styleId="bj" onchange="sendBjdm();" value="${rs.bjdm}"> 
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"	labelProperty="bjmc" />
								</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</logic:notEqual>
				</td>
			</tr>
			<tr>
				<th scope="col">
					����������
				</th>
				<td>
					<html:text property="save_bzrxm"  value="${rs.bzrxm}" readonly="true"></html:text>
				</td>
				<th>
					�༶��Ա��
				</th>
				<td >
					<html:text property="save_tyrs" value="${rs.tyrs}" readonly="true"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					�༶����
				</th>
				<td>
					<html:text property="save_bjrs"  readonly="true" value="${rs.bjrs}"></html:text>
				</td>
				<th>
					�༶��Ա��
				</th>
				<td>
					<html:text property="save_dyrs" readonly="true" value="${rs.dyrs}"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					���ΰ༶����ѧ���ɲ�����
				</th>
				<td>
					<html:text property="save_gbrs" value="${rs.gbrs}" readonly="true"></html:text>
				</td>
				<th>
					���2000Ԫ�����������
				</th>
				<td>
					<html:text property="save_zzqk" maxlength="50" value="${rs.zzqk}"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					��ѧ���ܴ������
				</th>
				<td colspan="3">
					<logic:empty name="cfList">
						û�м�¼��
					</logic:empty>
					<logic:notEmpty name="cfList">
						<input type="hidden" id="cfqk" value="0">
						<table width="90%" id="rsTable" class="tbstyle">
							<tr style="cursor:hand">
								<td>ѧ��</td>
								<td>����</td>
								<td>ѧ��</td>
								<td>ѧ��</td>
								<td>�������</td>
								<td>����ԭ��</td>
								<td>����ʱ��</td>
								<td>�����ĺ�</td>
							</tr>
						<logic:iterate id="s" name="cfList">
							<tr style="cursor:hand">
								<logic:iterate id="v" name="s">
									<td>
										<bean:write name="v"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</table>
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<th>
					��ѧ�ڲ����ʲ����˴�
				</th>
				<td colspan="3">
					<logic:empty name="bkxx">
						û�м�¼
					</logic:empty>
					<logic:notEmpty name="bkxx">
						<table width="90%" id="rsTable" class="tbstyle">
							<tr style="cursor:hand">
								<td>ѧ��</td>
								<td>ѧ��</td>
								<td>�༶����</td>
								<td>��������</td>
								<td>������</td>
							</tr>
							<logic:iterate id="s" name="bkxx">
								<tr style="cursor:hand">
									<td>
										<bean:write property="xn" name="s"/>
									</td>
									<td>
										<bean:write property="xq" name="s"/>
									</td>
									<td>
										<bean:write property="bjrs" name="s"/>
									</td>
									<td>
										<bean:write property="rs" name="s"/>
									</td>
									<td>
										<bean:write property="bkl" name="s"/>
									</td>
								</tr>
							</logic:iterate>
						</table>
					</logic:notEmpty>
					
				</td>
			</tr>
			<tr>
				<th>
					УԺ�������ұ���
				</th>
				<td colspan="3">
					<html:textarea property="save_wmqs" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.wmqs }">
						
					</html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					Ƿ��ѧ�����������
				</th>
				<td colspan="3">
					<html:textarea property="save_jfqk" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.jfqk}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					�뵳���뵳�������ӵ�����������
				</th>
				<td colspan="3">
					�뵳������${rs.dyrs }<br/>
					�뵳����������${rs.dybl }<br/>
					�뵳��������������${rs.jjfzrs } <br/>
					�뵳�������ӱ�����${rs.jjfzbl }
				</td>
			</tr>
			<tr>
				<th>
					����������������
				</th>
				<td colspan="3">
					<html:textarea property="save_dyqk" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.dyqk}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					��ѧ���ܸ��ֽ�����������Ƽ�������
				</th>
				<td colspan="3">
					<logic:empty name="pjpyxx">
						û�м�¼��
					</logic:empty>
					<logic:notEmpty name="pjpyxx">
						<table width="90%" id="rsTable" class="tbstyle">
								<tr style="cursor:hand">
									<td>ѧ��</td>
									<td>�༶</td>
									<td>�񽱽���</td>
									<td>������</td>
								</tr>
								<logic:iterate id="s" name="pjpyxx">
									<tr style="cursor:hand">
										<logic:iterate id="v" name="s">
											<td>
												<bean:write name="v"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<th>
					��Ҫ�¼�
				</th>
				<td colspan="3">
					<html:textarea property="save_zysj" onblur="checkLeng(this,2000)" style="word-break:break-all;width:90%" rows="15" value="${rs.zysj}"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					���������
				</th>
				<td colspan="3">
					<html:textarea property="save_bzryj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.bzryj}"></html:textarea>
				</td>
			</tr>
			<logic:equal value="sh" name="doType">
				<logic:equal name="isFdy" value="true">
					<tr>
						<th>
							����Ա���
						</th>
						<td>	
							<html:select property="save_fdysh" value="${rs.fdysh}">
								<html:options collection="shztList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>
							����Ա���
						</th>
						<td colspan="3">
							<html:textarea property="save_fdyyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.fdyyj}"></html:textarea>
						</td>
					</tr>
				</logic:equal>
				<logic:notEqual name="isFdy" value="true">
					<logic:equal value="xy" name="userType">
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />���
							</th>
							<td>	
								<html:select property="save_xysh" value="${rs.xysh}" styleId="xysh">
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />���
							</th>
							<td colspan="3">
								<html:textarea property="save_xyyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.xyyj}"></html:textarea>
							</td>
						</tr>
					</logic:equal>
				</logic:notEqual>
					<logic:equal value="xx" name="userType">
						<tr>
							<th>
								ѧУ���
							</th>
							<td>	
								<html:select property="save_xxsh" value="${rs.xxsh}">
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
							<tr>
								<th>
									ѧУ���
								</th>
								<td colspan="3">
								<html:textarea property="save_xxyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.xxyj}"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="admin" name="userType">
							<tr>
							<th>
								ѧУ���
							</th>
							<td>	
								<html:select property="save_xxsh" value="${rs.xxsh}">
									<html:options collection="shztList" property="en" labelProperty="cn"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
						</tr>
							<tr>
								<th>
									ѧУ���
								</th>
								<td colspan="3">
								<html:textarea property="save_xxyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.xxyj}"></html:textarea>
							</td>
						</tr>
					</logic:equal>
			</logic:equal>
			<logic:equal value="view" name="doType">
				<tr>
						<th>
							����Ա���
						</th>
						<td>	
							<html:text property="save_fdysh" value="${rs.fdysh}" readonly="true"></html:text>
						</td>
						<th></th>
						<td></td>
					</tr>
					<tr>
						<th>
							����Ա���
						</th>
						<td colspan="3">
							<html:textarea property="save_fdyyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.fdyyj}" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />���
							</th>
							<td>	
							<html:text property="save_xysh" value="${rs.xysh}" readonly="true"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />���
							</th>
							<td colspan="3">
								<html:textarea property="save_xyyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.xyyj}" readonly="true"></html:textarea>
							</td>
						</tr>
						<tr>
						<th>
								ѧУ���
							</th>
							<td>	
								<html:text property="save_xxsh" value="${rs.xxsh}" readonly="true"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
							<tr>
								<th>
									ѧУ���
								</th>
								<td colspan="3">
								<html:textarea property="save_xxyj" onblur="checkLeng(this,500)" style="word-break:break-all;width:90%" rows="5" value="${rs.xxyj}" readonly="true"></html:textarea>
							</td>
						</tr>
			</logic:equal>
			</tbody>
			 <tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	<logic:notEqual value="sh" name="doType">
						<logic:notEqual value="view" name="doType">
							<logic:notEqual value="modi" name="doType">
								<button id="buttonSave" onclick="checkCf();">
									��    &nbsp;&nbsp;��
								</button>
							</logic:notEqual>
						</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="sh" name="doType">
							<button id="buttonSave" onclick="checkSyme();">
								��   &nbsp;&nbsp;��
							</button>
					</logic:equal>
					<logic:equal value="view" name="doType">
							<button id="buttonSave" onclick="dy();">
								��    &nbsp;&nbsp;ӡ
							</button>
							<button id="buttonSave" onclick="window.close();return false;">
								��    &nbsp;&nbsp;��
							</button>
					</logic:equal>
					<logic:equal value="modi" name="doType">
							<button id="buttonSave" onclick="saveUpdate('/xgxt/guizhdx.do?method=xjbjOne&doType=update','');">
								��   &nbsp;&nbsp;��
							</button>
					</logic:equal>			           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
</body>
<logic:notEmpty name="result">
			<script>
				alert("" + $('message').value);
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						Close();
						
						if(window.dialogArguments.document.getElementById("isPage")){
							window.dialogArguments.document.getElementById("isPage").value="yes";
						}
						window.dialogArguments.document.getElementById('search_go').click();	
				}
			</script>
		</logic:notEmpty>
</html>
