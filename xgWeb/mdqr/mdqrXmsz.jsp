<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		
		function saveXmsz(){
			if($("xmmc").value==""){
				alert("��Ŀ���Ʋ���Ϊ��!");
				return false;
			}
			
			if($("xmlbdm").value==""){
				alert("��Ŀ�����Ϊ��!");
				return false;
			}
			
			if($("xmsm").value==""){
				alert("��Ŀ˵������Ϊ��!");
				return false;
			}
			refreshForm("/xgxt/mdqr.do?method=mdqrXmsz&doType=save");
		}
		
		function updateXmsz(){
			refreshForm("/xgxt/mdqr.do?method=mdqrXmUpdate&doType=update");
		}
		
		function fdysz(){
			$('sxysh').style.display="";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function xysz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="";
			$('xyNbsptwo').style.display="";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function xxsz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="none";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="";
			$('xxNbsptwo').style.display="";
		}
		
		function ufdysz(){
			$('sxysh').style.display="";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="";
			$('sbsh').style.display="";
			$('bsh').checked="true";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function uxysz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('bsh').checked="true";
			$('xyNbspone').style.display="";
			$('xyNbsptwo').style.display="";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function uxxsz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="none";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="";
			$('xxNbsptwo').style.display="";
			$('bsh').checked="true";
		}
		
		function loadShlc(){
			if($("add") && $("add").value=="yes"){
				$("fsq").checked="true";
				$("fsh").checked="true";
				$("fqr").checked="true";
				$("wxz").checked="true";
				$("wxzqr").checked="true";
				$("wzq").checked="true";
				$("bsh").checked="true";
			}
			if($("mdsz").value=="fdy"){
				 fdysz();
			}else if($("mdsz").value=="xy"){
				 xysz();
			}else if($("mdsz").value=="xx"){
				 xxsz();
			}else if($("mdsz").value=="no"){
				 fdysz();
			}
		}
		
		function showSz(){
			if($("ksq") && $("ksq").checked){
				$('showMdsz').style.display="";
			}else{
				$('showMdsz').style.display="none";
			}
			
			if($("ksh") && $("ksh").checked){
				$('fdysz').style.display="";
			}else{
				$('fdysz').style.display="none";
			}
			
			if($("kqr") && $("kqr").checked){
				$('showMdqr').style.display="";
			}else{
				$('showMdqr').style.display="none";
			}
		}
		
	</script>
	</head>
	<body onload="loadShlc();showSz()"  >
		
		<html:form action="/mdqr" method="post">
			<input type="hidden" name ="xxdm" id = "xxdm" value="<bean:write name="xxdm" />"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}
					<logic:equal name="doType" value="add">
					-����
					</logic:equal>
					<logic:equal name="doType" value="modi">
					-�޸�
					</logic:equal>
					<logic:equal name="doType" value="view">
					-�鿴
					</logic:equal>
					</a>
				</p>
			</div>
					<input type="hidden" id="doType" name="doType"
						value="${doType }" />
					<input type="hidden" id="userType" name="userType" value="${userType }" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="${pkValue }" />
					<input type="hidden" id="disableEle" name="disableEle" value="" />
					<input type="hidden" id="readonlyEle" name="readonlyEle"
						value="zgh" />
					<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
					<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
					<logic:equal name="doType" value="add">
						<input type="hidden" id="save_xmdm" name="save_xmdm" value="${xmdm }"/>
					</logic:equal>
					<input type="hidden" name="message" id="message" value="${message }"/>
					<logic:equal name="doType" value="modi">
						<input type="hidden" name="save_xmdm" id="save_xmdm" value="${rs.xmdm}"/>
					</logic:equal>
					<logic:empty name="rs">
						<input type="hidden" name="add" id="add" value="yes"/>
					</logic:empty>
					<input type="hidden" name="mdsz" id="mdsz" value="${rs.save_mdsz}"/>
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>��Ŀ����</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
							<tr>
								<th style="width:16%">
									<font color="red">*</font>��Ŀ����
								</th>
								<td>
									<logic:empty name="rs">
										<html:text  property="save_xmmc" styleId="xmmc" maxlength="15"/>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:text name="rs"  property="save_xmmc" styleId="xmmc" maxlength="15"/>
									</logic:notEmpty>
								</td>
								<th>
									<font color="red">*</font>��Ŀ���
								</th>
								<td>
									<logic:empty name="rs">
										<html:select property="save_xmlbdm" styleId="xmlbdm">
											<html:option value=""></html:option>
											<html:options collection="xmlbList" property="dm"
												 labelProperty="mc"/>
										</html:select>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:select name="rs" property="save_xmlbdm" styleId="xmlbdm">
											<html:option value=""></html:option>
											<html:options collection="xmlbList" property="dm"
												 labelProperty="mc"/>
										</html:select>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>��Ŀ˵��
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:textarea   property="save_xmsm" styleId="xmsm" cols="67" rows="5" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"></html:textarea>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:textarea name="rs"  property="save_xmsm"  styleId="xmsm"  cols="67" rows="5" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"></html:textarea>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th align="right">
									�ɷ�����
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_kfsq" styleId="ksq" value="yes" onclick="$('showMdsz').style.display=''"></html:radio>����
										<html:radio property="save_kfsq" styleId="fsq" value="no" onclick="$('showMdsz').style.display='none'"></html:radio>�ر�
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_kfsq"  styleId="ksq" value="yes" onclick="$('showMdsz').style.display=''"></html:radio>����
										<html:radio name="rs" property="save_kfsq"  styleId="fsq" value="no"  onclick="$('showMdsz').style.display='none'"></html:radio>�ر�
									</logic:notEmpty>
									<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��ʾ��������Ŀ�Ƿ񿪷�������������</font>
								</td>
							</tr>
							<tr id="showMdsz" style="display:none">
								<th align="right">
									��������
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_mdsz" value="fdy" onclick='fdysz()'/>����Ա
									 	<html:radio property="save_mdsz" value="xy" onclick='xysz()'/><bean:message key="lable.xb" />
									 	<font color="blue">
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	��ʾ��������Ŀ����������ʱ���û�Ȩ�ޡ�</font><br/>
									 	<html:radio property="save_mdsz" value="xx" onclick="xxsz()"/>ѧУ
									 	<html:radio property="save_mdsz" styleId="wxz"  value="no" onclick='fdysz()' />������
									</logic:empty>
									<logic:notEmpty name="rs">
									 	<html:radio name="rs" property="save_mdsz"   value="fdy" onclick="ufdysz();"/>����Ա
									 	<html:radio name="rs" property="save_mdsz" value="xy" onclick="uxysz();"/><bean:message key="lable.xb" />
									 	<font color="blue">
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	��ʾ��������Ŀ����������ʱ���û�Ȩ�ޡ�</font><br/>
									 	<html:radio name="rs" property="save_mdsz" value="xx" onclick="uxxsz();"/>ѧУ
									 	<html:radio name="rs" property="save_mdsz"  value="no" onclick="ufdysz();"/>������
								 	</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th align="right">
									�ɷ����
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_kfsh" styleId="ksh" value="yes" onclick="$('fdysz').style.display=''"></html:radio>����
										<html:radio property="save_kfsh" styleId="fsh" value="no" onclick="$('fdysz').style.display='none'"></html:radio>�ر�
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_kfsh" value="yes" styleId="ksh" onclick="$('fdysz').style.display=''"></html:radio>����
										<html:radio name="rs" property="save_kfsh" value="no"  styleId="fsh" onclick="$('fdysz').style.display='none'"></html:radio>�ر�
									</logic:notEmpty>
									<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��ʾ��������Ŀ�Ƿ񿪷���˹��ܡ�</font>
								</td>
							</tr>
							<logic:empty name="rs">
										<tr id="fdysz" style="display:none">
											<th>
												�������
											</th>
											<td colspan="3">
												<span id="sxysh"><html:radio  property="save_shjb" styleId="xysh" value="xysh"/><bean:message key="lable.xb" />���</span>
										 		<span id="sxxsh"><html:radio  property="save_shjb" styleId="xxsh" value="xxsh"/>ѧУ���</span>
										 		<font color="blue">
										 		<span id="xyNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʾ��������Ŀ�����ʱ��������̣���ͬ����������Ȩ�ޣ�</font>
										 		<br/>
										 		<span id="sxyxsh"><html:radio  property="save_shjb" styleId="xyxsh" value="xyxsh"/><bean:message key="lable.xb" />��� - ѧУ���</span>
										 		<span id="sbsh"><html:radio  property="save_shjb" styleId="bsh" value="no"/>�����</span>
										 		<font color="blue">
										 		<span id="xyNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ܹ�ѡ����������Ҳ��ͬ��</font>
										 		
										 	</td>
										 </tr>
								</logic:empty>
								<logic:notEmpty name="rs">
									 	<tr id="fdysz" style="display:none">
											<th>
												�������
											</th>
											<td colspan="3">
									 			<span id="sxysh"><html:radio name="rs" property="save_shjb" styleId="xysh" value="xysh"/><bean:message key="lable.xb" />���</span>
										 		<span id="sxxsh"><html:radio name="rs" property="save_shjb" styleId="xxsh" value="xxsh"/>ѧУ���</span>
										 		<font color="blue">
										 		<span id="xyNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʾ��������Ŀ�����ʱ��������̣���ͬ����������Ȩ�ޣ�</font>
										 		<br/>
										 		<span id="sxyxsh"><html:radio name="rs" property="save_shjb" styleId="xyxsh" value="xyxsh"/><bean:message key="lable.xb" />��� - ѧУ���</span>
										 		<span id="sbsh"><html:radio name="rs" property="save_shjb" styleId="bsh" value="no"/>�����</span>
										 		<font color="blue">
										 		<span id="xyNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ܹ�ѡ����������Ҳ��ͬ��</font>
										 	</td>
										 </tr>
						 		</logic:notEmpty>
							<tr>
								<th align="right">
									�ɷ�ȷ��
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_kfqr" styleId="kqr" value="yes" onclick="$('showMdqr').style.display=''"></html:radio>����
										<html:radio property="save_kfqr" styleId="fqr" value="no" onclick="$('showMdqr').style.display='none'"></html:radio>�ر�
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_kfqr" styleId="kqr"  value="yes" onclick="$('showMdqr').style.display=''"></html:radio>����
										<html:radio name="rs" property="save_kfqr" styleId="fqr" value="no" onclick="$('showMdqr').style.display='none'"></html:radio>�ر�
									</logic:notEmpty>
									<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��ʾ��������Ŀ�Ƿ񿪷�ȷ���������ܡ�</font>
								</td>
							</tr>
							<tr id="showMdqr" style="display:none">
								<th align="right">
									����ȷ��
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_mdqr" value="fdy"/>����Ա
									 	<html:radio property="save_mdqr" value="xy"/><bean:message key="lable.xb" />
									 	<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	��ʾ��������Ŀ������ȷ��ʱ��Ȩ�ޡ�</font>
									 	<br/>
									 	<html:radio property="save_mdqr" value="xx"/>ѧУ
									 	<html:radio property="save_mdqr" styleId="wxzqr" value="no"/>��ȷ��
									</logic:empty>
									<logic:notEmpty name="rs">
									 	<html:radio name="rs"  property="save_mdqr" value="fdy"/>����Ա
									 	<html:radio name="rs"  property="save_mdqr" value="xy"/><bean:message key="lable.xb" />
									 	<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	��ʾ��������Ŀ������ȷ��ʱ��Ȩ�ޡ�</font>
									 	<br/>
									 	<html:radio name="rs"  property="save_mdqr" value="xx"/>ѧУ
									 	<html:radio name="rs"  property="save_mdqr" value="no"/>��ȷ��
									 </logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th align="right">
									����
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_szzq" value="xn" ></html:radio>ѧ��
										<html:radio property="save_szzq" value="xq"></html:radio>ѧ��
										<html:radio property="save_szzq" value="nd"></html:radio>���
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʾ��������Ŀ����������ʱ�������ԣ�</font>
										<br/>
										<html:radio property="save_szzq" styleId="wzq" value="wzq" ></html:radio>������
										<html:radio property="save_szzq" value="jyc"></html:radio>��һ��
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										ͬʱӰ����˺�������ȷ�ϡ�</font>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_szzq" value="xn"></html:radio>ѧ��
										<html:radio name="rs" property="save_szzq" value="xq"></html:radio>ѧ��
										<html:radio name="rs" property="save_szzq" value="nd"></html:radio>���
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ʾ��������Ŀ����������ʱ�������ԣ�</font>
										<br/>
										<html:radio name="rs" property="save_szzq" value="wzq"></html:radio>������
										<html:radio name="rs" property="save_szzq" value="jyc"></html:radio>��һ��
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										ͬʱӰ����˺�������ȷ�ϡ�</font>
									</logic:notEmpty>
									
								</td>
							</tr>
								
							
							</tbody>
							<tfoot>
								<tr>
									 <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
										<div class="btn">
											<logic:equal name="doType" value="add">
												<button onclick="saveXmsz();return false;">�� ��</button>
											</logic:equal>
											<logic:equal name="doType" value="modi">
												<button onclick="updateXmsz();return false;">�� ��</button>
											</logic:equal>
											<button onclick="Close();return false;">�� ��</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
						</div>
						<logic:present name="result">
						<script>
							alert(''+$('message').value);
							if (window.dialogArguments) {
								window.close();
								window.dialogArguments.document.getElementById('search_go').click();
							}
						</script>
						</logic:present>
		</html:form>
	</body>
</html>
