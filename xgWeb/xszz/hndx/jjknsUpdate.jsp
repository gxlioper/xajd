<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script language="javascript" src="js/xszz/xszzFunction.js"></script>
	<script type="text/javascript">
	function onShow(){
		var mklx = $("mklx").value;
		setReadonly();
		if(mklx == "sq"){
			$("yjField").style.display = "none";
		}
	}
	
	//��������
	function saveSqInfo(){
	
		var mklx = $("mklx").value;
		var bjsh = $("bjsh").value;
		var xysh = $("xysh").value;
		var xxsh = $("xxsh").value;
		
		
		if(mklx == "jg"){
			
			if(bjsh != "δ���" || xysh != "δ���" || xxsh != "δ���"){
				alert("������һ�����Ѿ���˸ô����룬�޷���������Ϣ�����޸ģ���ȷ��!");
				return false;
			}
		}
		
		saveUpdate('/xgxt/hndxXszz.do?method=jjknssq&doType=save','xh-xn');
	}
	
	//���
	function shSqInfo(shzt){

		var mklx = $("mklx").value;
		var xydm = $("xydm").value;
		var xn = $("xn").value;
		var xmmc = $("xmmc").value;
		var xmjb = $("knsjb").value;

		if(shzt == "tg"){//ͨ��
		
			dwr.engine.setAsync(false);
			
			var tableName="hndx_xszz_xyrsb";
			var colList =["xmrs"];
			var pk = "xn||szxy||xmmc||xmjb";
			var pkValue = xn+xydm+xmmc+xmjb;
			var xmrs = "0";
			
			getXszzInfo.getXszzInfo(tableName, pk, pkValue,colList,function(data){
				if(data!=null){
					if(data.xmrs !="" && data.xmrs != null){
						xmrs = data.xmrs;
					}
				}
			});
			
			var isBjsh = $("isBjsh").value;
			var userType = $("userType").value;
			var shzd = "";
			var xh = $("xh").value;
			var tgrs = "0";
			
			//�༶
			if(isBjsh == "true"){
				shzd = "bjsh";
			}
			//ѧУ
			else if(userType == "xx" || userType == "admin"){
				shzd = "xxsh";
			}
			//<bean:message key="lable.xsgzyxpzxy" />
			else if(userType == "xy"){
				shzd = "xysh";
			}

			getXszzInfo.getJjknsRs_Hndx(xn, xydm, xmjb,shzd, xh,function(data){
				if(data!=null){
					tgrs = data;
				}
			});
			
			dwr.engine.setAsync(true);	
			
			if(parseInt(tgrs) + 1 > parseInt(xmrs)){
				alert("�������������ͨ������Ϊ"+xmrs+"�ˣ�\n�����Ѿ��������ޣ��޷������ͨ������ȷ�ϣ�");
				return false;
			}
		}
		
		saveUpdate('/xgxt/hndxXszz.do?method=jjknsView&doType=sh&shzt='+shzt,'knsjb')
	}
	</script>
	<body onload="onShow()">
		<html:form action="/hndxXszz" enctype="multipart/form-data">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" id="lx" name="lx" value="ѧ��" />
			<input type="hidden" id="url" name="url"
				value="hndxXszz.do?method=jjknssq" />
			<input type="hidden" id="forward" name="forward"
				value="/hndxXszz.do?method=jjknssq" />
			<input type="hidden" id="isBjsh" name="isBjsh" value="${isBjsh }" />
			<html:hidden property="xmmc" value="�����������϶�" />
			<html:hidden name="rs" property="xn" value="${rs.xn }" />
			<!-- ������ end-->
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�${title }
				</div>
			</div>
			<div align="center">
				<font size="4"><strong>${rs.xn }ѧ�꾭���������϶�</strong>
				</font>
			</div>
			<fieldset>
				<legend>
					ѧ��������Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="35%">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<!-- ���� -->
							<logic:equal name="mklx" value="sq">
								<!-- ��ѧ�� -->
								<logic:notEqual name="userType" value="stu">
									<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>
							</logic:equal>
							<!-- ���� end-->
							<!-- ��� -->
							<logic:equal name="mklx" value="jg">
								<logic:equal name="doType" value="add">
									<button onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
							</logic:equal>
							<!-- ��� end-->
						</td>
						<td align="right" width="15%">
							������
						</td>
						<td align="left">
							${rs.xm }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							${rs.xb }
						</td>
						<td align="right">
							���壺
						</td>
						<td align="left">
							${rs.mzmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							�������£�
						</td>
						<td align="left">
							${rs.csrq }
						</td>
						<td align="right">
							���֤�ţ�
						</td>
						<td align="left">
							${rs.sfzh }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							������ò��
						</td>
						<td align="left">
							${rs.zzmmmc }
						</td>
						<td align="right">
							�������룺
						</td>
						<td align="left">
							${rs.yzbm }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							��ѧǰ���ڣ�
						</td>
						<td align="left">
							${rs.hkxz }
						</td>
						<td align="right">
							�ֻ����룺
						</td>
						<td align="left">
							${rs.sjhm }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							�������ڵأ�
						</td>
						<td align="left" colspan="3">
							<html:select name="rs" property="hkshen" styleId="hkshen"
								onchange="loadShi('hkshen','hkshi','hkxian');" disabled="true">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select name="rs" property="hkshi" styleId="hkshi"
								onchange="loadXian('hkshi','hkxian')" disabled="true">
								<html:options collection="hkshiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							<html:select name="rs" property="hkxian" styleId="hkxian"
								disabled="true">
								<html:options collection="hkxianList" property="xiandm"
									labelProperty="xianmc" />
							</html:select>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					��У��Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							�꼶��
						</td>
						<td align="left" width="35%">
							${rs.nj }
						</td>
						<td align="right" width="15%">
							У����
						</td>
						<td align="left">
							${rs.xqmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" width="">
							<html:hidden name="rs" property="xydm" />
							${rs.xymc }
						</td>
						<td align="right" width="">
							¥����
						</td>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							רҵ��
						</td>
						<td align="left" width="">
							${rs.zymc }
						</td>
						<td align="right" width="">
							������
						</td>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							�༶��
						</td>
						<td align="left" width="">
							${rs.bjmc }
						</td>
						<td align="right" width="">
							���Һţ�
						</td>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							��ѧʱ�䣺
						</td>
						<td align="left" width="">
							${rs.rxrq }
						</td>
						<td align="right" width="">
							����绰��
						</td>
						<td align="left">
							${rs.qsdh }
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					��ͥ��Ϣ
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							��ͥ������
						</td>
						<td align="left" width="35%">
							<html:text name="rs" property="jtrs"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
						<td align="right" width="15%">
							��ͥ�������룺
						</td>
						<td align="left">
							<html:text name="rs" property="jtnzsr"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							��ͥ������Ҫ��Դ��
						</td>
						<td align="left" width="">
							<html:text name="rs" property="jtsrly" maxlength="20" />
						</td>
						<td align="right" width="">
							��ͥ�������룺
						</td>
						<td align="left">
							<html:text name="rs" property="jtyzsr"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							��ͥ�绰��
						</td>
						<td align="left" width="">
							<html:text name="rs" property="jtdh"
								onkeypress="return onlyNum(this,20)" maxlength="20"
								style="ime-mode:disabled" />
						</td>
						<td align="right" width="">
							��ͥ�˾������룺
						</td>
						<td align="left">
							<html:text name="rs" property="jtrjsr"
								onkeypress="return onlyNum(this,5)" maxlength="5"
								style="ime-mode:disabled" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							��ͥ��ծ�����
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="jtfzqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							��ͥסַ��
						</td>
						<td align="left" width="" colspan="3">
							<html:text name="rs" property="jtdd" maxlength="50"
								style="width: 90%" />
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>
					��������
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							�Ƿ�¶���
						</td>
						<td align="left" width="35%">
							<html:select name="rs" property="sfge">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="15%">
							�Ƿ�м���
						</td>
						<td align="left">
							<html:select name="rs" property="sfcj">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							�Ƿ�ͱ�����
						</td>
						<td align="left" width="">
							<html:select name="rs" property="sfdb">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="">
							�Ƿ���ʿ��Ů��
						</td>
						<td align="left">
							<html:select name="rs" property="sflszn">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							�Ƿ�������Ȼ�ֺ���
						</td>
						<td align="left" width="">
							<html:select name="rs" property="sfzrch">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="">
							��ͥ��Ա�Ƿ��ڻ��ز���
						</td>
						<td align="left">
							<html:select name="rs" property="sfjthb">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							�Ƿ���ƶ��֤����
						</td>
						<td align="left" width="">
							<html:select name="rs" property="sfpkzm">
								<html:option value=""></html:option>
								<html:options collection="sfList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td align="right" width="">
							���ж���ѧ������
							<br>
							����ѧ�����ˣ�
						</td>
						<td align="left">
							<html:text name="rs" property="jtdxrs" maxlength="5" />
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							���������ȼ���
						</td>
						<td align="left" width="">
							<html:select name="rs" property="dydj">
								<html:options collection="dydjList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" width="">

						</td>
						<td align="left">

						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							ѧ������&nbsp;&nbsp;&nbsp;
							<br>
							��ͥ����&nbsp;&nbsp;&nbsp;
							<br>
							���������
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="xsjtjjqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							���˻񡰽�������������
							<br>
							���ڡ��������������⡱��
							<br>
							�����������
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="brjdqzmbqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							���������
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:textarea name="rs" property="qtqk" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							ƶ��֤���ϴ���
						</td>
						<td align="left" width="" colspan="3">
							<!-- ���ϴ��ļ� -->
							<logic:empty name="rs" property="scdz">
								<input type="file" name="uploadFile" style="width:60%" id="kk">
								&nbsp;&nbsp;
								<font color="red">(�ļ���СС��&lt;10M&gt;)</font>
							</logic:empty>
							<!-- ���ϴ��ļ� -->
							<logic:notEmpty name="rs" property="scdz">
								<html:hidden name="rs" property="scdz" />
								<a
									href="czxxDtjsDyxx.do?method=downLoadFile&dir=<bean:write name="rs" property="scdz" />"
									target="_blank" />�������</a>
								&nbsp;&nbsp;
								<a
									href="javascript:refreshForm('/xgxt/hndxXszz.do?method=jjknssq&doType=fileDel')" />���ɾ��</a>
							</logic:notEmpty>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset id="yjField">
				<legend>
					������
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font color="red">*</font>����������
						</td>
						<td align="left" width="" colspan="3">
							<!-- ��� -->
							<logic:equal name="mklx" value="sh">
								<html:select name="rs" property="knsjb" style="">
									<html:option value=""></html:option>
									<html:options collection="knsjbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</logic:equal>
							<!-- ��� end-->
							<!-- ����� -->
							<logic:notEqual name="mklx" value="sh">
								<html:select name="rs" property="knsjb" style="" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="knsjbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</logic:notEqual>
							<!-- ����� end-->
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right" width="">
							�༶�����
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:hidden name="rs" property="bjsh" />
							<html:textarea name="rs" property="bjshyj" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" width="">
							<bean:message key="lable.xsgzyxpzxy" />�����
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:hidden name="rs" property="xysh" />
							<html:textarea name="rs" property="xyshyj" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" width="">
							ѧУ�����
							<br>
							<font color="red">(��500��)</font>
						</td>
						<td align="left" width="" colspan="3">
							<html:hidden name="rs" property="xxsh" />
							<html:textarea name="rs" property="xxshyj" rows="5"
								onblur="chLeng(this,500)" style="width: 90%"></html:textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton"> 
					<!-- ���� --> 
					<logic:equal name="mklx" value="sq">
						<logic:notEqual name="doType" value="view">
							<button class="button2" id="buttonSave" onclick="saveSqInfo()"
								style="width: 80px">
								�� ��
							</button> 
							&nbsp;&nbsp;
						</logic:notEqual>
					</logic:equal> 
					<!-- ���� end--> 
					<!-- ��� --> 
					<logic:equal name="mklx" value="jg">
						<logic:notEqual name="doType" value="view">
							<button class="button2" id="buttonSave" onclick="saveSqInfo()"
								style="width: 80px">
								�� ��
							</button> 
							&nbsp;&nbsp;
						</logic:notEqual>
					</logic:equal> 
					<!-- ��� end--> 
					<!-- ��� --> 
					<logic:equal name="mklx" value="sh">
						<logic:notEqual name="doType" value="view">
							<button class="button2" id="buttonSave" onclick="shSqInfo('tg')"
								style="width: 80px">
								ͨ ��
							</button> 
							&nbsp;&nbsp;
							<button class="button2" id="buttonSave" onclick="shSqInfo('btg')"
								style="width: 80px">
								��ͨ��
							</button> 
							&nbsp;&nbsp;
						</logic:notEqual>
					</logic:equal> 
					<!-- ��� end--> 
					<!-- ������ --> 
					<logic:notEqual name="mklx" value="sq">
						<button class="button2" id="buttonClose" onclick="window.close();return false;"
							style="width: 80px" id="buttonClose">
							�� ��
						</button>
					</logic:notEqual> 
					<!-- ������ end--> 
					</span>
			</div>
			<logic:present name="fileUplod">
				<script>
					alert("�ϴ��ļ�������ȷ��");
				</script>
			</logic:present>
			<logic:notPresent name="fileUplod">
				<logic:present name="result">
					<script>
						if($("message") && $("message").value != ""){
							alert($("message").value);
							$("message").value = "";
							$("doType").value = "";
						}
						if(opener){
							opener.document.getElementById("search_go").click();
		      				window.close();
	      				}
					</script>
				</logic:present>
			</logic:notPresent>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
