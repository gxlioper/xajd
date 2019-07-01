<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveXhff(){
			var url="xhgl.do?method=xhffUpdate&doType=save";
			
			if($("xh") && $("xh").value==""){
				alertInfo("学号不能为空!",function(){});
				return false;
			}

			if($("ffsj") && $("ffsj").value==""){
				alertInfo("发放时间不能为空!",function(){});
				return false;
			}
			
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function updateXhff(){
			var url="xhgl.do?method=xhffOne&doType=save";
			
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		
		jQuery(function(){
			if(jQuery("#doType").val()=="view"){
				jQuery("#qdz").attr("disabled",true);
				jQuery("#zdz").attr("disabled",true);
			}
		})
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/xhgl" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/xhgl.do?method=xhffUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName" value='xg_view_rcsw_wffxhxs' />
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
										<button type="button" class="button2" id="btn_bc" onclick="saveXhff()">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" class="button2" id="btn_bc" onclick="updateXhff()">
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
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>
							<th style="width:16%">
								年度
							</th>
							<td style="width:34%">
								${nd}
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
								经办人
							</th>
							<td style="width:34%">
								<html:select  property="select_jbr"  style="width:90px" value="${rs.jbr }"
									styleId="jbr">
									<html:option value=""></html:option>
									<html:options collection="jbrList" property="jbrgh"
										labelProperty="jbrxm" />
								</html:select>
							</td>
							<th style="width:16%">
								<font color="red">*</font>发放时间
							</th>
							<td style="width:34%">
								<logic:equal name="doType" value="add">
									<input type="text" name="ffsj" id="ffsj"
										onblur="dateFormatChg(this)" style="cursor:hand"
										onclick="showCalendar(this.id,'y-mm-dd')"/>	
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${rs.ffsj}
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								备注<br/>
								<font color="red">(限60字)</font>
							</th>
							<td colspan="3">
									<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLeng(this,60);"
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
