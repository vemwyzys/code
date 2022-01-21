// 属性的类型
// 数据属性
let person1 = {
    name: "nick"
}

let person = {};
Object.defineProperty(person, "name", {
    configurable: false,
    enumerable: false,
    writable: false,
    value: "wss"
});

//访问器属性
let book = {
    year_: 2017,
    edition: 1
};

//year被定义为访问器属性
Object.defineProperty(book, "year", {
    get() {
        return this.year_;
    },
    set(newValue) {
        if (newValue > 2017) {
            this.year_ = newValue;
            this.edition += newValue - 2017;
        }
    }
});
// book.year
// 2017
// book.year = 2018
// 2018
// book.edition
// 2
// book.year =2222
// 2222
// book.edition
// 207

//所有数据属性 configurable=false，enumerable=false
let book2 = {};
Object.defineProperties(book2, {
    year_: {
        value: 2017
    },
    edition: {
        value: 1
    },
    year: {
        get() {
            return this.year_;
        },
        set(newValue) {
            if (newValue > 2017) {
                this.year_ = newValue;
                this.edition += newValue - 2017;
            }
        }
    }
});


//合并对象
let dest, src, result;
src = {
    id: 'src'
}
dest = {}
result = Object.assign(dest, src)
console.log(result)
console.log(dest)

//合并对象 将src的本地属性们一起复制到目标对象上
dest = {
    set a(val) {
        console.log(`Invoked dest setter with param ${val}`);
    }
};
src = {
    get a() {
        console.log('Invoked src getter');
        return 'foo';
    }
};
let descripters = Object.getOwnPropertyDescriptors(src)
Object.assign(dest, src);
console.log(dest);

//对象的相等判定 使用Object.is()
//recursive相等判定
function recursivelyCheckEqual(x, ...rest) {
    return Object.is(x, rest[0]) &&
        (rest.length < 2 || recursivelyCheckEqual(...rest));
}
console.log(recursivelyCheckEqual(1, true, []));

//没有可计算属性时
const nameKey = "name";
const ageKey = "age";
let person3 = {};
person3[nameKey] = "Matt";
person3[ageKey] = 27;
//有可计算属性: 将[]内作为表达式, 而不是字符串
let person4 = {
    [nameKey]: "Matt",
    [ageKey]: 99
}

function getUniqueKey(key) {
    return `${key}_${person4.age++}`
}
let person5 = {
    [getUniqueKey(nameKey)]: "Matt2"
}
console.log(person5); // {name_99: 'Matt2'}

//简写方法名
let human = {
    sayName: function (name) {
        console.log(`My name is ${name}`);
    }
}
human.sayName('Matt')

human = {
    sayName(name) {
        console.log(`My name is ${name}`);
    }
}
human.sayName('Matt')

person = {
    name_: 'matt',
    get name() {
        return this.name_;
    },
    set name(name) {
        return this.name_ = name;
    },
    age: 18
}


// 对象解构
console.log('对象解构');
let {
    name: personName,
    age: personAge
} = person;
console.log(personName);
console.log(personAge);
let {
    name,
    name_,
    age
} = person;
console.log(name + " " + name_ + " " + age);

// 解构且赋予默认值
({
    name,
    grade = 5
} = person);
console.log(grade);

let anotherName, anotherAge;

({
    name: anotherName,
    age: anotherAge
} = person)

//嵌套结构
person = {
    name: "aa",
    age: 28,
    job: {
        title: "engineer"
    }
}
//将person.job.title -> anotherTitle
let anotherTitle;
({
    job: {
        title: anotherTitle
    }
} = person)


//将person.job.title -> title
let {
    job: {
        title
    }
} = person;
console.log(title);