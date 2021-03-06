<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript">
		function getValue(){
			var value="";
			var syrs=document.getElementById("xyrs").value;
			var knsbl=document.getElementById("knsbl").value;
			value=Math.round(syrs*(knsbl*0.01));
			document.getElementById("xyknsrs").value=value;
		}	
		
		function save(){
			if (Math.round(parseFloat(val('xyrs'))*parseFloat(val('knsbl'))/100)>parseFloat(val('syknss'))) {
				alert("使用困难生数低于标准！");
				return false;
			}
			if(parseInt(val('xyrs'))<parseInt(val('syknss'))){
				alert("使用困难生数多于需要人数！");
				return false;
			}
			if(checkSjTj('gzksrq','工作开始时间','gzjsrq','工作结束时间')){
				if(filedNotNull(['gwdm','sqdw','gzjsrq','xyrs','syknss','jcfs','jybcbz','gzsj','gznr'],'')){
					refreshForm('gwfb.do?method=gwfb&act=save');
				}else{
					alert('请将带*号的项目输入完整！');
					return false;
				}
			}
		}
	</script>
</head>
<body>
	<html:form action="/gwfb.do?method=gwfb" method="post">
		<input type="hidden" id="path" name="path" value="${path}"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		<input type="hidden" id="gwsbsj" name="gwsbsj" value="${gwsbsj}" />
		<input type="hidden" id="knsbl" name="knsbl" value="${conf.knsbl}" />
		<input type="hidden" id="xueqi" name="save_xueqi" value="${rs.save_xueqi}" />
		<input type="hidden" id="doType" name="doType" value="${doType}" />
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
		<input type="hidden" id="save_gwflag" name="save_gwflag" value="xngw" />
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title}</a>
			</p>
		</div>
		<!--页签-->
		<%@ include file="/qgzx/mjxy/gwfbtitle.jsp"%>
		<div class="tab">
		  <table width="100%" class="formlist" id="rsT">
				<thead>
					<tr>
						<th colspan="4">
							<span>岗位信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>校区</th>
					<td>
						<html:select name="rs" property="save_xq" style="width:120px" styleId="xqdm">
							<html:options collection="xiaoquList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<th><span class="red">*</span>岗位名称</th>
					<td height="22" align="left">
						<!--增加-->
						<logic:notEqual value="modi" name="doType">
							<html:text name="rs" property="save_gwdm" styleId="gwdm" onkeyup="value=value.replace('-','');replaceStr(this)" maxlength="20" />
						</logic:notEqual>

						<!--修改-->
						<logic:equal value="modi" name="doType">
							<html:text name="rs" property="save_gwdm" styleId="gwdm" readonly="true"  maxlength="20"/>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<th>岗位性质</th>
					<td>
						<html:select name="rs" property="save_gwxz" style="width:120px" styleId="gwxz">
							<html:options collection="gwxzList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<th><span class="red">*</span>申请单位</th>
					<td>
						<html:select name="rs" property="save_sqdw" styleId="sqdw" style="width:120px" onchange="getYrdwInfo()">
							<html:options collection="yrdwList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>学年</th>
					<td>
						<html:text name="rs" property="save_xn" style="width: 90px" readonly="true" />
					</td>
					<th>年度</th>
					<td>
						<html:text name="rs" property="save_nd" style="width: 90px" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>工作开始日期</th>
					<td>
						<html:text name='rs' property="save_gzksrq" styleId="gzksrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzksrq','y-mm-dd');" />
					</td>
					<th><span class="red">*</span>工作结束日期</th>
					<td>
						<html:text name='rs' property="save_gzjsrq" styleId="gzjsrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzjsrq','y-mm-dd');" />
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>需求人数</th>
					<td height="22" align="left">
						<html:text name="rs" property="save_xyrs" styleId="xyrs" maxlength="3" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
					</td>
					<th><span class="red">*</span>使用困难生数</th>
					<td>
						<html:text name="rs" property="save_syknss" styleId="syknss" maxlength="3"
						onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>计酬方式</th>
					<td>
						<html:select name="rs" property="save_jcfs" onchange="subloadPost();loadJcbz(this.value)" styleId="jcfs">
							<html:options collection="jcfsList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th><span class="red">*</span>建议报酬标准</th>
					<td>
						<html:text name="rs" property="save_jybcbz" styleId="jybcbz" maxlength="20" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
						<span id="jybcbzDw"></span>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>工作时间</th>
					<td>
						<html:text name="rs" property="save_gzsj" styleId="gzsj" maxlength="20"/>
						(例：周一上，周二下...)
						<span id="gzsjDw"></span>
					</td>							
					<th>联系电话</th>
					<td>
						<html:text name="rs" property="save_lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "
							maxlength="15" />
					</td>						
				</tr>
				<tr>
					<th><span class="red">*</span>工作内容</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_gznr" styleId="gznr"
							cols="80" rows="5" onblur="chLeng(this,'150')">
						</html:textarea>
					</td>
				</tr>		
				<tr>
					<th>岗位要求</th>
					<td colspan="3">
						<html:textarea name="rs" 
						               property="save_gwtsyq" 
						               cols="80"
						               rows="5" 
						               styleId="gwtsyq" 
						               onblur="chLeng(this,'100')">
						</html:textarea>
					</td>
				</tr>
				<tr>
					<th>人员要求</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_ryyq" cols="80" rows="5" styleId="ryyq" onblur="chLeng(this,'100')"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>申请报告</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_sqbg" cols="80" rows="5" styleId="sqbg" onblur="chLeng(this,'125')"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>申请单位意见</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_sqdwyj" cols="80" onblur="chLeng(this,'100')"
							rows="5"></html:textarea>
					</td>
				</tr>				
				<tr>
					<th>备注</th>
					<td colspan="3">
						<html:textarea name="rs" property="save_bz" cols="80" rows="5" styleId="bz" onblur="chLeng(this,'60')"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz noPrin">"<span class="red">*</span>"为必填项&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="red">提示:岗位使用困难生比例不得低于${conf.knsbl}%</span></div>
			          <div class="btn">
						<logic:equal value="yes" name="writeAble">
			           		<button type="button"  id="buttonSave" onclick="save();return false;">
								保 存
							</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
	<logic:present name="result">
		<intput type="hidden" id="message" value="${message}"/>
		<script>
			alert(document.getElementById('message').value);
			if(window.dialogArguments){
		 		window.dialogArguments.document.getElementById('search_go').onclick();
		 	}
			Close();
		</script>
	</logic:present>
</body>
</html>
