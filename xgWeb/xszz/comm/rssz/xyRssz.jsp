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
						<td colspan="4">
							<table width="100%">
								<tr>
									<td width="10%" align="right">
										 院<br><br>系
									</td>
									<td width="30%">
										<html:select property="xydm" style="width:100% " multiple="multiple" styleId="xyL" size="12" onmouseover="null">
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>
									</td>
									<td width="10%">
										<button type="button"  class="button2" style="width:100%" id="addXy" onclick="addAllOfRightFrame('xyL','xyR')">
											&gt;&nbsp;&gt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="addXy" onclick="addAllRightFrame('xyL','xyR')">
											&gt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="delXy" onclick="addAllLeftFrame('xyL','xyR')">
											&lt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="delXy" onclick="addAllOfLeftFrame('xyL','xyR')">
											&lt;&nbsp;&lt;
										</button>
									</td>
									<td width="10%" align="right">
										设<br>置<br>院<br>系
									</td>
									<td width="30%">
										<html:select property="queryequals_xydm" style="width:100%" styleId="xyR" multiple="multiple"
											size="12" ondblclick="" onmouseover="null">
										</html:select>		
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</tbody>
				</table>