<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xsxx/xsxxzgkdFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxZgkd.js'></script>
	<script type='text/javascript' src='js/stuinfoFunction.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
		function zgdzdxSaveXsxx(){
			//����ʱ�ж�ѧ����Ϣ�Ƿ��Ѿ�����
			var result = true;
			dwr.engine.setAsync(false);
			xsxxZgkd.getBtzdByYh(val('yhjs'),"xsxxb",function(data){
				if(data != null){
					for(var i=0; i<data.length; i++){
						if(ele(data[i].en) && ele(data[i].en).value == ""){
							if(data[i].en == "syd"){
								alert("��Դ�ػ���Դ��������Ϊ�գ�");
							}else{
								alert(data[i].cn + "����Ϊ�գ�");								
							}
							result = false;
							break;
						}
					}
				}				
			});
			dwr.engine.setAsync(true);
			if(result){
				var doType = val('doType');
				var xh = val('xh');
				var tvalue = ["xh", "xm", "xy", "zy", "bj", "nj"];
				for(var i=0;i<tvalue.length;i++){
					if(document.getElementById(tvalue[i]).value==""){
					alert("�뽫�����ŵ���Ŀ��д������");
					return false;
					}
				}
				if($("sfzh")){//���֤�ż��
					var sfzh = document.getElementById('sfzh').value;
					if(sfzh!=""){
						if(!checkSfzh(document.getElementById('sfzh'))){
							return false;
						}
					}
				}
				if(doType == 'add'){
					getXjydInfo.getColumnEx("view_xsjbxx","xh",xh,function(data){			
							if(data==true){
								alert('ѧ���Ѿ����ڣ�');
								return false;
							}else{
								refreshForm('xsxxZgdzdx.do?method=saveStuinfo&doType=add');
								$("buttonSave").disabled=true;
							}					
					});
					
				}else{
					refreshForm('xsxxZgdzdx.do?method=saveStuinfo&doType=modi');
					$("buttonSave").disabled=true;
				}
			}
		}
		
 		function sfbyChek(){
			var nfby = document.getElementById('nfby').value;			
			var xh = document.getElementById('xh').value;
			    if(nfby=='��'){
				  //�ܷ��ҵ�ж�
				  getStuDetails.zgdzdxCheckBy(xh,function(data){
					   if(data==false){
						  alert('��ѧ���������ϱ�ҵ������');
						  document.getElementById("nfby").options[0].selected=true;				
						  return false;						
					   }
				  });
				}									
		 }
		 
		function sfzcChek(){
            var sfzc = document.getElementById('sfzc').value;
			var xh = document.getElementById('xh').value;  
			if(sfzc=='��ע��'){				
				//�Ƿ�ע���ж�
				getStuDetails.zgdzdxCheckZc(xh,function(data){
					if(data==false){
						alert('��ѧ����������ע����������ȷ�Ϸ����Ƿ���壡');
						document.getElementById("sfzc").options[0].selected=true;				
						return false;	
					}
				});
			}
 		}	
 		
 		function addZpsc(url) {
			var d_width = document.body.clientWidth;
			var d_height = document.body.clientHeight ;
			var d_left = 0;
			var d_top = 0;
			var d_color = "#EEF4F9";
			var d_width_top = 400;
			var d_height_top = 180;
			var d_left_top = (d_width - d_width_top) / 2;
			var d_top_top = (d_height - d_height_top) / 2;
			var d_color_top = "#EEF4F9";
			dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px;'>";
			dd_html += "</div>";
			dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
			dd_html += "<iframe name='mainFrame' style='height:100%; width:100%; ' marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' src='";
			dd_html += url;
			dd_html += "'></iframe>";
			dd_html += "</div>";
			tmpdiv1.innerHTML = dd_html;
		}		
	</script>
</head>
	<body onload="showColumns()">
		<html:form action="/xsxx_zgkd.do" method="post">
			<input type="hidden" id="zdList" name="zdList" value="<bean:write name="zdList"/>" />
			<input type="hidden" name="url" id="url" value="/xsxx/zgkd/zgkd_stu_modinfo.jsp"/>
			<input type="hidden" name="redirect" id="redirect" value=""/>
			<input type="hidden" name="variable" id="variable" value=""/>
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="sfzcV" id="sfzcV" value="${rs.sfzc}" />
			<input type="hidden" name="nfbyV" id="nfbyV" value="${rs.nfby}" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="yhjs" id="yhjs" value="${userType}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��Ϣ�޸� - �޸ĸ�����Ϣ</a>
				</p>
			</div>

			<logic:equal name="sqsjFlag" value="1">
				<script>
		   			 alert("�����趨ʱ�䷶Χ��,�ݲ����Ÿù���!");
		    		 location.href="about:blank";
   			 	</script>
			</logic:equal>

			<div class="tab">
			<table class="formlist" id="rsTable" width="100%">
				<thead>
					<tr>
						<th colspan="5">
							<span>������Ϣ�޸�</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>ѧ��</th>
					<td>
						<html:text property="xh" name="rs" styleId="xh" maxlength="20" />
					</td>
					<th><span class="red">*</span>����</th>
					<td>
						<html:text property="xm" name="rs" disabled="true" maxlength="16"
							styleId="xm" />
					</td>
					<td rowspan="6">
						<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
							border="0" style="width:140;height:160"/>
						<logic:equal value="update" name="doType">
						<div>
							<button type="button" onclick="if(val('xh') != ''){addZpsc('uploadPicture.do?method=uploadPicture&type=stu&id=<bean:write name='rs' property="xh"/>')}"
									id="buttonSave">
									�ϴ���Ƭ
							</button>
						</div>
						</logic:equal>
						<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/stu_info_add.do?method=showStuInfo&oper=update&xh=<bean:write name='rs' property="xh"/>')"></button>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" name="rs" disabled="true"
							styleId="xy" onchange="initZyList();initBj();" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th><span class="red">*</span>רҵ</th>
					<td>
						<html:select property="zydm" name="rs" disabled="true" style="width:180px"
							styleId="zy" onchange="initBj()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>�꼶</th>
					<td>
						<html:select property="nj" name="rs" disabled="true" styleId="nj" style="width:180px"
							onchange="initZyList();initBj();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<th><span class="red">*</span>�༶</th>
					<td>
						<html:select property="bjdm" name="rs" disabled="true" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						<html:radio property="xb" value="��" name="rs" disabled="true"
							styleId="xbn">��</html:radio>
						<html:radio property="xb" value="Ů" name="rs" disabled="true"
							styleId="xbv">Ů</html:radio>
					</td>
					<th>ѧ��</th>
					<td>
						<html:text property="xz" name="rs" disabled="true" maxlength="2"
							onkeyup="value=value.replace(/[^\d]/g,'')" />
						��
					</td>
				</tr>

				<tr>
					<th>����</th>
					<td>
						<html:select property="mz" name="rs" disabled="true" styleId="mz">
							<html:option value=""></html:option>
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<th>������ò</th>
					<td>
						<html:select property="zzmm" name="rs" disabled="true"
							styleId="zzmm">
							<html:option value=""></html:option>
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
				</tr>

				<tr>
					<th>ѧ��״̬</th>
					<td>
						<html:select property="xjztm" name="rs" disabled="true"
							styleId="xjztm">
							<html:option value=""></html:option>
							<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
						</html:select>
					</td>
					<th>��������</th>
					<td>
						<html:text property="csrq" name="rs" disabled="true" readonly=""
							onclick="return showCalendar('csrq','y-mm-dd');"  onblur="dateFormatChg(this);" styleId="csrq" />
					</td>
				</tr>

				<tr>
					<th>����ƴ��</th>
					<td>
						<html:text property="xmpy" name="rs" disabled="true"
							styleId="xmpy" maxlength="32" />
					</td>
					<th>���</th>
					<td colspan="2">
						<html:text property="sg" name="rs" disabled="true" styleId="sg"
							maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')" />
						����
					</td>
				</tr>

				<tr>
					<th>������</th>
					<td>
						<html:text property="cym" name="rs" disabled="true" styleId="cym" maxlength="16" />
					</td>
					<th>����</th>
					<td colspan="2">
						<html:text property="tz" name="rs" disabled="true" styleId="tz"
							maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')" />
						ǧ��
					</td>
				</tr>

				<tr>
					<th>���֤��</th>
					<td>
						<html:text property="sfzh" name="rs" disabled="true"
							styleId="sfzh" maxlength="18" />
					</td>
					<th>�س�</th>
					<td colspan="2">
						<html:text property="tc" name="rs" disabled="true" styleId="tc"
							maxlength="32" />
					</td>
				</tr>

				<tr>
					<th>������ʽ</th>
					<td>
						<html:text property="pyfs" name="rs" disabled="true"
							styleId="pyfs" maxlength="32" />
					</td>
					<th>�������</th>
					<td colspan="2">
						<html:text property="pycc" name="rs" disabled="true"
							styleId="pycc" maxlength="32" />
					</td>
				</tr>

				<tr>
					<th>��ѧ��ʽ</th>
					<td>
						<html:text property="rxfs" name="rs" disabled="true"
							styleId="rxfs" maxlength="32" />
					</td>
					<th>�������</th>
					<td colspan="2">
						<html:text property="kslb" name="rs" disabled="true"
							styleId="kslb" maxlength="32" />
					</td>
				</tr>

				<tr>
					<th>��Դ����	</th>
					<td>
						<html:text property="syd" name="rs" disabled="true" styleId="syd"
							maxlength="25" />
					</td>
					<th>������</th>
					<td colspan="2">
						<html:text property="ksh" name="rs" disabled="true"
							styleId="ksh" maxlength="32" onkeyup="value=value.replace(/[^\d]/g,'')"/>
					</td>
				</tr>

				<tr>
					<th>����</th>
					<td>						
						<html:text property="jg" name="rs" disabled="true" styleId="jg"
							maxlength="10" />
					</td>

					<th>��ϵ�绰</th>
					<td colspan="2">
						<html:text property="lxdh" name="rs" disabled="true"
							styleId="lxdh" maxlength="15" />
					</td>
				</tr>
				<tr>
					<th>��ҵ��ѧ</th>
					<td colspan="4">
						<html:text property="rxqdw" name="rs" disabled="true" styleId="rxqdw" maxlength="125" style="width:100%"/>
					</td>					
				</tr>
				<tr>
					<th>��ѧʱ��</th>
					<td>
						<html:text property="rxrq" name="rs" disabled="true" styleId="rxrq" onclick="return showCalendar('rxrq','y-mm-dd');"/>
					</td>

					<th>��������</th>
					<td colspan="2">
						<html:text property="dzyx" name="rs" disabled="true"
							styleId="dzyx" maxlength="32" />
					</td>
				</tr>

				<tr>
					<th>��ѧ�꼶</th>
					<td>
						<html:select property="rxnj" name="rs" disabled="true"
							styleId="rxnj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<th>�ֻ�����</th>
					<td colspan="2">
						<html:text property="sjhm" name="rs" disabled="true"
							styleId="sjhm" maxlength="11"
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>

				<tr>
					<th>�Ƿ�ע��</th>
					<td>
						<logic:equal value="" property="sfzc" name="rs" >
							<html:select property="sfzc" name="rs" disabled="true" value="δע��"
								styleId="sfzc">
								<html:option value=""></html:option>
								<html:options collection="sfzcList" property="en"
									labelProperty="cn" />
							</html:select>
						</logic:equal>
						
						<logic:notEqual value="" property="sfzc" name="rs" >
							<html:select property="sfzc" name="rs" disabled="true" onchange="sfzcChek()"
								styleId="sfzc">
								<html:option value=""></html:option>
								<html:options collection="sfzcList" property="en"
									labelProperty="cn" />
							</html:select>
						</logic:notEqual>
						
					</td>
					<th>�ܷ��ҵ</th>
					<td colspan="2">
						<html:select property="nfby" name="rs" disabled="true" onchange="sfbyChek()"
							styleId="nfby">
							<html:option value=""></html:option>
							<html:options collection="nfbyList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>�Ƿ��ҵ��</th>
					<td>
						<html:select property="sfbys" name="rs" styleId="sfbys" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<th>�Ƿ��ѱ�ҵ</th>
					<td colspan="2">
						<html:select property="sfyby" name="rs" styleId="sfyby" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>�Ƿ���У	</th>
					<td>
						<html:select property="sfzx" name="rs" styleId="sfzx" disabled="true">
							<html:option value=""></html:option>
							<html:option value="��У">��У</html:option>
							<html:option value="����У">����У</html:option>
						</html:select>
					</td>
					<th>��ҵʱ��</th>
					<td colspan="2">
						<html:text property="byny" name="rs" disabled="true"
							readonly="true" onclick="return showCalendar('byny','y-mm-dd');"
							styleId="byny" />
					</td>
				</tr>
				<tr>
					<th>ѧ������</th>
					<td>
						<html:select property="xslx" name="rs"  disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xsLxList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
					<th>ѧ�����</th>
					<td colspan="2">
						<html:select property="xslb" name="rs"  disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xsLbList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>��ע</th>
					<td colspan="4">
						<html:textarea property="bz" disabled="true" name="rs" rows="5" cols="80" onblur="chLeng(this,500)"></html:textarea>
					</td>					
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="5"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <logic:notPresent name="details">
							<button type="button" class="button2" id="buttonSave" onclick="zgdzdxSaveXsxx()">
								�� ��
							</button>					
						</logic:notPresent>
						<button type="button" class="button2" onclick="Close();return false;">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>

			<div id="tmpdiv1"></div>
			<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");	
					Close();	
					window.dialogArguments.document.getElementById('search_go').click();		
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
