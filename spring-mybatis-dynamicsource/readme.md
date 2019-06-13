### simple demo for mybatis configuration

#### 步骤

创建数据库 master 和 slave
在数据库中执行sql

    create table user(
      id int not null  auto_increment primary key,
      name varchar(50) not null,
      salary DECIMAL(20, 2),
      status varchar(20) not null,
      birth_date DATE,
      last_login_time TIMESTAMP not null
    )ENGINE=InnoDB DEFAULT CHARSET=utf8;

master用于新增数据
salve用于查询数据


- 查询
在slave中执行sql


    insert into user(name,salary,status,birth_date,last_login_time)
    values ('张三', 10000.0, 'NORMAL', curdate() ,current_timestamp());

浏览器中访问

    http://localhost:8080/user/list
    
    [{"id":1,"name":"张三","birthDate":[2019,6,13],"salary":10000.00,"status":"NORMAL","lastLoginTime":[2019,6,13,12,9,39]}]
    
- 新增

    
    POST /user HTTP/1.1
    Host: localhost:8080
    Content-Type: application/json
    User-Agent: PostmanRuntime/7.11.0
    Accept: */*
    Cache-Control: no-cache
    Postman-Token: 322d0527-0f37-4863-9422-7b4860d0f274,56906d00-0f83-4272-b2bb-e721909b3205
    Host: localhost:8080
    cookie: access_token=CQf4WBjUHEXnHlsGsOaZRdyZpzVdJfl7rFs4IhwPI5yifuuKJiV3UECFyJXkAp9B+5s0i3mfk5h0Ofxi4ky3IdGnhyRPC26G/pj4viem39nOLrDKtPPo4ZkeUApGSaks/X2L0biK53TesZVgwmye4+NTzPsqEkhzwJ/sN3vUrQBF1DX56AXrFwq+4upBH+srkVd2XaZ/Ne5Op34TUjfFOQ==
    accept-encoding: gzip, deflate
    content-length: 112
    Connection: keep-alive
    cache-control: no-cache
    
    {"name":"哈哈","salary":2000,"status":"NORMAL","lastLoginTime":"2010-12-04 12:00:00","birthDate":"2000-10-10"}
    
 数据添加到了master数据库

