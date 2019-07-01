<%@ page language="java" contentType="text/html; charset=GBK"%>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>分配情况(<font color="red">注：设置人数时请选择分配部门，否则将默认为所有部门</font>)</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th align="right" width="15%">
							年级：
						</th>
						<td align="left" width="35%">
							<html:select property="nj" style="" onchange="selectListNull();initZyList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<th align="right" width="15%">
							院系：
						</th>
						<td align="left" width="35%">
							<html:select property="xydm" style="" styleId="xy" onchange="selectListNull();initZyList();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<th align="right" width="">
							专业：
						</th>
						<td align="left" width="">
							<html:select property="zydm" style="" styleId="zy" onchange="selectListNull();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
						<td align="right" width="">
							
						</td>
						<td align="left" width="">
							
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%">
								<tr>
									<td width="10%" align="right">
										 班<br><br>级
									</td>
									<td width="30%">
										<html:select property="bjdm" style="width:100% " multiple="multiple" styleId="bj" size="12" onmouseover="null">
											<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
										</html:select>
									</td>
									<td width="10%">
										<button type="button"  class="button2" style="width:100%" id="addBj" onclick="addAllOfRightFrame('bj','bjR')">
											&gt;&nbsp;&gt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="addBj" onclick="addAllRightFrame('bj','bjR')">
											&gt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="delBj" onclick="addAllLeftFrame('bj','bjR')">
											&lt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="delBj" onclick="addAllOfLeftFrame('bj','bjR')">
											&lt;&nbsp;&lt;
										</button>
									</td>
									<td width="10%" align="right">
										设<br>置<br>班<br>级
									</td>
									<td width="30%">
										<html:select property="queryequals_bjdm" style="width:100%" styleId="bjR" multiple="multiple"
											size="12" onmouseover="null">
										</html:select>		
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</tbody>
				</table>