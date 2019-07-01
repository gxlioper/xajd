<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/comm/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			CsmsradioCheck();
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm" onsubmit="return false;">
			<input type="hidden" id="objStr" name="objStr"/>
			<input type="hidden" id=cyxy name="cyxys" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>项目申报</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								学年
							</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" style="width:100px" >
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							</td>
							<th><span class="red">*</span>学期</th>
							<td>
								<html:select  property="xq" styleId="xq">
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>项目名称
							</th>
							<td width="30%">
								<html:text property="xmmc" styleId="xmmc" maxlength="50"></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>项目级别
							</th>
							<td width="30%">
								<html:select property="xmjbdm" styleId="xmjbdm" style="width:130px">
									<html:options collection="xmjbList" property="xmjbdm"
										labelProperty="xmjbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>申报部门
							</th>
							<td width="30%">
								<html:select property="sbbmdm" styleId="sbbmdm" style="width:150px" onchange="getCyxy()">
									<html:options collection="bmList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>所属科目
							</th>
							<td width="30%">
								<html:select property="sskmdm" styleId="sskmdm" style="width:130px">
									<html:options collection="xmkmList" property="sskmdm"
										labelProperty="sskmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>可参与人/团数
							</th>
							<td width="30%">
								<label>
									<input type="radio" checked="checked" name="csms" value="1"/>个人
								</label>
								<html:text style="width:50px;"  property="kcyrs" styleId="kcyrs" maxlength="5" value="0"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
								<label>
									<input type="radio"  name="csms" value="2"/>团体
								</label>
								<html:text style="width:50px;display:none;" disabled="true" value="0" property="kcyrs" styleId="kcyrss" maxlength="5"
									onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>项目开始时间
							</th>
							<td width="30%">
								<html:text property="xmkssj"
									onclick="return showCalendar('xmkssj','y-mm-dd');" styleId="xmkssj" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>申报人
							</th>
							<td width="30%" >
								<html:text property="sbr" styleId="sbr" value="${sbr}"/>
								<button onclick="showDialog('教师选择',680,480,'szdw_fdyjtff.do?method=showFdysNotF5');return false;" class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
							<th width="20%">
								<font color="red">*</font>申报人联系方式
							</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)"></html:text>
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								是否设立奖项
							</th>
							<td width="30%" >
								<html:radio  property="sfsljx"  value="1" onclick="changeSljx();">是</html:radio>
								<html:radio  property="sfsljx"  value="0" onclick="changeSljx();" >否</html:radio>
							</td>
							<th width="20%">
								<font color="red">*</font>基础学分
							</th>
							<td width="30%" >
								<html:text property="jcxf" styleId="jcxf" maxlength="5" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>
							</td>
						</tr>
						<logic:equal value="13627" name="xxdm">
						<tr>
							<th width="20%">
								项目场地
							</th>
							<td width="30%">
								<html:text property="xmcd" styleId="xmcd" maxlength="25" ></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>板块所属
							</th>
							<td width="30%">
								<html:select property="bkgs" styleId="bkgs" style="width:130px">
									<html:options collection="bkgsList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						</logic:equal>
					</tbody>
				 </table>
				 <div style="overflow-y:auto;" id="jxDiv">
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr>
						<th colspan="4">
								<span>项目奖项信息</span>
							<img src="xsxx/fbgl/images/add-icons-1.gif" alt="增加" onclick="addRow();return false;">
							<img src="xsxx/fbgl/images/delete-icons-2.gif" alt="删除" onclick="delRow();return false;">
						</th>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'><font color="red">*</font>奖项名称</td>
							<td width='15%'><font color="red">*</font>附加学分</td>
							<td width='15%'><font color="red">*</font>奖项顺序</td>
						</tr>
						
					</thead>
					<tbody id="tbody_xmjxxx">
						<tr>
							<td>
								<input type="checkbox" />
							</td>
							<td>
								<input type="text" name="jxmc" maxlength="20"/>
							</td>
							<td >
								<input type="text" name="fjxf" maxlength="5" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"/>
							</td>
							<td >
							    <input type="text" name="xssx" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');"/>
							</td>																			
						</tr>
					</tbody>
				</table>
				</div>
				<table width="100%" border="0" class="formlist">
					<logic:equal value="11032" name="xxdm">
					<tr>
					<th>
						可参与学院
					</th>
					<td colspan="3">
						<logic:iterate id="t" name="xyList" indexId="index">
						<font style="width:100px;">
						<input type="checkbox" name="cyxy" value="${t.xydm}" />${t.xymc}
						<%if((index+1)%5==0){%> </br> <%}%>
						</font>
						</logic:iterate>
					</td>
					</tr>
					</logic:equal>
					<tr><th width="20%">项目描述
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="xmms" styleId="xmms" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%">得/扣分依据
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="dkfyj" styleId="dkfyj" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
					<tr><th width="20%">参与要求
								</br><font color="red">(限500字)</font></th>
							<td colspan="3">
								<html:textarea property="cyyq" styleId="cyyq" 
											   onkeypress="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
					</tr>
				</table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveXmsbjg('save');">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

