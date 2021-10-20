/**
 * ES6新引入了Map和Set，
 * 记得要new
 */
var m = new Map([
    ['mick', 95],
    ['bob', 75],
    ['tracy', 27]
]);

m.set('Adam', 54);
m.has('Adam');
m.get('bob');
m.delete('tracy');

var s = new Set([
    1, 2, 4
]);
s.add(5);
s.delete(1);

//iterable

/**
 * iterable类型  包括Array, Set, Map
 * for(var element of iterable){}
 */
for (var i of m) {
    console.log(i)
}
var arr = [1, 2, 4, 5];
for (var i of arr) {
    console.log(i);
}
/**
 * for ... in循环由于历史遗留问题，
 * 它遍历的实际上是对象的属性名称。
 * 一个Array数组实际上也是一个对象，
 * 它的每个元素的索引被视为一个属性。

当我们手动给Array对象添加了额外的属性后，
for ... in循环将带来意想不到的意外效果：

var a = ['A', 'B', 'C'];
a.name = 'Hello';
for (var x in a) {
    console.log(x); // '0', '1', '2', 'name'
}
 */

//the better way is to use iterable.forEach
var arr1=[1,2,3,4,5];
arr1.forEach(function (element, index, array) {
    console.log(`element: ${element} , index: ${index}, array: ${array}`);
});
m.forEach(function (value, key, map) {
    console.log(`element: ${value} , index: ${key}, map: ${map}`);
});
//you can also reduce the parameter
m.forEach(function (value, key) {
    console.log(`element: ${value} , index: ${key}`);
});