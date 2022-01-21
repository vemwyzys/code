// 工厂模式，可以批量生产对象，但没有解决生产出来的对象没有标识的问题
function createPerson(name, age, job) {
    let o = new Object();
    o.name = name;
    o.age = age;
    o.job = job;
    o.sayName = function () {
        console.log(this.name);
    }
    return o;
}
let person1 = createPerson("aa", 12, {
    title: "engineer"
});
console.log(person1);

// 构造函数模式
// 与工厂模式的区别：无显式的创建对象，属性方法直接赋值给this，没有return
// 如果不使用new Person()的方式调用，则内部的this指向为global
function Person(name, age, job) {
    this.age = age;
    this.name = name;
    this.job = job;
    // 等价于 this.sayName = new Function(console.log("say my name: "+this.name);)
    // 每新建一个person会新创建一个Function实例
    this.sayName = function () {
        console.log("say my name: "+this.name);
    }
}

let personbb = new Person("bb", 19, 'engineer');
console.log(personbb);
// 获取构造器
let ct = personbb.constructor;
console.log(ct);
// 使用构造器创建对象
let personCC = new ct('cc', 12, 'job');
console.log(personCC);

let test = Person("test", 19, 'job');
console.log(test);

console.log(personbb instanceof Person);
console.log(personCC instanceof Person);

// 构造函数也是函数
// 不使用new调用则添加到window 或者 global
Person('name',0,'job');
global.sayName();

// 使用call()/apply()，将特定对象指定为作用域
let deviant = {};
Person.call(deviant,'deviant', 17, 'job');
deviant.sayName();
let deviant2 = {};
Person.apply(deviant2,['deviant2', 17, 'job']);
deviant2.sayName();

// 因为构造函数内的sayName函数每个都是新的Function实例，所以false
console.log(personbb.sayName===personCC.sayName); // false
// 改造函数到外面
function sayName(){
    console.log(this.name);
}
function Person(age){
    this.age = age;
    this.sayName = sayName;
}
// sayName()函数相同
console.log(new Person().sayName===new Person().sayName);
//test cherrypcik