<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		
		<script	type="text/javascript">
			function getLd(){
				var ld = jQuery("#ld").val();		
				jQuery.getJSON('gyglnew_wsjc_ajax.do?method=getAllLd',{},function(data){
					jQuery('#ldmc').empty();
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							if(ld==data[i].lddm){
								jQuery('#ldmc').html(data[i].ldmc);
							}
						}
					}
				});
			}
			
			function Save(){
			if($("ld") && $("ld").value==""){
	     		alertInfo("楼栋不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("qs") && $("qs").value==""){
	     		alertInfo("寝室不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("jcsj") && $("jcsj").value==""){
	     		alertInfo("检查时间不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	
	     	if($("jcry") && $("jcry").value==""){
	     		alertInfo("检查人员不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
				confirmInfo("是否要保存新数据？",saveWsjcInfo);	
			}
			
			function saveWsjcInfo(tag){
			if(tag=="ok"){
				jQuery.ajaxSetup({async:false});	
				// 得到JSON对象
			    var parameter ={};	
			    parameter["wsdjbz"]=escape(jQuery("#wsdjbz").val());
				parameter["dgldqbz"]=escape(jQuery("#dgldqbz").val());
				parameter["jcry"]=escape(jQuery("#jcry").val());
				parameter["jcsj"]=escape(jQuery("#jcsj").val());
				parameter["dgldq"]=escape(jQuery("#dgldq").val());
				parameter["wsdj"]=escape(jQuery("#wsdj").val());
				parameter["ld"]=escape(jQuery("#ld").val());
				parameter["qs"]=escape(jQuery("#qs").val());
				var url = "gyglnew_wsjc_ajax.do?method=wsjcXg";
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
	<body onload="getLd()">
	
		<html:form action="/gyglnew_wsjc" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<input type="hidden" name="ld" id="ld" value="${rs.ld }" />
			<input type="hidden" name="qs" id="qs" value="${rs.qs }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:550px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>修改卫生检查</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								楼栋
							</th>
							
							<td width="34%" colspan="3" id="ldmc">
							</td>
							<th width="16%">
								寝室
							</th>
							
							<td width="34%" colspan="3" id="qs">
								${rs.qs}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%" colspan="3" id="xy">
								${rs.xy}
							</td>
							<th width="16%">
								班级
							</th>
							
							<td width="34%" colspan="3" id="bj">
								${rs.bj}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								性别
							</th>
							<td width="34%" colspan="3" id="xb">
								${rs.xb}
							</td>
							<th width="16%">
								所管辖辅导员
							</th>
							
							<td width="34%" colspan="3" id="sgxfdy">
								${rs.sgxfdy}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								卫生等级
							</th>
							<td width="34%" colspan="3" >
								<html:text property="wsdj" styleId="wsdj" maxlength="50" style="width: 250px" value="${rs.wsdj}">
								</html:text>
							</td>
							<th width="16%">
								大功率电器
							</th>
							
							<td width="34%" colspan="3" >
								<html:text property="dgldq" styleId="dgldq" maxlength="50" style="width: 250px" value="${rs.dgldq}">
								</html:text>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<font color="red">*</font>检查时间
							</th>
							<td width="34%" colspan="3" >
								<html:text property="jcsj" styleId="jcsj"   style="width:90px" value="${rs.jcsj}"
									onclick="return showCalendar('jcsj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
							<th width="16%">
								<font color="red">*</font>检查人员
							</th>
							
							<td width="34%" colspan="3" >
								<html:text property="jcry" styleId="jcry" maxlength="50" style="width: 250px" value="${rs.jcry}">
								</html:text>
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								卫生等级备注
							</th>
							<td colspan="7" >
							<html:textarea  property='wsdjbz' styleId="wsdjbz" style="word-break:break-all;width:95%" onblur="chLeng(this,1000);"
									rows='8' value="${rs.wsdjbz }"  />
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								大功率电器备注
							</th>
							<td colspan="7" >
							<html:textarea  property='dgldqbz' styleId="dgldqbz" style="word-break:break-all;width:95%" onblur="chLeng(this,1000);"
									rows='8' value="${rs.dgldqbz }"  />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
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

