
//map reduce
var arr = [1, 2, 3, 4, 5, 6];
function pow(x) {
    return x * x;
}
console.log(arr.map(pow));//返回一个数组

var strArr = arr.map(String);
console.log(strArr);

var sum = arr.reduce(function (x, y) {
    return x + y;
});
console.log(sum);

function string2int(s) {
    var charArr = [];
    for (var c of s) {
        charArr.push(c);
    }
    console.log(charArr);
    var numArr = charArr.map(function (c) {
        return c * 1;
    });
    console.log(numArr);
    return numArr.reduce(function (x, y) {
        console.log(x, y);
        return x * 10 + y;
    });
}

function normalize(arr){

    

    return [];
}