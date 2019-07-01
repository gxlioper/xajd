<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		
		<script	type="text/javascript">
			function checkIs(){
				var bjdm = jQuery("#bjdmxgq").val();		
				jQuery.getJSON('gyglnew_qszf_ajax.do?method=getFzbjForZflsgh',{zflsgh:jQuery("#zflsghxgq").val()},function(data){
					jQuery('#fzbj').empty();
					jQuery('#xsszld').empty();
					jQuery('#xsszqsh').empty();
					jQuery('#fzbj').append("<option value=''>--请选择--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							if(bjdm==data[i].bjdm){
								var option = "<option selected=\"selected\" value=\"" + data[i].bjdm + "\">" + data[i].bjmc + "</option>";
								jQuery('#fzbj').append(option);
								getLd();
							}else{
								var option = "<option value=\"" + data[i].bjdm + "\">" + data[i].bjmc + "</option>";
								jQuery('#fzbj').append(option);
							}
							
						}
					}
				});
				var parameter ={};
				parameter["zflsgh"]=escape(jQuery("#zflsghxgq").val());
				var url = "gyglnew_qszf_ajax.do?method=getZflsxmForZflsgh";
				jQuery.post(url,parameter,
						function(result){
							jQuery('#zflsxm').html(result);
						}
					);
			}
			
			function getLd(){
				var lddm = jQuery("#lddmxgq").val();
				jQuery.getJSON('gyglnew_qszf_ajax.do?method=getLdForFzbj',{fzbj:jQuery("#fzbj").val()},function(data){
					jQuery('#xsszld').empty();
					jQuery('#xsszqsh').empty();
					jQuery('#xsszld').append("<option value=''>--请选择--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							if(lddm==data[i].lddm){
								var option = "<option selected=\"selected\" value=\"" + data[i].lddm + "\">" + data[i].ldmc + "</option>";
								jQuery('#xsszld').append(option);
								getQsh();
							}else{
								var option = "<option value=\"" + data[i].lddm + "\">" + data[i].ldmc + "</option>";
								jQuery('#xsszld').append(option);
							}
						}
					}
				});
			}
			
			function getQsh(){
				var qsh = jQuery("#qshxgq").val();
				jQuery.getJSON('gyglnew_qszf_ajax.do?method=getQshForLd',{fzbj:jQuery("#fzbj").val(),xsszld:jQuery("#xsszld").val()},function(data){
					jQuery('#xsszqsh').empty();
					jQuery('#xsszqsh').append("<option value=''>--请选择--</option>");
					if(data != null && data.length != 0){
						for(var i=0; i<data.length; i++){
							if(qsh==data[i].xsszqsh){
								var option = "<option selected=\"selected\" value=\"" + data[i].xsszqsh + "\">" + data[i].xsszqsh + "</option>";
								jQuery('#xsszqsh').append(option);
							}else{
								var option = "<option value=\"" + data[i].xsszqsh + "\">" + data[i].xsszqsh + "</option>";
								jQuery('#xsszqsh').append(option);
							}
							
						}
					}
				});
			}
			
			function Save(){
			if($("zflsgh") && $("zflsgh").value==""){
	     		alertInfo("走访老师工号不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("fzbj") && $("fzbj").value==""){
	     		alertInfo("负责班级不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("xsszld") && $("xsszld").value==""){
	     		alertInfo("学生所在楼栋不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("xsszqsh") && $("xsszqsh").value==""){
	     		alertInfo("学生所在寝室号不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
	     	if($("xqsj") && $("xqsj").value==""){
	     		alertInfo("走访时间不能为空!",function(tag){
	     			if(tag=="ok"){
	     				return false;
	     			}
	     		});
	     		return false;
	     	}
				confirmInfo("是否要保存新数据？",saveQszfInfo);	
			}
			
			function saveQszfInfo(tag){
			if(tag=="ok"){
				jQuery.ajaxSetup({async:false});	
				// 得到JSON对象
			    var parameter ={};	
				parameter["zflsgh"]=escape(jQuery("#zflsghxgq").val());
				parameter["fzbj"]=escape(jQuery("#fzbj").val());
				parameter["xsszld"]=escape(jQuery("#xsszld").val());
				parameter["xsszqsh"]=escape(jQuery("#xsszqsh").val());
				parameter["fzbjxgq"]=escape(jQuery("#bjdmxgq").val());
				parameter["xsszldxgq"]=escape(jQuery("#lddmxgq").val());
				parameter["xsszqshxgq"]=escape(jQuery("#qshxgq").val());
				parameter["xqsj"]=escape(jQuery("#xqsj").val());
				parameter["bz"]=escape(jQuery("#bz").val());
				var url = "gyglnew_qszf_ajax.do?method=qszfXg";
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
			
			function modiInit(){
				var zflsgh = jQuery("#zflsghxgq").val();
				if(zflsgh!="" && zflsgh!=null){
					checkIs();
				}
			}
		</script>
	</head>
	<body onload="modiInit()">
	
		<html:form action="/gyglnew_qszf" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<input type="hidden" name="zflsgh" id="zflsghxgq" value="${rs.zgh}" />
			<input type="hidden" name="bjdmxgq" id="bjdmxgq" value="${rs.bjdm }" />
			<input type="hidden" name="lddmxgq" id="lddmxgq" value="${rs.lddm }" />
			<input type="hidden" name="qshxgq" id="qshxgq" value="${rs.qsh }" />
			<input type="hidden" name="xqsj" id="xqsj" value="${rs.xqsj }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:360px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>修改寝室走访</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="20%">
								走访老师工号
							</th>
							
							<td width="40%" colspan="3" >
								${rs.zgh}
							</td>
							<th width="15%">
								走访老师姓名
							</th>
							
							<td width="25%" colspan="3" id="zflsxm">
							</td>
						</tr>
						<tr style="height:22px">
							<th width="20%">
								<font color="red">*</font>负责班级
							</th>
							<td width="40%" colspan="3" >
								<html:select property="fzbj" styleId="fzbj" onchange="getLd();">
								</html:select>
							</td>
							<th width="15%">
								<font color="red">*</font>学生所在楼栋
							</th>
							
							<td width="25%" colspan="3" >
								<html:select property="xsszld" styleId="xsszld" onchange="getQsh();">
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
						<th width="20%">
								<font color="red">*</font>学生所在寝室号
							</th>
							
							<td width="40%" colspan="3" >
								<html:select property="xsszqsh" styleId="xsszqsh">
								</html:select>
							</td>
							<th width="15%">
								走访时间
							</th>
							
							<td width="25%" colspan="3" id="xqsj">
								${rs.xqsj}
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								备注
							</th>
							<td colspan="11" >
							<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='10' value="${rs.bz }"  />
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

