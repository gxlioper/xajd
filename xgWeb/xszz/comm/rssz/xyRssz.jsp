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
						<td colspan="4">
							<table width="100%">
								<tr>
									<td width="10%" align="right">
										 Ժ<br><br>ϵ
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
										��<br>��<br>Ժ<br>ϵ
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