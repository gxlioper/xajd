<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveXszzc(){
			var url="rcsw_xszgl.do?method=xszzcUpdate&doType=save";
			jQuery.ajaxSetup({async:false});
			if($("xh") && $("xh").value==""){
				alertInfo("学号不能为空!",function(){});
				return false;
			}
			
			if($("select_jbr") && $("select_jbr").value==""){
				alertInfo("经办人不能为空!",function(){});
				return false;
			}
			jQuery.ajaxSetup({async:true});
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function updateSyddk(){
			var url="zgmsxy_xszz.do?method=syddkOne&doType=save";
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/rcsw_xszgl" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/rcsw_xszgl.do?method=xszzcUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='xg_view_rcsw_wzcxs' />
			<input type="hidden" name="pkValue" id="pkValue" value='${rs.pkValue}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<!-- 隐藏域 -->
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" class="button2" id="btn_bc" onclick="saveXszzc()">
											保 存
										</button>
									</logic:equal>
									<button type="button" class="button2" id="btn_gb" onclick="Close();return false;">
											关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead >
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>学号
							</th>
							<td style="width:34%">
								<html:text property="xh" styleId="xh" readonly="true"
									value="${rs.xh }" />
								<logic:equal name="doType" value="add">
									<button type="button" class="btn_01" id="" onclick="sendXx();return false;">
										选 择
									</button>
								</logic:equal>
							</td>
							<th style="width:16%">
								学年
							</th>
							<td style="width:34%">
								${xn}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
							<th style="width:16%">
								学期
							</th>
							<td style="width:34%">
								${xqmc}
							</td>
						</tr>
						
						<tr>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								<font color="red">*</font>经办人
							</th>
							<td style="width:34%">
								<logic:equal name="doType" value="add">
								<html:select  property="select_jbr"  style="width:90px"
										styleId="select_jbr">
										<html:option value=""></html:option>
										<html:options collection="jbrList" property="jbrgh"
											labelProperty="jbrxm" />
								</html:select>
								</logic:equal>
								<logic:equal name="doType" value="view">
										${rs.czrxm }
								</logic:equal>
							</td>
							<th style="width:16%">
								
							</th>
							<td style="width:34%">
								
							</td>
						</tr>	
						<tr>
							<th style="width:16%">
								备注
								<br/>(限500字)
							</th>
							<td colspan="3">
									<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"
										rows='4' />
							</td>
						</tr>	
					</tbody>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>
