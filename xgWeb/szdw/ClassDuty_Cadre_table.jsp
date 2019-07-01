<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>

	<script language="javascript">
	function selectLb(){
		
		var lbid = "jbdm";
		var xxid = "bjgb";
		
		var tableName = "sxjy_bjgbzlb"; 
		var dm = "bjgbdm"; 
		var mc = "bjgbmc";
		var msg = "";
		var pk = "GBZLJB";
		var pkValue = $(lbid).value;
		
		if(pkValue == ""){
			pk = "";
		}
		
		getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(xxid);
				DWRUtil.addOptions(xxid,data,"dm","mc");
				$(xxid).options[0].value = "";
			}
			});
	}
     function disabledBjzw(){
         var len = document.getElementById("pk").value.length;
         if(len>0){
              document.getElementById("bjgb").disabled="false";
         }
         
     }
     function chSj(){
     	if($("kssj") && $("jssj")){
     		if($("kssj").value != "" && $("jssj").value != ""){
     			if($("kssj").value > $("jssj").value){
     				alertInfo("结束时间早于开始时间，请确认！！！");
     				return false;
     			}
     		}
     	}
     	return true;
     }
     function bjgbCommonSave(url,pkFields,pk){
		var eles = pkFields.split("-");
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i])){
				if(document.getElementById(eles[i]).value == "") {
					alertInfo("请将带\"*\"号的项目输入完整！");
					return false;
				}
			}
		}
		document.forms[0].action = url;
		document.forms[0].submit();
	}
</script>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/show_classStudentCadre_list" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>思政队伍-班级情况-学生干部</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" name="pk" property="pk" value="<bean:write name="rs" property="pk"/>" />
				<input type="hidden" name="bjdm" property="bjdm" value="<bean:write name="rs" property="bjdm"/>" />
				<input type="hidden" name="url" id="url"  value="/classDuty_Cadre_updata.do?act=bjzw&doType=add" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<div class="tab">
			 	 <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>填写学生干部表</span></th>
			        </tr>
			    </thead>
			    <tbody>
			    	<tr>
						<th align="right">
								<font color="red">*</font>学号
						</th>
						<td align="left">
							<!-- 增加  -->
							<logic:equal name="doType" value="add">
								<input type="text" name='xh' id="xh"  readonly="true"/>
							</logic:equal>
							<!-- 修改 单条记录 -->
							<logic:notEqual name="doType" value="add">
								<input type="text" name='query_xh' id="query_xh"  readonly="true"
										value="${rs.xh }" />
								<html:hidden property="xh" value="${rs.xh }"/>
							</logic:notEqual>
							<!-- 增加时显示 学号查询按钮 -->
							<logic:notEqual name="doType" value="modi">
								<button type="button" onclick="showDialog('请选择一个学生',680,550,'xsxx_xsgl.do?method=showStudents&goto=classDuty_Cadre_updata.do?act=bjzw&doType=add')"
										class="btn_01" id="buttonFindStu">
										选择
								</button>
							</logic:notEqual>
						</td>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>	

					</tr>
					<tr style="height:22px">
						<th align="right" >
							班级
						</th>
						<td align="left" >
							<bean:write name='rs' property="bjmc" />
						</td>
						<th align="right">
							姓名
						</th>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" >
							性别
						</th>
						<td align="left" >
							<bean:write name="rs"  property="xb"/>
						</td>
						<th align="right" >
								联系方式
						</th>
							<td align="left" >
								<html:text name="rs"  property="lxfs" style="width: 180px" maxlength="20"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" >
							<font color="red">*</font>职务级别
						</th>
						<td align="left">						
							<html:select  name="rs" property="jbdm" style="width:180px" styleId="jbdm" onchange="selectLb()">
							<html:option value=""></html:option>
							<html:options collection="bjzwTypeList" property="jbdm" labelProperty="jbmc" />
							</html:select>				        
						</td>
						<th align="right" >
							<font color="red">*</font>担任职务
						</th>
						<td align="left">						
						<html:select property="bjgb" style="width:180px" styleId="bjgb">
							<html:option value=""></html:option>
							<html:options collection="bjgbList" property="bjgbdm"
								labelProperty="bjgbmc" />
						</html:select>				        
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							任职开始时间
						</th>
						<td align="left">
								<html:text name = "rs" property="kssj" styleId="kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;width:180px" readonly = "true"
								onclick="return showCalendar('kssj','y-mm-dd');" />
						</td>
						<th align="right" >
							任职结束时间
						</th>
						<td align="left" >
								<html:text name = "rs" property="jssj" styleId="jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;width:180px"  readonly = "true"
								onclick="return showCalendar('jssj','y-mm-dd');" />
						</td>
					</tr>
					<tr style="height:22px">
					<th align="right" >
							备注<br/>
							(限制字数40)
						</th>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLengs(this,300);"
										rows='8' onblur="chLengs(this,40)"/>
					</td>
					</tr>
					</tbody>
					 <tfoot>
			    	 <tr>
				        <td colspan="4"><logic:notEqual value="view" name="operation"><div class="bz">"<span class="red">*</span>"为必填项</div></logic:notEqual>
				          <div class="btn">
				          	<logic:notEqual value="view" name="operation">
				          		<button type="button" name="保存" onclick="if(chSj()){bjgbCommonSave('/xgxt/classDuty_Cadre_save.do?type=bjzw&','bjgb-xh-jbdm','bjzw')}">保 存</button>
				          	</logic:notEqual>
				          		<button type="button" onclick="Close();return false;">关 闭</button>
				          </div></td>
				      </tr>	
			    </tfoot>
				</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script type="text/javascript"><%--
					alertInfo('保存成功', function(){
						dialogArgumentsQueryChick();
						window.close();
					});
				    
				    --%>
				    showAlert('保存成功',{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
					alertInfo("提交失败！");
				    </script>
				</logic:equal>
			</logic:notEmpty>
			
				<logic:equal name="ok" value="ok">
					<script>
					alertInfo("操作成功！");
    				</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>
					alertInfo("操作失败！");
				    </script>
				</logic:equal>
			
		</html:form>
	</body>
</html>