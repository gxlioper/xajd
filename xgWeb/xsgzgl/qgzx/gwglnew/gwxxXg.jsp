<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initData();
				changeYxssz();
				if(jQuery("#xxdm").val() == "10351"){
					jQuery("#qxCheck").bind("click",function(){
						xz(this);
					});
				}
			});
			function selTea(){
				showDialog("ѡ�����Ա", 770, 520, "stglStsq.do?method=getTea")	
			}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwglnew" method="post" styleId="form">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="oldGwxh" id="oldGwxh" value="${rs.gwxh }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden" id="xxdm" value="${xxdm}" />
			
			<!-- ��ʾ��Ϣ -->
			<div class="prompt" id="div_help" >
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
					���������޸�ʱӦ<font color="blue">���ڻ����</font>�ڸ�����
					</span>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<logic:equal name="cs" property="qgzq" value="xn">
								         ѧ��
                                </logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									ѧ��ѧ��
								</logic:equal>
							</th>
							<td width="34%" colspan="3" >
								<logic:equal name="cs" property="qgzq" value="xn">
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
									<input type="hidden" id="xq" name="xq" value=""/>
									${rs.xn }
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									<input type="hidden" id="xq" name="xq" value="${rs.xq }"/>
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
									${rs.xn }&nbsp;&nbsp;&nbsp;${rs.xqmc }
								</logic:equal>
							</td>
							<th width="16%">
								���˲���
							</th>
							
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font class="red">*</font>��λ����
							</th>
							
							<td width="34%" colspan="3" >
								<html:text name="rs" property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								<font class="red">*</font>��λ����
							</th>
							<td width="34%" colspan="3" >
								<logic:equal value="10704" name="xxdm">
									<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" onchange="gwxzChange(this)">
										<html:option value="">---------��ѡ��---------</html:option>
										<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
									</html:select>
								</logic:equal>	
								<logic:notEqual value="10704" name="xxdm">
									<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" >
										<html:option value="">---------��ѡ��---------</html:option>
										<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font class="red">*</font>��������
							</th>
							<td width="34%" colspan="3" >
								<html:text name="rs" property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkInputNum(this)"></html:text>
								<html:hidden name="rs" property="zgrs" styleId="zgrs"/>
								(�ڸ�������<font color="red" >${rs.zgrs}</font>)
							</td>
							<th width="16%">
								<font class="red">*</font>��������
							</th>
							<td width="34%" colspan="3">
								<html:text name="rs" property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (��)
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						<tr>
							<th><font color="red">*</font>���˵�λ���</th>
							<td colspan="3">
								<html:select property="zxdwlb" styleId="zxdwlb" value="${rs1.zxdwlb}" style="width:200px;" >
								    <html:option value="">---------��ѡ��---------</html:option>
									<html:options collection="yrdwlblist" property="zxdwlbdm" labelProperty="zxdwlbmc"/>
								</html:select>
							</td>
							<th><font color="red">*</font>�ʽ���Դ</th>
							<td colspan="3">
								<html:select property="zjly" styleId="zjly"  value="${rs1.zjly}" style="width:200px;" >
									<html:option value="">---------��ѡ��---------</html:option>
									<html:options collection="zjlylist" property="zjlydm" labelProperty="zjlymc"/>
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th><span class="red">*</span>��Чʱ����</th>
							<td colspan="3">
							   <logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio name="rs" property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
							<th><span class="red">*</span>�Ƿ��ܸ�λ����������</th>
							<td colspan="3">
							   <logic:present name="isnotList">
									<logic:iterate id="o" name="isnotList" >
										<html:radio name="rs" property="sfsgwsqsxz" onclick="" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<logic:equal value="11488" name="xxdm">
							<tr>
								<th><font color="red">*</font>��λ����</th>
								<td colspan="3">
									<html:select property="gwlx" styleId="gwlx" value="${rs.gwlx}">
										<html:option value=""></html:option>
										<html:option value="�̶���">�̶���</html:option>
										<html:option value="��ʱ��">��ʱ��</html:option>
									</html:select>
								</td>
								<th><font color="red">*</font>��ѧ������Ա</th>
					            <td colspan="3">
									<html:text property="fzls" styleId="zxfdy" maxlength="10" value="${rs.fzls}" />
					            </td>
							</tr>
							<tr>
								<th><font color="red">*</font>��ϵ�绰</th>
								<td colspan="3"><html:text  property="lxdh" value="${rs.lxdh}"  styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" ></html:text></td>
								<th></th>
								<td colspan="3"></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<tr>
							<th><font color="red">*</font>��������</th>
							<td colspan="3">
								<html:hidden name="rs" property="ssbm" styleId="ssbm"/>
								${rs.ssbmmc}
							</td>
							<th><font color="red">*</font>������ʦ</th>
				            <td colspan="3">
				            		<input type="text" name="stfzrxm" style="width:80px;" id="stfzrxm" readonly="true" value="${rs.fzlsxm}" maxlength="10"/>
				            		<html:hidden name="rs"  property="fzls" styleId="stfzr"  />
									<button class="btn_01" onclick="selTea()"  type="button">ѡ��</button>
				            </td>
						</tr>
						<tr>
							<th><font color="red">*</font>��ϵ�绰</th>
							<td colspan="3"><html:text name="rs"  property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" ></html:text></td>
							<th><font color="red">*</font>������ʦ�칫��</th>
				            <td colspan="3">
				            	<html:text property="lsbgs" name="rs" styleId="lsbgs" maxlength="40"></html:text>
				            </td>
						</tr>
						
						</logic:equal>

						<logic:equal name="xxdm" value="10351">
							<tr>
								<th><font color="red">*</font>��ϵ��</th>
								<td colspan="3">
									<html:text  property="lxr" styleId="lxr" maxlength="15" onkeyup=""  value="${rs.lxr}"/>
								</td>
								<th><font color="red">*</font>��ϵ�绰</th>
								<td colspan="3"><html:text  property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)"   value="${rs.lxdh}"/></td>

							</tr>
						</logic:equal>

						<tr>
							<th><span class="red">*</span>��λ��ʼ����</th>
							<td colspan="3">
								<html:text name="rs" property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
							<th id="gwjssj_th"></th>
							<td colspan="3" id="gwjssj_td">
								<html:text name="rs" property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						<logic:equal value="12867" name="xxdm">
						<tr id="zxsInfo">
						<th><span class="red">*</span>��λ�����</th>
								<td colspan="3">
								<input name="gwshr" id="gwshr" class="gwshr" style="width:120px;" value="${rs.gwshr}" readonly="readonly"/>
									<button type="button" onclick="showDialog('��ʦѡ��',680,480,'qgzx_gwglnew.do?method=showFdys');return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
							</td>
							<th>��λ���������</th>
								<td colspan="3" >
								<input name="gwshrxm" id="gwshrxm" class="gwshrxm" style="width:120px;" value="${rs.gwshrxm}" readonly="readonly"/>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th><span class="red">*</span>��λ����׼</th>
							<td colspan="3">
								<html:text name="rs" property="gwcjbz" readonly ="true" styleId="gwcjbz" style="width:80px" ></html:text> Ԫ/Сʱ
							</td>
							<th><span class="red">*</span>���ѻ���</th>
							<td colspan="3">
								<html:text name="rs" property="jfhb" styleId="jfhb" style="width:80px" onblur="checkJe(this)"></html:text> Ԫ/Сʱ
							</td>
						</tr>
						<tr>
							<th>�Գ�</th>
							<td colspan="6">
								<html:text  name="rs" property="zc" styleId="zc" style="width:80px" onblur="checkJe(this)"></html:text> Ԫ/Сʱ
							</td>
						</tr>
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>
								<logic:equal name="xxdm" value="10344">
								 �±���Ԥ��
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								��λ�������
							    </logic:notEqual>
							</th>
							<td width="34%" colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>Ԫ/�� &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr id="gdgcjbzTr" style="display:none">
								<th width="16%">
									�̶��ڳ���׼
								</th>
								<td width="34%" colspan="7">
									<span id="gdgcjbzSpan">${jcpz.gdgcjbz}</span>
									<span>Ԫ/��  &nbsp;&nbsp;(�̶���ÿ�³���׼)</span>
								</td>
							</tr>
						</logic:equal>
							<logic:equal value="10344" name="xxdm">
							<tr>
								<th align="right" >
									<font color="red">*</font>����У��
								</th>
								<td colspan="7" >
									<html:select property="ssxq" styleId="ssxq" style="width:80%" name="rs">
										<html:options collection="xqList" property="dm" labelProperty="xqmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<span class="red">*</span>�����ص�
								</th>
								<td colspan="7" >
									<html:text name="rs" property="gzdd" style="width:97%" styleId="gzdd" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<span class="red">*</span>����ʱ��
								</th>
								<td colspan="7" >
									<html:text name="rs" property="gzsj" style="width:97%"  styleId="gzsj" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<span class="red">*</span>��������
								</th>
								<td colspan="7" >
									<html:textarea name="rs" property="gznr" style="width:97%" rows="5" styleId="gznr" onblur="chLengs(this,500);"/>
								</td>
							</tr>
							
						</logic:equal>
						<!--���ݴ�ѧ���Ի���λ����ѧԺ��Χ���Ի�-->
						<logic:equal value="10351" name="xxdm">
							<tr>
								<th>
									<font color="red">*</font>��λ���뿪��ѧԺ
									<input type="checkbox" id="qxCheck"/>ȫѡ
								</th>							
								<td colspan="7">
									<logic:iterate id="t" name="xyList" indexId="index">
										<font style="width:100px;">
										<html:multibox property="sqxy" value="${t.xydm}"></html:multibox>${t.xymc}
										<%if((index+1)%5==0){%> </br> <%}%>
										</font>
									</logic:iterate>
								</td>						
							</tr>
						</logic:equal>
						<logic:notEqual value="10344" name="xxdm">
							<tr style="height:40px">
								<th align="right" >
									<font class="red">*</font>��λ����<br/><font color="red">(��1000��)</font>
								</th>
								<td colspan="7" style="word-break:break-all;width:97%">
									<html:textarea  name="rs" property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
								</td>
							</tr>
						</logic:notEqual>
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>
								<logic:equal name="xxdm" value="10344">
								   �Ͷ�ǿ�ȼ�����Ҫ��
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  ��λ��ԱҪ��
							    </logic:notEqual>
								
								<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								��λԤ����ԱЧ��<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
							<logic:equal name="xxdm" value="10344">
								�ù�����
							</logic:equal>
							<logic:notEqual name="xxdm" value="10344">
								��ע
							</logic:notEqual>
							<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='6'   />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="bcXgGwxx()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

