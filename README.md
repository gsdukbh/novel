# novel 



## 关于

`novel` 是一个小说阅读工具

## 程序主要流程


[项目uml](doc/项目uml.mdj) startUML 项目文件

## 如何构建你的证书

```bash

openssl genrsa -out server.pem 2048

openssl rsa -in server.pem -pubout -out public.pem
openssl pkcs8 -topk8 -inform pem -in  server.pem -outform pem -nocrypt -out private.pem
```
