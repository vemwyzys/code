const path = require("path");

//定义js打包规则
module.exports = {
    //入口函数，从哪里开始进行编译打包
    entry: "./src/main.js",
    //编译成功后把内容输出到哪里去
    output: {
        //定义输出的指定目录__dirname当前项目根目录，dist文件夹
        path: path.resolve(__dirname, "./dist"),
        //合并的js文件存储在dist/bundule.js文件中
        filename: "bundle.js"
    },
    module:{
        rules:[{
            test:/\.css$/,//把项目中所有的.css文件进行打包
            use:["style-loader","css-loader"]
        }]
    }
}