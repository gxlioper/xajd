<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script>
			function mbxxXgBc(){
				if($("xm") && $("xm").value.trim()==""){
			 		alertInfo("姓名不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if($("sfzh") && $("sfzh").value.trim()==""){
			 		alertInfo("身份证号不能为空!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				jQuery.ajaxSetup({async:false});	
				// 得到JSON对象
			    var parameter ={};	
			    parameter["xm"]=escape(jQuery("#xm").val());
				parameter["xb"]=escape(jQuery("#xb").val());
				parameter["csrq"]=escape(jQuery("#csrq").val());
				parameter["sfzh"]=escape(jQuery("#sfzh").val());
				parameter["whcd"]=escape(jQuery("#whcd").val());
				parameter["zy"]=escape(jQuery("#zy").val());
				parameter["zc"]=escape(jQuery("#zc").val());
				parameter["zw"]=escape(jQuery("#zw").val());
				parameter["rdsj"]=escape(jQuery("#rdsj").val());
				parameter["zzmmdm"]=escape(jQuery("#zzmmdm").val());
				parameter["sftwjr"]=escape(jQuery("#sftwjr").val());
				parameter["zygw"]=escape(jQuery("#zygw").val());
				parameter["gzdw"]=escape(jQuery("#gzdw").val());
				parameter["lxfs"]=escape(jQuery("#lxfs").val());
				parameter["bgdh"]=escape(jQuery("#bgdh").val());
				parameter["dh"]=escape(jQuery("#dh").val());
				parameter["mbzw"]=escape(jQuery("#mbzw").val());
				parameter["zzbx"]=escape(jQuery("#zzbx").val());
				parameter["jsxl"]=escape(jQuery("#jsxl").val());
				parameter["jtdz"]=escape(jQuery("#jtdz").val());
				var url = "rwgl_mbxxgl_ajax.do?method=mbxxYz";
				url = "rwgl_mbxxgl_ajax.do?method=mbxxBc&doType=update";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						if("保存成功"==result){
							alertInfo(result);
						}else{
							alertInfo(result,function(tag){
				     			if(tag=="ok"){
				     				return false;
				     			}
				     		});
				     		return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body style="width:97%">
		<html:form action="/rwgl_mbxxgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>民兵信息修改</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>姓名
							</th>
							<td width="34%">
								<html:text name="rs" property="xm" styleId="xm" maxlength="16" styleClass="text_nor" />
							</td>
							<th width="16%">
								性别
							</th>
							<td width="34%" >
								<html:select name="rs" property="xb" styleId="xb" style="width:150px">
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								出生日期
							</th>
							<td width="34%">
								<html:text name="rs" property="csrq" styleId="csrq" maxlength="10" onclick="return showCalendar('csrq','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>身份证号
							</th>
							<td width="34%">
								${rs.sfzh }
								<html:hidden name="rs" property="sfzh" styleId="sfzh" ></html:hidden>
							</td>
						</tr>
						<tr>
							<th width="16%">
								文化程度
							</th>
							<td width="34%">
								<html:text name="rs" property="whcd" styleId="whcd" maxlength="16" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								专业
							</th>
							<td width="34%">
								<html:text name="rs" property="zy" styleId="zy" maxlength="25" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								职称
							</th>
							<td width="34%">
								<html:text name="rs" property="zc" styleId="zc" maxlength="32" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								职务
							</th>
							<td width="34%">
								<html:text name="rs" property="zw" styleId="zw" maxlength="32" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								入队时间
							</th>
							<td width="34%">
								<html:text name="rs" property="rdsj" styleId="rdsj" maxlength="10" onclick="return showCalendar('rdsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%">
								<html:select name="rs" property="zzmmdm" styleId="zzmmdm" style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								转业退伍军人
							</th>
							<td width="34%">
								<html:select name="rs" property="sftwjr" styleId="sftwjr" style="width:150px">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
							</td>
							<th width="16%">
								专业岗位
							</th>
							<td width="34%">
								<html:text name="rs" property="zygw" styleId="zygw" maxlength="32" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								工作单位
							</th>
							<td width="34%">
								<html:text name="rs" property="gzdw" styleId="gzdw" maxlength="32" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								联系方式
							</th>
							<td width="34%">
								<html:text name="rs" property="lxfs" styleId="lxfs" maxlength="60" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								办公电话
							</th>
							<td width="34%">
								<html:text name="rs" property="bgdh" styleId="bgdh" maxlength="20" styleClass="text_nor" onkeydown="return onlyNum(this,20)" onmousedown="return onlyNum(this,20)" ></html:text>
							</td>
							<th width="16%">
								短号
							</th>
							<td width="34%">
								<html:text name="rs" property="dh" styleId="dh" maxlength="60" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								民兵职务
							</th>
							<td width="34%">
								<html:text name="rs" property="mbzw" styleId="mbzw" maxlength="32" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								政治表现
							</th>
							<td width="34%">
								<html:text name="rs" property="zzbx" styleId="zzbx" maxlength="32" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								家庭地址
							</th>
							<td width="84%" colspan="3">
								<html:text name="rs" property="jtdz" styleId="jtdz" maxlength="500" style="width:465px"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								何时受过何种军事训练<br/><font class="red">(限1000字)</font>
							</th>
							<td width="84%" colspan="3">
								<html:textarea name="rs" property='jsxl' styleId="jsxl" style="word-break:break-all;width:99%"
										rows='3' onblur="chLeng(this,1000)"/>
							</td>
						</tr>
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="mbxxXgBc()">
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
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

