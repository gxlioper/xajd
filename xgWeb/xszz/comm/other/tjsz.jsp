<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>条件设置</span>
							</th>
						</tr>
					</thead>
					<tbody>	
					<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('tjts')" onmouseout="hiddTsDiv('tjts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：</font>	
								<font color="blue" id="tjts" style="display : none">限制申请者的相关条件，默认皆为无限制。</font>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="15%">
							困难生限制：
						</th>
						<td width="" colspan="3">
							<!-- 需要困难生条件 -->
							<logic:equal name="knsTj" value="yes">
								<html:select name="rs" property="needKns" style="" styleId="knsSelect" onchange="setKnsJb()">
									<html:options collection="szsfList" property="en" labelProperty="cn" />
								</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							级别：
							<!-- 需要困难生条件 -->
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
							<!-- 不需要困难生条件 -->
							<logic:equal name="knsTj" value="no">
								不做控制
							</logic:equal>		
						</td>
					</tr>
					<!-- 使用李涛涛版本通用评奖的学校 -->
					<!-- 贵州大学 -->
					<logic:equal name="xxdm" value="10657">
					<tr style="height: 23px">
						<th align="right">
							年度获得上限：
						</th>
						<td align="left">
							<input type="text" name="save_hdsx" id="hdsx" value="${rs.save_hdsx }"
								onkeypress="return sztzNumInputValue(this,7,event)" 
								maxlength="7" style="width:30%;ime-mode:disabled"/>
							<input type="hidden" name="xmtjb" value="hdsx@zzhz">
						</td>
						<td colspan="2">
							<span onmousemove="showTsDiv('hzts')" onmouseout="hiddTsDiv('hzts')">
								<font color="blue">提示：</font>	
								<font color="blue" id="hzts" style="display : none">
									若申请者本年度（指1月1日-12月31日）获得的资助金额未<br>
									超过所设置的金额上限，才可以被审核通过本项目。
								</font>
							</span>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right" width="">
							智育分：
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
							综合分：
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
					<!-- 使用李涛涛over -->
					<!-- 贵州大学增加条件：加权平均分、加权平均绩点--qph--- 2012.3.8-->
						<tr>
							<th align="right" width="">
								加权平均分：
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
								加权平均绩点：
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
					
					
					<!-- 话说贵州大学要求 增加一项“平均成绩”，设置方法像智育分一样 。大师说要通用~ 2010.12.7 qph-->
					<tr>
						<th>平均成绩：</th>
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
								<font color="blue">提示：</font>	
								<font color="blue" id="pjcjts" style="display : none">
									平均成绩是根据项目的申请周期取评奖学年、年度或学期对应的成绩。<br/>
									如：设置“国家助学贷款”的申请周期为“学年”，评奖学年为“2009-2010”，<br/>
									那么平均成绩取的就是“2009-2010”学年的数据。
								</font>
							</span>
						</td>
					</tr>
					<!-- 平均成绩结束 end -->
					
					<tr style="height: 23px">
						<th align="right" width="">
							学制：
						</th>
						<td width="">
							<html:select name="rs" property="save_xz" style="" styleId="xz" onchange="">
								<html:option value="无限制">无限制</html:option>
								<html:options collection="xzList" property="dm" labelProperty="mc" />
							</html:select>
							<input type="hidden" name="xmtjb" value="xz@view_xsjbxx">
						</td>
						
						<logic:equal value="" property="save_bjgkms" name="rs">
							<th align="right">
								<input type="radio" name="bjgkm" checked="checked"  onclick="$('nobjg').disabled=!(this.checked);$('bjgkms').disabled=this.checked;">无不及格科目：
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
								<input type="radio" name="bjgkm"  onclick="$('nobjg').disabled=!(this.checked);$('bjgkms').disabled=this.checked;">无不及格科目：
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
							无违纪情况：
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
								允许不及格门次：
							</th>
							
							<td>
								<html:text property="save_bjgkms" name="rs" styleId="bjgkms" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" style="width:80px" disabled="true" />
								<input type="hidden" name="xmtjb" value="bjgkms@xszz_bjgkms" >
							</td>
						</logic:equal>
						<logic:notEqual value="" property="save_bjgkms" name="rs">
							<th>
								<input type="radio" checked="checked" name="bjgkm" onclick="$('nobjg').disabled=this.checked;$('bjgkms').disabled=!(this.checked);">
								允许不及格门次：
							</th>
							
							<td>
								<html:text property="save_bjgkms" name="rs" styleId="bjgkms" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" style="width:80px" />
								<input type="hidden" name="xmtjb" value="bjgkms@xszz_bjgkms" >
							</td>
						</logic:notEqual>
						
						
							
							
						
						
					</tr>
	</tbody>
</table>