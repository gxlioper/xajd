<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script	language="javascript" defer="defer" >
			//获得军训学生名单信息
			function getJxxs(){
				var xn = jQuery("#xn").val();
				var url = 'jxgl_grry.do?method=getJxxs&xn='+xn;	
				showTopWin(url,800,600);
			}
			//获得显示学生军训信息
			function checkIs(){
				jQuery.ajaxSetup({async:false});
				// 得到JSON对象
			    var parameter ={};
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["xh"]=escape(jQuery("#xh").val());
				var url = "jxgl_grry_ajax.do?method=getXsjx";
				jQuery.post(url,parameter,
					function(data){
						if(data!=null){
							var json=eval(data);
							jQuery("#xm").text(json[0].xm);
							jQuery("#tuanmc").text(json[0].tuanmc);
							jQuery("#yingmc").text(json[0].yingmc);
							jQuery("#lianmc").text(json[0].lianmc);
							jQuery("#bjmc").text(json[0].bjmc);
							return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
			// 保存
			function Save(){
				if($("xh") && $("xh").value==""){
		     		alertInfo("学号不能为空!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				if($("grrydm") && $("grrydm").value==""){
		     		alertInfo("个人荣誉不能为空!",function(tag){
		     			if(tag=="ok"){
		     				return false;
		     			}
		     		});
		     		return false;
	     		}
				var message = checkSaveInfo();
				if("true"!=message){
					alertInfo(message,function(tag){
						if(tag=="ok"){
							return false;
						}
					});
					return false;
				}
				confirmInfo("是否要保存新数据？",saveGrryInfo);	
				
			}
			function checkSaveInfo(){
				var data = "true";
				jQuery.ajaxSetup({async:false});
				// 得到JSON对象
			    var parameter ={};
			    parameter["xh"]=escape(jQuery("#xh").val());
			    parameter["xn"]=escape(jQuery("#xn").val());
			    parameter["grrydm"]=escape(jQuery("#grrydm").val());
			    parameter["doType"]=escape("add");
				var url = "jxgl_grry_ajax.do?method=checkSaveInfo";
				jQuery.post(url,parameter,
					function(result){
						data = result;
					}
				);
				jQuery.ajaxSetup({async:true});
				return data;
			}
			
			function saveGrryInfo(tag){
				if(tag=="ok"){
					jQuery.ajaxSetup({async:false});
					// 得到JSON对象
				    var parameter ={};	
				    parameter["xn"]=escape(jQuery("#xn").val());
				    parameter["xh"]=escape(jQuery("#xh").val());
				    parameter["grrydm"]=escape(jQuery("#grrydm").val());
				    parameter["bz"]=escape(jQuery("#bz").val());
					var url = "jxgl_grry_ajax.do?method=save";
			        $("divWaiting").style.display="";
					$("divDisable").style.display="";
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					jQuery.ajaxSetup({async:true});
					}
			}
		</script>
	</head>
	<body>
		<html:form action="/jxgl_grry" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
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
								<span>个人荣誉增加</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>学号
							</th>
							<td width="34%">
								<html:text property="xh" styleId="xh" size="10" readonly="true"></html:text>
								<input  style="display: none" onclick="checkIs();" id="disbutton"/>
								<button type="button" onclick="getJxxs();" class="btn_01" id="buttonFindStu">
									选择
								</button>
							</td>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xn" styleId="xn"></html:hidden>
								${rs.xn}
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								姓名
							</th>
							<td>
								<font id="xm"></font>
							</td>
							<th>
								团
							</th>
							<td>
								<font id="tuanmc"></font>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								营
							</th>
							<td>
								<font id="yingmc"></font>
							</td>
							<th>
								连
							</th>
							<td>
								<font id="lianmc"></font>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								班级
							</th>
							<td>
								<font id="bjmc"></font>
							</td>
							<th>
								<font color="red">*</font>个人荣誉
							</th>
							<td>
								<html:select property="grrydm" styleId="grrydm">
									<option value=''>--请选择--</option>
									<html:options collection="grryList" property="grrydm" labelProperty="grrymc"/>
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								备注
							</th>
							<td colspan="3">
								<html:textarea  property='bz' styleId="bz" onblur="chLeng(this,1000);" style="word-break:break-all;width:97%" rows='3' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="Save();return false;">
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

