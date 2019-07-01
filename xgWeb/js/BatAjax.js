if (BatAjax == null) var BatAjax = {};
BatAjax._isObject = function(data) {
  return (data && typeof data == "object");
};
BatAjax._isArray = function(data) {
  return (data && data instanceof Array);
};
BatAjax._isDate = function(data) {
  return (data && data instanceof Date);
};
BatAjax._isHTMLElement = function(ele, nodeName) {
  if (ele == null || typeof ele != "object" || ele.nodeName == null) {
    return false;
  }
  if (nodeName != null) {
    var test = ele.nodeName.toLowerCase();
    if (typeof nodeName == "string") {
      return test == nodeName.toLowerCase();
    }
    if (BatAjax._isArray(nodeName)) {
      var match = false;
      for (var i = 0; i < nodeName.length && !match; i++) {
        if (test == nodeName[i].toLowerCase()) {
          match =  true;
        }
      }
      return match;
    }
    return false;
  }
  return true;
};
BatAjax._getValueFrom = function(data, method) {
  if (method == null) return data;
  else if (typeof method == 'function') return method(data);
  else return data[method];
};
BatAjax.addOptions = function(ele, data) {
  ele = $(ele);
  if (ele == null) return;
  var useOptions = BatAjax._isHTMLElement(ele, "select");
  var useLi = BatAjax._isHTMLElement(ele, ["ul", "ol"]);

  if (data == null) return;

  var text, value, li;
  if (BatAjax._isArray(data)) {
    for (var i = 0; i < data.length; i++) {
      if (useOptions) {
        if (arguments[2] != null) {
          if (arguments[3] != null) {
            text = BatAjax._getValueFrom(data[i], arguments[3]);
            value = BatAjax._getValueFrom(data[i], arguments[2]);
          }
          else text = value = BatAjax._getValueFrom(data[i], arguments[2]);
        }
        else text = value = BatAjax._getValueFrom(data[i], arguments[3]);

        if (text != null || value) ele.options[ele.options.length] = new Option(text, value);
      }
      else {
        li = document.createElement("li");
        value = BatAjax._getValueFrom(data[i], arguments[2]);
        if (value != null) {
          li.innerHTML = value;
          ele.appendChild(li);
        }
      }
    }
  }
  else if (arguments[3] != null) {
    if (!useOptions) {
      return;
    }
    for (var prop in data) {
      value = BatAjax._getValueFrom(data[prop], arguments[2]);
      text = BatAjax._getValueFrom(data[prop], arguments[3]);

      if (text || value) ele.options[ele.options.length] = new Option(text, value);
    }
  }
  else {
    if (!useOptions) {
      return;
    }
    for (var prop in data) {
      if (typeof data[prop] != "function") {
        if (arguments[2]) ele.options[ele.options.length] = new Option(prop, data[prop]);
        else ele.options[ele.options.length] = new Option(data[prop], prop);
      }
    }
  }
};
BatAjax.removeAllOptions = function(ele) {
  ele = $(ele);
  if (ele == null) return;
  var useOptions = BatAjax._isHTMLElement(ele, "select");
  var useLi = BatAjax._isHTMLElement(ele, ["ul", "ol"]);
  if (!useOptions && !useLi) {
    return;
  }
  if (useOptions) {
    ele.options.length = 0;
  }
  else {
    while (ele.childNodes.length > 0) {
      ele.removeChild(ele.firstChild);
    }
  }
};
BatAjax.addRows = function(ele, data, cellFuncs, options) {
  ele = $(ele);
  if (ele == null) return;
  if (!BatAjax._isHTMLElement(ele, ["table", "tbody", "thead", "tfoot"])) {
    return;
  }
  if (!options) options = {};
  if (!options.rowCreator) options.rowCreator = BatAjax._defaultRowCreator;
  if (!options.cellCreator) options.cellCreator = BatAjax._defaultCellCreator;
  var tr, rowNum;
  if (BatAjax._isArray(data)) {
    for (rowNum = 0; rowNum < data.length; rowNum++) {
      options.rowData = data[rowNum];
      options.rowIndex = rowNum;
      options.rowNum = rowNum;
      options.data = null;
      options.cellNum = -1;
      tr = BatAjax._addRowInner(cellFuncs, options);
      if (tr != null) ele.appendChild(tr);
    }
  }
  else if (typeof data == "object") {
    rowNum = 0;
    for (var rowIndex in data) {
      options.rowData = data[rowIndex];
      options.rowIndex = rowIndex;
      options.rowNum = rowNum;
      options.data = null;
      options.cellNum = -1;
      tr = BatAjax._addRowInner(cellFuncs, options);
      if (tr != null) ele.appendChild(tr);
      rowNum++;
    }
  }
};
BatAjax._addRowInner = function(cellFuncs, options) {
  var tr = options.rowCreator(options);
  if (tr == null) return null;
  for (var cellNum = 0; cellNum < cellFuncs.length; cellNum++) {
    var func = cellFuncs[cellNum];
    if (typeof func == 'function') options.data = func(options.rowData, options);
    else options.data = func || "";
    options.cellNum = cellNum;
    var td = options.cellCreator(options);
    if (td != null) {
      if (options.data != null) {
        if (BatAjax._isHTMLElement(options.data)) td.appendChild(options.data);
        else {
          if (BatAjax._shouldEscapeHtml(options) && typeof(options.data) == "string") {
            td.innerHTML = BatAjax.escapeHtml(options.data);
          }
          else {
            td.innerHTML = options.data;
          }
        }
      }
      tr.appendChild(td);
    }
  }
  return tr;
};

BatAjax._shouldEscapeHtml = function(options) {
  if (options && options.escapeHtml != null) {
    return options.escapeHtml;
  }
  return true;
};

BatAjax.escapeHtml = function(original) {
  var div = document.createElement('div');
  var text = document.createTextNode(original);
  div.appendChild(text);
  return div.innerHTML;
};

BatAjax._defaultRowCreator = function(options) {
  var tr = document.createElement("tr");
  tr.style.cssText = "cursor:hand";
  tr.onclick = function(){
  	rowOnClick(this);
  };
  return tr;
};

BatAjax._defaultCellCreator = function(options) {
  return document.createElement("td");
};

BatAjax.removeAllRows = function(ele, options) {
  ele = $(ele);
  if (ele == null) return;
  if (!options) options = {};
  if (!options.filter) options.filter = function() { return true; };
  if (!BatAjax._isHTMLElement(ele, ["table", "tbody", "thead", "tfoot"])) {
    return;
  }
  var child = ele.firstChild;
  var next;
  while (child != null) {
    next = child.nextSibling;
    if (options.filter(child)) {
      ele.removeChild(child);
    }
    child = next;
  }
};
