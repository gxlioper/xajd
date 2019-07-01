<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>		
		<fieldset>
				<legend>身心能力&nbsp;&nbsp;&nbsp;
						<font color="blue"><button type="button" class="button2" onclick="add1('tTb')" style="height:18px;width:20px">+</button> 
							&nbsp;<button type="button" class="button2" onclick="decrease1('tTb')" style="height:18px;width:20px">-</button></font>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<thead style="height:23px">
						<tr>
							<td align="center">
								项目
							</td>
							<td align="center">
								得分类型
							</td>
							<td align="center">
								分数
							</td>
							<td align="center">
								身心能力素质
							</td>
						</tr>
					</thead>
					<tr style="display:none"  id="1">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm1" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm1" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs1" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr1" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="2">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm2" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm2" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs2" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr2" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="3">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm3" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm3" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs3" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr3" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="4">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm4" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm4" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs4" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr4" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="5">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm5" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm5" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs5" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr5" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="6">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm6" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm6" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs6" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr6" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="7">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm7" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm7" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs7" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr7" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="8">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm8" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm8" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs8" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr8" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="9">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm9" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm9" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs9" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr9" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="10">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm10" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm10" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs10" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr10" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="11">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm11" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm11" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs11" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr11" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="12">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm12" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm12" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs12" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr12" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="13">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm13" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm13" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs13" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr13" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="14">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm14" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm14" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs14" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr14" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="15">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm15" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm15" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs15" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr15" style="width:300px"></html:text>
						</td>
					</tr>
					<!-- 16-20 -->
					<tr style="display:none"  id="16">
						<td align="center">
								<html:text property="dfxm" styleId="dfxm16" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm16" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs16" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr16" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="17">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm17" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm17" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs17" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr17" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="18">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm18" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm18" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs18" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr18" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="19">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm19" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm19" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs19" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr19" style="width:300px"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="20">
					<td align="center">
								<html:text property="dfxm" styleId="dfxm20" style="width:100px"></html:text>
						</td>
						<td align="center">
							<html:select property="jcxm" styleId="jcxm20" style="width:90px">
								<html:option value="0">加分</html:option>
								<html:option value="1">扣分</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs20" style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:text property="lr" styleId="lr20" style="width:300px"></html:text>
						</td>
					</tr>
				</table>
		</fieldset>