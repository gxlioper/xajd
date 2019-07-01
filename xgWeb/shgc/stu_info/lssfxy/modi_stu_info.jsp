<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>	
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script>
		function change(){
			var num=document.getElementById("num").value;
			for(var i=1;i<(num*1+1);i++){
				document.getElementById("jtcy"+i).style.display='';
			}
			for(var j=1;j<11-num;j++){							
				document.getElementById("jtcy"+(num*1+j)).style.display='none';
			}
		}
		
		function initPage(){
				document.getElementById("num").value=1;
				change();
		}
		
		function checkPage(){
			var ssbh = document.getElementById("ssbh").value;
			var qsdh = document.getElementById("qsdh").value;
			var flag = false;
			getStuDetails.checkQsdhAndQsbh(ssbh,qsdh,function(data){
				if(data==""){
					flag = true;
					refreshForm('stu_info_add.do?method=modiStuInfo&act=save')
				}else{
					alert(data+",��˶�׼ȷ��");
					flag =  false;
				}
			});			
			return flag;
		}
		
		function loadShi(){
			var shen = document.getElementById("jgshen").value;	
			getStuDetails.getShiList(shen,function(data){
				if (data.shiList != null) {
						var objId = "jgshi";				
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);					
							DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}	
				if (data.xianList !=null){
					var objId = "jgxian";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);					
							DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
						}
				}
			});
		}
	
		function loadXian(){
			var shi = document.getElementById("jgshi").value;	
			getStuDetails.getXianList(shi,function(data){
				if (data != null) {
						var objId = "jgxian";
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);							
							DWRUtil.addOptions(objId,data,"xiandm","xianmc");
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}		
			});
		}
		function loadInit(){
			var xxdm = document.getElementById("xxdm").value;
			if(xxdm == "10690"){
				var sfdkVal = document.getElementById("sfdkVal").value;
				if(sfdkVal =="" || sfdkVal==null){
					document.getElementById("sfdkf").checked=true;
				}
			}
		
		}
		
		function saveInfo(){
			var xxdm = document.getElementById("xxdm").value;
			document.getElementById("jg").value="";
			if(xxdm == "10690"){//��������<bean:message key="lable.xsgzyxpzxy" />
				var jgs = document.getElementById("jgshen").value;
				var jgshi = document.getElementById("jgshi").value;
				var jgx = document.getElementById("jgxian").value;
				var jg = document.getElementById("jg").value;
				if(jgs!="" && jgs!=null){
					jg += document.getElementById("jgshen").options[document.getElementById("jgshen").selectedIndex].text;
				}			
				if(jgshi!="" && jgshi!=null){
					jg += document.getElementById("jgshi").options[document.getElementById("jgshi").selectedIndex].text;
				}
				if(jgx!="" && jgx!=null){
					jg += document.getElementById("jgxian").options[document.getElementById("jgxian").selectedIndex].text;
				}
			}
			if(xxdm == "10856"){//�Ϻ�����
				if($("sfzh")){
					var sfzh = document.getElementById('sfzh').value;
					if(sfzh!=""){
						if(!checkSfzh(sfzh)){
							return false;
						}
					}
				}
			}
			document.getElementById("jg").value=jg;
			refreshForm('stu_info_add.do?method=modiStuInfo&act=save');
		}
	</script>
</head>
	<body onload="loadInit();">
		<html:form action="/stu_info_add.do" method="post">
			<input type="hidden" id="url" name="url" value="/stu_info_add.do?method=modiStuInfo" />
			<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-xh" />
			<input type="hidden" id="sfdkVal" value="${rs.sfdk}" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<html:hidden property="jg" styleId="jg" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em>
					<a>
						<logic:equal name="userOnLine" value="student" scope="session">
							ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ
						</logic:equal>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							ѧ����Ϣ - ��Ϣ�޸� - �޸�ѧ����ͥ��Ϣ
						</logic:equal>
					</a>
				</p>
			</div>

			<logic:equal name="userType" value="admin" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</p>
			</logic:equal>

			<logic:equal name="userType" value="xx" scope="session">
				<br />
				<br />
				<br />
				<p align="center">
					��ҳ��ֻ��ѧ����<bean:message key="lable.xsgzyxpzxy" />�û����Է���
				</p>
			</logic:equal>

			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("�����趨ʱ�䷶Χ��,�ݲ������޸�!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>

			<logic:equal name="dataSaved" value="ok" scope="request">
				<script>
  					alert("����ɹ���");
  				</script>
			</logic:equal>
			<logic:present name="rs">
				<div class="tab">	
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ��������Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								<bean:write name="userName" scope="session" />
							</td>
							<th>����</th>
							<td>
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
							<th>רҵ</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
						</tr>
						<tr>
							<th>�꼶</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>�༶</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<th>��ϵ�绰</th>
							<td>
								<input type="text" style="width:200px" name="lxdh" id="lxdh"
									value="<bean:write name="rs" property="lxdh"/>"
									maxlength="120" onkeyup="value=value.replace(/[^\d|\-]/g,'') " />
							</td>
							<th>QQ����</th>
							<td>
								<input type="text" style="width:200px" name="qqhm" id="qqhm"
									value="<bean:write name="rs" property="qqhm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
							</td>
						</tr>
						<tr>
							<th>�ֻ�����</th>
							<td>
								<input type="text" style="width:200px" name="sjhm" id="sjhm"
									value="<bean:write name="rs" property="sjhm"/>"
									onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
							</td>
							<th>E-Mail</th>
							<td>
								<input type="text" style="width:200px" name="email" id="dzyx"
									value="<bean:write name="rs" property="email"/>" maxlength="32"/>
							</td>
						</tr>
						<tr>
							<th>��ͥͨѶ��ַ</th>
							<td>
								<input type="text" style="width:200px" name="jtszd" id="jtszd"
									value="<bean:write name="rs" property="jtszd"/>"
									maxlength="25" />
							</td>
							<th>��ͥ�ʱ�</th>
							<td>
								<input type="text" style="width:200px" name="yb" id="yb"
									value="<bean:write name="rs" property="yb"/>" maxlength="10"
									onkeyup="value=value.replace(/[^\d]/g,'') " />
							</td>
						</tr>
						<tr>
							<th>��ͥ�绰</th>
							<td>
								<input type="text" style="width:200px" name="lxdh1" id="lxdh1"
									value="<bean:write name="rs" property="lxdh1"/>"
									maxlength="50" onkeyup="value=value.replace(/[^\d|\-]/g,'') " />
							</td>
							<th>��ͥ����״��</th>
							<td>
								<input type="text" style="width:200px" name="jjzk" id="jjzk"
									value="<bean:write name="rs" property="jjzk"/>" maxlength="100"/>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt1').style.display=(document.getElementById('jt1').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ1
								</th>
							</tr>
						</thead>
						<tbody>
						<tr id="jt1">
							<td colspan="4">
								<table width="100%" class="formlist">
									<tbody>
									<tr>
										<th>����</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_xm"
												id="jtcy1_xm"
												value="<bean:write name="rs" property="jtcy1_xm"/>"
												maxlength="25" />
										</td>
										<th>�뱾�˹�ϵ</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_gx"
												id="jtcy1_gx"
												value="<bean:write name="rs" property="jtcy1_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<th>��������</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_nl" id="jtcy1_nl"
												value="<bean:write name="rs" property="jtcy1_nl"/>"
												onclick="return showCalendar('jtcy1_nl','y-mm-dd');" 
												readonly="readonly"
												maxlength="10" />
										</td>
										<th>���֤����</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_sfzh"
												id="jtcy1_sfzh"
												value="<bean:write name="rs" property="jtcy1_sfzh"/>"
												maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>����</th>
										<td>
											<html:select property="jtcy1_mz" name="rs">
												<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
											</html:select>
										</td>
										<th>������ò</th>
										<td>
											<html:select property="jtcy1_zzmm" name="rs">
												<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
											</html:select>
										</td>
									</tr>
									<tr>
										<th>ְ��</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_zw"
												id="jtcy1_zw"
												value="<bean:write name="rs" property="jtcy1_zw"/>"
												maxlength="10" />
										</td>
										<th>ְҵ</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_zy"
												id="jtcy1_zy"
												value="<bean:write name="rs" property="jtcy1_zy"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<th>������λ�绰</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_lxdh1"
												id="jtcy1_lxdh1"
												value="<bean:write name="rs" property="jtcy1_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d|\-]/g,'') " maxlength="13" />
										</td>
										<th>���˵绰</th>
										<td>
											<input type="text" style="width:200px" name="jtcy1_lxdh2"
												id="jtcy1_lxdh2"
												value="<bean:write name="rs" property="jtcy1_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d|\-]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<th>������ַ</th>
										<td>
											<input type="text" name="jtcy1_gzdz"
												id="jtcy1_gzdz"
												value="<bean:write name="rs" property="jtcy1_gzdz"/>"
												maxlength="25" />
										</td>
										<th>��Ҫ����ϵ��Ϣ</th>
										<td>
											<input type="text" name="zyshgxxx1"
												id="zyshgxxx1"
												value="<bean:write name="rs" property="zyshgxxx1"/>"
												maxlength="150" />
										</td>											
									</tr>
									</tbody>
								</table>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt2').style.display=(document.getElementById('jt2').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ2
								</th>
							</tr>
						</thead>
						<tbody>
						<tr id="jt2" style="display:none">
							<td colspan="4">
								<table width="100%" class="formlist">
									<tbody>
									<tr>
										<th>����</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_xm"
												id="jtcy2_xm"
												value="<bean:write name="rs" property="jtcy2_xm"/>"
												maxlength="25" />
										</td>
										<th>�뱾�˹�ϵ</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_gx"
												id="jtcy2_gx"
												value="<bean:write name="rs" property="jtcy2_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<th>��������</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_nl" id="jtcy2_nl"
												value="<bean:write name="rs" property="jtcy2_nl"/>"
												onclick="return showCalendar('jtcy2_nl','y-mm-dd');" 
												readonly="readonly"
												maxlength="10" />
										</td>
										<th>���֤����</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_sfzh"
												id="jtcy2_sfzh"
												value="<bean:write name="rs" property="jtcy2_sfzh"/>"
												maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>����</th>
										<td>
											<html:select property="jtcy2_mz" name="rs">
												<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
											</html:select>
										</td>
										<th>������ò</th>
										<td>
											<html:select property="jtcy2_zzmm" name="rs">
												<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
											</html:select>
										</td>
									</tr>
									<tr>
										<th>ְ��</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_zw"
												id="jtcy2_zw"
												value="<bean:write name="rs" property="jtcy2_zw"/>"
												maxlength="10" />
										</td>
										<th>ְҵ</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_zy"
												id="jtcy2_zy"
												value="<bean:write name="rs" property="jtcy2_zy"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<th>������λ�绰</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_lxdh1"
												id="jtcy2_lxdh1"
												value="<bean:write name="rs" property="jtcy2_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d|\-]/g,'') " maxlength="13" />
										</td>
										<th>���˵绰</th>
										<td>
											<input type="text" style="width:200px" name="jtcy2_lxdh2"
												id="jtcy2_lxdh2"
												value="<bean:write name="rs" property="jtcy2_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d|\-]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<th>������ַ</th>
										<td>
											<input type="text" name="jtcy2_gzdz"
												id="jtcy2_gzdz"
												value="<bean:write name="rs" property="jtcy2_gzdz"/>"
												maxlength="25" />
										</td>
										<th>��Ҫ����ϵ��Ϣ</th>
										<td>
											<input type="text" name="zyshgxxx2"
												id="zyshgxxx2"
												value="<bean:write name="rs" property="zyshgxxx2"/>"
												maxlength="150" />
										</td>											
									</tr>
								</tbody>
								</table>
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4" style="cursor:hand" align="center"
									onclick="document.getElementById('jt3').style.display=(document.getElementById('jt3').style.display==''?'none':'')">
									ѧ����ͥ��Ա��Ϣ3
								</th>
							</tr>
						</thead>
						<tbody>
						<tr id="jt3" style="display:none">
							<td colspan="4">
								<table width="100%" class="formlist">
									<tbody>
									<tr>
										<th>����</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_xm"
												id="jtcy3_xm"
												value="<bean:write name="rs" property="jtcy3_xm"/>"
												maxlength="25" />
										</td>
										<th>�뱾�˹�ϵ</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_gx"
												id="jtcy3_gx"
												value="<bean:write name="rs" property="jtcy3_gx"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<th>��������</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_nl" id="jtcy3_nl"
												value="<bean:write name="rs" property="jtcy3_nl"/>"
												onclick="return showCalendar('jtcy3_nl','y-mm-dd');" 
												readonly="readonly"
												maxlength="10" />
										</td>
										<th>���֤����</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_sfzh"
												id="jtcy3_sfzh"
												value="<bean:write name="rs" property="jtcy3_sfzh"/>"
												maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>����</th>
										<td>
											<html:select property="jtcy3_mz" name="rs">
												<html:options collection="mzList" property="mzdm" labelProperty="mzmc"/>
											</html:select>
										</td>
										<th>������ò</th>
										<td>
											<html:select property="jtcy3_zzmm" name="rs">
												<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
											</html:select>
										</td>
									</tr>
									<tr>
										<th>ְ��</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_zw"
												id="jtcy3_zw"
												value="<bean:write name="rs" property="jtcy3_zw"/>"
												maxlength="10" />
										</td>
										<th>ְҵ</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_zy"
												id="jtcy3_zy"
												value="<bean:write name="rs" property="jtcy3_zy"/>"
												maxlength="10" />
										</td>
									</tr>
									<tr>
										<th>������λ�绰</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_lxdh1"
												id="jtcy3_lxdh1"
												value="<bean:write name="rs" property="jtcy3_lxdh1"/>"
												onkeyup="value=value.replace(/[^\d|\-]/g,'') " maxlength="13" />
										</td>
										<th>���˵绰</th>
										<td>
											<input type="text" style="width:200px" name="jtcy3_lxdh2"
												id="jtcy3_lxdh2"
												value="<bean:write name="rs" property="jtcy3_lxdh2"/>"
												onkeyup="value=value.replace(/[^\d|\-]/g,'') " maxlength="13" />
										</td>
									</tr>
									<tr>
										<th>������ַ</th>
										<td>
											<input type="text" name="jtcy3_gzdz"
												id="jtcy3_gzdz"
												value="<bean:write name="rs" property="jtcy3_gzdz"/>"
												maxlength="25" />
										</td>
										<th>��Ҫ����ϵ��Ϣ</th>
										<td>
											<input type="text" name="zyshgxxx3"
												id="zyshgxxx3"
												value="<bean:write name="rs" property="zyshgxxx3"/>"
												maxlength="150" />
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
				            <button class="button2"
								onclick="refreshForm('stu_info_add.do?method=modiStuInfo&act=save')">
								�� �� �� Ϣ
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
			</logic:present>

			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
