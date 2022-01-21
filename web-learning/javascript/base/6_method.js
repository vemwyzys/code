var wanggang = {
    nickName: 'wg',
    birth: 1990,
    age: function () {
        var y = new Date().getFullYear();
        return y - this.birth;
    }
}

wanggang.age;
wanggang.age();

var nick = {
    nickName: 'nick',
    birth: 1998,
    age: function () {
        var that = this;
        function getAge() {
            return new Date().getFullYear() - that.birth;
        }
        return getAge();
    }
}

//无对象调用的函数使用
function anotherAge() {
    var y = new Date().getFullYear();
    console.log(this);
    //如果不是对象调用（obj.anotherAge()），this将会是window全局对象
    return y - this.birth;
}
anotherAge();

//apple & call
Math.max.apply(null, [3, 5, 4]);
Math.max.call(null, 3, 5, 4);

// 装饰器
var parseIntCount = 0;
var oldParseIntparseInt; //保留原函数
window.parseInt = function () {
    count += 1;
    //调用原函数
    return oldParseIntparseInt.apple(null, arguments);
}