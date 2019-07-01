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
	<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/xljk/xlkhxsWh.js"></script>
	<script language="javascript">	
     function sqSave(){
     	var url="/xgxt/xljkMjxyXlkhxs.do?method=xlkhxsWh&doType=save";
        save(url);
     }
     
     function modi(url){
			var url="/xgxt/xljkMjxyXlkhxs.do?method=xlkhxsOne&doType=save";
			 save(url);
		}
   
   function sqPrint(){
   		window.open('xljkMjxyXlkhxs.do?method=khxsdtb&pkValue=${pkValue}');
   }
   jQuery(function(){
		onShow();
	})

</script>
</head>
	<body >
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <html:form action="/xljkMjxyXlkhxs" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/xljkMjxyXlkhxs.do?method=xlkhxsWh" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<input type="hidden" id="option" name="shone" value="${option}"/>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			
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
						<th>
							ѧ��
						</th>
						<td>
							<logic:notEmpty name="rs" scope="request">
								<html:text  property="xh" styleId="xh"
									onblur="dctStuXh()" name="rs"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<html:hidden name='rs' property="save_xh" value="${rs.xh}" />
							</logic:notEmpty>
							<logic:empty name="rs" scope="request">
								<html:text  property="xh" styleId="xh" 
									onblur="dctStuXh()" 
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
							</logic:empty >
							<logic:equal name="doType" value="add">
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
							</logic:equal>
						</td>
						<th>
							����
						</th>
						<td>
							<input type="text" name="xm" value="${rs.xm }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							<input type="text" name="xb" value="${rs.xb }" readonly="true"/>
						</td>
						<th>
							����
						</th>
						<td>
							<input type="text" name="nl" value="${rs.nl }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							�꼶
						</th>
						<td>
							<input type="text" name="nj" value="${rs.nj }" readonly="true"/>
						</td>
						<th>
							ϵ��
						</th>
						<td>
							<input type="text" name="xymc" value="${rs.xymc }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							רҵ
						</th>
						<td>
							<input type="text" name="zymc" value="${rs.zymc }" readonly="true"/>
						</td>
						<th>
							�༶
						</th>
						<td>
							<input type="text" name="bjmc" value="${rs.bjmc }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							��ϵ�绰
						</th>
						<td>
							<input type="text" name="lxdh" value="${rs.lxdh }" readonly="true"/>
						</td>
						<th>
							ͨѶ��ַ
						</th>
						<td>
							<input type="text" name="lxdz" value="${rs.lxdz }" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th>
							ѧ����ѯ����<br>
							<font color="red">(500��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="3" cols="76"  style="word-break:break-all;"
							 property="save_zxwt" value="${rs.zxwt }" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
					<tr>
						<th>
							�ճ�ѧϰ��������
							<br/>��������Ǽ�
							<br/>
							<font color="red">(500��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="3" cols="76"  style="word-break:break-all;"
							 property="save_jbqk" value="${rs.jbqk }" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ����Ǽ�
			    </span>
			    </h3>
			
				<p>
					<logic:equal value="view" name="doType">
						<button  value="+" >+</button>
						<button value="-" >-</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd1" style="width: 20px">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel1" style="width: 20px">
						&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					
					<logic:notEqual value="view" name="doType">
						<button value="+"
							onclick="trAdd('flag1','add','numAdd1','rzqk');"
							>+</button>
						<button value="-" onclick="trDel('flag1','delRow1');"
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
						      
									<logic:equal name="doType" value="add">
										<button  id="buttonSave" onclick="sqSave();">
										��  �� 
										</button>
									</logic:equal>
									<logic:equal name="doType" value="modi">
										<button  id="buttonSave" onclick="modi();">
										��  �� 
										</button>
									</logic:equal>
									<button onclick="sqPrint()">
										����ѧ����̬
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
											<td align="center" nowrap="nowrap" style='width:5%'>
												��ѯ����
											</td>
											<td align="center" nowrap="nowrap" style='width:5%'>
												��ѯʱ��
											</td>
											<td align="center" nowrap="nowrap" style='width:5%'>
												��ѯ��ʦ
											</td>
											<td align="center" nowrap="nowrap" style='width:10%'>
												��ѯ��¼
											</td>
											<td align="center" nowrap="nowrap" style='width:5%'>
												�Ƿ������ѯ
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

