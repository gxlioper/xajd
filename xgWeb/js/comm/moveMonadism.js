/*
* ����ʵ�ֱ�����ݵ��ڲ��϶�
* ȷ���м���Ȳ�Ĵ��ڣ�idΪָ��
*/
/*--------ȫ�ֱ���-----------*/
var dragedTable_x0, dragedTable_y0, dragedTable_x1, dragedTable_y1;
var dragedTable_movable = false;
var dragedTable_preCell = null;
var dragedTable_normalColor = null;
//��ʼ��Ԫ�����ɫ
var dragedTable_preColor = "lavender";
//Ŀ�굥Ԫ�����ɫ
var dragedTable_endColor = "#FFCCFF";
var dragedTable_movedDiv = "dragedTable_movedDiv";
var dragedTable_tableId = "";
/*--------ȫ�ֱ���-----------*/
function DragedTable(tableId) {
	dragedTable_tableId = tableId;
	var oTempDiv = document.createElement("div");
	oTempDiv.id = dragedTable_movedDiv;
	oTempDiv.onselectstart = function () {
		return false;
	};
	oTempDiv.style.cursor = "hand";
	oTempDiv.style.position = "absolute";
	oTempDiv.style.border = "1px solid black";
	oTempDiv.style.backgroundColor = dragedTable_endColor;
	oTempDiv.style.display = "none";
	document.body.appendChild(oTempDiv);
	document.all(tableId).onmousedown = showDiv;
}
//�õ��ؼ��ľ���λ��
function getPos(cell) {
	var pos = new Array();
	var t = cell.offsetTop;
	var l = cell.offsetLeft;
	while (cell = cell.offsetParent) {
		t += cell.offsetTop;
		l += cell.offsetLeft;
	}
	pos[0] = t;
	pos[1] = l;
	return pos;
}
//��ʾͼ��
function showDiv() {
	var obj = event.srcElement;
	var pos = new Array();
	//��ȡ����ͼ��
	var oDiv = document.all(dragedTable_movedDiv);
	if (obj.tagName.toLowerCase() == "td"  
		&& obj.className != "noMove") {
		obj.style.cursor = "hand";
		pos = getPos(obj);
		//�����м���Ȳ�λ�ã���ֵ
		oDiv.style.width = obj.offsetWidth;
		oDiv.style.height = obj.offsetHeight;
		oDiv.style.top = pos[0];
		oDiv.style.left = pos[1];
		oDiv.innerHTML = obj.innerHTML;
		oDiv.style.display = "";
		dragedTable_x0 = pos[1];
		dragedTable_y0 = pos[0];
		dragedTable_x1 = event.clientX;
		dragedTable_y1 = event.clientY;
		//��סԭtd
		dragedTable_normalColor = obj.style.backgroundColor;
		obj.style.backgroundColor = dragedTable_preColor;
		dragedTable_preCell = obj;
		dragedTable_movable = true;
	}
}
function dragDiv() {
	if (dragedTable_movable) {
		var oDiv = document.all(dragedTable_movedDiv);
		var pos = new Array();
		oDiv.style.top = event.clientY - dragedTable_y1 + dragedTable_y0;
		oDiv.style.left = event.clientX - dragedTable_x1 + dragedTable_x0;
		var oTable = document.all(dragedTable_tableId);
		for (var i = 0; i < oTable.cells.length; i++) {
			if (oTable.cells[i].tagName.toLowerCase() == "td" && oTable.cells[i].className != "noMove") {
				pos = getPos(oTable.cells[i]);
				if (event.x > pos[1] && event.x < pos[1] + oTable.cells[i].offsetWidth && event.y > pos[0] && event.y < pos[0] + oTable.cells[i].offsetHeight) {
					if (oTable.cells[i] != dragedTable_preCell) {
						oTable.cells[i].style.backgroundColor = dragedTable_endColor;
					}
				} else {
					if (oTable.cells[i] != dragedTable_preCell) {
						oTable.cells[i].style.backgroundColor = dragedTable_normalColor;
					}
				}
			}
		}
	}
}
function hideDiv() {
	if (dragedTable_movable) {
		var oTable = document.all(dragedTable_tableId);
		var pos = new Array();
		if (dragedTable_preCell != null) {
			for (var i = 0; i < oTable.cells.length; i++) {
				pos = getPos(oTable.cells[i]);
				//�������λ�ã��Ƿ���ĳ����Ԫ��ķ�Χ֮��
				if (event.x > pos[1] && event.x < pos[1] + oTable.cells[i].offsetWidth && event.y > pos[0] && event.y < pos[0] + oTable.cells[i].offsetHeight) {
					if (oTable.cells[i].tagName.toLowerCase() == "td" && oTable.cells[i].className != "noMove") {
						//�����ı�
						dragedTable_preCell.innerHTML = oTable.cells[i].innerHTML;
						oTable.cells[i].innerHTML = document.all(dragedTable_movedDiv).innerHTML;
						//���ԭ��Ԫ���Ŀ�굥Ԫ�����ʽ
						dragedTable_preCell.style.backgroundColor = dragedTable_normalColor;
						oTable.cells[i].style.backgroundColor = dragedTable_normalColor;
						oTable.cells[i].style.cursor = "";
						dragedTable_preCell.style.cursor = "";
						dragedTable_preCell.style.backgroundColor = dragedTable_normalColor;
					}
				}
			}
		}
		dragedTable_movable = false;
		//�����ʾͼ��
		document.all(dragedTable_movedDiv).style.display = "none";
	}
}
document.onmouseup = function () {
	hideDiv();
	if(dragedTable_movable){
		var oTable = document.all(dragedTable_tableId);
		for (var i = 0; i < oTable.cells.length; i++) {
			oTable.cells[i].style.backgroundColor = dragedTable_normalColor;
		}
	}
};
document.onmousemove = function () {
	dragDiv();
};

