/**
 * 
 * 函数参数
 */
//方式1
function abs(x) {
    if (x >= 0) {
        return x;
    } else {
        return -x;
    }
}

//方式2
var abs1 = function (x) {
    if (x >= 0) {
        return x;
    } else {
        return -x;
    }
};

//调用参数个数随意
abs(1);   //ok
abs(1, 2);  //ok
abs();     //ok  it will return NaN；

//判断参数类型
function judge(x) {
    if (typeof x != 'number') {
        throw 'Not a number';
    }
}

//arguments是函数内附送的参数列表
function implicateArguments(x, y) {
    console.log(x, y);
    console.log(arguments);
    for (var i = 0; i < arguments.length; i++) {
        console.log(`arg ${i} ${arguments[i]}`);
    }
}

//即使不定义形式参数，也能得到运行时的参数
function abs() {
    if (arguments.length === 0) {
        return 0;
    }
    var x = arguments[0];
    return x >= 0 ? x : -x;
}

//rest 指代其他参数  列表
function rest(a, b, ...rest) {
    console.log(a);
    console.log(b);
    console.log(rest);
}

function area_of_circle(r, pi) {
    console.log(r);
    console.log(pi);
    if (r === 0) { return 0; }
    if (pi === undefined) { pi = 3.14; }
    return Math.pow(r, 2) * pi;
}

// 测试:
if (area_of_circle(2) === 12.56 && area_of_circle(2, 3.1416) === 12.5664) {
    console.log('测试通过');
} else {
    console.log('测试失败');
}

/**
 * 变量作用域
 */
//全局对象绑定到window对象上
var cource = "abc";
console.log(cource);
console.log(window.cource);

//为了避免全局下函数和冲突，可以使用名字空间namespace
var MYAPP = {};
MYAPP.name = 'myapp';
MYAPP.version = 1.0;
MYAPP.foo = function () {
    return 'foo';
}

//局部作用域
function varFunction() {
    for (var varI = 0; varI < 100; varI++) {
        //
    }
    varI += 100;//ok 依然可用，没有仅仅局限于for循环中
}

function letFunction() {
    for (let letI = 0; letI < 100; letI++) {
        //
    }
    letI += 100; //not ok.    let使letI仅仅在for块中被定义
    console.log(letI);//Uncaught ReferenceError;
}


//常量，不可以被修改
const PI = 3.14;


//解构赋值//
////传统赋值
var arrayEqual = ['hello', 'js', 'es6'];
var x = arrayEqual[0];
var y = arrayEqual[1];
var z = arrayEqual[2];
console.log(x, y, z);
//解构赋值
var [x1, y1, z1] = ['hello', 'js', 'es6'];
console.log(x1, y1, z1);

var [x2, [y2, z2]] = ['a', ['b', 'c']];
console.log(x2, y2, z2);

var person1 = {
    name: 'nc',
    age: 20,
    gender: 'male',
    address: {
        city: 'hz',
        zipcode: '11111'
    }
}
//解构赋值获得对象属性, 记得变量名与属性名相同
var { name, age, gender } = person1;
console.log(name, age, gender);
//也可以嵌套
var { name, address: { city, zipcode } } = person1;
console.log(name, city, zipcode);
//若undefined，可以赋值默认值
var { name, single = true } = person1;
console.log(name, single);
//使用:修改赋予不同于属性名的参数名
var { name, age: ageNum } = person1;
console.log(name, ageNum);

//交换变量将不再需要temp变量
var xxx = 1, yyy = 2;
[xxx, yyy] = [yyy, xxx];

