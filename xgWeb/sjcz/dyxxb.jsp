<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/szxx_PartyBuild.js"></script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		
		<html:form action="/data_search" method="post">
	
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><span id="tipFollow"></span></a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
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
				<input type="hidden" id="url" name="url" value="/sjcz/dyxxb.jsp" />
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
			    			<tr>
			        		<th colspan="4"><span>��Ա��Ϣά��</span></th>
			        		</tr>
			    		</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
							    <button type="button" onclick="dataCanModi(true)"
									id="buttonModi">
									�� ��
								</button>
								<button type="button" 
									onclick="if(checkXnNd('xn','nd'))partyBuildDataDoSave('xn-xq-xh-pxjssj');"
									 id="buttonSave">
									�� ��
								</button>
								<button type="button" onclick="Close();return false;" 
									id="buttonClose">
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
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									id="buttonFindStu" style="display:none" class='btn_01'>
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
								<font color="red">*</font>�뵳����
							</th>
							<td align="left">
								<html:text name='rs' property="rdsj" styleId="pxjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('pxjssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
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
								ѧ�����
							</th>
							<td align="left">
								<html:select name='rs' property="xsccdm" style="width:90px">
													<html:option value=""></html:option>
													<html:options collection="xsccList" property="xsccdm"
														labelProperty="xsccmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>ת��ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="zzsj" styleId="zzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zzsj','y-mm-dd');" />
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								Ԥ����Ա��ʼʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="ybdykssj" styleId="ybdykssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('ybdykssj','y-mm-dd');" />
							</td>
							<th>
								Ԥ����Ա����ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="ybdyjssj" styleId="ybdyjssj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('ybdyjssj','y-mm-dd');" />
							</td>
						</tr>
						<logic:equal name="xxdm" value="12645">
							<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xl" styleId="xl" />
							</td>
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="jg" styleId="jg" />
							</td>
							</tr>
							<tr>
							<th>
								������
							</th>
							<td align="left">
								<html:text name='rs' property="csd" styleId="csd" />
							</td>
							<th>
							</th>
							<td align="left">
							</td>
							</tr>
						</logic:equal>
						<tr align="left">
							<th>
								�μӻ���
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='cjhdqk' style="width:99%"
									rows='5' onblur="chLeng(this,'200')"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								֧���������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zbshqk' style="width:99%"
									rows='5' onblur="chLeng(this,'200')"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								���ѽ������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='dfjnqk' style="width:99%"
									rows='5' onblur="chLeng(this,'200')"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								�μ�����ѧϰ���
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zzxxqk' style="width:99%"
									rows='5' onblur="chLeng(this,'200')"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								��֯��ϵת��ȥ��
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='gxzcqx' style="width:99%"
									rows='5' onblur="chLeng(this,'100')"/>
							</td>
						</tr>
						<tr align="left">
							<th>
								��ע
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' onblur="chLeng(this,'60')"/>
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
							<td colspan="3">
								<html:textarea name='rs' property='zybx' style="width:99%"
									rows='5' onblur="chLeng(this,'250')"/>
							</td>
						</tr>
					</logic:equal>
					</tbody>
					</table>
					</div>
				</fieldset>
				<div class="buttontool">
					
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
