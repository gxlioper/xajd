<%@ page language="java" contentType="text/html; charset=GBK"%>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������(<font color="red">ע����������ʱ��ѡ����䲿�ţ�����Ĭ��Ϊ���в���</font>)</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th align="right" width="15%">
							�꼶��
						</th>
						<td align="left" width="35%">
							<html:select property="nj" style="" onchange="selectListNull();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<th align="right" width="15%">
							Ժϵ��
						</th>
						<td align="left" width="35%">
							<html:select property="xydm" style="" styleId="xy" onchange="selectListNull();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>	
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<table width="100%">
								<tr>
									<td width="10%" align="right">
										 ר<br><br>ҵ
									</td>
									<td width="30%">
										<html:select property="zydm" style="width:100% " multiple="multiple" styleId="zy" size="12" onmouseover="null">
											<html:options collection="zyList" property="zydm" labelProperty="zymc" />
										</html:select>
									</td>
									<td width="10%">
										<button type="button"  class="button2" style="width:100%" id="addZy" onclick="addAllOfRightFrame('zyL','zyR')">
											&gt;&nbsp;&gt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="addZy" onclick="addAllRightFrame('zyL','zyR')">
											&gt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="delZy" onclick="addAllLeftFrame('zyL','zyR')">
											&lt;
										</button>
										<br />
										<button type="button"  class="button2" style="width:100%" id="delZy" onclick="addAllOfLeftFrame('zyL','zyR')">
											&lt;&nbsp;&lt;
										</button>
									</td>
									<td width="10%" align="right">
										��<br>��<br>ר<br>ҵ
									</td>
									<td width="30%">
										<html:select property="queryequals_zydm" style="width:100%" styleId="zyR" multiple="multiple"
											size="12" ondblclick="" onmouseover="null">
										</html:select>		
									</td>
								</tr>
							</table>
						</td>
					</tr>
					</tbody>
				</table>