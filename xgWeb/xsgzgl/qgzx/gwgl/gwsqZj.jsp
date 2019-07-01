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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwsq.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function(){
				initData();
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
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/qgzx_gwgl" method="post">
		<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
		<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
		<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style='tab；width:100%;overflow-x:hidden;overflow-y:auto;height:555px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位增加</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								学年
							</th>
							
							<td width="34%">
								<input type="hidden" id="xn" name="xn" value="${rs.xn }"/>
								${rs.xn }
							</td>
							<th width="16%">
								用人部门
							</th>
							
							<td width="34%" >
								<input type="hidden" id="yrbm" name="yrbm" value="${rs.yrbm }"/>
								${rs.yrdwmc }
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
								<html:select property="gwxzdm" styleId="gwxzdm" style="width:200px" >
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
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
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>岗位酬金上限
							</th>
							<td width="34%" colspan="3">
								<html:text property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh"></span>
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font color="red">*</font>岗位描述<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font color="red">*</font>岗位人员要求<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								岗位预期人员效果<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								备注<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3" >
							<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style='height: 30px'>
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="zjBcGwsq()">
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

