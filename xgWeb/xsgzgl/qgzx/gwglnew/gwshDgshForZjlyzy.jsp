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
			if("12867"==jQuery("#xxdm").val()){
				changeXq();
				changeYxssz();
			}
			if("10351"==jQuery("#xxdm").val()){
				changeYxssz();
			}
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${rs.gwdm}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${rs.shid}");
			
			
		});
		function changeXq(){
			if("实习岗" == jQuery("#gwlx").val()){
				jQuery("#xq").attr("disabled","disabled");
				jQuery("#xq").hide();
				jQuery("#xn").attr("disabled","disabled");
				jQuery("#xn").hide();
				jQuery("#nd").show();
				jQuery("#nd").attr("disabled",false);
			}else if("固定岗" == jQuery("#gwlx").val()){
				jQuery("#xq").attr("disabled",false);
				jQuery("#xq").show();
				jQuery("#xn").attr("disabled",false);
				jQuery("#xn").show();
				jQuery("#nd").attr("disabled","disabled");
				jQuery("#nd").hide();
			}
		}
		
		</script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" styleId="gwshform" method="post">
		<html:hidden property="gwid" styleId="gwid"/>
			<input type="hidden" id="gwid" name="gwid" value="${fdyQx }"  />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }"  />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwxzmc" id="gwxzmc" value="${rs.gwxzmc }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="yrdwdm" id ="yrdwdm" value ="${rs.yrdwdm}"/>
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<input type="hidden"  id="xqrs" value="${rs.xqrs }"/>
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
			<input type="hidden"  id="xxdm" value="${rs.shzt}"/>
			<input type="hidden"  id="qgzq" name="qgzq" value="${cs.qgzq}" />
			<input type="hidden" id="splcid" name="splcid" value="${rs.splcid}" />
			<input type="hidden" id="gwdm" name="gwdm" value="${rs.gwdm}" />
			<input type="hidden" id="sqr" name="sqr" value="${rs.sqrzgh}" />
			<input type="hidden" id="sqsj" name="sqsj" value="${rs.sqsj}" />
			<input type="hidden" id="gwyxs" name="gwyxs" value="${rs.gwyxs}" />
			<input type="hidden" id="xqList" name="xqList" value="${xqList}" />
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
								部门
							</th>
							<td width="34%">
								<input type="hidden" id="yrbm" name="yrbm" />
								${rs.yrdwmc }
							</td>
							<th width="16%">
								联系人
							</th>
							
							<td width="34%">
								<input type="hidden" id="lxr" name="lxr" value="${rs.lxr }" />
								${rs.lxr }
							</td>
						</tr>
						<tr>
							<th width="16%">
								申报时间
							</th>
							<td width="34%" >
								<input type="hidden" id="sqsj" name="sqsj" />
								${rs.sqsj}
							</td>
							<th width="16%">
								联系电话
							</th>
							<td width="34%">
								<input type="hidden" id="lxPhone" name="lxPhone" value="${rs.lxphone }" />
								${rs.lxphone }
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>岗位名称
							</th>
							<td width="34%">
								<input type="text" id="gwmc" name="gwmc" value="${rs.gwmc }" />
							</td>
							<th width="16%">
								<font color="red">*</font>需求人数
							</th>
							<td width="34%">
								<input type="text" id="xqrs" name="xqrs" value="${rs.xqrs }" />
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>岗位性质
							</th>
							<td>
								<html:select property="gwlx" styleId="gwlx" onclick="changeXq();" value="${rs.gwxzmc }">
									<html:option value="固定岗">固定岗</html:option>
									<html:option value="实习岗">实习岗</html:option>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>困难生数
							</th>
							<td width="34%">
								<input type="text" id="knsrs" name="knsrs" value="${rs.knsrs }" />
							</td>
						</tr>
						<tr id="zxsInfo">
							<th><span class="red">*</span>岗位审核人</th>
							<td>
								<input type="text" id="gwshr" class="gwshr" name="gwshr" style="width:120px;" readonly="readonly" value="${rs.gwshr }"/>
								<button type="button" onclick="showDialog('教师选择',680,480,'qgzx_gwglnew.do?method=showFdys');return false;" class="btn_01" id="buttonFindStu">
										选择
								</button>
							</td>
							<th>
								<span class="red">*</span>岗位审核人姓名
							</th>
							<td width="30%" class="xm">
								<input type="text" id="gwshrxm" class="gwshrxm" name="gwshrxm"
									style="width: 120px;" readonly="readonly"  value="${rs.gwshrxm }"/>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>岗位要求</th>
							<td colspan="3" >
								<html:textarea  property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
												rows='6' value="${rs.gwryyq }"/>
							</td>
						</tr>
						<tr>
							<th align="right" >
								<font color="red">*</font>工作内容<br/><font color="red">(限500字)</font>
							</th>
							<td colspan="3" >
								<html:textarea property="gwms" style="width:97%" rows="5" onblur="chLengs(this,500);" styleId="gznr"  value="${rs.gwms }"/>
							</td>
						</tr>
						<logic:notEmpty name="xqList">
							<tr>
								<th width="16%">
									<span class="red">*</span>在岗年度
								</th>
								<td colspan="3">
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<html:select property="xq" styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									
									<html:select property="nd" styleId="nd" style="display:none">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								
								</td>
							</tr>
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
						</logic:notEmpty>
						
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
			<div style="height: 50px">
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

