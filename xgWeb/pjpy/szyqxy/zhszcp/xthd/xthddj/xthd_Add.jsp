<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript">
var count = 1;
 function trAdd(the_tab,type){
    var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    count=len;     
	var cellfu =[
	function(data){
	return count+ "<input type='hidden' style='width:80px'  name='xuh' value='xuh'>";
	},
	function(data){	    
	return "<textarea rows='3' cols='32' id='hdnr" + count + "' name='hdnr'></textarea>";
    },	
	function(data){	    
	return "<input type='text' style='width:80px' name='xthdrq' id='xthdrq" + count + "' readonly='true' onblur='dateFormatChg(this)' style='cursor:hand;' onclick='time(this.id)'>";
    },
    function(data){
    return "<input type='text' style='width:100px'  name='jldj' id='jldj" + count + "' >";
    },
    function(data){    
    	return "<select style='width:60px' id='jjf" + count + "' name='jjf'><option value='�ӷ�'>�ӷ�</option><option value='����'>����</option></select>";	 
    },
    function(data){    
    	return "<input type='text' style='width:60px' id='pf" + count + "' name='pf' onkeypress='return sztzNumInputValue(this,6,event)' onblur='onlyNumInput(this)' maxlength='10'>";	 
     },
     function(data){    
       	return "<input type='text' style='width:60px' id='xxsh" + count + "' value='δ���' readonly='true' name='xxsh' maxlength='10'>";	 
        }
	];
	
	if(type=='add'){
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
    }else{
       if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){          
          DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
          count++;
       }
       $("numAdd").value = "";
    }        
}
function time(id){
	return showCalendar(id,'y-mm-dd');
}
function trDel(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    if(confirm("ȷ��Ҫɾ����"+(length)+"�У�")){      
    	if($("xxsh"+length).value!="δ���"){
            alert("�ü�¼�Ѿ�����ز�����ˣ����ܽ���ɾ��������");   
            return false;
          }else{
            tabobj.deleteRow(tabobj.rows.length-1);    
          }  
    } 
}
function trDelAll(the_tab){
    var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;
    var num = $("numDel").value; 
    if(length==0){
       $("numDel").value = "";
       return false;     
    }
    if(num==""||num==null){	
       return false;
    }
    if(num>length){
      num = length;
    }
    if(confirm("ȷ��Ҫɾ�����"+num+"�У�")){
      	 for(i=1;i<=num;i++){           
               if($("xxsh"+length).value!="δ���"){
                 alert("���Ѿ�����ز�����˵ļ�¼�����ܽ���ɾ��������");   
                 return false;
               }
               length--;
            } 
         for(i=1;i<=num;i++){
            length--;
            tabobj.deleteRow(tabobj.rows.length-1);
         }
      }
    $("numDel").value = "";
}	
function fillValue(obj){
		var xm= "xm" + obj.id.replace("xh","");
		var bj= "bj" + obj.id.replace("xh","");
  		if(obj.value==""){
  		  return false;
  		}	
		getSztzData.getXsInfo(obj.value,function(data){
		if(data!=null){
			$(xm).value=data[0];
			$(bj).value=data[1];
		}else{
			alert("��ѧ�Ų����ڣ���������ȷѧ�ţ�");
			obj.value="";
			obj.focus();
			}
		});

}
function ztbhSave(the_tab){
   var tabobj = document.getElementById(the_tab);
    var rowLen = tabobj.rows.length;
    var xh ="";
    var xm ="";
    var bj = "";
    var bhzt ="";
    var bhnr = "";
    var bhbx = "";
    var fs = "";
    var bz = "";
    var xn = "";
    var xq = "";
    if($("xh")){
        xh = $("xh").value; 
        if(xh==""){
            alert("ѧ�Ų���Ϊ�գ�");
            return false;
        }
     }
    if($("xn")){
       xn = $("xn").value; 
       if(xn==""){
           alert("ѧ�겻��Ϊ�գ�");
           return false;
       }
    }
    if($("xq")){
       xq = $("xq").value;
       if(xq==""){
           alert("ѧ�ڲ���Ϊ�գ�");
           return false;
       }
    }
    var pkValue = xn+xq+xh;
    var nullCout = 0;
    if(rowLen == 0){
		alert("ѧ�Ż�������Ϊ�գ���");
		return false;
	}
    for(var i=1;i<=rowLen;i++){
 	   if($("hdnr"+i)){
			if($("hdnr"+i).value == ""){
				alert("��"+i+"��,����ݲ���Ϊ�գ���ȷ��");
				return false;
				}
			if($("hdnr"+i).value.length>300){
				alert("��"+i+"��,�������������ֻ����300������ȷ��");
				return false;
			}
 		}
			if($("xthdrq"+i)){
    			if($("xthdrq"+i).value == ""){
    				alert("��"+i+"��,���ڲ���Ϊ�գ���ȷ��");
    				return false;
    			}
			}
 }
        refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_dshdAdd&xxk=wthd&doType=save');
         $("buttonSave").disabled=true;
}

function onShow(){
	var xh = $("xh").value;
	var xn = $("xn").value;
	var xq = $("xq").value;
	if(xh != ""){
		dwr.engine.setAsync(false);
		getSzPjpyDAO.dao_getXsXthdDwr(xh,xn,xq,"wthd",function(data){
			if(data !=null && data.length>0){
				$("numAdd").value=data.length;
				trAdd('flag','madd');    
				for(var i=1;i<=data.length;i++){
					if($("hdnr"+i)){
						$("hdnr"+i).value = data[i-1].hdnr;
					}
					if($("xthdrq"+i)){
						$("xthdrq"+i).value = data[i-1].xthdrq;
					}
					if($("jldj"+i)){
						$("jldj"+i).value = data[i-1].jldj;
					}
					if($("jjf"+i)){
						$("jjf"+i).value = data[i-1].jjf;
					}
					if($("pf"+i)){
						$("pf"+i).value = data[i-1].pf;
					}
					if($("xxsh"+i)){
						$("xxsh"+i).value = data[i-1].xxsh;
					}
				}
			}		
		});
		dwr.engine.setAsync(true);
	}
}

		
jQuery(function(){
	onShow();
})

	</script>
	<body>
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/xthd/xthddj/xthd_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ۺ�����-ѧ���ۺ��������ɿ�-����¼��
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
<%--				<thead>--%>
<%--					<tr style="height: 23px">--%>
<%--						<td colspan="4" align="center">--%>
<%--							˼��������������ʷ�����--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--				</thead>--%>
				<tr style="height: 23px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<logic:notEqual name="userType" value="stu" scope="session">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />

							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:notEqual>
						<logic:equal name="userType" value="stu" scope="session">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
						</logic:equal>
					</td>
					  <td align="right">
						������
					</td>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
				    <td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
							<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
				    <td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					<p>
						<!-- ��ѯ�õ�����������ʾ���� -->
						<input  value="+" onclick="trAdd('flag','add')" />
						<input  value="-" onclick="trDel('flag')" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
						<input type="text" name="numAdd" id="numAdd" style="width: 50px"
							onblur="trAdd('flag','madd')">
						&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
						<input type="text" name="numDel" id="numDel" style="width: 50px"
							onblur="trDelAll('flag')">
						&nbsp;��
					</p>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
				<tr>
						<td>
					<div class="mid_box">
							<table align="center" style="width: 100%" id="rsT" class="tbstyle">
								<!-- ��ӡʱ��һ�в���ʾ- -->
								<thead style="height: 23px">
									<tr>
										<td nowrap="nowrap">
											���
										</td>
										<td nowrap="nowrap">
											�����<font color="red">(��300����)</font>
										</td>
										<td nowrap="nowrap">
											����
										</td>
										<td nowrap="nowrap">
											�����ȼ�
										</td>
										<td nowrap="nowrap">
											�Ӽ���
										</td>
										<td nowrap="nowrap">
											����
										</td>
										<td nowrap="nowrap">
											ѧУ���
										</td>
									</tr>
								</thead>
								<tbody width="100%" class="tbstyle" id="flag">
<%--									<logic:notEmpty name="rs">--%>
<%--										<logic:iterate name="rs" id="s">--%>
<%--											<tr>--%>
<%--												<td>--%>
<%--													<input type="hidden" name='_xuh'--%>
<%--														value="<bean:write name="s" property="xuh"/>">--%>
<%--													<bean:write name="s" property="xuh" />--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<input type='text' style='width: 80px'--%>
<%--														id="xh <bean:write name="s" property="xuh"/>" name='_xh'--%>
<%--														maxlength='20'--%>
<%--														value="<bean:write name="s" property="xh"/>"--%>
<%--														onblur='fillValue(this)'>--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<input type='text' style='width: 50px'--%>
<%--														id="xm <bean:write name="s" property="xuh"/>" name='_xm'--%>
<%--														value="<bean:write name="s" property="xm"/>"--%>
<%--														readonly='true'>--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<input type='text' style='width: 90px'--%>
<%--														id="bj <bean:write name="s" property="xuh"/>" name='_bj'--%>
<%--														value="<bean:write name="s" property="bj"/>"--%>
<%--														readonly='true'>--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<input type='text' style='width: 120px' name='_bhzt'--%>
<%--														value="<bean:write name="s" property="bhzt"/>">--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<textarea rows='3' type='text' style='width: 120px'--%>
<%--														name='_bhnr'>--%>
<%--														<bean:write name="s" property="bhnr" />--%>
<%--													</textarea>--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<input type='text' style='width: 100px' name='_bhsj'--%>
<%--														id='bhsj<bean:write name="s" property="xuh"/>'--%>
<%--														value="<bean:write name="s" property="bhsj"/>"--%>
<%--														onblur="dateFormatChg(this)" style="cursor:hand;"--%>
<%--														onclick="time(this.id)">--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<textarea rows='3' style='width: 120px' name='_bhbx'>--%>
<%--														<bean:write name="s" property="bhbx" />--%>
<%--													</textarea>--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<input type='text' style='width: 50px' name='_fs'--%>
<%--														value="<bean:write name="s" property="fs"/>" maxlength='5'>--%>
<%--												</td>--%>
<%--												<td>--%>
<%--													<textarea style='width: 90px' name='_bz' rows="3">--%>
<%--														<bean:write name="s" property="bz" />--%>
<%--													</textarea>--%>
<%--												</td>--%>
<%--											</tr>--%>
<%--										</logic:iterate>--%>
<%--									</logic:notEmpty>--%>
								</tbody>				
							</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonSave" onclick="ztbhSave('flag');" style="width: 80px">
						�� ��
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="buttonClose" onclick="window.dialogArguments.document.getElementById('search_go').click();window.close();" style="width: 80px" id="buttonClose">
						�� ��
					</button> </span>
			</div>
			<logic:present name="done">
				<logic:equal value="yes" name="done">
					<script>
	alert('�����ɹ���');
</script>
				</logic:equal>
				<logic:equal value="no" name="done">
					<script>
	alert('����ʧ�ܣ�');
</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
