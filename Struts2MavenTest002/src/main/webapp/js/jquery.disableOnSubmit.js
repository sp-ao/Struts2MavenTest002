var DisableSubmit ={
    arg: {},
    timer: 1000,

    init: function(arg, timer){
        if (typeof(arg) == "object") this.arg = arg;
        if (timer) this.timer = timer;
        this.addEvent(window, 'load', this.setEvent(), false);
        this.addEvent(window, 'unload', this.setEnable(), false);
    },

    addEvent: function(elem, eventType, fn, useCapture){
        if (elem.addEventListener){
            elem.addEventListener(eventType, fn, useCapture);
            return true;
        }
        else if (elem.attachEvent){
            var r = elem.attachEvent('on' + eventType, fn);
            return r;
        }
        else{
            elem['on'+eventType] = fn;
        }
    },

    setEvent: function(){
        var self = this;
        return function(){
            if (self.arg.name){
                for (var i = 0; i < self.arg.name.length; i++){
                    var elem = document.getElementsByName(self.arg.name[i]);
                    if (elem){
                        for (var j = 0; j < elem.length; j++){
                            self.addEvent(elem[j], 'click', self.setDisable(elem[j]), false);
                        }
                    }
                }
            }
            if (self.arg.id){
                for (var i = 0; i < self.arg.id.length; i++){
                    var elem = document.getElementById(self.arg.id[i]);
                    if (elem){
                        self.addEvent(elem, 'click', self.setDisable(elem), false);
                    }
                }
            }
        }
    },

    // history.back() 対策でページアウトするするときにボタンを再度enable化しておく
    setEnable: function(){
        var self = this;
        return function(){
            if (self.arg.name){
                for (var i = 0; i < self.arg.name.length; i++){
                    var elem = document.getElementsByName(self.arg.name[i]);
                    if (elem){
                        for (var j = 0; j < elem.length; j++){
                            elem[j].disabled = false;
                        }
                    }
                }
            }
            if (self.arg.id){
                for (var i = 0; i < self.arg.id.length; i++){
                    var elem = document.getElementById(self.arg.id[i]);
                    if (elem){
                        elem.disabled = false;
                    }
                }
            }
        }
    },

    // 二度押し防止対策でボタンを 1mm-sec 後にdisable化しておく。これで hidden データ作成しなくても get/post が正常処理される
    setDisable: function(elem){
        var self = this;
        var func = elem.onclick;
        elem.onclick = "";
        return function(evt){
            var elem = evt.srcElement || evt.target;
            window.setTimeout(function(){ elem.disabled = true; }, 1);
            if (func) window.setTimeout(function(){ func(); }, 2);
            window.setTimeout(function(){ elem.disabled = false; }, self.timer);
        }
    }
}