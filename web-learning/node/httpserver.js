const http = require('http');

http.createServer(function (request, response) {
    response.writeHead(200, { 'Content-type': 'text/plain' });
    response.end("hello server!!!");
}).listen(8888);
console.log('success')
