<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
			<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	
	<body onload="clin();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a> ��Ԣ���� - ���� - ��Ԣά������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/XsgyglDispatch.do?method=gywxSq" method="post">
			<input type="hidden" id="url" name="url"
				value="/XsgyglDispatch.do?method=gywxSq" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="disableEle" name="disableEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
			<input type="hidden" id="fzbmV" name="fzbmV" />
			<logic:equal value="isNotStu" name="isNotStu">
				<div align="center">
					<font color="red">ֻ��ѧ���û���������!</font>
				</div>
			</logic:equal>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="ssbh" name="ssbh" value="${rs.ssbh}" />
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��Ԣά������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th align="right">
							<font color="red">*</font>¥�����ƣ�
						</th>
						<td align="left">
							<html:text name="rs" property="ldmc" readonly="true"></html:text>
						</td>
						<th align="right">
							<font color="red">*</font>ѧ�꣺
						</th>
						<td align="left">
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>���Һţ�
						</th>
						<td align="left">
							<html:text name="rs" property="qsh" readonly="true"></html:text>
							<input type="hidden" id="ssbh" name="ssbh" value="${rs.ssbh}" />
						</td>


						<th align="right">
							<font color="red">*</font>ѧ�ڣ�
						</th>
						<td align="left">
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>����ʱ�䣺
						</th>
						<td align="left">
							<html:text name="rs" property="bxsj" readonly="true"
								onblur="dateFormatChg(this)"
								onclick="return showCalendar('bxsj','y-mm-dd');"
								style="cursor:hand " />
						</td>
						<th align="right">
							�����ˣ�
						</th>
						<td align="left">
							<html:text name="rs" property="bxr" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						
						<th align="right">
							������
						</th>
						<td align="left">
							<html:text name="rs" property="xm" readonly="true"></html:text>
						</td>
						<th align="right">
						    ��ϵ��ʽ��
						</th>
						<td align="left">
						    <html:text name="rs" property="lxfs" maxlength="15"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>ά�����ݣ�<br>
							<��100����>
						</th>
						<logic:present name="isCSMZZYJSXY">
							<td align="left">
								<html:select name="rs" property="wxnr" style="width:150px"
									styleId="wxnr" onchange="getGyWxNrFzBmList()">
									<html:option value=""></html:option>
									<html:options collection="wxnrList" property="nrdm"
										labelProperty="nrmc" />
								</html:select>
							</td>

							<th align="right">
								�����ţ�
							</th>
							<td align="left">
								<html:select name="rs" property="fzbm" style="width:150px"
									styleId="fzbm">
									<html:options collection="fzbmList" property="fzbmdm"
										labelProperty="fzbmmc" />
								</html:select>
							</td>
						</logic:present>
						<logic:notPresent name="isCSMZZYJSXY">
							<td align="left" colspan="3">
								<textarea rows="5" cols="80" name="wxnr"></textarea>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<th align="right">
							��ע��<br>
							<��100����>
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="80" name="bz" ></textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
										<button id="buttonSave" 
											onclick="to_save()"
											style="width: 80px">
											�� ��
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>

		</html:form>
		<logic:equal value="yes" name="done">
			<script type="text/javascript">
	     alert("����ɹ���");
	    </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script type="text/javascript">
	     alert("����ʧ�ܣ�");
	    </script>
		</logic:equal>
	</body>
	<script type="text/javascript">
     function clin(){
        if($("ssbh").value==""){
          alert("��ǰ�û���û����ס��");
          return false;
        }
     }
     function to_save(){
        if(IsNoEmpty('xn-xq-ldmc-qsh-bxsj-wxnr')){
          if($("wxnr")){
             if($("wxnr").value.length>100){
                alert("ά�������������ܴ���100��")
                return false;
             }
          }
          if($("bz")){
             if($("bz").value.length>100){
                alert("��ע�������ܴ���100��")
                return false;
             }
          }
          refreshForm('/xgxt/XsgyglDispatch.do?method=gywxSq&doType=save');
          $("buttonSave").disabled=true;
        }
     }
  </script>
</html>
