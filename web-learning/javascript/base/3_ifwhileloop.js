/**
 * JavaScript把null、undefined、0、NaN和空字符串''视为false，
 * 其他值一概视为true，因此上述代码条件判断的结果是true
 */
//for for array;
var arr = [1, 2, 'name', true];
for (var i = 0; i < arr.length; i++) {
    console.log(arr[i]);
}
for (var i in arr) {
    console.log(i);//the index, warning! the i is a string!
    console.log(arr[i]);
}

//for with judge
var x = 0;
for (; ;) {
    if (x > 100) {
        break;
    }
    x++;
    console.log(x);
}

//for for the properties in an object.
var o = {
    name: 'Jack',
    age: 18,
    city: 'beijing'
}
for (var key in o) {
    console.log(key);
}
for (const key in o) {
    if (Object.hasOwnProperty.call(o, key)) {
        const element = o[key];
        console.log(element);
    }
}

// while and doWhile
var num = 5;
while (num > 0) {
    console.log(num--);
}
do {
    console.log(num++);
} while (num < 5);