<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/dagl/daxxgl/addDaxxgl.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body >
		<html:form action="/daxxgl" method="post"  onsubmit="return false;" styleId="form">
			<div style='width:100%;height:500px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr width="100%">
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr><td align="left" colspan="4"><span style="color:blue;">����ת����Ϣ</span></td></tr>
						<tr>
							<th width="18%">
								<span class="red">*</span>����ת��ʱ��
							</th>
							<td width="32%" >
								<html:text property="dazrsj" styleId="dazrsj"  style="width:50%" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'dazcsj');" />���鵵���ڣ�
							</td>
							<th width="18%">
								����ת�뷽ʽ
							</th>
							<td width="32%">
								<html:text property="dazrfs" styleId="dazrfs"  style="width:50%"  maxlength="15" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
								<span class="red">*</span>ת�뷽ʽ���
							</th>
							<td  colspan="3" >
								<html:text property="zrfsbh" styleId="zrfsbh"  style="width:50%"  maxlength="50" readonly="fasle" />���������/��Ҫ�ţ�
							</td>
						</tr>
						<tr><td align="left" colspan="4"><span style="color:blue">����ת����Ϣ</span></td></tr>
						<tr>
							<th >
								����ת��ʱ��
							</th>
							<td  >
								<html:text property="dazcsj" styleId="dazcsj"  style="width:50%"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'dazrsj');"  />
							</td>
							<th >
								����ת����ʽ
							</th>
							<td >
								<html:text property="dazcfs" styleId="dazcfs"  style="width:50%"  maxlength="15" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
							<logic:equal name="xxdm" value="12869">��Ҫ���</logic:equal>
							<logic:notEqual name="xxdm" value="12869">����ת�����</logic:notEqual>
							</th>
							<td  colspan="3" >
								<html:text property="zcfsbh" styleId="zcfsbh"  style="width:50%"  maxlength="50" readonly="fasle" />
							</td>
						</tr>
						<logic:equal name="sfxsbyqx" value="1" >
							<tr><td align="left" colspan="4"><span style="color:blue">��ҵȥ����Ϣ</span></td></tr>
							<tr>
								<th >
									��ҵȥ��
								</th>
								<td colspan="3">
									<html:select property="byqxdm" style="width:120px" styleId="byqxdm">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="byqxList" property="byqxdm"
											labelProperty="byqxmc" />
									</html:select></td>
								</td>
							</tr>
							<tr>
								<th >
									��λ����
								</th>
								<td  >
									<html:text property="dwmc" styleId="dwmc"  style="width:50%"  maxlength="50" readonly="fasle" />
								</td> 
								<th >
									��λ�ʱ�
								</th>
								<td >
									<html:text property="dwyb" styleId="dwyb"  style="width:50%"  maxlength="6" readonly="fasle"  onkeyup="checkInputData(this);"/>
								</td>
							</tr>
							<tr>
								<th >
									��λ��ַ
								</th>
								<td  colspan="3" >
									<html:text property="dwdz" styleId="dwdz"  style="width:50%"  maxlength="200" readonly="fasle" />
								</td>
							</tr>
							<tr>
								<th >
									��λ��ϵ��
								</th>
								<td  >
									<html:text property="dwlxr" styleId="dwlxr"  style="width:50%"  maxlength="35" readonly="fasle" />
								</td>
								<th >
									��λ��ϵ�绰
								</th>
								<td  >
									<html:text property="dwlxdh" styleId="dwlxdh"  style="width:50%"  maxlength="30" readonly="fasle" onblur="checkPhone(this);"/>
								</td>
							</tr>
						</logic:equal>
						
						<logic:equal name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">����Ͷ����Ϣ</span></td></tr>
						<tr>
							<th >
								����Ͷ�ݵ�λ
							</th>
							<td  >
								<html:text property="dazjdw" styleId="dazjdw"  style="width:50%"  maxlength="100" readonly="fasle" />
							</td>
							<th >
								����Ͷ���ʱ�
							</th>
							<td   >
								<html:text property="dazjyb" styleId="dazjyb"  style="width:50%"  maxlength="6" readonly="fasle" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th >
								����Ͷ�ݵ�ַ
							</th>
							<td   colspan="3">
								<html:text property="dazjdz" styleId="dazjdz"  style="width:50%"  maxlength="200" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
								����Ͷ�ݵ�λ��ϵ��
							</th>
							<td  >
								<html:text property="dazjdwlxr" styleId="dazjdwlxr"  style="width:50%"   maxlength="35" readonly="fasle" />
							</td>
							<th >
								����Ͷ�ݵ�λ�绰
							</th>
							<td   >
								<html:text property="dazjdwdh" styleId="dazjdwdh"  style="width:50%"  maxlength="30" readonly="fasle" onblur="checkPhone(this);"/>
							</td>
						</tr>
						</logic:equal>
						
						<logic:notEqual name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">����ת����Ϣ</span></td></tr>
						<tr>
							<th >
								����ת�ĵ�λ
							</th>
							<td  >
								<html:text property="dazjdw" styleId="dazjdw"  style="width:50%"  maxlength="100" readonly="fasle" />
							</td>
							<th >
								����ת���ʱ�
							</th>
							<td   >
								<html:text property="dazjyb" styleId="dazjyb"  style="width:50%"  maxlength="6" readonly="fasle" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th >
								����ת�ĵ�ַ
							</th>
							<td   colspan="3">
								<html:text property="dazjdz" styleId="dazjdz"  style="width:50%"  maxlength="200" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
								����ת�ĵ�λ��ϵ��
							</th>
							<td  >
								<html:text property="dazjdwlxr" styleId="dazjdwlxr"  style="width:50%"   maxlength="35" readonly="fasle" />
							</td>
							<th >
								����ת�ĵ�λ�绰
							</th>
							<td   >
								<html:text property="dazjdwdh" styleId="dazjdwdh"  style="width:50%"  maxlength="30" readonly="fasle" onblur="checkPhone(this);"/>
							</td>
						</tr>
						</logic:notEqual>
						
						<tr><td align="left" colspan="4"><span style="color:blue">������Ϣ</span></td></tr>
						<tr>
							<th >
								��ע<br><span class="red">(��500��)</span>
							</th>
							<td   colspan="3">
								<html:textarea property="bz" styleId="bz"  style="width:95%;word-break:break-all;" rows="4"  readonly="fasle"  onblur="chLeng(this,500);"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table border="0" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>
