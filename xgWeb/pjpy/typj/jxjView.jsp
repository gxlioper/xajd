<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/pjpy/typj.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript">
	function checkSyme() {
		var syme = window.dialogArguments.document.getElementById('syme').value;
		var userType = window.dialogArguments.document.getElementById('userType').value;
		var isFdy = window.dialogArguments.document.getElementById('isFdy').value;
		var flg = false;
		var sh = $('sh').value;
		
		if ( !((syme<0 || syme==0) && 'xy' == userType)) {
			flg = true;
		}
		
		if (!flg && 'ͨ��'==sh && '10657'==$('xxdm').value) {
			alert('������������!');
			return false;
		} 
		saveUpdate('/xgxt/typj.do?method=jxjView&doType=modify','save_xh-save_xn-save_jxjdm');
	}
	
	function setPage(){
		var text = $('jxjmc').value;
		
		if('12645' == $('xxdm').value) {
			if ('������������չ�ɾͽ�ѧ��'==text){
				$('szynltz').style.display='';
				$('nbcsjxj').style.display='none';
				$('gjlzjxj').style.display='none';
			}else if('������־��ѧ��'==text){
				$('szynltz').style.display='none';
				$('nbcsjxj').style.display='none';
				$('gjlzjxj').style.display='';
			} else {
				$('nbcsjxj').style.display='';
				$('szynltz').style.display='none';
				$('gjlzjxj').style.display='none';
			}
		}
		
		stringFormat(['fdyyjTd','xyyjTd','xxyjTd','shyjTd'],100);
	}
	
</script>
</head>
<body onload="setPage();display();">
	<html:form action="/typj" method="post">
		<input type="hidden" id="url" name="url" value="/typj.do?method=jxjsq" />
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="shjb" id="shjb" value="${shjb }"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
		<input type="hidden" id="jxjmc" value="${rs.jxjmc }"/>
		<input type="hidden" name="save_xq" value="${rs.xq }"/>
		<input type="hidden" name="save_nd" value="${rs.nd }"/>
		<input type="hidden" name="save_sqsj" value="${rs.sqsj }" id="sqsj"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<div class='tab'>
		<table class="formlist" width="100%">
			<thead>
				<tr><th colspan="4"><span>������Ϣ</span></th></tr>
			</thead>
			<tbody>
			<tr>
				<th width="24%"><font color="red">*</font>ѧ��</th>
				<td width="26%">
					<html:text  property="save_xh" styleId="xh"  value="${rs.xh}" readonly="true"/>
				</td>
				<th width="24%">����</th>
				<td width="26%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>${rs.xb }</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th><font color="red">*</font>ѧ��</th>
				<td>
					<html:text property="save_xn" name="rs" readonly="true"></html:text>
				</td>
				<th><font color="red">*</font>��ѧ������</th>
				<td>
					<logic:equal value="view" name="doType">
						${rs.jxjmc }
					</logic:equal>
					<logic:notEqual value="view" name="doType">
						<html:select property="save_jxjdm" name="rs" disabled="true">
							<html:options collection="jxjList" property="dm" labelProperty="mc"/>
						</html:select>
						<html:hidden property="save_jxjdm" name="rs"/>
					</logic:notEqual>
					
				</td>
			</tr>
			</tbody>
		</table>
	
		<logic:notEqual value="11355" name="xxdm" scope="session">
				
				<table width="99%" id="rsTable" class="formlist">
					<logic:empty  name="zcpm">
						<thead>
							<tr>
								<th colspan="3"><span>�ۺ����ʲ���</span></th>
							</tr>							
						</thead>
						<tbody>
							<tr>
								<td>û�м�¼��</td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="zcpm">
					<thead>
						<tr><th  colspan="6"><span>�ۺ����ʲ���<font color="blue">(���ݲ�������ģ�������õ��������ڻ�ȡ�۲�����)</font></span></th></tr>
						<tr>
							<td align="center">ѧ��</td>
							<td align="center">ѧ��</td>
							<td align="center">���</td>
							<td align="center">����</td>
							<td align="center">����</td>
							<td align="center">����</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="v" name="zcpm" offset="0" scope="request">
							<tr align="center" style="cursor:hand">
								<td>
									${v.xn }
								</td>
								<td>
									${v.xqmc }
								</td>
								<td>
									${v.nd }
								</td>
								<td>
									${v.mc }
								</td>
								<td>
									${v.fs }
								</td>
								<td>
									${v.pm }
								</td>
							</tr>
						</logic:iterate>
					</tbody>			
				</logic:notEmpty>
				</table>
		</logic:notEqual>
		<!-- ���ݴ�ѧ -->
		<logic:equal value="10657" name="xxdm" scope="session">
			<jsp:include flush="true" page="/pjpy/typj/guizhdx_jxjsq.jsp"></jsp:include>	
		</logic:equal>
	
		<!-- ����ְҵ -->
		<logic:equal value="11355" name="xxdm" scope="session">
			<jsp:include flush="true" page="/pjpy/typj/nnzy_jxjsq.jsp"></jsp:include>
		</logic:equal>
		
		<!-- �������� -->
		<logic:equal value="12645" name="xxdm" scope="session">
			<jsp:include flush="true" page="/pjpy/typj/nbcs_jxjsq.jsp"></jsp:include>
		</logic:equal>
	
	
		<logic:notEqual value="update" name="doType">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4"><span>���</span></th>
					</tr>
				</thead>
				<tbody>
				<logic:equal value="view" name="doType">
					<logic:equal value="1" name="shjb">
						<tr>
							<th width="24%">���״̬</th>
							<td colspan="3">
							  ${rs.shzt }
							</td>
						</tr>
						<tr>
							<th width="24%">������</th>
							<td colspan="3" id="shyjTd">
								${rs.shyj }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="2" name="shjb">
						<tr>
							<th width="24%"><bean:message key="lable.xsgzyxpzxy" />���</th>
							<td width="26%">
							  ${rs.xysh}
							</td>
							<th width="24%">ѧУ���</th>
							<td width="26%">
							  ${rs.xxsh}
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />���</th>
							<td colspan="3" id="xyyjTd">
								${rs.xyyj }
							</td>
						</tr>
						<tr>
							<th>ѧУ���</th>
							<td colspan="3" id="xxyjTd">
								${rs.xxyj }
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="3" name="shjb">
						<tr>
							<th width="24%">����Ա���</th>
							<td width="26%">
							  ${rs.fdysh}
							</td>
							<th width="24%"></th>
							<td width="26%">
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />���</th>
							<td>
							  ${rs.xysh}
							</td>
							<th>ѧУ���</th>
							<td>
							  ${rs.xxsh}
							</td>
						</tr>
						<tr>
							<th>����Ա���</th>
							<td colspan="3" id="fdyyjTd">
								${rs.fdyyj }
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />���</td>
							<td colspan="3" id="xyyjTd">
								${rs.xyyj }
							</td>
						</tr>
						<tr>
							<th>ѧУ���</th>
							<td colspan="3" id="xxyjTd">
								${rs.xxyj }
							</td>
						</tr>
					</logic:equal>
				</logic:equal>
				<logic:equal value="sh" name="doType">
					<logic:equal value="1" name="shjb">
						<tr>
							<th width="24%">���״̬</th>
							<td  colspan="3">
							 	<html:select property="save_shzt" name="rs" styleId="sh">
							 		<html:options collection="shztList" property="en" labelProperty="cn"/>
							 	</html:select>
							</td>
						</tr>
						<tr>
							<th width="24%">������</th>
							<td colspan="3">
								<html:textarea property="save_shyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="2" name="shjb">
						<logic:equal value="xy" name="userType" scope="session">
							<tr>
								<th width="24%"><bean:message key="lable.xsgzyxpzxy" />���</th>
								<td colspan="3">
								 	<html:select property="save_xysh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th><bean:message key="lable.xsgzyxpzxy" />���</th>
								<td>
									<html:textarea property="save_xyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="xy" name="userType" scope="session">
							<tr>
								<th width="24%">ѧУ���</th>
								<td colspan="3">
								 	<html:select property="save_xxsh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th>ѧУ���</th>
								<td>
									<html:textarea property="save_xxyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:notEqual>
					</logic:equal>
					<logic:equal value="3" name="shjb">
						<logic:equal value="true" name="isFdy" scope="session">
							<tr>
								<th width="24%">����Ա���</th>
								<td  colspan="3">
								 	<html:select property="save_fdysh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th>����Ա���</th>
								<td>
									<html:textarea property="save_fdyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<!--�㽭�Ƽ�ѧԺ-->
						<logic:equal value="11057" name="xxdm">
							<logic:notEqual value="true" name="isFdy" scope="session">
								<logic:equal value="true" name="isBzr" scope="session">
									<tr>
										<th width="24%">����Ա���</th>
										<td colspan="3">
										 	<html:select property="save_fdysh" name="rs" styleId="sh">
										 		<html:options collection="shztList" property="en" labelProperty="cn"/>
										 	</html:select>
										</td>
									</tr>
									<tr>
										<th>����Ա���</th>
										<td colspan="3">
											<html:textarea property="save_fdyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:notEqual value="true" name="isBzr" scope="session">
									<logic:equal value="xy" name="userType" scope="session">
										<tr>
											<th width="24%"><bean:message key="lable.xsgzyxpzxy" />���</th>
											<td colspan="3">
											 	<html:select property="save_xysh" name="rs" styleId="sh">
											 		<html:options collection="shztList" property="en" labelProperty="cn"/>
											 	</html:select>
											</td>
										</tr>
										<tr>
											<th><bean:message key="lable.xsgzyxpzxy" />���</th>
											<td colspan="3">
												<html:textarea property="save_xyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
											</td>
										</tr>
									</logic:equal>
									<logic:notEqual value="xy" name="userType" scope="session">
										<tr>
											<th width="24%">ѧУ���</th>
											<td colspan="3">
											 	<html:select property="save_xxsh" name="rs" styleId="sh">
											 		<html:options collection="shztList" property="en" labelProperty="cn"/>
											 	</html:select>
											</td>
										</tr>
										<tr>
											<th>ѧУ���</th>
											<td colspan="3">
												<html:textarea property="save_xxyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
											</td>
										</tr>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</logic:equal>
						<!--end�㽭�Ƽ�ѧԺ-->
	
						<!--���㽭�Ƽ�ѧԺ-->
						<logic:notEqual value="11057" name="xxdm">
							<logic:notEqual value="true" name="isFdy" scope="session">
								<logic:equal value="xy" name="userType" scope="session">
									<tr>
										<th width="24%"><bean:message key="lable.xsgzyxpzxy" />���</th>
										<td colspan="3">
										 	<html:select property="save_xysh" name="rs" styleId="sh">
										 		<html:options collection="shztList" property="en" labelProperty="cn"/>
										 	</html:select>
										</td>
									</tr>
									<tr>
										<th><bean:message key="lable.xsgzyxpzxy" />���</th>
										<td colspan="3">
											<html:textarea property="save_xyyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
										</td>
									</tr>
								</logic:equal>
						</logic:notEqual>
						<logic:notEqual value="xy" name="userType" scope="session">
							<tr>
								<th width="24%">ѧУ���</th>
								<td colspan="3">
								 	<html:select property="save_xxsh" name="rs" styleId="sh">
								 		<html:options collection="shztList" property="en" labelProperty="cn"/>
								 	</html:select>
								</td>
							</tr>
							<tr>
								<th>ѧУ���</th>
								<td colspan="3">
									<html:textarea property="save_xxyj" style="width:90%;word-break:break-all;" rows="8" name="rs" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
						</logic:notEqual>
						</logic:notEqual>
						<!--end���㽭�Ƽ�ѧԺ-->					
					</logic:equal>
				</logic:equal>
				</tbody>
			</table>	
		</logic:notEqual>
		<table width="100%" class="formlist">
			<tfoot>
		      <tr>
		        <td colspan="4">
		          <div class="btn">
					<logic:notEqual value="view" name="doType">
						<logic:equal value="update" name="doType">
							<button class="" id="buttonSave" onclick="saveUpdate('/xgxt/typj.do?method=jxjView&doType=modify','save_xh-save_xn-save_jxjdm');">
								����
							</button>
						</logic:equal>
						<logic:equal value="sh" name="doType">
								<button class="" id="buttonSave" onclick="checkSyme();">
									����
								</button>
						</logic:equal>
					</logic:notEqual>
						<button class="" id="buttonSave" onclick="window.close();return false;">
							�ر�
						</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					if(window.dialogArguments.document.getElementById("isPage")){
							window.dialogArguments.document.getElementById("isPage").value="yes";
					}
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>