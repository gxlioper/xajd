/*******************************************************************************
 * ********************����������*******\
 * 
 * @author cola.loadDatamachine qq:371452875 mail:zzw1986@gmail.com
 * @param data
 * @returns \
 ******************************************************************************/
var DateGrid = function(data) {
	// ����
	this.reader = data.reader;
	this.porxy = data.proxy;
	this.weekName = data.weekName || ['����', '��һ', '�ܶ�', '����', '����', '����', '����' ];
	this.headerClass = '';
	//this.height = 600;// table�ĸ߶�
	this.width = "100%";// table�Ŀ��
	this.operator = data.operator;
	this.event = data.event;
	this.td_onclick = '';
	this.tableClass = 'datetab datetab_jy';
	this.preMonthClass = 'last';
	this.divClass = 'con';
	this.nextMothClass = 'last';
	this.todayClass = '';//������ʾ�������ʽ tdcon
	this.div_eventbar_class = 'poprcap clearall';
	this.event_timeClass = '#de0303';
	this.selectDayClass = '';
	this.overClass = '';
	this.start_time = '8:00';
	this.end_time = '18:00';
	this.offset_time = 240;
	//this.timesect_name = [ '����', '����', '����' ];
	this.timesect_name = '';
	// {'08:00~12:00':'����','08:00~12:00':'����','12:00~17:00':'����','17:00~22:00':'����'};
	// ����
	this.eventItem = [];
	this.style = data.style;// Ĭ��
	this.secttime = [];
	this.startday = '';
	this.virtualDay = new Date();
	this.today = new Date();
	this.father = {};
};
/** *********************************action***************************************** */
DateGrid.prototype.toMonthView = function() {
	this.style = 'month';
	this.repaint();
};
DateGrid.prototype.toWeekView = function() {
	this.style = 'week';
	this.repaint();
};
DateGrid.prototype.toDayView = function() {
	this.style = 'day';
	this.repaint();
};

DateGrid.prototype.render = function(domid) {
	this.father = document.getElementById(domid);
	this.repaint();

};
DateGrid.prototype.clearAllEventsInTable = function() {
	// ���¼ܹ�
	this.eventItem = {};
	var table = null;
	if (this.style == 'month') {
		table = this.getMonthTable();
	} else if (this.style == 'week') {
		table = this.getWeekTable();
	} else if (this.style == 'day') {
		table = this.getDayTable();
	}
	this.father.innerHTML = '';
	this.father.appendChild(table);
};

DateGrid.prototype.load = function(data) {
	// �Ȱ�ԭ�����������
//	this.clearAllEventsInTable();
//	this.eventItem = data;
	
};

DateGrid.prototype.goPre = function() {
	if (this.style == 'month') {
		this.virtualDay.setMonth(this.virtualDay.getMonth() - 1);
	} else if (this.style == 'week') {
		this.virtualDay.setDate(this.virtualDay.getDate() - 7);
	} else if (this.style == 'day') {
		this.virtualDay.setDate(this.virtualDay.getDate() - 1);
	}
	this.repaint();
};
DateGrid.prototype.goNext = function() {
	if (this.style == 'month') {
		this.virtualDay.setMonth(this.virtualDay.getMonth() + 1);
	} else if (this.style == 'week') {
		this.virtualDay.setDate(this.virtualDay.getDate() + 7);
	} else if (this.style == 'day') {
		this.virtualDay.setDate(this.virtualDay.getDate() + 1);
	}
	this.repaint();
};
/** *********************************view***************************************** */

DateGrid.prototype.getEventDiv = function(eventData) {
	var id = eventData[this.reader.id];
//	var title = eventData[this.reader.title];
//	var date = eventData[this.reader.date];
//	var time = eventData[this.reader.time] == null ? "����"
//			: eventData[this.reader.time];
//	var dateend = eventData[this.reader.dateend];
//	var timeend = eventData[this.reader.timeend];
//	var content = eventData[this.reader.content];
//	var url = eventData[this.reader.url];
//	var editurl = eventData["editurl"];
//	var viewurl = eventData["viewurl"];
//	var deleteurl = eventData["deleteurl"];
//	var color = eventData["color"];

	var div_eventbar = document.createElement("div");
	if (this.event.length > 0) {
		// div_eventbar.style.position="absolute";
//		div_eventbar.onmouseout = function() {
//			document.getElementById(id).style.display = 'none';
//		};
//		div_eventbar.onmousemove = function() {
//			// var div = document.getElementById(this);
//			var info = getInfo(this);
//			var left = info.left;
//			if (left < 100) {
//				div.className = 'popLayerLeft';
//			} else {
//				div.className = 'popLayer';
//			}
//			div.name = "popLayer";
//			document.getElementById(id).style.display = '';
//		};
	}
//	div_eventbar.className = this.div_eventbar_class;
//	var label_title = document.createElement("label");
//	label_title.style.backgroundColor = color;
//	if (title.length > 10)
//		title = title.substr(0, 5) + '...';
//	label_title.innerHTML = title;
//	div_eventbar.appendChild(label_title);
//
//	var div = document.createElement("div");
//	div_eventbar.appendChild(div);
//
//	div.style.display = 'none';
//	div.id = id;
//
//	div.className = 'popLayer';
//	div.onmousemove = function() {
//
//		var div = document.getElementById(id);
//
//		div.style.display = '';
//
//	};
//	div.onmouseout = function() {
//		document.getElementById(id).style.display = 'none';
//	};
//
//	var div3 = document.createElement("div");
//	div.appendChild(div3);
//	div3.className = 'popcon';
//	var span_time = document.createElement("span");
//	if (timeend == null) {
//		span_time.innerHTML = time;
//	} else {
//		span_time.innerHTML = time + '-' + timeend;
//	}
//
//	var div_button = document.createElement("div");
//	div3.appendChild(div_button);
//	div_button.className = 'but_pop';
//
//	var span_title = document.createElement("span");
//	span_title.className = 'poptit';
//	div3.appendChild(span_time);
//	div3.appendChild(span_title);
//
//	if (content != null) {
//		span_title.innerHTML = content;
//	}
//
//	if (this.event.length > 0) {
//		var event = this.event;
//
//		for ( var i = 0; i < event.length; i++) {
//			var button = document.createElement("button");
//			button.innerHTML = event[i]["name"];
//			div_button.appendChild(button);
//			button.onclick = event[i]["oper"];
//		}
//
//	}
//
	return div_eventbar;
};

DateGrid.prototype.getDayTable = function() {
	this.startday = this.formatDate(this.virtualDay);
	this.endday = this.startday;
	if (this.secttime.length == 0) {
		this.secttime = this.get();
	}

	// ����table
	var table = document.createElement("table");
	table.className = this.tableClass;
	this.father.innerHTML = "";
	// this.father.appendChild(table);
	// ����thead
	var thead = document.createElement("thead");
	var tr = document.createElement("TR");
	var td = document.createElement("td");
	td.innerHTML = "ʱ��";
	tr.appendChild(td);

	var td = document.createElement("td");
	td.innerHTML = (this.virtualDay.getMonth() + 1) + '/'
			+ this.virtualDay.getDate() + '('
			+ this.weekName[this.virtualDay.getDay()] + ')';

	// +GetLunarDateString(this.virtualDay);
	tr.appendChild(td);

	thead.appendChild(tr);

	table.appendChild(thead);
	// ����tbody
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);

	var tr = document.createElement("TR");
	var th = document.createElement("th");
	th.innerHTML = "����";
	tr.appendChild(th);

	var td = document.createElement("td");
	td.id = this.formatDate(this.virtualDay);
	tr.appendChild(td);
	tbody.appendChild(tr);

	for ( var i = 0; i < (this.secttime.length - 1); i++) {
		// ����tr
		var tr = document.createElement("tr");
		tbody.appendChild(tr);

		// ����th
		var th = document.createElement("th");

		th.width = 75;
		//th.innerHTML = this.timesect_name[i];
		// +this.formatTime(this.secttime[i])+'-'+this.formatTime(this.secttime[i+1])
		tr.appendChild(th);
		// ����td

		var td = document.createElement("td");
		td.style.height = this.height / this.secttime.length;
		var ss = this.formatDateTime(this.virtualDay, this.secttime[i],
				this.secttime[i + 1]);
		;
		td.id = this.formatDateTime(this.virtualDay, this.secttime[i],
				this.secttime[i + 1]);
		tr.appendChild(td);
		var fun = this.operator.add;
		if (fun) {
			td.attachEvent("ondblclick", function() {
				fun(event.srcElement.id);
			});
			// alert(this.operator.add);
		}
		// ��һ������������ �ڶ��������ǿ�ʼʱ�� �����������ǽ���ʱ��
	}
	return table;
};
DateGrid.prototype.getWeekTable = function() {
	if (this.secttime.length == 0) {
		this.secttime = this.getTimesect();
	}

	var data = this.getThisWeekViewDate(this.virtualDay);
	var weekViewDates = data[0];
	this.startday = this.formatDate(weekViewDates[0]);
	this.endday = this.formatDate(weekViewDates[6]);
	// ����table
	var table = document.createElement("table");
	table.className = this.tableClass;

	// ����thead
	var thead = document.createElement("thead");
	var tr = document.createElement("TR");
	var td = document.createElement("td");
	tr.appendChild(td);
	td.innerHTML = 'ʱ��';
	for ( var i = 0; i < 7; i++) {
		var td = document.createElement("td");
		td.width = "100";
		td.innerHTML = (weekViewDates[i].getMonth() + 1) + '/'
				+ weekViewDates[i].getDate() + '(' + this.weekName[i] + ')';// +GetLunarDateString(weekViewDates[i]);
		tr.appendChild(td);

	}
	thead.appendChild(tr);
	table.appendChild(thead);
	// ����tbody
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	var tr = document.createElement("tr");
	var th = document.createElement("th");
	th.innerHTML = "����";
	tr.appendChild(th);
	for ( var j = 0; j < 7; j++) {

		var td = document.createElement("td");
		td.id = this.formatDate(weekViewDates[j]);
		tr.appendChild(td);

		var fun = this.operator.add;
		if (fun) {
			td.attachEvent("ondblclick", function() {
				fun(event.srcElement.id);
			});
			// alert(this.operator.add);
		}
	}
	tbody.appendChild(tr);
	// this.father.appendChild(table);
	for ( var i = 0; i < (this.secttime.length - 1); i++) {
		// ����tr
		var tr = document.createElement("tr");
		tbody.appendChild(tr);
		// ����th
		var th = document.createElement("th");
		// th.innerHTML=this.secttime[i].getHours()+":"+this.secttime[i].getMinutes()+"-"+this.secttime[i+1].getHours()+":"+this.secttime[i+1].getMinutes()
		// ;
		//th.innerHTML = this.timesect_name[i];
		// th.innerHTML=this.formatTime(this.secttime[i])+'-'+this.formatTime(this.secttime[i+1]);
		tr.appendChild(th);
		// ����td
		for ( var j = 0; j < 7; j++) {
			var td = document.createElement("td");
			td.id = this.formatDateTime(weekViewDates[j], this.secttime[i],
					this.secttime[i + 1]);
			tr.appendChild(td);
			td.style.height = this.height / this.secttime.length;
			var fun = this.operator.add;
			if (fun) {
				td.attachEvent("ondblclick", function() {
					fun(event.srcElement.id);
				});
				// alert(this.operator.add);
			}
		}

	}
	return table;
};
DateGrid.prototype.getMonthTable = function() {
	if (this.secttime.length == 0) {
		this.secttime = this.getTimesect();
	}
	var data = this.getThisMonthViewDate(this.virtualDay);
	var monthViewDates = data[0];
	this.startday = this.formatDate(monthViewDates[0][0]);
	this.endday = this.formatDate(monthViewDates[5][6]);

	// ����table
	var table = document.createElement("table");
	    table.className = this.tableClass;
	// ����thead
	var thead = document.createElement("thead");
	    table.appendChild(thead);
	    
	var tr = document.createElement("tr");
	for ( var i = 0; i < this.weekName.length; i++) {

		var td = document.createElement("td");
		var span = document.createElement("span");
			span.className="size14 bold";
			span.innerHTML = this.weekName[i];
			td.appendChild(span);
		tr.appendChild(td);
	}
	thead.appendChild(tr);
	// ����tbody
	var tbody = document.createElement("tbody");
	table.appendChild(tbody);
	// ����tr
	for ( var i = 0; i < monthViewDates.length; i++) {
		var tr = document.createElement("tr");// this.table.insertRow();
		tbody.appendChild(tr);
		
		/*if (this.reader["sjdHTML"] != ""){
			var firstTd = document.createElement("th");
			firstTd.innerHTML = this.reader["sjdHTML"];
			tr.appendChild(firstTd);
		}*/
		
		for ( var j = 0; j < monthViewDates[i].length; j++) {
			// ����td
			var td = document.createElement("td");// tr.insertCell();
				td.className="last";
			var div = document.createElement("div");
			var temp_date = new Date();
			temp_date = monthViewDates[i][j];
			div.id = this.formatDate(temp_date);
			var fun = this.operator.add;

			div.className = this.divClass;
			div.innerHTML = "<p>"+temp_date.getDate()+"</p>";
			//����ʾũ��
			//div.innerHTML = temp_date.getDate() + GetLunarDateString(temp_date);
			td.appendChild(div);
			tr.appendChild(td);
			if (temp_date < data[1]) {
				td.className = this.preMonthClass;
			} else if (temp_date >= data[2]) {
				td.className = this.nextMothClass;
			} else if (this.formatDate(temp_date) == this.formatDate(data[3])) {
				td.className = this.todayClass;
			} else {
			}
		}
	}
	;
	return table;
};
// data

// conf

/** *********************************tool***************************************** */

DateGrid.prototype.getId = function(date, time) {
	var temp_date = new Date();
	temp_date.setYear(parseInt(date.split("-")[0]));
	var month = date.split("-")[1];
	if (month.charAt(0) == '0')
		month = month.charAt(1);
	temp_date.setMonth(parseInt(month) - 1);
	var day = date.split("-")[2];
	if (day.charAt(0) == '0')
		day = day.charAt(1);
	temp_date.setDate(parseInt(day));
	var temp_id = '';
	var hour = time.split(':')[0];
	var minute = time.split(':')[1];
	if (hour.charAt(0) == '0')
		hour = hour.charAt(1);
	if (minute.charAt(0) == '0')
		minute = minute.charAt(1);
	var temp_time = new Date(this.secttime[0]);
	temp_time.setHours(parseInt(hour));
	temp_time.setMinutes(parseInt(minute));
	for ( var i = 0; i < (this.secttime.length - 1); i++) {
		if (this.formatTime(this.secttime[i]) <= this.formatTime(temp_time)
				&& this.formatTime(temp_time) < this
						.formatTime(this.secttime[i + 1])) {
			temp_id = this.formatDateTime(temp_date, this.secttime[i],
					this.secttime[i + 1]);
			break;
		}
	}
	return temp_id;
};
// ʱ��Ƭ��
DateGrid.prototype.getTimesect = function() {
	var secttime = new Array();
	var start_time = new Date();
	start_time.setHours(parseInt(this.start_time.split(":")[0]));
	start_time.setMinutes(parseInt(this.end_time.split(":")[1]));
	start_time.setSeconds(0);
	var end_time = new Date();
	end_time.setHours(parseInt(this.end_time.split(":")[0]));
	end_time.setMinutes(parseInt(this.end_time.split(":")[1]));
	end_time.setSeconds(0);
	var distance = parseInt(this.offset_time);
	var temp_time = new Date(start_time);
	var i = 0;
	while (temp_time < end_time) {
		secttime[i] = new Date(temp_time);
		temp_time.setMinutes(temp_time.getMinutes() + distance);
		temp_time.setSeconds(0);
		i++;
	}
	secttime[secttime.length] = new Date(temp_time);
	return secttime;
};
// һ������
DateGrid.prototype.getThisWeekViewDate = function(_date) {

	var data = new Array();
	var temp_date = new Date(_date);
	// �õ������ڵĵ�һ��
	var weekViewDates = new Array();
	temp_date.setDate(temp_date.getDate() - temp_date.getDay());
	for ( var i = 0; i < 7; i++) {
		weekViewDates[i] = new Date(temp_date);
		temp_date.setDate(temp_date.getDate() + 1);
	}
	data[0] = weekViewDates;
	data[1] = new Date();
	return data;
};
// �õ�6*7��ȫ����������
DateGrid.prototype.getThisMonthViewDate = function(_date) {
	var data = new Array();
	var temp_date = new Date(_date);
	temp_date.setDate(1);
	var monthViewDates = new Array();
	var num_days_between = temp_date.getDay();
	temp_date.setDate(temp_date.getDate() - num_days_between);// the begnning
																// of month
	for ( var i = 0; i < 6; i++) {
		monthViewDates[i] = new Array();
		for ( var j = 0; j < 7; j++) {
			monthViewDates[i][j] = new Date(temp_date);
			temp_date.setDate(temp_date.getDate() + 1);
		}
	}
	data[0] = monthViewDates;
	data[1] = new Date(_date);
	data[1].setDate(1);
	data[2] = new Date(data[1]);
	data[2].setMonth(data[1].getMonth() + 1);
	data[3] = new Date();
	return data;
};

// ����
DateGrid.prototype.repaint = function() {
	var table = null;
	if (this.style == 'month') {
		table = this.getMonthTable();
	} else if (this.style == 'week') {
		table = this.getWeekTable();
	} else if (this.style == 'day') {
		table = this.getDayTable();
	}
	this.father.innerHTML = '';
	this.father.appendChild(table);
};
DateGrid.prototype.refresh = function() {
	this.repaint();
};

DateGrid.prototype.formatDate = function(date) {
	var str = '';
	str = date.getFullYear()
			+ '-'
			+ (date.getMonth() < 9 ? ('0' + (date.getMonth() + 1)) : (date
					.getMonth() + 1)) + '-'
			+ (date.getDate() < 10 ? ('0' + date.getDate()) : date.getDate());

	return str;
};
DateGrid.prototype.formatTime = function(time) {
	var str = '';
	str = ''
			+ (time.getHours() < 10 ? ('0' + time.getHours()) : time.getHours())
			+ ':'
			+ (time.getMinutes() < 10 ? ('0' + time.getMinutes()) : time
					.getMinutes());
	return str;
};
DateGrid.prototype.formatDateTime = function(date, starttime, endtime) {
	var str = this.formatDate(date) + '/' + this.formatTime(starttime) + '-'
			+ this.formatTime(endtime);
	return str;
};

DateGrid.prototype.show = function(str) {
	document.getElementById(str).style.display = '';
};
DateGrid.prototype.hide = function(str) {
	document.getElementById(str).style.display = 'none';
};

var LunarDaysOfMonth = new Array(0xd4a8, 0xd4a0, 0xda50, 0x5aa8, 0x56a0,
		0xaad8, 0x25d0, 0x92d0, 0xc958, 0xa950, // 2001-2010
		0xb4a0, 0xb550, 0xb550, 0x55a8, 0x4ba0, 0xa5b0, 0x52b8, 0x52b0, 0xa930,
		0x74a8, // 2011-2020
		0x6aa0, 0xad50, 0x4da8, 0x4b60, 0x9570, 0xa4e0, 0xd260, 0xe930, 0xd530,
		0x5aa0, // 2021-2030
		0x6b50, 0x96d0, 0x4ae8, 0x4ad0, 0xa4d0, 0xd258, 0xd250, 0xd520, 0xdaa0,
		0xb5a0, // 2031-2040
		0x56d0, 0x4ad8, 0x49b0, 0xa4b8, 0xa4b0, 0xaa50, 0xb528, 0x6d20, 0xada0,
		0x55b0 // 2041-2050
);

// ����LunarLeapYear���ũ��2001�굽2050�����µ��·ݣ���û����Ϊ0���Ӹߵ��ͣ�ÿ�ֽڴ�����
var LunarLeapYear = new Array(0x40, 0x02, 0x07, 0x00, 0x50, // 2001-2010
0x04, 0x09, 0x00, 0x60, 0x04, // 2011-2020
0x00, 0x20, 0x60, 0x05, 0x00, // 2021-2030
0x30, 0xb0, 0x06, 0x00, 0x50, // 2031-2040
0x02, 0x07, 0x00, 0x50, 0x03 // 2041-2050
);

// ����ũ��iLunarYear��������·ݣ���û���򷵻�0
function GetLeapMonth(iLunarYear) {
	var Leap = LunarLeapYear[(iLunarYear - 2001) >> 1];
	return (((iLunarYear - 2001) & 1) == 0) ? (Leap >> 4) : (Leap & 0x0f);
}

// ����ũ��iLunarYer��iLunarMonth�µ������������һ��������
// ���iLunarMonth�������£� ����Ϊ0������Ϊ���µ�����
// ���iLunarMonth�����£� ����Ϊ��һ���µ�����������Ϊǰһ���µ�����
function LunarMonthDays(iLunarYear, iLunarMonth) {
	var High;
	var Low;
	var Bit;

	High = 0;
	Low = 29;
	Bit = 16 - iLunarMonth;
	if ((iLunarMonth > GetLeapMonth(iLunarYear))
			&& (GetLeapMonth(iLunarYear) > 0))
		Bit--;
	if ((LunarDaysOfMonth[iLunarYear - 2001] & (1 << Bit)) > 0)
		Low++;
	if (iLunarMonth == GetLeapMonth(iLunarYear)) {
		High = ((LunarDaysOfMonth[iLunarYear - 2001] & (1 << (Bit - 1))) > 0) ? 30
				: 29;
	}

	return Low + (High << 16);
}

// ����ũ��iLunarYear���������
function LunarYearDays(iLunarYear) {
	var Days;
	var tmp;

	Days = 0;
	for ( var i = 1; i <= 12; i++) {
		tmp = LunarMonthDays(iLunarYear, i);
		Days = Days + ((tmp >> 16) & 0xffff); // ȡ��λ
		Days = Days + (tmp & 0xffff); // ȡ��λ
	}

	return Days;
}

// ��ũ��iLunarYear���ʽ������ɵ�֧���귨��ʾ���ַ���
function FormatLunarYear(iLunarYear) {
	var szText1 = new String("���ұ����켺�����ɹ�");
	var szText2 = new String("�ӳ���î������δ�����纥");
	var strYear;

	strYear = szText1.substr((iLunarYear - 4) % 10, 1);
	strYear = strYear + szText2.substr((iLunarYear - 4) % 12, 1)+"��";

	return strYear;
}

// ��ũ��iLunarMonth�¸�ʽ����ũ����ʾ���ַ���
function FormatLunarMonth(iLunarMonth) {
	var szText = new String("�������������߰˾�ʮ");
	var strMonth;

	if (iLunarMonth <= 10) {
		strMonth = szText.substr(iLunarMonth - 1, 1);
	} else if (iLunarMonth == 11)
		strMonth = "ʮһ";
	else
		strMonth = "ʮ��";

	return strMonth + "��";
}

// ��ũ��iLunarDay�ո�ʽ����ũ����ʾ���ַ���
function FormatLunarDay(iLunarDay) {
	var szText1 = new String("��ʮإ��");
	var szText2 = new String("һ�����������߰˾�ʮ");
	var strDay;
	if ((iLunarDay != 20) && (iLunarDay != 30)) {
		strDay = szText1.substr((iLunarDay - 1) / 10, 1)
				+ szText2.substr((iLunarDay - 1) % 10, 1);
	} else if (iLunarDay != 20) {
		strDay = szText1.substr(iLunarDay / 10, 1) + "ʮ";
	} else {
		strDay = "��ʮ";
	}

	return strDay;
}

// ����������ת��Ϊũ�����ڣ�����ũ����ʾ���ַ���
function GetLunarDateString(SolarDate) {
	var tmp;
	var iLunarYear;
	var iLunarMonth;
	var iLunarDay;
	var Leap = false;
	var MinMilli = 1000 * 60;
	var HrMilli = MinMilli * 60;
	var DyMilli = HrMilli * 24;

	// ��2001��1��1�����𣬸����Ĺ��������Ѿ���ȥ������
	// 11323��1970��1��1�յ�2001��1��1��֮�����������ΪDate�Ǵ�1970��1��1����Ϊ����
	var iSpanDays = Math.round(SolarDate.getTime() / DyMilli) - 11323;

	// ����2001��1��24��Ϊũ��2001�����³�һ����23��
	if (iSpanDays < 23) {
		iYear = 2000;
		iLunarMonth = 12;
		iLunarDay = iSpanDays + 7;
	} else {
		// ��ũ��2001�����³�һ����
		iSpanDays = iSpanDays - 23;
		iLunarYear = 2001;
		iLunarMonth = 1;
		iLunarDay = 1;

		// ����ũ����
		tmp = LunarYearDays(iLunarYear);
		while (iSpanDays >= tmp) {
			iSpanDays -= tmp;
			iLunarYear++;
			tmp = LunarYearDays(iLunarYear);
		}

		// ����ũ����
		tmp = LunarMonthDays(iLunarYear, iLunarMonth) & 0xffff; // ȡ����
		while (iSpanDays >= tmp) {
			iSpanDays -= tmp;
			if (iLunarMonth == GetLeapMonth(iLunarYear)) // �����������
			{
				tmp = LunarMonthDays(iLunarYear, iLunarMonth) >> 16; // ȡ����
				if (iSpanDays < tmp) {
					Leap = (tmp > 0) ? true : false; // ���µĺ���£�
					break;
				}
				iSpanDays = iSpanDays - tmp;
			}

			iLunarMonth++;
			tmp = LunarMonthDays(iLunarYear, iLunarMonth) & 0xffff; // ȡ����
		}

		// ����ũ����
		iLunarDay += iSpanDays;
	}

	return /*
			 * FormatLunarYear(iLunarYear) + (Leap ? "��" : "") +
			 * FormatLunarMonth(iLunarMonth) +
			 */FormatLunarDay(iLunarDay);
}
function show(url) {
	// showDivWindow(url,800,400,'');
	var width = "770";
	var height = "480";
	var scrolling = "auto";
	tipsWindown("", "id:text", width,height, "true", "", "true", "id", url,
			height, scrolling);
	// tipsWindown("","id:text",700,450,"true","","true","id",url,450,'auto');
}

function getInfo(o) {// ȡ������
	var to = new Object();
	to.left = to.right = to.top = to.bottom = 0;
	var twidth = o.offsetWidth;
	var theight = o.offsetHeight;
	while (o != document.body && o != null) {

		to.left += o.offsetLeft;
		to.top += o.offsetTop;
		o = o.offsetParent;
	}
	to.right = to.left + twidth;
	to.bottom = to.top + theight;
	return to;
}


function goToday(){
	dd.virtualDay=new Date(dd.today);
	dd.refresh();
	jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'��'+(dd.virtualDay.getMonth()+1)+'��');
	
	loadData();
}
function goPre(){
	dd.goPre();
	jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'��'+(dd.virtualDay.getMonth()+1)+'��');
	loadData();
	
}
function goNext(){
	dd.goNext();
	jQuery('#curMonth').html(dd.virtualDay.getFullYear()+'��'+(dd.virtualDay.getMonth()+1)+'��');
	loadData();
}