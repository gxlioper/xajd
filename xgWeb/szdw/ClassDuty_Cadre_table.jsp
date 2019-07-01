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
     				alertInfo("����ʱ�����ڿ�ʼʱ�䣬��ȷ�ϣ�����");
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
					alertInfo("�뽫��\"*\"�ŵ���Ŀ����������");
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
					<em>���ĵ�ǰλ��:</em><a>˼������-�༶���-ѧ���ɲ�</a>
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
			        	<th colspan="4"><span>��дѧ���ɲ���</span></th>
			        </tr>
			    </thead>
			    <tbody>
			    	<tr>
						<th align="right">
								<font color="red">*</font>ѧ��
						</th>
						<td align="left">
							<!-- ����  -->
							<logic:equal name="doType" value="add">
								<input type="text" name='xh' id="xh"  readonly="true"/>
							</logic:equal>
							<!-- �޸� ������¼ -->
							<logic:notEqual name="doType" value="add">
								<input type="text" name='query_xh' id="query_xh"  readonly="true"
										value="${rs.xh }" />
								<html:hidden property="xh" value="${rs.xh }"/>
							</logic:notEqual>
							<!-- ����ʱ��ʾ ѧ�Ų�ѯ��ť -->
							<logic:notEqual name="doType" value="modi">
								<button type="button" onclick="showDialog('��ѡ��һ��ѧ��',680,550,'xsxx_xsgl.do?method=showStudents&goto=classDuty_Cadre_updata.do?act=bjzw&doType=add')"
										class="btn_01" id="buttonFindStu">
										ѡ��
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
							�༶
						</th>
						<td align="left" >
							<bean:write name='rs' property="bjmc" />
						</td>
						<th align="right">
							����
						</th>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" >
							�Ա�
						</th>
						<td align="left" >
							<bean:write name="rs"  property="xb"/>
						</td>
						<th align="right" >
								��ϵ��ʽ
						</th>
							<td align="left" >
								<html:text name="rs"  property="lxfs" style="width: 180px" maxlength="20"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right" >
							<font color="red">*</font>ְ�񼶱�
						</th>
						<td align="left">						
							<html:select  name="rs" property="jbdm" style="width:180px" styleId="jbdm" onchange="selectLb()">
							<html:option value=""></html:option>
							<html:options collection="bjzwTypeList" property="jbdm" labelProperty="jbmc" />
							</html:select>				        
						</td>
						<th align="right" >
							<font color="red">*</font>����ְ��
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
							��ְ��ʼʱ��
						</th>
						<td align="left">
								<html:text name = "rs" property="kssj" styleId="kssj"
								onblur="dateFormatChg(this)" style="cursor:hand;width:180px" readonly = "true"
								onclick="return showCalendar('kssj','y-mm-dd');" />
						</td>
						<th align="right" >
							��ְ����ʱ��
						</th>
						<td align="left" >
								<html:text name = "rs" property="jssj" styleId="jssj"
								onblur="dateFormatChg(this)" style="cursor:hand;width:180px"  readonly = "true"
								onclick="return showCalendar('jssj','y-mm-dd');" />
						</td>
					</tr>
					<tr style="height:22px">
					<th align="right" >
							��ע<br/>
							(��������40)
						</th>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%" onblur="chLengs(this,300);"
										rows='8' onblur="chLengs(this,40)"/>
					</td>
					</tr>
					</tbody>
					 <tfoot>
			    	 <tr>
				        <td colspan="4"><logic:notEqual value="view" name="operation"><div class="bz">"<span class="red">*</span>"Ϊ������</div></logic:notEqual>
				          <div class="btn">
				          	<logic:notEqual value="view" name="operation">
				          		<button type="button" name="����" onclick="if(chSj()){bjgbCommonSave('/xgxt/classDuty_Cadre_save.do?type=bjzw&','bjgb-xh-jbdm','bjzw')}">�� ��</button>
				          	</logic:notEqual>
				          		<button type="button" onclick="Close();return false;">�� ��</button>
				          </div></td>
				      </tr>	
			    </tfoot>
				</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script type="text/javascript"><%--
					alertInfo('����ɹ�', function(){
						dialogArgumentsQueryChick();
						window.close();
					});
				    
				    --%>
				    showAlert('����ɹ�',{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
					alertInfo("�ύʧ�ܣ�");
				    </script>
				</logic:equal>
			</logic:notEmpty>
			
				<logic:equal name="ok" value="ok">
					<script>
					alertInfo("�����ɹ���");
    				</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>
					alertInfo("����ʧ�ܣ�");
				    </script>
				</logic:equal>
			
		</html:form>
	</body>
</html>