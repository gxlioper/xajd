<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		//保存预约申请表、咨询信息表
		function saveYysqInfo(){
				if(jQuery("#zxrq").val()==""){
					return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
				}
				var zxurl = "xlzx_zxyyclnew.do?method=updateXlzxInfo";
				//防止出现undefined		
				var qssj = (!jQuery("#zxqssj").val()) ? "" : jQuery("#zxqssj").val();
				var jssj = (!jQuery("#zxjssj").val()) ? "" : jQuery("#zxjssj").val();
				var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
				if(jQuery("#pbfs").val() == '2'){
					if(sjddm == ""){
						return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
					}
				}	
				var	 zxParameter ={
						zxrq:jQuery("#zxrq").val(),
						qssj:qssj,
						jssj:jssj,
						sjddm:sjddm,
						//xh:jQuery("#xh").val(),
						yyid:jQuery("#yyid").val(),
						zgh:jQuery("#zgh").val(),
						xstell:jQuery("#sjhm").val(),
						zxstatus: 1,//咨询状态   1待咨询2已咨询
						zxtell:jQuery("#zxtell").val(),
						zxdz:encodeURI(encodeURI(jQuery("#zxdz").val())),
						bz:encodeURI(encodeURI(jQuery("#bz").val()))
					};
				if(jQuery("#jzxx_tb").length>0){
                    if(!checkJzxx()){
                        return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
                    }
                    zxParameter["jzxm"] = jQuery("#jzxm").val();
                    zxParameter["jzxb"] = jQuery("input[name=jzxb]:checked").val();
                    zxParameter["jzlxdh"] = jQuery("#jzlxdh").val();
                    zxParameter["gx"] = jQuery("#gx").val();
                    zxParameter["jzdzyx"] = jQuery("#jzdzyx").val();
                    zxParameter["jtjq"] = jQuery("#jtjq").val();
                    zxParameter["fqzy"] = jQuery("#fqzy").val();
                    zxParameter["fxl"] = jQuery("#fxl").val();
                    zxParameter["mqzy"] = jQuery("#mqzy").val();
                    zxParameter["mxl"] = jQuery("#mxl").val();
                    zxParameter["jtdz"] = jQuery("#jtdz").val();
                    zxParameter["xssfzx"] = jQuery("input[name=xssfzx]:checked").val();
                    zxParameter["fdysfzx"] = jQuery("input[name=fdysfzx]:checked").val();
                    zxParameter["lfmd"] = jQuery("#lfmd").val();
                    zxParameter["jzxxid"] = jQuery("#jzxxid").val();
                    zxParameter["jzxxsqid"] = jQuery("#jzxxsqid").val();
				}
					jQuery.ajaxSetup({async:false});
						jQuery.post(zxurl,zxParameter,function(data){
							if(data == true){
								showAlert("保存成功！",{},{"clkFun":function(){
									frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
									window.close();
								}});
							}else{
								showAlert("保存失败！");
							}
						},'json');
					jQuery.ajaxSetup({async:true});
		}
        function checkEmail(obj) {
            var dzyx = jQuery(obj).val();
            if(!isEmail(dzyx) && dzyx!=""){
                showAlertDivLayer("请输入正确的电子邮箱！");
                return false;
            }
        }

        function checkLxdh(){
            if(!isTelephone("jzlxdh")){
                showAlert("请填写正确的家长联系电话！");
                return false;
            }
        }

        function checkJzxx(){
            var flag = true;
            if(jQuery("#jzxx_tb").is(":visible")){
                var checkId = 'jzxm-jzlxdh-gx-jtdz-lfmd-jzdzyx';
                if(!checkNotNull(checkId)){
                    flag = false;
                }
                var jzxb = jQuery("input[type=radio][name='jzxb']:checked");
                var xssfzx = jQuery("input[type=radio][name='xssfzx']:checked");
                var fdysfzx = jQuery("input[type=radio][name='fdysfzx']:checked");
                if(jzxb.length<=0 || xssfzx.length<=0 ||fdysfzx.length<=0 )
                    flag = false;


            }
            return flag;
        }
		function selectXh(){
			var gotoPath = jQuery("#path").val();
//			if(jQuery("#zgh").val()!=""){
//				gotoPath +="$zgh="+jQuery("#zgh").val();
//			}
			showDialog("请选择一个学生",800,600,"xlzx_tsxs.do?method=getTsxsInfo&gotoPath="+gotoPath);
		}
		
		function selectZxs(){
			var gotoPath = jQuery("#path").val();
			
			if(jQuery("#xh").val()!=""){
				gotoPath +="$xh="+jQuery("#xh").val();
			}
			showDialog("请选择一个咨询师",800,600,"xlzx_zxs.do?method=getZxsInfo&gotoPath="+gotoPath);
		}
		
		
		function delValidate(){
			var flag = false;
			var xh = jQuery("#xh").val();
			var zgh = jQuery("#zgh").val();
			var date = jQuery("#zxrq").val();
			var id = jQuery("#yyid").val();
			if(zgh!="" && date!=""){
				jQuery.ajaxSetup({async:false});
				jQuery.post("xlzx_zxspb.do?method=getSfkyFlag&xh="+xh,{pbdate:date,zgh:zgh,id:id},function(data){
					var sjddmList = null;
					if(data["message"]==""){
						flag = true;
						sjddmList = data["sjddmList"];
						if(sjddmList != null && sjddmList.length > 0){
							var optionHtml = "<option></option>";
							jQuery(sjddmList).each(function(i,n){
								optionHtml += "<option value='"+n.sjddm+"'>"+n.sjdmc+"</option>";
							});
							jQuery("#sjddm").empty();
							jQuery("#sjddm").append(optionHtml);
							jQuery("#xqdm").text(data['xqmc'])
						}
					}else{
						showAlert(data["message"],{},{"clkFun":function(){
							jQuery("#zxrq").val("");
							if(jQuery("#pbfs").val() == '2'){
								jQuery("#sjddm").empty();
								jQuery("#xqdm").text("");
							}
							flag = false;
						}});
					}
				},'json');
				jQuery.ajaxSetup({async:true});
			}
		}
		
					
		</script>
	</head>
	<body>
		<input type="hidden" name="path" id="path" value="${path}" />
		<input type="hidden" name="zgh" id="zgh" value="${zxsInfo.zgh}" />
		<input type="hidden" name="sjhm" id="sjhm" value="${xsInfo.sjhm}" />
		<input type="hidden" name="yyid" id="yyid" value="${yyzxInfo.zxyyid}" />
		<input type="hidden" name="xh" id="xh" value="${yyzxInfo.xh}"/>
		<input type="hidden" name="pbfs" id="pbfs" value="${pbfs}"/>
		<html:form action="/xlzx_zxyyclnew" method="post">
			<div style='width:100%; height:480px; overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="2">
							<span>预约方式信息</span>
						</th>
					</tr>
					</thead>
					<th width="16%">
						<span class="red">*</span>预约方式
					</th>
					<td >
							${yyzxInfo.yyfsmc}
					</td>
				</table>
				<logic:present name="jzxx">
					<logic:notEmpty name="jzxx">
						<%
							HashMap<String,String> jzxxMap = (HashMap<String,String>)request.getAttribute("jzxx");
						%>
						<table width="100%" border="0" class="formlist" id="jzxx_tb">
							<thead>
							<tr>
								<th colspan="4">
									<span>家长填写信息</span>
									<input type="hidden" value="${jzxx.id}" id="jzxxid"/>
									<input type="hidden" value="${jzxx.sqid}" id="jzxxsqid"/>
								</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<th width="16%">
									<span class="red">*</span><span>家长姓名</span>
								</th>
								<td width="34">
									<input type="text" name="jzxm" id="jzxm" onblur="checkLen(this,10)" value="${jzxx.jzxm}"/>
								</td>
								<th width="16%">
									<span class="red">*</span><span>性别</span>
								</th>
								<td width="34">
									<label><input type="radio" name="jzxb" value="1" style="width: 15px;"
											<% if("1".equals(jzxxMap.get("jzxb"))){%> checked="checked" <% } %>/>男</label>
									<label><input type="radio" name="jzxb" value="2" style="width: 15px;"
											<% if("2".equals(jzxxMap.get("jzxb"))){%> checked="checked" <% } %>/>女</label>
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span class="red">*</span><span>联系电话</span>
								</th>
								<td width="34">
									<input type="text" name="jzlxdh" id="jzlxdh" maxlength="12"  onblur="checkLxdh();"  value="${jzxx.jzlxdh}" />
								</td>
								<th width="16%">
									<span class="red">*</span><span>与学生关系</span>
								</th>
								<td width="34">
									<input type="text" name="gx" id="gx" onblur="checkLen(this,10)"  value="${jzxx.gx}"/>
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span class="red">*</span><span>电子邮箱</span>
								</th>
								<td width="34">
									<input type="text" name="jzdzyx" id="jzdzyx"
										   maxlength="30"  onblur="checkEmail(this)"  value="${jzxx.jzdzyx}"/>
								</td>
								<th width="16%">
									<span>家庭健全</span>
								</th>
								<td width="34">
									<input type="text" name="jtjq" id="jtjq" onblur="checkLen(this,10)" value="${jzxx.jtjq}"/>
								</td>

							</tr>
							<tr>
								<th width="16%">
									<span>父亲职业</span>
								</th>
								<td width="34">
									<input type="text" name="fqzy" id="fqzy" onblur="checkLen(this,10)"  value="${jzxx.fqzy}"/>
								</td>
								<th width="16%">
									<span>父学历</span>
								</th>
								<td width="34">
									<input type="text" name="fxl" id="fxl" onblur="checkLen(this,10)"  value="${jzxx.fxl}"/>
								</td>

							</tr>
							<tr>
								<th width="16%">
									<span>母亲职业</span>
								</th>
								<td width="34">
									<input type="text" name="mqzy" id="mqzy" onblur="checkLen(this,10)" value="${jzxx.mqzy}"/>
								</td>
								<th width="16%">
									<span>母学历</span>
								</th>
								<td width="34">
									<input type="text" name="mxl" id="mxl" onblur="checkLen(this,10)"  value="${jzxx.mxl}"/>
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span class="red">*</span><span>家庭住址</span>
								</th>
								<td  colspan="3">
									<input type="text" name="jtdz" id="jtdz" style="width: 95%" onblur="checkLen(this,50)" value="${jzxx.jtdz}"/>
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span class="red">*</span><span>学生是否知晓</span>
								</th>
								<td width="34">
									<label><input type="radio" name="xssfzx" value="1" style="width: 15px;"
											<% if("1".equals(jzxxMap.get("xssfzx"))){%> checked="checked" <% } %>/>是</label>
									<label><input type="radio" name="xssfzx" value="0" style="width: 15px;"
											<% if("0".equals(jzxxMap.get("xssfzx"))){%> checked="checked" <% } %>/>否</label>
								</td>
								<th width="16%">
									<span class="red">*</span><span>辅导员是否知晓</span>
								</th>
								<td width="34">
									<label><input type="radio" name="fdysfzx" value="1" style="width: 15px;"
											<% if("1".equals(jzxxMap.get("fdysfzx"))){%> checked="checked" <% } %>/>是</label>
									<label><input type="radio" name="fdysfzx" value="0" style="width: 15px;"
											<% if("0".equals(jzxxMap.get("fdysfzx"))){%> checked="checked" <% } %>/>否</label>
								</td>
							</tr>
							<tr>
								<th width="16%">
									<span class="red">*</span><span>来访目的</span>
									<br/><font color="red">(限500字)</font>
								</th>
								<td  colspan="3">
							<textarea rows="4" cols="" style="width: 98%"
									  id="lfmd" name="lfmd" onblur="checkLen(this,500)">${jzxx.lfmd}</textarea>
								</td>
							</tr>
							</tbody>

						</table>
					</logic:notEmpty>
				</logic:present>

				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr >
							<th colspan="4">
								<span>咨询师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfo">
						<tr style="height:10px">
							<th width="16%">
								职工号
							</th>
							<td width="34%">
								${zxsInfo.zgh}
							</td>
							
							<th  width="16%">
								姓名
							</th>
							<td  width="34%">
								${zxsInfo.xm}
							</td>
						</tr>
						<tr style="height:10px">
							<th  width="16%">
								性别
							</th>
							<td  width="34%">
								${zxsInfo.xb }
							</td>
							<th width="16%">
								年龄
							</th>
							<td  width="34%">
								${zxsInfo.age}
							</td>
						</tr>
						<tr style="height:10px">
							<th width="16%">
								联系电话
							</th>
							<td  width="34%">
								${zxsInfo.lxdh }
								
							</td>
							<th width="16%">
								所在部门
							</th>
							<td  width="34%">
								${zxsInfo.bmmc }
								
							</td>
						</tr>						
					</tbody>			
							
					
					<thead>
						<tr>
							<th colspan="4">
								<span>咨询安排信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="dealInfo">
					<tr style="height:10px" >
							<th>
								<span class="red">*</span>咨询安排日期
							</th>
							<td >
									<html:text name="yyzxInfo" property="zxrq" styleId="zxrq" style="width:100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${today}'})"  onchange="delValidate();"/>
							</td>
							<th  width="16%">
								<logic:equal name="pbfs" value="2"><span class="red">*</span></logic:equal>咨询时段
							</th>
							<td  width="34%" >
								<logic:equal name="pbfs" value="2">
									<html:select property="sjddm" styleId="sjddm" style="width:60%">
										<html:options collection="sjddmList" property="sjddm" labelProperty="sjdmc"/>
									</html:select>
								</logic:equal>
								<logic:notEqual name="pbfs" value="2">
									<html:text name="yyzxInfo" property="zxqssj" styleId="zxqssj" style="width:30%" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'zxjssj\\')}'})" readonly="true"/>&nbsp;至&nbsp;
									<html:text name="yyzxInfo" property="zxjssj" styleId="zxjssj" style="width:30%" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'zxqssj\\')}'})" readonly="true" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								咨询联系电话
							</th>
							<td colspan="3">
								<html:text name="yyzxInfo" property="zxtell" styleId="zxtell"   maxlength="11" onblur="checkInputData(this);"/>
							</td>

						</tr>
						<tr>
							<th>
								咨询地址
							</th>
							<td colspan="3">
								<html:text name="yyzxInfo" property="zxdz" styleId="zxdz" style="width:90%"  maxlength="50"  />
							</td>
						</tr>
						<logic:equal name="xxdm" value="10026">
							<tr>
								<th>校区</th>
								<td colspan="3" id="xqdm">${xqmc}</td>
							</tr>
						</logic:equal>
						<tr style="height:10px" name="yyfkId">
							<th  width="16%">
								备注<br/>
								<font color="red"><B>(限500字)</B></font>
							</th>
							<td  width="34%" colspan="3">
								<html:textarea value="${yyzxInfo.zxbz}" property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLengs(this,500);" rows='4' />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveYysqInfo();return false;">
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

