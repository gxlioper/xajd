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
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script type="text/javascript">	
			jQuery(function() {
				initData();
				changeYxssz();
				//温州大学个性化默认选中用户所在部门为用人单位申请范围
				if(jQuery("#xxdm").val() == "10351"){
					var sfxy = jQuery("#sfxy").val();
					var checkboxs = jQuery("input[name='sqxy']");
					var bmdm = jQuery("#userDep").val();
					if(sfxy != '1'){//如果为校级用户，全部选定
						jQuery(checkboxs).each(function(i,n){						
							jQuery(n).attr("checked",true);
						})
					}else{//如果为院系用户，只选定自己的部门
						jQuery(checkboxs).each(function(i,n){
							if(jQuery(n).val() == bmdm){
								jQuery(n).attr("checked",true);
								return;
							}
						})
					}
					jQuery("#qxCheck").bind("click",function(){
						xz(this);
					});
				}				
			});

			function selTea(){
				showDialog("选择管理员", 770, 520, "stglStsq.do?method=getTea")	
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_gwglnew" method="post" styleId="form">
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden"  id="qgzq" name="qgzq" value="${rs.qgzq}" />
			<input type="hidden" id="userDep" value="${bmdm}" />
			<input type="hidden" id="sfxy" value="${sfxy}" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<!-- 提示信息 -->
			<div class="prompt" id="div_help" >
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
					同一学年
					<logic:equal name="rs" property="qgzq" value="xq">
						同一学期
					</logic:equal>
					同一部门中<font color="blue">不能增加</font>岗位<font color="blue">名称相同</font>的岗位
					</span>
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
			</div>
			<!-- 提示信息 end-->
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font><logic:equal name="rs" property="qgzq" value="xn">
									学年
								</logic:equal>
								<logic:equal name="rs" property="qgzq" value="xq">
									学年学期
								</logic:equal>
							</th>
							
							<td width="34%">
								<logic:equal name="rs" property="qgzq" value="xn">
									<html:select name="rs" property="xn" styleId="xn" style="width:200px" >
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								   </html:select>
									<input type="hidden" id="xq" name="xq" value=""/>
                                </logic:equal>
                                <logic:equal name="rs" property="qgzq" value="xq">
									<html:select name="rs" property="xn" styleId="xn" style="width:100px" >
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								   </html:select>
								   <html:select name="rs" property="xq" styleId="xq" style="width:100px" >
								   	<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								   </html:select>
                                </logic:equal>
								
							</td>
							<th width="16%">
								<font color="red">*</font>用人部门
							</th>
							
							<td width="34%" >
								<html:select name="rs" property="yrbm" styleId="yrbm" style="width:200px" disabled="${rs.dis}" onchange="changeYrdw(this);return false;">
									<html:options collection="yrbmList" property="bmdm" labelProperty="bmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>岗位名称
							</th>
							
							<td width="34%">
								
								<html:text property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>岗位性质
							</th>
							
							<td width="34%">
								<logic:equal value="10704" name="xxdm">
								<html:select property="gwxzdm" styleId="gwxzdm" style="width:200px" onchange="gwxzChange(this)" >
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</logic:equal>	
							<logic:notEqual value="10704" name="xxdm">
								<html:select property="gwxzdm" styleId="gwxzdm" style="width:200px">
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</logic:notEqual>
								
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>需求人数
							</th>
							
							<td width="34%">
								<html:text property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (人)
							</td>
							<th width="16%">
								<font color="red">*</font>困难生数
							</th>
							<td width="34%">
								<html:text property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)" value="0"></html:text>  (人)
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						<tr>
							<th><font color="red">*</font>用人单位类别</th>
							<td>
								<html:select property="zxdwlb" styleId="zxdwlb" style="width:200px;">
								<html:option value="">---------请选择---------</html:option>
									<html:options collection="yrdwlblist" property="zxdwlbdm" labelProperty="zxdwlbmc"/>
								</html:select>
							</td>
							<th><font color="red">*</font>资金来源</th>
							<td>
								<html:select property="zjly" styleId="zjly" style="width:200px;">
								<html:option value="">---------请选择---------</html:option>
									<html:options collection="zjlylist" property="zjlydm" labelProperty="zjlymc"/>
								</html:select>
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th><span class="red">*</span>有效时设置</th>
							<td>
							   <logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
							<th><span class="red">*</span>是否受岗位申请数限制</th>
							<td>
							   <logic:present name="isnotList">
									<logic:iterate id="o" name="isnotList" >
										<html:radio property="sfsgwsqsxz" onclick="" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
						<logic:equal value="11488" name="xxdm">
							<tr>
								<th><font color="red">*</font>岗位类型</th>
								<td>
									<html:select property="gwlx" styleId="gwlx">
										<html:option value=""></html:option>
										<html:option value="固定岗">固定岗</html:option>
										<html:option value="临时岗">临时岗</html:option>
									</html:select>
								</td>
								<th><font color="red">*</font>助学管理辅导员</th>
					            <td>
									<html:text property="fzls" styleId="zxfdy" maxlength="10" />
					            </td>
							</tr>
							<tr>
								<th><font color="red">*</font>联系电话</th>
								<td><html:text  property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" ></html:text></td>
								<th></th>
								<td></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
							<tr>
								<th><font color="red">*</font>所属部门</th>
								<td>
									<html:select name="rs" property="ssbm" styleId="ssbm" style="width:200px" disabled="${rs.dis}">
										<html:options collection="yrbmList" property="bmdm" labelProperty="bmmc"/>
								    </html:select>
								</td>
								<th><font color="red">*</font>负责老师</th>
					            <td>
					            	<input type="text" name="stfzrxm" style="width:80px;" id="stfzrxm" readonly="true" maxlength="10"/>
									<html:hidden property="fzls" styleId="stfzr" />
									<button class="btn_01" onclick="selTea()"  type="button">选择</button>
					            </td>
							</tr>
							<tr>
								<th><font color="red">*</font>联系电话</th>
								<td><html:text  property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" ></html:text></td>
								<th><font color="red">*</font>负责老师办公室</th>
					            <td>
					            	<html:text property="lsbgs" styleId="lsbgs" maxlength="40"></html:text>
					            </td>
							</tr>
						
						</logic:equal>
						<logic:equal name="xxdm" value="10351">
							<tr>
								<th><font color="red">*</font>联系人</th>
								<td>
									<html:text  property="lxr" styleId="lxr" maxlength="15" onkeyup="" />
								</td>
								<th><font color="red">*</font>联系电话</th>
								<td><html:text  property="lxdh" styleId="lxdh" maxlength="15" onkeyup="checkInputLxfx(this)" /></td>

							</tr>
						</logic:equal>

						<tr>
							<th><span class="red">*</span>岗位开始日期</th>
							<td>
								<html:text  property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
							<th id="gwjssj_th"></th>
							<td id="gwjssj_td">
								<html:text  property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						<logic:equal value="12867" name="xxdm">
						<tr id="zxsInfo">
						<th><span class="red">*</span>岗位审核人</th>
								<td>
								<input type="text" id="gwshr" class="gwshr" name="gwshr" style="width:120px;" readonly="readonly" value="${zxsInfo.zgh}"/>
									<button type="button" onclick="showDialog('教师选择',680,480,'qgzx_gwglnew.do?method=showFdys');return false;" class="btn_01" id="buttonFindStu">
										选择
									</button>
							</td>
							<th>岗位审核人姓名</th>
								<td width="30%"  class="xm">
								<input type="text" id="gwshrxm" class="gwshrxm" name="gwshrxm" style="width:120px;" readonly="readonly" value="${zxsInfo.xm}"/>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th><span class="red">*</span>岗位酬金标准</th>
							<td>
								<html:text  property="gwcjbz" readonly="true" styleId="gwcjbz" style="width:80px" ></html:text> 元/小时
							</td>
						
							<th><span class="red">*</span>经费划拨</th>
							<td>
								<html:text  property="jfhb" styleId="jfhb" style="width:80px" onblur="checkJe(this)"></html:text> 元/小时
							</td>
						</tr>
						<tr>
							<th>自筹</th>
							<td colspan="3">
								<html:text  property="zc" styleId="zc" style="width:80px" onblur="checkJe(this)"></html:text> 元/小时
							</td>
						</tr>
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>
								<logic:equal name="xxdm" value="10344">
								 月报酬预算
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								岗位酬金上限
							    </logic:notEqual>
								
							</th>
							<td width="34%" colspan="6">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月 &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr id="gdgcjbzTr" style="display:none">
								<th width="16%">
									固定岗酬金标准
								</th>
								<td width="34%" colspan="3">
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
									<html:select property="ssxq" styleId="ssxq" style="width:80%">
										<html:options collection="xqList" property="dm" labelProperty="xqmc"/>
									</html:select>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<font color="red">*</font>工作地点
								</th>
								<td colspan="3" >
									<html:text property="gzdd" styleId="gzdd" style="width:97%" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<font color="red">*</font>工作时间
								</th>
								<td colspan="3" >
									<html:text property="gzsj" style="width:97%" styleId="gzsj" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th align="right" >
									<font color="red">*</font>工作内容<br/><font color="red">(限500字)</font>
								</th>
								<td colspan="3" >
									<html:textarea property="gznr" style="width:97%" rows="5" onblur="chLengs(this,500);" styleId="gznr" />
								</td>
							</tr>
							
						</logic:equal>
						<!-- 温州大学个性化 -->
						<logic:equal value="10351" name="xxdm">
							<tr>
								<th>
									<font color="red">*</font>岗位申请开放学院</br>
									<input type="checkbox" id="qxCheck"/>全选
								</th>							
								<td colspan="3">
									<logic:iterate id="t" name="xyList" indexId="index">
									<font style="width:100px;">
									<input type="checkbox" name="sqxy" value="${t.xydm}" />${t.xymc}
									<%if((index+1)%5==0){%> </br> <%}%>
									</font>
									</logic:iterate>
								</td>						
							</tr>
						</logic:equal>
						<logic:notEqual value="10344" name="xxdm">
						    <tr style="height:80px">
							
								<th align="right" >
									<font color="red">*</font>岗位描述<br/><font color="red">(限1000字)</font>
								</th>
								<td colspan="3" >
								<html:textarea  property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
										rows='6' />
								</td>
						   </tr>
						</logic:notEqual>
						<tr style="height:80px">
							<th align="right" >
								<font color="red">*</font>
								<logic:equal name="xxdm" value="10344">
								   劳动强度及技术要求
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  岗位人员要求
							    </logic:notEqual>
								
								<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								岗位预期人员效果<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
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
							</logic:notEqual>
								<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='6' />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="zjBcGwxx()">
										保 存
									</button>
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

