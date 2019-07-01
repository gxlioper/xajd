<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>		
		<fieldset>
				<legend>课程&nbsp;&nbsp;&nbsp;
						<font color="blue"><button type="button" class="button2" onclick="add2('tTb')" 
							style="height:18px;width:20px">+</button> 
							&nbsp;<button type="button" class="button2" onclick="decrease2('tTb')" 
							style="height:18px;width:20px">-</button></font>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<thead style="height:23px">
						<tr>
							<td align="center">
								课程名称
							</td>
							<td align="center">
								分数
							</td>
							<td align="center">
								课程类型
							</td>
							<td align="center">
								是否必修课
							</td>
							<td align="center">
								得分
							</td>
						</tr>
					</thead>
					<!-- 1 - 10 -->
					<tr style="display:none"  id="1">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc1"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj1"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx1" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk1" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df1" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="2">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc2"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj2"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx2" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk2" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df2" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="3">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc3"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj3"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx3" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk3" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df3" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="4">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc4"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj4"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx4" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk4" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df4" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="5">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc5"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj5"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx5" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk5" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df5" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="6">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc6"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj6"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx6" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk6" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df6" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="7">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc7"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj7"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx7" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk7" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df7" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="8">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc8"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj8"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx8" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk8" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df8" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="9">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc9"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj9"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx9" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk9" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df9" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="10">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc10"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj10"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx10" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk10" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df10" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<!-- 11 - 20 -->
					<tr style="display:none"  id="11">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc11"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj11"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx11" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk11" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df11" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="12">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc12"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj12"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx12" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk12" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df12" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="13">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc13"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj13"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx13" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk13" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df13" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="14">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc14"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj14"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx14" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk14" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df14" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="15">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc15"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj15"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx15" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk15" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df15" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="16">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc16"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj16"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx16" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk16" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df16" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="17">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc17"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj17"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx17" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk17" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df17" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="18">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc18"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj18"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx18" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk18" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df18" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="19">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc19"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj19"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx19" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk19" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df19" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
					<tr style="display:none"  id="20">
						<td align="center">
							<html:text property="kcmc" styleId="kcmc20"></html:text>
						</td>
						<td align="center">
							<html:text property="cj" onblur="ckinpdata(this)" styleId="cj20"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="kclx" styleId="kclx20" style="width:100px">
								<html:option value="0">专业课</html:option>
								<html:option value="1">文化课</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select property="sfbxk" styleId="sfbxk20" style="width:50px">
								<html:option value="0">是</html:option>
								<html:option value="1">否</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text property="df" styleId="df20" style="width:90px" onkeypress="return sztzNumInputValue(this,9,event)"></html:text>
						</td>
					</tr>
				</table>
		</fieldset>