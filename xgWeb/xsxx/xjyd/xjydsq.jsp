<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/stuinfoFunction.js"></script>
<script type="text/javascript" src="js/xsxx/xjyd.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
<script>
	function save(){
		var oper = val('oper');
		//�����ֶ��Ƿ���д		
		if(filedNotNull(['xh','ydxh','clwh','ydlbdm','ydrq'])){
			if('modi' == oper){
				//�޸�
				refreshForm('xjyd.do?method=xjydsq&doType=modify');
			}else{
				//����
				refreshForm('xjyd.do?method=xjydsq&doType=save');
			}
		}else{
			alert('�뽫��*�ŵ���Ŀ��д������');
			return false;
		}	
	}
	
</script>
</head>

<body onload="getYdlbInfo();">
	<html:form action="/xjyd.do" method="post">
		<input type="hidden" id="url" name="url" value="/xjyd.do?method=xjydsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />		
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<input type="hidden" name="xyV" id="xyV" value="" />
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="njV" id="njV" value="" />
		<input type="hidden" name="oper" id="oper" value="${oper}" />
		<!--�û���Ϣ-->
		<%@ include file="/xsxx/yhxx.jsp"%>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.jbxx.style.display=(document.all.jbxx.style.display =='none')?'':'none'"> 
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="jbxx">
					<tr>
						<th width="24%"><font color="red">*</font>ѧ��</th>
						<td width="26%">
							<logic:equal value="stu" name="userType">
								<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
									onkeypress="autoFillStuInfo(event.keyCode,this)"
									onblur="chkIsStu(this,'view_xsjbxx','xh')"  readonly="true"/>
							</logic:equal>
							<logic:notEqual value="stu" name="userType">
								<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
									onkeypress="autoFillStuInfo(event.keyCode,this)"
									onblur="chkIsStu(this,'view_xsjbxx','xh')"/>	
								<logic:notEqual value="modi" name="oper">
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>						
								
							</logic:notEqual>							
						</td>
						<th>�Ա�</th>
						<td>${rs.xb }</td>
					</tr>
					<tr>
						<th width="24%">����</th>
						<td width="26%">
							${rs.xm }
						</td>
						<th>��������</th>
						<td>${rs.csrq }</td>
					</tr>
					<tr>
						<th>�ֻ�����</th>
						<td>${rs.sjhm }</td>
						<th>�̶��绰</th>
						<td>${rs.lxdh }</td>
					</tr>
					<tr>
						<th><span class="red">*</span>�춯���</th>
						<td>
							<logic:notEqual value="modi" name="oper">
								<html:text name="rs" property="save_ydxh" styleId="ydxh" value="${rs.ydxh}"/>
							</logic:notEqual>
							<logic:equal value="modi" name="oper">
								<html:text name="rs" property="save_ydxh" styleId="ydxh" value="${rs.ydxh}" readonly="true"/>
							</logic:equal>					
						</td>
						<th><span class="red">*</span>�����ĺ�</th>
						<td>
							<html:text name="rs" property="save_clwh" styleId="clwh" />
						</td>
					</tr>					
					<tr>
						<th><span class="red">*</span>�춯���</th>
						<td>
							<html:select name="rs" styleId="ydlbdm" property="save_ydlbm" style="width:160px" 
								onchange="getYdlbInfo();initXjByYdlb(this.value);">
								<html:option value=""></html:option>
								<html:options collection="ydlbList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<th><span class="red">*</span>�춯����</th>
						<td>
							<html:text name="rs" property="save_ydrq" styleId="ydrq"
								onclick="return showCalendar('ydrq','y-mm-dd');" />
						</td>
					</tr>
					<tr>						
						
						<th>�춯˵��</th>
						<td>
							<html:text name="rs" property="save_ydsm" />
						</td>
						<th>�춯��ֹ����</th>
						<td>
							<html:text name="rs" property="save_ydjzrq" styleId="ydjzrq"
								onclick="return showCalendar('ydjzrq','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<th>�춯ԭ��</th>
						<td colspan="3">  
							<html:text name="rs" property="save_ydyy" style="width:90%"/>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4" onclick="document.all.xjxx.style.display=(document.all.xjxx.style.display =='none')?'':'none'"> 
							<span>ѧ����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody id="xjxx">
					<tr>
						<td colspan="2">
							<table class="formlist" style="width:100%;height:100%"> 
								<thead>
									<tr>
										<th colspan="2"><span>�춯ǰ��Ϣ</span></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th height="21px">�꼶</th>
										<td width="70%">
											${rs.ydqnj}
											<input type="hidden" id="ydqnj" name="save_ydqnj" value="${rs.ydqnj}"/>
										</td>
									</tr>
									<tr>
										<th height="21px"><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											${rs.ydqxymc}
											<input type="hidden" id="ydqxydm" name="save_ydqxydm" value="${rs.ydqxydm}"/>
										</td>
									</tr>
									<tr>
										<th height="21px">רҵ</th>
										<td>
											${rs.ydqzymc}
											<input type="hidden" id="ydqzydm" name="save_ydqzydm" value="${rs.ydqzydm}"/>
										</td>
									</tr>
									<tr>
										<th height="21px">�༶</th>
										<td>
											${rs.ydqbjmc}
											<input type="hidden" id="ydqbjdm" name="save_ydqbdm" value="${rs.ydqbdm}"/>
										</td>
									</tr>
									<tr>
										<th height="21px">ѧ��</th>
										<td>
											${rs.ydqxz}
											<input type="hidden" id="ydqxz" name="save_ydqxz" value="${rs.ydqxz}"/>
										</td>
									</tr>
									<tr>
										<th height="21px">ѧ��״̬</th>
										<td>
											${rs.ydqxjztm}
											<input type="hidden" id="ydqxjztm" name="save_ydqxjztm" value="${rs.ydqxjztm}"/>
										</td>
									</tr>
									<tr>
										<th height="21px">�Ƿ���У</th>
										<td>
											${rs.ydqsfzx}
											<input type="hidden" id="ydqsfzx" name="save_ydqsfzx" value="${rs.ydqsfzx}"/>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
						<td colspan="2">
							<table class="formlist" style="width:100%;height:100%">
								<thead>
									<tr>
										<th colspan="2"><span>�춯����Ϣ</span></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th height="20px"><span class="red">*</span>�꼶</th>
										<td>
											<html:select disabled="true" name="rs" property="save_ydhnj" styleId="nj" style="width:180px"
												onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options  collection="njList" property="nj"
													labelProperty="nj" />		
											</html:select>				
											<input type="hidden" name="t_ydhnj" value="${rs.ydhnj}"/>						
										</td>
									</tr>
									<tr>
										<th height="20px"><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
										<td>
											<html:select disabled="true" styleId="xy" name="rs" property="save_ydhxydm" style="width:180px"
												onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options  collection="xyList" property="xydm" labelProperty="xymc" />
											</html:select>
											<input type="hidden" name="t_ydhxydm" value="${rs.ydhxydm}"/>
										</td>
									</tr>
									<tr>
										<th height="20px"><span class="red">*</span>רҵ</th>
										<td>
											<html:select disabled="true" name="rs" property="save_ydhzydm" styleId="zy" style="width:180px"
												onchange="initBjList();">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm" labelProperty="zymc" />
											</html:select>
											<input type="hidden" name="t_ydhzydm" value="${rs.ydhzydm}"/>
										</td>
									</tr>
									<tr>
										<th height="20px"><span class="red">*</span>�༶</th>
										<td>
											<html:select disabled="true" name="rs" property="save_ydhbdm" styleId="bj" style="width:180px" 
												onchange="">
												<html:option value=""></html:option>
												<html:options  collection="bjList" property="bjdm" labelProperty="bjmc" />
											</html:select>
											<input type="hidden" name="t_ydhbjdm" value="${rs.ydhbjdm}"/>
										</td>
									</tr>
									<tr>
										<th height="20px">ѧ��</th>
										<td>
											<html:text disabled="true" name="rs" styleId="xz"
												property="save_ydhxz" onkeyup="value=value.replace(/[^\d]/g,'') "/>
										   <input type="hidden" name="t_ydhxz" value="${rs.ydhxz}"/>
										</td>
									</tr>
									<tr>
										<th height="20px">ѧ��״̬</th>
										<td>
											<html:select property="ydhxjztm" name="rs" styleId="ydhxj" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
											</html:select>
											<input type="hidden" name="save_ydhxjztm" id="h_ydhxj" value="${rs.ydhxjztm}"/>
										</td>
									</tr>
									<tr>
										<th height="20px">�Ƿ���У</th>
										<td>
											<html:select property="ydhsfzx" name="rs" styleId="ydhsfzx" disabled="true">
												<html:option value=""></html:option>
												<html:options collection="sfzxList" property="dm" labelProperty="mc"/>
											</html:select>
											<input type="hidden" name="save_ydhsfzx" id="h_ydhsfzx" value="${rs.ydhsfzx}"/>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>					
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<div class="buttontool">
			            <logic:equal value="yes" name="writeAble">
							<button type="button" class="" onclick="save();return false;" >
								��&nbsp;&nbsp;��
							</button>
							<button type="button" class="" onclick="Close();return false;" id="but_close" style="display:none">
								��&nbsp;&nbsp;��
							</button>
			            </logic:equal>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
		<script>
			if(window.dialogArguments){
				ele('but_close').style.display = '';
			}
		</script>
	</html:form>
</body>
</html>