<%@ page import="com.zfsoft.xgxt.xlzx.yysq.xstxxx.XstxxxForm" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysq/js/yyzxDetail.js"></script>

		<script type="text/javascript">
            jQuery(function(){
				var sfdszn = "${xstxxx.sfdszn}";
				if(sfdszn == "0"){
                    jQuery("#div_zmr").show();
				}else{
                    jQuery("#div_zmr").hide();
				}

                jQuery("input[type=radio][name=sfdszn]").click(function(){
                    var sfdszn = jQuery("input[type=radio][name=sfdszn]:checked").val();
                    if(sfdszn == '1'){
                        jQuery("#div_zmr").hide();
                    }else{
                        jQuery("#div_zmr").show();
                    }
                });
            });
		</script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_yysq" method="post" styleId="form">
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.id}" />
			<input type="hidden" name="yyzxrq" id="yyzxrq" value="${yyzxInfo.yyzxrq}"/>	
			<input type="hidden" name="status" id="status" value="${yyzxInfo.status}" />

			<div style='width:100%;height:460px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr >
						<th colspan="4">
							<span>学生填写信息</span>
						</th>
					</tr>
					</thead>
					<tbody id="xstxxx_tb">
					<input type="hidden" name="xh" id="xh" value="${xstxxx.xh}" />
					<%
						XstxxxForm xstxxxForm = (XstxxxForm)request.getAttribute("xstxxx");
					%>
					<tr>
						<th>
							<span class="red">*</span>婚否
						</th>
						<td >
							<label><input type="radio" name="hf" value="1"
									<% if("1".equals(xstxxxForm.getHf())){%> checked="checked" <% } %> />是</label>
							<label><input type="radio" name="hf" value="0"
									<% if("0".equals(xstxxxForm.getHf())){%> checked="checked" <% } %> />否</label>
						</td>
						<th>
							<span class="red">*</span>是否有子女
						</th>
						<td  >
							<label><input type="radio" name="sfyzn" value="1"
									<% if("1".equals(xstxxxForm.getSfyzn())){%> checked="checked" <% } %>/>是</label>
							<label><input type="radio" name="sfyzn" value="0"
									<% if("0".equals(xstxxxForm.getSfyzn())){%> checked="checked" <% } %>/>否</label>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>独生子女
						</th>
						<td  >
							<label><input type="radio" name="sfdszn" value="1"
									<% if("1".equals(xstxxxForm.getSfdszn())){%> checked="checked" <% } %>/>是</label>
							<label><input type="radio" name="sfdszn" value="0"
									<% if("0".equals(xstxxxForm.getSfdszn())){%> checked="checked" <% } %>/>否</label>
							<div id="div_zmr" style="margin-left: 10px;float: right" >
								姊妹人：<input type="text" name="zmr" style="width: 80px;"
										   id="zmr" onblur="checkLen(this,2)" value="${xstxxx.zmr}">
							</div>
						</td>
						<th>
							<span class="red">*</span>电子邮箱
						</th>
						<td  >
							<input type="text" name="dzyx" id="dzyx" value="${xstxxx.dzyx}"
								   maxlength="30"  onblur="checkEmail(this)" />
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>父亲职业
						</th>
						<td >
							<input type="text" name="fqzy" id="fqzy"  value="${xstxxx.fqzy}" onblur="checkLen(this,50)"/>
						</td>
						<th>
							<span class="red">*</span>父亲学历
						</th>
						<td  >
							<input type="text" name="fxl" id="fxl" value="${xstxxx.fxl}" onblur="checkLen(this,50)"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>母亲职业
						</th>
						<td >
							<input type="text" name="mqzy" id="mqzy" value="${xstxxx.mqzy}" onblur="checkLen(this,50)"/>
						</td>
						<th>
							<span class="red">*</span>母亲学历
						</th>
						<td  >
							<input type="text" name="mxl" id="mxl" value="${xstxxx.mxl}" onblur="checkLen(this,50)"/>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>家庭地址
						</th>
						<td >
							<input type="text" name="jtdz" id="jtdz" value="${xstxxx.jtdz}" onblur="checkLen(this,50)"/>
						</td>
						<th>
							<span class="red">*</span>生源地
						</th>
						<td>
							<label><input type="radio" name="syd" value="cs"
									<% if("cs".equals(xstxxxForm.getSyd())){%> checked="checked" <% } %>/>城市</label>
							<label><input type="radio" name="syd" value="xc"
									<% if("xc".equals(xstxxxForm.getSyd())){%> checked="checked" <% } %> />县城</label>
							<label><input type="radio" name="syd" value="nc"
									<% if("nc".equals(xstxxxForm.getSyd())){%> checked="checked" <% } %> />乡镇农村</label>
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>咨询目的
							<br />
							<font color="red">(限制在500字内)</font>
						</th>
						<td colspan="3">
							<textarea name="zxmd" id="zxmd" rows="4" cols=""
									  style="width: 98%" onblur="checkLen(this,500)">${xstxxx.zxmd}</textarea>
						</td>
					</tr>
					</tbody>
				</table>
				<logic:equal value="1" name="sfscsq">
					<table width="100%" border="0" class="formlist" id="yytxxx_tb">
						<thead>
						<tr>
							<th colspan="2">
								<span>预约填写信息</span>
							</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<th rowspan="3" width="20%">一周的情绪状态</th>
							<td width="80%">
								<span class="red">*</span>总体：
								<label><html:radio property="qxztzt" value="hh">很好</html:radio></label>
								<label><html:radio property="qxztzt" value="jh">较好</html:radio></label>
								<label><html:radio property="qxztzt" value="yb">一般</html:radio></label>
								<label><html:radio property="qxztzt" value="jc">较差</html:radio></label>
								<label><html:radio property="qxztzt" value="hc">很差</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>焦虑：
								<label><html:radio property="qxztjl" value="yz">严重</html:radio></label>
								<label><html:radio property="qxztjl" value="jz">较重</html:radio></label>
								<label><html:radio property="qxztjl" value="y">有 &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztjl" value="qw">轻微</html:radio></label>
								<label><html:radio property="qxztjl" value="w">无</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>抑郁：
								<label><html:radio property="qxztyy" value="yz">严重</html:radio></label>
								<label><html:radio property="qxztyy" value="jz">较重</html:radio></label>
								<label><html:radio property="qxztyy" value="y">有 &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztyy" value="qw">轻微</html:radio></label>
								<label><html:radio property="qxztyy" value="w">无</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>上次咨询后的改变</th>
							<td>
								<label><html:radio property="sczxhgb" value="hmx">很明显</html:radio></label>
								<label><html:radio property="sczxhgb" value="jmx">较明显</html:radio></label>
								<label><html:radio property="sczxhgb" value="yb">一般</html:radio></label>
								<label><html:radio property="sczxhgb" value="jbmx">较不明显</html:radio></label>
								<label><html:radio property="sczxhgb" value="bmx">不明显</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>对自己最近的状态</th>
							<td>
								<label><html:radio property="zjzt" value="hmy">很满意</html:radio></label>
								<label><html:radio property="zjzt" value="jmy">较满意</html:radio></label>
								<label><html:radio property="zjzt" value="yb">一般</html:radio></label>
								<label><html:radio property="zjzt" value="jbmc">较不满意</html:radio></label>
								<label><html:radio property="zjzt" value="bmy">不满意</html:radio></label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>本次咨询的问题
								<br />
								<font color="red">(限制在500字内)</font>
							</th>
							<td>
								<html:textarea property="bczxwt" styleId="bczxwt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>上次咨询后的生理、心理状态和社会功能
								<br />
								<font color="red">(限制在500字内)</font>
							</th>
							<td>
								<html:textarea property="zxhzt" styleId="zxhzt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
						</tbody>
					</table>
				</logic:equal>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>预约咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfoList">
							<tr style="height:10px">
								<th  width="16%">
									姓名
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxsxm}
								</td>
							</tr>
							<tr style="height:10px">
								<th  width="16%">
									性别
								</th>
								<td  width="34%">
									${yyzxInfo.zxsxb }
								</td>
								<th width="16%">
									年龄
								</th>
								<td  width="34%">
									${yyzxInfo.zxsage}
								</td>
							</tr>
							<tr style="height:10px">
								<th width="16%">
									联系电话
								</th>
								<td  width="34%">
									${yyzxInfo.lxdh }
									
								</td>
								<th width="16%">
									所在部门
								</th>
								<td  width="34%">
									${yyzxInfo.bmmc }
								</td>
							</tr>
							<logic:equal name="xxdm" value="10026">
								<tr style="height:10px">
									<th width="16%">
										校区
									</th>
									<td   colspan="3">
										${yyzxInfo.xxxq}
									</td>
								</tr>
							</logic:equal>
							<tr style="height:10px">
								<th width="16%">
									咨询地址
								</th>
								<td   colspan="3">
									${yyzxInfo.address}
								</td>
							</tr>
							<tr style="height:10px">
								<th>
									任职资质<br/>
								</th>
								<td colspan="3">
									${yyzxInfo.zxszg }
								</td>
							</tr>
							<tr style="height:30px">
								<th>
									简介<br/>
								</th>
								<td colspan="3">
									${yyzxInfo.zxsjj}
								</td>
							</tr>
							
					</tbody>
					
					 <thead>
						<tr >
							<th colspan="4">
								<span>预约申请信息</span>
							</th>
						</tr>
					</thead>
						<tbody id="yyzxInfo"> 
						<tr style="height:10px">
							<th>
								预约咨询日期
							</th>
							<td >
								<span class="red"><B>${yyzxInfo.yyzxrq}</B></span>
							</td>
							<th width="16%">
								<logic:notEqual name ="doType" value="view"><logic:equal value="2" name="pbfs"><span class="red">*</span></logic:equal></logic:notEqual>预约咨询时段
							</th>
							<td width="34%">
							<logic:equal name ="doType" value="view">
								<logic:notEqual value="2" name="pbfs">
										${ yyzxInfo.yyqssj}&nbsp;
									<logic:notEqual  name="yyzxInfo" property="yyjssj" value="">
										至&nbsp;${yyzxInfo.yyjssj}
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal value="2" name="pbfs">
									${yyzxInfo.sjdmc}
								</logic:equal>
							</logic:equal>
							<logic:notEqual name ="doType" value="view">
								<logic:notEqual value="2" name="pbfs">
									<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${ yyzxInfo.yyqssj}" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'jssj\\')}'})" readonly="true" />&nbsp;至&nbsp;
									<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${ yyzxInfo.yyjssj}" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'qssj\\')}'})" readonly="true" />
								</logic:notEqual>
								<logic:equal value="2" name="pbfs">
									<html:select styleId="sjddm" property="sjddm" value="${yyzxInfo.sjddm}">
										<html:option value=""></html:option>
										<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
									</html:select>
								</logic:equal>
							</logic:notEqual>
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<logic:notEqual name ="doType" value="view"><span class="red">*</span></logic:notEqual>预留联系号码
							</th>
							<td>
							<logic:equal name ="doType" value="view">
									${yyzxInfo.xstell}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:text property="xstell" styleId="xstell"   maxlength="11"  value="${ yyzxInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
								</logic:notEqual>
							</td>
							<th width="16%">
								学年学期
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr  style="height:10px">
							<th>
								<logic:notEqual name ="doType" value="view"><span class="red">*</span></logic:notEqual>咨询主题
							</th>
							<td colspan="3">
								<logic:equal name ="doType" value="view">
									${yyzxInfo.yyzxzt}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yyzxInfo.yyzxzt}" />
								</logic:notEqual>
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								咨询概要
								<logic:notEqual name ="doType" value="view"><br/>
								<font color="red"><B>(限500字)</B></font></logic:notEqual>
							</th>
							<td colspan="3">
								<logic:notEqual name ="doType" value="view">
									<html:textarea  property='yyzxxq' styleId="yyzxxq" style="word-break:break-all;width:99%" value="${ yyzxInfo.yyzxxq}" onblur="chLengs(this,500);" rows='4' />
								</logic:notEqual>
								<logic:equal name ="doType" value="view">
									${ yyzxInfo.yyzxxq}
								</logic:equal>
							</td>
						</tr>
					</tbody>
						
					<thead name ="yyqkInfo">
						<tr>
							<th colspan="4">
								<span>预约反馈情况</span>
							</th>
						</tr>
					</thead>
					<tbody id="yyInfo">
							<tr style="height:10px">
								<th  width="16%">
									预约状态
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.statusmc}
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo">
								<th  width="16%">
									咨询安排日期
								</th>
								<td  width="34%" >
									<span class="red"><B>${yyzxInfo.zxrq}</B></span>
								</td>
								<th width="16%">
									咨询时段
								</th>
								<td width="34%">
									<logic:notEqual value="2" name="pbfs">
										${yyzxInfo.zxqssj}&nbsp;
										<logic:notEqual  name="yyzxInfo" property="zxjssj" value="">
											至&nbsp;${yyzxInfo.zxjssj}
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="2" name="pbfs">
										${yyzxInfo.sjdmczx}
									</logic:equal>
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo">
								<th  width="16%">
									咨询电话
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxtell}
								</td>
							</tr>
							<tr style="height:10px" name ="yyqkInfo" >
								<th  width="16%">
									咨询地址
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxdz}
								</td>
							</tr>
							<tr style="height:10px" name="yysbyytr">
								<th  width="16%">
									预约失败原因<br/>
								<logic:notEqual name="doType" value="view"><font color="red"><B>(限500字)</B></font></logic:notEqual>
								</th>
								<td  width="34%" colspan="3">
								<logic:equal name ="doType" value="view">
									${yyzxInfo.yysbyy}
								</logic:equal>
								<logic:notEqual name ="doType" value="view">
									<html:textarea  property='yysbyy' styleId="yysbyy" value="${yyzxInfo.yysbyy}" style="word-break:break-all;width:99%" onblur="chLengs(this,500);" rows='4' />
								</logic:notEqual>
								</td>
							</tr>
					</tbody>
						
					<thead name="zxxgInfo">
						<tr >
							<th colspan="4">
								<span>咨询相关信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxInfo" name="zxxgInfo">
							<tr style="height:10px">
								<th  width="16%">
									咨询情况
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.zxztmc}
								</td>
							</tr>
							<tr style="height:40px">
								<th  width="16%">
									咨询评价
								</th>
								<td  width="34%" colspan="3">
									${yyzxInfo.xspj}
								</td>
							</tr>
					</tbody>
					</table>
				</div>
			  <table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" id="btx">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveyyzxInfo();return false;">
										保 存
									</button>
									<button onclick="Close();return false;">
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

