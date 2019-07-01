<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
<script>
	function save(){
		//�����ֶ��Ƿ���д
		if(filedNotNull(['xh','xm','xydm','zydm','bjdm','nj'])){
			//�ύ
			refreshForm('xxcshgl.do?method=xsxxUpdate&doType=save');
		}else{
			alert('�뽫��*�ŵ���Ŀ��д������');
			return false;
		}	
	}
	//��ʼ��ҳ��
	function initPage(){
		dwr.engine.setAsync(false);
		//����
		var jgs = val('jgsV');
		if(jgs != ''){
			loadShi('jgs','jgshi','jgx');	
			var jgshi = val('jgshiV');
			if(jgshi != ''){
				setVal('jgshi',jgshi);
				loadXian('jgshi','jgx');				
			}		
			setVal('jgx',val('jgxV'));
		}
		//��Դ��
		var sys = val('sysV');
		if(sys != ''){
			loadShi('syshen','syshi','syxian');			
			var syshi = val('syshiV');
			if(syshi != ''){
				setVal('syshi',syshi);
				loadXian('syshi','syxian');				
			}
			setVal('syxian',val('syxV'));
		}
		//�������ڵ�
		var hks = val('hkshenV');
		if(hks != ''){
			loadShi('hkshen','hkshi','hkxian');
			var hkshi = val('hkshiV');
			if(hkshi != ''){
				setVal('hkshi',hkshi);
				loadXian('hkshi','hkxian');
			}
			setVal('hkxian',val('hkxianV'));
		}
		dwr.engine.setAsync(true);
	}
</script>
</head>

<body onload="initPage()">
	<html:form action="/xxcshgl.do?method=xsxxUpdate" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>

		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4"> 
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="15%"><font color="red">*</font>ѧ��</th>
						<td>
							${rs.xh}
							<input type="hidden" name="save_xh" value="${rs.xh}"/>						
						</td>
						<th width="15%"><font color="red">*</font>����</th>
						<td>
							<input type="text" name="save_xm" value="${rs.xm}" id="xm" maxlength="16" class="text_nor"/>
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							<html:radio name="rs" property="save_xb" value="1">��</html:radio>
							<html:radio name="rs" property="save_xb" value="2">Ů</html:radio>							
						</td>
						<th>��������</th>
						<td>
							<input type="text" name="save_csrq" value="${rs.csrq}" onclick="return showCalendar('csrq','y-mm-dd');" id="csrq" class="text_nor"/>
						</td>
					</tr>
				<tr>
					<th>����</th>
					<td>
						<html:select name="rs" property="save_mz" styleId="mz" style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<th>������ò</th>
					<td>
						<html:select name="rs" property="save_zzmm" styleId="zzmm" style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>���֤��</th>
					<td align="left">
						<div class="pos" style="z-index:1">
							<html:text name="rs" property="save_sfzh" styleId="sfzh" styleClass="text_nor" onblur="checkTextError(this,'sfzhErrow')" />
							<div id="sfzhErrow" class="hide">
								<p>
									���֤�Ÿ�ʽ����ȷ
								</p>
							</div>
						</div>
						
					</td>
					<th>�������</th>
					<td align="left">
						<html:select name="rs" property="save_pycc" style="" onchange="" styleId="pycc">
							<html:options collection="xsccList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td colspan="3">
						<input type='hidden' id="jgsV" value="${rs.save_jgs}"/>
						<html:select name="rs" property="save_jgs" styleId="jgs" onchange="loadShi('jgs','jgshi','jgx');">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select> ʡ/��
						<input type='hidden' id="jgshiV" value="${rs.save_jgshi}"/>
						<html:select name="rs" property="save_jgshi" styleId="jgshi"
							onchange="loadXian('jgshi','jgx')">
						</html:select> ��
						<input type='hidden' id="jgxV" value="${rs.save_jgx}"/>
						<html:select name="rs" property="save_jgx" styleId="jgx">
						</html:select> ��/��
					</td>
				</tr>
				<tr>
					<th>��Դ����<br/>(��Դ��)</th>
					<td colspan="3">
						<input type='hidden' id="sysV" value="${rs.save_syds}"/>
						<html:select name="rs" property="save_syds" styleId="syshen" onchange="loadShi('syshen','syshi','syxian')">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select> ʡ/��
						<input type='hidden' id="syshiV" value="${rs.save_sydshi}"/>
						<html:select name="rs" property="save_sydshi" styleId="syshi"
							onchange="loadXian('syshi','syxian')">								
						</html:select> ��
						<input type='hidden' id="syxV" value="${rs.save_sydx}"/>
						<html:select name="rs" property="save_sydx" styleId="syxian">								
						</html:select> ��/��							
					</td>
				</tr>
				<thead>
					<tr>
			        	<th colspan="5"><span>ѧ����Ϣ</span></th>
			        </tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>�꼶</th>
		            <td>
			            <html:select name="rs" property="save_nj" styleId="nj"
			              style="width:90px" onchange="initZyList();initBjList();">
			              <html:option value=""></html:option>
			              <html:options collection="njList" property="nj"
			                labelProperty="nj" />
			            </html:select>
		          	</td>
					<th>ѧ��(��)</th>
					<td>
						<html:text name="rs" property="save_xz"
							onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="3" styleClass="text_nor"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
          			<td>
						<html:select name="rs" property="save_xydm" styleId="xy"
							style="width:180px" onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<logic:notEmpty name="xyList">
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
							 </logic:notEmpty>
						</html:select>
						<input type="hidden" name="xyV" value="${xydm}" />
					</td>
					<th>�Ƿ�ע��</th>
					<td>
						<html:select property="save_sfzc" name="rs" style="width:120px" styleId="sfzc">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>רҵ</th>
					<td>
						<html:select name="rs" property="save_zydm" style="width:180px"
							styleId="zy" onchange="initBjList();">
							<html:option value=""></html:option>
							<logic:notEmpty name="zyList">
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</logic:notEmpty>
						</html:select>
						<input type="hidden" name="zyV" value="${xydm}" />
					</td>
					<th>�Ƿ��߶���</th>
					<td>
						<html:select property="save_sfzd" name="rs" style="width:120px" styleId="sfzd">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>�༶</th>
					<td>
						<html:select name="rs" property="save_bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<logic:notEmpty name="bjList">
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</logic:notEmpty>
						</html:select>
						<input type="hidden" name="bjV" value="${bjdm}" />
					</td>
					<th>�Ƿ���У</th>
					<td>
						<html:select property="save_sfzx" name="rs" styleId="sfzx">
							<html:option value=""></html:option>
							<html:option value="��У">��У</html:option>
							<html:option value="����У">����У</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>ѧ��״̬</th>
					<td>
						<html:select name="rs" property="save_xjztm" style="width:150" styleId="xjzt">
							<html:option value=""></html:option>
							<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
						</html:select>
					</td>
					<th>�Ƿ��ҵ��</th>
					<td>
						<html:select property="save_sfbys" name="rs" style="width:120px" styleId="sfbys">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��ѧʱ��</th>
					<td align="left">
						<html:text name="rs" property="save_rxrq" maxlength="10" styleId="rxrq" styleClass="text_nor" onclick="return showCalendar('rxrq','y-mm-dd');" />
					</td>
					<th>�Ƿ��ҵ</th>
					<td>
						<html:select property="save_sfyby" name="rs" style="width:120px" styleId="sfyby">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��ҵʱ��</th>
					<td colspan="3">
						<html:text property="save_bysj" name="rs"  styleId="bysj" styleClass="text_nor" readonly="true"
							maxlength="10" onclick="return showCalendar('bysj','y-mm-dd');"/>
					</td>
				</tr>
				</tobdy>
				<thead>
					<tr>
			        	<th colspan="5"><span>��ϵ��ʽ��֤��</span></th>
			        </tr>
				</thead>
				<tbody>
				<tr>
					<th>�̶��绰</th>
					<td>
						<html:text name="rs" property="save_lxdh"
							onkeyup="value=value.replace(/[^\d]/g,'') " styleClass="text_nor" maxlength="13" />
					</td>
					<th>�ֻ�����</th>
					<td>
						<html:text name="rs" property="save_sjhm"
							onkeyup="value=value.replace(/[^\d]/g,'') " styleClass="text_nor" maxlength="11" />
					</td>
				</tr>
				<tr>
					<th>QQ����</th>
					<td>
						<html:text name="rs" property="save_qqhm"
							onkeyup="value=value.replace(/[^\d]/g,'') " styleClass="text_nor" maxlength="20" />
					</td>
					<th>��������</th>
					<td>
						<div class="pos" style="z-index:1">
							<html:text name="rs" property="save_dzyx" styleClass="text_nor" styleId="dzyx" onblur="checkTextError(this,'emaliErrow')"/>
							<div id="emaliErrow" class="hide">
								<p>
									���������ʽ����ȷ
								</p>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>
						<html:select name="rs" property="save_yhdm"  styleId="yhdm">
							<html:option value=""></html:option>
							<html:options collection="yhList" property="yhdm" labelProperty="yhmc"/>
						</html:select>
					</td>
					<th>���п���</th>
					<td>
						<html:text name="rs" property="save_yhkh" maxlength="20"
							onkeyup="value=value.replace(/[^\d]/g,'') " styleClass="text_nor"/>
					</td>
				</tr>	
				<tr>					
					<th>һ��ͨ��</th>
					<td colspan="3">
						<html:text name="rs" property="save_kh" 
							onkeypress="return onlyNum(this,8)"
							style="ime-mode:disabled" maxlength="20"/>
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
			        	<th colspan="5"><span>������Ϣ</span></th>
			        </tr>
				</thead>
				<tbody>
				<tr>
					<th>����ƴ��</th>
					<td>
						<html:text name="rs" property="save_xmpy" maxlength="64" styleClass="text_nor" onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " />
					</td>
					<th>������</th>
					<td align="left" colspan="2">
						<html:text name="rs" property="save_cym" styleClass="text_nor" maxlength="16" />
					</td>
					
				</tr>
				<tr>
					<th>���(cm)</th>
					<td align="left">
						<html:text name="rs" property="save_sg"
							onkeyup="value=value.replace(/[^\d]/g,'') " styleClass="text_nor" maxlength="3" />
					</td>
					<th>����(kg)</th>
					<td colspan="2">
						<html:text name="rs" property="save_tz"
							onkeyup="value=value.replace(/[^\d|.]/g,'') " styleClass="text_nor" maxlength="4" />
					</td>
				</tr>
				<tr>					
					<th>�س�</th>
					<td>
						<html:text name="rs" property="save_tc" styleClass="text_nor" maxlength="32" />
					</td>
					<th>�������</th>
					<td align="left" colspan="2">
						<html:text name="rs" property="save_kslb" styleClass="text_nor" maxlength="32" />
					</td>
				</tr>
				<tr>
					<th>��ѧ��ʽ</th>
					<td align="left">
						<html:text name="rs" property="save_rxfs" styleClass="text_nor" maxlength="32" />
					</td>
					<th>������ʽ</th>
					<td align="left" colspan="2">
						<html:text name="rs" property="save_pyfs" styleClass="text_nor" maxlength="32" />
					</td>
				</tr>
				<tr>
					<th>�������ڵ�</th>
					<td colspan="4">
						<input type='hidden' id="hkshenV" value="${rs.save_hkshen}"/>
						<html:select name="rs" property="save_hkshen" styleId="hkshen" onchange="loadShi('hkshen','hkshi','hkxian');">
							<html:option value="">--��ѡ��--</html:option>
							<html:options collection="ssList" property="ssdm"
								labelProperty="ssmc" />
						</html:select> ʡ/��
						<input type='hidden' id="hkshiV" value="${rs.save_hkshi}"/>
						<html:select name="rs" property="save_hkshi" styleId="hkshi"
							onchange="loadXian('hkshi','hkxian')">
						</html:select> ��
						<input type='hidden' id="hkxianV" value="${rs.save_hkxian}"/>
						<html:select name="rs" property="save_hkxian" styleId="hkxian">
						</html:select> ��/��
					</td>
				</tr>	
				
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<div class="buttontool">
			            <logic:notEqual value="view" name="doType">
							<button type="button" class="" onclick="save();return false;" id="saveButton">
								��&nbsp;&nbsp;��
							</button>
			            </logic:notEqual>
						<button type="button" class="" onclick="Close();return false;">
								��&nbsp;&nbsp;��
						</button>
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
	</html:form>
</body>
</html>