<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script>
		jQuery(document).ready(function(){
			var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
			// 基础配置 设置的薪酬上限
			var gwzgcjsx=jQuery("#gwzgcjsx").val();
			var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
			// 岗位设置酬金上限
			var gwcjsx=jQuery("#gwcjsx").val();
			// 如果此岗位未设置
			if(gwcjsx==""){
				jQuery("#gwcjsx").val(gwzgcjsx);
				jQuery("#gwcjsxh").text(gwzgcjsx);
			}
			if("no"==sfsdgwcjsx){
				jQuery("#gwcjsxTr").hide();
			}else{
				jQuery("#gwcjsxTr").show();
				jQuery("#gwcjsx").hide();
				jQuery("#gwcjsxh").show();
				jQuery("#sxcolor").hide();
			}
			if("10704"==jQuery("#xxdm").val()&&'${rs.gwxzmc}'=="固定岗"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
		});
		//显示退岗信息
		function showTgxsxx(obj){
			jQuery(obj).parent().parent().hide();
			jQuery("#div_tgxsxx").show();
		}

		//新版查看弹出层
		function zxsxxView(xh) {
			showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
		}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
						
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<div style="height:450px;overflow-x:hidden;overflow-y:auto;padding-right:3px;">
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
									${rs.xn }
								</logic:equal>
								<logic:equal name="cs" property="qgzq" value="xq">
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
								${rs.xqrs}(在岗人数：<font color="red" >${rs.zgrs}</font>)
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
						<logic:equal name="xxdm" value="10351">
							<tr>
								<th>联系人</th>
								<td>
									${rs.lxr}
								</td>
								<th>联系电话</th>
								<td>${rs.lxdh}</td>

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
							<td>
								${rs.gwshr}
							</td>
							<th>岗位审核人姓名</th>
							<td>
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
				<!-- <div style="height:250px;overflow-y:auto;"> -->
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
					<tbody>
						<tr>
							<td>该岗位无在岗人员！</td>
						</tr>
						<tr>
							<td>
								<a href="#" onclick="showTgxsxx(this);return false;">查看退岗学生信息...</a>
							</td>
						</tr>
					</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td width='10px'>行号</td>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
							<td width='25%'>班级</td>
							<td width='12%'>是否困难生</td>
							<td width='10%'>是否在校</td>
							<logic:equal value="12036" name='xxdm'>
							<td width='10%'>上岗月份</td>
							</logic:equal>
							<logic:notEqual value="12036" name='xxdm'>
							<td width='10%'>上岗日期</td>
							</logic:notEqual>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
						<tr>
							<td colspan="7">
								<a href="#" onclick="showTgxsxx(this);return false;">查看退岗学生信息...</a>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
				</table>
				<div id="div_tgxsxx" style="display: none;">
					<table border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>退岗学生信息</span>
								</th>
							</tr>
						</thead>
					</table>
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<logic:empty name="rs" property="tgryHtml">
						<tbody>
							<tr>
								<td colspan="4">该岗位无退岗人员！</td>
							</tr>
						</tbody>
						</logic:empty>
						<logic:notEmpty name="rs" property="tgryHtml">
						<thead>
							<tr>
								<td width='10px'>行号</td>
								<td width='15%'>学号</td>
								<td width='15%'>姓名</td>
								<td width='25%'>班级</td>
								<td width='15%'>是否困难生</td>
								<td width='15%'>是否在校</td>
								<td width='15%'>上岗日期</td>
								<td width='15%'>退岗日期</td>
							</tr>
						</thead>
						<tbody id="tbody_tgryxx">
							${rs.tgryHtml }
						</tbody>
						</logic:notEmpty>
					</table>
					</div>
				<!--</div>-->
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
							
								<div class="btn">
									<button type="button" onclick="iFClose();">
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

