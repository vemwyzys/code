/**
 * js‘s object is unordered collection data type
 * composed fo key-value
 */
var xiaoming = {
    name: 'nick',
    birth: 1990,
    school: 'No.1 Middle School',
    'middle-school': 'sth',
    height: 1.70,
    weight: 65,
    score: null
}
//get the value fo the object.
var birth = xiaoming.birth;
var middleSchool = xiaoming['middle-school'];

//delete the properties.
delete xiaoming["middle-school"];
delete xiaoming.height;

//judge if the object has some properties.
'name' in xiaoming;  //true;
'grade' in xiaoming; //false;

//even some properties inherit from super.
'toStirng' in xiaoming;
//or use the hasOwnProperty() to exclude the inherits;
xiaoming.hasOwnProperty('name'); //true;
xiaoming.hasOwnProperty('toString');//false; 

/**
 * 创建对象
 */
//工厂函数模式
function createPerson(name, age) {
    let o = new Object();
    o.name = name;
    o.age = age;
    o.sayName = function () {
        console.log(o.name);
    }
    return o;
}
let person0 = createPerson('0', 19);
person0.sayName();
//工厂模式缺点，没有类型标识,类型是Object

//构造函数模式
function Person(name, age) {
    this.name = name;
    this.age = age;
    this.sayName = function () {
        console.log(this.name);
    }
}
let personA = new Person('a', 12);
personA.sayName();
console.log(personA.constructor == Person);//true


//构造函数2
let Person = function (name, age) {
    this.name = name;
    this.age = age;
    this.sayName = function () {
        console.log(this.name);
    }
}
let personVar1 = new Person('Var1', 18);

//prototype parttern
function Person() {
    //共享变量
    Person.prototype.name = 'nick';
    Person.prototype.job = 'engineer';

    //共享函数
    Person.prototype.sayName = function () {
        console.log(this.name);
    }
}
let person1 = new Person;
let person2 = new Person;
person1.sayName();
person2.sayName();

console.log(person1.sayName === person2.sayName);