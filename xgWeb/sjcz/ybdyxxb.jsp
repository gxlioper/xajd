<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/szxx_PartyBuild.js"></script>
	<script language="javascript">
	function dyzz(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top);
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------��Աת��---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "ʱ��:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<input type='text' name='gzkssj' id='gzkssj' onblur='dateFormatChg(this)' onclick='time(this.id)'  style='cursor:hand;' readonly='true'/>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveYbdy()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function time(id){
	return showCalendar(id,'y-mm-dd');
	}
	
	function saveYbdy(){
		if($("gzkssj")){
			if($("gzkssj").value == ""){
				alert("��ȷ��ת��ʱ��");
				return false;
			}
		}
		if(checkXnNd('xn','nd')){
			partyBuildDataDoSave('xn-xq-xh-pxkssj');
		}
	}
</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			<logic:empty name="rs">
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/ybdyxxb.jsp" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
					    	<tr>
					        	<th colspan="4"><span>Ԥ����Ա��Ϣά��1</span></th>
					        </tr>
					    </thead>
					
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					          <button type="button" onclick="dataCanModi(true);return false;"
									 id="buttonModi">
									�� ��
								</button>
								<button type="button" onclick="if(checkXnNd('xn','nd')){if(($('pxkssj').value>$('pxjssj').value.replace(' ',''))&&$('pxjssj').value!=''){;alert('��ʼʱ����ڽ���ʱ�䣡');return false;}partyBuildDataDoSave('xn-xq-xh-pxkssj');}"
									id="buttonSave">
									�� ��
								</button>
								<!-- ��ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
								<logic:equal name="xxdm" value="12061">
								<logic:equal name="doType" value="modi">
									<button type="button" onclick="dyzz();return false;">
									��Աת��
									</button>
								</logic:equal>
								</logic:equal>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do?zzmm=ybdy',750,550);"
									class="btn_01" id="buttonFindStu" style="display:none">
									ѡ��
								</button>
							</td>
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								<font color="red">*</font>Ԥ����ʼ����
							</th>
							<td align="left">
								<html:text name='rs' property="kssj" styleId="pxkssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxkssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								Ԥ����������
							</th>
							<td align="left">
								<html:text name='rs' property="jssj" styleId="pxjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxjssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
						<th>
							��ϵ��
						</th>
						<td align="left">
							<html:text name="rs" property="lxr1" styleId="lxrfirst" maxlength="15"></html:text>
						</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th>
									�Ƿ����ת��
								</th>
								<td align="left">
									<html:select  name='rs' property="sfkyzz" style="width:40px" styleId="sfkyzz">
									<html:option value=""></html:option>
									<html:options collection="ynList" property="en" labelProperty="cn" />
								</html:select>
								</td>
						</tr>
						<tr>
							<th>
									̸������
							</th>
							<td align="left">
								<html:text name='rs' property="thcs" styleId="thcs"  maxlength="20"/>
							</td>
							<th>
									˼��㱨����
							</th>
							<td align="left">
							<html:text name='rs' property="sxhbcs" styleId="sxhbcs" maxlength="3" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th>
								ѧ�����
							</th>
							<td align="left">
								<html:select name='rs' property="xsccdm" style="width:90px">
													<html:option value=""></html:option>
													<html:options collection="xsccList" property="xsccdm"
														labelProperty="xsccmc" />
								</html:select>
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						
						<tr>
							<th>
								���ѽ������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='dfjnqk' style="width:99%"
									rows='2' onblur="chLeng(this,'100')"/>
							</td>
						</tr>
						<tr>
							<th>
								�μ�����ѧϰ���
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='cjzzxxqk' style="width:99%"
									rows='5' onblur="chLeng(this,'300')"/>
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='kcqk' style="width:99%"
									rows='6' onblur="chLeng(this,'300')"/>
							</td>
						</tr>
						<logic:equal name="xxdm" value="11417">
							<th>
								��֯��ϵת��ȥ��
							</th>
							<td colspan="3">
								<html:textarea name='rs' property="zzgxzx" styleId="zzgxzx" 
								rows='3'style="width:99%" />
							</td>
						</logic:equal>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='3' onblur="chLeng(this,'60')"/>
							</td>
						</tr>
						<logic:present name="shownblg">
						<tr align="left">
						<td align="center" colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>������ּ�¼</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
						<tr>
					<td colspan="4">
							<div id="child2" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
						<tr align="left">
							<th style="width:25%">
								һѧ���������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx1' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th>
								��ѧ���������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx2' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th>
								��ѧ���������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx3' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left">
							<th>
								��ѧ���������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='ssbx4' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</table>
						</div>
						
					</td>
					</tr>
						</logic:present>
					<logic:equal name="xxdm" value="10491">
						<tr align="left">
							<th>
								��Ҫ����
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zygx' style="width:99%"
									rows='5' onblur="chLeng(this,'250')"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								��Ҫ����
							</th>
							<td colspan="3"><html:textarea name='rs' property='zybx' style="width:99%"
									rows='5' onblur="chLeng(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
						</tbody>
					</table>
					</div>
				<div id="tmpdiv1"></div>
				<div class="buttontool">
					
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
