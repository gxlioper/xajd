<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/TybZhszcpService.js'></script>
<script type="text/javascript">
var count = 1;
var max = 0;

//������������������������
function trAdd(the_tab,type) {
	var len = document.getElementById(the_tab).rows.length+1;
    var num = $("numAdd").value;
    var doType = $("doType").value;
    var cellfu = new Array();
	
    count=len;  
    //�Զ����ֶη�װ�ɵ�JSON�����ַ���
    var jsonStr = $('jsonStr').value;
	var array = new Array();
	if (jsonStr != null && jsonStr != "") {
		array = jsonStr.split("!@");
	}	
	
	//���Զ����ֶη�װ�ɵ�JSON�����ַ���ת��JSON���󲢴����array��
	var cellfu = new Array(array.length);
	//Ĭ�ϵ�һ��Ϊ�����
	cellfu[0] = function(data){return "<input type='hidden' style='width:100%'  name='_xuh' id='xuh"+count+"'>"+'' + "<input type='checkbox' name='cbv' value='"+count+"' />" ;};
	
	//ѭ������ÿ����������
	for(var i=0;i<array.length;i++){
		if(array[i] != null && array[i] != ""){
			var htmlStr = "";
			var obj = eval('(' + array[i] + ')');
			htmlStr = scHtmlCode(obj, max);
			cellfu[i + 1] = htmlStr;
		}
	}
	
	//ͨ��DWR����һ��
       max++;
       DWRUtil.addRows(the_tab,[''],cellfu,{escapeHtml:false});
		
	   //���ֶ�Ϊ�����б�ʱ���������б�����	
       loadSelectData(array, max-1);   
}

//����������
function moreTrAdd(the_tab,type) {
	var num = $("numAdd").value;
	if(num==""||num==null){	
           return false;
       }
       for(i=count;i<=num;i++){         
          trAdd(the_tab,type);
          max++;
          
          count++;
       }
       $("numAdd").value = "";
}

//����ɾ��������
function trDel(the_tab) {
	var tabobj = document.getElementById(the_tab);
    var length = tabobj.rows.length;   
    if(length==0){
       return false;
    }
    
    var checkbox = document.getElementsByName('cbv');
    var checkArray = new Array();
    var size = 0
    for(i=0;i<checkbox.length;i++){
    	if(checkbox[i].checked == true){
    		checkArray[size++] = eval(checkbox[i].value);
    	}	
    }
    
    if(checkArray.length == 0){
    	alert('��û��ѡ��Ҫ�����ļ�¼����ѡ��');
    	return false;
    }
    	
   
    if(confirm('ȷ��Ҫɾ��ѡ�еļ�¼��')){
    	for(i=0;i<checkArray.length;i++){
    		tabobj.deleteRow(checkArray[i]-1);
    		for(j=0;j<checkArray.length;j++){
    			checkArray[j] += -1;
    		}
   	 }
    
   	 for(i=0;i<checkbox.length;i++){
    		checkbox[i].value=i+1;
  	  }
    }
}

//����ɾ��������
function trDelAll(the_tab) {
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
            length--;
         }
       for(i=1;i<=num;i++){
          length--;
          tabobj.deleteRow(tabobj.rows.length-1);
       }
    }
    $("numDel").value = "";
}

function inputNumPd(obj) {
	var str = "";
	if (obj != null && obj.sfnum == '1') {
		str = " onkeyup='ckinpdataonlynum(this)' ";
	}
	return str;
}

function inputCheckValue(obj) {
	var xzf = obj.xzf;
	if (obj != null && xzf != null && xzf != "") {
		return " onblur='equalsMax(this,"+xzf+")'";
	}
	return "";
}

function equalsMax(obj, xzf) {
	var val = obj.value;
	val = parseFloat(val);
	if (val > xzf) {
		alert("����ֵ���ܴ���:" + xzf);
		obj.value = "";
		return false;
	}
	return true;
}

//ͨ���������ݷ����ַ�
function scHtmlCode(obj, max) {
	var htmlStr = "";
	if (obj != null) {
		if (obj.zdlx == '001') {//�ı���
			htmlStr = "<input type='text' style='width:90px' name='";
			htmlStr += obj.zdid;
			htmlStr += "' id='";
			htmlStr += obj.zdid;
			htmlStr += max;
			htmlStr += "' maxlength='";
			htmlStr += obj.zdcd;
			htmlStr += "'";
			htmlStr += inputNumPd(obj);
			htmlStr += inputCheckValue(obj);
			htmlStr += " />";
		} else if (obj.zdlx == '002') {//ʱ��
			htmlStr = "<input type='text' name='";
			htmlStr += obj.zdid;
			htmlStr += "' id='";
			htmlStr += obj.zdid;
			htmlStr += max;
			htmlStr += "' readonly='true' style='cursor:hand;' style='width:70px' onclick='return showCalendar("+"\""+obj.zdid + max+"\"" +",\"y-mm-dd\");' onblur='dateFormatChg(this)'";
			htmlStr += "/>";
		} else if (obj.zdlx == '003') {//������
			htmlStr = "<select name='";
			htmlStr += obj.zdid;
			htmlStr += "' id='";
			htmlStr += obj.zdid;
			htmlStr += max;
			htmlStr += "'>";
			htmlStr += "<option value='' ></option>";
			htmlStr += "</select>";
		} else if (obj.zdlx == '004') {//���ı�
			htmlStr = "<input type='text' name='";
			htmlStr += obj.zdid;
			htmlStr += "' id='";
			htmlStr += obj.zdid;
			htmlStr += max;
			htmlStr += "' maxlength='";
			htmlStr += obj.zdcd;
			htmlStr += "'";
			htmlStr += inputNumPd(obj);
			htmlStr += inputCheckValue(obj);
			htmlStr += " />";
		} else if (obj.zdlx == '005') { //�ı���
			htmlStr = "<textarea  name='";
			htmlStr += obj.zdid;
			htmlStr += "' id='";
			htmlStr += obj.zdid;
			htmlStr += max;
			htmlStr += "' rows='2";
			htmlStr += "' onblur='chLeng(this,"+"\""+obj.zdcd+"\""+")' />";
		} 
	}
	return htmlStr;
}



//�����Զ����ֶ������б�����
function loadSelectData(idArray, num) {
		for (var i=0;i<idArray.length;i++) {
			if (idArray[i] != null && idArray[i] != "") {
				
				var jsonObj = eval('(' + idArray[i] + ')');
				if (jsonObj.zdlx=='003') {//�ֶ�����Ϊ�����б�
					
					//�����б��ֶ�ID
					var objId = jsonObj.zdid + num;	
				    dwr.engine.setAsync(false);
				    
				    //ͨ��DWR���������б�����
					TybZhszcpService.queryZdyzdSelectData(jsonObj.zdid, jsonObj.tabname,function (data) {
						DWRUtil.removeAllOptions(objId);
				     	DWRUtil.addOptions(objId,data,"dm","mc");	
					}); 
					dwr.engine.setAsync(true); 
				}	
			}
		}
}

function saveData() {
		
		var len = document.getElementById('flag').rows.length;
		
		if (len == 0) {
			alert("������Ҫ��������ݣ�");
			return false;
		}
		
		var jsonStr = document.getElementById('jsonStr').value;
		if (jsonStr != null && jsonStr != '') {
			var array = jsonStr.split("!@");

			for (var i=0;i<array.length;i++) {
				if (array[i] != null && array[i] != "") {
					
				var obj = eval('(' + array[i] + ')');

				//�ǿ��ֶ����ǿռ��	
				if (obj.sfnull == '0') {
					//�ֶ����ƶ�������
					var nameArr = document.getElementsByName(obj.zdid);
					
					for (var j=0;j<nameArr.length;j++) {
						if (nameArr[j].value == null || nameArr[j].value == "") {
							alert("��*���ֶ�Ϊ������!");
							return false;		
						}
					}
				}
				}
			}
		}
		
		
		
		saveinfo('pjpy_tyb_zhszcpAdd.do?act=save','');
	}	
</script>
</head>
<body>
		<html:form action="/pjpyTybZhszcp" method="post">
		<input type="hidden" name="jsonStr" id="jsonStr" value="${jsonStr }"/>
		<input type="hidden" name="doType" id="doType" value="${doType }" />
		<input type="hidden" name="xh" id="xh" value="${xh }"/>
		<input type="hidden" name="bm" id="bm" value="${bm }"/>
		<input type="hidden" name="dmlb" id="dmlb" value="${dmlb}"/>
		<input type="hidden" name="dm" id="dm" value="${dm}"/>
		<input type="hidden" name="errmsg" id="errmsg" value="${errmsg }"/>
		<div class="tab">
			<logic:notEmpty name="rs">
				<p>
					<button type="button" onclick="trAdd('flag','add')" style="width: 40px" >+</button>
					<button type="button" onclick="trDel('flag')" style="width: 40px" >-</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
					
					<input type="text" name="numAdd" id="numAdd" style="width: 20px"
						onblur="moreTrAdd('flag','madd')"/>
					
					&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
					<input type="text" name="numDel" id="numDel" style="width: 20px"
						onblur="trDelAll('flag')"/>
					&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</p>
				
				<table class="formlist" width="100%" id="tTb">
					<thead>
				<tr>
					<th>
						<span>����ά��</span>
					</th>
				</tr>
			</thead>
			<tfoot>
				      <tr>
				        <td ><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <logic:notEqual value="view" name="flag">
								<button type="button" class="button2" id="btn_save"
									onclick="saveData()"
									style="width:80px">
									�� ��
								</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
							<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
								style="width:80px" id="buttonClose">
								�� ��
							</button>		
				          </div>
				        </td>
				      </tr>
				    </tfoot>
					<tbody>
					<tr>
						<td>
							<div class="mid_box">
							<div class="con_overlfow">
								<table align="center" style="width: 100%" id="rsT"
									class="formlist">
									<!-- ��ӡʱ��һ�в���ʾ- -->
									<thead style="height: 23px">
										<tr>
											<td align="center" nowrap="nowrap" style='width:5%'>
												<input type="checkbox" name="cv" id="cb" onclick="selectAll()"/>
											</td>
											<logic:iterate name="rs" id="s">
												<td align="center" nowrap="nowrap">
													${s.sfnullmc }
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<tbody width="100%" class="tbstyle" id="flag" align="center">
									</tbody>
								</table>
								</div>
							</div>
						</td>
					</tr>
					</tbody>
					
				</table>

			</logic:notEmpty>
			<logic:empty name="rs">
				<p align="center">
					���޼�¼!
				</p>
			</logic:empty>
		<script type="text/javascript">
			//�ݲ�ʵ�����ݼ���
			//window.onload=function loadData(){		
			//}
		</script>
		</html:form>

</body>

