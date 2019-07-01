<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwsh.js" defer="defer"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwglnewcomm.js"></script>
		<script type="text/javascript">
		jQuery(document).ready(function(){
			if("10351"==jQuery("#xxdm").val()){
				changeYxssz();
			}
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.gwdm}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${rs.shid}");
		});
		</script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" styleId="gwshform" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwxzmc" id="gwxzmc" value="${rs.gwxzmc }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xn" id ="xn" value ="${rs.xn}"/>
			<input type="hidden" name="yrdwdm" id ="yrdwdm" value ="${rs.yrdwdm}"/>
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden"  id="xqrs" value="${rs.xqrs }"/>
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
			<input type="hidden"  id="qgzq" name="qgzq" value="${cs.qgzq}" />
			<input type="hidden" id="splcid" name="splcid" value="${rs.splcid}" />
			<input type="hidden" id="gwdm" name="gwdm" value="${rs.gwdm}" />
			<input type="hidden" id="sqr" name="sqr" value="${rs.sqrzgh}" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
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
	                               <input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
	                               <input type="hidden" id="xq" name="xq" value=""/>
	                               ${rs.xn }
                                </logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									<input type="hidden" id="xq" name="xq" value="${rs.xq }"/>
									<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
									${rs.xn }&nbsp;&nbsp;&nbsp;${rs.xqmc }
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
						
						
						
						
						<logic:equal value="10351" name="xxdm">
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
								<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" >
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
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
								<html:text name="rs" property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (人)
							</td>
						</tr>
						<tr>
							<th>有效时设置</th>
							<td colspan="3">
								<logic:present name="yxsszList">
									<logic:iterate id="yxsszMap" name="yxsszList" >
										<html:radio name="rs" property="yxssz" onclick="changeYxssz();" value="${yxsszMap.dm}">${yxsszMap.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
								<%-- ${rs.yxsszmc} --%>
							</td>
							<th>是否受岗位申请数限制</th>
							<td colspan="3">
								<logic:present name="isnotList">
									<logic:iterate id="o" name="isnotList" >
										<html:radio name="rs" property="sfsgwsqsxz" onclick="" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>
							<logic:equal name="xxdm" value="10351">
								<tr>
									<th>联系人</th>
									<td colspan="3" >
										${rs.lxr}
									</td>
									<th>联系电话</th>
									<td colspan="3" >
										${rs.lxdh}
									</td>

								</tr>
							</logic:equal>
						<tr>
							<th><span class="red">*</span>岗位开始时间</th>
							<td colspan="3">
								<html:text name="rs" property="gwkssj" styleId="gwkssj" size="10" readonly="true"></html:text>
							</td>
							<th id="gwjssj_th"></th>
							<td id="gwjssj_td" colspan="3">
								<html:text name="rs" property="gwjssj" styleId="gwjssj" size="10" readonly="true"></html:text>
							</td>
						</tr>
						</logic:equal>
						
						
						<logic:notEqual value="10351" name="xxdm">
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
						   <th width="16%">用人单位类别</th>
							<td width="34%" colspan="3" >
								${rs.zxdwlbmc}
							</td>
							<th width="16%">资金来源</th>
							<td width="34%" colspan="3">
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
						<logic:equal value="11488" name="xxdm">
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
						<tr>
							<th>岗位有效时</th>
							<td colspan="7">
								${rs.gwyxs}
							</td>
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
						</logic:notEqual>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th><span class="red">*</span>岗位酬金标准</th>
							<td colspan="3">
								<html:text name="rs" property="gwcjbz" readonly ="true" styleId="gwcjbz" style="width:80px"></html:text> 元/小时
							</td>
							<th><span class="red">*</span>经费划拨</th>
							<td colspan="3">
								<html:text name="rs" property="jfhb" styleId="jfhb" style="width:80px" onblur="checkJe(this)"></html:text> 元/小时
							</td>
						</tr>
						<tr>
							<th>自筹</th>
							<td colspan="6">
								<html:text  name="rs" property="zc" styleId="zc" style="width:80px"  onblur="checkJe(this)"></html:text> 元/小时
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
							<td colspan="7">
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
						<tr>
							<th width="16%">
								申请人
							</th>
							<td width="34%" colspan="3" >
								${rs.sqr }
							</td>
							<th width="16%">
								申请时间
							</th>
							
							<td width="34%" colspan="3">
								${rs.sqsj }
							</td>
						</tr>
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
							<tr >
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
					</tbody>
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
				<thead>
					<tr>
						<th colspan="8">
							<span>审核信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th >
						审核结果
					</th>
					<td id="shjgSpan" colspan="7">
						
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> 审核意见
						<br />
						<font color="red">(限200字)</font>
					</th>
					<td colspan="7">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="gwxxshBc();return false;">
										保存
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
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

