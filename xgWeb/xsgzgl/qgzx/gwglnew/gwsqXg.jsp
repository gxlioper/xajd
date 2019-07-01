<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsq.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
				<script type="text/javascript"> 
			jQuery(document).ready(function(){
				changeYxssz();
				var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
				//基础配置 设置的薪酬上限
				var gwzgcjsx=jQuery("#gwzgcjsx").val();
				var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
				//岗位设置酬金上限
				var gwcjsx=jQuery("#gwcjsx").val();
				//如果此岗位未设置
					if(gwcjsx==""){
						jQuery("#gwcjsx").val(gwzgcjsx);
						jQuery("#gwcjsxh").text(gwzgcjsx);
					}
					
				if("10704"!=jQuery("#xxdm").val()){
					if("no"==sfsdgwcjsx){
						jQuery("#gwcjsxTr").hide();
					}else{
						jQuery("#gwcjsxTr").show();
						if("no"==sfkgggwcjsx){
							jQuery("#gwcjsx").hide();
							jQuery("#gwcjsxh").show();
							jQuery("#sxcolor").hide();
						}else{
							jQuery("#gwcjsx").show();
							jQuery("#gwcjsxh").hide();
							jQuery("#sxcolor").show();
						}
					}
				}else{
					var gwxz = jQuery("#gwxzdm").find("option:selected").text();
					if(gwxz=="固定岗"){
						jQuery("#gdgcjbzTr").show();
						jQuery("#gwcjsxTr").hide();
					}else{
						if("no"==sfsdgwcjsx){
							jQuery("#gwcjsxTr").hide();
						}else{
							jQuery("#gwcjsxTr").show();
							if("no"==sfkgggwcjsx){
								jQuery("#gwcjsx").hide();
								jQuery("#gwcjsxh").show();
								jQuery("#sxcolor").hide();
							}else{
								jQuery("#gwcjsx").show();
								jQuery("#gwcjsxh").hide();
								jQuery("#sxcolor").show();
							}
						}
					}
				}
				var doType=jQuery("#doType").val();
				//查看处理
				if(doType!="update"){
					if("10704"==jQuery("#xxdm").val()&&'${rs.gwxzmc }'=="固定岗"){
						jQuery("#gdgcjbzTr").show();
						jQuery("#gwcjsxTr").hide();
					}
					jQuery("#gwcjsx").hide();
					jQuery("#gwcjsxh").show();
					jQuery("#sxcolor").hide();
					jQuery(".bz").hide();
				}
				if(jQuery("#xxdm").val() == "10351"){
					jQuery("#qxCheck").bind("click",function(){
						xz(this);
					});
				}
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.gwdm}&tt="+new Date().getTime());
			});

			function selTea(){
				showDialog("选择管理员", 770, 520, "stglStsq.do?method=getTea")	
			}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwglnew" method="post" styleId="form">
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="oldGwxh" id="oldGwxh" value="${rs.gwxh }" />
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<div style='tab；width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="8">
								<span>岗位申请信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<logic:equal name="cs" property="qgzq" value="xn">
								         学年
                                </logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									学年学期
								</logic:equal>
							</th>
							<td width="34%" colspan="3" >
								<logic:equal name="cs" property="qgzq" value="xn">
									<input type="hidden" id="xn" name="xn" value="${rs.xn}"/>
									<input type="hidden" id="xq" name="xq" value=""/>
									${rs.xn }
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									<input type="hidden" id="xq" name="xq" value="${rs.xq}"/>
									<input type="hidden" id="xn" name="xn" value="${rs.xn}"/>
									${rs.xn }&nbsp;&nbsp;&nbsp;${xqmc }
								</logic:equal>

							</td>
							<th width="16%">
								用人部门
							</th>
							
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
						</tr>
						<logic:equal name="doType" value="update">
						<tr>
							<th width="16%">
								<font id="sxcolor" color="red">*</font>岗位名称
							</th>
							<td width="34%" colspan="3" >
								<html:text name="rs" property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								<font id="sxcolor" color="red">*</font>岗位性质
							</th>
							<td width="34%" colspan="3" >
							<logic:equal value="10704" name="xxdm">
								<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" onchange="gwxzChange(this)">
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</logic:equal>	
							<logic:notEqual value="10704" name="xxdm">
								<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px">
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font class="red">*</font>需求人数
							</th>
							<td width="34%" colspan="3" >
								<html:text name="rs" property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkInputNum(this)"></html:text>
							</td>
							<th width="16%">
								<font class="red">*</font>困难生数
							</th>
							
							<td width="34%" colspan="3">
								<html:text name="rs" property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (人)
							</td>
						</tr>
					<logic:equal value="10511" name="xxdm">
						<tr>
							<th><font color="red">*</font>用人单位类别</th>
							<td colspan="3">
								<html:select property="zxdwlb" styleId="zxdwlb" value="${rs.zxdwlb}" style="width:200px;" >
								    <html:option value="">---------请选择---------</html:option>
									<html:options collection="yrdwlblist" property="zxdwlbdm" labelProperty="zxdwlbmc"/>
								</html:select>
							</td>
							<th><font color="red">*</font>资金来源</th>
							<td colspan="3">
								<html:select property="zjly" styleId="zjly"  value="${rs.zjly}" style="width:200px;" >
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="zjlylist" property="zjlydm" labelProperty="zjlymc"/>
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th><span class="red">*</span>有效时设置</th>
							<td colspan="3">
							   <logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio name="rs" property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
							<th><span class="red">*</span>是否受岗位申请数限制</th>
							<td colspan="3">
							   <logic:present name="isnotList">
									<logic:iterate id="o" name="isnotList" >
										<html:radio name="rs" property="sfsgwsqsxz" onclick="" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<logic:equal value="11488" name="xxdm">
							<tr>
								<th><font color="red">*</font>岗位类型</th>
								<td colspan="3">
									<html:select property="gwlx" styleId="gwlx" value="${rs.gwlx}">
										<html:option value=""></html:option>
										<html:option value="固定岗">固定岗</html:option>
										<html:option value="临时岗">临时岗</html:option>
									</html:select>
								</td>
								<th><font color="red">*</font>助学管理辅导员</th>
					            <td colspan="3">
									<html:text property="fzls" styleId="zxfdy" maxlength="10" value="${rs.fzls}" />
					            </td>
							</tr>
							<tr>
								<th><font color="red">*</font>联系电话</th>
								<td colspan="3"><html:text  property="lxdh" value="${rs.lxdh}"  styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" ></html:text></td>
								<th></th>
								<td colspan="3"></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<tr>
							<th><font color="red">*</font>所属部门</th>
							<td colspan="3">
								<html:hidden name="rs" property="ssbm" styleId="ssbm"/>
								${rs.ssbmmc}
							</td>
							<th><font color="red">*</font>负责老师</th>
				            <td colspan="3">
				            	<input type="text" name="stfzrxm" style="width:80px;" id="stfzrxm" readonly="true" value="${rs.fzlsxm}" maxlength="10"/>
				            		<html:hidden name="rs"  property="fzls" styleId="stfzr"  />
									<button class="btn_01" onclick="selTea()"  type="button">选择</button>
				            </td>
						</tr>
						<tr>
							<th><font color="red">*</font>联系电话</th>
							<td colspan="3"><html:text name="rs"  property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" ></html:text></td>
							<th><font color="red">*</font>负责老师办公室</th>
				            <td colspan="3">
				            	<html:text property="lsbgs" name="rs" styleId="lsbgs" maxlength="40"></html:text>
				            </td>
						</tr>
						</logic:equal>
							<logic:equal name="xxdm" value="10351">
								<tr>
									<th><font color="red">*</font>联系人</th>
									<td colspan="3" >
										<html:text  property="lxr" styleId="lxr" maxlength="15" onkeyup="" value="${rs.lxr}" />
									</td>
									<th><font color="red">*</font>联系电话</th>
									<td colspan="3" >
										<html:text  property="lxdh" styleId="lxdh" maxlength="15"
													onkeyup="checkInputLxfx(this)" value="${rs.lxdh}" />
									</td>

								</tr>
							</logic:equal>
						<tr>
							<th><span class="red">*</span>岗位开始日期</th>
							<td colspan="3">
								<html:text name="rs" property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
							<th id="gwjssj_th"></th>
							<td id="gwjssj_td" colspan="3">
								<html:text name="rs" property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						<logic:equal value="12867" name="xxdm">
						<tr id="zxsInfo">
						<th><span class="red">*</span>岗位审核人</th>
								<td colspan="3">
								<input name="gwshr" id="gwshr" class="gwshr" style="width:120px;" value="${rs.gwshr}" readonly="readonly"/>
									<button type="button" onclick="showDialog('教师选择',680,480,'qgzx_gwglnew.do?method=showFdys');return false;" class="btn_01" id="buttonFindStu">
										选择
									</button>
							</td>
							<th>岗位审核人姓名</th>
								<td colspan="3" >
								<input name="gwshrxm" id="gwshrxm" class="gwshrxm" style="width:120px;" value="${rs.gwshrxm}" readonly="readonly"/>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th><span class="red">*</span>岗位酬金标准</th>
							<td colspan="3">
								<html:text name="rs" property="gwcjbz" readonly ="true" styleId="gwcjbz" style="width:80px" ></html:text> 元/小时
							</td>
							<th><span class="red">*</span>经费划拨</th>
							<td colspan="3">
								<html:text name="rs" property="jfhb" styleId="jfhb" style="width:80px" onblur="checkJe(this)"></html:text> 元/小时
							</td>
						</tr>
						<tr>
							<th>自筹</th>
							<td colspan="6">
								<html:text  name="rs" property="zc" styleId="zc" style="width:80px" onblur="checkJe(this)"></html:text> 元/小时
							</td>
						</tr>
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font><logic:equal name="xxdm" value="10344">
								 月报酬预算
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								岗位酬金上限
							    </logic:notEqual>
							</th>
							<td width="34%" colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr id="gdgcjbzTr" style="display:none">
								<th width="16%">
									固定岗酬金标准
								</th>
								<td width="34%" colspan="7">
									<span id="gdgcjbzSpan">${jcpz.gdgcjbz}</span>
									<span>元/月  &nbsp;&nbsp;(固定岗每月酬金标准)</span>
								</td>
							</tr>
						</logic:equal>
							<logic:equal value="10344" name="xxdm">
							<tr>
								<th align="right" >
									<font color="red">*</font>所属校区
								</th>
								<td colspan="3" >
									<html:select property="ssxq" name="rs" styleId="ssxq" style="width:80%">
										<html:options collection="xqList" property="dm" labelProperty="xqmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<font color="red">*</font>工作地点
								</th>
								<td colspan="7" >
									<html:text name="rs" property="gzdd" style="width:97%" styleId="gzdd" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<font color="red">*</font>工作时间
								</th>
								<td colspan="7" >
									<html:text name="rs" property="gzsj" style="width:97%" styleId="gzsj" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<font color="red">*</font>工作内容<br/><font color="red">(限500字)</font>
								</th>
								<td colspan="7" >
									<html:textarea name="rs" property="gznr" style="width:97%" rows="5" styleId="gznr" onblur="chLengs(this,500);"/>
								</td>
							</tr>
							
						</logic:equal>
						<!--温州大学个性化岗位申请学院范围个性化-->
						<logic:equal value="10351" name="xxdm">
							<tr>
								<th>
									<font color="red">*</font>岗位申请开放学院</br>
									<input type="checkbox" id="qxCheck"/>全选
								</th>							
								<td colspan="7">
									<logic:iterate id="t" name="xyList" indexId="index">
										<font style="width:100px;">
										<html:multibox property="sqxy" value="${t.xydm}"></html:multibox>${t.xymc}
										<%if((index+1)%5==0){%> </br> <%}%>
										</font>
									</logic:iterate>
								</td>						
							</tr>
						</logic:equal>
						<logic:notEqual value="10344" name="xxdm">
							<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>岗位描述<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						   </tr>
						</logic:notEqual>
				
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font><logic:equal name="xxdm" value="10344">
								   劳动强度及技术要求
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  岗位人员要求
							    </logic:notEqual><br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								岗位预期人员效果<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								用工理由
							</logic:equal>
							<logic:notEqual name="xxdm" value="10344">
								备注
							</logic:notEqual><br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='4'   />
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="doType" value="update">
							<tr>
							<th width="16%">
								岗位名称
							</th>
							
							<td width="34%" colspan="3" >
								${rs.gwmc }
							</td>
							<th width="16%">
								岗位性质
							</th>
							<td width="34%" colspan="3" >
								${rs.gwxzmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								需求人数
							</th>
							<td width="34%" colspan="3" >
								${rs.xqrs }(人)
							</td>
							<th width="16%">
								困难生数
							</th>
							
							<td width="34%" colspan="3">
								${rs.knsrs }(人)
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						<tr>
						   <th>用人单位类别</th>
							<td colspan="3">
								${rs.zxdwlbmc}
							</td>
							<th>资金来源</th>
							<td colspan="3">
								${rs.zjlymc}
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>有效时设置</th>
							<td colspan="3">
								${rs.yxsszmc}
							</td>
							<th>是否受岗位申请数限制</th>
							<td colspan="3">
								${rs.sfsgwsqsxzmc}
							</td>
						</tr>
						<logic:equal name="xxdm" value="11488">
							<tr>
								<th>岗位类型</th>
								<td colspan="3">
									${rs.gwlx}
								</td>
								<th>助学管理辅导员</th>
					            <td colspan="3">
					            	${rs.fzls}
					            </td>
							</tr>
							<tr>
								<th>联系电话</th>
								<td colspan="3">${rs.lxdh}</td>
								<th></th>
								<td colspan="3"></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<tr>
							<th>所属部门</th>
							<td colspan="3">
								<html:hidden name="rs" property="ssbm" styleId="ssbm"/>
								${rs.ssbmmc}
							</td>
							<th>负责老师</th>
				            <td colspan="3">
				            	${rs.fzlsxm}
				            </td>
						</tr>
						<tr>
							<th>联系电话</th>
							<td colspan="3">${rs.lxdh}</td>
							<th>负责老师办公室</th>
				            <td colspan="3">
				            	${rs.lsbgs}
				            </td>
						</tr>
						
						</logic:equal>

							<logic:equal name="xxdm" value="10351">
								<tr>
									<th>联系人</th>
									<td colspan="3" >${rs.lxr}</td>
									<th>联系电话</th>
									<td colspan="3" >${rs.lxdh}</td>

								</tr>
							</logic:equal>
						<tr>
							<th>岗位有效时</th>
							<td colspan="3">
								${rs.gwyxs}
							</td>
							<logic:equal value="12309" name="xxdm">
							<th>岗位酬金标准</th>
							<td colspan="3">
							${rs.gwcjbz} 元/小时
							</td>
							</logic:equal>
						</tr>
						<logic:equal value="12867" name="xxdm">
						<tr>
							<th>岗位审核人</th>
							<td colspan="3">
								${rs.gwshr}
							</td>
							<th>岗位审核人姓名</th>
							<td colspan="3">
								${rs.gwshrxm}
							</td>
						</tr>	
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
						<tr>
							<th>经费划拨</th>
							<td colspan="3">
							${rs.jfhb} 元/小时
							</td>
							<th>自筹</th>
							<td colspan="3">
							${rs.zc} 元/小时
							</td>
						</tr>
							
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font><logic:equal name="xxdm" value="10344">
								 月报酬预算
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								岗位酬金上限
							    </logic:notEqual>
							</th>
							<td width="34%" colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr id="gdgcjbzTr" style="display:none">
								<th width="16%">
									固定岗酬金标准
								</th>
								<td width="34%" colspan="7">
									<span id="gdgcjbzSpan">${jcpz.gdgcjbz}</span>
									<span>元/月  &nbsp;&nbsp;(固定岗每月酬金标准)</span>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="10351" name="xxdm">
							<tr>
								<th>
									岗位申请开放学院
								</th>							
								<td colspan="7">
									${rs.sqxyms}
								</td>						
							</tr>
						</logic:equal>	
					
						<logic:equal value="10344" name="xxdm">
							<tr>
								<th align="right" >
									所属校区
								</th>
								<td colspan="7" >
									${rs.ssxqmc }
								</td>
							</tr>
							<tr>
								<th align="right" >
									工作地点
								</th>
								<td colspan="7" >
									${rs.gzdd }
								</td>
							</tr>
							<tr>	
								<th align="right" >
									工作时间
								</th>
								<td colspan="7" >
									${rs.gzsj }
								</td>
							</tr>
							<tr>
								<th align="right" >
									工作内容
								</th>
								<td colspan="7" >
									${rs.gznr }
								</td>
							</tr>
							
						</logic:equal>
						

						<logic:notEqual value="10344" name="xxdm">
							<tr style="height:40px">
								<th align="right" >
									岗位描述
								</th>
								<td colspan="7" style="word-break:break-all;width:97%">
									${rs.gwms }
								</td>
							</tr>
						</logic:notEqual>

						<tr style="height:40px">
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								   劳动强度及技术要求
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  岗位人员要求
							    </logic:notEqual>
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwryyq }
							</td>
							
						</tr>
							<tr style="height:40px">
							<th align="right" >
								岗位预期人员效果
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwyqryxg }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								用工理由
							</logic:equal>
							<logic:notEqual name="xxdm" value="10344">
								备注
							</logic:notEqual>
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.bz }
							</td>
						</tr>
						</logic:notEqual>
					</tbody>
					<logic:notEqual name="doType" value="update">
						<thead>
						<tr>
							<th colspan="8">
								<span>审批信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8" id="shlccx">
							
							</td>
						</tr>
					</tbody>
					</logic:notEqual>
				</table>
			</div>
			<div style="height: 30px">
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:equal name="doType" value="update">
										<button type="button" onclick="bcXgGwsq('save')">
											保存草稿
										</button>
										<button type="button" onclick="bcXgGwsq('submit')">
										提交申请
									</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

