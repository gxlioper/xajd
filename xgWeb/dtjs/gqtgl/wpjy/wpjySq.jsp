<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/dtjs/gqtgl/wpjySq.js"></script>
	<script language="javascript">	
     function sqSave(){
     	var doType=$('doType').value;
     	var option=$('option').value;
     	var url="";
     	if("sq"==doType){
     		url="/xgxt/ntzyWpjy.do?method=wpjySq&doType=save";
     	}else if("modi"==doType){
     		url="/xgxt/ntzyWpjy.do?method=wpjyUp&doType=modi";
     	}else if("shone"==option){
     		url="/xgxt/ntzyWpjy.do?method=shOneWpjy&doType=save";
     	}
     	
        save(url);
     }
   
   function sqPrint(){
   		window.open('ntzyWpjy.do?method=wpjySqb&pkValue=${pkValue}');
   }
   jQuery(function(){
		onShow();
	})

</script>
</head>
	<body >
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <html:form action="/ntzyWpjy" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/sjxyDektqh.do?method=sqDektqh" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<logic:equal name="doType" value="sq">
				<input type="hidden" id="save_sqsj" name="save_sqsj" value="${sqsj}"/>
			</logic:equal>
			<logic:notEqual name="doType" value="sq">
				<input type="hidden" id="save_sqsj" name="save_sqsj" value="${rs.sqsj}"/>
			</logic:notEqual>
			<input type="hidden" id="option" name="shone" value="${option}"/>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<logic:equal name="doType" value="sq">
				<input type="hidden" id="save_bmdm" name="save_bmdm" value="${userDep}" />
			</logic:equal>
			<logic:empty name="rs">
			<input type="hidden" id="save_xn" name="save_xn" value="${save_xn}"/>
			</logic:empty>
			<logic:empty name="rs">
			<input type="hidden" id="save_xq" name="save_xq" value="${save_xq}"/>
			</logic:empty>
			<logic:notEmpty name="rs">
			<input type="hidden" id="save_xn" name="save_xn" value="${rs.xn}"/>
			</logic:notEmpty>
			<logic:notEmpty name="rs">
			<input type="hidden" id="save_xq" name="save_xq" value="${rs.xq}"/>
			</logic:notEmpty>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>�������д</span></th>
			        </tr>
			    </thead>
			
				<tbody>
				<tr>
					<th >
						������
					</th>
					<td align="left">
						<logic:empty name="rs">
						${userName} 
						<html:hidden property="save_sqr"  value="${userName}"/>
						</logic:empty>
						<logic:notEmpty name="rs">
							<html:text name="rs" readonly="true" property="save_sqr"/>
						</logic:notEmpty>
					</td>
					<th >
						����ʱ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="sq">
							${sqsj}
						</logic:equal>
						<logic:notEqual name="doType" value="sq">
							${rs.sqsj}
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height:22px">
					
					<th>
						<font color="red">*</font>���벿�ţ�
					</th>
					<td align="left">
						<logic:empty name="rs">
						<html:text  property="save_sqbm" />
						</logic:empty>
						<logic:notEmpty name="rs">
						<html:text name="rs" readonly="true" property="save_sqbm" />
						</logic:notEmpty>
					</td>
					<th>
						<font color="red">*</font>������
					</th>
					<td align="left">
						<logic:empty name="rs">
						<html:text property="save_jbr" value="${xm}" />
						</logic:empty>
						<logic:notEmpty name="rs">
						<html:text name="rs" readonly="true" property="save_jbr" />
						</logic:notEmpty>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						��ϵ�绰
					</th>
					
					<td align="left">
						<logic:empty name="rs">
						<html:text property="save_lxdh" onkeypress="return onlyNum(this,20)"/>
						</logic:empty>
						<logic:notEmpty name="rs">
						<html:text name="rs" property="save_lxdh"  onkeypress="return onlyNum(this,20)"/>
						</logic:notEmpty>	
					</td>
					<th align="right">
						<font color="red">*</font>��������
					</th>
					<td align="left">
						<logic:empty name="rs">
						 <html:text property="save_jyrq" styleId="jyrq" 
							onclick="return showCalendar('jyrq','y-mm-dd');" 
							onblur="dateFormatChg(this)" readonly="true" />
						</logic:empty>
						<logic:notEmpty name="rs">
						 <html:text name="rs" readonly="true" property="save_jyrq" styleId="jyrq" />
						</logic:notEmpty>	
						
					</td>
				</tr>
				<tr>
					<logic:equal name="option" value="shone">
					<th align="right">
						��������
					</th>
					<td align="left">
						<logic:empty name="rs">
						 <html:text property="save_sqsy"/>
						</logic:empty>
						<logic:notEmpty name="rs">
						 <html:text name="rs" property="save_sqsy"/>
						</logic:notEmpty>	
					</td>
					<th align="right">
						���
					</th>
					<td align="left">
						<logic:equal name="userType" value="xx">
							<html:select property="save_xxsh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
							</logic:equal>
							<html:hidden property="save_xysh" value="ͨ��"/>
						<logic:equal name="userType" value="admin">
							<html:select property="save_xxsh">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								<html:hidden property="save_xysh" value="ͨ��"/>
							</logic:equal>
						<logic:notEqual name="userType" value="xx">
							<logic:notEqual name="userType" value="admin">
							<html:select property="save_xysh">
								<html:option value=""></html:option>
								<html:option value="δ���">δ���</html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
							</html:select>
							</logic:notEqual>
						</logic:notEqual>
					</td>
					</logic:equal>
				
					<logic:notEqual name="option" value="shone">
					<th align="right">
						��������
					</th>
					<td align="left" colspan="3">
						<logic:empty name="rs">
						 <html:text property="save_sqsy"/>
						</logic:empty>
						<logic:notEmpty name="rs">
						 <html:text name="rs" property="save_sqsy"/>
						</logic:notEmpty>	
					</td>
					</logic:notEqual>	
				</tr>
			</table>
			</div>
			
			    <h3 class="datetitle_01">
			    <span>
			    	������Ʒ���
			    </span>
			    </h3>
			
				<p>
					<logic:equal value="shone" name="option">
						<button type="button"  value="+" >+</button>
						<button type="button" value="-" >-</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px">
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					
					<logic:notEqual value="shone" name="option">
						<button type="button" value="+"
							onclick="trAdd('flag1','add','numAdd1','rzqk');"
							>+</button>
						<button type="button" value="-" onclick="trDel('flag1','delRow1');"
							>-</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px"
							onblur="trAdd('flag1','madd','numAdd1','rzqk')">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px"
							onblur="trDelAll('flag1','numDel1')">
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
				</p>
				<div class="tab">
			 		 <table width="100%"  border="0" class="formlist">
				  			 <tfoot>
						      <tr>
						        <td><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						          
						          <div class="btn">
						            <logic:equal name="writeAble" value="yes">
									<logic:notEqual name="option" value="shone">
										<logic:notEqual name="doType" value="view">
										<button type="button"  id="buttonSave" onclick="sqSave();" >
											��  �� 
										</button>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal name="option" value="shone">
										<logic:equal name="write" value="yes">
										<button type="button"  id="buttonSave" onclick="sqSave();">
										��  �� 
										</button>
										</logic:equal>
									</logic:equal>
									</logic:equal>
									<button type="button" onclick="sqPrint()">
										��  ӡ
									</button>
						          </div>
						          </td>
						      </tr>
						    </tfoot>
						    <tbody>
						    <tr><td>
						    <div class="formbox">
				  				<table summary="" class="datelist" align="" width="100%">
									<!-- ��ӡʱ��һ�в���ʾ- -->
								
									<thead>
										<tr>
											<td align="center" nowrap="nowrap" style='width:6%'>
												ѡ��ɾ����
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												�豸����
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												��λ
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												����
											</td>
											<td align="center" nowrap="nowrap" style='width:20%'>
												�黹��ע
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
						</div>
						</td></tr>
						</tbody>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			    </script>
			</logic:equal>
			<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
		</html:form>
	</body>
</html>

