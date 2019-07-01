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
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwxzmc" id="gwxzmc" value="${rs.gwxzmc }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="sgsjs" id="sgsjs" value="" />
			<input type="hidden" name="sqbhs" id="sqbhs" value="" />
			<input type="hidden" name="xhTal" id="xhTal" value="" />
			<input type="hidden"  id="xxdm" value="${xxdm}"/>			
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="height:450px;overflow-x:hidden;overflow-y:auto;">
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
								<logic:equal name="cs" property="qgzq" value="xn">
								         学年
                                </logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
									学年学期
								</logic:equal>
							</th>
							<td width="34%">
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
							
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								岗位名称
							</th>
							
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								岗位性质
							</th>
							
							<td width="34%">
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								需求人数
							</th>
							<td width="34%">
								${rs.xqrs}(在岗人数：<font class="red" >${rs.zgrs}</font>)
							</td>
							<th width="16%">
								困难生数
							</th>
							<td width="34%">
								${rs.knsrs}(人数：<font class="red" >${rs.zgknsrs}</font>)
							</td>
						</tr>
						<logic:equal value="10511" name="xxdm">
						<tr>
						   <th>用人单位类别</th>
							<td >
								${rs.zxdwlbmc}
							</td>
							<th>资金来源</th>
							<td >
								${rs.zjlymc}
							</td>
						</tr>
						</logic:equal>
						<tr>
							<th>有效时设置</th>
							<td>
								${rs.yxsszmc}
							</td>
							<th>是否受岗位申请数限制</th>
							<td>
								${rs.sfsgwsqsxzmc}
							</td>
						</tr>
							<logic:equal name="xxdm" value="11488">
							<tr>
								<th>岗位类型</th>
								<td >
									${rs.gwlx}
								</td>
								<th>助学管理辅导员</th>
					            <td >
					            	${rs.fzls}
					            </td>
							</tr>
							<tr>
								<th>联系电话</th>
								<td >${rs.lxdh}</td>
								<th></th>
								<td ></td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10344">
						<tr>
							<th>所属部门</th>
							<td >
								${rs.ssbmmc}
							</td>
							<th>负责老师</th>
				            <td >
				               ${rs.fzlsxm}
				            </td>
						</tr>
						<tr>
							<th>联系电话</th>
							<td >
								${rs.lxdh}
							</td>
							<th>负责老师办公室</th>
				            <td >
				            	${rs.lsbgs}
				            </td>
						</tr>
						
						</logic:equal>
						<tr>
							<th>岗位有效时</th>
							<td colspan="6">
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
						<logic:equal value="10052" name="xxdm">
						<tr>
							<th width="16%">
								岗位序号
							</th>
							<td width="34%" colspan="6">
								${rs.gwxh}
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
						<tr>
						<th>岗位酬金标准</th>
							<td >
								${rs.gwcjbz} 元/小时
							</td>
							<th>经费划拨</th>
							<td >
								${rs.jfhb} 元/小时
							</td>
						</tr>
						<tr>
							<th>自筹</th>
							<td colspan="6">
								${rs.zc} 元/小时
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
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<logic:equal value="10704" name="xxdm">
							<tr id="gdgcjbzTr" style="display:none">
								<th width="16%">
									固定岗酬金标准
								</th>
								<td width="34%" colspan="6">
									<span id="gdgcjbzSpan">${jcpz.gdgcjbz}</span>
									<span>元/月  &nbsp;&nbsp;(固定岗每月酬金标准)</span>
								</td>
							</tr>
						</logic:equal>
						<logic:equal value="10344" name="xxdm">
							<tr>
								<th align="right" >
									所属校区
								</th>
								<td colspan="3" >
									${rs.ssxqmc }
								</td>
							</tr>
							<tr>
								<th align="right" >
									工作地点
								</th>
								<td colspan="3" >
									${rs.gzdd }
								</td>
							</tr>
							<tr>
								<th align="right" >
									工作时间
								</th>
								<td colspan="3" >
									${rs.gzsj }
								</td>
							</tr>
							<tr>
								<th align="right" >
									工作内容
								</th>
								<td colspan="3" >
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
						<tr>
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
								   劳动强度及技术要求
							    </logic:equal>
							    <logic:notEqual name="xxdm" value="10344">
								  岗位人员要求
							    </logic:notEqual>
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位预期人员效果
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwyqryxg}
							</td>
						</tr>
						<tr>
							<th align="right" >
								<logic:equal name="xxdm" value="10344">
									用工理由
								</logic:equal>
								<logic:notEqual name="xxdm" value="10344">
									备注
								</logic:notEqual>
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>在岗学生信息</span>
							</th>
						</tr>
					</thead>
				</table>
				<div style="height:240px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
						<tbody>
							<tr>
								<td>该岗位无在岗人员，无需退岗！</td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td ><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td >学号</td>
							<td >姓名</td>
							<td >班级</td>
							<td >是否困难生</td>
							<td >是否在校</td>
							<td >目前勤工岗位数</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
					</tbody>
					</logic:notEmpty>
				</table>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:notEmpty name="rs" property="zgryHtml">
									<button type="button" onclick="tgRyxx()">
										退岗
									</button>
									</logic:notEmpty>
									<button type="button" onclick="refreshParentTg();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwglnew/ryxxCk.jsp"%>
			</div>
		</html:form>
	</body>
</html>

