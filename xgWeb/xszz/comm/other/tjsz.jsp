<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>	
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('tjts')" onmouseout="hiddTsDiv('tjts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��</font>	
								<font color="blue" id="tjts" style="display : none">���������ߵ����������Ĭ�Ͻ�Ϊ�����ơ�</font>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							���������ƣ�
						</th>
						<td width="" colspan="3">
							<!-- ��Ҫ���������� -->
							<logic:equal name="knsTj" value="yes">
								<html:select name="rs" property="needKns" style="" styleId="knsSelect" onchange="setKnsJb()">
									<html:options collection="szsfList" property="en" labelProperty="cn" />
								</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							����
							<!-- ��Ҫ���������� -->
							<logic:equal name="knsTj" value="yes">
								<logic:notEmpty name="knsList">
									<logic:iterate name="knsList" id="kns" indexId="num">
										<logic:notEqual name="num" value="0">
											<input type="checkbox" name="knsCk" id="${kns.dm }" value="${kns.dm }" onclick="checkKns()" disabled="disabled"/>${kns.mc }
										</logic:notEqual>
									</logic:iterate>
								</logic:notEmpty>					
								<input type="hidden" name="save_iskns" style="" id="iskns" value="${rs.save_iskns }"/>
								<input type="hidden" name="xmtjb" value="iskns@xszz_knsb">
							</logic:equal>
							</logic:equal>
							<!-- ����Ҫ���������� -->
							<logic:equal name="knsTj" value="no">
								��������
							</logic:equal>		
						</td>
					</tr>
					<!-- ʹ�������ΰ汾ͨ��������ѧУ -->
					<!-- ���ݴ�ѧ -->
					<logic:equal name="xxdm" value="10657">
					<tr style="height: 23px">
						<th align="right">
							��Ȼ�����ޣ�
						</th>
						<td align="left">
							<input type="text" name="save_hdsx" id="hdsx" value="${rs.save_hdsx }"
								onkeypress="return sztzNumInputValue(this,7,event)" 
								maxlength="7" style="width:30%;ime-mode:disabled"/>
							<input type="hidden" name="xmtjb" value="hdsx@zzhz">
						</td>
						<td colspan="2">
							<span onmousemove="showTsDiv('hzts')" onmouseout="hiddTsDiv('hzts')">
								<font color="blue">��ʾ��</font>	
								<font color="blue" id="hzts" style="display : none">
									�������߱���ȣ�ָ1��1��-12��31�գ���õ��������δ<br>
									���������õĽ�����ޣ��ſ��Ա����ͨ������Ŀ��
								</font>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							�����֣�
						</th>
						<td width="">
							<html:select name="rs" property="zyftj" style="" styleId="zyftj" onchange="">
								<html:option value=""></html:option>
								<html:options collection="tjlxList" property="en" labelProperty="cn" />
							</html:select>
							<input type="text" name="zyfz" style="" id="zyfz" value="${rs.zyfz }"
								onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5" 
								style="width:20%;ime-mode:disabled"/>
							<input type="hidden" name="save_zyf" style="" id="zyf" value="${rs.save_zyf }"/>
							<input type="hidden" name="xmtjb" value="zyf@zyfb">
						</td>
						<th align="right">
							�ۺϷ֣�
						</th>
						<td>
							<html:select name="rs" property="zhftj" style="" styleId="zhftj" onchange="">
								<html:option value=""></html:option>
								<html:options collection="tjlxList" property="en" labelProperty="cn" />
							</html:select>
							<input type="text" name="zhfz" style="" id="zhfz" value="${rs.zhfz }"
								onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5" 
								style="width:20%;ime-mode:disabled"/>
							<input type="hidden" name="save_zhf" style="" id="zhf" value="${rs.save_zhf }"/>
							<input type="hidden" name="xmtjb" value="zhf@zhfb">
						</td>
					</tr>
					<!-- ʹ��������over -->
					<!-- ���ݴ�ѧ������������Ȩƽ���֡���Ȩƽ������--qph--- 2012.3.8-->
						<tr>
							<th align="right" width="">
								��Ȩƽ���֣�
							</th>
							<td width="">
								<html:select name="rs" property="jqpjftj" styleId="jqpjftj">
									<html:option value=""></html:option>
									<html:options collection="tjlxList" property="en" labelProperty="cn" />
								</html:select>
								<input type="text" name="jqpjfz" id="jqpjfz" value="${rs.jqpjfz }"
									onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5" 
									style="width:20%;ime-mode:disabled"/>
								<input type="hidden" name="save_jqpjf" style="" id="jqpjf" value="${rs.save_jqpjf }"/>
								<input type="hidden" name="xmtjb" value="jqpjf@jqpjfb">
							</td>
							<th align="right">
								��Ȩƽ�����㣺
							</th>
							<td>
								<html:select name="rs" property="jqpjjdtj" styleId="jqpjjdtj">
									<html:option value=""></html:option>
									<html:options collection="tjlxList" property="en" labelProperty="cn" />
								</html:select>
								<input type="text" name="jqpjjdz" style="" id="jqpjjdz" value="${rs.jqpjjdz }"
									onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5" 
									style="width:20%;ime-mode:disabled"/>
								<input type="hidden" name="save_jqpjjd" style="" id="jqpjjd" value="${rs.save_jqpjjd }"/>
								<input type="hidden" name="xmtjb" value="jqpjjd@jqpjjdb">
							</td>
						</tr>
					</logic:equal>
					
					
					<!-- ��˵���ݴ�ѧҪ�� ����һ�ƽ���ɼ��������÷�����������һ�� ����ʦ˵Ҫͨ��~ 2010.12.7 qph-->
					<tr>
						<th>ƽ���ɼ���</th>
						<td>
							<html:select name="rs" property="pjcjtj" style="" styleId="pjcjtj" >
								<html:option value=""></html:option>
								<html:options collection="tjlxList" property="en" labelProperty="cn" />
							</html:select>
							<input type="text" name="pjcjz" style="" id="pjcjz" value="${rs.pjcjz }"
								onkeypress="return sztzNumInputValue(this,5,event)" maxlength="5" 
								style="width:20%;ime-mode:disabled"/>
								
							<input type="hidden" name="save_pjcj" style="" id="pjcj" value="${rs.save_pjcj }"/>
							<input type="hidden" name="xmtjb" value="pjcj@pjcjb">
						</td>
						<td colspan="2">
						<span onmousemove="showTsDiv('pjcjts')" onmouseout="hiddTsDiv('pjcjts')">
								<font color="blue">��ʾ��</font>	
								<font color="blue" id="pjcjts" style="display : none">
									ƽ���ɼ��Ǹ�����Ŀ����������ȡ����ѧ�ꡢ��Ȼ�ѧ�ڶ�Ӧ�ĳɼ���<br/>
									�磺���á�������ѧ�������������Ϊ��ѧ�ꡱ������ѧ��Ϊ��2009-2010����<br/>
									��ôƽ���ɼ�ȡ�ľ��ǡ�2009-2010��ѧ������ݡ�
								</font>
							</span>
						</td>
					</tr>
					<!-- ƽ���ɼ����� end -->
					
					<tr style="height: 23px">
						<th align="right" width="">
							ѧ�ƣ�
						</th>
						<td width="">
							<html:select name="rs" property="save_xz" style="" styleId="xz" onchange="">
								<html:option value="������">������</html:option>
								<html:options collection="xzList" property="dm" labelProperty="mc" />
							</html:select>
							<input type="hidden" name="xmtjb" value="xz@view_xsjbxx">
						</td>
						
						<logic:equal value="" property="save_bjgkms" name="rs">
							<th align="right">
								<input type="radio" name="bjgkm" checked="checked"  onclick="$('nobjg').disabled=!(this.checked);$('bjgkms').disabled=this.checked;">�޲������Ŀ��
							</th>
							<td>
								<html:select name="rs" property="save_nobjg" style="" styleId="nobjg">
									<html:options collection="szsfList" property="en" labelProperty="cn" />
								</html:select>
								<input type="hidden" name="xmtjb" value="nobjg@xszz_bjgqk">
							</td>
							
						</logic:equal>
						<logic:notEqual value="" property="save_bjgkms" name="rs">
							<th align="right">
								<input type="radio" name="bjgkm"  onclick="$('nobjg').disabled=!(this.checked);$('bjgkms').disabled=this.checked;">�޲������Ŀ��
							</th>
							<td>
								<html:select name="rs" property="save_nobjg" style="" styleId="nobjg" disabled="true">
									<html:options collection="szsfList" property="en" labelProperty="cn" />
								</html:select>
								<input type="hidden" name="xmtjb" value="nobjg@xszz_bjgqk">
							</td>
						</logic:notEqual>
					</tr>
					
					<tr style="height: 23px">
						<th align="right" width="">
							��Υ�������
						</th>
						<td width="">
							<html:select name="rs" property="save_nowj" style="" styleId="nowj" onchange="">
								<html:options collection="szsfList" property="en" labelProperty="cn" />
							</html:select>
							<input type="hidden" name="xmtjb" value="nowj@xszz_wjqk">
						</td>
						
						<logic:equal value="" property="save_bjgkms" name="rs">
							<th>
								<input type="radio" name="bjgkm" onclick="$('nobjg').disabled=this.checked;$('bjgkms').disabled=!(this.checked);">
								���������ŴΣ�
							</th>
							
							<td>
								<html:text property="save_bjgkms" name="rs" styleId="bjgkms" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" style="width:80px" disabled="true" />
								<input type="hidden" name="xmtjb" value="bjgkms@xszz_bjgkms" >
							</td>
						</logic:equal>
						<logic:notEqual value="" property="save_bjgkms" name="rs">
							<th>
								<input type="radio" checked="checked" name="bjgkm" onclick="$('nobjg').disabled=this.checked;$('bjgkms').disabled=!(this.checked);">
								���������ŴΣ�
							</th>
							
							<td>
								<html:text property="save_bjgkms" name="rs" styleId="bjgkms" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" style="width:80px" />
								<input type="hidden" name="xmtjb" value="bjgkms@xszz_bjgkms" >
							</td>
						</logic:notEqual>
						
						
							
							
						
						
					</tr>
	</tbody>
</table>