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
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr width="100%">
							<th colspan="4">
								<span>档案信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr><td align="left" colspan="4"><span style="color:blue;">档案转入信息</span></td></tr>
						<tr>
							<th width="18%">
								<span class="red">*</span>档案转入时间
							</th>
							<td width="32%" >
								<html:text property="dazrsj" styleId="dazrsj"  style="width:50%" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'dazcsj');" />（归档日期）
							</td>
							<th width="18%">
								档案转入方式
							</th>
							<td width="32%">
								<html:text property="dazrfs" styleId="dazrfs"  style="width:50%"  maxlength="15" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
								<span class="red">*</span>转入方式编号
							</th>
							<td  colspan="3" >
								<html:text property="zrfsbh" styleId="zrfsbh"  style="width:50%"  maxlength="50" readonly="fasle" />（档案编号/机要号）
							</td>
						</tr>
						<tr><td align="left" colspan="4"><span style="color:blue">档案转出信息</span></td></tr>
						<tr>
							<th >
								档案转出时间
							</th>
							<td  >
								<html:text property="dazcsj" styleId="dazcsj"  style="width:50%"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'dazrsj');"  />
							</td>
							<th >
								档案转出方式
							</th>
							<td >
								<html:text property="dazcfs" styleId="dazcfs"  style="width:50%"  maxlength="15" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
							<logic:equal name="xxdm" value="12869">机要编号</logic:equal>
							<logic:notEqual name="xxdm" value="12869">档案转出编号</logic:notEqual>
							</th>
							<td  colspan="3" >
								<html:text property="zcfsbh" styleId="zcfsbh"  style="width:50%"  maxlength="50" readonly="fasle" />
							</td>
						</tr>
						<logic:equal name="sfxsbyqx" value="1" >
							<tr><td align="left" colspan="4"><span style="color:blue">毕业去向信息</span></td></tr>
							<tr>
								<th >
									毕业去向
								</th>
								<td colspan="3">
									<html:select property="byqxdm" style="width:120px" styleId="byqxdm">
										<html:option value="">--请选择--</html:option>
										<html:options collection="byqxList" property="byqxdm"
											labelProperty="byqxmc" />
									</html:select></td>
								</td>
							</tr>
							<tr>
								<th >
									单位名称
								</th>
								<td  >
									<html:text property="dwmc" styleId="dwmc"  style="width:50%"  maxlength="50" readonly="fasle" />
								</td> 
								<th >
									单位邮编
								</th>
								<td >
									<html:text property="dwyb" styleId="dwyb"  style="width:50%"  maxlength="6" readonly="fasle"  onkeyup="checkInputData(this);"/>
								</td>
							</tr>
							<tr>
								<th >
									单位地址
								</th>
								<td  colspan="3" >
									<html:text property="dwdz" styleId="dwdz"  style="width:50%"  maxlength="200" readonly="fasle" />
								</td>
							</tr>
							<tr>
								<th >
									单位联系人
								</th>
								<td  >
									<html:text property="dwlxr" styleId="dwlxr"  style="width:50%"  maxlength="35" readonly="fasle" />
								</td>
								<th >
									单位联系电话
								</th>
								<td  >
									<html:text property="dwlxdh" styleId="dwlxdh"  style="width:50%"  maxlength="30" readonly="fasle" onblur="checkPhone(this);"/>
								</td>
							</tr>
						</logic:equal>
						
						<logic:equal name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">档案投递信息</span></td></tr>
						<tr>
							<th >
								档案投递单位
							</th>
							<td  >
								<html:text property="dazjdw" styleId="dazjdw"  style="width:50%"  maxlength="100" readonly="fasle" />
							</td>
							<th >
								档案投递邮编
							</th>
							<td   >
								<html:text property="dazjyb" styleId="dazjyb"  style="width:50%"  maxlength="6" readonly="fasle" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th >
								档案投递地址
							</th>
							<td   colspan="3">
								<html:text property="dazjdz" styleId="dazjdz"  style="width:50%"  maxlength="200" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
								档案投递单位联系人
							</th>
							<td  >
								<html:text property="dazjdwlxr" styleId="dazjdwlxr"  style="width:50%"   maxlength="35" readonly="fasle" />
							</td>
							<th >
								档案投递单位电话
							</th>
							<td   >
								<html:text property="dazjdwdh" styleId="dazjdwdh"  style="width:50%"  maxlength="30" readonly="fasle" onblur="checkPhone(this);"/>
							</td>
						</tr>
						</logic:equal>
						
						<logic:notEqual name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">档案转寄信息</span></td></tr>
						<tr>
							<th >
								档案转寄单位
							</th>
							<td  >
								<html:text property="dazjdw" styleId="dazjdw"  style="width:50%"  maxlength="100" readonly="fasle" />
							</td>
							<th >
								档案转寄邮编
							</th>
							<td   >
								<html:text property="dazjyb" styleId="dazjyb"  style="width:50%"  maxlength="6" readonly="fasle" onkeyup="checkInputData(this);"/>
							</td>
						</tr>
						<tr>
							<th >
								档案转寄地址
							</th>
							<td   colspan="3">
								<html:text property="dazjdz" styleId="dazjdz"  style="width:50%"  maxlength="200" readonly="fasle" />
							</td>
						</tr>
						<tr>
							<th >
								档案转寄单位联系人
							</th>
							<td  >
								<html:text property="dazjdwlxr" styleId="dazjdwlxr"  style="width:50%"   maxlength="35" readonly="fasle" />
							</td>
							<th >
								档案转寄单位电话
							</th>
							<td   >
								<html:text property="dazjdwdh" styleId="dazjdwdh"  style="width:50%"  maxlength="30" readonly="fasle" onblur="checkPhone(this);"/>
							</td>
						</tr>
						</logic:notEqual>
						
						<tr><td align="left" colspan="4"><span style="color:blue">其它信息</span></td></tr>
						<tr>
							<th >
								备注<br><span class="red">(限500字)</span>
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
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();return false;">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>
